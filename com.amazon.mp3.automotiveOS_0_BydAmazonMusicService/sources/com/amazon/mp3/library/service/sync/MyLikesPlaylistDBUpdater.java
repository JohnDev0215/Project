package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.service.sync.MyLikesPlaylistDBUpdater;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.subscription.UserSubscription;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: MyLikesPlaylistDBUpdater.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/library/service/sync/MyLikesPlaylistDBUpdater;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyLikesPlaylistDBUpdater {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final String TAG;

    /* JADX INFO: compiled from: MyLikesPlaylistDBUpdater.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010\u0011\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J \u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001d\u001a\u00020\u001eR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/library/service/sync/MyLikesPlaylistDBUpdater$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "addTrackToDB", "", "primeTrack", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "addTrackToMyLikesInDB", "", "context", "Landroid/content/Context;", "trackId", "autoDownloadIfMyLikesUnified", "playlistId", "", "copyNullLuidTrackToCatalogOnly", "trackContentUri", "Landroid/net/Uri;", "getMyLikesPlaylistId", "(Landroid/content/Context;)Ljava/lang/Long;", "removeTrackFromMyLikesFromDB", "trackExistsInMyLikes", "", "updateMyLikesPlaylistInDB", "newLikeState", "Lcom/amazon/music/media/playback/ratings/LikeState;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return MyLikesPlaylistDBUpdater.TAG;
        }

        public final int addTrackToDB(PrimeTrack primeTrack) {
            Intrinsics.checkNotNullParameter(primeTrack, "primeTrack");
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            if (UserSubscriptionUtil.isNightwingOnly() || !userSubscription.hasSubscription()) {
                Log.debug(getTAG(), "No need to add/remove liked catalog tracks in the Track table  since My Likes playlist is not accessible by Free Tier users and non subscribers.");
                return 0;
            }
            try {
                return new CTAPrimeCache(AmazonApplication.getApplication()).insertPrimeTracks(primeTrack.getAlbumAsin(), primeTrack.getAlbum(), primeTrack.getAlbumArtist(), CollectionsKt__CollectionsJVMKt.listOf(primeTrack));
            } catch (SQLiteDatabaseLockedException e) {
                Log.error(getTAG(), "Failed to add track to the Track table: {}", primeTrack.getAsin(), e);
                return 0;
            }
        }

        public final void updateMyLikesPlaylistInDB(final Context context, final PrimeTrack primeTrack, final LikeState newLikeState) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(newLikeState, "newLikeState");
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            if (UserSubscriptionUtil.isNightwingOnly() || !userSubscription.hasSubscription()) {
                Log.debug(getTAG(), "No need to update My Likes since it's not accessible by Free Tier users and non subscribers");
            } else {
                if (primeTrack == null) {
                    return;
                }
                AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.library.service.sync.MyLikesPlaylistDBUpdater$Companion$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        MyLikesPlaylistDBUpdater.Companion.m1346updateMyLikesPlaylistInDB$lambda1$lambda0(primeTrack, primeTrack, context, newLikeState);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: updateMyLikesPlaylistInDB$lambda-1$lambda-0, reason: not valid java name */
        public static final void m1346updateMyLikesPlaylistInDB$lambda1$lambda0(PrimeTrack it, PrimeTrack primeTrack, Context context, LikeState newLikeState) throws Throwable {
            Intrinsics.checkNotNullParameter(it, "$it");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(newLikeState, "$newLikeState");
            if (it.getAsin() != null && it.getLuid() == null) {
                Uri trackUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "cta", primeTrack == null ? null : primeTrack.getAsin());
                Companion companion = MyLikesPlaylistDBUpdater.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(trackUri, "trackUri");
                companion.copyNullLuidTrackToCatalogOnly(context, trackUri);
            }
            if (LikeState.LIKE == newLikeState) {
                MyLikesPlaylistDBUpdater.INSTANCE.addTrackToMyLikesInDB(context, it.getAsin());
            } else {
                MyLikesPlaylistDBUpdater.INSTANCE.removeTrackFromMyLikesFromDB(context, it.getAsin());
            }
        }

        private final Long getMyLikesPlaylistId(Context context) throws Throwable {
            Cursor cursorQuery;
            Cursor cursor = null;
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Playlist", new String[]{"_id"}, "canLikeTracks = ?", new String[]{"0"}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                Long lValueOf = Long.valueOf(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_id")));
                                DbUtil.closeCursor(cursorQuery);
                                return lValueOf;
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorQuery;
                            DbUtil.closeCursor(cursor);
                            throw th;
                        }
                    } catch (IllegalArgumentException e) {
                        e = e;
                        Log.error(getTAG(), "Unable to get My Likes playlist id", e);
                    }
                }
            } catch (IllegalArgumentException e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                DbUtil.closeCursor(cursor);
                throw th;
            }
            DbUtil.closeCursor(cursorQuery);
            return null;
        }

        private final void removeTrackFromMyLikesFromDB(Context context, String trackId) throws Throwable {
            if (StringUtils.isBlank(trackId)) {
                return;
            }
            Intrinsics.checkNotNull(context);
            Long myLikesPlaylistId = getMyLikesPlaylistId(context);
            if (myLikesPlaylistId == null) {
                return;
            }
            myLikesPlaylistId.longValue();
            SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
            writableDatabase.beginTransaction();
            try {
                writableDatabase.delete("PlaylistTrack", "udo_playlist_id=? AND track_luid=?", new String[]{myLikesPlaylistId.toString(), trackId});
                writableDatabase.setTransactionSuccessful();
            } finally {
                writableDatabase.endTransaction();
            }
        }

        private final void addTrackToMyLikesInDB(final Context context, String trackId) throws Throwable {
            if (StringUtils.isBlank(trackId)) {
                return;
            }
            Intrinsics.checkNotNull(context);
            Long myLikesPlaylistId = getMyLikesPlaylistId(context);
            if (myLikesPlaylistId == null) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.mp3.library.service.sync.MyLikesPlaylistDBUpdater$Companion$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SyncService.startSync(context, 19);
                    }
                }, TimeUnit.SECONDS.toMillis(5L));
                return;
            }
            long jLongValue = myLikesPlaylistId.longValue();
            Intrinsics.checkNotNull(trackId);
            if (trackExistsInMyLikes(jLongValue, trackId)) {
                return;
            }
            SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
            try {
                try {
                    DbUtil.beginTransaction(writableDatabase);
                    writableDatabase.execSQL(Intrinsics.stringPlus("UPDATE PlaylistTrack SET udo=udo+1  WHERE udo_playlist_id=", myLikesPlaylistId));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("udo_playlist_id", myLikesPlaylistId);
                    contentValues.put("track_luid", trackId);
                    contentValues.put("udo", (Integer) 0);
                    writableDatabase.insertWithOnConflict("PlaylistTrack", null, contentValues, 5);
                    writableDatabase.setTransactionSuccessful();
                    autoDownloadIfMyLikesUnified(context, myLikesPlaylistId.longValue());
                } catch (Exception e) {
                    Log.error(getTAG(), "Unable to add liked songs to My Likes playlist", e);
                }
            } finally {
                writableDatabase.endTransaction();
            }
        }

        private final void autoDownloadIfMyLikesUnified(Context context, long playlistId) {
            if (context == null) {
                return;
            }
            PlaylistUpdater.downloadUpdatedUnifiedPlaylist(context, playlistId, "cirrus");
        }

        private final void copyNullLuidTrackToCatalogOnly(Context context, Uri trackContentUri) {
            if (trackContentUri == null) {
                Log.error(getTAG(), "Failed to copy the null-luid track. The trackContentUri can't be null.");
                return;
            }
            Cursor cursorQuery = context.getApplicationContext().getContentResolver().query(trackContentUri, null, null, null, null);
            if (cursorQuery == null) {
                try {
                    Log.verbose(getTAG(), Intrinsics.stringPlus("The cursor is null for ", trackContentUri));
                } catch (Throwable th) {
                    DbUtil.closeCursor(cursorQuery);
                    throw th;
                }
            }
            if (cursorQuery != null) {
                int count = cursorQuery.getCount();
                Log.verbose(getTAG(), Intrinsics.stringPlus("Found tracks: ", Integer.valueOf(count)));
                if (count != 0) {
                    while (cursorQuery.moveToNext()) {
                        PlaylistUtil.copyNullLuidTrackToCatalogOnly(context, cursorQuery);
                    }
                } else {
                    DbUtil.closeCursor(cursorQuery);
                    return;
                }
            }
            DbUtil.closeCursor(cursorQuery);
        }

        private final boolean trackExistsInMyLikes(long playlistId, String trackId) {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("PlaylistTrack");
            Cursor cursorQuery = sQLiteQueryBuilder.query(CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext()), new String[]{"track_luid"}, "udo_playlist_id=? AND track_luid=? ", new String[]{String.valueOf(playlistId), trackId}, null, null, null);
            try {
                return cursorQuery.getCount() > 0;
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TAG = companion.getClass().getSimpleName();
    }
}
