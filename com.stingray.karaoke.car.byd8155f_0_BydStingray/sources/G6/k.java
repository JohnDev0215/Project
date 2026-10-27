package G6;

import A7.m;
import S8.InterfaceC0725w;
import T.X;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X f2707m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f2708n;
    public final /* synthetic */ X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X f2709p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str, X x8, X x9, X x10, X x11, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = str;
        this.f2707m = x8;
        this.f2708n = x9;
        this.o = x10;
        this.f2709p = x11;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k(this.l, this.f2707m, this.f2708n, this.o, this.f2709p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        k kVar = (k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        kVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        X x8 = this.f2708n;
        String str = (String) x8.getValue();
        String str2 = this.l;
        boolean zA = m.a(str2, str);
        X x9 = this.f2707m;
        X x10 = this.o;
        if (!zA) {
            X x11 = this.f2709p;
            if (m.a(str2, (String) x11.getValue())) {
                if (((Boolean) x10.getValue()).booleanValue()) {
                    x9.setValue(Boolean.valueOf(!((Boolean) x9.getValue()).booleanValue()));
                    x10.setValue(Boolean.FALSE);
                }
            } else if (((Boolean) x10.getValue()).booleanValue()) {
                x11.setValue(str2);
                x10.setValue(Boolean.FALSE);
            } else {
                x8.setValue(str2);
                x10.setValue(Boolean.TRUE);
            }
        } else if (!((Boolean) x10.getValue()).booleanValue()) {
            x9.setValue(Boolean.valueOf(!((Boolean) x9.getValue()).booleanValue()));
            x10.setValue(Boolean.TRUE);
        }
        return q.f19267a;
    }
}
