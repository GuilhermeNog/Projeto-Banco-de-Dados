package DAO;

import Model.MaterialExame;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialExameDAO extends ConexaoDB {
    private static final String INSERT_MATERIALEXAME_SQL = "INSERT INTO materialexame (material, observacao) VALUES (?, ?);";
    private static final String SELECT_MATERIALEXAME_BY_ID = "SELECT id, material, observacao FROM materialexame WHERE id = ?";
    private static final String SELECT_ALL_MATERIALEXAME = "SELECT * FROM materialexame;";
    private static final String DELETE_MATERIALEXAME_SQL = "DELETE FROM materialexame WHERE id = ?;";
    private static final String UPDATE_MATERIALEXAME_SQL = "UPDATE materialexame SET material = ?, observacao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM materialexame;";

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

    public void insertMaterialExame(MaterialExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MATERIALEXAME_SQL)) {
            preparedStatement.setString(1, entidade.getMaterial());
            preparedStatement.setString(2, entidade.getObservacao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MaterialExame selectMaterialExame(int id) {
        MaterialExame entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MATERIALEXAME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String material = rs.getString("material");
                String observacao = rs.getString("observacao");

                entidade = new MaterialExame(id, material, observacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<MaterialExame> selectAllMaterialExame() {
        List<MaterialExame> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MATERIALEXAME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String material = rs.getString("material");
                String observacao = rs.getString("observacao");

                entidades.add(new MaterialExame(id, material, observacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMaterialExame(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MATERIALEXAME_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMaterialExame(MaterialExame entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MATERIALEXAME_SQL)) {
            statement.setString(1, entidade.getMaterial());
            statement.setString(2, entidade.getObservacao());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
