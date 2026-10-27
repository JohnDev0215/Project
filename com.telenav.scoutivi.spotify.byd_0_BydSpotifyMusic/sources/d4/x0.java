package d4;

import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.details.DetailViewModel;
import e4.a;

/* JADX INFO: compiled from: FragmentDetailsListBindingW600dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class x0 extends v0 implements a.InterfaceC0071a {

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private static final ViewDataBinding.i f5866h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private static final SparseIntArray f5867i0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private final ImageView f5868c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private final View.OnClickListener f5869d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private final View.OnClickListener f5870e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private final View.OnClickListener f5871f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private long f5872g0;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(19);
        f5866h0 = iVar;
        iVar.a(0, new String[]{"detail_error_layout"}, new int[]{13}, new int[]{com.vivid.spotify.v.detail_error_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        f5867i0 = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.topBg, 14);
        sparseIntArray.put(com.vivid.spotify.t.logo, 15);
        sparseIntArray.put(com.vivid.spotify.t.rule_view_play_all, 16);
        sparseIntArray.put(com.vivid.spotify.t.title_layout, 17);
        sparseIntArray.put(com.vivid.spotify.t.btn_back, 18);
    }

    public x0(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 19, f5866h0, f5867i0));
    }

    private boolean g0(ViewDataBinding viewDataBinding, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 32;
        }
        return true;
    }

    private boolean h0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 256;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 16;
        }
        return true;
    }

    private boolean j0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 64;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 128;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 1;
        }
        return true;
    }

    private boolean m0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 2;
        }
        return true;
    }

    private boolean n0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 4096;
        }
        return true;
    }

    private boolean o0(androidx.lifecycle.t<Boolean> tVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 1024;
        }
        return true;
    }

    private boolean p0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 8;
        }
        return true;
    }

    private boolean q0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 2048;
        }
        return true;
    }

    private boolean r0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 512;
        }
        return true;
    }

    private boolean s0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5872g0 |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.f5872g0 != 0) {
                return true;
            }
            return this.I.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.f5872g0 = 16384L;
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
                detailViewModel.onPlayAllButtonClicked();
                return;
            }
            return;
        }
        if (i7 == 2) {
            DetailViewModel detailViewModel2 = this.f5798b0;
            if (detailViewModel2 != null) {
                detailViewModel2.reloadData();
                return;
            }
            return;
        }
        if (i7 != 3) {
            return;
        }
        DetailViewModel detailViewModel3 = this.f5798b0;
        if (detailViewModel3 != null) {
            detailViewModel3.toggleFavStatus();
        }
    }

    @Override // d4.v0
    public void f0(DetailViewModel detailViewModel) {
        this.f5798b0 = detailViewModel;
        synchronized (this) {
            this.f5872g0 |= 8192;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0312 A[PHI: r2
  0x0312: PHI (r2v3 long) = (r2v2 long), (r2v11 long) binds: [B:212:0x02f8, B:221:0x030c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x014a  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 1244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.x0.x():void");
    }

    private x0(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 13, (LinearLayout) objArr[18], (LinearLayout) objArr[9], (ViewDataBinding) objArr[13], null, null, (ImageView) objArr[2], (ShimmerRecyclerView) objArr[7], (ImageView) objArr[15], (ImageView) objArr[4], (MotionLayout) objArr[0], (View) objArr[16], (ShimmerLayout) objArr[3], (View) objArr[6], (TabLayout) objArr[8], (View) objArr[5], (LinearLayout) objArr[17], (ImageView) objArr[14], null, (TextView) objArr[11], (TextView) objArr[12], (ViewPager2) objArr[1]);
        this.f5872g0 = -1L;
        this.H.setTag(null);
        V(this.I);
        this.L.setTag(null);
        this.M.setTag(null);
        ImageView imageView = (ImageView) objArr[10];
        this.f5868c0 = imageView;
        imageView.setTag(null);
        this.O.setTag(null);
        this.P.setTag(null);
        this.R.setTag(null);
        this.S.setTag(null);
        this.T.setTag(null);
        this.U.setTag(null);
        this.Y.setTag(null);
        this.Z.setTag(null);
        this.f5797a0.setTag(null);
        X(view);
        this.f5869d0 = new e4.a(this, 3);
        this.f5870e0 = new e4.a(this, 1);
        this.f5871f0 = new e4.a(this, 2);
        K();
    }
}
