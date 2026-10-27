package com.android.permissioncontroller.permission.data;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.DataRepository.InactiveTimekeeper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DataRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004:\u0001%B\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0015\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00028\u00012\u0006\u0010\u0016\u001a\u00028\u0000H%¢\u0006\u0002\u0010\u0017J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u0007H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b8\u0004X\u0085\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/android/permissioncontroller/permission/data/DataRepository;", "K", "V", "Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "Landroid/content/ComponentCallbacks2;", "()V", "TIME_THRESHOLD_ALL_NANOS", "", "TIME_THRESHOLD_LAX_NANOS", "TIME_THRESHOLD_TIGHT_NANOS", "data", "", "getData", "()Ljava/util/Map;", "isLowMemoryDevice", "", "lock", "", "getLock", "()Ljava/lang/Object;", "registered", "get", "key", "(Ljava/lang/Object;)Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "invalidateSingle", "", "(Ljava/lang/Object;)V", "newValue", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onLowMemory", "onTrimMemory", "level", "", "trimInactiveData", "threshold", "InactiveTimekeeper", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public abstract class DataRepository<K, V extends InactiveTimekeeper> implements ComponentCallbacks2 {
    private final long TIME_THRESHOLD_ALL_NANOS;
    private boolean isLowMemoryDevice;
    private boolean registered;
    private final long TIME_THRESHOLD_LAX_NANOS = TimeUnit.NANOSECONDS.convert(5, TimeUnit.MINUTES);
    private final long TIME_THRESHOLD_TIGHT_NANOS = TimeUnit.NANOSECONDS.convert(1, TimeUnit.MINUTES);
    private final Object lock = new Object();
    private final Map<K, V> data = new LinkedHashMap();

    protected abstract V newValue(K key);

    public DataRepository() {
        ActivityManager activityManager = (ActivityManager) PermissionControllerApplication.get().getSystemService(ActivityManager.class);
        this.isLowMemoryDevice = activityManager != null ? activityManager.isLowRamDevice() : false;
        PermissionControllerApplication.get().registerComponentCallbacks(this);
    }

    protected final Object getLock() {
        return this.lock;
    }

    protected final Map<K, V> getData() {
        return this.data;
    }

    public final V get(K key) {
        V v;
        synchronized (this.lock) {
            Map $this$getOrPut$iv = this.data;
            Object value$iv = $this$getOrPut$iv.get(key);
            if (value$iv == null) {
                InactiveTimekeeper inactiveTimekeeperNewValue = newValue(key);
                $this$getOrPut$iv.put(key, inactiveTimekeeperNewValue);
                value$iv = inactiveTimekeeperNewValue;
            }
            v = (V) value$iv;
        }
        return v;
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        long j;
        if (this.isLowMemoryDevice) {
            trimInactiveData(this.TIME_THRESHOLD_ALL_NANOS);
            return;
        }
        if (level == 5) {
            j = this.TIME_THRESHOLD_LAX_NANOS;
        } else if (level == 10) {
            j = this.TIME_THRESHOLD_TIGHT_NANOS;
        } else if (level == 15) {
            j = this.TIME_THRESHOLD_ALL_NANOS;
        } else if (level == 40) {
            j = this.TIME_THRESHOLD_LAX_NANOS;
        } else if (level == 60) {
            j = this.TIME_THRESHOLD_TIGHT_NANOS;
        } else if (level == 80) {
            j = this.TIME_THRESHOLD_ALL_NANOS;
        } else {
            return;
        }
        trimInactiveData(j);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        onTrimMemory(80);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkParameterIsNotNull(newConfig, "newConfig");
    }

    public final void invalidateSingle(K key) {
        synchronized (this.lock) {
            this.data.remove(key);
        }
    }

    private final void trimInactiveData(long threshold) {
        Long timeInactive;
        synchronized (this.lock) {
            Iterable $this$forEach$iv = CollectionsKt.toList(this.data.keySet());
            for (Object element$iv : $this$forEach$iv) {
                V v = this.data.get(element$iv);
                if (v != null && (timeInactive = v.getTimeInactive()) != null) {
                    long it = timeInactive.longValue();
                    if (it >= threshold) {
                        this.data.remove(element$iv);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: DataRepository.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0005\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/android/permissioncontroller/permission/data/DataRepository$InactiveTimekeeper;", "", "timeInactive", "", "getTimeInactive", "()Ljava/lang/Long;", "timeWentInactive", "getTimeWentInactive", "setTimeWentInactive", "(Ljava/lang/Long;)V", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public interface InactiveTimekeeper {
        Long getTimeInactive();

        Long getTimeWentInactive();

        void setTimeWentInactive(Long l);

        /* JADX INFO: compiled from: DataRepository.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public static final class DefaultImpls {
            public static Long getTimeInactive(InactiveTimekeeper $this) {
                Long timeWentInactive = $this.getTimeWentInactive();
                if (timeWentInactive == null) {
                    return null;
                }
                long time = timeWentInactive.longValue();
                return Long.valueOf(System.nanoTime() - time);
            }
        }
    }
}
