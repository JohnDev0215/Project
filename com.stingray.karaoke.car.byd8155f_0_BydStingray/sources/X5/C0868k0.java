package X5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.drivemodedetector.DriveMode;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0868k0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10283m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0868k0(D0 d02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10283m = d02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0868k0(this.f10283m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0868k0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        D0 d02 = this.f10283m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (((V8.f0) d02.f10123S.l).getValue() == DriveMode.PARKED) {
                this.l = 1;
                if (AbstractC0727y.i(3000L, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AbstractC1386c.u(obj);
        V8.f0 f0Var = d02.f10102A0;
        Boolean bool = Boolean.FALSE;
        f0Var.getClass();
        f0Var.m(null, bool);
        return m7.q.f19267a;
    }
}
