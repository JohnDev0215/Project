package X5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0864i0 extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ boolean f10269m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ boolean f10270n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0864i0(int i7, InterfaceC2189c interfaceC2189c, int i10) {
        super(i7, interfaceC2189c);
        this.l = i10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        Boolean bool = (Boolean) obj;
        switch (this.l) {
            case 0:
                boolean zBooleanValue = bool.booleanValue();
                boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
                C0864i0 c0864i0 = new C0864i0(3, (InterfaceC2189c) obj3, 0);
                c0864i0.f10269m = zBooleanValue;
                c0864i0.f10270n = zBooleanValue2;
                return c0864i0.invokeSuspend(m7.q.f19267a);
            default:
                boolean zBooleanValue3 = bool.booleanValue();
                boolean zBooleanValue4 = ((Boolean) obj2).booleanValue();
                C0864i0 c0864i02 = new C0864i0(3, (InterfaceC2189c) obj3, 1);
                c0864i02.f10269m = zBooleanValue3;
                c0864i02.f10270n = zBooleanValue4;
                return c0864i02.invokeSuspend(m7.q.f19267a);
        }
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        boolean z6 = false;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                AbstractC1386c.u(obj);
                boolean z10 = this.f10269m;
                boolean z11 = this.f10270n;
                if (z10 && z11) {
                    z6 = true;
                }
                return Boolean.valueOf(z6);
            default:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                boolean z12 = this.f10269m;
                boolean z13 = this.f10270n;
                if (z12 && z13) {
                    z6 = true;
                }
                return Boolean.valueOf(z6);
        }
    }
}
