package com.bydux.api.common.tools.util;

import android.view.animation.PathInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public class PathInterpolatorUtils {
    public static PathInterpolator getAccelerate() {
        return new PathInterpolator(0.2f, 0.0f, 1.0f, 1.0f);
    }

    public static PathInterpolator getSharp() {
        return new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    }

    public static PathInterpolator getSlowDown() {
        return new PathInterpolator(0.0f, 0.0f, 0.1f, 1.0f);
    }

    public static PathInterpolator getStandard() {
        return new PathInterpolator(0.2f, 0.2f, 0.1f, 1.0f);
    }

    public static PathInterpolator getUniformSpeed() {
        return new PathInterpolator(0.0f, 0.0f, 1.0f, 1.0f);
    }
}
