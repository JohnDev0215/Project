package androidx.lifecycle;

/* JADX INFO: compiled from: ViewModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f1625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r f1626b;

    /* JADX INFO: compiled from: ViewModelProvider.java */
    public interface a {
        <T extends p> T a(Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    static abstract class b extends c implements a {
        b() {
        }

        @Override // androidx.lifecycle.q.a
        public <T extends p> T a(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }

        public abstract <T extends p> T c(String str, Class<T> cls);
    }

    /* JADX INFO: compiled from: ViewModelProvider.java */
    static class c {
        c() {
        }

        void b(p pVar) {
        }
    }

    public q(r rVar, a aVar) {
        this.f1625a = aVar;
        this.f1626b = rVar;
    }

    public <T extends p> T a(Class<T> cls) {
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
    public <T extends p> T b(String str, Class<T> cls) {
        T t = (T) this.f1626b.b(str);
        if (cls.isInstance(t)) {
            Object obj = this.f1625a;
            if (obj instanceof c) {
                ((c) obj).b(t);
            }
            return t;
        }
        a aVar = this.f1625a;
        T t2 = aVar instanceof b ? (T) ((b) aVar).c(str, cls) : (T) aVar.a(cls);
        this.f1626b.d(str, t2);
        return t2;
    }
}
