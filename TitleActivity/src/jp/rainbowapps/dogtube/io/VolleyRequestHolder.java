package jp.rainbowapps.dogtube.io;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * RequestQueueを保持しておくクラスです。<br/>
 * このRequestQueueのインスタンスを使いまわすことで、通信を行います。
 */
public final class VolleyRequestHolder {

	/** {@link RequestQueue} */
	private static RequestQueue sRequestQueue;

	/**
	 * 非公開のコンストラクタ
	 */
	private VolleyRequestHolder() {
		// 処理なし
	}

	/**
	 * {@link RequestQueue}を取得します。
	 * 
	 * @param context
	 *            {@link Context}
	 * @return {@link RequestQueue}
	 */
	public static RequestQueue newRequestQueue(final Context context) {
		if (sRequestQueue == null) {
			sRequestQueue = Volley.newRequestQueue(context);
		}
		return sRequestQueue;
	}
}
