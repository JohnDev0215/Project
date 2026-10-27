package p;

/* JADX INFO: loaded from: classes.dex */
public final class bc2 {
    public final xk1 a;
    public final xa b;
    public final lw1 c;

    public bc2(xa xaVar, lw1 lw1Var) {
        vk1 vk1Var = vk1.a;
        v03.j(xaVar, "appMetadata");
        v03.j(lw1Var, "policyInputs");
        this.a = vk1Var;
        this.b = xaVar;
        this.c = lw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bc2)) {
            return false;
        }
        bc2 bc2Var = (bc2) obj;
        return v03.b(this.a, bc2Var.a) && v03.b(this.b, bc2Var.b) && v03.b(this.c, bc2Var.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "Settings(mode=" + this.a + ", appMetadata=" + this.b + ", policyInputs=" + this.c + ')';
    }
}
