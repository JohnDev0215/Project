package b.b.l;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.os.RemoteException;
import b.b.l.f;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.Map;

/* JADX INFO: compiled from: RemoteModelServiceStub.java */
/* JADX INFO: loaded from: classes2.dex */
public class q extends f.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2016a;

    /* JADX INFO: compiled from: RemoteModelServiceStub.java */
    public interface a {
    }

    public q(Context context) {
        this.f2016a = context;
    }

    @Override // b.b.l.f
    public Reply D(InvokeMsg invokeMsg) throws RemoteException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return p.k(invokeMsg);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // b.b.l.f
    public Reply M(GetMsg getMsg) throws RemoteException {
        return p.i(getMsg);
    }

    @Override // b.b.l.f
    public void N(e eVar, String str, int i, long j) throws RemoteException {
        if (eVar != null) {
            p.n(eVar, str, i, j);
        }
    }

    @Override // b.b.l.f
    public void O(e eVar) throws RemoteException {
        if (eVar != null) {
            p.s(eVar);
        }
    }

    @Override // b.b.l.f
    public int W() {
        return Process.myPid();
    }

    public void f0() {
        Context applicationContext = this.f2016a.getApplicationContext();
        if (applicationContext != null) {
            this.f2016a = applicationContext;
        }
        ComplexData.i(this.f2016a.getClassLoader());
    }

    @Override // b.b.l.f
    public void i(GCMsg gCMsg) throws RemoteException {
        p.r(gCMsg);
    }

    @Override // b.b.l.f
    public ComplexData l(Map map, ComplexData complexData) throws RemoteException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            l lVarJ = p.j(map);
            lVarJ.b(complexData);
            Object objM = p.m(lVarJ);
            return new ComplexData(objM != null ? new Object[]{objM} : new Object[0]);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // b.b.l.f
    public void x(InvokeMsg invokeMsg) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Reply replyK = p.k(invokeMsg);
            if (replyK != null && replyK.b() != 0) {
                p.e(invokeMsg.a().a(), replyK);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
