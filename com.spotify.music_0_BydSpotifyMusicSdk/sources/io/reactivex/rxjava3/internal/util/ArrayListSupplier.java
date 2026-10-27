package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.q;
import java.util.ArrayList;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ArrayListSupplier implements q, o {
    public static final ArrayListSupplier c;
    public static final /* synthetic */ ArrayListSupplier[] e;

    static {
        ArrayListSupplier arrayListSupplier = new ArrayListSupplier("INSTANCE", 0);
        c = arrayListSupplier;
        e = new ArrayListSupplier[]{arrayListSupplier};
    }

    public static ArrayListSupplier valueOf(String str) {
        return (ArrayListSupplier) Enum.valueOf(ArrayListSupplier.class, str);
    }

    public static ArrayListSupplier[] values() {
        return (ArrayListSupplier[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        return new ArrayList();
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return new ArrayList();
    }
}
