package androidx.activity;

import androidx.activity.ComponentActivity;
import androidx.activity.result.a;
import defpackage.s2;
import defpackage.t2;

/* JADX INFO: compiled from: ComponentActivity.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ t2.a f;
    public final /* synthetic */ ComponentActivity.b g;

    public a(ComponentActivity.b bVar, int i, t2.a aVar) {
        this.g = bVar;
        this.e = i;
        this.f = aVar;
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
    @Override // java.lang.Runnable
    public final void run() {
        s2<O> s2Var;
        Object obj = this.f.a;
        ComponentActivity.b bVar = this.g;
        String str = (String) bVar.a.get(Integer.valueOf(this.e));
        if (str == null) {
            return;
        }
        a.C0001a c0001a = (a.C0001a) bVar.e.get(str);
        if (c0001a == null || (s2Var = c0001a.a) == 0) {
            bVar.g.remove(str);
            bVar.f.put(str, obj);
        } else if (bVar.d.remove(str)) {
            s2Var.a(obj);
        }
    }
}
