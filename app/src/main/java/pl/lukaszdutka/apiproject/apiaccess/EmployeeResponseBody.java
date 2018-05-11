package pl.lukaszdutka.apiproject.apiaccess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 09.05.2018.
 */

public class EmployeeResponseBody {

    @SerializedName("employee") //?
    @Expose
    private ApiEmployee employee;

    public ApiEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(ApiEmployee employee) {
        this.employee = employee;
    }

}