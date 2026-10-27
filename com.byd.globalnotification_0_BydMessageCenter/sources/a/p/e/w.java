package a.p.e;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.m f1264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1265b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1266c = new Rect();

    public w(RecyclerView.m mVar, u uVar) {
        this.f1264a = mVar;
    }

    public static w a(RecyclerView.m mVar, int i) {
        if (i == 0) {
            return new u(mVar);
        }
        if (i == 1) {
            return new v(mVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public int m() {
        if (Integer.MIN_VALUE == this.f1265b) {
            return 0;
        }
        return l() - this.f1265b;
    }

    public abstract int n(View view);

    public abstract int o(View view);

    public abstract void p(int i);
}
