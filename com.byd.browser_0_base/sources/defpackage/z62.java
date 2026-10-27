package defpackage;

import defpackage.kl;

/* JADX INFO: compiled from: PropertyReference.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z62 extends kl implements x31 {
    public z62(Object obj) {
        super(obj, d70.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", true);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof z62) {
            z62 z62Var = (z62) obj;
            return c().equals(z62Var.c()) && this.h.equals(z62Var.h) && this.i.equals(z62Var.i) && i21.a(this.f, z62Var.f);
        }
        if (!(obj instanceof x31)) {
            return false;
        }
        v31 v31VarB = this.e;
        if (v31VarB == null) {
            v31VarB = b();
            this.e = v31VarB;
        }
        return obj.equals(v31VarB);
    }

    public final int hashCode() {
        return this.i.hashCode() + ((this.h.hashCode() + (c().hashCode() * 31)) * 31);
    }

    public final String toString() {
        v31 v31VarB = this.e;
        if (v31VarB == null) {
            v31VarB = b();
            this.e = v31VarB;
        }
        return v31VarB != this ? v31VarB.toString() : gq2.i(new StringBuilder("property "), this.h, " (Kotlin reflection is not available)");
    }

    public z62() {
        super(kl.a.e, null, null, null, false);
    }
}
