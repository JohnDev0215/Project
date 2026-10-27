package c.a.a.a.r;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f2332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2333b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2334c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f2332a = (View) aVar;
    }

    private void a() {
        ViewParent parent = this.f2332a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f2332a);
        }
    }

    public int b() {
        return this.f2334c;
    }

    public boolean c() {
        return this.f2333b;
    }

    public void d(Bundle bundle) {
        this.f2333b = bundle.getBoolean("expanded", false);
        this.f2334c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f2333b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f2333b);
        bundle.putInt("expandedComponentIdHint", this.f2334c);
        return bundle;
    }

    public void f(int i) {
        this.f2334c = i;
    }
}
