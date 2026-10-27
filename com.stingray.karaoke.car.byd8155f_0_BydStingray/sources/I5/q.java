package I5;

import S8.AbstractC0727y;
import S8.E;
import S8.l0;
import V8.f0;
import androidx.lifecycle.C0982u;
import androidx.lifecycle.C0983v;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.ActionOwner;
import java.util.concurrent.atomic.AtomicReference;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3016m;

    public /* synthetic */ q(MainActivity mainActivity, int i7) {
        this.l = i7;
        this.f3016m = mainActivity;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        boolean z6;
        C0983v c0983v;
        m7.q qVar = m7.q.f19267a;
        MainActivity mainActivity = this.f3016m;
        switch (this.l) {
            case 0:
                int i7 = MainActivity.f12974j0;
                if (((Boolean) ((f0) mainActivity.s().f10122R.l).getValue()).booleanValue()) {
                    mainActivity.s().o();
                    z6 = true;
                } else {
                    z6 = false;
                }
                break;
            case 1:
                if (mainActivity.r().f4635N.getValue() == PlayerStatus.PLAYING) {
                    mainActivity.r().s(ActionOwner.HOST);
                }
                mainActivity.finish();
                break;
            case 2:
                int i10 = MainActivity.f12974j0;
                mainActivity.s().o();
                break;
            case 3:
                A7.m.f(mainActivity, "<this>");
                androidx.lifecycle.C c = mainActivity.l;
                A7.m.f(c, "<this>");
                while (true) {
                    AtomicReference atomicReference = (AtomicReference) c.f476m;
                    c0983v = (C0983v) atomicReference.get();
                    if (c0983v == null) {
                        l0 l0VarC = AbstractC0727y.c();
                        Z8.e eVar = E.f8084a;
                        c0983v = new C0983v(c, h6.q.x(l0VarC, X8.m.f10573a.f8932q));
                        while (!atomicReference.compareAndSet(null, c0983v)) {
                            if (atomicReference.get() != null) {
                            }
                            break;
                        }
                        Z8.e eVar2 = E.f8084a;
                        AbstractC0727y.w(c0983v, X8.m.f10573a.f8932q, null, new C0982u(c0983v, null), 2);
                    }
                }
                AbstractC0727y.w(c0983v, null, null, new w(mainActivity, null), 3);
                break;
            default:
                int i11 = MainActivity.f12974j0;
                f0 f0Var = mainActivity.q().f14617m.f18272a.f18253j;
                Boolean bool = Boolean.FALSE;
                f0Var.getClass();
                f0Var.m(null, bool);
                break;
        }
        return qVar;
    }
}
