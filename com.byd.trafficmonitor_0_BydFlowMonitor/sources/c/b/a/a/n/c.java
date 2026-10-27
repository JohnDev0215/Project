package c.b.a.a.n;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f4046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4047b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4048c;

    public c() {
        this.f4047b = 0;
        this.f4048c = 0;
    }

    public int E() {
        d dVar = this.f4046a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    public void F(CoordinatorLayout coordinatorLayout, V v, int i2) {
        coordinatorLayout.M(v, i2);
    }

    public boolean G(int i2) {
        d dVar = this.f4046a;
        if (dVar != null) {
            return dVar.f(i2);
        }
        this.f4047b = i2;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        F(coordinatorLayout, v, i2);
        if (this.f4046a == null) {
            this.f4046a = new d(v);
        }
        this.f4046a.d();
        this.f4046a.a();
        int i3 = this.f4047b;
        if (i3 != 0) {
            this.f4046a.f(i3);
            this.f4047b = 0;
        }
        int i4 = this.f4048c;
        if (i4 == 0) {
            return true;
        }
        this.f4046a.e(i4);
        this.f4048c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4047b = 0;
        this.f4048c = 0;
    }
}
