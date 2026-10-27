package com.abc.displaylib;

import android.hardware.display.DisplayManager;

/* JADX INFO: loaded from: classes.dex */
public class DisplayListenUtil {
    private static volatile DisplayListenUtil instance;

    public interface OnBackLightStatusChangeListener {
        void onStatusChange(int i, int i2);
    }

    public static class OneKeyBackLightListener implements DisplayManager.OnekeyStatusListener {
        private OnBackLightStatusChangeListener onBackLightStatusChangeListener;

        public OneKeyBackLightListener(OnBackLightStatusChangeListener onBackLightStatusChangeListener) {
            this.onBackLightStatusChangeListener = onBackLightStatusChangeListener;
        }

        public void onOnekeyStatusChange(int i, int i2) {
            OnBackLightStatusChangeListener onBackLightStatusChangeListener = this.onBackLightStatusChangeListener;
            if (onBackLightStatusChangeListener != null) {
                onBackLightStatusChangeListener.onStatusChange(i, i2);
            }
        }
    }

    private DisplayListenUtil() {
    }

    public static DisplayListenUtil getInstance() {
        if (instance == null) {
            synchronized (DisplayListenUtil.class) {
                if (instance == null) {
                    instance = new DisplayListenUtil();
                }
            }
        }
        return instance;
    }

    public OneKeyBackLightListener createOneKeyBackLightListener(OnBackLightStatusChangeListener onBackLightStatusChangeListener) {
        return new OneKeyBackLightListener(onBackLightStatusChangeListener);
    }
}
