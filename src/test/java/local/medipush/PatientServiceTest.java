package local.medipush;

import local.medipush.domain.MedInfo;
import local.medipush.domain.Medicine;
import local.medipush.domain.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PatientServiceTest {

    @Autowired PatientService patientsService = new PatientService();
/*
    @Test
    void save() {
        Patient patient = new Patient();
        Medicine med = new Medicine();

        med.setProd_name("helloprod");
        boolean[] ts = {true, false, true};
        med.setTake_session(ts);

        List<Medicine> tm = new ArrayList<Medicine>();
        tm.add(med);

        patient.setName("kim");
        patient.setSSN("1234");
        patient.setPregnant(true);
        patient.setTake_med(tm);

        System.out.println("test start");
        System.out.println(patient.toString());
        System.out.println(patientsService);

        patientsService.save(patient);
    }

    @Test
    void deleteInfo() {
        patientsService.deleteInfo("12345", "hello");
    }

    @Test
    void find(){
        String med_name = "에듀란트정25밀리그램(릴피비린염산염)";
        patientsService.findMed(med_name);
    }


    @Test
    void search(){
        String s = "가";
        List<MedInfo> list =  patientsService.searchMed(s);
        System.out.println(list.size() + "founded");
        for(MedInfo m : list){
            System.out.println(m.getProdName());
        }
    }
*/
}