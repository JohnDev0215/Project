package androidx.sqlite.db;

/* JADX INFO: loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String query, Object[] bindArgs) {
        this.mQuery = query;
        this.mBindArgs = bindArgs;
    }

    public SimpleSQLiteQuery(String query) {
        this(query, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram statement) {
        bind(statement, this.mBindArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        Object[] objArr = this.mBindArgs;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public static void bind(SupportSQLiteProgram statement, Object[] bindArgs) {
        if (bindArgs == null) {
            return;
        }
        int limit = bindArgs.length;
        for (int i = 0; i < limit; i++) {
            Object arg = bindArgs[i];
            bind(statement, i + 1, arg);
        }
    }

    private static void bind(SupportSQLiteProgram statement, int index, Object arg) {
        if (arg == null) {
            statement.bindNull(index);
            return;
        }
        if (arg instanceof byte[]) {
            statement.bindBlob(index, (byte[]) arg);
            return;
        }
        if (arg instanceof Float) {
            statement.bindDouble(index, ((Float) arg).floatValue());
            return;
        }
        if (arg instanceof Double) {
            statement.bindDouble(index, ((Double) arg).doubleValue());
            return;
        }
        if (arg instanceof Long) {
            statement.bindLong(index, ((Long) arg).longValue());
            return;
        }
        if (arg instanceof Integer) {
            statement.bindLong(index, ((Integer) arg).intValue());
            return;
        }
        if (arg instanceof Short) {
            statement.bindLong(index, ((Short) arg).shortValue());
            return;
        }
        if (arg instanceof Byte) {
            statement.bindLong(index, ((Byte) arg).byteValue());
            return;
        }
        if (arg instanceof String) {
            statement.bindString(index, (String) arg);
            return;
        }
        if (arg instanceof Boolean) {
            statement.bindLong(index, ((Boolean) arg).booleanValue() ? 1L : 0L);
            return;
        }
        throw new IllegalArgumentException("Cannot bind " + arg + " at index " + index + " Supported types: null, byte[], float, double, long, int, short, byte, string");
    }
}
