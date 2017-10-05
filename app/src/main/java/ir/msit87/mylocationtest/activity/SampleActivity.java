package ir.msit87.mylocationtest.activity;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;

import ir.msit87.mylocationtest.R;
import ir.msit87.mylocationtest.SamplePresenter;
import ir.msit87.mylocationtest.SamplePresenter.SampleView;


/**
 * Created by MSIT on 8/24/2017.
 */

public class SampleActivity extends LocationBaseActivity implements SampleView {

    TextView locationText;
    ProgressDialog progressDialog;
    SamplePresenter samplePresenter;

    /**
     * Implement LocationBaseActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_display_layout);
        locationText = (TextView) findViewById(R.id.locationText);
        samplePresenter = new SamplePresenter(this);
        getLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getLocationManager().isWaitingForLocation() && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        samplePresenter.destroy();
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        /**
         *  String rationalMessage,  String gpsMessage
         */
        return Configurations.defaultConfiguration("Gimme the permission!", "Would you mind to turn GPS on?");
    }

    @Override
    public void onLocationChanged(Location location) {
        samplePresenter.onLocationChanged(location);
    }

    @Override
    public void onLocationFailed(int type) {
        samplePresenter.onLocationFailed(type);
    }

    @Override
    public void onProcessTypeChanged(int processType) {
        samplePresenter.onProcessTypeChanged(processType);
    }

    /**
     * Implement SampleView Functions
     */
    @Override
    public String getText() {
        return locationText.getText().toString();
    }

    @Override
    public void setText(String text) {
        locationText.setText(text);
    }

    @Override
    public void updateProgress(String text) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(text);
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     *
     */
    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Getting location...");
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }
}
