package com.google.android.flexbox;

import android.os.Parcelable;

/* JADX INFO: loaded from: classes6.dex */
interface FlexItem extends Parcelable {
    int getAlignSelf();

    float getFlexBasisPercent();

    float getFlexGrow();

    float getFlexShrink();

    int getHeight();

    int getMarginBottom();

    int getMarginEnd();

    int getMarginLeft();

    int getMarginRight();

    int getMarginStart();

    int getMarginTop();

    int getMaxHeight();

    int getMaxWidth();

    int getMinHeight();

    int getMinWidth();

    int getOrder();

    int getWidth();

    boolean isWrapBefore();

    void setAlignSelf(int i);

    void setFlexBasisPercent(float f);

    void setFlexGrow(float f);

    void setFlexShrink(float f);

    void setHeight(int i);

    void setMaxHeight(int i);

    void setMaxWidth(int i);

    void setMinHeight(int i);

    void setMinWidth(int i);

    void setOrder(int i);

    void setWidth(int i);
}
