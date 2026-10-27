package com.android.car.settings.wifi;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CarWifiManager implements WifiTracker.WifiListener {
    private final Context mContext;
    private final List<Listener> mListeners = new ArrayList();
    private boolean mStarted;
    private WifiManager mWifiManager;
    private WifiTracker mWifiTracker;

    public interface Listener {
        void onAccessPointsChanged();

        void onWifiStateChanged(int i);
    }

    public CarWifiManager(Context context) {
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mWifiTracker = new WifiTracker(context, this, true, true);
    }

    public boolean addListener(Listener listener) {
        return this.mListeners.add(listener);
    }

    public boolean removeListener(Listener listener) {
        return this.mListeners.remove(listener);
    }

    public void start() {
        if (!this.mStarted) {
            this.mStarted = true;
            this.mWifiTracker.onStart();
        }
    }

    public void stop() {
        if (this.mStarted) {
            this.mStarted = false;
            this.mWifiTracker.onStop();
        }
    }

    public void destroy() {
        this.mWifiTracker.onDestroy();
    }

    public List<AccessPoint> getAllAccessPoints() {
        return getAccessPoints(false);
    }

    public List<AccessPoint> getSavedAccessPoints() {
        return getAccessPoints(true);
    }

    private List<AccessPoint> getAccessPoints(boolean saved) {
        List<AccessPoint> accessPoints = new ArrayList<>();
        if (this.mWifiManager.isWifiEnabled()) {
            for (AccessPoint accessPoint : this.mWifiTracker.getAccessPoints()) {
                if (shouldIncludeAp(accessPoint, saved)) {
                    accessPoints.add(accessPoint);
                }
            }
        }
        return accessPoints;
    }

    private boolean shouldIncludeAp(AccessPoint accessPoint, boolean saved) {
        if (saved) {
            return accessPoint.isReachable() && accessPoint.isSaved();
        }
        return accessPoint.isReachable();
    }

    public AccessPoint getConnectedAccessPoint() {
        for (AccessPoint accessPoint : getAllAccessPoints()) {
            if (accessPoint.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                return accessPoint;
            }
        }
        return null;
    }

    public boolean isWifiEnabled() {
        return this.mWifiManager.isWifiEnabled();
    }

    public boolean isWifiApEnabled() {
        return this.mWifiManager.isWifiApEnabled();
    }

    public SoftApConfiguration getSoftApConfig() {
        return this.mWifiManager.getSoftApConfiguration();
    }

    public void setSoftApConfig(SoftApConfiguration config) {
        this.mWifiManager.setSoftApConfiguration(config);
    }

    public String getCountryCode() {
        return this.mWifiManager.getCountryCode();
    }

    public boolean is5GhzBandSupported() {
        return this.mWifiManager.is5GHzBandSupported();
    }

    public int getWifiState() {
        return this.mWifiManager.getWifiState();
    }

    public boolean setWifiEnabled(boolean enabled) {
        return this.mWifiManager.setWifiEnabled(enabled);
    }

    public void connectToPublicWifi(AccessPoint accessPoint, WifiManager.ActionListener listener) {
        accessPoint.generateOpenNetworkConfig();
        this.mWifiManager.connect(accessPoint.getConfig(), listener);
    }

    public void connectToSavedWifi(AccessPoint accessPoint, WifiManager.ActionListener listener) {
        if (accessPoint.isSaved()) {
            this.mWifiManager.connect(accessPoint.getConfig(), listener);
        }
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onWifiStateChanged(int state) {
        for (Listener listener : this.mListeners) {
            listener.onWifiStateChanged(state);
        }
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onConnectedChanged() {
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onAccessPointsChanged() {
        for (Listener listener : this.mListeners) {
            listener.onAccessPointsChanged();
        }
    }
}
