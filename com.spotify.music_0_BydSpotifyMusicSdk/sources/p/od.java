package p;

/* JADX INFO: loaded from: classes.dex */
public final class od {
    public final Boolean a;

    public od(Boolean bool) {
        this.a = bool;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof od) && v03.b(this.a, ((od) obj).a);
    }

    public final int hashCode() {
        Boolean bool = this.a;
        if (bool == null) {
            return 0;
        }
        return bool.hashCode();
    }

    public final String toString() {
        return "AccessInfo(isUserMemberOfAtLeastOneGroup=" + this.a + ')';
    }
}
