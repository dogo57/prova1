package org.example.dao;

import org.example.model.Projeto;
import org.example.util.ConnectionFactory;

import java.sql.*;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Projeto.
 * <p>
 * Esta classe é responsável por gerenciar todas as operações de persistência
 * de dados (CRUD) para os projetos no banco de dados. Ela contém a lógica para
 * criar, ler, atualizar e deletar registros de projetos, além de implementar
 * as regras de negócio associadas.
 *
 * @author Seu Nome
 * @version 1.0
 */
public class ProjetoDAO {

    /**
     * Cadastra um novo projeto no banco de dados.
     * <p>
     * <b>Regra de Negócio 2:</b> Antes de criar o projeto, este método realiza uma
     * verificação explícita para garantir que o {@code id_funcionario} fornecido
     * corresponde a um funcionário existente. Se o funcionário não for encontrado,
     * a operação é cancelada. Esta é uma camada de validação adicional à restrição
     * de chave estrangeira do banco de dados.
     * <p>
     * <b>Regra de Negócio 4:</b> Exibe uma mensagem de erro detalhada no console se a
     * operação falhar, seja pela ausência do funcionário ou por um erro de SQL.
     * <p>
     * <b>Regra de Negócio 5:</b> Imprime uma mensagem de confirmação no console
     * indicando que o projeto foi cadastrado com sucesso.
     *
     * @param projeto O objeto {@link Projeto} contendo os dados do novo projeto a ser inserido.
     *                O ID do projeto é ignorado, pois é gerado automaticamente pelo banco.
     */
    public void create(Projeto projeto) {
        // REGRA 2: Um projeto não pode ser criado sem vínculo com um funcionário existente.
        // A verificação abaixo é uma boa prática para fornecer feedback claro ao usuário antes
        // de depender apenas da restrição do banco de dados.
        String checkFuncSql = "SELECT id FROM funcionario WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkFuncSql)) {

            checkStmt.setInt(1, projeto.getId_funcionario());

            try (ResultSet rs = checkStmt.executeQuery()) {
                // Se o ResultSet estiver vazio (rs.next() for falso), o funcionário não existe.
                if (!rs.next()) {
                    System.err.println("Erro: Funcionário com ID " + projeto.getId_funcionario() + " não existe. Projeto não pode ser criado."); // Regra 4
                    return; // Aborta a criação do projeto.
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar funcionário: " + e.getMessage());
            return; // Aborta se houver erro na verificação.
        }

        // Se a verificação do funcionário passar, prossegue com a inserção do projeto.
        String sql = "INSERT INTO projeto (nome, descricao, id_funcionario) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setInt(3, projeto.getId_funcionario());

            stmt.executeUpdate();

            System.out.println("Projeto cadastrado com sucesso!"); // Regra 5
        } catch (SQLException e) {
            // A exceção aqui pode ocorrer por outros motivos, como dados inválidos ou falha de conexão.
            System.err.println("Erro ao cadastrar projeto: " + e.getMessage()); // Regra 4
        }
    }

    // TODO: Implementar os métodos para ler, atualizar e deletar projetos.

    /**
     * Busca um projeto pelo seu ID.
     * @param id O ID do projeto a ser buscado.
     * @return um objeto Projeto se encontrado, caso contrário, null.
     */
    // public Projeto findById(int id) { ... }

    /**
     * Exclui um projeto do banco de dados.
     * @param id O ID do projeto a ser excluído.
     */
    // public void delete(int id) { ... }

    /**
     * Atualiza os dados de um projeto existente no banco de dados.
     * @param projeto O objeto Projeto com os dados atualizados.
     */
    // public void update(Projeto projeto) { ... }
}