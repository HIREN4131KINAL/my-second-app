package com.xyz.sports;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class Questions extends Activity {
	Button bt_locker_room,slideHandleButton;
	SlidingDrawer slidingDrawer;
	// public final static String ITEM_TITLE = "title";
	public final static String ITEM_CAPTION = "caption";

	// SectionHeaders
	private final static String[] SectionHeaders = new String[] {
			"? OF THE WEEK", "CATEGORY  Y", "CATEGORY  Z", "CATEGORY  A",
			"CATEGORY  B" };

	// Section Contents
	private final static String[] SectionContents = new String[] {
			"Question                   27-08-2014                  1",
			"Question                   27-08-2014                  1",
			"Question                   27-08-2014                  1",
			"                                     More           "};

	// MENU - ListView
	@SuppressWarnings("unused")
	private ListView addJournalEntryItem;

	// Adapter for ListView Contents
	private SeparatedListAdapter adapter;

	// ListView Contents
	private ListView journalListView;

	public Map<String, ?> createItem(String title, String caption) {
		Map<String, String> item = new HashMap<String, String>();
		// item.put(ITEM_TITLE, title);
		item.put(ITEM_CAPTION, caption);
		return item;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.question);
		LoaduiELements();
		LoadLIsNers();
		loadpanal();
		// Interactive Tools
		// final ArrayAdapter<String> journalEntryAdapter = new
		// ArrayAdapter<String>(this, R.layout.add_journalentry_menuitem, new
		// String[]{"Add Journal Entry"});

		// AddJournalEntryItem
		// addJournalEntryItem = (ListView)
		// this.findViewById(R.id.add_journalentry_menuitem);
		// addJournalEntryItem.setAdapter(journalEntryAdapter);
		// addJournalEntryItem.setOnItemClickListener(new OnItemClickListener()
		// {
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view, int
		// position, long duration)
		// {
		// String item = journalEntryAdapter.getItem(position);
		// Toast.makeText(getApplicationContext(), item,
		// Toast.LENGTH_SHORT).show();
		// }
		// });
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

	private void LoaduiELements() {
		// TODO Auto-generated method stub
		bt_locker_room=(Button)findViewById(R.id.bt_locker_room);
		
		// Create the ListView Adapter
		adapter = new SeparatedListAdapter(this);
		ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this,
				R.layout.list_item, SectionContents);

		// Add Sections
		for (int i = 0; i < SectionHeaders.length; i++) {
			adapter.addSection(SectionHeaders[i], listadapter);
		}

		// Get a reference to the ListView holder
		journalListView = (ListView) this.findViewById(R.id.list_journal);

		// Set the adapter on the ListView holder
		journalListView.setAdapter(adapter);
	}

	private void LoadLIsNers() {
		// TODO Auto-generated method stub
		// Listen for Click events
		journalListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long duration) {
			//	String item = (String) adapter.getItem(position);
//				Toast.makeText(getApplicationContext(), item,
//						Toast.LENGTH_SHORT).show();
				Intent video = new Intent(Questions.this,QuestionsDetails.class);
				startActivity(video);
			}
		});
		
		bt_locker_room.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent locarroom = new Intent(Questions.this,PopUpbotom.class);
				startActivity(locarroom);
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	//	super.onBackPressed();
		//Questions.this.finish();
	}
}
