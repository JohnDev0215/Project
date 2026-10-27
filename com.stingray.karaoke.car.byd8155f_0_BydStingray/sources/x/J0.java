package x;

/* JADX INFO: loaded from: classes.dex */
public final class J0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC2652s f23419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2612A f23420b;
    public final int c;

    public J0(AbstractC2652s abstractC2652s, InterfaceC2612A interfaceC2612A, int i7) {
        this.f23419a = abstractC2652s;
        this.f23420b = interfaceC2612A;
        this.c = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof J0)) {
            return false;
        }
        J0 j02 = (J0) obj;
        return A7.m.a(this.f23419a, j02.f23419a) && A7.m.a(this.f23420b, j02.f23420b) && this.c == j02.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + ((this.f23420b.hashCode() + (this.f23419a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "VectorizedKeyframeSpecElementInfo(vectorValue=" + this.f23419a + ", easing=" + this.f23420b + ", arcMode=" + ((Object) ("ArcMode(value=" + this.c + ')')) + ')';
    }
}
