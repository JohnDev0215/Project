package b.a.c;

import android.os.RemoteException;
import b.a.c.e;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.CallbackMsg;
import com.byd.mvcadapter.rpc.GCMsg;
import java.util.Map;

/* JADX INFO: compiled from: RemoteModelCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public class o extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f246a;

    public o(d dVar) {
        this.f246a = dVar;
    }

    @Override // b.a.c.e
    public void e(GCMsg gCMsg) throws RemoteException {
        ((b.a.c.u.h) this.f246a).U().s(gCMsg);
    }

    @Override // b.a.c.e
    public ComplexData f(Map map, ComplexData complexData) throws RemoteException {
        Object objL;
        Object[] objArr;
        if (this.f246a != null) {
            l lVarJ = p.j(map);
            lVarJ.b(complexData);
            objL = this.f246a.l(this.f246a.O(lVarJ));
        } else {
            objL = null;
        }
        if (objL == null) {
            objArr = new Object[0];
        } else {
            if (objL instanceof ComplexData) {
                return (ComplexData) objL;
            }
            objArr = objL.getClass().isArray() ? (Object[]) objL : new Object[]{objL};
        }
        return new ComplexData(objArr);
    }

    @Override // b.a.c.e
    public void o(CallbackMsg callbackMsg) throws RemoteException {
        b.a.c.u.h hVar = (b.a.c.u.h) this.f246a;
        hVar.U().B(hVar.U().A(callbackMsg));
    }

    @Override // b.a.c.e
    public Reply t(CallbackMsg callbackMsg) throws RemoteException {
        return ((b.a.c.u.h) this.f246a).U().A(callbackMsg);
    }

    @Override // b.a.c.e
    public void u(Reply reply) {
        ((b.a.c.u.h) this.f246a).U().B(reply);
    }
}
