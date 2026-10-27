package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.query.QuerySortOrder;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import com.amplifyframework.core.model.query.predicate.QueryPredicateGroup;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public enum SqlKeyword {
    DELIMITER(" "),
    SEPARATOR(", "),
    VARIABLE("?"),
    EQUAL("="),
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    GREATER_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_OR_EQUAL("<="),
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    BETWEEN("BETWEEN"),
    IN("IN"),
    LIKE("LIKE"),
    EXISTS("EXISTS"),
    SELECT("SELECT"),
    FROM("FROM"),
    WHERE("WHERE"),
    JOIN("JOIN"),
    INNER_JOIN("INNER JOIN"),
    OUTER_JOIN("OUTER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    ON("ON"),
    AS("AS"),
    OFFSET("OFFSET"),
    LIMIT("LIMIT"),
    ORDER_BY("ORDER BY"),
    ASC("ASC"),
    DESC("DESC"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL");

    private static final Map<QueryOperator.Type, SqlKeyword> QUERY_OPERATOR_TO_SQL;
    private static final Map<QueryPredicateGroup.Type, SqlKeyword> QUERY_PREDICATE_GROUP_TO_SQL;
    private static final Map<QuerySortOrder, SqlKeyword> QUERY_SORT_BY_TO_SQL;
    private final String stringValue;

    static {
        SqlKeyword sqlKeyword = EQUAL;
        SqlKeyword sqlKeyword2 = NOT_EQUAL;
        SqlKeyword sqlKeyword3 = GREATER_THAN;
        SqlKeyword sqlKeyword4 = GREATER_OR_EQUAL;
        SqlKeyword sqlKeyword5 = LESS_THAN;
        SqlKeyword sqlKeyword6 = LESS_OR_EQUAL;
        SqlKeyword sqlKeyword7 = AND;
        SqlKeyword sqlKeyword8 = OR;
        SqlKeyword sqlKeyword9 = NOT;
        SqlKeyword sqlKeyword10 = ASC;
        SqlKeyword sqlKeyword11 = DESC;
        HashMap map = new HashMap();
        QUERY_OPERATOR_TO_SQL = map;
        HashMap map2 = new HashMap();
        QUERY_PREDICATE_GROUP_TO_SQL = map2;
        HashMap map3 = new HashMap();
        QUERY_SORT_BY_TO_SQL = map3;
        map.put(QueryOperator.Type.EQUAL, sqlKeyword);
        map.put(QueryOperator.Type.NOT_EQUAL, sqlKeyword2);
        map.put(QueryOperator.Type.GREATER_THAN, sqlKeyword3);
        map.put(QueryOperator.Type.GREATER_OR_EQUAL, sqlKeyword4);
        map.put(QueryOperator.Type.LESS_THAN, sqlKeyword5);
        map.put(QueryOperator.Type.LESS_OR_EQUAL, sqlKeyword6);
        map2.put(QueryPredicateGroup.Type.AND, sqlKeyword7);
        map2.put(QueryPredicateGroup.Type.OR, sqlKeyword8);
        map2.put(QueryPredicateGroup.Type.NOT, sqlKeyword9);
        map3.put(QuerySortOrder.ASCENDING, sqlKeyword10);
        map3.put(QuerySortOrder.DESCENDING, sqlKeyword11);
    }

    SqlKeyword(String str) {
        this.stringValue = str;
    }

    @NonNull
    public static SqlKeyword fromQueryOperator(@NonNull QueryOperator.Type type) {
        Map<QueryOperator.Type, SqlKeyword> map = QUERY_OPERATOR_TO_SQL;
        Objects.requireNonNull(type);
        SqlKeyword sqlKeyword = map.get(type);
        if (sqlKeyword != null) {
            return sqlKeyword;
        }
        throw new IllegalArgumentException("No SQL keyword mapping defined for query operator type = " + type.toString());
    }

    @NonNull
    public static SqlKeyword fromQueryPredicateGroup(@NonNull QueryPredicateGroup.Type type) {
        Map<QueryPredicateGroup.Type, SqlKeyword> map = QUERY_PREDICATE_GROUP_TO_SQL;
        Objects.requireNonNull(type);
        SqlKeyword sqlKeyword = map.get(type);
        if (sqlKeyword != null) {
            return sqlKeyword;
        }
        throw new IllegalArgumentException("No SQL keyword mapping defined for query predicate group type = " + type.toString());
    }

    @NonNull
    public static SqlKeyword fromQuerySortOrder(@NonNull QuerySortOrder querySortOrder) {
        Map<QuerySortOrder, SqlKeyword> map = QUERY_SORT_BY_TO_SQL;
        Objects.requireNonNull(querySortOrder);
        SqlKeyword sqlKeyword = map.get(querySortOrder);
        if (sqlKeyword != null) {
            return sqlKeyword;
        }
        throw new IllegalArgumentException("No SQL keyword mapping defined for query sort order type = " + querySortOrder.toString());
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.stringValue;
    }
}
