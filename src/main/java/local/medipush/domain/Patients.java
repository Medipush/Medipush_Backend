package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document (collection = "patients")
public class Patients {
    @Id
    private String id;

    private String SSN;
    private String name;
    private boolean pregnant;
    private List<Medicine> take_med;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public List<Medicine> getTake_med() {
        return take_med;
    }

    public void setTake_med(List<Medicine> take_med) {
        this.take_med = take_med;
    }

    @Override
    public String toString() {
        return "Patients{" +
                "id='" + id + '\'' +
                ", SSN='" + SSN + '\'' +
                ", name='" + name + '\'' +
                ", pregnant=" + pregnant +
                ", take_med=" + take_med +
                '}';
    }
}
