package t8;

import F8.AbstractC0287w;

/* JADX INFO: loaded from: classes.dex */
public final class o extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0287w f22506a;

    public o(AbstractC0287w abstractC0287w) {
        this.f22506a = abstractC0287w;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof o) && A7.m.a(this.f22506a, ((o) obj).f22506a);
    }

    public final int hashCode() {
        return this.f22506a.hashCode();
    }

    public final String toString() {
        return "LocalClass(type=" + this.f22506a + ')';
    }
}
