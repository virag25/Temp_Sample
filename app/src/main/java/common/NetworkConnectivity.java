package common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


import myandroi.sampledemo.MyApplication;

public class NetworkConnectivity {

    public static boolean isConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) MyApplication
                    .getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("unused")
    public boolean IsFastConnection() {
        boolean isFast = false;
        ConnectivityManager cm = (ConnectivityManager) MyApplication
                .getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo Info = cm.getActiveNetworkInfo();
        if (Info == null || !Info.isConnectedOrConnecting()) {
            // Log.i(TAG, "No connection");
            Utils.LogInfo("No connection");
            isFast = false;
        } else {
            int netType = Info.getType();

            int netSubtype = Info.getSubtype();
            int speed = 0;
            if (netType == ConnectivityManager.TYPE_WIFI) {
                // Log.i(TAG, "Wifi connection");
                Utils.LogInfo("Wifi connection");
                WifiManager wifiManager = (WifiManager) MyApplication
                        .getContext().getSystemService(Context.WIFI_SERVICE);
                // List<ScanResult> scanResult = wifiManager.getScanResults();
                // for (int i = 0; i < scanResult.size(); i++) {
                // Log.d("scanResult", "Speed of wifi"
                // + scanResult.get(i).level);// The db level of signal
                // }
                speed = wifiManager.getConnectionInfo().getRssi();
                // Need to get wifi strength
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager telephonyManager = (TelephonyManager) MyApplication
                        .getContext().getSystemService(
                                Context.TELEPHONY_SERVICE);
                // CellInfoGsm cellinfogsm = (CellInfoGsm) telephonyManager
                // .getAllCellInfo().get(0);
                // CellSignalStrengthGsm cellSignalStrengthGsm = cellinfogsm
                // .getCellSignalStrength();
                // speed = cellSignalStrengthGsm.getDbm();
            }

        }
        return isFast;
    }

}
