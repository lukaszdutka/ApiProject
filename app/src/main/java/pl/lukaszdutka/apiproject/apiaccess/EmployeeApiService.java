package pl.lukaszdutka.apiproject.apiaccess;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by user on 08.05.2018.
 */

public interface EmployeeApiService {

    @GET("employee")
    Call<EmployeesResponseBody> getAllEmployeesList();

    @GET("employee/{id}")
    Call<EmployeeResponseBody> getEmployeeWithId(String id);

    @POST("create")
    Call<EmployeesResponseBody> createEmployee(ApiEmployee employee);

    @PUT("update/{id}")
    Call<EmployeesResponseBody> updateEmployeeWithId();

    @DELETE("delete/{id}")
    Call<EmployeesResponseBody> deleteEmployeeWithId();
}

