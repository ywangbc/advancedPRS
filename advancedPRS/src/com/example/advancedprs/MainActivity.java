/**
 * 
 */
package com.example.advancedprs;

/**
 * @author RyanWANG
 *
 */
import com.example.advancedprs.R;
import java.util.ArrayList;
import javax.crypto.KeyGenerator;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	EditText un, pw;
	TextView error;
	Button ok;
	Boolean validPassword;
	private String resp;
	private String errorMsg;
	


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		un = (EditText) findViewById(R.id.et_un);
		pw = (EditText) findViewById(R.id.et_pw);
		ok = (Button) findViewById(R.id.btn_login);
		error = (TextView) findViewById(R.id.tv_error);

		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/**
				 * According with the new StrictGuard policy, running long tasks
				 * on the Main UI thread is not possible So creating new thread
				 * to create and execute http operations
				 */
				new Thread(new Runnable() {

					@Override
					public void run() {
						ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
						postParameters.add(new BasicNameValuePair("username",
								un.getText().toString()));
						postParameters.add(new BasicNameValuePair("password",
								pw.getText().toString()));
						
						//validPassword = checkValid(postParameters);
						
					    /*
						if(validPassword)
						{
							resp = "Valid Password!";
						}
						*/
					}

				}).start();
				try {
					/** wait a second to get response from server */
					Thread.sleep(1000);
					/**
					 * Inside the new thread we cannot update the main thread So
					 * updating the main thread outside the new thread
					 */

					error.setText(resp);

					if (null != errorMsg && !errorMsg.isEmpty()) {
						error.setText(errorMsg);
					}
				} catch (Exception e) {
					error.setText(e.getMessage());
				}
				
				Intent viewqIntent = new Intent(MainActivity.this, ViewQuestion.class);
				//myIntent.putExtra("key", value); //Optional parameters
				startActivity(viewqIntent);
				
			}
		});
	}
	
	
	Boolean checkValid(ArrayList<NameValuePair> postParameters)
	{
		return validPassword;
		
	}
	
	
	
	
	
	
	
	
}