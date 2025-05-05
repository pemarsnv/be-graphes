# Graph & Algorithm project &mdash; INSA Toulouse

This is an INSA local copy of [Mikael CAPELLE's repository](https://gitea.typename.fr/INSA/be-graphes).

## How to start?

1. On your favorite platform, create a new empty repository of yours.
  - [INSA GitEtud](https://git.etud.insa-toulouse.fr/)
  - [Gitlab](https://gitlab.com/)
  - [Bitbucket](https://bitbucket.org/)
  - [Github](https://github.com/)

2. On your computer or on an INSA computer, clone your own repository (in the following, we call it MINE)
    ```bash
    git clone (here-the-https-URL-to-your-own-repository)
    ```

3. Somewhere else on the same computer, clone this repository (we call it THEIRS)
    ```bash
    git clone https://git.etud.insa-toulouse.fr/lebotlan/BE-Graphes.git
    ```

4. In this cloned directory (THEIRS), remove the `.git` folder.

5. Your repository (MINE) is initially empty. Move all the content of the BE-Graphes project (THEIRS) into it. Keep also the .gitignore file.

6. Commit your changes (MINE):
    ```bash
    git status
    git add .
    git status    ## Check the added files
    git commit -m "My initial commit"
    git push
    ```

L'UML des objets principaux (Graph, Node, Arc, Path, RoadInformation)

https://lucid.app/lucidchart/d8f1783c-5afc-4c3e-8fa0-8c4194d0b662/edit?viewport_loc=-14%2C204%2C1993%2C915%2CHWEp-vi-RSFO&invitationId=inv_fc8753ca-0204-4463-8cea-887d1ac7f58e

L'UML des packages Algos et AlgosShortestPath

https://lucid.app/lucidchart/15a51df5-6f4d-4a53-8f23-e8330ea23e1a/edit?viewport_loc=-3807%2C-5713%2C3268%2C1417%2C0_0&invitationId=inv_1db7e098-bba9-490f-aa79-19bb63147caa
