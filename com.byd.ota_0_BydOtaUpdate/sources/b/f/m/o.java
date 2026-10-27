package b.f.m;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1786a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1787b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f1786a | this.f1787b;
    }

    public void b(View view, View view2, int i2) {
        c(view, view2, i2, 0);
    }

    public void c(View view, View view2, int i2, int i3) {
        if (i3 == 1) {
            this.f1787b = i2;
        } else {
            this.f1786a = i2;
        }
    }

    public void d(View view, int i2) {
        if (i2 == 1) {
            this.f1787b = 0;
        } else {
            this.f1786a = 0;
        }
    }
}
