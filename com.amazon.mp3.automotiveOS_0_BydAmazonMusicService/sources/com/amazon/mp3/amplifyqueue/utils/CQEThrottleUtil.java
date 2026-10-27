package com.amazon.mp3.amplifyqueue.utils;

import android.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: CQEThrottleUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u0014\u0010\n\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u001e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/CQEThrottleUtil;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "authThrottle", "", "action", "Lkotlin/Function0;", "datastoreThrottle", "executeThrottled", "threshold", "", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CQEThrottleUtil {
    public static final CQEThrottleUtil INSTANCE = new CQEThrottleUtil();
    private static final String TAG = CQEThrottleUtil.class.getSimpleName();

    private CQEThrottleUtil() {
    }

    private final void executeThrottled(int threshold, Function0<Unit> action) {
        if (threshold <= 0 || RangesKt___RangesKt.random(new IntRange(1, 100), Random.INSTANCE) > threshold) {
            return;
        }
        action.invoke();
    }

    public final void datastoreThrottle(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        int cQEThresholdConfig = AmplifyConfigurationUtil.INSTANCE.getCQEThresholdConfig("cqe_datastore_operation_threshold_config");
        Log.d(TAG, Intrinsics.stringPlus("dataStoreOperation at threshold: ", Integer.valueOf(cQEThresholdConfig)));
        executeThrottled(cQEThresholdConfig, action);
    }

    public final void authThrottle(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        int cQEThresholdConfig = AmplifyConfigurationUtil.INSTANCE.getCQEThresholdConfig("cqe_auth_threshold_config");
        Log.d(TAG, Intrinsics.stringPlus("auth at threshold: ", Integer.valueOf(cQEThresholdConfig)));
        executeThrottled(cQEThresholdConfig, action);
    }
}
