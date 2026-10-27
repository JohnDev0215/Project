package c1;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2261c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2262d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2263e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2266h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2267i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2259a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2264f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2265g = 0;

    public boolean a(RecyclerView.z zVar) {
        int i8 = this.f2261c;
        return i8 >= 0 && i8 < zVar.b();
    }

    public View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f2261c);
        this.f2261c += this.f2262d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f2260b + ", mCurrentPosition=" + this.f2261c + ", mItemDirection=" + this.f2262d + ", mLayoutDirection=" + this.f2263e + ", mStartLine=" + this.f2264f + ", mEndLine=" + this.f2265g + '}';
    }
}
