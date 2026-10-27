package p;

/* JADX INFO: loaded from: classes.dex */
public final class ji {
    public final long a;
    public final wi b;
    public final ai c;

    public ji(long j, wi wiVar, ai aiVar) {
        this.a = j;
        if (wiVar == null) {
            throw new NullPointerException("Null transportContext");
        }
        this.b = wiVar;
        this.c = aiVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ji)) {
            return false;
        }
        ji jiVar = (ji) obj;
        return this.a == jiVar.a && this.b.equals(jiVar.b) && this.c.equals(jiVar.c);
    }

    public final int hashCode() {
        long j = this.a;
        return ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "PersistedEvent{id=" + this.a + ", transportContext=" + this.b + ", event=" + this.c + "}";
    }
}
