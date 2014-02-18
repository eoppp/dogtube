package jp.rainbowapps.dogtube;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity implements
		OnInitializedListener {

	/** APIキー */
	private static final String DEVELOPER_KEY = "AIzaSyD-uZqJaAUZLYMyyVhsjSsUoSocQ3h1Neo";

	/** 動画IDのキー */
	public static final String KEY_VIDEO_ID = "videoId";

	/** タイトルのキー */
	public static final String KEY_TITLE = "title";

	/** {@link YouTubePlayerView } */
	private YouTubePlayerView mYouTubePlayerView;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		init();
	}

	/**
	 * 初期化を行います。
	 */
	private void init() {
		// YouTubeの初期化
		mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		mYouTubePlayerView.initialize(DEVELOPER_KEY, this);

		// タイトル
		final TextView titleView = (TextView) findViewById(R.id.title);
		titleView.setText(getIntent().getStringExtra(KEY_TITLE));

	}

	/**
	 * 成功した時に実行する処理です。
	 */
	@Override
	public void onInitializationSuccess(final Provider provider,
			final YouTubePlayer player, final boolean wasRestored) {
		if (!wasRestored) {
			// 動画読み込み
			player.cueVideo(getIntent().getStringExtra(KEY_VIDEO_ID));
		}
	}

	@Override
	public void onInitializationFailure(final Provider provider,
			final YouTubeInitializationResult errorReason) {
		// エラーの処理を行う。この処理をどういう風にするかはあなた次第
		Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
	}
}
