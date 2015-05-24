package com.amarlabs.findblooddonors;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlotLocationFragmentActivity extends Fragment implements OnMapReadyCallback {

	public GPSTracker gps;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View mainView = inflater.inflate(R.layout.activity_fragment, container, false);
		
		
		MapFragment mapFragment = (MapFragment) this.getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return mainView;
	}
	
	@Override
	public void onMapReady(GoogleMap map) {
		
		double latitude = 0;
		double longitude = 0;
		
		gps = new GPSTracker(this.getActivity().getApplicationContext());
		 
        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        }else{
            gps.showSettingsAlert();
        }
        
        LatLng currentLocation = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13));

        map.addMarker(new MarkerOptions()
                .title("You")
                .snippet("+919739990234")
                .position(currentLocation));
    }

	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}
}