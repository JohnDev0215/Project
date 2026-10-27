package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGLinearGradientManagerInterface<T extends View> {
    void setClipPath(T t, @Nullable String str);

    void setClipRule(T t, int i);

    void setDisplay(T t, @Nullable String str);

    void setGradient(T t, @Nullable ReadableArray readableArray);

    void setGradientTransform(T t, @Nullable ReadableArray readableArray);

    void setGradientUnits(T t, int i);

    void setMarkerEnd(T t, @Nullable String str);

    void setMarkerMid(T t, @Nullable String str);

    void setMarkerStart(T t, @Nullable String str);

    void setMask(T t, @Nullable String str);

    void setMatrix(T t, @Nullable ReadableArray readableArray);

    void setName(T t, @Nullable String str);

    void setPointerEvents(T t, @Nullable String str);

    void setResponsible(T t, boolean z);

    void setX1(T t, @Nullable Double d);

    void setX1(T t, @Nullable String str);

    void setX2(T t, @Nullable Double d);

    void setX2(T t, @Nullable String str);

    void setY1(T t, @Nullable Double d);

    void setY1(T t, @Nullable String str);

    void setY2(T t, @Nullable Double d);

    void setY2(T t, @Nullable String str);
}
