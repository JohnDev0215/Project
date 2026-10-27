package defpackage;

import defpackage.kd2;
import java.util.HashMap;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class kk0<K, V> extends kd2<K, V> {
    public final HashMap<K, kd2.c<K, V>> i = new HashMap<>();

    @Override // defpackage.kd2
    public final kd2.c<K, V> a(K k) {
        return this.i.get(k);
    }

    @Override // defpackage.kd2
    public final V b(K k) {
        V v = (V) super.b(k);
        this.i.remove(k);
        return v;
    }

    public final V c(K k, V v) {
        kd2.c<K, V> cVarA = a(k);
        if (cVarA != null) {
            return cVarA.f;
        }
        HashMap<K, kd2.c<K, V>> map = this.i;
        kd2.c<K, V> cVar = new kd2.c<>(k, v);
        this.h++;
        kd2.c<K, V> cVar2 = this.f;
        if (cVar2 == null) {
            this.e = cVar;
            this.f = cVar;
        } else {
            cVar2.g = cVar;
            cVar.h = cVar2;
            this.f = cVar;
        }
        map.put(k, cVar);
        return null;
    }
}
