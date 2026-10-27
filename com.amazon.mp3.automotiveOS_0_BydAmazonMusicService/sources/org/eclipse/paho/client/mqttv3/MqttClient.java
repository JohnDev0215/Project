package org.eclipse.paho.client.mqttv3;

import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* JADX INFO: loaded from: classes6.dex */
public class MqttClient implements IMqttClient {
    protected MqttAsyncClient aClient;
    protected long timeToWait;

    public MqttClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(str, str2, mqttClientPersistence);
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence, ScheduledExecutorService scheduledExecutorService) throws MqttException {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(str, str2, mqttClientPersistence, new ScheduledExecutorPingSender(scheduledExecutorService), scheduledExecutorService);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void connect() throws MqttException {
        connect(new MqttConnectOptions());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void connect(MqttConnectOptions mqttConnectOptions) throws MqttException {
        this.aClient.connect(mqttConnectOptions, null, null).waitForCompletion(getTimeToWait());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken connectWithResult(MqttConnectOptions mqttConnectOptions) throws MqttException {
        IMqttToken iMqttTokenConnect = this.aClient.connect(mqttConnectOptions, null, null);
        iMqttTokenConnect.waitForCompletion(getTimeToWait());
        return iMqttTokenConnect;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnect() throws MqttException {
        this.aClient.disconnect().waitForCompletion();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnect(long j) throws MqttException {
        this.aClient.disconnect(j, null, null).waitForCompletion();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnectForcibly() throws MqttException {
        this.aClient.disconnectForcibly();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnectForcibly(long j) throws MqttException {
        this.aClient.disconnectForcibly(j);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.aClient.disconnectForcibly(j, j2);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.aClient.disconnectForcibly(j, j2, z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str) throws Exception {
        subscribe(new String[]{str}, new int[]{1});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr) throws Exception {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str, int i) throws Exception {
        subscribe(new String[]{str}, new int[]{i});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr, int[] iArr) throws Exception {
        subscribe(strArr, iArr, (IMqttMessageListener[]) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str, IMqttMessageListener iMqttMessageListener) throws Exception {
        subscribe(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) throws Exception {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws Exception {
        subscribe(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws Exception {
        IMqttToken iMqttTokenSubscribe = this.aClient.subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
        iMqttTokenSubscribe.waitForCompletion(getTimeToWait());
        int[] grantedQos = iMqttTokenSubscribe.getGrantedQos();
        for (int i = 0; i < grantedQos.length; i++) {
            iArr[i] = grantedQos[i];
        }
        if (grantedQos.length == 1 && iArr[0] == 128) {
            throw new MqttException(128);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{1});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str, int i) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{i});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr) throws MqttException {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        return subscribeWithResponse(strArr, iArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        return subscribeWithResponse(strArr, iArr, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr, int[] iArr) throws MqttException {
        return subscribeWithResponse(strArr, iArr, (IMqttMessageListener[]) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttToken subscribeWithResponse(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws Exception {
        IMqttToken iMqttTokenSubscribe = this.aClient.subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
        iMqttTokenSubscribe.waitForCompletion(getTimeToWait());
        return iMqttTokenSubscribe;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void unsubscribe(String str) throws MqttException {
        unsubscribe(new String[]{str});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void unsubscribe(String[] strArr) throws MqttException {
        this.aClient.unsubscribe(strArr, (Object) null, (IMqttActionListener) null).waitForCompletion(getTimeToWait());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void publish(String str, byte[] bArr, int i, boolean z) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        publish(str, mqttMessage);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void publish(String str, MqttMessage mqttMessage) throws MqttException {
        this.aClient.publish(str, mqttMessage, (Object) null, (IMqttActionListener) null).waitForCompletion(getTimeToWait());
    }

    public void setTimeToWait(long j) throws IllegalArgumentException {
        if (j < -1) {
            throw new IllegalArgumentException();
        }
        this.timeToWait = j;
    }

    public long getTimeToWait() {
        return this.timeToWait;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient, java.lang.AutoCloseable
    public void close() throws MqttException {
        this.aClient.close(false);
    }

    public void close(boolean z) throws MqttException {
        this.aClient.close(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public String getClientId() {
        return this.aClient.getClientId();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.aClient.getPendingDeliveryTokens();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public String getServerURI() {
        return this.aClient.getServerURI();
    }

    public String getCurrentServerURI() {
        return this.aClient.getCurrentServerURI();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public MqttTopic getTopic(String str) {
        return this.aClient.getTopic(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public boolean isConnected() {
        return this.aClient.isConnected();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void setCallback(MqttCallback mqttCallback) {
        this.aClient.setCallback(mqttCallback);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void setManualAcks(boolean z) {
        this.aClient.setManualAcks(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.aClient.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return MqttAsyncClient.generateClientId();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttClient
    public void reconnect() throws MqttException {
        this.aClient.reconnect();
    }

    public Debug getDebug() {
        return this.aClient.getDebug();
    }
}
