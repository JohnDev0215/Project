package d4;

import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.details.DetailViewModel;
import e4.a;

/* JADX INFO: compiled from: FragmentDetailsListBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class z0 extends v0 implements a.InterfaceC0071a {

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private static final ViewDataBinding.i f5936l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private static final SparseIntArray f5937m0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private final TextView f5938c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private final TextView f5939d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private final TextView f5940e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private final TextView f5941f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private final ImageView f5942g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private final View.OnClickListener f5943h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private final View.OnClickListener f5944i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private final View.OnClickListener f5945j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private long f5946k0;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(22);
        f5936l0 = iVar;
        iVar.a(0, new String[]{"common_error_layout"}, new int[]{17}, new int[]{com.vivid.spotify.v.common_error_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        f5937m0 = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.header, 18);
        sparseIntArray.put(com.vivid.spotify.t.topBg, 19);
        sparseIntArray.put(com.vivid.spotify.t.logo, 20);
        sparseIntArray.put(com.vivid.spotify.t.btn_back, 21);
    }

    public z0(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 22, f5936l0, f5937m0));
    }

    private boolean g0(ViewDataBinding viewDataBinding, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 32;
        }
        return true;
    }

    private boolean h0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 256;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 16;
        }
        return true;
    }

    private boolean j0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 64;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 128;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 1;
        }
        return true;
    }

    private boolean m0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 2;
        }
        return true;
    }

    private boolean n0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 4096;
        }
        return true;
    }

    private boolean o0(androidx.lifecycle.t<Boolean> tVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 1024;
        }
        return true;
    }

    private boolean p0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 8;
        }
        return true;
    }

    private boolean q0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 2048;
        }
        return true;
    }

    private boolean r0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 512;
        }
        return true;
    }

    private boolean s0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5946k0 |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.f5946k0 != 0) {
                return true;
            }
            return this.I.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.f5946k0 = 16384L;
        }
        this.I.K();
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return l0((ObservableBoolean) obj, i8);
            case 1:
                return m0((ObservableBoolean) obj, i8);
            case 2:
                return s0((androidx.databinding.l) obj, i8);
            case 3:
                return p0((ObservableInt) obj, i8);
            case 4:
                return i0((ObservableBoolean) obj, i8);
            case 5:
                return g0((ViewDataBinding) obj, i8);
            case 6:
                return j0((ObservableBoolean) obj, i8);
            case 7:
                return k0((ObservableBoolean) obj, i8);
            case 8:
                return h0((androidx.databinding.l) obj, i8);
            case 9:
                return r0((androidx.databinding.l) obj, i8);
            case 10:
                return o0((androidx.lifecycle.t) obj, i8);
            case 11:
                return q0((ObservableBoolean) obj, i8);
            case 12:
                return n0((ObservableBoolean) obj, i8);
            default:
                return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void W(androidx.lifecycle.o oVar) {
        super.W(oVar);
        this.I.W(oVar);
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            DetailViewModel detailViewModel = this.f5798b0;
            if (detailViewModel != null) {
                detailViewModel.toggleFavStatus();
                return;
            }
            return;
        }
        if (i7 == 2) {
            DetailViewModel detailViewModel2 = this.f5798b0;
            if (detailViewModel2 != null) {
                detailViewModel2.onPlayAllButtonClicked();
                return;
            }
            return;
        }
        if (i7 != 3) {
            return;
        }
        DetailViewModel detailViewModel3 = this.f5798b0;
        if (detailViewModel3 != null) {
            detailViewModel3.reloadData();
        }
    }

    @Override // d4.v0
    public void f0(DetailViewModel detailViewModel) {
        this.f5798b0 = detailViewModel;
        synchronized (this) {
            this.f5946k0 |= 8192;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0150  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 1729
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.z0.x():void");
    }

    private z0(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 13, (LinearLayout) objArr[21], (LinearLayout) objArr[8], (ViewDataBinding) objArr[17], (FrameLayout) objArr[6], (View) objArr[18], (ImageView) objArr[3], (ShimmerRecyclerView) objArr[16], (ImageView) objArr[20], (ImageView) objArr[13], (MotionLayout) objArr[0], null, (ShimmerLayout) objArr[15], (View) objArr[10], (TabLayout) objArr[14], (View) objArr[4], null, (ImageView) objArr[19], (ConstraintLayout) objArr[12], null, null, (ViewPager2) objArr[1]);
        this.f5946k0 = -1L;
        this.H.setTag(null);
        V(this.I);
        this.J.setTag(null);
        this.L.setTag(null);
        this.M.setTag(null);
        TextView textView = (TextView) objArr[11];
        this.f5938c0 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) objArr[2];
        this.f5939d0 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) objArr[5];
        this.f5940e0 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) objArr[7];
        this.f5941f0 = textView4;
        textView4.setTag(null);
        ImageView imageView = (ImageView) objArr[9];
        this.f5942g0 = imageView;
        imageView.setTag(null);
        this.O.setTag(null);
        this.P.setTag(null);
        this.R.setTag(null);
        this.S.setTag(null);
        this.T.setTag(null);
        this.U.setTag(null);
        this.X.setTag(null);
        this.f5797a0.setTag(null);
        X(view);
        this.f5943h0 = new e4.a(this, 3);
        this.f5944i0 = new e4.a(this, 1);
        this.f5945j0 = new e4.a(this, 2);
        K();
    }
}
