package c.b.a.a.r;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f2718a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2719b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2720c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f2718a = (View) aVar;
    }

    private void a() {
        ViewParent parent = this.f2718a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f2718a);
        }
    }

    public int b() {
        return this.f2720c;
    }

    public boolean c() {
        return this.f2719b;
    }

    public void d(Bundle bundle) {
        this.f2719b = bundle.getBoolean("expanded", false);
        this.f2720c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f2719b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f2719b);
        bundle.putInt("expandedComponentIdHint", this.f2720c);
        return bundle;
    }

    public void f(int i) {
        this.f2720c = i;
    }
}
