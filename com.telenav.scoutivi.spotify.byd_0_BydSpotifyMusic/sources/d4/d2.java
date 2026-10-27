package d4;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import e4.a;
import e4.b;
import e4.c;
import n0.d;

/* JADX INFO: compiled from: FragmentNowPlayingRhdBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d2 extends z1 implements a.InterfaceC0071a, c.a, b.a {
    private static final SparseIntArray A0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    private static final ViewDataBinding.i f5738z0 = null;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private final ConstraintLayout f5739c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private final ImageView f5740d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private final ImageView f5741e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private final ImageView f5742f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private final ImageView f5743g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private final ImageView f5744h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private final ImageView f5745i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private final TextView f5746j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private final TextView f5747k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private final ImageView f5748l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private final ImageView f5749m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private final View.OnClickListener f5750n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private final View.OnClickListener f5751o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final d.InterfaceC0132d f5752p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private final View.OnClickListener f5753q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private final View.OnClickListener f5754r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private final View.OnClickListener f5755s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private final d.c f5756t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private final View.OnClickListener f5757u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private final View.OnClickListener f5758v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    private androidx.databinding.h f5759w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    private long f5760x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    private long f5761y0;

    /* JADX INFO: compiled from: FragmentNowPlayingRhdBindingW840dpH480dpImpl.java */
    class a implements androidx.databinding.h {
        a() {
        }

        @Override // androidx.databinding.h
        public void a() {
            int progress = d2.this.W.getProgress();
            NowPlayingViewModel nowPlayingViewModel = d2.this.f5948b0;
            if (nowPlayingViewModel != null) {
                ObservableInt observableInt = nowPlayingViewModel.position;
                if (observableInt != null) {
                    observableInt.r(progress);
                }
            }
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        A0 = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.cover, 24);
        sparseIntArray.put(com.vivid.spotify.t.now_playing_page_return_bar, 25);
        sparseIntArray.put(com.vivid.spotify.t.logo, 26);
        sparseIntArray.put(com.vivid.spotify.t.title, 27);
        sparseIntArray.put(com.vivid.spotify.t.time_info_layout, 28);
        sparseIntArray.put(com.vivid.spotify.t.bottom_menu, 29);
    }

    public d2(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 30, f5738z0, A0));
    }

    private boolean A0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 16;
        }
        return true;
    }

    private boolean B0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 8589934592L;
        }
        return true;
    }

    private boolean C0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 256;
        }
        return true;
    }

    private boolean D0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 1048576;
        }
        return true;
    }

    private boolean E0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 2048;
        }
        return true;
    }

    private boolean F0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 67108864;
        }
        return true;
    }

    private boolean G0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 2147483648L;
        }
        return true;
    }

    private boolean H0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 4;
        }
        return true;
    }

    private boolean I0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 262144;
        }
        return true;
    }

    private boolean J0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 524288;
        }
        return true;
    }

    private boolean K0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 4096;
        }
        return true;
    }

    private boolean L0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 16384;
        }
        return true;
    }

    private boolean M0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 131072;
        }
        return true;
    }

    private boolean N0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 2;
        }
        return true;
    }

    private boolean O0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 33554432;
        }
        return true;
    }

    private boolean P0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 274877906944L;
        }
        return true;
    }

    private boolean Q0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 128;
        }
        return true;
    }

    private boolean e0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 32768;
        }
        return true;
    }

    private boolean f0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 68719476736L;
        }
        return true;
    }

    private boolean g0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 64;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 16777216;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 1073741824;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 17179869184L;
        }
        return true;
    }

    private boolean k0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 4294967296L;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 65536;
        }
        return true;
    }

    private boolean m0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 8;
        }
        return true;
    }

    private boolean n0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 8388608;
        }
        return true;
    }

    private boolean o0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 536870912;
        }
        return true;
    }

    private boolean p0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 1024;
        }
        return true;
    }

    private boolean q0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 137438953472L;
        }
        return true;
    }

    private boolean r0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 512;
        }
        return true;
    }

    private boolean s0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 32;
        }
        return true;
    }

    private boolean t0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 8192;
        }
        return true;
    }

    private boolean u0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 1;
        }
        return true;
    }

    private boolean v0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 268435456;
        }
        return true;
    }

    private boolean w0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 134217728;
        }
        return true;
    }

    private boolean x0(ObservableInt observableInt, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 2097152;
        }
        return true;
    }

    private boolean y0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 34359738368L;
        }
        return true;
    }

    private boolean z0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.f5760x0 |= 4194304;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.f5760x0 == 0 && this.f5761y0 == 0) {
                return false;
            }
            return true;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.f5760x0 = 1099511627776L;
            this.f5761y0 = 0L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        switch (i7) {
            case 0:
                return u0((ObservableInt) obj, i8);
            case 1:
                return N0((ObservableInt) obj, i8);
            case 2:
                return H0((androidx.databinding.l) obj, i8);
            case 3:
                return m0((ObservableBoolean) obj, i8);
            case 4:
                return A0((ObservableInt) obj, i8);
            case 5:
                return s0((ObservableInt) obj, i8);
            case 6:
                return g0((ObservableInt) obj, i8);
            case 7:
                return Q0((androidx.databinding.l) obj, i8);
            case 8:
                return C0((ObservableBoolean) obj, i8);
            case 9:
                return r0((androidx.databinding.l) obj, i8);
            case 10:
                return p0((ObservableBoolean) obj, i8);
            case 11:
                return E0((ObservableBoolean) obj, i8);
            case 12:
                return K0((ObservableInt) obj, i8);
            case 13:
                return t0((ObservableBoolean) obj, i8);
            case 14:
                return L0((ObservableBoolean) obj, i8);
            case 15:
                return e0((ObservableBoolean) obj, i8);
            case 16:
                return l0((ObservableBoolean) obj, i8);
            case 17:
                return M0((androidx.databinding.l) obj, i8);
            case 18:
                return I0((ObservableInt) obj, i8);
            case 19:
                return J0((ObservableBoolean) obj, i8);
            case 20:
                return D0((ObservableBoolean) obj, i8);
            case 21:
                return x0((ObservableInt) obj, i8);
            case 22:
                return z0((androidx.databinding.l) obj, i8);
            case 23:
                return n0((androidx.databinding.l) obj, i8);
            case 24:
                return h0((ObservableBoolean) obj, i8);
            case 25:
                return O0((ObservableBoolean) obj, i8);
            case 26:
                return F0((ObservableInt) obj, i8);
            case 27:
                return w0((androidx.databinding.l) obj, i8);
            case 28:
                return v0((ObservableBoolean) obj, i8);
            case 29:
                return o0((ObservableInt) obj, i8);
            case 30:
                return i0((ObservableBoolean) obj, i8);
            case 31:
                return G0((ObservableBoolean) obj, i8);
            case 32:
                return k0((ObservableInt) obj, i8);
            case 33:
                return B0((ObservableBoolean) obj, i8);
            case 34:
                return j0((androidx.databinding.l) obj, i8);
            case 35:
                return y0((ObservableBoolean) obj, i8);
            case 36:
                return f0((androidx.databinding.l) obj, i8);
            case 37:
                return q0((ObservableBoolean) obj, i8);
            case 38:
                return P0((androidx.databinding.l) obj, i8);
            default:
                return false;
        }
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            NowPlayingViewModel nowPlayingViewModel = this.f5948b0;
            if (nowPlayingViewModel != null) {
                g4.h hVar = nowPlayingViewModel.collectControlAction;
                if (hVar != null) {
                    hVar.f(view);
                }
                return;
            }
            return;
        }
        if (i7 == 2) {
            NowPlayingViewModel nowPlayingViewModel2 = this.f5948b0;
            if (nowPlayingViewModel2 != null) {
                g4.h hVar2 = nowPlayingViewModel2.speedControlAction;
                if (hVar2 != null) {
                    hVar2.f(view);
                    return;
                }
                return;
            }
            return;
        }
        switch (i7) {
            case 5:
                NowPlayingViewModel nowPlayingViewModel3 = this.f5948b0;
                if (nowPlayingViewModel3 != null) {
                    g4.h hVar3 = nowPlayingViewModel3.bottomControlAction4;
                    if (hVar3 != null) {
                        hVar3.f(view);
                    }
                }
                break;
            case 6:
                NowPlayingViewModel nowPlayingViewModel4 = this.f5948b0;
                if (nowPlayingViewModel4 != null) {
                    g4.h hVar4 = nowPlayingViewModel4.bottomControlAction2;
                    if (hVar4 != null) {
                        hVar4.f(view);
                    }
                }
                break;
            case 7:
                NowPlayingViewModel nowPlayingViewModel5 = this.f5948b0;
                if (nowPlayingViewModel5 != null) {
                    g4.h hVar5 = nowPlayingViewModel5.playControlAction;
                    if (hVar5 != null) {
                        hVar5.f(view);
                    }
                }
                break;
            case 8:
                NowPlayingViewModel nowPlayingViewModel6 = this.f5948b0;
                if (nowPlayingViewModel6 != null) {
                    g4.h hVar6 = nowPlayingViewModel6.bottomControlAction3;
                    if (hVar6 != null) {
                        hVar6.f(view);
                    }
                }
                break;
            case 9:
                NowPlayingViewModel nowPlayingViewModel7 = this.f5948b0;
                if (nowPlayingViewModel7 != null) {
                    g4.h hVar7 = nowPlayingViewModel7.bottomControlAction5;
                    if (hVar7 != null) {
                        hVar7.f(view);
                    }
                }
                break;
        }
    }

    @Override // d4.z1
    public void d0(NowPlayingViewModel nowPlayingViewModel) {
        this.f5948b0 = nowPlayingViewModel;
        synchronized (this) {
            this.f5760x0 |= 549755813888L;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // e4.b.a
    public final void h(int i7, SeekBar seekBar) {
        NowPlayingViewModel nowPlayingViewModel = this.f5948b0;
        if (nowPlayingViewModel != null) {
            nowPlayingViewModel.onStartTrackingTouch();
        }
    }

    @Override // e4.c.a
    public final void j(int i7, SeekBar seekBar) {
        NowPlayingViewModel nowPlayingViewModel = this.f5948b0;
        if (nowPlayingViewModel != null) {
            nowPlayingViewModel.onStopTrackingTouch();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0592  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x064b  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0665  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0675  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x068d  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x06f2  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0735  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0746  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x077a  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x07cf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x082b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x086d  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0880  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0898  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x08a8  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x08c3  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x08c9  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x08e7  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x0945  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00fb A[PHI: r2
  0x00fb: PHI (r2v67 long) = (r2v0 long), (r2v69 long) binds: [B:53:0x00d2, B:66:0x00f6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0162  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 4291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.d2.x():void");
    }

    private d2(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 39, (ConstraintLayout) objArr[29], (FrameLayout) objArr[15], (FrameLayout) objArr[20], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[22], (FrameLayout) objArr[6], (FrameLayout) objArr[17], (FrameLayout) objArr[8], (View) objArr[24], (TextView) objArr[12], null, null, (ImageView) objArr[26], (FrameLayout) objArr[25], (FrameLayout) objArr[1], (TextView) objArr[11], (AppCompatSeekBar) objArr[10], (ProgressBar) objArr[18], (FrameLayout) objArr[4], (RelativeLayout) objArr[28], (FrameLayout) objArr[27]);
        this.f5759w0 = new a();
        this.f5760x0 = -1L;
        this.f5761y0 = -1L;
        this.H.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        this.M.setTag(null);
        this.N.setTag(null);
        this.P.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.f5739c0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[14];
        this.f5740d0 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) objArr[16];
        this.f5741e0 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) objArr[19];
        this.f5742f0 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) objArr[2];
        this.f5743g0 = imageView4;
        imageView4.setTag(null);
        ImageView imageView5 = (ImageView) objArr[21];
        this.f5744h0 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) objArr[23];
        this.f5745i0 = imageView6;
        imageView6.setTag(null);
        TextView textView = (TextView) objArr[3];
        this.f5746j0 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) objArr[5];
        this.f5747k0 = textView2;
        textView2.setTag(null);
        ImageView imageView7 = (ImageView) objArr[7];
        this.f5748l0 = imageView7;
        imageView7.setTag(null);
        ImageView imageView8 = (ImageView) objArr[9];
        this.f5749m0 = imageView8;
        imageView8.setTag(null);
        this.U.setTag(null);
        this.V.setTag(null);
        this.W.setTag(null);
        this.X.setTag(null);
        this.Y.setTag(null);
        X(view);
        this.f5750n0 = new e4.a(this, 9);
        this.f5751o0 = new e4.a(this, 7);
        this.f5752p0 = new e4.c(this, 3);
        this.f5753q0 = new e4.a(this, 8);
        this.f5754r0 = new e4.a(this, 1);
        this.f5755s0 = new e4.a(this, 5);
        this.f5756t0 = new e4.b(this, 4);
        this.f5757u0 = new e4.a(this, 6);
        this.f5758v0 = new e4.a(this, 2);
        K();
    }
}
