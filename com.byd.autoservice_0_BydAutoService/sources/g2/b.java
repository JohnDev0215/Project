package g2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f2945a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2946b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2947c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f2945a = (View) aVar;
    }

    public final void a() {
        ViewParent parent = this.f2945a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f2945a);
        }
    }

    public int b() {
        return this.f2947c;
    }

    public boolean c() {
        return this.f2946b;
    }

    public void d(Bundle bundle) {
        this.f2946b = bundle.getBoolean("expanded", false);
        this.f2947c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f2946b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f2946b);
        bundle.putInt("expandedComponentIdHint", this.f2947c);
        return bundle;
    }

    public void f(int i3) {
        this.f2947c = i3;
    }
}
