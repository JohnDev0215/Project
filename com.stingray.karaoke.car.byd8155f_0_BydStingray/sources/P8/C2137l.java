package p8;

/* JADX INFO: renamed from: p8.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2137l implements Comparable {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final EnumC2124L f20984m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f20985n;

    public C2137l(int i7, EnumC2124L enumC2124L, boolean z6) {
        this.l = i7;
        this.f20984m = enumC2124L;
        this.f20985n = z6;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.l - ((C2137l) obj).l;
    }
}
