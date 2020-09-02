package local.medipush;

import local.medipush.domain.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedRepository extends MongoRepository<Medicine, String> {
    public Medicine findByProdName(String prod_name);    //정확히 prod_name인 이름을 가진 약을 DB에서 불러옴
    public List<Medicine> findByProdNameLike(String regex); //이름에 prod_name이 포함된 약들을 DB에서 불러옴
}
