package z2;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Surface;
import e2.q0;
import h2.AbstractC1343A;
import io.sentry.android.core.RunnableC1447x;
import n2.C1913m;

/* JADX INFO: renamed from: z2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2758d implements Handler.Callback {
    public final Handler l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2759e f24273m;

    public C2758d(C2759e c2759e, s2.k kVar) {
        this.f24273m = c2759e;
        Handler handlerL = AbstractC1343A.l(this);
        this.l = handlerL;
        kVar.h(this, handlerL);
    }

    public final void a(long j3) {
        Surface surface;
        C2759e c2759e = this.f24273m;
        if (this != c2759e.f24307s1 || c2759e.f22137V == null) {
            return;
        }
        if (j3 == Long.MAX_VALUE) {
            c2759e.G0 = true;
            return;
        }
        try {
            c2759e.y0(j3);
            q0 q0Var = c2759e.f24302n1;
            boolean zEquals = q0Var.equals(q0.d);
            m2.b bVar = c2759e.f24279P0;
            if (!zEquals && !q0Var.equals(c2759e.f24303o1)) {
                c2759e.f24303o1 = q0Var;
                bVar.y0(q0Var);
            }
            c2759e.f22121I0.f19651e++;
            o oVar = c2759e.f24282S0;
            boolean z6 = oVar.d != 3;
            oVar.d = 3;
            oVar.f24349k.getClass();
            oVar.f24344f = AbstractC1343A.H(SystemClock.elapsedRealtime());
            if (z6 && (surface = c2759e.f24290a1) != null) {
                Handler handler = (Handler) bVar.l;
                if (handler != null) {
                    handler.post(new RunnableC1447x(bVar, surface, SystemClock.elapsedRealtime(), 2));
                }
                c2759e.f24292d1 = true;
            }
            c2759e.f0(j3);
        } catch (C1913m e10) {
            c2759e.f22119H0 = e10;
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        int i7 = message.arg1;
        int i10 = message.arg2;
        int i11 = AbstractC1343A.f15195a;
        a(((((long) i7) & 4294967295L) << 32) | (4294967295L & ((long) i10)));
        return true;
    }
}
