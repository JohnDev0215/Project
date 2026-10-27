package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.rootui.GridViewModel;
import e4.a;

/* JADX INFO: compiled from: FragmentMainGridBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class o1 extends k1 implements a.InterfaceC0071a {
    private static final ViewDataBinding.i R;
    private static final SparseIntArray S;
    private final ConstraintLayout M;
    private final p N;
    private final TextView O;
    private final View.OnClickListener P;
    private long Q;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(8);
        R = iVar;
        iVar.a(0, new String[]{"common_error_layout"}, new int[]{6}, new int[]{com.vivid.spotify.v.common_error_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        S = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.topBg, 7);
    }

    public o1(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 8, R, S));
    }

    private boolean g0(androidx.lifecycle.t<Boolean> tVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 2;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 4;
        }
        return true;
    }

    private boolean i0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.Q != 0) {
                return true;
            }
            return this.N.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.Q = 16L;
        }
        this.N.K();
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return i0((androidx.databinding.l) obj, i8);
        }
        if (i7 == 1) {
            return g0((androidx.lifecycle.t) obj, i8);
        }
        if (i7 != 2) {
            return false;
        }
        return h0((ObservableBoolean) obj, i8);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void W(androidx.lifecycle.o oVar) {
        super.W(oVar);
        this.N.W(oVar);
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        GridViewModel gridViewModel = this.L;
        if (gridViewModel != null) {
            gridViewModel.reloadData();
        }
    }

    @Override // d4.k1
    public void f0(GridViewModel gridViewModel) {
        this.L = gridViewModel;
        synchronized (this) {
            this.Q |= 8;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00be  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.o1.x():void");
    }

    private o1(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 3, (LinearLayout) objArr[3], (ImageView) objArr[5], (ShimmerRecyclerView) objArr[2], (ImageView) objArr[7], (View) objArr[1]);
        this.Q = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        this.I.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.M = constraintLayout;
        constraintLayout.setTag(null);
        p pVar = (p) objArr[6];
        this.N = pVar;
        V(pVar);
        TextView textView = (TextView) objArr[4];
        this.O = textView;
        textView.setTag(null);
        this.K.setTag(null);
        X(view);
        this.P = new e4.a(this, 1);
        K();
    }
}
