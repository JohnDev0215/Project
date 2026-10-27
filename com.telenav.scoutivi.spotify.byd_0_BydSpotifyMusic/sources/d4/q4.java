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
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import e4.a;

/* JADX INFO: compiled from: NowPlayingBarBindingW600dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class q4 extends o4 implements a.InterfaceC0071a {
    private static final ViewDataBinding.i Z = null;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private static final SparseIntArray f5784a0;
    private final FrameLayout Q;
    private final ConstraintLayout R;
    private final ImageView S;
    private final ImageView T;
    private final ImageView U;
    private final View.OnClickListener V;
    private final View.OnClickListener W;
    private final View.OnClickListener X;
    private long Y;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f5784a0 = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.now_playing_bar_cover, 11);
        sparseIntArray.put(com.vivid.spotify.t.title_layout, 12);
    }

    public q4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 13, Z, f5784a0));
    }

    private boolean e0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 131072;
        }
        return true;
    }

    private boolean f0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 2;
        }
        return true;
    }

    private boolean g0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 128;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 64;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 16;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 512;
        }
        return true;
    }

    private boolean k0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 32;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 2048;
        }
        return true;
    }

    private boolean m0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 4;
        }
        return true;
    }

    private boolean n0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 32768;
        }
        return true;
    }

    private boolean o0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 16384;
        }
        return true;
    }

    private boolean p0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 65536;
        }
        return true;
    }

    private boolean q0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 8192;
        }
        return true;
    }

    private boolean r0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 1;
        }
        return true;
    }

    private boolean s0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 4096;
        }
        return true;
    }

    private boolean t0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 256;
        }
        return true;
    }

    private boolean u0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 8;
        }
        return true;
    }

    private boolean v0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Y |= 1024;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.Y != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.Y = 524288L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return r0((ObservableBoolean) obj, i8);
            case 1:
                return f0((androidx.databinding.l) obj, i8);
            case 2:
                return m0((ObservableBoolean) obj, i8);
            case 3:
                return u0((androidx.databinding.l) obj, i8);
            case 4:
                return i0((ObservableBoolean) obj, i8);
            case 5:
                return k0((ObservableInt) obj, i8);
            case 6:
                return h0((ObservableBoolean) obj, i8);
            case 7:
                return g0((ObservableInt) obj, i8);
            case 8:
                return t0((ObservableInt) obj, i8);
            case 9:
                return j0((androidx.databinding.l) obj, i8);
            case 10:
                return v0((androidx.databinding.l) obj, i8);
            case 11:
                return l0((ObservableBoolean) obj, i8);
            case 12:
                return s0((ObservableBoolean) obj, i8);
            case 13:
                return q0((androidx.databinding.l) obj, i8);
            case 14:
                return o0((ObservableInt) obj, i8);
            case 15:
                return n0((androidx.databinding.l) obj, i8);
            case 16:
                return p0((ObservableBoolean) obj, i8);
            case 17:
                return e0((ObservableBoolean) obj, i8);
            default:
                return false;
        }
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            NowPlayingViewModel nowPlayingViewModel = this.P;
            if (nowPlayingViewModel != null) {
                g4.h hVar = nowPlayingViewModel.barBottomControlAction3;
                if (hVar != null) {
                    hVar.f(view);
                    return;
                }
                return;
            }
            return;
        }
        if (i7 == 2) {
            NowPlayingViewModel nowPlayingViewModel2 = this.P;
            if (nowPlayingViewModel2 != null) {
                g4.h hVar2 = nowPlayingViewModel2.barPlayControlAction;
                if (hVar2 != null) {
                    hVar2.f(view);
                    return;
                }
                return;
            }
            return;
        }
        if (i7 != 3) {
            return;
        }
        NowPlayingViewModel nowPlayingViewModel3 = this.P;
        if (nowPlayingViewModel3 != null) {
            g4.h hVar3 = nowPlayingViewModel3.barBottomControlAction2;
            if (hVar3 != null) {
                hVar3.f(view);
            }
        }
    }

    @Override // d4.o4
    public void d0(NowPlayingViewModel nowPlayingViewModel) {
        this.P = nowPlayingViewModel;
        synchronized (this) {
            this.Y |= 262144;
        }
        p(com.vivid.spotify.a.f5200e);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x05f4  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0604  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0611  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x061c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x068d  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x06b9  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0709  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x0716  */
    /* JADX WARN: Removed duplicated region for block: B:444:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0150  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 1825
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.q4.x():void");
    }

    private q4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 18, null, (FrameLayout) objArr[4], (FrameLayout) objArr[2], (FrameLayout) objArr[7], (View) objArr[11], (TextView) objArr[10], (TextView) objArr[9], (ProgressBar) objArr[5], (LinearLayoutCompat) objArr[12]);
        this.Y = -1L;
        this.H.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.Q = frameLayout;
        frameLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[1];
        this.R = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[3];
        this.S = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) objArr[6];
        this.T = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) objArr[8];
        this.U = imageView3;
        imageView3.setTag(null);
        this.L.setTag(null);
        this.M.setTag(null);
        this.N.setTag(null);
        X(view);
        this.V = new e4.a(this, 2);
        this.W = new e4.a(this, 1);
        this.X = new e4.a(this, 3);
        K();
    }
}
