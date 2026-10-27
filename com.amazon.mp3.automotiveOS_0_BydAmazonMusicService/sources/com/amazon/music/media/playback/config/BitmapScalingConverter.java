package com.amazon.music.media.playback.config;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.config.ConvertingItemLoader;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapScalingConverter implements ConvertingItemLoader.Converter<Bitmap, Bitmap> {
    private int height;
    private int width;

    public BitmapScalingConverter(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int getTargetWidth() {
        return this.width;
    }

    public int getTargetHeight() {
        return this.height;
    }

    @Override // com.amazon.music.media.playback.config.ConvertingItemLoader.Converter
    @NonNull
    public Bitmap convert(@NonNull Bitmap bitmap) throws IOException {
        return convert(bitmap, false);
    }

    @Override // com.amazon.music.media.playback.config.ConvertingItemLoader.Converter
    @Nullable
    public Bitmap fastConvert(@NonNull Bitmap bitmap) {
        return convert(bitmap, true);
    }

    private Bitmap convert(@NonNull Bitmap bitmap, boolean z) {
        int iRound;
        int iRound2;
        int i = this.width;
        if (i <= 0 && this.height <= 0) {
            return bitmap;
        }
        float width = i > 0 ? bitmap.getWidth() / this.width : Float.MAX_VALUE;
        float height = this.height > 0 ? bitmap.getHeight() / this.height : Float.MAX_VALUE;
        if (width <= 2.0f || height <= 2.0f) {
            return bitmap;
        }
        if (z) {
            return null;
        }
        if (width < height) {
            iRound2 = this.width;
            iRound = Math.round(bitmap.getHeight() / width);
        } else {
            iRound = this.height;
            iRound2 = Math.round(bitmap.getWidth() / height);
        }
        return Bitmap.createScaledBitmap(bitmap, iRound2, iRound, true);
    }
}
