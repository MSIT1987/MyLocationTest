package ir.msit87.mylocationtest.view;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.List;

import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.model.Results;

/**
 * MapView Interface Created by MSIT on 10/3/2017.
 */

public interface MapView {

    void showProgress(String message);

    void updateProgress(String message);

    void dismissProgress();

    void errorProgress(String error);

    void onPredictionsObtain(List<Predictions> predictionsList);

    void setLatLng(LatLng latLng);

    //void onLocationObtain(List<Results> resultsList);
}

