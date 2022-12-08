package Model;

public class Laboratorio extends GenericModel {
    private String descricao;
    private String cnes;
    private String cnpj;
    private String crbm;
    private String nome_fantasia;

    public Laboratorio(Integer id, String descricao, String cnes, String cnpj, String crbm, String nome_fantasia) {
        this.descricao = descricao;
        this.cnes = cnes;
        this.cnpj = cnpj;
        this.crbm = crbm;
        this.nome_fantasia = nome_fantasia;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCrbm() {
        return crbm;
    }

    public void setCrbm(String crbm) {
        this.crbm = crbm;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
                "id'" + this.getId() + "\'" +
                "descricao='" + descricao + '\'' +
                ", cnes='" + cnes + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", crbm='" + crbm + '\'' +
                ", nome_fantasia='" + nome_fantasia + '\'' +
                '}';
    }
}
