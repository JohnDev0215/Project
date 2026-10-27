package io.reactivex.rxjava3.internal.operators.flowable;

import p.tg2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FlowableInternalHelper$RequestMax implements io.reactivex.rxjava3.functions.g {
    public static final FlowableInternalHelper$RequestMax c;
    public static final /* synthetic */ FlowableInternalHelper$RequestMax[] e;

    static {
        FlowableInternalHelper$RequestMax flowableInternalHelper$RequestMax = new FlowableInternalHelper$RequestMax("INSTANCE", 0);
        c = flowableInternalHelper$RequestMax;
        e = new FlowableInternalHelper$RequestMax[]{flowableInternalHelper$RequestMax};
    }

    public static FlowableInternalHelper$RequestMax valueOf(String str) {
        return (FlowableInternalHelper$RequestMax) Enum.valueOf(FlowableInternalHelper$RequestMax.class, str);
    }

    public static FlowableInternalHelper$RequestMax[] values() {
        return (FlowableInternalHelper$RequestMax[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        ((tg2) obj).a(Long.MAX_VALUE);
    }
}
