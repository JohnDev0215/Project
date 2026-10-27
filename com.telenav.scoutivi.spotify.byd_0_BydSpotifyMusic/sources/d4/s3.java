package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.spotify.auto.parcel.PlaybackSpeedActionParcelable;
import com.vivid.spotify.nowplaying.NowPlayingFragment;

/* JADX INFO: compiled from: ListSpeedBindingW600dpH900dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class s3 extends p3 {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M;
    private final FrameLayout J;
    private long K;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        M = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.title, 2);
    }

    public s3(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, L, M));
    }

    private boolean e0(androidx.databinding.k<PlaybackSpeedActionParcelable> kVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.K != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.K = 4L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 != 0) {
            return false;
        }
        return e0((androidx.databinding.k) obj, i8);
    }

    @Override // d4.p3
    public void d0(com.vivid.spotify.nowplaying.k kVar) {
        this.I = kVar;
        synchronized (this) {
            this.K |= 2;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        synchronized (this) {
            j7 = this.K;
            this.K = 0L;
        }
        com.vivid.spotify.nowplaying.k kVar = this.I;
        long j8 = j7 & 7;
        if (j8 != 0) {
            kVar = kVar != null ? kVar.f5342a : null;
            b0(0, kVar);
        }
        if (j8 != 0) {
            NowPlayingFragment.X1(this.G, kVar);
        }
    }

    private s3(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1, (RecyclerView) objArr[1], (TextView) objArr[2]);
        this.K = -1L;
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.J = frameLayout;
        frameLayout.setTag(null);
        this.G.setTag(null);
        X(view);
        K();
    }
}
