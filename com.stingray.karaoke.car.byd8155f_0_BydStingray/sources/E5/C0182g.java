package E5;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: E5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0182g extends q {
    public static final C0181f d = new C0181f(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0181f f1807e = new C0181f(3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1808a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f1809b;
    public final Object c;

    public C0182g(H h10, Type type, Type type2) {
        h10.getClass();
        Set set = G5.f.f2685a;
        this.f1809b = h10.b(type, set);
        this.c = h10.b(type2, set);
    }

    @Override // E5.q
    public final Object a(u uVar) {
        switch (this.f1808a) {
            case 0:
                ArrayList arrayList = new ArrayList();
                uVar.b();
                while (uVar.m()) {
                    arrayList.add(this.f1809b.a(uVar));
                }
                uVar.i();
                Object objNewInstance = Array.newInstance((Class<?>) this.c, arrayList.size());
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    Array.set(objNewInstance, i7, arrayList.get(i7));
                }
                return objNewInstance;
            default:
                E e10 = new E();
                uVar.g();
                while (uVar.m()) {
                    v vVar = (v) uVar;
                    if (vVar.m()) {
                        vVar.f1832u = vVar.R();
                        vVar.f1829r = 11;
                    }
                    Object objA = this.f1809b.a(uVar);
                    Object objA2 = ((q) this.c).a(uVar);
                    Object objPut = e10.put(objA, objA2);
                    if (objPut != null) {
                        throw new A9.k("Map key '" + objA + "' has multiple values at path " + uVar.k() + ": " + objPut + " and " + objA2);
                    }
                }
                uVar.j();
                return e10;
        }
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        switch (this.f1808a) {
            case 0:
                xVar.b();
                int length = Array.getLength(obj);
                for (int i7 = 0; i7 < length; i7++) {
                    this.f1809b.d(xVar, Array.get(obj, i7));
                }
                ((w) xVar).z(']', 1, 2);
                return;
            default:
                xVar.g();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    if (entry.getKey() == null) {
                        throw new A9.k("Map key is null at " + xVar.j());
                    }
                    int iO = xVar.o();
                    if (iO != 5 && iO != 3) {
                        throw new IllegalStateException("Nesting problem.");
                    }
                    xVar.f1838p = true;
                    this.f1809b.d(xVar, entry.getKey());
                    ((q) this.c).d(xVar, entry.getValue());
                }
                xVar.i();
                return;
        }
    }

    public final String toString() {
        switch (this.f1808a) {
            case 0:
                return this.f1809b + ".array()";
            default:
                return "JsonAdapter(" + this.f1809b + "=" + ((q) this.c) + ")";
        }
    }

    public C0182g(Class cls, q qVar) {
        this.c = cls;
        this.f1809b = qVar;
    }
}
