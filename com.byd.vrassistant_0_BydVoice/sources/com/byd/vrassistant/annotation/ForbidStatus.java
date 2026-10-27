package com.byd.vrassistant.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface ForbidStatus {
    public static final int AVM_NO_MAIN_SEAT = 8;
    public static final int BOOTING = 3;
    public static final int CALL = 2;
    public static final int CAR_LOCK = 6;
    public static final int CLOUD_REGISTER = 4;
    public static final int NO = 0;
    public static final int PRIVACY = 5;
    public static final int REVERSE = 1;
    public static final int TANGSTEP_ANDROIDAUTO = 7;
    public static final int ZOOM_MEETING = 9;
}
