package com.ecarx.xui.adaptapi.vehicle.hud;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IHUD {
    public static final int SETTING_FUNC_ADJUST = 34;
    public static final int SETTING_FUNC_DISPLAY_BTPHONE = 1004;
    public static final int SETTING_FUNC_DISPLAY_DRIVE_ENVIRONMENT = 1005;
    public static final int SETTING_FUNC_DISPLAY_MEIDA = 1002;
    public static final int SETTING_FUNC_DISPLAY_NAVI = 1003;
    public static final int SETTING_FUNC_DISPLAY_SAFETY = 1001;
    public static final int SETTING_FUNC_ONOFF = 33;
    public static final int SETTING_FUNC_SNOWING_MODE = 1010;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HudFunctions {
    }
}
