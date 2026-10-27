package c.b.a.a.b;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f1675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1676b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1677c;

    public c() {
        this.f1676b = 0;
        this.f1677c = 0;
    }

    public int E() {
        d dVar = this.f1675a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    public void F(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.M(v, i);
    }

    public boolean G(int i) {
        d dVar = this.f1675a;
        if (dVar != null) {
            return dVar.f(i);
        }
        this.f1676b = i;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        F(coordinatorLayout, v, i);
        if (this.f1675a == null) {
            this.f1675a = new d(v);
        }
        this.f1675a.d();
        this.f1675a.a();
        int i2 = this.f1676b;
        if (i2 != 0) {
            this.f1675a.f(i2);
            this.f1676b = 0;
        }
        int i3 = this.f1677c;
        if (i3 == 0) {
            return true;
        }
        this.f1675a.e(i3);
        this.f1677c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1676b = 0;
        this.f1677c = 0;
    }
}
