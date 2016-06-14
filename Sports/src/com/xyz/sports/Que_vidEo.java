package com.xyz.sports;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.VideoView;

public class Que_vidEo extends Activity {
	boolean running;
	ProgressWheel pw_two;
	// ProgressWheel pw_five;
	int progress = 0, cnt_dec = 0;
	VideoView videoView;

//	TextView txt_countdown;
	String videoUrl;
	Button bt_locker_room, slideHandleButton;
	@SuppressWarnings("deprecation")
	SlidingDrawer slidingDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.que_video);

		loadELEments();
		AddLisners();
		loadpanal();
		// for (int i = cnt_dec; i > 0; i--) {
		// txt_countdown.setText("" + i);
		// Log.e("dec seek "+i, "chk chk ");
		// }

		new YourAsyncTask().execute();

	}

	private void loadELEments() {
		// TODO Auto-generated method stub
	//	txt_countdown = (TextView) findViewById(R.id.txt_countdown);
		pw_two = (ProgressWheel) findViewById(R.id.progressBarTwo);
		bt_locker_room = (Button) findViewById(R.id.bt_locker_room);
		videoView = (VideoView) findViewById(R.id.videoView);

	}

	private void AddLisners() {
		// TODO Auto-generated method stub
		bt_locker_room.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent locarroom = new Intent(Que_vidEo.this, PopUpbotom.class);
				startActivity(locarroom);
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
				pw_two.setVisibility(View.INVISIBLE);
			}
		});

		slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				slideHandleButton.setBackgroundResource(R.drawable.arrow_up);
				pw_two.setVisibility(View.VISIBLE);
			}
		});
	}

	private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(Que_vidEo.this, "",
					"Loading Video wait...", true);
			onPause();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				String url = "http://daily3gp.com/vids/747.3gp";
				videoUrl = getUrlVideoRTSP(url);

				Log.e("Video url for playing=========>>>>>", videoUrl);
			} catch (Exception e) {
				Log.e("Login Soap Calling in Exception", e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();

			if (VideoPlaybackActivity.startvideo == 0) {
				final Runnable r = new Runnable() {
					public void run() {
						running = true;
						while (progress < 361) {
							pw_two.incrementProgress();
							progress++;
							cnt_dec++;
							try {

								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						running = false;
					}
				};

				if (!running) {
					progress = 0;
					pw_two.resetCount();
					Thread s = new Thread(r);
					s.start();

				}
			}
			videoView.setVideoURI(Uri.parse(videoUrl));
			// MediaController mc = new MediaController(Que_vidEo.this);
			// videoView.setMediaController(mc);
			videoView.requestFocus();
			videoView.start();
		//	txt_countdown.setText("" + cnt_dec);
			// mc.show();
		}

	}

	public static String getUrlVideoRTSP(String urlYoutube) {
		try {
			String gdy = "http://gdata.youtube.com/feeds/api/videos/";
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			String id = extractYoutubeId(urlYoutube);
			URL url = new URL(gdy + id);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			Document doc = documentBuilder.parse(connection.getInputStream());
			Element el = doc.getDocumentElement();
			NodeList list = el.getElementsByTagName("media:content");// /media:content
			String cursor = urlYoutube;
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node != null) {
					NamedNodeMap nodeMap = node.getAttributes();
					HashMap<String, String> maps = new HashMap<String, String>();
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Attr att = (Attr) nodeMap.item(j);
						maps.put(att.getName(), att.getValue());
					}
					if (maps.containsKey("yt:format")) {
						String f = maps.get("yt:format");
						if (maps.containsKey("url")) {
							cursor = maps.get("url");
						}
						if (f.equals("1"))
							return cursor;
					}
				}
			}
			return cursor;
		} catch (Exception ex) {
			Log.e("Get Url Video RTSP Exception======>>", ex.toString());
		}
		return urlYoutube;

	}

	protected static String extractYoutubeId(String url)
			throws MalformedURLException {
		String id = null;
		try {
			String query = new URL(url).getQuery();
			if (query != null) {
				String[] param = query.split("&");
				for (String row : param) {
					String[] param1 = row.split("=");
					if (param1[0].equals("v")) {
						id = param1[1];
					}
				}
			} else {
				if (url.contains("embed")) {
					id = url.substring(url.lastIndexOf("/") + 1);
				}
			}
		} catch (Exception ex) {
			Log.e("Exception", ex.toString());
		}
		return id;
	}

	public void onPause() {
		super.onPause();
		progress = 361;
		pw_two.resetCount();
		videoView.stopPlayback();
	}
}
