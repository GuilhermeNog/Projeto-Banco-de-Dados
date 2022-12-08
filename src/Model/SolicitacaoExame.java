package Model;

import java.sql.Date;

public class SolicitacaoExame extends GenericModel {
    private String nm_prescito;
    private Integer consulta_medica_id;
    private Date dt_solicitacao;
    private Integer habilitacao_exame_id;
    private Integer exame_id;

    public SolicitacaoExame(Integer id, String nm_prescito, Integer consulta_medica_id, Date dt_solicitacao, Integer habilitacao_exame_id, Integer exame_id) {
        this.nm_prescito = nm_prescito;
        this.consulta_medica_id = consulta_medica_id;
        this.dt_solicitacao = dt_solicitacao;
        this.habilitacao_exame_id = habilitacao_exame_id;
        this.exame_id = exame_id;
        super.setId(id);
    }

    public String getNm_prescito() {
        return nm_prescito;
    }

    public void setNm_prescito(String nm_prescito) {
        this.nm_prescito = nm_prescito;
    }

    public Integer getConsulta_medica_id() {
        return consulta_medica_id;
    }

    public void setConsulta_medica_id(Integer consulta_medica_id) {
        this.consulta_medica_id = consulta_medica_id;
    }

    public Date getDt_solicitacao() {
        return dt_solicitacao;
    }

    public void setDt_solicitacao(Date dt_solicitacao) {
        this.dt_solicitacao = dt_solicitacao;
    }

    public Integer getHabilitacao_exame_id() {
        return habilitacao_exame_id;
    }

    public void setHabilitacao_exame_id(Integer habilitacao_exame_id) {
        this.habilitacao_exame_id = habilitacao_exame_id;
    }

    public Integer getExame_id() {
        return exame_id;
    }

    public void setExame_id(Integer exame_id) {
        this.exame_id = exame_id;
    }

    @Override
    public String toString() {
        return "SolicitacaoExame{" +
                "id'" + this.getId() + "\'" +
                "nm_prescito='" + nm_prescito + '\'' +
                ", consulta_medica_id=" + consulta_medica_id +
                ", dt_solicitacao=" + dt_solicitacao +
                ", habilitacao_exame_id=" + habilitacao_exame_id +
                ", exame_id=" + exame_id +
                '}';
    }
}
