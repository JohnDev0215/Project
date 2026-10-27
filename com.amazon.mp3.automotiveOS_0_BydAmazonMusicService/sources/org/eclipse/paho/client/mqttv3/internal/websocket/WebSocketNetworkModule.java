package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.nio.ByteBuffer;
import java.util.Properties;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class WebSocketNetworkModule extends TCPNetworkModule {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule";
    private Properties customWebsocketHeaders;
    private String host;
    private Logger log;
    private ByteArrayOutputStream outputStream;
    private PipedInputStream pipedInputStream;
    private int port;
    ByteBuffer recievedPayload;
    private String uri;
    private WebSocketReceiver webSocketReceiver;

    public WebSocketNetworkModule(SocketFactory socketFactory, String str, String str2, int i, String str3, Properties properties) {
        super(socketFactory, str2, i, str3);
        this.log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        this.outputStream = new ExtendedByteArrayOutputStream(this);
        this.uri = str;
        this.host = str2;
        this.port = i;
        this.customWebsocketHeaders = properties;
        this.pipedInputStream = new PipedInputStream();
        this.log.setResourceName(str3);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws MqttException, IOException {
        super.start();
        new WebSocketHandshake(getSocketInputStream(), getSocketOutputStream(), this.uri, this.host, this.port, this.customWebsocketHeaders).execute();
        WebSocketReceiver webSocketReceiver = new WebSocketReceiver(getSocketInputStream(), this.pipedInputStream);
        this.webSocketReceiver = webSocketReceiver;
        webSocketReceiver.start("webSocketReceiver");
    }

    OutputStream getSocketOutputStream() throws IOException {
        return super.getOutputStream();
    }

    InputStream getSocketInputStream() throws IOException {
        return super.getInputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.pipedInputStream;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.outputStream;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        getSocketOutputStream().write(new WebSocketFrame((byte) 8, true, "1000".getBytes()).encodeFrame());
        getSocketOutputStream().flush();
        WebSocketReceiver webSocketReceiver = this.webSocketReceiver;
        if (webSocketReceiver != null) {
            webSocketReceiver.stop();
        }
        super.stop();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "ws://" + this.host + ":" + this.port;
    }
}
