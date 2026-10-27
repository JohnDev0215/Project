package com.byd.overseaappstore.module.ability.util.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.byd.car.locale.ccTLD;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import spi.mq;
import spi.nq;
import spi.tr;
import spi.ur;

/* JADX INFO: loaded from: classes.dex */
public final class OverseaAppStoreDb_Impl extends OverseaAppStoreDb {
    public volatile nq a;
    public volatile ur b;

    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `SoftwareLocal` (`packageName` TEXT NOT NULL, `id` INTEGER NOT NULL, `name` TEXT NOT NULL, `sourceDir` TEXT NOT NULL, `totalSize` INTEGER NOT NULL, `downloadUrl` TEXT NOT NULL, `icon` TEXT NOT NULL, `classifyId` INTEGER NOT NULL, `classifyName` TEXT NOT NULL, `versionCode` INTEGER NOT NULL, `state` INTEGER NOT NULL, `signature` TEXT NOT NULL, `sourceType` INTEGER NOT NULL DEFAULT 1, PRIMARY KEY(`packageName`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `SoftwareExtraLocal` (`packageName` TEXT NOT NULL, `primePackageName` TEXT NOT NULL, `signature` TEXT NOT NULL, `versionCode` INTEGER NOT NULL, `versionName` TEXT NOT NULL, PRIMARY KEY(`packageName`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3b6cf687d8d69fb8bdea453bd33ccef2')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `SoftwareLocal`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `SoftwareExtraLocal`");
            List<RoomDatabase.Callback> list = OverseaAppStoreDb_Impl.this.mCallbacks;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    OverseaAppStoreDb_Impl.this.mCallbacks.get(i).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            List<RoomDatabase.Callback> list = OverseaAppStoreDb_Impl.this.mCallbacks;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    OverseaAppStoreDb_Impl.this.mCallbacks.get(i).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            OverseaAppStoreDb_Impl.this.mDatabase = supportSQLiteDatabase;
            OverseaAppStoreDb_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            List<RoomDatabase.Callback> list = OverseaAppStoreDb_Impl.this.mCallbacks;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    OverseaAppStoreDb_Impl.this.mCallbacks.get(i).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap map = new HashMap(13);
            map.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, 1));
            map.put(ccTLD.INDONESIA, new TableInfo.Column(ccTLD.INDONESIA, "INTEGER", true, 0, null, 1));
            map.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
            map.put("sourceDir", new TableInfo.Column("sourceDir", "TEXT", true, 0, null, 1));
            map.put("totalSize", new TableInfo.Column("totalSize", "INTEGER", true, 0, null, 1));
            map.put("downloadUrl", new TableInfo.Column("downloadUrl", "TEXT", true, 0, null, 1));
            map.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, 1));
            map.put("classifyId", new TableInfo.Column("classifyId", "INTEGER", true, 0, null, 1));
            map.put("classifyName", new TableInfo.Column("classifyName", "TEXT", true, 0, null, 1));
            map.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, 1));
            map.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, 1));
            map.put("signature", new TableInfo.Column("signature", "TEXT", true, 0, null, 1));
            map.put("sourceType", new TableInfo.Column("sourceType", "INTEGER", true, 0, "1", 1));
            TableInfo tableInfo = new TableInfo("SoftwareLocal", map, new HashSet(0), new HashSet(0));
            TableInfo tableInfo2 = TableInfo.read(supportSQLiteDatabase, "SoftwareLocal");
            if (!tableInfo.equals(tableInfo2)) {
                return new RoomOpenHelper.ValidationResult(false, "SoftwareLocal(com.byd.overseaappstore.module.ability.util.db.entity.SoftwareLocal).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
            }
            HashMap map2 = new HashMap(5);
            map2.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, 1));
            map2.put("primePackageName", new TableInfo.Column("primePackageName", "TEXT", true, 0, null, 1));
            map2.put("signature", new TableInfo.Column("signature", "TEXT", true, 0, null, 1));
            map2.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, 1));
            map2.put("versionName", new TableInfo.Column("versionName", "TEXT", true, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("SoftwareExtraLocal", map2, new HashSet(0), new HashSet(0));
            TableInfo tableInfo4 = TableInfo.read(supportSQLiteDatabase, "SoftwareExtraLocal");
            if (tableInfo3.equals(tableInfo4)) {
                return new RoomOpenHelper.ValidationResult(true, null);
            }
            return new RoomOpenHelper.ValidationResult(false, "SoftwareExtraLocal(com.byd.overseaappstore.module.ability.util.db.entity.SoftwareExtraLocal).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
        }
    }

    @Override // com.byd.overseaappstore.module.ability.util.db.OverseaAppStoreDb
    public final mq c() {
        nq nqVar;
        if (this.a != null) {
            return this.a;
        }
        synchronized (this) {
            if (this.a == null) {
                this.a = new nq(this);
            }
            nqVar = this.a;
        }
        return nqVar;
    }

    @Override // androidx.room.RoomDatabase
    public final void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `SoftwareLocal`");
            writableDatabase.execSQL("DELETE FROM `SoftwareExtraLocal`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public final InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "SoftwareLocal", "SoftwareExtraLocal");
    }

    @Override // androidx.room.RoomDatabase
    public final SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(2), "3b6cf687d8d69fb8bdea453bd33ccef2", "f7c95ee2faf45586080ded4358b99ac6")).build());
    }

    @Override // com.byd.overseaappstore.module.ability.util.db.OverseaAppStoreDb
    public final tr d() {
        ur urVar;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new ur(this);
            }
            urVar = this.b;
        }
        return urVar;
    }

    @Override // androidx.room.RoomDatabase
    public final List<Migration> getAutoMigrations(@NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // androidx.room.RoomDatabase
    public final Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public final Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(mq.class, Collections.emptyList());
        map.put(tr.class, Collections.emptyList());
        return map;
    }
}
