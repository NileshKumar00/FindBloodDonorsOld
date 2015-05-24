package com.amarlabs.findblooddonors;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class HomeActivity extends ActionBarActivity {

	private Spinner spinner;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        setSpinners();
        
        // Blood donation eligibility link
//        http://www.redcrossblood.org/donating-blood/eligibility-requirements
        
    }
    
    private void setSpinners() {
    	setBloodGroupSpinnerContent();
    	setReasonToFindDonorsSpinner();
	}

	private void setReasonToFindDonorsSpinner() {
		
		final List<String> list = new ArrayList<String>();
		list.add("Accident");
		list.add("Blood Donation Camp");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		
		spinner = (Spinner) this.findViewById( R.id.spinner2);
		spinner.setAdapter( adapter );
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				list.get(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
        });
		
	}

	private void setBloodGroupSpinnerContent() {

    	final List<String> list = new ArrayList<String>();
		list.add("A-");list.add("AB-");list.add("O-");list.add("B-");
		list.add("A+");list.add("AB+");list.add("O+");list.add("B+");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		
		spinner = (Spinner) this.findViewById( R.id.spinner1);
		spinner.setAdapter( adapter );
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				list.get(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
        });
	}

    public void plotBloodDonorsLocation(View view) {

    	EditText mEdit   = (EditText)findViewById(R.id.editText1);
    	Intent intent = new Intent(this, PlotLocationActivity.class);
    	
    	Bundle bundle = new Bundle(); //Create the bundle
    	bundle.putString("radius", mEdit.getText().toString()); //Add your data to bundle
    	intent.putExtras(bundle); //Add the bundle to the intent
    	
    	startActivity(intent);
    	
//    	Intent intent2 = new Intent(this, LauncherActivity.class);
//    	startActivity(intent2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
        switch (item.getItemId()) {
        	case R.id.action_settings:
        		goToSettings();
        		return true;
        	case R.id.action_help:
        		goToHelp();
        		return true;
        	case R.id.action_share:
        		goToShare();
        		return true;
        	/*case R.id.action_invite:
        		goToInviteFriends();
        		return true;
        	case R.id.create_group:
        		goToCreateGroups();
        		return true;*/
        	default:
        		return super.onOptionsItemSelected(item);
        }
    }

	private void goToShare() {
		// TODO Create a message box
		
	}

	/*private void goToInviteFriends() {
		Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);		
	}*/

	private void goToHelp() {
		Intent intent = new Intent(this, HelpActivity.class);
    	startActivity(intent);
	}

	private void goToSettings() {
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
	}
}
