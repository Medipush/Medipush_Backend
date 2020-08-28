package local.medipush;

import local.medipush.DURMRepository;
import local.medipush.DURPRepository;
import local.medipush.MedRepository;
import local.medipush.PatientRepository;
import local.medipush.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired public PatientRepository patientRepository;
    @Autowired public DURPRepository durpRepository;
    @Autowired public DURMRepository durmRepository;
    @Autowired public MedRepository medRepository;

    public void save(Patient patient){

        String med_name = patient.getTake_med().get(0).getProd_name();
        Patient findPatient = patientRepository.findBySSN(patient.getSSN());

        //System.out.println("findPatient = " + findPatient.toString());

        if(findPatient != null) {
            List<Medicine> newList = new ArrayList<Medicine>();
            newList.addAll(findPatient.getTake_med());
            /**
             * doubled medicine exception
             */

            /**
             * medicine validity check is required(PatientController)
             */

            List<DURInfo> infos = medRepository.findByProdName(med_name).getCautionInfo();
            for(DURInfo d : infos){
                if(d.getDur().equals("병용금기")){
                    for(Medicine m : newList){
                        String oldMed = m.getProd_name();
                        List<String> oldIngr = medRepository.findByProdName(oldMed).getIngredients();
                        for(String s : oldIngr){
                            if(s.contains(d.getIngr())){
                                String warn = med_name + "와(과) " + oldMed + "은(는) 같이 복용하지 마십시오.";
                                patient.addCaution(warn);
                                break;
                            }
                        }
                    }
                }
            }

            newList.addAll(patient.getTake_med());
            patient.setTake_med(newList);

            patientRepository.deleteById(findPatient.getId());
        }


        if(patient.getPregnant()) {
            List<DURInfo> infos = medRepository.findByProdName(med_name).getCautionInfo();
            for(DURInfo d : infos){
                if(d.getDur().equals("임부금기")){
                    String warn = "임신 중이므로 " + med_name + "복용에 주의가 필요합니다.";
                    patient.addCaution(warn);
                    break;
                }
            }
        }
        patientRepository.save(patient);
    }



    /**
     * receive take_med info(date) from mobile app
     * change take_med
     */

    public Patient findInfo(String SSN){
        /**
         * NPE
         */
        return patientRepository.findBySSN(SSN);
    }

    public void deleteInfo(String SSN, String medName){
        Patient findPatient = patientRepository.findBySSN(SSN);

        if(findPatient != null) {
            findPatient.deleteMed(medName);
            findPatient.deleteCautions(medName);
            patientRepository.deleteById(findPatient.getId());
        }

        patientRepository.save(findPatient);
    }
}
