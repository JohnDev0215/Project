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

/* JADX INFO: compiled from: NowPlayingBarBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class p4 extends o4 implements a.InterfaceC0071a {
    private static final ViewDataBinding.i X = null;
    private static final SparseIntArray Y;
    private final FrameLayout Q;
    private final ConstraintLayout R;
    private final ImageView S;
    private final ImageView T;
    private final View.OnClickListener U;
    private final View.OnClickListener V;
    private long W;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        Y = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.now_playing_bar_cover, 9);
        sparseIntArray.put(com.vivid.spotify.t.title_layout, 10);
    }

    public p4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 11, X, Y));
    }

    private boolean e0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 8;
        }
        return true;
    }

    private boolean f0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 64;
        }
        return true;
    }

    private boolean g0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 16;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 256;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 2;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 4096;
        }
        return true;
    }

    private boolean k0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 2048;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 8192;
        }
        return true;
    }

    private boolean m0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 1024;
        }
        return true;
    }

    private boolean n0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 1;
        }
        return true;
    }

    private boolean o0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 512;
        }
        return true;
    }

    private boolean p0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 32;
        }
        return true;
    }

    private boolean q0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 4;
        }
        return true;
    }

    private boolean r0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.W |= 128;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.W != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.W = 32768L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return n0((ObservableBoolean) obj, i8);
            case 1:
                return i0((ObservableBoolean) obj, i8);
            case 2:
                return q0((androidx.databinding.l) obj, i8);
            case 3:
                return e0((ObservableBoolean) obj, i8);
            case 4:
                return g0((ObservableInt) obj, i8);
            case 5:
                return p0((ObservableInt) obj, i8);
            case 6:
                return f0((androidx.databinding.l) obj, i8);
            case 7:
                return r0((androidx.databinding.l) obj, i8);
            case 8:
                return h0((ObservableBoolean) obj, i8);
            case 9:
                return o0((ObservableBoolean) obj, i8);
            case 10:
                return m0((androidx.databinding.l) obj, i8);
            case 11:
                return k0((ObservableInt) obj, i8);
            case 12:
                return j0((androidx.databinding.l) obj, i8);
            case 13:
                return l0((ObservableBoolean) obj, i8);
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
        if (i7 != 2) {
            return;
        }
        NowPlayingViewModel nowPlayingViewModel2 = this.P;
        if (nowPlayingViewModel2 != null) {
            g4.h hVar2 = nowPlayingViewModel2.barPlayControlAction;
            if (hVar2 != null) {
                hVar2.f(view);
            }
        }
    }

    @Override // d4.o4
    public void d0(NowPlayingViewModel nowPlayingViewModel) {
        this.P = nowPlayingViewModel;
        synchronized (this) {
            this.W |= 16384;
        }
        p(com.vivid.spotify.a.f5200e);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:345:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e3 A[PHI: r2
  0x00e3: PHI (r2v35 long) = (r2v34 long), (r2v36 long) binds: [B:46:0x00b8, B:59:0x00de] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 1348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.p4.x():void");
    }

    private p4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 14, null, (FrameLayout) objArr[4], (FrameLayout) objArr[2], null, (View) objArr[9], (TextView) objArr[8], (TextView) objArr[7], (ProgressBar) objArr[5], (LinearLayoutCompat) objArr[10]);
        this.W = -1L;
        this.H.setTag(null);
        this.I.setTag(null);
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
        this.L.setTag(null);
        this.M.setTag(null);
        this.N.setTag(null);
        X(view);
        this.U = new e4.a(this, 2);
        this.V = new e4.a(this, 1);
        K();
    }
}
