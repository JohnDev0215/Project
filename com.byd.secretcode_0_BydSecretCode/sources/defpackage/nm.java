package defpackage;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.EventBusException;

/* JADX INFO: compiled from: SubscriberMethodFinder.java */
/* JADX INFO: loaded from: classes.dex */
public class nm {
    public static final Map<Class<?>, List<mm>> a = new ConcurrentHashMap();
    public static final a[] b = new a[4];
    public List<rm> c;
    public final boolean d;
    public final boolean e;

    /* JADX INFO: compiled from: SubscriberMethodFinder.java */
    public static class a {
        public final List<mm> a = new ArrayList();
        public final Map<Class, Object> b = new HashMap();
        public final Map<String, Class> c = new HashMap();
        public final StringBuilder d = new StringBuilder(128);
        public Class<?> e;
        public Class<?> f;
        public boolean g;
        public qm h;

        public boolean a(Method method, Class<?> cls) {
            Object objPut = this.b.put(cls, method);
            if (objPut == null) {
                return true;
            }
            if (objPut instanceof Method) {
                if (!b((Method) objPut, cls)) {
                    throw new IllegalStateException();
                }
                this.b.put(cls, this);
            }
            return b(method, cls);
        }

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

        public void c(Class<?> cls) {
            this.f = cls;
            this.e = cls;
            this.g = false;
            this.h = null;
        }

        public void d() {
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

        public void e() {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }
    }

    public nm(List<rm> list, boolean z, boolean z2) {
        this.c = list;
        this.d = z;
        this.e = z2;
    }

    public List<mm> a(Class<?> cls) {
        Map<Class<?>, List<mm>> map = a;
        List<mm> list = map.get(cls);
        if (list != null) {
            return list;
        }
        List<mm> listC = this.e ? c(cls) : b(cls);
        if (!listC.isEmpty()) {
            map.put(cls, listC);
            return listC;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }

    public final List<mm> b(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f != null) {
            qm qmVarF = f(aVarG);
            aVarG.h = qmVarF;
            if (qmVarF != null) {
                for (mm mmVar : qmVarF.a()) {
                    if (aVarG.a(mmVar.a, mmVar.c)) {
                        aVarG.a.add(mmVar);
                    }
                }
            } else {
                d(aVarG);
            }
            aVarG.d();
        }
        return e(aVarG);
    }

    public final List<mm> c(Class<?> cls) {
        a aVarG = g();
        aVarG.c(cls);
        while (aVarG.f != null) {
            d(aVarG);
            aVarG.d();
        }
        return e(aVarG);
    }

    public final void d(a aVar) {
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
                        km kmVar = (km) method.getAnnotation(km.class);
                        if (kmVar != null) {
                            Class<?> cls = parameterTypes[0];
                            if (aVar.a(method, cls)) {
                                aVar.a.add(new mm(method, cls, kmVar.threadMode(), kmVar.priority(), kmVar.sticky()));
                            }
                        }
                    } else if (this.d && method.isAnnotationPresent(km.class)) {
                        throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                    }
                } else if (this.d && method.isAnnotationPresent(km.class)) {
                    throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
                }
            }
        } catch (LinkageError e) {
            String str = "Could not inspect methods of " + aVar.f.getName();
            throw new EventBusException(this.e ? str + ". Please consider using EventBus annotation processor to avoid reflection." : str + ". Please make this class visible to EventBus annotation processor to avoid reflection.", e);
        }
    }

    public final List<mm> e(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.a);
        aVar.e();
        synchronized (b) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                a[] aVarArr = b;
                if (aVarArr[i] == null) {
                    aVarArr[i] = aVar;
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    public final qm f(a aVar) {
        qm qmVar = aVar.h;
        if (qmVar != null && qmVar.c() != null) {
            qm qmVarC = aVar.h.c();
            if (aVar.f == qmVarC.b()) {
                return qmVarC;
            }
        }
        List<rm> list = this.c;
        if (list == null) {
            return null;
        }
        Iterator<rm> it = list.iterator();
        while (it.hasNext()) {
            qm qmVarA = it.next().a(aVar.f);
            if (qmVarA != null) {
                return qmVarA;
            }
        }
        return null;
    }

    public final a g() {
        synchronized (b) {
            for (int i = 0; i < 4; i++) {
                a[] aVarArr = b;
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
