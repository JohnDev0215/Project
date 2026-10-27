package d.b.a.a.r;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f3969a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3970b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3971c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f3969a = (View) aVar;
    }

    private void a() {
        ViewParent parent = this.f3969a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f3969a);
        }
    }

    public int b() {
        return this.f3971c;
    }

    public boolean c() {
        return this.f3970b;
    }

    public void d(Bundle bundle) {
        this.f3970b = bundle.getBoolean("expanded", false);
        this.f3971c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f3970b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f3970b);
        bundle.putInt("expandedComponentIdHint", this.f3971c);
        return bundle;
    }

    public void f(int i) {
        this.f3971c = i;
    }
}
