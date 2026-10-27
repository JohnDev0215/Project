package com.byd.carsettings.ui.fragment.dilink;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import b.b.a.j.c.a;
import b.b.g.q.f.d.i;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracMode;
import com.byd.carsettings.sound.model.Manufactor;
import com.byd.carsettings.ui.widget.AreaableSeekbar;
import com.byd.mvp.mvp.XFragment;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.libpag.PAGFile;
import org.libpag.PAGImageView;

/* JADX INFO: loaded from: classes2.dex */
public class SoundFragment extends XFragment<b.b.g.n.k.f> implements View.OnClickListener, a.InterfaceC0023a {
    public static final int[] m = {R.drawable.img_sound_wave_power, R.drawable.img_sound_wave_science_fiction};
    public TextView A;
    public TextView B;
    public TextView D;
    public AreaableSeekbar E;
    public AreaableSeekbar F;
    public AreaableSeekbar G;
    public AreaableSeekbar H;
    public AreaableSeekbar I;
    public t J;
    public long K;
    public b.b.g.q.f.d.i L;
    public b.b.g.q.f.d.i M;
    public final int N = 0;
    public int O = 0;
    public final ConcurrentHashMap<String, b.b.g.q.f.d.i> P = new ConcurrentHashMap<>();
    public ViewGroup n;
    public ViewGroup o;
    public ViewGroup p;
    public ViewGroup q;
    public ViewGroup r;
    public ViewGroup s;
    public ScrollView t;
    public ImageView u;
    public PAGImageView v;
    public FrameLayout w;
    public TextView x;
    public TextView y;
    public TextView z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3158a;

