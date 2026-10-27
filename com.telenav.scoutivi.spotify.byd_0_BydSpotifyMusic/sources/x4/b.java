package x4;

import java.util.Objects;
import v4.c;

/* JADX INFO: compiled from: ObjectHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c<Object, Object> f9852a = new a();

    /* JADX INFO: compiled from: ObjectHelper.java */
    static final class a implements c<Object, Object> {
        a() {
        }
    }

    public static int a(int i7, int i8) {
        if (i7 < i8) {
            return -1;
        }
        return i7 > i8 ? 1 : 0;
    }

    public static int b(long j7, long j8) {
        if (j7 < j8) {
            return -1;
        }
        return j7 > j8 ? 1 : 0;
    }

    public static <T> T c(T t6, String str) {
        Objects.requireNonNull(t6, str);
        return t6;
    }
}
