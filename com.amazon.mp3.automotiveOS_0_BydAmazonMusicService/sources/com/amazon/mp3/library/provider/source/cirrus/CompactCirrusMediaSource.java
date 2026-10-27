package com.amazon.mp3.library.provider.source.cirrus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CompactCirrusMediaSource.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J3\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0011J=\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0014R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/CompactCirrusMediaSource;", "", "()V", "COMPACT_PLAYLISTS_PROJECTION", "", "", "[Ljava/lang/String;", "COMPACT_PRIME_PLAYLISTS_PROJECTION", "TAG", "kotlin.jvm.PlatformType", "getDb", "Landroid/database/sqlite/SQLiteDatabase;", "queryCompactPrimePlaylists", "Landroid/database/Cursor;", "selection", "selectionArgs", "limit", "(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "queryCompactUdoPlaylists", "sortOrder", "(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CompactCirrusMediaSource {
    public static final CompactCirrusMediaSource INSTANCE = new CompactCirrusMediaSource();
    private static final String TAG = CompactCirrusMediaSource.class.getSimpleName();
    private static final String[] COMPACT_PLAYLISTS_PROJECTION = {"_id", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "luid"};
    private static final String[] COMPACT_PRIME_PLAYLISTS_PROJECTION = {"_id", "\"vnd.android.cursor.item/vnd.amazonmp3.primeplaylist\" as type", "luid", "asin"};

    private CompactCirrusMediaSource() {
    }

    private final synchronized SQLiteDatabase getDb() {
        SQLiteDatabase readOnlyDatabase;
        readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getApplication().getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(readOnlyDatabase, "getReadOnlyDatabase(Amaz…ion().applicationContext)");
        return readOnlyDatabase;
    }

    @JvmStatic
    public static final synchronized Cursor queryCompactPrimePlaylists(String selection, String[] selectionArgs, String limit) {
        try {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("PrimePlaylists");
            Cursor cursorQuery = sQLiteQueryBuilder.query(INSTANCE.getDb(), COMPACT_PRIME_PLAYLISTS_PROJECTION, selection, selectionArgs, "PrimePlaylists._id", null, CirrusMediaSource.getPrimePlaylistSortOrder(), limit);
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Compact querying prime playlists failed.", e);
            return null;
        }
    }

    @JvmStatic
    public static final synchronized Cursor queryCompactUdoPlaylists(String selection, String[] selectionArgs, String sortOrder, String limit) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Playlist");
        try {
            Cursor cursorQuery = sQLiteQueryBuilder.query(INSTANCE.getDb(), COMPACT_PLAYLISTS_PROJECTION, selection, selectionArgs, "Playlist._id", null, (sortOrder == null || !StringsKt__StringsKt.contains$default((CharSequence) sortOrder, (CharSequence) "dateLastModified", false, 2, (Object) null)) ? CirrusMediaSource.getUdoPlaylistSortOrder() : "dateLastModified DESC", limit);
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Compact querying udo playlists failed.", e);
            return null;
        }
    }
}
