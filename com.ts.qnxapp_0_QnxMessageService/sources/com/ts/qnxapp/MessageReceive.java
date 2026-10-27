package com.ts.qnxapp;

import com.ts.qnxapp.util.LogUtil;
import ipc.fdbus.FdbusMessage;

/* JADX INFO: loaded from: classes.dex */
public class MessageReceive {
    private static String TAG = "MessageReceive";
    private MessageReceiveHandler mMessageReceiveHandler;

    public MessageReceive(MessageReceiveHandler messageReceiveHandler) {
        LogUtil.logI(TAG, "MessageReceive constructor method");
        this.mMessageReceiveHandler = messageReceiveHandler;
    }

    public void startReceiveHandler() {
        this.mMessageReceiveHandler.startHandleMessage();
    }

    public void receiveMessage(FdbusMessage receiveMessage, int flag, int msgType) {
        this.mMessageReceiveHandler.pushMessage(receiveMessage, flag, msgType);
    }
}
