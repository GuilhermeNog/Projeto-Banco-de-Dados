package Model;

public class Exame extends GenericModel {
    private Integer tipo_exame_id;
    private String descricao;
    private Integer material_exame_id;
    private String metodo;

    public Exame(Integer id, Integer tipo_exame_id, String descricao, Integer material_exame_id, String metodo) {
        this.tipo_exame_id = tipo_exame_id;
        this.descricao = descricao;
        this.material_exame_id = material_exame_id;
        this.metodo = metodo;
        super.setId(id);
    }

    public Integer getTipo_exame_id() {
        return tipo_exame_id;
    }

    public void setTipo_exame_id(Integer tipo_exame_id) {
        this.tipo_exame_id = tipo_exame_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getMaterial_exame_id() {
        return material_exame_id;
    }

    public void setMaterial_exame_id(Integer material_exame_id) {
        this.material_exame_id = material_exame_id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "id'" + this.getId() + "\'" +
                "tipo_exame_id=" + tipo_exame_id +
                ", descricao='" + descricao + '\'' +
                ", material_exame_id=" + material_exame_id +
                ", metodo='" + metodo + '\'' +
                '}';
    }
}
