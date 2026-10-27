package defpackage;

import java.util.LinkedHashMap;

/* JADX INFO: compiled from: LruHashMap.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class va1<K, V> {
    public final LinkedHashMap<K, V> a = new LinkedHashMap<>(0, 0.75f, true);

    public final V a(K k, V v) {
        i21.e(k, "key");
        i21.e(v, "value");
        return this.a.put(k, v);
    }
}
