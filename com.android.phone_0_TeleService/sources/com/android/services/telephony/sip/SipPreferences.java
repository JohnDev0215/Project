package com.android.services.telephony.sip;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import com.android.phone.R;

/* JADX INFO: loaded from: classes.dex */
public class SipPreferences {
    private Context mContext;

    public SipPreferences(Context context) {
        this.mContext = context;
    }

    public void setSipCallOption(String str) {
        Settings.System.putString(this.mContext.getContentResolver(), "sip_call_options", str);
        this.mContext.sendBroadcast(new Intent("android.net.sip.action.SIP_CALL_OPTION_CHANGED"));
    }

    public String getSipCallOption() {
        String string = Settings.System.getString(this.mContext.getContentResolver(), "sip_call_options");
        return string != null ? string : this.mContext.getString(R.string.sip_address_only);
    }

    public void setReceivingCallsEnabled(boolean z) {
        Settings.System.putInt(this.mContext.getContentResolver(), "sip_receive_calls", z ? 1 : 0);
    }

    public boolean isReceivingCallsEnabled() {
        try {
            return Settings.System.getInt(this.mContext.getContentResolver(), "sip_receive_calls") != 0;
        } catch (Settings.SettingNotFoundException e) {
            log("isReceivingCallsEnabled, option not set; use default value, exception: " + e);
            return false;
        }
    }

    public void clearSharedPreferences() {
        this.mContext.deleteSharedPreferences("SIP_PREFERENCES");
    }

    private static void log(String str) {
        Log.d("SIP", "[SipPreferences] " + str);
    }
}
