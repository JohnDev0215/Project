package q8;

/* JADX INFO: renamed from: q8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC2197a {
    f21259n((boolean) (0 == true ? 1 : 0), "NO_ARGUMENTS"),
    /* JADX INFO: Fake field, exist only in values array */
    EF17((boolean) (1 == true ? 1 : 0), "UNLESS_EMPTY"),
    /* JADX INFO: Fake field, exist only in values array */
    ALWAYS_PARENTHESIZED(true, true);

    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f21260m;

    /* synthetic */ EnumC2197a(boolean z6, String str) {
        this((i & 1) != 0 ? false : z6, false);
    }

    EnumC2197a(boolean z6, boolean z10) {
        this.l = z6;
        this.f21260m = z10;
    }
}
