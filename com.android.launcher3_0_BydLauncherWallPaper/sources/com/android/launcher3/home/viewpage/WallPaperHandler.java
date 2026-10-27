package com.android.launcher3.home.viewpage;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes4.dex */
public class WallPaperHandler extends Handler {
    public WallPaperHandler(Handler.Callback callback) {
        super(callback);
    }

    public void sendHandleDelay(int what, Object obj, long time) {
        clearMessage(what);
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        sendMessageDelayed(message, time);
    }

    public void sendHandleDelay(int what) {
        clearMessage(what);
        Message message = new Message();
        message.what = what;
        message.obj = null;
        sendMessageDelayed(message, 2000L);
    }

    public void sendHandleDelay(int what, int time) {
        clearMessage(what);
        Message message = new Message();
        message.what = what;
        sendMessageDelayed(message, time);
    }

    public void sendHandlePrompty(int what, Object value) {
        clearMessage(what);
        Message message = new Message();
        message.what = what;
        message.obj = value;
        sendMessage(message);
    }

    private void clearMessage(int type) {
        removeMessages(type);
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
}
