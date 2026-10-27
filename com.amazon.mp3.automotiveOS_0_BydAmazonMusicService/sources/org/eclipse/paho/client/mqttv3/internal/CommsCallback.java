package org.eclipse.paho.client.mqttv3.internal;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class CommsCallback implements Runnable {
    private static final String CLASS_NAME = CommsCallback.class.getName();
    private static final int INBOUND_QUEUE_SIZE = 10;
    private Future<?> callbackFuture;
    private Thread callbackThread;
    private Hashtable<String, IMqttMessageListener> callbacks;
    private ClientComms clientComms;
    private ClientState clientState;
    private final Vector<MqttToken> completeQueue;
    private State current_state;
    private final Object lifecycle;
    private final Logger log;
    private boolean manualAcks;
    private final Vector<MqttWireMessage> messageQueue;
    private MqttCallback mqttCallback;
    private MqttCallbackExtended reconnectInternalCallback;
    private final Object spaceAvailable;
    private State target_state;
    private String threadName;
    private final Object workAvailable;

    private enum State {
        STOPPED,
        RUNNING,
        QUIESCING;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static State[] valuesCustom() {
            State[] stateArrValuesCustom = values();
            int length = stateArrValuesCustom.length;
            State[] stateArr = new State[length];
            System.arraycopy(stateArrValuesCustom, 0, stateArr, 0, length);
            return stateArr;
        }
    }

    CommsCallback(ClientComms clientComms) {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        this.log = logger;
        State state = State.STOPPED;
        this.current_state = state;
        this.target_state = state;
        this.lifecycle = new Object();
        this.workAvailable = new Object();
        this.spaceAvailable = new Object();
        this.manualAcks = false;
        this.clientComms = clientComms;
        this.messageQueue = new Vector<>(10);
        this.completeQueue = new Vector<>(10);
        this.callbacks = new Hashtable<>();
        logger.setResourceName(clientComms.getClient().getClientId());
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public void start(String str, ExecutorService executorService) {
        this.threadName = str;
        synchronized (this.lifecycle) {
            if (this.current_state == State.STOPPED) {
                this.messageQueue.clear();
                this.completeQueue.clear();
                this.target_state = State.RUNNING;
                if (executorService == null) {
                    new Thread(this).start();
                } else {
                    this.callbackFuture = executorService.submit(this);
                }
            }
        }
        while (!isRunning()) {
            try {
                Thread.sleep(100L);
            } catch (Exception unused) {
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            Future<?> future = this.callbackFuture;
            if (future != null) {
                future.cancel(true);
            }
        }
        if (isRunning()) {
            Logger logger = this.log;
            String str = CLASS_NAME;
            logger.fine(str, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "700");
            synchronized (this.lifecycle) {
                this.target_state = State.STOPPED;
            }
            if (!Thread.currentThread().equals(this.callbackThread)) {
                synchronized (this.workAvailable) {
                    this.log.fine(str, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "701");
                    this.workAvailable.notifyAll();
                }
                while (isRunning()) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception unused) {
                    }
                    this.clientState.notifyQueueLock();
                }
            }
            this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "703");
        }
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.reconnectInternalCallback = mqttCallbackExtended;
    }

    public void setManualAcks(boolean z) {
        this.manualAcks = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        MqttToken mqttTokenElementAt;
        MqttPublish mqttPublish;
        Thread threadCurrentThread = Thread.currentThread();
        this.callbackThread = threadCurrentThread;
        threadCurrentThread.setName(this.threadName);
        synchronized (this.lifecycle) {
            this.current_state = State.RUNNING;
        }
        while (isRunning()) {
            try {
                try {
                    synchronized (this.workAvailable) {
                        if (isRunning() && this.messageQueue.isEmpty() && this.completeQueue.isEmpty()) {
                            this.log.fine(CLASS_NAME, "run", "704");
                            this.workAvailable.wait();
                        }
                    }
                } catch (InterruptedException unused) {
                }
                if (isRunning()) {
                    synchronized (this.completeQueue) {
                        if (this.completeQueue.isEmpty()) {
                            mqttTokenElementAt = null;
                        } else {
                            mqttTokenElementAt = this.completeQueue.elementAt(0);
                            this.completeQueue.removeElementAt(0);
                        }
                    }
                    if (mqttTokenElementAt != null) {
                        handleActionComplete(mqttTokenElementAt);
                    }
                    synchronized (this.messageQueue) {
                        if (this.messageQueue.isEmpty()) {
                            mqttPublish = null;
                        } else {
                            mqttPublish = (MqttPublish) this.messageQueue.elementAt(0);
                            this.messageQueue.removeElementAt(0);
                        }
                    }
                    if (mqttPublish != null) {
                        handleMessage(mqttPublish);
                    }
                }
                if (isQuiescing()) {
                    this.clientState.checkQuiesceLock();
                }
                synchronized (this.spaceAvailable) {
                    this.log.fine(CLASS_NAME, "run", "706");
                    this.spaceAvailable.notifyAll();
                }
            } catch (Throwable th) {
                try {
                    Logger logger = this.log;
                    String str = CLASS_NAME;
                    logger.fine(str, "run", "714", null, th);
                    this.clientComms.shutdownConnection(null, new MqttException(th));
                    synchronized (this.spaceAvailable) {
                        this.log.fine(str, "run", "706");
                        this.spaceAvailable.notifyAll();
                    }
                } catch (Throwable th2) {
                    synchronized (this.spaceAvailable) {
                        this.log.fine(CLASS_NAME, "run", "706");
                        this.spaceAvailable.notifyAll();
                        throw th2;
                    }
                }
            }
        }
        synchronized (this.lifecycle) {
            this.current_state = State.STOPPED;
        }
        this.callbackThread = null;
    }

    private void handleActionComplete(MqttToken mqttToken) throws MqttException {
        synchronized (mqttToken) {
            this.log.fine(CLASS_NAME, "handleActionComplete", "705", new Object[]{mqttToken.internalTok.getKey()});
            if (mqttToken.isComplete()) {
                this.clientState.notifyComplete(mqttToken);
            }
            mqttToken.internalTok.notifyComplete();
            if (!mqttToken.internalTok.isNotified()) {
                if (this.mqttCallback != null && (mqttToken instanceof MqttDeliveryToken) && mqttToken.isComplete()) {
                    this.mqttCallback.deliveryComplete((MqttDeliveryToken) mqttToken);
                }
                fireActionEvent(mqttToken);
            }
            if (mqttToken.isComplete() && (mqttToken instanceof MqttDeliveryToken)) {
                mqttToken.internalTok.setNotified(true);
            }
        }
    }

    public void connectionLost(MqttException mqttException) {
        try {
            if (this.mqttCallback != null && mqttException != null) {
                this.log.fine(CLASS_NAME, "connectionLost", "708", new Object[]{mqttException});
                this.mqttCallback.connectionLost(mqttException);
            }
            MqttCallbackExtended mqttCallbackExtended = this.reconnectInternalCallback;
            if (mqttCallbackExtended == null || mqttException == null) {
                return;
            }
            mqttCallbackExtended.connectionLost(mqttException);
        } catch (Throwable th) {
            this.log.fine(CLASS_NAME, "connectionLost", "720", new Object[]{th});
        }
    }

    public void fireActionEvent(MqttToken mqttToken) {
        IMqttActionListener actionCallback;
        if (mqttToken == null || (actionCallback = mqttToken.getActionCallback()) == null) {
            return;
        }
        if (mqttToken.getException() == null) {
            this.log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
            actionCallback.onSuccess(mqttToken);
        } else {
            this.log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
            actionCallback.onFailure(mqttToken, mqttToken.getException());
        }
    }

    public void messageArrived(MqttPublish mqttPublish) {
        if (this.mqttCallback != null || this.callbacks.size() > 0) {
            synchronized (this.spaceAvailable) {
                while (isRunning() && !isQuiescing() && this.messageQueue.size() >= 10) {
                    try {
                        this.log.fine(CLASS_NAME, "messageArrived", "709");
                        this.spaceAvailable.wait(200L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            if (isQuiescing()) {
                return;
            }
            this.messageQueue.addElement(mqttPublish);
            synchronized (this.workAvailable) {
                this.log.fine(CLASS_NAME, "messageArrived", "710");
                this.workAvailable.notifyAll();
            }
        }
    }

    public void quiesce() {
        synchronized (this.lifecycle) {
            if (this.current_state == State.RUNNING) {
                this.current_state = State.QUIESCING;
            }
        }
        synchronized (this.spaceAvailable) {
            this.log.fine(CLASS_NAME, "quiesce", "711");
            this.spaceAvailable.notifyAll();
        }
    }

    public boolean isQuiesced() {
        return isQuiescing() && this.completeQueue.size() == 0 && this.messageQueue.size() == 0;
    }

    private void handleMessage(MqttPublish mqttPublish) throws Exception {
        String topicName = mqttPublish.getTopicName();
        this.log.fine(CLASS_NAME, "handleMessage", "713", new Object[]{Integer.valueOf(mqttPublish.getMessageId()), topicName});
        deliverMessage(topicName, mqttPublish.getMessageId(), mqttPublish.getMessage());
        if (this.manualAcks) {
            return;
        }
        if (mqttPublish.getMessage().getQos() == 1) {
            this.clientComms.internalSend(new MqttPubAck(mqttPublish), new MqttToken(this.clientComms.getClient().getClientId()));
        } else if (mqttPublish.getMessage().getQos() == 2) {
            this.clientComms.deliveryComplete(mqttPublish);
            MqttPubComp mqttPubComp = new MqttPubComp(mqttPublish);
            ClientComms clientComms = this.clientComms;
            clientComms.internalSend(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
        }
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        if (i2 == 1) {
            this.clientComms.internalSend(new MqttPubAck(i), new MqttToken(this.clientComms.getClient().getClientId()));
        } else if (i2 == 2) {
            this.clientComms.deliveryComplete(i);
            MqttPubComp mqttPubComp = new MqttPubComp(i);
            ClientComms clientComms = this.clientComms;
            clientComms.internalSend(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
        }
    }

    public void asyncOperationComplete(MqttToken mqttToken) {
        if (isRunning()) {
            this.completeQueue.addElement(mqttToken);
            synchronized (this.workAvailable) {
                this.log.fine(CLASS_NAME, "asyncOperationComplete", "715", new Object[]{mqttToken.internalTok.getKey()});
                this.workAvailable.notifyAll();
            }
            return;
        }
        try {
            handleActionComplete(mqttToken);
        } catch (Throwable th) {
            this.log.fine(CLASS_NAME, "asyncOperationComplete", "719", null, th);
            this.clientComms.shutdownConnection(null, new MqttException(th));
        }
    }

    protected Thread getThread() {
        return this.callbackThread;
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.callbacks.put(str, iMqttMessageListener);
    }

    public void removeMessageListener(String str) {
        this.callbacks.remove(str);
    }

    public void removeMessageListeners() {
        this.callbacks.clear();
    }

    protected boolean deliverMessage(String str, int i, MqttMessage mqttMessage) throws Exception {
        Enumeration<String> enumerationKeys = this.callbacks.keys();
        boolean z = false;
        while (enumerationKeys.hasMoreElements()) {
            String strNextElement = enumerationKeys.nextElement();
            IMqttMessageListener iMqttMessageListener = this.callbacks.get(strNextElement);
            if (iMqttMessageListener != null && MqttTopic.isMatched(strNextElement, str)) {
                mqttMessage.setId(i);
                iMqttMessageListener.messageArrived(str, mqttMessage);
                z = true;
            }
        }
        if (this.mqttCallback == null || z) {
            return z;
        }
        mqttMessage.setId(i);
        this.mqttCallback.messageArrived(str, mqttMessage);
        return true;
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.lifecycle) {
            State state = this.current_state;
            State state2 = State.RUNNING;
            z = (state == state2 || state == State.QUIESCING) && this.target_state == state2;
        }
        return z;
    }

    public boolean isQuiescing() {
        boolean z;
        synchronized (this.lifecycle) {
            z = this.current_state == State.QUIESCING;
        }
        return z;
    }
}
