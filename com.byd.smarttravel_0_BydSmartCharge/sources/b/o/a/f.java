package b.o.a;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1399c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1400d;
    public int e;
    public boolean h;
    public boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1397a = true;
    public int f = 0;
    public int g = 0;

    public boolean a(RecyclerView.z zVar) {
        int i = this.f1399c;
        return i >= 0 && i < zVar.b();
    }

    public View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1399c);
        this.f1399c += this.f1400d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1398b + ", mCurrentPosition=" + this.f1399c + ", mItemDirection=" + this.f1400d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
