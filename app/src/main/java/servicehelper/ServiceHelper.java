package servicehelper;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NoHttpResponseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;

import common.Utils;


public class ServiceHelper {

	// private static String TAG = "QClub.ServiceHelper";
	@SuppressWarnings("unused")
	private static String TAG = "";
	public static final String COMMON_ERROR = "Could not connect to server, please try again later";
	public static final String REGISTER_USER = "register_user";
	public static final String LOGIN = "login";
	public static final String LOGIN_WITH_SOCIAL = "login_with_social";
	public static final String FORGET_PASSWORD = "forgot_password";
	public static final String GET_ALL_VENUES = "get_all_venues";
	public static final String GET_ALL_TRAINING = "get_all_trainings";
	public static final String GET_ALL_CITY = "get_citylist";
	public static final String GET_ALL_COUNTRY = "get_countrylist";
	public static final String GET_ALL_SPORTS = "get_all_sports";
	public static final String GET_ALL_BASIC_VENUE = "get_venuelist";
	public static final String GET_MATCHES = "get_all_matches";
	public static final String ADD_MATCH = "add_match";
	public static final String EDIT_MATCH = "edit_match";
	public static final String GET_ALL_EVENT = "new_get_events";
	public static final String ADD_EVENT = "add_event";
	public static final String ADD_EVENT_PROMOTIONAL_IMAGE = "add_promotional_images";
	public static final String DELETE_EVENT_PROMOTIONAL_IMAGE = "delete_promotional_images";
	public static final String EDIT_EVENT = "edit_event";
	public static final String DELETE_EVENT = "delete_event";
	public static final String GET_PLAYERS_GAME = "get_player_games";
	public static final String GIVE_LIKE = "like_content";
	public static final String GIVE_REVIEW = "give_review";
	public static final String GET_FRIENDLIST = "get_friendlist";
	public static final String FRIEND_UPDATE = "friend_update";
	public static final String GET_USER_DETAIL = "get_userinfo";
	public static final String UPDATE_PROFILE = "update_profile";
	public static final String EVENT_ATTENDANCE = "new_event_attendance";
	public static final String PLAYER_ATTENDANCE = "player_attendance";
	
	public static final String GET_NOTIFICATIONS = "get_notifications";
	
	public static final String CHAT_LIST = "get_chatlist";
	public static final String CHAT_UPDATE = "chat_update";
	
	public static final String DEVICE_REGISTER = "device_register";
	
	public static final String SEND_MESSAGE = "send_message";
	public static final String GET_MESSAGE = "get_messages";
	
	public static final String GET_ARENADETAIL = "get_arenadetail";
	public static final String GET_BATCHDETAIL = "get_batchdetail";
	
	public static final String READ_NOTIFICATION = "read_notification";
	public static final String SEEN_NOTIFICATION = "seen_notification";
	
	public static final String DELETE_PLAYER = "delete_player";
	public static final String DELETE_MATCH = "delete_match";
	
	public static final String GET_COUNTS = "get_counts";
	
	public static final String USER_LOGOUT = "logout";

	public static final String GET_ATTENDLIST = "new_get_attendlist";
	public static final String CLEAR_NOTIFICATION = "delete_notif_history";
	public static final String CLEAR_CHAT = "delete_chat";
	public static final String CUSTUM_VENUE = "add_custom_venue";
	public static final String CHANGE_PASSWORD = "change_password";

	public static final String SEND_OTP = "send_otp";

	public static final String RESEND_OTP = "resend_otp";

	public static final String DO_VERIFY = "do_verify";

	public enum RequestMethod {
		GET, POST
	}

	public interface ServiceHelperDelegate {
		/**
		 * Calls when got the response from the API
		 * 
		 * @param res
		 *            Service Response Obejct
		 */
		public void CallFinish(ServiceResponse res);

		/**
		 * Service call fail with error message
		 * 
		 * @param ErrorMessage
		 *            Error Message
		 */
		public void CallFailure(String ErrorMessage);
	}

	// String m_methodName = null;
	private ServiceHelperDelegate m_delegate = null;

	public static final String URL = "http://www.sportify.co.in/sportsapi/api/";
//	public static final String URL = "http://scteches.com/sportsapp/sportsapi/api/";
	private ArrayList<String> m_params = new ArrayList<String>();
	@SuppressWarnings("unused")
	private HashMap<String, String> m_hashParams = new HashMap<String, String>();
	private static int REQUEST_TIMEOUT = 100000;
	public RequestMethod RequestMethodType = RequestMethod.GET;
	String m_methodName = null;
	int REQUEST_TAG = 0;

	public ServiceHelper(String method) {
		m_methodName = method;
	}

	/**
	 * When using more than one call from one class and same delegate is used.
	 * So the call response is identify by TAG
	 */
	public ServiceHelper(String method, int tag) {
		m_methodName = method;
		REQUEST_TAG = tag;
		RequestMethodType = RequestMethod.GET;
	}

	public ServiceHelper(String method, RequestMethod requestMethod) {
		m_methodName = method;
		RequestMethodType = requestMethod;
	}

	public void setTAG(String tag) {
		TAG = tag;
	}

	// public void addParam(String key, Object value) {
	// m_hashParams.put(key, String.valueOf(value));
	// }

	public void addParam(String key, Object value) {
		m_params.add(key + "=" + value);
	}

