package b.b.l.u;

import android.os.RemoteException;
import android.util.Log;
import b.b.l.d;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: RpcModel.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.b.l.d {
    public j n;

    @Override // b.b.l.d
    public d.g H() {
        return new d();
    }

    public Reply T(int i, GetMsg getMsg) {
        Reply replyM;
        ArrayList<d.j> arrayListJ = J();
        Iterator<d.j> it = arrayListJ.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            d.j next = it.next();
            if (next != null && next.d() == i) {
                b.b.l.f fVarE = next.e();
                if (fVarE == null || !fVarE.asBinder().pingBinder()) {
                    break;
                }
                try {
                    replyM = fVarE.M(getMsg);
                } catch (RemoteException e2) {
                    Reply reply = new Reply();
                    reply.h(-1);
                    reply.g(Log.getStackTraceString(e2));
                    replyM = reply;
                }
            }
        }
        replyM = null;
        if (replyM == null && f.b()) {
            f.d("getInstance: " + i + " -> " + getMsg.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("connection count: ");
            sb.append(arrayListJ.size());
            f.d(sb.toString());
            for (int i2 = 0; i2 < arrayListJ.size(); i2++) {
                d.j jVar = arrayListJ.get(i2);
                f.d("connection[" + i2 + "]: " + jVar.toString());
                b.b.l.f fVarE2 = jVar.e();
                if (fVarE2 != null) {
                    f.d("connection[" + i2 + "]: pingBinder: " + fVarE2.asBinder().pingBinder());
                }
            }
        }
        return replyM;
    }

    public j U() {
        return this.n;
    }

    public Reply V(int i, InvokeMsg invokeMsg) {
        int i2;
        Reply replyD;
        ArrayList<d.j> arrayListJ = J();
        Iterator<d.j> it = arrayListJ.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            d.j next = it.next();
            if (next != null && next.d() == i) {
                b.b.l.f fVarE = next.e();
                if (fVarE == null || !fVarE.asBinder().pingBinder()) {
                    break;
                }
                try {
                    if (invokeMsg.t() || !invokeMsg.s().equals(Void.TYPE.getName())) {
                        replyD = fVarE.D(invokeMsg);
                    } else {
                        fVarE.x(invokeMsg);
                        replyD = new Reply();
                        replyD.h(0);
                    }
                } catch (RemoteException e2) {
                    Reply reply = new Reply();
                    reply.h(-1);
                    reply.g(Log.getStackTraceString(e2));
                    replyD = reply;
                }
            }
        }
        replyD = null;
        if (replyD == null && f.b()) {
            f.d("invokeMethod: " + i + " -> " + invokeMsg.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("connection count: ");
            sb.append(arrayListJ.size());
            f.d(sb.toString());
            for (i2 = 0; i2 < arrayListJ.size(); i2++) {
                d.j jVar = arrayListJ.get(i2);
                f.d("connection[" + i2 + "]: " + jVar.toString());
                b.b.l.f fVarE2 = jVar.e();
                if (fVarE2 != null) {
                    f.d("connection[" + i2 + "]: pingBinder: " + fVarE2.asBinder().pingBinder());
                }
            }
        }
        return replyD;
    }

    public void W(j jVar) {
        this.n = jVar;
    }
}
