package com.google.android.exoplayer2.util;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: loaded from: classes6.dex */
@SuppressLint({"ParcelCreator"})
public class EdgeTypeSpan extends MetricAffectingSpan implements ParcelableSpan {
    private int edgeColor;
    private int edgeType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.text.ParcelableSpan
    public int getSpanTypeId() {
        return 100;
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
    }

    public EdgeTypeSpan(int i, int i2) {
        this.edgeType = i;
        this.edgeColor = i2;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        int i = this.edgeType;
        if (i == 1) {
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setShadowLayer(2.0f, -1.0f, -1.0f, this.edgeColor);
            return;
        }
        if (i == 2) {
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setShadowLayer(2.0f, 1.0f, 1.0f, this.edgeColor);
            return;
        }
        if (i == 3) {
            textPaint.setStrokeJoin(Paint.Join.ROUND);
            textPaint.setStrokeWidth(2.0f);
            textPaint.setColor(this.edgeColor);
            textPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        if (i == 4) {
            textPaint.setShadowLayer(4.0f, -2.0f, -2.0f, this.edgeColor);
        } else {
            if (i != 5) {
                return;
            }
            textPaint.setShadowLayer(4.0f, 2.0f, 2.0f, this.edgeColor);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.edgeType);
        parcel.writeInt(this.edgeColor);
    }
}
