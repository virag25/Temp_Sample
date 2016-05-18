package common;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class PARAMETERS {
	
	public interface SHARED_PREFERENCE_KEYS {

        /* Shared Preference Title */
        public final String SHARED_PREFERENCE_TITLE = "SP_SPORT_APP";
        public final String KEY_HANDLER_MESSAGE = "handler_message";
        
        
	}
	
	public final static String SENDER_ID = "1076838335710" ;
	
	public final static String KEY_SPORT_FIND_SPIORT = "find_sport";
	public final static String KEY_SPORT_FIND_CITY = "find_city";
	public final static String KEY_SPORT_FIND_SPORT_ID = "find_sport_id";
	public final static String KEY_SPORT_FIND_RATING = "find_rating";
	public final static String KEY_SPORT_LEARN_SPIORT = "learn_sport";
	public final static String KEY_SPORT_LEARN_SPIORT_ID = "learn_sport_id";
	public final static String KEY_SPORT_LEARN_CITY = "learn_city";
	public final static String KEY_SPORT_LEARN_TRAINING = "learn_training";
	public final static String KEY_SPORT_LEARN_TRAINING_STRING = "learn_training_string";
	public final static String KEY_SPORT_EVENT_SPIORT = "event_sport";
	public final static String KEY_SPORT_EVENT_SPIORT_ID = "event_sport_id";
	public final static String KEY_SPORT_EVENT_CITY = "event_city";
	public final static String KEY_SPORT_FRIEND_CITY = "friend_city";
	public final static String KEY_SPORT_FRIEND_SPORT = "friend_sport";
	public final static String KEY_SPORT_FRIEND_SPORT_ID = "friend_sport_id";
	public final static String KEY_SPORT_FRIEND_PLAYER_LAVEL = "friend_player_lavel";
	public final static String UPDATECHATLIST = "UPDATECHATLIST";
	public final static String UPDATEGROUPCHATLIST = "UPDATEGROUPCHATLIST";
	public final static String UPDATEPLAYERFILTER = "UPDATEPLAYERFILTER";

	public final static String KEY_SPORT_PLAY_PLAYER_LAVEL = "play_player_lavel";
	public final static String KEY_SPORT_PLAY_DATE = "play_date";
	public final static String KEY_SPORT_PLAY_CITY = "play_city";
	public final static String KEY_SPORT_PLAY_START_TIME = "play_start_time";
	public final static String KEY_SPORT_PLAY_END_TIME = "play_end_time";
	public final static String KEY_SPORT_PLAY_SPORT_ID = "play_sport_id";

	public static String getIMEINO(Context ctx) {
		TelephonyManager tManager = (TelephonyManager) ctx
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imeiid = "";
		imeiid = tManager.getDeviceId();
		if (imeiid == null) {
			imeiid = "";
		}
		if (imeiid.length() <= 0) {
			imeiid = Secure.getString(ctx.getContentResolver(),
					Secure.ANDROID_ID);
		}
		// Toast.makeText(ctx, imeiid, Toast.LENGTH_LONG).show();
		return imeiid;
	}
	
}
