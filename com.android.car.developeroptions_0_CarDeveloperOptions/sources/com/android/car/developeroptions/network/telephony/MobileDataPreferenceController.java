package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class MobileDataPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String DIALOG_TAG = "MobileDataDialog";
    private DataContentObserver mDataContentObserver;
    int mDialogType;
    private FragmentManager mFragmentManager;
    boolean mNeedDialog;
    private SwitchPreference mPreference;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return i != -1 ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public MobileDataPreferenceController(Context context, String str) {
        super(context, str);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        int i = this.mSubId;
        if (i != -1) {
            this.mDataContentObserver.register(this.mContext, i);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        if (this.mSubId != -1) {
            this.mDataContentObserver.unRegister(this.mContext);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        if (!this.mNeedDialog) {
            return true;
        }
        showDialog(this.mDialogType);
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        boolean zIsDialogNeeded = isDialogNeeded();
        this.mNeedDialog = zIsDialogNeeded;
        if (zIsDialogNeeded) {
            return false;
        }
        MobileNetworkUtils.setMobileDataEnabled(this.mContext, this.mSubId, z, false);
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mTelephonyManager.isDataEnabled();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(!isOpportunistic());
    }

    private boolean isOpportunistic() {
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        return activeSubscriptionInfo != null && activeSubscriptionInfo.isOpportunistic();
    }

    public static Uri getObservableUri(int i) {
        Uri uriFor = Settings.Global.getUriFor("mobile_data");
        if (TelephonyManager.getDefault().getSimCount() == 1) {
            return uriFor;
        }
        return Settings.Global.getUriFor("mobile_data" + i);
    }

    public void init(FragmentManager fragmentManager, int i) {
        this.mFragmentManager = fragmentManager;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    boolean isDialogNeeded() {
        boolean z = !isChecked();
        boolean z2 = this.mTelephonyManager.getSimCount() > 1;
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        boolean z3 = this.mSubscriptionManager.isActiveSubscriptionId(defaultDataSubscriptionId) && defaultDataSubscriptionId != this.mSubId;
        if (z) {
            if (z2 && z3) {
                this.mDialogType = 1;
                return true;
            }
        } else if (!z2) {
            this.mDialogType = 0;
            return true;
        }
        return false;
    }

    private void showDialog(int i) {
        MobileDataDialogFragment.newInstance(i, this.mSubId).show(this.mFragmentManager, DIALOG_TAG);
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            MobileDataPreferenceController mobileDataPreferenceController = MobileDataPreferenceController.this;
            mobileDataPreferenceController.updateState(mobileDataPreferenceController.mPreference);
        }

        public void register(Context context, int i) {
            context.getContentResolver().registerContentObserver(MobileDataPreferenceController.getObservableUri(i), false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
