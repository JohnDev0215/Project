package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public interface MessageReceiverWithResponder extends MessageReceiver {
    boolean acceptWithResponder(Message message, MessageReceiver messageReceiver);
}
