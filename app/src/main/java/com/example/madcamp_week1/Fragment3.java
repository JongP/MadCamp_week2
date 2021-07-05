package com.example.madcamp_week1;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Fragment3 extends Fragment {

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private MapPOIItem createMarkers(String place, double latitude, double longitude){

        MapPOIItem marker = new MapPOIItem();

        marker.setItemName(place);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.YellowPin); // 마커를 클릭했을때, 기본으로 제공하는 YellowPin 마커 모양.
        marker.setShowDisclosureButtonOnCalloutBalloon(false);

        return marker;
    }

    private Coord[] createCoords(){
        Coord[] coords = new Coord[20];

        coords[0] = new Coord(36.36267352354882,127.35791396778204);
        coords[1] = new Coord(36.36351372100507, 127.35721028348622);
        coords[2] = new Coord(36.36299396642154, 127.35623018342451);
        coords[3] = new Coord(36.362026813544816, 127.35341241453233);
        coords[4] = new Coord(36.36363775538186, 127.35867327094368);
        coords[5] = new Coord(36.36313293783038, 127.35872666517896);
        coords[6] = new Coord(36.36281587360104, 127.35777534296157);
        coords[7] = new Coord(36.363362434975976, 127.35732379972953);
        coords[8] = new Coord(36.363921648692795, 127.35791411233888);
        coords[9] = new Coord(36.36307281302966, 127.35774030706231);
        coords[10] = new Coord(36.36204705787819, 127.35418409141468);
        coords[11] = new Coord(36.36233053115967, 127.35507674131362);
        coords[12] = new Coord(36.36320980385998, 127.35713368467495);
        coords[13] = new Coord(36.363252085749686, 127.35806146578395 );
        coords[14] = new Coord(36.36257295195989, 127.35764331130726);
        coords[15] = new Coord(36.36364603925113, 127.35891565295813);
        coords[16] = new Coord(36.36336164618246, 127.35908983679943);
        coords[17] = new Coord(36.36259191694817, 127.3573286336779);
        coords[18] = new Coord(36.362151648881444, 127.35386144616314);
        coords[19] = new Coord(36.36105869295062, 127.35395677507192);

        return coords;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        MapView mapView = new MapView(getActivity());

        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view_id);

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(36.3622662202898, 127.3562463651629), true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        ArrayList<String> restList = new ArrayList<>();

        try {
            AssetManager assetManager = getActivity().getAssets();

            InputStream is = assetManager.open("jsonDirectory/restaurantList.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonData);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jo = jsonArray.getJSONObject(i);
                String name = jo.getString("name");
                restList.add(name.replace("\n", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Coord[] coords = createCoords();

        for(int i = 0; i < restList.size(); i++){
            mapView.addPOIItem(createMarkers(restList.get(i),coords[i].getLatitude(), coords[i].getLongitude()));
        }

        mapViewContainer.addView(mapView);

        return view;
    }

    class Coord{
        double latitude;
        double longitude;

        public Coord(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}