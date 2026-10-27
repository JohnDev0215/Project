package com.android.launcher3.util;

import android.view.Window;
import com.android.launcher3.Utilities;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class SystemUiController {
    public static final int FLAG_DARK_NAV = 2;
    public static final int FLAG_DARK_STATUS = 8;
    public static final int FLAG_LIGHT_NAV = 1;
    public static final int FLAG_LIGHT_STATUS = 4;
    public static final int UI_STATE_ALL_APPS = 1;
    public static final int UI_STATE_BASE_WINDOW = 0;
    public static final int UI_STATE_OVERVIEW = 4;
    public static final int UI_STATE_ROOT_VIEW = 3;
    public static final int UI_STATE_WIDGET_BOTTOM_SHEET = 2;
    private final int[] mStates = new int[5];
    private final Window mWindow;

    public SystemUiController(Window window) {
        this.mWindow = window;
    }

    public void updateUiState(int uiState, boolean isLight) {
        updateUiState(uiState, isLight ? 5 : 10);
    }

    public void updateUiState(int uiState, int flags) {
        int[] iArr = this.mStates;
        if (iArr[uiState] == flags) {
            return;
        }
        iArr[uiState] = flags;
        int systemUiVisibility = this.mWindow.getDecorView().getSystemUiVisibility();
        int i = systemUiVisibility;
        for (int i2 : this.mStates) {
            if (Utilities.ATLEAST_OREO) {
                if ((i2 & 1) != 0) {
                    i |= 16;
                } else if ((i2 & 2) != 0) {
                    i &= -17;
                }
            }
            if ((i2 & 4) != 0) {
                i |= 8192;
            } else if ((i2 & 8) != 0) {
                i &= -8193;
            }
        }
        if (i != systemUiVisibility) {
            this.mWindow.getDecorView().setSystemUiVisibility(i);
        }
    }

    public String toString() {
        return "mStates=" + Arrays.toString(this.mStates);
    }
}
