package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.Log;
import com.amazon.music.ContentAccessType;
import com.facebook.react.bridge.BaseJavaModule;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatalogStatusTiersOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\bH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\u0018\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\u0018\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\bH\u0002J\u0018\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\bH\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/library/service/sync/CatalogStatusTiersOperation;", "Lcom/amazon/mp3/library/service/sync/SyncOperation;", "context", "Landroid/content/Context;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V", "TAG", "", "kotlin.jvm.PlatformType", "getMatchingTrackCursor", "Landroid/database/Cursor;", "asin", "getUpdatedContentCatalogStatus", "Lcom/amazon/mp3/prime/ContentCatalogStatus;", "catalogStatusTiers", "Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers;", "contentCatalogStatus", BaseJavaModule.METHOD_TYPE_SYNC, "", "updateCatalogStatusTiers", "", "cursor", "updateContentCatalogStatus", "updateMissingCatalogStatusTiers", "updateTrackByAsin", "values", "Landroid/content/ContentValues;", "updateTrackByLuid", "luid", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CatalogStatusTiersOperation extends SyncOperation {
    private final String TAG;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] TRACKS_PROJECTION = {"luid", "asin", "ownership_status", "prime_status", "catalog_status_tiers"};
    private static final String[] PLAYLIST_TRACKS_PROJECTION = {"asin", "catalog_status", "catalog_status_tiers"};

    @JvmStatic
    public static final void setShouldPerformSync(Context context, boolean z) {
        INSTANCE.setShouldPerformSync(context, z);
    }

    @JvmStatic
    public static final boolean shouldPerformContentCatalogStatusSync(Context context) {
        return INSTANCE.shouldPerformContentCatalogStatusSync(context);
    }

    @JvmStatic
    public static final boolean shouldPerformFullSync(Context context) {
        return INSTANCE.shouldPerformFullSync(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogStatusTiersOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        this.TAG = CatalogStatusTiersOperation.class.getSimpleName();
    }

    /* JADX INFO: compiled from: CatalogStatusTiersOperation.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/library/service/sync/CatalogStatusTiersOperation$Companion;", "", "()V", "PLAYLIST_TRACKS_PROJECTION", "", "", "[Ljava/lang/String;", "TRACKS_PROJECTION", "containsCatalogStatusPlaylistTracksClause", "containsCatalogStatusTracksClause", "matchedTrackClause", "missingCatalogClause", "setShouldPerformSync", "", "context", "Landroid/content/Context;", "performSync", "", "shouldPerformContentCatalogStatusSync", "shouldPerformFullSync", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean shouldPerformFullSync(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return Intrinsics.areEqual(SyncService.getSonicRushFullTrackSync(context), "perform_sync");
        }

        @JvmStatic
        public final boolean shouldPerformContentCatalogStatusSync(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return Feature.sonic_rush.isEnabled() && Intrinsics.areEqual(SyncService.getContentCatalogStatusSync(context), "perform_sync");
        }

        @JvmStatic
        public final void setShouldPerformSync(Context context, boolean performSync) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (performSync) {
                SyncService.setSonicRushFullTrackSync(context, "perform_sync");
            } else {
                SyncService.setSonicRushFullTrackSync(context, "sync_not_needed");
                SyncService.setContentCatalogStatusSync(context, "sync_not_needed");
            }
        }
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    protected int sync() {
        Companion companion = INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (companion.shouldPerformFullSync(context)) {
            updateMissingCatalogStatusTiers();
            SyncService.setSonicRushFullTrackSync(getContext(), "sync_complete");
        }
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        if (!companion.shouldPerformContentCatalogStatusSync(context2)) {
            return 0;
        }
        updateContentCatalogStatus();
        SyncService.setContentCatalogStatusSync(getContext(), "sync_complete");
        return 0;
    }

    private final void updateContentCatalogStatus() {
        Cursor cursorQuery = getDatabase().query("Track", TRACKS_PROJECTION, "catalog_status_tiers!= 0", null, null, null, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    CatalogStatusTiers catalogStatusTiersDeserialize = CatalogStatusTiers.INSTANCE.deserialize(cursorQuery.getInt(cursorQuery.getColumnIndex("catalog_status_tiers")));
                    ContentCatalogStatus currentCatalogStatus = ContentCatalogStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("prime_status")));
                    Intrinsics.checkNotNullExpressionValue(currentCatalogStatus, "currentCatalogStatus");
                    if (getUpdatedContentCatalogStatus(catalogStatusTiersDeserialize, currentCatalogStatus) != null) {
                        ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.fromValues(catalogStatusTiersDeserialize, ContentOwnershipStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status"))).isOwned(), false);
                        Intrinsics.checkNotNullExpressionValue(contentCatalogStatus, "contentCatalogStatus");
                        updateContentCatalogStatus(cursorQuery, contentCatalogStatus);
                    }
                } finally {
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorQuery, null);
        }
        cursorQuery = getDatabase().query("PrimePlaylistTracks", PLAYLIST_TRACKS_PROJECTION, "catalog_status_tiers!= 0", null, null, null, null);
        if (cursorQuery == null) {
            return;
        }
        while (cursorQuery.moveToNext()) {
            try {
                CatalogStatusTiers catalogStatusTiersDeserialize2 = CatalogStatusTiers.INSTANCE.deserialize(cursorQuery.getInt(cursorQuery.getColumnIndex("catalog_status_tiers")));
                ContentCatalogStatus currentCatalogStatus2 = ContentCatalogStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("catalog_status")));
                Intrinsics.checkNotNullExpressionValue(currentCatalogStatus2, "currentCatalogStatus");
                ContentCatalogStatus updatedContentCatalogStatus = getUpdatedContentCatalogStatus(catalogStatusTiersDeserialize2, currentCatalogStatus2);
                if (updatedContentCatalogStatus != null) {
                    ContentValues contentValues = new ContentValues();
                    String asin = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
                    contentValues.put("catalog_status", Integer.valueOf(updatedContentCatalogStatus.getValue()));
                    if (asin != null) {
                        SQLiteDatabase database = getDatabase();
                        Intrinsics.checkNotNullExpressionValue(asin, "asin");
                        database.update("PrimePlaylistTracks", contentValues, "asin = ?", new String[]{asin});
                    }
                }
            } finally {
            }
        }
        Unit unit2 = Unit.INSTANCE;
        CloseableKt.closeFinally(cursorQuery, null);
    }

    private final ContentCatalogStatus getUpdatedContentCatalogStatus(CatalogStatusTiers catalogStatusTiers, ContentCatalogStatus contentCatalogStatus) {
        ContentCatalogStatus contentCatalogStatusFromValues;
        if (!catalogStatusTiers.getIsSonicRush() || catalogStatusTiers.getIsPrime() || contentCatalogStatus == (contentCatalogStatusFromValues = ContentCatalogStatus.fromValues(catalogStatusTiers, false, false))) {
            return null;
        }
        return contentCatalogStatusFromValues;
    }

    private final void updateMissingCatalogStatusTiers() {
        Cursor matchingTrackCursor;
        Integer numValueOf;
        Cursor cursorQuery = getDatabase().query("Track", TRACKS_PROJECTION, "prime_status>175 AND prime_status<400 AND catalog_status_tiers= 0", null, null, null, null);
        if (cursorQuery == null) {
            return;
        }
        while (true) {
            try {
                Unit unit = null;
                if (cursorQuery.moveToNext()) {
                    int columnIndex = cursorQuery.getColumnIndex("asin");
                    String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                    if (string == null || (matchingTrackCursor = getMatchingTrackCursor(string)) == null) {
                        numValueOf = null;
                    } else {
                        try {
                            if (matchingTrackCursor.getCount() > 0) {
                                matchingTrackCursor.moveToFirst();
                                numValueOf = Integer.valueOf(matchingTrackCursor.getInt(matchingTrackCursor.getColumnIndex("catalog_status_tiers")));
                            } else {
                                numValueOf = null;
                            }
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(matchingTrackCursor, null);
                        } finally {
                        }
                    }
                    if (numValueOf != null) {
                        updateCatalogStatusTiers(cursorQuery, numValueOf.intValue());
                        unit = Unit.INSTANCE;
                    }
                    if (unit == null) {
                        updateCatalogStatusTiers(cursorQuery);
                    }
                } else {
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursorQuery, null);
                    return;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorQuery, th);
                    throw th2;
                }
            }
        }
    }

    private final Cursor getMatchingTrackCursor(String asin) {
        return getDatabase().query("Track", TRACKS_PROJECTION, "catalog_status_tiers!= 0 AND asin=?", new String[]{asin}, null, null, null);
    }

    private final void updateCatalogStatusTiers(Cursor cursor) {
        ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursor.getInt(cursor.getColumnIndex("prime_status")));
        CatalogStatusTiers.Companion companion = CatalogStatusTiers.INSTANCE;
        Set<ContentAccessType> supportedTiers = contentCatalogStatusFromValue.getSupportedTiers();
        Intrinsics.checkNotNullExpressionValue(supportedTiers, "catalogStatus.supportedTiers");
        updateCatalogStatusTiers(cursor, companion.from(supportedTiers).serialize());
    }

    private final void updateCatalogStatusTiers(Cursor cursor, int catalogStatusTiers) {
        int columnIndex = cursor.getColumnIndex("luid");
        String string = cursor.isNull(columnIndex) ? null : cursor.getString(columnIndex);
        int columnIndex2 = cursor.getColumnIndex("asin");
        String string2 = cursor.isNull(columnIndex2) ? null : cursor.getString(columnIndex2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("catalog_status_tiers", Integer.valueOf(catalogStatusTiers));
        if (string2 != null) {
            updateTrackByAsin(contentValues, string2);
        } else if (string != null) {
            updateTrackByLuid(contentValues, string);
        }
    }

    private final void updateContentCatalogStatus(Cursor cursor, ContentCatalogStatus contentCatalogStatus) {
        int columnIndex = cursor.getColumnIndex("luid");
        String string = cursor.isNull(columnIndex) ? null : cursor.getString(columnIndex);
        int columnIndex2 = cursor.getColumnIndex("asin");
        String string2 = cursor.isNull(columnIndex2) ? null : cursor.getString(columnIndex2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("prime_status", Integer.valueOf(contentCatalogStatus.getValue()));
        if (string2 != null) {
            updateTrackByAsin(contentValues, string2);
        } else if (string != null) {
            updateTrackByLuid(contentValues, string);
        }
    }

    private final void updateTrackByAsin(ContentValues values, String asin) {
        try {
            getDatabase().update("Track", values, "asin=?", new String[]{asin});
        } catch (Exception e) {
            Log.debug(this.TAG, "Unable to update catalogStatusTier for asin: " + asin + " - " + ((Object) e.getMessage()));
        }
    }

    private final void updateTrackByLuid(ContentValues values, String luid) {
        try {
            getDatabase().update("Track", values, "luid=?", new String[]{luid});
        } catch (Exception e) {
            Log.debug(this.TAG, "Unable to update catalogStatusTier for luid: " + luid + "  - " + ((Object) e.getMessage()));
        }
    }
}
