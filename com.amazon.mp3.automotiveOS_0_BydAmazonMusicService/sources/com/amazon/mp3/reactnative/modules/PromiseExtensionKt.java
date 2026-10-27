package com.amazon.mp3.reactnative.modules;

import com.amazon.mp3.util.Log;
import com.facebook.react.bridge.Promise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PromiseExtension.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u0012\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0001¨\u0006\t"}, d2 = {"Lcom/facebook/react/bridge/Promise;", "", "providerName", "", "rejectWithProviderNotFound", "", "value", "methodCall", "resolveOrRejectWhenNull", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class PromiseExtensionKt {
    public static final void rejectWithProviderNotFound(Promise promise, String providerName) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        Intrinsics.checkNotNullParameter(providerName, "providerName");
        Log.error("ReactNativeBridge", Intrinsics.stringPlus(providerName, " not found"));
        promise.reject("providerNotFound", providerName);
    }

    public static final void resolveOrRejectWhenNull(Promise promise, Object obj, String methodCall) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        Intrinsics.checkNotNullParameter(methodCall, "methodCall");
        if (obj != null) {
            promise.resolve(obj);
        } else {
            Log.warning("ReactNativeBridge", Intrinsics.stringPlus(methodCall, " get null value"));
            promise.reject("notFound", methodCall);
        }
    }
}
