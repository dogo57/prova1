package org.example.dao;

import org.example.model.Pessoa;
import org.example.util.ConnectionFactory;

import java.sql.*;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Pessoa.
 * <p>
 * Esta classe encapsula toda a lógica de interação com o banco de dados
 * para as operações de persistência da entidade Pessoa, como criação,
 * consulta, atualização e exclusão (CRUD).
 *
 * @author Seu Nome
 * @version 1.0
 */
public class PessoaDAO {

    /**
     * Insere um novo registro de Pessoa no banco de dados.
     * <p>
     * <b>Regra de Negócio 4:</b> Exibe uma mensagem de erro clara no console se ocorrer uma
     * falha durante a operação de inserção (ex: e-mail duplicado).
     * <p>
     * <b>Regra de Negócio 5:</b> Exibe uma mensagem de confirmação no console se o cadastro
     * for bem-sucedido.
     *
     * @param pessoa O objeto {@link Pessoa} contendo os dados (nome e e-mail) a serem salvos.
     *               O ID é ignorado, pois é gerado automaticamente pelo banco de dados.
     */
    public void create(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?, ?)";

        // O try-with-resources garante que a conexão e o PreparedStatement sejam fechados
        // automaticamente ao final do bloco, mesmo que ocorram exceções.
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());

            stmt.executeUpdate();

            System.out.println("Pessoa cadastrada com sucesso!"); // Regra 5
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar pessoa: " + e.getMessage()); // Regra 4
        }
    }

    /**
     * Busca e retorna uma pessoa do banco de dados pelo seu identificador único (ID).
     *
     * @param id O ID da pessoa a ser consultada.
     * @return Um objeto {@link Pessoa} preenchido com os dados encontrados,
     *         ou {@code null} se nenhuma pessoa for encontrada com o ID fornecido.
     */
    public Pessoa findById(int id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica se a consulta retornou algum resultado
                if (rs.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(rs.getInt("id"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEmail(rs.getString("email"));
                    return pessoa;
                }
            }
        } catch (SQLException e) {
            // Exibe o erro mas não interrompe o programa, permitindo que o chamador trate o retorno nulo.
            System.err.println("Erro ao buscar pessoa: " + e.getMessage());
        }

        return null; // Retorna nulo se a pessoa não foi encontrada ou se ocorreu um erro.
    }

    /**
     * Exclui uma pessoa do banco de dados.
     * <p>
     * <b>Atenção:</b> A exclusão de uma pessoa pode falhar ou ter efeitos colaterais
     * se ela for referenciada em outras tabelas (como 'funcionario'), devido a
     * restrições de chave estrangeira. A configuração 'ON DELETE CASCADE' na FK
     * da tabela 'funcionario' faria com que o funcionário correspondente também
     * fosse excluído.
     *
     * @param id O ID da pessoa a ser excluída.
     */
    // public void delete(int id) { ... }

    /**
     * Atualiza os dados de uma pessoa existente no banco de dados.
     *
     * @param pessoa O objeto {@link Pessoa} com o ID da pessoa a ser atualizada
     *               e os novos dados (nome e e-mail).
     */
    // public void update(Pessoa pessoa) { ... }
}