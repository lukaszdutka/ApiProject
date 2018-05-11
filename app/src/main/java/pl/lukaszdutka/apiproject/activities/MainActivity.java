package pl.lukaszdutka.apiproject.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import pl.lukaszdutka.apiproject.R;
import pl.lukaszdutka.apiproject.adapter.ApiItemsAdapter;
import pl.lukaszdutka.apiproject.apiaccess.ApiEmployee;
import pl.lukaszdutka.apiproject.apiaccess.EmployeeApiService;
import pl.lukaszdutka.apiproject.apiaccess.EmployeesResponseBody;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    //@BindString(R.string.missingSearchData)
    String missingSearchData;

    @BindString(R.string.apiFailure)
    String apiFailure;

    private List<ApiEmployee> employeeList = new ArrayList<>();

    private Retrofit retrofit;
    private EmployeeApiService service;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ContentResolver contentResolver;
    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.m_recyclerview);
        ButterKnife.bind(this);

        initializeActionBar();
        initializeRetrofit();
        //readEmployeeList();
    }

    private void initializeActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(EmployeeApiService.class);
    }

    private void initializeViewAdapter() {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ApiItemsAdapter(employeeList, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void readEmployeeList() {
        service.getAllEmployeesList().enqueue(new Callback<EmployeesResponseBody>() {

            @Override
            public void onResponse(Call<EmployeesResponseBody> call, Response<EmployeesResponseBody> response) {
                for (ApiEmployee employee : response.body().getEmployees()) {
                    employeeList.add(employee);
                }
                initializeViewAdapter();
            }

            @Override
            public void onFailure(Call<EmployeesResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), apiFailure, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
