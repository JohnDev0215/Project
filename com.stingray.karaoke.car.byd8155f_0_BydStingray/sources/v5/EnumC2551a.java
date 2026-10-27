package v5;

/* JADX INFO: renamed from: v5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC2551a {
    /* JADX INFO: Fake field, exist only in values array */
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    /* JADX INFO: Fake field, exist only in values array */
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    /* JADX INFO: Fake field, exist only in values array */
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    /* JADX INFO: Fake field, exist only in values array */
    HANZI(new int[]{8, 10, 12}, 13);

    public final int[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23015m;

    EnumC2551a(int[] iArr, int i7) {
        this.l = iArr;
        this.f23015m = i7;
    }

    public final int a(C2552b c2552b) {
        int i7 = c2552b.f23017a;
        return this.l[i7 <= 9 ? (char) 0 : i7 <= 26 ? (char) 1 : (char) 2];
    }
}
