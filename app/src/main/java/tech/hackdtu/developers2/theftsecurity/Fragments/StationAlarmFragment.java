package tech.hackdtu.developers2.theftsecurity.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import tech.hackdtu.developers2.theftsecurity.PoJo.Metro;
import tech.hackdtu.developers2.theftsecurity.R;

/**
 * Created by ishaandhamija on 14/10/17.
 */

public class StationAlarmFragment extends Fragment {

    JSONArray obj;

    Spinner spnFrom,spnTo;
    String[] name ;
    String Source,Destination;
    int length;
    public static int from,to,flag=0;

    public static ArrayList<Metro> metro = new ArrayList<>();

    Button btnSetAlarm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        try {

            obj = new JSONArray(loadJsonFromAsset());
            name = new String[obj.length()];
            length = obj.length();
            for(int i=0;i<obj.length();i++)
            {
                try {
                    JSONObject json = obj.getJSONObject(i);

                    name[i] = json.getString("name");
                    JSONObject xJson = json.getJSONObject("details");
                    Metro thisMetro = new Metro(json.getString("name"),xJson.getString("line"),xJson.getString("latitude"),xJson.getString("longitude"));
                    metro.add(thisMetro);
                    Log.d("234234", "onCreate: " + name[i]);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            Log.d("123123", "onCreateView: " + obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_station_alarm, container, false);

        spnFrom = rootView.findViewById(R.id.spn_SelectSource);
        spnTo = rootView.findViewById(R.id.spn_SelectDestination);

        btnSetAlarm = rootView.findViewById(R.id.btn_SetAlarm);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,name);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnTo.setAdapter(adapter);
        spnFrom.setAdapter(adapter);


        spnTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Destination = spnTo.getSelectedItem().toString();
                Log.d("124124", "onItemClick: " + Destination);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Source = spnFrom.getSelectedItem().toString();
                Log.d("124124", "onItemSelected: " + Source);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DifferentStations()){

//                    Toast.makeText(getContext(), "Sucess Different Stations", Toast.LENGTH_SHORT).show();
                    Log.d("234234", "onClick: " + length);
                    for(int i=0;i<length;i++)
                    {
                        Log.d("12341234", "onClick: " + metro.get(i).getName());
                        Log.d("1231234", "onClick: " + metro.get(i).getLine());
                        if(metro.get(i).getName().equals(Source) && metro.get(i).getLine().equals("[\"Yellow Line\"]"))
                        {
//                            Toast.makeText(getContext(), "Source on Yellow Line", Toast.LENGTH_SHORT).show();
                            from = i;
                            Log.d("234234", "onClick: " + from);
                        }
                        else if(metro.get(i).getName().equals(Destination) && metro.get(i).getLine().equals("[\"Yellow Line\"]"))
                        {
//                            Toast.makeText(getContext(), "Destination on Yellow Line", Toast.LENGTH_SHORT).show();
                            to = i;
                            Log.d("234234", "onClick: " + to);
                        }
                    }



                }
                else
                {
                    Toast.makeText(getContext(), "Please Select Different Stations", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }



    public String loadJsonFromAsset(){

        String json = null;

        try
        {
            InputStream is = this.getActivity().getAssets().open("metro.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return  json;
    }


    public boolean DifferentStations()
    {
        if(Source.equals(Destination))
        {
            return false;
        }

        return true;
    }
}