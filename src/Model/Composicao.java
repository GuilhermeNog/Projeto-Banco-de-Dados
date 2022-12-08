package Model;

public class Composicao extends GenericModel {
    private Integer exame_id;
    private Integer composicao_exame_id;
    private Integer valor_referencia_composicao_exame_id;

    public Composicao(Integer id, Integer exame_id, Integer composicao_exame_id, Integer valor_referencia_composicao_exame_id) {
        this.exame_id = exame_id;
        this.composicao_exame_id = composicao_exame_id;
        this.valor_referencia_composicao_exame_id = valor_referencia_composicao_exame_id;
        super.setId(id);
    }

    public Integer getExame_id() {
        return exame_id;
    }

    public void setExame_id(Integer exame_id) {
        this.exame_id = exame_id;
    }

    public Integer getComposicao_exame_id() {
        return composicao_exame_id;
    }

    public void setComposicao_exame_id(Integer composicao_exame_id) {
        this.composicao_exame_id = composicao_exame_id;
    }

    public Integer getValor_referencia_composicao_exame_id() {
        return valor_referencia_composicao_exame_id;
    }

    public void setValor_referencia_composicao_exame_id(Integer valor_referencia_composicao_exame_id) {
        this.valor_referencia_composicao_exame_id = valor_referencia_composicao_exame_id;
    }

    @Override
    public String toString() {
        return "Composicao{" +
                "id'" + this.getId() + "\'" +
                "exame_id=" + exame_id +
                ", composicao_exame_id=" + composicao_exame_id +
                ", valor_referencia_composicao_exame_id=" + valor_referencia_composicao_exame_id +
                '}';
    }
}
