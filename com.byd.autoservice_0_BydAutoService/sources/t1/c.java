package t1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ViewOffsetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public class c<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f5030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5031b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5032c;

    public c() {
        this.f5031b = 0;
        this.f5032c = 0;
    }

    public int E() {
        d dVar = this.f5030a;
        if (dVar != null) {
            return dVar.c();
        }
        return 0;
    }

    public void F(CoordinatorLayout coordinatorLayout, V v3, int i3) {
        coordinatorLayout.M(v3, i3);
    }

    public boolean G(int i3) {
        d dVar = this.f5030a;
        if (dVar != null) {
            return dVar.f(i3);
        }
        this.f5031b = i3;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v3, int i3) {
        F(coordinatorLayout, v3, i3);
        if (this.f5030a == null) {
            this.f5030a = new d(v3);
        }
        this.f5030a.d();
        this.f5030a.a();
        int i4 = this.f5031b;
        if (i4 != 0) {
            this.f5030a.f(i4);
            this.f5031b = 0;
        }
        int i5 = this.f5032c;
        if (i5 == 0) {
            return true;
        }
        this.f5030a.e(i5);
        this.f5032c = 0;
        return true;
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5031b = 0;
        this.f5032c = 0;
    }
}
