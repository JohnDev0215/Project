package P5;

import Q5.C0606p0;
import T7.AbstractC0789h;
import T7.C0797p;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import o8.C2061f;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class w implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f5405m;

    public /* synthetic */ w(Object obj, int i7) {
        this.l = i7;
        this.f5405m = obj;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                return ((p) this.f5405m).p();
            case 1:
                StringBuilder sb = new StringBuilder("Scope for type parameter ");
                C0606p0 c0606p0 = (C0606p0) this.f5405m;
                sb.append(((C2061f) c0606p0.f7009m).b());
                return h6.i.g(sb.toString(), ((AbstractC0789h) c0606p0.f7010n).getUpperBounds());
            case 2:
                C0797p c0797p = (C0797p) this.f5405m;
                c0797p.getClass();
                HashSet hashSet = new HashSet();
                for (C2061f c2061f : (Set) c0797p.f8858e.f8861t.a()) {
                    if (c2061f == null) {
                        C0797p.h(5);
                        throw null;
                    }
                    hashSet.addAll((Collection) c0797p.f8857b.b(c2061f));
                    hashSet.addAll((Collection) c0797p.c.b(c2061f));
                }
                return hashSet;
            default:
                return (List) this.f5405m;
        }
    }
}
