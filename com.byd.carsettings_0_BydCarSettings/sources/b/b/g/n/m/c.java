package b.b.g.n.m;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import b.b.g.n.q.i;
import b.b.g.n.q.m;
import b.b.g.n.q.u;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.CommonFragment;

/* JADX INFO: compiled from: CommonPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends g<CommonFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1203a = "c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ContentObserver f1205c;

    /* JADX INFO: compiled from: CommonPresenter.java */
    public class a extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
        }
    }

    public c(Context context) {
        a aVar = new a(new Handler());
        this.f1205c = aVar;
        this.f1204b = context;
        if (context.getContentResolver() != null) {
            this.f1204b.getContentResolver().registerContentObserver(Settings.Global.getUriFor("ui_night_mode"), false, aVar);
        }
    }

    public int c() {
        return u.I().b();
    }

    public boolean d() {
        int iA = u.I().a();
        b.b.m.c.a.a(f1203a, "isAmbientAreaSupport:state = " + iA);
        return iA >= 2;
    }

    public boolean e() {
        return u.I().c() > 1;
    }

    public boolean f() {
        int I = m.p0().I();
        return I == 1 || I == 2;
    }

    public boolean g() {
        return u.I().F() == 1;
    }

    public boolean h() {
        return i.k().b() == 1;
    }

    public boolean i() {
        int iH = m.p0().h(0);
        return iH == 1 || iH == 2;
    }

    public boolean j() {
        return u.I().J() == 1;
    }

    public boolean l() {
        boolean zG = b.b.g.n.q.b.c().g();
        int iR = u.I().r();
        return !zG && (iR == 3 || iR == 2);
    }

    public boolean m() {
        return u.I().b0() == 1;
    }

    public void n() {
        if (this.f1204b.getContentResolver() != null) {
            this.f1204b.getContentResolver().unregisterContentObserver(this.f1205c);
        }
    }
}
