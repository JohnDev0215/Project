package c.b.a.a.o;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1742b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1743c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f1741a = (View) aVar;
    }

    public final void a() {
        ViewParent parent = this.f1741a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f1741a);
        }
    }

    public int b() {
        return this.f1743c;
    }

    public boolean c() {
        return this.f1742b;
    }

    public void d(Bundle bundle) {
        this.f1742b = bundle.getBoolean("expanded", false);
        this.f1743c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f1742b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f1742b);
        bundle.putInt("expandedComponentIdHint", this.f1743c);
        return bundle;
    }

    public void f(int i) {
        this.f1743c = i;
    }
}
