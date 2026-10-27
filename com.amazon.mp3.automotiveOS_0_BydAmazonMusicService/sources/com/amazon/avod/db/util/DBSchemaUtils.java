package com.amazon.avod.db.util;

import com.amazon.avod.db.DBConstraint;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DBSchemaUtils {
    private static final Function<Map.Entry<String, String>, String> TABLE_COLUMNS_FUNCTION = new TableColumnsFunction();

    public static String createIndexStatement(String str, String str2, String str3) {
        return "CREATE INDEX " + str2 + " ON " + str + str3;
    }

    public static String createTableStatement(String str, Map<String, String> map) {
        return createTableStatement(str, map, ImmutableList.of());
    }

    public static String createTableStatement(@Nonnull String str, @Nonnull Map<String, String> map, @Nonnull ImmutableList<DBConstraint> immutableList) {
        Preconditions.checkNotNull(str, "tableName");
        Preconditions.checkNotNull(map, "tableColumns");
        Preconditions.checkNotNull(immutableList, "constraints");
        Collection collectionTransform = Collections2.transform(map.entrySet(), TABLE_COLUMNS_FUNCTION);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(str);
        sb.append(" (");
        Joiner.on(", ").appendTo(sb, collectionTransform);
        UnmodifiableIterator<DBConstraint> it = immutableList.iterator();
        while (it.hasNext()) {
            DBConstraint next = it.next();
            sb.append(" , ");
            sb.append(next.toConstraintQuery());
        }
        sb.append(')');
        return sb.toString();
    }

    public static String addColumnStatement(String str, String str2, String str3) {
        return "ALTER TABLE " + str + " ADD COLUMN " + str2 + " " + str3;
    }

    public static String addColumnStatement(String str, String str2, String str3, String str4) {
        return addColumnStatement(str, str2, str3) + " DEFAULT " + str4;
    }

    private static class TableColumnsFunction implements Function<Map.Entry<String, String>, String> {
        private TableColumnsFunction() {
        }

        @Override // com.google.common.base.Function
        public String apply(@Nonnull Map.Entry<String, String> entry) {
            return entry.getKey() + ' ' + entry.getValue();
        }
    }
}
