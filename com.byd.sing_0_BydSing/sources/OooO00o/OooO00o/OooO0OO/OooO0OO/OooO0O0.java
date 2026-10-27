package OooO00o.OooO00o.OooO0Oo.OooO0Oo;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.d;
import android.content.Context;
import com.blankj.utilcode.util.ToastUtils;
import com.loostone.libserver.version2.bean.DongleParams;
import com.loostone.libserver.version2.bean.PlatformParams;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0O0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kotlin.a f93a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f94b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f95c;

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.b> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f96OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.b invoke() {
            return new OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.b();
        }
    }

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<d> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0o f97OooO00o = new OooO0o();

        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final d invoke() {
            return new d();
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public final class b extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public PlatformParams f98b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ OooO0O0 f99c;

        public b(OooO0O0 oooO0O0) {
            h.d(oooO0O0, "this$0");
            this.f99c = oooO0O0;
        }

        public final void a(PlatformParams platformParams) {
            h.d(platformParams, "param");
            this.f98b = platformParams;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b configListener;
            int i = 0;
            while (this.f99c.f95c && i < 1800000) {
                if (OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a.d()) {
                    PlatformParams platformParams = this.f98b;
                    if (platformParams != null) {
                        this.f99c.l().o(platformParams);
                    }
                } else {
                    PlatformParams platformParams2 = this.f98b;
                    if (platformParams2 != null && (configListener = platformParams2.getConfigListener()) != null) {
                        configListener.onError("no network!");
                    }
                }
                i++;
                Thread.sleep(1000L);
            }
            OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetMgr", "stop tuning mode");
            ToastUtils.s("stop tuning mode", new Object[0]);
            this.f99c.f95c = false;
        }
    }

    static {
        new a(null);
    }

    public OooO0O0() {
        this(null, 1);
    }

    public OooO0O0(Context context) {
        h.d(context, "context");
        this.f93a = kotlin.d.b(OooO0OO.f96OooO00o);
        this.f94b = kotlin.d.b(OooO0o.f97OooO00o);
    }

    public final void a() {
        k().j();
        k().n();
        k().k();
    }

    public final void d(i iVar) {
        h.d(iVar, "timerCallback");
        k().e(iVar);
        l().e(iVar);
    }

    public final void e(DongleParams dongleParams) {
        h.d(dongleParams, "params");
        OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetMgr", "receiverVerification()");
        if (g()) {
            k().m(dongleParams);
            return;
        }
        OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b configListener = dongleParams.getConfigListener();
        if (configListener != null) {
            configListener.onError("cannot start ota");
        }
        OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b downloadListener = dongleParams.getDownloadListener();
        if (downloadListener == null) {
            return;
        }
        downloadListener.onError("cannot start ota");
    }

    public final void f(PlatformParams platformParams) {
        h.d(platformParams, "params");
        if (this.f95c) {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b configListener = platformParams.getConfigListener();
            if (configListener == null) {
                return;
            }
            configListener.onError("already start tuning mode");
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetMgr", "start tuning mode");
        ToastUtils.s("start tuning mode", new Object[0]);
        this.f95c = true;
        b bVar = new b(this);
        bVar.a(platformParams);
        bVar.start();
    }

    public final boolean g() {
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a aVar = OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a;
        return aVar.f() && aVar.b() && aVar.d();
    }

    public final void h() {
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.i.f162a.b();
    }

    public final void i(PlatformParams platformParams) {
        h.d(platformParams, "params");
        OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetMgr", "platformVerification()");
        if (g()) {
            l().n(platformParams);
            return;
        }
        OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b configListener = platformParams.getConfigListener();
        if (configListener != null) {
            configListener.onError("cannot start ota");
        }
        OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b downloadListener = platformParams.getDownloadListener();
        if (downloadListener == null) {
            return;
        }
        downloadListener.onError("cannot start ota");
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.b k() {
        return (OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.b) this.f93a.getValue();
    }

    public final d l() {
        return (d) this.f94b.getValue();
    }

    public final void m() {
        k().k();
        l().k();
    }

    public final boolean n() {
        return this.f95c;
    }

    public final void o() {
        k().j();
        l().j();
    }

    public final void p() {
        if (!this.f95c) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetMgr", "already stop tuning mode");
            return;
        }
        this.f95c = false;
        OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetMgr", "stop tuning mode");
        ToastUtils.s("stop tuning mode", new Object[0]);
    }

    public final void q() {
        l().j();
        l().p();
        l().k();
    }

    public /* synthetic */ OooO0O0(Context context, int i) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }
}
