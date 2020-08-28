package local.medipush;

import local.medipush.domain.Medicine;
import local.medipush.domain.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    PatientService patientService = new PatientService();

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String medInfo(){
        return "infoInsert";
    }

    @PostMapping("/infoInsert_do")
    public String create(PatientForm form){
        System.out.println("create start");
        Patient patient = new Patient();
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

        patientService.save(patient);
        System.out.println("create end");
        return "redirect:/";
    }

    /**
     *
     * medicine validity check
     *
     */



    @ResponseBody
    @RequestMapping(value = "/infoRequest", produces = "application/json;charset=UTF-8")
    public Patient returnInfo(String SSN){
        return patientService.findInfo(SSN);
    }

    @RequestMapping("/infoDelete")
    public void deleteInfo(String SSN, String medName){
        patientService.deleteInfo(SSN, medName);
    }
}