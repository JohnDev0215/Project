package com.facebook.imageutils;

import android.media.ExifInterface;
import androidx.annotation.RequiresApi;
import com.facebook.common.logging.FLog;
import com.facebook.soloader.DoNotOptimize;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public class HeifExifUtil {
    public static int getOrientation(final InputStream inputStream) {
        return HeifExifUtilAndroidN.getOrientation(inputStream);
    }

    @DoNotOptimize
    private static class HeifExifUtilAndroidN {
        private HeifExifUtilAndroidN() {
        }

        @RequiresApi(api = 24)
        static int getOrientation(final InputStream inputStream) {
            try {
                return new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
            } catch (IOException e) {
                FLog.d("HeifExifUtil", "Failed reading Heif Exif orientation -> ignoring", (Throwable) e);
                return 0;
            }
        }
    }
}
