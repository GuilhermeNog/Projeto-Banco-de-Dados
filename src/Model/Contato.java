package Model;

public class Contato extends GenericModel {
    private String telefone;
    private Integer laboratorio_id;

    public Contato(Integer id, String telefone, Integer laboratorio_id) {
        this.telefone = telefone;
        this.laboratorio_id = laboratorio_id;
        super.setId(id);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getLaboratorio_id() {
        return laboratorio_id;
    }

    public void setLaboratorio_id(Integer laboratorio_id) {
        this.laboratorio_id = laboratorio_id;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id'" + this.getId() + "\'" +
                "telefone='" + telefone + '\'' +
                ", laboratorio_id=" + laboratorio_id +
                '}';
    }
}
