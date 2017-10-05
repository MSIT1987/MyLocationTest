package ir.msit87.mylocationtest.view;


import android.location.Location;

import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.List;

import ir.msit87.mylocationtest.model.Predictions;

/**
 * MapView Interface Created by MSIT on 10/3/2017.
 */

public interface MapView {

    void showProgress();

    void dismissProgress();

    void errorProgress(String error);

    void onMapObtain(List<Predictions> predictionsList);
}

