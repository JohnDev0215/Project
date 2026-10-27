package com.byd.automultipletheme.common;

import android.hardware.input.InputManager;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public class ClickEventUtil {
    private ClickEventUtil() {
    }

    private static class Holder {
        private static ClickEventUtil instance = new ClickEventUtil();

        private Holder() {
        }
    }

    public static ClickEventUtil getInstance() {
        return Holder.instance;
    }

    public void sendEvent(int i, int i2, int i3, long j, long j2) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(j, j2, i2, i, (i3 & 128) != 0 ? 1 : 0, 0, -1, 0, i3 | 8 | 64, 257), 0);
    }
}
