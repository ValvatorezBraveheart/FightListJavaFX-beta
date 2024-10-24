
# FightListJavaFX

This is a personal project I took on to learn more about JavaFX, mysql connection and building a project in general.

It is a dumb downed single player clone of fightlist, a mobile game.

It is a game where each round a topic is given and you have to type as many words related to that topic within 60 seconds.




## Features
Some of the extra features included:
- Connection to mysql for leaderboard
- Simple auto correct for checking answers
- Simple SFX thoughout the game

Extra stuffs to be implemented later on:
- Setting to adjust volume, size and skipping start timer option
- Account system

## Prerequisite

Before running this application, please ensure that you have the following installed on your system:

- **Java Version:** At least Java 22

### Checking Your Java Version

To check your current Java version, open a terminal or command prompt and run the following command:

```bash
java -version
```

If you have older version of java, install a new one at [Java Downloads](https://www.oracle.com/ca-en/java/technologies/downloads/)


## Installation (Without Leaderboard)

To install the project:

- **Download the executable file from [here]()** 
- **Click and play**


## Installation (With Leaderboard)


To install the project:

- **Download the executable file from [here]()** 
- **Click and play**

To set up database for the leaderboard:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/your-repo.git
   ```
2. **Create a Database**
- Open your MySQL command line or a GUI tool (like MySQL Workbench) and run the following command to create a database:
     ```sql
     CREATE DATABASE fightlist;
     ```
3. **Configure Database User**
   - Create a user and grant them privileges on your new database: (Swap out YOUR_USERNAME and YOUR_PASSWORD to whatever you desired)
     ```sql
     CREATE USER 'YOUR_USERNAME'@'localhost' IDENTIFIED BY 'YOUR_PASSWORD';
     GRANT ALL PRIVILEGES ON fightlist.* TO 'YOUR_USERNAME'@'localhost';
     FLUSH PRIVILEGES;
     ```

4. **Create a table for Leaderboard**
    - Create a table named 'leaderboard'
    ```sql
    USE fightlist;
    CREATE TABLE leaderboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    score INT
    );
    ```
);

5. **(Optional) Add Test Data**
    - Create some data in the leaderboard
    ```sql
    INSERT INTO leaderboard (username, score)
    VALUES ('lmao', '1'),
           ('lol', '2'),
           ('I dont know what name to put for the last one', '3');
    ```


5. **Run Migrations (if applicable)**
   - If your project uses migrations, run the migration command to set up the tables:
     ```bash
     # Example for Laravel
     php artisan migrate
     ```

6. Edit .env file
   - In the .env file, change the username and password to what you have in the database earlier
     ```plaintext
     DATABASE_USERNAME = "YOUR_USERNAME"
     DATABASE_PASSWORD = "YOUR_PASSWORD"
     ```

7. **Verify Connection**
   - Test the connection to ensure everything is set up correctly.
   - Simple run the project and click on the leaderboard button to see if it runs correctly

For any issues, please refer to the [MySQL documentation](https://dev.mysql.com/doc/) or check the project's issue tracker.



## Authors

- [@ValB](https://www.github.com/ValvatorezBraveheart)


## Third Party Assets

- **UI Game SFX Pack**: [SFX sound pack for the game]
  - **License**: License information not found. Please check AudioHero for clarification.
  - **Source**: [AudioHero](https://www.audiohero.com)

- **Dimbo Font**:  [Main font for the game]
  - **License**: [© 2009, 2010, 2011, 2012 GrandChaos9000. Some Rights Reserved.  (Under License by Creative Commons CC-BY-SA 3.0)
  - **Source**: [1001fonts](https://www.1001fonts.com/dimbo-font.html)
