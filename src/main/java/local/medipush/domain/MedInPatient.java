package local.medipush.domain;

import java.util.Arrays;

public class MedInPatient {
    /**
     * 환자의 약 복용 정보를 가지고 있는 객체
     * Patient 객체에 List로 들어감
     *
     *      {"prodName":"네바민정(레바미피드)","take_session":[true,false,false]}
     *        약 이름                         약 복용 시간(아침, 점심, 저녁) 배열
     **/

    private String prodName;    //약 이름
    private boolean[] take_session = new boolean[3];    //약 복용 시간(아침, 점심, 저녁) 배열

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public boolean[] getTake_session() {
        return take_session;
    }

    public void setTake_session(boolean[] take_session) {
        this.take_session = take_session;
    }

    @Override
    public String toString() {
        return "MedInPatient{" +
                "prod_name='" + prodName + '\'' +
                ", take_session=" + Arrays.toString(take_session) +
                '}';
    }
}
