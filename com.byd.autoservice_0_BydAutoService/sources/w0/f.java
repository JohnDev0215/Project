package w0;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5634d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5635e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5638h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5639i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5631a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5636f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5637g = 0;

    public boolean a(RecyclerView.z zVar) {
        int i3 = this.f5633c;
        return i3 >= 0 && i3 < zVar.b();
    }

    public View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f5633c);
        this.f5633c += this.f5634d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f5632b + ", mCurrentPosition=" + this.f5633c + ", mItemDirection=" + this.f5634d + ", mLayoutDirection=" + this.f5635e + ", mStartLine=" + this.f5636f + ", mEndLine=" + this.f5637g + '}';
    }
}
