package com.amazon.music.curate.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.music.curate.model.LibraryContextMapping;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.PlaylistTrack;
import com.amazon.music.curate.model.Track;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDatabase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Database(entities = {Track.class, Playlist.class, PlaylistTrack.class, LibraryContextMapping.class}, exportSchema = false, version = 5)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/curate/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "libraryContextMappingDao", "Lcom/amazon/music/curate/data/database/LibraryContextMappingDao;", "playlistDao", "Lcom/amazon/music/curate/data/database/PlaylistDao;", "playlistTrackDao", "Lcom/amazon/music/curate/data/database/PlaylistTrackDao;", "trackDao", "Lcom/amazon/music/curate/data/database/TrackDao;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AppDatabase extends RoomDatabase {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile AppDatabase INSTANCE;

    public abstract LibraryContextMappingDao libraryContextMappingDao();

    public abstract PlaylistDao playlistDao();

    public abstract PlaylistTrackDao playlistTrackDao();

    public abstract TrackDao trackDao();

    /* JADX INFO: compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/data/database/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/amazon/music/curate/data/database/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppDatabase getDatabase(Context context) {
            AppDatabase appDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            AppDatabase appDatabase2 = AppDatabase.INSTANCE;
            if (appDatabase2 != null) {
                return appDatabase2;
            }
            synchronized (this) {
                RoomDatabase roomDatabaseBuild = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "curate_database").fallbackToDestructiveMigration().build();
                Intrinsics.checkNotNullExpressionValue(roomDatabaseBuild, "databaseBuilder(\n       …                 .build()");
                appDatabase = (AppDatabase) roomDatabaseBuild;
                AppDatabase.INSTANCE = appDatabase;
            }
            return appDatabase;
        }
    }
}
