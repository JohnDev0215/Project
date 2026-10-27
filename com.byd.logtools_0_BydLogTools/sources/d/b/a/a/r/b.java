package d.b.a.a.r;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f4787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4788b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4789c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f4787a = (View) aVar;
    }

    private void a() {
        ViewParent parent = this.f4787a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f4787a);
        }
    }

    public int b() {
        return this.f4789c;
    }

    public boolean c() {
        return this.f4788b;
    }

    public void d(Bundle bundle) {
        this.f4788b = bundle.getBoolean("expanded", false);
        this.f4789c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f4788b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f4788b);
        bundle.putInt("expandedComponentIdHint", this.f4789c);
        return bundle;
    }

    public void f(int i) {
        this.f4789c = i;
    }
}
