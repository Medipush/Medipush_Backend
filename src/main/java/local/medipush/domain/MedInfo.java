package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "medicine")
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

    public String cautionToString() {
        if(cautionInfo != null) {
            StringBuffer sb = new StringBuffer();
            for (DURInfo d : cautionInfo) {
                sb.append('[').append(d.getDur()).append(": ").append(d.getIngr()).append("] ");
            }
            return sb.toString();
        }
        else{return "";}
    }

}
