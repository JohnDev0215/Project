package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: Nl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0244Nl extends AbstractC0280Pl {
    public static final Class c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public C0244Nl(AbstractC0226Ml abstractC0226Ml) {
        super(null);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static List d(Object obj, long j, int i) {
        List list;
        List list2 = (List) AbstractC0066Dm.n(obj, j);
        if (list2.isEmpty()) {
            List c0191Kl = list2 instanceof InterfaceC0209Ll ? new C0191Kl(i) : new ArrayList(i);
            AbstractC0066Dm.f.q(obj, j, c0191Kl);
            return c0191Kl;
        }
        if (c.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + i);
            arrayList.addAll(list2);
            AbstractC0066Dm.f.q(obj, j, arrayList);
            list = arrayList;
        } else {
            if (!(list2 instanceof C1782xm)) {
                return list2;
            }
            C0191Kl c0191Kl2 = new C0191Kl(list2.size() + i);
            c0191Kl2.addAll(c0191Kl2.size(), (C1782xm) list2);
            AbstractC0066Dm.f.q(obj, j, c0191Kl2);
            list = c0191Kl2;
        }
        return list;
    }

    @Override // defpackage.AbstractC0280Pl
    public void a(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) AbstractC0066Dm.n(obj, j);
        if (list instanceof InterfaceC0209Ll) {
            objUnmodifiableList = ((InterfaceC0209Ll) list).a();
        } else if (c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        AbstractC0066Dm.f.q(obj, j, objUnmodifiableList);
    }

    @Override // defpackage.AbstractC0280Pl
    public void b(Object obj, Object obj2, long j) {
        List list = (List) AbstractC0066Dm.n(obj2, j);
        List listD = d(obj, j, list.size());
        int size = listD.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            listD.addAll(list);
        }
        if (size > 0) {
            list = listD;
        }
        AbstractC0066Dm.f.q(obj, j, list);
    }

    @Override // defpackage.AbstractC0280Pl
    public List c(Object obj, long j) {
        return d(obj, j, 10);
    }
}
