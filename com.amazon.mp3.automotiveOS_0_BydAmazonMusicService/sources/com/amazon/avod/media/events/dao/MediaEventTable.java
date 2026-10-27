package com.amazon.avod.media.events.dao;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventTable implements DBTable {
    private final ImmutableMap<String, String> mColumns = ImmutableMap.builder().put("sequence", "INTEGER NOT NULL").put("appInstanceId", "TEXT").put(TetheredMessageKey.timestamp, "INTEGER NOT NULL").put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "TEXT NOT NULL").put("payload", "TEXT NOT NULL").build();
    private final ImmutableMap<String, String> mIndexes = ImmutableMap.builder().put("sequence", "(sequence)").put("appInstanceId", "(appInstanceId)").put(TetheredMessageKey.timestamp, "(timestamp)").build();

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "mediaEvent";
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
