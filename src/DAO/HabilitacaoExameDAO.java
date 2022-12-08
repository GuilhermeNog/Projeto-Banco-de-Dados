package DAO;

import Model.HabilitacaoExame;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabilitacaoExameDAO extends ConexaoDB {
    private static final String INSERT_HABILITACAOEXAME_SQL = "INSERT INTO habilitacaoexame (observacao, custo, laboratorio_id, tipo_exame_id) VALUES (?, ?, ?, ?);";
    private static final String SELECT_HABILITACAOEXAME_BY_ID = "SELECT id, observacao, custo, laboratorio_id, tipo_exame_id FROM habilitacaoexame WHERE id = ?";
    private static final String SELECT_ALL_HABILITACAOEXAME = "SELECT * FROM habilitacaoexame;";
    private static final String DELETE_HABILITACAOEXAME_SQL = "DELETE FROM habilitacaoexame WHERE id = ?;";
    private static final String UPDATE_HABILITACAOEXAME_SQL = "UPDATE habilitacaoexame SET observacao = ?, custo = ?, laboratorio_id = ?, tipo_exame_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM habilitacaoexame;";

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

    public void insertHabilitacaoExame(HabilitacaoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_HABILITACAOEXAME_SQL)) {
            preparedStatement.setString(1, entidade.getObservacao());
            preparedStatement.setObject(2, entidade.getCusto());
            preparedStatement.setInt(3, entidade.getLaboratorio_id());
            preparedStatement.setInt(4, entidade.getTipo_exame_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public HabilitacaoExame selectHabilitacaoExame(int id) {
        HabilitacaoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_HABILITACAOEXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String observacao = rs.getString("observacao");
                Object custo = rs.getObject("custo");
                Integer laboratorio_id = rs.getInt("laboratorio_id");
                Integer tipo_exame_id = rs.getInt("tipo_exame_id");

                entidade = new HabilitacaoExame(id, observacao, (BigInteger) custo, laboratorio_id, tipo_exame_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<HabilitacaoExame> selectAllHabilitacaoExame() {
        List<HabilitacaoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_HABILITACAOEXAME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String observacao = rs.getString("observacao");
                Object custo = rs.getObject("custo");
                Integer laboratorio_id = rs.getInt("laboratorio_id");
                Integer tipo_exame_id = rs.getInt("tipo_exame_id");

                entidades.add(new HabilitacaoExame(id, observacao, (BigInteger) custo, laboratorio_id, tipo_exame_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteHabilitacaoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_HABILITACAOEXAME_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateHabilitacaoExame(HabilitacaoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_HABILITACAOEXAME_SQL)) {
            statement.setString(1, entidade.getObservacao());
            statement.setObject(2, entidade.getCusto());
            statement.setInt(3, entidade.getLaboratorio_id());
            statement.setInt(4, entidade.getTipo_exame_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
