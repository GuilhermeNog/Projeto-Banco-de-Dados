package Model;

public class UnidadeMedida extends GenericModel {
    private String descricao;

    public UnidadeMedida(Integer id, String descricao) {
        this.descricao = descricao;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "UnidadeMedida{" +
                "id'" + this.getId() + "\'" +
                "descricao='" + descricao + '\'' +
                '}';
    }
}
