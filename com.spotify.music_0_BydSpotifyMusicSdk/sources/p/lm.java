package p;

/* JADX INFO: loaded from: classes.dex */
public final class lm extends ci1 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f147p;

    public lm(qk qkVar) {
        this.f147p = qkVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof lm) && v03.b(this.f147p, ((lm) obj).f147p);
    }

    public final int hashCode() {
        return this.f147p.hashCode();
    }

    public final String toString() {
        return "Element(value=" + this.f147p + ')';
    }
}
