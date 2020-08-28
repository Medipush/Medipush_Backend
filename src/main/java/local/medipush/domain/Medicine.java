package local.medipush.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medicine {
    private String prod_name;
    private boolean[] take_session = new boolean[3];

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
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
                "prod_name='" + prod_name + '\'' +
                ", take_session=" + Arrays.toString(take_session) +
                '}';
    }
}
