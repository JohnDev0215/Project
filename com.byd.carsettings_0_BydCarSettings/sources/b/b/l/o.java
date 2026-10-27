package b.b.l;

import android.os.RemoteException;
import b.b.l.e;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.CallbackMsg;
import com.byd.mvcadapter.rpc.GCMsg;
import java.util.Map;

/* JADX INFO: compiled from: RemoteModelCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public class o extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2002a;

    public o(d dVar) {
        this.f2002a = dVar;
    }

    @Override // b.b.l.e
    public void G(CallbackMsg callbackMsg) throws RemoteException {
        b.b.l.u.h hVar = (b.b.l.u.h) this.f2002a;
        hVar.U().B(hVar.U().A(callbackMsg));
    }

    @Override // b.b.l.e
    public Reply Q(CallbackMsg callbackMsg) throws RemoteException {
        return ((b.b.l.u.h) this.f2002a).U().A(callbackMsg);
    }

    @Override // b.b.l.e
    public void R(Reply reply) {
        ((b.b.l.u.h) this.f2002a).U().B(reply);
    }

    @Override // b.b.l.e
    public void i(GCMsg gCMsg) throws RemoteException {
        ((b.b.l.u.h) this.f2002a).U().s(gCMsg);
    }

    @Override // b.b.l.e
    public ComplexData l(Map map, ComplexData complexData) throws RemoteException {
        Object objL;
        Object[] objArr;
        if (this.f2002a != null) {
            l lVarJ = p.j(map);
            lVarJ.b(complexData);
            objL = this.f2002a.l(this.f2002a.O(lVarJ));
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
}
