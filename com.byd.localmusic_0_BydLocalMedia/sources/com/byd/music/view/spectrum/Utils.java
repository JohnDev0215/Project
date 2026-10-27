package com.byd.music.view.spectrum;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public class Utils {
    public static float getAngle(float f) {
        return f < 90.0f ? 390.0f - f : f - 90.0f;
    }

    public static List<PointF> calculateBezier3(List<PointF> list, float f, Path path) {
        PointF pointF;
        PointF pointF2;
        PointF pointF3;
        PointF pointF4;
        PointF pointF5;
        List<PointF> list2 = list;
        ArrayList arrayList = new ArrayList();
        list.size();
        PointF pointF6 = new PointF();
        PointF pointF7 = new PointF();
        PointF pointF8 = null;
        int i = 0;
        while (i <= list.size() - 3) {
            PointF pointF9 = list2.get(i);
            int i2 = i + 1;
            PointF pointF10 = list2.get(i2);
            PointF pointF11 = list2.get(i + 2);
            pointF6.x = (pointF9.x + pointF10.x) / 2.0f;
            pointF6.y = (pointF9.y + pointF10.y) / 2.0f;
            pointF7.x = (pointF10.x + pointF11.x) / 2.0f;
            pointF7.y = (pointF10.y + pointF11.y) / 2.0f;
            float fHypot = (float) Math.hypot(pointF10.x - pointF9.x, pointF10.y - pointF9.y);
            float fHypot2 = (fHypot / (((float) Math.hypot(pointF11.x - pointF10.x, pointF11.y - pointF10.y)) + fHypot)) * f;
            float f2 = (1.0f - fHypot2) * f;
            float f3 = pointF6.x - pointF7.x;
            float f4 = pointF6.y - pointF7.y;
            PointF pointF12 = new PointF();
            pointF12.x = pointF10.x + (f3 * fHypot2);
            pointF12.y = pointF10.y + (fHypot2 * f4);
            PointF pointF13 = new PointF();
            pointF13.x = pointF10.x + ((-f3) * f2);
            pointF13.y = pointF10.y + ((-f4) * f2);
            if (i == 0) {
                PointF pointF14 = new PointF((pointF9.x + pointF12.x) / 2.0f, (pointF9.y + pointF12.y) / 2.0f);
                PointF pointF15 = new PointF((pointF12.x + pointF10.x) / 2.0f, (pointF12.y + pointF10.y) / 2.0f);
                float fHypot3 = (float) Math.hypot(pointF12.x - pointF9.x, pointF12.y - pointF9.y);
                pointF = pointF6;
                pointF2 = pointF7;
                float fHypot4 = (fHypot3 / (((float) Math.hypot(pointF10.x - pointF12.x, pointF10.y - pointF12.y)) + fHypot3)) * f;
                PointF pointF16 = new PointF();
                pointF16.x = pointF12.x + ((pointF14.x - pointF15.x) * fHypot4);
                pointF16.y = pointF12.y + ((pointF14.y - pointF15.y) * fHypot4);
                if (path != null) {
                    pointF4 = pointF13;
                    pointF5 = pointF12;
                    path.cubicTo(pointF16.x, pointF16.y, pointF12.x, pointF12.y, pointF10.x, pointF10.y);
                } else {
                    pointF4 = pointF13;
                    pointF5 = pointF12;
                }
                arrayList.add(pointF16);
                arrayList.add(pointF5);
                arrayList.add(pointF10);
                pointF3 = pointF4;
            } else {
                pointF = pointF6;
                pointF2 = pointF7;
                pointF3 = pointF13;
                if (path != null) {
                    path.cubicTo(pointF8.x, pointF8.y, pointF12.x, pointF12.y, pointF10.x, pointF10.y);
                }
                arrayList.add(pointF8);
                arrayList.add(pointF12);
                arrayList.add(pointF10);
            }
            if (i == list.size() - 3) {
                PointF pointF17 = new PointF((pointF10.x + pointF3.x) / 2.0f, (pointF10.y + pointF3.y) / 2.0f);
                PointF pointF18 = new PointF((pointF11.x + pointF3.x) / 2.0f, (pointF11.y + pointF3.y) / 2.0f);
                float fHypot5 = (float) Math.hypot(pointF3.x - pointF10.x, pointF3.y - pointF10.y);
                float fHypot6 = (float) Math.hypot(pointF11.x - pointF3.x, pointF11.y - pointF3.y);
                float f5 = (fHypot6 / (fHypot5 + fHypot6)) * f;
                PointF pointF19 = new PointF();
                pointF19.x = pointF3.x + ((pointF18.x - pointF17.x) * f5);
                pointF19.y = pointF3.y + ((pointF18.y - pointF17.y) * f5);
                if (path != null) {
                    path.cubicTo(pointF3.x, pointF3.y, pointF19.x, pointF19.y, pointF11.x, pointF11.y);
                }
                arrayList.add(pointF3);
                arrayList.add(pointF19);
                arrayList.add(pointF11);
            }
            list2 = list;
            pointF8 = pointF3;
            i = i2;
            pointF6 = pointF;
            pointF7 = pointF2;
        }
        return arrayList;
    }

    public static boolean calculateBesselSmoothPath(float f, int i, boolean z, PathMeasure pathMeasure, Path path, List<Point> list) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        if (list.size() < 2) {
            return false;
        }
        path.reset();
        int size = list.size();
        float f8 = Float.NaN;
        float f9 = Float.NaN;
        float f10 = Float.NaN;
        float f11 = Float.NaN;
        float f12 = Float.NaN;
        int i2 = 0;
        float f13 = Float.NaN;
        while (i2 < size) {
            if (Float.isNaN(f8)) {
                Point point = list.get(i2);
                float f14 = point.x;
                f3 = point.y;
                f2 = f14;
            } else {
                f2 = f8;
                f3 = f9;
            }
            if (!Float.isNaN(f13)) {
                f4 = f13;
                f5 = f11;
            } else if (i2 > 0) {
                Point point2 = list.get(i2 - 1);
                float f15 = point2.x;
                f5 = point2.y;
                f4 = f15;
            } else {
                f4 = f2;
                f5 = f3;
            }
            if (Float.isNaN(f10)) {
                if (i2 > 1) {
                    Point point3 = list.get(i2 - 2);
                    f10 = point3.x;
                    f12 = point3.y;
                } else {
                    f10 = f4;
                    f12 = f5;
                }
            }
            if (i2 < size - 1) {
                Point point4 = list.get(i2 + 1);
                float f16 = point4.x;
                f7 = point4.y;
                f6 = f16;
            } else {
                f6 = f2;
                f7 = f3;
            }
            if (i2 == 0) {
                path.moveTo(f2, f3);
            } else {
                float f17 = i;
                path.cubicTo(((f2 - f10) * f) + f4 + f17, (f * (f3 - f12)) + f5 + f17, (f2 - (f * (f6 - f4))) - f17, (f3 - (f * (f7 - f5))) - f17, f2, f3);
            }
            i2++;
            f13 = f2;
            f11 = f3;
            f10 = f4;
            f12 = f5;
            f8 = f6;
            f9 = f7;
        }
        pathMeasure.setPath(path, z);
        return pathMeasure.getSegment(0.0f, pathMeasure.getLength() * 1.0f, path, true);
    }

    public static Point calcPoint(int i, int i2, int i3, float f, Point point) {
        double d = i3;
        double d2 = (((double) f) * 3.141592653589793d) / 180.0d;
        point.x = (int) (((double) i) + (Math.cos(d2) * d));
        point.y = (int) (((double) i2) + (d * Math.sin(d2)));
        return point;
    }

    public static int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int randomInt(Random random, int i, int i2) {
        int i3 = (i2 - i) + 1;
        if (i3 == 0) {
            return (i + i2) / 2;
        }
        return (random.nextInt(i2) % i3) + i;
    }

    public static Bitmap getScaleBitmap(int i, int i2, Resources resources) throws Throwable {
        float f;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeResource(resources, i2, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        float f2 = 0.0f;
        if (i3 > i || i4 > i) {
            float f3 = i;
            float f4 = i3 / f3;
            float f5 = i4 / f3;
            f2 = f4;
            f = f5;
        } else {
            f = 0.0f;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = (int) Math.max(f2, f);
        options.inDither = true;
        return Bitmap.createScaledBitmap((Bitmap) new WeakReference(BitmapFactory.decodeResource(resources, i2, options)).get(), i, i, true);
    }

    public static Bitmap getCirleBitmap(int i, Bitmap bitmap, Paint paint) {
        WeakReference weakReference = new WeakReference(Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888));
        Canvas canvas = new Canvas((Bitmap) weakReference.get());
        Path path = new Path();
        float f = i / 2;
        path.addCircle(f, f, f, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return (Bitmap) weakReference.get();
    }
}
