package com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate;

import android.content.Context;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoveOfflineKatanaQualityDRMContentOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/RemoveOfflineKatanaQualityDRMContentOperation;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "removeAll", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/RemoveKatanaContentOperationResult;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RemoveOfflineKatanaQualityDRMContentOperation {
    public static final RemoveOfflineKatanaQualityDRMContentOperation INSTANCE = new RemoveOfflineKatanaQualityDRMContentOperation();
    private static final String TAG = RemoveOfflineKatanaQualityDRMContentOperation.class.getSimpleName();

    private RemoveOfflineKatanaQualityDRMContentOperation() {
    }

    public final RemoveKatanaContentOperationResult removeAll(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.debug(TAG, "Removing all offline katana content");
        try {
            for (MusicTrack musicTrack : TrackUtil.getKatanaQualityDownloadedTracks(context, "cirrus-local")) {
                Log.debug(TAG, Intrinsics.stringPlus("Removing track downloaded in katana quality:  ", musicTrack));
                DeleteUtil.deleteTracksFromDeviceOnCurrentThread(context, musicTrack.getContentUri());
            }
            for (CatalogPlaylist catalogPlaylist : PrimePlaylistUtil.getKatanaQualityDownloadedPrimePlaylists(context)) {
                Log.debug(TAG, Intrinsics.stringPlus("Removing prime playlist downloaded in katana quality: ", catalogPlaylist));
                PrimePlaylistUtil.deleteOfflinePrimePlaylist(context, catalogPlaylist.getLuid());
            }
            return RemoveKatanaContentOperationResult.SUCCESS;
        } catch (Exception unused) {
            return RemoveKatanaContentOperationResult.FAILED;
        }
    }
}
