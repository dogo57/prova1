package org.example.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {





        private static final String URL = "jdbc:mysql://localhost:3306/empresa";
        private static final String USER = "root"; // Substitua pelo seu usuário do MySQL
        private static final String PASSWORD = ""; // Substitua pela sua senha

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                // Lançar uma exceção em tempo de execução para sinalizar falha grave
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
    }

