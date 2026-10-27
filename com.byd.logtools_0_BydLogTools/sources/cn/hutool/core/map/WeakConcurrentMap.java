package cn.hutool.core.map;

import cn.hutool.core.util.ReferenceUtil$ReferenceType;
import java.lang.ref.Reference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class WeakConcurrentMap<K, V> extends ReferenceConcurrentMap<K, V> {
    public WeakConcurrentMap() {
        this(new ConcurrentHashMap());
    }

    public WeakConcurrentMap(ConcurrentMap<Reference<K>, V> concurrentMap) {
        super(concurrentMap, ReferenceUtil$ReferenceType.WEAK);
    }
}
