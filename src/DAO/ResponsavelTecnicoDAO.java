package DAO;

import Model.ResponsavelTecnico;
import Model.ResponsavelTecnicoHasLaboratorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelTecnicoDAO extends ConexaoDB {
    private static final String INSERT_RESPONSAVELTECNICO_SQL = "INSERT INTO responsaveltecnico (nome, conselho, formacao, sigla_formacao_id) VALUES (?, ?, ?, ?);";
    private static final String SELECT_RESPONSAVELTECNICO_BY_ID = "SELECT id, nome, conselho, formacao, sigla_formacao_id FROM responsaveltecnico WHERE id = ?";
    private static final String SELECT_ALL_RESPONSAVELTECNICO = "SELECT * FROM responsaveltecnico;";
    private static final String DELETE_RESPONSAVELTECNICO_SQL = "DELETE FROM responsaveltecnico WHERE id = ?;";
    private static final String UPDATE_RESPONSAVELTECNICO_SQL = "UPDATE responsaveltecnico SET nome = ?, conselho = ?, formacao = ?, sigla_formacao_id = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM responsaveltecnico;";

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

    public void insertResponsavelTecnico(ResponsavelTecnico entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_RESPONSAVELTECNICO_SQL)) {
            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getConselho());
            preparedStatement.setString(3, entidade.getFormacao());
            preparedStatement.setInt(4, entidade.getSigla_formacao_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponsavelTecnico selectResponsavelTecnico(int id) {
        ResponsavelTecnico entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_RESPONSAVELTECNICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String conselho = rs.getString("conselho");
                String formacao = rs.getString("formacao");
                Integer sigla_formacao_id = rs.getInt("sigla_formacao_id");

                entidade = new ResponsavelTecnico(id, nome, conselho, formacao, sigla_formacao_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ResponsavelTecnico> selectAllResponsavelTecnico() {
        List<ResponsavelTecnico> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_RESPONSAVELTECNICO)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String conselho = rs.getString("conselho");
                String formacao = rs.getString("formacao");
                Integer sigla_formacao_id = rs.getInt("sigla_formacao_id");

                entidades.add(new ResponsavelTecnico(id, nome, conselho, formacao, sigla_formacao_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteResponsavelTecnico(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_RESPONSAVELTECNICO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateResponsavelTecnico(ResponsavelTecnico entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_RESPONSAVELTECNICO_SQL)) {
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getConselho());
            statement.setString(3, entidade.getFormacao());
            statement.setInt(4, entidade.getSigla_formacao_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
