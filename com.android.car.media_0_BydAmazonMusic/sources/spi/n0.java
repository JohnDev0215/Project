package spi;

import android.car.media.IXCarMediaService;
import android.car.utils.Logs;
import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import java.util.Objects;

/* JADX INFO: compiled from: BydBaseCarMediaServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n0 implements IXCarMediaService {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        Logs.d("XCarMediaManager", "BydCarMediaServiceImpl,init()");
    }

    @Override // android.car.media.IXCarMediaService
    public void onPlayStateChanged(MediaMetadataCompat mediaMetadataCompat, PlaybackStateCompat playbackStateCompat) {
        Objects.requireNonNull(t0.a);
    }

    @Override // android.car.media.IXCarMediaService
    public void onProgressChanged(long j, long j2) {
    }
}
