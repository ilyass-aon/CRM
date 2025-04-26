package dao;

import model.Interaction;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionDAO {


    public void ajouterInteraction(Interaction interaction) {
        String sql = "INSERT INTO interaction (client_id, note, date) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, interaction.getClientId());
            stmt.setString(2, interaction.getNote());
            stmt.setString(3, interaction.getDate().toString()); // format ISO 8601 recommandé

            stmt.executeUpdate();
            System.out.println(" Interaction ajoutée !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lister les interactions d’un client
    public List<Interaction> listerInteractionsParClient(int clientId) {
        List<Interaction> interactions = new ArrayList<>();
        String sql = "SELECT * FROM interaction WHERE client_id = ? ORDER BY date DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Interaction i = new Interaction(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getString("note"),
                        Date.valueOf(rs.getString("date"))
                );
                interactions.add(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interactions;
    }
    public void supprimerInteraction(int id) {
        String sql = "DELETE FROM interaction WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Interaction supprimée !");
            } else {
                System.out.println(" Aucune interaction trouvée avec cet ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void modifierInteraction(Interaction interaction) {
        String sql = "UPDATE interaction SET note = ?, date = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, interaction.getNote());
            stmt.setString(2, interaction.getDate().toString());
            stmt.setInt(3, interaction.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Interaction modifiée !");
            } else {
                System.out.println(" Interaction non trouvée.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
