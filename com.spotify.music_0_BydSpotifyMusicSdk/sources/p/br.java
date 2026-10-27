package p;

/* JADX INFO: loaded from: classes.dex */
public final class br extends cr {
    public final Throwable a;

    public br(Throwable th) {
        this.a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof br) {
            if (v03.b(this.a, ((br) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // p.cr
    public final String toString() {
        return "Closed(" + this.a + ')';
    }
}
