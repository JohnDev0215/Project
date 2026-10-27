package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.wifi.details.WifiNetworkDetailsFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;

/* JADX INFO: loaded from: classes.dex */
public class WifiConnectionPreferenceController extends AbstractPreferenceController implements WifiTracker.WifiListener {
    private AccessPointPreference.UserBadgeCache mBadgeCache;
    private int mMetricsCategory;
    private Context mPrefContext;
    private AccessPointPreference mPreference;
    private PreferenceGroup mPreferenceGroup;
    private String mPreferenceGroupKey;
    private UpdateListener mUpdateListener;
    private WifiTracker mWifiTracker;
    private int order;

    public interface UpdateListener {
        void onChildrenUpdated();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "active_wifi_connection";
    }

    public WifiConnectionPreferenceController(Context context, Lifecycle lifecycle, UpdateListener updateListener, String str, int i, int i2) {
        super(context);
        this.mUpdateListener = updateListener;
        this.mPreferenceGroupKey = str;
        this.mWifiTracker = WifiTrackerFactory.create(context, this, lifecycle, true, true);
        this.order = i;
        this.mMetricsCategory = i2;
        this.mBadgeCache = new AccessPointPreference.UserBadgeCache(context.getPackageManager());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mWifiTracker.isConnected() && getCurrentAccessPoint() != null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(this.mPreferenceGroupKey);
        this.mPrefContext = preferenceScreen.getContext();
        update();
    }

    private AccessPoint getCurrentAccessPoint() {
        for (AccessPoint accessPoint : this.mWifiTracker.getAccessPoints()) {
            if (accessPoint.isActive()) {
                return accessPoint;
            }
        }
        return null;
    }

    private void updatePreference(AccessPoint accessPoint) {
        AccessPointPreference accessPointPreference = this.mPreference;
        if (accessPointPreference != null) {
            this.mPreferenceGroup.removePreference(accessPointPreference);
            this.mPreference = null;
        }
        if (accessPoint == null || this.mPrefContext == null) {
            return;
        }
        AccessPointPreference accessPointPreference2 = new AccessPointPreference(accessPoint, this.mPrefContext, this.mBadgeCache, R.drawable.ic_wifi_signal_0, false);
        this.mPreference = accessPointPreference2;
        accessPointPreference2.setKey("active_wifi_connection");
        this.mPreference.refresh();
        this.mPreference.setOrder(this.order);
        this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiConnectionPreferenceController$MHPP7HTiJIPOArOf36ZxxvDiEzA
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$updatePreference$0$WifiConnectionPreferenceController(preference);
            }
        });
        this.mPreferenceGroup.addPreference(this.mPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updatePreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updatePreference$0$WifiConnectionPreferenceController(Preference preference) {
        Bundle bundle = new Bundle();
        this.mPreference.getAccessPoint().saveWifiState(bundle);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mPrefContext);
        subSettingLauncher.setTitleRes(R.string.pref_title_network_details);
        subSettingLauncher.setDestination(WifiNetworkDetailsFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setSourceMetricsCategory(this.mMetricsCategory);
        subSettingLauncher.launch();
        return true;
    }

    private void update() {
        AccessPoint currentAccessPoint = this.mWifiTracker.isConnected() ? getCurrentAccessPoint() : null;
        if (currentAccessPoint == null) {
            updatePreference(null);
        } else {
            AccessPointPreference accessPointPreference = this.mPreference;
            if (accessPointPreference == null || !accessPointPreference.getAccessPoint().equals(currentAccessPoint)) {
                updatePreference(currentAccessPoint);
            } else {
                AccessPointPreference accessPointPreference2 = this.mPreference;
                if (accessPointPreference2 != null) {
                    accessPointPreference2.refresh();
                }
            }
        }
        this.mUpdateListener.onChildrenUpdated();
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onWifiStateChanged(int i) {
        update();
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onConnectedChanged() {
        update();
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onAccessPointsChanged() {
        update();
    }
}
