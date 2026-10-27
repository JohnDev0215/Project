package b.h.m;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2639b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f2638a | this.f2639b;
    }

    public void b(View view, View view2, int i2) {
        c(view, view2, i2, 0);
    }

    public void c(View view, View view2, int i2, int i3) {
        if (i3 == 1) {
            this.f2639b = i2;
        } else {
            this.f2638a = i2;
        }
    }

    public void d(View view) {
        e(view, 0);
    }

    public void e(View view, int i2) {
        if (i2 == 1) {
            this.f2639b = 0;
        } else {
            this.f2638a = 0;
        }
    }
}
