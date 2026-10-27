package L8;

import b8.C1011f;

/* JADX INFO: loaded from: classes.dex */
public final class z extends n {
    public static final z d = new z("must have no value parameters", 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f4113e = new z("must have a single value parameter", 1);
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(String str, int i7) {
        super(str, 1);
        this.c = i7;
    }

    @Override // L8.e
    public final boolean c(C1011f c1011f) {
        switch (this.c) {
            case 0:
                return c1011f.D0().isEmpty();
            default:
                return c1011f.D0().size() == 1;
        }
    }
}
