package b.b.g.n.k;

import b.b.g.n.q.m;
import b.b.g.p.d;
import com.byd.carsettings.model.dilink.BydDate;
import com.byd.carsettings.system.model.BydLanguage;
import com.byd.carsettings.ui.fragment.dilink.SystemFragment;
import com.byd.mvp.ui.widget.BydBridgeWebView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: compiled from: SystemPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b.b.m.e.g<SystemFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1143a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.p.d f1144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f1145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f1146d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f1147e;

    /* JADX INFO: compiled from: SystemPresenter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BydLanguage f1148a;

        public a(BydLanguage bydLanguage) {
            this.f1148a = bydLanguage;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.a("SystemPresenter", "setLanguage:" + this.f1148a);
            g.this.f1145c.I2(this.f1148a.getCanValue());
            g.this.f1144b.m(this.f1148a.getLocale());
        }
    }

    /* JADX INFO: compiled from: SystemPresenter.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1150a;

        public b(String str) {
            this.f1150a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.f1150a;
            str.hashCode();
            if (str.equals("auto_time_zone")) {
                if (g.this.getV() != null) {
                    ((SystemFragment) g.this.getV()).Z();
                }
            } else if (str.equals("byd_memory_playback_state") && g.this.getV() != null) {
                ((SystemFragment) g.this.getV()).Y();
            }
        }
    }

    /* JADX INFO: compiled from: SystemPresenter.java */
    public static class c extends b.b.g.p.j.d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<g> f1152b;

        public /* synthetic */ c(String str, g gVar, a aVar) {
            this(str, gVar);
        }

        @Override // b.b.g.p.j.d
        public void a() {
            WeakReference<g> weakReference = this.f1152b;
            if (weakReference == null || weakReference.get() == null || this.f1152b.get().getV() == null) {
                return;
            }
            this.f1152b.get().u(this.f1408a);
        }

        public c(String str, g gVar) {
            super(str);
            this.f1152b = new WeakReference<>(gVar);
        }
    }

    /* JADX INFO: compiled from: SystemPresenter.java */
    public static class d extends b.b.g.p.j.d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<g> f1153b;

        public /* synthetic */ d(String str, g gVar, a aVar) {
            this(str, gVar);
        }

        @Override // b.b.g.p.j.d
        public void a() {
            WeakReference<g> weakReference = this.f1153b;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1153b.get().u(this.f1408a);
        }

        public d(String str, g gVar) {
            super(str);
            this.f1153b = new WeakReference<>(gVar);
        }
    }

    public void A(boolean z) {
        b.b.m.c.a.a("SystemPresenter", "---setTimeSystem---is24H:" + z);
        this.f1144b.L0(getV().getContext(), z);
    }

    public void B() {
        this.f1144b.N0(getV().getContext());
    }

    public b.b.g.m.d.b l() {
        return this.f1144b.u(getV().getContext());
    }

    public String m() {
        return b.b.g.p.m.a.d(this.f1144b.v(getV().getContext()));
    }

    public TimeZone n() {
        return this.f1144b.w();
    }

    public boolean o() {
        return this.f1144b.L(getV().getContext());
    }

    public int q() {
        return this.f1144b.S(getV().getContext());
    }

    public List<b.b.g.m.d.e> r() {
        return this.f1144b.T(getV().getContext());
    }

    public void s() {
        b.b.g.p.d dVarA = d.g.a();
        this.f1144b = dVarA;
        dVarA.W(getV().getContext());
        m mVarP0 = m.p0();
        this.f1145c = mVarP0;
        mVarP0.I1(getV().getContext().getApplicationContext());
        BydBridgeWebView.k();
        a aVar = null;
        String str = "byd_memory_playback_state";
        if (this.f1146d == null) {
            this.f1146d = new d(str, this, aVar);
        }
        this.f1144b.i0(getV().getContext(), "byd_memory_playback_state", this.f1146d);
        String str2 = "auto_time_zone";
        if (this.f1147e == null) {
            this.f1147e = new c(str2, this, aVar);
        }
        this.f1144b.h0(getV().getContext(), "auto_time_zone", this.f1147e);
    }

    public boolean t() {
        return this.f1144b.e0(getV().getContext());
    }

    public final void u(String str) {
        if (getV() == null || getV().getActivity() == null) {
            return;
        }
        b.b.m.c.a.a("SystemPresenter", "---onSettingsValueChanged---key:" + str);
        getV().getActivity().runOnUiThread(new b(str));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        b.b.g.p.d dVar = this.f1144b;
        if (dVar != null) {
            dVar.O0(getV().getContext(), this.f1146d);
            this.f1144b.O0(getV().getContext(), this.f1147e);
        }
        super.unbindV();
        this.f1146d = null;
        this.f1147e = null;
    }

    public void v(String str) {
        b.b.m.c.a.a("SystemPresenter", "---setCurTimeZone---id:" + str);
        this.f1144b.z0(getV().getContext(), str);
    }

    public void w(BydLanguage bydLanguage) {
        if (bydLanguage == null) {
            return;
        }
        b.b.m.c.a.a("SystemPresenter", "---setLanguage---language:" + bydLanguage);
        b.b.a.f.h.a().j(new a(bydLanguage));
    }

    public void x(BydDate bydDate) {
        this.f1144b.F0(getV().getContext(), bydDate);
    }

    public void y(boolean z) {
        b.b.m.c.a.a("SystemPresenter", "---setMemoryPlaybackState---enable:" + z);
        this.f1144b.G0(getV().getContext(), z);
    }

    public void z(boolean z) {
        b.b.m.c.a.a("SystemPresenter", "---setTimeAutoSyncEnable---enable:" + z);
        this.f1144b.K0(getV().getContext(), z);
    }
}
