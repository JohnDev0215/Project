package com.android.car.developeroptions.wifi.savedaccesspoints;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.utils.PreferenceGroupChildrenCache;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiSavedConfigUtils;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SubscribedAccessPointsPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, Preference.OnPreferenceClickListener, WifiManager.ActionListener {
    private static final String TAG = "SubscribedAPPrefCtrl";
    private final PreferenceGroupChildrenCache mChildrenCache;
    private SavedAccessPointsWifiSettings mHost;
    private PreferenceGroup mPreferenceGroup;
    private final AccessPointPreference.UserBadgeCache mUserBadgeCache;
    private final WifiManager mWifiManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public SubscribedAccessPointsPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserBadgeCache = new AccessPointPreference.UserBadgeCache(context.getPackageManager());
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mChildrenCache = new PreferenceGroupChildrenCache();
    }

    public SubscribedAccessPointsPreferenceController setHost(SavedAccessPointsWifiSettings savedAccessPointsWifiSettings) {
        this.mHost = savedAccessPointsWifiSettings;
        return this;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        lambda$postRefreshSubscribedAccessPoints$0();
    }

    public void postRefreshSubscribedAccessPoints() {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.wifi.savedaccesspoints.-$$Lambda$SubscribedAccessPointsPreferenceController$swRz-TMMrPSDaSUOgRP8dKrzNs4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$postRefreshSubscribedAccessPoints$0$SubscribedAccessPointsPreferenceController();
            }
        });
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        SavedAccessPointsWifiSettings savedAccessPointsWifiSettings = this.mHost;
        if (savedAccessPointsWifiSettings == null) {
            return false;
        }
        savedAccessPointsWifiSettings.showWifiDialog((AccessPointPreference) preference);
        return false;
    }

    public void onSuccess() {
        postRefreshSubscribedAccessPoints();
    }

    public void onFailure(int i) {
        postRefreshSubscribedAccessPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: refreshSubscribedAccessPoints, reason: merged with bridge method [inline-methods] */
    public void lambda$postRefreshSubscribedAccessPoints$0$SubscribedAccessPointsPreferenceController() {
        if (this.mPreferenceGroup == null) {
            Log.w(TAG, "PreferenceGroup is null, skipping.");
            return;
        }
        SavedAccessPointsWifiSettings savedAccessPointsWifiSettings = this.mHost;
        if (savedAccessPointsWifiSettings != null && !savedAccessPointsWifiSettings.isSubscriptionsFeatureEnabled()) {
            this.mPreferenceGroup.setVisible(false);
            return;
        }
        Context context = this.mPreferenceGroup.getContext();
        List<AccessPoint> allConfigs = WifiSavedConfigUtils.getAllConfigs(this.mContext, this.mWifiManager);
        Collections.sort(allConfigs, SavedNetworkComparator.INSTANCE);
        this.mChildrenCache.cacheRemoveAllPrefs(this.mPreferenceGroup);
        int size = allConfigs.size();
        for (int i = 0; i < size; i++) {
            AccessPoint accessPoint = allConfigs.get(i);
            if (accessPoint.isPasspointConfig()) {
                String key = accessPoint.getKey();
                AccessPointPreference accessPointPreference = (AccessPointPreference) this.mChildrenCache.getCachedPreference(key);
                if (accessPointPreference == null) {
                    accessPointPreference = new AccessPointPreference(accessPoint, context, this.mUserBadgeCache, true);
                    accessPointPreference.setKey(key);
                    accessPointPreference.setIcon((Drawable) null);
                    accessPointPreference.setOnPreferenceClickListener(this);
                    this.mPreferenceGroup.addPreference(accessPointPreference);
                }
                accessPointPreference.setOrder(i);
            }
        }
        this.mChildrenCache.removeCachedPrefs(this.mPreferenceGroup);
        if (this.mPreferenceGroup.getPreferenceCount() < 1) {
            Log.w(TAG, "Subscribed networks activity loaded, but there are no subscribed networks!");
            this.mPreferenceGroup.setVisible(false);
        } else {
            this.mPreferenceGroup.setVisible(true);
        }
    }
}
