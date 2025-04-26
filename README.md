# CRM Fondamental en Console
Description  

Ce projet est un CRM (Customer Relationship Management) basique fonctionnant en console.
Il permet de gérer les données des clients (ajout, modification, suppression, recherche) ainsi que l’historique des interactions et des rappels de suivi.

# Fonctionnalités

. Ajouter un client (nom, prénom, téléphone, email)  
. Afficher tous les clients ou rechercher un client spécifique  
. Modifier les informations d’un client existant  
. Supprimer un client  
. Enregistrer des interactions (notes, date)  
. Planifier des rappels de suivi  
. Recherche avancée (par nom, dernière interaction, rappel à venir)  
. Interface en ligne de commande intuitive et simple  

# Technologies Utilisées

Langage : Java 17
IDE : IntelliJ IDEA
Base de données : SQLite via JDBC
Librairies Java :
. java.sql
. java.util.Scanner
. java.time.LocalDate

# Architecture :

MVC (Model - View - Controller)  
POO (Programmation Orientée Objet)  

# Architecture du projet

Model : Client, Interaction, Rappel  
Controller : ClientController, InteractionController, RappelController  
View : ConsoleView  
Database : DatabaseManager (gestion des connexions et scripts SQL)  

# Configurer la base de données :

Le projet utilise une base SQLite.  
Le fichier de base sera automatiquement créé lors du premier lancement.
