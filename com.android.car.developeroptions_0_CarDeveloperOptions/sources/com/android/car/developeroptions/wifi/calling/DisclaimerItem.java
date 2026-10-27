package com.android.car.developeroptions.wifi.calling;

import android.content.Context;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public abstract class DisclaimerItem {
    private final CarrierConfigManager mCarrierConfigManager;
    protected final Context mContext;
    protected final int mSubId;

    protected abstract int getMessageId();

    protected abstract String getName();

    protected abstract String getPrefKey();

    protected abstract int getTitleId();

    DisclaimerItem(Context context, int i) {
        this.mContext = context;
        this.mSubId = i;
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    void onAgreed() {
        setBooleanSharedPrefs(getPrefKey(), true);
    }

    boolean shouldShow() {
        if (getBooleanSharedPrefs(getPrefKey(), false)) {
            logd("shouldShow: false due to a user has already agreed.");
            return false;
        }
        logd("shouldShow: true");
        return true;
    }

    protected PersistableBundle getCarrierConfig() {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        return configForSubId != null ? configForSubId : CarrierConfigManager.getDefaultConfig();
    }

    protected void logd(String str) {
        Log.d(getName(), "[" + this.mSubId + "] " + str);
    }

    private boolean getBooleanSharedPrefs(String str, boolean z) {
        return this.mContext.getSharedPreferences("wfc_disclaimer_prefs", 0).getBoolean(str + this.mSubId, z);
    }

    private void setBooleanSharedPrefs(String str, boolean z) {
        this.mContext.getSharedPreferences("wfc_disclaimer_prefs", 0).edit().putBoolean(str + this.mSubId, z).apply();
    }
}
