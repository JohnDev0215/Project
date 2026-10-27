package P7;

import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2057b f5415a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2057b f5416b;
    public final C2057b c;

    public c(C2057b c2057b, C2057b c2057b2, C2057b c2057b3) {
        this.f5415a = c2057b;
        this.f5416b = c2057b2;
        this.c = c2057b3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return A7.m.a(this.f5415a, cVar.f5415a) && A7.m.a(this.f5416b, cVar.f5416b) && A7.m.a(this.c, cVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.f5416b.hashCode() + (this.f5415a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "PlatformMutabilityMapping(javaClass=" + this.f5415a + ", kotlinReadOnly=" + this.f5416b + ", kotlinMutable=" + this.c + ')';
    }
}
