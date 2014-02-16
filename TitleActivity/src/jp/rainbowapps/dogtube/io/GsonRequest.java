package jp.rainbowapps.dogtube.io;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Gsonのリクエストを扱うクラスです。
 * 
 * @param <T>
 * @see https://gist.github.com/ficusk/5474673
 */
public class GsonRequest<T> extends Request<T> {
	/** {@link Gson} */
	private final Gson mGson;
	/** {@link Class} */
	private final Class<T> mClazz;
	/** リクエストヘッダの{@link Map} */
	private final Map<String, String> mHeaders;
	/** {@link Listener} */
	private final Listener<T> mListener;

	/**
	 * GETリクエストでリクエストを生成します。
	 * 
	 * @param url
	 *            リクエストするURL
	 * @param clazz
	 *            Gsonが扱うクラス
	 * @param headers
	 *            リクエストヘッダの{@link Map}
	 */
	public GsonRequest(String url, Class<T> clazz, Map<String, String> headers, Listener<T> listener, ErrorListener errorListener) {
		super(Method.GET, url, errorListener);
		mGson = new Gson();
		mClazz = clazz;
		mHeaders = headers;
		mListener = listener;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return mHeaders != null ? mHeaders : super.getHeaders();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return Response.success(mGson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}
}