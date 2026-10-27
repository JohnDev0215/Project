package com.android.systemui.screenshot;

import android.os.IBinder;
import android.view.IWindowManager;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes.dex */
public class ScrollCaptureController {
    public static final int STATUS_A = 0;
    public static final int STATUS_B = 1;
    private StatusListener mListener;
    private final IWindowManager mWindowManagerService;

    interface StatusListener {
        void onScrollCaptureStatus(boolean available);
    }

    @Inject
    public ScrollCaptureController(IWindowManager windowManagerService) {
        this.mWindowManagerService = windowManagerService;
    }

    public void getStatus(IBinder window, StatusListener listener) {
        this.mListener = listener;
    }
}
