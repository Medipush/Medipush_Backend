package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "test")
public class MedInfo {
    @Id
    private String id;
    private String prodName;
    private List<String> ingredient;
    private List<DURInfo> cautionInfo;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public List<DURInfo> getCautionInfo() {
        return cautionInfo;
    }

    public void setCautionInfo(List<DURInfo> cautionInfo) {
        this.cautionInfo = cautionInfo;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

}
