package com.byd.music.view.jingyun.util;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import com.byd.music.view.jingyun.other.CirclePoint;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DrawUtil {
    public static void drawCurvesFromPoints(Canvas canvas, List<CirclePoint> list, double d, Paint paint) {
        int size = list.size();
        Point[] pointArr = new Point[size];
        int i = 0;
        while (i < size) {
            CirclePoint circlePoint = list.get(i);
            int i2 = i + 1;
            CirclePoint circlePoint2 = list.get(i2 % size);
            pointArr[i] = new Point((circlePoint.x + circlePoint2.x) / 2, (circlePoint.y + circlePoint2.y) / 2);
            i = i2;
        }
        Point[] pointArr2 = new Point[size];
        int i3 = 0;
        while (i3 < size) {
            CirclePoint circlePoint3 = list.get(i3);
            int i4 = i3 + 1;
            int i5 = i4 % size;
            CirclePoint circlePoint4 = list.get(i5);
            CirclePoint circlePoint5 = list.get((i3 + 2) % size);
            double dDistance = distance(circlePoint3, circlePoint4);
            pointArr2[i3] = ratioPointConvert(pointArr[i5], pointArr[i3], dDistance / (distance(circlePoint4, circlePoint5) + dDistance));
            i3 = i4;
        }
        int i6 = size * 2;
        Point[] pointArr3 = new Point[i6];
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            Point point = pointArr2[i7];
            int i9 = i7 + 1;
            int i10 = i9 % size;
            CirclePoint circlePoint6 = list.get(i10);
            int i11 = point.x - circlePoint6.x;
            int i12 = point.y - circlePoint6.y;
            Point[] pointArr4 = pointArr2;
            Point point2 = new Point(pointArr[i7].x - i11, pointArr[i7].y - i12);
            Point point3 = new Point(pointArr[i10].x - i11, pointArr[i10].y - i12);
            int i13 = i8 + 1;
            pointArr3[i8] = ratioPointConvert(point2, circlePoint6, d);
            i8 = i13 + 1;
            pointArr3[i13] = ratioPointConvert(point3, circlePoint6, d);
            i7 = i9;
            pointArr2 = pointArr4;
        }
        Path path = new Path();
        int i14 = 0;
        while (i14 < size) {
            CirclePoint circlePoint7 = list.get(i14);
            int i15 = i14 + 1;
            CirclePoint circlePoint8 = list.get(i15 % size);
            Point point4 = pointArr3[((r5 + i6) - 1) % i6];
            Point point5 = pointArr3[(i14 * 2) % i6];
            path.reset();
            path.moveTo(circlePoint7.x, circlePoint7.y);
            path.cubicTo(point4.x, point4.y, point5.x, point5.y, circlePoint8.x, circlePoint8.y);
            canvas.drawPath(path, paint);
            i14 = i15;
        }
    }

    public static void drawCicleLineFromTowPoints(Canvas canvas, Point point, Point point2, Paint paint) {
        paint.setPathEffect(new CornerPathEffect(1.0f));
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.quadTo((point.x + point2.x) / 2, (point.y + point2.y) / 2, point2.x, point2.y);
        canvas.drawPath(path, paint);
    }

    private static double distance(Point point, Point point2) {
        return Math.sqrt(((point.x - point2.x) * (point.x - point2.x)) + ((point.y - point2.y) * (point.y - point2.y)));
    }

    private static Point ratioPointConvert(Point point, Point point2, double d) {
        Point point3 = new Point();
        point3.x = (int) ((((double) (point.x - point2.x)) * d) + ((double) point2.x));
        point3.y = (int) ((d * ((double) (point.y - point2.y))) + ((double) point2.y));
        return point3;
    }
}
