package com.ts.qnxapp;

import com.ts.qnxapp.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class MessageSender {
    private static String TAG = "MessageSender";
    private MessageSenderHandler mMessageSenderHandler;

    public MessageSender(MessageSenderHandler messageSenderHandler) {
        LogUtil.logI(TAG, "MessageSender constructor method");
        this.mMessageSenderHandler = messageSenderHandler;
    }

    public void startMessageHandler() {
        this.mMessageSenderHandler.startHandleMessage();
    }

    public boolean sendMessage(IpcMessage ipcMessage) {
        this.mMessageSenderHandler.pushMessage(ipcMessage);
        return true;
    }
}
