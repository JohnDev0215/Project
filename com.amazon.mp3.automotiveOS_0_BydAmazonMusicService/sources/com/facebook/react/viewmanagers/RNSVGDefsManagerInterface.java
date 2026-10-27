package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGDefsManagerInterface<T extends View> {
    void setClipPath(T t, @Nullable String str);

    void setClipRule(T t, int i);

    void setDisplay(T t, @Nullable String str);

    void setMarkerEnd(T t, @Nullable String str);

    void setMarkerMid(T t, @Nullable String str);

    void setMarkerStart(T t, @Nullable String str);

    void setMask(T t, @Nullable String str);

    void setMatrix(T t, @Nullable ReadableArray readableArray);

    void setName(T t, @Nullable String str);

    void setPointerEvents(T t, @Nullable String str);

    void setResponsible(T t, boolean z);
}
