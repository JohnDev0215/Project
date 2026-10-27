package b.b.l.u;

import android.util.AndroidRuntimeException;
import b.b.l.d;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: compiled from: RpcConnecter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends d.g {
    public j l;
    public String m = "";

    /* JADX INFO: compiled from: RpcConnecter.java */
    public class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Class<?> f2059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f2060b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b f2061c;

        public a(long j, b.b.l.u.a aVar) {
            this.f2060b = j;
            this.f2059a = aVar.c();
            this.f2061c = aVar.b();
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr == null) {
                objArr = c.f2050b;
            }
            if (c.p(method)) {
                a aVar = (a) Proxy.getInvocationHandler(objArr[0]);
                return Boolean.valueOf(this.f2059a == aVar.f2059a && this.f2060b == aVar.f2060b);
            }
            if (c.r(method)) {
                return toString() + ": " + this.f2059a + ", id: " + this.f2060b;
            }
            if (c.q(method)) {
                return Integer.valueOf(System.identityHashCode(obj));
            }
            if (f.b()) {
                f.d(d.this.l.g + " " + toString() + " invoke: " + method + ", " + c.d(objArr) + ", id: " + this.f2060b);
            }
            InvokeMsg invokeMsg = new InvokeMsg(d.this.l, d.this.c(), this.f2060b, this.f2059a.getName(), method, objArr);
            invokeMsg.m(d.this.b());
            invokeMsg.o(d.this.m);
            invokeMsg.v(method.getAnnotation(m.class) != null);
            if (this.f2061c != null) {
                Reply replyInvoke = this.f2061c.invoke(invokeMsg, new g(obj, method, objArr));
                if (replyInvoke != null) {
                    return Integer.valueOf(replyInvoke.b());
                }
            }
            Reply replyV = d.this.l.F().V(d.this.c(), invokeMsg);
            if (replyV == null || replyV.b() != 0) {
                throw new AndroidRuntimeException(replyV != null ? replyV.a() : "Could not find correct remote connection");
            }
            ClassLoader classLoader = this.f2059a.getClassLoader();
            return (classLoader.getParent() == null || ComplexData.b() == classLoader) ? replyV.c() : replyV.d(classLoader);
        }

        public String toString() {
            return "invokeProxy@" + Integer.toHexString(hashCode());
        }
    }

    public <T> T l(Class<? extends T> cls, String str, Object... objArr) {
        return new b.b.l.u.a(this).i(cls).j(objArr).k(str).a();
    }

    public final Object m(long j, b.b.l.u.a aVar) {
        Class<?> clsC = aVar.c();
        return Proxy.newProxyInstance(clsC.getClassLoader(), clsC.isInterface() ? new Class[]{clsC} : clsC.getInterfaces(), new a(j, aVar));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public <T> T n(b.b.l.u.a aVar) {
        Class<? extends T> clsC = aVar.c();
        Object[] objArrE = aVar.e();
        GetMsg getMsg = new GetMsg(this.l, c(), aVar.f(), aVar.g(), aVar.d(), objArrE);
        getMsg.m(b());
        getMsg.o(this.m);
        Reply replyT = this.l.F().T(c(), getMsg);
        if (replyT == null || replyT.b() != 0) {
            throw new AndroidRuntimeException(replyT != null ? replyT.a() : "Could not find correct remote connection");
        }
        int iC = c();
        long jD = getMsg.d();
        T t = (T) this.l.f2054f.a(iC, Long.valueOf(jD), clsC);
        if (t != null) {
            return t;
        }
        T t2 = (T) m(getMsg.d(), aVar);
        this.l.f2054f.b(t2, iC, Long.valueOf(jD), clsC);
        return t2;
    }

    public void o(j jVar) {
        this.l = jVar;
    }

    public d p(String str) {
        this.m = str;
        return this;
    }
}
