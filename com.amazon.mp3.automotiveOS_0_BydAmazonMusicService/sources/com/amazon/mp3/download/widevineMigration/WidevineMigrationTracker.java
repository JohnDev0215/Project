package com.amazon.mp3.download.widevineMigration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.DBUpdateUtil;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidevineMigrationTracker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001a\b\u0016\u0018\u0000 >2\u00020\u0001:\u0001>B\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b<\u0010=J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u000bH\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\fH\u0002J\u0006\u0010\u0013\u001a\u00020\u0002J\u0006\u0010\u0014\u001a\u00020\u0002R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001b\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\"\u0010&\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u0010,\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010'\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R\"\u0010/\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u0010'\u001a\u0004\b0\u0010)\"\u0004\b1\u0010+R\"\u00102\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u0010'\u001a\u0004\b3\u0010)\"\u0004\b4\u0010+R$\u00108\u001a\u00020%2\u0006\u00105\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R$\u0010;\u001a\u00020%2\u0006\u00105\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b9\u0010)\"\u0004\b:\u0010+¨\u0006?"}, d2 = {"Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTracker;", "", "", "evaluateRemainingLibraryTracks", "evaluateInEligibleLibraryTracks", "evaluateRemainingCatalogPlaylistTracks", "evaluateInEligibleCatalogPlaylistTracks", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "", "isInEligible", "Lcom/amazon/mp3/playlist/CatalogPlaylistTrack;", "Landroid/database/Cursor;", "tracksCursor", "", "Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationRequiredTrack;", "getLibraryTracksRequiredMigration", "catalogPlaylistTracksCursor", "getCatalogPlaylistTracksRequiredMigration", "init", "refresh", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/database/sqlite/SQLiteDatabase;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTrackerDAO;", "dao", "Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTrackerDAO;", "Landroid/content/SharedPreferences;", "sharedPref", "Landroid/content/SharedPreferences;", "", "remainingLibraryTracksCount", "I", "getRemainingLibraryTracksCount", "()I", "setRemainingLibraryTracksCount", "(I)V", "remainingCatalogPlaylistTracksCount", "getRemainingCatalogPlaylistTracksCount", "setRemainingCatalogPlaylistTracksCount", "inEligibleLibraryTracksCount", "getInEligibleLibraryTracksCount", "setInEligibleLibraryTracksCount", "inEligibleCatalogPlaylistTracksCount", "getInEligibleCatalogPlaylistTracksCount", "setInEligibleCatalogPlaylistTracksCount", "value", "getOriginalLibraryTracksCount", "setOriginalLibraryTracksCount", "originalLibraryTracksCount", "getOriginalCatalogPlaylistTracksCount", "setOriginalCatalogPlaylistTracksCount", "originalCatalogPlaylistTracksCount", "<init>", "(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class WidevineMigrationTracker {
    private static final String TAG = WidevineMigrationTrackerMetrics.class.getSimpleName();
    private final Context context;
    private final WidevineMigrationTrackerDAO dao;
    private final SQLiteDatabase db;
    private int inEligibleCatalogPlaylistTracksCount;
    private int inEligibleLibraryTracksCount;
    private int remainingCatalogPlaylistTracksCount;
    private int remainingLibraryTracksCount;
    private final SharedPreferences sharedPref;

    public WidevineMigrationTracker(Context context, SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        this.context = context;
        this.db = db;
        this.dao = new WidevineMigrationTrackerDAO(db);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.mp3.widevinemigration", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…    Context.MODE_PRIVATE)");
        this.sharedPref = sharedPreferences;
    }

    public final int getOriginalLibraryTracksCount() {
        return this.sharedPref.getInt("LibraryTrackCount", 0);
    }

    public final void setOriginalLibraryTracksCount(int i) {
        this.sharedPref.edit().putInt("LibraryTrackCount", i).apply();
    }

    public final int getOriginalCatalogPlaylistTracksCount() {
        return this.sharedPref.getInt("CatalogPlaylistTrackCount", 0);
    }

    public final void setOriginalCatalogPlaylistTracksCount(int i) {
        this.sharedPref.edit().putInt("CatalogPlaylistTrackCount", i).apply();
    }

    public final int getRemainingLibraryTracksCount() {
        return this.remainingLibraryTracksCount;
    }

    public final void setRemainingLibraryTracksCount(int i) {
        this.remainingLibraryTracksCount = i;
    }

    public final int getRemainingCatalogPlaylistTracksCount() {
        return this.remainingCatalogPlaylistTracksCount;
    }

    public final void setRemainingCatalogPlaylistTracksCount(int i) {
        this.remainingCatalogPlaylistTracksCount = i;
    }

    public final int getInEligibleLibraryTracksCount() {
        return this.inEligibleLibraryTracksCount;
    }

    public final void setInEligibleLibraryTracksCount(int i) {
        this.inEligibleLibraryTracksCount = i;
    }

    public final int getInEligibleCatalogPlaylistTracksCount() {
        return this.inEligibleCatalogPlaylistTracksCount;
    }

    public final void setInEligibleCatalogPlaylistTracksCount(int i) {
        this.inEligibleCatalogPlaylistTracksCount = i;
    }

    public final void init() {
        String str = TAG;
        Log.debug(str, "Init");
        this.dao.clearAll();
        DBUpdateUtil dBUpdateUtil = DBUpdateUtil.INSTANCE;
        List<WidevineMigrationRequiredTrack> libraryTracksRequiredMigration = getLibraryTracksRequiredMigration(dBUpdateUtil.getPreWidevineCloudTracksCursor(this.db));
        this.dao.insert(libraryTracksRequiredMigration);
        setOriginalLibraryTracksCount(libraryTracksRequiredMigration.size());
        List<WidevineMigrationRequiredTrack> catalogPlaylistTracksRequiredMigration = getCatalogPlaylistTracksRequiredMigration(dBUpdateUtil.getPreWidevinePrimePlaylistTracksCursor(this.db));
        this.dao.insert(catalogPlaylistTracksRequiredMigration);
        setOriginalCatalogPlaylistTracksCount(catalogPlaylistTracksRequiredMigration.size());
        Log.debug(str, "Init complete");
    }

    public final void refresh() {
        String str = TAG;
        Log.debug(str, "Refresh");
        evaluateRemainingLibraryTracks();
        evaluateInEligibleLibraryTracks();
        evaluateRemainingCatalogPlaylistTracks();
        evaluateInEligibleCatalogPlaylistTracks();
        Log.debug(str, "Refresh Complete");
    }

    private final void evaluateRemainingLibraryTracks() {
        List<WidevineMigrationRequiredTrack> tracksByType = this.dao.getTracksByType(0);
        List<WidevineMigrationRequiredTrack> libraryTracksRequiredMigration = getLibraryTracksRequiredMigration(DBUpdateUtil.INSTANCE.getNotDownloadedLocalTracksCursor(this.db));
        ArrayList arrayList = new ArrayList();
        for (Object obj : tracksByType) {
            if (!libraryTracksRequiredMigration.contains((WidevineMigrationRequiredTrack) obj)) {
                arrayList.add(obj);
            }
        }
        this.remainingLibraryTracksCount = tracksByType.size() - arrayList.size();
        this.dao.delete(arrayList);
    }

    private final void evaluateInEligibleLibraryTracks() {
        boolean zIsInEligible;
        List<WidevineMigrationRequiredTrack> tracksByType = this.dao.getTracksByType(0);
        CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(this.context);
        ArrayList arrayList = new ArrayList();
        for (Object obj : tracksByType) {
            if (!(((WidevineMigrationRequiredTrack) obj).getAsin().length() == 0)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            MusicTrack trackForAsin = cirrusSourceLibraryItemFactory.getTrackForAsin(((WidevineMigrationRequiredTrack) obj2).getAsin());
            if (trackForAsin != null) {
                Log.verbose(TAG, Intrinsics.stringPlus("Track: ", trackForAsin));
                zIsInEligible = isInEligible(trackForAsin);
            } else {
                zIsInEligible = false;
            }
            if (zIsInEligible) {
                arrayList2.add(obj2);
            }
        }
        this.inEligibleLibraryTracksCount = arrayList2.size();
    }

    private final void evaluateRemainingCatalogPlaylistTracks() {
        List<WidevineMigrationRequiredTrack> tracksByType = this.dao.getTracksByType(1);
        List<WidevineMigrationRequiredTrack> catalogPlaylistTracksRequiredMigration = getCatalogPlaylistTracksRequiredMigration(DBUpdateUtil.INSTANCE.getPrimePlaylistTracksCursorNotDownloadedAndNotInitated(this.db));
        ArrayList arrayList = new ArrayList();
        for (Object obj : tracksByType) {
            if (!catalogPlaylistTracksRequiredMigration.contains((WidevineMigrationRequiredTrack) obj)) {
                arrayList.add(obj);
            }
        }
        this.remainingCatalogPlaylistTracksCount = tracksByType.size() - arrayList.size();
        this.dao.delete(arrayList);
    }

    private final void evaluateInEligibleCatalogPlaylistTracks() {
        boolean zIsInEligible;
        List<WidevineMigrationRequiredTrack> tracksByType = this.dao.getTracksByType(1);
        PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager(this.context);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = tracksByType.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!(((WidevineMigrationRequiredTrack) next).getAsin().length() == 0)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            CatalogPlaylistTrack track = primePlaylistDatabaseManager.getTrack(((WidevineMigrationRequiredTrack) obj).getAsin(), 0);
            if (track != null) {
                Log.verbose(TAG, Intrinsics.stringPlus("Track: ", track));
                zIsInEligible = isInEligible(track);
            } else {
                zIsInEligible = false;
            }
            if (zIsInEligible) {
                arrayList2.add(obj);
            }
        }
        this.inEligibleCatalogPlaylistTracksCount = arrayList2.size();
    }

    private final boolean isInEligible(MusicTrack track) {
        if (!track.isPureCatalog() || track.isCatalogAccessible()) {
            return track.isAllPreviouslyCatalog() && track.isNotOwned();
        }
        return true;
    }

    private final boolean isInEligible(CatalogPlaylistTrack track) {
        return ContentAccessUtil.getCatalogContentStatusUnavailableReason(track.getCatalogStatus(), ContentAccessUtil.ContentAccessOperation.DOWNLOAD) != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0046 A[Catch: all -> 0x0057, TryCatch #0 {all -> 0x0057, blocks: (B:2:0x0000, B:5:0x000c, B:7:0x0012, B:11:0x0021, B:15:0x002f, B:20:0x003c, B:26:0x004e, B:25:0x0046), top: B:35:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<com.amazon.mp3.download.widevineMigration.WidevineMigrationRequiredTrack> getLibraryTracksRequiredMigration(android.database.Cursor r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L57
            r0.<init>()     // Catch: java.lang.Throwable -> L57
            boolean r1 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L57
            if (r1 != 0) goto Lc
            goto L52
        Lc:
            boolean r1 = r7.isAfterLast()     // Catch: java.lang.Throwable -> L57
            if (r1 != 0) goto L52
            java.lang.String r1 = "luid"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L57
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L57
            java.lang.String r2 = ""
            if (r1 != 0) goto L21
            r1 = r2
        L21:
            java.lang.String r3 = "asin"
            int r3 = r7.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L57
            java.lang.String r3 = r7.getString(r3)     // Catch: java.lang.Throwable -> L57
            if (r3 != 0) goto L2e
            goto L2f
        L2e:
            r2 = r3
        L2f:
            int r3 = r2.length()     // Catch: java.lang.Throwable -> L57
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L39
            r3 = r4
            goto L3a
        L39:
            r3 = r5
        L3a:
            if (r3 == 0) goto L46
            int r3 = r1.length()     // Catch: java.lang.Throwable -> L57
            if (r3 != 0) goto L43
            goto L44
        L43:
            r4 = r5
        L44:
            if (r4 != 0) goto L4e
        L46:
            com.amazon.mp3.download.widevineMigration.WidevineMigrationRequiredTrack r3 = new com.amazon.mp3.download.widevineMigration.WidevineMigrationRequiredTrack     // Catch: java.lang.Throwable -> L57
            r3.<init>(r2, r1, r5)     // Catch: java.lang.Throwable -> L57
            r0.add(r3)     // Catch: java.lang.Throwable -> L57
        L4e:
            r7.moveToNext()     // Catch: java.lang.Throwable -> L57
            goto Lc
        L52:
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r7, r1)
            return r0
        L57:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L59
        L59:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.widevineMigration.WidevineMigrationTracker.getLibraryTracksRequiredMigration(android.database.Cursor):java.util.List");
    }

    private final List<WidevineMigrationRequiredTrack> getCatalogPlaylistTracksRequiredMigration(Cursor catalogPlaylistTracksCursor) {
        try {
            ArrayList arrayList = new ArrayList();
            if (catalogPlaylistTracksCursor.moveToFirst()) {
                while (!catalogPlaylistTracksCursor.isAfterLast()) {
                    String string = catalogPlaylistTracksCursor.getString(catalogPlaylistTracksCursor.getColumnIndex("asin"));
                    if (string == null) {
                        string = "";
                    }
                    if (!(string.length() == 0)) {
                        arrayList.add(new WidevineMigrationRequiredTrack(string, "", 1));
                        catalogPlaylistTracksCursor.moveToNext();
                    }
                }
            }
            CloseableKt.closeFinally(catalogPlaylistTracksCursor, null);
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(catalogPlaylistTracksCursor, th);
                throw th2;
            }
        }
    }
}
