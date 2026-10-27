package y5;

/* JADX INFO: renamed from: y5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2748f extends AbstractC2745c {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public byte[] f24128q;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        AbstractC2745c abstractC2745c = (AbstractC2745c) obj;
        long j3 = abstractC2745c.l;
        long j10 = this.l;
        if (j10 < j3) {
            return -1;
        }
        if (j10 <= j3) {
            int i7 = this.f24127m.f413b;
            int i10 = abstractC2745c.f24127m.f413b;
            if (i7 > i10) {
                return -1;
            }
            if (i7 >= i10 && (abstractC2745c instanceof C2748f)) {
                return new String(this.f24128q).compareTo(new String(((C2748f) abstractC2745c).f24128q));
            }
        }
        return 1;
    }
}
