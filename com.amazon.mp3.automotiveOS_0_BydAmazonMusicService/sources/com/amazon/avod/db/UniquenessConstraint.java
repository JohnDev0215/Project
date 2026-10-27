package com.amazon.avod.db;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class UniquenessConstraint implements DBConstraint {
    private final ImmutableList<String> mColumns;
    private final String mOnConflict;

    public UniquenessConstraint(@Nonnull ImmutableList<String> immutableList, @Nonnull String str) {
        this.mColumns = (ImmutableList) Preconditions.checkNotNull(immutableList, "columns");
        this.mOnConflict = (String) Preconditions.checkNotNull(str, "onConflict");
        Preconditions.checkArgument(!immutableList.isEmpty(), "Must specify at least one column for uniqueness constraint");
    }

    @Override // com.amazon.avod.db.DBConstraint
    public String toConstraintQuery() {
        return String.format(Locale.US, "UNIQUE (%s) ON CONFLICT %s", Joiner.on(", ").join(this.mColumns), this.mOnConflict);
    }
}
