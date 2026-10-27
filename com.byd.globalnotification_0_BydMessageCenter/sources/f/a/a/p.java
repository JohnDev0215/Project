package f.a.a;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<Class<?>, List<o>> f2577d = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a[] f2578e = new a[4];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<f.a.a.t.b> f2579a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2580b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2581c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<o> f2582a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<Class, Object> f2583b = new HashMap();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Map<String, Class> f2584c = new HashMap();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final StringBuilder f2585d = new StringBuilder(128);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Class<?> f2586e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2587f;
        public f.a.a.t.a g;

        public boolean a(Method method, Class<?> cls) {
            Object objPut = this.f2583b.put(cls, method);
            if (objPut == null) {
                return true;
            }
            if (objPut instanceof Method) {
                if (!b((Method) objPut, cls)) {
                    throw new IllegalStateException();
                }
                this.f2583b.put(cls, this);
            }
            return b(method, cls);
        }

        public final boolean b(Method method, Class<?> cls) {
            this.f2585d.setLength(0);
            this.f2585d.append(method.getName());
            StringBuilder sb = this.f2585d;
            sb.append('>');
            sb.append(cls.getName());
            String string = this.f2585d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class clsPut = this.f2584c.put(string, declaringClass);
            if (clsPut == null || clsPut.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.f2584c.put(string, clsPut);
            return false;
        }

        public void c() {
            if (!this.f2587f) {
                Class<? super Object> superclass = this.f2586e.getSuperclass();
                this.f2586e = superclass;
                String name = superclass.getName();
                if (!name.startsWith("java.") && !name.startsWith("javax.") && !name.startsWith("android.") && !name.startsWith("androidx.")) {
                    return;
                }
            }
            this.f2586e = null;
        }
    }

    public p(List<f.a.a.t.b> list, boolean z, boolean z2) {
        this.f2579a = list;
        this.f2580b = z;
        this.f2581c = z2;
    }

    public final void a(a aVar) {
        StringBuilder sbD;
        String str;
        Method[] methods;
        try {
            try {
                methods = aVar.f2586e.getDeclaredMethods();
            } catch (Throwable unused) {
                methods = aVar.f2586e.getMethods();
                aVar.f2587f = true;
            }
            for (Method method : methods) {
                int modifiers = method.getModifiers();
                if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        m mVar = (m) method.getAnnotation(m.class);
                        if (mVar != null) {
                            Class<?> cls = parameterTypes[0];
                            if (aVar.a(method, cls)) {
                                aVar.f2582a.add(new o(method, cls, mVar.threadMode(), mVar.priority(), mVar.sticky()));
                            }
                        }
                    } else if (this.f2580b && method.isAnnotationPresent(m.class)) {
                        throw new e("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                    }
                } else if (this.f2580b && method.isAnnotationPresent(m.class)) {
                    throw new e(b.a.a.a.a.c(method.getDeclaringClass().getName() + "." + method.getName(), " is a illegal @Subscribe method: must be public, non-static, and non-abstract"));
                }
            }
        } catch (LinkageError e2) {
            StringBuilder sbD2 = b.a.a.a.a.d("Could not inspect methods of ");
            sbD2.append(aVar.f2586e.getName());
            String string = sbD2.toString();
            if (this.f2581c) {
                sbD = b.a.a.a.a.d(string);
                str = ". Please consider using EventBus annotation processor to avoid reflection.";
            } else {
                sbD = b.a.a.a.a.d(string);
                str = ". Please make this class visible to EventBus annotation processor to avoid reflection.";
            }
            sbD.append(str);
            throw new e(sbD.toString(), e2);
        }
    }

    public final List<o> b(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.f2582a);
        aVar.f2582a.clear();
        aVar.f2583b.clear();
        aVar.f2584c.clear();
        int i = 0;
        aVar.f2585d.setLength(0);
        aVar.f2586e = null;
        aVar.f2587f = false;
        aVar.g = null;
        synchronized (f2578e) {
            while (true) {
                if (i >= 4) {
                    break;
                }
                if (f2578e[i] == null) {
                    f2578e[i] = aVar;
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    public final a c() {
        synchronized (f2578e) {
            for (int i = 0; i < 4; i++) {
                a aVar = f2578e[i];
                if (aVar != null) {
                    f2578e[i] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }
}
