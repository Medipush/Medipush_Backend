package local.medipush;

import local.medipush.domain.MedInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedRepository extends MongoRepository<MedInfo, String> {
    public MedInfo findByProdName(String prod_name);
    public List<MedInfo> findByProdNameLike(String regex);
}
