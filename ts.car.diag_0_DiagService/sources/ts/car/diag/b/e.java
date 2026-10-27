package ts.car.diag.b;

import android.content.Context;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import c.a.a.a.h;
import com.byd.mediacenter.api.media.MediaControl;
import com.ts.diag.ReadResp;
import com.ts.diag.Subscribe;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import ts.car.diag.DiagServices;
import ts.car.diag.manager.f;
import ts.car.diag.manager.g;
import ts.car.diag.manager.i;
import ts.car.diag.manager.j;
import ts.car.diag.manager.m;
import ts.car.diag.manager.n;
import ts.car.diag.manager.o;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class e extends ts.car.diag.b.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    h f1571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private o f1572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i f1573c;
    private g d;
    private f e;
    private b f;
    private DiagServices g = DiagServices.w();
    Context h;

    /* JADX INFO: compiled from: caradapter-bug */
    private class b implements f.m {
        private b() {
        }

        @Override // ts.car.diag.manager.f.m
        public void a(short s, boolean z) {
            q.h("HalWriteManager", "onWriteControlResult " + Integer.toHexString(s) + " status: " + z);
            e eVar = e.this;
            eVar.p(eVar.j(s, !z ? (byte) 1 : (byte) 0));
        }
    }

    public e(h hVar, Context context) {
        this.f1571a = hVar;
        this.h = context;
    }

    private c.a.a.a.g i(c.a.a.a.f fVar, byte b2) {
        c.a.a.a.g gVar = new c.a.a.a.g();
        gVar.f1294a = fVar.f1291a;
        gVar.f1295b = b2;
        return gVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c.a.a.a.g j(short s, byte b2) {
        c.a.a.a.g gVar = new c.a.a.a.g();
        gVar.f1294a = s;
        gVar.f1295b = b2;
        return gVar;
    }

    private byte k(c.a.a.a.f fVar) {
        List<Subscribe> list = this.g.u;
        Iterator<Subscribe> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Subscribe next = it.next();
            ReadResp readResp = new ReadResp();
            readResp.setDataLength(fVar.f1292b);
            readResp.setDid(fVar.f1291a);
            readResp.setData(fVar.f1293c);
            RemoteCallbackList remoteCallbackList = new RemoteCallbackList();
            remoteCallbackList.register(next.getiDiagReportCallBack());
            int iBeginBroadcast = remoteCallbackList.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                remoteCallbackList.getBroadcastItem(i).readInfoFromHalReport(readResp, MediaControl.PARAM_SUCCESS);
            }
            remoteCallbackList.finishBroadcast();
        }
        q.h("HalWriteManager", "subscribes.size is " + list.size());
        return list.size() > 0 ? (byte) 0 : (byte) 1;
    }

    private byte[] l(c.a.a.a.f fVar) {
        int size = fVar.f1293c.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = fVar.f1293c.get(i).byteValue();
        }
        return bArr;
    }

    public void m(c.a.a.a.f fVar, byte b2) {
        q.h("HalWriteManager", "halWriteToFramework did: " + ((int) fVar.f1291a) + ", operateData: " + ((int) b2));
        try {
            if (o(fVar, b2)) {
                return;
            }
            short s = fVar.f1291a;
            byte bI = s != -763 ? s != -718 ? s != -687 ? s != -668 ? s != 4660 ? s != -671 ? s != -670 ? (byte) 1 : this.f1572b.i(b2) : this.f1572b.m(Byte.valueOf(b2)) : (byte) 0 : this.f1572b.h(new String(l(fVar), StandardCharsets.US_ASCII)) : this.f1573c.k(l(fVar)) : this.d.b(b2) : k(fVar);
            q.h("HalWriteManager", "halWriteToFramework diag_writeClientsReport writeReq " + ((int) fVar.f1291a) + " status: " + ((int) bI));
            if (this.f1571a != null) {
                this.f1571a.k(i(fVar, bI));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void n(o oVar, i iVar, j jVar, n nVar, ts.car.diag.manager.h hVar, m mVar) {
        this.f1572b = oVar;
        this.f1573c = iVar;
        this.e = f.g(this.h);
        b bVar = new b();
        this.f = bVar;
        this.e.r(bVar);
        this.d = g.a(this.h);
    }

    public boolean o(c.a.a.a.f fVar, byte b2) {
        short s = fVar.f1291a;
        if (s == -704) {
            this.e.o(a(l(fVar)));
            return true;
        }
        if (s == -697) {
            this.e.d(b2);
            return true;
        }
        if (s != -693) {
            return false;
        }
        byte[] bArrL = l(fVar);
        this.e.s(a(bArrL), b(bArrL, 4));
        return true;
    }

    public void p(c.a.a.a.g gVar) {
        if (this.f1571a != null) {
            try {
                q.h("HalWriteManager", "sendResultToHal " + Integer.toHexString(gVar.f1294a) + " res " + gVar);
                this.f1571a.k(gVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void q(h hVar) {
        this.f1571a = hVar;
    }
}
