/**
 * 
 */
package com.example.advancedprs;

/**
 * @author RyanWANG
 *
 */
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint("NewApi")
public class MainActivity extends Activity {
	EditText un, pw;
	
	Button ok;
	Boolean validPassword;
	private String resp;
	private String errorMsg;
	public final int TOASTINTERVAL = 3000;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		un = (EditText) findViewById(R.id.et_un);
		pw = (EditText) findViewById(R.id.et_pw);
		ok = (Button) findViewById(R.id.btn_login);


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
						/*
						validPassword = checkValid(postParameters);
						
					    
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
					
					
					

					if (null != errorMsg && !errorMsg.isEmpty()) {
						
					}
				} catch (Exception e) {
					
				}
				
				
				Intent viewqIntent = new Intent(MainActivity.this, ViewQuestion.class);
				//myIntent.putExtra("key", value); 
				startActivity(viewqIntent);
				
			}
		});
	}
	
	
	Boolean checkValid(ArrayList<NameValuePair> postParameters)
	{
		if (mIsBound) {
			// Send Message to the Logging Service
			logMessageToService();
		}
		
		else
		{
			Toast.makeText(this, "Haven't been bound to any service!", TOASTINTERVAL).show();
		}
		
		return validPassword;
		
	}
	
	private final static String MESSAGE_KEY = "course.examples.Services.Logging.MESSAGE";
	private final static int LOG_OP = 1;
	private final static String TAG = "LoggingServiceClient";
	
	private final static Intent mLoggingServiceIntent = new Intent(
			"com.example.advancedprsloginservice.LoginService");

	private Messenger mMessengerToLoginService;
	private boolean mIsBound;

	// Object implementing Service Connection callbacks
	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder service) {

			// Messenger object connected to the LoggingService
			mMessengerToLoginService = new Messenger(service);

			mIsBound = true;

		}

		public void onServiceDisconnected(ComponentName className) {

			mMessengerToLoginService = null;

			mIsBound = false;

		}
	};
	
	// Create a Message and sent it to the LoggingService
	// via the mMessenger Object

	private void logMessageToService() {

		// Create Message 
		Message msg = Message.obtain(null, LOG_OP);
		Bundle bundle = new Bundle();
		bundle.putString(MESSAGE_KEY, "Log This Message");
		msg.setData(bundle);
		/*
		try {
			
			// Send Message to LoggingService using Messenger
			//mMessengerToLoggingService.send(msg);

		} 
		
		catch (RemoteException e) {
			Log.e(TAG, e.toString());
		}
		*/
	}
	
	
	
	
	
	
	
	
}