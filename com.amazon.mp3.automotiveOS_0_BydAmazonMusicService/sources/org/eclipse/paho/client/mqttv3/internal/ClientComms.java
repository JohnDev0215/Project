package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class ClientComms {
    public static String BUILD_LEVEL = "L${build.level}";
    private static final byte CLOSED = 4;
    private static final byte CONNECTED = 0;
    private static final byte CONNECTING = 1;
    private static final byte DISCONNECTED = 3;
    private static final byte DISCONNECTING = 2;
    public static String VERSION = "${project.version}";
    private final String CLASS_NAME;
    private CommsCallback callback;
    private IMqttAsyncClient client;
    private ClientState clientState;
    private boolean closePending;
    private final Object conLock;
    private MqttConnectOptions conOptions;
    private byte conState;
    private DisconnectedMessageBuffer disconnectedMessageBuffer;
    private ExecutorService executorService;
    private final Logger log;
    private int networkModuleIndex;
    private NetworkModule[] networkModules;
    private MqttClientPersistence persistence;
    private MqttPingSender pingSender;
    private CommsReceiver receiver;
    private boolean resting;
    private CommsSender sender;
    private boolean stoppingComms;
    private CommsTokenStore tokenStore;

    public ClientComms(IMqttAsyncClient iMqttAsyncClient, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ExecutorService executorService, HighResolutionTimer highResolutionTimer) throws MqttException {
        String name = ClientComms.class.getName();
        this.CLASS_NAME = name;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        this.log = logger;
        this.stoppingComms = false;
        this.conLock = new Object();
        this.closePending = false;
        this.resting = false;
        this.conState = (byte) 3;
        this.client = iMqttAsyncClient;
        this.persistence = mqttClientPersistence;
        this.pingSender = mqttPingSender;
        mqttPingSender.init(this);
        this.executorService = executorService;
        this.tokenStore = new CommsTokenStore(getClient().getClientId());
        this.callback = new CommsCallback(this);
        ClientState clientState = new ClientState(mqttClientPersistence, this.tokenStore, this.callback, this, mqttPingSender, highResolutionTimer);
        this.clientState = clientState;
        this.callback.setClientState(clientState);
        logger.setResourceName(getClient().getClientId());
    }

    CommsReceiver getReceiver() {
        return this.receiver;
    }

    private void shutdownExecutorService() {
        MqttConnectOptions mqttConnectOptions;
        this.executorService.shutdown();
        try {
            ExecutorService executorService = this.executorService;
            if (executorService == null || (mqttConnectOptions = this.conOptions) == null) {
                return;
            }
            long executorServiceTimeout = mqttConnectOptions.getExecutorServiceTimeout();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (executorService.awaitTermination(executorServiceTimeout, timeUnit)) {
                return;
            }
            this.executorService.shutdownNow();
            if (this.executorService.awaitTermination(this.conOptions.getExecutorServiceTimeout(), timeUnit)) {
                return;
            }
            this.log.fine(this.CLASS_NAME, "shutdownExecutorService", "executorService did not terminate");
        } catch (InterruptedException unused) {
            this.executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    void internalSend(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        this.log.fine(this.CLASS_NAME, "internalSend", "200", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        if (mqttToken.getClient() == null) {
            mqttToken.internalTok.setClient(getClient());
            try {
                this.clientState.send(mqttWireMessage, mqttToken);
                return;
            } catch (MqttException e) {
                mqttToken.internalTok.setClient(null);
                if (mqttWireMessage instanceof MqttPublish) {
                    this.clientState.undo((MqttPublish) mqttWireMessage);
                }
                throw e;
            }
        }
        this.log.fine(this.CLASS_NAME, "internalSend", "213", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        throw new MqttException(32201);
    }

    public void sendNoWait(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (isConnected() || ((!isConnected() && (mqttWireMessage instanceof MqttConnect)) || (isDisconnecting() && (mqttWireMessage instanceof MqttDisconnect)))) {
            DisconnectedMessageBuffer disconnectedMessageBuffer = this.disconnectedMessageBuffer;
            if (disconnectedMessageBuffer != null && disconnectedMessageBuffer.getMessageCount() != 0) {
                this.log.fine(this.CLASS_NAME, "sendNoWait", "507", new Object[]{mqttWireMessage.getKey()});
                if (this.disconnectedMessageBuffer.isPersistBuffer()) {
                    this.clientState.persistBufferedMessage(mqttWireMessage);
                }
                this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
                return;
            }
            internalSend(mqttWireMessage, mqttToken);
            return;
        }
        if (this.disconnectedMessageBuffer != null) {
            this.log.fine(this.CLASS_NAME, "sendNoWait", "508", new Object[]{mqttWireMessage.getKey()});
            if (this.disconnectedMessageBuffer.isPersistBuffer()) {
                this.clientState.persistBufferedMessage(mqttWireMessage);
            }
            this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
            return;
        }
        this.log.fine(this.CLASS_NAME, "sendNoWait", "208");
        throw ExceptionHelper.createMqttException(32104);
    }

    public boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) throws MqttException {
        return this.clientState.removeMessage(iMqttDeliveryToken);
    }

    public void close(boolean z) throws MqttException {
        synchronized (this.conLock) {
            if (!isClosed()) {
                if (!isDisconnected() || z) {
                    this.log.fine(this.CLASS_NAME, "close", "224");
                    if (isConnecting()) {
                        throw new MqttException(32110);
                    }
                    if (isConnected()) {
                        throw ExceptionHelper.createMqttException(32100);
                    }
                    if (isDisconnecting()) {
                        this.closePending = true;
                        return;
                    }
                }
                this.conState = (byte) 4;
                this.clientState.close();
                this.clientState = null;
                this.callback = null;
                this.persistence = null;
                this.sender = null;
                this.pingSender = null;
                this.receiver = null;
                this.networkModules = null;
                this.conOptions = null;
                this.tokenStore = null;
            }
        }
    }

    public void connect(MqttConnectOptions mqttConnectOptions, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (isDisconnected() && !this.closePending) {
                this.log.fine(this.CLASS_NAME, "connect", "214");
                this.conState = (byte) 1;
                this.conOptions = mqttConnectOptions;
                MqttConnect mqttConnect = new MqttConnect(this.client.getClientId(), this.conOptions.getMqttVersion(), this.conOptions.isCleanSession(), this.conOptions.getKeepAliveInterval(), this.conOptions.getUserName(), this.conOptions.getPassword(), this.conOptions.getWillMessage(), this.conOptions.getWillDestination());
                this.clientState.setKeepAliveSecs(this.conOptions.getKeepAliveInterval());
                this.clientState.setCleanSession(this.conOptions.isCleanSession());
                this.clientState.setMaxInflight(this.conOptions.getMaxInflight());
                this.tokenStore.open();
                new ConnectBG(this, mqttToken, mqttConnect, this.executorService).start();
            } else {
                this.log.fine(this.CLASS_NAME, "connect", "207", new Object[]{Byte.valueOf(this.conState)});
                if (isClosed() || this.closePending) {
                    throw new MqttException(32111);
                }
                if (isConnecting()) {
                    throw new MqttException(32110);
                }
                if (isDisconnecting()) {
                    throw new MqttException(32102);
                }
                throw ExceptionHelper.createMqttException(32100);
            }
        }
    }

    public void connectComplete(MqttConnack mqttConnack, MqttException mqttException) throws MqttException {
        int returnCode = mqttConnack.getReturnCode();
        synchronized (this.conLock) {
            if (returnCode == 0) {
                this.log.fine(this.CLASS_NAME, "connectComplete", "215");
                this.conState = (byte) 0;
            } else {
                this.log.fine(this.CLASS_NAME, "connectComplete", "204", new Object[]{Integer.valueOf(returnCode)});
                throw mqttException;
            }
        }
    }

    public void shutdownConnection(MqttToken mqttToken, MqttException mqttException) {
        CommsCallback commsCallback;
        CommsCallback commsCallback2;
        MqttClientPersistence mqttClientPersistence;
        NetworkModule networkModule;
        synchronized (this.conLock) {
            if (!this.stoppingComms && !this.closePending && !isClosed()) {
                this.stoppingComms = true;
                this.log.fine(this.CLASS_NAME, "shutdownConnection", "216");
                boolean z = isConnected() || isDisconnecting();
                this.conState = (byte) 2;
                if (mqttToken != null && !mqttToken.isComplete()) {
                    mqttToken.internalTok.setException(mqttException);
                }
                CommsCallback commsCallback3 = this.callback;
                if (commsCallback3 != null) {
                    commsCallback3.stop();
                }
                CommsReceiver commsReceiver = this.receiver;
                if (commsReceiver != null) {
                    commsReceiver.stop();
                }
                try {
                    NetworkModule[] networkModuleArr = this.networkModules;
                    if (networkModuleArr != null && (networkModule = networkModuleArr[this.networkModuleIndex]) != null) {
                        networkModule.stop();
                    }
                } catch (Exception unused) {
                }
                this.tokenStore.quiesce(new MqttException(32102));
                MqttToken mqttTokenHandleOldTokens = handleOldTokens(mqttToken, mqttException);
                try {
                    this.clientState.disconnected(mqttException);
                    if (this.clientState.getCleanSession()) {
                        this.callback.removeMessageListeners();
                    }
                } catch (Exception unused2) {
                }
                CommsSender commsSender = this.sender;
                if (commsSender != null) {
                    commsSender.stop();
                }
                MqttPingSender mqttPingSender = this.pingSender;
                if (mqttPingSender != null) {
                    mqttPingSender.stop();
                }
                try {
                    if (this.disconnectedMessageBuffer == null && (mqttClientPersistence = this.persistence) != null) {
                        mqttClientPersistence.close();
                    }
                } catch (Exception unused3) {
                }
                synchronized (this.conLock) {
                    this.log.fine(this.CLASS_NAME, "shutdownConnection", "217");
                    this.conState = (byte) 3;
                    this.stoppingComms = false;
                }
                if (mqttTokenHandleOldTokens != null && (commsCallback2 = this.callback) != null) {
                    commsCallback2.asyncOperationComplete(mqttTokenHandleOldTokens);
                }
                if (z && (commsCallback = this.callback) != null) {
                    commsCallback.connectionLost(mqttException);
                }
                synchronized (this.conLock) {
                    if (this.closePending) {
                        try {
                            close(true);
                        } catch (Exception unused4) {
                        }
                    }
                }
            }
        }
    }

    private MqttToken handleOldTokens(MqttToken mqttToken, MqttException mqttException) {
        this.log.fine(this.CLASS_NAME, "handleOldTokens", "222");
        MqttToken mqttToken2 = null;
        if (mqttToken != null) {
            try {
                if (!mqttToken.isComplete() && this.tokenStore.getToken(mqttToken.internalTok.getKey()) == null) {
                    this.tokenStore.saveToken(mqttToken, mqttToken.internalTok.getKey());
                }
            } catch (Exception unused) {
            }
        }
        Enumeration enumerationElements = this.clientState.resolveOldTokens(mqttException).elements();
        while (enumerationElements.hasMoreElements()) {
            MqttToken mqttToken3 = (MqttToken) enumerationElements.nextElement();
            if (mqttToken3.internalTok.getKey().equals(MqttDisconnect.KEY) || mqttToken3.internalTok.getKey().equals("Con")) {
                mqttToken2 = mqttToken3;
            } else {
                this.callback.asyncOperationComplete(mqttToken3);
            }
        }
        return mqttToken2;
    }

    public void disconnect(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (isClosed()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "223");
                throw ExceptionHelper.createMqttException(32111);
            }
            if (isDisconnected()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "211");
                throw ExceptionHelper.createMqttException(32101);
            }
            if (isDisconnecting()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "219");
                throw ExceptionHelper.createMqttException(32102);
            }
            if (Thread.currentThread() == this.callback.getThread()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "210");
                throw ExceptionHelper.createMqttException(32107);
            }
            this.log.fine(this.CLASS_NAME, "disconnect", "218");
            this.conState = (byte) 2;
            new DisconnectBG(mqttDisconnect, j, mqttToken, this.executorService).start();
        }
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        disconnectForcibly(j, j2, true);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.conState = (byte) 2;
        ClientState clientState = this.clientState;
        if (clientState != null) {
            clientState.quiesce(j);
        }
        MqttToken mqttToken = new MqttToken(this.client.getClientId());
        if (z) {
            try {
                internalSend(new MqttDisconnect(), mqttToken);
                mqttToken.waitForCompletion(j2);
            } catch (Exception unused) {
            } catch (Throwable th) {
                mqttToken.internalTok.markComplete(null, null);
                shutdownConnection(mqttToken, null);
                throw th;
            }
        }
        mqttToken.internalTok.markComplete(null, null);
        shutdownConnection(mqttToken, null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 0;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = true;
            if (this.conState != 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDisconnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 3;
        }
        return z;
    }

    public boolean isDisconnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 2;
        }
        return z;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 4;
        }
        return z;
    }

    public boolean isResting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.resting;
        }
        return z;
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.callback.setCallback(mqttCallback);
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.callback.setReconnectCallback(mqttCallbackExtended);
    }

    public void setManualAcks(boolean z) {
        this.callback.setManualAcks(z);
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.callback.messageArrivedComplete(i, i2);
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.callback.setMessageListener(str, iMqttMessageListener);
    }

    public void removeMessageListener(String str) {
        this.callback.removeMessageListener(str);
    }

    protected MqttTopic getTopic(String str) {
        return new MqttTopic(str, this);
    }

    public void setNetworkModuleIndex(int i) {
        this.networkModuleIndex = i;
    }

    public int getNetworkModuleIndex() {
        return this.networkModuleIndex;
    }

    public NetworkModule[] getNetworkModules() {
        return this.networkModules;
    }

    public void setNetworkModules(NetworkModule[] networkModuleArr) {
        this.networkModules = (NetworkModule[]) networkModuleArr.clone();
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.tokenStore.getOutstandingDelTokens();
    }

    protected void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.clientState.deliveryComplete(mqttPublish);
    }

    protected void deliveryComplete(int i) throws MqttPersistenceException {
        this.clientState.deliveryComplete(i);
    }

    public IMqttAsyncClient getClient() {
        return this.client;
    }

    public long getKeepAlive() {
        return this.clientState.getKeepAlive();
    }

    public ClientState getClientState() {
        return this.clientState;
    }

    public MqttConnectOptions getConOptions() {
        return this.conOptions;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("conState", Integer.valueOf(this.conState));
        properties.put("serverURI", getClient().getServerURI());
        properties.put("callback", this.callback);
        properties.put("stoppingComms", Boolean.valueOf(this.stoppingComms));
        return properties;
    }

    private class ConnectBG implements Runnable {
        ClientComms clientComms;
        MqttConnect conPacket;
        MqttToken conToken;
        private String threadName;

        ConnectBG(ClientComms clientComms, MqttToken mqttToken, MqttConnect mqttConnect, ExecutorService executorService) {
            this.clientComms = clientComms;
            this.conToken = mqttToken;
            this.conPacket = mqttConnect;
            this.threadName = "MQTT Con: " + ClientComms.this.getClient().getClientId();
        }

        void start() {
            if (ClientComms.this.executorService != null) {
                ClientComms.this.executorService.execute(this);
            } else {
                new Thread(this).start();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName(this.threadName);
            ClientComms.this.log.fine(ClientComms.this.CLASS_NAME, "connectBG:run", "220");
            MqttException e = null;
            try {
                for (MqttDeliveryToken mqttDeliveryToken : ClientComms.this.tokenStore.getOutstandingDelTokens()) {
                    mqttDeliveryToken.internalTok.setException(null);
                }
                ClientComms.this.tokenStore.saveToken(this.conToken, this.conPacket);
                NetworkModule networkModule = ClientComms.this.networkModules[ClientComms.this.networkModuleIndex];
                networkModule.start();
                ClientComms.this.receiver = new CommsReceiver(this.clientComms, ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getInputStream());
                ClientComms.this.receiver.start("MQTT Rec: " + ClientComms.this.getClient().getClientId(), ClientComms.this.executorService);
                ClientComms.this.sender = new CommsSender(this.clientComms, ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getOutputStream());
                ClientComms.this.sender.start("MQTT Snd: " + ClientComms.this.getClient().getClientId(), ClientComms.this.executorService);
                ClientComms.this.callback.start("MQTT Call: " + ClientComms.this.getClient().getClientId(), ClientComms.this.executorService);
                ClientComms.this.internalSend(this.conPacket, this.conToken);
            } catch (MqttException e2) {
                e = e2;
                ClientComms.this.log.fine(ClientComms.this.CLASS_NAME, "connectBG:run", "212", null, e);
            } catch (Exception e3) {
                ClientComms.this.log.fine(ClientComms.this.CLASS_NAME, "connectBG:run", "209", null, e3);
                e = ExceptionHelper.createMqttException(e3);
            }
            if (e != null) {
                ClientComms.this.shutdownConnection(this.conToken, e);
            }
        }
    }

    private class DisconnectBG implements Runnable {
        MqttDisconnect disconnect;
        long quiesceTimeout;
        private String threadName;
        MqttToken token;

        DisconnectBG(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken, ExecutorService executorService) {
            this.disconnect = mqttDisconnect;
            this.quiesceTimeout = j;
            this.token = mqttToken;
        }

        void start() {
            this.threadName = "MQTT Disc: " + ClientComms.this.getClient().getClientId();
            if (ClientComms.this.executorService != null) {
                ClientComms.this.executorService.execute(this);
            } else {
                new Thread(this).start();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x00ae  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r4 = this;
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                java.lang.String r1 = r4.threadName
                r0.setName(r1)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.logging.Logger r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$1(r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                java.lang.String r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$2(r1)
                java.lang.String r2 = "disconnectBG:run"
                java.lang.String r3 = "221"
                r0.fine(r1, r2, r3)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.ClientState r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$6(r0)
                long r1 = r4.quiesceTimeout
                r0.quiesce(r1)
                r0 = 0
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect r2 = r4.disconnect     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.MqttToken r3 = r4.token     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                r1.internalSend(r2, r3)     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r1)     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                if (r1 == 0) goto L4c
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r1)     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                boolean r1 = r1.isRunning()     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                if (r1 == 0) goto L4c
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.token     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                r1.waitUntilSent()     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
            L4c:
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.token
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok
                r1.markComplete(r0, r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r1)
                if (r1 == 0) goto Lae
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r1)
                boolean r1 = r1.isRunning()
                if (r1 != 0) goto Lb5
                goto Lae
            L68:
                r1 = move-exception
                org.eclipse.paho.client.mqttv3.MqttToken r2 = r4.token
                org.eclipse.paho.client.mqttv3.internal.Token r2 = r2.internalTok
                r2.markComplete(r0, r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r2)
                if (r2 == 0) goto L84
                org.eclipse.paho.client.mqttv3.internal.ClientComms r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r2)
                boolean r2 = r2.isRunning()
                if (r2 != 0) goto L8b
            L84:
                org.eclipse.paho.client.mqttv3.MqttToken r2 = r4.token
                org.eclipse.paho.client.mqttv3.internal.Token r2 = r2.internalTok
                r2.notifyComplete()
            L8b:
                org.eclipse.paho.client.mqttv3.internal.ClientComms r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.MqttToken r3 = r4.token
                r2.shutdownConnection(r3, r0)
                throw r1
            L93:
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.token
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok
                r1.markComplete(r0, r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r1)
                if (r1 == 0) goto Lae
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.access$10(r1)
                boolean r1 = r1.isRunning()
                if (r1 != 0) goto Lb5
            Lae:
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.token
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok
                r1.notifyComplete()
            Lb5:
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.MqttToken r2 = r4.token
                r1.shutdownConnection(r2, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientComms.DisconnectBG.run():void");
        }
    }

    public MqttToken checkForActivity() {
        return checkForActivity(null);
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) {
        try {
            return this.clientState.checkForActivity(iMqttActionListener);
        } catch (MqttException e) {
            handleRunException(e);
            return null;
        } catch (Exception e2) {
            handleRunException(e2);
            return null;
        }
    }

    private void handleRunException(Exception exc) {
        MqttException mqttException;
        this.log.fine(this.CLASS_NAME, "handleRunException", "804", null, exc);
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        shutdownConnection(null, mqttException);
    }

    public void setRestingState(boolean z) {
        this.resting = z;
    }

    public void setDisconnectedMessageBuffer(DisconnectedMessageBuffer disconnectedMessageBuffer) {
        this.disconnectedMessageBuffer = disconnectedMessageBuffer;
    }

    public int getBufferedMessageCount() {
        return this.disconnectedMessageBuffer.getMessageCount();
    }

    public MqttMessage getBufferedMessage(int i) {
        return ((MqttPublish) this.disconnectedMessageBuffer.getMessage(i).getMessage()).getMessage();
    }

    public void deleteBufferedMessage(int i) {
        this.disconnectedMessageBuffer.deleteMessage(i);
    }

    public void notifyConnect() {
        if (this.disconnectedMessageBuffer != null) {
            this.log.fine(this.CLASS_NAME, "notifyConnect", "509", null);
            this.disconnectedMessageBuffer.setPublishCallback(new ReconnectDisconnectedBufferCallback("notifyConnect"));
            this.disconnectedMessageBuffer.setMessageDiscardedCallBack(new MessageDiscardedCallback());
            ExecutorService executorService = this.executorService;
            if (executorService == null) {
                new Thread(this.disconnectedMessageBuffer).start();
            } else {
                executorService.execute(this.disconnectedMessageBuffer);
            }
        }
    }

    class MessageDiscardedCallback implements IDiscardedBufferMessageCallback {
        MessageDiscardedCallback() {
        }

        @Override // org.eclipse.paho.client.mqttv3.internal.IDiscardedBufferMessageCallback
        public void messageDiscarded(MqttWireMessage mqttWireMessage) {
            if (ClientComms.this.disconnectedMessageBuffer.isPersistBuffer()) {
                ClientComms.this.clientState.unPersistBufferedMessage(mqttWireMessage);
            }
        }
    }

    class ReconnectDisconnectedBufferCallback implements IDisconnectedBufferCallback {
        final String methodName;

        ReconnectDisconnectedBufferCallback(String str) {
            this.methodName = str;
        }

        @Override // org.eclipse.paho.client.mqttv3.internal.IDisconnectedBufferCallback
        public void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException {
            if (!ClientComms.this.isConnected()) {
                ClientComms.this.log.fine(ClientComms.this.CLASS_NAME, this.methodName, "208");
                throw ExceptionHelper.createMqttException(32104);
            }
            while (ClientComms.this.clientState.getActualInFlight() >= ClientComms.this.clientState.getMaxInFlight() - 3) {
                Thread.yield();
            }
            ClientComms.this.log.fine(ClientComms.this.CLASS_NAME, this.methodName, "510", new Object[]{bufferedMessage.getMessage().getKey()});
            ClientComms.this.internalSend(bufferedMessage.getMessage(), bufferedMessage.getToken());
            ClientComms.this.clientState.unPersistBufferedMessage(bufferedMessage.getMessage());
        }
    }

    public int getActualInFlight() {
        return this.clientState.getActualInFlight();
    }
}