        public a(int i) {
            this.f3158a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.x.setText(String.valueOf(this.f3158a));
            SoundFragment.this.E.setProgress(this.f3158a);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3160a;

        public b(int i) {
            this.f3160a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.y.setText(String.valueOf(this.f3160a));
            SoundFragment.this.F.setProgress(this.f3160a);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3162a;

        public c(int i) {
            this.f3162a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.z.setText(String.valueOf(this.f3162a));
            SoundFragment.this.G.setProgress(this.f3162a);
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3164a;

        public d(int i) {
            this.f3164a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.A.setText(String.valueOf(this.f3164a));
            SoundFragment.this.H.setProgress(this.f3164a);
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3166a;

        public e(int i) {
            this.f3166a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.B.setText(String.valueOf(this.f3166a));
            SoundFragment.this.I.setProgress(this.f3166a);
        }
    }

    public class f implements i.a {
        public f() {
        }

        @Override // b.b.g.q.f.d.i.a
        public void a(DiracMode diracMode) {
            SoundFragment.this.p0(diracMode);
        }
    }

    public class g implements i.b {
        public g() {
        }

        @Override // b.b.g.q.f.d.i.b
        public void a(boolean z) {
            SoundFragment.this.r0(z);
        }
    }

    public class h implements b.b.m.f.c.a.e {
        public h() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            SoundFragment.this.s0(i);
        }
    }

    public class i implements i.c {
        public i() {
        }

        @Override // b.b.g.q.f.d.i.c
        public void a(int i, boolean z) {
            if (!z) {
                SoundFragment.this.s0(-1);
            } else {
                SoundFragment soundFragment = SoundFragment.this;
                soundFragment.s0(soundFragment.O);
            }
        }
    }

    public static /* synthetic */ class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3172a;

        static {
            int[] iArr = new int[Manufactor.values().length];
            f3172a = iArr;
            try {
                iArr[Manufactor.DIRAC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3172a[Manufactor.DYNAUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3172a[Manufactor.HARMAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class k implements b.b.g.q.h.c.b {
        public k() {
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.K = 2131298051L;
            SoundFragment.this.J.removeMessages(500);
            SoundFragment.this.J.sendEmptyMessage(500);
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.J.removeMessages(500);
            Message messageObtain = Message.obtain();
            messageObtain.what = 501;
            SoundFragment.this.f0(messageObtain);
        }
    }

    public class l implements b.b.g.q.h.c.b {
        public l() {
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.K = 2131298053L;
            SoundFragment.this.J.removeMessages(500);
            SoundFragment.this.J.sendEmptyMessage(500);
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.J.removeMessages(500);
            Message messageObtain = Message.obtain();
            messageObtain.what = 501;
            SoundFragment.this.f0(messageObtain);
        }
    }

    public class m implements b.b.g.q.h.c.b {
        public m() {
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.K = 2131298054L;
            SoundFragment.this.J.removeMessages(500);
            SoundFragment.this.J.sendEmptyMessage(500);
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.J.removeMessages(500);
            Message messageObtain = Message.obtain();
            messageObtain.what = 501;
            SoundFragment.this.f0(messageObtain);
        }
    }

    public class n implements b.b.g.q.h.c.b {
        public n() {
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.K = 2131298056L;
            SoundFragment.this.J.removeMessages(500);
            SoundFragment.this.J.sendEmptyMessage(500);
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.J.removeMessages(500);
            Message messageObtain = Message.obtain();
            messageObtain.what = 501;
            SoundFragment.this.f0(messageObtain);
        }
    }

    public class o implements b.b.g.q.h.c.b {
        public o() {
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.K = 2131298052L;
            SoundFragment.this.J.removeMessages(500);
            SoundFragment.this.J.sendEmptyMessage(500);
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SoundFragment.this.J.removeMessages(500);
            Message messageObtain = Message.obtain();
            messageObtain.what = 501;
            SoundFragment.this.f0(messageObtain);
        }
    }

    public class p implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3178a;

        public p(boolean z) {
            this.f3178a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.E.setInvalid(this.f3178a);
        }
    }

    public class q implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3180a;

        public q(boolean z) {
            this.f3180a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.G.setInvalid(this.f3180a);
        }
    }

    public class r implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3182a;

        public r(boolean z) {
            this.f3182a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.H.setInvalid(this.f3182a);
        }
    }

    public class s implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3184a;

        public s(boolean z) {
            this.f3184a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            SoundFragment.this.I.setInvalid(this.f3184a);
        }
    }

    public static class t extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<SoundFragment> f3186a;

        public t(SoundFragment soundFragment) {
            this.f3186a = new WeakReference<>(soundFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (this.f3186a.get() == null) {
                return;
            }
            this.f3186a.get().f0(message);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        ((b.b.g.n.k.f) mo7getP()).init(context);
        this.n = (ViewGroup) this.f3717c.findViewById(R.id.sound_parent);
        this.t = (ScrollView) this.f3717c.findViewById(R.id.scv_sound_pager);
        this.u = (ImageView) this.f3717c.findViewById(R.id.iv_sound_pager);
        this.v = (PAGImageView) this.f3717c.findViewById(R.id.pag_sound_pager);
        this.w = (FrameLayout) this.f3717c.findViewById(R.id.fl_manufactor_pager_sound);
        this.o = (ViewGroup) this.f3717c.findViewById(R.id.ll_media_volume_sound_pager);
        this.p = (ViewGroup) this.f3717c.findViewById(R.id.ll_phone_volume_sound_pager);
        this.q = (ViewGroup) this.f3717c.findViewById(R.id.ll_ringtone_volume_sound_pager);
        this.r = (ViewGroup) this.f3717c.findViewById(R.id.ll_tts_volume_sound_pager);
        this.s = (ViewGroup) this.f3717c.findViewById(R.id.ll_navi_volume_sound_pager);
        this.x = (TextView) this.f3717c.findViewById(R.id.tv_media_volume_sound_pager);
        this.y = (TextView) this.f3717c.findViewById(R.id.tv_phone_volume_sound_pager);
        this.z = (TextView) this.f3717c.findViewById(R.id.tv_ringtone_volume_sound_pager);
        this.A = (TextView) this.f3717c.findViewById(R.id.tv_tts_volume_sound_pager);
        this.B = (TextView) this.f3717c.findViewById(R.id.tv_navi_volume_sound_pager);
        this.D = (TextView) this.f3717c.findViewById(R.id.tv_di_sheng);
        this.E = (AreaableSeekbar) this.f3717c.findViewById(R.id.sbar_media_volume_sound_pager);
        this.F = (AreaableSeekbar) this.f3717c.findViewById(R.id.sbar_phone_volume_sound_pager);
        this.G = (AreaableSeekbar) this.f3717c.findViewById(R.id.sbar_ringtone_volume_sound_pager);
        this.H = (AreaableSeekbar) this.f3717c.findViewById(R.id.sbar_tts_volume_sound_pager);
        this.I = (AreaableSeekbar) this.f3717c.findViewById(R.id.sbar_navi_volume_sound_pager);
        this.J = new t(this);
        this.E.setOnSeekBarChangeListener(new k());
        this.F.setOnSeekBarChangeListener(new l());
        this.G.setOnSeekBarChangeListener(new m());
        this.H.setOnSeekBarChangeListener(new n());
        this.I.setOnSeekBarChangeListener(new o());
    }

    public final void b0(b.b.g.o.h.a aVar) {
        Manufactor manufactor;
        if (aVar == null || (manufactor = aVar.f1342a) == null) {
            return;
        }
        this.L = d0(manufactor);
        b.b.m.c.a.a(this.f3715a, "addSoundUI", "mPager: " + this.L);
        b.b.g.q.f.d.i iVar = this.L;
        if (iVar == null || iVar.getRootView() == null) {
            return;
        }
        if (this.L.equals(this.M)) {
            this.L.loadData();
            return;
        }
        b.b.g.q.f.d.i iVar2 = this.M;
        if (iVar2 != null) {
            iVar2.onHide();
            this.M.stop();
        }
        this.L.loadData();
        this.M = this.L;
        this.w.removeAllViews();
        int i2 = j.f3172a[manufactor.ordinal()];
        if (i2 == 1) {
            this.L.f(new f());
            this.L.h(new g());
        } else if (i2 == 2) {
            this.L.setOnItemClickedListener(new h());
            this.L.k(new i());
        }
        this.w.addView(this.L.getRootView());
    }

    public final void c0() {
        this.E.setOnSeekBarChangeListener(null);
        this.I.setOnSeekBarChangeListener(null);
        this.F.setOnSeekBarChangeListener(null);
        this.G.setOnSeekBarChangeListener(null);
        this.H.setOnSeekBarChangeListener(null);
        AreaableSeekbar areaableSeekbar = this.E;
        if (areaableSeekbar != null) {
            areaableSeekbar.b();
        }
        if (this.E != null) {
            this.G.b();
        }
        if (this.E != null) {
            this.H.b();
        }
        if (this.E != null) {
            this.I.b();
        }
    }

    public final synchronized b.b.g.q.f.d.i d0(Manufactor manufactor) {
        b.b.g.q.f.d.i iVar = this.P.get(manufactor.name());
        b.b.m.c.a.a(this.f3715a, "---getSoundPager---manufactor = " + manufactor + ", pager: " + iVar);
        if (iVar != null) {
            return iVar;
        }
        b.b.g.q.f.d.i iVarK = b.b.g.o.i.c.k(getActivity(), this);
        if (iVarK != null) {
            this.P.put(manufactor.name(), iVarK);
        }
        return iVarK;
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.k.f newP() {
        return new b.b.g.n.k.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0(Message message) {
        long j2 = this.K;
        if (j2 == 2131298051) {
            int progress = this.E.getProgress();
            this.x.setText(String.valueOf(progress));
            ((b.b.g.n.k.f) mo7getP()).C(progress);
            b.b.m.c.a.a(this.f3715a + "-TimerTask", "设置媒体音量:" + progress);
        } else if (j2 == 2131298053) {
            int progress2 = this.F.getProgress();
            this.y.setText(String.valueOf(progress2));
            ((b.b.g.n.k.f) mo7getP()).F(progress2);
            b.b.m.c.a.a(this.f3715a + "-TimerTask", "设置电话音量:" + progress2);
        } else if (j2 == 2131298054) {
            int progress3 = this.G.getProgress();
            this.z.setText(String.valueOf(progress3));
            ((b.b.g.n.k.f) mo7getP()).H(progress3);
            b.b.m.c.a.a(this.f3715a + "-TimerTask", "设置铃声音量:" + progress3);
        } else if (j2 == 2131298056) {
            int progress4 = this.H.getProgress();
            this.A.setText(String.valueOf(progress4));
            ((b.b.g.n.k.f) mo7getP()).J(progress4);
            b.b.m.c.a.a(this.f3715a + "-TimerTask", "设置智能语音音量:" + progress4);
        } else if (j2 == 2131298052) {
            int progress5 = this.I.getProgress();
            this.B.setText(String.valueOf(progress5));
            ((b.b.g.n.k.f) mo7getP()).E(progress5);
            b.b.m.c.a.a(this.f3715a + "-TimerTask", "设置导航音量:" + progress5);
        }
        if (message == null || message.what != 500) {
            return;
        }
        this.J.sendEmptyMessageDelayed(500, 100L);
    }

    public void g0(boolean z) {
        if (getActivity() == null) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "--onMediaMuteStateChanged--muted: " + z);
        getActivity().runOnUiThread(new p(z));
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_sound;
    }

    public void h0(int i2) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new a(i2));
    }

    public void i0(boolean z) {
        if (getActivity() == null) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "--onNaviMuteStateChanged--muted: " + z);
        getActivity().runOnUiThread(new s(z));
    }

    public void j0(int i2) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new e(i2));
    }

