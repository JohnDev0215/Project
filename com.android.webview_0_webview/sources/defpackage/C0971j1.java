package defpackage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: j1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0971j1 extends C1633v1 implements Map {
    public AbstractC1578u1 z;

    public C0971j1() {
    }

    @Override // java.util.Map
    public Set entrySet() {
        AbstractC1578u1 abstractC1578u1K = k();
        if (abstractC1578u1K.a == null) {
            abstractC1578u1K.a = new C1359q1(abstractC1578u1K);
        }
        return abstractC1578u1K.a;
    }

    public final AbstractC1578u1 k() {
        if (this.z == null) {
            this.z = new C0916i1(this);
        }
        return this.z;
    }

    @Override // java.util.Map
    public Set keySet() {
        AbstractC1578u1 abstractC1578u1K = k();
        if (abstractC1578u1K.b == null) {
            abstractC1578u1K.b = new C1413r1(abstractC1578u1K);
        }
        return abstractC1578u1K.b;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        b(map.size() + this.u);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection values() {
        AbstractC1578u1 abstractC1578u1K = k();
        if (abstractC1578u1K.c == null) {
            abstractC1578u1K.c = new C1523t1(abstractC1578u1K);
        }
        return abstractC1578u1K.c;
    }

    public C0971j1(int i) {
        super(i);
    }

    public C0971j1(C1633v1 c1633v1) {
        if (c1633v1 != null) {
            int i = c1633v1.u;
            b(this.u + i);
            if (this.u != 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    put(c1633v1.h(i2), c1633v1.j(i2));
                }
            } else if (i > 0) {
                System.arraycopy(c1633v1.s, 0, this.s, 0, i);
                System.arraycopy(c1633v1.t, 0, this.t, 0, i << 1);
                this.u = i;
            }
        }
    }
}
