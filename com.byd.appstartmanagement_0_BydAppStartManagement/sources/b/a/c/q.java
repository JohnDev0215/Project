package b.a.c;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.os.RemoteException;
import b.a.c.f;
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
    public Context f258a;

    /* JADX INFO: compiled from: RemoteModelServiceStub.java */
    public interface a {
    }

    public q(Context context) {
        this.f258a = context;
    }

    public void D() {
        Context applicationContext = this.f258a.getApplicationContext();
        if (applicationContext != null) {
            this.f258a = applicationContext;
        }
        ComplexData.i(this.f258a.getClassLoader());
    }

    @Override // b.a.c.f
    public void e(GCMsg gCMsg) throws RemoteException {
        p.r(gCMsg);
    }

    @Override // b.a.c.f
    public ComplexData f(Map map, ComplexData complexData) throws RemoteException {
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

    @Override // b.a.c.f
    public void k(InvokeMsg invokeMsg) {
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

    @Override // b.a.c.f
    public Reply n(InvokeMsg invokeMsg) throws RemoteException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return p.k(invokeMsg);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // b.a.c.f
    public Reply p(GetMsg getMsg) throws RemoteException {
        return p.i(getMsg);
    }

    @Override // b.a.c.f
    public void q(e eVar, String str, int i, long j) throws RemoteException {
        if (eVar != null) {
            p.n(eVar, str, i, j);
        }
    }

    @Override // b.a.c.f
    public void r(e eVar) throws RemoteException {
        if (eVar != null) {
            p.s(eVar);
        }
    }

    @Override // b.a.c.f
    public int v() {
        return Process.myPid();
    }
}
