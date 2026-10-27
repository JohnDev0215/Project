package com.ecarx.xui.adaptapi.vehicle.bcm;

import com.ecarx.xui.adaptapi.Tribool;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IWasher {
    public static final int WINDOW_ALL_WINDSHIELDS = 3;
    public static final int WINDOW_FRONT_WINDSHIELD = 1;
    public static final int WINDOW_REAR_WINDSHIELD = 2;

    public interface WasherStatusListener {
        void onWasherStatus(int i, boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WasherType {
    }

    void attachWasherStatusListener(WasherStatusListener washerStatusListener, int[] iArr);

    void detachWasherStatusListener(WasherStatusListener washerStatusListener);

    Tribool isWasherOpened(int i);

    boolean setWasherOnOff(int i, boolean z);
}
