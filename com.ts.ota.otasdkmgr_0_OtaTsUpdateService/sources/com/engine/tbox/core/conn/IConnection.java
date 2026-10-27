package com.engine.tbox.core.conn;

import com.engine.tbox.core.data.CountSyncMessage;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface IConnection {
    boolean deInit();

    boolean init();

    boolean registerConnectionStateCallback(ConnectionStateChangeCallback connectionStateChangeCallback);

    boolean sendAsyncMessage(CountSyncMessage countSyncMessage);

    boolean sendByteBuffer(ByteBuffer byteBuffer);

    boolean sendSyncMessage(CountSyncMessage countSyncMessage, IResponse iResponse);

    boolean start();

    boolean stop();

    boolean unregisterConnectionStateCallback(ConnectionStateChangeCallback connectionStateChangeCallback);
}
