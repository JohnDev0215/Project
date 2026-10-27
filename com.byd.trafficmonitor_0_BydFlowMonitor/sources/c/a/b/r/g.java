package c.a.b.r;

import android.os.Message;
import android.os.Process;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.Pair;
import c.a.b.m;
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
/* JADX INFO: loaded from: classes.dex */
public class g extends b implements m.c {
    public static ThreadLocal<Caller> j = new ThreadLocal<>();
    public String k = "";
    public boolean l = true;
    public c.a.b.r.a m;

    /* JADX INFO: compiled from: RpcResponse.java */
    public class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Class<?> f3551a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3552b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3553c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3554d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3555e = 0;

        public a(Class<?> cls, int i2, long j, long j2) {
            this.f3551a = cls;
            this.f3552b = i2;
            this.f3553c = j;
            this.f3554d = j2;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr == null) {
                objArr = b.f3515c;
            }
            Object[] objArr2 = objArr;
            if (b.p(method)) {
                a aVar = (a) Proxy.getInvocationHandler(objArr2[0]);
                return Boolean.valueOf(this.f3551a == aVar.f3551a && this.f3552b == aVar.f3552b && this.f3554d == aVar.f3554d);
            }
            if (b.r(method)) {
                return toString() + ": " + this.f3551a + ", id: " + this.f3554d;
            }
            if (b.q(method)) {
                return Integer.valueOf(System.identityHashCode(obj));
            }
            if (d.a()) {
                d.c(g.this.f3519g + " " + toString() + " invoke: " + method + ", " + b.e(objArr2) + ", id: " + this.f3554d);
            }
            CallbackMsg callbackMsg = new CallbackMsg(g.this, this.f3552b, this.f3554d, method, objArr2);
            callbackMsg.t(this.f3553c);
            callbackMsg.y(method.getAnnotation(h.class) != null);
            Reply replyD = m.d(callbackMsg);
            if (replyD == null) {
                return null;
            }
            if (replyD.k() != 0) {
                throw new AndroidRuntimeException(replyD.j());
            }
            ClassLoader classLoader = this.f3551a.getClassLoader();
            return (classLoader.getParent() == null || ComplexData.k() == classLoader) ? replyD.l() : replyD.m(classLoader);
        }

        public String toString() {
            return "callbackProxy@" + Integer.toHexString(hashCode());
        }
    }

    public g() {
        m.p(this);
        m.c(this);
    }

    @Override // c.a.b.m.c
    public void a(int i2) {
        if (d.a()) {
            d.c(this.f3519g + " disconnected with client: " + i2);
        }
        for (Object obj : m(i2)) {
            if (obj instanceof m.c) {
                ((m.c) obj).a(i2);
            }
        }
        u(i2);
    }

    @Override // c.a.b.r.b, android.os.Handler.Callback
    public /* bridge */ /* synthetic */ boolean handleMessage(Message message) {
        return super.handleMessage(message);
    }

    @Override // c.a.b.r.b
    public /* bridge */ /* synthetic */ String i() {
        return super.i();
    }

    @Override // c.a.b.r.b
    public /* bridge */ /* synthetic */ void s(GCMsg gCMsg) {
        super.s(gCMsg);
    }

    public final Object v(int i2, long j2, long j3, Class<?> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.isInterface() ? new Class[]{cls} : cls.getInterfaces(), new a(cls, i2, j2, j3));
    }

    public Reply w(GetMsg getMsg) {
        if (d.a()) {
            d.c(this.f3519g + " getInstance: " + getMsg.toString());
        }
        Reply reply = new Reply();
        reply.r(this.l);
        String strV = getMsg.v();
        int iJ = getMsg.j().j();
        if (getMsg.w()) {
            if (d.a.a.a.a.c.c(strV)) {
                reply.p(-1);
                reply.o("Could not find registered object, class: " + strV);
            } else {
                Pair<Class<?>[], Object[]> pairE = c.a.b.a.e(getMsg.p());
                try {
                    c(iJ, getMsg.m(), RefUtils.r(RefUtils.b(strV, this.f3516d.getClassLoader()), (Class[]) pairE.first, z(pairE, iJ, getMsg.k())));
                } catch (Throwable th) {
                    reply.p(-1);
                    reply.o(Log.getStackTraceString(th));
                }
            }
        } else if (!d(iJ, getMsg.m(), strV)) {
            reply.p(-1);
            reply.o("[" + Process.myPid() + "]Could not find registered object, class: " + strV);
        }
        return reply;
    }

    public String x() {
        return this.k;
    }

    public Reply y(InvokeMsg invokeMsg) {
        if (d.a()) {
            d.c(this.f3519g + " invokeMethod 1: " + invokeMsg.toString());
        }
        String strV = invokeMsg.v();
        String strW = invokeMsg.w();
        String strX = invokeMsg.x();
        Pair<Class<?>[], Object[]> pairE = c.a.b.a.e(invokeMsg.p());
        int iJ = invokeMsg.j().j();
        Object[] objArrZ = z(pairE, iJ, invokeMsg.k());
        invokeMsg.l();
        if (this.m != null) {
            Reply replyA = this.m.a(invokeMsg, new e(null, strV, strW, strX, objArrZ));
            if (replyA != null) {
                return replyA;
            }
        }
        Reply reply = new Reply();
        reply.r(this.l);
        ArrayList<Object> arrayListN = n(iJ, invokeMsg.m());
        j.set(invokeMsg.j());
        try {
            for (Object obj : arrayListN) {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                if (ComplexData.k() != classLoader) {
                    objArrZ = ComplexData.j(objArrZ, classLoader);
                }
                if (RefUtils.j(obj.getClass(), strW, objArrZ) != null) {
                    try {
                        reply.q(RefUtils.a(obj, strW, objArrZ));
                        if (d.a()) {
                            d.c(this.f3519g + " invokeMethod 2: " + obj + ", " + strW + ", " + b.e(objArrZ));
                        }
                    } catch (Throwable th) {
                        reply.p(-1);
                        reply.o(Log.getStackTraceString(th));
                    }
                    return reply;
                }
            }
            j.remove();
            reply.p(-1);
            reply.o("[" + Process.myPid() + "]Could not find method in register object to invoke: " + strW + b.f((Class[]) pairE.first));
            return reply;
        } finally {
            j.remove();
        }
    }

    public final Object[] z(Pair<Class<?>[], Object[]> pair, int i2, long j2) {
        Class<?>[] clsArr = (Class[]) pair.first;
        Object[] objArr = (Object[]) pair.second;
        for (int i3 = 0; i3 < clsArr.length; i3++) {
            Class<?> cls = clsArr[i3];
            if (cls != null && cls.isInterface()) {
                long jLongValue = ((Long) objArr[i3]).longValue();
                if (jLongValue > 0) {
                    Object objA = this.f3518f.a(i2, Long.valueOf(jLongValue), cls);
                    if (objA == null) {
                        objA = v(i2, j2, jLongValue, cls);
                        this.f3518f.b(objA, i2, Long.valueOf(jLongValue), cls);
                    }
                    objArr[i3] = objA;
                } else {
                    objArr[i3] = null;
                }
            }
        }
        return objArr;
    }
}
