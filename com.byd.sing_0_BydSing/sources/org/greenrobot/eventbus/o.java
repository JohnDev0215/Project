package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: SubscriberMethodFinder.java */
/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<Class<?>, List<n>> f4161a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a[] f4162b = new a[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<org.greenrobot.eventbus.r.b> f4163c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f4164d;
    private final boolean e;

    /* JADX INFO: compiled from: SubscriberMethodFinder.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<n> f4165a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<Class, Object> f4166b = new HashMap();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Map<String, Class> f4167c = new HashMap();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final StringBuilder f4168d = new StringBuilder(128);
        Class<?> e;
        Class<?> f;
        boolean g;
        org.greenrobot.eventbus.r.a h;

        a() {
        }

        private boolean b(Method method, Class<?> cls) {
            this.f4168d.setLength(0);
            this.f4168d.append(method.getName());
            StringBuilder sb = this.f4168d;
            sb.append('>');
            sb.append(cls.getName());
            String string = this.f4168d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class clsPut = this.f4167c.put(string, declaringClass);
            if (clsPut == null || clsPut.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.f4167c.put(string, clsPut);
            return false;
        }

        boolean a(Method method, Class<?> cls) {
            Object objPut = this.f4166b.put(cls, method);
            if (objPut == null) {
                return true;
            }
            if (objPut instanceof Method) {
                if (!b((Method) objPut, cls)) {
                    throw new IllegalStateException();
                }
                this.f4166b.put(cls, this);
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
            if (this.g) {
                this.f = null;
                return;
            }
            Class<? super Object> superclass = this.f.getSuperclass();
            this.f = superclass;
            String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                this.f = null;
            }
        }

        void e() {
            this.f4165a.clear();
            this.f4166b.clear();
            this.f4167c.clear();
            this.f4168d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }
    }

    o(List<org.greenrobot.eventbus.r.b> list, boolean z, boolean z2) {
        this.f4163c = list;
        this.f4164d = z;
        this.e = z2;
    }

    private List<n> b(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f != null) {
            org.greenrobot.eventbus.r.a aVarF = f(aVarG);
            aVarG.h = aVarF;
            if (aVarF != null) {
                for (n nVar : aVarF.a()) {
                    if (aVarG.a(nVar.f4157a, nVar.f4159c)) {
                        aVarG.f4165a.add(nVar);
                    }
                }
            } else {
                d(aVarG);
            }
            aVarG.d();
        }
        return e(aVarG);
    }

    private List<n> c(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f != null) {
            d(aVarG);
            aVarG.d();
        }
        return e(aVarG);
    }

    private void d(a aVar) {
        Method[] methods;
        try {
            try {
                methods = aVar.f.getDeclaredMethods();
            } catch (Throwable unused) {
                methods = aVar.f.getMethods();
                aVar.g = true;
            }
            for (Method method : methods) {
                int modifiers = method.getModifiers();
                if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        l lVar = (l) method.getAnnotation(l.class);
                        if (lVar != null) {
                            Class<?> cls = parameterTypes[0];
                            if (aVar.a(method, cls)) {
                                aVar.f4165a.add(new n(method, cls, lVar.threadMode(), lVar.priority(), lVar.sticky()));
                            }
                        }
                    } else if (this.f4164d && method.isAnnotationPresent(l.class)) {
                        throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                    }
                } else if (this.f4164d && method.isAnnotationPresent(l.class)) {
                    throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
                }
            }
        } catch (LinkageError e) {
            String str = "Could not inspect methods of " + aVar.f.getName();
            throw new EventBusException(this.e ? str + ". Please consider using EventBus annotation processor to avoid reflection." : str + ". Please make this class visible to EventBus annotation processor to avoid reflection.", e);
        }
    }

    private List<n> e(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.f4165a);
        aVar.e();
        synchronized (f4162b) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                a[] aVarArr = f4162b;
                if (aVarArr[i] == null) {
                    aVarArr[i] = aVar;
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    private org.greenrobot.eventbus.r.a f(a aVar) {
        org.greenrobot.eventbus.r.a aVar2 = aVar.h;
        if (aVar2 != null && aVar2.c() != null) {
            org.greenrobot.eventbus.r.a aVarC = aVar.h.c();
            if (aVar.f == aVarC.b()) {
                return aVarC;
            }
        }
        List<org.greenrobot.eventbus.r.b> list = this.f4163c;
        if (list == null) {
            return null;
        }
        Iterator<org.greenrobot.eventbus.r.b> it = list.iterator();
        while (it.hasNext()) {
            org.greenrobot.eventbus.r.a aVarA = it.next().a(aVar.f);
            if (aVarA != null) {
                return aVarA;
            }
        }
        return null;
    }

    private a g() {
        synchronized (f4162b) {
            for (int i = 0; i < 4; i++) {
                a[] aVarArr = f4162b;
                a aVar = aVarArr[i];
                if (aVar != null) {
                    aVarArr[i] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }

    List<n> a(Class<?> cls) {
        Map<Class<?>, List<n>> map = f4161a;
        List<n> list = map.get(cls);
        if (list != null) {
            return list;
        }
        List<n> listC = this.e ? c(cls) : b(cls);
        if (!listC.isEmpty()) {
            map.put(cls, listC);
            return listC;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }
}
