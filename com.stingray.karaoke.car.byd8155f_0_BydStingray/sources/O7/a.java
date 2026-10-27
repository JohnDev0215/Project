package O7;

import A7.m;
import E8.l;
import Q7.InterfaceC0652e;
import Q8.h;
import Q8.o;
import T7.z;
import io.sentry.hints.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.w;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class a implements S7.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f5168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f5169b;

    public a(l lVar, z zVar) {
        m.f(zVar, "module");
        this.f5168a = lVar;
        this.f5169b = zVar;
    }

    @Override // S7.c
    public final Collection a(C2058c c2058c) {
        m.f(c2058c, "packageFqName");
        return w.l;
    }

    @Override // S7.c
    public final boolean b(C2058c c2058c, C2061f c2061f) {
        m.f(c2058c, "packageFqName");
        m.f(c2061f, "name");
        String strB = c2061f.b();
        m.e(strB, "name.asString()");
        if (!o.z0(strB, "Function", false) && !o.z0(strB, "KFunction", false) && !o.z0(strB, "SuspendFunction", false) && !o.z0(strB, "KSuspendFunction", false)) {
            return false;
        }
        e.f5181n.getClass();
        return i.l(strB, c2058c) != null;
    }

    @Override // S7.c
    public final InterfaceC0652e c(C2057b c2057b) {
        m.f(c2057b, "classId");
        if (c2057b.c || !c2057b.f20565b.e().d()) {
            return null;
        }
        String strB = c2057b.h().b();
        if (!h.C0(strB, "Function", false)) {
            return null;
        }
        C2058c c2058cG = c2057b.g();
        m.e(c2058cG, "classId.packageFqName");
        e.f5181n.getClass();
        d dVarL = i.l(strB, c2058cG);
        if (dVarL == null) {
            return null;
        }
        List list = (List) C7.a.N(((T7.w) this.f5169b.n0(c2058cG)).f8914q, T7.w.f8912t[0]);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof C8.c) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            it.next();
        }
        if (AbstractC1998m.a0(arrayList2) != null) {
            throw new ClassCastException();
        }
        return new c(this.f5168a, (C8.c) AbstractC1998m.Y(arrayList), dVarL.f5179a, dVarL.f5180b);
    }
}
