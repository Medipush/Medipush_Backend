package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "dur_mix")
public class DURMix {
    @Id
    private String id;
    private List<String> mix;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMix() {
        return mix;
    }

    public void setMix(List<String> mix) {
        this.mix = mix;
    }
}
