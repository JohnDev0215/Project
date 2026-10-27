package p;

/* JADX INFO: loaded from: classes.dex */
public final class w72 extends x72 {
    public final String a;

    public w72(String str) {
        v03.j(str, "message");
        this.a = str;
    }

    @Override // p.x72
    public final String a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof w72) && v03.b(this.a, ((w72) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Incorrect(message="), this.a, ')');
    }
}
