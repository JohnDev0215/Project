package android.net;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.android.internal.util.AsyncChannel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public abstract class NetworkAgent {
    private static final int BASE = 528384;
    private static final long BW_REFRESH_MIN_WIN_MS = 500;
    public static final int CMD_ADD_KEEPALIVE_PACKET_FILTER = 528400;
    public static final int CMD_PREVENT_AUTOMATIC_RECONNECT = 528399;
    public static final int CMD_REMOVE_KEEPALIVE_PACKET_FILTER = 528401;
    public static final int CMD_REPORT_NETWORK_STATUS = 528391;
    public static final int CMD_REQUEST_BANDWIDTH_UPDATE = 528394;
    public static final int CMD_SAVE_ACCEPT_UNVALIDATED = 528393;
    public static final int CMD_SET_SIGNAL_STRENGTH_THRESHOLDS = 528398;
    public static final int CMD_START_SOCKET_KEEPALIVE = 528395;
    public static final int CMD_STOP_SOCKET_KEEPALIVE = 528396;
    public static final int CMD_SUSPECT_BAD = 528384;
    private static final boolean DBG = true;
    public static final int EVENT_NETWORK_CAPABILITIES_CHANGED = 528386;
    public static final int EVENT_NETWORK_INFO_CHANGED = 528385;
    public static final int EVENT_NETWORK_PROPERTIES_CHANGED = 528387;
    public static final int EVENT_NETWORK_SCORE_CHANGED = 528388;
    public static final int EVENT_SET_EXPLICITLY_SELECTED = 528392;
    public static final int EVENT_SOCKET_KEEPALIVE = 528397;
    public static final int INVALID_NETWORK = 2;
    public static String REDIRECT_URL_KEY = "redirect URL";
    public static final int VALIDATION_STATUS_NOT_VALID = 2;
    public static final int VALIDATION_STATUS_VALID = 1;
    public static final int VALID_NETWORK = 1;
    private static final boolean VDBG = false;
    public static final int WIFI_BASE_SCORE = 60;
    private final String LOG_TAG;
    private volatile AsyncChannel mAsyncChannel;
    private AtomicBoolean mBandwidthUpdatePending;
    private boolean mBandwidthUpdateScheduled;
    private final Handler mHandler;
    private volatile InitialConfiguration mInitialConfiguration;
    private final boolean mIsLegacy;
    private volatile long mLastBwRefreshTime;
    private volatile Network mNetwork;
    private NetworkInfo mNetworkInfo;
    private final ArrayList<Message> mPreConnectedQueue;
    private final Object mRegisterLock;
    public final int providerId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidationStatus {
    }

    protected void addKeepalivePacketFilter(Message message) {
    }

    protected void networkStatus(int i, String str) {
    }

    protected void pollLceData() {
    }

    protected void preventAutomaticReconnect() {
    }

    protected void removeKeepalivePacketFilter(Message message) {
    }

    protected void saveAcceptUnvalidated(boolean z) {
    }

    protected void setSignalStrengthThresholds(int[] iArr) {
    }

    protected void unwanted() {
    }

    public NetworkAgent(Looper looper, Context context, String str, NetworkInfo networkInfo, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i) {
        this(looper, context, str, networkInfo, networkCapabilities, linkProperties, i, null, -1);
    }

    public NetworkAgent(Looper looper, Context context, String str, NetworkInfo networkInfo, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i, NetworkAgentConfig networkAgentConfig) {
        this(looper, context, str, networkInfo, networkCapabilities, linkProperties, i, networkAgentConfig, -1);
    }

    public NetworkAgent(Looper looper, Context context, String str, NetworkInfo networkInfo, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i, int i2) {
        this(looper, context, str, networkInfo, networkCapabilities, linkProperties, i, null, i2);
    }

    public NetworkAgent(Looper looper, Context context, String str, NetworkInfo networkInfo, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i, NetworkAgentConfig networkAgentConfig, int i2) {
        this(looper, context, str, networkCapabilities, linkProperties, i, networkAgentConfig, i2, networkInfo, true);
        register();
    }

    private static NetworkInfo getLegacyNetworkInfo(NetworkAgentConfig networkAgentConfig) {
        NetworkInfo networkInfo = new NetworkInfo(networkAgentConfig.legacyType, 0, networkAgentConfig.legacyTypeName, "");
        networkInfo.setIsAvailable(true);
        networkInfo.setExtraInfo(networkAgentConfig.getLegacyExtraInfo());
        return networkInfo;
    }

    public NetworkAgent(Context context, Looper looper, String str, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i, NetworkAgentConfig networkAgentConfig, NetworkProvider networkProvider) {
        this(looper, context, str, networkCapabilities, linkProperties, i, networkAgentConfig, networkProvider == null ? -1 : networkProvider.getProviderId(), getLegacyNetworkInfo(networkAgentConfig), false);
    }

    private static class InitialConfiguration {
        public final NetworkCapabilities capabilities;
        public final NetworkAgentConfig config;
        public final Context context;
        public final NetworkInfo info;
        public final LinkProperties properties;
        public final int score;

        InitialConfiguration(Context context, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i, NetworkAgentConfig networkAgentConfig, NetworkInfo networkInfo) {
            this.context = context;
            this.capabilities = networkCapabilities;
            this.properties = linkProperties;
            this.score = i;
            this.config = networkAgentConfig;
            this.info = networkInfo;
        }
    }

    private NetworkAgent(Looper looper, Context context, String str, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, int i, NetworkAgentConfig networkAgentConfig, int i2, NetworkInfo networkInfo, boolean z) {
        this.mPreConnectedQueue = new ArrayList<>();
        this.mLastBwRefreshTime = 0L;
        this.mBandwidthUpdateScheduled = false;
        this.mBandwidthUpdatePending = new AtomicBoolean(false);
        this.mRegisterLock = new Object();
        this.mHandler = new NetworkAgentHandler(looper);
        this.LOG_TAG = str;
        this.mIsLegacy = z;
        this.mNetworkInfo = new NetworkInfo(networkInfo);
        this.providerId = i2;
        if (networkInfo == null || networkCapabilities == null || linkProperties == null) {
            throw new IllegalArgumentException();
        }
        this.mInitialConfiguration = new InitialConfiguration(context, new NetworkCapabilities(networkCapabilities), new LinkProperties(linkProperties), i, networkAgentConfig, networkInfo);
    }

    private class NetworkAgentHandler extends Handler {
        NetworkAgentHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Uri uri = null;
            switch (message.what) {
                case AsyncChannel.CMD_CHANNEL_FULL_CONNECTION /* 69633 */:
                    if (NetworkAgent.this.mAsyncChannel != null) {
                        NetworkAgent.this.log("Received new connection while already connected!");
                        return;
                    }
                    AsyncChannel asyncChannel = new AsyncChannel();
                    asyncChannel.connected(null, this, message.replyTo);
                    asyncChannel.replyToMessage(message, AsyncChannel.CMD_CHANNEL_FULLY_CONNECTED, 0);
                    synchronized (NetworkAgent.this.mPreConnectedQueue) {
                        NetworkAgent.this.mAsyncChannel = asyncChannel;
                        Iterator it = NetworkAgent.this.mPreConnectedQueue.iterator();
                        while (it.hasNext()) {
                            asyncChannel.sendMessage((Message) it.next());
                        }
                        NetworkAgent.this.mPreConnectedQueue.clear();
                        break;
                    }
                    return;
                case AsyncChannel.CMD_CHANNEL_DISCONNECT /* 69635 */:
                    if (NetworkAgent.this.mAsyncChannel != null) {
                        NetworkAgent.this.mAsyncChannel.disconnect();
                        return;
                    }
                    return;
                case AsyncChannel.CMD_CHANNEL_DISCONNECTED /* 69636 */:
                    NetworkAgent.this.log("NetworkAgent channel lost");
                    NetworkAgent.this.onNetworkUnwanted();
                    synchronized (NetworkAgent.this.mPreConnectedQueue) {
                        NetworkAgent.this.mAsyncChannel = null;
                        break;
                    }
                    return;
                case 528384:
                    NetworkAgent.this.log("Unhandled Message " + message);
                    return;
                case NetworkAgent.CMD_REPORT_NETWORK_STATUS /* 528391 */:
                    String string = ((Bundle) message.obj).getString(NetworkAgent.REDIRECT_URL_KEY);
                    if (string != null) {
                        try {
                            uri = Uri.parse(string);
                        } catch (Exception e) {
                            Log.wtf(NetworkAgent.this.LOG_TAG, "Surprising URI : " + string, e);
                        }
                        break;
                    }
                    NetworkAgent.this.onValidationStatus(message.arg1, uri);
                    return;
                case NetworkAgent.CMD_SAVE_ACCEPT_UNVALIDATED /* 528393 */:
                    NetworkAgent.this.onSaveAcceptUnvalidated(message.arg1 != 0);
                    return;
                case NetworkAgent.CMD_REQUEST_BANDWIDTH_UPDATE /* 528394 */:
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis >= NetworkAgent.this.mLastBwRefreshTime + NetworkAgent.BW_REFRESH_MIN_WIN_MS) {
                        NetworkAgent.this.mBandwidthUpdateScheduled = false;
                        if (NetworkAgent.this.mBandwidthUpdatePending.getAndSet(true)) {
                            return;
                        }
                        NetworkAgent.this.onBandwidthUpdateRequested();
                        return;
                    }
                    if (NetworkAgent.this.mBandwidthUpdateScheduled) {
                        return;
                    }
                    NetworkAgent.this.mBandwidthUpdateScheduled = sendEmptyMessageDelayed(NetworkAgent.CMD_REQUEST_BANDWIDTH_UPDATE, ((NetworkAgent.this.mLastBwRefreshTime + NetworkAgent.BW_REFRESH_MIN_WIN_MS) - jCurrentTimeMillis) + 1);
                    return;
                case NetworkAgent.CMD_START_SOCKET_KEEPALIVE /* 528395 */:
                    NetworkAgent.this.onStartSocketKeepalive(message.arg1, Duration.ofSeconds(message.arg2), (KeepalivePacketData) message.obj);
                    return;
                case NetworkAgent.CMD_STOP_SOCKET_KEEPALIVE /* 528396 */:
                    NetworkAgent.this.onStopSocketKeepalive(message.arg1);
                    return;
                case NetworkAgent.CMD_SET_SIGNAL_STRENGTH_THRESHOLDS /* 528398 */:
                    ArrayList<Integer> integerArrayList = ((Bundle) message.obj).getIntegerArrayList("thresholds");
                    int size = integerArrayList != null ? integerArrayList.size() : 0;
                    int[] iArr = new int[size];
                    for (int i = 0; i < size; i++) {
                        iArr[i] = integerArrayList.get(i).intValue();
                    }
                    NetworkAgent.this.onSignalStrengthThresholdsUpdated(iArr);
                    return;
                case NetworkAgent.CMD_PREVENT_AUTOMATIC_RECONNECT /* 528399 */:
                    NetworkAgent.this.onAutomaticReconnectDisabled();
                    return;
                case NetworkAgent.CMD_ADD_KEEPALIVE_PACKET_FILTER /* 528400 */:
                    NetworkAgent.this.onAddKeepalivePacketFilter(message.arg1, (KeepalivePacketData) message.obj);
                    return;
                case NetworkAgent.CMD_REMOVE_KEEPALIVE_PACKET_FILTER /* 528401 */:
                    NetworkAgent.this.onRemoveKeepalivePacketFilter(message.arg1);
                    return;
                default:
                    return;
            }
        }
    }

    public Network register() {
        synchronized (this.mRegisterLock) {
            if (this.mNetwork != null) {
                throw new IllegalStateException("Agent already registered");
            }
            this.mNetwork = ((ConnectivityManager) this.mInitialConfiguration.context.getSystemService("connectivity")).registerNetworkAgent(new Messenger(this.mHandler), new NetworkInfo(this.mInitialConfiguration.info), this.mInitialConfiguration.properties, this.mInitialConfiguration.capabilities, this.mInitialConfiguration.score, this.mInitialConfiguration.config, this.providerId);
            this.mInitialConfiguration = null;
        }
        return this.mNetwork;
    }

    public Messenger registerForTest(Network network) {
        log("Registering NetworkAgent for test");
        synchronized (this.mRegisterLock) {
            this.mNetwork = network;
            this.mInitialConfiguration = null;
        }
        return new Messenger(this.mHandler);
    }

    public boolean waitForIdle(long j) {
        final ConditionVariable conditionVariable = new ConditionVariable(false);
        this.mHandler.post(new Runnable() { // from class: android.net.-$$Lambda$xEDVsWySjOhZCU-CTVGu6ziJ2xc
            @Override // java.lang.Runnable
            public final void run() {
                conditionVariable.open();
            }
        });
        return conditionVariable.block(j);
    }

    public Network getNetwork() {
        return this.mNetwork;
    }

    private void queueOrSendMessage(int i, Object obj) {
        queueOrSendMessage(i, 0, 0, obj);
    }

    private void queueOrSendMessage(int i, int i2, int i3) {
        queueOrSendMessage(i, i2, i3, null);
    }

    private void queueOrSendMessage(int i, int i2, int i3, Object obj) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.arg2 = i3;
        messageObtain.obj = obj;
        queueOrSendMessage(messageObtain);
    }

    private void queueOrSendMessage(Message message) {
        synchronized (this.mPreConnectedQueue) {
            if (this.mAsyncChannel != null) {
                this.mAsyncChannel.sendMessage(message);
            } else {
                this.mPreConnectedQueue.add(message);
            }
        }
    }

    public final void sendLinkProperties(LinkProperties linkProperties) {
        Objects.requireNonNull(linkProperties);
        queueOrSendMessage(EVENT_NETWORK_PROPERTIES_CHANGED, new LinkProperties(linkProperties));
    }

    public void markConnected() {
        if (this.mIsLegacy) {
            throw new UnsupportedOperationException("Legacy agents can't call markConnected.");
        }
        this.mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.CONNECTED, null, this.mNetworkInfo.getExtraInfo());
        queueOrSendMessage(EVENT_NETWORK_INFO_CHANGED, this.mNetworkInfo);
    }

    public void unregister() {
        if (this.mIsLegacy) {
            throw new UnsupportedOperationException("Legacy agents can't call unregister.");
        }
        this.mNetworkInfo.setDetailedState(NetworkInfo.DetailedState.DISCONNECTED, null, null);
        queueOrSendMessage(EVENT_NETWORK_INFO_CHANGED, this.mNetworkInfo);
    }

    @Deprecated
    public void setLegacySubtype(int i, String str) {
        if (this.mIsLegacy) {
            throw new UnsupportedOperationException("Legacy agents can't call setLegacySubtype.");
        }
        this.mNetworkInfo.setSubtype(i, str);
        queueOrSendMessage(EVENT_NETWORK_INFO_CHANGED, this.mNetworkInfo);
    }

    @Deprecated
    public void setLegacyExtraInfo(String str) {
        if (this.mIsLegacy) {
            throw new UnsupportedOperationException("Legacy agents can't call setLegacyExtraInfo.");
        }
        this.mNetworkInfo.setExtraInfo(str);
        queueOrSendMessage(EVENT_NETWORK_INFO_CHANGED, this.mNetworkInfo);
    }

    public final void sendNetworkInfo(NetworkInfo networkInfo) {
        if (!this.mIsLegacy) {
            throw new UnsupportedOperationException("Only legacy agents can call sendNetworkInfo.");
        }
        queueOrSendMessage(EVENT_NETWORK_INFO_CHANGED, new NetworkInfo(networkInfo));
    }

    public final void sendNetworkCapabilities(NetworkCapabilities networkCapabilities) {
        Objects.requireNonNull(networkCapabilities);
        this.mBandwidthUpdatePending.set(false);
        this.mLastBwRefreshTime = System.currentTimeMillis();
        queueOrSendMessage(EVENT_NETWORK_CAPABILITIES_CHANGED, new NetworkCapabilities(networkCapabilities));
    }

    public final void sendNetworkScore(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Score must be >= 0");
        }
        queueOrSendMessage(EVENT_NETWORK_SCORE_CHANGED, i, 0);
    }

    public void explicitlySelected(boolean z) {
        explicitlySelected(true, z);
    }

    public void explicitlySelected(boolean z, boolean z2) {
        queueOrSendMessage(EVENT_SET_EXPLICITLY_SELECTED, z ? 1 : 0, z2 ? 1 : 0);
    }

    public void onNetworkUnwanted() {
        unwanted();
    }

    public void onBandwidthUpdateRequested() {
        pollLceData();
    }

    public void onValidationStatus(int i, Uri uri) {
        networkStatus(i, uri == null ? "" : uri.toString());
    }

    public void onSaveAcceptUnvalidated(boolean z) {
        saveAcceptUnvalidated(z);
    }

    public void onStartSocketKeepalive(int i, Duration duration, KeepalivePacketData keepalivePacketData) {
        long seconds = duration.getSeconds();
        if (seconds < 10 || seconds > 3600) {
            throw new IllegalArgumentException("Interval needs to be comprised between 10 and 3600 but was " + seconds);
        }
        Message messageObtainMessage = this.mHandler.obtainMessage(CMD_START_SOCKET_KEEPALIVE, i, (int) seconds, keepalivePacketData);
        startSocketKeepalive(messageObtainMessage);
        messageObtainMessage.recycle();
    }

    protected void startSocketKeepalive(Message message) {
        onSocketKeepaliveEvent(message.arg1, -30);
    }

    public void onStopSocketKeepalive(int i) {
        Message messageObtainMessage = this.mHandler.obtainMessage(CMD_STOP_SOCKET_KEEPALIVE, i, 0, null);
        stopSocketKeepalive(messageObtainMessage);
        messageObtainMessage.recycle();
    }

    protected void stopSocketKeepalive(Message message) {
        onSocketKeepaliveEvent(message.arg1, -30);
    }

    public final void sendSocketKeepaliveEvent(int i, int i2) {
        queueOrSendMessage(EVENT_SOCKET_KEEPALIVE, i, i2);
    }

    public void onSocketKeepaliveEvent(int i, int i2) {
        sendSocketKeepaliveEvent(i, i2);
    }

    public void onAddKeepalivePacketFilter(int i, KeepalivePacketData keepalivePacketData) {
        Message messageObtainMessage = this.mHandler.obtainMessage(CMD_ADD_KEEPALIVE_PACKET_FILTER, i, 0, keepalivePacketData);
        addKeepalivePacketFilter(messageObtainMessage);
        messageObtainMessage.recycle();
    }

    public void onRemoveKeepalivePacketFilter(int i) {
        Message messageObtainMessage = this.mHandler.obtainMessage(CMD_REMOVE_KEEPALIVE_PACKET_FILTER, i, 0, null);
        removeKeepalivePacketFilter(messageObtainMessage);
        messageObtainMessage.recycle();
    }

    public void onSignalStrengthThresholdsUpdated(int[] iArr) {
        setSignalStrengthThresholds(iArr);
    }

    public void onAutomaticReconnectDisabled() {
        preventAutomaticReconnect();
    }

    protected void log(String str) {
        Log.d(this.LOG_TAG, "NetworkAgent: " + str);
    }
}
