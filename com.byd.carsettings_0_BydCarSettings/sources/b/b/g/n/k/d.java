package b.b.g.n.k;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import b.b.g.p.d;
import com.byd.carsettings.system.model.ClockStyle;
import com.byd.carsettings.ui.fragment.dilink.DisplayFragment;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DisplayPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.m.e.g<DisplayFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.p.d f1102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0054d f1103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f1104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ContentResolver f1105d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ContentObserver f1106e = new a(new Handler());

    /* JADX INFO: compiled from: DisplayPresenter.java */
    public class a extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            b.b.m.c.a.a("DisplayPresenter", "ContentObserver--onChange--getV:" + d.this.getV());
            if (d.this.getV() == null || ((DisplayFragment) d.this.getV()).getContext() == null || ((DisplayFragment) d.this.getV()).getContext().getContentResolver() == null) {
                return;
            }
            int i = 2;
            try {
                i = Settings.Global.getInt(((DisplayFragment) d.this.getV()).getContext().getContentResolver(), "ui_night_mode");
                b.b.m.c.a.a("DisplayPresenter", "ContentObserver onChange:" + i);
            } catch (Settings.SettingNotFoundException e2) {
                e2.printStackTrace();
            }
            b.b.a.c.a.j(new b.b.a.c.a(230000, d.this.o(i)));
        }
    }

    /* JADX INFO: compiled from: DisplayPresenter.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1108a;

        public b(String str) {
            this.f1108a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.f1108a;
            str.hashCode();
            if (str.equals("byd_screensaver_mode")) {
                if (d.this.getV() != null) {
                    ((DisplayFragment) d.this.getV()).c0();
                }
            } else if (str.equals("multimedia_brightness_linkage") && d.this.getV() != null) {
                ((DisplayFragment) d.this.getV()).a0();
            }
        }
    }

    /* JADX INFO: compiled from: DisplayPresenter.java */
    public static class c extends b.b.g.p.j.d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<d> f1110b;

        public /* synthetic */ c(String str, d dVar, a aVar) {
            this(str, dVar);
        }

        @Override // b.b.g.p.j.d
        public void a() {
            WeakReference<d> weakReference = this.f1110b;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1110b.get().u(this.f1408a);
        }

        public c(String str, d dVar) {
            super(str);
            this.f1110b = new WeakReference<>(dVar);
        }
    }

    /* JADX INFO: renamed from: b.b.g.n.k.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DisplayPresenter.java */
    public static class C0054d extends b.b.g.p.j.d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<d> f1111b;

        public /* synthetic */ C0054d(String str, d dVar, a aVar) {
            this(str, dVar);
        }

        @Override // b.b.g.p.j.d
        public void a() {
            WeakReference<d> weakReference = this.f1111b;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1111b.get().u(this.f1408a);
        }

        public C0054d(String str, d dVar) {
            super(str);
            this.f1111b = new WeakReference<>(dVar);
        }
    }

    public void init(Context context) {
        b.b.g.p.d dVarA = d.g.a();
        this.f1102a = dVarA;
        dVarA.W(getV().getContext().getApplicationContext());
        ContentResolver contentResolver = context.getContentResolver();
        this.f1105d = contentResolver;
        if (contentResolver != null) {
            contentResolver.registerContentObserver(Settings.Global.getUriFor("ui_night_mode"), false, this.f1106e);
        }
        if (this.f1102a.Y(getV().getContext())) {
            this.f1102a.x0(getV().getContext(), false);
        }
        String str = "byd_screensaver_mode";
        a aVar = null;
        if (this.f1103b == null) {
            this.f1103b = new C0054d(str, this, aVar);
        }
        this.f1102a.h0(getV().getContext(), "byd_screensaver_mode", this.f1103b);
        String str2 = "multimedia_brightness_linkage";
        if (this.f1104c == null) {
            this.f1104c = new c(str2, this, aVar);
        }
        this.f1102a.i0(getV().getContext(), "multimedia_brightness_linkage", this.f1104c);
    }

    public int n() {
        return this.f1102a.o(getV().getContext());
    }

    public int o(int i) {
        return this.f1102a.y(i);
    }

    public int q() {
        return this.f1102a.K(getV().getContext());
    }

    public ClockStyle r() {
        return this.f1102a.Q(getV().getContext());
    }

    public boolean s(Context context) {
        return this.f1102a.V(context);
    }

    public boolean t() {
        return this.f1102a.Z(getV().getContext());
    }

    public final void u(String str) {
        if (getV() == null || getV().getActivity() == null) {
            return;
        }
        getV().getActivity().runOnUiThread(new b(str));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        b.b.g.p.d dVar = this.f1102a;
        if (dVar != null) {
            dVar.O0(getV().getContext(), this.f1103b);
            this.f1102a.O0(getV().getContext(), this.f1104c);
        }
        ContentResolver contentResolver = this.f1105d;
        if (contentResolver != null) {
            contentResolver.unregisterContentObserver(this.f1106e);
        }
        super.unbindV();
        this.f1103b = null;
        this.f1104c = null;
    }

    public void v(int i) {
        this.f1102a.v0(getV().getContext(), i);
    }

    public void w(boolean z) {
        b.b.m.c.a.a("DisplayPresenter", "---setBrightnessLinkageEnable---enable: " + z);
        this.f1102a.w0(getV().getContext(), z);
    }

    public void x(int i) {
        b.b.m.c.a.a("DisplayPresenter", "---setDayNightModeByIndex---index: " + i);
        this.f1102a.B0(getV().getContext(), i);
    }

    public void y(ClockStyle clockStyle) {
        b.b.m.c.a.a("DisplayPresenter", "---setScreenSaverClockStyle---style: " + clockStyle);
        this.f1102a.J0(getV().getContext(), clockStyle);
    }

    public void z() {
    }
}
