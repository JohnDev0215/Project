package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleSource;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class SingleInternalHelper$ToFlowable implements io.reactivex.rxjava3.functions.o {
    public static final SingleInternalHelper$ToFlowable c;
    public static final /* synthetic */ SingleInternalHelper$ToFlowable[] e;

    static {
        SingleInternalHelper$ToFlowable singleInternalHelper$ToFlowable = new SingleInternalHelper$ToFlowable("INSTANCE", 0);
        c = singleInternalHelper$ToFlowable;
        e = new SingleInternalHelper$ToFlowable[]{singleInternalHelper$ToFlowable};
    }

    public static SingleInternalHelper$ToFlowable valueOf(String str) {
        return (SingleInternalHelper$ToFlowable) Enum.valueOf(SingleInternalHelper$ToFlowable.class, str);
    }

    public static SingleInternalHelper$ToFlowable[] values() {
        return (SingleInternalHelper$ToFlowable[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        return new l0((SingleSource) obj);
    }
}