    public void k0(int i2) {
        if (getActivity() == null) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "--onPhoneVolumeChanged--volume: " + i2);
        getActivity().runOnUiThread(new b(i2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.a.j.c.a.InterfaceC0023a
    public void l(View view, boolean z, boolean z2) {
        if (view == null || mo7getP() == 0 || !z2 || z) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "---onInvalidStateChanged---getP().isAllMute: " + ((b.b.g.n.k.f) mo7getP()).v());
        b.b.g.n.k.f fVar = (b.b.g.n.k.f) mo7getP();
        int id = view.getId();
        if (id == R.id.sbar_media_volume_sound_pager) {
            b.b.m.c.a.a(this.f3715a, "---onInvalidStateChanged---media_volume");
            if (fVar.w()) {
                fVar.B(false);
            }
        } else if (id == R.id.sbar_ringtone_volume_sound_pager) {
            b.b.m.c.a.a(this.f3715a, "---onInvalidStateChanged---ringtone_volume");
            if (fVar.y()) {
                fVar.G(false);
            }
        } else if (id == R.id.sbar_tts_volume_sound_pager) {
            b.b.m.c.a.a(this.f3715a, "---onInvalidStateChanged---tts_volume");
            if (fVar.z()) {
                fVar.I(false);
            }
        } else if (id == R.id.sbar_navi_volume_sound_pager) {
            b.b.m.c.a.a(this.f3715a, "---onInvalidStateChanged---navi_volume");
            if (fVar.x()) {
                fVar.D(false);
            }
        }
        fVar.A(false);
    }

    public void l0(boolean z) {
        if (getActivity() == null) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "--onRingtoneMuteStateChanged--muted: " + z);
        getActivity().runOnUiThread(new q(z));
    }

