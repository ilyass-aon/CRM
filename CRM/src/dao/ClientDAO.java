package dao;

import model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void ajouterClient(Client client) {
        String sql = "INSERT INTO client(nom, prenom, email, telephone) VALUES(?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getTelephone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listerClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
    public void modifierClient(Client client) {
        String sql = "UPDATE client SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getTelephone());
            stmt.setInt(5, client.getId());

            int lignesModifiees = stmt.executeUpdate();
            if (lignesModifiees == 0) {
                System.out.println("Aucun client trouvé avec l'ID : " + client.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimerClient(int id) {
        String sql = "DELETE FROM client WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int lignesSupprimees = stmt.executeUpdate();
            if (lignesSupprimees == 0) {
                System.out.println("Aucun client trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> rechercherClients(String motCle) {
        List<Client> resultats = new ArrayList<>();
        String sql = "SELECT * FROM client WHERE nom LIKE ? OR email LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String filtre = "%" + motCle + "%";
            stmt.setString(1, filtre);
            stmt.setString(2, filtre);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
                resultats.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultats;
    }



}
