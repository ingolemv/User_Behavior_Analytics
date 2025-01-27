import os
import torch
from transformers import AutoModelForCausalLM, AutoTokenizer, TrainingArguments, Trainer
from datasets import load_dataset

def fine_tune_model(base_model: str, dataset_path: str, output_dir: str):
    # Load model and tokenizer
    model = AutoModelForCausalLM.from_pretrained(base_model)
    tokenizer = AutoTokenizer.from_pretrained(base_model)
    
    # Load and preprocess dataset
    dataset = load_dataset('json', data_files=dataset_path)
    tokenized_dataset = dataset.map(lambda x: tokenizer(x['text'], truncation=True, padding='max_length'), batched=True)
    
    # Training configuration
    training_args = TrainingArguments(
        output_dir=output_dir,
        per_device_train_batch_size=4,
        num_train_epochs=3,
        save_steps=10_000,
        save_total_limit=2,
        logging_dir='./logs',
        logging_steps=500,
        evaluation_strategy="steps",
        eval_steps=500,
        warmup_steps=500,
        weight_decay=0.01,
        fp16=True,
        push_to_hub=False
    )
    
    # Initialize Trainer
    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=tokenized_dataset['train'],
        eval_dataset=tokenized_dataset['validation']
    )
    
    # Fine-tune the model
    trainer.train()
    
    # Save the fine-tuned model
    model.save_pretrained(output_dir)
    tokenizer.save_pretrained(output_dir)

if __name__ == "__main__":
    fine_tune_model(
        base_model="gpt2",
        dataset_path="data/training_data.json",
        output_dir="models/fine_tuned"
    ) 