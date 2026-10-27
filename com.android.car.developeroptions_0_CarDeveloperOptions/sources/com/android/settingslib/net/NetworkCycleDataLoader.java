package com.android.settingslib.net;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.INetworkStatsService;
import android.net.INetworkStatsSession;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkStatsHistory;
import android.net.NetworkTemplate;
import android.net.TrafficStats;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Pair;
import androidx.loader.content.AsyncTaskLoader;
import com.android.settingslib.NetworkPolicyEditor;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class NetworkCycleDataLoader<D> extends AsyncTaskLoader<D> {
    private final ArrayList<Long> mCycles;
    protected final NetworkStatsManager mNetworkStatsManager;
    final INetworkStatsService mNetworkStatsService;
    protected final NetworkTemplate mNetworkTemplate;
    private final NetworkPolicy mPolicy;

    abstract D getCycleUsage();

    abstract void recordUsage(long j, long j2);

    protected NetworkCycleDataLoader(Builder<?> builder) {
        super(((Builder) builder).mContext);
        this.mNetworkTemplate = ((Builder) builder).mNetworkTemplate;
        this.mCycles = ((Builder) builder).mCycles;
        this.mNetworkStatsManager = (NetworkStatsManager) ((Builder) builder).mContext.getSystemService("netstats");
        this.mNetworkStatsService = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
        NetworkPolicyEditor networkPolicyEditor = new NetworkPolicyEditor(NetworkPolicyManager.from(((Builder) builder).mContext));
        networkPolicyEditor.read();
        this.mPolicy = networkPolicyEditor.getPolicy(this.mNetworkTemplate);
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public D loadInBackground() {
        ArrayList<Long> arrayList = this.mCycles;
        if (arrayList != null && arrayList.size() > 1) {
            loadDataForSpecificCycles();
        } else if (this.mPolicy == null) {
            loadFourWeeksData();
        } else {
            loadPolicyData();
        }
        return getCycleUsage();
    }

    void loadPolicyData() {
        Iterator itCycleIterator = NetworkPolicyManager.cycleIterator(this.mPolicy);
        while (itCycleIterator.hasNext()) {
            Pair pair = (Pair) itCycleIterator.next();
            recordUsage(((ZonedDateTime) pair.first).toInstant().toEpochMilli(), ((ZonedDateTime) pair.second).toInstant().toEpochMilli());
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    protected void onReset() {
        super.onReset();
        cancelLoad();
    }

    void loadFourWeeksData() {
        try {
            INetworkStatsSession iNetworkStatsSessionOpenSession = this.mNetworkStatsService.openSession();
            NetworkStatsHistory historyForNetwork = iNetworkStatsSessionOpenSession.getHistoryForNetwork(this.mNetworkTemplate, 10);
            long start = historyForNetwork.getStart();
            long end = historyForNetwork.getEnd();
            while (end > start) {
                long j = end - 2419200000L;
                recordUsage(j, end);
                end = j;
            }
            TrafficStats.closeQuietly(iNetworkStatsSessionOpenSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    void loadDataForSpecificCycles() {
        long jLongValue = this.mCycles.get(0).longValue();
        int i = 1;
        int size = this.mCycles.size() - 1;
        while (i <= size) {
            long jLongValue2 = this.mCycles.get(i).longValue();
            recordUsage(jLongValue2, jLongValue);
            i++;
            jLongValue = jLongValue2;
        }
    }

    protected long getTotalUsage(NetworkStats networkStats) {
        long rxBytes = 0;
        if (networkStats != null) {
            NetworkStats.Bucket bucket = new NetworkStats.Bucket();
            while (networkStats.hasNextBucket() && networkStats.getNextBucket(bucket)) {
                rxBytes += bucket.getRxBytes() + bucket.getTxBytes();
            }
            networkStats.close();
        }
        return rxBytes;
    }

    public ArrayList<Long> getCycles() {
        return this.mCycles;
    }

    public static abstract class Builder<T extends NetworkCycleDataLoader> {
        private final Context mContext;
        private ArrayList<Long> mCycles;
        private NetworkTemplate mNetworkTemplate;

        public abstract T build();

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder<T> setNetworkTemplate(NetworkTemplate networkTemplate) {
            this.mNetworkTemplate = networkTemplate;
            return this;
        }

        public Builder<T> setCycles(ArrayList<Long> arrayList) {
            this.mCycles = arrayList;
            return this;
        }
    }
}
