package com.byd.car;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class VehicleUIStyle {
    public static final int STYLE_DENZA = 1;
    public static final int STYLE_DYNASTY = 0;
    public static final int STYLE_FUI = 3;
    public static final int STYLE_OCEAN = 2;
    public static final int STYLE_R4UI = 10;
    public static final int STYLE_RUI = 4;
    public static final int STYLE_UNDEFINED = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UIStyle {
    }
}
