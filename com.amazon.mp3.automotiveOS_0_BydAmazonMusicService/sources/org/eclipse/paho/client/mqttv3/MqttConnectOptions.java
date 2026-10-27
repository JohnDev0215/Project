package org.eclipse.paho.client.mqttv3;

import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import org.eclipse.paho.client.mqttv3.internal.NetworkModuleService;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* JADX INFO: loaded from: classes6.dex */
public class MqttConnectOptions {
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    public static final int MAX_INFLIGHT_DEFAULT = 10;
    public static final int MQTT_VERSION_3_1 = 3;
    public static final int MQTT_VERSION_3_1_1 = 4;
    public static final int MQTT_VERSION_DEFAULT = 0;
    private char[] password;
    private SocketFactory socketFactory;
    private String userName;
    private int keepAliveInterval = 60;
    private int maxInflight = 10;
    private String willDestination = null;
    private MqttMessage willMessage = null;
    private Properties sslClientProps = null;
    private boolean httpsHostnameVerificationEnabled = true;
    private HostnameVerifier sslHostnameVerifier = null;
    private boolean cleanSession = true;
    private int connectionTimeout = 30;
    private String[] serverURIs = null;
    private int mqttVersion = 0;
    private boolean automaticReconnect = false;
    private int maxReconnectDelay = 128000;
    private Properties customWebSocketHeaders = null;
    private int executorServiceTimeout = 1;

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        this.password = (char[]) cArr.clone();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public int getMaxReconnectDelay() {
        return this.maxReconnectDelay;
    }

    public void setMaxReconnectDelay(int i) {
        this.maxReconnectDelay = i;
    }

    public void setWill(MqttTopic mqttTopic, byte[] bArr, int i, boolean z) {
        String name = mqttTopic.getName();
        validateWill(name, bArr);
        setWill(name, new MqttMessage(bArr), i, z);
    }

    public void setWill(String str, byte[] bArr, int i, boolean z) {
        validateWill(str, bArr);
        setWill(str, new MqttMessage(bArr), i, z);
    }

    private void validateWill(String str, Object obj) {
        if (str == null || obj == null) {
            throw new IllegalArgumentException();
        }
        MqttTopic.validate(str, false);
    }

    protected void setWill(String str, MqttMessage mqttMessage, int i, boolean z) {
        this.willDestination = str;
        this.willMessage = mqttMessage;
        mqttMessage.setQos(i);
        this.willMessage.setRetained(z);
        this.willMessage.setMutable(false);
    }

    public int getKeepAliveInterval() {
        return this.keepAliveInterval;
    }

    public int getMqttVersion() {
        return this.mqttVersion;
    }

    public void setKeepAliveInterval(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.keepAliveInterval = i;
    }

    public int getMaxInflight() {
        return this.maxInflight;
    }

    public void setMaxInflight(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.maxInflight = i;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.connectionTimeout = i;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public String getWillDestination() {
        return this.willDestination;
    }

    public MqttMessage getWillMessage() {
        return this.willMessage;
    }

    public Properties getSSLProperties() {
        return this.sslClientProps;
    }

    public void setSSLProperties(Properties properties) {
        this.sslClientProps = properties;
    }

    public boolean isHttpsHostnameVerificationEnabled() {
        return this.httpsHostnameVerificationEnabled;
    }

    public void setHttpsHostnameVerificationEnabled(boolean z) {
        this.httpsHostnameVerificationEnabled = z;
    }

    public HostnameVerifier getSSLHostnameVerifier() {
        return this.sslHostnameVerifier;
    }

    public void setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.sslHostnameVerifier = hostnameVerifier;
    }

    public boolean isCleanSession() {
        return this.cleanSession;
    }

    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    public String[] getServerURIs() {
        return this.serverURIs;
    }

    public void setServerURIs(String[] strArr) {
        for (String str : strArr) {
            NetworkModuleService.validateURI(str);
        }
        this.serverURIs = (String[]) strArr.clone();
    }

    public void setMqttVersion(int i) throws IllegalArgumentException {
        if (i != 0 && i != 3 && i != 4) {
            throw new IllegalArgumentException("An incorrect version was used \"" + i + "\". Acceptable version options are 0, 3 and 4.");
        }
        this.mqttVersion = i;
    }

    public boolean isAutomaticReconnect() {
        return this.automaticReconnect;
    }

    public void setAutomaticReconnect(boolean z) {
        this.automaticReconnect = z;
    }

    public int getExecutorServiceTimeout() {
        return this.executorServiceTimeout;
    }

    public void setExecutorServiceTimeout(int i) {
        this.executorServiceTimeout = i;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("MqttVersion", Integer.valueOf(getMqttVersion()));
        properties.put("CleanSession", Boolean.valueOf(isCleanSession()));
        properties.put("ConTimeout", Integer.valueOf(getConnectionTimeout()));
        properties.put("KeepAliveInterval", Integer.valueOf(getKeepAliveInterval()));
        properties.put("UserName", getUserName() == null ? "null" : getUserName());
        properties.put("WillDestination", getWillDestination() == null ? "null" : getWillDestination());
        if (getSocketFactory() == null) {
            properties.put("SocketFactory", "null");
        } else {
            properties.put("SocketFactory", getSocketFactory());
        }
        if (getSSLProperties() == null) {
            properties.put("SSLProperties", "null");
        } else {
            properties.put("SSLProperties", getSSLProperties());
        }
        return properties;
    }

    public void setCustomWebSocketHeaders(Properties properties) {
        this.customWebSocketHeaders = properties;
    }

    public Properties getCustomWebSocketHeaders() {
        return this.customWebSocketHeaders;
    }

    public String toString() {
        return Debug.dumpProperties(getDebug(), "Connection options");
    }
}
