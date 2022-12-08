package Model;

public class MaterialExame extends GenericModel {
    private String material;
    private String observacao;

    public MaterialExame(Integer id, String material, String observacao) {
        this.material = material;
        this.observacao = observacao;
        super.setId(id);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "MaterialExame{" +
                "id'" + this.getId() + "\'" +
                "material='" + material + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
