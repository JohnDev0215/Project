package com.byd.overseaappstore.module.ability.util.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.r5;

/* JADX INFO: loaded from: classes.dex */
public final class ConstDb$Migration7Tox {

    @NotNull
    public static final ConstDb$Migration7Tox a = new ConstDb$Migration7Tox();

    @NotNull
    public static ConstDb$Migration7Tox$MIGRATION_7_8$1 b = new Migration() { // from class: com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration7Tox$MIGRATION_7_8$1
        @Override // androidx.room.migration.Migration
        public final void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            Objects.requireNonNull(r5.a);
            Intrinsics.checkNotNullParameter(database, "database");
        }
    };

    @NotNull
    public static ConstDb$Migration7Tox$MIGRATION_7_9$1 c = new Migration() { // from class: com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration7Tox$MIGRATION_7_9$1
        @Override // androidx.room.migration.Migration
        public final void migrate(@NotNull SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
            r5 r5Var = r5.a;
            Objects.requireNonNull(r5Var);
            Intrinsics.checkNotNullParameter(database, "database");
            Objects.requireNonNull(r5Var);
            Intrinsics.checkNotNullParameter(database, "database");
        }
    };

    private ConstDb$Migration7Tox() {
    }
}
