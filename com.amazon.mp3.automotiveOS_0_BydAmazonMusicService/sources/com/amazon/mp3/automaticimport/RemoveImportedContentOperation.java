package com.amazon.mp3.automaticimport;

import android.database.Cursor;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoveImportedContentOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/automaticimport/RemoveImportedContentOperation;", "", "()V", "IMPORTED_CONTENT_REMOVAL_FAILURE", "", "IMPORTED_CONTENT_REMOVAL_SUCCESS", "TAG", "kotlin.jvm.PlatformType", "removeImportedTracksFromTables", "", "cursor", "Landroid/database/Cursor;", "localTrackUtil", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/LocalTrackUtil;", "trackDataUtil", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/TrackDataUtil;", "run", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RemoveImportedContentOperation {
    public static final RemoveImportedContentOperation INSTANCE = new RemoveImportedContentOperation();
    private static final String TAG = RemoveImportedContentOperation.class.getSimpleName();
    private static final String IMPORTED_CONTENT_REMOVAL_SUCCESS = "importedContentRemovalSuccess";
    private static final String IMPORTED_CONTENT_REMOVAL_FAILURE = "importedContentRemovalFailure";

    private RemoveImportedContentOperation() {
    }

    public final boolean run(LocalTrackUtil localTrackUtil, TrackDataUtil trackDataUtil) {
        Intrinsics.checkNotNullParameter(localTrackUtil, "localTrackUtil");
        Intrinsics.checkNotNullParameter(trackDataUtil, "trackDataUtil");
        try {
            Cursor cursorQueryForAllImportedTracks = localTrackUtil.queryForAllImportedTracks();
            int count = cursorQueryForAllImportedTracks.getCount();
            int iRemoveImportedTracksFromTables = removeImportedTracksFromTables(cursorQueryForAllImportedTracks, localTrackUtil, trackDataUtil);
            Log.debug(TAG, Intrinsics.stringPlus("number of imported items deleted: ", Integer.valueOf(iRemoveImportedTracksFromTables)));
            boolean z = iRemoveImportedTracksFromTables == count;
            if (z) {
                MetricsLogger.sendEvent(FlexEvent.builder(IMPORTED_CONTENT_REMOVAL_SUCCESS).withFlexNum1(Float.valueOf(iRemoveImportedTracksFromTables)).build());
            } else {
                MetricsLogger.sendEvent(FlexEvent.builder(IMPORTED_CONTENT_REMOVAL_FAILURE).withFlexNum1(Float.valueOf(iRemoveImportedTracksFromTables)).withFlexNum2(Float.valueOf(count)).build());
            }
            return z;
        } catch (Exception unused) {
            MetricsLogger.sendEvent(FlexEvent.builder(IMPORTED_CONTENT_REMOVAL_FAILURE).build());
            return false;
        }
    }

    private final int removeImportedTracksFromTables(Cursor cursor, LocalTrackUtil localTrackUtil, TrackDataUtil trackDataUtil) {
        int i = 0;
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex("track_luid");
            if (cursor.moveToFirst()) {
                int i2 = 0;
                do {
                    String string = cursor.getString(columnIndex);
                    String[] strArr = {string};
                    localTrackUtil.deleteTrackLocalUrisByLuids(strArr);
                    int iDeleteTracksByLuid = trackDataUtil.deleteTracksByLuid(strArr);
                    if (iDeleteTracksByLuid < 1) {
                        Log.error(TAG, Intrinsics.stringPlus("Failed to delete Track: luid = ", string));
                    }
                    i2 += iDeleteTracksByLuid;
                } while (cursor.moveToNext());
                i = i2;
            }
            cursor.close();
        }
        return i;
    }
}
