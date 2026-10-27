package G;

import d0.InterfaceC1054c;
import java.util.LinkedHashMap;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1054c f2503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E.n f2504b;
    public final LinkedHashMap c = new LinkedHashMap();

    public G(InterfaceC1054c interfaceC1054c, E.n nVar) {
        this.f2503a = interfaceC1054c;
        this.f2504b = nVar;
    }

    public final InterfaceC2785n a(Object obj, int i7, Object obj2) {
        b0.b bVar;
        LinkedHashMap linkedHashMap = this.c;
        F f10 = (F) linkedHashMap.get(obj);
        if (f10 != null && f10.c == i7 && A7.m.a(f10.f2501b, obj2)) {
            b0.b bVar2 = f10.d;
            if (bVar2 != null) {
                return bVar2;
            }
            bVar = new b0.b(1403994769, new A.L(f10.f2502e, 5, f10), true);
            f10.d = bVar;
        } else {
            F f11 = new F(this, i7, obj, obj2);
            linkedHashMap.put(obj, f11);
            bVar = f11.d;
            if (bVar == null) {
                b0.b bVar3 = new b0.b(1403994769, new A.L(this, 5, f11), true);
                f11.d = bVar3;
                return bVar3;
            }
        }
        return bVar;
    }

    public final Object b(Object obj) {
        if (obj == null) {
            return null;
        }
        F f10 = (F) this.c.get(obj);
        if (f10 != null) {
            return f10.f2501b;
        }
        I i7 = (I) this.f2504b.a();
        int iA = i7.a(obj);
        if (iA != -1) {
            return i7.d(iA);
        }
        return null;
    }
}
