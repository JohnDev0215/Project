package a4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import java.io.Closeable;

/* JADX INFO: renamed from: a4.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0927h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Matrix f11132a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final E7.b f11133b = new E7.b(2);
    public static final E7.b c = new E7.b(3);
    public static final E7.b d = new E7.b(4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final E7.b f11134e = new E7.b(5);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f11135f = (float) (Math.sqrt(2.0d) / 2.0d);

    public static void a(Path path, float f10, float f11, float f12) {
        PathMeasure pathMeasure = (PathMeasure) f11133b.get();
        Path path2 = (Path) c.get();
        Path path3 = (Path) d.get();
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (!(f10 == 1.0f && f11 == 0.0f) && length >= 1.0f && Math.abs((f11 - f10) - 1.0f) >= 0.01d) {
            float f13 = f10 * length;
            float f14 = f11 * length;
            float f15 = f12 * length;
            float fMin = Math.min(f13, f14) + f15;
            float fMax = Math.max(f13, f14) + f15;
            if (fMin >= length && fMax >= length) {
                fMin = AbstractC0925f.d(fMin, length);
                fMax = AbstractC0925f.d(fMax, length);
            }
            if (fMin < 0.0f) {
                fMin = AbstractC0925f.d(fMin, length);
            }
            if (fMax < 0.0f) {
                fMax = AbstractC0925f.d(fMax, length);
            }
            if (fMin == fMax) {
                path.reset();
                return;
            }
            if (fMin >= fMax) {
                fMin -= length;
            }
            path2.reset();
            pathMeasure.getSegment(fMin, fMax, path2, true);
            if (fMax > length) {
                path3.reset();
                pathMeasure.getSegment(0.0f, fMax % length, path3, true);
                path2.addPath(path3);
            } else if (fMin < 0.0f) {
                path3.reset();
                pathMeasure.getSegment(fMin + length, length, path3, true);
                path2.addPath(path3);
            }
            path.set(path2);
        }
    }

    public static void b(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e10) {
            throw e10;
        } catch (Exception unused) {
        }
    }

    public static float c() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static Bitmap d(Bitmap bitmap, int i7, int i10) {
        if (bitmap.getWidth() == i7 && bitmap.getHeight() == i10) {
            return bitmap;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i7, i10, true);
        bitmap.recycle();
        return bitmapCreateScaledBitmap;
    }
}
