package c.a.b;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.os.RemoteException;
import c.a.b.e;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.Map;

/* JADX INFO: compiled from: RemoteModelServiceStub.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3491a;

    public n(Context context) {
        this.f3491a = context;
    }

    @Override // c.a.b.e
    public int D() {
        return Process.myPid();
    }

    @Override // c.a.b.e
    public Reply G(InvokeMsg invokeMsg) throws RemoteException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return m.k(invokeMsg);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void K() {
        Context applicationContext = this.f3491a.getApplicationContext();
        if (applicationContext != null) {
            this.f3491a = applicationContext;
        }
        ComplexData.r(this.f3491a.getClassLoader());
    }

    @Override // c.a.b.e
    public void f(GCMsg gCMsg) throws RemoteException {
        m.q(gCMsg);
    }

    @Override // c.a.b.e
    public void p(InvokeMsg invokeMsg) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Reply replyK = m.k(invokeMsg);
            if (replyK != null && replyK.k() != 0) {
                m.e(invokeMsg.j().j(), replyK);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // c.a.b.e
    public Reply q(GetMsg getMsg) throws RemoteException {
        return m.i(getMsg);
    }

    @Override // c.a.b.e
    public void r(d dVar, String str, int i2, long j) throws RemoteException {
        if (dVar != null) {
            m.n(dVar, str, i2, j);
        }
    }

    @Override // c.a.b.e
    public ComplexData t(Map map, ComplexData complexData) throws RemoteException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            j jVarJ = m.j(map);
            jVarJ.b(complexData);
            Object objM = m.m(jVarJ);
            return new ComplexData(objM != null ? new Object[]{objM} : new Object[0]);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // c.a.b.e
    public void u(d dVar) throws RemoteException {
        if (dVar != null) {
            m.r(dVar);
        }
    }
}
