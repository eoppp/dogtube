package jp.rainbowapps.dogtube.io;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 画像のキャッシュを行わないクラスです。
 */
public class NoImageCache implements ImageCache {

	/**
	 * 何を返しません。
	 */
	@Override
	public Bitmap getBitmap(String url) {
		return null;
	}

	/**
	 * 何も処理しません。
	 */
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// 処理なし
	}

}
