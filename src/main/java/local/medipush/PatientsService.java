package local.medipush;

import local.medipush.domain.Medicine;
import local.medipush.domain.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsService {

    @Autowired public PatientsRepository patientsRepository;

    public void save(Patients patients){

        Patients findPatients = patientsRepository.findBySSN(patients.getSSN());

        //System.out.println("findPatients = " + findPatients.toString());

        if(findPatients != null) {
            List<Medicine> newList = new ArrayList<Medicine>();
            newList.addAll(findPatients.getTake_med());
            /**
             * doubled medicine exception
             */
            newList.addAll(patients.getTake_med());
            patients.setTake_med(newList);

            patientsRepository.deleteById(findPatients.getId());
        }

        patientsRepository.save(patients);
    }

    /**
     * receive take_med info(date) from mobile app
     * change take_med
     */

}
