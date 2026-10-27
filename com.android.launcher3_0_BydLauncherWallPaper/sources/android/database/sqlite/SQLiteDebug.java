package android.database.sqlite;

import android.os.Build;
import android.os.Process;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Printer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteDebug {

    public static class PagerStats {
        public ArrayList<DbStats> dbStats;
        public int largestMemAlloc;
        public int memoryUsed;
        public int pageCacheOverflow;
    }

    private static native void nativeGetPagerStats(PagerStats pagerStats);

    public static final class NoPreloadHolder {
        public static final boolean DEBUG_LOG_DETAILED;
        private static final String SLOW_QUERY_THRESHOLD_PROP = "db.log.slow_query_threshold";
        public static final boolean DEBUG_SQL_LOG = Log.isLoggable("SQLiteLog", 2);
        public static final boolean DEBUG_SQL_STATEMENTS = Log.isLoggable("SQLiteStatements", 2);
        public static final boolean DEBUG_SQL_TIME = Log.isLoggable("SQLiteTime", 2);
        public static final boolean DEBUG_LOG_SLOW_QUERIES = Build.IS_DEBUGGABLE;
        private static final String SLOW_QUERY_THRESHOLD_UID_PROP = "db.log.slow_query_threshold." + Process.myUid();

        static {
            boolean z = false;
            if (Build.IS_DEBUGGABLE && SystemProperties.getBoolean("db.log.detailed", false)) {
                z = true;
            }
            DEBUG_LOG_DETAILED = z;
        }
    }

    private SQLiteDebug() {
    }

    public static boolean shouldLogSlowQuery(long j) {
        return j >= ((long) Math.min(SystemProperties.getInt("db.log.slow_query_threshold", Integer.MAX_VALUE), SystemProperties.getInt(NoPreloadHolder.SLOW_QUERY_THRESHOLD_UID_PROP, Integer.MAX_VALUE)));
    }

    public static class DbStats {
        public String cache;
        public String dbName;
        public long dbSize;
        public int lookaside;
        public long pageSize;

        public DbStats(String str, long j, long j2, int i, int i2, int i3, int i4) {
            this.dbName = str;
            this.pageSize = j2 / 1024;
            this.dbSize = (j * j2) / 1024;
            this.lookaside = i;
            this.cache = i2 + "/" + i3 + "/" + i4;
        }
    }

    public static PagerStats getDatabaseInfo() {
        PagerStats pagerStats = new PagerStats();
        nativeGetPagerStats(pagerStats);
        pagerStats.dbStats = SQLiteDatabase.getDbStats();
        return pagerStats;
    }

    public static void dump(Printer printer, String[] strArr) {
        dump(printer, strArr, false);
    }

    public static void dump(Printer printer, String[] strArr, boolean z) {
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equals("-v")) {
                z2 = true;
            }
        }
        SQLiteDatabase.dumpAll(printer, z2, z);
    }
}
