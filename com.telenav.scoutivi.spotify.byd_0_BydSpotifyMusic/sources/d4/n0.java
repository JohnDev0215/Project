package d4;

import android.support.v4.media.MediaBrowserCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.rootui.category.GridViewModel;
import e4.a;
import java.util.List;

/* JADX INFO: compiled from: FragmentCategoryGridBindingW600dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class n0 extends l0 implements a.InterfaceC0071a {
    private static final ViewDataBinding.i N;
    private static final SparseIntArray O;
    private final RelativeLayout J;
    private final p K;
    private final View.OnClickListener L;
    private long M;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(3);
        N = iVar;
        iVar.a(0, new String[]{"common_error_layout"}, new int[]{2}, new int[]{com.vivid.spotify.v.common_error_layout});
        O = null;
    }

    public n0(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, N, O));
    }

    private boolean h0(LiveData<Boolean> liveData, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.M |= 1;
        }
        return true;
    }

    private boolean i0(LiveData<List<MediaBrowserCompat.MediaItem>> liveData, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.M |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.M != 0) {
                return true;
            }
            return this.K.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.M = 64L;
        }
        this.K.K();
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return h0((LiveData) obj, i8);
        }
        if (i7 != 1) {
            return false;
        }
        return i0((LiveData) obj, i8);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void W(androidx.lifecycle.o oVar) {
        super.W(oVar);
        this.K.W(oVar);
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        GridViewModel gridViewModel = this.H;
        if (gridViewModel != null) {
            gridViewModel.refresh();
        }
    }

    @Override // d4.l0
    public void f0(RecyclerView.n nVar) {
        this.I = nVar;
    }

    @Override // d4.l0
    public void g0(GridViewModel gridViewModel) {
        this.H = gridViewModel;
        synchronized (this) {
            this.M |= 16;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004d  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            r14 = this;
            monitor-enter(r14)
            long r0 = r14.M     // Catch: java.lang.Throwable -> L84
            r2 = 0
            r14.M = r2     // Catch: java.lang.Throwable -> L84
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L84
            com.vivid.spotify.rootui.category.GridViewModel r4 = r14.H
            r5 = 83
            long r5 = r5 & r0
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            r6 = 1
            r7 = 82
            r9 = 81
            r11 = 0
            if (r5 == 0) goto L4f
            long r12 = r0 & r9
            int r5 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r5 == 0) goto L32
            if (r4 == 0) goto L24
            androidx.lifecycle.LiveData r5 = r4.observerError()
            goto L25
        L24:
            r5 = r11
        L25:
            r12 = 0
            r14.Z(r12, r5)
            if (r5 == 0) goto L33
            java.lang.Object r12 = r5.e()
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L33
        L32:
            r5 = r11
        L33:
            long r12 = r0 & r7
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 == 0) goto L4d
            if (r4 == 0) goto L40
            androidx.lifecycle.LiveData r4 = r4.observerList()
            goto L41
        L40:
            r4 = r11
        L41:
            r14.Z(r6, r4)
            if (r4 == 0) goto L4d
            java.lang.Object r4 = r4.e()
            java.util.List r4 = (java.util.List) r4
            goto L51
        L4d:
            r4 = r11
            goto L51
        L4f:
            r4 = r11
            r5 = r4
        L51:
            r12 = 64
            long r12 = r12 & r0
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 == 0) goto L69
            com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView r12 = r14.G
            d4.a0.d(r12, r11)
            com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView r11 = r14.G
            com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.F1(r11, r6)
            d4.p r6 = r14.K
            android.view.View$OnClickListener r11 = r14.L
            r6.e0(r11)
        L69:
            long r6 = r0 & r7
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L74
            com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView r6 = r14.G
            com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView.M1(r6, r4)
        L74:
            long r0 = r0 & r9
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L7e
            d4.p r0 = r14.K
            r0.d0(r5)
        L7e:
            d4.p r0 = r14.K
            androidx.databinding.ViewDataBinding.z(r0)
            return
        L84:
            r0 = move-exception
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L84
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.n0.x():void");
    }

    private n0(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 2, (ShimmerRecyclerView) objArr[1]);
        this.M = -1L;
        this.G.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.J = relativeLayout;
        relativeLayout.setTag(null);
        p pVar = (p) objArr[2];
        this.K = pVar;
        V(pVar);
        X(view);
        this.L = new e4.a(this, 1);
        K();
    }
}
