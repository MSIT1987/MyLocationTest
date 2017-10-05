package ir.msit87.mylocationtest;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;


import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.List;

import ir.msit87.mylocationtest.adapter.MapSearchAdapter;
import ir.msit87.mylocationtest.api.ApiService;
import ir.msit87.mylocationtest.frameWork.ui.custom.AMEditText;
import ir.msit87.mylocationtest.frameWork.ui.custom.PrimaryButton;
import ir.msit87.mylocationtest.model.InputQuery;
import ir.msit87.mylocationtest.model.MapModelImpl;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.presenter.MapPresenter;
import ir.msit87.mylocationtest.presenter.MapPresenterImpl;
import ir.msit87.mylocationtest.view.MapView;

/**
 * Created by MSIT on 10/3/2017.
 */

public class MapActivity extends LocationBaseActivity implements MapView, View.OnClickListener {

    MapPresenter presenter;

    MapSearchAdapter adapter;

    InputQuery inputQuery;

    AMEditText editText;

    PrimaryButton button;

    View search_clear_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MapPresenterImpl(new MapModelImpl());

        setContentView(R.layout.activity_maps);

        search_clear_button = findViewById(R.id.search_clear_button);
        search_clear_button.setOnClickListener(this);

        button = (PrimaryButton) findViewById(R.id.ok_button);
        button.setOnClickListener(this);

        editText = (AMEditText) findViewById(R.id.search_input);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                    search_clear_button.setVisibility(View.INVISIBLE);
                else
                    search_clear_button.setVisibility(View.VISIBLE);

                String searchInput = editable.toString();
                inputQuery = new InputQuery();
                inputQuery.setInput(searchInput);
                inputQuery.setLanguage(ApiService.LANGUAGE);
                inputQuery.setComponent(ApiService.COMPONENT);
                inputQuery.setRadius(ApiService.RADIUS);
                inputQuery.setKey(ApiService.API_KEY);
                initialize(inputQuery);
            }
        });
    }

    private void initialize(InputQuery inputQuery) {

        presenter.setView(this);

        if (inputQuery == null)
            return;

        presenter.getMap(inputQuery);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_result_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MapSearchAdapter(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_clear_button:
                editText.setText("");
                Toast.makeText(this, "clear_button clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.search_icon:
                Toast.makeText(this, "search clicked.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }


    @Override
    public void errorProgress(String error) {

    }

    @Override
    public void onMapObtain(List<Predictions> predictionsList) {
        adapter.updateLocationList(predictionsList);
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        presenter.onLocationChanged(location);
    }

    @Override
    public void onLocationFailed(int type) {
        presenter.onLocationFailed(type);
    }

    @Override
    public void onProcessTypeChanged(int processType) {
        presenter.onProcessTypedChange(processType);
    }
}
