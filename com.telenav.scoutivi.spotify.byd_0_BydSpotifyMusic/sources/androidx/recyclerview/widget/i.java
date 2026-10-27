package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f3147b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3149d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f3150e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f3153h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f3154i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f3146a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f3151f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f3152g = 0;

    i() {
    }

    boolean a(RecyclerView.z zVar) {
        int i7 = this.f3148c;
        return i7 >= 0 && i7 < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f3148c);
        this.f3148c += this.f3149d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f3147b + ", mCurrentPosition=" + this.f3148c + ", mItemDirection=" + this.f3149d + ", mLayoutDirection=" + this.f3150e + ", mStartLine=" + this.f3151f + ", mEndLine=" + this.f3152g + '}';
    }
}
