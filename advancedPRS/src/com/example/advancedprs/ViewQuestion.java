package com.example.advancedprs;

import com.example.advancedprs.R;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ViewQuestion extends Activity {

	private TextView mTextView;
	Button viewq;
	Button toChoice;
	Button toEssay;
	Button toDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		
		
		toChoice = (Button) findViewById(R.id.multichoice);
		toEssay = (Button) findViewById(R.id.essayquestion);
		toDrawable = (Button) findViewById(R.id.drawquestion);
		
		toChoice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent viewqIntent = new Intent(ViewQuestion.this, MultChoice.class);
				//myIntent.putExtra("key", value); //Optional parameters
				startActivity(viewqIntent);
			}
		});
		toEssay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent viewqIntent = new Intent(ViewQuestion.this, ShortEssay.class);
				//myIntent.putExtra("key", value); //Optional parameters
				startActivity(viewqIntent);
			}
		});
		toDrawable.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent viewqIntent = new Intent(ViewQuestion.this, Drawable.class);
				//myIntent.putExtra("key", value); //Optional parameters
				startActivity(viewqIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_question, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_view_question,
					container, false);
			return rootView;
		}
	}
	
	

}
