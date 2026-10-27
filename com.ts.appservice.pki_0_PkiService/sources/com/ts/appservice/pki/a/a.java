package com.ts.appservice.pki.a;

import android.os.RemoteCallbackList;
import com.ts.lib.pki.bean.PkiReadResp;
import com.ts.lib.pki.bean.PkiRoutineControlResp;
import com.ts.lib.pki.bean.PkiWriteResp;
import com.ts.lib.pki.callback.IPkiInstallCallBack;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RemoteCallbackList<IPkiInstallCallBack> f12a = new RemoteCallbackList<>();

    public void a(PkiReadResp pkiReadResp) {
        int iBeginBroadcast = this.f12a.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.f12a.getBroadcastItem(i).onPkiReadResp(pkiReadResp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f12a.finishBroadcast();
    }

    public void b(PkiWriteResp pkiWriteResp) {
        int iBeginBroadcast = this.f12a.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.f12a.getBroadcastItem(i).onPkiWriteResp(pkiWriteResp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f12a.finishBroadcast();
    }

    public void c(PkiRoutineControlResp pkiRoutineControlResp) {
        int iBeginBroadcast = this.f12a.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.f12a.getBroadcastItem(i).onPkiRoutineControlResp(pkiRoutineControlResp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f12a.finishBroadcast();
    }

    public void d() {
        this.f12a.kill();
    }

    public void e(IPkiInstallCallBack iPkiInstallCallBack) {
        if (iPkiInstallCallBack != null) {
            this.f12a.register(iPkiInstallCallBack);
        }
    }

    public void f(IPkiInstallCallBack iPkiInstallCallBack) {
        if (iPkiInstallCallBack != null) {
            this.f12a.unregister(iPkiInstallCallBack);
        }
    }
}
