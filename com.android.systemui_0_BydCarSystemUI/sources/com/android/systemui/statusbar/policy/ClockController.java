package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface ClockController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        default void onClockDate(String date) {
        }

        default void onClockTime(String time) {
        }
    }

    String getClockDate();

    String getClockTime();
}
