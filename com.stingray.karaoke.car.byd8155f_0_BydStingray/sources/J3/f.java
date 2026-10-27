package J3;

import A4.q;
import android.net.ConnectivityManager;
import android.net.Network;
import l3.P;
import m3.RunnableC1802b;

/* JADX INFO: loaded from: classes.dex */
public final class f extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3080a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3081b;

    public /* synthetic */ f(Object obj, int i7) {
        this.f3080a = i7;
        this.f3081b = obj;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        switch (this.f3080a) {
            case 0:
                P.c((P) this.f3081b, network, true);
                break;
            default:
                q.f().post(new RunnableC1802b(this, true));
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        switch (this.f3080a) {
            case 0:
                P.c((P) this.f3081b, network, false);
                break;
            default:
                q.f().post(new RunnableC1802b(this, false));
                break;
        }
    }
}
