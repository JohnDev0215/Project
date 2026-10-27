package com.byd.music.view.spectrum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import com.byd.music.view.jingyun.other.Triangle;
import com.byd.music.view.spectrum.VisualizerHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public class WaveRingView extends View implements VisualizerHelper.OnVisualizerEnergyCallBack {
    private static Long startTime = Long.valueOf(System.currentTimeMillis());
    private Point centerPoint;
    private float degress;
    private int distance;
    private boolean isBase;
    private boolean isDrawText;
    private boolean isDrawTriangle;
    private boolean isMove;
    private boolean isPoint;
    private boolean isPowerOffset;
    private boolean isRandom;
    private boolean isRandomMove;
    private boolean isRotate;
    private boolean isSpread;
    private boolean isWave;
    private List<Float> lastRadius;
    private List<WaveRingViewBean> list;
    private int mPaintColor;
    private double moveSpeed;
    private int moveValue;
    private Paint paint;
    private Path path;
    private Point point;
    private float powerPercent;
    private int powerPosition;
    private int radius;
    private Random random;
    private int randomAngle;
    private int scope;
    private int speed;
    private SweepGradient sweepGradient;
    private String text;
    private Triangle triangle;
    private int value;
    private int x;

    public void setRandom(boolean z) {
        if (z) {
            this.isRotate = !z;
        }
        this.isRandom = z;
    }

    public void setRotate(boolean z) {
        if (z) {
            this.isRandom = !z;
        }
        this.isRotate = z;
    }

    public void setBase(boolean z) {
        this.isBase = z;
    }

    public void setWave(boolean z) {
        this.isWave = z;
    }

    public void setPowerOffset(boolean z) {
        this.isPowerOffset = z;
    }

    public void setScope(int i) {
        this.scope = i;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setPoint(boolean z) {
        this.isPoint = z;
    }

    public void setSpread(boolean z) {
        this.isSpread = z;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public void setDrawText(boolean z) {
        this.isDrawText = z;
    }

    public void setMove(boolean z) {
        this.isMove = z;
    }

    public void setRandomMove(boolean z) {
        this.isRandomMove = z;
    }

    public void setMoveValue(int i) {
        this.moveValue = i;
    }

    public WaveRingView(Context context) {
        super(context);
        this.point = new Point();
        this.random = new Random();
        this.radius = 10;
        this.centerPoint = new Point();
        this.paint = new Paint();
        this.text = "THE RING OF GOD";
        this.mPaintColor = Color.parseColor("#cabfa3");
        this.isDrawTriangle = false;
        this.moveSpeed = 0.2d;
        this.path = new Path();
        this.isRotate = true;
        this.isRandom = false;
        this.isBase = false;
        this.isWave = true;
        this.isPoint = false;
        this.isPowerOffset = true;
        this.isSpread = true;
        this.isDrawText = false;
        this.isMove = true;
        this.isRandomMove = false;
        this.scope = 1;
        this.value = 150;
        this.distance = 0;
        this.speed = 3;
        this.moveValue = 1000;
        this.paint.setAntiAlias(true);
        this.list = new ArrayList();
        this.lastRadius = new ArrayList();
    }

    public WaveRingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.point = new Point();
        this.random = new Random();
        this.radius = 10;
        this.centerPoint = new Point();
        this.paint = new Paint();
        this.text = "THE RING OF GOD";
        this.mPaintColor = Color.parseColor("#cabfa3");
        this.isDrawTriangle = false;
        this.moveSpeed = 0.2d;
        this.path = new Path();
        this.isRotate = true;
        this.isRandom = false;
        this.isBase = false;
        this.isWave = true;
        this.isPoint = false;
        this.isPowerOffset = true;
        this.isSpread = true;
        this.isDrawText = false;
        this.isMove = true;
        this.isRandomMove = false;
        this.scope = 1;
        this.value = 150;
        this.distance = 0;
        this.speed = 3;
        this.moveValue = 1000;
        this.paint.setAntiAlias(true);
        this.list = new ArrayList();
        this.lastRadius = new ArrayList();
    }

    public WaveRingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.point = new Point();
        this.random = new Random();
        this.radius = 10;
        this.centerPoint = new Point();
        this.paint = new Paint();
        this.text = "THE RING OF GOD";
        this.mPaintColor = Color.parseColor("#cabfa3");
        this.isDrawTriangle = false;
        this.moveSpeed = 0.2d;
        this.path = new Path();
        this.isRotate = true;
        this.isRandom = false;
        this.isBase = false;
        this.isWave = true;
        this.isPoint = false;
        this.isPowerOffset = true;
        this.isSpread = true;
        this.isDrawText = false;
        this.isMove = true;
        this.isRandomMove = false;
        this.scope = 1;
        this.value = 150;
        this.distance = 0;
        this.speed = 3;
        this.moveValue = 1000;
        this.paint.setAntiAlias(true);
        this.list = new ArrayList();
        this.lastRadius = new ArrayList();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.centerPoint.set(i / 2, i2 / 2);
        this.sweepGradient = new SweepGradient(this.centerPoint.x, this.centerPoint.y, new int[]{-1, -1, -1, -1}, (float[]) null);
    }

    @Override // com.byd.music.view.spectrum.VisualizerHelper.OnVisualizerEnergyCallBack
    public void setWaveData(byte[] bArr, float f) {
        int length = bArr.length;
        int i = this.value;
        int i2 = length - i;
        float f2 = (this.radius + i) - this.distance;
        if (this.list.size() > 0) {
            if (this.lastRadius.size() == 0 || this.list.size() != this.lastRadius.size()) {
                this.lastRadius.clear();
                for (int i3 = 0; i3 < i2; i3++) {
                    this.lastRadius.add(Float.valueOf(f2));
                }
            }
            for (int i4 = 0; i4 < this.lastRadius.size(); i4++) {
                if (i4 < this.list.size()) {
                    if (this.list.get(i4).radius >= this.lastRadius.get(i4).floatValue()) {
                        if (this.isSpread) {
                            List<Float> list = this.lastRadius;
                            list.set(i4, Float.valueOf(list.get(i4).floatValue() + this.speed));
                        } else {
                            this.lastRadius.set(i4, Float.valueOf(this.list.get(i4).radius));
                        }
                    } else {
                        this.lastRadius.set(i4, Float.valueOf(this.list.get(i4).radius));
                    }
                }
            }
        }
        this.list.clear();
        float f3 = i2;
        for (int i5 = 0; i5 < i2; i5++) {
            float f4 = ((i5 * 1.0f) / f3) * 360.0f;
            this.powerPercent = this.isPowerOffset ? 0.0f : (bArr[i5] * 1.0f) / 256.0f;
            WaveRingViewBean waveRingViewBean = new WaveRingViewBean();
            waveRingViewBean.angle = f4 + (this.isRotate ? this.degress : getRandomAngle());
            waveRingViewBean.powerPercent = (bArr[i5] * 1.0f) / 256.0f;
            if (bArr[i5] > this.scope) {
                waveRingViewBean.radius = ((f2 - bArr[i5]) - (this.powerPercent * bArr[i5])) - (Utils.dp2px(this.distance) * this.powerPercent);
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, this.radius + bArr[i5] + this.value, waveRingViewBean.angle, waveRingViewBean.inner);
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, ((int) ((this.radius - bArr[i5]) - (this.powerPercent * bArr[i5]))) + this.value, waveRingViewBean.angle, waveRingViewBean.outter);
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, this.radius + this.value, waveRingViewBean.angle, waveRingViewBean.center);
            } else {
                waveRingViewBean.radius = f2;
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, this.radius + this.value, waveRingViewBean.angle, waveRingViewBean.inner);
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, this.radius + this.value, waveRingViewBean.angle, waveRingViewBean.outter);
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, this.radius + this.value, waveRingViewBean.angle, waveRingViewBean.center);
            }
            this.list.add(waveRingViewBean);
        }
        this.powerPosition = -1;
        if (this.isMove && f > this.moveValue) {
            this.powerPosition = this.isRandomMove ? Utils.randomInt(this.random, 1, this.list.size()) : getStrongPowerPosition(bArr);
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        this.paint.setShader(this.sweepGradient);
        this.paint.setColor(Color.argb(216, 255, 255, 255));
        this.paint.setStrokeWidth(Utils.dp2px(2.0f));
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            if (this.isBase) {
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStrokeWidth(Utils.dp2px(1.0f));
                canvas.drawLine(this.list.get(i2).inner.x, this.list.get(i2).inner.y, this.list.get(i2).outter.x, this.list.get(i2).outter.y, this.paint);
            }
            if (this.isPoint) {
                this.paint.setStyle(Paint.Style.FILL);
                if (i2 < this.lastRadius.size()) {
                    Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, (int) this.lastRadius.get(i2).floatValue(), this.list.get(i2).angle, this.list.get(i2).point);
                    canvas.drawArc(this.list.get(i2).point.x - Utils.dp2px(2.0f), this.list.get(i2).point.y - Utils.dp2px(2.0f), this.list.get(i2).point.x + Utils.dp2px(2.0f), this.list.get(i2).point.y + Utils.dp2px(2.0f), 0.0f, 360.0f, true, this.paint);
                }
            }
            if (this.isWave) {
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStrokeWidth(Utils.dp2px(1.8f));
                if (i2 == 0) {
                    List<WaveRingViewBean> list = this.list;
                    float f = list.get(list.size() - 1).center.x;
                    List<WaveRingViewBean> list2 = this.list;
                    canvas.drawLine(f, list2.get(list2.size() - 1).center.y, this.list.get(0).outter.x, this.list.get(0).outter.y, this.paint);
                    canvas.drawLine(this.list.get(0).outter.x, this.list.get(0).outter.y, this.list.get(1).center.x, this.list.get(1).center.y, this.paint);
                    canvas.drawLine(this.list.get(1).center.x, this.list.get(1).center.y, this.list.get(0).inner.x, this.list.get(0).inner.y, this.paint);
                    float f2 = this.list.get(0).inner.x;
                    float f3 = this.list.get(0).inner.y;
                    List<WaveRingViewBean> list3 = this.list;
                    float f4 = list3.get(list3.size() - 1).center.x;
                    List<WaveRingViewBean> list4 = this.list;
                    canvas.drawLine(f2, f3, f4, list4.get(list4.size() - 1).center.y, this.paint);
                } else if (i2 == this.list.size() - 1) {
                    float f5 = this.list.get(r1.size() - 2).center.x;
                    float f6 = this.list.get(r1.size() - 2).center.y;
                    List<WaveRingViewBean> list5 = this.list;
                    float f7 = list5.get(list5.size() - 1).outter.x;
                    List<WaveRingViewBean> list6 = this.list;
                    canvas.drawLine(f5, f6, f7, list6.get(list6.size() - 1).outter.y, this.paint);
                    List<WaveRingViewBean> list7 = this.list;
                    float f8 = list7.get(list7.size() - 1).outter.x;
                    List<WaveRingViewBean> list8 = this.list;
                    canvas.drawLine(f8, list8.get(list8.size() - 1).outter.y, this.list.get(0).center.x, this.list.get(0).center.y, this.paint);
                    float f9 = this.list.get(0).center.x;
                    float f10 = this.list.get(0).center.y;
                    List<WaveRingViewBean> list9 = this.list;
                    float f11 = list9.get(list9.size() - 1).inner.x;
                    List<WaveRingViewBean> list10 = this.list;
                    canvas.drawLine(f9, f10, f11, list10.get(list10.size() - 1).inner.y, this.paint);
                    List<WaveRingViewBean> list11 = this.list;
                    float f12 = list11.get(list11.size() - 1).inner.x;
                    List<WaveRingViewBean> list12 = this.list;
                    float f13 = list12.get(list12.size() - 1).inner.y;
                    float f14 = this.list.get(r1.size() - 2).center.x;
                    List<WaveRingViewBean> list13 = this.list;
                    canvas.drawLine(f12, f13, f14, list13.get(list13.size() - 2).center.y, this.paint);
                } else {
                    int i3 = i2 - 1;
                    canvas.drawLine(this.list.get(i3).center.x, this.list.get(i3).center.y, this.list.get(i2).outter.x, this.list.get(i2).outter.y, this.paint);
                    int i4 = i2 + 1;
                    canvas.drawLine(this.list.get(i2).outter.x, this.list.get(i2).outter.y, this.list.get(i4).center.x, this.list.get(i4).center.y, this.paint);
                    canvas.drawLine(this.list.get(i4).center.x, this.list.get(i4).center.y, this.list.get(i2).inner.x, this.list.get(i2).inner.y, this.paint);
                    canvas.drawLine(this.list.get(i2).inner.x, this.list.get(i2).inner.y, this.list.get(i3).center.x, this.list.get(i3).center.y, this.paint);
                }
            }
            if (this.isDrawTriangle) {
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStrokeWidth(5.0f);
                this.path.moveTo(this.triangle.topPoint1.x, this.triangle.topPoint1.y);
                this.path.lineTo(this.triangle.topPoint2.x, this.triangle.topPoint2.y);
                this.path.lineTo(this.triangle.topPoint3.x, this.triangle.topPoint3.y);
                this.path.close();
                canvas.drawPath(this.path, this.paint);
            }
        }
        if (this.isDrawText) {
            this.paint.setShader(null);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(-1);
            this.paint.setTextSize(Utils.sp2px(20.0f));
            this.paint.setStrokeWidth(Utils.dp2px(1.0f));
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.point.set(this.centerPoint.x, this.centerPoint.y);
            if (this.isMove && (i = this.powerPosition) > -1 && i < this.list.size()) {
                Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, (int) (this.list.get(this.powerPosition).powerPercent * 150.0f), this.list.get(this.powerPosition).angle, this.point);
                canvas.drawText(this.text, this.point.x, this.point.y, this.paint);
            }
            canvas.drawText(this.text, this.centerPoint.x, this.centerPoint.y, this.paint);
        }
        if (this.isRotate) {
            float f15 = this.degress;
            if (f15 >= 360.0f) {
                this.degress = 0.0f;
            } else {
                this.degress = f15 + 0.3f;
            }
        }
        int i5 = this.x + 1;
        this.x = i5;
        if (i5 > 300) {
            this.randomAngle = Utils.randomInt(this.random, 1, 4);
            this.x = 0;
        }
    }

    public int getAlpha(Triangle triangle) {
        double dMax = Math.max(Math.max(Math.sqrt(Math.pow(triangle.topPoint1.x - (getWidth() / 2), 2.0d) + Math.pow(triangle.topPoint1.y - (getHeight() / 2), 2.0d)), Math.sqrt(Math.pow(triangle.topPoint2.x - (getWidth() / 2), 2.0d) + Math.pow(triangle.topPoint2.y - (getHeight() / 2), 2.0d))), Math.sqrt(Math.pow(triangle.topPoint3.x - (getWidth() / 2), 2.0d) + Math.pow(triangle.topPoint3.y - (getHeight() / 2), 2.0d)));
        if (dMax < ((double) getWidth()) * 0.3d) {
            return 255;
        }
        double width = ((((-1275.0d) / (((double) getWidth()) * 2.0d)) * dMax) + 637.0d) - 280.0d;
        if (width < 0.0d) {
            width = 0.0d;
        }
        return (int) width;
    }

    private int getStrongPowerPosition(byte[] bArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            if (bArr[i2] > 0) {
                i = i2;
            }
        }
        return i;
    }

    private float getRandomAngle() {
        if (this.isRandom) {
            int i = this.randomAngle;
            if (i == 1) {
                return 90.0f;
            }
            if (i == 2) {
                return 180.0f;
            }
            if (i != 3) {
                return i != 4 ? 0.0f : 360.0f;
            }
            return 270.0f;
        }
        return this.degress;
    }

    private static class WaveRingViewBean {
        private float angle;
        private Point center;
        private Point inner;
        private Point outter;
        private Point point;
        private float powerPercent;
        private float radius;

        private WaveRingViewBean() {
            this.point = new Point();
            this.inner = new Point();
            this.center = new Point();
            this.outter = new Point();
        }
    }
}
