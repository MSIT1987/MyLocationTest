package ir.msit87.mylocationtest;

import android.app.ProgressDialog;
import android.graphics.Color;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import ir.msit87.mylocationtest.modelDirection.InputDirectionQuery;
import ir.msit87.mylocationtest.presenter.MapPresenter;
import ir.msit87.mylocationtest.presenter.MapPresenterImpl;
import ir.msit87.mylocationtest.view.MapView;

/**
 * MapActivity Created by MSIT on 10/3/2017.
 */

public class MapActivity extends LocationBaseActivity implements MapView, OnMapReadyCallback, OnSearchRecyclerClickListener {

    public static final String ICON_HEIGHT_KEY = "height";

    MapPresenter presenter;

    ProgressDialog progressDialog;

    MapSearchAdapter adapter;

    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    LatLng latLng;

    LatLng origin;
    LatLng destination;

    List<HashMap<String, String>> path = new ArrayList<>();

    List<LatLng> twoPoints = new ArrayList<>();

    InputPredictionQuery inputPredictionQuery;

    Boolean editFlag;

    @Bind(R.id.search_input)
    AMEditText editText;

//    @Bind(R.id.ok_button)
//    PrimaryButton button;

    @Bind(R.id.search_clear_button)
    View search_clear_button;

    @Bind(R.id.search_result_recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.location_marker)
    ImageView locationMarker;

    @Bind(R.id.my_location_button)
    PrimaryButton my_location_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);

        presenter = new MapPresenterImpl(new MapModelImpl());
        presenter.setView(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    search_clear_button.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                } else {
                    search_clear_button.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    search_clear_button.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                } else {
                    search_clear_button.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);

                    AnimationHelper.animateView(MapActivity.this.recyclerView, Skill.SineEaseInOut, ICON_HEIGHT_KEY, 250, (float) UtilityHelper.dpToPx(225));

                    String searchInput = editable.toString();
                    inputPredictionQuery = new InputPredictionQuery();
                    inputPredictionQuery.setInput(searchInput);
                    inputPredictionQuery.setLanguage(ApiService.LANGUAGE);
                    inputPredictionQuery.setComponent(ApiService.COMPONENT);
                    inputPredictionQuery.setRadius(ApiService.RADIUS);
                    inputPredictionQuery.setKey(ApiService.API_KEY);
                    initialize(inputPredictionQuery);

//                    MapActivity.this.my_location_button.setPrimaryBackgroundColor(MapActivity.this.getResources().getColor(R.color.colorPrimaryDark));


                }
            }
        });

        getLocation();

//        twoPoints = new ArrayList<>(2);

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

        if (inputPredictionQuery == null && editFlag)
            return;

        presenter.getPrediction(inputPredictionQuery);

        if (recyclerView.getVisibility() == View.INVISIBLE)
            recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MapSearchAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.my_location_button)
    public void onClick_My_location(View v) {

//        if (MapActivity.this.latLng != null) {
//
//            UtilityHelper.hideSoftKeyboard(this);
//
//            locationMarker.setVisibility(View.INVISIBLE);
//
//
//            googleMap
//                    .addMarker(new MarkerOptions()
//                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker))
//                            .title(MapActivity.this.editText.getText().toString())
//                            .position(latLng)
//                    );
//
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.latLng, 15.0f));
//        }
    }

    @OnClick(R.id.search_clear_button)
    public void onClick_clear(View v) {
        editText.setText("");
        this.editFlag = false;
    }

    @OnClick(R.id.search_icon)
    public void onClick_search_icon(View v) {
//        Toast.makeText(this, "search clicked.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(final String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
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
        MapActivity.this.googleMap.setPadding(0, UtilityHelper.dpToPx(93), 0, UtilityHelper.dpToPx(93));
        MapActivity.this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        MapActivity.this.googleMap.getUiSettings().setCompassEnabled(false);
        MapActivity.this.googleMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.map_style)));

        showPoints();
        showDirections();
    }


    private void showPoints() {
        /**
         *
         */
        if (!MapActivity.this.twoPoints.isEmpty()) {

            if (MapActivity.this.twoPoints.size() == 1) {


                MapActivity.this.googleMap.addMarker(new MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker))
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                                .title(MapActivity.this.editText.getText().toString())
                                .position(origin)
                );

                MapActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.origin, 15.0f));


//                MapActivity.this.my_location_button.hideLoading();

            } else if (MapActivity.this.twoPoints.size() == 2) {

//                MapActivity.this.my_location_button.hideLoading();


                UtilityHelper.hideSoftKeyboard(this);


                MapActivity.this.googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                        .title(MapActivity.this.editText.getText().toString())
                        .position(destination)
                );

                MapActivity.this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.destination, 15.0f));

            }
        }
    }


    private void showDirections() {

        ArrayList<LatLng> points = new ArrayList<>();

        if (!MapActivity.this.path.isEmpty()) {

            PolylineOptions polylineOptions = new PolylineOptions();
            MarkerOptions markerOptions = new MarkerOptions();


            for (int i = 0; i < path.size(); i++) {
                HashMap<String, String> point = path.get(i);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng latlng = new LatLng(lat, lng);

                points.add(latlng);
            }

            polylineOptions.addAll(points);
            polylineOptions.width(8);
            polylineOptions.color(Color.RED);

            MapActivity.this.googleMap.addPolyline(polylineOptions);

            MapActivity.this.path.clear();
        }
    }

    @Override
    public void setLatLng(final LatLng latLng) {
        this.latLng = latLng;
//
//        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        MapActivity.this.recyclerView.setVisibility(View.INVISIBLE);
//
//        locationMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setOrigin(LatLng origin) {
        this.origin = origin;

        this.twoPoints.add(origin);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MapActivity.this.recyclerView.setVisibility(View.INVISIBLE);

        locationMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDestination(LatLng destination) {
        this.destination = destination;
        this.twoPoints.add(destination);

        mapFragment.getMapAsync(this);
        MapActivity.this.recyclerView.setVisibility(View.INVISIBLE);

        onPathObtain();
    }

    @Override
    public void clearTwoPoints() {
        this.twoPoints.clear();
    }

    @Override
    public List<LatLng> getTwoPoints() {
        return twoPoints;
    }

    @Override
    public void setPath(List<HashMap<String, String>> path) {
        this.path = path;

        mapFragment.getMapAsync(this);
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
        this.editFlag = true;

//        MapActivity.this.my_location_button.showLoading();
    }


    private void onPathObtain() {

        InputDirectionQuery direction = new InputDirectionQuery();

        direction.setOrigin(origin.latitude + "," + origin.longitude);
        direction.setDestination(destination.latitude + "," + destination.longitude);
        direction.setSensor(ApiService.SENSOR);
        direction.setMode(ApiService.MODE);

        presenter.getDirection(direction);
    }
}
