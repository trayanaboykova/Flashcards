# 🃏 Flashcards (Java)

A console-based **flashcard learning application** that helps users study terms and definitions, track mistakes, and improve retention over time. The program supports interactive quizzes, statistics, file persistence, and detailed logging — turning simple flashcards into a powerful study tool.

Developed as part of my **[JetBrains Academy](https://www.jetbrains.com/academy/)** learning path, this project demonstrates Java collections, file I/O, program state management, and precise console interaction.

---

## 🚀 Project Overview

The Flashcards project evolves step by step into a fully featured learning application:

- **Card Management** — Add and remove flashcards with unique terms and definitions.
- **Interactive Quiz Mode** — Test knowledge by answering randomly selected cards.
- **Mistake Tracking** — Records how many errors are made for each card.
- **Statistics & Feedback** — Identifies the hardest cards and allows resetting stats.
- **File Persistence** — Import and export flashcards (including error counts).
- **Logging System** — Saves the full program input/output history to a file.
- **Command-Line Arguments** — Supports automatic import/export on startup and exit.

---

## 🎯 What I Learned

- 📚 Working with Java collections and maps to manage related data
- 💾 Reading from and writing to files safely
- 🧠 Managing application state across multiple user actions
- 🧪 Handling edge cases and enforcing data constraints
- 🧩 Building a complete application incrementally, step by step

---

## 🔧 Features

- ✔ Add, remove, import, and export flashcards  
- ✔ Randomized quiz sessions  
- ✔ Tracks mistakes per card  
- ✔ Displays hardest card(s) based on error count  
- ✔ Reset statistics at any time  
- ✔ Full application log saving  
- ✔ Command-line arguments:
  - `-import <file>`
  - `-export <file>`
- ✔ Clean and user-friendly console interface  

---

## 🛠️ Technologies Used

[![Java](https://skillicons.dev/icons?i=java&theme=light)](https://www.java.com/)

---

## 🤔 How to Run

1. Clone the repository
   ```bash
   git clone https://github.com/trayanaboykova/Flashcards.git
2. Open the project in your Java IDE (e.g., IntelliJ IDEA)
3. Compile and run Main.java
4. Optionally start with arguments:
    ```bash
    java Main -import cards.txt -export cards.txt
5. Use the interactive menu:
   ```bash
   add, remove, import, export, ask, log, hardest card, reset stats, exit

📈 Learning Outcomes
By completing this project, I:

Built a complete console application with persistent data

Combined multiple Java concepts into a cohesive system

Improved precision with strict input/output requirements

Gained confidence designing interactive CLI applications

🌟 Acknowledgments

Thanks to JetBrains Academy / Hyperskill for their structured, multi-stage approach that transforms small coding exercises into full, production-style applications — making learning both practical and rewarding.
