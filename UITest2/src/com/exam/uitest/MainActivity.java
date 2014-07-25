package com.exam.uitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private SimpleMap fl;
	private float size;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		size = 1;
		fl = (SimpleMap) findViewById(R.id.content);

		RailwayStation rs = new RailwayStation(this, "A", 100, 100);
		RailwayStation rs2 = new RailwayStation(this, "B", 100, 800);
		RailwayStation rs3 = new RailwayStation(this, "C", 600, 800);
		RailwayStation rs4 = new RailwayStation(this, "D", 600, 100);
		RailwayLine rl = new RailwayLine(this, "A-B", 100, 100, 100, 800);
		RailwayLine rl1 = new RailwayLine(this, "A-C", 100, 100, 600, 800);
		RailwayLine rl2 = new RailwayLine(this, "A-D", 100, 100, 600, 100);
		RailwayLine rl3 = new RailwayLine(this, "B-C", 100, 800, 600, 800);
		RailwayLine rl4 = new RailwayLine(this, "B-D", 100, 800, 600, 100);
		RailwayLine rl5 = new RailwayLine(this, "C-D", 600, 800, 600, 100);

		fl.setStations(rs).setStations(rs2).setStations(rs3).setStations(rs4)
				.setLines(rl).setLines(rl1).setLines(rl2).setLines(rl3)
				.setLines(rl4).setLines(rl5);
		Button bigger = (Button) findViewById(R.id.text);
		bigger.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				fl.reset();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
