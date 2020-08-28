package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dur_pregnant")
public class DURPregnant {
    @Id
    private String id;
    private int level;
    private String caution_info;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCaution_info() {
        return caution_info;
    }

    public void setCaution_info(String caution_info) {
        this.caution_info = caution_info;
    }
}
