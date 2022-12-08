package DAO;

import Model.Exame;
import Model.UnidadeMedida;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidaDAO extends ConexaoDB {
    private static final String INSERT_UNIDADEMEDIDA_SQL = "INSERT INTO unidademedida (descricao) VALUES (?);";
    private static final String SELECT_UNIDADEMEDIDA_BY_ID = "SELECT id, descricao FROM unidademedida WHERE id = ?";
    private static final String SELECT_ALL_UNIDADEMEDIDA = "SELECT * FROM unidademedida;";
    private static final String DELETE_UNIDADEMEDIDA_SQL = "DELETE FROM unidademedida WHERE id = ?;";
    private static final String UPDATE_UNIDADEMEDIDA_SQL = "UPDATE unidademedida SET descricao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM unidademedida;";

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

    public void insertUnidadeMedida(UnidadeMedida entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_UNIDADEMEDIDA_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public UnidadeMedida selectUnidadeMedida(int id) {
        UnidadeMedida entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_UNIDADEMEDIDA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");

                entidade = new UnidadeMedida(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<UnidadeMedida> selectAllUnidadeMedida() {
        List<UnidadeMedida> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_UNIDADEMEDIDA)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");

                entidades.add(new UnidadeMedida(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteUnidadeMedida(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_UNIDADEMEDIDA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUnidadeMedida(UnidadeMedida entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_UNIDADEMEDIDA_SQL)) {
            statement.setString(1, entidade.getDescricao());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
