package DAO;

import Model.Medico;
import Model.SiglaFormacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends ConexaoDB {
    private static final String INSERT_MEDICO_SQL = "INSERT INTO medico (crm, nome) VALUES (?, ?);";
    private static final String SELECT_MEDICO_BY_ID = "SELECT id, crm, nome FROM medico WHERE id = ?";
    private static final String SELECT_ALL_MEDICO = "SELECT * FROM medico;";
    private static final String DELETE_MEDICO_SQL = "DELETE FROM medico WHERE id = ?;";
    private static final String UPDATE_MEDICO_SQL = "UPDATE medico SET crm = ?, nome = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM medico;";

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

    public void insertMedico(Medico entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICO_SQL)) {
            preparedStatement.setString(1, entidade.getCrm());
            preparedStatement.setString(2, entidade.getNome());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Medico selectMedico(int id) {
        Medico entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String crm = rs.getString("crm");
                String nome = rs.getString("nome");

                entidade = new Medico(id, crm, nome);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Medico> selectAllMedico() {
        List<Medico> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDICO)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String crm = rs.getString("crm");
                String nome = rs.getString("nome");

                entidades.add(new Medico(id, crm, nome));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedico(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDICO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMedico(Medico entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MEDICO_SQL)) {
            statement.setString(1, entidade.getCrm());
            statement.setString(2, entidade.getNome());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
