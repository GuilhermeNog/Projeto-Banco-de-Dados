package DAO;

import Model.ResponsavelTecnico;
import Model.SiglaFormacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiglaFormacaoDAO extends ConexaoDB {
    private static final String INSERT_SIGLAFORMACAO_SQL = "INSERT INTO siglaformacao (sigla) VALUES (?);";
    private static final String SELECT_SIGLAFORMACAO_BY_ID = "SELECT id, sigla FROM siglaformacao WHERE id = ?";
    private static final String SELECT_ALL_SIGLAFORMACAO = "SELECT * FROM siglaformacao;";
    private static final String DELETE_SIGLAFORMACAO_SQL = "DELETE FROM siglaformacao WHERE id = ?;";
    private static final String UPDATE_SIGLAFORMACAO_SQL = "UPDATE siglaformacao SET sigla = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM siglaformacao;";

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

    public void insertSiglaFormacao(SiglaFormacao entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_SIGLAFORMACAO_SQL)) {
            preparedStatement.setString(1, entidade.getSigla());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public SiglaFormacao selectSiglaFormacao(int id) {
        SiglaFormacao entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_SIGLAFORMACAO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String sigla = rs.getString("sigla");

                entidade = new SiglaFormacao(id, sigla);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<SiglaFormacao> selectAllSiglaFormacao() {
        List<SiglaFormacao> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_SIGLAFORMACAO)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String sigla = rs.getString("sigla");

                entidades.add(new SiglaFormacao(id, sigla));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteSiglaFormacao(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_SIGLAFORMACAO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateSiglaFormacao(SiglaFormacao entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_SIGLAFORMACAO_SQL)) {
            statement.setString(1, entidade.getSigla());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
