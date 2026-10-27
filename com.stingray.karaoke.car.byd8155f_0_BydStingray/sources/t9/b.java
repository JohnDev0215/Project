package t9;

/* JADX INFO: loaded from: classes.dex */
public final class b extends y9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w9.b f22510a = new w9.b();

    public static boolean h(f fVar, int i7) {
        CharSequence charSequence = fVar.f22526a;
        return fVar.f22530g < 4 && i7 < charSequence.length() && charSequence.charAt(i7) == '>';
    }

    @Override // y9.a
    public final w9.a d() {
        return this.f22510a;
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        char cCharAt;
        int i7 = fVar.f22528e;
        if (!h(fVar, i7)) {
            return null;
        }
        int i10 = fVar.c + fVar.f22530g;
        int i11 = i10 + 1;
        CharSequence charSequence = fVar.f22526a;
        int i12 = i7 + 1;
        if (i12 < charSequence.length() && ((cCharAt = charSequence.charAt(i12)) == '\t' || cCharAt == ' ')) {
            i11 = i10 + 2;
        }
        return new P2.g(-1, i11, false);
    }
}
