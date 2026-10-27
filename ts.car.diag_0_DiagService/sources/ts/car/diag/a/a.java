package ts.car.diag.a;

import android.os.RemoteCallbackList;
import com.ts.diag.IDiagOtaCallBack;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f1550b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RemoteCallbackList<IDiagOtaCallBack> f1551a = new RemoteCallbackList<>();

    a() {
    }

    public static synchronized a a() {
        if (f1550b == null) {
            f1550b = new a();
        }
        return f1550b;
    }

    public void b(byte b2) {
        q.e("OtaCallBackManager", " notify7dfConnectStatusChange " + ((int) b2));
        int iBeginBroadcast = this.f1551a.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.f1551a.getBroadcastItem(i).on7dfConnectStatusChange(b2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f1551a.finishBroadcast();
    }

    public void c(IDiagOtaCallBack iDiagOtaCallBack) {
        this.f1551a.register(iDiagOtaCallBack);
    }

    public void d(IDiagOtaCallBack iDiagOtaCallBack) {
        this.f1551a.unregister(iDiagOtaCallBack);
    }
}
