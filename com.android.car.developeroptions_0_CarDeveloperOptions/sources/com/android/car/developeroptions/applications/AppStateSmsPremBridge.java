package com.android.car.developeroptions.applications;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.car.developeroptions.applications.AppStateBaseBridge;
import com.android.internal.telephony.ISms;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppStateSmsPremBridge extends AppStateBaseBridge {
    public static final ApplicationsState.AppFilter FILTER_APP_PREMIUM_SMS = new ApplicationsState.AppFilter() { // from class: com.android.car.developeroptions.applications.AppStateSmsPremBridge.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            Object obj = appEntry.extraInfo;
            return (obj instanceof SmsState) && ((SmsState) obj).smsState != 0;
        }
    };
    private final ISms mSmsManager;

    public static class SmsState {
        public int smsState;
    }

    public AppStateSmsPremBridge(Context context, ApplicationsState applicationsState, AppStateBaseBridge.Callback callback) {
        super(applicationsState, callback);
        this.mSmsManager = ISms.Stub.asInterface(ServiceManager.getService("isms"));
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge
    protected void loadAllExtraInfo() {
        ArrayList<ApplicationsState.AppEntry> allApps = this.mAppSession.getAllApps();
        int size = allApps.size();
        for (int i = 0; i < size; i++) {
            ApplicationsState.AppEntry appEntry = allApps.get(i);
            ApplicationInfo applicationInfo = appEntry.info;
            updateExtraInfo(appEntry, applicationInfo.packageName, applicationInfo.uid);
        }
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge
    protected void updateExtraInfo(ApplicationsState.AppEntry appEntry, String str, int i) {
        appEntry.extraInfo = getState(str);
    }

    public SmsState getState(String str) {
        SmsState smsState = new SmsState();
        smsState.smsState = getSmsState(str);
        return smsState;
    }

    private int getSmsState(String str) {
        try {
            return this.mSmsManager.getPremiumSmsPermission(str);
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public void setSmsState(String str, int i) {
        try {
            this.mSmsManager.setPremiumSmsPermission(str, i);
        } catch (RemoteException unused) {
        }
    }
}
