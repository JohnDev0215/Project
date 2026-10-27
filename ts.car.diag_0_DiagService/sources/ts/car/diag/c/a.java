package ts.car.diag.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.provider.Settings;
import com.byd.mediacenter.api.media.MediaControl;
import com.ts.diag.DtcData;
import com.ts.diag.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import ts.car.diag.DiagServices;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1575a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private C0053a f1576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiagServices f1577c;

    /* JADX INFO: renamed from: ts.car.diag.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0053a extends BroadcastReceiver {
        C0053a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.ts.diag.test_return_all_dtc".equals(intent.getAction())) {
                a.this.d();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        DiagServices diagServices = this.f1577c;
        if (diagServices == null || diagServices.u == null) {
            q.e("DiagDTCTestManager", "testReportDtcData: mSubscribes == null ");
            return;
        }
        DtcData dtcData = new DtcData();
        ArrayList arrayList = new ArrayList();
        arrayList.add("9F2411");
        arrayList.add("9F2411");
        arrayList.add("9F2413");
        arrayList.add("9F2511");
        arrayList.add("9F2513");
        arrayList.add("9F2611");
        arrayList.add("9F2613");
        arrayList.add("9F2711");
        arrayList.add("9F2713");
        arrayList.add("9F2811");
        arrayList.add("9F2813");
        arrayList.add("9F2911");
        arrayList.add("9F2913");
        arrayList.add("9F2A11");
        arrayList.add("9F2619");
        arrayList.add("9F2A13");
        arrayList.add("9F2B11");
        arrayList.add("9F3019");
        arrayList.add("9F2B13");
        arrayList.add("9F2C13");
        arrayList.add("9F2D13");
        arrayList.add("9F2E13");
        arrayList.add("9F2F13");
        arrayList.add("9F2C11");
        arrayList.add("9F2719");
        arrayList.add("9F3013");
        arrayList.add("9F2D11");
        arrayList.add("9F2819");
        arrayList.add("9F3113");
        arrayList.add("9F2E11");
        arrayList.add("9F2919");
        arrayList.add("9F3213");
        arrayList.add("9F2F11");
        arrayList.add("9F3313");
        arrayList.add("9F3011");
        arrayList.add("9F3413");
        arrayList.add("9F3111");
        arrayList.add("9F3513");
        arrayList.add("9F3211");
        arrayList.add("9F3613");
        arrayList.add("9F3311");
        arrayList.add("9F3713");
        arrayList.add("9F3411");
        arrayList.add("9F3813");
        arrayList.add("9F3511");
        arrayList.add("9F3913");
        arrayList.add("9F3611");
        arrayList.add("9F3A13");
        arrayList.add("9F3B13");
        arrayList.add("9F3711");
        arrayList.add("9F2419");
        arrayList.add("9F3811");
        arrayList.add("9F3C13");
        arrayList.add("9F3911");
        arrayList.add("9F3D13");
        arrayList.add("9F3A11");
        arrayList.add("9F3E13");
        arrayList.add("9F3B11");
        arrayList.add("9F2519");
        arrayList.add("9F3C11");
        arrayList.add("9F2414");
        arrayList.add("9F3F13");
        arrayList.add("9F2514");
        arrayList.add("9F4013");
        arrayList.add("9F2614");
        arrayList.add("9F4113");
        arrayList.add("9F2714");
        arrayList.add("9F4213");
        arrayList.add("9F2814");
        arrayList.add("9F4313");
        arrayList.add("9F2914");
        arrayList.add("9F4413");
        q.e("DiagDTCTestManager", "testReportDtcData: list: " + arrayList);
        dtcData.setData(arrayList);
        dtcData.setStatus((byte) 0);
        Iterator<Subscribe> it = this.f1577c.u.iterator();
        while (it.hasNext()) {
            try {
                it.next().getiDiagReportCallBack().readDTCDataReport(dtcData, MediaControl.PARAM_SUCCESS);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void b(DiagServices diagServices) {
        int i = Settings.System.getInt(diagServices.getContentResolver(), "key_for_test_DiagDTCTestManager", 0);
        this.f1575a = i;
        if (i == 0) {
            q.a("DiagDTCTestManager", "init mForTestValue is " + this.f1575a + " execute return");
            return;
        }
        this.f1577c = diagServices;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ts.diag.test_return_all_dtc");
        C0053a c0053a = new C0053a();
        this.f1576b = c0053a;
        diagServices.registerReceiver(c0053a, intentFilter);
    }

    public void c() {
        DiagServices diagServices;
        C0053a c0053a = this.f1576b;
        if (c0053a == null || (diagServices = this.f1577c) == null) {
            return;
        }
        diagServices.unregisterReceiver(c0053a);
    }
}
