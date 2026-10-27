package io.reactivex.rxjava3.core;

import com.spotify.voiceassistants.playermodels.ContextResponseKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BackpressureStrategy {
    public static final BackpressureStrategy c;
    public static final BackpressureStrategy e;
    public static final /* synthetic */ BackpressureStrategy[] k;

    /* JADX INFO: Fake field, exist only in values array */
    BackpressureStrategy EF0;

    static {
        BackpressureStrategy backpressureStrategy = new BackpressureStrategy("MISSING", 0);
        BackpressureStrategy backpressureStrategy2 = new BackpressureStrategy(ContextResponseKt.RESULT_ERROR, 1);
        BackpressureStrategy backpressureStrategy3 = new BackpressureStrategy("BUFFER", 2);
        c = backpressureStrategy3;
        BackpressureStrategy backpressureStrategy4 = new BackpressureStrategy("DROP", 3);
        BackpressureStrategy backpressureStrategy5 = new BackpressureStrategy("LATEST", 4);
        e = backpressureStrategy5;
        k = new BackpressureStrategy[]{backpressureStrategy, backpressureStrategy2, backpressureStrategy3, backpressureStrategy4, backpressureStrategy5};
    }

    public static BackpressureStrategy valueOf(String str) {
        return (BackpressureStrategy) Enum.valueOf(BackpressureStrategy.class, str);
    }

    public static BackpressureStrategy[] values() {
        return (BackpressureStrategy[]) k.clone();
    }
}
