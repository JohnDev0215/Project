package com.byd.profile.request.cell;

/* JADX INFO: loaded from: classes2.dex */
public class BaseValue {
    public static final int STATE_IGNORE = 0;
    public static final int STATE_OFF = 2;
    public static final int STATE_ON = 1;

    public static boolean isCareAbout(int value) {
        return value != 0;
    }
}
