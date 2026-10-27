package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class u12 {
    public int a;
    public int b;
    public int c;
    public int d;

    public final void a(n22 n22Var) {
        View view = n22Var.a;
        this.a = view.getLeft();
        this.b = view.getTop();
        this.c = view.getRight();
        this.d = view.getBottom();
    }
}
