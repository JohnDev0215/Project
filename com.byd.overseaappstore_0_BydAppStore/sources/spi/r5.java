package spi;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class r5 {

    @NotNull
    public static final r5 a = new r5();

    private r5() {
    }

    public final void a(@NotNull SupportSQLiteDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        database.execSQL("alter table SoftwareLocal add column sourceType integer not null default 1");
        database.execSQL("create table SoftwareExtraLocal (packageName text primary key not null,primePackageName text not null,signature text not null,versionCode integer not null,versionName text not null)");
    }
}
