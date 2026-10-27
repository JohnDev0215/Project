package com.android.car.developeroptions.network;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.network.SubscriptionsPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.wifi.WifiConnectionPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class MultiNetworkHeaderController extends BasePreferenceController implements WifiConnectionPreferenceController.UpdateListener, SubscriptionsPreferenceController.UpdateListener {
    public static final String TAG = "MultiNetworkHdrCtrl";
    private int mOriginalExpandedChildrenCount;
    private PreferenceCategory mPreferenceCategory;
    private PreferenceScreen mPreferenceScreen;
    private SubscriptionsPreferenceController mSubscriptionsController;
    private WifiConnectionPreferenceController mWifiController;

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

    public MultiNetworkHeaderController(Context context, String str) {
        super(context, str);
    }

    public void init(Lifecycle lifecycle) {
        this.mWifiController = createWifiController(lifecycle);
        this.mSubscriptionsController = createSubscriptionsController(lifecycle);
    }

    WifiConnectionPreferenceController createWifiController(Lifecycle lifecycle) {
        return new WifiConnectionPreferenceController(this.mContext, lifecycle, this, this.mPreferenceKey, 0, 746);
    }

    SubscriptionsPreferenceController createSubscriptionsController(Lifecycle lifecycle) {
        return new SubscriptionsPreferenceController(this.mContext, lifecycle, this, this.mPreferenceKey, 10);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
        this.mOriginalExpandedChildrenCount = preferenceScreen.getInitialExpandedChildrenCount();
        PreferenceCategory preferenceCategory = (PreferenceCategory) preferenceScreen.findPreference(this.mPreferenceKey);
        this.mPreferenceCategory = preferenceCategory;
        preferenceCategory.setVisible(isAvailable());
        this.mWifiController.displayPreference(preferenceScreen);
        this.mSubscriptionsController.displayPreference(preferenceScreen);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        SubscriptionsPreferenceController subscriptionsPreferenceController = this.mSubscriptionsController;
        return (subscriptionsPreferenceController == null || !subscriptionsPreferenceController.isAvailable()) ? 2 : 0;
    }

    @Override // com.android.car.developeroptions.wifi.WifiConnectionPreferenceController.UpdateListener, com.android.car.developeroptions.network.SubscriptionsPreferenceController.UpdateListener
    public void onChildrenUpdated() {
        boolean zIsAvailable = isAvailable();
        int i = this.mOriginalExpandedChildrenCount;
        if (i != Integer.MAX_VALUE) {
            if (zIsAvailable) {
                this.mPreferenceScreen.setInitialExpandedChildrenCount(i + this.mPreferenceCategory.getPreferenceCount());
            } else {
                this.mPreferenceScreen.setInitialExpandedChildrenCount(i);
            }
        }
        this.mPreferenceCategory.setVisible(zIsAvailable);
    }
}
