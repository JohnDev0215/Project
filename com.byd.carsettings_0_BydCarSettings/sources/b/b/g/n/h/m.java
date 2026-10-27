package b.b.g.n.h;

import android.content.Context;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.provider.Settings;
import android.util.Log;
import b.b.g.d.o;
import b.b.g.o.a;
import com.byd.carsettings.ui.fragment.carsettings.SmtRemindFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: SmtRemindPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class m extends b.b.m.e.g<SmtRemindFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1018a = 5;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1019b = 39;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static long f1020c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f1021d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f1022e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f1023f;
    public AbsBYDAutoAudioListener g = new a();
    public int[] h = {5, 8, 10, 12, 15, 17, 20, 22, 24};

    /* JADX INFO: compiled from: SmtRemindPresenter.java */
    public class a extends AbsBYDAutoAudioListener {
        public a() {
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onCarTipsVoiceStateChanged(int i, int i2) {
            super.onCarTipsVoiceStateChanged(i, i2);
            BydLogHelper.logD("onCarTipsVoiceStateChanged  : state : " + i2 + " , type : " + i);
            if (i == 0) {
                b.b.a.c.a.j(new b.b.a.c.a(80009, i2));
                b.b.a.h.a.b(this, "onCarTipsVoiceStateChanged  value : " + i2);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onEngineSoundTypeChanged(int i) {
            super.onEngineSoundTypeChanged(i);
            BydLogHelper.logD("onEngineSoundTypeChanged  : value " + i);
            b.b.a.c.a.j(new b.b.a.c.a(80004, i));
            b.b.a.h.a.b(this, "byd_avas onEngineSoundTypeChanged  value : " + i);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onMuteStateChanged(int i) {
            super.onMuteStateChanged(i);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onNavigationMediaStateChanged(int i) {
            super.onNavigationMediaStateChanged(i);
            BydLogHelper.logD("onNavigationMediaStateChanged  : state " + i);
            b.b.g.r.e.h(80006);
            b.b.a.c.a.j(new b.b.a.c.a(80006, i));
        }
    }

    /* JADX INFO: compiled from: SmtRemindPresenter.java */
    public class b implements b.b.g.o.g.b {
        public b() {
        }

        @Override // b.b.g.o.g.b
        public void e(boolean z) {
            BydLogHelper.logD("onUeMuteChanged  : state " + z);
            b.b.a.c.a.j(new b.b.a.c.a(80010, Boolean.valueOf(z)));
        }

        public /* synthetic */ b(m mVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: SmtRemindPresenter.java */
    public class c implements b.b.g.o.g.c {
        public c() {
        }

        @Override // b.b.g.o.g.c
        public void e(int i) {
            Log.e("SmtRemindPresenter", "--onUeVolumeChanged--zoneId volume: " + i);
            b.b.g.r.e.h(80001);
            b.b.a.c.a.j(new b.b.a.c.a(80001, i));
        }

        public /* synthetic */ c(m mVar, a aVar) {
            this();
        }
    }

    public m() {
        a aVar = null;
        this.f1022e = new c(this, aVar);
        this.f1023f = new b(this, aVar);
        n(b.b.a.a.a.b().a());
        this.f1021d = new o();
    }

    public void A(int i) {
        b.b.g.n.q.m.p0().J2(i);
    }

    public void B(Context context, boolean z) {
        Settings.System.putInt(context.getContentResolver(), "sound_effects_enabled", z ? 1 : 0);
        BydLogHelper.logD(m.class.getSimpleName(), " setKeyVoice  value : " + (z ? 1 : 0) + ", result : " + Settings.System.getInt(context.getContentResolver(), "sound_effects_enabled", 0));
    }

    public void C(int i) {
        BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).setNavigationMediaState(i);
    }

    public void D(int i) {
        BydLogHelper.logD("setReportVolume position : " + i);
        if (i < f1018a || i > f1019b) {
            return;
        }
        a.b.a().f0(i);
    }

    public void E(int i) {
        b.b.g.n.q.m.p0().q3(i);
    }

    public void F(boolean z) {
        a.b.a().e0(z);
    }

    public void G() {
        BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).unregisterListener(this.g);
        a.b.a().h0("SmtRemindPresenter");
        a.b.a().g0("ueMute");
    }

    public boolean c(Context context) {
        return m(context, "byd_report_toggle");
    }

    public int d() {
        b.b.a.h.a.b(this, "byd_avas getCarTipsSoundType  value : " + b.b.g.n.q.m.p0().s());
        return b.b.g.n.q.m.p0().s();
    }

    public int e() {
        int iT = b.b.g.n.q.m.p0().t(0);
        int iT2 = b.b.g.n.q.m.p0().t(1);
        int iT3 = b.b.g.n.q.m.p0().t(2);
        BydLogHelper.logD(m.class.getSimpleName(), " getCarTipsVoiceState  valueChime  : " + iT + " ,valueRadar :  " + iT2 + " , valueAdas : " + iT3);
        return iT;
    }

    public int f() {
        b.b.a.h.a.b(this, "byd_avas getEngineSoundType  value : " + BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).getEngineSoundType());
        return BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).getEngineSoundType();
    }

    public int g() {
        return b.b.g.n.q.m.p0().t0();
    }

    public boolean h(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "sound_effects_enabled", 0) == 1;
    }

    public boolean i() {
        return BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).getNavigationMediaState() == 1;
    }

    public int j() {
        if (t()) {
            return f1018a;
        }
        int iMax = Math.max(a.b.a().z(), f1018a);
        BydLogHelper.logD("getReportVolume value : " + iMax);
        return iMax;
    }

    public boolean l() {
        return b.b.g.n.q.m.p0().j1() == 1;
    }

    public final boolean m(Context context, String str) {
        return Settings.System.getInt(context.getContentResolver(), str, 1) == 1;
    }

    public final void n(Context context) {
        a.b.a().A(context);
    }

    public boolean o() {
        return this.f1021d.b();
    }

    public boolean q() {
        return this.f1021d.c();
    }

    public boolean r() {
        return this.f1021d.d();
    }

    public boolean s() {
        return this.f1021d.e();
    }

    public boolean t() {
        return a.b.a().F();
    }

    public final void u(Context context, String str, boolean z) {
        Settings.System.putInt(context.getContentResolver(), str, z ? 1 : 2);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        G();
    }

    public void v() {
        BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).registerListener(this.g);
        a.b.a().T("SmtRemindPresenter", this.f1022e);
        a.b.a().S("ueMute", this.f1023f);
    }

    public void w(Context context, boolean z) {
        u(context, "byd_report_toggle", z);
    }

    public void x(int i) {
        b.b.a.h.a.b(this, "setCarDoorTipsSoundType  carType : " + b.b.a.i.a.b());
        b.b.g.n.q.m.p0().b2(i);
        b.b.a.h.a.b(this, " mr setCarDoorTipsSoundType  value : " + i);
    }

    public void y(int i) {
        b.b.g.n.q.m.p0().c2(i);
        b.b.a.h.a.b(this, "byd_avas setCarTipsSoundType  value : " + i);
    }

    public void z(int i) {
        BydLogHelper.logD(m.class.getSimpleName(), " setCarTipsVoiceState : " + i);
        b.b.g.n.q.m.p0().d2(0, i);
        b.b.g.n.q.m.p0().d2(1, i);
        b.b.g.n.q.m.p0().d2(2, i);
    }
}
