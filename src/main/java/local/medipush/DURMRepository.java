package local.medipush;

import local.medipush.domain.DURMix;
import local.medipush.domain.DURPregnant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DURMRepository extends MongoRepository<DURMix, String> {
}
