package j7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.EventBusException;

/* JADX INFO: compiled from: SubscriberMethodFinder.java */
/* JADX INFO: loaded from: classes2.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<Class<?>, List<n>> f5672a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a[] f5673b = new a[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<l7.b> f5674c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f5675d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5676e;

    /* JADX INFO: compiled from: SubscriberMethodFinder.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<n> f5677a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<Class, Object> f5678b = new HashMap();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Map<String, Class> f5679c = new HashMap();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final StringBuilder f5680d = new StringBuilder(128);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Class<?> f5681e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Class<?> f5682f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f5683g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public l7.a f5684h;

        public boolean a(Method method, Class<?> cls) {
            Object objPut = this.f5678b.put(cls, method);
            if (objPut == null) {
                return true;
            }
            if (objPut instanceof Method) {
                if (!b((Method) objPut, cls)) {
                    throw new IllegalStateException();
                }
                this.f5678b.put(cls, this);
            }
            return b(method, cls);
        }

        public final boolean b(Method method, Class<?> cls) {
            this.f5680d.setLength(0);
            this.f5680d.append(method.getName());
            StringBuilder sb = this.f5680d;
            sb.append('>');
            sb.append(cls.getName());
            String string = this.f5680d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class clsPut = this.f5679c.put(string, declaringClass);
            if (clsPut == null || clsPut.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.f5679c.put(string, clsPut);
            return false;
        }

        public void c(Class<?> cls) {
            this.f5682f = cls;
            this.f5681e = cls;
            this.f5683g = false;
            this.f5684h = null;
        }

        public void d() {
            if (this.f5683g) {
                this.f5682f = null;
                return;
            }
            Class<? super Object> superclass = this.f5682f.getSuperclass();
            this.f5682f = superclass;
            String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                this.f5682f = null;
            }
        }

        public void e() {
            this.f5677a.clear();
            this.f5678b.clear();
            this.f5679c.clear();
            this.f5680d.setLength(0);
            this.f5681e = null;
            this.f5682f = null;
            this.f5683g = false;
            this.f5684h = null;
        }
    }

    public o(List<l7.b> list, boolean z7, boolean z8) {
        this.f5674c = list;
        this.f5675d = z7;
        this.f5676e = z8;
    }

    public List<n> a(Class<?> cls) {
        Map<Class<?>, List<n>> map = f5672a;
        List<n> list = map.get(cls);
        if (list != null) {
            return list;
        }
        List<n> listC = this.f5676e ? c(cls) : b(cls);
        if (!listC.isEmpty()) {
            map.put(cls, listC);
            return listC;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }

    public final List<n> b(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f5682f != null) {
            l7.a aVarF = f(aVarG);
            aVarG.f5684h = aVarF;
            if (aVarF != null) {
                for (n nVar : aVarF.a()) {
                    if (aVarG.a(nVar.f5666a, nVar.f5668c)) {
                        aVarG.f5677a.add(nVar);
                    }
                }
            } else {
                d(aVarG);
            }
            aVarG.d();
        }
        return e(aVarG);
    }

    public final List<n> c(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f5682f != null) {
            d(aVarG);
            aVarG.d();
        }
        return e(aVarG);
    }

    public final void d(a aVar) {
        Method[] methods;
        try {
            try {
                methods = aVar.f5682f.getDeclaredMethods();
            } catch (Throwable unused) {
                methods = aVar.f5682f.getMethods();
                aVar.f5683g = true;
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
                                aVar.f5677a.add(new n(method, cls, lVar.threadMode(), lVar.priority(), lVar.sticky()));
                            }
                        }
                    } else if (this.f5675d && method.isAnnotationPresent(l.class)) {
                        throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                    }
                } else if (this.f5675d && method.isAnnotationPresent(l.class)) {
                    throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
                }
            }
        } catch (LinkageError e8) {
            String str = "Could not inspect methods of " + aVar.f5682f.getName();
            throw new EventBusException(this.f5676e ? str + ". Please consider using EventBus annotation processor to avoid reflection." : str + ". Please make this class visible to EventBus annotation processor to avoid reflection.", e8);
        }
    }

    public final List<n> e(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.f5677a);
        aVar.e();
        synchronized (f5673b) {
            int i8 = 0;
            while (true) {
                if (i8 >= 4) {
                    break;
                }
                a[] aVarArr = f5673b;
                if (aVarArr[i8] == null) {
                    aVarArr[i8] = aVar;
                    break;
                }
                i8++;
            }
        }
        return arrayList;
    }

    public final l7.a f(a aVar) {
        l7.a aVar2 = aVar.f5684h;
        if (aVar2 != null && aVar2.c() != null) {
            l7.a aVarC = aVar.f5684h.c();
            if (aVar.f5682f == aVarC.b()) {
                return aVarC;
            }
        }
        List<l7.b> list = this.f5674c;
        if (list == null) {
            return null;
        }
        Iterator<l7.b> it = list.iterator();
        while (it.hasNext()) {
            l7.a aVarA = it.next().a(aVar.f5682f);
            if (aVarA != null) {
                return aVarA;
            }
        }
        return null;
    }

    public final a g() {
        synchronized (f5673b) {
            for (int i8 = 0; i8 < 4; i8++) {
                a[] aVarArr = f5673b;
                a aVar = aVarArr[i8];
                if (aVar != null) {
                    aVarArr[i8] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }
}
