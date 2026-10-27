package com.google.android.flexbox;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
interface FlexContainer {
    int getAlignContent();

    int getAlignItems();

    int getChildHeightMeasureSpec(int i, int i2, int i3);

    int getChildWidthMeasureSpec(int i, int i2, int i3);

    int getDecorationLengthCrossAxis(View view);

    int getDecorationLengthMainAxis(View view, int i, int i2);

    int getFlexDirection();

    int getFlexItemCount();

    List<FlexLine> getFlexLinesInternal();

    int getFlexWrap();

    int getJustifyContent();

    int getLargestMainSize();

    int getMaxLine();

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingStart();

    int getPaddingTop();

    View getReorderedFlexItemAt(int i);

    int getSumOfCrossSize();

    boolean isMainAxisDirectionHorizontal();

    void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine);

    void onNewFlexLineAdded(FlexLine flexLine);

    void setAlignContent(int i);

    void setAlignItems(int i);

    void setFlexDirection(int i);

    void setFlexWrap(int i);

    void setJustifyContent(int i);

    void setMaxLine(int i);

    void updateViewCache(int i, View view);
}
