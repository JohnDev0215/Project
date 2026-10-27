package ts.car.service.carlife;

import ts.car.service.carlife.data.MediaMetaData;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaDataListener {
    default void onMediaMetadataChanged(MediaMetaData mediaDataInfo) {
    }

    default void onMediaProgressChanged(int progress) {
    }

    default void onMusicStatusChanged(int status) {
    }
}
