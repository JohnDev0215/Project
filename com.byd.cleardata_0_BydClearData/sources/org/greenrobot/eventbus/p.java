package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<Class<?>, List<o>> f3324a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a[] f3325b = new a[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<org.greenrobot.eventbus.s.b> f3326c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f3327d;
    private final boolean e;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<o> f3328a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<Class, Object> f3329b = new HashMap();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Map<String, Class> f3330c = new HashMap();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final StringBuilder f3331d = new StringBuilder(128);
        Class<?> e;
        Class<?> f;
        boolean g;
        org.greenrobot.eventbus.s.a h;

        a() {
        }

        private boolean b(Method method, Class<?> cls) {
            this.f3331d.setLength(0);
            this.f3331d.append(method.getName());
            StringBuilder sb = this.f3331d;
            sb.append('>');
            sb.append(cls.getName());
            String string = this.f3331d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class clsPut = this.f3330c.put(string, declaringClass);
            if (clsPut == null || clsPut.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.f3330c.put(string, clsPut);
            return false;
        }

        boolean a(Method method, Class<?> cls) {
            Object objPut = this.f3329b.put(cls, method);
            if (objPut == null) {
                return true;
            }
            if (objPut instanceof Method) {
                if (!b((Method) objPut, cls)) {
                    throw new IllegalStateException();
                }
                this.f3329b.put(cls, this);
            }
            return b(method, cls);
        }

        void c(Class<?> cls) {
            this.f = cls;
            this.e = cls;
            this.g = false;
            this.h = null;
        }

        void d() {
            if (!this.g) {
                Class<? super Object> superclass = this.f.getSuperclass();
                this.f = superclass;
                String name = superclass.getName();
                if (!name.startsWith("java.") && !name.startsWith("javax.") && !name.startsWith("android.") && !name.startsWith("androidx.")) {
                    return;
                }
            }
            this.f = null;
        }

        void e() {
            this.f3328a.clear();
            this.f3329b.clear();
            this.f3330c.clear();
            this.f3331d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }
    }

    p(List<org.greenrobot.eventbus.s.b> list, boolean z, boolean z2) {
        this.f3326c = list;
        this.f3327d = z;
        this.e = z2;
    }

    private List<o> b(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f != null) {
            org.greenrobot.eventbus.s.a aVarF = f(aVarG);
            aVarG.h = aVarF;
            if (aVarF != null) {
                for (o oVar : aVarF.a()) {
                    if (aVarG.a(oVar.f3320a, oVar.f3322c)) {
                        aVarG.f3328a.add(oVar);
                    }
                }
            } else {
                d(aVarG);
            }
            aVarG.d();
        }
        return e(aVarG);
    }

    private List<o> c(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f != null) {
            d(aVarG);
            aVarG.d();
        }
        return e(aVarG);
    }

    private void d(a aVar) {
        StringBuilder sb;
        String str;
        Method[] methods;
        try {
            try {
                methods = aVar.f.getDeclaredMethods();
            } catch (LinkageError e) {
                String str2 = "Could not inspect methods of " + aVar.f.getName();
                if (this.e) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str = ". Please consider using EventBus annotation processor to avoid reflection.";
                } else {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str = ". Please make this class visible to EventBus annotation processor to avoid reflection.";
                }
                sb.append(str);
                throw new e(sb.toString(), e);
            }
        } catch (Throwable unused) {
            methods = aVar.f.getMethods();
            aVar.g = true;
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
                            aVar.f3328a.add(new o(method, cls, mVar.threadMode(), mVar.priority(), mVar.sticky()));
                        }
                    }
                } else if (this.f3327d && method.isAnnotationPresent(m.class)) {
                    throw new e("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.f3327d && method.isAnnotationPresent(m.class)) {
                throw new e((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }

    private List<o> e(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.f3328a);
        aVar.e();
        synchronized (f3325b) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                a[] aVarArr = f3325b;
                if (aVarArr[i] == null) {
                    aVarArr[i] = aVar;
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    private org.greenrobot.eventbus.s.a f(a aVar) {
        org.greenrobot.eventbus.s.a aVar2 = aVar.h;
        if (aVar2 != null && aVar2.c() != null) {
            org.greenrobot.eventbus.s.a aVarC = aVar.h.c();
            if (aVar.f == aVarC.b()) {
                return aVarC;
            }
        }
        List<org.greenrobot.eventbus.s.b> list = this.f3326c;
        if (list == null) {
            return null;
        }
        Iterator<org.greenrobot.eventbus.s.b> it = list.iterator();
        while (it.hasNext()) {
            org.greenrobot.eventbus.s.a aVarA = it.next().a(aVar.f);
            if (aVarA != null) {
                return aVarA;
            }
        }
        return null;
    }

    private a g() {
        synchronized (f3325b) {
            for (int i = 0; i < 4; i++) {
                a[] aVarArr = f3325b;
                a aVar = aVarArr[i];
                if (aVar != null) {
                    aVarArr[i] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }

    List<o> a(Class<?> cls) {
        Map<Class<?>, List<o>> map = f3324a;
        List<o> list = map.get(cls);
        if (list != null) {
            return list;
        }
        List<o> listC = this.e ? c(cls) : b(cls);
        if (!listC.isEmpty()) {
            map.put(cls, listC);
            return listC;
        }
        throw new e("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }
}
