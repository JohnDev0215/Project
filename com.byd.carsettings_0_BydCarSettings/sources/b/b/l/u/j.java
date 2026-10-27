package b.b.l.u;

import android.content.ComponentName;
import android.content.Context;
import android.os.IInterface;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.util.Log;
import b.b.l.d;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.refect.RefUtils;
import com.byd.mvcadapter.rpc.CallbackMsg;
import com.byd.mvcadapter.rpc.GCMsg;
import java.util.ArrayList;

/* JADX INFO: compiled from: RpcRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends c {
    public h j;
    public d.f k = new b();

    /* JADX INFO: compiled from: RpcRequest.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Reply f2087a;

        public a(Reply reply) {
            this.f2087a = reply;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2087a.e()) {
                throw new AndroidRuntimeException(this.f2087a.a());
            }
            Log.e("RPC-ERROR", this.f2087a.a());
        }
    }

    /* JADX INFO: compiled from: RpcRequest.java */
    public class b implements d.f {
        public b() {
        }

        @Override // b.b.l.d.f
        public void connected(ComponentName componentName, IInterface iInterface) {
        }

        @Override // b.b.l.d.f
        public void disconnected(ComponentName componentName, IInterface iInterface) {
            int iG = j.this.j.G(iInterface);
            if (f.b()) {
                f.d(j.this.g + " disconnected with service: " + iG);
            }
            j.this.v(iG);
        }
    }

    public j() {
        h hVar = new h();
        this.j = hVar;
        hVar.W(this);
    }

    public Reply A(CallbackMsg callbackMsg) {
        ClassLoader classLoader;
        if (f.b()) {
            f.d(this.g + " callbackMethod 1: " + callbackMsg.toString());
        }
        ArrayList<Object> arrayListN = n(callbackMsg.a().a(), callbackMsg.d());
        Reply reply = new Reply();
        String strQ = callbackMsg.q();
        Object[] objArr = (Object[]) b.b.l.a.e(callbackMsg.h()).second;
        for (Object obj : arrayListN) {
            if (RefUtils.k(obj.getClass(), strQ, objArr) != null) {
                try {
                    if ((obj instanceof l) && (classLoader = (ClassLoader) RefUtils.a(obj, "getClassLoader", new Object[0])) != null && classLoader.getParent() != null && ComplexData.b() != classLoader) {
                        objArr = (Object[]) b.b.l.a.e(callbackMsg.i(classLoader)).second;
                    }
                    reply.i(RefUtils.a(obj, strQ, objArr));
                    if (f.b()) {
                        f.d(this.g + " callbackMethod 2: " + obj + ", " + strQ + ", " + c.d(objArr));
                    }
                } catch (Throwable th) {
                    reply.h(-1);
                    reply.g(Log.getStackTraceString(th));
                }
                return reply;
            }
        }
        if (f.b()) {
            f.a(g());
        }
        reply.h(-1);
        reply.g("Could not find method in register object to callback: " + strQ);
        return reply;
    }

    public void B(Reply reply) {
        if (reply == null || reply.b() == 0) {
            return;
        }
        this.f2052d.post(new a(reply));
    }

    public d C(String str, String str2) {
        d dVar = (d) this.j.z(this.f2051c, str, str2);
        dVar.d(this.k);
        dVar.o(this);
        return dVar;
    }

    public d D(String str, String str2) {
        d dVar = (d) this.j.A(this.f2051c, str, str2);
        dVar.d(this.k);
        dVar.o(this);
        return dVar;
    }

    public void E(String str) {
        this.j.B(this.f2051c, str);
    }

    public h F() {
        return this.j;
    }

    public boolean G(String str) {
        return this.j.L(str);
    }

    public j H(Context context) {
        return (j) super.w(context);
    }

    @Override // b.b.l.u.c
    public /* bridge */ /* synthetic */ String g() {
        return super.g();
    }

    @Override // b.b.l.u.c, android.os.Handler.Callback
    public /* bridge */ /* synthetic */ boolean handleMessage(Message message) {
        return super.handleMessage(message);
    }

    @Override // b.b.l.u.c
    public /* bridge */ /* synthetic */ String i() {
        return super.i();
    }

    @Override // b.b.l.u.c
    public /* bridge */ /* synthetic */ void s(GCMsg gCMsg) {
        super.s(gCMsg);
    }

    public j(Context context) {
        h hVar = new h();
        this.j = hVar;
        hVar.W(this);
        H(context);
    }
}
