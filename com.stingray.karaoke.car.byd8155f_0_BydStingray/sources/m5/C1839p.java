package m5;

import java.lang.reflect.Field;
import o5.AbstractC2047c;
import r5.C2297a;

/* JADX INFO: renamed from: m5.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1839p extends AbstractC1838o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l5.o f19241b;

    public C1839p(l5.o oVar, C1840q c1840q) {
        super(c1840q);
        this.f19241b = oVar;
    }

    @Override // m5.AbstractC1838o
    public final Object c() {
        return this.f19241b.H();
    }

    @Override // m5.AbstractC1838o
    public final Object d(Object obj) {
        return obj;
    }

    @Override // m5.AbstractC1838o
    public final void e(Object obj, C2297a c2297a, C1837n c1837n) throws IllegalAccessException {
        Object objA = c1837n.f19237g.a(c2297a);
        if (objA == null && c1837n.f19238h) {
            return;
        }
        Field field = c1837n.f19234b;
        if (c1837n.d) {
            C1841s.b(obj, field);
        } else if (c1837n.f19239i) {
            throw new j5.p(M5.b.g("Cannot set value of 'static final' ", AbstractC2047c.d(field, false)));
        }
        field.set(obj, objA);
    }
}
