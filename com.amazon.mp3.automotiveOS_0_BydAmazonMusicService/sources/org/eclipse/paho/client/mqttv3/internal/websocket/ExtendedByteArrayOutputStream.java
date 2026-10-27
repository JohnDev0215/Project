package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes6.dex */
class ExtendedByteArrayOutputStream extends ByteArrayOutputStream {
    final WebSocketNetworkModule webSocketNetworkModule;
    final WebSocketSecureNetworkModule webSocketSecureNetworkModule;

    ExtendedByteArrayOutputStream(WebSocketNetworkModule webSocketNetworkModule) {
        this.webSocketNetworkModule = webSocketNetworkModule;
        this.webSocketSecureNetworkModule = null;
    }

    ExtendedByteArrayOutputStream(WebSocketSecureNetworkModule webSocketSecureNetworkModule) {
        this.webSocketNetworkModule = null;
        this.webSocketSecureNetworkModule = webSocketSecureNetworkModule;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ByteBuffer byteBufferWrap;
        synchronized (this) {
            byteBufferWrap = ByteBuffer.wrap(toByteArray());
            reset();
        }
        getSocketOutputStream().write(new WebSocketFrame((byte) 2, true, byteBufferWrap.array()).encodeFrame());
        getSocketOutputStream().flush();
    }

    OutputStream getSocketOutputStream() throws IOException {
        WebSocketNetworkModule webSocketNetworkModule = this.webSocketNetworkModule;
        if (webSocketNetworkModule != null) {
            return webSocketNetworkModule.getSocketOutputStream();
        }
        WebSocketSecureNetworkModule webSocketSecureNetworkModule = this.webSocketSecureNetworkModule;
        if (webSocketSecureNetworkModule != null) {
            return webSocketSecureNetworkModule.getSocketOutputStream();
        }
        return null;
    }
}
