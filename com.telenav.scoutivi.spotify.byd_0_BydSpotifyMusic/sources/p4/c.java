package p4;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: MapBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<K, V> f8846a;

    private c(int i7) {
        this.f8846a = a.b(i7);
    }

    public static <K, V> c<K, V> b(int i7) {
        return new c<>(i7);
    }

    public Map<K, V> a() {
        return this.f8846a.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f8846a);
    }

    public c<K, V> c(K k7, V v6) {
        this.f8846a.put(k7, v6);
        return this;
    }
}
