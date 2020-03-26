# Java implementation of "Rock-Paper-Scissors" game.

Spring boot REST implementation of "Rock-Paper-Scissors" game. In a nutshell the rules are:: at the same time, two players display one of three symbols: a rock, paper, or scissors. A rock beats scissors, scissors beat paper by cutting it, and paper beats rock by covering it. [Wiki page](https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors)
![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Rock-paper-scissors.svg/1024px-Rock-paper-scissors.svg.png)
This implementation supports 3 game strategies:
* **Random** This strategy chooses next symbol randomly. This strategy works well against new user (without game history) and against experienced users who know other strategies. There is no strategy which beats this with more than 33.(3)% probability

* **Arstechnica** This strategy is offered by [arstechnica website](https://arstechnica.com/science/2014/05/win-at-rock-paper-scissors-by-knowing-thy-opponent/) . If you lose the first round, switch to the thing that beats the thing your opponent just played. If you win, don't keep playing the same thing, but instead switch to the thing that would beat the thing that you just played. In other words, play the hand your losing opponent just played.

* **Simple statistics** This strategy is based on ['Markov chains'](https://en.wikipedia.org/wiki/Markov_chain) probabilistic theory. Choose symbol which beats player's most probably choice according to historical data of games with this player and the last play metadata. E.g. Player chooses Scissors after Rock (which was chosen in the last game) with 40% probability, so choose Rock.

## Run book
You need Java 8 and Maven 3.3.9 or later

### Build
Build the project:
>mvn clean install

Quick build: 
>mvn -T4 clean install -DskipTests -Dcheckstyle.skip

### Run 
Run jar file in rock-paper-scissors-api/target directory:
>java -jar rock-paper-scissors-api-0.0.1-SNAPSHOT.jar

## Endpoints

### Play endpoint
GET /play?playerId=${playerId}&playerSymbol=${playerSymbol}
- playerSymbol - ROCK/PAPER/SCISSORS

Play one game with player.

### Game history endpoint
GET /games-history?playerId=${playerId}
Get game history.

GET /games-history/statistic?playerId=${playerId}
Get statistic of games with player.

### Ping endpoint
GET /ping
Just returns pong if application is running.

### Info endpoint
GET /actuator/info
Returns information about game version: branch and last commit info.

### Metrics endpoint
GET /actuator/metrics
Returns application's metrics.

### Health endpoint
GET /actuator/health
Returns application's health status.

### Swagger ui
GET /swagger-ui.html
Show endpoints documentation.
