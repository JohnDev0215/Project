package com.amazon.avod.userdownload.internal.database;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public class DrmTable implements DBTable {
    private static final ImmutableMap<String, String> COLUMNS = getColumnsForVersion(31);
    private static final ImmutableMap<String, String> INDICES = getIndexesForVersion(31);
    private static final ImmutableList<DBConstraint> CONSTRAINTS = getConstraintsForVersion(31);

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "drm";
    }

    public static ImmutableMap<String, String> getColumnsForVersion(int i) {
        ImmutableMap.Builder builderPut = new ImmutableMap.Builder().put("drm_asset_id", "TEXT PRIMARY KEY").put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "TEXT").put("expiry", "INTEGER").put("view_hours", "INTEGER");
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            return builderPut.build();
        }
        if (i > 7) {
            builderPut.put("offline_keyid", "TEXT");
        }
        if (i > 9) {
            builderPut.put("drm_scheme", "TEXT");
        }
        if (i > 10) {
            builderPut.put("renderer_scheme", "TEXT");
        }
        if (i > 18) {
            builderPut.put("error_code", "TEXT");
        }
        if (i > 21) {
            builderPut.put("last_refresh_sec", "INTEGER NOT NULL DEFAULT 0");
        }
        return builderPut.build();
    }

    public static ImmutableMap<String, String> getIndexesForVersion(int i) {
        return ImmutableMap.builder().build();
    }

    public static ImmutableList<DBConstraint> getConstraintsForVersion(int i) {
        return ImmutableList.builder().build();
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
        return CONSTRAINTS;
    }
}
