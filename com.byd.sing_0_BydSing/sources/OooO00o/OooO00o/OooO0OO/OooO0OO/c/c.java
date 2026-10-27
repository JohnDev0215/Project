package OooO00o.OooO00o.OooO0Oo.OooO0Oo.c;

import com.blankj.utilcode.util.NetworkUtils;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class c implements NetworkUtils.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f138a;

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public interface b {
        void onNetworkChange(boolean z);
    }

    static {
        new a(null);
    }

    @Override // com.blankj.utilcode.util.NetworkUtils.a
    public void a() {
        OooO00o.OooO00o.OooO0O0.b.f41a.d("NetworkStatusMgr", " onDisconnected");
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a.c(false);
        b bVar = this.f138a;
        if (bVar == null) {
            return;
        }
        bVar.onNetworkChange(false);
    }

    @Override // com.blankj.utilcode.util.NetworkUtils.a
    public void b(NetworkUtils.NetworkType networkType) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("NetworkStatusMgr", "onConnected");
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a.c(true);
        b bVar = this.f138a;
        if (bVar == null) {
            return;
        }
        bVar.onNetworkChange(true);
    }

    public final void c() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("NetworkStatusMgr", "release NetworkStatusMgr");
        try {
            NetworkUtils.f(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void d(b bVar) {
        h.d(bVar, "networkChangeListener");
        this.f138a = bVar;
    }

    public final void e() {
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e("NetworkStatusMgr", "init NetworkStatusMgr");
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a aVar = OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a;
        boolean zC = NetworkUtils.c();
        bVar.e("NetworkStatusMgr", h.i("network is connected: ", Boolean.valueOf(zC)));
        aVar.c(zC);
        NetworkUtils.e(this);
    }
}
