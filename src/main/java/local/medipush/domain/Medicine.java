package local.medipush.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "medicine")
public class Medicine {
    /**
     *  DB에 있는 약 정보를 가져오기 위한 클래스
     *  {"prodName":"기네스타정","ingredient":["실로스타졸"],"cautionInfo":[{"ingr":"유당","dur":"첨가제주의"}]}
     *    약 이름                들어있는 성분                DUR 위험 정보      성분          DUR
     **/

    @Id
    private String id;
    private String prodName;    //약 이름
    private List<String> ingredient;    //들어있는 성분
    private List<DURInfo> cautionInfo;  //DURInfo List

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public List<DURInfo> getCautionInfo() {
        return cautionInfo;
    }

    public void setCautionInfo(List<DURInfo> cautionInfo) {
        this.cautionInfo = cautionInfo;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

    public String durToString() {
        //약 안에 있는 DUR 정보들을 약사에게 출력하는데 사용
        //html 형식의 문자열을 리턴

        if(cautionInfo != null) {
            StringBuffer sb = new StringBuffer();
            for (DURInfo d : cautionInfo) {
                sb.append(d.getDur()).append(": ").append(d.getIngr()).append("<br>");
            }
            return sb.toString();
        }
        else{ return ""; }
    }
}
