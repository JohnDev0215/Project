package l0;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: NestedScrollingParentHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3646b;

    public o(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f3645a | this.f3646b;
    }

    public void b(View view, View view2, int i3) {
        c(view, view2, i3, 0);
    }

    public void c(View view, View view2, int i3, int i4) {
        if (i4 == 1) {
            this.f3646b = i3;
        } else {
            this.f3645a = i3;
        }
    }

    public void d(View view, int i3) {
        if (i3 == 1) {
            this.f3646b = 0;
        } else {
            this.f3645a = 0;
        }
    }
}
