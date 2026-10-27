package k3;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class F0 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f17761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f17762b;
    public final /* synthetic */ I0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F0(I0 i02, Looper looper) {
        super(looper);
        this.c = i02;
        this.f17761a = true;
        this.f17762b = true;
    }

    public final void a(boolean z6, boolean z10) {
        boolean z11 = false;
        this.f17761a = this.f17761a && z6;
        if (this.f17762b && z10) {
            z11 = true;
        }
        this.f17762b = z11;
        if (hasMessages(1)) {
            return;
        }
        sendEmptyMessage(1);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        C1679y0 c1679y0;
        int i7;
        int iU;
        if (message.what != 1) {
            throw new IllegalStateException("Invalid message what=" + message.what);
        }
        I0 i02 = this.c;
        q1 q1VarN = i02.f17810s.n(i02.f17811t.e1(), i02.f17811t.c1(), i02.f17810s.f18079k);
        i02.f17810s = q1VarN;
        boolean z6 = this.f17761a;
        boolean z10 = this.f17762b;
        m1 m1Var = i02.f17800g;
        q1 q1VarP1 = m1Var.p1(q1VarN);
        B8.t tVar = m1Var.f18014e;
        d5.I iU2 = tVar.U();
        int i10 = 0;
        while (i10 < iU2.size()) {
            C1679y0 c1679y02 = (C1679y0) iU2.get(i10);
            try {
                E0.X xY = tVar.Y(c1679y02);
                if (xY != null) {
                    iU = xY.u();
                } else {
                    if (!(m1Var.f18014e.a0(c1679y02) || i02.f17801h.f17842e.a0(c1679y02))) {
                        break;
                    } else {
                        iU = 0;
                    }
                }
                e2.S sG = n1.g(tVar.S(c1679y02), i02.f17811t.A());
                InterfaceC1677x0 interfaceC1677x0 = c1679y02.d;
                AbstractC1346c.k(interfaceC1677x0);
                c1679y0 = c1679y02;
                i7 = i10;
                try {
                    interfaceC1677x0.c(iU, q1VarP1, sG, z6, z10);
                } catch (DeadObjectException unused) {
                    m1Var.f18014e.l0(c1679y0);
                } catch (RemoteException e10) {
                    e = e10;
                    AbstractC1346c.A("MediaSessionImpl", "Exception in " + c1679y0.toString(), e);
                }
            } catch (DeadObjectException unused2) {
                c1679y0 = c1679y02;
                i7 = i10;
            } catch (RemoteException e11) {
                e = e11;
                c1679y0 = c1679y02;
                i7 = i10;
            }
            i10 = i7 + 1;
        }
        this.f17761a = true;
        this.f17762b = true;
    }
}
