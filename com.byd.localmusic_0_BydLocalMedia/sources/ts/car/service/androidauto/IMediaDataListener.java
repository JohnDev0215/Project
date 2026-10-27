package ts.car.service.androidauto;

import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes5.dex */
public interface IMediaDataListener {
    default void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
    }

    default void onMediaProgressChanged(int i) {
    }

    default void onMusicStatusChanged(int i) {
    }
}
