package g6;

import Q8.o;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import m7.q;
import n6.w;
import n7.z;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: g6.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1308f extends i implements InterfaceC2785n {
    public final /* synthetic */ C1310h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f14989m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1308f(C1310h c1310h, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1310h;
        this.f14989m = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1308f(this.l, this.f14989m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1308f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object next;
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
            if (Q8.h.C0(str, this.f14989m, false) && o.s0(str, ".mid", false)) {
                break;
            }
        }
        String str2 = (String) next;
        if (str2 == null) {
            return null;
        }
        InputStream inputStreamA = C1310h.a(c1310h, str2);
        byte[] bArrW = inputStreamA != null ? z.w(inputStreamA) : null;
        if (bArrW != null) {
            return new w(new ByteArrayInputStream(bArrW));
        }
        return null;
    }
}
