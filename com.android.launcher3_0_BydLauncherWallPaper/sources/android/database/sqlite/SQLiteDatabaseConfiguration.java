package android.database.sqlite;

import android.util.ArrayMap;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteDatabaseConfiguration {
    private static final Pattern EMAIL_IN_DB_PATTERN = Pattern.compile("[\\w\\.\\-]+@[\\w\\.\\-]+");
    public static final String MEMORY_DB_PATH = ":memory:";
    public boolean foreignKeyConstraintsEnabled;
    public String journalMode;
    public final String label;
    public Locale locale;
    public int maxSqlCacheSize;
    public int openFlags;
    public final String path;
    public String syncMode;
    public final ArrayMap<String, UnaryOperator<String>> customScalarFunctions = new ArrayMap<>();
    public final ArrayMap<String, BinaryOperator<String>> customAggregateFunctions = new ArrayMap<>();
    public final ArrayList<Pair<String, Object[]>> perConnectionSql = new ArrayList<>();
    public int lookasideSlotSize = -1;
    public int lookasideSlotCount = -1;
    public long idleConnectionTimeoutMs = Long.MAX_VALUE;

    public SQLiteDatabaseConfiguration(String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("path must not be null.");
        }
        this.path = str;
        this.label = stripPathForLogs(str);
        this.openFlags = i;
        this.maxSqlCacheSize = 25;
        this.locale = Locale.getDefault();
    }

    public SQLiteDatabaseConfiguration(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration) {
        if (sQLiteDatabaseConfiguration == null) {
            throw new IllegalArgumentException("other must not be null.");
        }
        this.path = sQLiteDatabaseConfiguration.path;
        this.label = sQLiteDatabaseConfiguration.label;
        updateParametersFrom(sQLiteDatabaseConfiguration);
    }

    public void updateParametersFrom(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration) {
        if (sQLiteDatabaseConfiguration == null) {
            throw new IllegalArgumentException("other must not be null.");
        }
        if (!this.path.equals(sQLiteDatabaseConfiguration.path)) {
            throw new IllegalArgumentException("other configuration must refer to the same database.");
        }
        this.openFlags = sQLiteDatabaseConfiguration.openFlags;
        this.maxSqlCacheSize = sQLiteDatabaseConfiguration.maxSqlCacheSize;
        this.locale = sQLiteDatabaseConfiguration.locale;
        this.foreignKeyConstraintsEnabled = sQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled;
        this.customScalarFunctions.clear();
        this.customScalarFunctions.putAll((ArrayMap<? extends String, ? extends UnaryOperator<String>>) sQLiteDatabaseConfiguration.customScalarFunctions);
        this.customAggregateFunctions.clear();
        this.customAggregateFunctions.putAll((ArrayMap<? extends String, ? extends BinaryOperator<String>>) sQLiteDatabaseConfiguration.customAggregateFunctions);
        this.perConnectionSql.clear();
        this.perConnectionSql.addAll(sQLiteDatabaseConfiguration.perConnectionSql);
        this.lookasideSlotSize = sQLiteDatabaseConfiguration.lookasideSlotSize;
        this.lookasideSlotCount = sQLiteDatabaseConfiguration.lookasideSlotCount;
        this.idleConnectionTimeoutMs = sQLiteDatabaseConfiguration.idleConnectionTimeoutMs;
        this.journalMode = sQLiteDatabaseConfiguration.journalMode;
        this.syncMode = sQLiteDatabaseConfiguration.syncMode;
    }

    public boolean isInMemoryDb() {
        return this.path.equalsIgnoreCase(MEMORY_DB_PATH);
    }

    boolean isLegacyCompatibilityWalEnabled() {
        return this.journalMode == null && this.syncMode == null && (this.openFlags & Integer.MIN_VALUE) != 0;
    }

    private static String stripPathForLogs(String str) {
        return str.indexOf(64) == -1 ? str : EMAIL_IN_DB_PATTERN.matcher(str).replaceAll("XX@YY");
    }

    boolean isLookasideConfigSet() {
        return this.lookasideSlotCount >= 0 && this.lookasideSlotSize >= 0;
    }
}
