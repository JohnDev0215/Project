package spi;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration1Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration2Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration3Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration4Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration5Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration6Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration7Tox;
import com.byd.overseaappstore.module.ability.util.db.ConstDb$Migration8Tox;
import com.byd.overseaappstore.module.ability.util.db.OverseaAppStoreDb;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class t5 implements ll {
    public final ll<Context> a;

    public t5(ll<Context> llVar) {
        this.a = llVar;
    }

    public static OverseaAppStoreDb a(Context ctx) {
        Objects.requireNonNull(s5.a);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        RoomDatabase.Builder builderDatabaseBuilder = Room.databaseBuilder(ctx, OverseaAppStoreDb.class, "OverseaAppStore.db");
        Objects.requireNonNull(ConstDb$Migration1Tox.a);
        Objects.requireNonNull(ConstDb$Migration2Tox.a);
        Objects.requireNonNull(ConstDb$Migration3Tox.a);
        Objects.requireNonNull(ConstDb$Migration4Tox.a);
        Objects.requireNonNull(ConstDb$Migration5Tox.a);
        Objects.requireNonNull(ConstDb$Migration6Tox.a);
        Objects.requireNonNull(ConstDb$Migration7Tox.a);
        Objects.requireNonNull(ConstDb$Migration8Tox.a);
        RoomDatabase roomDatabaseBuild = builderDatabaseBuilder.addMigrations(ConstDb$Migration1Tox.b, ConstDb$Migration1Tox.c, ConstDb$Migration1Tox.d, ConstDb$Migration1Tox.e, ConstDb$Migration1Tox.f, ConstDb$Migration1Tox.g, ConstDb$Migration1Tox.h, ConstDb$Migration1Tox.i, ConstDb$Migration2Tox.b, ConstDb$Migration2Tox.c, ConstDb$Migration2Tox.d, ConstDb$Migration2Tox.e, ConstDb$Migration2Tox.f, ConstDb$Migration2Tox.g, ConstDb$Migration2Tox.h, ConstDb$Migration3Tox.b, ConstDb$Migration3Tox.c, ConstDb$Migration3Tox.d, ConstDb$Migration3Tox.e, ConstDb$Migration3Tox.f, ConstDb$Migration3Tox.g, ConstDb$Migration4Tox.b, ConstDb$Migration4Tox.c, ConstDb$Migration4Tox.d, ConstDb$Migration4Tox.e, ConstDb$Migration4Tox.f, ConstDb$Migration5Tox.b, ConstDb$Migration5Tox.c, ConstDb$Migration5Tox.d, ConstDb$Migration5Tox.e, ConstDb$Migration6Tox.b, ConstDb$Migration6Tox.c, ConstDb$Migration6Tox.d, ConstDb$Migration7Tox.b, ConstDb$Migration7Tox.c, ConstDb$Migration8Tox.b).build();
        Intrinsics.checkNotNullExpressionValue(roomDatabaseBuild, "databaseBuilder(ctx, Ove…   )\n            .build()");
        OverseaAppStoreDb overseaAppStoreDb = (OverseaAppStoreDb) roomDatabaseBuild;
        Objects.requireNonNull(overseaAppStoreDb, "Cannot return null from a non-@Nullable @Provides method");
        return overseaAppStoreDb;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get());
    }
}
