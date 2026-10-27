package ts.car.diag.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ts.car.diag.DiagServices;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class Receiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        q.a("DIAG-RECEIVER", "broadcast Receive success! ");
        String action = intent.getAction();
        if (!"com.action.exportLogs".equals(action)) {
            if ("com.action.getExportLogsResult".equals(action)) {
                c.a.a.a.c cVar = new c.a.a.a.c();
                cVar.f1284a = (byte) 3;
                cVar.f1285b = (short) -413;
                DiagServices diagServicesW = DiagServices.w();
                if (diagServicesW == null) {
                    q.a("DIAG-RECEIVER", "diag is null! ");
                    return;
                } else {
                    diagServicesW.x(cVar);
                    return;
                }
            }
            return;
        }
        c.a.a.a.c cVar2 = new c.a.a.a.c();
        cVar2.f1284a = (byte) 1;
        cVar2.f1285b = (short) -413;
        cVar2.f1286c.add((byte) 0);
        cVar2.f1286c.add((byte) 17);
        cVar2.f1286c.add((byte) 34);
        cVar2.f1286c.add((byte) 51);
        cVar2.f1286c.add((byte) 68);
        DiagServices diagServicesW2 = DiagServices.w();
        if (diagServicesW2 == null) {
            q.a("DIAG-RECEIVER", "ds is null! ");
        } else {
            diagServicesW2.x(cVar2);
        }
    }
}
