package jp.rainbowapps.dogtube.adapter;

import jp.rainbowapps.dogtube.R;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * カテゴリ選択ページのアダプタです。
 */
public class DogCategoryPagerAdapter extends PagerAdapter {

	/**
	 * 犬のカテゴリ
	 */
	enum DogCategory {
		/** チワワ */
		CHIHUAHUA(R.string.chihuahua, R.drawable.chihuahua, R.string.description_chihuahua, R.string.query_chihuahua),
		/** ポメラニアン */
		POMERANIAN(R.string.pomeranian, R.drawable.pomeranian, R.string.description_pomeranian, R.string.query_pomeranian),
		/** 柴犬 */
		SHIBA(R.string.shiba, R.drawable.shiba, R.string.description_shiba, R.string.query_shiba),
		/** キャバリア */
		CAVALIER(R.string.cavalier, R.drawable.cavalia, R.string.description_cavalier, R.string.query_cavalier);

		/** タイトル */
		final int titleResId;
		/** 画像のリソースID */
		final int imageResId;
		/** 説明 */
		final int description;
		/** クエリ */
		final int query;

		/**
		 * コンストラクタ
		 * 
		 * @param titleResId
		 *            タイトルのリソースID
		 * @param imageResId
		 *            画像のリソースID
		 * @param description
		 *            説明のリソースID
		 * @param query
		 *            クエリ
		 */
		private DogCategory(final int titleResId, final int imageResId, final int description, final int query) {
			this.titleResId = titleResId;
			this.imageResId = imageResId;
			this.description = description;
			this.query = query;
		}
	}

	/** {@link Context} */
	private Context mContext;

	/** {@link LayoutInflater} */
	private LayoutInflater mInflater;

	/**
	 * コンストラクタ
	 * 
	 * @param context
	 *            {@link Context}
	 */
	public DogCategoryPagerAdapter(final Context context) {
		mContext = context;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * ページのタイトルを取得します。
	 */
	@Override
	public CharSequence getPageTitle(final int position) {
		// enumからタイトルを取得
		return mContext.getString(DogCategory.values()[position].titleResId);
	}

	/**
	 * ページが作られる度に呼び出されます。
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// Viewの生成
		final View baseView = mInflater.inflate(R.layout.widget_category, null);
		// 犬の画像
		final ImageView categoryImage = (ImageView) baseView.findViewById(R.id.image);
		// 犬の説明
		final TextView categoryDescription = (TextView) baseView.findViewById(R.id.description);

		// 画像や文字列を登録
		final DogCategory currentCategory = DogCategory.values()[position];
		categoryImage.setImageResource(currentCategory.imageResId);
		categoryDescription.setText(currentCategory.description);

		container.addView(baseView);

		return baseView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroyItem(final ViewGroup container, final int position, final Object object) {
		container.removeView((View) object);
	}

	/**
	 * ページ数を取得します。
	 */
	@Override
	public int getCount() {
		// enumから犬の種類数を取得
		return DogCategory.values().length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isViewFromObject(final View view, final Object object) {
		return view == (View) object;
	}

	/**
	 * 検索クエリを取得します。
	 * 
	 * @param position
	 *            位置
	 * @return 検索クエリ
	 */
	public String getQuery(final int position) {
		return mContext.getString(DogCategory.values()[position].query);
	}
}
