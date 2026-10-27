package com.byd.overseaappstore.module.ability.util.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import spi.mq;
import spi.oq;
import spi.pq;
import spi.tr;

/* JADX INFO: loaded from: classes.dex */
@Database(entities = {pq.class, oq.class}, version = 2)
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/db/OverseaAppStoreDb;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public abstract class OverseaAppStoreDb extends RoomDatabase {
    @NotNull
    public abstract mq c();

    @NotNull
    public abstract tr d();
}
