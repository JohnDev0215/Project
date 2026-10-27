package ts.car.service.androidauto;

import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaDataListener {
    default void onMusicStatusChanged(int status) {
    }

    default void onMediaMetadataChanged(MediaMetadata metadata) {
    }

    default void onMediaProgressChanged(int progress) {
    }
}
