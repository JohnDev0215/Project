package p;

import android.window.OnBackInvokedCallback;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lp1 implements OnBackInvokedCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ lp1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        switch (this.a) {
            case 0:
                py0 py0Var = (py0) this.b;
                v03.j(py0Var, "$onBackInvoked");
                py0Var.invoke();
                break;
            case 1:
                ((s8) this.b).B();
                break;
            default:
                ((Runnable) this.b).run();
                break;
        }
    }
}
