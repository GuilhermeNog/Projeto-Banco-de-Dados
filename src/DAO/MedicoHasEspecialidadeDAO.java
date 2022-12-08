package DAO;

import Model.Especialidade;
import Model.MedicoHasEspecialidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoHasEspecialidadeDAO extends ConexaoDB {
    private static final String INSERT_MEDICOHASESPECIALIDADE_SQL = "INSERT INTO medicohasespecialidade (medico_id, especialidade_id) VALUES (?, ?);";
    private static final String SELECT_MEDICOHASESPECIALIDADE_BY_ID = "SELECT id, medico_id, especialidade_id FROM medicohasespecialidade WHERE id = ?";
    private static final String SELECT_ALL_MEDICOHASESPECIALIDADE = "SELECT * FROM medicohasespecialidade;";
    private static final String DELETE_MEDICOHASESPECIALIDADE_SQL = "DELETE FROM medicohasespecialidade WHERE id = ?;";
    private static final String UPDATE_MEDICOHASESPECIALIDADE_SQL = "UPDATE medicohasespecialidade SET medico_id = ?, especialidade_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM medicohasespecialidade;";

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

    public void insertMedicoHasEspecialidade(MedicoHasEspecialidade entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICOHASESPECIALIDADE_SQL)) {
            preparedStatement.setInt(1, entidade.getMedico_id());
            preparedStatement.setInt(2, entidade.getEspecialidade_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MedicoHasEspecialidade selectMedicoHasEspecialidade(int id) {
        MedicoHasEspecialidade entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICOHASESPECIALIDADE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer medico_id = rs.getInt("medico_id");
                Integer especialidade_id = rs.getInt("especialidade_id");

                entidade = new MedicoHasEspecialidade(id, medico_id, especialidade_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<MedicoHasEspecialidade> selectAllMedicoHasEspecialidade() {
        List<MedicoHasEspecialidade> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDICOHASESPECIALIDADE)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Integer medico_id = rs.getInt("medico_id");
                Integer especialidade_id = rs.getInt("especialidade_id");

                entidades.add(new MedicoHasEspecialidade(id, medico_id, especialidade_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedicoHasEspecialidade(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDICOHASESPECIALIDADE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMedicoHasEspecialidade(MedicoHasEspecialidade entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MEDICOHASESPECIALIDADE_SQL)) {
            statement.setInt(1, entidade.getMedico_id());
            statement.setInt(2, entidade.getEspecialidade_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
