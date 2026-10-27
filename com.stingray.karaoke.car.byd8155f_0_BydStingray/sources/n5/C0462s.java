package N5;

import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.ContentCompletedReason;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0462s extends s7.i implements InterfaceC2782k {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f4766m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4767n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0462s(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c, boolean z10) {
        super(1, interfaceC2189c);
        this.l = z6;
        this.f4766m = z10;
        this.f4767n = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0462s) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0462s(this.l, this.f4767n, interfaceC2189c, this.f4766m);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if (!this.l.c.g()) {
            this.l.C(PlayerStatus.STOPPED);
            return EnumC0445a.f4679n;
        }
        boolean zBooleanValue = ((Boolean) this.l.f4638Q.getValue()).booleanValue();
        Z z6 = this.l;
        z6.B = this.f4766m ? ContentCompletedReason.SKIPPED : ContentCompletedReason.COMPLETED;
        z6.o = this.f4767n;
        q0 q0Var = z6.c;
        synchronized (q0Var) {
            try {
                int iMax = Math.max(Math.min(q0Var.f4740h.size() - 1, q0Var.f4743k + 1), 0);
                int iIntValue = ((Number) ((V8.f0) q0Var.f4748r.l).getValue()).intValue();
                if (iIntValue == 0 || iIntValue == 1) {
                    q0Var.e(iMax);
                } else if (iIntValue == 2) {
                    if (q0Var.f4740h.size() == q0Var.f4743k + 1) {
                        q0Var.e(0);
                    } else {
                        q0Var.e(iMax);
                    }
                }
            } finally {
            }
        }
        if (zBooleanValue) {
            this.l.q();
            EnumC0445a enumC0445a = EnumC0445a.l;
        }
        return EnumC0445a.l;
    }
}
