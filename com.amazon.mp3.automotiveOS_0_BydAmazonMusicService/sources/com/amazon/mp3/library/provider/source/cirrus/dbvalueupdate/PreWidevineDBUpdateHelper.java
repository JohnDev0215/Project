package com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PreWidevineDBUpdateHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PreWidevineDBUpdateHelper;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PreWidevineDBUpdater;", "downloadStateUpdater", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DownloadStateUpdater;", "primePlaylistDownloadStateUpdater", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PrimePlaylistDownloadStateUpdater;", "(Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DownloadStateUpdater;Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PrimePlaylistDownloadStateUpdater;)V", "TAG", "", "kotlin.jvm.PlatformType", "combineResults", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DownloadStateUpdateResult;", "result1", "result2", "update", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PreWidevineDBUpdateResult;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PreWidevineDBUpdateHelper implements PreWidevineDBUpdater {
    private final String TAG;
    private final DownloadStateUpdater downloadStateUpdater;
    private final PrimePlaylistDownloadStateUpdater primePlaylistDownloadStateUpdater;

    /* JADX INFO: compiled from: PreWidevineDBUpdateHelper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadStateUpdateResult.values().length];
            iArr[DownloadStateUpdateResult.NO_TRACKS_TO_UPDATE.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PreWidevineDBUpdateHelper(DownloadStateUpdater downloadStateUpdater, PrimePlaylistDownloadStateUpdater primePlaylistDownloadStateUpdater) {
        Intrinsics.checkNotNullParameter(downloadStateUpdater, "downloadStateUpdater");
        Intrinsics.checkNotNullParameter(primePlaylistDownloadStateUpdater, "primePlaylistDownloadStateUpdater");
        this.downloadStateUpdater = downloadStateUpdater;
        this.primePlaylistDownloadStateUpdater = primePlaylistDownloadStateUpdater;
        this.TAG = PreWidevineDBUpdateHelper.class.getSimpleName();
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.PreWidevineDBUpdater
    public PreWidevineDBUpdateResult update(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Log.debug(this.TAG, "Begin updating Track Table tracks.");
        DownloadStateUpdater downloadStateUpdater = this.downloadStateUpdater;
        DBUpdateUtil dBUpdateUtil = DBUpdateUtil.INSTANCE;
        DownloadStateUpdateResult downloadStateUpdateResultUpdate = downloadStateUpdater.update(dBUpdateUtil.getDownloadedDRMTracksCursor(db), 6);
        DownloadStateUpdateResult downloadStateUpdateResult = DownloadStateUpdateResult.FAILED;
        if (downloadStateUpdateResultUpdate == downloadStateUpdateResult) {
            Log.debug(this.TAG, "Track Table tracks update failed.");
            return PreWidevineDBUpdateResult.FAILED;
        }
        Log.debug(this.TAG, "Begin updating PrimePlaylistTrack Table tracks.");
        DownloadStateUpdateResult downloadStateUpdateResultUpdate2 = this.primePlaylistDownloadStateUpdater.update(dBUpdateUtil.getDownloadedPrimePlaylistTracksCursor(db), 6);
        this.primePlaylistDownloadStateUpdater.updatePlaylist(dBUpdateUtil.getDownloadedPrimePlaylistCursor(db), 5);
        if (downloadStateUpdateResultUpdate2 == downloadStateUpdateResult) {
            Log.debug(this.TAG, "PrimePlaylistTrack Table tracks update failed.");
            return PreWidevineDBUpdateResult.FAILED;
        }
        if (WhenMappings.$EnumSwitchMapping$0[combineResults(downloadStateUpdateResultUpdate, downloadStateUpdateResultUpdate2).ordinal()] == 1) {
            return PreWidevineDBUpdateResult.NO_DRM_TRACKS_TO_UPDATE;
        }
        return PreWidevineDBUpdateResult.SUCCESS;
    }

    private final DownloadStateUpdateResult combineResults(DownloadStateUpdateResult result1, DownloadStateUpdateResult result2) {
        DownloadStateUpdateResult downloadStateUpdateResult = DownloadStateUpdateResult.NO_TRACKS_TO_UPDATE;
        return (result1 == downloadStateUpdateResult && result2 == downloadStateUpdateResult) ? downloadStateUpdateResult : DownloadStateUpdateResult.SUCCESS;
    }
}
