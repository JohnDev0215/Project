package j5;

import m5.AbstractC1842t;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public class l extends AbstractC1842t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y f17020a = null;

    @Override // j5.y
    public final Object a(C2297a c2297a) {
        y yVar = this.f17020a;
        if (yVar != null) {
            return yVar.a(c2297a);
        }
        throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) {
        y yVar = this.f17020a;
        if (yVar == null) {
            throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }
        yVar.b(c2298b, obj);
    }

    @Override // m5.AbstractC1842t
    public final y c() {
        y yVar = this.f17020a;
        if (yVar != null) {
            return yVar;
        }
        throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
    }
}
