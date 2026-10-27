package C6;

import A3.c;
import A7.m;
import B5.C0083g;
import B5.z;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.Set;
import m7.q;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends i implements InterfaceC2785n {
    public final /* synthetic */ b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f1009m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f1010n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(b bVar, String str, z zVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = bVar;
        this.f1009m = str;
        this.f1010n = zVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new a(this.l, this.f1009m, this.f1010n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        a aVar = (a) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        aVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        b bVar = this.l;
        if (bVar.c == null) {
            bVar.c = C0083g.h(bVar.f1012a);
        }
        C0083g c0083g = bVar.c;
        m.c(c0083g);
        z zVar = this.f1010n;
        String str = this.f1009m;
        c0083g.f(str, zVar);
        bVar.f1013b.a(M5.b.g("Screen Viewed: ", str), zVar.h());
        Set setEntrySet = zVar.l.entrySet();
        m.e(setEntrySet, "<get-entries>(...)");
        System.out.println((Object) ("========== SCREEN VIEWED: " + str + " ========== " + AbstractC1998m.f0(setEntrySet, null, null, null, new c(4), 31) + "\n"));
        return q.f19267a;
    }
}
