package DAO;

import Model.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends ConexaoDB {
    private static final String INSERT_ENDERECO_SQL = "INSERT INTO endereco (rua, numero, complemento, bairro, cep, cidade, laboratorio_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ENDERECO_BY_ID = "SELECT id, rua, numero, complemento, bairro, cep, cidade, laboratorio_id FROM endereco WHERE id = ?";
    private static final String SELECT_ALL_ENDERECO = "SELECT * FROM endereco;";
    private static final String DELETE_ENDERECO_SQL = "DELETE FROM endereco WHERE id = ?;";
    private static final String UPDATE_ENDERECO_SQL = "UPDATE endereco SET rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, laboratorio_id WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM endereco;";

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

    public void insertEndereco(Endereco entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_ENDERECO_SQL)) {
            preparedStatement.setString(1, entidade.getRua());
            preparedStatement.setString(2, entidade.getNumero());
            preparedStatement.setString(3, entidade.getComplemento());
            preparedStatement.setString(4, entidade.getCep());
            preparedStatement.setString(5, entidade.getCidade());
            preparedStatement.setInt(6, entidade.getLaboratorio_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Endereco selectEndereco(int id) {
        Endereco entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ENDERECO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String bairro = rs.getString("bairro");
                String cep = rs.getString("cep");
                String cidade = rs.getString("cidade");
                Integer laboratorio_id = rs.getInt("laboratorio_id");

                entidade = new Endereco(id, rua, numero, complemento, bairro, cep, cidade, laboratorio_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Endereco> selectAllEndereco() {
        List<Endereco> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_ENDERECO)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String bairro = rs.getString("bairro");
                String cep = rs.getString("cep");
                String cidade = rs.getString("cidade");
                Integer laboratorio_id = rs.getInt("laboratorio_id");

                entidades.add(new Endereco(id, rua, numero, complemento, bairro, cep, cidade, laboratorio_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteEndereco(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_ENDERECO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateEndereco(Endereco entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_ENDERECO_SQL)) {
            statement.setString(1, entidade.getRua());
            statement.setString(2, entidade.getNumero());
            statement.setString(3, entidade.getComplemento());
            statement.setString(4, entidade.getCep());
            statement.setString(5, entidade.getCidade());
            statement.setInt(6, entidade.getLaboratorio_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
