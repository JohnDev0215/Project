package N5;

import com.stingray.drivemodedetector.DriveMode;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0468y extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4779m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0468y(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4779m = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0468y c0468y = new C0468y(this.f4779m, interfaceC2189c);
        c0468y.l = obj;
        return c0468y;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0468y c0468y = (C0468y) create((DriveMode) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0468y.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        DriveMode driveMode = (DriveMode) this.l;
        DriveMode driveMode2 = DriveMode.DRIVING;
        Boolean boolValueOf = Boolean.valueOf(driveMode == driveMode2);
        Z z6 = this.f4779m;
        V8.f0 f0Var = z6.f4632K;
        f0Var.getClass();
        f0Var.m(null, boolValueOf);
        if (driveMode == driveMode2 && ((Boolean) z6.f4655e0.getValue()).booleanValue()) {
            Z.y(this.f4779m, false, false, ActionOwner.HOST, false, false, 24);
        }
        return m7.q.f19267a;
    }
}
