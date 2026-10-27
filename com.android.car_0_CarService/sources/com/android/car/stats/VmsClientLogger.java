package com.android.car.stats;

import android.car.vms.VmsLayer;
import android.util.ArrayMap;
import com.android.car.CarStatsLog;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class VmsClientLogger {
    private final String mPackageName;
    private final int mUid;
    private final Object mLock = new Object();
    private Map<Integer, AtomicLong> mConnectionStateCounters = new ArrayMap();
    private final Map<VmsLayer, VmsClientStats> mLayerStats = new ArrayMap();

    public static class ConnectionState {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int CONNECTION_ERROR = 5;
        public static final int DISCONNECTED = 3;
        public static final int TERMINATED = 4;
    }

    VmsClientLogger(int clientUid, String clientPackage) {
        this.mUid = clientUid;
        this.mPackageName = clientPackage != null ? clientPackage : "";
    }

    public int getUid() {
        return this.mUid;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void logConnectionState(int connectionState) {
        AtomicLong counter;
        CarStatsLog.write(CarStatsLog.VMS_CLIENT_CONNECTION_STATE_CHANGED, this.mUid, connectionState);
        synchronized (this.mLock) {
            counter = this.mConnectionStateCounters.computeIfAbsent(Integer.valueOf(connectionState), new Function() { // from class: com.android.car.stats.-$$Lambda$VmsClientLogger$ICC505kxiz154wELg0PHgXRuYKg
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return VmsClientLogger.lambda$logConnectionState$0((Integer) obj);
                }
            });
        }
        counter.incrementAndGet();
    }

    static /* synthetic */ AtomicLong lambda$logConnectionState$0(Integer ignored) {
        return new AtomicLong();
    }

    long getConnectionStateCount(int connectionState) {
        AtomicLong counter;
        synchronized (this.mLock) {
            counter = this.mConnectionStateCounters.get(Integer.valueOf(connectionState));
        }
        if (counter == null) {
            return 0L;
        }
        return counter.get();
    }

    public void logPacketSent(VmsLayer layer, long size) {
        getLayerEntry(layer).packetSent(size);
    }

    public void logPacketReceived(VmsLayer layer, long size) {
        getLayerEntry(layer).packetReceived(size);
    }

    public void logPacketDropped(VmsLayer layer, long size) {
        getLayerEntry(layer).packetDropped(size);
    }

    Collection<VmsClientStats> getLayerEntries() {
        Collection<VmsClientStats> collection;
        synchronized (this.mLock) {
            collection = (Collection) this.mLayerStats.values().stream().map(new Function() { // from class: com.android.car.stats.-$$Lambda$wWnXWH2SoxrmJII0VT8Cp7B8UpI
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new VmsClientStats((VmsClientStats) obj);
                }
            }).collect(Collectors.toList());
        }
        return collection;
    }

    private VmsClientStats getLayerEntry(final VmsLayer layer) {
        VmsClientStats vmsClientStatsComputeIfAbsent;
        synchronized (this.mLock) {
            vmsClientStatsComputeIfAbsent = this.mLayerStats.computeIfAbsent(layer, new Function() { // from class: com.android.car.stats.-$$Lambda$VmsClientLogger$8XrWPYGRskOQh7jC_oC9e7jw-9o
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$getLayerEntry$1$VmsClientLogger(layer, (VmsLayer) obj);
                }
            });
        }
        return vmsClientStatsComputeIfAbsent;
    }

    public /* synthetic */ VmsClientStats lambda$getLayerEntry$1$VmsClientLogger(VmsLayer layer, VmsLayer k) {
        return new VmsClientStats(this.mUid, layer);
    }
}
