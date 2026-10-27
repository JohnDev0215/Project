package com.amazon.avod.drm.db;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class DrmContentTable implements DBTable {
    private static final ImmutableMap<String, String> COLUMNS = ImmutableMap.builder().put("_id", "INTEGER primary key autoincrement").put("asin", "TEXT unique not null").put("drm_id", "TEXT unique not null").put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "TEXT not null").put("expiry", "INTEGER").put("view_hours", "INTEGER").build();
    private static final ImmutableMap<String, String> INDICES = ImmutableMap.builder().put("asin_index", String.format(Locale.US, "(%s)", "asin")).build();

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "drm_table";
    }

    DrmContentTable() {
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
