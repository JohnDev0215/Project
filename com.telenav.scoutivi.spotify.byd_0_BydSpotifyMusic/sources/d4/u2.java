package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.labels.LabelsView;
import com.vivid.spotify.common.ui.BaseTabLayout;
import com.vivid.spotify.search.SearchViewModel;
import e4.a;
import java.util.List;

/* JADX INFO: compiled from: FragmentSearchBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class u2 extends t2 implements a.InterfaceC0071a {

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private static final ViewDataBinding.i f5791d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private static final SparseIntArray f5792e0;
    private final TextView X;
    private final FrameLayout Y;
    private final View.OnClickListener Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private final View.OnClickListener f5793a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private androidx.databinding.h f5794b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private long f5795c0;

    /* JADX INFO: compiled from: FragmentSearchBindingImpl.java */
    class a implements androidx.databinding.h {
        a() {
        }

        @Override // androidx.databinding.h
        public void a() {
            String strA = n0.e.a(u2.this.G);
            SearchViewModel searchViewModel = u2.this.W;
            if (searchViewModel != null) {
                androidx.databinding.l<String> lVar = searchViewModel.input;
                if (lVar != null) {
                    lVar.r(strA);
                }
            }
        }
    }

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(16);
        f5791d0 = iVar;
        iVar.a(0, new String[]{"fragment_search_shimmer"}, new int[]{12}, new int[]{com.vivid.spotify.v.fragment_search_shimmer});
        SparseIntArray sparseIntArray = new SparseIntArray();
        f5792e0 = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.topBg, 13);
        sparseIntArray.put(com.vivid.spotify.t.input_layout, 14);
        sparseIntArray.put(com.vivid.spotify.t.icon_search, 15);
    }

    public u2(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 16, f5791d0, f5792e0));
    }

    private boolean h0(d3 d3Var, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5795c0 |= 2;
        }
        return true;
    }

    private boolean i0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5795c0 |= 8;
        }
        return true;
    }

    private boolean j0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5795c0 |= 4;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5795c0 |= 32;
        }
        return true;
    }

    private boolean l0(androidx.lifecycle.t<List<com.vivid.spotify.rootui.w>> tVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5795c0 |= 16;
        }
        return true;
    }

    private boolean m0(androidx.databinding.k<String> kVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5795c0 |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.f5795c0 != 0) {
                return true;
            }
            return this.R.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.f5795c0 = 128L;
        }
        this.R.K();
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return m0((androidx.databinding.k) obj, i8);
        }
        if (i7 == 1) {
            return h0((d3) obj, i8);
        }
        if (i7 == 2) {
            return j0((ObservableInt) obj, i8);
        }
        if (i7 == 3) {
            return i0((androidx.databinding.l) obj, i8);
        }
        if (i7 == 4) {
            return l0((androidx.lifecycle.t) obj, i8);
        }
        if (i7 != 5) {
            return false;
        }
        return k0((ObservableBoolean) obj, i8);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void W(androidx.lifecycle.o oVar) {
        super.W(oVar);
        this.R.W(oVar);
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            SearchViewModel searchViewModel = this.W;
            if (searchViewModel != null) {
                searchViewModel.clearInput();
                return;
            }
            return;
        }
        if (i7 != 2) {
            return;
        }
        SearchViewModel searchViewModel2 = this.W;
        if (searchViewModel2 != null) {
            searchViewModel2.clearSearchKeywords();
        }
    }

    @Override // d4.t2
    public void g0(SearchViewModel searchViewModel) {
        this.W = searchViewModel;
        synchronized (this) {
            this.f5795c0 |= 64;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0143 A[PHI: r6
  0x0143: PHI (r6v6 androidx.databinding.k<java.lang.String>) = (r6v5 androidx.databinding.k<java.lang.String>), (r6v9 androidx.databinding.k<java.lang.String>) binds: [B:101:0x012d, B:108:0x013f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01a3 A[PHI: r2
  0x01a3: PHI (r2v3 long) = (r2v2 long), (r2v13 long) binds: [B:135:0x018c, B:144:0x019d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01bf A[PHI: r2
  0x01bf: PHI (r2v5 long) = (r2v4 long), (r2v11 long) binds: [B:149:0x01a8, B:158:0x01b9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008d A[PHI: r2
  0x008d: PHI (r2v19 long) = (r2v18 long), (r2v26 long) binds: [B:35:0x0071, B:44:0x0087] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011b  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 631
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.u2.x():void");
    }

    private u2(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 6, (AppCompatEditText) objArr[2], null, (TextView) objArr[8], (TextView) objArr[10], (TextView) objArr[9], (LinearLayout) objArr[3], (ImageView) objArr[15], (View) objArr[14], (LabelsView) objArr[7], null, (ConstraintLayout) objArr[0], (d3) objArr[12], (BaseTabLayout) objArr[11], (TextView) objArr[4], (ImageView) objArr[13], (ViewPager2) objArr[1]);
        this.f5794b0 = new a();
        this.f5795c0 = -1L;
        this.G.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        this.O.setTag(null);
        TextView textView = (TextView) objArr[5];
        this.X = textView;
        textView.setTag(null);
        FrameLayout frameLayout = (FrameLayout) objArr[6];
        this.Y = frameLayout;
        frameLayout.setTag(null);
        this.Q.setTag(null);
        V(this.R);
        this.S.setTag(null);
        this.T.setTag(null);
        this.V.setTag(null);
        X(view);
        this.Z = new e4.a(this, 1);
        this.f5793a0 = new e4.a(this, 2);
        K();
    }
}
