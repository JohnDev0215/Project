package com.ecarx.xui.adaptapi.device.ads;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IAdvertise {
    public static final int OPERATION_CLICKED = 2;
    public static final int OPERATION_DISPLAY = 1;
    public static final int OPERATION_SKIPPED = 3;

    public interface OnAdOperationChangedListener {
        void onOperationChanged(String str, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OperationCode {
    }

    void addOnAdOperationChangedListener(OnAdOperationChangedListener onAdOperationChangedListener);

    void removeOnAdOperationChangedListener(OnAdOperationChangedListener onAdOperationChangedListener);

    void setBootAdInfo(IBootAdInfo iBootAdInfo);
}
