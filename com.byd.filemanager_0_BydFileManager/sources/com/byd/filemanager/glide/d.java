package com.byd.filemanager.glide;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import java.io.IOException;

/* JADX INFO: compiled from: VideoFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements com.bumptech.glide.load.data.d<Bitmap> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f3565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    MediaMetadataRetriever.BitmapParams f3566c;

    public d(f fVar) {
        MediaMetadataRetriever.BitmapParams bitmapParams = new MediaMetadataRetriever.BitmapParams();
        this.f3566c = bitmapParams;
        this.f3565b = fVar;
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
    public DataSource c() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.d
    public void cancel() {
    }

    public Bitmap d(f fVar, int i) throws IOException {
        float fMax = Math.max(200, 120);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        Bitmap frameAtTime = null;
        try {
            try {
                mediaMetadataRetriever.setDataSource(fVar.a());
                String strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
                String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                if (!TextUtils.isEmpty(strExtractMetadata) && !TextUtils.isEmpty(strExtractMetadata2) && (frameAtTime = mediaMetadataRetriever.getFrameAtTime()) != null) {
                    int width = frameAtTime.getWidth();
                    int height = frameAtTime.getHeight();
                    float fMax2 = Math.max(width, height);
                    if (fMax2 > fMax) {
                        float f = fMax / fMax2;
                        frameAtTime = Bitmap.createScaledBitmap(frameAtTime, Math.round(width * f), Math.round(f * height), true);
                    }
                }
            } catch (Exception e2) {
                com.byd.filemanager.k.b.d("getVideoShotCut failed", e2);
            }
            return frameAtTime;
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public void e(Priority priority, d.a<? super Bitmap> aVar) throws IOException {
        Bitmap bitmapD = d(this.f3565b, 0);
        if (bitmapD != null) {
            aVar.f(bitmapD);
        } else {
            com.byd.filemanager.k.b.b("without imageData!");
            aVar.d(new Exception("without imageData!"));
        }
    }
}
