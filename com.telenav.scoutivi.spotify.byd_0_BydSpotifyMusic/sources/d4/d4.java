package d4;

import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.MediaCoverItemViewModel;
import e4.a;

/* JADX INFO: compiled from: MediaCoverItemBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d4 extends z3 implements a.InterfaceC0071a {
    private static final ViewDataBinding.i R = null;
    private static final SparseIntArray S = null;
    private final ConstraintLayout L;
    private final TextView M;
    private final ImageView N;
    private final View.OnClickListener O;
    private final View.OnClickListener P;
    private long Q;

    public d4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 7, R, S));
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 64;
        }
        return true;
    }

    private boolean i0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 16;
        }
        return true;
    }

    private boolean j0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 4;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 32;
        }
        return true;
    }

    private boolean l0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 1;
        }
        return true;
    }

    private boolean m0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 2;
        }
        return true;
    }

    private boolean n0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.Q != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.Q = 256L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return l0((ObservableInt) obj, i8);
            case 1:
                return m0((androidx.databinding.l) obj, i8);
            case 2:
                return j0((ObservableBoolean) obj, i8);
            case 3:
                return n0((androidx.databinding.l) obj, i8);
            case 4:
                return i0((androidx.databinding.l) obj, i8);
            case 5:
                return k0((ObservableBoolean) obj, i8);
            case 6:
                return h0((ObservableBoolean) obj, i8);
            default:
                return false;
        }
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            MediaCoverItemViewModel mediaCoverItemViewModel = this.K;
            if (mediaCoverItemViewModel != null) {
                mediaCoverItemViewModel.onItemClicked(view);
                return;
            }
            return;
        }
        if (i7 != 2) {
            return;
        }
        MediaCoverItemViewModel mediaCoverItemViewModel2 = this.K;
        if (mediaCoverItemViewModel2 != null) {
            mediaCoverItemViewModel2.onPlayButtonClicked();
        }
    }

    @Override // d4.z3
    public void g0(MediaCoverItemViewModel mediaCoverItemViewModel) {
        this.K = mediaCoverItemViewModel;
        synchronized (this) {
            this.Q |= 128;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ad A[PHI: r2
  0x00ad: PHI (r2v10 long) = (r2v9 long), (r2v19 long) binds: [B:41:0x0087, B:54:0x00a7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0116  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.d4.x():void");
    }

    private d4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 7, (LinearLayout) objArr[5], (ImageView) objArr[1], (TextView) objArr[4], (TextView) objArr[3]);
        this.Q = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.L = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) objArr[2];
        this.M = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) objArr[6];
        this.N = imageView;
        imageView.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        X(view);
        this.O = new e4.a(this, 1);
        this.P = new e4.a(this, 2);
        K();
    }
}
