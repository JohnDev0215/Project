package d4;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.BaseListItemViewModel;
import e4.a;

/* JADX INFO: compiled from: BaseListItemBindingW600dpH900dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends f implements a.InterfaceC0071a {
    private static final ViewDataBinding.i U = null;
    private static final SparseIntArray V = null;
    private final ConstraintLayout N;
    private final ImageView O;
    private final LinearLayoutCompat P;
    private final ProgressBar Q;
    private final View.OnClickListener R;
    private final View.OnClickListener S;
    private long T;

    public i(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 10, U, V));
    }

    private boolean h0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 1;
        }
        return true;
    }

    private boolean i0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 8192;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 512;
        }
        return true;
    }

    private boolean k0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 4;
        }
        return true;
    }

    private boolean l0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 128;
        }
        return true;
    }

    private boolean m0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 16;
        }
        return true;
    }

    private boolean n0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 2048;
        }
        return true;
    }

    private boolean o0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 1024;
        }
        return true;
    }

    private boolean p0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 32;
        }
        return true;
    }

    private boolean q0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 256;
        }
        return true;
    }

    private boolean r0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 2;
        }
        return true;
    }

    private boolean s0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 4096;
        }
        return true;
    }

    private boolean t0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 8;
        }
        return true;
    }

    private boolean u0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 64;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.T != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.T = 32768L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return h0((androidx.databinding.l) obj, i8);
            case 1:
                return r0((androidx.databinding.l) obj, i8);
            case 2:
                return k0((ObservableInt) obj, i8);
            case 3:
                return t0((androidx.databinding.l) obj, i8);
            case 4:
                return m0((ObservableBoolean) obj, i8);
            case 5:
                return p0((ObservableBoolean) obj, i8);
            case 6:
                return u0((androidx.databinding.l) obj, i8);
            case 7:
                return l0((androidx.databinding.l) obj, i8);
            case 8:
                return q0((ObservableInt) obj, i8);
            case 9:
                return j0((androidx.databinding.l) obj, i8);
            case 10:
                return o0((ObservableBoolean) obj, i8);
            case 11:
                return n0((ObservableBoolean) obj, i8);
            case 12:
                return s0((ObservableBoolean) obj, i8);
            case 13:
                return i0((ObservableInt) obj, i8);
            default:
                return false;
        }
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            BaseListItemViewModel baseListItemViewModel = this.M;
            if (baseListItemViewModel != null) {
                baseListItemViewModel.onItemClicked();
                return;
            }
            return;
        }
        if (i7 != 2) {
            return;
        }
        BaseListItemViewModel baseListItemViewModel2 = this.M;
        if (baseListItemViewModel2 != null) {
            baseListItemViewModel2.onRightButtonClicked();
        }
    }

    @Override // d4.f
    public void g0(BaseListItemViewModel baseListItemViewModel) {
        this.M = baseListItemViewModel;
        synchronized (this) {
            this.T |= 16384;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x038b A[PHI: r2
  0x038b: PHI (r2v5 long) = (r2v4 long), (r2v20 long) binds: [B:221:0x036f, B:230:0x0386] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:335:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0142  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 1340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.i.x():void");
    }

    private i(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 14, (ImageView) objArr[1], (ImageView) objArr[7], (ImageView) objArr[2], (FrameLayout) objArr[3], (TextView) objArr[8], (TextView) objArr[5]);
        this.T = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.N = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[4];
        this.O = imageView;
        imageView.setTag(null);
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) objArr[6];
        this.P = linearLayoutCompat;
        linearLayoutCompat.setTag(null);
        ProgressBar progressBar = (ProgressBar) objArr[9];
        this.Q = progressBar;
        progressBar.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        X(view);
        this.R = new e4.a(this, 2);
        this.S = new e4.a(this, 1);
        K();
    }
}
