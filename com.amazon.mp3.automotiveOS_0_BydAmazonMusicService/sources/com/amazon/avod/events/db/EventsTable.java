package com.amazon.avod.events.db;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class EventsTable implements DBTable {
    private static final ImmutableMap<String, String> COLUMNS = ImmutableMap.builder().put("_id", "INTEGER PRIMARY KEY").put("ASIN", "STRING").put("Name", "STRING").put("Type", "STRING").put("SessionId", "STRING").put("Priority", "INTEGER").put("Body", "STRING").put("Timestamp", "INTEGER").put("RetryCount", "INTEGER").put("Processed", "INTEGER").put("AccountId", "STRING").put("ProfileId", "STRING").build();
    private static final ImmutableMap<String, String> INDICES;
    private static EventsTable instance;

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "events";
    }

    private EventsTable() {
    }

    static {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Locale locale = Locale.US;
        INDICES = builder.put("ASIN", String.format(locale, "(%s)", "ASIN")).put("Type", String.format(locale, "(%s)", "Type")).put("Priority", String.format(locale, "(%s)", "Priority")).put("Processed", String.format(locale, "(%s)", "Processed")).put("Profile", String.format(locale, "(%s, %s, %s, %s, %s)", "Type", "AccountId", "ProfileId", "ASIN", "SessionId")).build();
    }

    public static synchronized EventsTable getInstance() {
        if (instance == null) {
            instance = new EventsTable();
        }
        return instance;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableMap<String, String> getColumns() {
        return COLUMNS;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableMap<String, String> getIndexes() {
        return INDICES;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableList<DBConstraint> getConstraints() {
        return ImmutableList.of();
    }
}
