package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1818b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1820d;
    int e;
    boolean h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1817a = true;
    int f = 0;
    int g = 0;

    f() {
    }

    boolean a(RecyclerView.z zVar) {
        int i = this.f1819c;
        return i >= 0 && i < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1819c);
        this.f1819c += this.f1820d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1818b + ", mCurrentPosition=" + this.f1819c + ", mItemDirection=" + this.f1820d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
