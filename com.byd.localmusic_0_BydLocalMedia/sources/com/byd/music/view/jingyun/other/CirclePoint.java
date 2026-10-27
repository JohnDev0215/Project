package com.byd.music.view.jingyun.other;

import android.graphics.Point;

/* JADX INFO: loaded from: classes5.dex */
public class CirclePoint extends Point {
    public int angle;
    public double circleR;
    public double circleX;
    public double circleY;
    public int x2;
    public int y2;

    public CirclePoint(int i, double d, double d2, double d3) {
        this.angle = i;
        this.circleR = d;
        this.circleX = d2;
        this.circleY = d3;
        Point point = getPoint(i, d);
        this.x = point.x;
        this.y = point.y;
        this.x2 = this.x;
        this.y2 = this.y;
    }

    public void move(int i) {
        double d = i;
        Point point = getPoint(this.angle, this.circleR + d);
        this.x = point.x;
        this.y = point.y;
        Point point2 = getPoint(this.angle, this.circleR - d);
        this.x2 = point2.x;
        this.y2 = point2.y;
    }

    public Point getBezierPoint(int i) {
        return getPoint(this.angle + 1, i);
    }

    public Point getPoint(int i, double d) {
        double d2 = 0.0d;
        if (i == -180) {
            d = -d;
        } else {
            if (i == -90) {
                d = -d;
            } else if (i != 0) {
                if (i != 90) {
                    double d3 = i;
                    double dSin = Math.sin(Math.toRadians(d3)) * d;
                    d *= Math.cos(Math.toRadians(d3));
                    d2 = dSin;
                }
            }
            double d4 = d;
            d = 0.0d;
            d2 = d4;
        }
        return new Point((int) (d + this.circleX), (int) (d2 + this.circleY));
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int i) {
        this.angle = i;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }
}
