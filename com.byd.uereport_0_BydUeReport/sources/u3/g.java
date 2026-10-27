package u3;

import android.os.Message;
import android.os.Process;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.Pair;
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
import t3.m;

/* JADX INFO: compiled from: RpcResponse.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends b implements m.c {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static ThreadLocal<Caller> f8189l = new ThreadLocal<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f8190m = "";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f8191n = true;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public u3.a f8192o;

    /* JADX INFO: compiled from: RpcResponse.java */
    public class a implements InvocationHandler {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Class<?> f8193d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8194e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f8195f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public long f8196g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f8197h = 0;

        public a(Class<?> cls, int i8, long j8, long j9) {
            this.f8193d = cls;
            this.f8194e = i8;
            this.f8195f = j8;
            this.f8196g = j9;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr == null) {
                objArr = b.f8150e;
            }
            Object[] objArr2 = objArr;
            if (b.p(method)) {
                a aVar = (a) Proxy.getInvocationHandler(objArr2[0]);
                return Boolean.valueOf(this.f8193d == aVar.f8193d && this.f8194e == aVar.f8194e && this.f8196g == aVar.f8196g);
            }
            if (b.r(method)) {
                return toString() + ": " + this.f8193d + ", id: " + this.f8196g;
            }
            if (b.q(method)) {
                return Integer.valueOf(System.identityHashCode(obj));
            }
            if (d.a()) {
                d.c(g.this.f8154i + " " + toString() + " invoke: " + method + ", " + b.e(objArr2) + ", id: " + this.f8196g);
            }
            CallbackMsg callbackMsg = new CallbackMsg(g.this, this.f8194e, this.f8196g, method, objArr2);
            callbackMsg.p(this.f8195f);
            callbackMsg.u(method.getAnnotation(h.class) != null);
            Reply replyD = m.d(callbackMsg);
            if (replyD == null) {
                return null;
            }
            if (replyD.g() != 0) {
                throw new AndroidRuntimeException(replyD.f());
            }
            ClassLoader classLoader = this.f8193d.getClassLoader();
            return (classLoader.getParent() == null || ComplexData.g() == classLoader) ? replyD.h() : replyD.i(classLoader);
        }

        public String toString() {
            return "callbackProxy@" + Integer.toHexString(hashCode());
        }
    }

    public g() {
        m.p(this);
        m.c(this);
    }

    @Override // t3.m.c
    public void a(int i8) {
        if (d.a()) {
            d.c(this.f8154i + " disconnected with client: " + i8);
        }
        for (Object obj : m(i8)) {
            if (obj instanceof m.c) {
                ((m.c) obj).a(i8);
            }
        }
        u(i8);
    }

    @Override // u3.b, android.os.Handler.Callback
    public /* bridge */ /* synthetic */ boolean handleMessage(Message message) {
        return super.handleMessage(message);
    }

    @Override // u3.b
    public /* bridge */ /* synthetic */ String i() {
        return super.i();
    }

    @Override // u3.b
    public /* bridge */ /* synthetic */ void s(GCMsg gCMsg) {
        super.s(gCMsg);
    }

    public final Object v(int i8, long j8, long j9, Class<?> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.isInterface() ? new Class[]{cls} : cls.getInterfaces(), new a(cls, i8, j8, j9));
    }

    public Reply w(GetMsg getMsg) {
        if (d.a()) {
            d.c(this.f8154i + " getInstance: " + getMsg.toString());
        }
        Reply reply = new Reply();
        reply.n(this.f8191n);
        String strR = getMsg.r();
        int iF = getMsg.f().f();
        if (getMsg.s()) {
            if (c7.c.c(strR)) {
                reply.l(-1);
                reply.k("Could not find registered object, class: " + strR);
            } else {
                Pair<Class<?>[], Object[]> pairE = t3.a.e(getMsg.l());
                try {
                    c(iF, getMsg.i(), RefUtils.r(RefUtils.b(strR, this.f8151f.getClassLoader()), (Class[]) pairE.first, z(pairE, iF, getMsg.g())));
                } catch (Throwable th) {
                    reply.l(-1);
                    reply.k(Log.getStackTraceString(th));
                }
            }
        } else if (!d(iF, getMsg.i(), strR)) {
            reply.l(-1);
            reply.k("[" + Process.myPid() + "]Could not find registered object, class: " + strR);
        }
        return reply;
    }

    public String x() {
        return this.f8190m;
    }

    public Reply y(InvokeMsg invokeMsg) {
        if (d.a()) {
            d.c(this.f8154i + " invokeMethod 1: " + invokeMsg.toString());
        }
        String strR = invokeMsg.r();
        String strS = invokeMsg.s();
        String strT = invokeMsg.t();
        Pair<Class<?>[], Object[]> pairE = t3.a.e(invokeMsg.l());
        int iF = invokeMsg.f().f();
        Object[] objArrZ = z(pairE, iF, invokeMsg.g());
        invokeMsg.h();
        if (this.f8192o != null) {
            Reply replyA = this.f8192o.a(invokeMsg, new e(null, strR, strS, strT, objArrZ));
            if (replyA != null) {
                return replyA;
            }
        }
        Reply reply = new Reply();
        reply.n(this.f8191n);
        ArrayList<Object> arrayListN = n(iF, invokeMsg.i());
        f8189l.set(invokeMsg.f());
        try {
            for (Object obj : arrayListN) {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                if (ComplexData.g() != classLoader) {
                    objArrZ = ComplexData.f(objArrZ, classLoader);
                }
                if (RefUtils.j(obj.getClass(), strS, objArrZ) != null) {
                    try {
                        reply.m(RefUtils.a(obj, strS, objArrZ));
                        if (d.a()) {
                            d.c(this.f8154i + " invokeMethod 2: " + obj + ", " + strS + ", " + b.e(objArrZ));
                        }
                    } catch (Throwable th) {
                        reply.l(-1);
                        reply.k(Log.getStackTraceString(th));
                    }
                    return reply;
                }
            }
            f8189l.remove();
            reply.l(-1);
            reply.k("[" + Process.myPid() + "]Could not find method in register object to invoke: " + strS + b.f((Class[]) pairE.first));
            return reply;
        } finally {
            f8189l.remove();
        }
    }

    public final Object[] z(Pair<Class<?>[], Object[]> pair, int i8, long j8) {
        Class<?>[] clsArr = (Class[]) pair.first;
        Object[] objArr = (Object[]) pair.second;
        for (int i9 = 0; i9 < clsArr.length; i9++) {
            Class<?> cls = clsArr[i9];
            if (cls != null && cls.isInterface()) {
                long jLongValue = ((Long) objArr[i9]).longValue();
                if (jLongValue > 0) {
                    Object objA = this.f8153h.a(i8, Long.valueOf(jLongValue), cls);
                    if (objA == null) {
                        objA = v(i8, j8, jLongValue, cls);
                        this.f8153h.b(objA, i8, Long.valueOf(jLongValue), cls);
                    }
                    objArr[i9] = objA;
                } else {
                    objArr[i9] = null;
                }
            }
        }
        return objArr;
    }
}
