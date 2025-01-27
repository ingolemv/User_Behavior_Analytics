from transformers import pipeline
from .utils import preprocess_text
import torch

class AnomalyDetector:
    def __init__(self, model_path: str):
        self.device = 0 if torch.cuda.is_available() else -1
        self.classifier = pipeline(
            "text-classification",
            model=model_path,
            device=self.device
        )
    
    def detect_anomaly(self, text: str, threshold: float = 0.8) -> dict:
        processed_text = preprocess_text(text)
        result = self.classifier(processed_text)
        
        if result[0]['label'] == 'ANOMALY' and result[0]['score'] >= threshold:
            return {
                'is_anomaly': True,
                'confidence': result[0]['score'],
                'reason': 'LLM detected suspicious pattern'
            }
        return {
            'is_anomaly': False,
            'confidence': 1 - result[0]['score']
        }

if __name__ == "__main__":
    detector = AnomalyDetector("models/fine_tuned")
    result = detector.detect_anomaly("User accessed sensitive file from unusual location")
    print(result) 