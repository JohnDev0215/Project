package com.android.car.developeroptions.wifi.slice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiScanWorker extends SliceBackgroundWorker<AccessPoint> implements WifiTracker.WifiListener {
    CaptivePortalNetworkCallback mCaptivePortalNetworkCallback;
    private final Context mContext;
    private WifiTracker mWifiTracker;

    class CaptivePortalNetworkCallback extends ConnectivityManager.NetworkCallback {
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onConnectedChanged() {
    }

    public WifiScanWorker(Context context, Uri uri) {
        super(context, uri);
        this.mContext = context;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mWifiTracker.onDestroy();
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onWifiStateChanged(int i) {
        notifySliceChange();
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onAccessPointsChanged() {
        if (!this.mWifiTracker.getManager().isWifiEnabled()) {
            updateResults(null);
            return;
        }
        List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
        ArrayList arrayList = new ArrayList();
        for (AccessPoint accessPoint : accessPoints) {
            if (accessPoint.isReachable()) {
                arrayList.add(clone(accessPoint));
                if (arrayList.size() >= 3) {
                    break;
                }
            }
        }
        updateResults(arrayList);
    }

    private AccessPoint clone(AccessPoint accessPoint) {
        Bundle bundle = new Bundle();
        accessPoint.saveWifiState(bundle);
        return new AccessPoint(this.mContext, bundle);
    }

    @Override // com.android.car.developeroptions.slices.SliceBackgroundWorker
    protected boolean areListsTheSame(List<AccessPoint> list, List<AccessPoint> list2) {
        if (!list.equals(list2)) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (getState(list.get(i)) != getState(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private NetworkInfo.State getState(AccessPoint accessPoint) {
        NetworkInfo networkInfo = accessPoint.getNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.getState();
        }
        return null;
    }
}
