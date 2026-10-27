package com.amazon.music.find.data.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDatabaseMigrationV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/find/data/database/AppDatabaseMigrationV2;", "Landroidx/room/migration/Migration;", "()V", "TTL_MILLIS", "", "migrate", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "migrateSearchEntityTable", "migrateSearchQueryTable", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppDatabaseMigrationV2 extends Migration {
    public static final AppDatabaseMigrationV2 INSTANCE = new AppDatabaseMigrationV2();
    private static final long TTL_MILLIS = 7776000000L;

    private AppDatabaseMigrationV2() {
        super(1, 2);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        migrateSearchQueryTable(database);
        migrateSearchEntityTable(database);
    }

    private final void migrateSearchQueryTable(SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE search_query_2 (query TEXT NOT NULL COLLATE NOCASE, timestamp INTEGER NOT NULL, profile_id TEXT NOT NULL, PRIMARY KEY (query, profile_id))");
        database.execSQL("INSERT INTO search_query_2 (query, timestamp, profile_id) SELECT query, timestamp, 'undefined' as profile_id FROM search_query WHERE timestamp >= ?", new Long[]{Long.valueOf(System.currentTimeMillis() - TTL_MILLIS)});
        database.execSQL("DROP TABLE IF EXISTS search_query");
        database.execSQL("ALTER TABLE search_query_2 RENAME TO search_query");
    }

    private final void migrateSearchEntityTable(SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE search_entity_2 (title TEXT NOT NULL COLLATE NOCASE, entity_id TEXT NOT NULL, entity_id_type TEXT NOT NULL, entity_type TEXT NOT NULL, image_url TEXT NOT NULL, entity_property TEXT, timestamp INTEGER NOT NULL, did_navigate_from_search INTEGER NOT NULL, content_url TEXT, profile_id TEXT NOT NULL, PRIMARY KEY (entity_id, entity_id_type, profile_id))");
        database.execSQL("INSERT INTO search_entity_2 (title, entity_id, entity_id_type, entity_type, image_url, entity_property, timestamp, did_navigate_from_search, content_url, profile_id) SELECT title, entity_id, entity_id_type, entity_type, image_url, entity_property, timestamp, did_navigate_from_search, content_url, 'undefined' as profile_id FROM search_entity WHERE timestamp >= ?", new Long[]{Long.valueOf(System.currentTimeMillis() - TTL_MILLIS)});
        database.execSQL("DROP TABLE IF EXISTS search_entity");
        database.execSQL("ALTER TABLE search_entity_2 RENAME TO search_entity");
    }
}
