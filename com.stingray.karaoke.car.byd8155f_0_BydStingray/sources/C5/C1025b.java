package c5;

/* JADX INFO: renamed from: c5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1025b extends AbstractC1024a {
    public final char l;

    public C1025b(char c) {
        this.l = c;
    }

    @Override // c5.AbstractC1024a
    public final boolean a(char c) {
        return c == this.l;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CharMatcher.is('");
        char[] cArr = new char[6];
        cArr[0] = '\\';
        cArr[1] = 'u';
        cArr[2] = 0;
        cArr[3] = 0;
        cArr[4] = 0;
        cArr[5] = 0;
        char c = this.l;
        for (int i7 = 0; i7 < 4; i7++) {
            cArr[5 - i7] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        sb.append(String.copyValueOf(cArr));
        sb.append("')");
        return sb.toString();
    }
}
