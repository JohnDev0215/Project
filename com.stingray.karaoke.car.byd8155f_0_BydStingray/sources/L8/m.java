package L8;

import b8.C1011f;

/* JADX INFO: loaded from: classes.dex */
public final class m extends n {
    public static final m d = new m("must be a member function", 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f4088e = new m("must be a member or an extension function", 1);
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(String str, int i7) {
        super(str, 0);
        this.c = i7;
    }

    @Override // L8.e
    public final boolean c(C1011f c1011f) {
        switch (this.c) {
            case 0:
                return c1011f.f8903v != null;
            default:
                return (c1011f.f8903v == null && c1011f.f8902u == null) ? false : true;
        }
    }
}
