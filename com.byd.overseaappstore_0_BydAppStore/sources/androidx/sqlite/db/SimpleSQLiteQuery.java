package androidx.sqlite.db;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {

    @Nullable
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String str) {
        this(str, null);
    }

    public SimpleSQLiteQuery(String str, @Nullable Object[] objArr) {
        this.mQuery = str;
        this.mBindArgs = objArr;
    }

    private static void bind(SupportSQLiteProgram supportSQLiteProgram, int i, Object obj) {
        long jLongValue;
        int iByteValue;
        double dDoubleValue;
        if (obj == null) {
            supportSQLiteProgram.bindNull(i);
            return;
        }
        if (obj instanceof byte[]) {
            supportSQLiteProgram.bindBlob(i, (byte[]) obj);
            return;
        }
        if (obj instanceof Float) {
            dDoubleValue = ((Float) obj).floatValue();
        } else {
            if (!(obj instanceof Double)) {
                if (obj instanceof Long) {
                    jLongValue = ((Long) obj).longValue();
                } else {
                    if (obj instanceof Integer) {
                        iByteValue = ((Integer) obj).intValue();
                    } else if (obj instanceof Short) {
                        iByteValue = ((Short) obj).shortValue();
                    } else if (obj instanceof Byte) {
                        iByteValue = ((Byte) obj).byteValue();
                    } else {
                        if (obj instanceof String) {
                            supportSQLiteProgram.bindString(i, (String) obj);
                            return;
                        }
                        if (!(obj instanceof Boolean)) {
                            throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i + " Supported types: null, byte[], float, double, long, int, short, byte, string");
                        }
                        jLongValue = ((Boolean) obj).booleanValue() ? 1L : 0L;
                    }
                    jLongValue = iByteValue;
                }
                supportSQLiteProgram.bindLong(i, jLongValue);
                return;
            }
            dDoubleValue = ((Double) obj).doubleValue();
        }
        supportSQLiteProgram.bindDouble(i, dDoubleValue);
    }

    public static void bind(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            i++;
            bind(supportSQLiteProgram, i, obj);
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        bind(supportSQLiteProgram, this.mBindArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        Object[] objArr = this.mBindArgs;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }
}
