package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: ViewModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final s f1518b;

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class a extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f1519a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Application f1520b;

        public a(Application application) {
            this.f1520b = application;
        }

        public static a b(Application application) {
            if (f1519a == null) {
                f1519a = new a(application);
            }
            return f1519a;
        }

        @Override // androidx.lifecycle.r.d, androidx.lifecycle.r.b
        public <T extends q> T a(Class<T> cls) {
            if (!androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                return (T) super.a(cls);
            }
            try {
                return cls.getConstructor(Application.class).newInstance(this.f1520b);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimeException("Cannot create an instance of " + cls, e5);
            }
        }
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public interface b {
        <T extends q> T a(Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    static abstract class c implements b {
        c() {
        }

        @Override // androidx.lifecycle.r.b
        public <T extends q> T a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        public abstract <T extends q> T b(String str, Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class d implements b {
        @Override // androidx.lifecycle.r.b
        public <T extends q> T a(Class<T> cls) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            }
        }
    }

    public r(t tVar, b bVar) {
        this(tVar.getViewModelStore(), bVar);
    }

    public <T extends q> T a(Class<T> cls) {
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
    public <T extends q> T b(String str, Class<T> cls) {
        T t = (T) this.f1518b.b(str);
        if (cls.isInstance(t)) {
            return t;
        }
        b bVar = this.f1517a;
        T t2 = bVar instanceof c ? (T) ((c) bVar).b(str, cls) : (T) bVar.a(cls);
        this.f1518b.c(str, t2);
        return t2;
    }

    public r(s sVar, b bVar) {
        this.f1517a = bVar;
        this.f1518b = sVar;
    }
}
