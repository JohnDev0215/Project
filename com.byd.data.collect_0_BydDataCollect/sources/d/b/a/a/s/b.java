package d.b.a.a.s;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f4705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4706b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4707c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f4705a = (View) aVar;
    }

    private void a() {
        ViewParent parent = this.f4705a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).m(this.f4705a);
        }
    }

    public int b() {
        return this.f4707c;
    }

    public boolean c() {
        return this.f4706b;
    }

    public void d(Bundle bundle) {
        this.f4706b = bundle.getBoolean("expanded", false);
        this.f4707c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f4706b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f4706b);
        bundle.putInt("expandedComponentIdHint", this.f4707c);
        return bundle;
    }

    public void f(int i2) {
        this.f4707c = i2;
    }
}
