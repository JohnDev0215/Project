package H7;

import K7.p0;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;

/* JADX INFO: loaded from: classes.dex */
public final class E implements TypeVariable, Type {
    public final y l;

    public E(y yVar) {
        A7.m.f(yVar, "typeParameter");
        this.l = yVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TypeVariable) || !getName().equals(((TypeVariable) obj).getName())) {
            return false;
        }
        getGenericDeclaration();
        throw null;
    }

    @Override // java.lang.reflect.TypeVariable
    public final Type[] getBounds() {
        p0 p0Var = (p0) this.l;
        p0Var.getClass();
        w wVar = p0.o[0];
        Object objA = p0Var.f3828m.a();
        A7.m.e(objA, "<get-upperBounds>(...)");
        List list = (List) objA;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(H.r((x) it.next(), true));
        }
        return (Type[]) arrayList.toArray(new Type[0]);
    }

    @Override // java.lang.reflect.TypeVariable
    public final GenericDeclaration getGenericDeclaration() {
        String strG = M5.b.g("An operation is not implemented: ", "getGenericDeclaration() is not yet supported for type variables created from KType: " + this.l);
        A7.m.f(strG, "message");
        throw new m7.h(strG);
    }

    @Override // java.lang.reflect.TypeVariable
    public final String getName() {
        return ((p0) this.l).d();
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        return getName();
    }

    public final int hashCode() {
        getName();
        getGenericDeclaration();
        throw null;
    }

    public final String toString() {
        return getName();
    }
}
