import re
from typing import Optional

def preprocess_text(text: str) -> str:
    """Clean and preprocess input text for the model"""
    text = text.lower().strip()
    text = re.sub(r'\s+', ' ', text)  # Remove extra spaces
    text = re.sub(r'[^\w\s]', '', text)  # Remove special characters
    return text

def validate_input(text: str) -> Optional[str]:
    """Validate input text for anomaly detection"""
    if not text or not isinstance(text, str):
        return "Input must be a non-empty string"
    if len(text) > 1000:
        return "Input text is too long (max 1000 characters)"
    return None 