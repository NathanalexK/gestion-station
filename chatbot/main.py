import json
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel



# from solution import indicateurs_financiers, build_phrase
import google.generativeai as genai

genai.configure(api_key="")
model = genai.GenerativeModel("gemini-1.5-flash")

app = FastAPI()

origins = [
    "*",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class Prompt(BaseModel):
    prompt: str

@app.post("/generate")
def give_suggestions(prompt: Prompt):
#     indicateur = next((i for i in indicateurs_financiers if i["id"] == id), None)
#     phrase = build_phrase(indicateur, pourcentage)
#     print(phrase)

    response = model.generate_content(prompt.prompt)
    return response.text