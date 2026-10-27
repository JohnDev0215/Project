package a.k;

import android.view.Choreographer;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: loaded from: classes.dex */
public class q implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewDataBinding f928a;

    public q(ViewDataBinding viewDataBinding) {
        this.f928a = viewDataBinding;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        this.f928a.f1541b.run();
    }
}
