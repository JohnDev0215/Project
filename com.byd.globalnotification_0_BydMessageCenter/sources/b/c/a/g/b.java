package b.c.a.g;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1762a;

    static {
        int i = 1;
        try {
            int i2 = f1762a;
            if (i2 == 0) {
                i = 0;
            } else if (i2 == 2) {
                i = 2;
            }
        } catch (Throwable th) {
            b.c.a.h.b.b(" BydUiStyleConfig --> getSysUIStyle:error:" + th);
        }
        f1762a = i;
    }
}
