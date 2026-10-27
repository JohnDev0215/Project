package com.amazon.podcast.storage;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastRoomMigrations {
    static final Migration MIGRATION_1_2;
    static final Migration MIGRATION_2_3;
    static final Migration MIGRATION_3_4;
    static final Migration MIGRATION_4_5;
    static final Migration MIGRATION_5_6;
    static final Migration MIGRATION_6_7;
    static final Migration MIGRATION_7_8;
    static final Migration MIGRATION_8_9;
    static final Migration MIGRATION_9_10;

    static {
        int i = 2;
        MIGRATION_1_2 = new Migration(1, i) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.1
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `ui_metric_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `signalType` TEXT NOT NULL, `metricsPreset` TEXT NOT NULL, `pageType` TEXT NOT NULL, `contentSrc` TEXT NOT NULL, `containerType` TEXT NOT NULL, `entityType` TEXT NOT NULL, `entityIdType` TEXT NOT NULL, `entityId` TEXT NOT NULL, `detailPageItemId` TEXT NOT NULL, `detailPageItemIdType` TEXT NOT NULL, `refMarker` TEXT NOT NULL, `actionType` TEXT NOT NULL, `interactionType` TEXT NOT NULL, `contentName` TEXT NOT NULL, `containerIndex` INTEGER NOT NULL, `entityPos` INTEGER NOT NULL, `firstViewableIndex` INTEGER NOT NULL, `lastViewableIndex` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
            }
        };
        int i2 = 3;
        MIGRATION_2_3 = new Migration(i, i2) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.2
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("BEGIN;");
                database.execSQL("ALTER TABLE `playback_metric_operations` ADD `errorException` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `playback_metric_operations` ADD `mediaTitle` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `playback_metric_operations` ADD `mediaCollectionId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `playback_metric_operations` ADD `mediaSubTitle` TEXT NOT NULL DEFAULT '';");
                database.execSQL("COMMIT;");
            }
        };
        int i3 = 4;
        MIGRATION_3_4 = new Migration(i2, i3) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.3
            @Override // androidx.room.migration.Migration
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `completed_operations`(`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `completed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
                database.execSQL("CREATE TABLE IF NOT EXISTS `completions`(`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `podcastTitle` TEXT NOT NULL, `title` TEXT NOT NULL, `image` TEXT NOT NULL, `podcastImage` TEXT NOT NULL, `publishDate` TEXT NOT NULL, `description` TEXT NOT NULL, `authors` TEXT NOT NULL, `seasonNumber` TEXT NOT NULL, `completed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
            }
        };
        int i4 = 5;
        MIGRATION_4_5 = new Migration(i3, i4) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.4
            @Override // androidx.room.migration.Migration
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `category_follows` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `followed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                database.execSQL("CREATE TABLE IF NOT EXISTS `category_follow_operations` (`operationId` TEXT NOT NULL,`elementId` TEXT NOT NULL, `title` TEXT NOT NULL, `followed` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
            }
        };
        int i5 = 6;
        MIGRATION_5_6 = new Migration(i4, i5) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.5
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("BEGIN;");
                database.execSQL("ALTER TABLE `playback_metric_operations` ADD `playbackInstanceId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("COMMIT;");
            }
        };
        int i6 = 7;
        MIGRATION_6_7 = new Migration(i5, i6) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.6
            @Override // androidx.room.migration.Migration
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("BEGIN;");
                database.execSQL("ALTER TABLE `ui_metric_operations` ADD `numItemsAccessible` INTEGER NOT NULL DEFAULT -1;");
                database.execSQL("ALTER TABLE `ui_metric_operations` ADD `containerInstId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("COMMIT;");
            }
        };
        int i7 = 8;
        MIGRATION_7_8 = new Migration(i6, i7) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.7
            @Override // androidx.room.migration.Migration
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("BEGIN;");
                database.execSQL("ALTER TABLE `follows` ADD `podcastShowVariantId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `saves` ADD `playbackMode` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `saves` ADD `contentTraits` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `saves` ADD `availableUpsells` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `saves` ADD `availabilityDate` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `saves` ADD `podcastShowVariantId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `saves` ADD `podcastEpisodeVariantId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `downloads` ADD `playbackMode` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `downloads` ADD `contentTraits` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `downloads` ADD `availableUpsells` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `downloads` ADD `availabilityDate` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `downloads` ADD `podcastShowVariantId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("ALTER TABLE `downloads` ADD `podcastEpisodeVariantId` TEXT NOT NULL DEFAULT '';");
                database.execSQL("COMMIT;");
            }
        };
        int i8 = 9;
        MIGRATION_8_9 = new Migration(i7, i8) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.8
            @Override // androidx.room.migration.Migration
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `bite_bookmarks` (`id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `progressMilliseconds` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                database.execSQL("CREATE TABLE IF NOT EXISTS `bite_bookmark_operations` (`operationId` TEXT NOT NULL, `id` TEXT NOT NULL, `podcastId` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `totalDurationMilliseconds` INTEGER NOT NULL, `progressMilliseconds` INTEGER NOT NULL, `updatedTime` INTEGER NOT NULL, `isProcessed` INTEGER NOT NULL, PRIMARY KEY(`operationId`))");
            }
        };
        MIGRATION_9_10 = new Migration(i8, 10) { // from class: com.amazon.podcast.storage.PodcastRoomMigrations.9
            @Override // androidx.room.migration.Migration
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("BEGIN;");
                database.execSQL("ALTER TABLE `playback_metric_operations` ADD `playbackEngineType` TEXT NOT NULL DEFAULT '';");
                database.execSQL("COMMIT;");
            }
        };
    }
}
