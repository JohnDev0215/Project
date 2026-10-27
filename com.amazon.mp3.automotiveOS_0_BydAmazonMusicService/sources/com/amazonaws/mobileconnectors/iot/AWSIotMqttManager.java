package com.amazonaws.mobileconnectors.iot;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.amazonaws.AmazonClientException;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttMessageDeliveryCallback;
import com.amazonaws.regions.Region;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.VersionInfoUtils;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/* JADX INFO: loaded from: classes5.dex */
public class AWSIotMqttManager {
    public static final Integer DEFAULT_AUTO_RECONNECT_ATTEMPTS;
    public static final Boolean DEFAULT_AUTO_RECONNECT_ENABLED;
    private static final Integer DEFAULT_CONNECTION_STABILITY_TIME_SECONDS;
    public static final Integer DEFAULT_KEEP_ALIVE_SECONDS;
    private static final Long DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES;
    public static final Integer DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND;
    public static final Boolean DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED;
    private static final String TOKENIZE_ENDPOINT_PATTERN = "[\\.:]";
    private final String accountEndpointPrefix;
    private AuthenticationMode authMode;
    private boolean autoReconnect;
    private int autoReconnectsAttempted;
    private AWSCredentialsProvider clientCredentialsProvider;
    private SocketFactory clientSocketFactory;
    private Integer connectionStabilityTime;
    private MqttManagerConnectionState connectionState;
    private int currentReconnectRetryTime;
    private Properties customWebsocketHeaders;
    private long drainingInterval;
    private final String endpoint;
    private boolean fullQueueKeepsOldest;
    private Long lastConnackTime;
    private int maxAutoReconnectAttempts;
    private int maxReconnectRetryTime;
    private int minReconnectRetryTime;
    private String mqttBrokerURL;
    private MqttAsyncClient mqttClient;
    private final String mqttClientId;
    private AWSIotMqttLastWillAndTestament mqttLWT;
    private final ConcurrentLinkedQueue<AWSIotMqttQueueMessage> mqttMessageQueue;
    private boolean needResubscribe;
    private Integer offlinePublishQueueBound;
    private boolean offlinePublishQueueEnabled;
    private String password;
    private String proxyHost;
    private int proxyPort;
    private final Region region;
    private boolean sessionPresent;
    private AWSIotWebSocketUrlSigner signer;
    private final Map<String, AWSIotMqttTopic> topicListeners;
    private Long unitTestMillisOverride;
    private boolean userDisconnect;
    private int userKeepAlive;
    private AWSIotMqttClientStatusCallback userStatusCallback;
    private String username;
    private static final Integer ANDROID_API_LEVEL_16 = 16;
    private static final Integer MILLIS_IN_ONE_SECOND = 1000;
    private static final Log LOGGER = LogFactory.getLog((Class<?>) AWSIotMqttManager.class);
    public static final Integer DEFAULT_MIN_RECONNECT_RETRY_TIME_SECONDS = 4;
    public static final Integer DEFAULT_MAX_RECONNECT_RETRY_TIME_SECONDS = 64;
    private boolean cleanSession = true;
    private boolean metricsIsEnabled = true;
    String userMetaData = "?SDK=Android&Version=" + VersionInfoUtils.getVersion();
    Map<String, String> userMetaDataMap = new HashMap();

    enum AuthenticationMode {
        KEYSTORE,
        IAM,
        CUSTOM_AUTH,
        USERNAME_PASSWORD
    }

    static {
        Boolean bool = Boolean.TRUE;
        DEFAULT_AUTO_RECONNECT_ENABLED = bool;
        DEFAULT_AUTO_RECONNECT_ATTEMPTS = 10;
        DEFAULT_KEEP_ALIVE_SECONDS = 300;
        DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED = bool;
        DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND = 100;
        DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES = 250L;
        DEFAULT_CONNECTION_STABILITY_TIME_SECONDS = 10;
    }

    public void setMetricsIsEnabled(boolean z) {
        this.metricsIsEnabled = z;
        Log log = LOGGER;
        StringBuilder sb = new StringBuilder();
        sb.append("Metrics collection is ");
        sb.append(this.metricsIsEnabled ? "enabled" : "disabled");
        log.info(sb.toString());
    }

    public boolean isMetricsEnabled() {
        return this.metricsIsEnabled;
    }

    @Deprecated
    public void addUserMetaData(Map<String, String> map) {
        StringBuilder sb = new StringBuilder(this.userMetaData);
        int length = sb.length();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().equals("SDK") || entry.getKey().equals(JsonDocumentFields.VERSION)) {
                    LOGGER.warn("Keynames 'SDK' and 'Version' are reserved and will be skipped");
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        if (sb.length() > 255) {
            LOGGER.warn("Too many characters. User metadata was truncated.", new IllegalArgumentException("Total number of characters in user metadata cannot exceed " + (255 - length)));
            this.userMetaData = sb.substring(0, 255);
            return;
        }
        this.userMetaData = sb.toString();
    }

