package com.android.car.settings.datausage;

import android.content.Context;
import android.net.INetworkStatsService;
import android.net.INetworkStatsSession;
import android.net.NetworkPolicyManager;
import android.net.NetworkStats;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.settings.common.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class AppsNetworkStatsManager {
    private static final Logger LOG = new Logger((Class<?>) AppsNetworkStatsManager.class);
    private static final int NETWORK_STATS_ID = 1;
    private final List<Callback> mAppsNetworkStatsListeners = new ArrayList();
    private final Context mContext;
    private final NetworkPolicyManager mNetworkPolicyManager;
    private INetworkStatsSession mStatsSession;

    public interface Callback {
        void onDataLoaded(@Nullable NetworkStats networkStats, @Nullable int[] iArr);
    }

    AppsNetworkStatsManager(Context context) {
        this.mContext = context;
        this.mNetworkPolicyManager = NetworkPolicyManager.from(context);
        try {
            this.mStatsSession = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats")).openSession();
        } catch (RemoteException e) {
            LOG.e("Could not open a network session", e);
        }
    }

    public void registerListener(Callback appsNetworkStatsListener) {
        if (!this.mAppsNetworkStatsListeners.contains(appsNetworkStatsListener)) {
            this.mAppsNetworkStatsListeners.add(appsNetworkStatsListener);
        }
    }

    public void unregisterListener(Callback appsNetworkStatsListener) {
        this.mAppsNetworkStatsListeners.remove(appsNetworkStatsListener);
    }

    public void startLoading(LoaderManager loaderManager, Bundle bundle) {
        loaderManager.restartLoader(1, bundle, new AppsNetworkStatsResult());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppsNetworkStatsLoaded(NetworkStats stats, int[] restrictedUids) {
        for (Callback listener : this.mAppsNetworkStatsListeners) {
            listener.onDataLoaded(stats, restrictedUids);
        }
    }

    private class AppsNetworkStatsResult implements LoaderManager.LoaderCallbacks<NetworkStats> {
        private AppsNetworkStatsResult() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<NetworkStats> onCreateLoader(int id, Bundle args) {
            return new SummaryForAllUidLoader(AppsNetworkStatsManager.this.mContext, AppsNetworkStatsManager.this.mStatsSession, args);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<NetworkStats> loader, NetworkStats data) {
            int[] restrictedUids = AppsNetworkStatsManager.this.mNetworkPolicyManager.getUidsWithPolicy(1);
            AppsNetworkStatsManager.this.onAppsNetworkStatsLoaded(data, restrictedUids);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<NetworkStats> loader) {
            AppsNetworkStatsManager.this.onAppsNetworkStatsLoaded(null, new int[0]);
        }
    }
}
