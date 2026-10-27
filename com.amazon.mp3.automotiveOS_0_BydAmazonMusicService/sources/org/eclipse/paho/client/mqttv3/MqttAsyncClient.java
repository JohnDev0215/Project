package org.eclipse.paho.client.mqttv3;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.HighResolutionTimer;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModuleService;
import org.eclipse.paho.client.mqttv3.internal.SystemHighResolutionTimer;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* JADX INFO: loaded from: classes6.dex */
public class MqttAsyncClient implements IMqttAsyncClient {
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000;
    private static final char MAX_HIGH_SURROGATE = 56319;
    private static final char MIN_HIGH_SURROGATE = 55296;
    private static final long QUIESCE_TIMEOUT = 30000;
    private String clientId;
    protected ClientComms comms;
    private MqttConnectOptions connOpts;
    private ScheduledExecutorService executorService;
    private Logger log;
    private MqttCallback mqttCallback;
    private MqttClientPersistence persistence;
    private Timer reconnectTimer;
    private boolean reconnecting;
    private String serverURI;
    private Hashtable topics;
    private Object userContext;
    private static final String CLASS_NAME = MqttAsyncClient.class.getName();
    private static int reconnectDelay = 1000;
    private static final Object clientLock = new Object();

    protected static boolean Character_isHighSurrogate(char c) {
        return c >= 55296 && c <= 56319;
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this(str, str2, mqttClientPersistence, mqttPingSender, null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ScheduledExecutorService scheduledExecutorService) throws MqttException {
        this(str, str2, mqttClientPersistence, mqttPingSender, scheduledExecutorService, null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ScheduledExecutorService scheduledExecutorService, HighResolutionTimer highResolutionTimer) throws MqttException {
        ScheduledExecutorService scheduledExecutorService2;
        HighResolutionTimer systemHighResolutionTimer;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        this.log = logger;
        this.reconnecting = false;
        logger.setResourceName(str2);
        if (str2 == null) {
            throw new IllegalArgumentException("Null clientId");
        }
        int i = 0;
        int i2 = 0;
        while (i < str2.length() - 1) {
            if (Character_isHighSurrogate(str2.charAt(i))) {
                i++;
            }
            i2++;
            i++;
        }
        if (i2 > 65535) {
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        NetworkModuleService.validateURI(str);
        this.serverURI = str;
        this.clientId = str2;
        this.persistence = mqttClientPersistence;
        if (mqttClientPersistence == null) {
            this.persistence = new MemoryPersistence();
        }
        if (highResolutionTimer == null) {
            scheduledExecutorService2 = scheduledExecutorService;
            systemHighResolutionTimer = new SystemHighResolutionTimer();
        } else {
            scheduledExecutorService2 = scheduledExecutorService;
            systemHighResolutionTimer = highResolutionTimer;
        }
        this.executorService = scheduledExecutorService2;
        this.log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
        this.persistence.open(str2, str);
        this.comms = new ClientComms(this, this.persistence, mqttPingSender, this.executorService, systemHighResolutionTimer);
        this.persistence.close();
        this.topics = new Hashtable();
    }

    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) throws MqttException {
        this.log.fine(CLASS_NAME, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null || serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i = 0; i < serverURIs.length; i++) {
            networkModuleArr[i] = createNetworkModule(serverURIs[i], mqttConnectOptions);
        }
        this.log.fine(CLASS_NAME, "createNetworkModules", "108");
        return networkModuleArr;
    }

    private NetworkModule createNetworkModule(String str, MqttConnectOptions mqttConnectOptions) throws MqttException {
        this.log.fine(CLASS_NAME, "createNetworkModule", "115", new Object[]{str});
        return NetworkModuleService.createInstance(str, mqttConnectOptions, this.clientId);
    }

    private String getHostName(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            iIndexOf = str.indexOf(47);
        }
        if (iIndexOf == -1) {
            iIndexOf = str.length();
        }
        return str.substring(0, iIndexOf);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect() throws MqttException {
        return connect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException {
        return connect(mqttConnectOptions, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (this.comms.isClosed()) {
            throw new MqttException(32111);
        }
        if (mqttConnectOptions == null) {
            mqttConnectOptions = new MqttConnectOptions();
        }
        MqttConnectOptions mqttConnectOptions2 = mqttConnectOptions;
        this.connOpts = mqttConnectOptions2;
        this.userContext = obj;
        boolean zIsAutomaticReconnect = mqttConnectOptions2.isAutomaticReconnect();
        Logger logger = this.log;
        String str = CLASS_NAME;
        Object[] objArr = new Object[8];
        objArr[0] = Boolean.valueOf(mqttConnectOptions2.isCleanSession());
        objArr[1] = Integer.valueOf(mqttConnectOptions2.getConnectionTimeout());
        objArr[2] = Integer.valueOf(mqttConnectOptions2.getKeepAliveInterval());
        objArr[3] = mqttConnectOptions2.getUserName();
        objArr[4] = mqttConnectOptions2.getPassword() == null ? "[null]" : "[notnull]";
        objArr[5] = mqttConnectOptions2.getWillMessage() != null ? "[notnull]" : "[null]";
        objArr[6] = obj;
        objArr[7] = iMqttActionListener;
        logger.fine(str, "connect", "103", objArr);
        this.comms.setNetworkModules(createNetworkModules(this.serverURI, mqttConnectOptions2));
        this.comms.setReconnectCallback(new MqttReconnectCallback(zIsAutomaticReconnect));
        MqttToken mqttToken = new MqttToken(getClientId());
        ConnectActionListener connectActionListener = new ConnectActionListener(this, this.persistence, this.comms, mqttConnectOptions2, mqttToken, obj, iMqttActionListener, this.reconnecting);
        mqttToken.setActionCallback(connectActionListener);
        mqttToken.setUserContext(this);
        MqttCallback mqttCallback = this.mqttCallback;
        if (mqttCallback instanceof MqttCallbackExtended) {
            connectActionListener.setMqttCallbackExtended((MqttCallbackExtended) mqttCallback);
        }
        this.comms.setNetworkModuleIndex(0);
        connectActionListener.connect();
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(30000L, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j) throws MqttException {
        return disconnect(j, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "disconnect", "104", new Object[]{Long.valueOf(j), obj, iMqttActionListener});
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.comms.disconnect(new MqttDisconnect(), j, mqttToken);
            this.log.fine(str, "disconnect", "108");
            return mqttToken;
        } catch (MqttException e) {
            this.log.fine(CLASS_NAME, "disconnect", "105", null, e);
            throw e;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(30000L, 10000L);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j) throws MqttException {
        disconnectForcibly(30000L, j);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.comms.disconnectForcibly(j, j2);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.comms.disconnectForcibly(j, j2, z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean isConnected() {
        return this.comms.isConnected();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getClientId() {
        return this.clientId;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getServerURI() {
        return this.serverURI;
    }

    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }

    protected MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.topics.get(str);
        if (mqttTopic != null) {
            return mqttTopic;
        }
        MqttTopic mqttTopic2 = new MqttTopic(str, this.comms);
        this.topics.put(str, mqttTopic2);
        return mqttTopic2;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "ping", "117");
        MqttToken mqttTokenCheckForActivity = this.comms.checkForActivity(iMqttActionListener);
        this.log.fine(str, "ping", "118");
        return mqttTokenCheckForActivity;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (String str : strArr) {
            MqttTopic.validate(str, true);
            this.comms.removeMessageListener(str);
        }
        return subscribeBase(strArr, iArr, obj, iMqttActionListener);
    }

    private IMqttToken subscribeBase(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (this.log.isLoggable(5)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("topic=");
                stringBuffer.append(strArr[i]);
                stringBuffer.append(" qos=");
                stringBuffer.append(iArr[i]);
            }
            this.log.fine(CLASS_NAME, "subscribe", "106", new Object[]{stringBuffer.toString(), obj, iMqttActionListener});
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
        this.log.fine(CLASS_NAME, "subscribe", "109");
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) throws Exception {
        IMqttMessageListener iMqttMessageListener;
        if ((iMqttMessageListenerArr != null && iMqttMessageListenerArr.length != iArr.length) || iArr.length != strArr.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < strArr.length; i++) {
            MqttTopic.validate(strArr[i], true);
            if (iMqttMessageListenerArr == null || (iMqttMessageListener = iMqttMessageListenerArr[i]) == null) {
                this.comms.removeMessageListener(strArr[i]);
            } else {
                this.comms.setMessageListener(strArr[i], iMqttMessageListener);
            }
        }
        try {
            return subscribeBase(strArr, iArr, obj, iMqttActionListener);
        } catch (Exception e) {
            for (String str : strArr) {
                this.comms.removeMessageListener(str);
            }
            throw e;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (this.log.isLoggable(5)) {
            String str = "";
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    str = String.valueOf(str) + ", ";
                }
                str = String.valueOf(str) + strArr[i];
            }
            this.log.fine(CLASS_NAME, "unsubscribe", "107", new Object[]{str, obj, iMqttActionListener});
        }
        for (String str2 : strArr) {
            MqttTopic.validate(str2, true);
        }
        for (String str3 : strArr) {
            this.comms.removeMessageListener(str3);
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        this.log.fine(CLASS_NAME, "unsubscribe", "110");
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) throws MqttException {
        return this.comms.removeMessage(iMqttDeliveryToken);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setManualAcks(boolean z) {
        this.comms.setManualAcks(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.comms.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return CLIENT_ID_PREFIX + System.nanoTime();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException {
        return publish(str, bArr, i, z, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = this.log;
        String str2 = CLASS_NAME;
        logger.fine(str2, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.setMessage(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        this.log.fine(str2, "publish", "112");
        return mqttDeliveryToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void reconnect() throws MqttException {
        this.log.fine(CLASS_NAME, "reconnect", "500", new Object[]{this.clientId});
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (this.comms.isClosed()) {
            throw new MqttException(32111);
        }
        stopReconnectCycle();
        attemptReconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptReconnect() {
        this.log.fine(CLASS_NAME, "attemptReconnect", "500", new Object[]{this.clientId});
        try {
            connect(this.connOpts, this.userContext, new MqttReconnectActionListener("attemptReconnect"));
        } catch (MqttSecurityException e) {
            this.log.fine(CLASS_NAME, "attemptReconnect", "804", null, e);
        } catch (MqttException e2) {
            this.log.fine(CLASS_NAME, "attemptReconnect", "804", null, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReconnectCycle() {
        this.log.fine(CLASS_NAME, "startReconnectCycle", "503", new Object[]{this.clientId, Long.valueOf(reconnectDelay)});
        Timer timer = new Timer("MQTT Reconnect: " + this.clientId);
        this.reconnectTimer = timer;
        timer.schedule(new ReconnectTask(this, null), (long) reconnectDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopReconnectCycle() {
        this.log.fine(CLASS_NAME, "stopReconnectCycle", "504", new Object[]{this.clientId});
        synchronized (clientLock) {
            if (this.connOpts.isAutomaticReconnect()) {
                Timer timer = this.reconnectTimer;
                if (timer != null) {
                    timer.cancel();
                    this.reconnectTimer = null;
                }
                reconnectDelay = 1000;
            }
        }
    }

    private class ReconnectTask extends TimerTask {
        private static final String methodName = "ReconnectTask.run";

        private ReconnectTask() {
        }

        /* synthetic */ ReconnectTask(MqttAsyncClient mqttAsyncClient, ReconnectTask reconnectTask) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MqttAsyncClient.this.log.fine(MqttAsyncClient.CLASS_NAME, methodName, "506");
            MqttAsyncClient.this.attemptReconnect();
        }
    }

    class MqttReconnectCallback implements MqttCallbackExtended {
        final boolean automaticReconnect;

        @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
        public void connectComplete(boolean z, String str) {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        }

        MqttReconnectCallback(boolean z) {
            this.automaticReconnect = z;
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void connectionLost(Throwable th) {
            if (this.automaticReconnect) {
                MqttAsyncClient.this.comms.setRestingState(true);
                MqttAsyncClient.this.reconnecting = true;
                MqttAsyncClient.this.startReconnectCycle();
            }
        }
    }

    class MqttReconnectActionListener implements IMqttActionListener {
        final String methodName;

        MqttReconnectActionListener(String str) {
            this.methodName = str;
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            MqttAsyncClient.this.log.fine(MqttAsyncClient.CLASS_NAME, this.methodName, "501", new Object[]{iMqttToken.getClient().getClientId()});
            MqttAsyncClient.this.comms.setRestingState(false);
            MqttAsyncClient.this.stopReconnectCycle();
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            MqttAsyncClient.this.log.fine(MqttAsyncClient.CLASS_NAME, this.methodName, "502", new Object[]{iMqttToken.getClient().getClientId()});
            if (MqttAsyncClient.reconnectDelay < MqttAsyncClient.this.connOpts.getMaxReconnectDelay()) {
                MqttAsyncClient.reconnectDelay *= 2;
            }
            rescheduleReconnectCycle(MqttAsyncClient.reconnectDelay);
        }

        private void rescheduleReconnectCycle(int i) {
            MqttAsyncClient.this.log.fine(MqttAsyncClient.CLASS_NAME, String.valueOf(this.methodName) + ":rescheduleReconnectCycle", "505", new Object[]{MqttAsyncClient.this.clientId, String.valueOf(MqttAsyncClient.reconnectDelay)});
            synchronized (MqttAsyncClient.clientLock) {
                if (MqttAsyncClient.this.connOpts.isAutomaticReconnect()) {
                    if (MqttAsyncClient.this.reconnectTimer != null) {
                        MqttAsyncClient.this.reconnectTimer.schedule(new ReconnectTask(MqttAsyncClient.this, null), i);
                    } else {
                        MqttAsyncClient.reconnectDelay = i;
                        MqttAsyncClient.this.startReconnectCycle();
                    }
                }
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public MqttMessage getBufferedMessage(int i) {
        return this.comms.getBufferedMessage(i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void deleteBufferedMessage(int i) {
        this.comms.deleteBufferedMessage(i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public int getInFlightMessageCount() {
        return this.comms.getActualInFlight();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient, java.lang.AutoCloseable
    public void close() throws MqttException {
        close(false);
    }

    public void close(boolean z) throws MqttException {
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "close", "113");
        this.comms.close(z);
        this.log.fine(str, "close", "114");
    }

    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }
}
