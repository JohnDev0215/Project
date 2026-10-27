package org.eclipse.paho.client.mqttv3.internal;

import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class ClientState {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.ClientState";
    private static final int MAX_MSG_ID = 65535;
    private static final int MIN_MSG_ID = 1;
    private static final String PERSISTENCE_CONFIRMED_PREFIX = "sc-";
    private static final String PERSISTENCE_RECEIVED_PREFIX = "r-";
    private static final String PERSISTENCE_SENT_BUFFERED_PREFIX = "sb-";
    private static final String PERSISTENCE_SENT_PREFIX = "s-";
    private int actualInFlight;
    private CommsCallback callback;
    private boolean cleanSession;
    private ClientComms clientComms;
    private boolean connected;
    private HighResolutionTimer highResolutionTimer;
    private int inFlightPubRels;
    private Hashtable inUseMsgIds;
    private Hashtable inboundQoS2;
    private long keepAliveNanos;
    private long lastInboundActivity;
    private long lastOutboundActivity;
    private long lastPing;
    private Logger log;
    private int maxInflight;
    private int nextMsgId;
    private Hashtable outboundQoS0;
    private Hashtable outboundQoS1;
    private Hashtable outboundQoS2;
    private volatile Vector pendingFlows;
    private volatile Vector pendingMessages;
    private MqttClientPersistence persistence;
    private MqttWireMessage pingCommand;
    private int pingOutstanding;
    private final Object pingOutstandingLock;
    private MqttPingSender pingSender;
    private final Object queueLock;
    private final Object quiesceLock;
    private boolean quiescing;
    private CommsTokenStore tokenStore;

    protected ClientState(MqttClientPersistence mqttClientPersistence, CommsTokenStore commsTokenStore, CommsCallback commsCallback, ClientComms clientComms, MqttPingSender mqttPingSender, HighResolutionTimer highResolutionTimer) throws MqttException {
        String str = CLASS_NAME;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, str);
        this.log = logger;
        this.nextMsgId = 0;
        this.clientComms = null;
        this.callback = null;
        this.maxInflight = 0;
        this.actualInFlight = 0;
        this.inFlightPubRels = 0;
        this.queueLock = new Object();
        this.quiesceLock = new Object();
        this.quiescing = false;
        this.lastOutboundActivity = 0L;
        this.lastInboundActivity = 0L;
        this.lastPing = 0L;
        this.pingOutstandingLock = new Object();
        this.pingOutstanding = 0;
        this.connected = false;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.pingSender = null;
        logger.setResourceName(clientComms.getClient().getClientId());
        this.log.finer(str, "<Init>", "");
        this.inUseMsgIds = new Hashtable();
        this.pendingFlows = new Vector();
        this.outboundQoS2 = new Hashtable();
        this.outboundQoS1 = new Hashtable();
        this.outboundQoS0 = new Hashtable();
        this.inboundQoS2 = new Hashtable();
        this.pingCommand = new MqttPingReq();
        this.inFlightPubRels = 0;
        this.actualInFlight = 0;
        this.persistence = mqttClientPersistence;
        this.callback = commsCallback;
        this.tokenStore = commsTokenStore;
        this.clientComms = clientComms;
        this.pingSender = mqttPingSender;
        this.highResolutionTimer = highResolutionTimer;
        restoreState();
    }

    protected void setMaxInflight(int i) {
        this.maxInflight = i;
        this.pendingMessages = new Vector(this.maxInflight);
    }

    protected void setKeepAliveSecs(long j) {
        this.keepAliveNanos = TimeUnit.SECONDS.toNanos(j);
    }

    protected long getKeepAlive() {
        return TimeUnit.NANOSECONDS.toMillis(this.keepAliveNanos);
    }

    protected void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    protected boolean getCleanSession() {
        return this.cleanSession;
    }

    private String getSendPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_SENT_PREFIX + mqttWireMessage.getMessageId();
    }

    private String getSendPersistenceKey(int i) {
        return PERSISTENCE_SENT_PREFIX + i;
    }

    private String getSendConfirmPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_CONFIRMED_PREFIX + mqttWireMessage.getMessageId();
    }

    private String getReceivedPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_RECEIVED_PREFIX + mqttWireMessage.getMessageId();
    }

    private String getReceivedPersistenceKey(int i) {
        return PERSISTENCE_RECEIVED_PREFIX + i;
    }

    private String getSendBufferedPersistenceKey(MqttWireMessage mqttWireMessage) {
        return PERSISTENCE_SENT_BUFFERED_PREFIX + mqttWireMessage.getMessageId();
    }

    protected void clearState() throws MqttException {
        this.log.fine(CLASS_NAME, "clearState", ">");
        this.persistence.clear();
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
    }

    private MqttWireMessage restoreMessage(String str, MqttPersistable mqttPersistable) throws MqttException {
        MqttWireMessage mqttWireMessageCreateWireMessage;
        try {
            mqttWireMessageCreateWireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        } catch (MqttException e) {
            this.log.fine(CLASS_NAME, "restoreMessage", "602", new Object[]{str}, e);
            if (!(e.getCause() instanceof EOFException)) {
                throw e;
            }
            if (str != null) {
                this.persistence.remove(str);
            }
            mqttWireMessageCreateWireMessage = null;
        }
        this.log.fine(CLASS_NAME, "restoreMessage", "601", new Object[]{str, mqttWireMessageCreateWireMessage});
        return mqttWireMessageCreateWireMessage;
    }

    private void insertInOrder(Vector vector, MqttWireMessage mqttWireMessage) {
        int messageId = mqttWireMessage.getMessageId();
        for (int i = 0; i < vector.size(); i++) {
            if (((MqttWireMessage) vector.elementAt(i)).getMessageId() > messageId) {
                vector.insertElementAt(mqttWireMessage, i);
                return;
            }
        }
        vector.addElement(mqttWireMessage);
    }

    private Vector reOrder(Vector vector) {
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < vector.size()) {
            int messageId = ((MqttWireMessage) vector.elementAt(i)).getMessageId();
            int i5 = messageId - i2;
            if (i5 > i3) {
                i4 = i;
                i3 = i5;
            }
            i++;
            i2 = messageId;
        }
        int i6 = (65535 - i2) + ((MqttWireMessage) vector.elementAt(0)).getMessageId() > i3 ? 0 : i4;
        for (int i7 = i6; i7 < vector.size(); i7++) {
            vector2.addElement(vector.elementAt(i7));
        }
        for (int i8 = 0; i8 < i6; i8++) {
            vector2.addElement(vector.elementAt(i8));
        }
        return vector2;
    }

    protected void restoreState() throws MqttException {
        Enumeration enumerationKeys = this.persistence.keys();
        int iMax = this.nextMsgId;
        Vector vector = new Vector();
        this.log.fine(CLASS_NAME, "restoreState", "600");
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            MqttWireMessage mqttWireMessageRestoreMessage = restoreMessage(str, this.persistence.get(str));
            if (mqttWireMessageRestoreMessage != null) {
                if (str.startsWith(PERSISTENCE_RECEIVED_PREFIX)) {
                    this.log.fine(CLASS_NAME, "restoreState", "604", new Object[]{str, mqttWireMessageRestoreMessage});
                    this.inboundQoS2.put(Integer.valueOf(mqttWireMessageRestoreMessage.getMessageId()), mqttWireMessageRestoreMessage);
                } else if (str.startsWith(PERSISTENCE_SENT_PREFIX)) {
                    MqttPublish mqttPublish = (MqttPublish) mqttWireMessageRestoreMessage;
                    iMax = Math.max(mqttPublish.getMessageId(), iMax);
                    if (this.persistence.containsKey(getSendConfirmPersistenceKey(mqttPublish))) {
                        MqttPubRel mqttPubRel = (MqttPubRel) restoreMessage(str, this.persistence.get(getSendConfirmPersistenceKey(mqttPublish)));
                        if (mqttPubRel != null) {
                            this.log.fine(CLASS_NAME, "restoreState", "605", new Object[]{str, mqttWireMessageRestoreMessage});
                            this.outboundQoS2.put(Integer.valueOf(mqttPubRel.getMessageId()), mqttPubRel);
                        } else {
                            this.log.fine(CLASS_NAME, "restoreState", "606", new Object[]{str, mqttWireMessageRestoreMessage});
                        }
                    } else {
                        mqttPublish.setDuplicate(true);
                        if (mqttPublish.getMessage().getQos() == 2) {
                            this.log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, mqttWireMessageRestoreMessage});
                            this.outboundQoS2.put(Integer.valueOf(mqttPublish.getMessageId()), mqttPublish);
                        } else {
                            this.log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, mqttWireMessageRestoreMessage});
                            this.outboundQoS1.put(Integer.valueOf(mqttPublish.getMessageId()), mqttPublish);
                        }
                    }
                    this.tokenStore.restoreToken(mqttPublish).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(Integer.valueOf(mqttPublish.getMessageId()), Integer.valueOf(mqttPublish.getMessageId()));
                } else if (str.startsWith(PERSISTENCE_SENT_BUFFERED_PREFIX)) {
                    MqttPublish mqttPublish2 = (MqttPublish) mqttWireMessageRestoreMessage;
                    iMax = Math.max(mqttPublish2.getMessageId(), iMax);
                    if (mqttPublish2.getMessage().getQos() == 2) {
                        this.log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, mqttWireMessageRestoreMessage});
                        this.outboundQoS2.put(Integer.valueOf(mqttPublish2.getMessageId()), mqttPublish2);
                    } else if (mqttPublish2.getMessage().getQos() == 1) {
                        this.log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, mqttWireMessageRestoreMessage});
                        this.outboundQoS1.put(Integer.valueOf(mqttPublish2.getMessageId()), mqttPublish2);
                    } else {
                        this.log.fine(CLASS_NAME, "restoreState", "511", new Object[]{str, mqttWireMessageRestoreMessage});
                        this.outboundQoS0.put(Integer.valueOf(mqttPublish2.getMessageId()), mqttPublish2);
                        this.persistence.remove(str);
                    }
                    this.tokenStore.restoreToken(mqttPublish2).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(Integer.valueOf(mqttPublish2.getMessageId()), Integer.valueOf(mqttPublish2.getMessageId()));
                } else if (str.startsWith(PERSISTENCE_CONFIRMED_PREFIX) && !this.persistence.containsKey(getSendPersistenceKey((MqttPubRel) mqttWireMessageRestoreMessage))) {
                    vector.addElement(str);
                }
            }
        }
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            String str2 = (String) enumerationElements.nextElement();
            this.log.fine(CLASS_NAME, "restoreState", "609", new Object[]{str2});
            this.persistence.remove(str2);
        }
        this.nextMsgId = iMax;
    }

    private void restoreInflightMessages() {
        this.pendingMessages = new Vector(this.maxInflight);
        this.pendingFlows = new Vector();
        Enumeration enumerationKeys = this.outboundQoS2.keys();
        while (enumerationKeys.hasMoreElements()) {
            Object objNextElement = enumerationKeys.nextElement();
            MqttWireMessage mqttWireMessage = (MqttWireMessage) this.outboundQoS2.get(objNextElement);
            if (mqttWireMessage instanceof MqttPublish) {
                this.log.fine(CLASS_NAME, "restoreInflightMessages", "610", new Object[]{objNextElement});
                mqttWireMessage.setDuplicate(true);
                insertInOrder(this.pendingMessages, (MqttPublish) mqttWireMessage);
            } else if (mqttWireMessage instanceof MqttPubRel) {
                this.log.fine(CLASS_NAME, "restoreInflightMessages", "611", new Object[]{objNextElement});
                insertInOrder(this.pendingFlows, (MqttPubRel) mqttWireMessage);
            }
        }
        Enumeration enumerationKeys2 = this.outboundQoS1.keys();
        while (enumerationKeys2.hasMoreElements()) {
            Object objNextElement2 = enumerationKeys2.nextElement();
            MqttPublish mqttPublish = (MqttPublish) this.outboundQoS1.get(objNextElement2);
            mqttPublish.setDuplicate(true);
            this.log.fine(CLASS_NAME, "restoreInflightMessages", "612", new Object[]{objNextElement2});
            insertInOrder(this.pendingMessages, mqttPublish);
        }
        Enumeration enumerationKeys3 = this.outboundQoS0.keys();
        while (enumerationKeys3.hasMoreElements()) {
            Object objNextElement3 = enumerationKeys3.nextElement();
            MqttPublish mqttPublish2 = (MqttPublish) this.outboundQoS0.get(objNextElement3);
            this.log.fine(CLASS_NAME, "restoreInflightMessages", "512", new Object[]{objNextElement3});
            insertInOrder(this.pendingMessages, mqttPublish2);
        }
        this.pendingFlows = reOrder(this.pendingFlows);
        this.pendingMessages = reOrder(this.pendingMessages);
    }

    public void send(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttWireMessage.isMessageIdRequired() && mqttWireMessage.getMessageId() == 0 && (((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() != 0) || (mqttWireMessage instanceof MqttPubAck) || (mqttWireMessage instanceof MqttPubRec) || (mqttWireMessage instanceof MqttPubRel) || (mqttWireMessage instanceof MqttPubComp) || (mqttWireMessage instanceof MqttSubscribe) || (mqttWireMessage instanceof MqttSuback) || (mqttWireMessage instanceof MqttUnsubscribe) || (mqttWireMessage instanceof MqttUnsubAck))) {
            mqttWireMessage.setMessageId(getNextMessageId());
        }
        if (mqttToken != null) {
            mqttWireMessage.setToken(mqttToken);
            try {
                mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
            } catch (Exception unused) {
            }
        }
        if (mqttWireMessage instanceof MqttPublish) {
            synchronized (this.queueLock) {
                int i = this.actualInFlight;
                if (i >= this.maxInflight) {
                    this.log.fine(CLASS_NAME, "send", "613", new Object[]{Integer.valueOf(i)});
                    throw new MqttException(32202);
                }
                MqttMessage message = ((MqttPublish) mqttWireMessage).getMessage();
                this.log.fine(CLASS_NAME, "send", "628", new Object[]{Integer.valueOf(mqttWireMessage.getMessageId()), Integer.valueOf(message.getQos()), mqttWireMessage});
                int qos = message.getQos();
                if (qos == 1) {
                    this.outboundQoS1.put(Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage);
                    this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                    this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                } else if (qos == 2) {
                    this.outboundQoS2.put(Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage);
                    this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                    this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                }
                this.pendingMessages.addElement(mqttWireMessage);
                this.queueLock.notifyAll();
            }
            return;
        }
        this.log.fine(CLASS_NAME, "send", "615", new Object[]{Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (mqttWireMessage instanceof MqttConnect) {
            synchronized (this.queueLock) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                this.pendingFlows.insertElementAt(mqttWireMessage, 0);
                this.queueLock.notifyAll();
            }
            return;
        }
        if (mqttWireMessage instanceof MqttPingReq) {
            this.pingCommand = mqttWireMessage;
        } else if (mqttWireMessage instanceof MqttPubRel) {
            this.outboundQoS2.put(Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage);
            this.persistence.put(getSendConfirmPersistenceKey(mqttWireMessage), (MqttPubRel) mqttWireMessage);
        } else if (mqttWireMessage instanceof MqttPubComp) {
            this.persistence.remove(getReceivedPersistenceKey(mqttWireMessage));
        }
        synchronized (this.queueLock) {
            if (!(mqttWireMessage instanceof MqttAck)) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
            }
            this.pendingFlows.addElement(mqttWireMessage);
            this.queueLock.notifyAll();
        }
    }

    public void persistBufferedMessage(MqttWireMessage mqttWireMessage) throws MqttException {
        String sendBufferedPersistenceKey = getSendBufferedPersistenceKey(mqttWireMessage);
        try {
            mqttWireMessage.setMessageId(getNextMessageId());
            String sendBufferedPersistenceKey2 = getSendBufferedPersistenceKey(mqttWireMessage);
            try {
                this.persistence.put(sendBufferedPersistenceKey2, (MqttPublish) mqttWireMessage);
            } catch (MqttPersistenceException unused) {
                this.log.fine(CLASS_NAME, "persistBufferedMessage", "515");
                this.persistence.open(this.clientComms.getClient().getClientId(), this.clientComms.getClient().getServerURI());
                this.persistence.put(sendBufferedPersistenceKey2, (MqttPublish) mqttWireMessage);
            }
            this.log.fine(CLASS_NAME, "persistBufferedMessage", "513", new Object[]{sendBufferedPersistenceKey2});
        } catch (MqttException e) {
            this.log.warning(CLASS_NAME, "persistBufferedMessage", "514", new Object[]{sendBufferedPersistenceKey});
            throw e;
        }
    }

    public void unPersistBufferedMessage(MqttWireMessage mqttWireMessage) {
        try {
            this.log.fine(CLASS_NAME, "unPersistBufferedMessage", "517", new Object[]{mqttWireMessage.getKey()});
            this.persistence.remove(getSendBufferedPersistenceKey(mqttWireMessage));
        } catch (MqttPersistenceException unused) {
            this.log.fine(CLASS_NAME, "unPersistBufferedMessage", "518", new Object[]{mqttWireMessage.getKey()});
        }
    }

    protected void undo(MqttPublish mqttPublish) throws MqttPersistenceException {
        synchronized (this.queueLock) {
            this.log.fine(CLASS_NAME, "undo", "618", new Object[]{Integer.valueOf(mqttPublish.getMessageId()), Integer.valueOf(mqttPublish.getMessage().getQos())});
            if (mqttPublish.getMessage().getQos() == 1) {
                this.outboundQoS1.remove(Integer.valueOf(mqttPublish.getMessageId()));
            } else {
                this.outboundQoS2.remove(Integer.valueOf(mqttPublish.getMessageId()));
            }
            this.pendingMessages.removeElement(mqttPublish);
            this.persistence.remove(getSendPersistenceKey(mqttPublish));
            this.tokenStore.removeToken(mqttPublish);
            if (mqttPublish.getMessage().getQos() > 0) {
                releaseMessageId(mqttPublish.getMessageId());
                mqttPublish.setMessageId(0);
            }
            checkQuiesceLock();
        }
    }

    protected boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) throws MqttException {
        boolean z;
        MqttMessage message = iMqttDeliveryToken.getMessage();
        int messageId = iMqttDeliveryToken.getMessageId();
        synchronized (this.queueLock) {
            z = true;
            boolean z2 = message.getQos() == 1 && this.outboundQoS1.remove(Integer.valueOf(messageId)) != null;
            if (message.getQos() == 2 && this.outboundQoS2.remove(Integer.valueOf(messageId)) != null) {
                z2 = true;
            }
            if (!this.pendingMessages.removeElement(message)) {
                z = z2;
            }
            this.persistence.remove(getSendPersistenceKey(messageId));
            this.tokenStore.removeToken(Integer.toString(messageId));
            releaseMessageId(messageId);
            decrementInFlight();
        }
        return z;
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) throws MqttException {
        long jMax;
        MqttToken mqttToken;
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "checkForActivity", "616", new Object[0]);
        synchronized (this.quiesceLock) {
            if (this.quiescing) {
                return null;
            }
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            timeUnit.toMillis(this.keepAliveNanos);
            if (!this.connected || this.keepAliveNanos <= 0) {
                return null;
            }
            long jNanoTime = this.highResolutionTimer.nanoTime();
            synchronized (this.pingOutstandingLock) {
                int i = this.pingOutstanding;
                if (i > 0) {
                    long j = jNanoTime - this.lastInboundActivity;
                    long j2 = this.keepAliveNanos;
                    if (j >= ((long) 100000) + j2) {
                        this.log.severe(str, "checkForActivity", "619", new Object[]{Long.valueOf(j2), Long.valueOf(this.lastOutboundActivity), Long.valueOf(this.lastInboundActivity), Long.valueOf(jNanoTime), Long.valueOf(this.lastPing)});
                        throw ExceptionHelper.createMqttException(32000);
                    }
                }
                if (i == 0) {
                    long j3 = jNanoTime - this.lastOutboundActivity;
                    long j4 = this.keepAliveNanos;
                    if (j3 >= 2 * j4) {
                        this.log.severe(str, "checkForActivity", "642", new Object[]{Long.valueOf(j4), Long.valueOf(this.lastOutboundActivity), Long.valueOf(this.lastInboundActivity), Long.valueOf(jNanoTime), Long.valueOf(this.lastPing)});
                        throw ExceptionHelper.createMqttException(32002);
                    }
                }
                if ((i == 0 && jNanoTime - this.lastInboundActivity >= this.keepAliveNanos - ((long) 100000)) || jNanoTime - this.lastOutboundActivity >= this.keepAliveNanos - ((long) 100000)) {
                    this.log.fine(str, "checkForActivity", "620", new Object[]{Long.valueOf(this.keepAliveNanos), Long.valueOf(this.lastOutboundActivity), Long.valueOf(this.lastInboundActivity)});
                    mqttToken = new MqttToken(this.clientComms.getClient().getClientId());
                    if (iMqttActionListener != null) {
                        mqttToken.setActionCallback(iMqttActionListener);
                    }
                    this.tokenStore.saveToken(mqttToken, this.pingCommand);
                    this.pendingFlows.insertElementAt(this.pingCommand, 0);
                    jMax = getKeepAlive();
                    notifyQueueLock();
                } else {
                    this.log.fine(str, "checkForActivity", "634", null);
                    jMax = Math.max(1L, getKeepAlive() - timeUnit.toMillis(jNanoTime - this.lastOutboundActivity));
                    mqttToken = null;
                }
            }
            this.log.fine(str, "checkForActivity", "624", new Object[]{Long.valueOf(jMax)});
            this.pingSender.schedule(jMax);
            return mqttToken;
        }
    }

    protected MqttWireMessage get() throws MqttException {
        synchronized (this.queueLock) {
            MqttWireMessage mqttWireMessage = null;
            while (mqttWireMessage == null) {
                if ((this.pendingMessages.isEmpty() && this.pendingFlows.isEmpty()) || (this.pendingFlows.isEmpty() && this.actualInFlight >= this.maxInflight)) {
                    try {
                        Logger logger = this.log;
                        String str = CLASS_NAME;
                        logger.fine(str, "get", "644");
                        this.queueLock.wait();
                        this.log.fine(str, "get", "647");
                    } catch (InterruptedException unused) {
                    }
                }
                if (this.pendingFlows != null && (this.connected || (!this.pendingFlows.isEmpty() && (((MqttWireMessage) this.pendingFlows.elementAt(0)) instanceof MqttConnect)))) {
                    if (!this.pendingFlows.isEmpty()) {
                        mqttWireMessage = (MqttWireMessage) this.pendingFlows.remove(0);
                        if (mqttWireMessage instanceof MqttPubRel) {
                            int i = this.inFlightPubRels + 1;
                            this.inFlightPubRels = i;
                            this.log.fine(CLASS_NAME, "get", "617", new Object[]{Integer.valueOf(i)});
                        }
                        checkQuiesceLock();
                    } else if (!this.pendingMessages.isEmpty()) {
                        if (this.actualInFlight < this.maxInflight) {
                            mqttWireMessage = (MqttWireMessage) this.pendingMessages.elementAt(0);
                            this.pendingMessages.removeElementAt(0);
                            int i2 = this.actualInFlight + 1;
                            this.actualInFlight = i2;
                            this.log.fine(CLASS_NAME, "get", "623", new Object[]{Integer.valueOf(i2)});
                        } else {
                            this.log.fine(CLASS_NAME, "get", "622");
                        }
                    }
                }
                this.log.fine(CLASS_NAME, "get", "621");
                return null;
            }
            return mqttWireMessage;
        }
    }

    public void setKeepAliveInterval(long j) {
        this.keepAliveNanos = TimeUnit.MILLISECONDS.toNanos(j);
    }

    public void notifySentBytes(int i) {
        if (i > 0) {
            this.lastOutboundActivity = this.highResolutionTimer.nanoTime();
        }
        this.log.fine(CLASS_NAME, "notifySentBytes", "643", new Object[]{Integer.valueOf(i)});
    }

    protected void notifySent(MqttWireMessage mqttWireMessage) {
        int i;
        this.lastOutboundActivity = this.highResolutionTimer.nanoTime();
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "notifySent", "625", new Object[]{mqttWireMessage.getKey()});
        MqttToken token = mqttWireMessage.getToken();
        if (token == null && (token = this.tokenStore.getToken(mqttWireMessage)) == null) {
            return;
        }
        token.internalTok.notifySent();
        if (mqttWireMessage instanceof MqttPingReq) {
            synchronized (this.pingOutstandingLock) {
                long jNanoTime = this.highResolutionTimer.nanoTime();
                synchronized (this.pingOutstandingLock) {
                    this.lastPing = jNanoTime;
                    i = this.pingOutstanding + 1;
                    this.pingOutstanding = i;
                }
                this.log.fine(str, "notifySent", "635", new Object[]{Integer.valueOf(i)});
            }
            return;
        }
        if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() == 0) {
            token.internalTok.markComplete(null, null);
            this.callback.asyncOperationComplete(token);
            decrementInFlight();
            releaseMessageId(mqttWireMessage.getMessageId());
            this.tokenStore.removeToken(mqttWireMessage);
            checkQuiesceLock();
        }
    }

    private void decrementInFlight() {
        synchronized (this.queueLock) {
            int i = this.actualInFlight - 1;
            this.actualInFlight = i;
            this.log.fine(CLASS_NAME, "decrementInFlight", "646", new Object[]{Integer.valueOf(i)});
            if (!checkQuiesceLock()) {
                this.queueLock.notifyAll();
            }
        }
    }

    protected boolean checkQuiesceLock() {
        int iCount = this.tokenStore.count();
        if (!this.quiescing || iCount != 0 || this.pendingFlows.size() != 0 || !this.callback.isQuiesced()) {
            return false;
        }
        this.log.fine(CLASS_NAME, "checkQuiesceLock", "626", new Object[]{Boolean.valueOf(this.quiescing), Integer.valueOf(this.actualInFlight), Integer.valueOf(this.pendingFlows.size()), Integer.valueOf(this.inFlightPubRels), Boolean.valueOf(this.callback.isQuiesced()), Integer.valueOf(iCount)});
        synchronized (this.quiesceLock) {
            this.quiesceLock.notifyAll();
        }
        return true;
    }

    public void notifyReceivedBytes(int i) {
        if (i > 0) {
            this.lastInboundActivity = this.highResolutionTimer.nanoTime();
        }
        this.log.fine(CLASS_NAME, "notifyReceivedBytes", "630", new Object[]{Integer.valueOf(i)});
    }

    protected void notifyReceivedAck(MqttAck mqttAck) throws MqttException {
        this.lastInboundActivity = this.highResolutionTimer.nanoTime();
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "notifyReceivedAck", "627", new Object[]{Integer.valueOf(mqttAck.getMessageId()), mqttAck});
        MqttToken token = this.tokenStore.getToken(mqttAck);
        if (token == null) {
            this.log.fine(str, "notifyReceivedAck", "662", new Object[]{Integer.valueOf(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubRec) {
            send(new MqttPubRel((MqttPubRec) mqttAck), token);
        } else if ((mqttAck instanceof MqttPubAck) || (mqttAck instanceof MqttPubComp)) {
            notifyResult(mqttAck, token, null);
        } else if (mqttAck instanceof MqttPingResp) {
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = Math.max(0, this.pingOutstanding - 1);
                notifyResult(mqttAck, token, null);
                if (this.pingOutstanding == 0) {
                    this.tokenStore.removeToken(mqttAck);
                }
            }
            this.log.fine(str, "notifyReceivedAck", "636", new Object[]{Integer.valueOf(this.pingOutstanding)});
        } else if (mqttAck instanceof MqttConnack) {
            MqttConnack mqttConnack = (MqttConnack) mqttAck;
            int returnCode = mqttConnack.getReturnCode();
            if (returnCode == 0) {
                synchronized (this.queueLock) {
                    if (this.cleanSession) {
                        clearState();
                        this.tokenStore.saveToken(token, mqttAck);
                    }
                    this.inFlightPubRels = 0;
                    this.actualInFlight = 0;
                    restoreInflightMessages();
                    connected();
                }
                this.clientComms.connectComplete(mqttConnack, null);
                notifyResult(mqttAck, token, null);
                this.tokenStore.removeToken(mqttAck);
                synchronized (this.queueLock) {
                    this.queueLock.notifyAll();
                }
            } else {
                throw ExceptionHelper.createMqttException(returnCode);
            }
        } else {
            notifyResult(mqttAck, token, null);
            releaseMessageId(mqttAck.getMessageId());
            this.tokenStore.removeToken(mqttAck);
        }
        checkQuiesceLock();
    }

    protected void notifyReceivedMsg(MqttWireMessage mqttWireMessage) throws MqttException {
        this.lastInboundActivity = this.highResolutionTimer.nanoTime();
        this.log.fine(CLASS_NAME, "notifyReceivedMsg", "651", new Object[]{Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (this.quiescing) {
            return;
        }
        if (mqttWireMessage instanceof MqttPublish) {
            MqttPublish mqttPublish = (MqttPublish) mqttWireMessage;
            int qos = mqttPublish.getMessage().getQos();
            if (qos == 0 || qos == 1) {
                CommsCallback commsCallback = this.callback;
                if (commsCallback != null) {
                    commsCallback.messageArrived(mqttPublish);
                    return;
                }
                return;
            }
            if (qos != 2) {
                return;
            }
            this.persistence.put(getReceivedPersistenceKey(mqttWireMessage), mqttPublish);
            this.inboundQoS2.put(Integer.valueOf(mqttPublish.getMessageId()), mqttPublish);
            send(new MqttPubRec(mqttPublish), null);
            return;
        }
        if (mqttWireMessage instanceof MqttPubRel) {
            MqttPublish mqttPublish2 = (MqttPublish) this.inboundQoS2.get(Integer.valueOf(mqttWireMessage.getMessageId()));
            if (mqttPublish2 != null) {
                CommsCallback commsCallback2 = this.callback;
                if (commsCallback2 != null) {
                    commsCallback2.messageArrived(mqttPublish2);
                    return;
                }
                return;
            }
            send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
        }
    }

    protected void notifyComplete(MqttToken mqttToken) throws MqttException {
        MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage == null || !(wireMessage instanceof MqttAck)) {
            return;
        }
        Logger logger = this.log;
        String str = CLASS_NAME;
        logger.fine(str, "notifyComplete", "629", new Object[]{Integer.valueOf(wireMessage.getMessageId()), mqttToken, wireMessage});
        MqttAck mqttAck = (MqttAck) wireMessage;
        if (mqttAck instanceof MqttPubAck) {
            this.persistence.remove(getSendPersistenceKey(wireMessage));
            this.persistence.remove(getSendBufferedPersistenceKey(wireMessage));
            this.outboundQoS1.remove(Integer.valueOf(mqttAck.getMessageId()));
            decrementInFlight();
            releaseMessageId(wireMessage.getMessageId());
            this.tokenStore.removeToken(wireMessage);
            this.log.fine(str, "notifyComplete", "650", new Object[]{Integer.valueOf(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubComp) {
            this.persistence.remove(getSendPersistenceKey(wireMessage));
            this.persistence.remove(getSendConfirmPersistenceKey(wireMessage));
            this.persistence.remove(getSendBufferedPersistenceKey(wireMessage));
            this.outboundQoS2.remove(Integer.valueOf(mqttAck.getMessageId()));
            this.inFlightPubRels--;
            decrementInFlight();
            releaseMessageId(wireMessage.getMessageId());
            this.tokenStore.removeToken(wireMessage);
            this.log.fine(str, "notifyComplete", "645", new Object[]{Integer.valueOf(mqttAck.getMessageId()), Integer.valueOf(this.inFlightPubRels)});
        }
        checkQuiesceLock();
    }

    protected void notifyResult(MqttWireMessage mqttWireMessage, MqttToken mqttToken, MqttException mqttException) {
        mqttToken.internalTok.markComplete(mqttWireMessage, mqttException);
        mqttToken.internalTok.notifyComplete();
        if (mqttWireMessage != null && (mqttWireMessage instanceof MqttAck) && !(mqttWireMessage instanceof MqttPubRec)) {
            this.log.fine(CLASS_NAME, "notifyResult", "648", new Object[]{mqttToken.internalTok.getKey(), mqttWireMessage, mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            this.log.fine(CLASS_NAME, "notifyResult", "649", new Object[]{mqttToken.internalTok.getKey(), mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
    }

    public void connected() {
        this.log.fine(CLASS_NAME, "connected", "631");
        this.connected = true;
        this.pingSender.start();
    }

    public Vector resolveOldTokens(MqttException mqttException) {
        this.log.fine(CLASS_NAME, "resolveOldTokens", "632", new Object[]{mqttException});
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector outstandingTokens = this.tokenStore.getOutstandingTokens();
        Enumeration enumerationElements = outstandingTokens.elements();
        while (enumerationElements.hasMoreElements()) {
            MqttToken mqttToken = (MqttToken) enumerationElements.nextElement();
            synchronized (mqttToken) {
                if (!mqttToken.isComplete() && !mqttToken.internalTok.isCompletePending() && mqttToken.getException() == null) {
                    mqttToken.internalTok.setException(mqttException);
                }
            }
            if (!(mqttToken instanceof MqttDeliveryToken)) {
                this.tokenStore.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }

    public void disconnected(MqttException mqttException) {
        this.log.fine(CLASS_NAME, "disconnected", "633", new Object[]{mqttException});
        this.connected = false;
        try {
            if (this.cleanSession) {
                clearState();
            }
            this.pendingMessages.clear();
            this.pendingFlows.clear();
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = 0;
            }
        } catch (MqttException unused) {
        }
    }

    private synchronized void releaseMessageId(int i) {
        this.inUseMsgIds.remove(Integer.valueOf(i));
    }

    private synchronized int getNextMessageId() throws MqttException {
        int i;
        int i2 = this.nextMsgId;
        int i3 = 0;
        do {
            int i4 = this.nextMsgId + 1;
            this.nextMsgId = i4;
            if (i4 > 65535) {
                this.nextMsgId = 1;
            }
            i = this.nextMsgId;
            if (i == i2 && (i3 = i3 + 1) == 2) {
                throw ExceptionHelper.createMqttException(32001);
            }
        } while (this.inUseMsgIds.containsKey(Integer.valueOf(i)));
        Integer numValueOf = Integer.valueOf(this.nextMsgId);
        this.inUseMsgIds.put(numValueOf, numValueOf);
        return this.nextMsgId;
    }

    public void quiesce(long j) {
        if (j > 0) {
            Logger logger = this.log;
            String str = CLASS_NAME;
            logger.fine(str, "quiesce", "637", new Object[]{Long.valueOf(j)});
            synchronized (this.queueLock) {
                this.quiescing = true;
            }
            this.callback.quiesce();
            notifyQueueLock();
            synchronized (this.quiesceLock) {
                try {
                    int iCount = this.tokenStore.count();
                    if (iCount > 0 || this.pendingFlows.size() > 0 || !this.callback.isQuiesced()) {
                        this.log.fine(str, "quiesce", "639", new Object[]{Integer.valueOf(this.actualInFlight), Integer.valueOf(this.pendingFlows.size()), Integer.valueOf(this.inFlightPubRels), Integer.valueOf(iCount)});
                        this.quiesceLock.wait(j);
                    }
                } catch (InterruptedException unused) {
                }
            }
            synchronized (this.queueLock) {
                this.pendingMessages.clear();
                this.pendingFlows.clear();
                this.quiescing = false;
                this.actualInFlight = 0;
            }
            this.log.fine(CLASS_NAME, "quiesce", "640");
        }
    }

    public void notifyQueueLock() {
        synchronized (this.queueLock) {
            this.log.fine(CLASS_NAME, "notifyQueueLock", "638");
            this.queueLock.notifyAll();
        }
    }

    protected void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{Integer.valueOf(mqttPublish.getMessageId())});
        this.persistence.remove(getReceivedPersistenceKey(mqttPublish));
        this.inboundQoS2.remove(Integer.valueOf(mqttPublish.getMessageId()));
    }

    protected void deliveryComplete(int i) throws MqttPersistenceException {
        this.log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{Integer.valueOf(i)});
        this.persistence.remove(getReceivedPersistenceKey(i));
        this.inboundQoS2.remove(Integer.valueOf(i));
    }

    public int getActualInFlight() {
        return this.actualInFlight;
    }

    public int getMaxInFlight() {
        return this.maxInflight;
    }

    protected void close() {
        this.inUseMsgIds.clear();
        if (this.pendingMessages != null) {
            this.pendingMessages.clear();
        }
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
        this.inUseMsgIds = null;
        this.pendingMessages = null;
        this.pendingFlows = null;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.tokenStore = null;
        this.callback = null;
        this.clientComms = null;
        this.persistence = null;
        this.pingCommand = null;
        this.highResolutionTimer = null;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("In use msgids", this.inUseMsgIds);
        properties.put("pendingMessages", this.pendingMessages);
        properties.put("pendingFlows", this.pendingFlows);
        properties.put("maxInflight", Integer.valueOf(this.maxInflight));
        properties.put("nextMsgID", Integer.valueOf(this.nextMsgId));
        properties.put("actualInFlight", Integer.valueOf(this.actualInFlight));
        properties.put("inFlightPubRels", Integer.valueOf(this.inFlightPubRels));
        properties.put("quiescing", Boolean.valueOf(this.quiescing));
        properties.put("pingoutstanding", Integer.valueOf(this.pingOutstanding));
        properties.put("lastOutboundActivity", Long.valueOf(this.lastOutboundActivity));
        properties.put("lastInboundActivity", Long.valueOf(this.lastInboundActivity));
        properties.put("outboundQoS2", this.outboundQoS2);
        properties.put("outboundQoS1", this.outboundQoS1);
        properties.put("outboundQoS0", this.outboundQoS0);
        properties.put("inboundQoS2", this.inboundQoS2);
        properties.put("tokens", this.tokenStore);
        return properties;
    }
}
