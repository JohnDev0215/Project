package k3;

import android.graphics.Bitmap;
import android.os.RemoteException;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: k3.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1640f0 implements Runnable {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f17947m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f17948n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f17949p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f17950q;

    public /* synthetic */ RunnableC1640f0(C1650j0 c1650j0, AtomicInteger atomicInteger, List list, ArrayList arrayList, int i7) {
        this.f17948n = c1650j0;
        this.o = atomicInteger;
        this.f17949p = list;
        this.f17950q = arrayList;
        this.f17947m = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bitmap bitmap;
        switch (this.l) {
            case 0:
                C1650j0 c1650j0 = (C1650j0) this.f17948n;
                c1650j0.getClass();
                int iIncrementAndGet = ((AtomicInteger) this.o).incrementAndGet();
                List list = (List) this.f17949p;
                if (iIncrementAndGet == list.size()) {
                    int i7 = 0;
                    while (true) {
                        ArrayList arrayList = (ArrayList) this.f17950q;
                        if (i7 < arrayList.size()) {
                            h5.y yVar = (h5.y) arrayList.get(i7);
                            if (yVar != null) {
                                try {
                                    bitmap = (Bitmap) l9.l.N(yVar);
                                } catch (CancellationException | ExecutionException e10) {
                                    AbstractC1346c.o("MCImplLegacy", "Failed to get bitmap", e10);
                                    bitmap = null;
                                }
                            } else {
                                bitmap = null;
                            }
                            c1650j0.f18004h.E(AbstractC1663q.g((e2.F) list.get(i7), bitmap), this.f17947m + i7);
                            i7++;
                        }
                    }
                }
                break;
            default:
                P0 p02 = (P0) this.f17950q;
                Q0 q02 = (Q0) this.f17948n;
                if (!q02.f17843f.h()) {
                    boolean zIsActive = ((l3.L) q02.f17847j.f18537m).f18523a.isActive();
                    y1 y1Var = (y1) this.o;
                    int i10 = this.f17947m;
                    l3.T t10 = (l3.T) this.f17949p;
                    if (!zIsActive) {
                        StringBuilder sb = new StringBuilder("Ignore incoming session command before initialization. command=");
                        sb.append(y1Var == null ? Integer.valueOf(i10) : y1Var.f18168b);
                        sb.append(", pid=");
                        sb.append(t10.f18543a.f18540b);
                        AbstractC1346c.z("MediaSessionLegacyStub", sb.toString());
                    } else {
                        C1679y0 c1679y0K = q02.K(t10);
                        B8.t tVar = q02.f17842e;
                        if (y1Var != null) {
                            if (!tVar.e0(c1679y0K, y1Var)) {
                            }
                        } else if (!tVar.d0(c1679y0K, i10)) {
                        }
                        try {
                            p02.d(c1679y0K);
                        } catch (RemoteException e11) {
                            AbstractC1346c.A("MediaSessionLegacyStub", "Exception in " + c1679y0K, e11);
                        }
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ RunnableC1640f0(Q0 q02, y1 y1Var, int i7, l3.T t10, P0 p02) {
        this.f17948n = q02;
        this.o = y1Var;
        this.f17947m = i7;
        this.f17949p = t10;
        this.f17950q = p02;
    }
}
