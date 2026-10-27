package com.android.car.stats;

import android.app.StatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.StatsEvent;
import com.android.car.CarStatsLog;
import com.android.internal.util.ConcurrentUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
public class CarStatsService {
    private static final boolean DEBUG = false;
    private static final String TAG = "CarStatsService";
    private static final String VMS_CLIENT_STATS_DUMPSYS_HEADER = "uid,layerType,layerChannel,layerVersion,txBytes,txPackets,rxBytes,rxPackets,droppedBytes,droppedPackets";
    private static final String VMS_CONNECTION_STATS_DUMPSYS_HEADER = "uid,packageName,attempts,connected,disconnected,terminated,errors";
    private final Context mContext;
    private final PackageManager mPackageManager;
    private final StatsManager mStatsManager;
    private final Map<Integer, VmsClientLogger> mVmsClientStats = new ArrayMap();
    private static final Function<VmsClientLogger, String> VMS_CONNECTION_STATS_DUMPSYS_FORMAT = new Function() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$SQru8gltIw7fRY4QCKc1quKTXM4
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            VmsClientLogger vmsClientLogger = (VmsClientLogger) obj;
            return String.format(Locale.US, "%d,%s,%d,%d,%d,%d,%d", Integer.valueOf(vmsClientLogger.getUid()), vmsClientLogger.getPackageName(), Long.valueOf(vmsClientLogger.getConnectionStateCount(1)), Long.valueOf(vmsClientLogger.getConnectionStateCount(2)), Long.valueOf(vmsClientLogger.getConnectionStateCount(3)), Long.valueOf(vmsClientLogger.getConnectionStateCount(4)), Long.valueOf(vmsClientLogger.getConnectionStateCount(5)));
        }
    };
    private static final Function<VmsClientStats, String> VMS_CLIENT_STATS_DUMPSYS_FORMAT = new Function() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$OLzDBKTN8MWqmffWR8KiuWo1lQ8
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            VmsClientStats vmsClientStats = (VmsClientStats) obj;
            return String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d", Integer.valueOf(vmsClientStats.getUid()), Integer.valueOf(vmsClientStats.getLayerType()), Integer.valueOf(vmsClientStats.getLayerChannel()), Integer.valueOf(vmsClientStats.getLayerVersion()), Long.valueOf(vmsClientStats.getTxBytes()), Long.valueOf(vmsClientStats.getTxPackets()), Long.valueOf(vmsClientStats.getRxBytes()), Long.valueOf(vmsClientStats.getRxPackets()), Long.valueOf(vmsClientStats.getDroppedBytes()), Long.valueOf(vmsClientStats.getDroppedPackets()));
        }
    };
    private static final Comparator<VmsClientStats> VMS_CLIENT_STATS_ORDER = Comparator.comparingInt(new ToIntFunction() { // from class: com.android.car.stats.-$$Lambda$NzQHmHcmCXpZGdMwzFRNdZAA_b8
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            return ((VmsClientStats) obj).getUid();
        }
    }).thenComparingInt(new ToIntFunction() { // from class: com.android.car.stats.-$$Lambda$DMw-CAZLiLmN9p9vsgV1s4qTACU
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            return ((VmsClientStats) obj).getLayerType();
        }
    }).thenComparingInt(new ToIntFunction() { // from class: com.android.car.stats.-$$Lambda$Ae3HZE7DkCQiACxOME57hDQoAhg
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            return ((VmsClientStats) obj).getLayerChannel();
        }
    }).thenComparingInt(new ToIntFunction() { // from class: com.android.car.stats.-$$Lambda$ZC55c5KE7QSb5PvNh2uMLCvlBSA
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            return ((VmsClientStats) obj).getLayerVersion();
        }
    });

    public CarStatsService(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mStatsManager = (StatsManager) this.mContext.getSystemService("stats");
    }

    public void init() {
        StatsManager.PullAtomMetadata metadata = new StatsManager.PullAtomMetadata.Builder().setAdditiveFields(new int[]{5, 6, 7, 8, 9, 10}).build();
        this.mStatsManager.setPullAtomCallback(CarStatsLog.VMS_CLIENT_STATS, metadata, ConcurrentUtils.DIRECT_EXECUTOR, new StatsManager.StatsPullAtomCallback() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$3kFxUYWnk6XFAfZUd4hPBkaL3ls
            public final int onPullAtom(int i, List list) {
                return this.f$0.lambda$init$2$CarStatsService(i, list);
            }
        });
    }

    public VmsClientLogger getVmsClientLogger(int clientUid) {
        VmsClientLogger vmsClientLoggerComputeIfAbsent;
        synchronized (this.mVmsClientStats) {
            vmsClientLoggerComputeIfAbsent = this.mVmsClientStats.computeIfAbsent(Integer.valueOf(clientUid), new Function() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$UXlHpK82sXN7GpDcu-bXv4U5Gn8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$getVmsClientLogger$3$CarStatsService((Integer) obj);
                }
            });
        }
        return vmsClientLoggerComputeIfAbsent;
    }

    public /* synthetic */ VmsClientLogger lambda$getVmsClientLogger$3$CarStatsService(Integer uid) {
        String packageName = this.mPackageManager.getNameForUid(uid.intValue());
        return new VmsClientLogger(uid.intValue(), packageName);
    }

    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        List<String> flags = Arrays.asList(args);
        if (args.length == 0 || flags.contains("--vms-client")) {
            dumpVmsStats(writer);
        }
    }

    private void dumpVmsStats(final PrintWriter writer) {
        synchronized (this.mVmsClientStats) {
            writer.println(VMS_CONNECTION_STATS_DUMPSYS_HEADER);
            this.mVmsClientStats.values().stream().filter(new Predicate() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$gok5M3Ys9w6MIgeaB0bJvJkhfjE
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return CarStatsService.lambda$dumpVmsStats$4((VmsClientLogger) obj);
                }
            }).sorted(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.car.stats.-$$Lambda$elBXxx0RKD-S_mR5Y3_7D4sSrh8
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((VmsClientLogger) obj).getUid();
                }
            })).forEachOrdered(new Consumer() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$D30V1MTtipJXEnzEhWtcroDP61I
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    writer.println(CarStatsService.VMS_CONNECTION_STATS_DUMPSYS_FORMAT.apply((VmsClientLogger) obj));
                }
            });
            writer.println();
            writer.println(VMS_CLIENT_STATS_DUMPSYS_HEADER);
            dumpVmsClientStats(new Consumer() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$uSD_lTbVXaLxqLgC1zFsXTRbniE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    writer.println(CarStatsService.VMS_CLIENT_STATS_DUMPSYS_FORMAT.apply((VmsClientStats) obj));
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$dumpVmsStats$4(VmsClientLogger entry) {
        return entry.getUid() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pullVmsClientStats, reason: merged with bridge method [inline-methods] */
    public int lambda$init$2$CarStatsService(final int atomTag, final List<StatsEvent> pulledData) {
        if (atomTag != 10065) {
            Log.w(TAG, "Unexpected atom tag: " + atomTag);
            return 1;
        }
        dumpVmsClientStats(new Consumer() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$PKgfzAtAaXaZ-oHiFH5EqLpHLC8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CarStatsService.lambda$pullVmsClientStats$7(atomTag, pulledData, (VmsClientStats) obj);
            }
        });
        return 0;
    }

    static /* synthetic */ void lambda$pullVmsClientStats$7(int atomTag, List pulledData, VmsClientStats entry) {
        StatsEvent e = StatsEvent.newBuilder().setAtomId(atomTag).writeInt(entry.getUid()).addBooleanAnnotation((byte) 1, true).writeInt(entry.getLayerType()).writeInt(entry.getLayerChannel()).writeInt(entry.getLayerVersion()).writeLong(entry.getTxBytes()).writeLong(entry.getTxPackets()).writeLong(entry.getRxBytes()).writeLong(entry.getRxPackets()).writeLong(entry.getDroppedBytes()).writeLong(entry.getDroppedPackets()).build();
        pulledData.add(e);
    }

    private void dumpVmsClientStats(Consumer<VmsClientStats> dumpFn) {
        synchronized (this.mVmsClientStats) {
            this.mVmsClientStats.values().stream().flatMap(new Function() { // from class: com.android.car.stats.-$$Lambda$CarStatsService$RQEvqnOJK08OT_Yyw0XlfA2qguI
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VmsClientLogger) obj).getLayerEntries().stream();
                }
            }).sorted(VMS_CLIENT_STATS_ORDER).forEachOrdered(dumpFn);
        }
    }
}
