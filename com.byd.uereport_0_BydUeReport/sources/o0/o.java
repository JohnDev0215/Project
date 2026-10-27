package o0;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6694b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f6693a | this.f6694b;
    }

    public void b(View view, View view2, int i8) {
        c(view, view2, i8, 0);
    }

    public void c(View view, View view2, int i8, int i9) {
        if (i9 == 1) {
            this.f6694b = i8;
        } else {
            this.f6693a = i8;
        }
    }

    public void d(View view) {
        e(view, 0);
    }

    public void e(View view, int i8) {
        if (i8 == 1) {
            this.f6694b = 0;
        } else {
            this.f6693a = 0;
        }
    }
}
