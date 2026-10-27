package defpackage;

import defpackage.xb1;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes3.dex */
public final class n8<K, V> extends vm2<K, V> implements Map<K, V> {
    public l8 l;

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        if (this.l == null) {
            this.l = new l8(this);
        }
        l8 l8Var = this.l;
        if (l8Var.a == null) {
            l8Var.a = new xb1.b();
        }
        return l8Var.a;
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        if (this.l == null) {
            this.l = new l8(this);
        }
        l8 l8Var = this.l;
        if (l8Var.b == null) {
            l8Var.b = new xb1.c();
        }
        return l8Var.b;
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size() + this.g;
        int i = this.g;
        int[] iArr = this.e;
        if (iArr.length < size) {
            Object[] objArr = this.f;
            a(size);
            if (this.g > 0) {
                System.arraycopy(iArr, 0, this.e, 0, i);
                System.arraycopy(objArr, 0, this.f, 0, i << 1);
            }
            vm2.c(iArr, objArr, i);
        }
        if (this.g != i) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        if (this.l == null) {
            this.l = new l8(this);
        }
        l8 l8Var = this.l;
        if (l8Var.c == null) {
            l8Var.c = new xb1.e();
        }
        return l8Var.c;
    }
}
