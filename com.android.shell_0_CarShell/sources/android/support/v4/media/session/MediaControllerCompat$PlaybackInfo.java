package android.support.v4.media.session;

import androidx.media.AudioAttributesCompat;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat$PlaybackInfo {
    MediaControllerCompat$PlaybackInfo(int i, int i2, int i3, int i4, int i5) {
        AudioAttributesCompat.Builder builder = new AudioAttributesCompat.Builder();
        builder.setLegacyStreamType(i2);
        this(i, builder.build(), i3, i4, i5);
    }

    MediaControllerCompat$PlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
    }
}
