package local.medipush;

import local.medipush.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired public PatientRepository patientRepository;      //patient  DB
    @Autowired public MedRepository medRepository;              //medicine DB


    /**
     *  save
     *  - 넘겨받은 patient를 서버에 저장
     *  - 새 약의 병용금기 성분이 기존의 약들에 들어있는지 확인 후, 있다면 주의 문구를 생성
     *  - 환자가 임신 중일 경우, 새 약에 임부금기 성분이 있는지 확인하고 있다면 주의 문구를 생성
     **/
    public void save(Patient patient){
        String med_name = patient.getTake_med().get(0).getProdName();      //새로 넘어온 복용 약 이름
        Patient findPatient = patientRepository.findBySSN(patient.getSSN());    //넘어온 SSN으로 환자 존재유무 판별
        List<String> newCautions = new ArrayList<String>();     //DUR 주의 문구


        // 기존의 환자가 존재하면
        if(findPatient != null) {
            // 기존의 약들과 주의 문구를 저장
            List<MedInPatient> newList = new ArrayList<MedInPatient>();
            newList.addAll(findPatient.getTake_med());
            newCautions.addAll(findPatient.getCautions());

            // 새 약에 대한 DUR-병용금기 주의 문구 업데이트

            // 새 약의 DUR 정보를 가져옴
            List<DURInfo> newMedInfos = medRepository.findByProdName(med_name).getCautionInfo();
            for(DURInfo info : newMedInfos){
                // 만약 병용금기 성분이 존재한다면
                if(info.getDur().equals("병용금기")){   //getDUR : DURInfo 객체에서 DUR을 반환

                    // 기존의 약들에서 탐색
                    for(MedInPatient prevMed : newList){
                        String prevMedName = prevMed.getProdName();

                        // 기존의 약에 들어있는 성분들을 하나하나 탐색
                        List<String> prevMedIngr = medRepository.findByProdName(prevMedName).getIngredient();
                        for(String ingr : prevMedIngr){

                            // 병용금기에 해당하는 성분이 기존의 약에 들어있다면 주의 문구 추가
                            if(ingr.contains(info.getIngr())){  //getIngr : DURInfo 객체에서 DUR에 해당하는 성분을 반환
                                String warn = med_name + "와(과) " + prevMedName + "은(는) 같이 복용하지 마십시오.";
                                newCautions.add(warn);
                                break;
                            }
                        }
                    }
                }
            }

            // patient에 복용 약 List 정보 업데이트
            newList.addAll(patient.getTake_med());
            patient.setTake_med(newList);
            // 원래의 데이터를 삭제
            patientRepository.deleteById(findPatient.getId());
        }


        // 새 약에 대한 DUR-임부금기 주의 문구 업데이트
        if(patient.getPregnant()) {
            List<DURInfo> newMedInfos = medRepository.findByProdName(med_name).getCautionInfo();
            for(DURInfo info : newMedInfos){
                if(info.getDur().equals("임부금기")){   // DUR-임부금기 정보가 있으면
                    // 새 약에 대한 주의 문구를 업데이트
                    String warn = "임신 중이므로 " + med_name + "복용에 주의가 필요합니다.";
                    newCautions.add(warn);
                    break;
                }
            }
        }

        // 경고 문구 업데이트
        patient.setCautions(newCautions);

        //데이터 저장
        patientRepository.save(patient);
    }




    /**
     *  findInfo
     *  - 넘겨받은 SSN에 대하여 그에 맞는 환자를 찾아 반환
     **/
    public Patient findInfo(String SSN){
        return patientRepository.findBySSN(SSN);
    }


    /**
     *  deleteInfo
     *  - 넘겨받은 SSN과 medName에 대해 SSN에 해당하는 환자의 medName 약을 삭제
     **/
    public void deleteInfo(String SSN, String medName){
        // SSN에 해당하는 환자를 반환
        Patient findPatient = patientRepository.findBySSN(SSN);
        if(findPatient != null) {
            // 환자 안의 medName 약을 삭제
            findPatient.deleteMed(medName);
            // 환자 주의 문구 List 안의 medName이 들어가는 문장을 삭제
            findPatient.deleteCautions(medName);
            // 원래의 정보를 삭제
            patientRepository.deleteById(findPatient.getId());

            // 복용할 약이 남아있는 경우에만 데이터 저장
            if(findPatient.getTake_med().size() != 0) {
                patientRepository.save(findPatient);
            }
        }
    }

    /**
     *  searchMed
     *  - name을 포함하는 약을 List로 반환
     **/
    public List<Medicine> searchMed(String name){
        return medRepository.findByProdNameLike(name);
    }
}
