package L8;

import b8.C1011f;

/* JADX INFO: loaded from: classes.dex */
public final class y extends n {
    public final /* synthetic */ int c = 1;
    public final int d;

    /* JADX WARN: Illegal instructions before constructor call */
    public y(int i7) {
        StringBuilder sbJ = M5.b.j("must have at least ", i7, " value parameter");
        sbJ.append(i7 > 1 ? "s" : "");
        super(sbJ.toString(), 1);
        this.d = i7;
    }

    @Override // L8.e
    public final boolean c(C1011f c1011f) {
        switch (this.c) {
            case 0:
                if (c1011f.D0().size() >= this.d) {
                }
                break;
            default:
                if (c1011f.D0().size() == this.d) {
                }
                break;
        }
        return false;
    }

    public y() {
        super("must have exactly 2 value parameters", 1);
        this.d = 2;
    }
}
