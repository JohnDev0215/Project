package b.a.c.u;

import android.os.RemoteException;
import android.util.Log;
import b.a.c.d;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: RpcModel.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.a.c.d {
    public j n;

    @Override // b.a.c.d
    public d.g H() {
        return new d();
    }

    public Reply T(int i, GetMsg getMsg) {
        Reply replyP;
        ArrayList<d.j> arrayListJ = J();
        Iterator<d.j> it = arrayListJ.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            d.j next = it.next();
            if (next != null && next.d() == i) {
                b.a.c.f fVarE = next.e();
                if (fVarE == null || !fVarE.asBinder().pingBinder()) {
                    break;
                }
                try {
                    replyP = fVarE.p(getMsg);
                } catch (RemoteException e) {
                    Reply reply = new Reply();
                    reply.h(-1);
                    reply.g(Log.getStackTraceString(e));
                    replyP = reply;
                }
            }
        }
        replyP = null;
        if (replyP == null && f.b()) {
            f.d("getInstance: " + i + " -> " + getMsg.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("connection count: ");
            sb.append(arrayListJ.size());
            f.d(sb.toString());
            for (int i2 = 0; i2 < arrayListJ.size(); i2++) {
                d.j jVar = arrayListJ.get(i2);
                f.d("connection[" + i2 + "]: " + jVar.toString());
                b.a.c.f fVarE2 = jVar.e();
                if (fVarE2 != null) {
                    f.d("connection[" + i2 + "]: pingBinder: " + fVarE2.asBinder().pingBinder());
                }
            }
        }
        return replyP;
    }

    public j U() {
        return this.n;
    }

    public Reply V(int i, InvokeMsg invokeMsg) {
        int i2;
        Reply replyN;
        ArrayList<d.j> arrayListJ = J();
        Iterator<d.j> it = arrayListJ.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            d.j next = it.next();
            if (next != null && next.d() == i) {
                b.a.c.f fVarE = next.e();
                if (fVarE == null || !fVarE.asBinder().pingBinder()) {
                    break;
                }
                try {
                    if (invokeMsg.t() || !invokeMsg.s().equals(Void.TYPE.getName())) {
                        replyN = fVarE.n(invokeMsg);
                    } else {
                        fVarE.k(invokeMsg);
                        replyN = new Reply();
                        replyN.h(0);
                    }
                } catch (RemoteException e) {
                    Reply reply = new Reply();
                    reply.h(-1);
                    reply.g(Log.getStackTraceString(e));
                    replyN = reply;
                }
            }
        }
        replyN = null;
        if (replyN == null && f.b()) {
            f.d("invokeMethod: " + i + " -> " + invokeMsg.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("connection count: ");
            sb.append(arrayListJ.size());
            f.d(sb.toString());
            for (i2 = 0; i2 < arrayListJ.size(); i2++) {
                d.j jVar = arrayListJ.get(i2);
                f.d("connection[" + i2 + "]: " + jVar.toString());
                b.a.c.f fVarE2 = jVar.e();
                if (fVarE2 != null) {
                    f.d("connection[" + i2 + "]: pingBinder: " + fVarE2.asBinder().pingBinder());
                }
            }
        }
        return replyN;
    }

    public void W(j jVar) {
        this.n = jVar;
    }
}
