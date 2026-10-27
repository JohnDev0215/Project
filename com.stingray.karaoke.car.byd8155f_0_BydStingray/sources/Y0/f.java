package Y0;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f10612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f10613b;
    public static final float c;

    static {
        a(0.0f);
        a(0.5f);
        f10612a = 0.5f;
        a(-1.0f);
        f10613b = -1.0f;
        a(1.0f);
        c = 1.0f;
    }

    public static void a(float f10) {
        if ((0.0f > f10 || f10 > 1.0f) && f10 != -1.0f) {
            throw new IllegalStateException("topRatio should be in [0..1] range or -1");
        }
    }
}
