package com.bugsnag.android;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.util.DisplayMetrics;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.TaskType;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: DeviceDataCollector.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010D\u001a\u00020C\u0012\u0006\u0010G\u001a\u00020F\u0012\u0006\u0010X\u001a\u00020W\u0012\b\u0010I\u001a\u0004\u0018\u00010\t\u0012\b\u0010J\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010L\u001a\u00020K\u0012\u0006\u0010O\u001a\u00020N\u0012\u0006\u0010Z\u001a\u00020Y\u0012\u0006\u0010R\u001a\u00020Q\u0012\u0006\u0010U\u001a\u00020T¢\u0006\u0004\b[\u0010\\J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\f\u001a\u00020\u000b2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0002J\n\u0010\r\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u000e\u001a\u00020\u0002H\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002J\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\n\u0010\u0013\u001a\u0004\u0018\u00010\tH\u0002J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u0017H\u0002J\u0011\u0010\u0019\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0004\b\u0019\u0010\u0016J\u0006\u0010\u001b\u001a\u00020\u001aJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u0014J\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001fJ\u0013\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0!¢\u0006\u0004\b\"\u0010#J\b\u0010$\u001a\u00020\u0014H\u0007J\u0011\u0010'\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\b%\u0010&J\u0017\u0010+\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0005H\u0000¢\u0006\u0004\b)\u0010*R\u001c\u0010.\u001a\n -*\u0004\u0018\u00010,0,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00107R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020\t0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\"\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u001c\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u001e\u0010?\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010>R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010D\u001a\u00020C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u00107R\u0016\u0010J\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u00107R\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010U\u001a\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010V¨\u0006]"}, d2 = {"Lcom/bugsnag/android/DeviceDataCollector;", "", "", "checkIsRooted", "isEmulator", "", "getScreenDensityDpi", "()Ljava/lang/Integer;", "", "", "into", "", "populateBatteryInfo", "getLocationStatus", "isLocationEnabled", "getNetworkAccess", "", "getScreenDensity", "()Ljava/lang/Float;", "getScreenResolution", "", "calculateFreeMemory", "()Ljava/lang/Long;", "Ljava/util/concurrent/Future;", "retrieveTotalDeviceMemory", "calculateTotalMemory", "Lcom/bugsnag/android/Device;", "generateDevice", "now", "Lcom/bugsnag/android/DeviceWithState;", "generateDeviceWithState", "", "getDeviceMetadata", "", "getCpuAbi", "()[Ljava/lang/String;", "calculateFreeDisk", "getOrientationAsString$bugsnag_android_core_release", "()Ljava/lang/String;", "getOrientationAsString", "newOrientation", "updateOrientation$bugsnag_android_core_release", "(I)Z", "updateOrientation", "Landroid/util/DisplayMetrics;", "kotlin.jvm.PlatformType", "displayMetrics", "Landroid/util/DisplayMetrics;", "emulator", "Z", "screenDensity", "Ljava/lang/Float;", "dpi", "Ljava/lang/Integer;", "screenResolution", "Ljava/lang/String;", Splash.PARAMS_LOCALE, "cpuAbi", "[Ljava/lang/String;", "runtimeVersions", "Ljava/util/Map;", "rootedFuture", "Ljava/util/concurrent/Future;", "totalMemoryFuture", "Ljava/util/concurrent/atomic/AtomicInteger;", "orientation", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lcom/bugsnag/android/Connectivity;", "connectivity", "Lcom/bugsnag/android/Connectivity;", "Landroid/content/Context;", "appContext", "Landroid/content/Context;", Splash.PARAMS_DEVICE_ID, "internalDeviceId", "Lcom/bugsnag/android/DeviceBuildInfo;", "buildInfo", "Lcom/bugsnag/android/DeviceBuildInfo;", "Ljava/io/File;", "dataDirectory", "Ljava/io/File;", "Lcom/bugsnag/android/internal/BackgroundTaskService;", "bgTaskService", "Lcom/bugsnag/android/internal/BackgroundTaskService;", "Lcom/bugsnag/android/Logger;", "logger", "Lcom/bugsnag/android/Logger;", "Landroid/content/res/Resources;", "resources", "Lcom/bugsnag/android/RootDetector;", "rootDetector", "<init>", "(Lcom/bugsnag/android/Connectivity;Landroid/content/Context;Landroid/content/res/Resources;Ljava/lang/String;Ljava/lang/String;Lcom/bugsnag/android/DeviceBuildInfo;Ljava/io/File;Lcom/bugsnag/android/RootDetector;Lcom/bugsnag/android/internal/BackgroundTaskService;Lcom/bugsnag/android/Logger;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class DeviceDataCollector {
    private final Context appContext;
    private final BackgroundTaskService bgTaskService;
    private final DeviceBuildInfo buildInfo;
    private final Connectivity connectivity;
    private final String[] cpuAbi;
    private final File dataDirectory;
    private final String deviceId;
    private final DisplayMetrics displayMetrics;
    private final String internalDeviceId;
    private final String locale;
    private final Logger logger;
    private AtomicInteger orientation;
    private final Future<Boolean> rootedFuture;
    private Map<String, Object> runtimeVersions;
    private final Future<Long> totalMemoryFuture;
    private final boolean emulator = isEmulator();
    private final Float screenDensity = getScreenDensity();
    private final Integer dpi = getScreenDensityDpi();
    private final String screenResolution = getScreenResolution();

    public DeviceDataCollector(Connectivity connectivity, Context context, Resources resources, String str, String str2, DeviceBuildInfo deviceBuildInfo, File file, final RootDetector rootDetector, BackgroundTaskService backgroundTaskService, Logger logger) {
        Future<Boolean> futureSubmitTask;
        this.connectivity = connectivity;
        this.appContext = context;
        this.deviceId = str;
        this.internalDeviceId = str2;
        this.buildInfo = deviceBuildInfo;
        this.dataDirectory = file;
        this.bgTaskService = backgroundTaskService;
        this.logger = logger;
        this.displayMetrics = resources.getDisplayMetrics();
        String string = Locale.getDefault().toString();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(string, "Locale.getDefault().toString()");
        this.locale = string;
        this.cpuAbi = getCpuAbi();
        this.totalMemoryFuture = retrieveTotalDeviceMemory();
        this.orientation = new AtomicInteger(resources.getConfiguration().orientation);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Integer apiLevel = deviceBuildInfo.getApiLevel();
        if (apiLevel != null) {
            linkedHashMap.put("androidApiLevel", Integer.valueOf(apiLevel.intValue()));
        }
        String osBuild = deviceBuildInfo.getOsBuild();
        if (osBuild != null) {
            linkedHashMap.put("osBuild", osBuild);
        }
        this.runtimeVersions = linkedHashMap;
        try {
            futureSubmitTask = backgroundTaskService.submitTask(TaskType.IO, new Callable<Boolean>() { // from class: com.bugsnag.android.DeviceDataCollector.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public final Boolean call() {
                    return Boolean.valueOf(rootDetector.isRooted());
                }
            });
        } catch (RejectedExecutionException e) {
            this.logger.w("Failed to perform root detection checks", e);
            futureSubmitTask = null;
        }
        this.rootedFuture = futureSubmitTask;
    }

    public final Device generateDevice() {
        Object objM2810constructorimpl;
        DeviceBuildInfo deviceBuildInfo = this.buildInfo;
        String[] strArr = this.cpuAbi;
        Boolean boolValueOf = Boolean.valueOf(checkIsRooted());
        String str = this.deviceId;
        String str2 = this.locale;
        Future<Long> future = this.totalMemoryFuture;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(future != null ? future.get() : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        return new Device(deviceBuildInfo, strArr, boolValueOf, str, str2, (Long) (Result.m2816isFailureimpl(objM2810constructorimpl) ? null : objM2810constructorimpl), MapsKt__MapsKt.toMutableMap(this.runtimeVersions));
    }

    public final DeviceWithState generateDeviceWithState(long now) {
        Object objM2810constructorimpl;
        DeviceBuildInfo deviceBuildInfo = this.buildInfo;
        Boolean boolValueOf = Boolean.valueOf(checkIsRooted());
        String str = this.deviceId;
        String str2 = this.locale;
        Future<Long> future = this.totalMemoryFuture;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(future != null ? future.get() : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        return new DeviceWithState(deviceBuildInfo, boolValueOf, str, str2, (Long) (Result.m2816isFailureimpl(objM2810constructorimpl) ? null : objM2810constructorimpl), MapsKt__MapsKt.toMutableMap(this.runtimeVersions), Long.valueOf(calculateFreeDisk()), calculateFreeMemory(), getOrientationAsString$bugsnag_android_core_release(), new Date(now));
    }

    public final Map<String, Object> getDeviceMetadata() {
        HashMap map = new HashMap();
        populateBatteryInfo(map);
        map.put("locationStatus", getLocationStatus());
        map.put("networkAccess", getNetworkAccess());
        map.put("brand", this.buildInfo.getBrand());
        map.put("screenDensity", this.screenDensity);
        map.put("dpi", this.dpi);
        map.put("emulator", Boolean.valueOf(this.emulator));
        map.put("screenResolution", this.screenResolution);
        return map;
    }

    private final boolean checkIsRooted() {
        try {
            Future<Boolean> future = this.rootedFuture;
            if (future == null) {
                return false;
            }
            Boolean bool = future.get();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(bool, "rootedFuture.get()");
            return bool.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private final boolean isEmulator() {
        String fingerprint = this.buildInfo.getFingerprint();
        if (fingerprint != null) {
            return StringsKt__StringsJVMKt.startsWith$default(fingerprint, EnvironmentCompat.MEDIA_UNKNOWN, false, 2, null) || StringsKt__StringsKt.contains$default((CharSequence) fingerprint, (CharSequence) "generic", false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) fingerprint, (CharSequence) "vbox", false, 2, (Object) null);
        }
        return false;
    }

    private final Integer getScreenDensityDpi() {
        DisplayMetrics displayMetrics = this.displayMetrics;
        if (displayMetrics != null) {
            return Integer.valueOf(displayMetrics.densityDpi);
        }
        return null;
    }

    private final void populateBatteryInfo(Map<String, Object> into) {
        try {
            Intent intentRegisterReceiverSafe = ContextExtensionsKt.registerReceiverSafe(this.appContext, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"), this.logger);
            if (intentRegisterReceiverSafe != null) {
                int intExtra = intentRegisterReceiverSafe.getIntExtra("level", -1);
                int intExtra2 = intentRegisterReceiverSafe.getIntExtra("scale", -1);
                if (intExtra != -1 || intExtra2 != -1) {
                    into.put("batteryLevel", Float.valueOf(intExtra / intExtra2));
                }
                int intExtra3 = intentRegisterReceiverSafe.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
                into.put("charging", Boolean.valueOf(intExtra3 == 2 || intExtra3 == 5));
            }
        } catch (Exception unused) {
            this.logger.w("Could not get battery status");
        }
    }

    private final String getLocationStatus() {
        try {
            return isLocationEnabled() ? "allowed" : "disallowed";
        } catch (Exception unused) {
            this.logger.w("Could not get locationStatus");
            return null;
        }
    }

    private final boolean isLocationEnabled() {
        if (Build.VERSION.SDK_INT >= 31) {
            LocationManager locationManager = ContextExtensionsKt.getLocationManager(this.appContext);
            if (locationManager == null || !locationManager.isLocationEnabled()) {
                return false;
            }
        } else {
            String string = Settings.Secure.getString(this.appContext.getContentResolver(), "location_providers_allowed");
            if (string == null) {
                return false;
            }
            if (!(string.length() > 0)) {
                return false;
            }
        }
        return true;
    }

    private final String getNetworkAccess() {
        return this.connectivity.retrieveNetworkAccessState();
    }

    private final Float getScreenDensity() {
        DisplayMetrics displayMetrics = this.displayMetrics;
        if (displayMetrics != null) {
            return Float.valueOf(displayMetrics.density);
        }
        return null;
    }

    private final String getScreenResolution() {
        DisplayMetrics displayMetrics = this.displayMetrics;
        if (displayMetrics == null) {
            return null;
        }
        int iMax = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        DisplayMetrics displayMetrics2 = this.displayMetrics;
        int iMin = Math.min(displayMetrics2.widthPixels, displayMetrics2.heightPixels);
        StringBuilder sb = new StringBuilder();
        sb.append(iMax);
        sb.append('x');
        sb.append(iMin);
        return sb.toString();
    }

    public final String[] getCpuAbi() {
        String[] cpuAbis = this.buildInfo.getCpuAbis();
        return cpuAbis != null ? cpuAbis : new String[0];
    }

    @SuppressLint({"UsableSpace"})
    public final long calculateFreeDisk() {
        Object objM2810constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl((Long) this.bgTaskService.submitTask(TaskType.IO, new Callable<Long>() { // from class: com.bugsnag.android.DeviceDataCollector$calculateFreeDisk$1$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public final Long call() {
                    return Long.valueOf(this.$this_runCatching.dataDirectory.getUsableSpace());
                }
            }).get());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2816isFailureimpl(objM2810constructorimpl)) {
            objM2810constructorimpl = 0L;
        }
        return ((Number) objM2810constructorimpl).longValue();
    }

    private final Long calculateFreeMemory() {
        Long lValueOf;
        Object objM2810constructorimpl;
        ActivityManager activityManagerFrom = ContextExtensionsKt.getActivityManagerFrom(this.appContext);
        if (activityManagerFrom != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManagerFrom.getMemoryInfo(memoryInfo);
            lValueOf = Long.valueOf(memoryInfo.availMem);
        } else {
            lValueOf = null;
        }
        if (lValueOf != null) {
            return lValueOf;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl((Long) Process.class.getDeclaredMethod("getFreeMemory", new Class[0]).invoke(null, new Object[0]));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        return (Long) (Result.m2816isFailureimpl(objM2810constructorimpl) ? null : objM2810constructorimpl);
    }

    private final Future<Long> retrieveTotalDeviceMemory() {
        try {
            return this.bgTaskService.submitTask(TaskType.DEFAULT, new Callable<Long>() { // from class: com.bugsnag.android.DeviceDataCollector.retrieveTotalDeviceMemory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public final Long call() {
                    return DeviceDataCollector.this.calculateTotalMemory();
                }
            });
        } catch (RejectedExecutionException e) {
            this.logger.w("Failed to lookup available device memory", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Long calculateTotalMemory() {
        Long lValueOf;
        Object objM2810constructorimpl;
        ActivityManager activityManagerFrom = ContextExtensionsKt.getActivityManagerFrom(this.appContext);
        if (activityManagerFrom != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManagerFrom.getMemoryInfo(memoryInfo);
            lValueOf = Long.valueOf(memoryInfo.totalMem);
        } else {
            lValueOf = null;
        }
        if (lValueOf != null) {
            return lValueOf;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl((Long) Process.class.getDeclaredMethod("getTotalMemory", new Class[0]).invoke(null, new Object[0]));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        return (Long) (Result.m2816isFailureimpl(objM2810constructorimpl) ? null : objM2810constructorimpl);
    }

    public final String getOrientationAsString$bugsnag_android_core_release() {
        int i = this.orientation.get();
        if (i == 1) {
            return "portrait";
        }
        if (i != 2) {
            return null;
        }
        return "landscape";
    }

    public final boolean updateOrientation$bugsnag_android_core_release(int newOrientation) {
        return this.orientation.getAndSet(newOrientation) != newOrientation;
    }
}
