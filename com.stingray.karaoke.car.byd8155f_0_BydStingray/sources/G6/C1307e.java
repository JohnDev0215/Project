package g6;

import Q8.o;
import S8.InterfaceC0725w;
import h6.n;
import h8.AbstractC1386c;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import l8.C1789g;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: g6.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1307e extends i implements InterfaceC2785n {
    public final /* synthetic */ C1310h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f14988m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1307e(C1310h c1310h, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1310h;
        this.f14988m = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1307e(this.l, this.f14988m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1307e) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Exception {
        Object next;
        InputStream inputStreamA;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C1310h c1310h = this.l;
        Iterator it = c1310h.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String str = (String) next;
            if (Q8.h.C0(str, this.f14988m, false) && o.s0(str, ".kml", false)) {
                break;
            }
        }
        String str2 = (String) next;
        if (str2 != null && (inputStreamA = C1310h.a(c1310h, str2)) != null) {
            try {
                return new C1789g(8).v(new InputStreamReader(inputStreamA));
            } catch (Exception e10) {
                new Integer(n.i("Scoring", "Error loading KML", e10));
                throw new Exception("Failed to load KML");
            } finally {
                inputStreamA.close();
            }
        }
        throw new Exception("Failed to load KML");
    }
}
