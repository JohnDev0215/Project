package com.amazon.mp3.util;

import android.content.Context;
import com.amazon.mp3.platform.providers.AppConfigProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: CQEThrottleUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u001c\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u001c\u0010\r\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/util/CQEThrottleUtil;", "", "", "threshold", "Lkotlin/Function0;", "", "action", "executeThrottled", "Landroid/content/Context;", "context", "getNextEntitiesThrottle", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
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

    public final void getNextEntitiesThrottle(Context context, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(action, "action");
        int iMo2012int = AppConfigProvider.INSTANCE.getInstance(context).getConfiguration().mo2012int("cqe_get_next_entities_threshold_config", 25);
        Log.debug(TAG, Intrinsics.stringPlus("getNextEntities at threshold: ", Integer.valueOf(iMo2012int)));
        executeThrottled(iMo2012int, action);
    }
}
