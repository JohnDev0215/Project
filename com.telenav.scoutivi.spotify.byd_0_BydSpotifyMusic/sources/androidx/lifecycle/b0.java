package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: ViewModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f2531a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d0 f2532b;

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class a extends d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static a f2533c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Application f2534b;

        public a(Application application) {
            this.f2534b = application;
        }

        public static a c(Application application) {
            if (f2533c == null) {
                f2533c = new a(application);
            }
            return f2533c;
        }

        @Override // androidx.lifecycle.b0.d, androidx.lifecycle.b0.b
        public <T extends a0> T a(Class<T> cls) {
            if (!androidx.lifecycle.b.class.isAssignableFrom(cls)) {
                return (T) super.a(cls);
            }
            try {
                return cls.getConstructor(Application.class).newInstance(this.f2534b);
            } catch (IllegalAccessException e7) {
                throw new RuntimeException("Cannot create an instance of " + cls, e7);
            } catch (InstantiationException e8) {
                throw new RuntimeException("Cannot create an instance of " + cls, e8);
            } catch (NoSuchMethodException e9) {
                throw new RuntimeException("Cannot create an instance of " + cls, e9);
            } catch (InvocationTargetException e10) {
                throw new RuntimeException("Cannot create an instance of " + cls, e10);
            }
        }
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public interface b {
        <T extends a0> T a(Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    static abstract class c extends e implements b {
        c() {
        }

        public <T extends a0> T a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        public abstract <T extends a0> T c(String str, Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class d implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static d f2535a;

        static d b() {
            if (f2535a == null) {
                f2535a = new d();
            }
            return f2535a;
        }

        @Override // androidx.lifecycle.b0.b
        public <T extends a0> T a(Class<T> cls) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e7) {
                throw new RuntimeException("Cannot create an instance of " + cls, e7);
            } catch (InstantiationException e8) {
                throw new RuntimeException("Cannot create an instance of " + cls, e8);
            }
        }
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    static class e {
        e() {
        }

        void b(a0 a0Var) {
        }
    }

    public b0(e0 e0Var) {
        this(e0Var.k(), e0Var instanceof h ? ((h) e0Var).m() : d.b());
    }

    public <T extends a0> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return (T) b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
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
    public <T extends a0> T b(String str, Class<T> cls) {
        T t6 = (T) this.f2532b.b(str);
        if (cls.isInstance(t6)) {
            Object obj = this.f2531a;
            if (obj instanceof e) {
                ((e) obj).b(t6);
            }
            return t6;
        }
        b bVar = this.f2531a;
        T t7 = bVar instanceof c ? (T) ((c) bVar).c(str, cls) : (T) bVar.a(cls);
        this.f2532b.d(str, t7);
        return t7;
    }

    public b0(e0 e0Var, b bVar) {
        this(e0Var.k(), bVar);
    }

    public b0(d0 d0Var, b bVar) {
        this.f2531a = bVar;
        this.f2532b = d0Var;
    }
}
