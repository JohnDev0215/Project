package lombok;

/* JADX INFO: loaded from: classes5.dex */
public class Lombok {
    public static <T> T preventNullAnalysis(T t) {
        return t;
    }

    public static RuntimeException sneakyThrow(Throwable th) {
        if (th == null) {
            throw new NullPointerException("t");
        }
        return (RuntimeException) sneakyThrow0(th);
    }

    private static <T extends Throwable> T sneakyThrow0(Throwable th) throws Throwable {
        throw th;
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
