package android.database.sqlite;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteDatabaseCorruptException extends SQLiteException {
    public SQLiteDatabaseCorruptException() {
    }

    public SQLiteDatabaseCorruptException(String str) {
        super(str);
    }

    public static boolean isCorruptException(Throwable th) {
        while (th != null) {
            if (th instanceof SQLiteDatabaseCorruptException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }
}
