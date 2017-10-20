package ir.msit87.mylocationtest.presenter;

import android.app.ProgressDialog;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import ir.msit87.mylocationtest.MapActivity;
import ir.msit87.mylocationtest.api.ApiClient;
import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;
import ir.msit87.mylocationtest.model.InputPredictionQuery;
import ir.msit87.mylocationtest.model.LocationResponse;
import ir.msit87.mylocationtest.model.MapModel;
import ir.msit87.mylocationtest.model.MapModelImpl;
import ir.msit87.mylocationtest.model.PlaceAutoCompleteResult;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.model.Results;
import ir.msit87.mylocationtest.modelDirection.DirectionResponse;
import ir.msit87.mylocationtest.modelDirection.InputDirectionQuery;
import ir.msit87.mylocationtest.modelDirection.Route;
import ir.msit87.mylocationtest.modelDirection.Step;
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

        ApiClient
                .getInstance()
                .getPredictions(inputPredictionQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PlaceAutoCompleteResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        callBack.onFailure(e.getMessage());
                        mapView.errorProgress(e.getMessage());
                    }

                    @Override
                    public void onNext(PlaceAutoCompleteResult placeAutoCompleteResult) {
                        if (placeAutoCompleteResult.getStatus().equals("OK")) {
                            List<Predictions> predictionsList = placeAutoCompleteResult.getPredictions();
                            mapView.onPredictionsObtain(predictionsList);
//                            callBack.onSuccess(predictionsList);
                        }
                    }
                });


//        mapModel.getPredictions(new MapModel.GetPredictionCallBack() {
//            @Override
//            public void onSuccess(List<Predictions> list) {
//                mapView.onPredictionsObtain(list);
//            }
//
//            @Override
//            public void onFailure(String error) {
//                mapView.errorProgress(error);
//            }
//
//            @Override
//            public void onNetworkFailure() {
//
//            }
//        }, inputPredictionQuery);
    }

    @Override
    public void getLocation(String place_id, String key) {

        ApiClient.getInstance()
                .getLocation(place_id, key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<LocationResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        callBack.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(LocationResponse locationResponse) {
                        if (locationResponse.getStatus().equals("OK")) {
                            List<Results> resultsList = locationResponse.getResults();
//                            callBack.onSuccess(resultsList);
                            Results results = resultsList.get(0);
                            Double Lat = results.getGeometry().getLocation().getLat();
                            Double Lng = results.getGeometry().getLocation().getLng();
                            LatLng latLng = new LatLng(Lat, Lng);
//                            mapView.setLatLng(latLng);

                            List<LatLng> twoPoints = mapView.getTwoPoints();

                            if (twoPoints.size() == 0) {
                                mapView.setOrigin(latLng);
                            } else if (twoPoints.size() == 1) {
                                mapView.setDestination(latLng);
                            } else if (twoPoints.size() > 1) {
                                mapView.clearTwoPoints();
                                mapView.setOrigin(latLng);
                            }
                        }
                    }
                });
    }

    @Override
    public void getDirection(InputDirectionQuery inputDirectionQuery) {

        ApiClient.getInstance()
                .getDirection(inputDirectionQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<DirectionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DirectionResponse directionResponse) {

                        if (directionResponse.getStatus().equals("OK")) {

//                            List<List<HashMap<String, String>>> pathList = new ArrayList<>();
                            List<HashMap<String, String>> path = new ArrayList();

                            /////////////////////
                            List<Route> routes = directionResponse.getRoutes();

                            List<Step> steps = routes.get(0).getLegs().get(0).getSteps();

                            for (int i = 0; i < steps.size(); i++) {
                                String polyLine = steps.get(i).getPolyline().getPoints();
                                List<LatLng> list = decodePolyline(polyLine);

                                for (int j = 0; j < list.size(); j++) {
                                    HashMap<String, String> hm = new HashMap<>();
                                    hm.put("lat", Double.toString(list.get(j).latitude));
                                    hm.put("lng", Double.toString(list.get(j).longitude));
                                    path.add(hm);
                                }
                            }

                            mapView.setPath(path);
                        }
                    }
                });
    }

    @Override
    public void getProgress(final String message) {

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String res = message;//your original doInBackground
                    subscriber.onNext(res);

                    subscriber.onCompleted();
                    // onNext would be comparable to AsyncTask.onProgressUpdate
                    // and usually applies when background process runs a loop
                } catch (Exception e) {
                    // if the process throws an exception or produces a result
                    // you'd consider error then use onError
                    subscriber.onError(e);
                }
            }
        });

        observable
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mapView.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mapView.errorProgress(e.getMessage());
                    }

                    @Override
                    public void onNext(String message) {
                        // result from Observable.onNext. The methods below correspond
                        // to their Observable counterparts.
                        mapView.showProgress(message);
                    }
                });

    }

    @Override
    public void onLocationChanged(Location location) {
        Double Lat = location.getLatitude() != 0 ? location.getLatitude() : MY_DEFAULT_LAT;
        Double Lng = location.getLongitude() != 0 ? location.getLongitude() : MY_DEFAULT_LNG;
        LatLng latLng = new LatLng(Lat, Lng);
//        mapView.setLatLng(latLng);

//        List<LatLng> twoPoints = mapView.getTwoPoints();
//
//        if (twoPoints.size() == 0) {
//            mapView.setOrigin(latLng);
//        } else if (twoPoints.size() == 1) {
//            mapView.setDestination(latLng);
//        } else  if (twoPoints.size() > 1) {
//            mapView.clearTwoPoints();
//            mapView.setOrigin(latLng);
//        }
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

    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> poly = new ArrayList<>();

        int index = 0;
        int length = encoded.length();

        int lat = 0;
        int lng = 0;

        while (index < length) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);


        }
        return poly;
    }
}
