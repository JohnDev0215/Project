package com.amazon.avod.db;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public interface DBTable {
    String getCleanUpWhereClause();

    ImmutableMap<String, String> getColumns();

    ImmutableList<DBConstraint> getConstraints();

    ImmutableMap<String, String> getIndexes();

    String getTableName();
}
