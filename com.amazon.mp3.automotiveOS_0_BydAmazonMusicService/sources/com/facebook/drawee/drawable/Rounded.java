package com.facebook.drawee.drawable;

/* JADX INFO: loaded from: classes5.dex */
public interface Rounded {
    int getBorderColor();

    float getBorderWidth();

    float getPadding();

    void setBorder(int color, float width);

    void setCircle(boolean isCircle);

    void setPadding(float padding);

    void setPaintFilterBitmap(boolean paintFilterBitmap);

    void setRadii(float[] radii);

    void setRadius(float radius);

    void setScaleDownInsideBorders(boolean scaleDownInsideBorders);
}
