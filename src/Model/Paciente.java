package Model;

import java.sql.Date;

public class Paciente extends GenericModel {
    private String nome;
    private Date dt_nascimento;

    public Paciente(Integer id, String nome, Date dt_nascimento) {
        this.nome = nome;
        this.dt_nascimento = dt_nascimento;
        super.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(Date dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id'" + this.getId() + "\'" +
                "nome='" + nome + '\'' +
                ", dt_nascimento=" + dt_nascimento +
                '}';
    }
}
