package com.android.car.developeroptions.nfc;

import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Pair;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.nfc.PaymentBackend;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NfcForegroundPreferenceController extends BasePreferenceController implements PaymentBackend.Callback, Preference.OnPreferenceChangeListener, LifecycleObserver, OnStart, OnStop {
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private PaymentBackend mPaymentBackend;
    private DropDownPreference mPreference;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NfcForegroundPreferenceController(Context context, String str) {
        super(context, str);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void setPaymentBackend(PaymentBackend paymentBackend) {
        this.mPaymentBackend = paymentBackend;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        PaymentBackend paymentBackend = this.mPaymentBackend;
        if (paymentBackend != null) {
            paymentBackend.registerCallback(this);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        PaymentBackend paymentBackend = this.mPaymentBackend;
        if (paymentBackend != null) {
            paymentBackend.unregisterCallback(this);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        PaymentBackend paymentBackend;
        List<PaymentBackend.PaymentAppInfo> paymentAppInfos;
        return (!this.mContext.getPackageManager().hasSystemFeature("android.hardware.nfc") || (paymentBackend = this.mPaymentBackend) == null || (paymentAppInfos = paymentBackend.getPaymentAppInfos()) == null || paymentAppInfos.isEmpty()) ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        DropDownPreference dropDownPreference = (DropDownPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = dropDownPreference;
        if (dropDownPreference == null) {
            return;
        }
        dropDownPreference.setEntries(new CharSequence[]{this.mContext.getText(R.string.nfc_payment_favor_open), this.mContext.getText(R.string.nfc_payment_favor_default)});
        this.mPreference.setEntryValues(new CharSequence[]{"1", "0"});
    }

    @Override // com.android.car.developeroptions.nfc.PaymentBackend.Callback
    public void onPaymentAppsChanged() {
        updateState(this.mPreference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof DropDownPreference) {
            ((DropDownPreference) preference).setValue(this.mPaymentBackend.isForegroundMode() ? "1" : "0");
        }
        super.updateState(preference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mPreference.getEntry();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!(preference instanceof DropDownPreference)) {
            return false;
        }
        DropDownPreference dropDownPreference = (DropDownPreference) preference;
        String str = (String) obj;
        dropDownPreference.setSummary(dropDownPreference.getEntries()[dropDownPreference.findIndexOfValue(str)]);
        boolean z = Integer.parseInt(str) != 0;
        this.mPaymentBackend.setForegroundMode(z);
        this.mMetricsFeatureProvider.action(this.mContext, z ? 1622 : 1623, new Pair[0]);
        return true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public void updateNonIndexableKeys(List<String> list) {
        String preferenceKey = getPreferenceKey();
        if (TextUtils.isEmpty(preferenceKey)) {
            return;
        }
        list.add(preferenceKey);
    }
}
