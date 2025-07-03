package org.example.dao;

import org.example.model.Funcionario;
import org.example.util.ConnectionFactory;

import java.sql.*;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Funcionario.
 * <p>
 * Esta classe é responsável por todas as operações de persistência (CRUD - Create, Read, Update, Delete)
 * relacionadas aos funcionários no banco de dados. Ela implementa as regras de negócio
 * específicas para a manipulação de dados de funcionários.
 *
 * @author Seu Nome
 * @version 1.0
 */
public class FuncionarioDAO {

    /**
     * Cadastra um novo funcionário no banco de dados.
     * <p>
     * <b>Regra de Negócio 1:</b> Antes de inserir o funcionário, este método verifica
     * se já existe um registro na tabela 'pessoa' com o mesmo ID. Se a pessoa não existir,
     * a operação é abortada.
     * <p>
     * <b>Regra de Negócio 4:</b> Exibe uma mensagem de erro clara se a pessoa não for encontrada
     * ou se ocorrer um erro de SQL.
     * <p>
     * <b>Regra de Negócio 5:</b> Exibe uma mensagem de confirmação no console se o cadastro
     * for realizado com sucesso.
     *
     * @param funcionario O objeto {@link Funcionario} contendo os dados a serem inseridos.
     *                    O ID do funcionário deve corresponder a um ID existente na tabela 'pessoa'.
     */
    public void create(Funcionario funcionario) {
        // REGRA 1: Verificar se o ID da pessoa existe na tabela pessoa.
        PessoaDAO pessoaDAO = new PessoaDAO();
        if (pessoaDAO.findById(funcionario.getId()) == null) {
            System.err.println("Erro: Não é possível criar funcionário. A pessoa com ID " + funcionario.getId() + " não existe."); // Regra 4
            return; // Aborta a operação
        }

        // Query SQL para inserir o funcionário
        String sql = "INSERT INTO funcionario (id, matricula, departamento) VALUES (?, ?, ?)";

        // Usa try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getDepartamento());

            stmt.executeUpdate();

            System.out.println("Funcionário cadastrado com sucesso!"); // Regra 5
        } catch (SQLException e) {
            // Em caso de erro (ex: matrícula duplicada), exibe uma mensagem clara
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage()); // Regra 4
        }
    }

    /**
     * Exclui um funcionário do banco de dados com base no seu ID.
     * <p>
     * <b>Regra de Negócio 3:</b> Antes de excluir, o método verifica se o funcionário
     * está vinculado a algum projeto. Se estiver, a exclusão é proibida.
     * <p>
     * <b>Regra de Negócio 4:</b> Exibe uma mensagem de erro clara se o funcionário estiver
     * vinculado a um projeto ou se ocorrer outro erro de SQL.
     * <p>
     * <b>Regra de Negócio 5:</b> Exibe uma mensagem de confirmação no console se a exclusão
     * for realizada com sucesso.
     *
     * @param id O ID do funcionário a ser excluído.
     */
    public void delete(int id) {
        // REGRA 3: Proibir a exclusão de um funcionário que esteja vinculado a algum projeto.
        String checkSql = "SELECT COUNT(*) FROM projeto WHERE id_funcionario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            // Verifica se a consulta retornou um resultado e se a contagem é maior que zero
            if (rs.next() && rs.getInt(1) > 0) {
                System.err.println("Erro: Não é possível excluir o funcionário. Ele está vinculado a " + rs.getInt(1) + " projeto(s)."); // Regra 4
                return; // Aborta a operação
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar projetos do funcionário: " + e.getMessage());
            return; // Aborta a operação em caso de falha na verificação
        }

        // Se a regra de negócio acima permitir, prossegue com a exclusão do funcionário
        String deleteSql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            deleteStmt.setInt(1, id);

            int affectedRows = deleteStmt.executeUpdate();

            // Verifica se alguma linha foi de fato excluída
            if (affectedRows > 0) {
                System.out.println("Funcionário excluído com sucesso!"); // Regra 5
            } else {
                // Informa o usuário caso o ID não corresponda a nenhum funcionário
                System.err.println("Erro: Nenhum funcionário encontrado com o ID " + id + " para exclusão.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage()); // Regra 4
        }
    }

    // TODO: Implementar os métodos findById(id), findAll() e update(Funcionario) para completar o CRUD.

    /**
     * Busca um funcionário pelo seu ID.
     * @param id O ID do funcionário a ser buscado.
     * @return um objeto Funcionario se encontrado, caso contrário, null.
     */
    // public Funcionario findById(int id) { ... }

    /**
     * Atualiza os dados de um funcionário no banco de dados.
     * @param funcionario O objeto Funcionario com os dados atualizados.
     */
    // public void update(Funcionario funcionario) { ... }
}