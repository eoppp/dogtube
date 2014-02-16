package jp.rainbowapps.dogtube;

import jp.rainbowapps.dogtube.adapter.DogCategoryPagerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * タイトル画面です。
 */
public class TitleActivity extends FragmentActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		initViews();
	}

	/**
	 * Viewの初期化を行います。
	 */
	private void initViews() {
		// フリックするView(ViewPager)の生成
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		final PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
		final DogCategoryPagerAdapter pagerAdapter = new DogCategoryPagerAdapter(this);

		viewPager.setAdapter(pagerAdapter);
		pagerTabStrip.setDrawFullUnderline(true);
		pagerTabStrip.setTabIndicatorColor(Color.LTGRAY);

		// 動画検索ボタン
		findViewById(R.id.search_movies).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String query = pagerAdapter.getQuery(viewPager.getCurrentItem());
				final Intent intent = new Intent(TitleActivity.this, VideoListActivity.class);
				intent.putExtra(VideoListActivity.KEY_QUERY, query);
				startActivity(intent);
			}
		});
	}

}
