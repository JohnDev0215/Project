package defpackage;

import dalvik.system.BaseDexClassLoader;

/* JADX INFO: compiled from: WrappedClassLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class wt3 extends ClassLoader {
    public final ClassLoader a;
    public final ClassLoader b;

    public wt3(ClassLoader classLoader, ClassLoader classLoader2) {
        this.a = classLoader;
        this.b = classLoader2;
    }

    @Override // java.lang.ClassLoader
    public final Class<?> findClass(String str) {
        try {
            return this.a.loadClass(str);
        } catch (ClassNotFoundException unused) {
            return this.b.loadClass(str);
        }
    }

    @Override // java.lang.ClassLoader
    public final String findLibrary(String str) {
        String strFindLibrary;
        ClassLoader classLoader = this.a;
        if (classLoader instanceof BaseDexClassLoader) {
            strFindLibrary = ((BaseDexClassLoader) classLoader).findLibrary(str);
            if (strFindLibrary != null) {
                return strFindLibrary;
            }
        } else {
            strFindLibrary = null;
        }
        ClassLoader classLoader2 = this.b;
        return classLoader2 instanceof BaseDexClassLoader ? ((BaseDexClassLoader) classLoader2).findLibrary(str) : strFindLibrary;
    }
}
