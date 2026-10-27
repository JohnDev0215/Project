package p;

/* JADX INFO: loaded from: classes.dex */
public final class td2 implements sd2 {
    public static final td2 a = new td2();

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return sd2.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        return obj instanceof sd2;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return 0;
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return u4.d(sd2.class, new StringBuilder("@"), "()");
    }
}
