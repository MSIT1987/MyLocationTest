package ir.msit87.mylocationtest;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

//                        googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(37.4233438, -122.0728817))
//                                .title("LinkedIn")
//                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(new LatLng(37.4629101, -122.2449094))
//                                .title("Facebook")
//                                .snippet("Facebook HQ: Menlo Park"));

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(35.691063, 51.407941))
                                .title("Apple"));

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.7226686, 51.3130596), 16));
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.4233438, -122.0728817))
//                .title("LinkedIn")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(37.4629101, -122.2449094))
//                .title("Facebook")
//                .snippet("Facebook HQ: Menlo Park"));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(35.7226686, 51.3130596))
                .title("Apple"));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.6940712, 51.389066), 12));
    }
}


//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//
//import ir.msit87.mylocationtest.activity.SampleActivity;
//import ir.msit87.mylocationtest.fragment.SampleFragmentActivity;
//import ir.msit87.mylocationtest.service.SampleServiceActivity;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    public void inActivityClick(View view) {
//        startActivity(new Intent(this, SampleActivity.class));
//    }
//
//    public void inFragmentClick(View view) {
//        startActivity(new Intent(this, SampleFragmentActivity.class));
//    }
//
//    public void inServiceClick(View view) {
//        startActivity(new Intent(this, SampleServiceActivity.class));
//    }
//}

//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.Html;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
//import com.google.android.gms.common.GooglePlayServicesRepairableException;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlaceAutocomplete;
//import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
//import com.google.android.gms.location.places.ui.PlaceSelectionListener;
//public class MainActivity extends AppCompatActivity implements PlaceSelectionListener {
//
//    private static final String LOG_TAG = "PlaceSelectionListener";
//    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
//            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
//    private static final int REQUEST_SELECT_PLACE = 1000;
//    private TextView locationTextView;
//    private TextView attributionsTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        locationTextView = (TextView) findViewById(R.id.txt_location);
//        attributionsTextView = (TextView) findViewById(R.id.txt_attributions);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        // Method #1
//        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_fragment);
//        autocompleteFragment.setOnPlaceSelectedListener(this);
//        autocompleteFragment.setHint("Search a Location");
//        autocompleteFragment.setBoundsBias(BOUNDS_MOUNTAIN_VIEW);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Method #2
//                try {
//                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
//                            .setBoundsBias(BOUNDS_MOUNTAIN_VIEW).build(MainActivity.this);
//                    startActivityForResult(intent, REQUEST_SELECT_PLACE);
//                } catch (GooglePlayServicesRepairableException |
//                        GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_search) {
//            // Method #3
//            try {
//                Intent intent = new PlaceAutocomplete.IntentBuilder
//                        (PlaceAutocomplete.MODE_OVERLAY)
//                        .setBoundsBias(BOUNDS_MOUNTAIN_VIEW)
//                        .build(MainActivity.this);
//                startActivityForResult(intent, REQUEST_SELECT_PLACE);
//            } catch (GooglePlayServicesRepairableException |
//                    GooglePlayServicesNotAvailableException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressLint("StringFormatMatches")
//    @Override
//    public void onPlaceSelected(Place place) {
//        Log.i(LOG_TAG, "Place Selected: " + place.getName());
//        locationTextView.setText(getString(R.string.formatted_place_data, place
//                .getName(), place.getAddress(), place.getPhoneNumber(), place
//                .getWebsiteUri(), place.getRating(), place.getId()));
//        if (!TextUtils.isEmpty(place.getAttributions())) {
//            attributionsTextView.setText(Html.fromHtml(place.getAttributions().toString()));
//        }
//    }
//
//    @Override
//    public void onError(Status status) {
//        Log.e(LOG_TAG, "onError: Status = " + status.toString());
//        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
//                Toast.LENGTH_SHORT).show();
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SELECT_PLACE) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlaceAutocomplete.getPlace(this, data);
//                this.onPlaceSelected(place);
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//                this.onError(status);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//}


