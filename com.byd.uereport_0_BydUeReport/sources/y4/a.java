package y4;

import android.content.Context;
import android.content.Intent;
import com.byd.systemsettings.SettingsCallDispatcher;
import com.byd.systemsettings.system.callback.IAvmOperator;
import com.byd.systemsettings.system.utils.CarIdentifier;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.avm.sdk.AvmManager;

/* JADX INFO: compiled from: BydAvmManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements IAvmOperator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f9283a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f9285c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public AvmManager f9286d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f9287e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f9288f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public f f9296n;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f9284b = "BydAvmECallManager";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f9289g = 18;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f9290h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x6.a f9291i = new C0147a();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AvmManager.c f9292j = new b();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public AvmManager.b f9293k = new c();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AvmManager.a f9294l = new d();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AvmManager.d f9295m = new e();

    /* JADX INFO: renamed from: y4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydAvmManager.java */
    public class C0147a implements x6.a {
        public C0147a() {
        }

        @Override // x6.a
        public void onError(int i8, String str) {
            BydLogHelper.logE("BydAvmECallManager", "avm onError.");
        }

        @Override // x6.a
        public void onServiceConnected() {
            BydLogHelper.logD("BydAvmECallManager", "avm onServiceConnected.");
            a.this.f9285c = true;
            a.this.f9286d.i(a.this.f9294l);
            a.this.f9286d.h(a.this.f9295m);
            a.this.f9286d.g(a.this.f9292j);
            if (CarIdentifier.supportScreenOffFromAvm()) {
                a.this.f9286d.f(a.this.f9293k);
            }
        }

        @Override // x6.a
        public void onServiceDisConnected() {
            BydLogHelper.logE("BydAvmECallManager", "avm onServiceDisConnected.");
            a.this.w();
            a.this.f9285c = false;
        }
    }

    /* JADX INFO: compiled from: BydAvmManager.java */
    public class b implements AvmManager.c {
        public b() {
        }

        @Override // com.ts.avm.sdk.AvmManager.c
        public void a(int i8, String str) {
            if (i8 != 2) {
                a.this.t(false);
                return;
            }
            BydLogHelper.logD("BydAvmECallManager", "avm is foreground, dismiss boot register dialog .");
            n4.a.z(a.this.f9287e).x();
            a.this.t(true);
            a.this.s();
        }
    }

    /* JADX INFO: compiled from: BydAvmManager.java */
    public class c implements AvmManager.b {
        public c() {
        }

        @Override // com.ts.avm.sdk.AvmManager.b
        public void onAvmDockerStatusChanged(int i8) {
            BydLogHelper.logD("BydAvmECallManager", "avm docker status : " + i8);
            a.this.m(i8);
        }
    }

    /* JADX INFO: compiled from: BydAvmManager.java */
    public class d implements AvmManager.a {
        public d() {
        }

        @Override // com.ts.avm.sdk.AvmManager.a
        public void a(String str, int i8, int i9) {
            BydLogHelper.logD("BydAvmECallManager", "onCallStatusChange,callType:" + i8 + ",action:" + i9);
            if (i8 == 1 && i9 == 2) {
                BydLogHelper.logD("BydAvmECallManager", "avm call off.");
                if (a.this.f9296n != null) {
                    a.this.f9296n.b();
                } else {
                    BydLogHelper.logD("BydAvmECallManager", "mOnAvmEventListener == null");
                }
            }
        }
    }

    /* JADX INFO: compiled from: BydAvmManager.java */
    public class e implements AvmManager.d {
        public e() {
        }

        @Override // com.ts.avm.sdk.AvmManager.d
        public void a(int i8) {
            BydLogHelper.logD("BydAvmECallManager", "avm view state : " + i8);
            if (i8 != 18 || a.this.f9296n == null) {
                return;
            }
            a.this.f9296n.a();
        }
    }

    /* JADX INFO: compiled from: BydAvmManager.java */
    public interface f {
        void a();

        void b();
    }

    public a(Context context) {
        this.f9287e = context.getApplicationContext();
    }

    public static a n(Context context) {
        if (f9283a == null) {
            synchronized (a.class) {
                if (f9283a == null) {
                    f9283a = new a(context);
                }
            }
        }
        return f9283a;
    }

    @Override // com.byd.systemsettings.system.callback.IAvmOperator
    public void hideAvm() {
        AvmManager avmManager = this.f9286d;
        if (avmManager != null) {
            avmManager.d();
        }
    }

    public final void m(int i8) {
        BydLogHelper.logD("BydAvmECallManager", "---doOnAvmDockerStatusChanged---status: " + i8);
        if (i8 == 6) {
            SettingsCallDispatcher.Singleton.get().showScreensaverOrScreenOff(true);
        } else {
            if (i8 != 7) {
                return;
            }
            SettingsCallDispatcher.Singleton.get().finishScreensaverOrScreenOff(true);
        }
    }

    public void o() {
        BydLogHelper.logD("BydAvmECallManager", "avm initAndRegister.");
        if (this.f9286d != null) {
            return;
        }
        AvmManager avmManagerC = AvmManager.c();
        this.f9286d = avmManagerC;
        avmManagerC.e(this.f9287e, this.f9291i);
        SettingsCallDispatcher.Singleton.get().registerIAvmOperator(this);
    }

    public boolean p() {
        return this.f9288f;
    }

    public boolean q() {
        AvmManager avmManager = this.f9286d;
        return avmManager != null && avmManager.b() == 18;
    }

    public void r(int i8, int i9) {
        BydLogHelper.logD("BydAvmECallManager", "send avm call state . ");
        if (!this.f9285c || this.f9286d == null) {
            return;
        }
        BydLogHelper.logD("BydAvmECallManager", "send avm call state . callTime:" + i8 + ",currentState:" + i9);
        this.f9286d.j(null, null, null, i8, 1, i9, false);
    }

    public final void s() {
        Intent intent = new Intent();
        intent.setAction("action_avm_foreground");
        a1.a.b(this.f9287e).d(intent);
    }

    @Override // com.byd.systemsettings.system.callback.IAvmOperator
    public void showAvm() {
        AvmManager avmManager = this.f9286d;
        if (avmManager != null) {
            avmManager.k();
        }
    }

    public final void t(boolean z7) {
        this.f9288f = z7;
    }

    public void u(f fVar) {
        this.f9296n = fVar;
    }

    public void v() {
        if (this.f9286d != null) {
            w();
            this.f9286d.a(this.f9291i);
            this.f9285c = false;
            this.f9286d = null;
        }
    }

    public final void w() {
        AvmManager avmManager;
        if (!this.f9285c || (avmManager = this.f9286d) == null) {
            return;
        }
        avmManager.o(this.f9294l);
        this.f9286d.n(this.f9295m);
        this.f9286d.m(this.f9292j);
        if (CarIdentifier.supportScreenOffFromAvm()) {
            this.f9286d.l(this.f9293k);
        }
    }
}
