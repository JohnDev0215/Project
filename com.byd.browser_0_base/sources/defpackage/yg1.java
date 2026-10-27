package defpackage;

/* JADX INFO: compiled from: MessageHeader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yg1 {
    public static final t50 e = new t50(24, 0);
    public static final t50 f = new t50(32, 1);
    public final t50 a;
    public final int b;
    public final int c;
    public long d;

    public yg1(int i) {
        this.a = e;
        this.b = i;
        this.c = 0;
        this.d = 0L;
    }

    public final boolean a(int i) {
        return (this.c & i) == i;
    }

    public final boolean b(int i) {
        return (this.c & 7) == i;
    }

    public final boolean c(int i, int i2) {
        return this.b == i && b(i2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || yg1.class != obj.getClass()) {
            return false;
        }
        yg1 yg1Var = (yg1) obj;
        t50 t50Var = this.a;
        t50 t50Var2 = yg1Var.a;
        return (t50Var == t50Var2 ? true : t50Var == null ? false : t50Var.equals(t50Var2)) && this.c == yg1Var.c && this.d == yg1Var.d && this.b == yg1Var.b;
    }

    public final int hashCode() {
        t50 t50Var = this.a;
        int iHashCode = ((((t50Var == null ? 0 : t50Var.hashCode()) + 31) * 31) + this.c) * 31;
        long j = this.d;
        return ((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.b;
    }

    public yg1(int i, int i2, long j) {
        this.a = f;
        this.b = i;
        this.c = i2;
        this.d = j;
    }

    public yg1(gq0 gq0Var) {
        e70 e70Var = new e70(gq0Var);
        t50 t50VarG = e70Var.g();
        this.a = t50VarG;
        int i = t50VarG.b;
        if (i >= 0) {
            int i2 = t50VarG.a;
            if (i2 < 24) {
                throw new q80(gq2.e("Incorrect message size, expecting at least 24, but got: ", i2));
            }
            if (i == 0 && i2 != 24) {
                throw new q80(gq2.e("Incorrect message size for a message with 0 fields, expecting 24, but got: ", i2));
            }
            if (i == 1 && i2 != 32) {
                throw new q80(gq2.e("Incorrect message size for a message with 1 fields, expecting 32, but got: ", i2));
            }
            if (e70Var.p(8) == 0) {
                this.b = e70Var.p(12);
                int iP = e70Var.p(16);
                this.c = iP;
                if (!((iP & 3) != 0)) {
                    this.d = 0L;
                    return;
                } else {
                    if (i2 >= 32) {
                        this.d = e70Var.s(24);
                        return;
                    }
                    throw new q80(gq2.e("Incorrect message size, expecting at least 32 for a message with a request identifier, but got: ", i2));
                }
            }
            throw new q80("Non-zero interface ID, expecting zero since associated interfaces are not yet supported.");
        }
        throw new q80(gq2.e("Incorrect number of fields, expecting at least 0, but got: ", i));
    }
}
