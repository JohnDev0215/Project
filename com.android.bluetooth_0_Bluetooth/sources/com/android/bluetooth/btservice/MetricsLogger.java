package com.android.bluetooth.btservice;

import com.android.bluetooth.BluetoothMetricsProto;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: classes.dex */
public class MetricsLogger {
    private static final HashMap<BluetoothMetricsProto.ProfileId, Integer> sProfileConnectionCounts = new HashMap<>();

    public static void logProfileConnectionEvent(BluetoothMetricsProto.ProfileId profileId) {
        synchronized (sProfileConnectionCounts) {
            sProfileConnectionCounts.merge(profileId, 1, new BiFunction() { // from class: com.android.bluetooth.btservice.-$$Lambda$LrkJFe4YP5g-sc0rXJgTGXS3PRE
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Integer.valueOf(Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue()));
                }
            });
        }
    }

    public static void dumpProto(final BluetoothMetricsProto.BluetoothLog.Builder metricsBuilder) {
        synchronized (sProfileConnectionCounts) {
            sProfileConnectionCounts.forEach(new BiConsumer() { // from class: com.android.bluetooth.btservice.-$$Lambda$MetricsLogger$hpfdq7OwithG_Jc8upyFvO5SRk8
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    metricsBuilder.addProfileConnectionStats(BluetoothMetricsProto.ProfileConnectionStats.newBuilder().setProfileId((BluetoothMetricsProto.ProfileId) obj).setNumTimesConnected(((Integer) obj2).intValue()).build());
                }
            });
            sProfileConnectionCounts.clear();
        }
    }
}
