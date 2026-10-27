package c.a.a.e;

import java.lang.ref.Reference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: WeakConcurrentMap.java */
/* JADX INFO: loaded from: classes.dex */
public class j<K, V> extends i<K, V> {
    public j() {
        this(new ConcurrentHashMap());
    }

    public j(ConcurrentMap<Reference<K>, V> concurrentMap) {
        super(concurrentMap, c.a.a.g.j.WEAK);
    }
}
