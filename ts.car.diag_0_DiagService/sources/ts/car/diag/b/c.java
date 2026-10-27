package ts.car.diag.b;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import c.a.a.a.h;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import ts.car.diag.manager.f;
import ts.car.diag.manager.i;
import ts.car.diag.manager.j;
import ts.car.diag.manager.m;
import ts.car.diag.manager.n;
import ts.car.diag.manager.o;
import ts.car.diag.manager.q;
import ts.car.diag.manager.u;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class c extends ts.car.diag.b.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private o f1556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f1557b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private j f1558c;
    private f d;
    private b e;
    h f;
    Context g;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ short f1559a;

        a(short s) {
            this.f1559a = s;
        }

        @Override // ts.car.diag.manager.u
        public void a(byte[] bArr) {
            q.h("HalReadManager", "readGyroStatus: " + c.this.c(bArr));
            c cVar = c.this;
            cVar.A(cVar.h(bArr, this.f1559a));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class b implements f.k {
        private b() {
        }

        @Override // ts.car.diag.manager.f.k
        public void a(short s, byte[] bArr) {
            c cVar = c.this;
            cVar.A(cVar.h(bArr, s));
        }

        /* synthetic */ b(c cVar, a aVar) {
            this();
        }
    }

    public c(h hVar, Context context) {
        this.f = hVar;
        this.g = context;
    }

    private boolean i(short s) {
        switch (s) {
            case -687:
                q(s);
                break;
            case -686:
                o(s);
                break;
            case -685:
                p(s);
                break;
        }
        return true;
    }

    private boolean k(short s) {
        if (s == -633) {
            r(s);
            return true;
        }
        switch (s) {
            case -640:
                s(s);
                break;
            case -639:
                t(s);
                break;
            case -638:
                u(s);
                break;
        }
        return true;
    }

    private boolean l(short s) {
        if (s == -704) {
            this.d.h();
            return true;
        }
        if (s == -698) {
            this.d.f();
            return true;
        }
        switch (s) {
            case -693:
                this.d.m();
                break;
            case -692:
                this.d.l();
                break;
            case -691:
                this.d.k();
                break;
        }
        return true;
    }

    private boolean m(short s) {
        switch (s) {
            case -671:
                y(s);
                break;
            case -670:
                x(s);
                break;
            case -669:
                w(s);
                break;
            case -668:
                v(s);
                break;
            case -667:
                z(s);
                break;
        }
        return true;
    }

    private void o(short s) {
        byte[] bArrF = this.f1557b.f();
        q.h("HalReadManager", "readBtConnectionStatus: status[0] " + ((int) bArrF[0]) + " status[1] " + ((int) bArrF[1]));
        A(h(bArrF, s));
    }

    private void p(short s) {
        q.h("HalReadManager", "readBtMacLocationAddress did " + Integer.toHexString(s));
        try {
            String strI = this.f1557b.i();
            String strJ = this.f1557b.j();
            q.i("HalReadManager", "readBtMacLocationAddress: bt0MacAddress " + strI + " bt1Message " + strJ);
            if (strI == null || strI.length() == 0) {
                strI = "                 ";
            }
            if (strJ == null || strJ.length() == 0) {
                strJ = "                 ";
            }
            byte[] bytes = strI.getBytes(StandardCharsets.US_ASCII);
            byte[] bytes2 = strJ.getBytes(StandardCharsets.US_ASCII);
            byte[] bArr = new byte[bytes.length + bytes2.length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            System.arraycopy(bytes2, 0, bArr, bytes.length, bytes2.length);
            A(h(bArr, s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void q(short s) {
        byte[] bArrG = this.f1557b.g();
        q.h("HalReadManager", "readBtOnOff: bt[0] " + ((int) bArrG[0]) + " bt[1] " + ((int) bArrG[1]));
        A(h(bArrG, s));
    }

    private void r(short s) {
        byte bA = this.f1558c.a();
        byte b2 = this.f1558c.b();
        byte bE = this.f1558c.e();
        byte[] bArr = new byte[7];
        bArr[0] = bA;
        bArr[1] = b2;
        bArr[2] = bE;
        byte[] bArrD = this.f1558c.d();
        for (int i = 0; i < bArrD.length; i++) {
            bArr[i + 3] = bArrD[i];
        }
        A(h(bArr, s));
    }

    private void s(short s) {
        byte bG = this.f1558c.g();
        q.h("HalReadManager", "readGpsInfo:" + ((int) bG));
        A(h(new byte[]{0, bG}, s));
    }

    private void t(short s) {
        byte bH = this.f1558c.h();
        q.h("HalReadManager", "readGpsLocationStatus:" + ((int) bH));
        A(h(g(bH), s));
    }

    private void u(short s) {
        this.f1558c.i(new a(s));
    }

    private void v(short s) {
        String strC = this.f1556a.c();
        q.h("HalReadManager", "readWifiApSsid: len:" + strC.length());
        StringBuffer stringBuffer = new StringBuffer(strC);
        int length = strC.length();
        if (length < 30) {
            for (int i = 0; i < 30 - length; i++) {
                stringBuffer.append(" ");
            }
        }
        A(h(stringBuffer.toString().getBytes(StandardCharsets.US_ASCII), s));
    }

    private void w(short s) {
        byte bD = this.f1556a.d();
        q.h("HalReadManager", "readWifiConnectionStatus WIFI mode:" + ((int) bD));
        byte[] bArr = new byte[2];
        bArr[0] = bD;
        if (bD == 1) {
            boolean zB = this.f1556a.b();
            q.h("HalReadManager", "readWifiConnectionStatus AP conn status:" + zB);
            if (zB) {
                bArr[1] = 1;
            } else {
                bArr[1] = 2;
            }
        } else if (bD != 2) {
            q.b("HalReadManager", "READ_WIFICONNECTIONSTATUS in dual/invalid mode");
            bArr[1] = -1;
        } else {
            byte bJ = this.f1556a.j();
            q.h("HalReadManager", "readWifiConnectionStatus station :" + ((int) bJ));
            bArr[1] = bJ;
        }
        A(h(bArr, s));
    }

    private void x(short s) {
        byte bD = this.f1556a.d();
        q.h("HalReadManager", "readWifiMode:" + ((int) bD));
        A(h(g(bD), s));
    }

    private void y(short s) {
        byte bE = this.f1556a.e();
        q.h("HalReadManager", "readWifiOnOff:" + ((int) bE));
        A(h(g(bE), s));
    }

    private void z(short s) {
        String strG = this.f1556a.g();
        String strF = this.f1556a.f();
        q.i("HalReadManager", "readWifiSelfMacAddress: stationMacAddress " + strG + " apMacAddress " + strF);
        if (TextUtils.isEmpty(strG)) {
            strG = "                 ";
        }
        if (TextUtils.isEmpty(strF)) {
            strF = "                 ";
        }
        String str = strG + strF;
        q.i("HalReadManager", "readWifiSelfMacAddress: selfMacAddress " + str);
        q.h("HalReadManager", "readWifiSelfMacAddress: length " + str.length());
        A(h(str.getBytes(StandardCharsets.US_ASCII), s));
    }

    public void A(c.a.a.a.b bVar) {
        if (this.f != null) {
            try {
                q.h("HalReadManager", "sendResultToHal " + Integer.toHexString(bVar.f1281a) + " res " + bVar);
                this.f.f(bVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void B(h hVar) {
        this.f = hVar;
    }

    public c.a.a.a.b h(byte[] bArr, short s) {
        c.a.a.a.b bVar = new c.a.a.a.b();
        for (int i = 0; i < bArr.length; i++) {
            bVar.d.add(Byte.valueOf(bArr[i]));
            q.a("HalReadManager", "Diag_ReadResp is:" + ((int) bArr[i]));
        }
        bVar.f1281a = s;
        bVar.f1283c = (short) bArr.length;
        q.h("HalReadManager", "Diag_ReadResp did " + ((int) s) + " bytes.length is:" + bArr.length + " data : " + Arrays.toString(bArr));
        return bVar;
    }

    public void j(short s) {
        q.h("HalReadManager", "halReadFromFramework: did " + ((int) s));
        if (l(s) || i(s) || m(s) || k(s) || s != 4660) {
            return;
        }
        q.h("HalReadManager", "TEST did 0x1234");
        A(h(new byte[]{1}, s));
    }

    public void n(o oVar, i iVar, j jVar, n nVar, ts.car.diag.manager.h hVar, m mVar) {
        this.f1556a = oVar;
        this.f1557b = iVar;
        this.f1558c = jVar;
        this.d = f.g(this.g);
        b bVar = new b(this, null);
        this.e = bVar;
        this.d.p(bVar);
    }
}
