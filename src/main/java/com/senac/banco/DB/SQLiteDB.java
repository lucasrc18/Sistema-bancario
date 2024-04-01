package com.senac.banco.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.senac.banco.model.User;

public class SQLiteDB {
    private Connection connection;

    public SQLiteDB() {
        try {
            // conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:sqlite:com.senac.banco.db");
//            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
//            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTable() throws SQLException {
    	 Statement statement = connection.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS user (" +
             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
             "name TEXT NOT NULL," +
             "cpf TEXT NOT NULL," +
             "email TEXT NOT NULL)";
             
         statement.execute(sql);
    }
    
    /** Insere o <code>User</code> no banco de dados
     *  @param user: User
     *  @return true ceaso o <code>User</code> tenha sido inserido com sucesso
     *  @return false caso o <code>User</code> nao tenha sido inserido 
     */
    public boolean insertUser(User user) {
		try {
			String sql = "INSERT INTO users (nome, cpf, email) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getCPF());
			statement.setString(3, user.getEmail());
			statement.executeUpdate();
			statement.close();
//			System.out.println("Usário inserido com sucesso!");
			JOptionPane.showMessageDialog(null, "Usário inserido com sucesso!");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir o usário: " + e.getMessage());
			return false;
		}
    }
    
    public User getUser() {
    	User user = new User(true);
    	
		String sql = "SELECT * FROM user";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				String name = resultSet.getString("name");
				String cpf = resultSet.getString("cpf");
				String email = resultSet.getString("email");
				
				user.setName(name);
		    	user.setCPF(cpf);
		    	user.setEmail(email);
			} else {
				return null;
			}
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return user;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }
}