package com.amarlabs.findblooddonors;

import java.util.ArrayList;
import java.util.List;

import com.amarlabs.common.MarkerContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PlotLocationActivity extends FragmentActivity  implements OnMapReadyCallback, OnInfoWindowClickListener {

//	private PlotLocationFragmentActivity mPlotLocationFragment;
	
	public List<MarkerContent> markerList;
	public GPSTracker gps;
	public Double radius;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plot_location);
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
      
        Bundle bundle = getIntent().getExtras(); //Get the bundle
        radius = Double.parseDouble(bundle.getString("radius")) * 1000 ; //Extract the data
		
//		mPlotLocationFragment = new PlotLocationFragmentActivity();
//      setFragment(mPlotLocationFragment);
	}
	
	/*public void setFragment(Fragment frag)
    {   
         FragmentManager fm = getFragmentManager();  
         if (fm.findFragmentById(R.id.map) == null) {
            fm.beginTransaction().add(R.id.container, frag).commit();
        }
      
    }*/
	
	@Override
	public void onMapReady(GoogleMap map) {
		double latitude = 0;
		double longitude = 0;
		
		gps = new GPSTracker(this);
		 
		if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        }else{
            gps.showSettingsAlert();
        }
		
		System.out.println("Latitude: " + latitude + "Longitude: " + longitude);
		
		LatLng currentLocation = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13));

        map.addMarker(new MarkerOptions()
                .title("Your Location")
                .snippet("+919739990234")
                .position(currentLocation));
        
        
        
        
        CircleOptions circleOptions = new CircleOptions()
        .center( new LatLng(latitude, longitude) )
        .radius( radius )
        .fillColor(0x40ff0000)
        .strokeColor(Color.TRANSPARENT)
        .strokeWidth(2);
       
      // Get back the mutable Circle
      Circle circle = map.addCircle(circleOptions);
      // more operations on the circle...
      
      
       
        // Call to get all the donors
        getDonorsAround();
        
        for (MarkerContent marker1: markerList) {
        	LatLng markerLocation = new LatLng(Double.parseDouble(marker1.getLatitude()), Double.parseDouble(marker1.getLongitude()));
        	
        	map.addMarker(new MarkerOptions()
            .title(marker1.getTitle())
            .snippet(marker1.getSnippet())
            .position(markerLocation));
        }
        
        map.setOnInfoWindowClickListener(this);
    }
	
	
	public void getDonorsAround() {
		markerList = new ArrayList<MarkerContent>();
		
		// Make an api call to get all donors around
        
        // Parse the JSON and map it to object MarkerOptions
        
        // Set each marker for each blood donor
		
		MarkerContent marker2 = new MarkerContent();
		marker2.setTitle("Abhinav");
		marker2.setSnippet("+918095950002");
		marker2.setLatitude("16.9573279");
		marker2.setLongitude("80.7084197");
		
		markerList.add(marker2);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plot_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*switch (item.getItemId()) {
			case R.id.action_settings:
	    		goToSettings();
	    		return true;
	    	case R.id.action_profile:
	    		goToProfile();
	    		return true;
	    	case R.id.action_preference:
	    		goToPreference();
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    }*/
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Intent phoneIntent = new Intent(Intent.ACTION_CALL);
		phoneIntent.setData(Uri.parse("tel:"+ marker.getSnippet()));
		
		try {
	         startActivity(phoneIntent);
	         finish();
	         Log.i("Finished making a call...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(this, 
	         "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
	      }
		
	}
}
