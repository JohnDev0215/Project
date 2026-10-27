package kotlin.text;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final kotlin.o.c f5181b;

    public g(String str, kotlin.o.c cVar) {
        kotlin.jvm.internal.i.c(str, "value");
        kotlin.jvm.internal.i.c(cVar, "range");
        this.f5180a = str;
        this.f5181b = cVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return kotlin.jvm.internal.i.a(this.f5180a, gVar.f5180a) && kotlin.jvm.internal.i.a(this.f5181b, gVar.f5181b);
    }

    public int hashCode() {
        String str = this.f5180a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        kotlin.o.c cVar = this.f5181b;
        return iHashCode + (cVar != null ? cVar.hashCode() : 0);
    }

    public String toString() {
        return "MatchGroup(value=" + this.f5180a + ", range=" + this.f5181b + ")";
    }
}
