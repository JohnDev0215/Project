package p;

/* JADX INFO: loaded from: classes.dex */
public final class mm2 {
    public final pm2 a;

    public mm2(pm2 pm2Var) {
        this.a = pm2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof mm2) && v03.b(this.a, ((mm2) obj).a);
    }

    public final int hashCode() {
        pm2 pm2Var = this.a;
        if (pm2Var == null) {
            return 0;
        }
        return pm2Var.hashCode();
    }

    public final String toString() {
        return "AlbumOfTrack(coverArt=" + this.a + ')';
    }
}
