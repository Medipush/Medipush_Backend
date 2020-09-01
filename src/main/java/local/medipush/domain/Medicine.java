package local.medipush.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medicine {
    private String prodName;
    private boolean[] take_session = new boolean[3];

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
        return "Medicine{" +
                "prod_name='" + prodName + '\'' +
                ", take_session=" + Arrays.toString(take_session) +
                '}';
    }
}
