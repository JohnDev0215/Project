package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGTextPathManagerInterface<T extends View> {
    void setAlignmentBaseline(T t, @Nullable String str);

    void setBaselineShift(T t, @Nullable Double d);

    void setBaselineShift(T t, @Nullable String str);

    void setClipPath(T t, @Nullable String str);

    void setClipRule(T t, int i);

    void setDisplay(T t, @Nullable String str);

    void setDx(T t, @Nullable ReadableArray readableArray);

    void setDy(T t, @Nullable ReadableArray readableArray);

    void setFill(T t, @Nullable ReadableMap readableMap);

    void setFillOpacity(T t, float f);

    void setFillRule(T t, int i);

    void setFont(T t, @Nullable ReadableMap readableMap);

    void setFontSize(T t, @Nullable Double d);

    void setFontSize(T t, @Nullable String str);

    void setFontWeight(T t, @Nullable Double d);

    void setFontWeight(T t, @Nullable String str);

    void setHref(T t, @Nullable String str);

    void setInlineSize(T t, @Nullable Double d);

    void setInlineSize(T t, @Nullable String str);

    void setLengthAdjust(T t, @Nullable String str);

    void setMarkerEnd(T t, @Nullable String str);

    void setMarkerMid(T t, @Nullable String str);

    void setMarkerStart(T t, @Nullable String str);

    void setMask(T t, @Nullable String str);

    void setMatrix(T t, @Nullable ReadableArray readableArray);

    void setMethod(T t, @Nullable String str);

    void setMidLine(T t, @Nullable String str);

    void setName(T t, @Nullable String str);

    void setPointerEvents(T t, @Nullable String str);

    void setPropList(T t, @Nullable ReadableArray readableArray);

    void setResponsible(T t, boolean z);

    void setRotate(T t, @Nullable ReadableArray readableArray);

    void setSide(T t, @Nullable String str);

    void setSpacing(T t, @Nullable String str);

    void setStartOffset(T t, @Nullable Double d);

    void setStartOffset(T t, @Nullable String str);

    void setStroke(T t, @Nullable ReadableMap readableMap);

    void setStrokeDasharray(T t, @Nullable ReadableArray readableArray);

    void setStrokeDashoffset(T t, float f);

    void setStrokeLinecap(T t, int i);

    void setStrokeLinejoin(T t, int i);

    void setStrokeMiterlimit(T t, float f);

    void setStrokeOpacity(T t, float f);

    void setStrokeWidth(T t, @Nullable Double d);

    void setStrokeWidth(T t, @Nullable String str);

    void setTextLength(T t, @Nullable Double d);

    void setTextLength(T t, @Nullable String str);

    void setVectorEffect(T t, int i);

    void setVerticalAlign(T t, @Nullable String str);

    void setX(T t, @Nullable ReadableArray readableArray);

    void setY(T t, @Nullable ReadableArray readableArray);
}
