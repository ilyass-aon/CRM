package app;

import dao.ClientDAO;
import model.Client;

import dao.InteractionDAO;
import model.Interaction;

import java.sql.Date;


import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientDAO clientDAO = new ClientDAO();

        boolean continuer = true;

        while (continuer) {
            System.out.println("\n--- MENU CRM ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher tous les clients");
            System.out.println("3. Modifier un client");
            System.out.println("4. Supprimer un client");
            System.out.println("5. Ajouter une interaction");
            System.out.println("6. Afficher l’historique d’un client");
            System.out.println("7. Modifier une interaction");
            System.out.println("8. Supprimer une interaction");
            System.out.println("9. Rechercher un client (par nom ou email)");

            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: // Ajouter
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Téléphone : ");
                    String telephone = scanner.nextLine();

                    Client nouveauClient = new Client(nom, prenom, email, telephone);
                    clientDAO.ajouterClient(nouveauClient);
                    System.out.println("------  Client ajouté !  ------");
                    break;

                case 2: // Afficher
                    List<Client> clients = clientDAO.listerClients();
                    if (clients.isEmpty()) {
                        System.out.println("   Aucun client trouvé.  ");
                    } else {
                        for (Client c : clients) {
                            System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom() + " | " + c.getEmail() + " | " + c.getTelephone());
                        }
                    }
                    break;

                case 3: // Modifier
                    System.out.print("ID du client à modifier : ");
                    int idModif = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nouveau nom : ");
                    String newNom = scanner.nextLine();
                    System.out.print("Nouveau prénom : ");
                    String newPrenom = scanner.nextLine();
                    System.out.print("Nouvel email : ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nouveau téléphone : ");
                    String newTel = scanner.nextLine();

                    Client modifClient = new Client(idModif, newNom, newPrenom, newEmail, newTel);
                    clientDAO.modifierClient(modifClient);
                    System.out.println("------  Client modifié !  ------ ");
                    break;

                case 4: // Supprimer
                    System.out.print("ID du client à supprimer : ");
                    int idSupp = scanner.nextInt();
                    scanner.nextLine();

                    clientDAO.supprimerClient(idSupp);
                    System.out.println("------ Client supprimé .  ------");
                    break;
                case 5:
                    System.out.print("ID du client : ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Note : ");
                    String note = scanner.nextLine();

                    System.out.print("Date (AAAA-MM-JJ) : ");
                    String dateStr = scanner.nextLine();
                    Date date = Date.valueOf(dateStr);

                    Interaction nouvelleInteraction = new Interaction(clientId, note, date);
                    InteractionDAO interactionDAO = new InteractionDAO();
                    interactionDAO.ajouterInteraction(nouvelleInteraction);
                    System.out.println("------ Interaction Ajouter  ------");
                    break;
                case 6:
                    System.out.print("ID du client pour voir l'historique : ");
                    int clientIdHist = scanner.nextInt();
                    scanner.nextLine();

                    InteractionDAO daoHist = new InteractionDAO();
                    List<Interaction> interactions = daoHist.listerInteractionsParClient(clientIdHist);

                    if (interactions.isEmpty()) {
                        System.out.println("    Aucune interaction trouvée.   ");
                    } else {
                        for (Interaction i : interactions) {
                            System.out.println("ID: " + i.getId() + " | Date: " + i.getDate() + " | Note: " + i.getNote());
                        }
                    }
                    break;
                case 7:
                    System.out.print("ID de l'interaction à modifier : ");
                    int idInteraction = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nouvelle note : ");
                    String newNote = scanner.nextLine();

                    System.out.print("Nouvelle date (AAAA-MM-JJ) : ");
                    String newDateStr = scanner.nextLine();
                    Date newDate = Date.valueOf(newDateStr);

                    Interaction modifI = new Interaction(idInteraction, 0, newNote, newDate); // client_id non nécessaire ici
                    new InteractionDAO().modifierInteraction(modifI);
                    break;
                case 8:
                    System.out.print("ID de l'interaction à supprimer : ");
                    int idSuppI = scanner.nextInt();
                    scanner.nextLine();

                    new InteractionDAO().supprimerInteraction(idSuppI);
                    break;
                case 9:
                    System.out.print("Entrez le nom ou l'email à rechercher : ");
                    String motCle = scanner.nextLine();

                    List<Client> resultats = clientDAO.rechercherClients(motCle);

                    if (resultats.isEmpty()) {
                        System.out.println("Aucun client trouvé.");
                    } else {
                        System.out.println("Résultats :");
                        for (Client c : resultats) {
                            System.out.println(c.getId() + " - " + c.getNom() + " " + c.getPrenom() + " | " + c.getEmail() + " | " + c.getTelephone());
                        }
                    }
                    break;


                case 0:
                    continuer = false;
                    System.out.println("------  Fermeture du programme...  ------");
                    break;

                default:
                    System.out.println("------  Choix invalide !  ------");
            }
        }

        scanner.close();
    }
}
