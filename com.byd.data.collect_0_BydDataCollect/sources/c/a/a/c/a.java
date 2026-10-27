package c.a.a.c;

/* JADX INFO: compiled from: ExceptionUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Throwable th) {
        return th == null ? "null" : c.a.a.f.b.f("{}: {}", th.getClass().getSimpleName(), th.getMessage());
    }
}
