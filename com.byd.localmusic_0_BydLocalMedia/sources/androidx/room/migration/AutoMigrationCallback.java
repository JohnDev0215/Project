package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes4.dex */
public interface AutoMigrationCallback {
    default void onPostMigrate(SupportSQLiteDatabase db) {
    }
}
