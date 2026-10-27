package ts.car.diag.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.provider.Settings;
import c.a.a.a.c;
import c.a.a.a.f;
import c.a.a.a.h;
import java.util.ArrayList;
import ts.car.diag.DiagServices;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class b {
    private static b e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1579a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiagServices.c f1580b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private h f1581c;
    private ts.car.diag.b.a d;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (b.this.f1579a == 0) {
                q.a("DiagTestManager", "onReceive mForTestValue is" + b.this.f1579a + " execute return");
                return;
            }
            if (!"com.ts.diag.test_action".equals(intent.getAction()) || b.this.f1580b == null) {
                return;
            }
            try {
                if (b.this.j(intent)) {
                    return;
                }
                if (b.this.i(intent)) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    b() {
    }

    private f f(short s, byte b2) {
        f fVar = new f();
        fVar.f1291a = s;
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add(Byte.valueOf(b2));
        if (s == -687) {
            arrayList.add(Byte.valueOf(b2));
        }
        fVar.f1293c = arrayList;
        return fVar;
    }

    public static synchronized b g() {
        if (e == null) {
            e = new b();
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i(Intent intent) {
        int intExtra = intent.getIntExtra("value", -1);
        int intExtra2 = intent.getIntExtra("type", -1);
        int intExtra3 = intent.getIntExtra("operateData", 1);
        int intExtra4 = intent.getIntExtra("controlType", 1);
        String stringExtra = intent.getStringExtra("data");
        if (intExtra2 == 4) {
            o((byte) intExtra3);
            return true;
        }
        if (intExtra2 != 6) {
            return false;
        }
        k(intExtra, intExtra4, stringExtra);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("value", -1);
            int intExtra2 = intent.getIntExtra("type", -1);
            int intExtra3 = intent.getIntExtra("operateData", 1);
            int intExtra4 = intent.getIntExtra("controlType", 1);
            intent.getStringExtra("data");
            q.a("DiagTestManager", "onReceive type " + intExtra2 + " did " + intExtra);
            if (intExtra2 == 1) {
                this.f1580b.i((short) intExtra);
            } else if (intExtra2 == 2) {
                this.f1580b.q(f((short) intExtra, (byte) intExtra3));
            } else {
                if (intExtra2 != 3) {
                    return false;
                }
                c cVar = new c();
                cVar.f1285b = (short) intExtra;
                cVar.f1284a = (byte) intExtra4;
                this.f1580b.n(cVar);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void k(int i, int i2, String str) {
        q.a("DiagTestManager", "sendToPki: did " + i + " controlType " + i2 + " data " + str);
        this.d.j(e(i, i2, str));
    }

    private void o(byte b2) {
        StringBuilder sb = new StringBuilder();
        sb.append("testRequestUpdateDtcData: operateData ");
        sb.append((int) b2);
        sb.append(" , mDiag is null：");
        sb.append(this.f1581c == null);
        q.a("DiagTestManager", sb.toString());
        h hVar = this.f1581c;
        if (hVar != null) {
            try {
                hVar.o(this.f1580b, b2);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public c e(int i, int i2, String str) {
        c cVar = new c();
        cVar.f1284a = (byte) i2;
        cVar.f1285b = (short) i;
        ArrayList<Byte> arrayList = new ArrayList<>();
        if (str != null) {
            byte[] bytes = str.getBytes();
            for (int i3 = 0; i3 < str.length(); i3++) {
                arrayList.add(Byte.valueOf(bytes[i3]));
            }
        }
        cVar.f1286c = arrayList;
        return cVar;
    }

    public void h(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "key_for_test_DiagTestManager", 0);
        this.f1579a = i;
        if (i != 0) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.ts.diag.test_action");
            context.getApplicationContext().registerReceiver(new a(), intentFilter);
            return;
        }
        q.a("DiagTestManager", "init mForTestValue is " + this.f1579a + " execute return");
    }

    public void l(DiagServices.c cVar) {
        if (this.f1579a != 0) {
            this.f1580b = cVar;
            return;
        }
        q.a("DiagTestManager", "setDiagCallback mForTestValue is" + this.f1579a + " execute return");
    }

    public void m(h hVar) {
        if (this.f1579a != 0) {
            this.f1581c = hVar;
            return;
        }
        q.a("DiagTestManager", "setDiagHalService mForTestValue is" + this.f1579a + " execute return");
    }

    public void n(ts.car.diag.b.a aVar) {
        if (this.f1579a != 0) {
            this.d = aVar;
            return;
        }
        q.a("DiagTestManager", "setPkiManager mForTestValue is" + this.f1579a + " execute return");
    }
}
