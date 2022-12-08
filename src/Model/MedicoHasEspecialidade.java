package Model;

public class MedicoHasEspecialidade extends GenericModel {
    private Integer medico_id;
    private Integer especialidade_id;

    public MedicoHasEspecialidade(Integer id, Integer medico_id, Integer especialidade_id) {
        this.medico_id = medico_id;
        this.especialidade_id = especialidade_id;
        super.setId(id);
    }

    public Integer getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(Integer medico_id) {
        this.medico_id = medico_id;
    }

    public Integer getEspecialidade_id() {
        return especialidade_id;
    }

    public void setEspecialidade_id(Integer especialidade_id) {
        this.especialidade_id = especialidade_id;
    }

    @Override
    public String toString() {
        return "MedicoHasEspecialidade{" +
                "id'" + this.getId() + "\'" +
                "medico_id=" + medico_id +
                ", especialidade_id=" + especialidade_id +
                '}';
    }
}
