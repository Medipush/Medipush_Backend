package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Document (collection = "patient")
public class Patient {
    /**
     * DB에 있는 환자 정보를 가져오기 위한 클래스
     *  {"SSN":"0009021234567","name":"박테스트","pregnant":false,"take_med":[]}
     *    환자 식별 정보          환자 이름         환자 임신 여부      복용중인 약
     *
     **/


    @Id
    private String id;
    private String SSN;     //환자 식별 정보 (주민등록번호)
    private String name;    //환자 이름
    private boolean pregnant;   //환자 임신 여부
    private List<MedInPatient> take_med = new ArrayList<MedInPatient>();    //환자가 복용중인 약 List
    private List<String> cautions = new ArrayList<String>();    //환자에게 적용되는 주의 문구 List

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

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

    public List<MedInPatient> getTake_med() {
        return take_med;
    }

    public void setTake_med(List<MedInPatient> take_med) {
        this.take_med = take_med;
    }

    public void addTake_med(MedInPatient med){
        this.take_med.add(med);
    }


    public void deleteMed(String medName){
        //MedInPatient(환자가 복용하고 있는 약) List에서 이름이 medName인것 삭제

        Iterator<MedInPatient> it = take_med.iterator();
        while(it.hasNext()){
            if(it.next().getProdName().contains(medName)){
                it.remove();
            }
        }
    }

    public void deleteCautions(String medName){
        //Caution String List(환자에게 적용되는 주의 문구)에서 medName이 들어가는 것 삭제

        Iterator<String> it = cautions.iterator();
        while(it.hasNext()){
            if(it.next().contains(medName)){
                it.remove();
            }
        }
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", SSN='" + SSN + '\'' +
                ", name='" + name + '\'' +
                ", pregnant=" + pregnant +
                ", take_med=" + take_med +
                ", cautions=" + cautions +
                '}';
    }
}
