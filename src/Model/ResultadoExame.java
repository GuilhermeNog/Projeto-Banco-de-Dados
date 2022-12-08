package Model;

import java.sql.Date;

public class ResultadoExame extends GenericModel {
    private Date dt_exame;
    private String valor;
    private Integer composicao_id;
    private Integer laudo_id;

    public ResultadoExame(Integer id, Date dt_exame, String valor, Integer composicao_id, Integer laudo_id) {
        this.dt_exame = dt_exame;
        this.valor = valor;
        this.composicao_id = composicao_id;
        this.laudo_id = laudo_id;
        super.setId(id);
    }

    public Date getDt_exame() {
        return dt_exame;
    }

    public void setDt_exame(Date dt_exame) {
        this.dt_exame = dt_exame;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getComposicao_id() {
        return composicao_id;
    }

    public void setComposicao_id(Integer composicao_id) {
        this.composicao_id = composicao_id;
    }

    public Integer getLaudo_id() {
        return laudo_id;
    }

    public void setLaudo_id(Integer laudo_id) {
        this.laudo_id = laudo_id;
    }

    @Override
    public String toString() {
        return "ResultadoExame{" +
                "id'" + this.getId() + "\'" +
                "dt_exame=" + dt_exame +
                ", valor='" + valor + '\'' +
                ", composicao_id=" + composicao_id +
                ", laudo_id=" + laudo_id +
                '}';
    }
}
