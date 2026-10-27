package com.amazon.avod.media.framework.network;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkHistoryTable implements DBTable {
    private static final ImmutableMap<String, String> COLUMNS = ImmutableMap.builder().put("_id", "INTEGER primary key autoincrement").put("access_point_id", "TEXT unique not null").put("bandwidth", "INTEGER").put("heuristic_bandwidth", "INTEGER").put("heuristic_bandwidth_stdev", "INTEGER").put("heuristics_state", "BLOB").put("last_used", "LONG").build();
    private static final ImmutableMap<String, String> INDICES = ImmutableMap.builder().put("AccessPointId", String.format(Locale.US, "(%s)", "access_point_id")).build();

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "access_point_table";
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
