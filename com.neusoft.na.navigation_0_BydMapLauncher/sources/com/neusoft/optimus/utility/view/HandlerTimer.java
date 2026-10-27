package com.neusoft.optimus.utility.view;

import android.os.Handler;
import android.os.HandlerThread;
import cerence.ark.assistant.controller.domain.cc.CCRequestsEnum;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class HandlerTimer extends Handler {
    private static final String cTag = "HandlerTimer";
    private Handler _handler;
    HandlerThread _handlerThread;
    private long _notifyInterval;
    private Runnable _runnable;
    private Runnable _timerRunnable;

    public void init() {
        LogTool.d(cTag, "init");
        this._timerRunnable = new TimerRunnable();
        HandlerThread handlerThread = new HandlerThread(cTag);
        this._handlerThread = handlerThread;
        handlerThread.start();
        this._handler = new Handler(this._handlerThread.getLooper());
    }

    public void start(Runnable runnable, long j, long j2) {
        Handler handler;
        LogTool.d(cTag, "start -- delay : " + j + "notifyInterval : " + j2);
        this._runnable = runnable;
        Runnable runnable2 = this._timerRunnable;
        if (runnable2 == null || (handler = this._handler) == null) {
            return;
        }
        this._notifyInterval = j2;
        handler.postDelayed(runnable2, j);
    }

    public void cancel() {
        LogTool.d(cTag, CCRequestsEnum.CANCEL);
        Handler handler = this._handler;
        if (handler != null) {
            handler.removeCallbacks(this._timerRunnable);
        }
    }

    private class TimerRunnable implements Runnable {
        private TimerRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (HandlerTimer.this._timerRunnable != null) {
                HandlerTimer.this._runnable.run();
            }
            HandlerTimer.this._handler.postDelayed(HandlerTimer.this._timerRunnable, HandlerTimer.this._notifyInterval);
        }
    }
}
