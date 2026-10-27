package androidx.sqlite.db.framework;

/* JADX INFO: loaded from: classes.dex */
final class FrameworkSQLiteOpenHelper$OpenHelper$CallbackException extends RuntimeException {
    private final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName callbackName;
    private final Throwable cause;

    public FrameworkSQLiteOpenHelper$OpenHelper$CallbackException(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName, Throwable th) {
        super(th);
        this.callbackName = frameworkSQLiteOpenHelper$OpenHelper$CallbackName;
        this.cause = th;
    }

    public final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName a() {
        return this.callbackName;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.cause;
    }
}
