package pl.lukaszdutka.apiproject.activities;

import android.content.ContentResolver;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.lukaszdutka.apiproject.R;
import pl.lukaszdutka.apiproject.apiaccess.ApiEmployee;
import pl.lukaszdutka.apiproject.apiaccess.EmployeeApiService;
import pl.lukaszdutka.apiproject.apiaccess.EmployeeResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EmployeeDetailsActivity extends AppCompatActivity {

    @BindView(R.id.employee_name)
    TextView employeeName;

    @BindView(R.id.employee_age)
    TextView employeeAge;

    @BindView(R.id.employee_salary)
    TextView employeeSalary;

    @BindView(R.id.employee_viewer)
    ViewPager viewer;

    @BindString(R.string.apiFailure)
    String apiFailure;

    private ApiEmployee apiEmployee;

    private Retrofit retrofit;
    private EmployeeApiService service;

    private ContentResolver contentResolver;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        ButterKnife.bind(this);
        setActivitySettings();

        initializeRetrofit();
        readEmployee();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(EmployeeApiService.class);
    }

    private void readEmployee() {
        String employeeId = getIntent().getStringExtra("eId");

        service.getEmployeeWithId(employeeId).enqueue(new Callback<EmployeeResponseBody>() {
            @Override
            public void onResponse(Call<EmployeeResponseBody> call, Response<EmployeeResponseBody> response) {
                apiEmployee = response.body().getEmployee();
                initializeEmployee();
            }

            @Override
            public void onFailure(Call<EmployeeResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), apiFailure, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeEmployee() {
        employeeName.setText(apiEmployee.getEmployeeName());
        employeeAge.setText(apiEmployee.getEmployeeAge());
        employeeSalary.setText(apiEmployee.getEmployeeSalary());
    }

    private void setActivitySettings() {
        employeeSalary.setMovementMethod(new ScrollingMovementMethod());
        //employeeSource.setMovementMethod(new ScrollingMovementMethod());
    }

}

