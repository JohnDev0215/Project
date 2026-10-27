package com.android.providers.media.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.providers.media.DatabaseHelper;
import com.android.providers.media.R;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteQueryBuilder {
    private static final String GET_ID_FOR_INSERT_CLAUSE = "_GET_ID(?)";
    private static final String GET_ID_FOR_UPDATE_CLAUSE = "ifnull(_GET_ID(?), _id)";
    private static final int STRICT_COLUMNS = 2;
    private static final int STRICT_GRAMMAR = 4;
    private static final int STRICT_PARENTHESES = 1;
    private static final String TAG = "SQLiteQueryBuilder";
    private int mStrictFlags;
    private static final Pattern sAggregationPattern = Pattern.compile("(?i)(AVG|COUNT|MAX|MIN|SUM|TOTAL|GROUP_CONCAT|UNICODE)\\((.+)\\)");
    private static final Pattern sPattern154193772 = Pattern.compile("(?i)%\\.(wmv|wm|wtv|asf|hls|mp4|m4v|mov|mp4v|3g2|3gp|3gp2|3gpp|mj2|qt|external|mov|asf|avi|divx|mpg|mpeg|mkv|webm|mk3d|mks|3gp|mpegts|ts|m2ts|m2t)");
    private static final Pattern sPattern156832140 = Pattern.compile("(?i)%com\\.gopro\\.smarty%");
    private static final Pattern sPattern158537159 = Pattern.compile("(?i)localized");
    private static final Pattern sCustomCollatorPattern = Pattern.compile("(?i)custom_[a-zA-Z]+");
    private Map<String, String> mProjectionMap = null;
    private Collection<Pattern> mProjectionGreylist = null;
    private String mTables = "";
    private StringBuilder mWhereClause = null;
    private int mTargetSdkVersion = 10000;
    private boolean mDistinct = false;

    public void setTargetSdkVersion(int i) {
        this.mTargetSdkVersion = i;
    }

    public void setDistinct(boolean z) {
        this.mDistinct = z;
    }

    public boolean isDistinct() {
        return this.mDistinct;
    }

    public String getTables() {
        return this.mTables;
    }

    public void setTables(String str) {
        this.mTables = str;
    }

    public void appendWhere(CharSequence charSequence) {
        if (this.mWhereClause == null) {
            this.mWhereClause = new StringBuilder(charSequence.length() + 16);
        }
        this.mWhereClause.append(charSequence);
    }

    public void appendWhereEscapeString(String str) {
        if (this.mWhereClause == null) {
            this.mWhereClause = new StringBuilder(str.length() + 16);
        }
        android.database.DatabaseUtils.appendEscapedSQLString(this.mWhereClause, str);
    }

    public void appendWhereStandalone(CharSequence charSequence) {
        if (this.mWhereClause == null) {
            this.mWhereClause = new StringBuilder(charSequence.length() + 16);
        }
        if (this.mWhereClause.length() > 0) {
            this.mWhereClause.append(" AND ");
        }
        StringBuilder sb = this.mWhereClause;
        sb.append('(');
        sb.append(charSequence);
        sb.append(')');
    }

    public void setProjectionMap(Map<String, String> map) {
        if (map != null) {
            this.mProjectionMap = new ArrayMap();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.mProjectionMap.put(entry.getKey().toLowerCase(Locale.ROOT), entry.getValue());
            }
            return;
        }
        this.mProjectionMap = null;
    }

    public Map<String, String> getProjectionMap() {
        return this.mProjectionMap;
    }

    public void setProjectionGreylist(Collection<Pattern> collection) {
        this.mProjectionGreylist = collection;
    }

    public Collection<Pattern> getProjectionGreylist() {
        return this.mProjectionGreylist;
    }

    public void setStrict(boolean z) {
        if (z) {
            this.mStrictFlags |= 1;
        } else {
            this.mStrictFlags &= -2;
        }
    }

    public boolean isStrict() {
        return (this.mStrictFlags & 1) != 0;
    }

    public void setStrictColumns(boolean z) {
        if (z) {
            this.mStrictFlags |= 2;
        } else {
            this.mStrictFlags &= -3;
        }
    }

    public boolean isStrictColumns() {
        return (this.mStrictFlags & 2) != 0;
    }

    public void setStrictGrammar(boolean z) {
        if (z) {
            this.mStrictFlags |= 4;
        } else {
            this.mStrictFlags &= -5;
        }
    }

    public boolean isStrictGrammar() {
        return (this.mStrictFlags & 4) != 0;
    }

    public static String buildQueryString(boolean z, String str, String[] strArr, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
        }
        StringBuilder sb = new StringBuilder(R.styleable.AppCompatTheme_windowFixedHeightMajor);
        sb.append("SELECT ");
        if (z) {
            sb.append("DISTINCT ");
        }
        if (strArr != null && strArr.length != 0) {
            appendColumns(sb, strArr);
        } else {
            sb.append("* ");
        }
        sb.append("FROM ");
        sb.append(str);
        appendClause(sb, " WHERE ", str2);
        appendClause(sb, " GROUP BY ", str3);
        appendClause(sb, " HAVING ", str4);
        appendClause(sb, " ORDER BY ", str5);
        appendClause(sb, " LIMIT ", str6);
        return sb.toString();
    }

    private static void appendClause(StringBuilder sb, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        sb.append(str);
        sb.append(str2);
    }

    public static void appendColumns(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (str != null) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(str);
            }
        }
        sb.append(' ');
    }

    public Cursor query(DatabaseHelper databaseHelper, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) {
        return query(databaseHelper, strArr, bundle.getString("android:query-arg-sql-selection"), bundle.getStringArray("android:query-arg-sql-selection-args"), bundle.getString("android:query-arg-sql-group-by"), bundle.getString("android:query-arg-sql-having"), bundle.getString("android:query-arg-sql-sort-order"), bundle.getString("android:query-arg-sql-limit"), cancellationSignal);
    }

    public Cursor query(DatabaseHelper databaseHelper, final String[] strArr, final String str, final String[] strArr2, final String str2, final String str3, final String str4, final String str5, final CancellationSignal cancellationSignal) {
        return (Cursor) databaseHelper.runWithoutTransaction(new Function() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$DnpaEqEZAnzgRrePY7MIAaGu0sI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$query$0$SQLiteQueryBuilder(strArr, str, strArr2, str2, str3, str4, str5, cancellationSignal, (SQLiteDatabase) obj);
            }
        });
    }

    /* JADX INFO: renamed from: query, reason: merged with bridge method [inline-methods] */
    public Cursor lambda$query$0$SQLiteQueryBuilder(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5, CancellationSignal cancellationSignal) {
        if (this.mTables == null) {
            return null;
        }
        String strBuildQuery = buildQuery(strArr, str, str2, str3, str4, str5);
        if (isStrictColumns()) {
            enforceStrictColumns(strArr);
        }
        if (isStrictGrammar()) {
            enforceStrictGrammar(str, str2, str3, str4, str5);
        }
        if (isStrict()) {
            sQLiteDatabase.validateSql(strBuildQuery, cancellationSignal);
            strBuildQuery = buildQuery(strArr, wrap(str), str2, wrap(str3), str4, str5);
        }
        if (Log.isLoggable(TAG, 3)) {
            if (Logging.IS_DEBUGGABLE) {
                Log.d(TAG, strBuildQuery + " with args " + Arrays.toString(strArr2));
            } else {
                Log.d(TAG, strBuildQuery);
            }
        }
        return sQLiteDatabase.rawQueryWithFactory(null, strBuildQuery, strArr2, SQLiteDatabase.findEditTable(this.mTables), cancellationSignal);
    }

    public long insert(DatabaseHelper databaseHelper, final ContentValues contentValues) {
        return ((Long) databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$YklJ51_aF7j8568p4VYgJs-CWNI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$insert$1$SQLiteQueryBuilder(contentValues, (SQLiteDatabase) obj);
            }
        })).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$insert$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Long lambda$insert$1$SQLiteQueryBuilder(ContentValues contentValues, SQLiteDatabase sQLiteDatabase) {
        return Long.valueOf(insert(sQLiteDatabase, contentValues));
    }

    public long insert(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
        Objects.requireNonNull(this.mTables, "No tables defined");
        Objects.requireNonNull(sQLiteDatabase, "No database defined");
        Objects.requireNonNull(contentValues, "No values defined");
        if (isStrictColumns()) {
            enforceStrictColumns(contentValues);
        }
        String strBuildInsert = buildInsert(contentValues);
        ArrayMap<String, Object> values = DatabaseUtils.getValues(contentValues);
        int size = values.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = values.valueAt(i);
        }
        if (Log.isLoggable(TAG, 3)) {
            if (Logging.IS_DEBUGGABLE) {
                Log.d(TAG, strBuildInsert + " with args " + Arrays.toString(objArr));
            } else {
                Log.d(TAG, strBuildInsert);
            }
        }
        return DatabaseUtils.executeInsert(sQLiteDatabase, strBuildInsert, objArr);
    }

    public int update(DatabaseHelper databaseHelper, final ContentValues contentValues, final String str, final String[] strArr) {
        return ((Integer) databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$AgEfPRRGX0oz5jjY6qV0HE57T3E
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$update$2$SQLiteQueryBuilder(contentValues, str, strArr, (SQLiteDatabase) obj);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$update$2$SQLiteQueryBuilder(ContentValues contentValues, String str, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        return Integer.valueOf(update(sQLiteDatabase, contentValues, str, strArr));
    }

    public int update(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, String str, String[] strArr) {
        Objects.requireNonNull(this.mTables, "No tables defined");
        Objects.requireNonNull(sQLiteDatabase, "No database defined");
        Objects.requireNonNull(contentValues, "No values defined");
        String strBuildUpdate = buildUpdate(contentValues, str);
        if (isStrictColumns()) {
            enforceStrictColumns(contentValues);
        }
        if (isStrictGrammar()) {
            enforceStrictGrammar(str, null, null, null, null);
        }
        if (isStrict()) {
            sQLiteDatabase.validateSql(strBuildUpdate, null);
            strBuildUpdate = buildUpdate(contentValues, wrap(str));
        }
        if (strArr == null) {
            strArr = new String[0];
        }
        ArrayMap<String, Object> values = DatabaseUtils.getValues(contentValues);
        int size = values.size();
        int length = strArr.length + size;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            if (i < size) {
                objArr[i] = values.valueAt(i);
            } else {
                objArr[i] = strArr[i - size];
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            if (Logging.IS_DEBUGGABLE) {
                Log.d(TAG, strBuildUpdate + " with args " + Arrays.toString(objArr));
            } else {
                Log.d(TAG, strBuildUpdate);
            }
        }
        return DatabaseUtils.executeUpdateDelete(sQLiteDatabase, strBuildUpdate, objArr);
    }

    public int delete(DatabaseHelper databaseHelper, final String str, final String[] strArr) {
        return ((Integer) databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$TqpmRZBeTEhfLyRQyEyDlFbBtxU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$delete$3$SQLiteQueryBuilder(str, strArr, (SQLiteDatabase) obj);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$delete$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$delete$3$SQLiteQueryBuilder(String str, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        return Integer.valueOf(delete(sQLiteDatabase, str, strArr));
    }

    public int delete(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        Objects.requireNonNull(this.mTables, "No tables defined");
        Objects.requireNonNull(sQLiteDatabase, "No database defined");
        String strBuildDelete = buildDelete(str);
        if (isStrictGrammar()) {
            enforceStrictGrammar(str, null, null, null, null);
        }
        if (isStrict()) {
            sQLiteDatabase.validateSql(strBuildDelete, null);
            strBuildDelete = buildDelete(wrap(str));
        }
        if (Log.isLoggable(TAG, 3)) {
            if (Logging.IS_DEBUGGABLE) {
                Log.d(TAG, strBuildDelete + " with args " + Arrays.toString(strArr));
            } else {
                Log.d(TAG, strBuildDelete);
            }
        }
        return DatabaseUtils.executeUpdateDelete(sQLiteDatabase, strBuildDelete, strArr);
    }

    private void enforceStrictColumns(String[] strArr) {
        Objects.requireNonNull(this.mProjectionMap, "No projection map defined");
        computeProjection(strArr);
    }

    private void enforceStrictColumns(ContentValues contentValues) {
        Objects.requireNonNull(this.mProjectionMap, "No projection map defined");
        ArrayMap<String, Object> values = DatabaseUtils.getValues(contentValues);
        for (int i = 0; i < values.size(); i++) {
            String strKeyAt = values.keyAt(i);
            if (!this.mProjectionMap.containsKey(strKeyAt)) {
                throw new IllegalArgumentException("Invalid column " + strKeyAt);
            }
        }
    }

    void enforceStrictGrammar(String str, String str2, String str3, String str4, String str5) {
        SQLiteTokenizer.tokenize(str, 0, new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$A_5pVV7R531pc2nfQKIgdK6wVX8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str2, 0, new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$A_5pVV7R531pc2nfQKIgdK6wVX8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str3, 0, new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$A_5pVV7R531pc2nfQKIgdK6wVX8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str4, 0, new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$A_5pVV7R531pc2nfQKIgdK6wVX8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str5, 0, new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$SQLiteQueryBuilder$A_5pVV7R531pc2nfQKIgdK6wVX8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceStrictToken(String str) {
        boolean zIsKeyword;
        if (TextUtils.isEmpty(str) || isTableOrColumn(str) || isCustomCollator(str) || SQLiteTokenizer.isFunction(str) || SQLiteTokenizer.isType(str)) {
            return;
        }
        zIsKeyword = SQLiteTokenizer.isKeyword(str);
        switch (str.toUpperCase(Locale.ROOT)) {
            case "SELECT":
            case "FROM":
            case "WHERE":
            case "GROUP":
            case "HAVING":
            case "WINDOW":
            case "VALUES":
            case "ORDER":
            case "LIMIT":
                zIsKeyword = false;
                break;
        }
        if (zIsKeyword) {
            return;
        }
        if (this.mTargetSdkVersion >= 30 || !(sPattern154193772.matcher(str).matches() || sPattern156832140.matcher(str).matches() || sPattern158537159.matcher(str).matches())) {
            throw new IllegalArgumentException("Invalid token " + str);
        }
    }

    public String buildQuery(String[] strArr, String str, String str2, String str3, String str4, String str5) {
        return buildQueryString(this.mDistinct, this.mTables, computeProjection(strArr), computeWhere(str), str2, str3, str4, str5);
    }

    public String buildInsert(ContentValues contentValues) {
        if (contentValues == null || contentValues.isEmpty()) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sb = new StringBuilder(R.styleable.AppCompatTheme_windowFixedHeightMajor);
        sb.append("INSERT INTO ");
        sb.append(SQLiteDatabase.findEditTable(this.mTables));
        sb.append(" (");
        boolean zEquals = Objects.equals(this.mTables, "files");
        if (zEquals) {
            contentValues.remove("generation_added");
            contentValues.remove("generation_modified");
        }
        ArrayMap<String, Object> values = DatabaseUtils.getValues(contentValues);
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(values.keyAt(i));
        }
        if (zEquals) {
            sb.append(',');
            sb.append("generation_added");
            sb.append(',');
            sb.append("generation_modified");
        }
        if (shouldAppendRowId(contentValues)) {
            sb.append(',');
            sb.append("_id");
        }
        sb.append(") VALUES (");
        for (int i2 = 0; i2 < values.size(); i2++) {
            if (i2 > 0) {
                sb.append(',');
            }
            sb.append('?');
        }
        if (zEquals) {
            sb.append(',');
            sb.append('(');
            sb.append(DatabaseHelper.CURRENT_GENERATION_CLAUSE);
            sb.append(')');
            sb.append(',');
            sb.append('(');
            sb.append(DatabaseHelper.CURRENT_GENERATION_CLAUSE);
            sb.append(')');
        }
        if (shouldAppendRowId(contentValues)) {
            sb.append(',');
            sb.append(DatabaseUtils.bindSelection(GET_ID_FOR_INSERT_CLAUSE, contentValues.getAsString("_data")));
        }
        sb.append(")");
        return sb.toString();
    }

    public String buildUpdate(ContentValues contentValues, String str) {
        if (contentValues == null || contentValues.isEmpty()) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sb = new StringBuilder(R.styleable.AppCompatTheme_windowFixedHeightMajor);
        sb.append("UPDATE ");
        sb.append(SQLiteDatabase.findEditTable(this.mTables));
        sb.append(" SET ");
        boolean zEquals = Objects.equals(this.mTables, "files");
        if (zEquals) {
            contentValues.remove("generation_added");
            contentValues.remove("generation_modified");
        }
        ArrayMap<String, Object> values = DatabaseUtils.getValues(contentValues);
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(values.keyAt(i));
            sb.append("=?");
        }
        if (zEquals) {
            sb.append(',');
            sb.append("generation_modified");
            sb.append('=');
            sb.append('(');
            sb.append(DatabaseHelper.CURRENT_GENERATION_CLAUSE);
            sb.append(')');
        }
        if (shouldAppendRowId(contentValues)) {
            sb.append(',');
            sb.append("_id");
            sb.append('=');
            sb.append(DatabaseUtils.bindSelection(GET_ID_FOR_UPDATE_CLAUSE, contentValues.getAsString("_data")));
        }
        appendClause(sb, " WHERE ", computeWhere(str));
        return sb.toString();
    }

    public String buildDelete(String str) {
        StringBuilder sb = new StringBuilder(R.styleable.AppCompatTheme_windowFixedHeightMajor);
        sb.append("DELETE FROM ");
        sb.append(SQLiteDatabase.findEditTable(this.mTables));
        appendClause(sb, " WHERE ", computeWhere(str));
        return sb.toString();
    }

    private static String maybeWithOperator(String str, String str2) {
        if (str == null) {
            return str2;
        }
        return str + "(" + str2 + ")";
    }

    public String[] computeProjection(String[] strArr) {
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            String[] strArr2 = new String[strArr.length];
            while (i < strArr.length) {
                strArr2[i] = computeSingleProjectionOrThrow(strArr[i]);
                i++;
            }
            return strArr2;
        }
        Map<String, String> map = this.mProjectionMap;
        if (map == null) {
            return null;
        }
        Set<Map.Entry<String, String>> setEntrySet = map.entrySet();
        String[] strArr3 = new String[setEntrySet.size()];
        for (Map.Entry<String, String> entry : setEntrySet) {
            if (!entry.getKey().equals("_count")) {
                strArr3[i] = entry.getValue();
                i++;
            }
        }
        return strArr3;
    }

    private String computeSingleProjectionOrThrow(String str) {
        String strComputeSingleProjection = computeSingleProjection(str);
        if (strComputeSingleProjection != null) {
            return strComputeSingleProjection;
        }
        throw new IllegalArgumentException("Invalid column " + str);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String computeSingleProjection(java.lang.String r6) {
        /*
            r5 = this;
            java.util.Map<java.lang.String, java.lang.String> r0 = r5.mProjectionMap
            if (r0 != 0) goto L5
            return r6
        L5:
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r1 = r6.toLowerCase(r1)
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L39
            java.util.regex.Pattern r3 = com.android.providers.media.util.SQLiteQueryBuilder.sAggregationPattern
            java.util.regex.Matcher r3 = r3.matcher(r6)
            boolean r4 = r3.matches()
            if (r4 == 0) goto L39
            java.lang.String r6 = r3.group(r1)
            r0 = 2
            java.lang.String r0 = r3.group(r0)
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.mProjectionMap
            java.util.Locale r4 = java.util.Locale.ROOT
            java.lang.String r4 = r0.toLowerCase(r4)
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            goto L3c
        L39:
            r3 = r0
            r0 = r6
            r6 = r2
        L3c:
            if (r3 == 0) goto L43
            java.lang.String r5 = maybeWithOperator(r6, r3)
            return r5
        L43:
            int r3 = r5.mStrictFlags
            if (r3 != 0) goto L5c
            java.lang.String r3 = " AS "
            boolean r3 = r0.contains(r3)
            if (r3 != 0) goto L57
            java.lang.String r3 = " as "
            boolean r3 = r0.contains(r3)
            if (r3 == 0) goto L5c
        L57:
            java.lang.String r5 = maybeWithOperator(r6, r0)
            return r5
        L5c:
            java.util.Collection<java.util.regex.Pattern> r5 = r5.mProjectionGreylist
            if (r5 == 0) goto L9a
            r3 = 0
            java.util.Iterator r5 = r5.iterator()
        L65:
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L7c
            java.lang.Object r4 = r5.next()
            java.util.regex.Pattern r4 = (java.util.regex.Pattern) r4
            java.util.regex.Matcher r4 = r4.matcher(r0)
            boolean r4 = r4.matches()
            if (r4 == 0) goto L65
            goto L7d
        L7c:
            r1 = r3
        L7d:
            if (r1 == 0) goto L9a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "Allowing abusive custom column: "
            r5.append(r1)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            java.lang.String r1 = "SQLiteQueryBuilder"
            android.util.Log.w(r1, r5)
            java.lang.String r5 = maybeWithOperator(r6, r0)
            return r5
        L9a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.SQLiteQueryBuilder.computeSingleProjection(java.lang.String):java.lang.String");
    }

    private boolean isTableOrColumn(String str) {
        return this.mTables.equals(str) || computeSingleProjection(str) != null;
    }

    private boolean isCustomCollator(String str) {
        return sCustomCollatorPattern.matcher(str).matches();
    }

    public String computeWhere(String str) {
        boolean z = !TextUtils.isEmpty(this.mWhereClause);
        boolean z2 = !TextUtils.isEmpty(str);
        if (!z && !z2) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append('(');
            sb.append((CharSequence) this.mWhereClause);
            sb.append(')');
        }
        if (z && z2) {
            sb.append(" AND ");
        }
        if (z2) {
            sb.append('(');
            sb.append(str);
            sb.append(')');
        }
        return sb.toString();
    }

    private String wrap(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return "(" + str + ")";
    }

    private static boolean shouldAppendRowId(ContentValues contentValues) {
        return !contentValues.containsKey("_id") && contentValues.containsKey("_data");
    }
}
