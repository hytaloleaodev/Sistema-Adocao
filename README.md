
# Sistema de Adoção de Animais 🐶🐱

Projeto desenvolvido para avaliação da disciplina de **Programação Orientada a Objetos (POO)**.

----------

## 📌 Objetivo

O sistema permite gerenciar animais, adotantes e adoções utilizando conceitos fundamentais de POO, como:

-   Encapsulamento
    
-   Herança e classes abstratas
    
-   Interfaces
    
-   Polimorfismo (sobrecarga e sobrescrita)
    
-   Exceptions e regras de negócio
    
-   Persistência dos dados utilizando arquivos CSV
    
-   (Opcional) Interface gráfica com **Java Swing**
    

----------

## 📁 Persistência Utilizada

A persistência foi implementada utilizando **arquivos CSV**, localizados na pasta `/data/`.

Os repositórios fazem leitura e escrita automática após cada operação (save, delete ou update).

Arquivos utilizados:

-   `data/animais.csv`
    
-   `data/adotantes.csv`
    
-   `data/adocoes.csv`
    

O sistema possui:

-   CRUD completo para **Animal**
    
-   CRUD completo para **Adotante**
    
-   Registro de **Adoções** com regras de negócio
    
-   Atualização automática dos arquivos CSV
    

----------

## 🛠️ Tecnologias Utilizadas

-   **Java 17+**
    
-   **Swing (GUI)**
    
-   **Persistência com CSV**
    
-   **(Opcional) Maven**
    

----------

## ▶️ Como Executar o Projeto

### **1. Clonar o repositório**

Bash

```
git clone https://github.com/hytaloleaodev/Sistema-Adocao.git
cd Sistema-Adocao

```

### **2. Compilar**

Caso esteja usando o terminal:

Bash

```
javac -d bin src/**/*.java

```

### **3. Executar (modo console e GUI)**

#### ✔️ **Para rodar o sistema no modo Console:**

Bash

```
java -cp bin br.com.ongadocao.Main

```

#### ✔️ **Para rodar apenas a GUI:**

Bash

```
java -cp bin br.com.ongadocao.gui.MainGui

```

_(A execução pode mudar dependendo da estrutura do seu pacote.)_

----------

## 📂 Estrutura do Projeto (exemplo)

```
/Sistema-Adocao
 ├── /src
 │   ├── br/com/ongadocao/model
 │   ├── br/com/ongadocao/repository
 │   ├── br/com/ongadocao/service
 │   ├── br/com/ongadocao/exception
 │   ├── br/com/ongadocao/gui
 │   └── br/com/ongadocao/Main.java
 │
 ├── /data
 │   ├── animais.csv
 │   ├── adotantes.csv
 │   └── adocoes.csv
 │
 ├── README.md
 └── .gitignore

```

----------

## 📝 Autor

**Hytalo Leão**