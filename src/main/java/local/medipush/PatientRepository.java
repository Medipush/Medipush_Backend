package local.medipush;

import local.medipush.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    public Patient findBySSN(String SSN);   //DB에서 SSN에 해당하는 환자를 반환
}
