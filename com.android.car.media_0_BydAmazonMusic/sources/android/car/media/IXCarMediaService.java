package android.car.media;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX INFO: loaded from: classes.dex */
public interface IXCarMediaService extends IProvider {
    public static final String TAG = "XCarMediaManager";

    void onPlayStateChanged(MediaMetadataCompat mediaMetadataCompat, PlaybackStateCompat playbackStateCompat);

    void onProgressChanged(long j, long j2);
}
