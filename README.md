# Sistema de Adoção de Animais 🐶🐱  
Projeto desenvolvido para avaliação da disciplina de Programação Orientada a Objetos.

---

## 📌 Objetivo
O sistema gerencia animais, adotantes e adoções, aplicando:
- Encapsulamento e modelo OO  
- Herança e classes abstratas  
- Interfaces  
- Sobrecarga e sobrescrita  
- Regras de negócio com Exceptions  
- Persistência dos dados utilizando arquivos CSV  
- (Opcional) Interface gráfica com Swing

---

## 📁 Persistência Utilizada
A persistência foi feita com **arquivos CSV**, armazenados na pasta `/data`.  
Os repositórios do projeto fazem leitura e escrita automática desses arquivos.

Arquivos utilizados:
- `data/animais.csv`
- `data/adotantes.csv`
- `data/adocoes.csv`

O sistema inclui:
- CRUD completo para Animal  
- CRUD completo para Adotante  
- Registro de Adoções com regras de negócio  
- Atualização automática do CSV após cada operação

---

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Swing (GUI)**
- **CSV para persistência**
- **Maven** *(se você estiver usando)*

---

## ▶️ Como Executar o Projeto

### **1. Clonar o repositório**
```bash
git clone https://github.com/SEU-USUARIO/SEU-REPO.git
cd SEU-REPO
