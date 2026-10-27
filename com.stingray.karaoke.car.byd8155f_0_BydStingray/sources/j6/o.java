package j6;

/* JADX INFO: loaded from: classes.dex */
public final class o extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f17061a;

    public o(a aVar) {
        this.f17061a = aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof o) && A7.m.a(this.f17061a, ((o) obj).f17061a);
    }

    public final int hashCode() {
        return this.f17061a.hashCode();
    }

    public final String toString() {
        return "FavoriteChanged(favoriteStatus=" + this.f17061a + ")";
    }
}
