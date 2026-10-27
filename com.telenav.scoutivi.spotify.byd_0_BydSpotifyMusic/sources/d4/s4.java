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

/* JADX INFO: compiled from: NowPlayingBarBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class s4 extends o4 implements a.InterfaceC0071a {

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private static final ViewDataBinding.i f5786b0 = null;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private static final SparseIntArray f5787c0;
    private final FrameLayout Q;
    private final ConstraintLayout R;
    private final ImageView S;
    private final ImageView T;
    private final ImageView U;
    private final ImageView V;
    private final View.OnClickListener W;
    private final View.OnClickListener X;
    private final View.OnClickListener Y;
    private final View.OnClickListener Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private long f5788a0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f5787c0 = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.now_playing_bar_cover, 13);
        sparseIntArray.put(com.vivid.spotify.t.title_layout, 14);
    }

    public s4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 15, f5786b0, f5787c0));
    }

    private boolean e0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 1048576;
        }
        return true;
    }

    private boolean f0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 1;
        }
        return true;
    }

    private boolean g0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 32;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 16;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 4;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 128;
        }
        return true;
    }

    private boolean k0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 4096;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 32768;
        }
        return true;
    }

    private boolean m0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 8;
        }
        return true;
    }

    private boolean n0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 262144;
        }
        return true;
    }

    private boolean o0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 65536;
        }
        return true;
    }

    private boolean p0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 8192;
        }
        return true;
    }

    private boolean q0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 2048;
        }
        return true;
    }

    private boolean r0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 524288;
        }
        return true;
    }

    private boolean s0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 131072;
        }
        return true;
    }

    private boolean t0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 2097152;
        }
        return true;
    }

    private boolean u0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 512;
        }
        return true;
    }

    private boolean v0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 1024;
        }
        return true;
    }

    private boolean w0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 16384;
        }
        return true;
    }

    private boolean x0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 64;
        }
        return true;
    }

    private boolean y0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 2;
        }
        return true;
    }

    private boolean z0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5788a0 |= 256;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.f5788a0 != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.f5788a0 = 8388608L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return f0((androidx.databinding.l) obj, i8);
            case 1:
                return y0((androidx.databinding.l) obj, i8);
            case 2:
                return i0((ObservableBoolean) obj, i8);
            case 3:
                return m0((ObservableBoolean) obj, i8);
            case 4:
                return h0((ObservableBoolean) obj, i8);
            case 5:
                return g0((ObservableInt) obj, i8);
            case 6:
                return x0((ObservableInt) obj, i8);
            case 7:
                return j0((androidx.databinding.l) obj, i8);
            case 8:
                return z0((androidx.databinding.l) obj, i8);
            case 9:
                return u0((androidx.databinding.l) obj, i8);
            case 10:
                return v0((ObservableBoolean) obj, i8);
            case 11:
                return q0((ObservableBoolean) obj, i8);
            case 12:
                return k0((ObservableInt) obj, i8);
            case 13:
                return p0((ObservableBoolean) obj, i8);
            case 14:
                return w0((ObservableBoolean) obj, i8);
            case 15:
                return l0((ObservableBoolean) obj, i8);
            case 16:
                return o0((ObservableInt) obj, i8);
            case 17:
                return s0((ObservableInt) obj, i8);
            case 18:
                return n0((androidx.databinding.l) obj, i8);
            case 19:
                return r0((androidx.databinding.l) obj, i8);
            case 20:
                return e0((ObservableBoolean) obj, i8);
            case 21:
                return t0((ObservableBoolean) obj, i8);
            default:
                return false;
        }
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            NowPlayingViewModel nowPlayingViewModel = this.P;
            if (nowPlayingViewModel != null) {
                g4.h hVar = nowPlayingViewModel.barBottomControlAction2;
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
        if (i7 == 3) {
            NowPlayingViewModel nowPlayingViewModel3 = this.P;
            if (nowPlayingViewModel3 != null) {
                g4.h hVar3 = nowPlayingViewModel3.barBottomControlAction3;
                if (hVar3 != null) {
                    hVar3.f(view);
                    return;
                }
                return;
            }
            return;
        }
        if (i7 != 4) {
            return;
        }
        NowPlayingViewModel nowPlayingViewModel4 = this.P;
        if (nowPlayingViewModel4 != null) {
            g4.h hVar4 = nowPlayingViewModel4.barCollectControlAction;
            if (hVar4 != null) {
                hVar4.f(view);
            }
        }
    }

    @Override // d4.o4
    public void d0(NowPlayingViewModel nowPlayingViewModel) {
        this.P = nowPlayingViewModel;
        synchronized (this) {
            this.f5788a0 |= 4194304;
        }
        p(com.vivid.spotify.a.f5200e);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0640  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x066b  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x06ab  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x06c2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x06e1  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x072e  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x073b  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0746  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x0767  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x077f  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x07aa  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x07d7  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x07e5  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x07f2  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0800  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x080d  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x081d  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x083d  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x084c  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x088d  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x089c  */
    /* JADX WARN: Removed duplicated region for block: B:542:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00db A[PHI: r2
  0x00db: PHI (r2v58 long) = (r2v57 long), (r2v59 long) binds: [B:46:0x00ac, B:59:0x00d6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0152  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 2215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.s4.x():void");
    }

    private s4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 22, (FrameLayout) objArr[11], (FrameLayout) objArr[6], (FrameLayout) objArr[9], (FrameLayout) objArr[4], (View) objArr[13], (TextView) objArr[3], (TextView) objArr[2], (ProgressBar) objArr[7], (LinearLayoutCompat) objArr[14]);
        this.f5788a0 = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.Q = frameLayout;
        frameLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[1];
        this.R = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[10];
        this.S = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) objArr[12];
        this.T = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) objArr[5];
        this.U = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) objArr[8];
        this.V = imageView4;
        imageView4.setTag(null);
        this.L.setTag(null);
        this.M.setTag(null);
        this.N.setTag(null);
        X(view);
        this.W = new e4.a(this, 3);
        this.X = new e4.a(this, 1);
        this.Y = new e4.a(this, 4);
        this.Z = new e4.a(this, 2);
        K();
    }
}
