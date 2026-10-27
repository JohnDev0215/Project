package com.android.systemui.shadow;

/* JADX INFO: loaded from: classes.dex */
public interface Shadow {
    void commit();

    Shadow setBlurRadius(float radius);

    Shadow setBlurRadius(int unit, float radius);

    Shadow setShadowColor(int color);

    Shadow setShadowColorRes(int colorRes);

    Shadow setShadowRadius(float radius);

    Shadow setShadowRadius(int unit, float radius);

    Shadow setXOffset(float offset);

    Shadow setXOffset(int unit, float offset);

    Shadow setYOffset(float offset);

    Shadow setYOffset(int unit, float offset);
}
