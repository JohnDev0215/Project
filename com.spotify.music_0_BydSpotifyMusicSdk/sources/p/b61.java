package p;

import java.util.Collections;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class b61 {
    public final ol1 a;
    public final int[] b;
    public final String[] c;
    public final Set d;

    public b61(ol1 ol1Var, int[] iArr, String[] strArr) {
        Set setSingleton;
        this.a = ol1Var;
        this.b = iArr;
        this.c = strArr;
        if (!(strArr.length == 0)) {
            setSingleton = Collections.singleton(strArr[0]);
            v03.i(setSingleton, "singleton(element)");
        } else {
            setSingleton = EmptySet.c;
        }
        this.d = setSingleton;
        if (iArr.length != strArr.length) {
            throw new IllegalStateException("Check failed.".toString());
        }
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
    public final void a(Set set) {
        Set set2;
        v03.j(set, "invalidatedTablesIds");
        int[] iArr = this.b;
        int length = iArr.length;
        if (length != 0) {
            int i = 0;
            if (length != 1) {
                SetBuilder setBuilder = new SetBuilder();
                int length2 = iArr.length;
                int i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    if (set.contains(Integer.valueOf(iArr[i]))) {
                        setBuilder.add(this.c[i2]);
                    }
                    i++;
                    i2 = i3;
                }
                setBuilder.b();
                set2 = setBuilder;
            } else {
                set2 = set.contains(Integer.valueOf(iArr[0])) ? this.d : EmptySet.c;
            }
        } else {
            set2 = EmptySet.c;
        }
        if (!set2.isEmpty()) {
            this.a.a(set2);
        }
    }
}
