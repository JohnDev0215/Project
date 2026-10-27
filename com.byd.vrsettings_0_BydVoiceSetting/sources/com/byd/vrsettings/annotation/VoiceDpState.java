package com.byd.vrsettings.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface VoiceDpState {
    public static final int DELETE = 2;
    public static final int INSTALL = 3;
    public static final int INSTALL_AND_USE = 4;
    public static final int INSTALL_DIFFERENTIAL = 5;
    public static final int INSTALL_DIFFERENTIAL_AND_USE = 6;
    public static final int SWITCH = 1;
}
