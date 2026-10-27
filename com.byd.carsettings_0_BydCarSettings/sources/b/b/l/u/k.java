package b.b.l.u;

import android.content.Context;
import android.os.Message;
import android.os.Process;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.Pair;
import b.b.l.p;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.refect.RefUtils;
import com.byd.mvcadapter.rpc.CallbackMsg;
import com.byd.mvcadapter.rpc.Caller;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/* JADX INFO: compiled from: RpcResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public class k extends c implements p.c {
    public static k j;
    public static ThreadLocal<Caller> k = new ThreadLocal<>();
    public String l;
    public boolean m;
    public b n;

    /* JADX INFO: compiled from: RpcResponse.java */
    public class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Class<?> f2090a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2091b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f2092c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f2093d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2094e = 0;

        public a(Class<?> cls, int i, long j, long j2) {
            this.f2090a = cls;
            this.f2091b = i;
            this.f2092c = j;
            this.f2093d = j2;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr == null) {
                objArr = c.f2050b;
            }
            Object[] objArr2 = objArr;
            if (c.p(method)) {
                a aVar = (a) Proxy.getInvocationHandler(objArr2[0]);
                return Boolean.valueOf(this.f2090a == aVar.f2090a && this.f2091b == aVar.f2091b && this.f2093d == aVar.f2093d);
            }
            if (c.r(method)) {
                return toString() + ": " + this.f2090a + ", id: " + this.f2093d;
            }
            if (c.q(method)) {
                return Integer.valueOf(System.identityHashCode(obj));
            }
            if (f.b()) {
                f.d(k.this.g + " " + toString() + " invoke: " + method + ", " + c.d(objArr2) + ", id: " + this.f2093d);
            }
            CallbackMsg callbackMsg = new CallbackMsg(k.this, this.f2091b, this.f2093d, method, objArr2);
            callbackMsg.m(this.f2092c);
            callbackMsg.u(method.getAnnotation(m.class) != null);
            Reply replyD = p.d(callbackMsg);
            if (replyD == null) {
                return null;
            }
            if (replyD.b() != 0) {
                throw new AndroidRuntimeException(replyD.a());
            }
            ClassLoader classLoader = this.f2090a.getClassLoader();
            return (classLoader.getParent() == null || ComplexData.b() == classLoader) ? replyD.c() : replyD.d(classLoader);
        }

        public String toString() {
            return "callbackProxy@" + Integer.toHexString(hashCode());
        }
    }

    public k() {
        this.l = "";
        this.m = true;
        p.p(this);
        p.c(this);
    }

    public Reply A(GetMsg getMsg) {
        if (f.b()) {
            f.d(this.g + " getInstance: " + getMsg.toString());
        }
        Reply reply = new Reply();
        reply.j(this.m);
        String strQ = getMsg.q();
        int iA = getMsg.a().a();
        if (getMsg.r()) {
            if (c.a.a.a.a.c.c(strQ)) {
                reply.h(-1);
                reply.g("Could not find registered object, class: " + strQ);
            } else {
                Pair<Class<?>[], Object[]> pairE = b.b.l.a.e(getMsg.h());
                try {
                    b(iA, getMsg.d(), RefUtils.t(RefUtils.c(strQ, this.f2051c.getClassLoader()), (Class[]) pairE.first, D(pairE, iA, getMsg.b())));
                } catch (Throwable th) {
                    reply.h(-1);
                    reply.g(Log.getStackTraceString(th));
                }
            }
        } else if (!c(iA, getMsg.d(), strQ)) {
            reply.h(-1);
            reply.g("[" + Process.myPid() + "]Could not find registered object, class: " + strQ);
        }
        return reply;
    }

    public String B() {
        return this.l;
    }

    public Reply C(InvokeMsg invokeMsg) {
        if (f.b()) {
            f.d(this.g + " invokeMethod 1: " + invokeMsg.toString());
        }
        String strQ = invokeMsg.q();
        String strR = invokeMsg.r();
        String strS = invokeMsg.s();
        Pair<Class<?>[], Object[]> pairE = b.b.l.a.e(invokeMsg.h());
        int iA = invokeMsg.a().a();
        Object[] objArrD = D(pairE, iA, invokeMsg.b());
        invokeMsg.c();
        if (this.n != null) {
            Reply replyInvoke = this.n.invoke(invokeMsg, new g(null, strQ, strR, strS, objArrD));
            if (replyInvoke != null) {
                return replyInvoke;
            }
        }
        Reply reply = new Reply();
        reply.j(this.m);
        ArrayList<Object> arrayListN = n(iA, invokeMsg.d());
        k.set(invokeMsg.a());
        try {
            for (Object obj : arrayListN) {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                if (ComplexData.b() != classLoader) {
                    objArrD = ComplexData.a(objArrD, classLoader);
                }
                if (RefUtils.k(obj.getClass(), strR, objArrD) != null) {
                    try {
                        reply.i(RefUtils.a(obj, strR, objArrD));
                        if (f.b()) {
                            f.d(this.g + " invokeMethod 2: " + obj + ", " + strR + ", " + c.d(objArrD));
                        }
                    } catch (Throwable th) {
                        reply.h(-1);
                        reply.g(Log.getStackTraceString(th));
                    }
                    return reply;
                }
            }
            k.remove();
            reply.h(-1);
            reply.g("[" + Process.myPid() + "]Could not find method in register object to invoke: " + strR + c.e((Class[]) pairE.first));
            return reply;
        } finally {
            k.remove();
        }
    }

    public final Object[] D(Pair<Class<?>[], Object[]> pair, int i, long j2) {
        Class<?>[] clsArr = (Class[]) pair.first;
        Object[] objArr = (Object[]) pair.second;
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            Class<?> cls = clsArr[i2];
            if (cls != null && cls.isInterface()) {
                long jLongValue = ((Long) objArr[i2]).longValue();
                if (jLongValue > 0) {
                    Object objA = this.f2054f.a(i, Long.valueOf(jLongValue), cls);
                    if (objA == null) {
                        objA = z(i, j2, jLongValue, cls);
                        this.f2054f.b(objA, i, Long.valueOf(jLongValue), cls);
                    }
                    objArr[i2] = objA;
                } else {
                    objArr[i2] = null;
                }
            }
        }
        return objArr;
    }

    public void E() {
        y();
        p.u(this);
        p.q(this);
        if (j == this) {
            j = null;
        }
    }

    public k F(Context context) {
        return (k) super.w(context);
    }

    public void G(b bVar) {
        this.n = bVar;
    }

    @Override // b.b.l.p.c
    public void clientDisconnect(int i) {
        if (f.b()) {
            f.d(this.g + " disconnected with client: " + i);
        }
        for (Object obj : m(i)) {
            if (obj instanceof p.c) {
                ((p.c) obj).clientDisconnect(i);
            }
        }
        v(i);
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

    @Override // b.b.l.u.c
    public void t(Object obj) {
        super.t(obj);
    }

    @Override // b.b.l.u.c
    public void x(Object obj) {
        super.x(obj);
    }

    @Override // b.b.l.u.c
    public void y() {
        super.y();
    }

    public final Object z(int i, long j2, long j3, Class<?> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.isInterface() ? new Class[]{cls} : cls.getInterfaces(), new a(cls, i, j2, j3));
    }

    public k(Context context, String str) {
        this.l = "";
        this.m = true;
        if (str != null) {
            this.l = str;
        }
        p.p(this);
        p.c(this);
        F(context);
    }
}
