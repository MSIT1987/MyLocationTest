package ir.msit87.mylocationtest.presenter;

import android.location.Location;

import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import ir.msit87.mylocationtest.model.InputQuery;
import ir.msit87.mylocationtest.view.MapView;

/**
 * Created by MSIT on 10/3/2017.
 */

public interface MapPresenter {

    void setView(MapView mapView);

    void getMap(InputQuery inputQuery);

    void onLocationChanged(Location location);

    void onLocationFailed(@FailType int failedType);

    void onProcessTypedChange(@ProcessType int processType);

    void destroy();
}
