package com.byd.music.view.jingyun.other;

import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public class Triangle {
    public static int moveAnglePre = 0;
    public static int triangleSize = 45;
    public int moveAngle = getMoveAngel();
    public Point topPoint1;
    public Point topPoint2;
    public Point topPoint3;
    public static Random random = new Random(System.currentTimeMillis());
    public static List<Point> pointList = new ArrayList();

    public Triangle(Point point, Point point2, Point point3) {
        this.topPoint1 = point;
        this.topPoint2 = point2;
        this.topPoint3 = point3;
    }

    private int getMoveAngel() {
        int iNextInt = 180 - new Random(System.currentTimeMillis() * System.currentTimeMillis()).nextInt(360);
        if (iNextInt == 0 || iNextInt == 90 || iNextInt == 180 || iNextInt == -90 || iNextInt == -180) {
            return getMoveAngel();
        }
        if (Math.abs(moveAnglePre - iNextInt) < 30) {
            return getMoveAngel();
        }
        moveAnglePre = iNextInt;
        return iNextInt;
    }

    public void move(int i) {
        double d = i;
        move(this, (int) (Math.cos(Math.toRadians(this.moveAngle)) * d), (int) (Math.sin(Math.toRadians(this.moveAngle)) * d));
    }

    public boolean isOut(int i, int i2) {
        return this.topPoint1.x <= 0 || this.topPoint1.y <= 0 || this.topPoint1.x >= i || this.topPoint1.y >= i2;
    }

    public static Triangle getRandomTriangle(int i, int i2) {
        int i3;
        pointList.clear();
        int i4 = -triangleSize;
        while (true) {
            i3 = triangleSize;
            if (i4 > i3) {
                break;
            }
            for (int i5 = -i3; i5 < triangleSize; i5 += 2) {
                pointList.add(new Point(i4, i5));
            }
            i4 += 2;
        }
        Point point = pointList.get(random.nextInt(i3 * i3));
        List<Point> list = pointList;
        Random random2 = random;
        int i6 = triangleSize;
        Point point2 = list.get(random2.nextInt(i6 * i6));
        List<Point> list2 = pointList;
        Random random3 = random;
        int i7 = triangleSize;
        Triangle triangle = new Triangle(point, point2, list2.get(random3.nextInt(i7 * i7)));
        move(triangle, i, i2);
        return isTriangle(triangle) ? triangle : getRandomTriangle(i, i2);
    }

    public static void move(Triangle triangle, int i, int i2) {
        triangle.topPoint1.x += i;
        triangle.topPoint2.x += i;
        triangle.topPoint3.x += i;
        triangle.topPoint1.y += i2;
        triangle.topPoint2.y += i2;
        triangle.topPoint3.y += i2;
    }

    public static boolean isTriangle(Triangle triangle) {
        double dSqrt = Math.sqrt(Math.pow(triangle.topPoint1.x - triangle.topPoint2.x, 2.0d) + Math.pow(triangle.topPoint1.y - triangle.topPoint2.y, 2.0d));
        double dSqrt2 = Math.sqrt(Math.pow(triangle.topPoint1.x - triangle.topPoint3.x, 2.0d) + Math.pow(triangle.topPoint1.y - triangle.topPoint3.y, 2.0d));
        double dSqrt3 = Math.sqrt(Math.pow(triangle.topPoint2.x - triangle.topPoint3.x, 2.0d) + Math.pow(triangle.topPoint2.y - triangle.topPoint3.y, 2.0d));
        double d = dSqrt + dSqrt2;
        int i = triangleSize;
        return d > ((double) (i / 10)) + dSqrt3 && dSqrt + dSqrt3 > ((double) (i / 10)) + dSqrt2 && dSqrt2 + dSqrt3 > ((double) (i / 10)) + dSqrt && dSqrt > ((double) (i / 10)) && dSqrt2 > ((double) (i / 10)) && dSqrt3 > ((double) (i / 10));
    }
}
