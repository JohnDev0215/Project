package com.engine.tbox.core.net;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/* JADX INFO: loaded from: classes.dex */
public interface IEventHandler {
    default boolean onRead(SocketChannel channel, ByteBuffer buffer) {
        return true;
    }

    default boolean onNewConnection(SocketChannel channel) {
        return true;
    }

    default boolean onConnectFailed(SocketChannel channel) {
        return true;
    }

    default boolean onConnectionSuccess(SocketChannel channel) {
        return true;
    }

    default boolean onSocketError(SocketChannel channel) {
        return true;
    }

    default boolean onExit() {
        return true;
    }
}
