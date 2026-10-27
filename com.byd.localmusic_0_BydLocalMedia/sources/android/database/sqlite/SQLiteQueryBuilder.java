package android.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.CancellationSignal;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteQueryBuilder {
    private static final int STRICT_COLUMNS = 2;
    private static final int STRICT_GRAMMAR = 4;
    private static final int STRICT_PARENTHESES = 1;
    private static final String TAG = "SQLiteQueryBuilder";
    private static final Pattern sAggregationPattern = Pattern.compile("(?i)(AVG|COUNT|MAX|MIN|SUM|TOTAL|GROUP_CONCAT)\\((.+)\\)");
    private int mStrictFlags;
    private Map<String, String> mProjectionMap = null;
    private Collection<Pattern> mProjectionGreylist = null;
    private String mTables = "";
    private StringBuilder mWhereClause = null;
    private boolean mDistinct = false;
    private SQLiteDatabase.CursorFactory mFactory = null;

    @Deprecated
    public boolean isProjectionAggregationAllowed() {
        return true;
    }

    @Deprecated
    public void setProjectionAggregationAllowed(boolean z) {
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
        DatabaseUtils.appendEscapedSQLString(this.mWhereClause, str);
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
        this.mProjectionMap = map;
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

    public void setCursorFactory(SQLiteDatabase.CursorFactory cursorFactory) {
        this.mFactory = cursorFactory;
    }

    public SQLiteDatabase.CursorFactory getCursorFactory() {
        return this.mFactory;
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
        StringBuilder sb = new StringBuilder(120);
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

    public Cursor query(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return query(sQLiteDatabase, strArr, str, strArr2, str2, str3, str4, null, null);
    }

    public Cursor query(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return query(sQLiteDatabase, strArr, str, strArr2, str2, str3, str4, str5, null);
    }

    public Cursor query(SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5, CancellationSignal cancellationSignal) {
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
        String str6 = strBuildQuery;
        if (Log.isLoggable(TAG, 3)) {
            if (Build.IS_DEBUGGABLE) {
                Log.d(TAG, str6 + " with args " + Arrays.toString(strArr2));
            } else {
                Log.d(TAG, str6);
            }
        }
        return sQLiteDatabase.rawQueryWithFactory(this.mFactory, str6, strArr2, SQLiteDatabase.findEditTable(this.mTables), cancellationSignal);
    }

    public long insert(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
        Objects.requireNonNull(this.mTables, "No tables defined");
        Objects.requireNonNull(sQLiteDatabase, "No database defined");
        Objects.requireNonNull(contentValues, "No values defined");
        if (isStrictColumns()) {
            enforceStrictColumns(contentValues);
        }
        String strBuildInsert = buildInsert(contentValues);
        ArrayMap<String, Object> values = contentValues.getValues();
        int size = values.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = values.valueAt(i);
        }
        if (Log.isLoggable(TAG, 3)) {
            if (Build.IS_DEBUGGABLE) {
                Log.d(TAG, strBuildInsert + " with args " + Arrays.toString(objArr));
            } else {
                Log.d(TAG, strBuildInsert);
            }
        }
        return DatabaseUtils.executeInsert(sQLiteDatabase, strBuildInsert, objArr);
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
            strArr = EmptyArray.STRING;
        }
        ArrayMap<String, Object> values = contentValues.getValues();
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
            if (Build.IS_DEBUGGABLE) {
                Log.d(TAG, strBuildUpdate + " with args " + Arrays.toString(objArr));
            } else {
                Log.d(TAG, strBuildUpdate);
            }
        }
        return DatabaseUtils.executeUpdateDelete(sQLiteDatabase, strBuildUpdate, objArr);
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
            if (Build.IS_DEBUGGABLE) {
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
        ArrayMap<String, Object> values = contentValues.getValues();
        for (int i = 0; i < values.size(); i++) {
            String strKeyAt = values.keyAt(i);
            if (!this.mProjectionMap.containsKey(strKeyAt)) {
                throw new IllegalArgumentException("Invalid column " + strKeyAt);
            }
        }
    }

    private void enforceStrictGrammar(String str, String str2, String str3, String str4, String str5) {
        SQLiteTokenizer.tokenize(str, 0, new Consumer() { // from class: android.database.sqlite.-$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str2, 0, new Consumer() { // from class: android.database.sqlite.-$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str3, 0, new Consumer() { // from class: android.database.sqlite.-$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str4, 0, new Consumer() { // from class: android.database.sqlite.-$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str5, 0, new Consumer() { // from class: android.database.sqlite.-$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.enforceStrictToken((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceStrictToken(String str) {
        boolean zIsKeyword;
        if (TextUtils.isEmpty(str) || isTableOrColumn(str) || SQLiteTokenizer.isFunction(str) || SQLiteTokenizer.isType(str)) {
            return;
        }
        zIsKeyword = SQLiteTokenizer.isKeyword(str);
        switch (str.toUpperCase(Locale.US)) {
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
        throw new IllegalArgumentException("Invalid token " + str);
    }

    public String buildQuery(String[] strArr, String str, String str2, String str3, String str4, String str5) {
        return buildQueryString(this.mDistinct, this.mTables, computeProjection(strArr), computeWhere(str), str2, str3, str4, str5);
    }

    @Deprecated
    public String buildQuery(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return buildQuery(strArr, str, str2, str3, str4, str5);
    }

    public String buildInsert(ContentValues contentValues) {
        if (contentValues == null || contentValues.isEmpty()) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sb = new StringBuilder(120);
        sb.append("INSERT INTO ");
        sb.append(SQLiteDatabase.findEditTable(this.mTables));
        sb.append(" (");
        ArrayMap<String, Object> values = contentValues.getValues();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(values.keyAt(i));
        }
        sb.append(") VALUES (");
        for (int i2 = 0; i2 < values.size(); i2++) {
            if (i2 > 0) {
                sb.append(',');
            }
            sb.append('?');
        }
        sb.append(")");
        return sb.toString();
    }

    public String buildUpdate(ContentValues contentValues, String str) {
        if (contentValues == null || contentValues.isEmpty()) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sb = new StringBuilder(120);
        sb.append("UPDATE ");
        sb.append(SQLiteDatabase.findEditTable(this.mTables));
        sb.append(" SET ");
        ArrayMap<String, Object> values = contentValues.getValues();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(values.keyAt(i));
            sb.append("=?");
        }
        appendClause(sb, " WHERE ", computeWhere(str));
        return sb.toString();
    }

    public String buildDelete(String str) {
        StringBuilder sb = new StringBuilder(120);
        sb.append("DELETE FROM ");
        sb.append(SQLiteDatabase.findEditTable(this.mTables));
        appendClause(sb, " WHERE ", computeWhere(str));
        return sb.toString();
    }

    public String buildUnionSubQuery(String str, String[] strArr, Set<String> set, int i, String str2, String str3, String str4, String str5) {
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String str6 = strArr[i2];
            if (str6.equals(str)) {
                strArr2[i2] = "'" + str2 + "' AS " + str;
            } else if (i2 <= i || set.contains(str6)) {
                strArr2[i2] = str6;
            } else {
                strArr2[i2] = "NULL AS " + str6;
            }
        }
        return buildQuery(strArr2, str3, str4, str5, null, null);
    }

    @Deprecated
    public String buildUnionSubQuery(String str, String[] strArr, Set<String> set, int i, String str2, String str3, String[] strArr2, String str4, String str5) {
        return buildUnionSubQuery(str, strArr, set, i, str2, str3, str4, str5);
    }

    public String buildUnionQuery(String[] strArr, String str, String str2) {
        StringBuilder sb = new StringBuilder(128);
        int length = strArr.length;
        String str3 = this.mDistinct ? " UNION " : " UNION ALL ";
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(str3);
            }
            sb.append(strArr[i]);
        }
        appendClause(sb, " ORDER BY ", str);
        appendClause(sb, " LIMIT ", str2);
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
        if (!ArrayUtils.isEmpty(strArr)) {
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
            if (!entry.getKey().equals(BaseColumns._COUNT)) {
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

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String computeSingleProjection(java.lang.String r7) {
        /*
            r6 = this;
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.mProjectionMap
            if (r0 != 0) goto L5
            return r7
        L5:
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L2d
            java.util.regex.Pattern r3 = android.database.sqlite.SQLiteQueryBuilder.sAggregationPattern
            java.util.regex.Matcher r3 = r3.matcher(r7)
            boolean r4 = r3.matches()
            if (r4 == 0) goto L2d
            java.lang.String r7 = r3.group(r1)
            r0 = 2
            java.lang.String r0 = r3.group(r0)
            java.util.Map<java.lang.String, java.lang.String> r3 = r6.mProjectionMap
            java.lang.Object r3 = r3.get(r0)
            java.lang.String r3 = (java.lang.String) r3
            goto L30
        L2d:
            r3 = r0
            r0 = r7
            r7 = r2
        L30:
            if (r3 == 0) goto L37
            java.lang.String r7 = maybeWithOperator(r7, r3)
            return r7
        L37:
            int r3 = r6.mStrictFlags
            if (r3 != 0) goto L50
            java.lang.String r3 = " AS "
            boolean r3 = r0.contains(r3)
            if (r3 != 0) goto L4b
            java.lang.String r3 = " as "
            boolean r3 = r0.contains(r3)
            if (r3 == 0) goto L50
        L4b:
            java.lang.String r7 = maybeWithOperator(r7, r0)
            return r7
        L50:
            java.util.Collection<java.util.regex.Pattern> r3 = r6.mProjectionGreylist
            if (r3 == 0) goto L8e
            r4 = 0
            java.util.Iterator r3 = r3.iterator()
        L59:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L70
            java.lang.Object r5 = r3.next()
            java.util.regex.Pattern r5 = (java.util.regex.Pattern) r5
            java.util.regex.Matcher r5 = r5.matcher(r0)
            boolean r5 = r5.matches()
            if (r5 == 0) goto L59
            goto L71
        L70:
            r1 = r4
        L71:
            if (r1 == 0) goto L8e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Allowing abusive custom column: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "SQLiteQueryBuilder"
            android.util.Log.w(r2, r1)
            java.lang.String r7 = maybeWithOperator(r7, r0)
            return r7
        L8e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.database.sqlite.SQLiteQueryBuilder.computeSingleProjection(java.lang.String):java.lang.String");
    }

    private boolean isTableOrColumn(String str) {
        return this.mTables.equals(str) || computeSingleProjection(str) != null;
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
}
