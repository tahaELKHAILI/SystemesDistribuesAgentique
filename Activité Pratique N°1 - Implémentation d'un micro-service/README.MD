# Activité Pratique N°1 : Implémentation d'un micro-service

Ce projet s'inscrit dans le cadre du cours de **Systèmes Distribués et Architectures Agentiques**. Il illustre la mise en œuvre pratique d'une architecture orientée micro-services.

---

## 📋 Table des matières
* À propos du projet
* Architecture générale
* Prérequis
* Structure du projet
* Screenshots


---

## 🚀 À propos du projet

Cette première activité pratique a pour objectif de concevoir, développer et déployer un premier **micro-service** indépendant. Elle permet de manipuler les concepts fondamentaux de la modularité, de l'exposition d'API REST et de la persistance des données au sein d'un environnement distribué.

---

## 🏛️ Architecture générale

* **Style architectural** : Orienté micro-services
* **Communication** : API REST / HTTP
* **Modèle de données / Persistance** : Spring Data JPA / H2

---

## ⚙️ Prérequis

Avant de lancer le projet, assurez-vous d'avoir installé sur votre machine :
* **Java** (JDK 17 ou version supérieure recommandée)
* **Maven** (ou utilisation du wrapper `./mvnw`)
* **Git**
* Un client API (comme **Postman**, **Insomnia** ou `curl`) pour tester les endpoints REST.

---

## 📁 Structure du projet

```text
Activité Pratique N°1 - Implémentation d'un micro-service/
├── src/
│   ├── main/
│   │   ├── java/com/...    # Code source (Entités, Repositories, Services, Contrôleurs)
│   │   └── resources/      # Fichiers de configuration (application.properties / application.yml)
   └── test/               # Tests unitaires et d'intégration
├── pom.xml                 # Configuration Maven / Dépendances
└── README.md
```

---

## Screenshots
### Swagger
![](./screenshots/Swagger/Swagger.png)
#### Display all accounts
![](./screenshots/Swagger/Swagger_GetAll.png)
#### Display account by ID
![](./screenshots/Swagger/Swagger_GetByID.png)
#### Create account
![](./screenshots/Swagger/Swagger_CreateAccount.png)
#### Update account
![](./screenshots/Swagger/Swagger_UpdateAccount.png)
#### Delete account
![](./screenshots/Swagger/Swagger_DeleteAccount.png)

### Postman
![](./screenshots/Postman/Postman.png)
#### Display all accounts
![](./screenshots/Postman/Postman_GetAccounts.png)
#### Display account by ID
![](./screenshots/Postman/Postman_GetAccount.png)
#### Create account
![](./screenshots/Postman/Postman_CreateAccount.png)
#### Update account
![](./screenshots/Postman/Postman_UpdateAccount.png)
#### Delete account
![](./screenshots/Postman/Postman_DeleteAccount.png)

### Spring data rest
#### Display all accounts
![](./screenshots/Spring%20Data%20Rest/dataRest_AllAccounts.png)
#### Display account by ID
![](./screenshots/Spring%20Data%20Rest/dataRest_Account.png)
### Projection
![](./screenshots/Spring%20Data%20Rest/dataRest_Projction.png)
### Display account by Type
![](./screenshots/Spring%20Data%20Rest/dataRest_SearchbyType.png)

### graphql
#### Display all accounts
![](./screenshots/graphql/Graphql_listAccounts.png)
#### Create account
![](./screenshots/graphql/Graphql_AddAccount.png)
#### Update account
![](./screenshots/graphql/Graphql_UpdateAccount.png)
#### Delete account
![](./screenshots/graphql/Graphql_DeleteAccount.png)