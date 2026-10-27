package b.g.k;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2722a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2723b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f2722a | this.f2723b;
    }

    public void b(View view, View view2, int i2) {
        c(view, view2, i2, 0);
    }

    public void c(View view, View view2, int i2, int i3) {
        if (i3 == 1) {
            this.f2723b = i2;
        } else {
            this.f2722a = i2;
        }
    }

    public void d(View view, int i2) {
        if (i2 == 1) {
            this.f2723b = 0;
        } else {
            this.f2722a = 0;
        }
    }
}
