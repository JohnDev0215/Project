package j5;

/* JADX INFO: renamed from: j5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1551i {
    public static final C1551i d = new C1551i("", "", false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17016b;
    public final boolean c;

    static {
        new C1551i("\n", "  ", true);
    }

    public C1551i(String str, String str2, boolean z6) {
        if (!str.matches("[\r\n]*")) {
            throw new IllegalArgumentException("Only combinations of \\n and \\r are allowed in newline.");
        }
        if (!str2.matches("[ \t]*")) {
            throw new IllegalArgumentException("Only combinations of spaces and tabs are allowed in indent.");
        }
        this.f17015a = str;
        this.f17016b = str2;
        this.c = z6;
    }
}
