# Jass Challenge 2020

This is a Java client (bot) for the [Jass challenge server](https://github.com/RFS-0/challenge).
This client allows you to easily develop a bot for the Jass challenge.

Note: This readme refers to the forked versions of the jass challenge server and the java client. 
The original server and client can be found here:
* [original jass challenge server](https://github.com/webplatformz/challenge)
* [original java client](https://github.com/webplatformz/challenge-client-java)

The forked version of the server offers a description how to deploy it as a kubernetes deployment (on GCP).
The forked version of the client offers some utility classes that should help you speed up the development of a bot.

## Getting started

1. Clone the forked server: `git clone https://github.com/RFS-0/challenge.git`
2. Clone this repository (i.e. the java client): `https://github.com/RFS-0/challenge-client-java.git`

## Running the server

To run the server execute the following commands in the `challenge` project (the server)
1. `npm install`
1. `npm run start:tournament`

This will start the jass challenge server on `localhost:3000`.

## How to create a new jass bot

1. Create an implementation of the [JassStrategy](src/main/java/com/zuehlke/jasschallenge/client/game/com.zuehlke.jasschallenge.client.strategy/JassStrategy.java) interface. This implementation will be your bot. See [RandomJassStrategy](src/main/java/com/zuehlke/jasschallenge/client/game/com.zuehlke.jasschallenge.client.strategy/RandomJassStrategy.java) or [StrongestOrWeakestStrategy](src/main/java/com/zuehlke/jasschallenge/client/game/com.zuehlke.jasschallenge.client.strategy/StrongestOrWeakestStrategy.java) for reference.
    * You might want to check out the [utils](src/main/java/com/zuehlke/jasschallenge/client/game/com.zuehlke.jasschallenge.client.strategy/utils) package, it provides some helpful classes to implement a new bot.
2. Create a new  application to run your bot. See [ch.zuehlke.com.bot.random.RandomBot](src/main/java/com/zuehlke/jasschallenge/ch.zuehlke.com.bot.random.RandomBot.java) or [ch.zuehlke.jasschallenge.bot.strongestorweakest.StrongestOrWeakestBot](src/main/java/com/zuehlke/jasschallenge/ch.zuehlke.jasschallenge.bot.strongestorweakest.StrongestOrWeakestBot.java) for reference. Just replace the values of the following attributes with your own values:
    * `BOT_NAME` -> define the name of your bot; must be unique among all bots
    * `STRATEGY` -> instantiate your implementation of the [JassStrategy](src/main/java/com/zuehlke/jasschallenge/client/game/com.zuehlke.jasschallenge.client.strategy/JassStrategy.java) interface
    * `SERVER_URL` -> once the tournament begins this has to be updated with the ip address and port of the remote jass challenge server. Set it to `ws://127.0.0.1:3000` during development.

## How to run the client

1. Run `./gradlew clean`
1. Run `./gradlew build`
1. Make sure to start the server in the tournament mode. (see below)
1. Execute the `main` method of your bot. Or if you want to run the application with the `./gradlew run` command be sure to update the `mainClassName` in [build.gradle](build.gradle).
1. Execute step 3 again. Because to form a team, two instances of your bot must be connected to the jass challenge server. Teams are formed via the bot name. This is why each team should use a unique name for their bot and you have to run your client twice to form a team.

### Example development scenario

1. You implemented a first version of your bot and now you would like to test it. For this you first start the [jass challenge server](https://github.com/RFS-0/challenge).
2. Switch to your clone of the server repository and execute `npm run start:tournament`
3. Go to `localhost:3000` and enter some user name, e.g. `user`:
![Alt text](doc/images/username.PNG?raw=true "Choose a user name")
4. Enter some tournament name, e.g. `tournament` and click enter:
![Alt text](doc/images/tournament.PNG?raw=true "Choose a user name")
5. You should now see this screen:
![Alt text](doc/images/tournament_page.PNG?raw=true "Tournament page")
6. To test against bots that use the random com.zuehlke.jasschallenge.client.strategy you have two options. Either run the main method of the [ch.zuehlke.com.bot.random.RandomBot](src/main/java/com/zuehlke/jasschallenge/ch.zuehlke.com.bot.random.RandomBot.java) twice or run `npm run bot:start` in the server repo to start 4 teams of random bots. The [ch.zuehlke.jasschallenge.bot.strongestorweakest.StrongestOrWeakestBot](src/main/java/com/zuehlke/jasschallenge/ch.zuehlke.jasschallenge.bot.strongestorweakest.StrongestOrWeakestBot.java) is only available in this version of the java client, so to evaluate your bot against it you have to run is main method twice.
7. Do not forget to execute the main method of your bot twice as well!
8. Once all the clients have connected to the server the tournament page it should look like this:
![Alt text](doc/images/tournamentReady.PNG?raw=true "Tournament ready")
9. Click start to run the tournament.
10. Click show rankings to see how well your bot performed:
![Alt text](doc/images/result.PNG?raw=true "Tournament result")

## Contributors ##
- Thanks to [fluescher](https://github.com/fluescher) for creating this skeleton.
- Thanks to [RFS-0](https://github.com/RFS-0) for implementing the StrongestOrWeakest bot, and for updating the repository to the latests versions.

