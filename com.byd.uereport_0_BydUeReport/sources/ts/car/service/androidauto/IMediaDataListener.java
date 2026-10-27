package ts.car.service.androidauto;

import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaDataListener {
    default void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
    }

    default void onMediaProgressChanged(int i8) {
    }

    default void onMusicStatusChanged(int i8) {
    }
}
