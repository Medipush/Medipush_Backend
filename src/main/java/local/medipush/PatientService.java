package local.medipush;

import local.medipush.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired public PatientRepository patientRepository;
    @Autowired public MedRepository medRepository;

    public void save(Patient patient){
        String med_name = patient.getTake_med().get(0).getProdName();
        Patient findPatient = patientRepository.findBySSN(patient.getSSN());
        List<String> newCautions = new ArrayList<String>();

        if(findPatient != null) {
            List<Medicine> newList = new ArrayList<Medicine>();
            newList.addAll(findPatient.getTake_med());
            newCautions.addAll(findPatient.getCautions());

            List<DURInfo> infos = medRepository.findByProdName(med_name).getCautionInfo();
            for(DURInfo d : infos){
                if(d.getDur().equals("병용금기")){
                    for(Medicine m : newList){
                        String oldMed = m.getProdName();
                        List<String> oldIngr = medRepository.findByProdName(oldMed).getIngredient();
                        for(String s : oldIngr){
                            if(s.contains(d.getIngr())){
                                String warn = med_name + "와(과) " + oldMed + "은(는) 같이 복용하지 마십시오.";
                                newCautions.add(warn);
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
                    newCautions.add(warn);
                    break;
                }
            }
        }
        patient.setCautions(newCautions);
        patientRepository.save(patient);
    }

    public Patient findInfo(String SSN){
        return patientRepository.findBySSN(SSN);
    }

    public void deleteInfo(String SSN, String medName){
        Patient findPatient = patientRepository.findBySSN(SSN);
        if(findPatient != null) {
            findPatient.deleteMed(medName);
            findPatient.deleteCautions(medName);
            patientRepository.deleteById(findPatient.getId());

            if(findPatient.getTake_med().size() != 0) {
                patientRepository.save(findPatient);
            }
        }
    }

    public List<MedInfo> searchMed(String name){
        return medRepository.findByProdNameLike(name);
    }
}
