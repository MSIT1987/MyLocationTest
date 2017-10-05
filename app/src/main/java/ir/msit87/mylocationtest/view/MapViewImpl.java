package ir.msit87.mylocationtest.view;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ir.msit87.mylocationtest.MainActivity;
import ir.msit87.mylocationtest.MapActivity;
import ir.msit87.mylocationtest.R;
import ir.msit87.mylocationtest.adapter.MapSearchAdapter;
import ir.msit87.mylocationtest.api.ApiService;
import ir.msit87.mylocationtest.frameWork.ui.custom.AMEditText;
import ir.msit87.mylocationtest.frameWork.ui.custom.PrimaryButton;
import ir.msit87.mylocationtest.model.InputQuery;
import ir.msit87.mylocationtest.model.Predictions;
import ir.msit87.mylocationtest.presenter.MapPresenter;
import ir.msit87.mylocationtest.presenter.MapPresenterImpl;

/**
 * Created by MSIT on 10/3/2017.
 */

@SuppressLint("ValidFragment")
public class MapViewImpl extends Fragment implements MapView{

    private MapPresenter presenter;
    private View rootView;
    private MapSearchAdapter adapter;

    View mainView;

    InputQuery inputQuery;

    AMEditText editText;

    PrimaryButton button;

    private View.OnClickListener onClickListener = null;

    public MapViewImpl(MapPresenter presenter, View.OnClickListener onClickListener) {

        this.onClickListener = onClickListener;
        ////

        this.presenter = presenter;

        presenter.setView(this);



        //mainView = LayoutInflater.from(MapActivity.context).inflate(R.layout.activity_maps, null);

        editText = (AMEditText) mainView.findViewById(R.id.search_input);

        button = (PrimaryButton) mainView.findViewById(R.id.ok_button);

        //button.setOnClickListener(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //Toast.makeText(MapActivity.context, "Hello", Toast.LENGTH_LONG).show();

        rootView = inflater.inflate(R.layout.recycler_fragment, container, false);

        if (inputQuery == null)
            return rootView;

//        inputQuery = presenter.getInputText();

        presenter.getMap(inputQuery);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.search_result_recyclerview);

        //recyclerView.setLayoutManager(new LinearLayoutManager(MapActivity.context));

        //adapter = new MapSearchAdapter(MapActivity.context);

        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void errorProgress(String error) {

    }

    @Override
    public void onMapObtain(List<Predictions> predictionsList) {
        adapter.updateLocationList(predictionsList);
    }

//    @Override
//    public void onEditTextChangeListener(InputQuery inputQuery) {
//
//    }

//    @Override
//    public void onEditTextChangeListener(AMEditText editText) {
////        editText = (AMEditText) mainView.findViewById(R.id.search_input);
//        String searchInput = editText.getEditableText().toString();
//        inputQuery = new InputQuery();
//        inputQuery.setInput(searchInput);
//        inputQuery.setLanguage(ApiService.LANGUAGE);
//        inputQuery.setComponent(ApiService.COMPONENT);
//        inputQuery.setRadius(ApiService.RADIUS);
//        inputQuery.setKey(ApiService.API_KEY);
//    }


//    @Override
//    public void onClick(View v) {
//        Toast.makeText(MapActivity.context, "Hello", Toast.LENGTH_LONG).show();
//    }

//    @Override
//    public void onClick(View v) {
////        presenter.getInputText(editText);
//        Toast.makeText(MapActivity.context, "Hello", Toast.LENGTH_LONG).show();
//        presenter.getInputText(editText);
//    }
}
