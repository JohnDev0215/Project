package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSVGSvgViewAndroidManagerInterface<T extends View> {
    void setAccessible(T t, boolean z);

    void setAlign(T t, @Nullable String str);

    void setBackfaceVisibility(T t, @Nullable String str);

    void setBbHeight(T t, @Nullable Double d);

    void setBbHeight(T t, @Nullable String str);

    void setBbWidth(T t, @Nullable Double d);

    void setBbWidth(T t, @Nullable String str);

    void setBorderBottomColor(T t, @Nullable Integer num);

    void setBorderBottomEndRadius(T t, float f);

    void setBorderBottomLeftRadius(T t, double d);

    void setBorderBottomRightRadius(T t, double d);

    void setBorderBottomStartRadius(T t, float f);

    void setBorderColor(T t, @Nullable Integer num);

    void setBorderEndColor(T t, @Nullable Integer num);

    void setBorderLeftColor(T t, @Nullable Integer num);

    void setBorderRadius(T t, double d);

    void setBorderRightColor(T t, @Nullable Integer num);

    void setBorderStartColor(T t, @Nullable Integer num);

    void setBorderStyle(T t, @Nullable String str);

    void setBorderTopColor(T t, @Nullable Integer num);

    void setBorderTopEndRadius(T t, float f);

    void setBorderTopLeftRadius(T t, double d);

    void setBorderTopRightRadius(T t, double d);

    void setBorderTopStartRadius(T t, float f);

    void setColor(T t, @Nullable Integer num);

    void setFocusable(T t, boolean z);

    void setHasTVPreferredFocus(T t, boolean z);

    void setHitSlop(T t, @Nullable ReadableMap readableMap);

    void setMeetOrSlice(T t, int i);

    void setMinX(T t, float f);

    void setMinY(T t, float f);

    void setNativeBackgroundAndroid(T t, @Nullable ReadableMap readableMap);

    void setNativeForegroundAndroid(T t, @Nullable ReadableMap readableMap);

    void setNeedsOffscreenAlphaCompositing(T t, boolean z);

    void setNextFocusDown(T t, int i);

    void setNextFocusForward(T t, int i);

    void setNextFocusLeft(T t, int i);

    void setNextFocusRight(T t, int i);

    void setNextFocusUp(T t, int i);

    void setPointerEvents(T t, @Nullable String str);

    void setRemoveClippedSubviews(T t, boolean z);

    void setTintColor(T t, @Nullable Integer num);

    void setVbHeight(T t, float f);

    void setVbWidth(T t, float f);
}
