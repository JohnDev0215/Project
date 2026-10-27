package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p f779b;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        @NonNull
        <T extends n> T a(@NonNull Class<T> cls);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static abstract class b extends c implements a {
        b() {
        }

        @Override // androidx.lifecycle.o.a
        @NonNull
        public <T extends n> T a(@NonNull Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        @NonNull
        public abstract <T extends n> T c(@NonNull String str, @NonNull Class<T> cls);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c {
        c() {
        }

        void b(@NonNull n nVar) {
        }
    }

    public o(@NonNull p pVar, @NonNull a aVar) {
        this.f778a = aVar;
        this.f779b = pVar;
    }

    @NonNull
    @MainThread
    public <T extends n> T a(@NonNull Class<T> cls) {
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
    @NonNull
    @MainThread
    public <T extends n> T b(@NonNull String str, @NonNull Class<T> cls) {
        T t = (T) this.f779b.b(str);
        if (cls.isInstance(t)) {
            Object obj = this.f778a;
            if (obj instanceof c) {
                ((c) obj).b(t);
            }
            return t;
        }
        a aVar = this.f778a;
        T t2 = aVar instanceof b ? (T) ((b) aVar).c(str, cls) : (T) aVar.a(cls);
        this.f779b.d(str, t2);
        return t2;
    }
}
