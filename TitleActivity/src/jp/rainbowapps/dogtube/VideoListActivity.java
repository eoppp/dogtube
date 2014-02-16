package jp.rainbowapps.dogtube;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import jp.rainbowapps.dogtube.adapter.VideoListAdapter;
import jp.rainbowapps.dogtube.io.GsonRequest;
import jp.rainbowapps.dogtube.io.VolleyRequestHolder;
import jp.rainbowapps.dogtube.model.SearchResult;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

/**
 * 動画のリスト画面です。
 */
public class VideoListActivity extends Activity implements
		Listener<SearchResult>, ErrorListener {

	/** 検索URLのプレフィックス */
	private static final String URL_PREFIX = "http://gdata.youtube.com/feeds/api/videos?alt=json&q=";

	/** 動画の検索を行うクエリ */
	public static final String KEY_QUERY = "query";

	/** 読み込み中のView */
	private View mLoadingView;

	/** 動画リスト */
	private ListView mMovieListView;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videolist);
		initViews();
		init();
	}

	/**
	 * Viewの初期化を行います。
	 */
	private void initViews() {
		// 読み込み中
		mLoadingView = findViewById(R.id.loading_layout);
		// リスト表示
		mMovieListView = (ListView) findViewById(R.id.movie_list);
	}

	/**
	 * 各種処理の初期化を行います。
	 */
	private void init() {
		try {
			final String query = getIntent().getStringExtra(KEY_QUERY);
			String url = URL_PREFIX + URLEncoder.encode(query, "UTF-8");
			startSearch(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 検索を開始します。
	 * 
	 * @param url
	 *            URL
	 */
	private void startSearch(final String url) {
		final RequestQueue requestQueue = VolleyRequestHolder
				.newRequestQueue(this);
		final GsonRequest<SearchResult> request = new GsonRequest<SearchResult>(
				url, SearchResult.class, null, this, this);
		// 通信のキャッシュをしない
		request.setShouldCache(false);
		requestQueue.add(request);
	}

	/**
	 * 通信成功時の処理を行います。
	 */
	@Override
	public void onResponse(final SearchResult response) {
		// リストの内容表示
		mMovieListView.setAdapter(new VideoListAdapter(this, response));
		mLoadingView.setVisibility(View.GONE);
	}

	/**
	 * 通信失敗時の処理を行います。
	 */
	@Override
	public void onErrorResponse(final VolleyError error) {
		// エラー時にメッセージ表示
		Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
		mLoadingView.setVisibility(View.GONE);
	}
}
