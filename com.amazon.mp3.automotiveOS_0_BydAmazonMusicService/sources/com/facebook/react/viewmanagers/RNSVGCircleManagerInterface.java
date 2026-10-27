package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGCircleManagerInterface<T extends View> {
    void setClipPath(T t, @Nullable String str);

    void setClipRule(T t, int i);

    void setCx(T t, @Nullable Double d);

    void setCx(T t, @Nullable String str);

    void setCy(T t, @Nullable Double d);

    void setCy(T t, @Nullable String str);

    void setDisplay(T t, @Nullable String str);

    void setFill(T t, @Nullable ReadableMap readableMap);

    void setFillOpacity(T t, float f);

    void setFillRule(T t, int i);

    void setMarkerEnd(T t, @Nullable String str);

    void setMarkerMid(T t, @Nullable String str);

    void setMarkerStart(T t, @Nullable String str);

    void setMask(T t, @Nullable String str);

    void setMatrix(T t, @Nullable ReadableArray readableArray);

    void setName(T t, @Nullable String str);

    void setPointerEvents(T t, @Nullable String str);

    void setPropList(T t, @Nullable ReadableArray readableArray);

    void setR(T t, @Nullable Double d);

    void setR(T t, @Nullable String str);

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
}