    public void updateUserMetaData(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("?SDK=Android&Version=" + VersionInfoUtils.getVersion());
        int length = sb.length();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.userMetaDataMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry2 : this.userMetaDataMap.entrySet()) {
            if (!entry2.getKey().equals("SDK") && !entry2.getKey().equals(JsonDocumentFields.VERSION)) {
                String value = entry2.getValue();
                if (value == null || "".equals(value)) {
                    sb.append("&" + entry2.getKey());
                } else {
                    sb.append("&" + entry2.getKey() + "=" + entry2.getValue());
                }
            } else {
                LOGGER.warn("Keynames 'SDK' and 'Version' are reserved and will be skipped");
            }
        }
        if (sb.length() > 255) {
            LOGGER.warn("Too many characters. User metadata was truncated.", new IllegalArgumentException("Total number of characters in user metadata cannot exceed " + (255 - length)));
            this.userMetaData = sb.substring(0, 255);
            return;
        }
        this.userMetaData = sb.toString();
    }

    public String getAccountEndpointPrefix() {
        return this.accountEndpointPrefix;
    }

    public boolean isAutoReconnect() {
        return this.autoReconnect;
    }

    public void setAutoReconnect(boolean z) {
        this.autoReconnect = z;
    }

    @Deprecated
    public int getReconnectTimeout() {
        return this.minReconnectRetryTime;
    }

    @Deprecated
    public void setReconnectTimeout(int i) {
        this.minReconnectRetryTime = i;
    }

    public void setReconnectRetryLimits(int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException("Minimum reconnect time needs to be less than Maximum.");
        }
        this.minReconnectRetryTime = i;
        this.maxReconnectRetryTime = i2;
    }

    public int getMinReconnectRetryTime() {
        return this.minReconnectRetryTime;
    }

    public int getMaxReconnectRetryTime() {
        return this.maxReconnectRetryTime;
    }

    public int getMaxAutoReconnectAttempts() {
        return this.maxAutoReconnectAttempts;
    }

    @Deprecated
    public void setMaxAutoReconnectAttepts(int i) {
        setMaxAutoReconnectAttempts(i);
    }

    public void setMaxAutoReconnectAttempts(int i) {
        if (i <= 0 && i != -1) {
            throw new IllegalArgumentException("Max reconnection attempts must be postive or -1");
        }
        this.maxAutoReconnectAttempts = i;
    }

    public void setConnectionStabilityTime(int i) {
        this.connectionStabilityTime = Integer.valueOf(i);
    }

    public int getConnectionStabilityTime() {
        return this.connectionStabilityTime.intValue();
    }

    public boolean isOfflinePublishQueueEnabled() {
        return this.offlinePublishQueueEnabled;
    }

    public void setOfflinePublishQueueEnabled(boolean z) {
        this.offlinePublishQueueEnabled = z;
    }

    public Integer getOfflinePublishQueueBound() {
        return this.offlinePublishQueueBound;
    }

    public void setOfflinePublishQueueBound(Integer num) {
        if (num.intValue() <= 0) {
            throw new IllegalArgumentException("Offline queue bound must be > 0");
        }
        this.offlinePublishQueueBound = num;
    }

    public Long getDrainingInterval() {
        return Long.valueOf(this.drainingInterval);
    }

    public void setDrainingInterval(Long l) {
        this.drainingInterval = l.longValue();
    }

    public boolean fullPublishQueueKeepsOldestMessages() {
        return this.fullQueueKeepsOldest;
    }

    public void setFullQueueToKeepOldestMessages() {
        this.fullQueueKeepsOldest = true;
    }

    public void setFullQueueToKeepNewestMessages() {
        this.fullQueueKeepsOldest = false;
    }

    public int getKeepAlive() {
        return this.userKeepAlive;
    }

    public void setKeepAlive(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Keep alive must be >= 0");
        }
        this.userKeepAlive = i;
    }

    public AWSIotMqttLastWillAndTestament getMqttLastWillAndTestament() {
        return this.mqttLWT;
    }

    public void setMqttLastWillAndTestament(AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament) {
        this.mqttLWT = aWSIotMqttLastWillAndTestament;
    }

    public void setCredentialsProvider(AWSCredentialsProvider aWSCredentialsProvider) {
        this.clientCredentialsProvider = aWSCredentialsProvider;
    }

    void setMqttClient(MqttAsyncClient mqttAsyncClient) {
        this.mqttClient = mqttAsyncClient;
    }

    ConcurrentLinkedQueue<AWSIotMqttQueueMessage> getMqttMessageQueue() {
        return this.mqttMessageQueue;
    }

    MqttManagerConnectionState getConnectionState() {
        return this.connectionState;
    }

    void setUnitTestMillisOverride(Long l) {
        this.unitTestMillisOverride = l;
    }

    Region getRegion() {
        return this.region;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Long getSystemTimeMs() {
        Long l = this.unitTestMillisOverride;
        return l == null ? Long.valueOf(System.currentTimeMillis()) : l;
    }

    public void setAutoResubscribe(boolean z) {
        this.needResubscribe = z;
    }

    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    public AWSIotMqttManager(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mqttClientId is null or empty");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("endpoint is null");
        }
        this.topicListeners = new ConcurrentHashMap();
        this.mqttMessageQueue = new ConcurrentLinkedQueue<>();
        this.mqttClientId = str;
        this.endpoint = str2;
        this.accountEndpointPrefix = null;
        this.region = getRegionFromIotEndpoint(str2);
        initDefaults();
    }

    public AWSIotMqttManager(String str, Region region, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mqttClientId is null or empty");
        }
        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("accountEndpointPrefix is null");
        }
        this.topicListeners = new ConcurrentHashMap();
        this.mqttMessageQueue = new ConcurrentLinkedQueue<>();
        this.accountEndpointPrefix = str2;
        this.mqttClientId = str;
        this.region = region;
        this.endpoint = null;
        initDefaults();
    }

    private AWSIotMqttManager(Region region, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mqttClientId is null or empty");
        }
        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("endpoint is null or empty.");
        }
        this.topicListeners = new ConcurrentHashMap();
        this.mqttMessageQueue = new ConcurrentLinkedQueue<>();
        this.endpoint = str2;
        this.mqttClientId = str;
        this.region = region;
        this.accountEndpointPrefix = null;
        initDefaults();
    }

    public static AWSIotMqttManager from(Region region, ClientId clientId, Endpoint endpoint) {
        return new AWSIotMqttManager(region, clientId.getValue(), endpoint.getValue());
    }

    private void initDefaults() {
        this.connectionState = MqttManagerConnectionState.Disconnected;
        this.autoReconnect = DEFAULT_AUTO_RECONNECT_ENABLED.booleanValue();
        this.minReconnectRetryTime = DEFAULT_MIN_RECONNECT_RETRY_TIME_SECONDS.intValue();
        this.maxReconnectRetryTime = DEFAULT_MAX_RECONNECT_RETRY_TIME_SECONDS.intValue();
        this.maxAutoReconnectAttempts = DEFAULT_AUTO_RECONNECT_ATTEMPTS.intValue();
        this.userKeepAlive = DEFAULT_KEEP_ALIVE_SECONDS.intValue();
        this.mqttLWT = null;
        this.offlinePublishQueueEnabled = DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED.booleanValue();
        this.offlinePublishQueueBound = DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND;
        this.drainingInterval = DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES.longValue();
        setFullQueueToKeepNewestMessages();
        this.connectionStabilityTime = DEFAULT_CONNECTION_STABILITY_TIME_SECONDS;
        this.unitTestMillisOverride = null;
        this.needResubscribe = true;
        this.customWebsocketHeaders = new Properties();
    }

    public void connectUsingALPN(KeyStore keyStore, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        connect(keyStore, 443, aWSIotMqttClientStatusCallback);
    }

    public void connectWithProxy(KeyStore keyStore, String str, int i, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        this.proxyHost = str;
        this.proxyPort = i;
        connect(keyStore, 8883, aWSIotMqttClientStatusCallback);
    }

    public void connect(KeyStore keyStore, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        connect(keyStore, 8883, aWSIotMqttClientStatusCallback);
    }

    private void connect(KeyStore keyStore, int i, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        SSLSocketFactory socketFactoryWithKeyStore;
        if (Build.VERSION.SDK_INT < ANDROID_API_LEVEL_16.intValue()) {
            throw new UnsupportedOperationException("API Level 16+ required for TLS 1.2 Mutual Auth");
        }
        if (keyStore == null) {
            throw new IllegalArgumentException("keyStore is null");
        }
        this.userStatusCallback = aWSIotMqttClientStatusCallback;
        if (!MqttManagerConnectionState.Disconnected.equals(this.connectionState)) {
            userConnectionCallback();
            return;
        }
        String str = this.endpoint;
        if (str != null) {
            this.mqttBrokerURL = String.format("ssl://%s:%d", str, Integer.valueOf(i));
        } else {
            String str2 = this.accountEndpointPrefix;
            if (str2 != null) {
                this.mqttBrokerURL = String.format("ssl://%s.iot.%s.%s:%d", str2, this.region.getName(), this.region.getDomain(), Integer.valueOf(i));
            } else {
                throw new IllegalStateException("No valid endpoint information is available. Please pass in a valid endpoint in AWSIotMqttManager.");
            }
        }
        this.authMode = AuthenticationMode.KEYSTORE;
        LOGGER.debug("MQTT broker: " + this.mqttBrokerURL);
        try {
            if (this.mqttClient == null) {
                this.mqttClient = new MqttAsyncClient(this.mqttBrokerURL, this.mqttClientId, new MemoryPersistence());
            }
            String str3 = this.proxyHost;
            if (str3 != null) {
                socketFactoryWithKeyStore = AWSIotSslUtility.getSocketFactoryWithKeyStoreAndProxy(keyStore, i, str3, this.proxyPort);
            } else {
                socketFactoryWithKeyStore = AWSIotSslUtility.getSocketFactoryWithKeyStore(keyStore, i);
            }
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament = this.mqttLWT;
            if (aWSIotMqttLastWillAndTestament != null) {
                mqttConnectOptions.setWill(aWSIotMqttLastWillAndTestament.getTopic(), this.mqttLWT.getMessage().getBytes(), this.mqttLWT.getQos().asInt(), false);
            }
            this.clientSocketFactory = socketFactoryWithKeyStore;
            mqttConnectOptions.setSocketFactory(socketFactoryWithKeyStore);
            mqttConnect(mqttConnectOptions);
        } catch (KeyManagementException e) {
            throw new AWSIotCertificateException("A certificate error occurred.", e);
        } catch (KeyStoreException e2) {
            throw new AWSIotCertificateException("A certificate error occurred.", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AWSIotCertificateException("A certificate error occurred.", e3);
        } catch (NoSuchProviderException e4) {
            throw new AWSIotCertificateException("A certificate error occurred.", e4);
        } catch (UnrecoverableKeyException e5) {
            throw new AWSIotCertificateException("A certificate error occurred.", e5);
        } catch (MqttException e6) {
            throw new AmazonClientException("An error occured in the MQTT client.", e6);
        }
    }

    public void connect(AWSCredentialsProvider aWSCredentialsProvider, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        this.clientCredentialsProvider = aWSCredentialsProvider;
        if (aWSCredentialsProvider == null) {
            throw new IllegalArgumentException("credentials provider cannot be null");
        }
        this.userStatusCallback = aWSIotMqttClientStatusCallback;
        if (!MqttManagerConnectionState.Disconnected.equals(this.connectionState)) {
            userConnectionCallback();
        } else {
            new Thread(new Runnable() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.1
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    AWSIotMqttManager.this.signer = new AWSIotWebSocketUrlSigner("iotdata");
                    if (AWSIotMqttManager.this.endpoint != null) {
                        str = String.format("%s:443", AWSIotMqttManager.this.endpoint);
                    } else if (AWSIotMqttManager.this.accountEndpointPrefix != null) {
                        str = String.format("%s.iot.%s.%s:443", AWSIotMqttManager.this.accountEndpointPrefix, AWSIotMqttManager.this.region.getName(), AWSIotMqttManager.this.region.getDomain());
                    } else {
                        throw new IllegalStateException("No valid endpoint information is available. Please pass in a valid endpoint in AWSIotMqttManager.");
                    }
                    AWSIotMqttManager.this.authMode = AuthenticationMode.IAM;
                    AWSIotMqttManager.LOGGER.debug("MQTT broker: " + str);
                    try {
                        String signedUrl = AWSIotMqttManager.this.signer.getSignedUrl(str, AWSIotMqttManager.this.clientCredentialsProvider.getCredentials(), System.currentTimeMillis() - (SDKGlobalConfiguration.getGlobalTimeOffset() * ((long) AWSIotMqttManager.MILLIS_IN_ONE_SECOND.intValue())), AWSIotMqttManager.this.region);
                        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                        mqttConnectOptions.setServerURIs(new String[]{signedUrl});
                        if (AWSIotMqttManager.this.mqttLWT != null) {
                            mqttConnectOptions.setWill(AWSIotMqttManager.this.mqttLWT.getTopic(), AWSIotMqttManager.this.mqttLWT.getMessage().getBytes(), AWSIotMqttManager.this.mqttLWT.getQos().asInt(), false);
                        }
                        if (AWSIotMqttManager.this.mqttClient == null) {
                            AWSIotMqttManager.this.mqttClient = new MqttAsyncClient("wss://" + str, AWSIotMqttManager.this.mqttClientId, new MemoryPersistence());
                        }
                        AWSIotMqttManager.this.mqttConnect(mqttConnectOptions);
                    } catch (MqttException e) {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                        AWSIotMqttManager.this.userConnectionCallback(new AmazonClientException("An error occurred in the MQTT client.", e));
                    } catch (Exception e2) {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                        AWSIotMqttManager.this.userConnectionCallback(e2);
                    }
                }
            }, "Mqtt Connect Thread").start();
        }
    }

    public void connect(String str, String str2, String str3, String str4, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("tokenKeyName/token/tokenSignature cannot be null");
        }
        this.userStatusCallback = aWSIotMqttClientStatusCallback;
        this.authMode = AuthenticationMode.CUSTOM_AUTH;
        this.customWebsocketHeaders.setProperty("X-Amz-CustomAuthorizer-Name", str4);
        this.customWebsocketHeaders.setProperty(str, str2);
        if (str3 != null) {
            this.customWebsocketHeaders.setProperty("X-Amz-CustomAuthorizer-Signature", str3);
        }
        customAuthConnect(buildOptions());
    }

    public void connect(String str, String str2, AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("username and/or password cannot be null.");
        }
        this.username = str;
        this.password = str2;
        this.userStatusCallback = aWSIotMqttClientStatusCallback;
        this.authMode = AuthenticationMode.USERNAME_PASSWORD;
        customAuthConnect(buildOptions());
    }

    private MqttConnectOptions buildOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        if (AuthenticationMode.USERNAME_PASSWORD.equals(this.authMode)) {
            mqttConnectOptions.setUserName(this.username);
            mqttConnectOptions.setPassword(this.password.toCharArray());
        }
        this.customWebsocketHeaders.setProperty(HttpHeader.USER_AGENT, this.userMetaData);
        mqttConnectOptions.setCustomWebSocketHeaders(this.customWebsocketHeaders);
        return mqttConnectOptions;
    }

    private void customAuthConnect(MqttConnectOptions mqttConnectOptions) {
        if (!MqttManagerConnectionState.Disconnected.equals(this.connectionState)) {
            userConnectionCallback();
            return;
        }
        String endpointWithHttpPort = getEndpointWithHttpPort();
        LOGGER.debug("MQTT broker endpoint: " + endpointWithHttpPort);
        try {
            if (this.mqttClient == null) {
                this.mqttClient = new MqttAsyncClient("wss://" + endpointWithHttpPort + "/mqtt", this.mqttClientId, new MemoryPersistence());
            }
            AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament = this.mqttLWT;
            if (aWSIotMqttLastWillAndTestament != null) {
                mqttConnectOptions.setWill(aWSIotMqttLastWillAndTestament.getTopic(), this.mqttLWT.getMessage().getBytes(), this.mqttLWT.getQos().asInt(), false);
            }
            this.customWebsocketHeaders.setProperty(HttpHeader.USER_AGENT, this.userMetaData);
            mqttConnectOptions.setCustomWebSocketHeaders(this.customWebsocketHeaders);
            mqttConnect(mqttConnectOptions);
        } catch (MqttException e) {
            this.connectionState = MqttManagerConnectionState.Disconnected;
            userConnectionCallback(new AmazonClientException("An error occurred in the MQTT client.", e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mqttConnect(MqttConnectOptions mqttConnectOptions) {
        Log log = LOGGER;
        log.debug("ready to do mqtt connect");
        mqttConnectOptions.setCleanSession(this.cleanSession);
        mqttConnectOptions.setKeepAliveInterval(this.userKeepAlive);
        if (isMetricsEnabled() && !AuthenticationMode.USERNAME_PASSWORD.equals(this.authMode)) {
            mqttConnectOptions.setUserName(this.userMetaData);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("metrics collection is ");
        sb.append(isMetricsEnabled() ? "enabled" : "disabled");
        sb.append(", username: ");
        sb.append(mqttConnectOptions.getUserName());
        log.info(sb.toString());
        this.topicListeners.clear();
        this.mqttMessageQueue.clear();
        resetReconnect();
        this.userDisconnect = false;
        setupCallbackForMqttClient();
        try {
            this.connectionState = MqttManagerConnectionState.Connecting;
            userConnectionCallback();
            this.mqttClient.connect(mqttConnectOptions, null, new IMqttActionListener() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.2
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    AWSIotMqttManager.LOGGER.info("onSuccess: mqtt connection is successful.");
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Connected;
                    AWSIotMqttManager aWSIotMqttManager = AWSIotMqttManager.this;
                    aWSIotMqttManager.lastConnackTime = aWSIotMqttManager.getSystemTimeMs();
                    AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                    if (AWSIotMqttManager.this.mqttMessageQueue.size() > 0) {
                        AWSIotMqttManager.this.publishMessagesFromQueue();
                    }
                    AWSIotMqttManager.this.userConnectionCallback();
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    AWSIotMqttManager.LOGGER.warn("onFailure: connection failed.", th);
                    if (!AWSIotMqttManager.this.userDisconnect && AWSIotMqttManager.this.autoReconnect) {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Reconnecting;
                        AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                        AWSIotMqttManager.this.userConnectionCallback(th);
                        AWSIotMqttManager.this.scheduleReconnect();
                    } else {
                        AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                        AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                        AWSIotMqttManager.this.userConnectionCallback(th);
                    }
                    AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                }
            });
        } catch (MqttException e) {
            int reasonCode = e.getReasonCode();
            if (reasonCode == 32100) {
                this.connectionState = MqttManagerConnectionState.Connected;
                userConnectionCallback();
            } else if (reasonCode == 32110) {
                this.connectionState = MqttManagerConnectionState.Connecting;
                userConnectionCallback();
            } else {
                this.connectionState = MqttManagerConnectionState.Disconnected;
                userConnectionCallback(e);
            }
        } catch (Exception e2) {
            this.connectionState = MqttManagerConnectionState.Disconnected;
            userConnectionCallback(e2);
        }
    }

    private static Region getRegionFromIotEndpoint(String str) {
        for (String str2 : str.toLowerCase().split(TOKENIZE_ENDPOINT_PATTERN)) {
            Region region = Region.getRegion(str2);
            if (region != null) {
                return region;
            }
        }
        throw new IllegalArgumentException("Cannot find AWS Region code within endpoint");
    }

    private String getEndpointWithHttpPort() {
        String str = this.endpoint;
        if (str != null) {
            return String.format("%s:443", str);
        }
        String str2 = this.accountEndpointPrefix;
        if (str2 != null) {
            return String.format("%s.iot.%s.%s:443", str2, this.region.getName(), this.region.getDomain());
        }
        throw new IllegalStateException("No valid endpoint information is available. Please pass in a valid endpoint in AWSIotMqttManager.");
    }

    public boolean disconnect() {
        this.userDisconnect = true;
        reset();
        this.topicListeners.clear();
        this.connectionState = MqttManagerConnectionState.Disconnected;
        userConnectionCallback();
        return true;
    }

    void reset() {
        MqttAsyncClient mqttAsyncClient = this.mqttClient;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            return;
        }
        try {
            this.mqttClient.disconnect(0L).waitForCompletion();
        } catch (MqttException e) {
            throw new AmazonClientException("Client error when disconnecting.", e);
        }
    }

    void reconnectToSession() {
        if (this.mqttClient == null || MqttManagerConnectionState.Disconnected.equals(this.connectionState)) {
            return;
        }
        Log log = LOGGER;
        log.info("attempting to reconnect to mqtt broker");
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(this.cleanSession);
        mqttConnectOptions.setKeepAliveInterval(this.userKeepAlive);
        AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament = this.mqttLWT;
        if (aWSIotMqttLastWillAndTestament != null) {
            mqttConnectOptions.setWill(aWSIotMqttLastWillAndTestament.getTopic(), this.mqttLWT.getMessage().getBytes(), this.mqttLWT.getQos().asInt(), false);
        }
        int i = AnonymousClass8.$SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttManager$AuthenticationMode[this.authMode.ordinal()];
        if (i == 1) {
            mqttConnectOptions.setSocketFactory(this.clientSocketFactory);
        } else if (i == 2) {
            reconnectWithIAM(mqttConnectOptions);
        } else if (i == 3) {
            mqttConnectOptions.setCustomWebSocketHeaders(this.customWebsocketHeaders);
        } else {
            if (i == 4) {
                mqttConnectOptions.setUserName(this.username);
                mqttConnectOptions.setPassword(this.password.toCharArray());
                mqttConnectOptions.setCustomWebSocketHeaders(this.customWebsocketHeaders);
            }
            handleConnectionFailure(new IllegalStateException("Unexpected value: " + this.authMode));
        }
        setupCallbackForMqttClient();
        try {
            this.autoReconnectsAttempted++;
            log.debug("mqtt reconnecting attempt " + this.autoReconnectsAttempted);
            this.mqttClient.connect(mqttConnectOptions, null, new IMqttActionListener() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.3
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    AWSIotMqttManager.LOGGER.info("Reconnect successful");
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Connected;
                    AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                    AWSIotMqttManager aWSIotMqttManager = AWSIotMqttManager.this;
                    aWSIotMqttManager.lastConnackTime = aWSIotMqttManager.getSystemTimeMs();
                    if (AWSIotMqttManager.this.needResubscribe) {
                        AWSIotMqttManager.this.resubscribeToTopics();
                    }
                    if (AWSIotMqttManager.this.mqttMessageQueue.size() > 0) {
                        AWSIotMqttManager.this.publishMessagesFromQueue();
                    }
                    AWSIotMqttManager.this.userConnectionCallback();
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    AWSIotMqttManager.LOGGER.warn("Reconnect failed ", th);
                    AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                    AWSIotMqttManager.this.handleConnectionFailure(th);
                }
            });
        } catch (MqttException e) {
            LOGGER.error("Exception during reconnect, exception: ", e);
            handleConnectionFailure(e);
        }
    }

    private void reconnectWithIAM(MqttConnectOptions mqttConnectOptions) {
        this.signer = new AWSIotWebSocketUrlSigner("iotdata");
        try {
            String signedUrl = this.signer.getSignedUrl(getEndpointWithHttpPort(), this.clientCredentialsProvider.getCredentials(), System.currentTimeMillis(), this.region);
            LOGGER.debug("Reconnect to mqtt broker: " + this.endpoint + " mqttWebSocketURL: " + signedUrl);
            mqttConnectOptions.setServerURIs(new String[]{signedUrl});
        } catch (AmazonClientException e) {
            LOGGER.error("Failed to get credentials. AmazonClientException: ", e);
            handleConnectionFailure(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectionFailure(Throwable th) {
        if (scheduleReconnect()) {
            this.connectionState = MqttManagerConnectionState.Reconnecting;
        } else {
            this.connectionState = MqttManagerConnectionState.Disconnected;
        }
        userConnectionCallback(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean scheduleReconnect() {
        Log log = LOGGER;
        log.info("schedule Reconnect attempt " + this.autoReconnectsAttempted + " of " + this.maxAutoReconnectAttempts + " in " + this.currentReconnectRetryTime + " seconds.");
        int i = this.maxAutoReconnectAttempts;
        if (i == -1 || this.autoReconnectsAttempted < i) {
            final HandlerThread handlerThread = new HandlerThread("Reconnect thread");
            handlerThread.start();
            new Handler(handlerThread.getLooper()).postDelayed(new Runnable() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.4
                @Override // java.lang.Runnable
                public void run() {
                    AWSIotMqttManager.LOGGER.debug("TID: " + handlerThread.getThreadId() + " trying to reconnect to session");
                    if (AWSIotMqttManager.this.mqttClient != null && !AWSIotMqttManager.this.mqttClient.isConnected()) {
                        AWSIotMqttManager.this.reconnectToSession();
                    }
                    handlerThread.quit();
                }
            }, MILLIS_IN_ONE_SECOND.intValue() * this.currentReconnectRetryTime);
            this.currentReconnectRetryTime = Math.min(this.currentReconnectRetryTime * 2, this.maxReconnectRetryTime);
            return true;
        }
        log.warn("schedule reconnect returns false");
        return false;
    }

    public void resetReconnect() {
        LOGGER.info("resetting reconnect attempt and retry time");
        this.autoReconnectsAttempted = 0;
        this.currentReconnectRetryTime = this.minReconnectRetryTime;
    }

    public void subscribeToTopic(String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        subscribeToTopic(str, aWSIotMqttQos, null, aWSIotMqttNewMessageCallback);
    }

    public void subscribeToTopic(String str, AWSIotMqttQos aWSIotMqttQos, final AWSIotMqttSubscriptionStatusCallback aWSIotMqttSubscriptionStatusCallback, AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        if (aWSIotMqttQos == null) {
            throw new IllegalArgumentException("QoS cannot be null.");
        }
        if (this.mqttClient != null) {
            try {
                this.topicListeners.put(str, new AWSIotMqttTopic(str, aWSIotMqttQos, aWSIotMqttNewMessageCallback));
                if (aWSIotMqttSubscriptionStatusCallback != null) {
                    this.mqttClient.subscribe(str, aWSIotMqttQos.asInt(), (Object) null, new IMqttActionListener() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.5
                        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                        public void onSuccess(IMqttToken iMqttToken) {
                            AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                            aWSIotMqttSubscriptionStatusCallback.onSuccess();
                        }

                        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                        public void onFailure(IMqttToken iMqttToken, Throwable th) {
                            AWSIotMqttManager.this.sessionPresent = iMqttToken.getSessionPresent();
                            aWSIotMqttSubscriptionStatusCallback.onFailure(th);
                        }
                    });
                } else {
                    this.mqttClient.subscribe(str, aWSIotMqttQos.asInt());
                }
            } catch (MqttException e) {
                this.topicListeners.remove(str);
                if (aWSIotMqttSubscriptionStatusCallback != null) {
                    aWSIotMqttSubscriptionStatusCallback.onFailure(e);
                    return;
                }
                throw new AmazonClientException("Client error when subscribing.", e);
            }
        }
    }

    public void unsubscribeTopic(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        MqttAsyncClient mqttAsyncClient = this.mqttClient;
        if (mqttAsyncClient != null) {
            try {
                mqttAsyncClient.unsubscribe(str);
                this.topicListeners.remove(str);
            } catch (MqttException e) {
                throw new AmazonClientException("Client error while unsubscribing.", e);
            }
        }
    }

    void resubscribeToTopics() {
        LOGGER.info("Auto-resubscribe is enabled. Resubscribing to previous topics.");
        for (AWSIotMqttTopic aWSIotMqttTopic : this.topicListeners.values()) {
            MqttAsyncClient mqttAsyncClient = this.mqttClient;
            if (mqttAsyncClient != null) {
                try {
                    mqttAsyncClient.subscribe(aWSIotMqttTopic.getTopic(), aWSIotMqttTopic.getQos().asInt());
                } catch (MqttException e) {
                    LOGGER.error("Error while resubscribing to previously subscribed toipcs.", e);
                }
            }
        }
    }

    public void publishString(String str, String str2, AWSIotMqttQos aWSIotMqttQos) {
        if (str == null) {
            throw new IllegalArgumentException("publish string is null");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        if (aWSIotMqttQos == null) {
            throw new IllegalArgumentException("QoS cannot be null");
        }
        publishData(str.getBytes(StringUtils.UTF8), str2, aWSIotMqttQos);
    }

    public void publishString(String str, String str2, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj) {
        publishString(str, str2, aWSIotMqttQos, aWSIotMqttMessageDeliveryCallback, obj, false);
    }

    public void publishString(String str, String str2, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException("publish string is null");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        if (aWSIotMqttQos == null) {
            throw new IllegalArgumentException("QoS cannot be null");
        }
        publishData(str.getBytes(StringUtils.UTF8), str2, aWSIotMqttQos, aWSIotMqttMessageDeliveryCallback, obj, z);
    }

    public void publishData(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos) {
        publishData(bArr, str, aWSIotMqttQos, null, null);
    }

    public void publishData(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj) {
        publishData(bArr, str, aWSIotMqttQos, aWSIotMqttMessageDeliveryCallback, obj, false);
    }

    public void publishData(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("topic is null or empty");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (aWSIotMqttQos == null) {
            throw new IllegalArgumentException("QoS cannot be null");
        }
        PublishMessageUserData publishMessageUserData = new PublishMessageUserData(aWSIotMqttMessageDeliveryCallback, obj);
        MqttManagerConnectionState mqttManagerConnectionState = this.connectionState;
        if (mqttManagerConnectionState == MqttManagerConnectionState.Connected) {
            if (this.mqttMessageQueue.isEmpty()) {
                try {
                    this.mqttClient.publish(str, bArr, aWSIotMqttQos.asInt(), z, publishMessageUserData, null);
                    return;
                } catch (MqttException e) {
                    notifyPublishResult(aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, obj, new AmazonClientException("Client error while publishing.", e));
                    return;
                }
            }
            putMessageInQueueAndNotify(bArr, str, aWSIotMqttQos, publishMessageUserData);
            return;
        }
        if (mqttManagerConnectionState == MqttManagerConnectionState.Reconnecting) {
            if (this.offlinePublishQueueEnabled) {
                putMessageInQueueAndNotify(bArr, str, aWSIotMqttQos, publishMessageUserData);
                return;
            } else {
                notifyPublishResult(aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, obj, new AmazonClientException("Client error while publishing : Offline publish queue is not enabled and client is not connected"));
                return;
            }
        }
        notifyPublishResult(aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, obj, new AmazonClientException("Client is disconnected or not yet connected."));
    }

    void putMessageInQueueAndNotify(byte[] bArr, String str, AWSIotMqttQos aWSIotMqttQos, PublishMessageUserData publishMessageUserData) {
        AWSIotMqttQueueMessage aWSIotMqttQueueMessage = new AWSIotMqttQueueMessage(str, bArr, aWSIotMqttQos, publishMessageUserData);
        if (this.mqttMessageQueue.size() >= this.offlinePublishQueueBound.intValue()) {
            if (this.fullQueueKeepsOldest) {
                notifyPublishResult(publishMessageUserData.getUserCallback(), AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, publishMessageUserData.getUserData(), new AmazonClientException("Failed to publish the message. Queue is full and set to hold onto the oldest messages."));
                return;
            }
            this.mqttMessageQueue.remove();
        }
        this.mqttMessageQueue.add(aWSIotMqttQueueMessage);
    }

    void publishMessagesFromQueue() {
        ConcurrentLinkedQueue<AWSIotMqttQueueMessage> concurrentLinkedQueue;
        if (this.connectionState != MqttManagerConnectionState.Connected || (concurrentLinkedQueue = this.mqttMessageQueue) == null || concurrentLinkedQueue.isEmpty()) {
            return;
        }
        AWSIotMqttQueueMessage aWSIotMqttQueueMessagePoll = this.mqttMessageQueue.poll();
        if (aWSIotMqttQueueMessagePoll != null) {
            try {
                if (aWSIotMqttQueueMessagePoll.getUserData() != null && aWSIotMqttQueueMessagePoll.getUserData().getUserCallback() != null) {
                    this.mqttClient.publish(aWSIotMqttQueueMessagePoll.getTopic(), aWSIotMqttQueueMessagePoll.getMessage(), aWSIotMqttQueueMessagePoll.getQos().asInt(), false, aWSIotMqttQueueMessagePoll.getUserData(), null);
                } else {
                    this.mqttClient.publish(aWSIotMqttQueueMessagePoll.getTopic(), aWSIotMqttQueueMessagePoll.getMessage(), aWSIotMqttQueueMessagePoll.getQos().asInt(), false);
                }
            } catch (MqttException e) {
                notifyPublishResult(aWSIotMqttQueueMessagePoll.getUserData().getUserCallback(), AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Fail, aWSIotMqttQueueMessagePoll.getUserData().getUserData(), new AmazonClientException("Client error while publishing.", e));
            }
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.6
            @Override // java.lang.Runnable
            public void run() {
                if (AWSIotMqttManager.this.mqttMessageQueue.isEmpty() || AWSIotMqttManager.this.connectionState != MqttManagerConnectionState.Connected) {
                    return;
                }
                AWSIotMqttManager.this.publishMessagesFromQueue();
            }
        }, this.drainingInterval);
    }

    void setupCallbackForMqttClient() {
        LOGGER.debug("Setting up Callback for MqttClient");
        this.mqttClient.setCallback(new MqttCallback() { // from class: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager.7
            @Override // org.eclipse.paho.client.mqttv3.MqttCallback
            public void connectionLost(Throwable th) {
                AWSIotMqttManager.LOGGER.warn("connection is Lost");
                if (!AWSIotMqttManager.this.userDisconnect && AWSIotMqttManager.this.autoReconnect) {
                    if (AWSIotMqttManager.this.lastConnackTime.longValue() + ((long) (AWSIotMqttManager.this.connectionStabilityTime.intValue() * AWSIotMqttManager.MILLIS_IN_ONE_SECOND.intValue())) < AWSIotMqttManager.this.getSystemTimeMs().longValue()) {
                        AWSIotMqttManager.this.resetReconnect();
                    }
                    AWSIotMqttManager.this.handleConnectionFailure(th);
                } else {
                    AWSIotMqttManager.this.connectionState = MqttManagerConnectionState.Disconnected;
                    AWSIotMqttManager.this.userConnectionCallback(th);
                }
            }

            @Override // org.eclipse.paho.client.mqttv3.MqttCallback
            public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
                AWSIotMqttTopic aWSIotMqttTopic;
                AWSIotMqttManager.LOGGER.info("message arrived on topic: " + str);
                byte[] payload = mqttMessage.getPayload();
                for (String str2 : AWSIotMqttManager.this.topicListeners.keySet()) {
                    if (AWSIotMqttManager.isTopicMatch(str2, str) && (aWSIotMqttTopic = (AWSIotMqttTopic) AWSIotMqttManager.this.topicListeners.get(str2)) != null && aWSIotMqttTopic.getCallback() != null) {
                        aWSIotMqttTopic.getCallback().onMessageArrived(str, payload);
                    }
                }
            }

            @Override // org.eclipse.paho.client.mqttv3.MqttCallback
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                AWSIotMqttManager.LOGGER.info("delivery is complete");
                if (iMqttDeliveryToken != null) {
                    Object userContext = iMqttDeliveryToken.getUserContext();
                    if (userContext instanceof PublishMessageUserData) {
                        PublishMessageUserData publishMessageUserData = (PublishMessageUserData) userContext;
                        AWSIotMqttManager.this.notifyPublishResult(publishMessageUserData.getUserCallback(), AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus.Success, publishMessageUserData.getUserData(), null);
                    }
                }
            }
        });
    }

    boolean isReadyToPublish() {
        MqttAsyncClient mqttAsyncClient = this.mqttClient;
        return mqttAsyncClient != null && mqttAsyncClient.isConnected();
    }

    void userConnectionCallback() {
        userConnectionCallback(null);
    }

    /* JADX INFO: renamed from: com.amazonaws.mobileconnectors.iot.AWSIotMqttManager$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttManager$AuthenticationMode;
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState;

        static {
            int[] iArr = new int[MqttManagerConnectionState.values().length];
            $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState = iArr;
            try {
                iArr[MqttManagerConnectionState.Connected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Connecting.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Reconnecting.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[MqttManagerConnectionState.Disconnected.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AuthenticationMode.values().length];
            $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttManager$AuthenticationMode = iArr2;
            try {
                iArr2[AuthenticationMode.KEYSTORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttManager$AuthenticationMode[AuthenticationMode.IAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttManager$AuthenticationMode[AuthenticationMode.CUSTOM_AUTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$AWSIotMqttManager$AuthenticationMode[AuthenticationMode.USERNAME_PASSWORD.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    void userConnectionCallback(Throwable th) {
        if (this.userStatusCallback != null) {
            int i = AnonymousClass8.$SwitchMap$com$amazonaws$mobileconnectors$iot$MqttManagerConnectionState[this.connectionState.ordinal()];
            if (i == 1) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected, th);
                return;
            }
            if (i == 2) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connecting, th);
            } else if (i == 3) {
                this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting, th);
            } else {
                if (i == 4) {
                    this.userStatusCallback.onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost, th);
                    return;
                }
                throw new IllegalStateException("Unknown connection state.");
            }
        }
    }

    void notifyPublishResult(AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, AWSIotMqttMessageDeliveryCallback.MessageDeliveryStatus messageDeliveryStatus, Object obj, RuntimeException runtimeException) {
        if (aWSIotMqttMessageDeliveryCallback != null) {
            aWSIotMqttMessageDeliveryCallback.statusChanged(messageDeliveryStatus, obj);
        } else if (runtimeException != null) {
            throw runtimeException;
        }
    }

    static boolean isTopicMatch(String str, String str2) {
        String[] strArrSplit = str.split("/");
        String[] strArrSplit2 = str2.split("/");
        if (strArrSplit.length > strArrSplit2.length) {
            return false;
        }
        for (int i = 0; i < strArrSplit.length; i++) {
            String str3 = strArrSplit[i];
            String str4 = strArrSplit2[i];
            if ("#".equals(str3)) {
                return true;
            }
            if (!MqttTopic.SINGLE_LEVEL_WILDCARD.equals(str3) && !str3.equals(str4)) {
                return false;
            }
        }
        return strArrSplit.length == strArrSplit2.length;
    }

    public static final class ClientId {
        private final String value;

        private ClientId(String str) {
            this.value = str;
        }

        public static ClientId fromString(String str) {
            return new ClientId(str);
        }

        public String getValue() {
            return this.value;
        }
    }

    public static final class Endpoint {
        private final String value;

        private Endpoint(String str) {
            this.value = str;
        }

        public static Endpoint fromString(String str) {
            return new Endpoint(str);
        }

        public String getValue() {
            return this.value;
        }
    }

    public boolean getSessionPresent() {
        return this.sessionPresent;
    }
}
