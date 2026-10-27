package org.eclipse.paho.client.mqttv3.internal;

import com.google.firebase.perf.plugin.util.AsmString;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class TCPNetworkModule implements NetworkModule {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule";
    private int conTimeout;
    private SocketFactory factory;
    private String host;
    private Logger log;
    private int port;
    protected Socket socket;

    public TCPNetworkModule(SocketFactory socketFactory, String str, int i, String str2) {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        this.log = logger;
        logger.setResourceName(str2);
        this.factory = socketFactory;
        this.host = str;
        this.port = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws MqttException, IOException {
        try {
            this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_START, "252", new Object[]{this.host, Integer.valueOf(this.port), Long.valueOf(this.conTimeout * 1000)});
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.host, this.port);
            Socket socketCreateSocket = this.factory.createSocket();
            this.socket = socketCreateSocket;
            socketCreateSocket.connect(inetSocketAddress, this.conTimeout * 1000);
            this.socket.setSoTimeout(1000);
        } catch (ConnectException e) {
            this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_START, "250", null, e);
            throw new MqttException(32103, e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }

    public void setConnectTimeout(int i) {
        this.conTimeout = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "tcp://" + this.host + ":" + this.port;
    }
}
