package spi;

import com.alibaba.android.arouter.utils.Consts;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: SubscriberMethodFinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oa {
    public static final Map<Class<?>, List<na>> d = new ConcurrentHashMap();
    public static final a[] e = new a[4];
    public List<ma> a;
    public final boolean b;
    public final boolean c;

    /* JADX INFO: compiled from: SubscriberMethodFinder.java */
    public static class a {
        public final List<na> a = new ArrayList();
        public final Map<Class, Object> b = new HashMap();
        public final Map<String, Class> c = new HashMap();
        public final StringBuilder d = new StringBuilder(128);
        public Class<?> e;
        public boolean f;
        public la g;

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to spi.oa$a for r2v3 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        public final boolean a(java.lang.reflect.Method r3, java.lang.Class<?> r4) {
            /*
                r2 = this;
                java.util.Map<java.lang.Class, java.lang.Object> r0 = r2.b
                java.lang.Object r0 = r0.put(r4, r3)
                if (r0 != 0) goto La
                r2 = 1
                return r2
            La:
                boolean r1 = r0 instanceof java.lang.reflect.Method
                if (r1 == 0) goto L22
                java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
                boolean r0 = r2.b(r0, r4)
                if (r0 == 0) goto L1c
                java.util.Map<java.lang.Class, java.lang.Object> r0 = r2.b
                r0.put(r4, r2)
                goto L22
            L1c:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                r2.<init>()
                throw r2
            L22:
                boolean r2 = r2.b(r3, r4)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.oa.a.a(java.lang.reflect.Method, java.lang.Class):boolean");
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final boolean b(Method method, Class<?> cls) {
            this.d.setLength(0);
            this.d.append(method.getName());
            StringBuilder sb = this.d;
            sb.append('>');
            sb.append(cls.getName());
            String string = this.d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class clsPut = this.c.put(string, declaringClass);
            if (clsPut == null || clsPut.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.c.put(string, clsPut);
            return false;
        }

        public final void c() {
            if (this.f) {
                this.e = null;
                return;
            }
            Class<? super Object> superclass = this.e.getSuperclass();
            this.e = superclass;
            String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                this.e = null;
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.na>] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashMap, java.util.Map<java.lang.Class, java.lang.Object>] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class>] */
        public final void d() {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.setLength(0);
            this.e = null;
            this.f = false;
            this.g = null;
        }
    }

    public oa(List<ma> list, boolean z, boolean z2) {
        this.a = list;
        this.b = z;
        this.c = z2;
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [java.util.ArrayList, java.util.List<spi.na>] */
    public final void a(a aVar) {
        Method[] methods;
        try {
            try {
                methods = aVar.e.getDeclaredMethods();
            } catch (LinkageError e2) {
                StringBuilder sbK = c.k("Could not inspect methods of ");
                sbK.append(aVar.e.getName());
                String string = sbK.toString();
                throw new q3(this.c ? c.f(string, ". Please consider using EventBus annotation processor to avoid reflection.") : c.f(string, ". Please make this class visible to EventBus annotation processor to avoid reflection."), e2);
            }
        } catch (Throwable unused) {
            methods = aVar.e.getMethods();
            aVar.f = true;
        }
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    ja jaVar = (ja) method.getAnnotation(ja.class);
                    if (jaVar != null) {
                        Class<?> cls = parameterTypes[0];
                        if (aVar.a(method, cls)) {
                            aVar.a.add(new na(method, cls, jaVar.threadMode(), jaVar.priority(), jaVar.sticky()));
                        }
                    }
                } else if (this.b && method.isAnnotationPresent(ja.class)) {
                    StringBuilder sbO = c.o("@Subscribe method ", method.getDeclaringClass().getName() + Consts.DOT + method.getName(), "must have exactly 1 parameter but has ");
                    sbO.append(parameterTypes.length);
                    throw new q3(sbO.toString());
                }
            } else if (this.b && method.isAnnotationPresent(ja.class)) {
                throw new q3(c.f(method.getDeclaringClass().getName() + Consts.DOT + method.getName(), " is a illegal @Subscribe method: must be public, non-static, and non-abstract"));
            }
        }
    }

    public final List<na> b(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.a);
        aVar.d();
        synchronized (e) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                a[] aVarArr = e;
                if (aVarArr[i] == null) {
                    aVarArr[i] = aVar;
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    public final a c() {
        synchronized (e) {
            for (int i = 0; i < 4; i++) {
                a[] aVarArr = e;
                a aVar = aVarArr[i];
                if (aVar != null) {
                    aVarArr[i] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }
}
