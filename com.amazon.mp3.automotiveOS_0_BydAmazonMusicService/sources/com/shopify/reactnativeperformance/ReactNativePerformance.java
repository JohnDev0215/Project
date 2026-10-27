package com.shopify.reactnativeperformance;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: ReactNativePerformance.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0007R$\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/shopify/reactnativeperformance/ReactNativePerformance;", "", "()V", "<set-?>", "", "nativeStartupTimestamp", "getNativeStartupTimestamp$shopify_react_native_performance_release", "()Ljava/lang/Long;", "Ljava/lang/Long;", "onAppStarted", "", "shopify_react-native-performance_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReactNativePerformance {
    public static final ReactNativePerformance INSTANCE = new ReactNativePerformance();
    private static Long nativeStartupTimestamp;

    private ReactNativePerformance() {
    }

    public final Long getNativeStartupTimestamp$shopify_react_native_performance_release() {
        return nativeStartupTimestamp;
    }

    @JvmStatic
    public static final void onAppStarted() {
        nativeStartupTimestamp = Long.valueOf(System.currentTimeMillis());
    }
}
