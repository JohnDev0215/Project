package androidx.lifecycle;

import defpackage.ac2;
import defpackage.an3;
import defpackage.g10;
import defpackage.i21;
import defpackage.xj1;
import defpackage.ym3;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: ViewModelProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nViewModelProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelProvider.kt\nandroidx/lifecycle/ViewModelProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,375:1\n1#2:376\n*E\n"})
public final class l {
    public final an3 a;
    public final a b;
    public final g10 c;

    /* JADX INFO: compiled from: ViewModelProvider.kt */
    public interface a {
        default <T extends ym3> T a(Class<T> cls) {
            throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
        }

        default ym3 b(Class cls, xj1 xj1Var) {
            return a(cls);
        }
    }

    /* JADX INFO: compiled from: ViewModelProvider.kt */
    public static class b {
    }

    public l(an3 an3Var, a aVar, g10 g10Var) {
        i21.e(an3Var, "store");
        i21.e(aVar, "factory");
        i21.e(g10Var, "defaultCreationExtras");
        this.a = an3Var;
        this.b = aVar;
        this.c = g10Var;
    }

    public final <T extends ym3> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) b(cls, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    public final ym3 b(Class cls, String str) {
        ym3 ym3VarA;
        i21.e(str, "key");
        an3 an3Var = this.a;
        an3Var.getClass();
        ym3 ym3Var = (ym3) an3Var.a.get(str);
        boolean zIsInstance = cls.isInstance(ym3Var);
        a aVar = this.b;
        if (zIsInstance) {
            if ((aVar instanceof b ? (b) aVar : null) != null) {
                i21.b(ym3Var);
            }
            i21.c(ym3Var, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
            return ym3Var;
        }
        xj1 xj1Var = new xj1(this.c);
        xj1Var.a.put(ac2.i, str);
        try {
            ym3VarA = aVar.b(cls, xj1Var);
        } catch (AbstractMethodError unused) {
            ym3VarA = aVar.a(cls);
        }
        i21.e(ym3VarA, "viewModel");
        ym3 ym3Var2 = (ym3) an3Var.a.put(str, ym3VarA);
        if (ym3Var2 != null) {
            ym3Var2.a();
        }
        return ym3VarA;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public l(an3 an3Var, a aVar) {
        this(an3Var, aVar, g10.a.b);
        i21.e(an3Var, "store");
        i21.e(aVar, "factory");
    }
}
