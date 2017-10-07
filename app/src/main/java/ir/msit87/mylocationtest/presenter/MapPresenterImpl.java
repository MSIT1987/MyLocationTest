package ir.msit87.mylocationtest.presenter;

import android.app.ProgressDialog;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.List;
import java.util.concurrent.Future;

import ir.msit87.mylocationtest.MapActivity;
import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;
import ir.msit87.mylocationtest.model.InputPredictionQuery;
import ir.msit87.mylocationtest.model.MapModel;
import ir.msit87.mylocationtest.model.MapModelImpl;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.model.Results;
import ir.msit87.mylocationtest.view.MapView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MapPresenterImpl Class Created by MSIT on 10/3/2017.
 */

public class MapPresenterImpl implements MapPresenter {

    private MapView mapView;
    private MapModelImpl mapModel;

    private final Double MY_DEFAULT_LAT = 53.0;
    private final Double MY_DEFAULT_LNG = 31.0;

    public MapPresenterImpl(MapModelImpl mapModel) {
        this.mapModel = mapModel;
    }

    @Override
    public void setView(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void getPrediction(InputPredictionQuery inputPredictionQuery) {

        mapModel.getPredictions(new MapModel.GetPredictionCallBack() {
            @Override
            public void onSuccess(List<Predictions> list) {
                mapView.onPredictionsObtain(list);
            }

            @Override
            public void onFailure(String error) {
                mapView.errorProgress(error);
            }

            @Override
            public void onNetworkFailure() {

            }
        }, inputPredictionQuery);
    }

    @Override
    public void getLocation(String place_id, String key) {

        mapModel.getLocations(new MapModel.GetLocationCallBack() {
            @Override
            public void onSuccess(List<Results> list) {
                Results results = list.get(0);
                Double Lat = results.getGeometry().getLocation().getLat();
                Double Lng = results.getGeometry().getLocation().getLng();
                LatLng latLng = new LatLng(Lat, Lng);
                mapView.setLatLng(latLng);
            }

            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onNetworkFailure() {

            }
        }, place_id, key);
    }

    @Override
    public void getProgress(String message) {
        mapModel.asyncTasks(new MapModel.GetProgressCallBack() {
            @Override
            public void onCompleted(String message) {
                mapView.dismissProgress();
            }

            @Override
            public void onError(String error) {
                mapView.errorProgress(error);
            }

            @Override
            public void onNext(String message) {
                mapView.showProgress(message);
            }
        }, message);
    }

    @Override
    public void onLocationChanged(Location location) {
        Double Lat = location.getLatitude() != 0 ? location.getLatitude() : MY_DEFAULT_LAT;
        Double Lng = location.getLongitude() != 0 ? location.getLongitude() : MY_DEFAULT_LNG;
        LatLng latLng = new LatLng(Lat, Lng);
        mapView.setLatLng(latLng);
    }

    @Override
    public void onLocationFailed(@FailType int failType) {

        switch (failType) {

            case FailType.TIMEOUT: {
                //mapView.setText("Couldn't get location, and timeout!");
                break;
            }
            case FailType.PERMISSION_DENIED: {
                //sampleView.setText("Couldn't get location, because user didn't give permission!");
                break;
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                //sampleView.setText("Couldn't get location, because network is not accessible!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_NOT_AVAILABLE: {
                //sampleView.setText("Couldn't get location, because Google Play Services not available!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_CONNECTION_FAIL: {
                //sampleView.setText("Couldn't get location, because Google Play Services connection failed!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DIALOG: {
                //sampleView.setText("Couldn't display settingsApi dialog!");
                break;
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DENIED: {
                //sampleView.setText("Couldn't get location, because user didn't activate providers via settingsApi!");
                break;
            }
            case FailType.VIEW_DETACHED: {
//                sampleView.setText("Couldn't get location, because in the process view was detached!");
                break;
            }
            case FailType.VIEW_NOT_REQUIRED_TYPE: {
//                sampleView.setText("Couldn't get location, because view wasn't sufficient enough to fulfill given configuration!");
                break;
            }
            case FailType.UNKNOWN: {
//                sampleView.setText("Ops! Something went wrong!");
                break;
            }
        }
    }

    @Override
    public void onProcessTypedChange(@ProcessType int processType) {

        switch (processType) {

            case ProcessType.ASKING_PERMISSIONS:
                break;
            case ProcessType.GETTING_LOCATION_FROM_GOOGLE_PLAY_SERVICES:
                mapView.updateProgress("در حال دریافت اطلاعات از گوگل پلی می باشد...");
                break;
            case ProcessType.GETTING_LOCATION_FROM_GPS_PROVIDER:
                mapView.updateProgress("در حال دریافت اطلاعات از GPS می باشد...");
                break;
            case ProcessType.GETTING_LOCATION_FROM_NETWORK_PROVIDER:
                mapView.updateProgress("در حال دریافت اطلاعات از اینترنت می باشد...");
                break;
            case ProcessType.GETTING_LOCATION_FROM_CUSTOM_PROVIDER:
                break;
            default:
                break;
        }
    }

    @Override
    public void destroy() {
        mapView = null;
    }

    @Override
    public void showProgress() {
        String message = "سیستم در حال دریافت اطلاعات مکانی شما می باشد...";
        mapView.showProgress(message);
    }

}
