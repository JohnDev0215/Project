package com.amazon.avod.db;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DBConstraint {
    @Nonnull
    String toConstraintQuery();
}
