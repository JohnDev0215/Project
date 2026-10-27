package defpackage;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class tg0 implements lz0 {
    public final boolean e;

    public tg0(boolean z) {
        this.e = z;
    }

    @Override // defpackage.lz0
    public final yn1 b() {
        return null;
    }

    @Override // defpackage.lz0
    public final boolean isActive() {
        return this.e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(this.e ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
