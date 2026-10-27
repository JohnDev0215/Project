package android.media;

import android.media.AudioMetadata;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioMetadataMap extends AudioMetadataReadMap {
    <T> T remove(AudioMetadata.Key<T> key);

    <T> T set(AudioMetadata.Key<T> key, T t);
}
