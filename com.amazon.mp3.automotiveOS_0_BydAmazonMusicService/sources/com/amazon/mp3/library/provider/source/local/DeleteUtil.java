package com.amazon.mp3.library.provider.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.generator.LocalLuidGenerator;
import com.amazon.mp3.download.library.DownloadFileUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistTrackUtil;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DeleteUtil {
    public static final String TAG = "DeleteUtil";

    private static boolean deleteFile(final Context context, String path, boolean hasSDCardPermission) {
        DocumentFile documentFileBySAF;
        if (path == null) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.canWrite()) {
            return file.delete();
        }
        if (!hasSDCardPermission || (documentFileBySAF = SDCardUtil.getDocumentFileBySAF(context, path)) == null) {
            return false;
        }
        return documentFileBySAF.delete();
    }

    public static void deleteTracksFromDevice(final Context context, final Uri tracksUri) {
        deleteTracksFromDevice(context, tracksUri, null);
    }

    public static void deleteTracksFromDevice(final Context context, final Uri tracksUri, @Nullable final DeleteListener deleteListener) {
        deleteTracksFromDevice(context, tracksUri, null, false);
    }

    public static void deleteTracksFromDevice(final Context context, final Uri tracksUri, @Nullable final DeleteListener deleteListener, final boolean deleteFromOfflineLibrary) {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.library.provider.source.local.DeleteUtil.1
            @Override // java.lang.Runnable
            public void run() {
                DeleteListener.DeletionResult deletionResultDeleteTracksFromDeviceOnCurrentThread = DeleteUtil.deleteTracksFromDeviceOnCurrentThread(context, tracksUri, deleteFromOfflineLibrary);
                DeleteListener deleteListener2 = deleteListener;
                if (deleteListener2 != null) {
                    deleteListener2.onComplete(deletionResultDeleteTracksFromDeviceOnCurrentThread);
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getLocalTrackUriForMatchHash(final android.content.ContentResolver r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "cirrus-local"
            android.net.Uri r2 = com.amazon.mp3.library.provider.MediaProvider.Tracks.getContentUri(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "match_hash = "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r4 = r0.toString()
            java.lang.String r8 = "local_uri"
            java.lang.String[] r3 = new java.lang.String[]{r8}
            r5 = 0
            r6 = 0
            r1 = r7
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)
            if (r7 == 0) goto L37
            boolean r8 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L32
            if (r8 == 0) goto L37
            r8 = 0
            java.lang.String r8 = r7.getString(r8)     // Catch: java.lang.Throwable -> L32
            goto L38
        L32:
            r8 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r7)
            throw r8
        L37:
            r8 = 0
        L38:
            com.amazon.mp3.util.DbUtil.closeCursor(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.local.DeleteUtil.getLocalTrackUriForMatchHash(android.content.ContentResolver, java.lang.String):java.lang.String");
    }

    public static synchronized DeleteListener.DeletionResult deleteTracksFromDeviceOnCurrentThread(final Context context, final Uri tracksUri) {
        return deleteTracksFromDeviceOnCurrentThread(context, tracksUri, false);
    }

    public static synchronized DeleteListener.DeletionResult deleteTracksFromDeviceOnCurrentThread(final Context context, Uri tracksUri, final boolean deleteFromOfflineLibrary) {
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        String source = MediaProvider.getSource(tracksUri);
        String type = contentResolver.getType(tracksUri);
        NowPlayingUtil.clearCollection(AmazonApplication.getContext(), tracksUri, PlayStateMutationReason.DELETING_TRACKS);
        if (type != null && !type.equals("vnd.android.cursor.dir/vnd.amazonmp3.track") && !type.equals("vnd.android.cursor.item/vnd.amazonmp3.track") && !type.equals("vnd.android.cursor.dir/vnd.amazonmp3.udoplaylisttrack") && !type.equals("vnd.android.cursor.item/vnd.amazonmp3.udoplaylisttrack")) {
            throw new IllegalArgumentException("Specified URI is not a Tracks type!");
        }
        if (type != null && type.equals("vnd.android.cursor.item/vnd.amazonmp3.track") && "cirrus".equals(source)) {
            String str = TAG;
            Log.debug(str, "Changing to corresponded local track uri");
            MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(tracksUri);
            if (track == null) {
                Log.debug(str, "cloudTrack not found, can't delete");
                return DeleteListener.DeletionResult.INSTANCE.buildDeletionError();
            }
            MusicTrack track2 = AmazonApplication.getLibraryItemFactory().getTrack("cirrus-local", track.getMatchHash());
            if (track2 == null) {
                Log.debug(str, "localTrack not found, can't delete");
                return DeleteListener.DeletionResult.INSTANCE.buildDeletionError();
            }
            tracksUri = MediaProvider.Tracks.getContentUri("cirrus-local", track2.getLuid());
        }
        if (SDCardUtil.hasSDCardPermission(context)) {
            return executeDeletionOnCurrentThread(context, tracksUri, true);
        }
        try {
            if (isSDCardPermissionNeeded(context, tracksUri)) {
                return DeleteListener.DeletionResult.INSTANCE.buildDeletionSDCardPermissionError(tracksUri);
            }
            return executeDeletionOnCurrentThread(context, tracksUri, false, deleteFromOfflineLibrary);
        } catch (Exception e) {
            Log.error(TAG, e.getMessage());
            return DeleteListener.DeletionResult.INSTANCE.buildDeletionError();
        }
    }

    private static boolean isSDCardPermissionNeeded(Context context, Uri tracksUri) throws Exception {
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        try {
            try {
                Cursor cursorQuery = contentResolver.query(tracksUri, new String[]{"local_uri", "match_hash", "ownership_status"}, "local_uri is not NULL", null, null);
                if (cursorQuery == null || cursorQuery.getCount() == 0) {
                    throw new NullPointerException("FAILED! resolver.query returned null or empty result.");
                }
                int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("local_uri");
                int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("match_hash");
                int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("ownership_status");
                int count = cursorQuery.getCount();
                cursorQuery.moveToFirst();
                for (int i = 0; i < count; i++) {
                    String string = cursorQuery.getString(columnIndexOrThrow);
                    String string2 = cursorQuery.getString(columnIndexOrThrow2);
                    if (!(!ContentOwnershipStatus.fromValue(cursorQuery.getInt(columnIndexOrThrow3)).isOwned())) {
                        if (string == null) {
                            string = getLocalTrackUriForMatchHash(contentResolver, string2);
                        }
                        File file = new File(string);
                        if (file.exists()) {
                            if (file.canWrite()) {
                                cursorQuery.moveToNext();
                            } else {
                                DbUtil.closeCursor(cursorQuery);
                                return true;
                            }
                        }
                    }
                }
                DbUtil.closeCursor(cursorQuery);
                return false;
            } catch (Exception e) {
                throw e;
            }
        } catch (Throwable th) {
            DbUtil.closeCursor(null);
            throw th;
        }
    }

    public static DeleteListener.DeletionResult executeDeletionOnCurrentThread(final Context context, final Uri tracksUri, final boolean hasSDCardPermission) {
        return executeDeletionOnCurrentThread(context, tracksUri, hasSDCardPermission, false);
    }

    public static DeleteListener.DeletionResult executeDeletionOnCurrentThread(final Context context, final Uri tracksUri, final boolean hasSDCardPermission, final boolean deleteFromOfflineLibrary) throws Throwable {
        MusicTrack currentTrack;
        String str;
        SQLiteDatabase sQLiteDatabase;
        int i;
        int i2;
        String str2;
        boolean zDeleteFile;
        String str3 = "cirrus-local";
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        String source = MediaProvider.getSource(tracksUri);
        String str4 = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = tracksUri.toString();
        objArr[1] = source == null ? "null source" : source;
        Log.debug(str4, "deleting track %s from device, source = %s", objArr);
        Cursor cursorQuery = null;
        try {
            try {
            } catch (Exception e) {
                e = e;
            } catch (Throwable th) {
                th = th;
            }
            try {
                cursorQuery = contentResolver.query(tracksUri, new String[]{"local_uri", "luid", "match_hash", "asin", "ownership_status"}, "local_uri is not NULL", null, null);
                if (source != null && cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() != 0) {
                            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("local_uri");
                            int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("match_hash");
                            int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("luid");
                            int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow("asin");
                            int columnIndexOrThrow5 = cursorQuery.getColumnIndexOrThrow("ownership_status");
                            int count = cursorQuery.getCount();
                            HashMap map = new HashMap();
                            HashSet hashSet = new HashSet();
                            cursorQuery.moveToFirst();
                            int i3 = 0;
                            int i4 = 0;
                            while (i3 < count) {
                                String string = cursorQuery.getString(columnIndexOrThrow);
                                String string2 = cursorQuery.getString(columnIndexOrThrow2);
                                int i5 = columnIndexOrThrow2;
                                String string3 = cursorQuery.getString(columnIndexOrThrow4);
                                int i6 = columnIndexOrThrow4;
                                String string4 = cursorQuery.getString(columnIndexOrThrow3);
                                int i7 = cursorQuery.getInt(columnIndexOrThrow5);
                                if (string == null) {
                                    string = getLocalTrackUriForMatchHash(contentResolver, string2);
                                }
                                int i8 = columnIndexOrThrow5;
                                String str5 = string;
                                boolean z = !ContentOwnershipStatus.fromValue(i7).isOwned();
                                int i9 = columnIndexOrThrow;
                                SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
                                writableDatabase.beginTransaction();
                                int i10 = count;
                                boolean z2 = string3 != null && new LocalTrackUtil(writableDatabase).isTrackAlsoDownloadedInPrimePlaylist(string3);
                                if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                                    str = str3;
                                    Cursor cursorQueryIsTrackDownloadedInPrimePlaylistTracksTable = CirrusMediaSource.queryIsTrackDownloadedInPrimePlaylistTracksTable(new String[]{string3}, context);
                                    z2 = cursorQueryIsTrackDownloadedInPrimePlaylistTracksTable != null && cursorQueryIsTrackDownloadedInPrimePlaylistTracksTable.moveToFirst();
                                    cursorQueryIsTrackDownloadedInPrimePlaylistTracksTable.close();
                                } else {
                                    str = str3;
                                }
                                writableDatabase.setTransactionSuccessful();
                                writableDatabase.endTransaction();
                                if (z) {
                                    boolean zIsTrackInAnyDownloadedUserPlaylists = StringUtils.isNotBlank(string3) ? PlaylistTrackUtil.isTrackInAnyDownloadedUserPlaylists(context, string3) : false;
                                    if (!zIsTrackInAnyDownloadedUserPlaylists && StringUtils.isNotBlank(string4)) {
                                        zIsTrackInAnyDownloadedUserPlaylists = PlaylistTrackUtil.isTrackInAnyDownloadedUserPlaylists(context, string4);
                                    }
                                    str2 = source;
                                    i = i3;
                                    sQLiteDatabase = writableDatabase;
                                    i2 = i4;
                                    Log.debug(TAG, "DRM track: asin = [%s], luid = [%s], isTrackAlsoDownloadedInPrimePlaylist = [%s], isTrackInAnyDownloadedUserPlaylists = [%s] ", string3, string4, Boolean.valueOf(z2), Boolean.valueOf(zIsTrackInAnyDownloadedUserPlaylists));
                                    if (z2 || zIsTrackInAnyDownloadedUserPlaylists) {
                                        zDeleteFile = false;
                                    } else {
                                        MusicDownloader.getInstance(context).removeDownload(string3, null);
                                        zDeleteFile = true;
                                    }
                                } else {
                                    sQLiteDatabase = writableDatabase;
                                    i = i3;
                                    i2 = i4;
                                    str2 = source;
                                    if (!PlaylistTrackUtil.isTrackInAnyDownloadedUserPlaylists(context, string4)) {
                                        zDeleteFile = deleteFile(context, str5, hasSDCardPermission);
                                        Log.debug(TAG, "The library track %s being deleted is not in any downloaded user playlists", string4);
                                    } else {
                                        Log.debug(TAG, "The library track %s being deleted has associated downloaded user playlists", string4);
                                        zDeleteFile = false;
                                    }
                                }
                                if (zDeleteFile) {
                                    contentResolver.delete(LocalMediaSource.BASE_TRACK_URI, "_data IN ( ? )", new String[]{str5});
                                    map.put(cursorQuery.getString(columnIndexOrThrow3), str5);
                                    hashSet.add(string2);
                                    i4 = i2 + 1;
                                } else {
                                    if (LocalLuidGenerator.isLocalLuid(string4) && deleteFromOfflineLibrary) {
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("playlist_track_status", Integer.valueOf(MusicTrack.PlaylistTrackStatus.LOCAL_TRACK_IN_PLAYLIST_NOT_IN_OFFLINE_LIBRARY.getValue()));
                                        sQLiteDatabase.update("Track", contentValues, "luid=?", new String[]{string4});
                                        Log.debug(TAG, "Removing local track asin =[%s], luid =[%s] from offline library", string3, string4);
                                    }
                                    i4 = i2;
                                }
                                cursorQuery.moveToNext();
                                i3 = i + 1;
                                columnIndexOrThrow2 = i5;
                                columnIndexOrThrow4 = i6;
                                count = i10;
                                columnIndexOrThrow5 = i8;
                                columnIndexOrThrow = i9;
                                source = str2;
                                str3 = str;
                            }
                            String str6 = str3;
                            int i11 = count;
                            int i12 = i4;
                            DownloadFileUtil.updateTracksDownloadStateViaMatchHashes(context, hashSet, 5);
                            if (str6.equals(source)) {
                                deleteTracksByLuidsLocalUris(map);
                            }
                            CirrusDatabaseUtil.updateAllCachedTrackCounts(context);
                            NowPlayingManager nowPlayingManager = NowPlayingManager.getInstance();
                            if (nowPlayingManager.getTrackCount() == 1 && (currentTrack = nowPlayingManager.getCurrentTrack()) != null && str6.equals(currentTrack.getSource()) && tracksUri.equals(currentTrack.getContentUri())) {
                                NowPlayingUtil.clearAndFinishNowPlaying(AmazonApplication.getContext(), PlayStateMutationReason.TRACK_DELETED);
                            }
                            contentResolver.notifyChange(Uri.parse("content://media"), null);
                            contentResolver.notifyChange(Uri.parse("content://" + MediaProvider.AUTHORITY), null);
                            PlaylistUtil.refreshPlaylistArt(MediaProvider.SmartPlaylists.getContentUri(str6, 2L));
                            DbUtil.closeCursor(cursorQuery);
                            return DeleteListener.DeletionResult.INSTANCE.buildDeletionSuccess(i12 == i11, i12, i11);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.error(TAG, "Caught an exception : " + e.toString());
                        DeleteListener.DeletionResult deletionResultBuildDeletionError = DeleteListener.DeletionResult.INSTANCE.buildDeletionError();
                        DbUtil.closeCursor(cursorQuery);
                        return deletionResultBuildDeletionError;
                    }
                }
                Object[] objArr2 = new Object[2];
                objArr2[0] = tracksUri.toString();
                objArr2[1] = cursorQuery == null ? "null cursor" : Integer.valueOf(cursorQuery.getCount());
                Log.warning(str4, "Unexpected issue happens when deleting track %s from device, cursor count = %s", objArr2);
                DeleteListener.DeletionResult deletionResultBuildDeletionError2 = DeleteListener.DeletionResult.INSTANCE.buildDeletionError();
                DbUtil.closeCursor(cursorQuery);
                return deletionResultBuildDeletionError2;
            } catch (Exception e3) {
                e = e3;
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                cursorQuery = null;
                DbUtil.closeCursor(cursorQuery);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static int deleteTracksByLuids(String source, Collection<String> luids) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            String[] strArr = new String[1];
            LocalTrackUtil localTrackUtil = new LocalTrackUtil(writableDatabase);
            Iterator<String> it = luids.iterator();
            int iDelete = 0;
            while (it.hasNext()) {
                strArr[0] = it.next();
                writableDatabase.delete("PlaylistTrack", "track_luid=?", strArr);
                localTrackUtil.deleteTrackLocalUrisByLuids(strArr);
                iDelete += writableDatabase.delete("Track", "luid=?", strArr);
            }
            writableDatabase.setTransactionSuccessful();
            return iDelete;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static void deleteTrackByAsin(@NonNull String trackAsin) {
        deleteTrackByAsin(trackAsin, null);
    }

    public static void deleteTrackByAsin(@NonNull String trackAsin, final Function0<Unit> onDeleted) {
        MusicDownloader.getInstance(AmazonApplication.getContext()).removeDownload(trackAsin, onDeleted == null ? null : new DeleteListener() { // from class: com.amazon.mp3.library.provider.source.local.DeleteUtil.2
            @Override // com.amazon.mp3.library.provider.source.local.DeleteListener
            public void onComplete(final DeleteListener.DeletionResult result) {
                onDeleted.invoke();
            }
        });
    }

    private static int deleteTracksByLuidsLocalUris(Map<String, String> luidLocalUriMap) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            String[] strArr = new String[1];
            String[] strArr2 = new String[1];
            LocalTrackUtil localTrackUtil = new LocalTrackUtil(writableDatabase);
            int iDelete = 0;
            for (Map.Entry<String, String> entry : luidLocalUriMap.entrySet()) {
                strArr[0] = entry.getKey();
                strArr2[0] = entry.getValue();
                writableDatabase.delete("PlaylistTrack", "track_luid=?", strArr);
                localTrackUtil.deleteTracksLocalUrisByUris(strArr2);
                iDelete += writableDatabase.delete("Track", "luid=?", strArr);
            }
            writableDatabase.setTransactionSuccessful();
            return iDelete;
        } finally {
            writableDatabase.endTransaction();
        }
    }
}
