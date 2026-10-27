package p8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: p8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2136k extends AbstractC2139n {
    public final C2133h l;

    public AbstractC2136k() {
        this.l = new C2133h();
    }

    public final boolean i() {
        int i7 = 0;
        while (true) {
            z zVar = this.l.f20980a;
            if (i7 >= zVar.f21003m.size()) {
                Iterator it = zVar.d().iterator();
                while (it.hasNext()) {
                    if (!C2133h.e((Map.Entry) it.next())) {
                        return false;
                    }
                }
                return true;
            }
            if (!C2133h.e((Map.Entry) zVar.f21003m.get(i7))) {
                return false;
            }
            i7++;
        }
    }

    public final int j() {
        z zVar;
        int i7 = 0;
        int iD = 0;
        while (true) {
            zVar = this.l.f20980a;
            if (i7 >= zVar.f21003m.size()) {
                break;
            }
            Map.Entry entry = (Map.Entry) zVar.f21003m.get(i7);
            iD += C2133h.d((C2137l) entry.getKey(), entry.getValue());
            i7++;
        }
        for (Map.Entry entry2 : zVar.d()) {
            iD += C2133h.d((C2137l) entry2.getKey(), entry2.getValue());
        }
        return iD;
    }

    public final Object k(C2138m c2138m) {
        o(c2138m);
        z zVar = this.l.f20980a;
        C2137l c2137l = c2138m.d;
        Object obj = zVar.get(c2137l);
        if (obj == null) {
            return c2138m.f20987b;
        }
        if (!c2137l.f20985n) {
            return c2138m.a(obj);
        }
        if (c2137l.f20984m.l != EnumC2125M.f20962t) {
            return obj;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(c2138m.a(it.next()));
        }
        return arrayList;
    }

    public final boolean l(C2138m c2138m) {
        o(c2138m);
        C2133h c2133h = this.l;
        c2133h.getClass();
        C2137l c2137l = c2138m.d;
        if (c2137l.f20985n) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return c2133h.f20980a.get(c2137l) != null;
    }

    public final void m() {
        this.l.f();
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n(p8.C2130e r9, A2.g r10, p8.C2132g r11, int r12) throws p8.C2142q {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.AbstractC2136k.n(p8.e, A2.g, p8.g, int):boolean");
    }

    public final void o(C2138m c2138m) {
        if (c2138m.f20986a != a()) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    public AbstractC2136k(AbstractC2135j abstractC2135j) {
        abstractC2135j.f20982m.f();
        abstractC2135j.f20983n = false;
        this.l = abstractC2135j.f20982m;
    }
}
