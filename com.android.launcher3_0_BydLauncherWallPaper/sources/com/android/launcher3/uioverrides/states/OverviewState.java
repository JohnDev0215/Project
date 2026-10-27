package com.android.launcher3.uioverrides.states;

import com.android.launcher3.LauncherState;

/* JADX INFO: loaded from: classes4.dex */
public class OverviewState extends LauncherState {
    public OverviewState(int id) {
        super(id, 1, 250, 4);
    }

    public static OverviewState newBackgroundState(int id) {
        return new OverviewState(id);
    }

    public static OverviewState newPeekState(int id) {
        return new OverviewState(id);
    }

    public static OverviewState newSwitchState(int id) {
        return new OverviewState(id);
    }
}
