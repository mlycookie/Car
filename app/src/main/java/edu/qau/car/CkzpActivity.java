package edu.qau.car;

import java.util.ArrayList;

import edu.qau.car.PzActivity.Holder;

import ru.truba.touchgallery.GalleryWidget.FilePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CkzpActivity extends Activity {

	private GalleryViewPager mViewPager;
	private ArrayList<String> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ckzp);
		items = new ArrayList<String>();

		Bundle bundle = getIntent().getExtras();
		for (Holder holder : PzActivity.xm) {
			if (bundle.containsKey(holder.name)) {
				items.add(bundle.getString(holder.name));
			}
		}

		UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);

		mViewPager = (GalleryViewPager) findViewById(R.id.viewer);
		// mViewPager.setOffscreenPageLimit(3);
		mViewPager.setAdapter(pagerAdapter);
	}

}
