package defpackage;

import android.animation.Animator;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* JADX INFO: compiled from: IndeterminateAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class nz0<T extends Animator> {
    public oz0 a;
    public final float[] b;
    public final int[] c;

    public nz0(int i) {
        this.b = new float[i * 2];
        this.c = new int[i];
    }

    public abstract void a();

    public abstract void b();

    public abstract void c(BaseProgressIndicator.c cVar);

    public abstract void d();

    public abstract void e();

    public abstract void f();
}
