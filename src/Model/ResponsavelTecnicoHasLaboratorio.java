package Model;

public class ResponsavelTecnicoHasLaboratorio extends GenericModel {
    private Integer responsavel_tecnico_id;
    private Integer laboratorio_id;

    public ResponsavelTecnicoHasLaboratorio(Integer id, Integer responsavel_tecnico_id, Integer laboratorio_id) {
        this.responsavel_tecnico_id = responsavel_tecnico_id;
        this.laboratorio_id = laboratorio_id;
        super.setId(id);
    }

    public Integer getResponsavel_tecnico_id() {
        return responsavel_tecnico_id;
    }

    public void setResponsavel_tecnico_id(Integer responsavel_tecnico_id) {
        this.responsavel_tecnico_id = responsavel_tecnico_id;
    }

    public Integer getLaboratorio_id() {
        return laboratorio_id;
    }

    public void setLaboratorio_id(Integer laboratorio_id) {
        this.laboratorio_id = laboratorio_id;
    }

    @Override
    public String toString() {
        return "ResponsavelTecnicoHasLaboratorio{" +
                "id'" + this.getId() + "\'" +
                "responsavel_tecnico_id=" + responsavel_tecnico_id +
                ", laboratorio_id=" + laboratorio_id +
                '}';
    }
}
