package defpackage;

import android.view.View;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: renamed from: p6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1309p6 {
    public ArrayList a = new ArrayList();
    public int b = Integer.MIN_VALUE;
    public int c = Integer.MIN_VALUE;
    public int d = 0;
    public final int e;
    public final /* synthetic */ StaggeredGridLayoutManager f;

    public C1309p6(StaggeredGridLayoutManager staggeredGridLayoutManager, int i) {
        this.f = staggeredGridLayoutManager;
        this.e = i;
    }

    public void a() {
        View view = (View) this.a.get(r0.size() - 1);
        C1088l6 c1088l6E = e(view);
        this.c = this.f.r.b(view);
        Objects.requireNonNull(c1088l6E);
    }

    public void b() {
        View view = (View) this.a.get(0);
        C1088l6 c1088l6E = e(view);
        this.b = this.f.r.c(view);
        Objects.requireNonNull(c1088l6E);
    }

    public void c() {
        this.a.clear();
        this.b = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.d = 0;
    }

    public int d(int i) {
        int i2 = this.c;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.a.size() == 0) {
            return i;
        }
        a();
        return this.c;
    }

    public C1088l6 e(View view) {
        return (C1088l6) view.getLayoutParams();
    }

    public int f(int i) {
        int i2 = this.b;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.a.size() == 0) {
            return i;
        }
        b();
        return this.b;
    }
}
