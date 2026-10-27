package com.android.car.settings.applications.specialaccess;

import android.telephony.SmsManager;
import com.android.car.settings.applications.specialaccess.AppEntryListManager;
import com.android.settingslib.applications.ApplicationsState;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AppStatePremiumSmsBridge implements AppEntryListManager.ExtraInfoBridge {
    private final SmsManager mSmsManager;

    public AppStatePremiumSmsBridge(SmsManager smsManager) {
        this.mSmsManager = smsManager;
    }

    @Override // com.android.car.settings.applications.specialaccess.AppEntryListManager.ExtraInfoBridge
    public void loadExtraInfo(List<ApplicationsState.AppEntry> entries) {
        for (ApplicationsState.AppEntry entry : entries) {
            entry.extraInfo = Integer.valueOf(getSmsState(entry.info.packageName));
        }
    }

    private int getSmsState(String packageName) {
        return this.mSmsManager.getPremiumSmsConsent(packageName);
    }
}
