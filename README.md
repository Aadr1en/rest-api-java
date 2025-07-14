REST API Java - Sans Framework
Démarrer en local
Prérequis

Java 17
Docker (pour conteneurisation)

Étapes

Compiler les fichiers Java :javac -cp src src/*.java


Exécuter l'application :java -cp src Main



Déploiement
L'image Docker peut être déployée sur des plateformes gratuites comme Render ou Railway. Assurez-vous que le port exposé est 8080 

Utilisez curl ou Postman pour tester les endpoints :

POST /api/queue : Ajouter un billet
GET /api/queue?next : Récupérer et supprimer le prochain billet
GET /api/queue?peek : Voir le billet en tête
GET /api/queue?size : Nombre de billets

structure du projet 

    rest-api-java/
    ├── src/
    │   ├── Main.java
    │   ├── FIFO.java
    │   ├── Task.java
    │   ├── TaskHandler.java
    │   ├── TaskService.java
    ├── .gitignore
    ├── build.sh
    ├── Dockerfile
    ├── README.md
