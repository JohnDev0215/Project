package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGUseManagerInterface<T extends View> {
    void setClipPath(T t, @Nullable String str);

    void setClipRule(T t, int i);

    void setDisplay(T t, @Nullable String str);

    void setFill(T t, @Nullable ReadableMap readableMap);

    void setFillOpacity(T t, float f);

    void setFillRule(T t, int i);

    void setHeight(T t, @Nullable Double d);

    void setHeight(T t, @Nullable String str);

    void setHref(T t, @Nullable String str);

    void setMarkerEnd(T t, @Nullable String str);

    void setMarkerMid(T t, @Nullable String str);

    void setMarkerStart(T t, @Nullable String str);

    void setMask(T t, @Nullable String str);

    void setMatrix(T t, @Nullable ReadableArray readableArray);

    void setName(T t, @Nullable String str);

    void setPointerEvents(T t, @Nullable String str);

    void setPropList(T t, @Nullable ReadableArray readableArray);

    void setResponsible(T t, boolean z);

    void setStroke(T t, @Nullable ReadableMap readableMap);

    void setStrokeDasharray(T t, @Nullable ReadableArray readableArray);

    void setStrokeDashoffset(T t, float f);

    void setStrokeLinecap(T t, int i);

    void setStrokeLinejoin(T t, int i);

    void setStrokeMiterlimit(T t, float f);

    void setStrokeOpacity(T t, float f);

    void setStrokeWidth(T t, @Nullable Double d);

    void setStrokeWidth(T t, @Nullable String str);

    void setVectorEffect(T t, int i);

    void setWidth(T t, @Nullable Double d);

    void setWidth(T t, @Nullable String str);

    void setX(T t, @Nullable Double d);

    void setX(T t, @Nullable String str);

    void setY(T t, @Nullable Double d);

    void setY(T t, @Nullable String str);
}
