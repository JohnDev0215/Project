package B6;

import A3.c;
import A6.d;
import A7.m;
import B5.C0083g;
import B5.z;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.LinkedHashMap;
import java.util.Set;
import m7.q;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends i implements InterfaceC2785n {
    public final /* synthetic */ String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f739m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ b f740n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(String str, String str2, b bVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = str;
        this.f739m = str2;
        this.f740n = bVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new a(this.l, this.f739m, this.f740n, interfaceC2189c);
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
        z zVar = new z();
        String str = this.l;
        zVar.k(str, "feature_name");
        String str2 = this.f739m;
        zVar.k(str2, "feature_action");
        LinkedHashMap linkedHashMap = d.f439a;
        com.bumptech.glide.d.p(zVar);
        b bVar = this.f740n;
        if (bVar.c == null) {
            bVar.c = C0083g.h(bVar.f741a);
        }
        C0083g c0083g = bVar.c;
        m.c(c0083g);
        c0083g.g("Feature Used", zVar);
        bVar.f742b.a("Feature Used: " + str + " " + str2, zVar.h());
        Set setEntrySet = zVar.l.entrySet();
        m.e(setEntrySet, "<get-entries>(...)");
        System.out.println((Object) android.hardware.bydauto.ac.tsimpl.a.i("========== FEATURE USED ==========  ", AbstractC1998m.f0(setEntrySet, null, null, null, new c(3), 31), "\n"));
        return q.f19267a;
    }
}
