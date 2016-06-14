package com.xyz.sports;

import com.xyz.sports.R.attr;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;

public class PopUpbotom extends Activity {

	Button slideHandleButton, bt_que, bt_logout;
	SlidingDrawer slidingDrawer;
	TextView txt_click_icon;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popupsliderbotom);
		loaduielements();
		Loadlisners();
		loadpanal();
		
	}

	private void loaduielements() {
		// TODO Auto-generated method stub
		txt_click_icon = (TextView) findViewById(R.id.txt_click_icon);
		bt_que = (Button) findViewById(R.id.bt_que);
		bt_logout = (Button) findViewById(R.id.bt_logout);
	}

	private void Loadlisners() {
		// TODO Auto-generated method stub
		bt_que.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent quesition = new Intent(getApplicationContext(),
						Questions.class);
				startActivity(quesition);
			}
		});
		bt_logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent logout = new Intent(PopUpbotom.this,
						MainActivity.class);
				startActivity(logout);
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void loadpanal() {
		// TODO Auto-generated method stub
		slideHandleButton = (Button) findViewById(R.id.slideHandleButton);
		slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);

		slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {

			@Override
			public void onDrawerOpened() {
				slideHandleButton.setBackgroundResource(R.drawable.arrow_down);
				txt_click_icon.setVisibility(View.GONE);
			}
		});

		slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				slideHandleButton.setBackgroundResource(R.drawable.arrow_up);
				txt_click_icon.setVisibility(View.VISIBLE);
			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	//	PopUpbotom.this.finish();
	}
}