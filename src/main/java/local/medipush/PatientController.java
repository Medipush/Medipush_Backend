package local.medipush;

import local.medipush.domain.MedInfo;
import local.medipush.domain.Medicine;
import local.medipush.domain.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if(form.getSSN() != null) {
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

            patient.addTake_med(med);

            patientService.save(patient);
        }
        return "redirect:/";
    }

    @GetMapping("/searchWindow")
    public String search(@RequestParam("medName") String name, Model model){
        List<MedInfo> medRes = patientService.searchMed(name);
        model.addAttribute("medRes", medRes);
        return "/searchWindow";
    }



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