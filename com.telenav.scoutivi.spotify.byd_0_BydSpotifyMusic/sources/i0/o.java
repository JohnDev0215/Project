package i0;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f6879a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f6880b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f6879a | this.f6880b;
    }

    public void b(View view, View view2, int i7) {
        c(view, view2, i7, 0);
    }

    public void c(View view, View view2, int i7, int i8) {
        if (i8 == 1) {
            this.f6880b = i7;
        } else {
            this.f6879a = i7;
        }
    }

    public void d(View view, int i7) {
        if (i7 == 1) {
            this.f6880b = 0;
        } else {
            this.f6879a = 0;
        }
    }
}
