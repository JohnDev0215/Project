package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.widget.Switch;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.development.featureflags.FeatureFlagPersistent;
import com.android.car.developeroptions.network.SubscriptionUtil;
import com.android.car.developeroptions.network.SubscriptionsChangeListener;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.widget.LayoutPreference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkSwitchController extends BasePreferenceController implements SubscriptionsChangeListener.SubscriptionsChangeListenerClient, LifecycleObserver {
    private static final String TAG = "MobileNetworkSwitchCtrl";
    private SubscriptionsChangeListener mChangeListener;
    private int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private SwitchBar mSwitchBar;

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

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onAirplaneModeChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public MobileNetworkSwitchController(Context context, String str) {
        super(context, str);
        this.mSubId = -1;
        this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
        this.mChangeListener = new SubscriptionsChangeListener(context, this);
    }

    public void init(Lifecycle lifecycle, int i) {
        lifecycle.addObserver(this);
        this.mSubId = i;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mChangeListener.start();
        update();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mChangeListener.stop();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SwitchBar switchBar = (SwitchBar) ((LayoutPreference) preferenceScreen.findPreference(this.mPreferenceKey)).findViewById(R.id.switch_bar);
        this.mSwitchBar = switchBar;
        switchBar.setSwitchBarText(R.string.mobile_network_use_sim_on, R.string.mobile_network_use_sim_off);
        this.mSwitchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$MobileNetworkSwitchController$tUU8wGl99eCmsTevSgC11SXwvc8
            @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
            public final void onSwitchChanged(Switch r1, boolean z) {
                this.f$0.lambda$displayPreference$0$MobileNetworkSwitchController(r1, z);
            }
        });
        update();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$MobileNetworkSwitchController(Switch r2, boolean z) {
        if (this.mSubscriptionManager.isSubscriptionEnabled(this.mSubId) == z || this.mSubscriptionManager.setSubscriptionEnabled(this.mSubId, z)) {
            return;
        }
        this.mSwitchBar.setChecked(!z);
    }

    private void update() {
        if (this.mSwitchBar == null) {
            return;
        }
        List<SubscriptionInfo> availableSubscriptions = SubscriptionUtil.getAvailableSubscriptions(this.mSubscriptionManager);
        if (this.mSubId == -1 || availableSubscriptions.size() < 2) {
            this.mSwitchBar.hide();
            return;
        }
        Iterator<SubscriptionInfo> it = availableSubscriptions.iterator();
        while (it.hasNext()) {
            if (it.next().getSubscriptionId() == this.mSubId) {
                this.mSwitchBar.show();
                this.mSwitchBar.setChecked(this.mSubscriptionManager.isSubscriptionEnabled(this.mSubId));
                return;
            }
        }
        this.mSwitchBar.hide();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return FeatureFlagPersistent.isEnabled(this.mContext, "settings_network_and_internet_v2") ? 1 : 2;
    }

    @Override // com.android.car.developeroptions.network.SubscriptionsChangeListener.SubscriptionsChangeListenerClient
    public void onSubscriptionsChanged() {
        update();
    }
}
