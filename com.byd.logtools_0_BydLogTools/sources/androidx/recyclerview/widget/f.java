package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1916c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1917d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1918e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1921h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1914a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1919f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f1920g = 0;

    f() {
    }

    boolean a(RecyclerView.z zVar) {
        int i = this.f1916c;
        return i >= 0 && i < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1916c);
        this.f1916c += this.f1917d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1915b + ", mCurrentPosition=" + this.f1916c + ", mItemDirection=" + this.f1917d + ", mLayoutDirection=" + this.f1918e + ", mStartLine=" + this.f1919f + ", mEndLine=" + this.f1920g + '}';
    }
}
