# MyAnimeList Randomizer

### How to run:

- Have a Client ID (register one at MAL website);
- Download the .zip;
- Create a folder inside `src/main` called `resources` that should contain the file `application.properties` with: 

```
client.id=YOUR_CLIENT_ID
```

- Run `src/main/java/com/victorfernandesneto/malrandomizer/MalRandomizerApplication.java`.

### How to request

When live, request by sending get to either `/anime` or `/manga` with the "username" header.

### Future improvements

I'll probably create a feature to only randomize Plan To Watch/Read anime/manga but I'm focusing in dockerize and deploy it to AWS.


### Contact

Feel free to fork, pull request or send me a message/e-mail so we can work together.
