# MyAnimeList Randomizer

### Technologies

<p>
    <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring-3.2.5-green?style=for-the-badge&logo=spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/Docker-blue?style=for-the-badge&logo=docker&logoColor=white"/>
    <img src="https://img.shields.io/badge/AWS-yellow?style=for-the-badge&logo=amazonaws&logoColor=black"/>
</p>

### How to run:

- Have a Client ID (register one at MAL website);
- Download the .zip;
- Set your environment variable CLIENT_ID to your client ID. `export CLIENT_ID="YOUR_CLIENT ID"` (for Linux)

- Run `src/main/java/com/victorfernandesneto/malrandomizer/MalRandomizerApplication.java`.

### How to request

When live, request by sending get to either `/anime` or `/manga` with the "username" header.

### Future improvements

- [x] I want to add filters to watching, watched, dropped, plan to watch so the user can have a more complete experience. (didn't)

- [x] I'm planning on "upgrading" this project to a Spring Web service. (did it)

- [x] I'll try to debug some exceptions while I study about them a little deeper.

- [x] I'll probably create a feature to only randomize Plan To Watch/Read anime/manga but I'm focusing in dockerize and deploy it to AWS.

- [x] Deployed the app in AWS (the only CORS Allowed is the localhost).

- [x] Post a [video](https://www.youtube.com/watch?v=pcZgI7ckof0) (in Portuguese, maybe I'll sub in English) explaining the architecture and motivation.


### Contact

Feel free to fork, pull request or send me a message/e-mail so we can work together.
