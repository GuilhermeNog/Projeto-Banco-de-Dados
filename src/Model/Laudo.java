package Model;

import java.sql.Date;

public class Laudo extends GenericModel {
    private String assinatura_digital;
    private Date dt_resultado;
    private String codigo;
    private Integer solicitacao_exame_id;

    public Laudo(Integer id, String assinatura_digital, Date dt_resultado, String codigo, Integer solicitacao_exame_id) {
        this.assinatura_digital = assinatura_digital;
        this.dt_resultado = dt_resultado;
        this.codigo = codigo;
        this.solicitacao_exame_id = solicitacao_exame_id;
        super.setId(id);
    }

    public String getAssinatura_digital() {
        return assinatura_digital;
    }

    public void setAssinatura_digital(String assinatura_digital) {
        this.assinatura_digital = assinatura_digital;
    }

    public Date getDt_resultado() {
        return dt_resultado;
    }

    public void setDt_resultado(Date dt_resultado) {
        this.dt_resultado = dt_resultado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getSolicitacao_exame_id() {
        return solicitacao_exame_id;
    }

    public void setSolicitacao_exame_id(Integer solicitacao_exame_id) {
        this.solicitacao_exame_id = solicitacao_exame_id;
    }

    @Override
    public String toString() {
        return "Laudo{" +
                "id'" + this.getId() + "\'" +
                "assinatura_digital='" + assinatura_digital + '\'' +
                ", dt_resultado=" + dt_resultado +
                ", codigo='" + codigo + '\'' +
                ", solicitacao_exame_id=" + solicitacao_exame_id +
                '}';
    }
}
