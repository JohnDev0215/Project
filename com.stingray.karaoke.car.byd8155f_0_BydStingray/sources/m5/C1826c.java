package m5;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1826c extends j5.y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19195a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19196b;
    public final Object c;

    public C1826c(j5.m mVar, Type type, j5.y yVar, l5.o oVar) {
        this.f19196b = new C1843u(mVar, yVar, type);
        this.c = oVar;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        switch (this.f19195a) {
            case 0:
                if (c2297a.Q() == 9) {
                    c2297a.M();
                    return null;
                }
                Collection collection = (Collection) ((l5.o) this.c).H();
                c2297a.b();
                while (c2297a.v()) {
                    collection.add(((j5.y) ((C1843u) this.f19196b).c).a(c2297a));
                }
                c2297a.k();
                return collection;
            default:
                Object objA = ((S) this.c).f19189n.a(c2297a);
                if (objA != null) {
                    Class cls = (Class) this.f19196b;
                    if (!cls.isInstance(objA)) {
                        throw new j5.p("Expected a " + cls.getName() + " but was " + objA.getClass().getName() + "; at path " + c2297a.q(true));
                    }
                }
                return objA;
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        switch (this.f19195a) {
            case 0:
                Collection collection = (Collection) obj;
                if (collection != null) {
                    c2298b.g();
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        ((C1843u) this.f19196b).b(c2298b, it.next());
                    }
                    c2298b.k();
                } else {
                    c2298b.v();
                }
                break;
            default:
                ((S) this.c).f19189n.b(c2298b, obj);
                break;
        }
    }

    public C1826c(S s6, Class cls) {
        this.c = s6;
        this.f19196b = cls;
    }
}
