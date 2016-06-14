package com.xyz.sports;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class QuestionsDetails extends Activity {
	ListView lv;
	Context context;
	Button bt_locker_room, slideHandleButton, btn_answer;
	@SuppressWarnings("deprecation")
	SlidingDrawer slidingDrawer;
	@SuppressWarnings("rawtypes")
	ArrayList prgmName;
	public static String[] prgmNameList = { "Coach  Tony", "Coach  Tony",
			"Coach  Tony", "Coach  Tony", "Coach  Tony" };

	public static int[] prgmImages = { R.drawable.video, R.drawable.video,
			R.drawable.video, R.drawable.video, R.drawable.video,
			R.drawable.video };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionsdetails);
		context = this;
		LoaduiELemenNTS();
		LoadLISNers();
		loadpanal();
	}

	private void LoaduiELemenNTS() {
		// TODO Auto-generated method stub
		bt_locker_room = (Button) findViewById(R.id.bt_locker_room);
		btn_answer = (Button) findViewById(R.id.btn_answer);
		lv = (ListView) findViewById(R.id.listView);
		lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
	}

	private void LoadLISNers() {
		// TODO Auto-generated method stub
		bt_locker_room.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent locarroom = new Intent(QuestionsDetails.this,
						PopUpbotom.class);
				startActivity(locarroom);
			}
		});

		btn_answer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent answer = new Intent(QuestionsDetails.this,
						VideoCaptureActivity.class);
				startActivity(answer);
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent video = new Intent(context, Que_vidEo.class);
				startActivity(video);
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void loadpanal() {
		// TODO Auto-generated method stub
		slideHandleButton = (Button) findViewById(R.id.slideHandleButton);
		slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);

		slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {

			@Override
			public void onDrawerOpened() {
				slideHandleButton.setBackgroundResource(R.drawable.arrow_down);
			}
		});

		slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				slideHandleButton.setBackgroundResource(R.drawable.arrow_up);
			}
		});
	}
}
