package edu.qau.car;

import java.io.File;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class CkActivity extends Activity {

	private ImageView ck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ck);

		ck = (ImageView) findViewById(R.id.ck);

		String url = getIntent().getStringExtra("tp");

		Picasso.with(this).load(url).placeholder(R.drawable.wsc)
				.error(R.drawable.no_photo).into(ck);

	}

}
