package DAO;

import Model.ConsultaMedica;
import Model.MedicoHasEspecialidade;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicaDAO extends ConexaoDB {
    private static final String INSERT_CONSULTAMEDICA_SQL = "INSERT INTO consultamedica (dt_consulta, medico_id, paciente_id, nm_atendimento) VALUES (?, ?, ?, ?);";
    private static final String SELECT_CONSULTAMEDICA_BY_ID = "SELECT id, dt_consulta, medico_id, paciente_id, nm_atendimento FROM consultamedica WHERE id = ?";
    private static final String SELECT_ALL_CONSULTAMEDICA = "SELECT * FROM consultamedica;";
    private static final String DELETE_CONSULTAMEDICA_SQL = "DELETE FROM consultamedica WHERE id = ?;";
    private static final String UPDATE_CONSULTAMEDICA_SQL = "UPDATE consultamedica SET dt_consulta = ?, medico_id = ?, paciente_id = ?, nm_atendimento = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM consultamedica;";

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

    public void insertConsultaMedica(ConsultaMedica entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CONSULTAMEDICA_SQL)) {
            preparedStatement.setDate(1, entidade.getDt_consulta());
            preparedStatement.setInt(2, entidade.getMedico_id());
            preparedStatement.setInt(3, entidade.getPaciente_id());
            preparedStatement.setString(4, entidade.getNm_atendimento());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ConsultaMedica selectConsultaMedica(int id) {
        ConsultaMedica entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CONSULTAMEDICA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Date dt_consulta = rs.getDate("dt_consulta");
                Integer medico_id = rs.getInt("medico_id");
                Integer paciente_id = rs.getInt("paciente_id");
                String nm_atendimento = rs.getString("nm_atendimento");

                entidade = new ConsultaMedica(id, dt_consulta, medico_id, paciente_id, nm_atendimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ConsultaMedica> selectAllConsultaMedica() {
        List<ConsultaMedica> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CONSULTAMEDICA)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date dt_consulta = rs.getDate("dt_consulta");
                Integer medico_id = rs.getInt("medico_id");
                Integer paciente_id = rs.getInt("paciente_id");
                String nm_atendimento = rs.getString("nm_atendimento");

                entidades.add(new ConsultaMedica(id, dt_consulta, medico_id, paciente_id, nm_atendimento));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteConsultaMedica(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_CONSULTAMEDICA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateConsultaMedica(ConsultaMedica entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_CONSULTAMEDICA_SQL)) {
            statement.setDate(1, entidade.getDt_consulta());
            statement.setInt(2, entidade.getMedico_id());
            statement.setInt(3, entidade.getPaciente_id());
            statement.setString(4, entidade.getNm_atendimento());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
