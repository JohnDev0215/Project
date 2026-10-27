package c.b.a.a.b0;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f3697a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3698b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3699c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f3697a = (View) aVar;
    }

    public final void a() {
        ViewParent parent = this.f3697a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f3697a);
        }
    }

    public int b() {
        return this.f3699c;
    }

    public boolean c() {
        return this.f3698b;
    }

    public void d(Bundle bundle) {
        this.f3698b = bundle.getBoolean("expanded", false);
        this.f3699c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f3698b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f3698b);
        bundle.putInt("expandedComponentIdHint", this.f3699c);
        return bundle;
    }

    public void f(int i2) {
        this.f3699c = i2;
    }
}
