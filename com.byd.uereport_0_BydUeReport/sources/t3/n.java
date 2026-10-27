package t3;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.os.RemoteException;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.Map;
import t3.e;

/* JADX INFO: compiled from: RemoteModelServiceStub.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8063a;

    public n(Context context) {
        this.f8063a = context;
    }

    public void A0() {
        Context applicationContext = this.f8063a.getApplicationContext();
        if (applicationContext != null) {
            this.f8063a = applicationContext;
        }
        ComplexData.n(this.f8063a.getClassLoader());
    }

    @Override // t3.e
    public Reply G(InvokeMsg invokeMsg) throws RemoteException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return m.k(invokeMsg);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // t3.e
    public void J(GCMsg gCMsg) throws RemoteException {
        m.q(gCMsg);
    }

    @Override // t3.e
    public Reply b0(GetMsg getMsg) throws RemoteException {
        return m.i(getMsg);
    }

    @Override // t3.e
    public void c0(d dVar, String str, int i8, long j8) throws RemoteException {
        if (dVar != null) {
            m.n(dVar, str, i8, j8);
        }
    }

    @Override // t3.e
    public ComplexData e0(Map map, ComplexData complexData) throws RemoteException {
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

    @Override // t3.e
    public void f0(d dVar) throws RemoteException {
        if (dVar != null) {
            m.r(dVar);
        }
    }

    @Override // t3.e
    public int r0() {
        return Process.myPid();
    }

    @Override // t3.e
    public void x(InvokeMsg invokeMsg) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Reply replyK = m.k(invokeMsg);
            if (replyK != null && replyK.g() != 0) {
                m.e(invokeMsg.f().f(), replyK);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
