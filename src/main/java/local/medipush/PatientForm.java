package local.medipush;

import local.medipush.domain.MedInPatient;

import java.util.List;

public class PatientForm {
        /**
         *  약사가 환자 정보를 입력하여 환자 정보를 서버에 저장할 때
         *  넘겨받은 값들을 저장하기 위한 클래스
         **/

        private String SSN;     //주민번호
        private String name;       //이름
        private boolean pregnant;   //임신 여부
        private List<MedInPatient> take_med;    //약 정보 배열

        private String prodName;    //약 이름
        private boolean session1;   //아침
        private boolean session2;   //점심
        private boolean session3;   //저녁

        public String getProdName() {
            return prodName;
        }

        public boolean[] getTake_session() {
            boolean[] take_session = {session1, session2, session3};
            return take_session;
        }

        public String getSSN() {
            return SSN;
        }

        public void setSSN(String SSN) {
            this.SSN = SSN;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean getPregnant() {
            return pregnant;
        }

        public void setPregnant(boolean pregnant) {
            this.pregnant = pregnant;
        }

        public List<MedInPatient> getTake_med() {
            return take_med;
        }

        public void setTake_med(List<MedInPatient> take_med) {
            this.take_med = take_med;
        }

        public boolean isSession1() {
            return session1;
        }

        public void setSession1(boolean session1) {
            this.session1 = session1;
        }

        public boolean isSession2() {
            return session2;
        }

        public void setSession2(boolean session2) {
            this.session2 = session2;
        }

        public boolean isSession3() {
            return session3;
        }

        public void setSession3(boolean session3) {
            this.session3 = session3;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }
}

