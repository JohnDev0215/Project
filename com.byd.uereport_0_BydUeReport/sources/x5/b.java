package x5;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: ExpandableWidgetHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f9091a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f9092b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f9093c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f9091a = (View) aVar;
    }

    public final void a() {
        ViewParent parent = this.f9091a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).p(this.f9091a);
        }
    }

    public int b() {
        return this.f9093c;
    }

    public boolean c() {
        return this.f9092b;
    }

    public void d(Bundle bundle) {
        this.f9092b = bundle.getBoolean("expanded", false);
        this.f9093c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.f9092b) {
            a();
        }
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.f9092b);
        bundle.putInt("expandedComponentIdHint", this.f9093c);
        return bundle;
    }

    public void f(int i8) {
        this.f9093c = i8;
    }
}
