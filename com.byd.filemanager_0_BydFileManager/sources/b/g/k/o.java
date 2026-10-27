package b.g.k;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1994a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1995b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f1994a | this.f1995b;
    }

    public void b(View view, View view2, int i) {
        c(view, view2, i, 0);
    }

    public void c(View view, View view2, int i, int i2) {
        if (i2 == 1) {
            this.f1995b = i;
        } else {
            this.f1994a = i;
        }
    }

    public void d(View view, int i) {
        if (i == 1) {
            this.f1995b = 0;
        } else {
            this.f1994a = 0;
        }
    }
}
