package local.medipush;

import local.medipush.domain.Medicine;
import local.medipush.domain.Patients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientsServiceTest {

    @Autowired PatientsService patientsService = new PatientsService();

    @Test
    void save() {
        Patients patient = new Patients();
        Medicine med = new Medicine();

        med.setProd_name("hello");
        boolean[] ts = {true, false, true};
        med.setTake_session(ts);

        List<Medicine> tm = new ArrayList<Medicine>();
        tm.add(med);

        patient.setName("kim");
        patient.setSSN("00101-101");
        patient.setPregnant(true);
        patient.setTake_med(tm);

        System.out.println("test start");
        System.out.println(patient.toString());
        System.out.println(patientsService);

        patientsService.save(patient);
    }
}