package defpackage;

import android.view.Choreographer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s5 implements Choreographer.FrameCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ s5(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((Runnable) obj).run();
                break;
            case 1:
                hj3 hj3Var = hj3.e;
                ((hj3) obj).getClass();
                break;
            default:
                zm0 zm0Var = zm0.this;
                Runnable runnable = zm0Var.b;
                if (runnable != null) {
                    runnable.run();
                    zm0Var.b = null;
                }
                break;
        }
    }
}
