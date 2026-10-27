package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.functions.q;
import java.util.HashMap;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class HashMapSupplier implements q {
    public static final HashMapSupplier c;
    public static final /* synthetic */ HashMapSupplier[] e;

    static {
        HashMapSupplier hashMapSupplier = new HashMapSupplier("INSTANCE", 0);
        c = hashMapSupplier;
        e = new HashMapSupplier[]{hashMapSupplier};
    }

    public static HashMapSupplier valueOf(String str) {
        return (HashMapSupplier) Enum.valueOf(HashMapSupplier.class, str);
    }

    public static HashMapSupplier[] values() {
        return (HashMapSupplier[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return new HashMap();
    }
}