    public void m0(int i2) {
        if (getActivity() == null) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "--onRingtoneVolumeChanged--volume: " + i2);
        getActivity().runOnUiThread(new c(i2));
    }

    public void n0(boolean z) {
        if (getActivity() == null) {
            return;
        }
        b.b.m.c.a.a(this.f3715a, "--onTtsMuteStateChanged--muted: " + z);
        getActivity().runOnUiThread(new r(z));
    }

    public void o0(int i2) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new d(i2));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        b.b.m.f.d.d.c(getClass().getName());
        c0();
        FrameLayout frameLayout = this.w;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        ConcurrentHashMap<String, b.b.g.q.f.d.i> concurrentHashMap = this.P;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            Iterator<b.b.g.q.f.d.i> it = this.P.values().iterator();
            while (it.hasNext()) {
                it.next().onDestroy();
            }
            this.P.clear();
        }
        this.J.removeMessages(500);
        if (this.v.getVisibility() == 0 || this.v.isPlaying()) {
            this.v.clearAnimation();
        }
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.n, true);
            setViewGroupRtl(this.o, false);
            setViewGroupRtl(this.p, false);
            setViewGroupRtl(this.q, false);
            setViewGroupRtl(this.r, false);
            setViewGroupRtl(this.s, false);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        b.b.m.c.a.a(this.f3715a, "onResume", "mPager: " + this.L);
        b.b.g.q.f.d.i iVar = this.L;
        if (iVar != null) {
            iVar.loadData();
        }
        if (this.v.getVisibility() == 0) {
            String str = B() ? "DiSound_Wave_Effect_night.pag" : "DiSound_Wave_Effect_light.pag";
            this.v.setComposition(PAGFile.Load(getContext().getAssets(), "pag/" + str));
            this.v.play();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.n, false);
            setViewGroupRtl(this.o, true);
            setViewGroupRtl(this.p, true);
            setViewGroupRtl(this.q, true);
            setViewGroupRtl(this.r, true);
            setViewGroupRtl(this.s, true);
        }
    }

    public final void p0(DiracMode diracMode) {
        int iB = b.b.g.o.i.c.b(diracMode);
        if (iB != 0) {
            this.u.setImageResource(iB);
        }
    }

    public final void q0() {
        int iJ = b.b.g.o.i.c.j();
        if (iJ != 0) {
            this.u.setImageResource(iJ);
        }
    }

    public final void r0(boolean z) {
        b.b.m.c.a.a(this.f3715a, "---updateSoundBgOnLoudnessStateChanged---b = " + z);
        this.u.setImageResource(R.drawable.img_carsetting_volume);
    }

    public final void s0(int i2) {
        b.b.m.c.a.a(this.f3715a, "---updateSoundBgOnSoundWaveChanged---index = " + i2);
        int[] iArr = m;
        if (i2 >= iArr.length) {
            return;
        }
        if (i2 == -1) {
            this.u.setImageResource(R.drawable.img_carsetting_dynaudio);
        } else {
            if (i2 < 0) {
                return;
            }
            this.O = i2;
            this.u.setImageResource(iArr[i2]);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        b.b.g.q.f.d.i iVar = this.L;
        if (iVar != null) {
            iVar.b(i2);
        }
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume--subindex = " + i2);
        I(this.t, 250, i2);
        if (!b.b.m.e.i.a.i()) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        int iQ = ((b.b.g.n.k.f) mo7getP()).q();
        this.x.setText(String.valueOf(iQ));
        this.E.setProgress(iQ);
        int iS = ((b.b.g.n.k.f) mo7getP()).s();
        this.y.setText(String.valueOf(iS));
        this.F.setProgress(iS);
        int iT = ((b.b.g.n.k.f) mo7getP()).t();
        this.z.setText(String.valueOf(iT));
        this.G.setProgress(iT);
        int iU = ((b.b.g.n.k.f) mo7getP()).u();
        this.A.setText(String.valueOf(iU));
        this.H.setProgress(iU);
        int iR = ((b.b.g.n.k.f) mo7getP()).r();
        this.B.setText(String.valueOf(iR));
        this.I.setProgress(iR);
        b.b.g.o.h.a aVarO = ((b.b.g.n.k.f) mo7getP()).o();
        b.b.m.c.a.a(this.f3715a, "initData", "config: " + aVarO);
        b0(aVarO);
        q0();
        boolean zW = ((b.b.g.n.k.f) mo7getP()).w();
        boolean zY = ((b.b.g.n.k.f) mo7getP()).y();
        boolean z = ((b.b.g.n.k.f) mo7getP()).z();
        boolean zX = ((b.b.g.n.k.f) mo7getP()).x();
        b.b.m.c.a.a(this.f3715a, "initData", "muted: " + zW + ", ringMuted: " + zY + ", ttsMuted: " + z + ", naviMuted: " + zX);
        this.E.setInvalid(zW);
        this.G.setInvalid(zY);
        this.H.setInvalid(z);
        this.I.setInvalid(zX);
        this.E.c(this);
        this.G.c(this);
        this.H.c(this);
        this.I.c(this);
        if (aVarO.f1342a == Manufactor.BS) {
            this.u.setVisibility(8);
            this.v.setVisibility(0);
            this.v.setRepeatCount(-1);
            this.D.setVisibility(0);
        }
    }
}
