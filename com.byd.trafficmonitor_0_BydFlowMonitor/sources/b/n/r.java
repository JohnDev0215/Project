package b.n;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: ViewModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f2973b;

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class a extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static a f2974b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Application f2975c;

        public a(Application application) {
            this.f2975c = application;
        }

        public static a c(Application application) {
            if (f2974b == null) {
                f2974b = new a(application);
            }
            return f2974b;
        }

        @Override // b.n.r.d, b.n.r.b
        public <T extends q> T a(Class<T> cls) {
            if (!b.n.a.class.isAssignableFrom(cls)) {
                return (T) super.a(cls);
            }
            try {
                return cls.getConstructor(Application.class).newInstance(this.f2975c);
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
    public static abstract class c extends e implements b {
        public <T extends q> T a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        public abstract <T extends q> T c(String str, Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class d implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static d f2976a;

        public static d b() {
            if (f2976a == null) {
                f2976a = new d();
            }
            return f2976a;
        }

        @Override // b.n.r.b
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

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public static class e {
        public void b(q qVar) {
        }
    }

    public r(s sVar, b bVar) {
        this.f2972a = bVar;
        this.f2973b = sVar;
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
        T t = (T) this.f2973b.b(str);
        if (cls.isInstance(t)) {
            Object obj = this.f2972a;
            if (obj instanceof e) {
                ((e) obj).b(t);
            }
            return t;
        }
        b bVar = this.f2972a;
        T t2 = bVar instanceof c ? (T) ((c) bVar).c(str, cls) : (T) bVar.a(cls);
        this.f2973b.d(str, t2);
        return t2;
    }
}
