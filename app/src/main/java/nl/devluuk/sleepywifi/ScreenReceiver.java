package nl.devluuk.sleepywifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {

    private WifiManager wifiManager;
    boolean wifiWasOn = false;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        boolean wifiEnabled = wifiManager.isWifiEnabled();

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
                wifiWasOn = true;
            } else {
                wifiWasOn = false;
            }
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)  && wifiWasOn) {
                wifiManager.setWifiEnabled(true);
        }

    }

}
