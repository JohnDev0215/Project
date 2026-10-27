package s0;

/* JADX INFO: renamed from: s0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2359z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f22063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22064b;

    public AbstractC2359z(int i7, boolean z6, boolean z10) {
        z6 = (i7 & 1) != 0 ? false : z6;
        z10 = (i7 & 2) != 0 ? false : z10;
        this.f22063a = z6;
        this.f22064b = z10;
    }
}
