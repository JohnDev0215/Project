package com.bugsnag.android;

import android.content.ComponentCallbacks2;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ClientComponentCallbacks.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012:\u0010\u0013\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00040\r\u0012\u001a\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00040\r¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fRH\u0010\u0013\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00040\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R+\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00040\r8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/bugsnag/android/ClientComponentCallbacks;", "Landroid/content/ComponentCallbacks2;", "Landroid/content/res/Configuration;", "newConfig", "", "onConfigurationChanged", "", "level", "onTrimMemory", "onLowMemory", "Lcom/bugsnag/android/DeviceDataCollector;", "deviceDataCollector", "Lcom/bugsnag/android/DeviceDataCollector;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "oldOrientation", "newOrientation", "cb", "Lkotlin/jvm/functions/Function2;", "", "memoryCallback", "getMemoryCallback", "()Lkotlin/jvm/functions/Function2;", "<init>", "(Lcom/bugsnag/android/DeviceDataCollector;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ClientComponentCallbacks implements ComponentCallbacks2 {
    private final Function2<String, String, Unit> cb;
    private final DeviceDataCollector deviceDataCollector;
    private final Function2<Boolean, Integer, Unit> memoryCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public ClientComponentCallbacks(DeviceDataCollector deviceDataCollector, Function2<? super String, ? super String, Unit> function2, Function2<? super Boolean, ? super Integer, Unit> function22) {
        this.deviceDataCollector = deviceDataCollector;
        this.cb = function2;
        this.memoryCallback = function22;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        String orientationAsString$bugsnag_android_core_release = this.deviceDataCollector.getOrientationAsString$bugsnag_android_core_release();
        if (this.deviceDataCollector.updateOrientation$bugsnag_android_core_release(newConfig.orientation)) {
            this.cb.mo4invoke(orientationAsString$bugsnag_android_core_release, this.deviceDataCollector.getOrientationAsString$bugsnag_android_core_release());
        }
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        this.memoryCallback.mo4invoke(Boolean.valueOf(level >= 80), Integer.valueOf(level));
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.memoryCallback.mo4invoke(Boolean.TRUE, null);
    }
}
