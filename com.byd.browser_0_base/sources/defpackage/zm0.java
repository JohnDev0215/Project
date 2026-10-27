package defpackage;

import android.view.Choreographer;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: FirstDrawDetector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zm0 {
    public final WeakReference<View> a;
    public Runnable b;
    public boolean c;

    /* JADX INFO: compiled from: FirstDrawDetector.java */
    public class a implements ViewTreeObserver.OnDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            zm0 zm0Var = zm0.this;
            if (zm0Var.c) {
                return;
            }
            zm0Var.c = true;
            Runnable runnable = zm0Var.b;
            if (runnable != null) {
                runnable.run();
                zm0Var.b = null;
            }
            PostTask.c(rh3.b, new d92(26, this));
        }
    }

    /* JADX INFO: compiled from: FirstDrawDetector.java */
    public class b implements ViewTreeObserver.OnPreDrawListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            Choreographer.getInstance().postFrameCallback(new s5(2, this));
            zm0 zm0Var = zm0.this;
            if (zm0Var.a.get() == null) {
                return true;
            }
            zm0Var.a.get().getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    public zm0(View view, Runnable runnable) {
        this.a = new WeakReference<>(view);
        this.b = runnable;
    }

    public final void a(boolean z) {
        a aVar = new a();
        WeakReference<View> weakReference = this.a;
        weakReference.get().getViewTreeObserver().addOnDrawListener(aVar);
        if (z) {
            return;
        }
        weakReference.get().getViewTreeObserver().addOnPreDrawListener(new b());
    }
}
