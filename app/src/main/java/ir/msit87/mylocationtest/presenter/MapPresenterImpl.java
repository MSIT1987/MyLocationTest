package ir.msit87.mylocationtest.presenter;

import android.location.Location;

import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.util.List;

import ir.msit87.mylocationtest.model.InputQuery;
import ir.msit87.mylocationtest.model.MapModel;
import ir.msit87.mylocationtest.model.MapModelImpl;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.view.MapView;

/**
 * MapPresenterImpl Class Created by MSIT on 10/3/2017.
 */

public class MapPresenterImpl implements MapPresenter {

    private MapView mapView;
    private MapModelImpl mapModel;


    public MapPresenterImpl(MapModelImpl mapModel) {
        this.mapModel = mapModel;
    }


    @Override
    public void setView(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void getMap(InputQuery inputQuery) {
        mapModel.FetchDataFromGoogleMap(new MapModel.GetMapCallBack() {
            @Override
            public void onSuccess(List<Predictions> list) {
                mapView.onMapObtain(list);
            }

            @Override
            public void onFailure(String error) {
                mapView.errorProgress(error);
            }

            @Override
            public void onNetworkFailure() {

            }
        }, inputQuery);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onLocationFailed(@FailType int failedType) {

    }

    @Override
    public void onProcessTypedChange(@ProcessType int processType) {

    }

    @Override
    public void destroy() {
        mapView = null;
    }

//    @Override
//    public void getInputText(InputQuery inputQuery) {
//        mapView.onEditTextChangeListener(inputQuery);
//    }
}
