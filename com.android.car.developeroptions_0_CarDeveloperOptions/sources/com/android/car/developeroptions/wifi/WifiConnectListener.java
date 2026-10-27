package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class WifiConnectListener implements WifiManager.ActionListener {
    private final Context mContext;

    public void onSuccess() {
    }

    public WifiConnectListener(Context context) {
        this.mContext = context;
    }

    public void onFailure(int i) {
        Context context = this.mContext;
        if (context != null) {
            Toast.makeText(context, R.string.wifi_failed_connect_message, 0).show();
        }
    }
}
