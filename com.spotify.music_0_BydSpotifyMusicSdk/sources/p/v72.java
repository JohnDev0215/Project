package p;

/* JADX INFO: loaded from: classes.dex */
public final class v72 extends x72 {
    public final String a;

    public v72(String str) {
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
        return (obj instanceof v72) && v03.b(this.a, ((v72) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Absent(message="), this.a, ')');
    }
}
