package DAO;

import Model.ValorReferenciaComposicaoExame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValorReferenciaComposicaoExameDAO extends ConexaoDB {
    private static final String INSERT_VALORREFERENCIACOMPOSICAOEXAME_SQL = "INSERT INTO valorreferenciacomposicaoexame (valor_minimo, valor_maximo, limitador_minimo, limitador_maximo, unidade_medida_id) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_VALORREFERENCIACOMPOSICAOEXAME_BY_ID = "SELECT id, valor_minimo, valor_maximo, limitador_minimo, limitador_maximo, unidade_medida_id FROM valorreferenciacomposicaoexame WHERE id = ?";
    private static final String SELECT_ALL_VALORREFERENCIACOMPOSICAOEXAME = "SELECT * FROM valorreferenciacomposicaoexame;";
    private static final String DELETE_VALORREFERENCIACOMPOSICAOEXAME_SQL = "DELETE FROM valorreferenciacomposicaoexame WHERE id = ?;";
    private static final String UPDATE_VALORREFERENCIACOMPOSICAOEXAME_SQL = "UPDATE valorreferenciacomposicaoexame SET valor_minimo = ?, valor_maximo = ?, limitador_minimo = ?, limitador_maximo = ?, unidade_medida_id = ?, WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM valorreferenciacomposicaoexame;";

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

    public void insertValorReferenciaComposicaoExame(ValorReferenciaComposicaoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_VALORREFERENCIACOMPOSICAOEXAME_SQL)) {
            preparedStatement.setString(1, entidade.getValor_minimo());
            preparedStatement.setString(2, entidade.getValor_maximo());
            preparedStatement.setString(3, entidade.getLimitador_minimo());
            preparedStatement.setString(4, entidade.getValor_maximo());
            preparedStatement.setInt(5, entidade.getUnidade_medida_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ValorReferenciaComposicaoExame selectValorReferenciaComposicaoExame(int id) {
        ValorReferenciaComposicaoExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_VALORREFERENCIACOMPOSICAOEXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String valor_minimo = rs.getString("valor_minimo");
                String valor_maximo = rs.getString("valor_maximo");
                String limitador_minimo = rs.getString("limitador_minimo");
                String limitador_maximo = rs.getString("limitador_maximo");
                Integer unidade_medida_id = rs.getInt("unidade_medida_id");

                entidade = new ValorReferenciaComposicaoExame(id, valor_minimo, valor_maximo, limitador_minimo, limitador_maximo, unidade_medida_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<ValorReferenciaComposicaoExame> selectAllValorReferenciaComposicaoExame() {
        List<ValorReferenciaComposicaoExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_VALORREFERENCIACOMPOSICAOEXAME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String valor_minimo = rs.getString("valor_minimo");
                String valor_maximo = rs.getString("valor_maximo");
                String limitador_minimo = rs.getString("limitador_minimo");
                String limitador_maximo = rs.getString("limitador_maximo");
                Integer unidade_medida_id = rs.getInt("unidade_medida_id");

                entidades.add(new ValorReferenciaComposicaoExame(id, valor_minimo, valor_maximo, limitador_minimo, limitador_maximo, unidade_medida_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteValorReferenciaComposicaoExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_VALORREFERENCIACOMPOSICAOEXAME_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateValorReferenciaComposicaoExame(ValorReferenciaComposicaoExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_VALORREFERENCIACOMPOSICAOEXAME_SQL)) {
            statement.setString(1, entidade.getValor_minimo());
            statement.setString(2, entidade.getValor_maximo());
            statement.setString(3, entidade.getLimitador_minimo());
            statement.setString(4, entidade.getValor_maximo());
            statement.setInt(5, entidade.getUnidade_medida_id());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
