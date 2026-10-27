package n4;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f19947b = new n(2);
    public static final n c = new n(0);
    public static final n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n f19948e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f19949f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e4.g f19950g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f19951h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19952a;

    static {
        n nVar = new n(1);
        d = nVar;
        f19948e = new n(3);
        f19949f = nVar;
        f19950g = e4.g.a(nVar, "com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy");
        f19951h = true;
    }

    public /* synthetic */ n(int i7) {
        this.f19952a = i7;
    }

    public final int a(int i7, int i10, int i11, int i12) {
        switch (this.f19952a) {
            case 0:
                if (b(i7, i10, i11, i12) != 1.0f) {
                    break;
                }
                break;
            case 2:
                if (f19951h) {
                }
                break;
        }
        return 2;
    }

    public final float b(int i7, int i10, int i11, int i12) {
        switch (this.f19952a) {
            case 0:
                return Math.min(1.0f, f19947b.b(i7, i10, i11, i12));
            case 1:
                return Math.max(i11 / i7, i12 / i10);
            case 2:
                if (f19951h) {
                    return Math.min(i11 / i7, i12 / i10);
                }
                return Math.max(i10 / i12, i7 / i11) != 0 ? 1.0f / Integer.highestOneBit(r0) : 1.0f;
            default:
                return 1.0f;
        }
    }
}
