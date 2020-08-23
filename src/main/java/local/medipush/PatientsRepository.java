package local.medipush;

import local.medipush.domain.Patients;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends MongoRepository<Patients, String> {
    public Patients findBySSN(String SSN);
}
