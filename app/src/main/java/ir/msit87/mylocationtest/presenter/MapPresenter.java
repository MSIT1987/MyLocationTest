package ir.msit87.mylocationtest.presenter;

import android.app.ProgressDialog;
import android.location.Location;

import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.List;

import ir.msit87.mylocationtest.model.InputPredictionQuery;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.view.MapView;

/**
 * Created by MSIT on 10/3/2017.
 */

public interface MapPresenter {

    void setView(MapView mapView);

    void getPrediction(InputPredictionQuery inputPredictionQuery);

    void getLocation(String place_id, String key);

    void getProgress(String message);

    void onLocationChanged(Location location);

    void onLocationFailed(@FailType int failedType);

    void onProcessTypedChange(@ProcessType int processType);

    void destroy();

    void showProgress();

//    void asyncTasks(String message);


}
