package com.byd.vrsettings.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface LoadingState {
    public static final int LOCAL = 2;
    public static final int ONLINE = 3;
    public static final int SWITCH = 1;
}
