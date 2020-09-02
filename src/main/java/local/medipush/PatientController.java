package local.medipush;

import local.medipush.domain.Medicine;
import local.medipush.domain.MedInPatient;
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
    public String Medicine(){
        return "infoInsert";
    }


    /**
        create
        - 약사가 환자 정보를 입력하여 환자 정보를 서버에 저장
     **/
    @PostMapping("/infoInsert_do")
    public String create(PatientForm form){
        if(form.getSSN() != null) {

            // 새로운 Patient 객체에다 Form에서 넘어온 정보를 옮김
            Patient patient = new Patient();
            patient.setName(form.getName());
            patient.setSSN(form.getSSN());
            patient.setPregnant(form.getPregnant());

            MedInPatient med = new MedInPatient();
            med.setProdName(form.getProdName());
            med.setTake_session(form.getTake_session());
            patient.addTake_med(med);

            //만든 Patient 객체를 저장
            patientService.save(patient);
        }
        return "redirect:/";
    }


    /**
     *  searchPatient
     *  - 환자 정보
     *  - 복용하는 약 정보
     *  - 검색한 약 정보 세 가지를 Model 에 넘겨줘야 함
     *
     *  - 넘겨준 Model 객체를 thymeleaf 템플릿을 이용해 html에 출력
     **/
    @GetMapping("/searchPWindow")
    public String searchP(@RequestParam("SSN") String ssn, @RequestParam("prodName") String prodName, Model model){

        // ssn에 해당하는 환자를 검색
        Patient patientRes = patientService.findInfo(ssn);
        if(patientRes != null) {

            // 찾은 환자(Patient)객체를 넘겨줌
            model.addAttribute("patient", patientRes);
            List<Medicine> takeMedRes = new ArrayList<Medicine>();
            for(MedInPatient m : patientRes.getTake_med()){
                takeMedRes.add(patientService.searchMed(m.getProdName()).get(0));
            }

            // 찾은 Patient가 복용하는 약 정보 List를 넘겨줌
            model.addAttribute("takeMedRes", takeMedRes);
        }

        if(!prodName.equals("")) {
            List<Medicine> sMedRes = patientService.searchMed(prodName);
            if (sMedRes != null) {
                // 약 이름으로 검색한 약 정보 List를 넘겨줌
                model.addAttribute("sMedRes", sMedRes);
            }
        }
        return "searchPWindow";
    }


    /**
     *  returnInfo
     *  - Android app에서 환자 정보를 parameter SSN 으로 요청했을 때
     *  - 환자 객체를 json 형태로 넘겨줌
     **/
    @ResponseBody
    @RequestMapping(value = "/infoRequest", produces = "application/json;charset=UTF-8")
    public Patient returnInfo(String SSN){
        return patientService.findInfo(SSN);
    }


    /**
     *  deleteInfo
     *  - andriod app에서 복용 약 삭제를 parameter medName, SSN으로 요청했을 때
     *  - 그 환자의 약을 삭제해줌
     **/
    @RequestMapping("/infoDelete")
    public void deleteInfo(String SSN, String medName){
        patientService.deleteInfo(SSN, medName);
    }
}