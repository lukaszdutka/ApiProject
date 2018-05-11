package pl.lukaszdutka.apiproject.apiaccess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 08.05.2018.
 */

public class EmployeesResponseBody {

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("Employees")
    @Expose
    private List<ApiEmployee> recipes = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ApiEmployee> getEmployees() {
        return recipes;
    }

    public void setEmployees(List<ApiEmployee> recipes) {
        this.recipes = recipes;
    }

}
