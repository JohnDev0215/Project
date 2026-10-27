package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.q;
import java.util.HashSet;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class Functions$HashSetSupplier implements q {
    public static final Functions$HashSetSupplier c;
    public static final /* synthetic */ Functions$HashSetSupplier[] e;

    static {
        Functions$HashSetSupplier functions$HashSetSupplier = new Functions$HashSetSupplier("INSTANCE", 0);
        c = functions$HashSetSupplier;
        e = new Functions$HashSetSupplier[]{functions$HashSetSupplier};
    }

    public static Functions$HashSetSupplier valueOf(String str) {
        return (Functions$HashSetSupplier) Enum.valueOf(Functions$HashSetSupplier.class, str);
    }

    public static Functions$HashSetSupplier[] values() {
        return (Functions$HashSetSupplier[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return new HashSet();
    }
}
