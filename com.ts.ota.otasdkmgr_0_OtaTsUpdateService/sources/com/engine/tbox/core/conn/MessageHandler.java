package com.engine.tbox.core.conn;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class MessageHandler extends Handler {
    protected AbstractConnection mConn;

    public void addConnection(AbstractConnection conn) {
        this.mConn = conn;
    }

    public MessageHandler(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
}
