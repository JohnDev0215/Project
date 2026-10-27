package com.android.systemui.shared.recents.utilities;

import android.animation.TypeEvaluator;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public class RectFEvaluator implements TypeEvaluator<RectF> {
    private final RectF mRect = new RectF();

    @Override // android.animation.TypeEvaluator
    public RectF evaluate(float fraction, RectF startValue, RectF endValue) {
        this.mRect.set(startValue.left + ((endValue.left - startValue.left) * fraction), startValue.top + ((endValue.top - startValue.top) * fraction), startValue.right + ((endValue.right - startValue.right) * fraction), startValue.bottom + ((endValue.bottom - startValue.bottom) * fraction));
        return this.mRect;
    }
}
