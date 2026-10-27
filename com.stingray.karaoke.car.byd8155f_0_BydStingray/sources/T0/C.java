package T0;

import android.view.Choreographer;
import h2.AbstractC1343A;
import java.util.concurrent.Executor;
import k3.C1674w;
import k3.I0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C implements Executor {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8422m;

    public /* synthetic */ C(Object obj, int i7) {
        this.l = i7;
        this.f8422m = obj;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(final Runnable runnable) {
        switch (this.l) {
            case 0:
                ((Choreographer) this.f8422m).postFrameCallback(new Choreographer.FrameCallback() { // from class: T0.D
                    @Override // android.view.Choreographer.FrameCallback
                    public final void doFrame(long j3) {
                        runnable.run();
                    }
                });
                break;
            case 1:
                AbstractC1343A.K(((C1674w) this.f8422m).f18144s, runnable);
                break;
            default:
                AbstractC1343A.K(((I0) this.f8422m).l, runnable);
                break;
        }
    }
}
