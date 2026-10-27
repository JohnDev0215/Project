package com.amazon.avod.db;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ForeignKeyConstraint implements DBConstraint {
    private final String mChildTable;
    private final Optional<String> mChildTableColumn = Optional.absent();
    private final String mColumn;

    public ForeignKeyConstraint(@Nonnull String str, @Nonnull String str2) {
        this.mColumn = (String) Preconditions.checkNotNull(str, "column");
        this.mChildTable = (String) Preconditions.checkNotNull(str2, "childTable");
    }

    @Override // com.amazon.avod.db.DBConstraint
    public String toConstraintQuery() {
        return String.format(Locale.US, "FOREIGN KEY (%s) REFERENCES %s", this.mColumn, this.mChildTableColumn.isPresent() ? String.format(Locale.US, "%s(%s)", this.mChildTable, this.mChildTableColumn.get()) : this.mChildTable);
    }
}
