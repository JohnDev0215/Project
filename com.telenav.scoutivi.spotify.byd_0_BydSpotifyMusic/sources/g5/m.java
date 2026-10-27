package g5;

import g5.l;

/* JADX INFO: compiled from: Result.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final Object a(Throwable exception) {
        kotlin.jvm.internal.l.e(exception, "exception");
        return new l.b(exception);
    }

    public static final void b(Object obj) throws Throwable {
        if (obj instanceof l.b) {
            throw ((l.b) obj).f6558e;
        }
    }
}
