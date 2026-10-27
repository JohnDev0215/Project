package com.android.car.developeroptions.network.telephony.cdma;

import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.network.telephony.MobileNetworkUtils;
import com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public abstract class CdmaBasePreferenceController extends TelephonyBasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private DataContentObserver mDataContentObserver;
    protected Preference mPreference;
    protected PreferenceManager mPreferenceManager;
    protected int mSubId;
    protected TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public CdmaBasePreferenceController(Context context, String str) {
        super(context, str);
        this.mSubId = -1;
        this.mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mDataContentObserver.register(this.mContext, this.mSubId);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mDataContentObserver.unRegister(this.mContext);
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.isCdmaOptions(this.mContext, i) ? 0 : 2;
    }

    public void init(PreferenceManager preferenceManager, int i) {
        this.mPreferenceManager = preferenceManager;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    public void init(int i) {
        init(null, i);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = preferenceFindPreference;
        if (preferenceFindPreference instanceof CdmaListPreference) {
            ((CdmaListPreference) preferenceFindPreference).setSubId(this.mSubId);
        }
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            CdmaBasePreferenceController cdmaBasePreferenceController = CdmaBasePreferenceController.this;
            cdmaBasePreferenceController.updateState(cdmaBasePreferenceController.mPreference);
        }

        public void register(Context context, int i) {
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("preferred_network_mode" + i), false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
