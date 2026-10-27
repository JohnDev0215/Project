package com.byd.music.view.spectrum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import com.byd.music.view.spectrum.VisualizerHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class WaveformView extends View implements VisualizerHelper.OnVisualizerEnergyCallBack {
    private int defultY;
    private List<Point> newData;
    private int offset;
    private Paint paint;
    private int spacing;

    public void setOffset(int i) {
        this.offset = i;
    }

    public WaveformView(Context context) {
        super(context);
        this.spacing = 5;
        this.offset = 0;
        this.paint = new Paint();
        this.newData = new ArrayList();
    }

    public WaveformView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.spacing = 5;
        this.offset = 0;
        this.paint = new Paint();
        this.newData = new ArrayList();
    }

    public WaveformView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.spacing = 5;
        this.offset = 0;
        this.paint = new Paint();
        this.newData = new ArrayList();
    }

    @Override // com.byd.music.view.spectrum.VisualizerHelper.OnVisualizerEnergyCallBack
    public void setWaveData(byte[] bArr, float f) {
        this.paint.setStrokeWidth(3.0f);
        this.paint.setColor(AppConstant.COLOR);
        this.spacing = (getWidth() / 256) + this.offset;
        this.defultY = getHeight() / 2;
        this.newData.clear();
        for (int i = 0; i < bArr.length; i++) {
            if (i == 0 || i == i - 1) {
                this.newData.add(new Point(0, this.defultY));
            } else {
                this.newData.add(new Point(this.spacing * i, this.defultY + getOffsetY(i, bArr[i])));
            }
        }
    }

    private int getOffsetY(int i, int i2) {
        if (i % 2 == 0) {
            return i2 * AppConstant.LAGER_OFFSET;
        }
        return (-i2) * AppConstant.LAGER_OFFSET;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.newData.size(); i++) {
            if (i > 1) {
                int i2 = i - 1;
                canvas.drawLine(this.newData.get(i2).x, this.newData.get(i2).y, this.newData.get(i).x, this.newData.get(i).y, this.paint);
            }
        }
        invalidate();
    }
}
