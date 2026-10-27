package kotlin.jvm.internal;

import p.d81;
import p.f81;
import p.qz0;
import p.r22;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class FunctionReference extends CallableReference implements qz0, f81 {
    private final int arity;
    private final int flags;

    public FunctionReference(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        this.arity = i;
        this.flags = i2 >> 1;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final d81 a() {
        r22.a.getClass();
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            return b().equals(functionReference.b()) && d().equals(functionReference.d()) && this.flags == functionReference.flags && this.arity == functionReference.arity && v03.b(this.receiver, functionReference.receiver) && v03.b(c(), functionReference.c());
        }
        if (!(obj instanceof f81)) {
            return false;
        }
        d81 d81Var = this.c;
        if (d81Var == null) {
            a();
            this.c = this;
            d81Var = this;
        }
        return obj.equals(d81Var);
    }

    @Override // p.qz0
    public final int getArity() {
        return this.arity;
    }

    public final int hashCode() {
        return d().hashCode() + ((b().hashCode() + (c() == null ? 0 : c().hashCode() * 31)) * 31);
    }

    public final String toString() {
        d81 d81Var = this.c;
        if (d81Var == null) {
            a();
            this.c = this;
            d81Var = this;
        }
        if (d81Var != this) {
            return d81Var.toString();
        }
        if ("<init>".equals(b())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + b() + " (Kotlin reflection is not available)";
    }
}
