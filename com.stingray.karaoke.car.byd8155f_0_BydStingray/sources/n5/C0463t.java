package N5;

import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.ContentCompletedReason;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0463t extends s7.i implements InterfaceC2782k {
    public final /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4768m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4769n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0463t(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c, boolean z10) {
        super(1, interfaceC2189c);
        this.l = z10;
        this.f4768m = z6;
        this.f4769n = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0463t) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0463t(this.f4768m, this.f4769n, interfaceC2189c, this.l);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if ((!this.l && this.f4768m.i() >= 3000) || (((Number) ((V8.f0) this.f4768m.c.f4750t.l).getValue()).intValue() < 1 && ((Number) ((V8.f0) this.f4768m.c.f4748r.l).getValue()).intValue() == 0)) {
            this.f4768m.u(this.f4769n);
            return EnumC0445a.f4679n;
        }
        Z z6 = this.f4768m;
        z6.B = ContentCompletedReason.SKIPPED;
        z6.o = this.f4769n;
        q0 q0Var = z6.c;
        synchronized (q0Var) {
            try {
                int iMin = Math.min(Math.max(0, q0Var.f4743k - 1), q0Var.f4740h.size() - 1);
                int iIntValue = ((Number) ((V8.f0) q0Var.f4748r.l).getValue()).intValue();
                if (iIntValue == 0 || iIntValue == 1) {
                    q0Var.e(iMin);
                } else if (iIntValue == 2) {
                    if (q0Var.f4743k - 1 < 0) {
                        q0Var.e(q0Var.f4740h.size() - 1);
                    } else {
                        q0Var.e(iMin);
                    }
                }
            } finally {
            }
        }
        if (((Boolean) ((V8.f0) this.f4768m.f4639R.l).getValue()).booleanValue()) {
            this.f4768m.q();
        }
        return EnumC0445a.l;
    }
}
