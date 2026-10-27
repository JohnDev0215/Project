package net.bytebuddy.dynamic;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes3.dex */
public class Nexus extends WeakReference<ClassLoader> {
    protected static final ReferenceQueue<ClassLoader> NO_QUEUE = null;
    public static final String PROPERTY = "net.bytebuddy.nexus.disabled";
    private static final ConcurrentMap<Nexus, Object> TYPE_INITIALIZERS = new ConcurrentHashMap();
    private final int classLoaderHashCode;
    private final int identification;
    private final String name;

    private Nexus(Class<?> cls, int i) {
        this(nonAnonymous(cls.getName()), cls.getClassLoader(), NO_QUEUE, i);
    }

    private Nexus(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i) {
        super(classLoader, classLoader == null ? null : referenceQueue);
        this.name = str;
        this.classLoaderHashCode = System.identityHashCode(classLoader);
        this.identification = i;
    }

    private static String nonAnonymous(String str) {
        int iIndexOf = str.indexOf(47);
        return iIndexOf == -1 ? str : str.substring(0, iIndexOf);
    }

    public static void initialize(Class<?> cls, int i) throws Exception {
        Object objRemove = TYPE_INITIALIZERS.remove(new Nexus(cls, i));
        if (objRemove != null) {
            objRemove.getClass().getMethod("onLoad", Class.class).invoke(objRemove, cls);
        }
    }

    public static void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, Object obj) {
        TYPE_INITIALIZERS.put(new Nexus(str, classLoader, referenceQueue, i), obj);
    }

    public static void clean(Reference<? super ClassLoader> reference) {
        TYPE_INITIALIZERS.remove(reference);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nexus nexus = (Nexus) obj;
        return this.classLoaderHashCode == nexus.classLoaderHashCode && this.identification == nexus.identification && this.name.equals(nexus.name) && get() == nexus.get();
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.classLoaderHashCode) * 31) + this.identification;
    }

    public String toString() {
        return "Nexus{name='" + this.name + "', classLoaderHashCode=" + this.classLoaderHashCode + ", identification=" + this.identification + ", classLoader=" + get() + '}';
    }
}
