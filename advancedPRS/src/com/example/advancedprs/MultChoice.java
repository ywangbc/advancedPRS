package com.example.advancedprs;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;

import android.app.Activity;
import android.app.Fragment;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MultChoice extends Activity {

	Button submitButton;
	RadioGroup answerGroup;
	RadioGroup radioSexGroup;
	String myText;
	
	private void setText(String innerText)
	{
		myText = new String(innerText);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mult_choice);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		submitButton = (Button) findViewById(R.id.submit);
		answerGroup = (RadioGroup) findViewById(R.id.multichoicegroup);
		radioSexGroup = (RadioGroup) findViewById(R.id.multichoicegroup);
		
		myText = new String("A%7C");
		

		
		radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	         public void onCheckedChanged(RadioGroup rg, int checkedId) {
	              for(int i=0; i<rg.getChildCount(); i++) {
	                   RadioButton btn = (RadioButton) rg.getChildAt(i);
	                   if(btn.getId() == checkedId) {
	                	   String text = "Non Selected|";
	                	   switch(i)
	                	   {
	                	   case 0:
	                		   text = "A%7C";
	                		   break;
	                	   case 1:
	                		   text = "B%7C";
	                		   break;
	                	   case 2:
	                		   text = "C%7C";
	                		   break;   
	                	   case 3:
	                		   text = "D%7C";
	                		   break;
	                	   case 4:
	                		   text = "E%7C";
	                		   break;
	                	   default:
	                			   break;
	                	   }
	                	   
	                        
	                        // do something with text
	                        setText(text);
	                        return;
	                   }
	              }
	         }
	    });
		
		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				int selectedId = radioSexGroup.getCheckedRadioButtonId();
				new HttpGetTask().execute(myText);
				
				
				
			} 
		});
	}

	private Object OnClickListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mult_choice, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_mult_choice,
					container, false);
			return rootView;
		}
	}
	
	private class HttpGetTask extends AsyncTask<String, Void, String> {

		private static final String TAG = "HttpPostTask";

		// Get your own user name at http://www.geonames.org/login
		private static final String USER_NAME = "aporter";

		private static final String URL = "http://143.89.226.38/getMobileMsg.aspx?answer=";

		AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

		@Override
		protected String doInBackground(String... params) {

			String newURL =new String(URL+params[0]);
			HttpPost request = new HttpPost(newURL);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();

			try {

				return mClient.execute(request, responseHandler);

			} catch (ClientProtocolException exception) {
				exception.printStackTrace();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			if (null != mClient)
				mClient.close();
		}


	}

}
