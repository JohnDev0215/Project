package h;

import android.content.Intent;
import android.content.IntentSender;
import android.os.RemoteException;
import android.util.Pair;
import h2.AbstractC1346c;
import java.io.Serializable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import k.C1584d;
import k3.C1;
import k3.C1628b0;
import k3.InterfaceC1655m;
import n2.Q;
import q3.C2174a;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f15102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f15103n;
    public final /* synthetic */ Object o;

    public /* synthetic */ k(Object obj, int i7, Object obj2, int i10) {
        this.l = i10;
        this.f15102m = obj;
        this.f15103n = i7;
        this.o = obj2;
    }

    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Object, q2.l] */
    @Override // java.lang.Runnable
    public final void run() {
        C1 c1;
        switch (this.l) {
            case 0:
                l lVar = (l) this.f15102m;
                A7.m.f(lVar, "this$0");
                Serializable serializable = (Serializable) ((e7.f) this.o).f14480m;
                String str = (String) lVar.f15104a.get(Integer.valueOf(this.f15103n));
                if (str != null) {
                    C1584d c1584d = (C1584d) lVar.f15106e.get(str);
                    if ((c1584d != null ? c1584d.f17603a : null) == null) {
                        lVar.f15108g.remove(str);
                        lVar.f15107f.put(str, serializable);
                    } else {
                        G2.a aVar = c1584d.f17603a;
                        if (lVar.d.remove(str)) {
                            aVar.l(serializable);
                        }
                    }
                    break;
                }
                break;
            case 1:
                l lVar2 = (l) this.f15102m;
                A7.m.f(lVar2, "this$0");
                IntentSender.SendIntentException sendIntentException = (IntentSender.SendIntentException) this.o;
                A7.m.f(sendIntentException, "$e");
                lVar2.a(this.f15103n, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", sendIntentException));
                break;
            case 2:
                for (h2.n nVar : (CopyOnWriteArraySet) this.f15102m) {
                    if (!nVar.d) {
                        int i7 = this.f15103n;
                        if (i7 != -1) {
                            nVar.f15221b.f(i7);
                        }
                        nVar.c = true;
                        ((h2.l) this.o).b(nVar.f15220a);
                    }
                }
                break;
            case 3:
                h5.v vVar = (h5.v) this.o;
                int i10 = this.f15103n;
                C1628b0 c1628b0 = (C1628b0) this.f15102m;
                c1628b0.getClass();
                try {
                    c1 = (C1) vVar.l;
                    AbstractC1346c.h(c1, "SessionResult must not be null");
                } catch (InterruptedException | ExecutionException e10) {
                    AbstractC1346c.A("MCImplBase", "Session operation failed", e10);
                    c1 = new C1(-1);
                } catch (CancellationException e11) {
                    AbstractC1346c.A("MCImplBase", "Session operation cancelled", e11);
                    c1 = new C1(1);
                }
                InterfaceC1655m interfaceC1655m = c1628b0.f17899A;
                if (interfaceC1655m != null) {
                    try {
                        interfaceC1655m.C0(c1628b0.c, i10, c1.b());
                    } catch (RemoteException unused) {
                        AbstractC1346c.z("MCImplBase", "Error in sending");
                        return;
                    }
                    break;
                }
                break;
            case 4:
                o2.e eVar = (o2.e) ((Q) this.f15102m).f19579m.f10764i;
                Pair pair = (Pair) this.o;
                eVar.a(((Integer) pair.first).intValue(), (C2492C) pair.second, this.f15103n);
                break;
            case 5:
                q2.k kVar = (q2.k) this.f15102m;
                this.o.a(kVar.f21209a, kVar.f21210b, this.f15103n);
                break;
            default:
                ((C2174a) this.f15102m).f21225b.f(this.f15103n, (Serializable) this.o);
                break;
        }
    }

    public /* synthetic */ k(Object obj, Object obj2, int i7, int i10) {
        this.l = i10;
        this.f15102m = obj;
        this.o = obj2;
        this.f15103n = i7;
    }
}
