package local.medipush;

import local.medipush.domain.Medicine;
import local.medipush.domain.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    @Autowired PatientsService patientsService = new PatientsService();

    @Autowired
    public PatientController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @GetMapping("/")
    public String medInfo(){
        return "medinfoInsert";
    }

    @PostMapping("/medinfoInsert_do")
    public String create(PatientForm form){
        Patients patient = new Patients();
        patient.setName(form.getName());
        patient.setSSN(form.getSSN());
        patient.setPregnant(form.getPregnant());

        /**
         * multiple input
         */
        Medicine med = new Medicine();
        med.setProd_name(form.getProd_name());
        med.setTake_session(form.getTake_session());

        List<Medicine> take_med = new ArrayList<Medicine>();
        take_med.add(med);
        patient.setTake_med(take_med);

        System.out.println("patient = " + patient.toString());

        patientsService.save(patient);
        return "redirect:/";
    }

}
