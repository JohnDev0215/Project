package com.neusoft.optimus.utility.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
public class ActionThread {
    private Handler _handler;
    private HandlerThread _thread;

    public ActionThread(String str) {
        this._thread = null;
        this._handler = null;
        HandlerThread handlerThread = new HandlerThread(str);
        this._thread = handlerThread;
        handlerThread.start();
        this._handler = new Handler(this._thread.getLooper(), new HandlerCallback());
    }

    public void postAction(Runnable runnable) {
        this._handler.post(runnable);
    }

    public void quit() {
        this._thread.getLooper().quit();
    }

    private static class HandlerCallback implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            return false;
        }

        private HandlerCallback() {
        }
    }
}
