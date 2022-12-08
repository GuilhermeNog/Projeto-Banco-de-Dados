package DAO;

import Model.Composicao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposicaoDAO extends ConexaoDB {
    private static final String INSERT_COMPOSICAO_SQL = "INSERT INTO composicao (exame_id, composicao_exame_id, valor_referencia_composicao_exame_id) VALUES (?, ?, ?);";
    private static final String SELECT_COMPOSICAO_BY_ID = "SELECT id, exame_id, composicao_exame_id, valor_referencia_composicao_exame_id FROM composicao WHERE id = ?";
    private static final String SELECT_ALL_COMPOSICAO = "SELECT * FROM composicao;";
    private static final String DELETE_COMPOSICAO_SQL = "DELETE FROM composicao WHERE id = ?;";
    private static final String UPDATE_COMPOSICAO_SQL = "UPDATE composicao SET exame_id = ?, composicao_exame_id = ?, valor_referencia_composicao_exame_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM composicao;";

    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void insertComposicao(Composicao entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_COMPOSICAO_SQL)) {
            preparedStatement.setInt(1, entidade.getExame_id());
            preparedStatement.setInt(2, entidade.getComposicao_exame_id());
            preparedStatement.setInt(3, entidade.getValor_referencia_composicao_exame_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Composicao selectComposicao(int id) {
        Composicao entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_COMPOSICAO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer exame_id = rs.getInt("exame_id");
                Integer composicao_exame_id = rs.getInt("composicao_exame_id");
                Integer valor_referencia_composicao_exame_id = rs.getInt("valor_referencia_composicao_exame_id");

                entidade = new Composicao(id, exame_id, composicao_exame_id, valor_referencia_composicao_exame_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Composicao> selectAllComposicao() {
        List<Composicao> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_COMPOSICAO)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Integer exame_id = rs.getInt("exame_id");
                Integer composicao_exame_id = rs.getInt("composicao_exame_id");
                Integer valor_referencia_composicao_exame_id = rs.getInt("valor_referencia_composicao_exame_id");

                entidades.add(new Composicao(id, exame_id, composicao_exame_id, valor_referencia_composicao_exame_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteComposicao(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_COMPOSICAO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateComposicao(Composicao entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_COMPOSICAO_SQL)) {
            statement.setInt(1, entidade.getExame_id());
            statement.setInt(2, entidade.getComposicao_exame_id());
            statement.setInt(3, entidade.getValor_referencia_composicao_exame_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
