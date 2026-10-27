package com.amazon.mp3.prime.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: AsinSubstitutionHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0002J4\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J6\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0019J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\bH\u0002J,\u0010 \u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"2\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000fJ(\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0002¨\u0006'"}, d2 = {"Lcom/amazon/mp3/prime/data/AsinSubstitutionHandler;", "", "()V", "deleteDuplicateCatalogRecordsNewAsin", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "newTrackAsin", "", "getSubstitutedAlbumPurchasedTracks", "Landroid/database/Cursor;", "database", "albumAsin", "getTrackCursorForLocalTrackUri", "handleAlbumAsinSubstitution", "", "newAlbumAsin", "oldAlbumAsin", "tracks", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "isMatchingSubstitutedTrack", "", "oldTitle", "oldDiscNum", "", "oldTrackNum", "newTitle", "newDiscNum", "newTrackNum", "isTrackDownloaded", "oldTrackAsin", "redownloadSubstitutedTracks", "context", "Landroid/content/Context;", "substitutedAsins", "updateTrackAfterAsinSubstitution", "luid", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AsinSubstitutionHandler {
    private static final ConcurrentHashMap<String, Object> asinLocksMap = new ConcurrentHashMap<>();
    private static final String[] TRACKS_PROJECTION = {"luid", "asin", "title", "disc_num", "track_num"};
    private static final String ALBUM_OWNED_TRACKS_CLAUSE = "source=0 AND ownership_status=" + ContentOwnershipStatus.OWNED.getValue() + " AND luid IS NOT NULL  AND album_asin=?";

    public final Map<String, String> handleAlbumAsinSubstitution(String newAlbumAsin, String oldAlbumAsin, Collection<? extends PrimeTrack> tracks) throws AsinSubstitutionException {
        String str;
        Object next;
        Intrinsics.checkNotNullParameter(newAlbumAsin, "newAlbumAsin");
        Intrinsics.checkNotNullParameter(oldAlbumAsin, "oldAlbumAsin");
        if (!Intrinsics.areEqual(newAlbumAsin, oldAlbumAsin)) {
            if (!(tracks == null || tracks.isEmpty())) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                ConcurrentHashMap<String, Object> concurrentHashMap = asinLocksMap;
                Object obj = concurrentHashMap.get(newAlbumAsin);
                if (obj == null) {
                    obj = new Object();
                    concurrentHashMap.putIfAbsent(newAlbumAsin, obj);
                }
                Object obj2 = obj;
                synchronized (obj2) {
                    concurrentHashMap.putIfAbsent(newAlbumAsin, obj2);
                    SQLiteDatabase db = CirrusDatabase.getWritableDatabase(AmazonApplication.getApplication().getApplicationContext());
                    Intrinsics.checkNotNullExpressionValue(db, "db");
                    Cursor substitutedAlbumPurchasedTracks = getSubstitutedAlbumPurchasedTracks(db, oldAlbumAsin);
                    if (substitutedAlbumPurchasedTracks != null) {
                        while (substitutedAlbumPurchasedTracks.moveToNext()) {
                            try {
                                int columnIndex = substitutedAlbumPurchasedTracks.getColumnIndex("asin");
                                String string = substitutedAlbumPurchasedTracks.isNull(columnIndex) ? null : substitutedAlbumPurchasedTracks.getString(columnIndex);
                                int columnIndex2 = substitutedAlbumPurchasedTracks.getColumnIndex("title");
                                String string2 = substitutedAlbumPurchasedTracks.isNull(columnIndex2) ? null : substitutedAlbumPurchasedTracks.getString(columnIndex2);
                                if (string2 == null) {
                                    string2 = "";
                                }
                                String str2 = string2;
                                int columnIndex3 = substitutedAlbumPurchasedTracks.getColumnIndex("disc_num");
                                Integer numValueOf = substitutedAlbumPurchasedTracks.isNull(columnIndex3) ? null : Integer.valueOf(substitutedAlbumPurchasedTracks.getInt(columnIndex3));
                                int iIntValue = -1;
                                int iIntValue2 = numValueOf == null ? -1 : numValueOf.intValue();
                                int columnIndex4 = substitutedAlbumPurchasedTracks.getColumnIndex("track_num");
                                Integer numValueOf2 = substitutedAlbumPurchasedTracks.isNull(columnIndex4) ? null : Integer.valueOf(substitutedAlbumPurchasedTracks.getInt(columnIndex4));
                                if (numValueOf2 != null) {
                                    iIntValue = numValueOf2.intValue();
                                }
                                int i = iIntValue;
                                Iterator<T> it = tracks.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        str = str2;
                                        next = null;
                                        break;
                                    }
                                    next = it.next();
                                    PrimeTrack primeTrack = (PrimeTrack) next;
                                    String title = primeTrack.getTitle();
                                    if (title == null) {
                                        title = "";
                                    }
                                    str = str2;
                                    if (isMatchingSubstitutedTrack(str2, iIntValue2, i, title, primeTrack.getDiscNum(), primeTrack.getTrackNum())) {
                                        break;
                                    }
                                    str2 = str;
                                }
                                PrimeTrack primeTrack2 = (PrimeTrack) next;
                                String albumAsin = primeTrack2 == null ? null : primeTrack2.getAlbumAsin();
                                String asin = primeTrack2 == null ? null : primeTrack2.getAsin();
                                if (albumAsin != null && asin != null) {
                                    deleteDuplicateCatalogRecordsNewAsin(db, asin);
                                    int columnIndex5 = substitutedAlbumPurchasedTracks.getColumnIndex("luid");
                                    String string3 = substitutedAlbumPurchasedTracks.isNull(columnIndex5) ? null : substitutedAlbumPurchasedTracks.getString(columnIndex5);
                                    if (string3 != null) {
                                        updateTrackAfterAsinSubstitution(db, string3, asin, albumAsin);
                                    }
                                    linkedHashMap.put(albumAsin, oldAlbumAsin);
                                    linkedHashMap.put(asin, string);
                                } else {
                                    throw new AsinSubstitutionException("Asin substitution failed to find a match for owned Track " + str + "; " + ((Object) string) + " in Album " + oldAlbumAsin);
                                }
                            } finally {
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(substitutedAlbumPurchasedTracks, null);
                    }
                    asinLocksMap.remove(newAlbumAsin);
                }
                return linkedHashMap;
            }
        }
        return MapsKt__MapsKt.emptyMap();
    }

    private final Cursor getSubstitutedAlbumPurchasedTracks(SQLiteDatabase database, String albumAsin) {
        return database.query("Track", TRACKS_PROJECTION, ALBUM_OWNED_TRACKS_CLAUSE, new String[]{albumAsin}, null, null, null);
    }

    public final boolean isMatchingSubstitutedTrack(String oldTitle, int oldDiscNum, int oldTrackNum, String newTitle, int newDiscNum, int newTrackNum) {
        Intrinsics.checkNotNullParameter(oldTitle, "oldTitle");
        Intrinsics.checkNotNullParameter(newTitle, "newTitle");
        return oldDiscNum == newDiscNum && oldDiscNum > 0 && oldTrackNum == newTrackNum && oldTrackNum > 0 && (StringsKt__StringsJVMKt.equals(oldTitle, newTitle, true) || StringsKt__StringsKt.contains((CharSequence) oldTitle, (CharSequence) newTitle, true) || StringsKt__StringsKt.contains((CharSequence) newTitle, (CharSequence) oldTitle, true));
    }

    private final void deleteDuplicateCatalogRecordsNewAsin(SQLiteDatabase db, String newTrackAsin) {
        db.delete("Track", "luid IS NULL  AND asin=?", new String[]{newTrackAsin});
    }

    private final void updateTrackAfterAsinSubstitution(SQLiteDatabase db, String luid, String newTrackAsin, String newAlbumAsin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("asin", newTrackAsin);
        contentValues.put("album_asin", newAlbumAsin);
        db.update("Track", contentValues, "luid=?", new String[]{luid});
    }

    private final Cursor getTrackCursorForLocalTrackUri(SQLiteDatabase database, String albumAsin) {
        return database.query("LocalTrackUri", new String[]{"track_asin", "track_luid"}, "track_asin=? AND track_luid IS NOT NULL", new String[]{albumAsin}, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTrackDownloaded(SQLiteDatabase database, String oldTrackAsin) {
        Cursor trackCursorForLocalTrackUri = getTrackCursorForLocalTrackUri(database, oldTrackAsin);
        if (trackCursorForLocalTrackUri == null) {
            return false;
        }
        try {
            if (trackCursorForLocalTrackUri.moveToFirst()) {
                CloseableKt.closeFinally(trackCursorForLocalTrackUri, null);
                return true;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(trackCursorForLocalTrackUri, null);
            return false;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(trackCursorForLocalTrackUri, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.data.AsinSubstitutionHandler$redownloadSubstitutedTracks$1, reason: invalid class name */
    /* JADX INFO: compiled from: AsinSubstitutionHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.prime.data.AsinSubstitutionHandler$redownloadSubstitutedTracks$1", f = "AsinSubstitutionHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Map<String, String> $substitutedAsins;
        int label;
        final /* synthetic */ AsinSubstitutionHandler this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Map<String, String> map, Context context, AsinSubstitutionHandler asinSubstitutionHandler, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$substitutedAsins = map;
            this.$context = context;
            this.this$0 = asinSubstitutionHandler;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$substitutedAsins, this.$context, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Map<String, String> map = this.$substitutedAsins;
            Context context = this.$context;
            AsinSubstitutionHandler asinSubstitutionHandler = this.this$0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value != null) {
                    SQLiteDatabase db = CirrusDatabase.getWritableDatabase(context);
                    Intrinsics.checkNotNullExpressionValue(db, "db");
                    if (asinSubstitutionHandler.isTrackDownloaded(db, value)) {
                        DeleteUtil.deleteTracksFromDevice(context, new CirrusSourceLibraryItemFactory(context).getUriForAsin(key));
                        Uri uriForAsin = new CirrusSourceLibraryItemFactory(context).getUriForAsin(key);
                        MusicDownloader.getInstance(context).download(uriForAsin.toString(), uriForAsin, null, false, true);
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void redownloadSubstitutedTracks(SQLiteDatabase db, Context context, Map<String, String> substitutedAsins) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(substitutedAsins, "substitutedAsins");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(substitutedAsins, context, this, null), 2, null);
    }
}
