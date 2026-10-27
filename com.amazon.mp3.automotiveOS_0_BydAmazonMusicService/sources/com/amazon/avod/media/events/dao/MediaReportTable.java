package com.amazon.avod.media.events.dao;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public class MediaReportTable implements DBTable {
    private final ImmutableMap<String, String> mColumns = ImmutableMap.builder().put("id", "INTEGER PRIMARY KEY AUTOINCREMENT").put("appInstanceId", "TEXT").put("payload", "TEXT NOT NULL").build();
    private final ImmutableMap<String, String> mIndexes = ImmutableMap.builder().put("id", "(id)").build();

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "mediaReport";
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableMap<String, String> getColumns() {
        return this.mColumns;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableMap<String, String> getIndexes() {
        return this.mIndexes;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableList<DBConstraint> getConstraints() {
        return ImmutableList.of();
    }
}
