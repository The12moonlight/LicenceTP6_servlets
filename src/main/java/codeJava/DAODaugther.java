/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import simplejdbc.DAO;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import simplejdbc.CustomerEntity;
import simplejdbc.DAOException;

/**
 *
 * @author pierr
 */
public class DAODaugther extends DAO {
    
    public DAODaugther(DataSource dataSource) {
        super(dataSource);
    }
    
    public List<String> recupStateFromBD() throws DAOException {
        List<String> listState = new LinkedList<>(); // Liste vIde
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
        try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
			ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
	    ) {
                   while (rs.next()) { // On a trouvé pour chaque etat on l'ajoute a la liste
			// On ajoute la valeur trouvé dans la liste
			listState.add(rs.getString(1));				
                   } // else on n'a pas trouvé, on renverra null
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        return listState;
    }
    
}
