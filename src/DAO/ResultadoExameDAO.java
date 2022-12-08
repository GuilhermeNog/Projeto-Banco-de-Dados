package DAO;

import Model.ResultadoExame;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultadoExameDAO extends ConexaoDB {
    private static final String INSERT_RESULTADOEXAME_SQL = "INSERT INTO resultadoexame (dt_exame, valor, composicao_id, laudo_id) VALUES (?, ?, ?, ?);";
    private static final String SELECT_RESULTADOEXAME_BY_ID = "SELECT id, dt_exame, valor, composicao_id, laudo_id FROM resultadoexame WHERE id = ?";
    private static final String SELECT_ALL_RESULTADOEXAME = "SELECT * FROM resultadoexame;";
    private static final String DELETE_RESULTADOEXAME_SQL = "DELETE FROM resultadoexame WHERE id = ?;";
    private static final String UPDATE_RESULTADOEXAME_SQL = "UPDATE resultadoexame SET dt_exame = ?, valor = ?, composicao_id = ?, laudo_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM resultadoexame;";

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

    public void insertResultadoExame(ResultadoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_RESULTADOEXAME_SQL)) {
            preparedStatement.setDate(2, entidade.getDt_exame());
            preparedStatement.setString(1, entidade.getValor());
            preparedStatement.setInt(3, entidade.getComposicao_id());
            preparedStatement.setInt(4, entidade.getLaudo_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultadoExame selectResultadoExame(int id) {
        ResultadoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_RESULTADOEXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Date dt_exame = rs.getDate("dt_exame");
                String valor = rs.getString("valor");
                Integer composicao_id = rs.getInt("composicao_id");
                Integer laudo_id = rs.getInt("laudo_id");

                entidade = new ResultadoExame(id, dt_exame, valor, composicao_id, laudo_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ResultadoExame> selectAllResultadoExame() {
        List<ResultadoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_RESULTADOEXAME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date dt_exame = rs.getDate("dt_exame");
                String valor = rs.getString("valor");
                Integer composicao_id = rs.getInt("composicao_id");
                Integer laudo_id = rs.getInt("laudo_id");

                entidades.add(new ResultadoExame(id, dt_exame, valor, composicao_id, laudo_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteResultadoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_RESULTADOEXAME_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateResultadoExame(ResultadoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_RESULTADOEXAME_SQL)) {
            statement.setDate(2, entidade.getDt_exame());
            statement.setString(1, entidade.getValor());
            statement.setInt(3, entidade.getComposicao_id());
            statement.setInt(4, entidade.getLaudo_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
