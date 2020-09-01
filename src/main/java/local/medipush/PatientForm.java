package local.medipush;

import local.medipush.domain.Medicine;

import java.util.List;

public class PatientForm {
        private String SSN;
        private String name;
        private boolean pregnant;
        private List<Medicine> take_med;

        private String prodName;
        private boolean session1;
        private boolean session2;
        private boolean session3;

        /**
         * multiple input
         */

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

        public List<Medicine> getTake_med() {
            return take_med;
        }

        public void setTake_med(List<Medicine> take_med) {
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

