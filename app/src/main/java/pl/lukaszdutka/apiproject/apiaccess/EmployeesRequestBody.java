package pl.lukaszdutka.apiproject.apiaccess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 08.05.2018.
 */

public class EmployeesRequestBody {

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("q")
    @Expose
    private String q;

    public EmployeesRequestBody(String key, String q) {
        this.key = key;
        this.q = q;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

}
