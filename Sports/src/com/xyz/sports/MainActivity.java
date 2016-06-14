package com.xyz.sports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bt_login;
	EditText et_ussername, et_password;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Loaduielements();
		addLisners();

	}

	private void Loaduielements() {
		// TODO Auto-generated method stub
		bt_login = (Button) findViewById(R.id.bt_login);
		et_ussername = (EditText) findViewById(R.id.et_ussername);
		et_password = (EditText) findViewById(R.id.et_password);
			
	}

	private void addLisners() {
		// TODO Auto-generated method stub
		bt_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ((et_ussername.getText().toString().trim().length()>0)&& (et_password.getText().toString().trim().length()>0)) {
					Intent login = new Intent(getApplicationContext(),PopUpbotom.class);
					startActivity(login);
				} else {
					Toast.makeText(getApplicationContext(),"Please enter required information", 3000).show();
				}

			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	//	super.onBackPressed();
	//	MainActivity.this.finish();
	}
}
