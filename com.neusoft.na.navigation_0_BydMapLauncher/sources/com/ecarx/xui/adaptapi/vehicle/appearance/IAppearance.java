package com.ecarx.xui.adaptapi.vehicle.appearance;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IAppearance {
    public static final int BUMPER_DESIGN_DEFAULT = 2147483637;
    public static final int BUMPER_DESIGN_LOWFRONT = 8;
    public static final int BUMPER_DESIGN_RDESIGN = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BUMPERDESIGN {
    }

    int getBumperDesign();
}
