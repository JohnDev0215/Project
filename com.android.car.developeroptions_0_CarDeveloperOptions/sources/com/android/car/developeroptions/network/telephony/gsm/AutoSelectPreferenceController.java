package com.android.car.developeroptions.network.telephony.gsm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.network.telephony.MobileNetworkUtils;
import com.android.car.developeroptions.network.telephony.NetworkSelectSettings;
import com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AutoSelectPreferenceController extends TelephonyTogglePreferenceController {
    private static final long MINIMUM_DIALOG_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private List<OnNetworkSelectModeListener> mListeners;
    private boolean mOnlyAutoSelectInHome;
    ProgressDialog mProgressDialog;
    private int mSubId;
    SwitchPreference mSwitchPreference;
    private TelephonyManager mTelephonyManager;
    private final Handler mUiHandler;

    public interface OnNetworkSelectModeListener {
        void onNetworkSelectModeChanged();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
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

    public AutoSelectPreferenceController(Context context, String str) {
        super(context, str);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mSubId = -1;
        this.mListeners = new ArrayList();
        this.mUiHandler = new Handler(Looper.getMainLooper());
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.shouldDisplayNetworkSelectOptions(this.mContext, i) ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSwitchPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mTelephonyManager.getNetworkSelectionMode() == 1;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setSummary((CharSequence) null);
        if (this.mTelephonyManager.getServiceState().getRoaming()) {
            preference.setEnabled(true);
            return;
        }
        preference.setEnabled(!this.mOnlyAutoSelectInHome);
        if (this.mOnlyAutoSelectInHome) {
            preference.setSummary(this.mContext.getString(R.string.manual_mode_disallowed_summary, this.mTelephonyManager.getSimOperatorName()));
        }
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (z) {
            final long jElapsedRealtime = SystemClock.elapsedRealtime();
            showAutoSelectProgressBar();
            this.mSwitchPreference.setEnabled(false);
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.network.telephony.gsm.-$$Lambda$AutoSelectPreferenceController$ZHVQFgnDhPp8anSWdWHn8oY-duk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setChecked$1$AutoSelectPreferenceController(jElapsedRealtime);
                }
            });
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("android.provider.extra.SUB_ID", this.mSubId);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(NetworkSelectSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(1581);
        subSettingLauncher.setTitleRes(R.string.choose_network_title);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setChecked$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setChecked$1$AutoSelectPreferenceController(long j) {
        this.mTelephonyManager.setNetworkSelectionModeAutomatic();
        final int networkSelectionMode = this.mTelephonyManager.getNetworkSelectionMode();
        this.mUiHandler.postDelayed(new Runnable() { // from class: com.android.car.developeroptions.network.telephony.gsm.-$$Lambda$AutoSelectPreferenceController$TTpKSm863c_F8BS-kxHVrfXUreA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setChecked$0$AutoSelectPreferenceController(networkSelectionMode);
            }
        }, Math.max(MINIMUM_DIALOG_TIME_MILLIS - (SystemClock.elapsedRealtime() - j), 0L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setChecked$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setChecked$0$AutoSelectPreferenceController(int i) {
        this.mSwitchPreference.setEnabled(true);
        this.mSwitchPreference.setChecked(i == 1);
        Iterator<OnNetworkSelectModeListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onNetworkSelectModeChanged();
        }
        dismissProgressBar();
    }

    public AutoSelectPreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        PersistableBundle configForSubId = ((CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class)).getConfigForSubId(this.mSubId);
        this.mOnlyAutoSelectInHome = configForSubId != null ? configForSubId.getBoolean("only_auto_select_in_home_network") : false;
        return this;
    }

    public AutoSelectPreferenceController addListener(OnNetworkSelectModeListener onNetworkSelectModeListener) {
        this.mListeners.add(onNetworkSelectModeListener);
        return this;
    }

    private void showAutoSelectProgressBar() {
        if (this.mProgressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this.mContext);
            this.mProgressDialog = progressDialog;
            progressDialog.setMessage(this.mContext.getResources().getString(R.string.register_automatically));
            this.mProgressDialog.setCanceledOnTouchOutside(false);
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setIndeterminate(true);
        }
        this.mProgressDialog.show();
    }

    private void dismissProgressBar() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        this.mProgressDialog.dismiss();
    }
}
