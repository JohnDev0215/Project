package io.reactivex.rxjava3.internal.operators.single;

import java.util.NoSuchElementException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class SingleInternalHelper$NoSuchElementSupplier implements io.reactivex.rxjava3.functions.q {
    public static final SingleInternalHelper$NoSuchElementSupplier c;
    public static final /* synthetic */ SingleInternalHelper$NoSuchElementSupplier[] e;

    static {
        SingleInternalHelper$NoSuchElementSupplier singleInternalHelper$NoSuchElementSupplier = new SingleInternalHelper$NoSuchElementSupplier("INSTANCE", 0);
        c = singleInternalHelper$NoSuchElementSupplier;
        e = new SingleInternalHelper$NoSuchElementSupplier[]{singleInternalHelper$NoSuchElementSupplier};
    }

    public static SingleInternalHelper$NoSuchElementSupplier valueOf(String str) {
        return (SingleInternalHelper$NoSuchElementSupplier) Enum.valueOf(SingleInternalHelper$NoSuchElementSupplier.class, str);
    }

    public static SingleInternalHelper$NoSuchElementSupplier[] values() {
        return (SingleInternalHelper$NoSuchElementSupplier[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return new NoSuchElementException();
    }
}
