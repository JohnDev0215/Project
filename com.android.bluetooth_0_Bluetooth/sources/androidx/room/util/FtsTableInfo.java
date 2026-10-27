package androidx.room.util;

import android.database.Cursor;
import android.net.INetd;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class FtsTableInfo {
    private static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set<String> columns;
    public final String name;
    public final Set<String> options;

    public FtsTableInfo(String name, Set<String> columns, Set<String> options) {
        this.name = name;
        this.columns = columns;
        this.options = options;
    }

    public FtsTableInfo(String name, Set<String> columns, String createSql) {
        this.name = name;
        this.columns = columns;
        this.options = parseOptions(createSql);
    }

    public static FtsTableInfo read(SupportSQLiteDatabase database, String tableName) {
        Set<String> columns = readColumns(database, tableName);
        Set<String> options = readOptions(database, tableName);
        return new FtsTableInfo(tableName, columns, options);
    }

    private static Set<String> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA table_info(`" + tableName + "`)");
        Set<String> columns = new HashSet<>();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex("name");
                while (cursor.moveToNext()) {
                    columns.add(cursor.getString(nameIndex));
                }
            }
            return columns;
        } finally {
            cursor.close();
        }
    }

    private static Set<String> readOptions(SupportSQLiteDatabase database, String tableName) {
        String sql = INetd.NEXTHOP_NONE;
        Cursor cursor = database.query("SELECT * FROM sqlite_master WHERE `name` = '" + tableName + "'");
        try {
            if (cursor.moveToFirst()) {
                sql = cursor.getString(cursor.getColumnIndexOrThrow("sql"));
            }
            cursor.close();
            return parseOptions(sql);
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.util.Set<java.lang.String> parseOptions(java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.FtsTableInfo.parseOptions(java.lang.String):java.util.Set");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FtsTableInfo)) {
            return false;
        }
        FtsTableInfo that = (FtsTableInfo) o;
        String str = this.name;
        if (str == null ? that.name != null : !str.equals(that.name)) {
            return false;
        }
        Set<String> set = this.columns;
        if (set == null ? that.columns != null : !set.equals(that.columns)) {
            return false;
        }
        Set<String> set2 = this.options;
        return set2 != null ? set2.equals(that.options) : that.options == null;
    }

    public int hashCode() {
        String str = this.name;
        int result = str != null ? str.hashCode() : 0;
        int i = result * 31;
        Set<String> set = this.columns;
        int result2 = i + (set != null ? set.hashCode() : 0);
        int result3 = result2 * 31;
        Set<String> set2 = this.options;
        return result3 + (set2 != null ? set2.hashCode() : 0);
    }

    public String toString() {
        return "FtsTableInfo{name='" + this.name + "', columns=" + this.columns + ", options=" + this.options + '}';
    }
}
