package D1;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f1128e = new byte[1792];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f1129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1130b;
    public int c;
    public char d;

    static {
        for (int i7 = 0; i7 < 1792; i7++) {
            f1128e[i7] = Character.getDirectionality(i7);
        }
    }

    public a(CharSequence charSequence) {
        this.f1129a = charSequence;
        this.f1130b = charSequence.length();
    }

    public final byte a() {
        int i7 = this.c - 1;
        CharSequence charSequence = this.f1129a;
        char cCharAt = charSequence.charAt(i7);
        this.d = cCharAt;
        if (Character.isLowSurrogate(cCharAt)) {
            int iCodePointBefore = Character.codePointBefore(charSequence, this.c);
            this.c -= Character.charCount(iCodePointBefore);
            return Character.getDirectionality(iCodePointBefore);
        }
        this.c--;
        char c = this.d;
        return c < 1792 ? f1128e[c] : Character.getDirectionality(c);
    }
}
