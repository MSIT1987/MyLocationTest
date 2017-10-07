package ir.msit87.mylocationtest;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.daimajia.easing.Skill;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.msit87.mylocationtest.adapter.MapSearchAdapter;
import ir.msit87.mylocationtest.api.ApiService;
import ir.msit87.mylocationtest.frameWork.helpers.AnimationHelper;
import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;
import ir.msit87.mylocationtest.frameWork.ui.custom.AMEditText;
import ir.msit87.mylocationtest.frameWork.ui.custom.PrimaryButton;
import ir.msit87.mylocationtest.listener.OnSearchRecyclerClickListener;
import ir.msit87.mylocationtest.model.InputPredictionQuery;
import ir.msit87.mylocationtest.model.MapModelImpl;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.model.Results;
import ir.msit87.mylocationtest.presenter.MapPresenter;
import ir.msit87.mylocationtest.presenter.MapPresenterImpl;
import ir.msit87.mylocationtest.view.MapView;

/**
 * MapActivity Created by MSIT on 10/3/2017.
 */

public class MapActivity extends LocationBaseActivity implements MapView, OnMapReadyCallback, View.OnClickListener, OnSearchRecyclerClickListener {

    MapPresenter presenter;

    ProgressDialog progressDialog;


    MapSearchAdapter adapter;

    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    LatLng latLng;

    InputPredictionQuery inputPredictionQuery;

    @Bind(R.id.search_input)
    AMEditText editText;

    @Bind(R.id.ok_button)
    PrimaryButton button;

    @Bind(R.id.search_clear_button)
    View search_clear_button;

    @Bind(R.id.search_result_recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.location_marker)
    ImageView locationMarker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MapPresenterImpl(new MapModelImpl());
        presenter.setView(this);

        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);

        search_clear_button.setOnClickListener(this);

        button.setOnClickListener(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    search_clear_button.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                } else {
                    search_clear_button.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

                String searchInput = editable.toString();
                inputPredictionQuery = new InputPredictionQuery();
                inputPredictionQuery.setInput(searchInput);
                inputPredictionQuery.setLanguage(ApiService.LANGUAGE);
                inputPredictionQuery.setComponent(ApiService.COMPONENT);
                inputPredictionQuery.setRadius(ApiService.RADIUS);
                inputPredictionQuery.setKey(ApiService.API_KEY);
                initialize(inputPredictionQuery);
            }
        });

        getLocation();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getLocationManager().isWaitingForLocation() && !getLocationManager().isAnyDialogShowing()) {
            presenter.showProgress();
//            presenter.asyncTasks(message);
            //presenter.getProgress(message);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        dismissProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private void initialize(InputPredictionQuery inputPredictionQuery) {

        if (inputPredictionQuery == null)
            return;

//        presenter.setView(this);
        presenter.getPrediction(inputPredictionQuery);

        if (recyclerView.getVisibility() == View.INVISIBLE)
            recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MapSearchAdapter(this, this);
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
    public void showProgress(final String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
//            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 2000);
        }
    }

    @Override
    public void updateProgress(String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        }

    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void errorProgress(String error) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog = null;
            progressDialog = new ProgressDialog(this);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage(error);
        }
    }

    @Override
    public void onPredictionsObtain(List<Predictions> predictionsList) {
        adapter.updateLocationList(predictionsList);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapActivity.this.googleMap = googleMap;

//        MapActivity.this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
//        MapActivity.this.googleMap.setPadding(0, UtilityHelper.dpToPx(93), 0, UtilityHelper.dpToPx(93));
        MapActivity.this.googleMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.map_style)));


        if (MapActivity.this.latLng != null) {
            UtilityHelper.hideSoftKeyboard(this);
            googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker))
                    .title(MapActivity.this.editText.getText().toString())
                    .position(latLng)
            );

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.latLng, 15.0f));
        }
    }

    @Override
    public void setLatLng(final LatLng latLng) {
        this.latLng = latLng;

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MapActivity.this.recyclerView.setVisibility(View.INVISIBLE);

//        mapFragment.getMapAsync(this);
//        onMapReady(googleMap);

        locationMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetData(String place_id, String key) {
        presenter.getLocation(place_id, key);
    }


    /**
     * LocationManager By "yayaLocationManager"
     */
    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration("درخواست مجوز", "آیا مایلید GPS خود را روشن کنید؟");
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

    @Override
    public void onSearchRecyclerClick(Object objectClick) {
        Predictions predictions = (Predictions) objectClick;
        editText.setText(predictions.getDescription());
    }


}