	// public void addParam(String key, int value) {
	// m_params.add(key + "=" + String.valueOf(value));
	// }

	public void setParams(ArrayList<String> params) {
		m_params = new ArrayList<String>(params);
	}

	public String getFinalUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append(URL);
		sb.append(m_methodName.toString());
		// ArrayList<String> m_params = new ArrayList<String>();
		// for (String key : m_hashParams.keySet()) {
		// m_params.add(key + "=" + m_hashParams.get(key));
		// }
		sb.append("?api_key=ec3084ee040b56be341141c35bfecbc8d07b96ee");

		if (RequestMethodType == RequestMethod.GET) {
			if (m_params.size() > 0) {
				String queryString = Utils.Instance().join(m_params, "&");
				sb.append("&");
				sb.append(queryString);
			}
		}
		return sb.toString();
	}

	// private ServiceHelperDelegate m_delegate = null;

	public void call(ServiceHelperDelegate delegate) {
		m_delegate = delegate;
		// if (NetworkConnectivity.isConnected()) {
		CallServiceAsync calling = new CallServiceAsync(true);
		calling.execute();

	}

	private String call() {
		StringBuilder builder = new StringBuilder();

		HttpClient client = getNewHttpClient();
		HttpRequestBase request = null;
		if (RequestMethodType == RequestMethod.GET) {
			request = new HttpGet(getFinalUrl());
			request.setHeader("Accept", "*/*");
			request.setHeader("Content-Type", "text/plain; charset=utf-8");
		} else {
			request = new HttpPost(getFinalUrl());
			// request.setHeader("Accept", "application/json");
			request.setHeader("Content-Type",
					"application/x-www-form-urlencoded");

			if (m_params.size() > 0) {
				// JSONObject json = JsonCreator.getJsonObject(m_params);
				String queryString = Utils.Instance().join(m_params, "&");
				Utils.LogInfo("REGISTER---->>" + queryString);
				StringEntity se;
				try {
					se = new StringEntity(queryString);
					((HttpPost) request).setEntity(se);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			request.getParams().setParameter("http.socket.timeout",
					REQUEST_TIMEOUT);

			Utils.LogInfo("**URL : " + getFinalUrl());
			request.setHeader("Cache-Control", "no-cache");

			HttpResponse response = client.execute(request);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				builder.append(statusCode);
				Utils.LogInfo("Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			builder.append("{\"result\":{\"code\":1011,\"error\":\"Unknow errro occuerd, please try again later.\"}}");
		}
		return builder.toString();
	}

	public void callUrl(String url, ServiceHelperDelegate delegate) {
		m_delegate = delegate;
		CallServiceAsync calling = new CallServiceAsync(url);
		calling.execute();
	}

	@SuppressWarnings("unused")
	private DefaultHttpClient getHttpClient() {
		final HttpParams httpParams = new BasicHttpParams();

		// SchemeRegistry schemeRegistry = new SchemeRegistry();
		// schemeRegistry.register(new Scheme("http", SSLSocketFactory
		// .getSocketFactory(), 80));
		//
		// SingleClientConnManager mgr = new SingleClientConnManager(httpParams,
		// schemeRegistry);
		// HttpProtocolParams.setUseExpectContinue(httpParams, false);
		// HttpConnectionParams.setConnectionTimeout(httpParams,
		// REQUEST_TIMEOUT);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				REQUEST_TIMEOUT);
		DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
		httpclient.setHttpRequestRetryHandler(new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= 2) {
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					return true;
				} else if (exception instanceof ClientProtocolException) {
					return true;
				}
				return false;
			}
		});
		return httpclient;
	}

	public HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();


			HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, REQUEST_TIMEOUT);


			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	private String call(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = getNewHttpClient();
		HttpRequestBase request = null;

		request = new HttpPost(url);
		try {
			request.setHeader("Cache-Control", "no-cache");

			HttpResponse response = client.execute(request);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				builder.append(statusCode);
				Utils.LogInfo("Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	class CallServiceAsync extends AsyncTask<Void, Void, ServiceResponse> {

		boolean m_withFullHeader = false;
		String m_url = null;

		public CallServiceAsync(boolean withFullHeader) {
			m_withFullHeader = withFullHeader;
		}

		public CallServiceAsync(String url) {
			m_url = url;
		}

		@Override
		protected ServiceResponse doInBackground(Void... params) {
			String strResponse = "";
			if (m_url == null) {
				strResponse = call();
			} else {
				strResponse = call(m_url);
			}
			ServiceResponse response = new ServiceResponse();
			// response.TAG = TAG;
			JSONObject data;
			try {
				data = new JSONObject(strResponse);
				if (data != null) {
					// .........for amuse me login................
					response.isSuccess = data.getBoolean("success");
					response.Message = data.getString("message");
					response.RawResponse = data.getString("data");
					response.ErrorCode = data.getInt("error_code");
					int code = data.getInt("error_code");
					if (code > 0) {
						response.isSuccess = false;
					}

				}
			} catch (JSONException e) {
				response.Message = ServiceHelper.COMMON_ERROR;
				e.printStackTrace();
			}
			Utils.LogInfo("RAW RESPONSE :: " + strResponse);
			return response;
		}

		@Override
		protected void onPostExecute(ServiceResponse result) {
			if (m_delegate != null) {
				m_delegate.CallFinish(result);
			}
			super.onPostExecute(result);
		}

	}
}
