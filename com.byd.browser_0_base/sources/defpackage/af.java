package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Pair;
import java.io.FileDescriptor;
import java.io.IOException;

/* JADX INFO: compiled from: BitmapUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class af {
    public static Pair<Bitmap, Float> a(FileDescriptor fileDescriptor, int i, boolean z) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int iMin = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 > i && i3 > i) {
            iMin = Math.min(i2, i3) / i;
        }
        options.inSampleSize = iMin;
        options.inJustDecodeBounds = false;
        Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        if (bitmapDecodeFileDescriptor == null) {
            return null;
        }
        return new Pair<>(e(bitmapDecodeFileDescriptor, i, z, fileDescriptor), Float.valueOf(bitmapDecodeFileDescriptor.getHeight() / bitmapDecodeFileDescriptor.getWidth()));
    }

    public static Matrix b(FileDescriptor fileDescriptor) {
        Matrix matrix = new Matrix();
        try {
            switch (new ExifInterface(fileDescriptor).getAttributeInt("Orientation", 0)) {
                case 0:
                    c(8);
                    break;
                case 1:
                    c(0);
                    break;
                case 2:
                    matrix.setScale(-1.0f, 1.0f);
                    c(6);
                    break;
                case 3:
                    matrix.postRotate(180.0f);
                    c(2);
                    break;
                case 4:
                    matrix.setScale(1.0f, -1.0f);
                    c(7);
                    break;
                case 5:
                    matrix.setRotate(90.0f);
                    matrix.postScale(-1.0f, 1.0f);
                    c(4);
                    break;
                case 6:
                    matrix.postRotate(90.0f);
                    c(1);
                    break;
                case 7:
                    matrix.setRotate(-90.0f);
                    matrix.postScale(-1.0f, 1.0f);
                    c(5);
                    break;
                case 8:
                    matrix.postRotate(-90.0f);
                    c(3);
                    break;
            }
        } catch (IOException unused) {
        }
        return matrix;
    }

    public static void c(int i) {
        oj2.U(i, 9, "Android.PhotoPicker.ExifOrientation");
    }

    public static Bitmap d(Bitmap bitmap, float f) {
        float fMin = Math.min(f / bitmap.getWidth(), f / bitmap.getHeight());
        return Bitmap.createScaledBitmap(bitmap, Math.round(fMin * bitmap.getWidth()), Math.round(bitmap.getHeight() * fMin), false);
    }

    public static Bitmap e(Bitmap bitmap, int i, boolean z, FileDescriptor fileDescriptor) {
        if (z) {
            return Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), b(fileDescriptor), true), i, (int) (r8.getHeight() * (i / r8.getWidth())), true);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width != i || height != i) {
            if (width <= i || height <= i) {
                if (width < i) {
                    height = (int) (height * (i / width));
                    width = i;
                }
                if (height < i) {
                    width = (int) (width * (i / height));
                    height = i;
                }
                bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            } else {
                float f = (width < height ? width : height) / i;
                bitmap = Bitmap.createScaledBitmap(bitmap, Math.round(width / f), Math.round(height / f), true);
            }
        }
        Bitmap bitmap2 = bitmap;
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width2 == i && height2 == i) {
            return bitmap2;
        }
        return Bitmap.createBitmap(bitmap2, width2 > i ? (width2 - i) / 2 : 0, height2 > i ? (height2 - i) / 2 : 0, i, i, b(fileDescriptor), true);
    }
}
