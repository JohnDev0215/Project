package com.byd.overseaappstore.module.ability.util.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.r5;

/* JADX INFO: loaded from: classes.dex */
public final class ConstDb$Migration8Tox {

    @NotNull
    public static final ConstDb$Migration8Tox a = new ConstDb$Migration8Tox();

    @NotNull
    public static ConstDb$Migration8Tox$MIGRATION_8_9$1 b = new Migration() { // from class: com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration8Tox$MIGRATION_8_9$1
        @Override // androidx.room.migration.Migration
        public final void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            Objects.requireNonNull(r5.a);
            Intrinsics.checkNotNullParameter(database, "database");
        }
    };

    private ConstDb$Migration8Tox() {
    }
}
