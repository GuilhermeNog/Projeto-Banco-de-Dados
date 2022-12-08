package DAO;

import Model.Endereco;
import Model.ResponsavelTecnicoHasLaboratorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelTecnicoHasLaboratorioDAO extends ConexaoDB {
    private static final String INSERT_RESPONSAVELTECNICOHASLABORATORIO_SQL = "INSERT INTO responsaveltecnicohaslaboratorio (responsavel_tecnico_id, laboratorio_id) VALUES (?, ?);";
    private static final String SELECT_RESPONSAVELTECNICOHASLABORATORIO_BY_ID = "SELECT id, responsavel_tecnico_id, laboratorio_id FROM responsaveltecnicohaslaboratorio WHERE id = ?";
    private static final String SELECT_ALL_RESPONSAVELTECNICOHASLABORATORIO = "SELECT * FROM responsaveltecnicohaslaboratorio;";
    private static final String DELETE_RESPONSAVELTECNICOHASLABORATORIO_SQL = "DELETE FROM responsaveltecnicohaslaboratorio WHERE id = ?;";
    private static final String UPDATE_RESPONSAVELTECNICOHASLABORATORIO_SQL = "UPDATE responsaveltecnicohaslaboratorio SET responsavel_tecnico_id = ?, laboratorio_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM responsaveltecnicohaslaboratorio;";

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

    public void insertResponsavelTecnicoHasLaboratorio(ResponsavelTecnicoHasLaboratorio entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_RESPONSAVELTECNICOHASLABORATORIO_SQL)) {
            preparedStatement.setInt(1, entidade.getResponsavel_tecnico_id());
            preparedStatement.setInt(2, entidade.getLaboratorio_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponsavelTecnicoHasLaboratorio selectResponsavelTecnicoHasLaboratorio(int id) {
        ResponsavelTecnicoHasLaboratorio entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_RESPONSAVELTECNICOHASLABORATORIO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer responsavel_tecnico_id = rs.getInt("responsavel_tecnico_id");
                Integer laboratorio_id = rs.getInt("laboratorio_id");

                entidade = new ResponsavelTecnicoHasLaboratorio(id, responsavel_tecnico_id, laboratorio_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ResponsavelTecnicoHasLaboratorio> selectAllResponsavelTecnicoHasLaboratorio() {
        List<ResponsavelTecnicoHasLaboratorio> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_RESPONSAVELTECNICOHASLABORATORIO)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Integer responsavel_tecnico_id = rs.getInt("responsavel_tecnico_id");
                Integer laboratorio_id = rs.getInt("laboratorio_id");

                entidades.add(new ResponsavelTecnicoHasLaboratorio(id, responsavel_tecnico_id, laboratorio_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteResponsavelTecnicoHasLaboratorio(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_RESPONSAVELTECNICOHASLABORATORIO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateResponsavelTecnicoHasLaboratorio(ResponsavelTecnicoHasLaboratorio entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_RESPONSAVELTECNICOHASLABORATORIO_SQL)) {
            statement.setInt(1, entidade.getResponsavel_tecnico_id());
            statement.setInt(2, entidade.getLaboratorio_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
