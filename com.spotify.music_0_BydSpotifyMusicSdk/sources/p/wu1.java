package p;

/* JADX INFO: loaded from: classes.dex */
public final class wu1 {
    public final String a;

    public wu1(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof wu1) && v03.b(this.a, ((wu1) obj).a);
    }

    public final int hashCode() {
        String str = this.a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("OnError(message="), this.a, ')');
    }
}
