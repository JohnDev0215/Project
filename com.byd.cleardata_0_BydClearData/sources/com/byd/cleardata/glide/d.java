package com.byd.cleardata.glide;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import com.bumptech.glide.g;
import com.bumptech.glide.load.data.d;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class d implements com.bumptech.glide.load.data.d<Bitmap> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f2661b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    MediaMetadataRetriever.BitmapParams f2662c;

    public d(f fVar) {
        MediaMetadataRetriever.BitmapParams bitmapParams = new MediaMetadataRetriever.BitmapParams();
        this.f2662c = bitmapParams;
        this.f2661b = fVar;
        bitmapParams.setPreferredConfig(Bitmap.Config.RGB_565);
    }

    @Override // com.bumptech.glide.load.data.d
    public Class<Bitmap> a() {
        return Bitmap.class;
    }

    @Override // com.bumptech.glide.load.data.d
    public void b() {
    }

    @Override // com.bumptech.glide.load.data.d
    public com.bumptech.glide.load.a c() {
        return com.bumptech.glide.load.a.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.d
    public void cancel() {
    }

    public Bitmap d(f fVar, int i) throws IOException {
        Bitmap frameAtTime;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(fVar.a());
                int i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                int i3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                com.byd.cleardata.i.c.b("video width:" + i2 + ", height:" + i3);
                if (i2 * i3 > 24000) {
                    com.byd.cleardata.i.c.b("video is too large");
                    frameAtTime = mediaMetadataRetriever.getScaledFrameAtTime(0L, 2, 200, 120);
                } else {
                    frameAtTime = mediaMetadataRetriever.getFrameAtTime(0L, 2);
                }
                return frameAtTime;
            } catch (Exception e) {
                com.byd.cleardata.i.c.d("getVideoShotCut failed", e);
                mediaMetadataRetriever.release();
                return null;
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public void e(g gVar, d.a<? super Bitmap> aVar) throws IOException {
        com.byd.cleardata.i.c.b("loadData:" + this.f2661b.a());
        Bitmap bitmapD = d(this.f2661b, 0);
        if (bitmapD != null) {
            aVar.f(bitmapD);
        } else {
            com.byd.cleardata.i.c.b("without imageData!");
            aVar.d(new Exception("without imageData!"));
        }
    }
}
