package com.ts.carplay.lib.ts;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.common.CarPlayResourceStateChange;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.SurfaceData;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayStackCmdHandler {
    private static final String TAG = "CarPlayStackCmdHandler";
    static CarPlayStackCmdHandler sCarPlayStackCmdHandler = null;
    private CarPlayStack mCarPlayStack;
    private HandlerThread mHandlerThread;
    private StackCmdHandler mStackCmdHandler;

    public CarPlayStackCmdHandler(CarPlayStack stack) {
        this.mCarPlayStack = stack;
        createStackCmdHandler();
        sCarPlayStackCmdHandler = this;
    }

    public static CarPlayStackCmdHandler getInstance() {
        CarPlayStackCmdHandler carPlayStackCmdHandler = sCarPlayStackCmdHandler;
        if (carPlayStackCmdHandler == null) {
            throw new IllegalStateException("Attempted to retrieve a handler before constructor was called.");
        }
        return carPlayStackCmdHandler;
    }

    public void carPlayShow(SurfaceData surfaceData) {
        Message msg = this.mStackCmdHandler.obtainMessage(5, surfaceData);
        this.mStackCmdHandler.sendMessage(msg);
    }

    public void releaseResource(int resource, int requestType) {
        Message msg = this.mStackCmdHandler.obtainMessage(1, resource, requestType);
        this.mStackCmdHandler.sendMessage(msg);
    }

    public void acquireResource(int resource, CpConst.CarPlayTransfer transfer, CarPlayResourceStateChange stateChange) {
        Message msg = this.mStackCmdHandler.obtainMessage(4, resource, transfer.ordinal(), stateChange);
        this.mStackCmdHandler.sendMessage(msg);
    }

    public void requestUi() {
        Message msg = this.mStackCmdHandler.obtainMessage(2, 5, -1);
        this.mStackCmdHandler.sendMessage(msg);
    }

    public void audioPlayControl(int cmd, boolean press) {
        Message msg = this.mStackCmdHandler.obtainMessage(3, cmd, -1, Boolean.valueOf(press));
        this.mStackCmdHandler.sendMessage(msg);
    }

    private void createStackCmdHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mStackCmdHandler = new StackCmdHandler(this, this.mHandlerThread.getLooper());
    }

    public class StackCmdHandler extends Handler {
        private CarPlayStackCmdHandler mCarPlayStackCmdHandler;

        public StackCmdHandler(CarPlayStackCmdHandler cn, Looper looper) {
            super(looper);
            this.mCarPlayStackCmdHandler = cn;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (this.mCarPlayStackCmdHandler == null) {
                return;
            }
            LogUtil.info(CarPlayStackCmdHandler.TAG, "handleMessage Start: " + msg.what);
            int i = msg.what;
            if (i == 1) {
                CarPlayStackCmdHandler.this.mCarPlayStack.releaseResource(msg.arg1, msg.arg2);
            } else if (i == 2) {
                CarPlayStackCmdHandler.this.mCarPlayStack.appLaunch(msg.arg1);
            } else if (i == 3) {
                CarPlayStackCmdHandler.this.mCarPlayStack.hardKeyEvent(msg.arg1, ((Boolean) msg.obj).booleanValue());
            } else if (i == 4) {
                CarPlayStackCmdHandler.this.mCarPlayStack.acquireResource(msg.arg1, msg.arg2, (CarPlayResourceStateChange) msg.obj);
            } else if (i == 5) {
                CarPlayStackCmdHandler.this.mCarPlayStack.carPlayShow((SurfaceData) msg.obj);
            } else {
                LogUtil.error(CarPlayStackCmdHandler.TAG, "handleMessage() Unknown event type: " + msg.what);
            }
            LogUtil.info(CarPlayStackCmdHandler.TAG, "handleMessage end: " + msg.what);
        }
    }
}
