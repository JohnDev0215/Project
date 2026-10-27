package com.android.server.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.DataStallReportParcelable;
import android.net.DnsResolver;
import android.net.INetworkMonitorCallbacks;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkTestResultParcelable;
import android.net.ProxyInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.net.captiveportal.CapportApiProbeResult;
import android.net.captiveportal.CaptivePortalProbeResult;
import android.net.captiveportal.CaptivePortalProbeSpec;
import android.net.metrics.IpConnectivityLog;
import android.net.metrics.NetworkEvent;
import android.net.metrics.ValidationProbeEvent;
import android.net.networkstack.util.RingBufferIndices;
import android.net.networkstack.util.State;
import android.net.networkstack.util.StateMachine;
import android.net.shared.NetworkMonitorUtils;
import android.net.shared.PrivateDnsConfig;
import android.net.util.DataStallUtils;
import android.net.util.NetworkStackUtils;
import android.net.util.SharedLog;
import android.net.util.Stopwatch;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.stats.connectivity.ProbeResult;
import android.stats.connectivity.ProbeType;
import android.telephony.CellIdentityNr;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.android.networkstack.NetworkStackNotifier;
import com.android.networkstack.R;
import com.android.networkstack.apishim.CaptivePortalDataShimImpl;
import com.android.networkstack.apishim.NetworkInformationShimImpl;
import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.apishim.common.UnsupportedApiLevelException;
import com.android.networkstack.metrics.DataStallDetectionStats;
import com.android.networkstack.metrics.DataStallStatsUtils;
import com.android.networkstack.metrics.NetworkValidationMetrics;
import com.android.networkstack.netlink.TcpSocketTracker;
import com.android.networkstack.util.DnsUtils;
import com.android.server.NetworkStackService;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NetworkMonitor extends StateMachine {
    static final String CONFIG_CAPTIVE_PORTAL_DNS_PROBE_TIMEOUT = "captive_portal_dns_probe_timeout";
    static final int INITIAL_REEVALUATE_DELAY_MS = 1000;
    static final int MAX_PROBE_THREAD_POOL_SIZE = 5;
    private static final String TAG = "NetworkMonitor";
    protected static final SparseArray<MccMncOverrideInfo> sCarrierIdToMccMnc;
    private boolean mAcceptPartialConnectivity;
    private final INetworkMonitorCallbacks mCallback;
    private final int mCallbackVersion;
    private final CaptivePortalProbeSpec[] mCaptivePortalFallbackSpecs;
    private final URL[] mCaptivePortalFallbackUrls;
    private final URL[] mCaptivePortalHttpUrls;
    private final URL[] mCaptivePortalHttpsUrls;
    private final State mCaptivePortalState;
    private final String mCaptivePortalUserAgent;
    private final Network mCleartextDnsNetwork;
    private final ConnectivityManager mCm;
    private final int mConsecutiveDnsTimeoutThreshold;
    private final Context mContext;
    private final int mDataStallEvaluationType;
    private final int mDataStallMinEvaluateTime;
    private int mDataStallTypeToCollect;
    private final int mDataStallValidDnsTimeThreshold;
    private final State mDefaultState;
    private final Dependencies mDependencies;
    private final DnsStallDetector mDnsStallDetector;
    private boolean mDontDisplaySigninNotification;
    private int mEvaluateAttempts;
    private final State mEvaluatingBandwidthState;
    private final int mEvaluatingBandwidthTimeoutMs;
    private final String mEvaluatingBandwidthUrl;
    private final State mEvaluatingPrivateDnsState;
    private final State mEvaluatingState;
    private final EvaluationState mEvaluationState;
    private final Stopwatch mEvaluationTimer;
    protected boolean mIsBandwidthCheckPassedOrIgnored;
    protected boolean mIsCaptivePortalCheckEnabled;
    private CaptivePortalProbeResult mLastPortalProbeResult;
    private long mLastProbeTime;
    private CustomIntentReceiver mLaunchCaptivePortalAppBroadcastReceiver;
    private LinkProperties mLinkProperties;
    private final int mMaxRetryTimerMs;
    private final State mMaybeNotifyState;
    private final boolean mMetricsEnabled;
    private final IpConnectivityLog mMetricsLog;
    private final Network mNetwork;
    private NetworkCapabilities mNetworkCapabilities;
    private final NetworkValidationMetrics mNetworkValidationMetrics;
    private final AtomicInteger mNextEvaluatingBandwidthThreadId;
    private int mNextFallbackUrlIndex;
    private final NetworkStackNotifier mNotifier;
    private String mPrivateDnsProviderHostname;
    private final boolean mPrivateIpNoInternetEnabled;
    private volatile int mProbeToken;
    private final State mProbingState;
    private final Random mRandom;
    private int mReevaluateDelayMs;
    private int mReevaluateToken;
    private final TcpSocketTracker mTcpTracker;
    private final TelephonyManager mTelephonyManager;
    private int mUidResponsibleForReeval;
    private boolean mUseHttps;
    private boolean mUserDoesNotWant;
    private final State mValidatedState;
    private final SharedLog mValidationLogs;
    private int mValidations;
    private final State mWaitingForNextProbeState;
    private final WifiManager mWifiManager;
    private static final boolean VDBG_STALL = Log.isLoggable(NetworkMonitor.class.getSimpleName(), 3);
    private static final long TEST_URL_EXPIRATION_MS = TimeUnit.MINUTES.toMillis(10);

    public interface DnsLogFunc {
        void log(String str);
    }

    static /* synthetic */ int access$3208(NetworkMonitor networkMonitor) {
        int i = networkMonitor.mValidations;
        networkMonitor.mValidations = i + 1;
        return i;
    }

    static /* synthetic */ int access$5004(NetworkMonitor networkMonitor) {
        int i = networkMonitor.mReevaluateToken + 1;
        networkMonitor.mReevaluateToken = i;
        return i;
    }

    static /* synthetic */ int access$5128(NetworkMonitor networkMonitor, int i) {
        int i2 = networkMonitor.mReevaluateDelayMs * i;
        networkMonitor.mReevaluateDelayMs = i2;
        return i2;
    }

    static /* synthetic */ int access$5208(NetworkMonitor networkMonitor) {
        int i = networkMonitor.mEvaluateAttempts;
        networkMonitor.mEvaluateAttempts = i + 1;
        return i;
    }

    static /* synthetic */ int access$6604(NetworkMonitor networkMonitor) {
        int i = networkMonitor.mProbeToken + 1;
        networkMonitor.mProbeToken = i;
        return i;
    }

    static {
        SparseArray<MccMncOverrideInfo> sparseArray = new SparseArray<>();
        sCarrierIdToMccMnc = sparseArray;
        sparseArray.put(1854, new MccMncOverrideInfo(460, 3));
    }

    enum EvaluationResult {
        VALIDATED(true),
        CAPTIVE_PORTAL(false);

        final boolean mIsValidated;

        EvaluationResult(boolean z) {
            this.mIsValidated = z;
        }
    }

    enum ValidationStage {
        FIRST_VALIDATION(true),
        REVALIDATION(false);

        final boolean mIsFirstValidation;

        ValidationStage(boolean z) {
            this.mIsFirstValidation = z;
        }
    }

    protected static final class MccMncOverrideInfo {
        public final int mcc;
        public final int mnc;

        MccMncOverrideInfo(int i, int i2) {
            this.mcc = i;
            this.mnc = i2;
        }
    }

    private int getCallbackVersion(INetworkMonitorCallbacks iNetworkMonitorCallbacks) {
        int interfaceVersion;
        try {
            interfaceVersion = iNetworkMonitorCallbacks.getInterfaceVersion();
        } catch (RemoteException unused) {
            interfaceVersion = 0;
        }
        if (Build.VERSION.SDK_INT == 29 && Build.VERSION.CODENAME.equals("REL") && interfaceVersion == 10000) {
            return 0;
        }
        return interfaceVersion;
    }

    public NetworkMonitor(Context context, INetworkMonitorCallbacks iNetworkMonitorCallbacks, Network network, SharedLog sharedLog, NetworkStackService.NetworkStackServiceManager networkStackServiceManager) {
        this(context, iNetworkMonitorCallbacks, network, new IpConnectivityLog(), sharedLog, networkStackServiceManager, Dependencies.DEFAULT, getTcpSocketTrackerOrNull(context, network));
    }

    public NetworkMonitor(Context context, INetworkMonitorCallbacks iNetworkMonitorCallbacks, Network network, IpConnectivityLog ipConnectivityLog, SharedLog sharedLog, NetworkStackService.NetworkStackServiceManager networkStackServiceManager, Dependencies dependencies, TcpSocketTracker tcpSocketTracker) {
        super(TAG + "/" + network.toString());
        this.mReevaluateToken = 0;
        this.mUidResponsibleForReeval = -1;
        this.mPrivateDnsProviderHostname = "";
        this.mNextEvaluatingBandwidthThreadId = new AtomicInteger(1);
        this.mValidations = 0;
        this.mUserDoesNotWant = false;
        this.mDontDisplaySigninNotification = false;
        this.mIsBandwidthCheckPassedOrIgnored = false;
        this.mDefaultState = new DefaultState();
        this.mValidatedState = new ValidatedState();
        this.mMaybeNotifyState = new MaybeNotifyState();
        this.mEvaluatingState = new EvaluatingState();
        this.mCaptivePortalState = new CaptivePortalState();
        this.mEvaluatingPrivateDnsState = new EvaluatingPrivateDnsState();
        this.mProbingState = new ProbingState();
        this.mWaitingForNextProbeState = new WaitingForNextProbeState();
        this.mEvaluatingBandwidthState = new EvaluatingBandwidthState();
        this.mLaunchCaptivePortalAppBroadcastReceiver = null;
        this.mEvaluationTimer = new Stopwatch();
        this.mLastPortalProbeResult = CaptivePortalProbeResult.failed(0);
        this.mNextFallbackUrlIndex = 0;
        this.mReevaluateDelayMs = INITIAL_REEVALUATE_DELAY_MS;
        this.mEvaluateAttempts = 0;
        this.mProbeToken = 0;
        this.mAcceptPartialConnectivity = false;
        this.mEvaluationState = new EvaluationState();
        this.mNetworkValidationMetrics = new NetworkValidationMetrics();
        setDbg(true);
        this.mContext = context;
        this.mMetricsLog = ipConnectivityLog;
        this.mValidationLogs = sharedLog;
        this.mCallback = iNetworkMonitorCallbacks;
        this.mCallbackVersion = getCallbackVersion(iNetworkMonitorCallbacks);
        this.mDependencies = dependencies;
        this.mNetwork = network;
        this.mCleartextDnsNetwork = dependencies.getPrivateDnsBypassNetwork(network);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mCm = (ConnectivityManager) context.getSystemService("connectivity");
        this.mNotifier = networkStackServiceManager.getNotifier();
        addState(this.mDefaultState);
        addState(this.mMaybeNotifyState, this.mDefaultState);
        addState(this.mEvaluatingState, this.mMaybeNotifyState);
        addState(this.mProbingState, this.mEvaluatingState);
        addState(this.mWaitingForNextProbeState, this.mEvaluatingState);
        addState(this.mCaptivePortalState, this.mMaybeNotifyState);
        addState(this.mEvaluatingPrivateDnsState, this.mDefaultState);
        addState(this.mEvaluatingBandwidthState, this.mDefaultState);
        addState(this.mValidatedState, this.mDefaultState);
        setInitialState(this.mDefaultState);
        this.mIsCaptivePortalCheckEnabled = getIsCaptivePortalCheckEnabled();
        this.mPrivateIpNoInternetEnabled = getIsPrivateIpNoInternetEnabled();
        this.mMetricsEnabled = dependencies.isFeatureEnabled(context, "connectivity", "validation_metrics_version", true);
        this.mUseHttps = getUseHttpsValidation();
        this.mCaptivePortalUserAgent = getCaptivePortalUserAgent();
        this.mCaptivePortalHttpsUrls = makeCaptivePortalHttpsUrls();
        this.mCaptivePortalHttpUrls = makeCaptivePortalHttpUrls();
        this.mCaptivePortalFallbackUrls = makeCaptivePortalFallbackUrls();
        this.mCaptivePortalFallbackSpecs = makeCaptivePortalFallbackProbeSpecs();
        this.mRandom = dependencies.getRandom();
        this.mConsecutiveDnsTimeoutThreshold = getConsecutiveDnsTimeoutThreshold();
        this.mDataStallMinEvaluateTime = getDataStallMinEvaluateTime();
        this.mDataStallValidDnsTimeThreshold = getDataStallValidDnsTimeThreshold();
        int dataStallEvaluationType = getDataStallEvaluationType();
        this.mDataStallEvaluationType = dataStallEvaluationType;
        this.mDnsStallDetector = initDnsStallDetectorIfRequired(dataStallEvaluationType, this.mConsecutiveDnsTimeoutThreshold);
        this.mTcpTracker = tcpSocketTracker;
        this.mEvaluatingBandwidthUrl = getResStringConfig(this.mContext, R.string.config_evaluating_bandwidth_url, null);
        this.mMaxRetryTimerMs = getResIntConfig(this.mContext, R.integer.config_evaluating_bandwidth_max_retry_timer_ms, 600000);
        this.mEvaluatingBandwidthTimeoutMs = getResIntConfig(this.mContext, R.integer.config_evaluating_bandwidth_timeout_ms, 10000);
        this.mLinkProperties = new LinkProperties();
        this.mNetworkCapabilities = new NetworkCapabilities(null);
    }

    public void setAcceptPartialConnectivity() {
        sendMessage(18);
    }

    public void forceReevaluation(int i) {
        sendMessage(8, i, 0);
    }

    public void notifyDnsResponse(int i) {
        sendMessage(17, i);
    }

    public void notifyPrivateDnsSettingsChanged(PrivateDnsConfig privateDnsConfig) {
        removeMessages(13);
        sendMessage(13, privateDnsConfig);
    }

    public void notifyNetworkConnected(LinkProperties linkProperties, NetworkCapabilities networkCapabilities) {
        sendMessage(1, new Pair(new LinkProperties(linkProperties), new NetworkCapabilities(networkCapabilities)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectedNetworkAttributes(Message message) {
        Pair pair = (Pair) message.obj;
        this.mLinkProperties = (LinkProperties) pair.first;
        this.mNetworkCapabilities = (NetworkCapabilities) pair.second;
    }

    public void notifyNetworkDisconnected() {
        sendMessage(7);
    }

    public void notifyLinkPropertiesChanged(LinkProperties linkProperties) {
        sendMessage(19, new LinkProperties(linkProperties));
    }

    public void notifyNetworkCapabilitiesChanged(NetworkCapabilities networkCapabilities) {
        sendMessage(20, new NetworkCapabilities(networkCapabilities));
    }

    public void launchCaptivePortalApp() {
        sendMessage(11);
    }

    public void notifyCaptivePortalAppFinished(int i) {
        sendMessage(9, i);
    }

    @Override // android.net.networkstack.util.StateMachine
    protected void log(String str) {
        Log.d(TAG + "/" + this.mCleartextDnsNetwork.toString(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validationLog(int i, Object obj, String str) {
        validationLog(String.format("%s %s %s", ValidationProbeEvent.getProbeName(i), obj, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validationLog(String str) {
        log(str);
        this.mValidationLogs.log(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValidationStage validationStage() {
        return this.mValidations == 0 ? ValidationStage.FIRST_VALIDATION : ValidationStage.REVALIDATION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidationRequired() {
        return NetworkMonitorUtils.isValidationRequired(this.mNetworkCapabilities);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPrivateDnsValidationRequired() {
        return NetworkMonitorUtils.isPrivateDnsValidationRequired(this.mNetworkCapabilities);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNetworkTested(NetworkTestResultParcelable networkTestResultParcelable) {
        try {
            if (this.mCallbackVersion <= 5) {
                this.mCallback.notifyNetworkTested(getLegacyTestResult(networkTestResultParcelable.result, networkTestResultParcelable.probesSucceeded), networkTestResultParcelable.redirectUrl);
            } else {
                this.mCallback.notifyNetworkTestedWithExtras(networkTestResultParcelable);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error sending network test result", e);
        }
    }

    protected int getLegacyTestResult(int i, int i2) {
        if (this.mCallbackVersion >= 3) {
            return i | i2;
        }
        if ((i & 1) != 0) {
            return 0;
        }
        return (i & 2) != 0 ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyProbeStatusChanged(int i, int i2) {
        try {
            this.mCallback.notifyProbeStatusChanged(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Error sending probe status", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProvisioningNotification(String str) {
        try {
            this.mCallback.showProvisioningNotification(str, this.mContext.getPackageName());
        } catch (RemoteException e) {
            Log.e(TAG, "Error showing provisioning notification", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProvisioningNotification() {
        try {
            this.mCallback.hideProvisioningNotification();
        } catch (RemoteException e) {
            Log.e(TAG, "Error hiding provisioning notification", e);
        }
    }

    private void notifyDataStallSuspected(DataStallReportParcelable dataStallReportParcelable) {
        try {
            this.mCallback.notifyDataStallSuspected(dataStallReportParcelable);
        } catch (RemoteException e) {
            Log.e(TAG, "Error sending notification for suspected data stall", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMetricsCollection() {
        if (this.mMetricsEnabled) {
            try {
                synchronized (this.mNetworkValidationMetrics) {
                    this.mNetworkValidationMetrics.startCollection(this.mNetworkCapabilities);
                }
            } catch (Exception e) {
                Log.wtf(TAG, "Error resetting validation metrics", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordProbeEventMetrics(ProbeType probeType, long j, ProbeResult probeResult, CaptivePortalDataShim captivePortalDataShim) {
        if (this.mMetricsEnabled) {
            try {
                synchronized (this.mNetworkValidationMetrics) {
                    this.mNetworkValidationMetrics.addProbeEvent(probeType, j, probeResult, captivePortalDataShim);
                }
            } catch (Exception e) {
                Log.wtf(TAG, "Error recording probe event", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordValidationResult(int i, String str) {
        if (this.mMetricsEnabled) {
            try {
                synchronized (this.mNetworkValidationMetrics) {
                    this.mNetworkValidationMetrics.setValidationResult(i, str);
                }
            } catch (Exception e) {
                Log.wtf(TAG, "Error recording validation result", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeStopCollectionAndSendMetrics() {
        if (this.mMetricsEnabled) {
            try {
                synchronized (this.mNetworkValidationMetrics) {
                    this.mNetworkValidationMetrics.maybeStopCollectionAndSend();
                }
            } catch (Exception e) {
                Log.wtf(TAG, "Error sending validation stats", e);
            }
        }
    }

    private class DefaultState extends State {
        private DefaultState() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                NetworkMonitor.this.updateConnectedNetworkAttributes(message);
                NetworkMonitor.this.logNetworkEvent(1);
                NetworkMonitor networkMonitor = NetworkMonitor.this;
                networkMonitor.transitionTo(networkMonitor.mEvaluatingState);
                return true;
            }
            if (i != 7) {
                if (i != 8) {
                    if (i == 9) {
                        NetworkMonitor.this.log("CaptivePortal App responded with " + message.arg1);
                        NetworkMonitor.this.mUseHttps = false;
                        int i2 = message.arg1;
                        if (i2 == 0) {
                            NetworkMonitor.this.sendMessage(8, 0, 0);
                        } else if (i2 == 1) {
                            NetworkMonitor.this.mDontDisplaySigninNotification = true;
                            NetworkMonitor.this.mUserDoesNotWant = true;
                            NetworkMonitor.this.mEvaluationState.reportEvaluationResult(0, null);
                            NetworkMonitor.this.mUidResponsibleForReeval = 0;
                            NetworkMonitor networkMonitor2 = NetworkMonitor.this;
                            networkMonitor2.transitionTo(networkMonitor2.mEvaluatingState);
                        } else if (i2 == 2) {
                            NetworkMonitor.this.mDontDisplaySigninNotification = true;
                            NetworkMonitor networkMonitor3 = NetworkMonitor.this;
                            networkMonitor3.mIsBandwidthCheckPassedOrIgnored = true;
                            networkMonitor3.transitionTo(networkMonitor3.mEvaluatingPrivateDnsState);
                        }
                        return true;
                    }
                    if (i != 12) {
                        if (i == 13) {
                            PrivateDnsConfig privateDnsConfig = (PrivateDnsConfig) message.obj;
                            if (!NetworkMonitor.this.isPrivateDnsValidationRequired() || privateDnsConfig == null || !privateDnsConfig.inStrictMode()) {
                                NetworkMonitor.this.mPrivateDnsProviderHostname = "";
                            } else {
                                NetworkMonitor.this.mPrivateDnsProviderHostname = privateDnsConfig.hostname;
                                NetworkMonitor.this.removeMessages(15);
                                NetworkMonitor.this.sendMessage(15);
                            }
                        } else {
                            switch (i) {
                                case 17:
                                    DnsStallDetector dnsStallDetector = NetworkMonitor.this.getDnsStallDetector();
                                    if (dnsStallDetector != null) {
                                        dnsStallDetector.accumulateConsecutiveDnsTimeoutCount(message.arg1);
                                    }
                                    break;
                                case 18:
                                    NetworkMonitor.this.maybeDisableHttpsProbing(true);
                                    break;
                                case 19:
                                    Uri captivePortalApiUrl = NetworkMonitor.getCaptivePortalApiUrl(NetworkMonitor.this.mLinkProperties);
                                    NetworkMonitor.this.mLinkProperties = (LinkProperties) message.obj;
                                    if (!Objects.equals(captivePortalApiUrl, NetworkMonitor.getCaptivePortalApiUrl(NetworkMonitor.this.mLinkProperties))) {
                                        NetworkMonitor.this.sendMessage(8, 0, 0);
                                    }
                                    break;
                                case 20:
                                    NetworkMonitor.this.mNetworkCapabilities = (NetworkCapabilities) message.obj;
                                    break;
                            }
                        }
                        return true;
                    }
                }
                if (NetworkMonitor.this.getCurrentState() == NetworkMonitor.this.mDefaultState) {
                    return true;
                }
                String str = "Forcing reevaluation for UID " + message.arg1;
                DnsStallDetector dnsStallDetector2 = NetworkMonitor.this.getDnsStallDetector();
                if (dnsStallDetector2 != null) {
                    str = str + ". Dns signal count: " + dnsStallDetector2.getConsecutiveTimeoutCount();
                }
                NetworkMonitor.this.validationLog(str);
                NetworkMonitor.this.mUidResponsibleForReeval = message.arg1;
                NetworkMonitor networkMonitor4 = NetworkMonitor.this;
                networkMonitor4.transitionTo(networkMonitor4.mEvaluatingState);
                return true;
            }
            NetworkMonitor.this.maybeStopCollectionAndSendMetrics();
            NetworkMonitor.this.logNetworkEvent(7);
            NetworkMonitor.this.quit();
            return true;
        }
    }

    private class ValidatedState extends State {
        private ValidatedState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            networkMonitor.maybeLogEvaluationResult(networkMonitor.networkEventType(networkMonitor.validationStage(), EvaluationResult.VALIDATED));
            NetworkMonitor.this.mEvaluationState.reportEvaluationResult((NetworkMonitor.this.mUseHttps || !NetworkMonitor.this.mAcceptPartialConnectivity) ? 1 : 3, null);
            NetworkMonitor.access$3208(NetworkMonitor.this);
            initSocketTrackingIfRequired();
            NetworkMonitor.this.sendTcpPollingEvent();
            NetworkMonitor.this.maybeStopCollectionAndSendMetrics();
        }

        private void initSocketTrackingIfRequired() {
            TcpSocketTracker tcpSocketTracker;
            if (NetworkMonitor.this.isValidationRequired() && (tcpSocketTracker = NetworkMonitor.this.getTcpSocketTracker()) != null) {
                tcpSocketTracker.pollSocketsInfo();
            }
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                NetworkMonitor.this.updateConnectedNetworkAttributes(message);
                NetworkMonitor networkMonitor = NetworkMonitor.this;
                networkMonitor.transitionTo(networkMonitor.mValidatedState);
            } else if (i == 15) {
                NetworkMonitor networkMonitor2 = NetworkMonitor.this;
                networkMonitor2.transitionTo(networkMonitor2.mEvaluatingPrivateDnsState);
            } else if (i == 17) {
                DnsStallDetector dnsStallDetector = NetworkMonitor.this.getDnsStallDetector();
                if (dnsStallDetector != null) {
                    dnsStallDetector.accumulateConsecutiveDnsTimeoutCount(message.arg1);
                    if (evaluateDataStall()) {
                        NetworkMonitor networkMonitor3 = NetworkMonitor.this;
                        networkMonitor3.transitionTo(networkMonitor3.mEvaluatingState);
                    }
                }
            } else {
                if (i != 21) {
                    return false;
                }
                TcpSocketTracker tcpSocketTracker = NetworkMonitor.this.getTcpSocketTracker();
                if (tcpSocketTracker != null) {
                    if (tcpSocketTracker.pollSocketsInfo() && evaluateDataStall()) {
                        NetworkMonitor networkMonitor4 = NetworkMonitor.this;
                        networkMonitor4.transitionTo(networkMonitor4.mEvaluatingState);
                    } else {
                        NetworkMonitor.this.sendTcpPollingEvent();
                    }
                }
            }
            return true;
        }

        boolean evaluateDataStall() {
            if (!NetworkMonitor.this.isDataStall()) {
                return false;
            }
            NetworkMonitor.this.validationLog("Suspecting data stall, reevaluate");
            return true;
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            NetworkMonitor.this.removeMessages(21);
        }
    }

    void sendTcpPollingEvent() {
        if (isValidationRequired()) {
            sendMessageDelayed(21, getTcpPollingInterval());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeWriteDataStallStats(CaptivePortalProbeResult captivePortalProbeResult) {
        if (this.mDataStallTypeToCollect == 0) {
            return;
        }
        for (int i : this.mNetworkCapabilities.getTransportTypes()) {
            this.mDependencies.writeDataStallDetectionStats(buildDataStallDetectionStats(i, this.mDataStallTypeToCollect), captivePortalProbeResult);
        }
        this.mDataStallTypeToCollect = 0;
    }

    protected DataStallDetectionStats buildDataStallDetectionStats(int i, int i2) {
        DataStallDetectionStats.Builder builder = new DataStallDetectionStats.Builder();
        if (VDBG_STALL) {
            log("collectDataStallMetrics: type=" + i + ", evaluation=" + i2);
        }
        builder.setEvaluationType(i2);
        builder.setNetworkType(i);
        if (i == 0) {
            boolean z = !this.mNetworkCapabilities.hasCapability(18);
            SignalStrength signalStrength = this.mTelephonyManager.getSignalStrength();
            builder.setCellData(this.mTelephonyManager.getDataNetworkType(), z, this.mTelephonyManager.getNetworkOperator(), this.mTelephonyManager.getSimOperator(), signalStrength != null ? signalStrength.getLevel() : 0);
        } else if (i == 1) {
            builder.setWiFiData(this.mWifiManager.getConnectionInfo());
        }
        addDnsEvents(builder);
        addTcpStats(builder);
        return builder.build();
    }

    private void addTcpStats(DataStallDetectionStats.Builder builder) {
        TcpSocketTracker tcpSocketTracker = getTcpSocketTracker();
        if (tcpSocketTracker == null) {
            return;
        }
        builder.setTcpSentSinceLastRecv(tcpSocketTracker.getSentSinceLastRecv());
        builder.setTcpFailRate(tcpSocketTracker.getLatestPacketFailPercentage());
    }

    protected void addDnsEvents(DataStallDetectionStats.Builder builder) {
        DnsStallDetector dnsStallDetector = getDnsStallDetector();
        if (dnsStallDetector == null) {
            return;
        }
        int size = dnsStallDetector.mResultIndices.size();
        for (int i = 1; i <= 20 && i <= size; i++) {
            int iIndexOf = dnsStallDetector.mResultIndices.indexOf(size - i);
            builder.addDnsEvent(dnsStallDetector.mDnsEvents[iIndexOf].mReturnCode, dnsStallDetector.mDnsEvents[iIndexOf].mTimeStamp);
        }
    }

    private class MaybeNotifyState extends State {
        private MaybeNotifyState() {
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (message.what != 11) {
                return false;
            }
            Bundle bundle = new Bundle();
            Network network = new Network(NetworkMonitor.this.mCleartextDnsNetwork);
            bundle.putParcelable("android.net.extra.NETWORK", network);
            CaptivePortalProbeResult captivePortalProbeResult = NetworkMonitor.this.mLastPortalProbeResult;
            bundle.putString("android.net.extra.CAPTIVE_PORTAL_URL", (!useRedirectUrlForPortal() || NetworkMonitor.this.makeURL(captivePortalProbeResult.redirectUrl) == null) ? captivePortalProbeResult.detectUrl : captivePortalProbeResult.redirectUrl);
            CaptivePortalProbeSpec captivePortalProbeSpec = captivePortalProbeResult.probeSpec;
            if (captivePortalProbeSpec != null) {
                bundle.putString("android.net.extra.CAPTIVE_PORTAL_PROBE_SPEC", captivePortalProbeSpec.getEncodedSpec());
            }
            bundle.putString("android.net.extra.CAPTIVE_PORTAL_USER_AGENT", NetworkMonitor.this.mCaptivePortalUserAgent);
            if (NetworkMonitor.this.mNotifier != null) {
                NetworkMonitor.this.mNotifier.notifyCaptivePortalValidationPending(network);
            }
            NetworkMonitor.this.mCm.startCaptivePortalApp(network, bundle);
            return true;
        }

        private boolean useRedirectUrlForPortal() {
            boolean zIsReleaseOrDevelopmentApiAbove = ShimUtils.isReleaseOrDevelopmentApiAbove(29);
            return zIsReleaseOrDevelopmentApiAbove && NetworkMonitor.this.mDependencies.isFeatureEnabled(NetworkMonitor.this.mContext, "connectivity", "dismiss_portal_in_validated_network", zIsReleaseOrDevelopmentApiAbove);
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            if (NetworkMonitor.this.mLaunchCaptivePortalAppBroadcastReceiver != null) {
                NetworkMonitor.this.mContext.unregisterReceiver(NetworkMonitor.this.mLaunchCaptivePortalAppBroadcastReceiver);
                NetworkMonitor.this.mLaunchCaptivePortalAppBroadcastReceiver = null;
            }
            NetworkMonitor.this.hideProvisioningNotification();
        }
    }

    private class EvaluatingState extends State {
        private Uri mEvaluatingCapportUrl;

        private EvaluatingState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            if (!NetworkMonitor.this.mEvaluationTimer.isStarted()) {
                NetworkMonitor.this.mEvaluationTimer.start();
            }
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            networkMonitor.sendMessage(6, NetworkMonitor.access$5004(networkMonitor), 0);
            if (NetworkMonitor.this.mUidResponsibleForReeval != -1) {
                TrafficStats.setThreadStatsUid(NetworkMonitor.this.mUidResponsibleForReeval);
                NetworkMonitor.this.mUidResponsibleForReeval = -1;
            }
            NetworkMonitor.this.mReevaluateDelayMs = NetworkMonitor.INITIAL_REEVALUATE_DELAY_MS;
            NetworkMonitor.this.mEvaluateAttempts = 0;
            this.mEvaluatingCapportUrl = NetworkMonitor.getCaptivePortalApiUrl(NetworkMonitor.this.mLinkProperties);
            NetworkMonitor.this.mEvaluationState.clearProbeResults();
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != 6) {
                if (i == 8) {
                    return !shouldAcceptForceRevalidation();
                }
                if (i != 18) {
                    return false;
                }
                NetworkMonitor.this.maybeDisableHttpsProbing(true);
                NetworkMonitor networkMonitor = NetworkMonitor.this;
                networkMonitor.transitionTo(networkMonitor.mEvaluatingPrivateDnsState);
                return true;
            }
            if (message.arg1 == NetworkMonitor.this.mReevaluateToken && !NetworkMonitor.this.mUserDoesNotWant) {
                if (!NetworkMonitor.this.isValidationRequired()) {
                    if (NetworkMonitor.this.isPrivateDnsValidationRequired()) {
                        NetworkMonitor.this.validationLog("Network would not satisfy default request, resolving private DNS");
                        NetworkMonitor networkMonitor2 = NetworkMonitor.this;
                        networkMonitor2.transitionTo(networkMonitor2.mEvaluatingPrivateDnsState);
                    } else {
                        NetworkMonitor.this.validationLog("Network would not satisfy default request, not validating");
                        NetworkMonitor networkMonitor3 = NetworkMonitor.this;
                        networkMonitor3.transitionTo(networkMonitor3.mValidatedState);
                    }
                    return true;
                }
                NetworkMonitor.access$5208(NetworkMonitor.this);
                NetworkMonitor networkMonitor4 = NetworkMonitor.this;
                networkMonitor4.transitionTo(networkMonitor4.mProbingState);
            }
            return true;
        }

        private boolean shouldAcceptForceRevalidation() {
            return NetworkMonitor.this.mEvaluateAttempts >= 5 || !Objects.equals(this.mEvaluatingCapportUrl, NetworkMonitor.getCaptivePortalApiUrl(NetworkMonitor.this.mLinkProperties));
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            TrafficStats.clearThreadStatsUid();
        }
    }

    private class CustomIntentReceiver extends BroadcastReceiver {
        private final String mAction;
        private final int mToken;
        private final int mWhat;

        CustomIntentReceiver(String str, int i, int i2) {
            this.mToken = i;
            this.mWhat = i2;
            this.mAction = str + "_" + NetworkMonitor.this.mCleartextDnsNetwork.getNetworkHandle() + "_" + i;
            NetworkMonitor.this.mContext.registerReceiver(this, new IntentFilter(this.mAction));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(this.mAction)) {
                NetworkMonitor networkMonitor = NetworkMonitor.this;
                networkMonitor.sendMessage(networkMonitor.obtainMessage(this.mWhat, this.mToken));
            }
        }
    }

    private class CaptivePortalState extends State {
        private CaptivePortalState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            networkMonitor.maybeLogEvaluationResult(networkMonitor.networkEventType(networkMonitor.validationStage(), EvaluationResult.CAPTIVE_PORTAL));
            if (NetworkMonitor.this.mDontDisplaySigninNotification) {
                return;
            }
            if (NetworkMonitor.this.mLaunchCaptivePortalAppBroadcastReceiver == null) {
                NetworkMonitor.this.mLaunchCaptivePortalAppBroadcastReceiver = NetworkMonitor.this.new CustomIntentReceiver("android.net.netmon.launchCaptivePortalApp", new Random().nextInt(), 11);
                NetworkMonitor networkMonitor2 = NetworkMonitor.this;
                networkMonitor2.showProvisioningNotification(networkMonitor2.mLaunchCaptivePortalAppBroadcastReceiver.mAction);
            }
            NetworkMonitor.this.sendMessageDelayed(12, 0, 600000L);
            NetworkMonitor.access$3208(NetworkMonitor.this);
            NetworkMonitor.this.maybeStopCollectionAndSendMetrics();
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            NetworkMonitor.this.removeMessages(12);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class EvaluatingPrivateDnsState extends State {
        private PrivateDnsConfig mPrivateDnsConfig;
        private int mPrivateDnsReevalDelayMs;

        private EvaluatingPrivateDnsState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            this.mPrivateDnsReevalDelayMs = NetworkMonitor.INITIAL_REEVALUATE_DELAY_MS;
            this.mPrivateDnsConfig = null;
            NetworkMonitor.this.sendMessage(15);
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 13) {
                this.mPrivateDnsReevalDelayMs = NetworkMonitor.INITIAL_REEVALUATE_DELAY_MS;
                return false;
            }
            if (i != 15) {
                return false;
            }
            if (!inStrictMode()) {
                NetworkMonitor.this.mEvaluationState.removeProbeResult(64);
            } else {
                if (!isStrictModeHostnameResolved()) {
                    resolveStrictModeHostname();
                    if (isStrictModeHostnameResolved()) {
                        notifyPrivateDnsConfigResolved();
                    } else {
                        handlePrivateDnsEvaluationFailure();
                        NetworkMonitor.this.recordProbeEventMetrics(ProbeType.PT_PRIVDNS, 0L, ProbeResult.PR_FAILURE, null);
                        return true;
                    }
                }
                if (!sendPrivateDnsProbe()) {
                    handlePrivateDnsEvaluationFailure();
                    return true;
                }
                handlePrivateDnsEvaluationSuccess();
            }
            if (NetworkMonitor.this.needEvaluatingBandwidth()) {
                NetworkMonitor networkMonitor = NetworkMonitor.this;
                networkMonitor.transitionTo(networkMonitor.mEvaluatingBandwidthState);
                return true;
            }
            NetworkMonitor networkMonitor2 = NetworkMonitor.this;
            networkMonitor2.transitionTo(networkMonitor2.mValidatedState);
            return true;
        }

        private boolean inStrictMode() {
            return !TextUtils.isEmpty(NetworkMonitor.this.mPrivateDnsProviderHostname);
        }

        private boolean isStrictModeHostnameResolved() {
            PrivateDnsConfig privateDnsConfig = this.mPrivateDnsConfig;
            return privateDnsConfig != null && privateDnsConfig.hostname.equals(NetworkMonitor.this.mPrivateDnsProviderHostname) && this.mPrivateDnsConfig.ips.length > 0;
        }

        private void resolveStrictModeHostname() {
            try {
                this.mPrivateDnsConfig = new PrivateDnsConfig(NetworkMonitor.this.mPrivateDnsProviderHostname, DnsUtils.getAllByName(NetworkMonitor.this.mDependencies.getDnsResolver(), NetworkMonitor.this.mCleartextDnsNetwork, NetworkMonitor.this.mPrivateDnsProviderHostname, NetworkMonitor.this.getDnsProbeTimeout(), new DnsLogFunc() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$EvaluatingPrivateDnsState$vwjryN6uFc1KGV1XsYj7QwvDcXs
                    @Override // com.android.server.connectivity.NetworkMonitor.DnsLogFunc
                    public final void log(String str) {
                        this.f$0.lambda$resolveStrictModeHostname$0$NetworkMonitor$EvaluatingPrivateDnsState(str);
                    }
                }));
            } catch (UnknownHostException unused) {
                this.mPrivateDnsConfig = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$resolveStrictModeHostname$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$resolveStrictModeHostname$0$NetworkMonitor$EvaluatingPrivateDnsState(String str) {
            NetworkMonitor.this.validationLog("Strict mode hostname resolution " + str);
        }

        private void notifyPrivateDnsConfigResolved() {
            try {
                NetworkMonitor.this.mCallback.notifyPrivateDnsConfigResolved(this.mPrivateDnsConfig.toParcel());
            } catch (RemoteException e) {
                Log.e(NetworkMonitor.TAG, "Error sending private DNS config resolved notification", e);
            }
        }

        private void handlePrivateDnsEvaluationSuccess() {
            NetworkMonitor.this.mEvaluationState.noteProbeResult(64, true);
        }

        private void handlePrivateDnsEvaluationFailure() {
            NetworkMonitor.this.mEvaluationState.noteProbeResult(64, false);
            NetworkMonitor.this.mEvaluationState.reportEvaluationResult(0, null);
            NetworkMonitor.this.sendMessageDelayed(15, this.mPrivateDnsReevalDelayMs);
            int i = this.mPrivateDnsReevalDelayMs * 2;
            this.mPrivateDnsReevalDelayMs = i;
            if (i > 600000) {
                this.mPrivateDnsReevalDelayMs = 600000;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0044  */
        /* JADX WARN: Type inference failed for: r6v13 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3, types: [boolean, int] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean sendPrivateDnsProbe() {
            /*
                r15 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.util.UUID r1 = java.util.UUID.randomUUID()
                java.lang.String r1 = r1.toString()
                r2 = 0
                r3 = 8
                java.lang.String r1 = r1.substring(r2, r3)
                r0.append(r1)
                java.lang.String r1 = "-dnsotls-ds.metric.gstatic.com"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                android.net.util.Stopwatch r1 = new android.net.util.Stopwatch
                r1.<init>()
                r1.start()
                r3 = 2
                r4 = 1
                r5 = 5
                com.android.server.connectivity.NetworkMonitor r6 = com.android.server.connectivity.NetworkMonitor.this     // Catch: java.net.UnknownHostException -> L61
                android.net.Network r6 = com.android.server.connectivity.NetworkMonitor.access$6200(r6)     // Catch: java.net.UnknownHostException -> L61
                java.net.InetAddress[] r6 = r6.getAllByName(r0)     // Catch: java.net.UnknownHostException -> L61
                long r7 = r1.stop()     // Catch: java.net.UnknownHostException -> L61
                java.lang.String r9 = java.util.Arrays.toString(r6)     // Catch: java.net.UnknownHostException -> L61
                if (r6 == 0) goto L44
                int r6 = r6.length     // Catch: java.net.UnknownHostException -> L61
                if (r6 <= 0) goto L44
                r6 = r4
                goto L45
            L44:
                r6 = r2
            L45:
                com.android.server.connectivity.NetworkMonitor r10 = com.android.server.connectivity.NetworkMonitor.this     // Catch: java.net.UnknownHostException -> L5c
                java.lang.String r11 = "%dus: %s"
                java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.net.UnknownHostException -> L5c
                java.lang.Long r13 = java.lang.Long.valueOf(r7)     // Catch: java.net.UnknownHostException -> L5c
                r12[r2] = r13     // Catch: java.net.UnknownHostException -> L5c
                r12[r4] = r9     // Catch: java.net.UnknownHostException -> L5c
                java.lang.String r9 = java.lang.String.format(r11, r12)     // Catch: java.net.UnknownHostException -> L5c
                com.android.server.connectivity.NetworkMonitor.access$6300(r10, r5, r0, r9)     // Catch: java.net.UnknownHostException -> L5c
                r0 = r7
                goto L82
            L5c:
                r7 = move-exception
                r14 = r7
                r7 = r6
                r6 = r14
                goto L63
            L61:
                r6 = move-exception
                r7 = r2
            L63:
                long r8 = r1.stop()
                com.android.server.connectivity.NetworkMonitor r1 = com.android.server.connectivity.NetworkMonitor.this
                java.lang.Object[] r3 = new java.lang.Object[r3]
                java.lang.Long r10 = java.lang.Long.valueOf(r8)
                r3[r2] = r10
                java.lang.String r2 = r6.getMessage()
                r3[r4] = r2
                java.lang.String r2 = "%dus - Error: %s"
                java.lang.String r2 = java.lang.String.format(r2, r3)
                com.android.server.connectivity.NetworkMonitor.access$6300(r1, r5, r0, r2)
                r6 = r7
                r0 = r8
            L82:
                com.android.server.connectivity.NetworkMonitor r7 = com.android.server.connectivity.NetworkMonitor.this
                android.stats.connectivity.ProbeType r8 = android.stats.connectivity.ProbeType.PT_PRIVDNS
                if (r6 == 0) goto L8b
                android.stats.connectivity.ProbeResult r2 = android.stats.connectivity.ProbeResult.PR_SUCCESS
                goto L8d
            L8b:
                android.stats.connectivity.ProbeResult r2 = android.stats.connectivity.ProbeResult.PR_FAILURE
            L8d:
                r11 = r2
                r12 = 0
                r9 = r0
                com.android.server.connectivity.NetworkMonitor.access$5700(r7, r8, r9, r11, r12)
                com.android.server.connectivity.NetworkMonitor r15 = com.android.server.connectivity.NetworkMonitor.this
                com.android.server.connectivity.NetworkMonitor.access$6400(r15, r0, r5, r6)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.connectivity.NetworkMonitor.EvaluatingPrivateDnsState.sendPrivateDnsProbe():boolean");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ProbingState extends State {
        private Thread mThread;

        private ProbingState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            NetworkMonitor.this.maybeStopCollectionAndSendMetrics();
            NetworkMonitor.this.startMetricsCollection();
            if (NetworkMonitor.this.mEvaluateAttempts >= 5) {
                TrafficStats.clearThreadStatsUid();
            }
            final int iAccess$6604 = NetworkMonitor.access$6604(NetworkMonitor.this);
            final EvaluationThreadDeps evaluationThreadDeps = new EvaluationThreadDeps(NetworkMonitor.this.mNetworkCapabilities);
            Thread thread = new Thread(new Runnable() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$ProbingState$tVWpPgvA30eIl1sdCMZ0xFcP-Fw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enter$0$NetworkMonitor$ProbingState(iAccess$6604, evaluationThreadDeps);
                }
            });
            this.mThread = thread;
            thread.start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$enter$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$enter$0$NetworkMonitor$ProbingState(int i, EvaluationThreadDeps evaluationThreadDeps) {
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            networkMonitor.sendMessage(networkMonitor.obtainMessage(16, i, 0, networkMonitor.isCaptivePortal(evaluationThreadDeps)));
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            switch (message.what) {
                case 16:
                    if (message.arg1 != NetworkMonitor.this.mProbeToken) {
                        return true;
                    }
                    CaptivePortalProbeResult captivePortalProbeResult = (CaptivePortalProbeResult) message.obj;
                    NetworkMonitor.this.mLastProbeTime = SystemClock.elapsedRealtime();
                    NetworkMonitor.this.maybeWriteDataStallStats(captivePortalProbeResult);
                    if (captivePortalProbeResult.isSuccessful()) {
                        NetworkMonitor networkMonitor = NetworkMonitor.this;
                        networkMonitor.transitionTo(networkMonitor.mEvaluatingPrivateDnsState);
                    } else if (captivePortalProbeResult.isPortal()) {
                        NetworkMonitor.this.mEvaluationState.reportEvaluationResult(0, captivePortalProbeResult.redirectUrl);
                        NetworkMonitor.this.mLastPortalProbeResult = captivePortalProbeResult;
                        NetworkMonitor networkMonitor2 = NetworkMonitor.this;
                        networkMonitor2.transitionTo(networkMonitor2.mCaptivePortalState);
                    } else if (captivePortalProbeResult.isPartialConnectivity()) {
                        NetworkMonitor.this.mEvaluationState.reportEvaluationResult(2, null);
                        NetworkMonitor networkMonitor3 = NetworkMonitor.this;
                        networkMonitor3.maybeDisableHttpsProbing(networkMonitor3.mAcceptPartialConnectivity);
                        if (NetworkMonitor.this.mAcceptPartialConnectivity) {
                            NetworkMonitor networkMonitor4 = NetworkMonitor.this;
                            networkMonitor4.transitionTo(networkMonitor4.mEvaluatingPrivateDnsState);
                        } else {
                            NetworkMonitor networkMonitor5 = NetworkMonitor.this;
                            networkMonitor5.transitionTo(networkMonitor5.mWaitingForNextProbeState);
                        }
                    } else {
                        NetworkMonitor.this.logNetworkEvent(3);
                        NetworkMonitor.this.mEvaluationState.reportEvaluationResult(0, null);
                        NetworkMonitor networkMonitor6 = NetworkMonitor.this;
                        networkMonitor6.transitionTo(networkMonitor6.mWaitingForNextProbeState);
                    }
                    return true;
                case 17:
                case 18:
                    return false;
                default:
                    NetworkMonitor.this.deferMessage(message);
                    return true;
            }
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            if (this.mThread.isAlive()) {
                this.mThread.interrupt();
            }
            this.mThread = null;
        }
    }

    private class WaitingForNextProbeState extends State {
        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            return false;
        }

        private WaitingForNextProbeState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            NetworkMonitor.this.maybeStopCollectionAndSendMetrics();
            scheduleNextProbe();
        }

        private void scheduleNextProbe() {
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            Message messageObtainMessage = networkMonitor.obtainMessage(6, NetworkMonitor.access$5004(networkMonitor), 0);
            NetworkMonitor.this.sendMessageDelayed(messageObtainMessage, r1.mReevaluateDelayMs);
            NetworkMonitor.access$5128(NetworkMonitor.this, 2);
            if (NetworkMonitor.this.mReevaluateDelayMs > 600000) {
                NetworkMonitor.this.mReevaluateDelayMs = 600000;
            }
        }
    }

    private final class EvaluatingBandwidthThread extends Thread {
        final int mThreadId;

        EvaluatingBandwidthThread(int i) {
            this.mThreadId = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            HttpURLConnection httpURLConnectionMakeProbeConnection = null;
            try {
                try {
                    try {
                        httpURLConnectionMakeProbeConnection = NetworkMonitor.this.makeProbeConnection(NetworkMonitor.this.makeURL(NetworkMonitor.this.mEvaluatingBandwidthUrl), true);
                        NetworkMonitor.this.sendMessageDelayed(24, NetworkMonitor.this.mEvaluatingBandwidthTimeoutMs);
                        readContentFromDownloadUrl(httpURLConnectionMakeProbeConnection);
                    } catch (InterruptedIOException unused) {
                        NetworkMonitor.this.validationLog("The thread is interrupted when executing the getInputStream(), return and wait for the bandwidth reevaluation");
                        if (httpURLConnectionMakeProbeConnection != null) {
                            httpURLConnectionMakeProbeConnection.disconnect();
                            return;
                        }
                        return;
                    }
                } catch (IOException e) {
                    NetworkMonitor.this.validationLog("Evaluating bandwidth failed: " + e + ", if the thread is not interrupted, transition to validated state directly to make sure user can use wifi normally.");
                    if (httpURLConnectionMakeProbeConnection != null) {
                    }
                }
                if (httpURLConnectionMakeProbeConnection != null) {
                    httpURLConnectionMakeProbeConnection.disconnect();
                }
                if (isInterrupted()) {
                    return;
                }
                NetworkMonitor.this.sendMessage(23, this.mThreadId);
            } catch (Throwable th) {
                if (httpURLConnectionMakeProbeConnection != null) {
                    httpURLConnectionMakeProbeConnection.disconnect();
                }
                throw th;
            }
        }

        private void readContentFromDownloadUrl(HttpURLConnection httpURLConnection) throws IOException {
            byte[] bArr = new byte[NetworkMonitor.INITIAL_REEVALUATE_DELAY_MS];
            InputStream inputStream = httpURLConnection.getInputStream();
            while (!isInterrupted() && inputStream.read(bArr) > 0) {
            }
        }
    }

    private class EvaluatingBandwidthState extends State {
        private int mCurrentThreadId;
        private EvaluatingBandwidthThread mEvaluatingBandwidthThread;
        private int mRetryBandwidthDelayMs;

        private EvaluatingBandwidthState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            this.mRetryBandwidthDelayMs = networkMonitor.getResIntConfig(networkMonitor.mContext, R.integer.config_evaluating_bandwidth_min_retry_timer_ms, NetworkMonitor.INITIAL_REEVALUATE_DELAY_MS);
            NetworkMonitor.this.sendMessage(22);
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            switch (message.what) {
                case 22:
                    this.mCurrentThreadId = NetworkMonitor.this.mNextEvaluatingBandwidthThreadId.getAndIncrement();
                    EvaluatingBandwidthThread evaluatingBandwidthThread = NetworkMonitor.this.new EvaluatingBandwidthThread(this.mCurrentThreadId);
                    this.mEvaluatingBandwidthThread = evaluatingBandwidthThread;
                    evaluatingBandwidthThread.start();
                    return true;
                case 23:
                    if (this.mCurrentThreadId == message.arg1) {
                        NetworkMonitor networkMonitor = NetworkMonitor.this;
                        networkMonitor.mIsBandwidthCheckPassedOrIgnored = true;
                        networkMonitor.transitionTo(networkMonitor.mValidatedState);
                    }
                    return true;
                case 24:
                    NetworkMonitor.this.validationLog("Evaluating bandwidth timeout!");
                    this.mEvaluatingBandwidthThread.interrupt();
                    scheduleReevaluatingBandwidth();
                    return true;
                default:
                    return false;
            }
        }

        private void scheduleReevaluatingBandwidth() {
            NetworkMonitor networkMonitor = NetworkMonitor.this;
            networkMonitor.sendMessageDelayed(networkMonitor.obtainMessage(22), this.mRetryBandwidthDelayMs);
            int i = this.mRetryBandwidthDelayMs * 2;
            this.mRetryBandwidthDelayMs = i;
            if (i > NetworkMonitor.this.mMaxRetryTimerMs) {
                this.mRetryBandwidthDelayMs = NetworkMonitor.this.mMaxRetryTimerMs;
            }
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            this.mEvaluatingBandwidthThread.interrupt();
            NetworkMonitor.this.removeMessages(22);
            NetworkMonitor.this.removeMessages(24);
        }
    }

    private static class OneAddressPerFamilyNetwork extends Network {
        OneAddressPerFamilyNetwork(Network network) {
            super(network.getPrivateDnsBypassingCopy());
        }

        @Override // android.net.Network
        public InetAddress[] getAllByName(String str) throws UnknownHostException {
            List<InetAddress> listAsList = Arrays.asList(super.getAllByName(str));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(((InetAddress) listAsList.get(0)).getClass(), (InetAddress) listAsList.get(0));
            Collections.shuffle(listAsList);
            for (InetAddress inetAddress : listAsList) {
                linkedHashMap.put(inetAddress.getClass(), inetAddress);
            }
            return (InetAddress[]) linkedHashMap.values().toArray(new InetAddress[linkedHashMap.size()]);
        }
    }

    boolean onlyWifiTransport() {
        int[] transportTypes = this.mNetworkCapabilities.getTransportTypes();
        return transportTypes.length == 1 && transportTypes[0] == 1;
    }

    boolean needEvaluatingBandwidth() {
        return !this.mIsBandwidthCheckPassedOrIgnored && !TextUtils.isEmpty(this.mEvaluatingBandwidthUrl) && this.mNetworkCapabilities.hasCapability(11) && onlyWifiTransport();
    }

    private boolean getIsCaptivePortalCheckEnabled() {
        return this.mDependencies.getSetting(this.mContext, "captive_portal_mode", 1) != 0;
    }

    private boolean getIsPrivateIpNoInternetEnabled() {
        return this.mDependencies.isFeatureEnabled(this.mContext, "dns_probe_private_ip_no_internet") || this.mContext.getResources().getBoolean(R.bool.config_force_dns_probe_private_ip_no_internet);
    }

    private boolean getUseHttpsValidation() {
        return this.mDependencies.getDeviceConfigPropertyInt("connectivity", "captive_portal_use_https", 1) == 1;
    }

    private String getMccFromCellInfo(CellInfo cellInfo) {
        if (cellInfo instanceof CellInfoGsm) {
            return ((CellInfoGsm) cellInfo).getCellIdentity().getMccString();
        }
        if (cellInfo instanceof CellInfoLte) {
            return ((CellInfoLte) cellInfo).getCellIdentity().getMccString();
        }
        if (cellInfo instanceof CellInfoWcdma) {
            return ((CellInfoWcdma) cellInfo).getCellIdentity().getMccString();
        }
        if (cellInfo instanceof CellInfoTdscdma) {
            return ((CellInfoTdscdma) cellInfo).getCellIdentity().getMccString();
        }
        if (cellInfo instanceof CellInfoNr) {
            return ((CellIdentityNr) ((CellInfoNr) cellInfo).getCellIdentity()).getMccString();
        }
        return null;
    }

    protected String getLocationMcc() {
        if (this.mContext.checkPermission("android.permission.ACCESS_FINE_LOCATION", Process.myPid(), Process.myUid()) == -1) {
            log("getLocationMcc : NetworkStack does not hold ACCESS_FINE_LOCATION");
            return null;
        }
        try {
            List<CellInfo> allCellInfo = this.mTelephonyManager.getAllCellInfo();
            if (allCellInfo == null) {
                return null;
            }
            HashMap map = new HashMap();
            Iterator<CellInfo> it = allCellInfo.iterator();
            while (it.hasNext()) {
                String mccFromCellInfo = getMccFromCellInfo(it.next());
                if (mccFromCellInfo != null) {
                    map.put(mccFromCellInfo, Integer.valueOf(((Integer) map.getOrDefault(mccFromCellInfo, 0)).intValue() + 1));
                }
            }
            if (map.size() <= 0) {
                return null;
            }
            return (String) ((Map.Entry) Collections.max(map.entrySet(), new Comparator() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$Rgh5RsXSPCgQj2egoivJfvkP2F0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((Integer) ((Map.Entry) obj).getValue()).compareTo((Integer) ((Map.Entry) obj2).getValue());
                }
            })).getKey();
        } catch (SecurityException e) {
            log("Permission is not granted:" + e);
            return null;
        }
    }

    MccMncOverrideInfo getMccMncOverrideInfo() {
        return sCarrierIdToMccMnc.get(this.mTelephonyManager.getSimCarrierId());
    }

    private Context getContextByMccMnc(int i, int i2) {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        if (i != -1) {
            configuration.mcc = i;
        }
        if (i2 != -1) {
            configuration.mnc = i2;
        }
        return this.mContext.createConfigurationContext(configuration);
    }

    protected Context getCustomizedContextOrDefault() {
        MccMncOverrideInfo mccMncOverrideInfo = getMccMncOverrideInfo();
        if (mccMncOverrideInfo != null) {
            return getContextByMccMnc(mccMncOverrideInfo.mcc, mccMncOverrideInfo.mnc);
        }
        if (!getResBooleanConfig(this.mContext, R.bool.config_no_sim_card_uses_neighbor_mcc, false) || 5 == this.mTelephonyManager.getSimState()) {
            return this.mContext;
        }
        String locationMcc = getLocationMcc();
        if (TextUtils.isEmpty(locationMcc)) {
            return this.mContext;
        }
        return getContextByMccMnc(Integer.parseInt(locationMcc), -1);
    }

    private String getTestUrl(String str) {
        String deviceConfigProperty = this.mDependencies.getDeviceConfigProperty("connectivity", "test_url_expiration_time", null);
        if (deviceConfigProperty == null) {
            return null;
        }
        try {
            long unsignedLong = Long.parseUnsignedLong(deviceConfigProperty);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (unsignedLong < jCurrentTimeMillis || unsignedLong - jCurrentTimeMillis > TEST_URL_EXPIRATION_MS) {
                return null;
            }
            return this.mDependencies.getDeviceConfigProperty("connectivity", str, null);
        } catch (NumberFormatException e) {
            loge("Invalid test URL expiration time format", e);
            return null;
        }
    }

    private String getCaptivePortalServerHttpsUrl() {
        String testUrl = getTestUrl("test_captive_portal_https_url");
        if (isValidTestUrl(testUrl)) {
            return testUrl;
        }
        Context customizedContextOrDefault = getCustomizedContextOrDefault();
        return getSettingFromResource(customizedContextOrDefault, R.string.config_captive_portal_https_url, "captive_portal_https_url", customizedContextOrDefault.getResources().getString(R.string.default_captive_portal_https_url));
    }

    private static boolean isValidTestUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return Uri.parse(str).getHost().equals("localhost");
        } catch (Throwable th) {
            Log.wtf(TAG, "Error parsing test URL", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDnsProbeTimeout() {
        return getIntSetting(this.mContext, R.integer.config_captive_portal_dns_probe_timeout, CONFIG_CAPTIVE_PORTAL_DNS_PROBE_TIMEOUT, 12500);
    }

    int getIntSetting(Context context, int i, String str, int i2) {
        try {
            return context.getResources().getInteger(i);
        } catch (Resources.NotFoundException unused) {
            return this.mDependencies.getDeviceConfigPropertyInt("connectivity", str, i2);
        }
    }

    boolean getResBooleanConfig(Context context, int i, boolean z) {
        try {
            return context.getResources().getBoolean(i);
        } catch (Resources.NotFoundException unused) {
            return z;
        }
    }

    int getResIntConfig(Context context, int i, int i2) {
        try {
            return context.getResources().getInteger(i);
        } catch (Resources.NotFoundException unused) {
            return i2;
        }
    }

    String getResStringConfig(Context context, int i, String str) {
        try {
            return context.getResources().getString(i);
        } catch (Resources.NotFoundException unused) {
            return str;
        }
    }

    public String getCaptivePortalServerHttpUrl() {
        String testUrl = getTestUrl("test_captive_portal_http_url");
        if (isValidTestUrl(testUrl)) {
            return testUrl;
        }
        Context customizedContextOrDefault = getCustomizedContextOrDefault();
        return getSettingFromResource(customizedContextOrDefault, R.string.config_captive_portal_http_url, "captive_portal_http_url", customizedContextOrDefault.getResources().getString(R.string.default_captive_portal_http_url));
    }

    private int getConsecutiveDnsTimeoutThreshold() {
        return this.mDependencies.getDeviceConfigPropertyInt("connectivity", "data_stall_consecutive_dns_timeout_threshold", 5);
    }

    private int getDataStallMinEvaluateTime() {
        return this.mDependencies.getDeviceConfigPropertyInt("connectivity", "data_stall_min_evaluate_interval", 60000);
    }

    private int getDataStallValidDnsTimeThreshold() {
        return this.mDependencies.getDeviceConfigPropertyInt("connectivity", "data_stall_valid_dns_time_threshold", 1800000);
    }

    int getDataStallEvaluationType() {
        return this.mDependencies.getDeviceConfigPropertyInt("connectivity", "data_stall_evaluation_type", 3);
    }

    private int getTcpPollingInterval() {
        return this.mDependencies.getDeviceConfigPropertyInt("connectivity", DataStallUtils.CONFIG_DATA_STALL_TCP_POLLING_INTERVAL, DataStallUtils.DEFAULT_TCP_POLLING_INTERVAL_MS);
    }

    URL[] makeCaptivePortalFallbackUrls() {
        try {
            return (URL[]) getProbeUrlArrayConfig(combineCaptivePortalUrls(this.mDependencies.getSetting(this.mContext, "captive_portal_fallback_url", (String) null), "captive_portal_other_fallback_urls"), R.array.config_captive_portal_fallback_urls, R.array.default_captive_portal_fallback_urls, new $$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAjx8S3_Q(this));
        } catch (Exception e) {
            Log.e(TAG, "Error parsing configured fallback URLs", e);
            return new URL[0];
        }
    }

    private CaptivePortalProbeSpec[] makeCaptivePortalFallbackProbeSpecs() {
        try {
            String deviceConfigProperty = this.mDependencies.getDeviceConfigProperty("connectivity", "captive_portal_fallback_probe_specs", null);
            CaptivePortalProbeSpec[] captivePortalProbeSpecArr = new CaptivePortalProbeSpec[0];
            if (!TextUtils.isEmpty(deviceConfigProperty)) {
                captivePortalProbeSpecArr = (CaptivePortalProbeSpec[]) CaptivePortalProbeSpec.parseCaptivePortalProbeSpecs(deviceConfigProperty).toArray(captivePortalProbeSpecArr);
            }
            return (CaptivePortalProbeSpec[]) getProbeUrlArrayConfig(captivePortalProbeSpecArr, R.array.config_captive_portal_fallback_probe_specs, NetworkStackUtils.DEFAULT_CAPTIVE_PORTAL_FALLBACK_PROBE_SPECS, new Function() { // from class: com.android.server.connectivity.-$$Lambda$xxQKW2zlaIKTbt0V96C5vfmzCto
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CaptivePortalProbeSpec.parseSpecOrNull((String) obj);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error parsing configured fallback probe specs", e);
            return null;
        }
    }

    private URL[] makeCaptivePortalHttpsUrls() {
        String captivePortalServerHttpsUrl = getCaptivePortalServerHttpsUrl();
        try {
            return (URL[]) getProbeUrlArrayConfig(combineCaptivePortalUrls(captivePortalServerHttpsUrl, "captive_portal_other_https_urls"), R.array.config_captive_portal_https_urls, NetworkStackUtils.DEFAULT_CAPTIVE_PORTAL_HTTPS_URLS, new $$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAjx8S3_Q(this));
        } catch (Exception e) {
            Log.e(TAG, "Error parsing configured https URLs", e);
            return new URL[]{makeURL(captivePortalServerHttpsUrl)};
        }
    }

    private URL[] makeCaptivePortalHttpUrls() {
        String captivePortalServerHttpUrl = getCaptivePortalServerHttpUrl();
        try {
            return (URL[]) getProbeUrlArrayConfig(combineCaptivePortalUrls(captivePortalServerHttpUrl, "captive_portal_other_http_urls"), R.array.config_captive_portal_http_urls, NetworkStackUtils.DEFAULT_CAPTIVE_PORTAL_HTTP_URLS, new $$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAjx8S3_Q(this));
        } catch (Exception e) {
            Log.e(TAG, "Error parsing configured http URLs", e);
            return new URL[]{makeURL(captivePortalServerHttpUrl)};
        }
    }

    private URL[] combineCaptivePortalUrls(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new URL[0];
        }
        return (URL[]) convertStrings((str + "," + this.mDependencies.getDeviceConfigProperty("connectivity", str2, "")).split(","), new $$Lambda$NetworkMonitor$Kh6WmPzS24WnRman3oAjx8S3_Q(this), new URL[0]);
    }

    private String getSettingFromResource(Context context, int i, String str, String str2) {
        String string = context.getResources().getString(i);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String setting = this.mDependencies.getSetting(context, str, (String) null);
        return !TextUtils.isEmpty(setting) ? setting : str2;
    }

    private <T> T[] getProbeUrlArrayConfig(T[] tArr, int i, int i2, Function<String, T> function) {
        return (T[]) getProbeUrlArrayConfig(tArr, i, getCustomizedContextOrDefault().getResources().getStringArray(i2), function);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T[] getProbeUrlArrayConfig(T[] tArr, int i, String[] strArr, Function<String, T> function) {
        String[] stringArray = getCustomizedContextOrDefault().getResources().getStringArray(i);
        if (stringArray.length != 0) {
            strArr = stringArray;
        } else if (tArr.length > 0) {
            return tArr;
        }
        return (T[]) convertStrings(strArr, function, Arrays.copyOf(tArr, 0));
    }

    private <T> T[] convertStrings(String[] strArr, Function<String, T> function, T[] tArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            T tApply = null;
            try {
                tApply = function.apply(str);
            } catch (Exception e) {
                Log.e(TAG, "Error parsing configuration", e);
            }
            if (tApply != null) {
                arrayList.add(tApply);
            }
        }
        return (T[]) arrayList.toArray(tArr);
    }

    private String getCaptivePortalUserAgent() {
        return this.mDependencies.getDeviceConfigProperty("connectivity", "captive_portal_user_agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.32 Safari/537.36");
    }

    private URL nextFallbackUrl() {
        if (this.mCaptivePortalFallbackUrls.length == 0) {
            return null;
        }
        int iAbs = Math.abs(this.mNextFallbackUrlIndex) % this.mCaptivePortalFallbackUrls.length;
        this.mNextFallbackUrlIndex += this.mRandom.nextInt();
        return this.mCaptivePortalFallbackUrls[iAbs];
    }

    private CaptivePortalProbeSpec nextFallbackSpec() {
        if (NetworkStackUtils.isEmpty(this.mCaptivePortalFallbackSpecs)) {
            return null;
        }
        int iAbs = Math.abs(this.mRandom.nextInt());
        CaptivePortalProbeSpec[] captivePortalProbeSpecArr = this.mCaptivePortalFallbackSpecs;
        return captivePortalProbeSpecArr[iAbs % captivePortalProbeSpecArr.length];
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class EvaluationThreadDeps {
        private final boolean mIsTestNetwork;

        EvaluationThreadDeps(NetworkCapabilities networkCapabilities) {
            this.mIsTestNetwork = networkCapabilities.hasTransport(7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CaptivePortalProbeResult isCaptivePortal(EvaluationThreadDeps evaluationThreadDeps) throws Throwable {
        URL urlMakeURL;
        CaptivePortalProbeResult captivePortalProbeResultSendDnsAndHttpProbes;
        if (!this.mIsCaptivePortalCheckEnabled) {
            validationLog("Validation disabled.");
            return CaptivePortalProbeResult.success(0);
        }
        URL[] urlArr = this.mCaptivePortalHttpsUrls;
        URL[] urlArr2 = this.mCaptivePortalHttpUrls;
        ProxyInfo httpProxy = this.mLinkProperties.getHttpProxy();
        if (httpProxy == null || Uri.EMPTY.equals(httpProxy.getPacFileUrl())) {
            urlMakeURL = null;
        } else {
            urlMakeURL = makeURL(httpProxy.getPacFileUrl().toString());
            if (urlMakeURL == null) {
                return CaptivePortalProbeResult.failed(0);
            }
        }
        if (urlMakeURL == null && (urlArr2.length == 0 || urlArr.length == 0 || urlArr2[0] == null || urlArr[0] == null)) {
            return CaptivePortalProbeResult.failed(0);
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (urlMakeURL != null) {
            captivePortalProbeResultSendDnsAndHttpProbes = sendDnsAndHttpProbes(null, urlMakeURL, 3);
            reportHttpProbeResult(8, captivePortalProbeResultSendDnsAndHttpProbes);
        } else if (this.mUseHttps && urlArr.length == 1 && urlArr2.length == 1) {
            captivePortalProbeResultSendDnsAndHttpProbes = sendHttpAndHttpsParallelWithFallbackProbes(evaluationThreadDeps, httpProxy, urlArr[0], urlArr2[0]);
        } else if (this.mUseHttps) {
            captivePortalProbeResultSendDnsAndHttpProbes = sendMultiParallelHttpAndHttpsProbes(evaluationThreadDeps, httpProxy, urlArr, urlArr2);
        } else {
            captivePortalProbeResultSendDnsAndHttpProbes = sendDnsAndHttpProbes(httpProxy, urlArr2[0], 1);
            reportHttpProbeResult(8, captivePortalProbeResultSendDnsAndHttpProbes);
        }
        log("isCaptivePortal: isSuccessful()=" + captivePortalProbeResultSendDnsAndHttpProbes.isSuccessful() + " isPortal()=" + captivePortalProbeResultSendDnsAndHttpProbes.isPortal() + " RedirectUrl=" + captivePortalProbeResultSendDnsAndHttpProbes.redirectUrl + " isPartialConnectivity()=" + captivePortalProbeResultSendDnsAndHttpProbes.isPartialConnectivity() + " Time=" + (SystemClock.elapsedRealtime() - jElapsedRealtime) + "ms");
        return captivePortalProbeResultSendDnsAndHttpProbes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CaptivePortalProbeResult sendDnsAndHttpProbes(ProxyInfo proxyInfo, URL url, int i) {
        InetAddress[] inetAddressArrSendDnsProbe = sendDnsProbe(proxyInfo != null ? proxyInfo.getHost() : url.getHost());
        if (this.mPrivateIpNoInternetEnabled && i == 1 && proxyInfo == null && hasPrivateIpAddress(inetAddressArrSendDnsProbe)) {
            recordProbeEventMetrics(NetworkValidationMetrics.probeTypeToEnum(i), 0L, ProbeResult.PR_PRIVATE_IP_DNS, null);
            return CaptivePortalProbeResult.PRIVATE_IP;
        }
        return sendHttpProbe(url, i, null);
    }

    protected InetAddress[] sendDnsProbeWithTimeout(final String str, int i) throws UnknownHostException {
        return DnsUtils.getAllByName(this.mDependencies.getDnsResolver(), this.mCleartextDnsNetwork, str, -1, 0, i, new DnsLogFunc() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$wQGEbXShg-2q-XVFCkYCG0QcQZA
            @Override // com.android.server.connectivity.NetworkMonitor.DnsLogFunc
            public final void log(String str2) {
                this.f$0.lambda$sendDnsProbeWithTimeout$1$NetworkMonitor(str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$sendDnsProbeWithTimeout$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$sendDnsProbeWithTimeout$1$NetworkMonitor(String str, String str2) {
        validationLog(0, str, str2);
    }

    private InetAddress[] sendDnsProbe(String str) {
        int i;
        ProbeResult probeResult;
        InetAddress[] inetAddressArrSendDnsProbeWithTimeout = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        try {
            inetAddressArrSendDnsProbeWithTimeout = sendDnsProbeWithTimeout(str, getDnsProbeTimeout());
            i = 1;
        } catch (UnknownHostException unused) {
            i = 0;
        }
        long jStop = stopwatch.stop();
        ProbeType probeType = ProbeType.PT_DNS;
        if (i == 1) {
            probeResult = ProbeResult.PR_SUCCESS;
        } else {
            probeResult = ProbeResult.PR_FAILURE;
        }
        recordProbeEventMetrics(probeType, jStop, probeResult, null);
        logValidationProbe(jStop, 0, i);
        return inetAddressArrSendDnsProbeWithTimeout;
    }

    private static boolean hasPrivateIpAddress(InetAddress[] inetAddressArr) {
        if (inetAddressArr == null) {
            return false;
        }
        for (InetAddress inetAddress : inetAddressArr) {
            if (inetAddress.isLinkLocalAddress() || inetAddress.isSiteLocalAddress() || NetworkStackUtils.isIPv6ULA(inetAddress)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected android.net.captiveportal.CaptivePortalProbeResult sendHttpProbe(java.net.URL r18, int r19, android.net.captiveportal.CaptivePortalProbeSpec r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.connectivity.NetworkMonitor.sendHttpProbe(java.net.URL, int, android.net.captiveportal.CaptivePortalProbeSpec):android.net.captiveportal.CaptivePortalProbeResult");
    }

    boolean matchesHttpContent(String str, int i) {
        String resStringConfig = getResStringConfig(this.mContext, i, "");
        try {
            return str.matches(resStringConfig);
        } catch (PatternSyntaxException e) {
            Log.e(TAG, "Pattern syntax exception occurs when matching the resource=" + resStringConfig, e);
            return false;
        }
    }

    boolean matchesHttpContentLength(long j) {
        if (j <= 0) {
            return false;
        }
        if (j <= 2147483647L) {
            return j > ((long) getResIntConfig(this.mContext, R.integer.config_min_matches_http_content_length, Integer.MAX_VALUE)) && j < ((long) getResIntConfig(this.mContext, R.integer.config_max_matches_http_content_length, 0));
        }
        logw("matchesHttpContentLength : Get invalid contentLength = " + j);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HttpURLConnection makeProbeConnection(URL url, boolean z) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.mCleartextDnsNetwork.openConnection(url);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestProperty("Connection", "close");
        httpURLConnection.setUseCaches(false);
        String str = this.mCaptivePortalUserAgent;
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        return httpURLConnection;
    }

    protected static String readAsString(InputStream inputStream, int i, Charset charset) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        char[] cArr = new char[INITIAL_REEVALUATE_DELAY_MS];
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStreamReader.read(cArr, 0, Math.min(i - i2, INITIAL_REEVALUATE_DELAY_MS));
            if (i3 < 0) {
                break;
            }
            i2 += i3;
            sb.append(cArr, 0, i3);
        }
        return sb.toString();
    }

    protected static Charset extractCharset(String str) {
        if (str == null) {
            return StandardCharsets.UTF_8;
        }
        Matcher matcher = Pattern.compile("; *charset=\"?([^ ;\"]+)\"?", 2).matcher(str);
        if (!matcher.find()) {
            return StandardCharsets.UTF_8;
        }
        try {
            return Charset.forName(matcher.group(1));
        } catch (IllegalArgumentException unused) {
            return StandardCharsets.UTF_8;
        }
    }

    private class ProbeThread extends Thread {
        private final CountDownLatch mLatch;
        private final Probe mProbe;
        private volatile CaptivePortalProbeResult mResult;

        ProbeThread(NetworkMonitor networkMonitor, CountDownLatch countDownLatch, EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, int i, Uri uri) {
            Probe httpProbe;
            this.mLatch = countDownLatch;
            if (i == 2) {
                httpProbe = networkMonitor.new HttpsProbe(evaluationThreadDeps, proxyInfo, url, uri);
            } else {
                httpProbe = networkMonitor.new HttpProbe(evaluationThreadDeps, proxyInfo, url, uri);
            }
            this.mProbe = httpProbe;
            this.mResult = CaptivePortalProbeResult.failed(i);
        }

        public CaptivePortalProbeResult result() {
            return this.mResult;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.mResult = this.mProbe.sendProbe();
            if (NetworkMonitor.isConclusiveResult(this.mResult, this.mProbe.mCaptivePortalApiUrl)) {
                while (this.mLatch.getCount() > 0) {
                    this.mLatch.countDown();
                }
            }
            this.mLatch.countDown();
        }
    }

    private static abstract class Probe {
        protected final Uri mCaptivePortalApiUrl;
        protected final EvaluationThreadDeps mDeps;
        protected final ProxyInfo mProxy;
        protected final URL mUrl;

        protected abstract CaptivePortalProbeResult sendProbe();

        protected Probe(EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, Uri uri) {
            this.mDeps = evaluationThreadDeps;
            this.mProxy = proxyInfo;
            this.mUrl = url;
            this.mCaptivePortalApiUrl = uri;
        }
    }

    final class HttpsProbe extends Probe {
        HttpsProbe(EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, Uri uri) {
            super(evaluationThreadDeps, proxyInfo, url, uri);
        }

        @Override // com.android.server.connectivity.NetworkMonitor.Probe
        protected CaptivePortalProbeResult sendProbe() {
            return NetworkMonitor.this.sendDnsAndHttpProbes(this.mProxy, this.mUrl, 2);
        }
    }

    final class HttpProbe extends Probe {
        HttpProbe(EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, Uri uri) {
            super(evaluationThreadDeps, proxyInfo, url, uri);
        }

        private CaptivePortalDataShim sendCapportApiProbe() {
            NetworkMonitor.this.validationLog("Fetching captive portal data from " + this.mCaptivePortalApiUrl);
            try {
                URL url = new URL(this.mCaptivePortalApiUrl.toString());
                boolean z = this.mDeps.mIsTestNetwork && "localhost".equals(url.getHost()) && "http".equals(url.getProtocol());
                if ("https".equals(url.getProtocol()) || z) {
                    HttpURLConnection httpURLConnectionMakeProbeConnection = NetworkMonitor.this.makeProbeConnection(url, true);
                    httpURLConnectionMakeProbeConnection.setRequestProperty("Accept", "application/captive+json");
                    if (httpURLConnectionMakeProbeConnection.getResponseCode() != 200) {
                        NetworkMonitor.this.validationLog("Non-200 API response code: " + httpURLConnectionMakeProbeConnection.getResponseCode());
                        return null;
                    }
                    Charset charsetExtractCharset = NetworkMonitor.extractCharset(httpURLConnectionMakeProbeConnection.getHeaderField("Content-Type"));
                    if (charsetExtractCharset != StandardCharsets.UTF_8) {
                        NetworkMonitor.this.validationLog("Invalid charset for capport API: " + charsetExtractCharset);
                        return null;
                    }
                    try {
                        CaptivePortalDataShim captivePortalDataShimFromJson = CaptivePortalDataShimImpl.fromJson(new JSONObject(NetworkMonitor.readAsString(httpURLConnectionMakeProbeConnection.getInputStream(), 4096, charsetExtractCharset)));
                        if (captivePortalDataShimFromJson == null || !captivePortalDataShimFromJson.isCaptive() || captivePortalDataShimFromJson.getUserPortalUrl() != null) {
                            return captivePortalDataShimFromJson;
                        }
                        NetworkMonitor.this.validationLog("Missing user-portal-url from capport response");
                        return null;
                    } catch (UnsupportedApiLevelException unused) {
                        NetworkMonitor.this.validationLog("Platform API too low to support capport API");
                        return null;
                    } catch (JSONException e) {
                        NetworkMonitor.this.validationLog("Could not parse capport API JSON: " + e.getMessage());
                        return null;
                    }
                }
                NetworkMonitor.this.validationLog("Invalid captive portal API protocol: " + url.getProtocol());
                return null;
            } catch (IOException e2) {
                NetworkMonitor.this.validationLog("I/O error reading capport data: " + e2.getMessage());
                return null;
            }
        }

        private CaptivePortalDataShim tryCapportApiProbe() {
            if (this.mCaptivePortalApiUrl == null) {
                return null;
            }
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();
            CaptivePortalDataShim captivePortalDataShimSendCapportApiProbe = sendCapportApiProbe();
            NetworkMonitor.this.recordProbeEventMetrics(ProbeType.PT_CAPPORT_API, stopwatch.stop(), captivePortalDataShimSendCapportApiProbe == null ? ProbeResult.PR_FAILURE : ProbeResult.PR_SUCCESS, captivePortalDataShimSendCapportApiProbe);
            return captivePortalDataShimSendCapportApiProbe;
        }

        @Override // com.android.server.connectivity.NetworkMonitor.Probe
        protected CaptivePortalProbeResult sendProbe() {
            CaptivePortalDataShim captivePortalDataShimTryCapportApiProbe = tryCapportApiProbe();
            if (captivePortalDataShimTryCapportApiProbe == null || !captivePortalDataShimTryCapportApiProbe.isCaptive()) {
                CaptivePortalProbeResult captivePortalProbeResultSendDnsAndHttpProbes = NetworkMonitor.this.sendDnsAndHttpProbes(this.mProxy, this.mUrl, 1);
                return this.mCaptivePortalApiUrl == null ? captivePortalProbeResultSendDnsAndHttpProbes : new CapportApiProbeResult(captivePortalProbeResultSendDnsAndHttpProbes, captivePortalDataShimTryCapportApiProbe);
            }
            String string = captivePortalDataShimTryCapportApiProbe.getUserPortalUrl().toString();
            return new CapportApiProbeResult(302, string, string, captivePortalDataShimTryCapportApiProbe, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isConclusiveResult(CaptivePortalProbeResult captivePortalProbeResult, Uri uri) {
        return captivePortalProbeResult.isPortal() || (captivePortalProbeResult.isConcludedFromHttps() && captivePortalProbeResult.isSuccessful() && uri == null);
    }

    private CaptivePortalProbeResult sendMultiParallelHttpAndHttpsProbes(final EvaluationThreadDeps evaluationThreadDeps, final ProxyInfo proxyInfo, URL[] urlArr, URL[] urlArr2) {
        int length = urlArr.length + urlArr2.length;
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(Math.min(length, 5));
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorServiceNewFixedThreadPool);
        final Uri captivePortalApiUrl = getCaptivePortalApiUrl(this.mLinkProperties);
        ArrayList arrayList = new ArrayList();
        try {
            try {
                final URL url = urlArr2[0];
                int i = 0;
                for (int length2 = urlArr2.length; i < length2; length2 = length2) {
                    final URL url2 = urlArr2[i];
                    arrayList.add(executorCompletionService.submit(new Callable() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$J_s5Y4yJ8e_AeYK4Y4M5NgBdbKg
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return this.f$0.lambda$sendMultiParallelHttpAndHttpsProbes$2$NetworkMonitor(evaluationThreadDeps, proxyInfo, url2, url, captivePortalApiUrl);
                        }
                    }));
                    i++;
                }
                for (final URL url3 : urlArr) {
                    arrayList.add(executorCompletionService.submit(new Callable() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$9FvCBIoPQKAAYPNDGLy3ff0FUSQ
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return this.f$0.lambda$sendMultiParallelHttpAndHttpsProbes$3$NetworkMonitor(evaluationThreadDeps, proxyInfo, url3, captivePortalApiUrl);
                        }
                    }));
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < length; i2++) {
                    arrayList2.add((CaptivePortalProbeResult) executorCompletionService.take().get());
                    CaptivePortalProbeResult captivePortalProbeResultEvaluateCapportResult = evaluateCapportResult(arrayList2, urlArr.length, captivePortalApiUrl != null);
                    if (captivePortalProbeResultEvaluateCapportResult != null) {
                        reportProbeResult(captivePortalProbeResultEvaluateCapportResult);
                        return captivePortalProbeResultEvaluateCapportResult;
                    }
                }
            } catch (InterruptedException unused) {
            } catch (ExecutionException e) {
                Log.e(TAG, "Error sending probes.", e);
            }
            return CaptivePortalProbeResult.failed(2);
        } finally {
            arrayList.forEach(new Consumer() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$zRhK33kNbw_1t3WADdLG52_NejY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Future) obj).cancel(true);
                }
            });
            executorServiceNewFixedThreadPool.shutdownNow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$sendMultiParallelHttpAndHttpsProbes$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CaptivePortalProbeResult lambda$sendMultiParallelHttpAndHttpsProbes$2$NetworkMonitor(EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, URL url2, Uri uri) throws Exception {
        if (!url.equals(url2)) {
            uri = null;
        }
        return new HttpProbe(evaluationThreadDeps, proxyInfo, url, uri).sendProbe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$sendMultiParallelHttpAndHttpsProbes$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CaptivePortalProbeResult lambda$sendMultiParallelHttpAndHttpsProbes$3$NetworkMonitor(EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, Uri uri) throws Exception {
        return new HttpsProbe(evaluationThreadDeps, proxyInfo, url, uri).sendProbe();
    }

    private CaptivePortalProbeResult evaluateCapportResult(List<CaptivePortalProbeResult> list, int i, boolean z) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        CaptivePortalProbeResult captivePortalProbeResult = null;
        CaptivePortalProbeResult captivePortalProbeResult2 = null;
        for (CaptivePortalProbeResult captivePortalProbeResult3 : list) {
            if (captivePortalProbeResult3 instanceof CapportApiProbeResult) {
                captivePortalProbeResult = captivePortalProbeResult3;
            } else if (captivePortalProbeResult3.isConcludedFromHttps()) {
                if (captivePortalProbeResult3.isSuccessful()) {
                    i2++;
                } else {
                    i3++;
                }
            } else if (captivePortalProbeResult3.isPortal()) {
                captivePortalProbeResult2 = captivePortalProbeResult3;
            } else if (captivePortalProbeResult3.isSuccessful()) {
                i4++;
            }
        }
        if (z && captivePortalProbeResult == null) {
            return null;
        }
        if (captivePortalProbeResult != null && captivePortalProbeResult.isPortal()) {
            return captivePortalProbeResult;
        }
        if (captivePortalProbeResult2 != null) {
            return captivePortalProbeResult2;
        }
        if (i2 > 0) {
            return CaptivePortalProbeResult.success(4);
        }
        if (i3 != i || i4 <= 0) {
            return null;
        }
        return CaptivePortalProbeResult.PARTIAL;
    }

    private void reportProbeResult(CaptivePortalProbeResult captivePortalProbeResult) {
        if (captivePortalProbeResult instanceof CapportApiProbeResult) {
            maybeReportCaptivePortalData(((CapportApiProbeResult) captivePortalProbeResult).getCaptivePortalData());
        }
        if (captivePortalProbeResult.isConcludedFromHttps()) {
            reportHttpProbeResult(16, captivePortalProbeResult);
        }
        if (captivePortalProbeResult.isConcludedFromHttp()) {
            reportHttpProbeResult(8, captivePortalProbeResult);
        }
    }

    private CaptivePortalProbeResult sendHttpAndHttpsParallelWithFallbackProbes(EvaluationThreadDeps evaluationThreadDeps, ProxyInfo proxyInfo, URL url, URL url2) throws Throwable {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Uri captivePortalApiUrl = getCaptivePortalApiUrl(this.mLinkProperties);
        ProbeThread probeThread = new ProbeThread(this, countDownLatch, evaluationThreadDeps, proxyInfo, url, 2, captivePortalApiUrl);
        ProbeThread probeThread2 = new ProbeThread(this, countDownLatch, evaluationThreadDeps, proxyInfo, url2, 1, captivePortalApiUrl);
        try {
            probeThread.start();
            probeThread2.start();
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
            CaptivePortalProbeResult captivePortalProbeResultResult = probeThread.result();
            CaptivePortalProbeResult captivePortalProbeResultResult2 = probeThread2.result();
            if (isConclusiveResult(captivePortalProbeResultResult2, captivePortalApiUrl)) {
                reportProbeResult(probeThread2.result());
                return captivePortalProbeResultResult2;
            }
            if (isConclusiveResult(captivePortalProbeResultResult, captivePortalApiUrl)) {
                reportProbeResult(probeThread.result());
                return captivePortalProbeResultResult;
            }
            if (this.mPrivateIpNoInternetEnabled && captivePortalProbeResultResult2.isDnsPrivateIpResponse()) {
                validationLog("DNS response to the URL is private IP");
                return CaptivePortalProbeResult.failed(2);
            }
            CaptivePortalProbeSpec captivePortalProbeSpecNextFallbackSpec = nextFallbackSpec();
            URL url3 = captivePortalProbeSpecNextFallbackSpec != null ? captivePortalProbeSpecNextFallbackSpec.getUrl() : nextFallbackUrl();
            CaptivePortalProbeResult captivePortalProbeResultSendHttpProbe = null;
            if (url3 != null) {
                captivePortalProbeResultSendHttpProbe = sendHttpProbe(url3, 4, captivePortalProbeSpecNextFallbackSpec);
                reportHttpProbeResult(32, captivePortalProbeResultSendHttpProbe);
                if (captivePortalProbeResultSendHttpProbe.isPortal()) {
                    return captivePortalProbeResultSendHttpProbe;
                }
            }
            try {
                probeThread2.join();
                reportProbeResult(probeThread2.result());
                if (probeThread2.result().isPortal()) {
                    return probeThread2.result();
                }
                probeThread.join();
                reportHttpProbeResult(16, probeThread.result());
                boolean z = probeThread2.result().isSuccessful() || (captivePortalProbeResultSendHttpProbe != null && captivePortalProbeResultSendHttpProbe.isSuccessful());
                if (probeThread.result().isFailed() && z) {
                    return CaptivePortalProbeResult.PARTIAL;
                }
                return probeThread.result();
            } catch (InterruptedException unused) {
                validationLog("Error: http or https probe wait interrupted!");
                return CaptivePortalProbeResult.failed(0);
            }
        } catch (InterruptedException unused2) {
            validationLog("Error: probes wait interrupted!");
            return CaptivePortalProbeResult.failed(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public URL makeURL(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            validationLog("Bad URL: " + str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logNetworkEvent(int i) {
        this.mMetricsLog.log(this.mCleartextDnsNetwork, this.mNetworkCapabilities.getTransportTypes(), new NetworkEvent(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int networkEventType(ValidationStage validationStage, EvaluationResult evaluationResult) {
        return validationStage.mIsFirstValidation ? evaluationResult.mIsValidated ? 8 : 10 : evaluationResult.mIsValidated ? 9 : 11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeLogEvaluationResult(int i) {
        if (this.mEvaluationTimer.isRunning()) {
            this.mMetricsLog.log(this.mCleartextDnsNetwork, this.mNetworkCapabilities.getTransportTypes(), new NetworkEvent(i, this.mEvaluationTimer.stop() / 1000));
            this.mEvaluationTimer.reset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logValidationProbe(long j, int i, int i2) {
        this.mMetricsLog.log(this.mCleartextDnsNetwork, this.mNetworkCapabilities.getTransportTypes(), new ValidationProbeEvent.Builder().setProbeType(i, validationStage().mIsFirstValidation).setReturnCode(i2).setDurationMs(j / 1000).build());
    }

    public static class Dependencies {
        public static final Dependencies DEFAULT = new Dependencies();

        public Network getPrivateDnsBypassNetwork(Network network) {
            return new OneAddressPerFamilyNetwork(network);
        }

        public DnsResolver getDnsResolver() {
            return DnsResolver.getInstance();
        }

        public Random getRandom() {
            return new Random();
        }

        public int getSetting(Context context, String str, int i) {
            return Settings.Global.getInt(context.getContentResolver(), str, i);
        }

        public String getSetting(Context context, String str, String str2) {
            String string = Settings.Global.getString(context.getContentResolver(), str);
            return string != null ? string : str2;
        }

        public String getDeviceConfigProperty(String str, String str2, String str3) {
            return NetworkStackUtils.getDeviceConfigProperty(str, str2, str3);
        }

        public int getDeviceConfigPropertyInt(String str, String str2, int i) {
            return NetworkStackUtils.getDeviceConfigPropertyInt(str, str2, i);
        }

        public boolean isFeatureEnabled(Context context, String str) {
            return NetworkStackUtils.isFeatureEnabled(context, "connectivity", str);
        }

        public boolean isFeatureEnabled(Context context, String str, String str2, boolean z) {
            return NetworkStackUtils.isFeatureEnabled(context, str, str2, z);
        }

        public void writeDataStallDetectionStats(DataStallDetectionStats dataStallDetectionStats, CaptivePortalProbeResult captivePortalProbeResult) {
            DataStallStatsUtils.write(dataStallDetectionStats, captivePortalProbeResult);
        }
    }

    protected class DnsStallDetector {
        private int mConsecutiveTimeoutCount = 0;
        final DnsResult[] mDnsEvents;
        final RingBufferIndices mResultIndices;
        private int mSize;

        DnsStallDetector(NetworkMonitor networkMonitor, int i) {
            int iMax = Math.max(20, i);
            this.mSize = iMax;
            this.mDnsEvents = new DnsResult[iMax];
            this.mResultIndices = new RingBufferIndices(iMax);
        }

        protected void accumulateConsecutiveDnsTimeoutCount(int i) {
            DnsResult dnsResult = new DnsResult(i);
            this.mDnsEvents[this.mResultIndices.add()] = dnsResult;
            if (dnsResult.isTimeout()) {
                this.mConsecutiveTimeoutCount++;
            } else {
                this.mConsecutiveTimeoutCount = 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDataStallSuspected(int i, int i2) {
            if (i <= 0) {
                Log.wtf(NetworkMonitor.TAG, "Timeout count threshold should be larger than 0.");
                return false;
            }
            if (this.mConsecutiveTimeoutCount < i) {
                return false;
            }
            RingBufferIndices ringBufferIndices = this.mResultIndices;
            return SystemClock.elapsedRealtime() - this.mDnsEvents[ringBufferIndices.indexOf(ringBufferIndices.size() - i)].mTimeStamp < ((long) i2);
        }

        int getConsecutiveTimeoutCount() {
            return this.mConsecutiveTimeoutCount;
        }
    }

    private static class DnsResult {
        private final int mReturnCode;
        private final long mTimeStamp = SystemClock.elapsedRealtime();

        DnsResult(int i) {
            this.mReturnCode = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isTimeout() {
            return this.mReturnCode == 255;
        }
    }

    protected DnsStallDetector getDnsStallDetector() {
        return this.mDnsStallDetector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TcpSocketTracker getTcpSocketTracker() {
        return this.mTcpTracker;
    }

    private boolean dataStallEvaluateTypeEnabled(int i) {
        return (this.mDataStallEvaluationType & i) != 0;
    }

    protected long getLastProbeTime() {
        return this.mLastProbeTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Boolean, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    protected boolean isDataStall() {
        ?? r0 = Boolean.TRUE;
        if (!isValidationRequired()) {
            return false;
        }
        ?? r1 = 0;
        ?? r12 = 0;
        r1 = 0;
        StringJoiner stringJoiner = new StringJoiner(", ");
        if (!this.mNetworkCapabilities.hasCapability(11) && SystemClock.elapsedRealtime() - getLastProbeTime() < this.mDataStallMinEvaluateTime) {
            return false;
        }
        TcpSocketTracker tcpSocketTracker = getTcpSocketTracker();
        if (dataStallEvaluateTypeEnabled(2) && tcpSocketTracker != null) {
            if (tcpSocketTracker.getLatestReceivedCount() > 0) {
                r12 = Boolean.FALSE;
            } else if (tcpSocketTracker.isDataStallSuspected()) {
                this.mDataStallTypeToCollect = 2;
                DataStallReportParcelable dataStallReportParcelable = new DataStallReportParcelable();
                dataStallReportParcelable.detectionMethod = 2;
                dataStallReportParcelable.timestampMillis = SystemClock.elapsedRealtime();
                dataStallReportParcelable.tcpPacketFailRate = tcpSocketTracker.getLatestPacketFailPercentage();
                dataStallReportParcelable.tcpMetricsCollectionPeriodMillis = getTcpPollingInterval();
                notifyDataStallSuspected(dataStallReportParcelable);
                r12 = r0;
            }
            stringJoiner.add("tcp packets received=" + tcpSocketTracker.getLatestReceivedCount()).add("latest tcp fail rate=" + tcpSocketTracker.getLatestPacketFailPercentage());
            r1 = r12;
        }
        DnsStallDetector dnsStallDetector = getDnsStallDetector();
        ?? r13 = r1;
        r13 = r1;
        if (r1 == 0 && dnsStallDetector != null) {
            r13 = r1;
            if (dataStallEvaluateTypeEnabled(1)) {
                ?? r14 = r1;
                if (dnsStallDetector.isDataStallSuspected(this.mConsecutiveDnsTimeoutThreshold, this.mDataStallValidDnsTimeThreshold)) {
                    this.mDataStallTypeToCollect = 1;
                    logNetworkEvent(12);
                    DataStallReportParcelable dataStallReportParcelable2 = new DataStallReportParcelable();
                    dataStallReportParcelable2.detectionMethod = 1;
                    dataStallReportParcelable2.timestampMillis = SystemClock.elapsedRealtime();
                    dataStallReportParcelable2.dnsConsecutiveTimeouts = this.mDnsStallDetector.getConsecutiveTimeoutCount();
                    notifyDataStallSuspected(dataStallReportParcelable2);
                    r14 = r0;
                }
                stringJoiner.add("consecutive dns timeout count=" + dnsStallDetector.getConsecutiveTimeoutCount());
                r13 = r14;
            }
        }
        if (r0.equals(r13) || VDBG_STALL) {
            log("isDataStall: result=" + r13 + ", " + stringJoiner);
        }
        if (r13 == 0) {
            return false;
        }
        return r13.booleanValue();
    }

    protected class EvaluationState {
        private int mEvaluationResult = 0;
        private int mProbeResults = 0;
        private int mProbeCompleted = 0;

        protected EvaluationState() {
        }

        protected void clearProbeResults() {
            this.mProbeResults = 0;
            this.mProbeCompleted = 0;
        }

        private void maybeNotifyProbeResults(Runnable runnable) {
            int i = this.mProbeCompleted;
            int i2 = this.mProbeResults;
            runnable.run();
            if (i == this.mProbeCompleted && i2 == this.mProbeResults) {
                return;
            }
            NetworkMonitor.this.notifyProbeStatusChanged(this.mProbeCompleted, this.mProbeResults);
        }

        protected void removeProbeResult(final int i) {
            maybeNotifyProbeResults(new Runnable() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$EvaluationState$7WPhI1qPZX5lUHEYG5YFrvRPFy8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$removeProbeResult$0$NetworkMonitor$EvaluationState(i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$removeProbeResult$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$removeProbeResult$0$NetworkMonitor$EvaluationState(int i) {
            int i2 = this.mProbeCompleted;
            int i3 = ~i;
            this.mProbeCompleted = i2 & i3;
            this.mProbeResults = i3 & this.mProbeResults;
        }

        protected void noteProbeResult(final int i, final boolean z) {
            maybeNotifyProbeResults(new Runnable() { // from class: com.android.server.connectivity.-$$Lambda$NetworkMonitor$EvaluationState$rQkxdGuIcksnkRfRoPmDllN1Fe8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$noteProbeResult$1$NetworkMonitor$EvaluationState(i, z);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$noteProbeResult$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$noteProbeResult$1$NetworkMonitor$EvaluationState(int i, boolean z) {
            this.mProbeCompleted |= i;
            if (z) {
                this.mProbeResults = i | this.mProbeResults;
            } else {
                this.mProbeResults = (~i) & this.mProbeResults;
            }
        }

        protected void reportEvaluationResult(int i, String str) {
            this.mEvaluationResult = i;
            NetworkTestResultParcelable networkTestResultParcelable = new NetworkTestResultParcelable();
            networkTestResultParcelable.result = i;
            networkTestResultParcelable.probesSucceeded = this.mProbeResults;
            networkTestResultParcelable.probesAttempted = this.mProbeCompleted;
            networkTestResultParcelable.redirectUrl = str;
            networkTestResultParcelable.timestampMillis = SystemClock.elapsedRealtime();
            NetworkMonitor.this.notifyNetworkTested(networkTestResultParcelable);
            NetworkMonitor.this.recordValidationResult(i, str);
        }

        protected int getEvaluationResult() {
            return this.mEvaluationResult;
        }

        protected int getProbeResults() {
            return this.mProbeResults;
        }

        protected int getProbeCompletedResult() {
            return this.mProbeCompleted;
        }
    }

    protected EvaluationState getEvaluationState() {
        return this.mEvaluationState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeDisableHttpsProbing(boolean z) {
        this.mAcceptPartialConnectivity = z;
        if ((this.mEvaluationState.getEvaluationResult() & 2) == 0 || !this.mAcceptPartialConnectivity) {
            return;
        }
        this.mUseHttps = false;
    }

    protected void reportHttpProbeResult(int i, CaptivePortalProbeResult captivePortalProbeResult) {
        boolean zIsSuccessful = captivePortalProbeResult.isSuccessful();
        if (zIsSuccessful) {
            i |= 4;
        }
        this.mEvaluationState.noteProbeResult(i, zIsSuccessful);
    }

    private void maybeReportCaptivePortalData(CaptivePortalDataShim captivePortalDataShim) {
        if (captivePortalDataShim == null) {
            return;
        }
        try {
            captivePortalDataShim.notifyChanged(this.mCallback);
        } catch (RemoteException e) {
            Log.e(TAG, "Error notifying ConnectivityService of new capport data", e);
        }
    }

    private static TcpSocketTracker getTcpSocketTrackerOrNull(Context context, Network network) {
        if ((Dependencies.DEFAULT.getDeviceConfigPropertyInt("connectivity", "data_stall_evaluation_type", 3) & 2) != 0) {
            return new TcpSocketTracker(new TcpSocketTracker.Dependencies(context), network);
        }
        return null;
    }

    private DnsStallDetector initDnsStallDetectorIfRequired(int i, int i2) {
        if ((i & 1) != 0) {
            return new DnsStallDetector(this, i2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Uri getCaptivePortalApiUrl(LinkProperties linkProperties) {
        return NetworkInformationShimImpl.newInstance().getCaptivePortalApiUrl(linkProperties);
    }
}
