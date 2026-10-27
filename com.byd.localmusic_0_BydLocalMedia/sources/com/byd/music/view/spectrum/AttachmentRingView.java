package com.byd.music.view.spectrum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import com.byd.music.view.spectrum.VisualizerHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public class AttachmentRingView extends View implements VisualizerHelper.OnVisualizerEnergyCallBack {
    private int between;
    private Point centerPoint;
    private int degress;
    private boolean isBomb;
    private boolean isColumnar;
    private boolean isRandom;
    private boolean isRotate;
    private boolean isWave;
    private List<AttachmentRingViewBean> list;
    private Paint paint;
    private int radius;
    private Random random;
    private int randomAngle;
    private int scope;
    private int start;
    private int x;

    public void setRandom(boolean z) {
        this.isRandom = z;
        this.isRotate = !z;
    }

    public void setRotate(boolean z) {
        this.isRotate = z;
        this.isRandom = !z;
    }

    public void setScope(int i) {
        this.scope = i;
    }

    public void setBomb(boolean z) {
        this.isBomb = z;
        this.isColumnar = !z;
        this.isWave = !this.isWave;
    }

    public void setColumnar(boolean z) {
        this.isColumnar = z;
        this.isBomb = !z;
        this.isWave = !this.isWave;
    }

    public void setWave(boolean z) {
        this.isWave = z;
        this.isBomb = !z;
        this.isColumnar = !z;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public AttachmentRingView(Context context) {
        super(context);
        this.between = 1;
        this.radius = Utils.dp2px(120.0f);
        this.centerPoint = new Point();
        Paint paint = new Paint();
        this.paint = paint;
        this.isRotate = false;
        this.isRandom = true;
        this.scope = 50;
        this.start = 10;
        this.isColumnar = false;
        this.isBomb = false;
        this.isWave = true;
        paint.setAntiAlias(true);
        this.list = new ArrayList();
        this.random = new Random();
    }

    public AttachmentRingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.between = 1;
        this.radius = Utils.dp2px(120.0f);
        this.centerPoint = new Point();
        Paint paint = new Paint();
        this.paint = paint;
        this.isRotate = false;
        this.isRandom = true;
        this.scope = 50;
        this.start = 10;
        this.isColumnar = false;
        this.isBomb = false;
        this.isWave = true;
        paint.setAntiAlias(true);
        this.list = new ArrayList();
        this.random = new Random();
    }

    public AttachmentRingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.between = 1;
        this.radius = Utils.dp2px(120.0f);
        this.centerPoint = new Point();
        Paint paint = new Paint();
        this.paint = paint;
        this.isRotate = false;
        this.isRandom = true;
        this.scope = 50;
        this.start = 10;
        this.isColumnar = false;
        this.isBomb = false;
        this.isWave = true;
        paint.setAntiAlias(true);
        this.list = new ArrayList();
        this.random = new Random();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.centerPoint.set(i / 2, i2 / 2);
    }

    @Override // com.byd.music.view.spectrum.VisualizerHelper.OnVisualizerEnergyCallBack
    public void setWaveData(byte[] bArr, float f) {
        this.list.clear();
        int length = bArr.length - this.scope;
        float f2 = this.radius * 6.28f;
        float f3 = f2 / 360.0f;
        float f4 = length;
        float f5 = f2 / f4;
        float f6 = this.between * f3;
        for (int i = 0; i < length; i++) {
            float f7 = ((i * 1.0f) / f4) * 360.0f;
            AttachmentRingViewBean attachmentRingViewBean = new AttachmentRingViewBean();
            attachmentRingViewBean.width = f5 - f6;
            Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, this.radius, (this.isRotate ? this.degress : getRandomAngle()) + f7, attachmentRingViewBean.start);
            Utils.calcPoint(this.centerPoint.x, this.centerPoint.y, ((int) (((double) this.radius) + (((double) bArr[i]) * 1.5d))) + this.start, f7 + (this.isRotate ? this.degress : getRandomAngle()), attachmentRingViewBean.end);
            this.list.add(attachmentRingViewBean);
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setStyle(Paint.Style.STROKE);
        if (this.isBomb) {
            for (int i = 0; i < this.list.size(); i++) {
                this.paint.setStrokeWidth(this.list.get(i).width);
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setColor(Color.argb(76, 255, 255, 255));
                canvas.drawLine(this.list.get(i).start.x, this.list.get(i).start.y, this.list.get(i).end.x, this.list.get(i).end.y, this.paint);
                this.paint.setColor(-1);
                this.paint.setStrokeWidth(this.list.get(i).width);
                canvas.drawPoint(this.list.get(i).end.x, this.list.get(i).end.y, this.paint);
            }
        } else if (this.isColumnar) {
            for (int i2 = 0; i2 < this.list.size(); i2++) {
                this.paint.setStrokeWidth(this.list.get(i2).width);
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setColor(-1);
                canvas.drawLine(this.list.get(i2).start.x, this.list.get(i2).start.y, this.list.get(i2).end.x, this.list.get(i2).end.y, this.paint);
            }
        } else if (this.isWave) {
            for (int i3 = 0; i3 < this.list.size(); i3++) {
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setStrokeWidth(Utils.dp2px(1.0f));
                this.paint.setColor(Color.argb(76, 255, 255, 255));
                if (i3 == 0) {
                    List<AttachmentRingViewBean> list = this.list;
                    float f = list.get(list.size() - 1).end.x;
                    List<AttachmentRingViewBean> list2 = this.list;
                    canvas.drawLine(f, list2.get(list2.size() - 1).end.y, this.list.get(0).end.x, this.list.get(0).end.y, this.paint);
                } else {
                    int i4 = i3 - 1;
                    canvas.drawLine(this.list.get(i4).end.x, this.list.get(i4).end.y, this.list.get(i3).end.x, this.list.get(i3).end.y, this.paint);
                }
            }
        }
        if (this.isRotate) {
            int i5 = this.degress;
            if (i5 >= 360) {
                this.degress = 0;
            } else {
                this.degress = i5 + 1;
            }
        }
        int i6 = this.x + 1;
        this.x = i6;
        if (i6 > 300) {
            this.randomAngle = Utils.randomInt(this.random, 1, 4);
            this.x = 0;
        }
    }

    private int getRandomAngle() {
        if (!this.isRandom) {
            return 0;
        }
        int i = this.randomAngle;
        if (i == 1) {
            return 90;
        }
        if (i == 2) {
            return 180;
        }
        if (i != 3) {
            return i != 4 ? 0 : 360;
        }
        return 270;
    }

    private static class AttachmentRingViewBean {
        private Point end;
        private Point start;
        private float width;

        private AttachmentRingViewBean() {
            this.start = new Point();
            this.end = new Point();
        }
    }
}
