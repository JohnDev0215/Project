package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1991b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1992c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1993d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1994e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1997h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f1998i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1990a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1995f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f1996g = 0;

    f() {
    }

    boolean a(RecyclerView.z zVar) {
        int i2 = this.f1992c;
        return i2 >= 0 && i2 < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1992c);
        this.f1992c += this.f1993d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1991b + ", mCurrentPosition=" + this.f1992c + ", mItemDirection=" + this.f1993d + ", mLayoutDirection=" + this.f1994e + ", mStartLine=" + this.f1995f + ", mEndLine=" + this.f1996g + '}';
    }
}
