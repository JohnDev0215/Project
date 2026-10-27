package com.amazon.music.find.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.amazon.music.find.data.database.entities.SearchHistory;
import com.amazon.music.find.data.database.entities.SearchItem;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDatabase.kt */
/* JADX INFO: loaded from: classes4.dex */
@TypeConverters({SearchTypeConverters.class})
@Database(entities = {SearchItem.class, SearchHistory.class}, version = 2)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/find/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "getSearchEntityDao", "Lcom/amazon/music/find/data/database/SearchEntityDao;", "getSearchQueryDao", "Lcom/amazon/music/find/data/database/SearchQueryDao;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AppDatabase extends RoomDatabase {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DATABASE_NAME = "find.db";
    private static volatile AppDatabase instance;

    public abstract SearchEntityDao getSearchEntityDao();

    public abstract SearchQueryDao getSearchQueryDao();

    /* JADX INFO: compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/data/database/AppDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "instance", "Lcom/amazon/music/find/data/database/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppDatabase getDatabase(Context context) {
            AppDatabase appDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            AppDatabase appDatabase2 = AppDatabase.instance;
            if (appDatabase2 != null) {
                return appDatabase2;
            }
            synchronized (this) {
                RoomDatabase roomDatabaseBuild = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME).fallbackToDestructiveMigration().addMigrations(AppDatabaseMigrationV2.INSTANCE).build();
                Intrinsics.checkNotNullExpressionValue(roomDatabaseBuild, "databaseBuilder(\n       …                 .build()");
                appDatabase = (AppDatabase) roomDatabaseBuild;
                AppDatabase.instance = appDatabase;
            }
            return appDatabase;
        }
    }
}
