package local.medipush.domain;

public class DURInfo {
    /**
     *  Medicine 안의 DUR 정보를 JSON 객체로 받아오기 위한 클래스
     *
     *      {"dur":"병용금기","ingr":"아바나필"}
     *        DUR정보         성분
     **/

    private String dur;     //DUR 정보
    private String ingr;    //성분

    public String getIngr() {
        return ingr;
    }

    public void setIngr(String ingr) {
        this.ingr = ingr;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }
}
