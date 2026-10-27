package com.bumptech.glide.integration.webp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import com.bumptech.glide.integration.webp.WebpHeaderParser;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class WebpBitmapFactory {
    private static final int IN_TEMP_BUFFER_SIZE = 8192;
    private static final int MAX_WEBP_HEADER_SIZE = 21;

    private static native Bitmap nativeDecodeByteArray(byte[] bArr, int i, int i2, BitmapFactory.Options options, float f, byte[] bArr2);

    private static native Bitmap nativeDecodeStream(InputStream inputStream, BitmapFactory.Options options, float f, byte[] bArr);

    static {
        System.loadLibrary("glide-webp");
    }

    private static void setDensityFromOptions(Bitmap bitmap, BitmapFactory.Options options) {
        if (bitmap == null || options == null) {
            return;
        }
        int i = options.inDensity;
        if (i != 0) {
            bitmap.setDensity(i);
            int i2 = options.inTargetDensity;
            if (i2 == 0 || i == i2 || i == options.inScreenDensity || !options.inScaled) {
                return;
            }
            bitmap.setDensity(i2);
            return;
        }
        if (options.inBitmap != null) {
            bitmap.setDensity(160);
        }
    }

    private static void setWebpBitmapOptions(Bitmap bitmap, BitmapFactory.Options options) {
        setDensityFromOptions(bitmap, options);
        if (options != null) {
            options.outMimeType = "image/webp";
        }
    }

    private static boolean setOutDimensions(BitmapFactory.Options options, int i, int i2) {
        if (options == null) {
            return false;
        }
        options.outWidth = i;
        options.outHeight = i2;
        return options.inJustDecodeBounds;
    }

    private static Bitmap createBitmap(int i, int i2, BitmapFactory.Options options) {
        if (options != null && options.inBitmap != null && options.inBitmap.isMutable()) {
            return options.inBitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setHasAlpha(true);
        bitmapCreateBitmap.eraseColor(0);
        return bitmapCreateBitmap;
    }

    private static void setDefaultPadding(Rect rect) {
        if (rect != null) {
            rect.top = -1;
            rect.left = -1;
            rect.bottom = -1;
            rect.right = -1;
        }
    }

    public static boolean webpSupportRequired(byte[] bArr, int i, int i2) {
        WebpHeaderParser.WebpImageType type;
        try {
            type = WebpHeaderParser.getType(bArr, i, i2);
        } catch (IOException unused) {
            type = WebpHeaderParser.WebpImageType.NONE_WEBP;
        }
        return Build.VERSION.SDK_INT <= 17 && WebpHeaderParser.isNonSimpleWebpType(type);
    }

    private static byte[] getImageHeader(InputStream inputStream) {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream, 21);
        }
        inputStream.mark(21);
        byte[] bArr = new byte[21];
        try {
            inputStream.read(bArr, 0, 21);
            inputStream.reset();
            return bArr;
        } catch (IOException unused) {
            return null;
        }
    }

    private static InputStream wrapToMarkSupportedStream(InputStream inputStream) {
        return !inputStream.markSupported() ? new BufferedInputStream(inputStream, 8192) : inputStream;
    }

    public static Bitmap decodeByteArray(byte[] bArr, int i, int i2) {
        return decodeByteArray(bArr, i, i2, null);
    }

    public static Bitmap decodeByteArray(byte[] bArr, int i, int i2, BitmapFactory.Options options) {
        if ((i | i2) < 0 || bArr.length < i + i2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (webpSupportRequired(bArr, i, i2)) {
            Bitmap bitmapNativeDecodeByteArray = nativeDecodeByteArray(bArr, i, i2, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            setWebpBitmapOptions(bitmapNativeDecodeByteArray, options);
            return bitmapNativeDecodeByteArray;
        }
        return BitmapFactory.decodeByteArray(bArr, i, i2, options);
    }

    public static Bitmap decodeFile(String str) {
        return decodeFile(str, null);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|31|3|(2:24|4)|28|5|16|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0032 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap decodeFile(java.lang.String r4, android.graphics.BitmapFactory.Options r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L10 java.lang.Exception -> L12
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L10 java.lang.Exception -> L12
            android.graphics.Bitmap r0 = decodeStream(r1, r0, r5)     // Catch: java.lang.Exception -> Le java.lang.Throwable -> L2e
        La:
            r1.close()     // Catch: java.io.IOException -> L2d
            goto L2d
        Le:
            r4 = move-exception
            goto L14
        L10:
            r4 = move-exception
            goto L30
        L12:
            r4 = move-exception
            r1 = r0
        L14:
            java.lang.String r5 = "WebpBitmapFactory"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r2.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r3 = "Unable to decode stream: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L2e
            r2.append(r4)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L2e
            android.util.Log.e(r5, r4)     // Catch: java.lang.Throwable -> L2e
            if (r1 == 0) goto L2d
            goto La
        L2d:
            return r0
        L2e:
            r4 = move-exception
            r0 = r1
        L30:
            if (r0 == 0) goto L35
            r0.close()     // Catch: java.io.IOException -> L35
        L35:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.integration.webp.WebpBitmapFactory.decodeFile(java.lang.String, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    public static Bitmap decodeResource(Resources resources, int i) {
        return decodeResource(resources, i, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0010 A[EXC_TOP_SPLITTER, PHI: r0 r3
  0x0010: PHI (r0v3 android.graphics.Bitmap) = (r0v9 android.graphics.Bitmap), (r0v7 android.graphics.Bitmap) binds: [B:16:0x0021, B:5:0x000e] A[DONT_GENERATE, DONT_INLINE]
  0x0010: PHI (r3v3 java.io.InputStream) = (r3v2 java.io.InputStream), (r3v5 java.io.InputStream) binds: [B:16:0x0021, B:5:0x000e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap decodeResource(android.content.res.Resources r2, int r3, android.graphics.BitmapFactory.Options r4) throws java.lang.Throwable {
        /*
            r0 = 0
            android.util.TypedValue r1 = new android.util.TypedValue     // Catch: java.lang.Throwable -> L19 java.lang.Exception -> L20
            r1.<init>()     // Catch: java.lang.Throwable -> L19 java.lang.Exception -> L20
            java.io.InputStream r3 = r2.openRawResource(r3, r1)     // Catch: java.lang.Throwable -> L19 java.lang.Exception -> L20
            android.graphics.Bitmap r0 = decodeResourceStream(r2, r1, r3, r0, r4)     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L21
            if (r3 == 0) goto L24
        L10:
            r3.close()     // Catch: java.io.IOException -> L14
            goto L24
        L14:
            goto L24
        L16:
            r2 = move-exception
            r0 = r3
            goto L1a
        L19:
            r2 = move-exception
        L1a:
            if (r0 == 0) goto L1f
            r0.close()     // Catch: java.io.IOException -> L1f
        L1f:
            throw r2
        L20:
            r3 = r0
        L21:
            if (r3 == 0) goto L24
            goto L10
        L24:
            if (r0 != 0) goto L35
            if (r4 == 0) goto L35
            android.graphics.Bitmap r2 = r4.inBitmap
            if (r2 != 0) goto L2d
            goto L35
        L2d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Problem decoding into existing bitmap"
            r2.<init>(r3)
            throw r2
        L35:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.integration.webp.WebpBitmapFactory.decodeResource(android.content.res.Resources, int, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        if (options.inDensity == 0 && typedValue != null) {
            int i = typedValue.density;
            if (i == 0) {
                options.inDensity = 160;
            } else if (i != 65535) {
                options.inDensity = i;
            }
        }
        if (options.inTargetDensity == 0 && resources != null) {
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        return decodeStream(inputStream, rect, options);
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor) {
        return decodeFileDescriptor(fileDescriptor, null, null);
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, BitmapFactory.Options options) {
        Bitmap bitmapDecodeFileDescriptor;
        InputStream inputStreamWrapToMarkSupportedStream = wrapToMarkSupportedStream(new FileInputStream(fileDescriptor));
        try {
            if (webpSupportRequired(getImageHeader(inputStreamWrapToMarkSupportedStream), 0, 21)) {
                bitmapDecodeFileDescriptor = nativeDecodeStream(inputStreamWrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
                setWebpBitmapOptions(bitmapDecodeFileDescriptor, options);
                setDefaultPadding(rect);
            } else {
                bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
            }
            return bitmapDecodeFileDescriptor;
        } finally {
            try {
                inputStreamWrapToMarkSupportedStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static Bitmap decodeStream(InputStream inputStream) {
        return decodeStream(inputStream, null, null);
    }

    public static Bitmap decodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        if (inputStream == null) {
            return null;
        }
        InputStream inputStreamWrapToMarkSupportedStream = wrapToMarkSupportedStream(inputStream);
        if (webpSupportRequired(getImageHeader(inputStreamWrapToMarkSupportedStream), 0, 21)) {
            Bitmap bitmapNativeDecodeStream = nativeDecodeStream(inputStreamWrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            setWebpBitmapOptions(bitmapNativeDecodeStream, options);
            setDefaultPadding(rect);
            return bitmapNativeDecodeStream;
        }
        return BitmapFactory.decodeStream(inputStreamWrapToMarkSupportedStream, rect, options);
    }

    private static byte[] getInTempStorageFromOptions(BitmapFactory.Options options) {
        return (options == null || options.inTempStorage == null) ? new byte[8192] : options.inTempStorage;
    }

    private static float getScaleFromOptions(BitmapFactory.Options options) {
        if (options == null) {
            return 1.0f;
        }
        int i = options.inSampleSize;
        float f = i > 1 ? 1.0f / i : 1.0f;
        if (!options.inScaled) {
            return f;
        }
        int i2 = options.inDensity;
        int i3 = options.inTargetDensity;
        return (i2 == 0 || i3 == 0 || i2 == options.inScreenDensity) ? f : i3 / i2;
    }
}
