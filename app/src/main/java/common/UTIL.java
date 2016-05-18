package common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class UTIL {

	// Common method for debug log
	private static boolean isLogEnable = true;

	public static void Log_e(String tag, String msg) {
		if (isLogEnable) {
			Log.e(tag, msg);
		}
	}

	
	// Check internet connection before code execuction, it will return true if
	// internet is present and false if internet is not present
	public static boolean isInternetAvailable(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	// Pattern to check email address is valid or not
	private final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(

	"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
			+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
			+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
			+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
			+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
			+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");

	// It will return true if email address is valid and return false if email
	// address is not valid
	public static boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}
	
	

	// Display dialog when internet is not present
	public static void showNetworkAlertDialog(Context mCtx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
		builder.setCancelable(false);
		builder.setTitle("Network Error");
		builder.setMessage("No Internet Available Try again.");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	
	
	@SuppressWarnings("deprecation")
	public static void AlertDialog(Context ctx,String msg){
//		final AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
//		   alertDialog.setTitle(ctx.getResources().getString(R.string.app_name));
//		   alertDialog.setMessage(msg);
//		   alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//			   public void onClick(DialogInterface dialog, int which) {
//				   alertDialog.dismiss();
//			   }
//		   });
//		   alertDialog.show();
	}
	
	// Set typeface on textview
	public static void SetTypeFace(Context ctx, TextView tv) {
		Typeface font = Typeface.createFromAsset(ctx.getAssets(),
				"Telex-Regular.ttf");
		tv.setTypeface(font);
	}

	// Set typeface on edittext
	public static void SetTypeFace(Context ctx, EditText et) {
		Typeface font = Typeface.createFromAsset(ctx.getAssets(),
				"Telex-Regular.ttf");
		et.setTypeface(font);
	}
	
	public static void SetTypeFace(Context ctx, Button bt) {
		Typeface font = Typeface.createFromAsset(ctx.getAssets(),
				"Telex-Regular.ttf");
		bt.setTypeface(font);
	}

	// Display toast from classes
	public static void displayToast(Context ctx, String msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	}

	public static void hideSoftKeyboard(Activity context) {
		InputMethodManager inputManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputManager != null)
			inputManager.hideSoftInputFromWindow(context.getWindow()
					.getDecorView().getApplicationWindowToken(), 0);
		context.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

	}

	public static void showAlertDialog(Context ctx, String message) {
		AlertDialog.Builder exitDialog = new AlertDialog.Builder(ctx);
		exitDialog.setMessage(message);
		exitDialog.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.dismiss();
					}
				});
		exitDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int arg1) {
						dialog.dismiss();
					}
				});
		exitDialog.show();
	}

	@SuppressLint("SimpleDateFormat")
	public static long differenceBetweenCurrentAndNextDate(String nextdate) {
		long diff = 0;
		Date nextDate;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			nextDate = simpleDateFormat.parse(nextdate);
			long nextDateTime = nextDate.getTime();
			diff = nextDateTime - System.currentTimeMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}

	@SuppressLint("SimpleDateFormat")
	public static long differenceBetweenTwoDates(String date_1, String date_2) {
		long diff = 0;
		Date date1, date2;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			date1 = simpleDateFormat.parse(date_1);
			date2 = simpleDateFormat.parse(date_2);
			long date1Long = date1.getTime();
			long date2Long = date2.getTime();
			diff = date2Long - date1Long;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}

	public static String timeRemainingInDHMSFormat(long differenceInMilliSeconds) {

		int SECOND = 1000;
		int MINUTE = 60 * SECOND;
		int HOUR = 60 * MINUTE;
		int DAY = 24 * HOUR;

		StringBuffer timeRemaining = new StringBuffer("");
		if (differenceInMilliSeconds > DAY) {
			timeRemaining.append(differenceInMilliSeconds / DAY).append(
					" days ");
			differenceInMilliSeconds %= DAY;
		}
		if (differenceInMilliSeconds > HOUR) {
			timeRemaining.append(differenceInMilliSeconds / HOUR).append(
					" hours ");
			differenceInMilliSeconds %= HOUR;
		}
		if (differenceInMilliSeconds > MINUTE) {
			timeRemaining.append(differenceInMilliSeconds / MINUTE).append(
					" minutes ");
			differenceInMilliSeconds %= MINUTE;
		}
		if (differenceInMilliSeconds > SECOND) {
			timeRemaining.append(differenceInMilliSeconds / SECOND).append(
					" seconds ");
			differenceInMilliSeconds %= SECOND;
		}

		return timeRemaining.toString();
	}

//	@SuppressWarnings("deprecation")
//	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
//			.resetViewBeforeLoading().cacheOnDisc()
//			.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
//			.bitmapConfig(Bitmap.Config.RGB_565)
//			.displayer(new FadeInBitmapDisplayer(200)).build();
	
	@SuppressLint("SimpleDateFormat")
	public static void showTimePicker(final EditText editText,Context context){
    	 Calendar mcurrentTime = Calendar.getInstance();
    	 int hour = mcurrentTime.get(Calendar.HOUR);
    	 int minute = mcurrentTime.get(Calendar.MINUTE);
    	 TimePickerDialog mTimePicker;
   
    	 mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
    		 @SuppressWarnings("deprecation")
			@Override
    		 public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
    			 Time tme = new Time(selectedHour, selectedMinute, 0);//seconds by default set to zero
    			 Format formatter;
    			 formatter = new SimpleDateFormat("h:mm a");
    			 editText.setText( formatter.format(tme));
    		 }
       
    	 }, hour, minute, false);
  
    	 mTimePicker.setTitle("Select Time");
    	 mTimePicker.show();
    }

}
