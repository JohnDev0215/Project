package kotlin.jvm.internal;

import p.d81;
import p.k81;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference extends CallableReference implements k81 {
    private final boolean syntheticJavaProperty;

    public PropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, (i & 1) == 1);
        this.syntheticJavaProperty = (i & 2) == 2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            return c().equals(propertyReference.c()) && b().equals(propertyReference.b()) && d().equals(propertyReference.d()) && v03.b(this.receiver, propertyReference.receiver);
        }
        if (obj instanceof k81) {
            return obj.equals(f());
        }
        return false;
    }

    public final d81 f() {
        if (this.syntheticJavaProperty) {
            return this;
        }
        d81 d81Var = this.c;
        if (d81Var != null) {
            return d81Var;
        }
        d81 d81VarA = a();
        this.c = d81VarA;
        return d81VarA;
    }

    public final int hashCode() {
        return d().hashCode() + ((b().hashCode() + (c().hashCode() * 31)) * 31);
    }

    public final String toString() {
        d81 d81VarF = f();
        if (d81VarF != this) {
            return d81VarF.toString();
        }
        return "property " + b() + " (Kotlin reflection is not available)";
    }
}
