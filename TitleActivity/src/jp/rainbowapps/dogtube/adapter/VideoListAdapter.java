package jp.rainbowapps.dogtube.adapter;

import jp.rainbowapps.dogtube.R;
import jp.rainbowapps.dogtube.io.NoImageCache;
import jp.rainbowapps.dogtube.io.VolleyRequestHolder;
import jp.rainbowapps.dogtube.model.SearchResult;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class VideoListAdapter extends ArrayAdapter<SearchResult> {

	/** {@link Context } */
	private Context mContext;

	/** {@link SearchResult } */
	private SearchResult mSearchResult;

	/** {@link ImageLoader } */
	private ImageLoader mImageLoader;

	/**
	 * @param context
	 *            {@link Context}
	 * @param searchResult
	 *            {@link SearchResult}
	 */
	public VideoListAdapter(final Context context,
			final SearchResult searchResult) {
		super(context, 0);
		mContext = context;
		mSearchResult = searchResult;
		mImageLoader = new ImageLoader(
				VolleyRequestHolder.newRequestQueue(mContext),
				new NoImageCache());
	}

	/**
	 * Viewを保持しておくクラスです。
	 */
	static class ViewHolder {
		/** サムネイル */
		NetworkImageView mThumbnailView;
		/** タイトル */
		TextView mTitleView;
		/** 再生時間 */
		TextView mVideoLength;
	}

	/**
	 * データが何件存在するか指定する。
	 */
	@Override
	public int getCount() {
		// 結果がない場合は0件
		if (mSearchResult.feed.entry == null) {
			return 0;
		}
		return mSearchResult.feed.entry.length;
	}

	/**
	 * リストの1行が表示される度に呼び出されるメソッドです。
	 */
	@Override
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {

		View targetView = convertView;
		ViewHolder holder;

		// Viewが構築されていない場合（初回時）
		if (targetView == null) {
			final LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			targetView = inflater.inflate(R.layout.widget_videolist, null);

			// Viewを一旦保持
			holder = new ViewHolder();
			holder.mThumbnailView = (NetworkImageView) targetView
					.findViewById(R.id.thumbnail);
			holder.mTitleView = (TextView) targetView.findViewById(R.id.title);
			holder.mVideoLength = (TextView) targetView
					.findViewById(R.id.video_length);

			// 読み込み中画像の設定
			holder.mThumbnailView.setDefaultImageResId(R.drawable.ic_launcher);

			targetView.setTag(holder);
		} else {
			// 保持しておいたものをf苦言
			holder = (ViewHolder) targetView.getTag();

		}

		// 表示に必要な情報をGSONで取り出す
		final String title = mSearchResult.feed.entry[position].title.$t;
		final String imageUrl = mSearchResult.feed.entry[position].media$group.media$thumbnail[0].url;
		final int videoLength = Integer
				.parseInt(mSearchResult.feed.entry[position].media$group.yt$duration.seconds);

		// サムネイルの表示
		holder.mThumbnailView.setImageUrl(imageUrl, mImageLoader);

		// タイトルの設定
		holder.mTitleView.setText(title);

		// 動画の再生時間の設定
		holder.mVideoLength.setText(mContext.getString(R.string.video_length,
				videoLength / 60, videoLength % 60));

		return targetView;
	}
}
