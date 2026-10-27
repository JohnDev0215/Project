package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGRadialGradientManagerInterface<T extends View> {
    void setClipPath(T t, @Nullable String str);

    void setClipRule(T t, int i);

    void setCx(T t, @Nullable Double d);

    void setCx(T t, @Nullable String str);

    void setCy(T t, @Nullable Double d);

    void setCy(T t, @Nullable String str);

    void setDisplay(T t, @Nullable String str);

    void setFx(T t, @Nullable Double d);

    void setFx(T t, @Nullable String str);

    void setFy(T t, @Nullable Double d);

    void setFy(T t, @Nullable String str);

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

    void setRx(T t, @Nullable Double d);

    void setRx(T t, @Nullable String str);

    void setRy(T t, @Nullable Double d);

    void setRy(T t, @Nullable String str);
}
