package k5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes2.dex */
public class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f5891a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5892b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5893c;

    public c() {
        this.f5892b = 0;
        this.f5893c = 0;
    }

    public int E() {
        d dVar = this.f5891a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    public void F(CoordinatorLayout coordinatorLayout, V v7, int i8) {
        coordinatorLayout.M(v7, i8);
    }

    public boolean G(int i8) {
        d dVar = this.f5891a;
        if (dVar != null) {
            return dVar.f(i8);
        }
        this.f5892b = i8;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v7, int i8) {
        F(coordinatorLayout, v7, i8);
        if (this.f5891a == null) {
            this.f5891a = new d(v7);
        }
        this.f5891a.d();
        this.f5891a.a();
        int i9 = this.f5892b;
        if (i9 != 0) {
            this.f5891a.f(i9);
            this.f5892b = 0;
        }
        int i10 = this.f5893c;
        if (i10 == 0) {
            return true;
        }
        this.f5891a.e(i10);
        this.f5893c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5892b = 0;
        this.f5893c = 0;
    }
}
