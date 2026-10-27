package com.android.car;

import android.car.storagemonitoring.ICarStorageMonitoring;
import android.car.storagemonitoring.IIoStatsListener;
import android.car.storagemonitoring.IoStats;
import android.car.storagemonitoring.IoStatsEntry;
import android.car.storagemonitoring.LifetimeWriteInfo;
import android.car.storagemonitoring.UidIoRecord;
import android.car.storagemonitoring.WearEstimate;
import android.car.storagemonitoring.WearEstimateChange;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.JsonWriter;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.Manifest;
import com.android.car.internal.CarPermission;
import com.android.car.storagemonitoring.IoStatsTracker;
import com.android.car.storagemonitoring.UidIoStatsProvider;
import com.android.car.storagemonitoring.WearEstimateRecord;
import com.android.car.storagemonitoring.WearHistory;
import com.android.car.storagemonitoring.WearInformation;
import com.android.car.storagemonitoring.WearInformationProvider;
import com.android.car.systeminterface.SystemInterface;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CarStorageMonitoringService extends ICarStorageMonitoring.Stub implements CarServiceBase {
    private static final boolean DBG = false;
    public static final String INTENT_EXCESSIVE_IO = "android.car.storagemonitoring.EXCESSIVE_IO";
    static final String LIFETIME_WRITES_FILENAME = "lifetime_write";
    private static final int MIN_WEAR_ESTIMATE_OF_CONCERN = 80;
    public static final long SHUTDOWN_COST_INFO_MISSING = -1;
    private static final String TAG = "CAR.STORAGE";
    static final String UPTIME_TRACKER_FILENAME = "service_uptime";
    static final String WEAR_INFO_FILENAME = "wear_info";
    private final Configuration mConfiguration;
    private final Context mContext;
    private final SlidingWindow<IoStats> mIoStatsSamples;
    private final File mLifetimeWriteFile;
    private final RemoteCallbackList<IIoStatsListener> mListeners;
    private final OnShutdownReboot mOnShutdownReboot;
    private String mShutdownCostMissingReason;
    private final CarPermission mStorageMonitoringPermission;
    private final SystemInterface mSystemInterface;
    private final UidIoStatsProvider mUidIoStatsProvider;
    private final File mUptimeTrackerFile;
    private List<WearEstimateChange> mWearEstimateChanges;
    private final File mWearInfoFile;
    private final WearInformationProvider[] mWearInformationProviders;
    private final Object mLock = new Object();
    private UptimeTracker mUptimeTracker = null;
    private Optional<WearInformation> mWearInformation = Optional.empty();
    private List<IoStatsEntry> mBootIoStats = Collections.emptyList();
    private IoStatsTracker mIoStatsTracker = null;
    private boolean mInitialized = false;
    private long mShutdownCostInfo = -1;

    public CarStorageMonitoringService(Context context, SystemInterface systemInterface) {
        this.mContext = context;
        Resources resources = context.getResources();
        this.mConfiguration = new Configuration(resources);
        if (Log.isLoggable("CAR.STORAGE", 3)) {
            Log.d("CAR.STORAGE", "service configuration: " + this.mConfiguration);
        }
        this.mUidIoStatsProvider = systemInterface.getUidIoStatsProvider();
        this.mUptimeTrackerFile = new File(systemInterface.getSystemCarDir(), UPTIME_TRACKER_FILENAME);
        this.mWearInfoFile = new File(systemInterface.getSystemCarDir(), WEAR_INFO_FILENAME);
        this.mLifetimeWriteFile = new File(systemInterface.getSystemCarDir(), LIFETIME_WRITES_FILENAME);
        this.mOnShutdownReboot = new OnShutdownReboot(this.mContext);
        this.mSystemInterface = systemInterface;
        this.mWearInformationProviders = systemInterface.getFlashWearInformationProviders();
        this.mStorageMonitoringPermission = new CarPermission(this.mContext, Manifest.permission.STORAGE_MONITORING);
        this.mWearEstimateChanges = Collections.emptyList();
        this.mIoStatsSamples = new SlidingWindow<>(this.mConfiguration.ioStatsNumSamplesToStore);
        this.mListeners = new RemoteCallbackList<>();
        systemInterface.scheduleActionForBootCompleted(new Runnable() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$FLkoPFpCfepPQegy66cz8wn6Z0o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$CarStorageMonitoringService();
            }
        }, Duration.ofSeconds(10L));
    }

    public /* synthetic */ void lambda$new$0$CarStorageMonitoringService() {
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
        }
    }

    private Optional<WearInformation> loadWearInformation() {
        for (WearInformationProvider provider : this.mWearInformationProviders) {
            WearInformation wearInfo = provider.load();
            if (wearInfo != null) {
                Log.d("CAR.STORAGE", "retrieved wear info " + wearInfo + " via provider " + provider);
                return Optional.of(wearInfo);
            }
        }
        Log.d("CAR.STORAGE", "no wear info available");
        return Optional.empty();
    }

    private WearHistory loadWearHistory() {
        if (this.mWearInfoFile.exists()) {
            try {
                WearHistory wearHistory = WearHistory.fromJson(this.mWearInfoFile);
                Log.d("CAR.STORAGE", "retrieved wear history " + wearHistory);
                return wearHistory;
            } catch (IOException | JSONException e) {
                Log.e("CAR.STORAGE", "unable to read wear info file " + this.mWearInfoFile, e);
            }
        }
        Log.d("CAR.STORAGE", "no wear history available");
        return new WearHistory();
    }

    private boolean addEventIfNeededLocked(WearHistory wearHistory) {
        WearEstimate lastWearEstimate;
        if (!this.mWearInformation.isPresent()) {
            return false;
        }
        WearInformation wearInformation = this.mWearInformation.get();
        WearEstimate currentWearEstimate = wearInformation.toWearEstimate();
        if (wearHistory.size() == 0) {
            lastWearEstimate = WearEstimate.UNKNOWN_ESTIMATE;
        } else {
            lastWearEstimate = wearHistory.getLast().getNewWearEstimate();
        }
        if (currentWearEstimate.equals(lastWearEstimate)) {
            return false;
        }
        WearEstimateRecord newRecord = new WearEstimateRecord(lastWearEstimate, currentWearEstimate, this.mUptimeTracker.getTotalUptime(), Instant.now());
        Log.d("CAR.STORAGE", "new wear record generated " + newRecord);
        wearHistory.add(newRecord);
        return true;
    }

    private void storeWearHistory(WearHistory wearHistory) {
        try {
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(this.mWearInfoFile));
            try {
                wearHistory.writeToJson(jsonWriter);
                jsonWriter.close();
            } finally {
            }
        } catch (IOException e) {
            Log.e("CAR.STORAGE", "unable to write wear info file" + this.mWearInfoFile, e);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        Log.d("CAR.STORAGE", "CarStorageMonitoringService init()");
        synchronized (this.mLock) {
            this.mUptimeTracker = new UptimeTracker(this.mUptimeTrackerFile, this.mConfiguration.uptimeIntervalBetweenUptimeDataWriteMs, this.mSystemInterface);
        }
    }

    private void launchWearChangeActivity() {
        String activityPath = this.mConfiguration.activityHandlerForFlashWearChanges;
        if (activityPath.isEmpty()) {
            return;
        }
        try {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(activityPath);
            Objects.requireNonNull(componentNameUnflattenFromString);
            ComponentName activityComponent = componentNameUnflattenFromString;
            Intent intent = new Intent();
            intent.setComponent(activityComponent);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException | NullPointerException e) {
            Log.e("CAR.STORAGE", "value of activityHandlerForFlashWearChanges invalid non-empty string " + activityPath, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logOnAdverseWearLevel(WearInformation wearInformation) {
        if (wearInformation.preEolInfo > 1 || Math.max(wearInformation.lifetimeEstimateA, wearInformation.lifetimeEstimateB) >= 80) {
            Log.w("CAR.STORAGE", "flash storage reached wear a level that requires attention: " + wearInformation);
        }
    }

    private SparseArray<UidIoRecord> loadNewIoStats() {
        SparseArray<UidIoRecord> ioRecords = this.mUidIoStatsProvider.load();
        return ioRecords == null ? new SparseArray<>() : ioRecords;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectNewIoMetrics() {
        IoStats ioStats;
        boolean needsExcessiveIoBroadcast;
        synchronized (this.mLock) {
            this.mIoStatsTracker.update(loadNewIoStats());
            SparseArray<IoStatsEntry> currentSample = this.mIoStatsTracker.getCurrentSample();
            ioStats = new IoStats((List) SparseArrayStream.valueStream(currentSample).collect(Collectors.toList()), this.mSystemInterface.getUptime());
            this.mIoStatsSamples.add(ioStats);
            needsExcessiveIoBroadcast = needsExcessiveIoBroadcastLocked();
        }
        dispatchNewIoEvent(ioStats);
        if (needsExcessiveIoBroadcast) {
            Log.d("CAR.STORAGE", "about to send android.car.storagemonitoring.EXCESSIVE_IO");
            sendExcessiveIoBroadcast();
        }
    }

    private void sendExcessiveIoBroadcast() {
        Log.w("CAR.STORAGE", "sending android.car.storagemonitoring.EXCESSIVE_IO");
        String receiverPath = this.mConfiguration.intentReceiverForUnacceptableIoMetrics;
        if (receiverPath.isEmpty()) {
            return;
        }
        try {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(receiverPath);
            Objects.requireNonNull(componentNameUnflattenFromString);
            ComponentName receiverComponent = componentNameUnflattenFromString;
            Intent intent = new Intent(INTENT_EXCESSIVE_IO);
            intent.setComponent(receiverComponent);
            intent.addFlags(268435456);
            this.mContext.sendBroadcast(intent, this.mStorageMonitoringPermission.toString());
        } catch (NullPointerException e) {
            Log.e("CAR.STORAGE", "value of intentReceiverForUnacceptableIoMetrics non-null but invalid:" + receiverPath, e);
        }
    }

    private boolean needsExcessiveIoBroadcastLocked() {
        return this.mIoStatsSamples.count(new Predicate() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$oYi0LAZoz3XnYK6IA8wVJ28vQgs
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$needsExcessiveIoBroadcastLocked$2$CarStorageMonitoringService((IoStats) obj);
            }
        }) > this.mConfiguration.maxExcessiveIoSamplesInWindow;
    }

    public /* synthetic */ boolean lambda$needsExcessiveIoBroadcastLocked$2$CarStorageMonitoringService(IoStats delta) {
        IoStatsEntry.Metrics total = delta.getTotals();
        boolean tooManyBytesWritten = total.bytesWrittenToStorage > this.mConfiguration.acceptableBytesWrittenPerSample;
        boolean tooManyFsyncCalls = total.fsyncCalls > ((long) this.mConfiguration.acceptableFsyncCallsPerSample);
        return tooManyBytesWritten || tooManyFsyncCalls;
    }

    private void dispatchNewIoEvent(final IoStats delta) {
        int listenersCount = this.mListeners.beginBroadcast();
        IntStream.range(0, listenersCount).forEach(new IntConsumer() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$vyacTkgK1DoyER-rF23J3hiShTk
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                this.f$0.lambda$dispatchNewIoEvent$3$CarStorageMonitoringService(delta, i);
            }
        });
        this.mListeners.finishBroadcast();
    }

    public /* synthetic */ void lambda$dispatchNewIoEvent$3$CarStorageMonitoringService(IoStats delta, int i) {
        try {
            this.mListeners.getBroadcastItem(i).onSnapshot(delta);
        } catch (RemoteException e) {
            Log.w("CAR.STORAGE", "failed to dispatch snapshot", e);
        }
    }

    private void doInitServiceIfNeededLocked() {
        if (this.mInitialized) {
            return;
        }
        Log.d("CAR.STORAGE", "initializing CarStorageMonitoringService");
        this.mWearInformation = loadWearInformation();
        WearHistory wearHistory = loadWearHistory();
        boolean didWearChangeHappen = addEventIfNeededLocked(wearHistory);
        if (didWearChangeHappen) {
            storeWearHistory(wearHistory);
        }
        Log.d("CAR.STORAGE", "wear history being tracked is " + wearHistory);
        this.mWearEstimateChanges = wearHistory.toWearEstimateChanges((long) this.mConfiguration.acceptableHoursPerOnePercentFlashWear);
        this.mOnShutdownReboot.addAction(new BiConsumer() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$iW_q4OhPqMk1TpQqVNc_KpQimRg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$doInitServiceIfNeededLocked$4$CarStorageMonitoringService((Context) obj, (Intent) obj2);
            }
        }).addAction(new BiConsumer() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$EJbN-Jw3SM_sLqMEo0na8GHh0IU
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$doInitServiceIfNeededLocked$5$CarStorageMonitoringService((Context) obj, (Intent) obj2);
            }
        });
        this.mWearInformation.ifPresent(new Consumer() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$qW99OHY0SHOxhCevR7AsJS-Qwkc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CarStorageMonitoringService.logOnAdverseWearLevel((WearInformation) obj);
            }
        });
        if (didWearChangeHappen) {
            launchWearChangeActivity();
        }
        final long bootUptime = this.mSystemInterface.getUptime();
        List<IoStatsEntry> list = (List) SparseArrayStream.valueStream(loadNewIoStats()).map(new Function() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$ATkoHdvCU9rZQibLuUUdxwEm33c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CarStorageMonitoringService.lambda$doInitServiceIfNeededLocked$6(bootUptime, (UidIoRecord) obj);
            }
        }).collect(Collectors.toList());
        this.mBootIoStats = list;
        this.mIoStatsTracker = new IoStatsTracker(list, this.mConfiguration.ioStatsRefreshRateMs, this.mSystemInterface.getSystemStateInterface());
        if (this.mConfiguration.ioStatsNumSamplesToStore <= 0) {
            Log.i("CAR.STORAGE", "service configuration disabled I/O sample window. not collecting samples");
        } else {
            this.mSystemInterface.scheduleAction(new Runnable() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$xKTnl5Ai9zjrmyRyKQQQttzcWnE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.collectNewIoMetrics();
                }
            }, this.mConfiguration.ioStatsRefreshRateMs);
        }
        this.mShutdownCostInfo = computeShutdownCostLocked();
        Log.d("CAR.STORAGE", "calculated data written in last shutdown was " + this.mShutdownCostInfo + " bytes");
        this.mLifetimeWriteFile.delete();
        Log.i("CAR.STORAGE", "CarStorageMonitoringService is up");
        this.mInitialized = true;
    }

    public /* synthetic */ void lambda$doInitServiceIfNeededLocked$4$CarStorageMonitoringService(Context c, Intent i) {
        logLifetimeWrites();
    }

    public /* synthetic */ void lambda$doInitServiceIfNeededLocked$5$CarStorageMonitoringService(Context c, Intent i) {
        release();
    }

    static /* synthetic */ IoStatsEntry lambda$doInitServiceIfNeededLocked$6(long bootUptime, UidIoRecord record) {
        IoStatsEntry stats = new IoStatsEntry(record, bootUptime);
        return stats;
    }

    private long computeShutdownCostLocked() {
        List<LifetimeWriteInfo> shutdownWrites = loadLifetimeWrites();
        if (shutdownWrites.isEmpty()) {
            Log.d("CAR.STORAGE", "lifetime write data from last shutdown missing");
            this.mShutdownCostMissingReason = "no historical writes stored at last shutdown";
            return -1L;
        }
        List<LifetimeWriteInfo> currentWrites = Arrays.asList(this.mSystemInterface.getLifetimeWriteInfoProvider().load());
        if (currentWrites.isEmpty()) {
            Log.d("CAR.STORAGE", "current lifetime write data missing");
            this.mShutdownCostMissingReason = "current write data cannot be obtained";
            return -1L;
        }
        long shutdownCost = 0;
        final Map<String, Long> shutdownLifetimeWrites = new HashMap<>();
        shutdownWrites.forEach(new Consumer() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$fnhNpUpJtX-VQEy-1-_I9l9tsf0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                LifetimeWriteInfo lifetimeWriteInfo = (LifetimeWriteInfo) obj;
                shutdownLifetimeWrites.put(lifetimeWriteInfo.partition, Long.valueOf(lifetimeWriteInfo.writtenBytes));
            }
        });
        for (int i = 0; i < currentWrites.size(); i++) {
            LifetimeWriteInfo li = currentWrites.get(i);
            long writtenAtShutdown = shutdownLifetimeWrites.getOrDefault(li.partition, Long.valueOf(li.writtenBytes)).longValue();
            long costDelta = li.writtenBytes - writtenAtShutdown;
            if (costDelta < 0) {
                this.mShutdownCostMissingReason = li.partition + " has a negative write amount (" + costDelta + " bytes)";
                Log.e("CAR.STORAGE", "partition " + li.partition + " reported " + costDelta + " bytes written to it during shutdown. assuming we can't determine proper shutdown information.");
                return -1L;
            }
            Log.d("CAR.STORAGE", "partition " + li.partition + " had " + costDelta + " bytes written to it during shutdown");
            shutdownCost += costDelta;
        }
        return shutdownCost;
    }

    private List<LifetimeWriteInfo> loadLifetimeWrites() {
        if (!this.mLifetimeWriteFile.exists() || !this.mLifetimeWriteFile.isFile()) {
            Log.d("CAR.STORAGE", "lifetime write file missing or inaccessible " + this.mLifetimeWriteFile);
            return Collections.emptyList();
        }
        try {
            JSONObject jsonObject = new JSONObject(new String(Files.readAllBytes(this.mLifetimeWriteFile.toPath())));
            JSONArray jsonArray = jsonObject.getJSONArray("lifetimeWriteInfo");
            List<LifetimeWriteInfo> result = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(new LifetimeWriteInfo(jsonArray.getJSONObject(i)));
            }
            return result;
        } catch (IOException | JSONException e) {
            Log.e("CAR.STORAGE", "lifetime write file does not contain valid JSON", e);
            return Collections.emptyList();
        }
    }

    private void logLifetimeWrites() {
        try {
            LifetimeWriteInfo[] lifetimeWriteInfos = this.mSystemInterface.getLifetimeWriteInfoProvider().load();
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(this.mLifetimeWriteFile));
            jsonWriter.beginObject();
            jsonWriter.name("lifetimeWriteInfo").beginArray();
            for (LifetimeWriteInfo writeInfo : lifetimeWriteInfos) {
                Log.d("CAR.STORAGE", "storing lifetime write info " + writeInfo);
                writeInfo.writeToJson(jsonWriter);
            }
            jsonWriter.endArray().endObject();
            jsonWriter.close();
        } catch (IOException e) {
            Log.e("CAR.STORAGE", "unable to save lifetime write info on shutdown", e);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        Log.i("CAR.STORAGE", "tearing down CarStorageMonitoringService");
        synchronized (this.mLock) {
            if (this.mUptimeTracker != null) {
                this.mUptimeTracker.onDestroy();
            }
        }
        this.mOnShutdownReboot.clearActions();
        this.mListeners.kill();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarStorageMonitoringService*");
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            writer.println("last wear information retrieved: " + ((String) this.mWearInformation.map(new Function() { // from class: com.android.car.-$$Lambda$uSYrTzekzeTrLj_eWJwZ43_K-gU
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((WearInformation) obj).toString();
                }
            }).orElse("missing")));
            writer.println("wear change history: " + ((String) this.mWearEstimateChanges.stream().map(new Function() { // from class: com.android.car.-$$Lambda$E1ddjIIK9NNOT-pZoeEXNskE8rU
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((WearEstimateChange) obj).toString();
                }
            }).collect(Collectors.joining("\n"))));
            writer.println("boot I/O stats: " + ((String) this.mBootIoStats.stream().map($$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0.INSTANCE).collect(Collectors.joining("\n"))));
            writer.println("aggregate I/O stats: " + ((String) SparseArrayStream.valueStream(this.mIoStatsTracker.getTotal()).map($$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0.INSTANCE).collect(Collectors.joining("\n"))));
            writer.println("I/O stats snapshots: ");
            writer.println((String) this.mIoStatsSamples.stream().map(new Function() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$OXyT_c8N_atm9xszyqeXeYuuak4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CarStorageMonitoringService.lambda$dump$8((IoStats) obj);
                }
            }).collect(Collectors.joining("\n------\n")));
            if (this.mShutdownCostInfo < 0) {
                writer.print("last shutdown cost: missing. ");
                if (this.mShutdownCostMissingReason != null && !this.mShutdownCostMissingReason.isEmpty()) {
                    writer.println("reason: " + this.mShutdownCostMissingReason);
                }
            } else {
                writer.println("last shutdown cost: " + this.mShutdownCostInfo + " bytes, estimated");
            }
        }
    }

    static /* synthetic */ String lambda$dump$8(IoStats sample) {
        return (String) sample.getStats().stream().map($$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0.INSTANCE).collect(Collectors.joining("\n"));
    }

    public int getPreEolIndicatorStatus() {
        int iIntValue;
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            iIntValue = ((Integer) this.mWearInformation.map(new Function() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$SgnF2lptvYsLsw7v6kFQv48n9pc
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((WearInformation) obj).preEolInfo);
                }
            }).orElse(0)).intValue();
        }
        return iIntValue;
    }

    public WearEstimate getWearEstimate() {
        WearEstimate wearEstimate;
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            wearEstimate = (WearEstimate) this.mWearInformation.map(new Function() { // from class: com.android.car.-$$Lambda$CarStorageMonitoringService$wI9y5ZmBpqzKI0rsel98Jn77EqM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CarStorageMonitoringService.lambda$getWearEstimate$10((WearInformation) obj);
                }
            }).orElse(WearEstimate.UNKNOWN_ESTIMATE);
        }
        return wearEstimate;
    }

    static /* synthetic */ WearEstimate lambda$getWearEstimate$10(WearInformation wi) {
        return new WearEstimate(wi.lifetimeEstimateA, wi.lifetimeEstimateB);
    }

    public List<WearEstimateChange> getWearEstimateHistory() {
        List<WearEstimateChange> listUnmodifiableList;
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            listUnmodifiableList = Collections.unmodifiableList(this.mWearEstimateChanges);
        }
        return listUnmodifiableList;
    }

    public List<IoStatsEntry> getBootIoStats() {
        this.mStorageMonitoringPermission.assertGranted();
        doInitServiceIfNeededLocked();
        return Collections.unmodifiableList(this.mBootIoStats);
    }

    public List<IoStatsEntry> getAggregateIoStats() {
        List<IoStatsEntry> listUnmodifiableList;
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            listUnmodifiableList = Collections.unmodifiableList((List) SparseArrayStream.valueStream(this.mIoStatsTracker.getTotal()).collect(Collectors.toList()));
        }
        return listUnmodifiableList;
    }

    public long getShutdownDiskWriteAmount() {
        long j;
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            j = this.mShutdownCostInfo;
        }
        return j;
    }

    public List<IoStats> getIoStatsDeltas() {
        List<IoStats> listUnmodifiableList;
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
            listUnmodifiableList = Collections.unmodifiableList((List) this.mIoStatsSamples.stream().collect(Collectors.toList()));
        }
        return listUnmodifiableList;
    }

    public void registerListener(IIoStatsListener listener) {
        this.mStorageMonitoringPermission.assertGranted();
        synchronized (this.mLock) {
            doInitServiceIfNeededLocked();
        }
        this.mListeners.register(listener);
    }

    public void unregisterListener(IIoStatsListener listener) {
        this.mStorageMonitoringPermission.assertGranted();
        this.mListeners.unregister(listener);
    }

    private static final class Configuration {
        final long acceptableBytesWrittenPerSample;
        final int acceptableFsyncCallsPerSample;
        final int acceptableHoursPerOnePercentFlashWear;
        final String activityHandlerForFlashWearChanges;
        final String intentReceiverForUnacceptableIoMetrics;
        final int ioStatsNumSamplesToStore;
        final int ioStatsRefreshRateMs;
        final int maxExcessiveIoSamplesInWindow;
        final long uptimeIntervalBetweenUptimeDataWriteMs;

        Configuration(Resources resources) throws Resources.NotFoundException {
            this.ioStatsNumSamplesToStore = resources.getInteger(R.integer.ioStatsNumSamplesToStore);
            this.acceptableBytesWrittenPerSample = resources.getInteger(R.integer.acceptableWrittenKBytesPerSample) * 1024;
            this.acceptableFsyncCallsPerSample = resources.getInteger(R.integer.acceptableFsyncCallsPerSample);
            this.maxExcessiveIoSamplesInWindow = resources.getInteger(R.integer.maxExcessiveIoSamplesInWindow);
            this.uptimeIntervalBetweenUptimeDataWriteMs = resources.getInteger(R.integer.uptimeHoursIntervalBetweenUptimeDataWrite) * 3600000;
            this.acceptableHoursPerOnePercentFlashWear = resources.getInteger(R.integer.acceptableHoursPerOnePercentFlashWear);
            this.ioStatsRefreshRateMs = resources.getInteger(R.integer.ioStatsRefreshRateSeconds) * 1000;
            this.activityHandlerForFlashWearChanges = resources.getString(R.string.activityHandlerForFlashWearChanges);
            this.intentReceiverForUnacceptableIoMetrics = resources.getString(R.string.intentReceiverForUnacceptableIoMetrics);
        }

        public String toString() {
            return String.format("acceptableBytesWrittenPerSample = %d, acceptableFsyncCallsPerSample = %d, acceptableHoursPerOnePercentFlashWear = %d, activityHandlerForFlashWearChanges = %s, intentReceiverForUnacceptableIoMetrics = %s, ioStatsNumSamplesToStore = %d, ioStatsRefreshRateMs = %d, maxExcessiveIoSamplesInWindow = %d, uptimeIntervalBetweenUptimeDataWriteMs = %d", Long.valueOf(this.acceptableBytesWrittenPerSample), Integer.valueOf(this.acceptableFsyncCallsPerSample), Integer.valueOf(this.acceptableHoursPerOnePercentFlashWear), this.activityHandlerForFlashWearChanges, this.intentReceiverForUnacceptableIoMetrics, Integer.valueOf(this.ioStatsNumSamplesToStore), Integer.valueOf(this.ioStatsRefreshRateMs), Integer.valueOf(this.maxExcessiveIoSamplesInWindow), Long.valueOf(this.uptimeIntervalBetweenUptimeDataWriteMs));
        }
    }
}
