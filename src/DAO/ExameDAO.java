package DAO;

import Model.Exame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO extends ConexaoDB {
    private static final String INSERT_EXAME_SQL = "INSERT INTO exame (tipo_exame_id, descricao, material_exame_id, metodo) VALUES (?, ?, ?, ?);";
    private static final String SELECT_EXAME_BY_ID = "SELECT id, tipo_exame_id, descricao, material_exame_id, metodo FROM exame WHERE id = ?";
    private static final String SELECT_ALL_EXAME = "SELECT * FROM exame;";
    private static final String DELETE_EXAME_SQL = "DELETE FROM exame WHERE id = ?;";
    private static final String UPDATE_EXAME_SQL = "UPDATE exame SET tipo_exame_id = ?, descricao = ?, material_exame_id = ?, metodo = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM exame;";

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

    public void insertExame(Exame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_EXAME_SQL)) {
            preparedStatement.setInt(1, entidade.getTipo_exame_id());
            preparedStatement.setString(2, entidade.getDescricao());
            preparedStatement.setInt(3, entidade.getMaterial_exame_id());
            preparedStatement.setString(4, entidade.getMetodo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Exame selectExame(int id) {
        Exame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_EXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer tipo_exame_id = rs.getInt("tipo_exame_id");
                String descricao = rs.getString("descricao");
                Integer material_exame_id = rs.getInt("material_exame_id");
                String metodo = rs.getString("metodo");

                entidade = new Exame(id, tipo_exame_id, descricao, material_exame_id, metodo);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Exame> selectAllExame() {
        List<Exame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_EXAME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Integer tipo_exame_id = rs.getInt("tipo_exame_id");
                String descricao = rs.getString("descricao");
                Integer material_exame_id = rs.getInt("material_exame_id");
                String metodo = rs.getString("metodo");

                entidades.add(new Exame(id, tipo_exame_id, descricao, material_exame_id, metodo));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_EXAME_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateExame(Exame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_EXAME_SQL)) {
            statement.setInt(1, entidade.getTipo_exame_id());
            statement.setString(2, entidade.getDescricao());
            statement.setInt(3, entidade.getMaterial_exame_id());
            statement.setString(4, entidade.getMetodo());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
