package io.branch.referral;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ImagesContract;
import com.visualon.OSMPUtils.voOSType;
import io.branch.indexing.ContentDiscoverer;
import io.branch.referral.BranchStrongMatchHelper;
import io.branch.referral.BranchViewHandler;
import io.branch.referral.DeferredAppLinkDataHandler;
import io.branch.referral.InstallListener;
import io.branch.referral.ServerRequest;
import io.branch.referral.SystemObserver;
import io.branch.referral.network.BranchRemoteInterface;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class Branch implements BranchViewHandler.IBranchViewEvents, SystemObserver.AdsParamsFetchEvents, InstallListener.IInstallReferrerEvents {
    private static Branch branchReferral_ = null;
    private static boolean bypassCurrentActivityIntentState_ = false;
    static boolean checkInstallReferrer_ = true;
    private static boolean disableDeviceIDFetch_ = false;
    private static boolean isActivityLifeCycleCallbackRegistered_ = false;
    private static boolean isAutoSessionMode_ = false;
    static boolean isForcedSession_ = false;
    private static boolean isSimulatingInstalls_ = false;
    private static long playStoreReferrerFetchTime = 1500;
    private BranchRemoteInterface branchRemoteInterface_;
    private Context context_;
    WeakReference<Activity> currentActivityReference_;
    private JSONObject deeplinkDebugParams_;
    private WeakReference<BranchReferralInitListener> deferredInitListener_;
    private final DeviceInfo deviceInfo_;
    private boolean enableFacebookAppLinkCheck_ = false;
    private CountDownLatch getFirstReferringParamsLatch;
    private CountDownLatch getLatestReferringParamsLatch;
    private boolean handleDelayedNewIntents_;
    private boolean hasNetwork_;
    private SESSION_STATE initState_;
    private final ConcurrentHashMap<String, String> instrumentationExtraData_;
    private INTENT_STATE intentState_;
    private boolean isActivityCreatedAndLaunched;
    private boolean isGAParamsFetchInProgress_;
    boolean isInitReportedThroughCallBack;
    boolean isInstantDeepLinkPossible;
    private Map<BranchLinkData, String> linkCache_;
    final Object lock;
    private int networkCount_;
    private boolean performCookieBasedStrongMatchingOnGAIDAvailable;
    private PrefHelper prefHelper_;
    private final ServerRequestQueue requestQueue_;
    private Semaphore serverSema_;
    private ShareLinkManager shareLinkManager_;
    private final TrackingController trackingController;
    private static CUSTOM_REFERRABLE_SETTINGS customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
    private static String cookieBasedMatchDomain_ = "app.link";
    private static int LATCH_WAIT_UNTIL = 2500;
    private static final String[] EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST = {"extra_launch_uri", "branch_intent"};
    private static boolean disableInstantDeepLinking = true;

    public interface BranchLinkCreateListener {
        void onLinkCreate(String str, BranchError branchError);
    }

    public interface BranchListResponseListener {
        void onReceivingResponse(JSONArray jSONArray, BranchError branchError);
    }

    public interface BranchReferralInitListener {
        void onInitFinished(JSONObject jSONObject, BranchError branchError);
    }

    public interface BranchReferralStateChangedListener {
        void onStateChanged(boolean z, BranchError branchError);
    }

    private enum CUSTOM_REFERRABLE_SETTINGS {
        USE_DEFAULT,
        REFERRABLE,
        NON_REFERRABLE
    }

    public interface IBranchViewControl {
        boolean skipBranchViewsOnThisActivity();
    }

    private enum INTENT_STATE {
        PENDING,
        READY
    }

    public interface LogoutStatusListener {
        void onLogoutFinished(boolean z, BranchError branchError);
    }

    private enum SESSION_STATE {
        INITIALISED,
        INITIALISING,
        UNINITIALISED
    }

    @Override // io.branch.referral.BranchViewHandler.IBranchViewEvents
    public void onBranchViewVisible(String str, String str2) {
    }

    private Branch(@NonNull Context context) {
        INTENT_STATE intent_state = INTENT_STATE.PENDING;
        this.intentState_ = intent_state;
        this.handleDelayedNewIntents_ = false;
        this.initState_ = SESSION_STATE.UNINITIALISED;
        this.isInitReportedThroughCallBack = false;
        this.isGAParamsFetchInProgress_ = false;
        this.getFirstReferringParamsLatch = null;
        this.getLatestReferringParamsLatch = null;
        this.performCookieBasedStrongMatchingOnGAIDAvailable = false;
        this.isInstantDeepLinkPossible = false;
        this.isActivityCreatedAndLaunched = false;
        this.prefHelper_ = PrefHelper.getInstance(context);
        TrackingController trackingController = new TrackingController(context);
        this.trackingController = trackingController;
        this.branchRemoteInterface_ = BranchRemoteInterface.getDefaultBranchRemoteInterface(context);
        DeviceInfo deviceInfoInitialize = DeviceInfo.initialize(context);
        this.deviceInfo_ = deviceInfoInitialize;
        this.requestQueue_ = ServerRequestQueue.getInstance(context);
        this.serverSema_ = new Semaphore(1);
        this.lock = new Object();
        this.networkCount_ = 0;
        this.hasNetwork_ = true;
        this.linkCache_ = new HashMap();
        this.instrumentationExtraData_ = new ConcurrentHashMap<>();
        if (!trackingController.isTrackingDisabled()) {
            this.isGAParamsFetchInProgress_ = deviceInfoInitialize.getSystemObserver().prefetchAdsParams(context, this);
        }
        this.handleDelayedNewIntents_ = true;
        this.intentState_ = intent_state;
    }

    public boolean isTrackingDisabled() {
        return this.trackingController.isTrackingDisabled();
    }

    @TargetApi(14)
    public static Branch getInstance() {
        if (branchReferral_ == null) {
            PrefHelper.Debug("Branch instance is not created yet. Make sure you have initialised Branch. [Consider Calling getInstance(Context ctx) if you still have issue.]");
        } else if (isAutoSessionMode_ && !isActivityLifeCycleCallbackRegistered_) {
            PrefHelper.Debug("Branch instance is not properly initialised. Make sure your Application class is extending BranchApp class. If you are not extending BranchApp class make sure you are initialising Branch in your Applications onCreate()");
        }
        return branchReferral_;
    }

    private static Branch getBranchInstance(@NonNull Context context, boolean z, String str) {
        boolean branchKey;
        if (branchReferral_ == null) {
            branchReferral_ = initInstance(context);
            boolean zCheckTestMode = BranchUtil.checkTestMode(context);
            if (z) {
                zCheckTestMode = false;
            }
            BranchUtil.setTestMode(zCheckTestMode);
            if (TextUtils.isEmpty(str)) {
                str = BranchUtil.readBranchKey(context);
            }
            if (TextUtils.isEmpty(str)) {
                String string = null;
                try {
                    Resources resources = context.getResources();
                    string = resources.getString(resources.getIdentifier("io.branch.apiKey", "string", context.getPackageName()));
                } catch (Exception unused) {
                }
                if (!TextUtils.isEmpty(string)) {
                    branchKey = branchReferral_.prefHelper_.setBranchKey(string);
                } else {
                    PrefHelper.Debug("Warning: Please enter your branch_key in your project's Manifest file!");
                    branchKey = branchReferral_.prefHelper_.setBranchKey("bnc_no_value");
                }
            } else {
                branchKey = branchReferral_.prefHelper_.setBranchKey(str);
            }
            if (branchKey) {
                branchReferral_.linkCache_.clear();
                branchReferral_.requestQueue_.clear();
            }
            branchReferral_.context_ = context.getApplicationContext();
            if (context instanceof Application) {
                isAutoSessionMode_ = true;
                branchReferral_.setActivityLifeCycleObserver((Application) context);
            }
        }
        return branchReferral_;
    }

    public static Branch getInstance(@NonNull Context context) {
        return getBranchInstance(context, true, null);
    }

    @TargetApi(14)
    public static Branch getAutoInstance(@NonNull Context context) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        getBranchInstance(context, true ^ BranchUtil.checkTestMode(context), null);
        BranchPreinstall.getPreinstallSystemData(branchReferral_, context);
        return branchReferral_;
    }

    private static Branch initInstance(@NonNull Context context) {
        return new Branch(context.getApplicationContext());
    }

    public void setRetryCount(int i) {
        PrefHelper prefHelper = this.prefHelper_;
        if (prefHelper == null || i < 0) {
            return;
        }
        prefHelper.setRetryCount(i);
    }

    public void setRetryInterval(int i) {
        PrefHelper prefHelper = this.prefHelper_;
        if (prefHelper == null || i <= 0) {
            return;
        }
        prefHelper.setRetryInterval(i);
    }

    public void setNetworkTimeout(int i) {
        PrefHelper prefHelper = this.prefHelper_;
        if (prefHelper == null || i <= 0) {
            return;
        }
        prefHelper.setTimeout(i);
    }

    public static void disableDeviceIDFetch(Boolean bool) {
        disableDeviceIDFetch_ = bool.booleanValue();
    }

    public static boolean isDeviceIDFetchDisabled() {
        return disableDeviceIDFetch_;
    }

    public void setRequestMetadata(@NonNull String str, @NonNull String str2) {
        this.prefHelper_.setRequestMetadata(str, str2);
    }

    public Branch addInstallMetadata(@NonNull String str, @NonNull String str2) {
        this.prefHelper_.addInstallMetadata(str, str2);
        return this;
    }

    public Branch setPreinstallCampaign(@NonNull String str) {
        addInstallMetadata(Defines$PreinstallKey.campaign.getKey(), str);
        return this;
    }

    public Branch setPreinstallPartner(@NonNull String str) {
        addInstallMetadata(Defines$PreinstallKey.partner.getKey(), str);
        return this;
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, Activity activity) {
        if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            initUserSessionInternal(branchReferralInitListener, activity, true);
        } else {
            initUserSessionInternal(branchReferralInitListener, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, Uri uri, Activity activity) {
        readAndStripParam(uri, activity);
        return initSession(branchReferralInitListener, activity);
    }

    private void initUserSessionInternal(BranchReferralInitListener branchReferralInitListener, Activity activity, boolean z) {
        if (activity != null) {
            this.currentActivityReference_ = new WeakReference<>(activity);
        }
        if (branchReferralInitListener != null) {
            this.deferredInitListener_ = new WeakReference<>(branchReferralInitListener);
        }
        if (hasUser() && hasSession() && this.initState_ == SESSION_STATE.INITIALISED) {
            reportInitSession(branchReferralInitListener);
            this.isInstantDeepLinkPossible = false;
            return;
        }
        if (this.isInstantDeepLinkPossible && reportInitSession(branchReferralInitListener)) {
            addExtraInstrumentationData(Defines$Jsonkey.InstantDeepLinkSession.getKey(), "true");
            this.isInstantDeepLinkPossible = false;
            checkForAutoDeepLinkConfiguration();
        }
        if (z) {
            this.prefHelper_.setIsReferrable();
        } else {
            this.prefHelper_.clearIsReferrable();
        }
        SESSION_STATE session_state = this.initState_;
        SESSION_STATE session_state2 = SESSION_STATE.INITIALISING;
        if (session_state != session_state2) {
            this.initState_ = session_state2;
            initializeSession(branchReferralInitListener);
        } else if (branchReferralInitListener != null) {
            this.requestQueue_.setInstallOrOpenCallback(branchReferralInitListener);
        }
    }

    private boolean reportInitSession(BranchReferralInitListener branchReferralInitListener) {
        if (branchReferralInitListener != null) {
            if (isAutoSessionMode_ && !this.isInitReportedThroughCallBack) {
                branchReferralInitListener.onInitFinished(getLatestReferringParams(), null);
                this.isInitReportedThroughCallBack = true;
            } else {
                branchReferralInitListener.onInitFinished(new JSONObject(), null);
            }
        }
        return this.isInitReportedThroughCallBack;
    }

    void closeSessionInternal() {
        executeClose();
        resetSessionReferredLink();
        this.trackingController.updateTrackingState(this.context_);
    }

    private void executeClose() {
        SESSION_STATE session_state = this.initState_;
        SESSION_STATE session_state2 = SESSION_STATE.UNINITIALISED;
        if (session_state != session_state2) {
            if (!this.hasNetwork_) {
                ServerRequest serverRequestPeek = this.requestQueue_.peek();
                if ((serverRequestPeek != null && (serverRequestPeek instanceof ServerRequestRegisterInstall)) || (serverRequestPeek instanceof ServerRequestRegisterOpen)) {
                    this.requestQueue_.dequeue();
                }
            } else if (!this.requestQueue_.containsClose()) {
                handleNewRequest(new ServerRequestRegisterClose(this.context_));
            }
            this.initState_ = session_state2;
        }
    }

    private boolean readAndStripParam(Uri uri, Activity activity) {
        String string;
        String str;
        if (!disableInstantDeepLinking && ((this.intentState_ == INTENT_STATE.READY || this.isActivityCreatedAndLaunched) && activity != null && activity.getIntent() != null && this.initState_ != SESSION_STATE.INITIALISED && !checkIntentForSessionRestart(activity.getIntent()))) {
            Intent intent = activity.getIntent();
            if (intent.getData() == null || (!this.isActivityCreatedAndLaunched && isIntentParamsAlreadyConsumed(activity))) {
                if (!this.prefHelper_.getInstallParams().equals("bnc_no_value")) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(Defines$Jsonkey.Clicked_Branch_Link.getKey(), false);
                        jSONObject.put(Defines$Jsonkey.IsFirstSession.getKey(), false);
                        this.prefHelper_.setSessionParams(jSONObject.toString());
                        this.isInstantDeepLinkPossible = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.BranchData;
                if (!TextUtils.isEmpty(intent.getStringExtra(defines$Jsonkey.getKey()))) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(intent.getStringExtra(defines$Jsonkey.getKey()));
                        jSONObject2.put(Defines$Jsonkey.Clicked_Branch_Link.getKey(), true);
                        this.prefHelper_.setSessionParams(jSONObject2.toString());
                        this.isInstantDeepLinkPossible = true;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    intent.removeExtra(Defines$Jsonkey.BranchData.getKey());
                    activity.setIntent(intent);
                } else if (uri.getQueryParameterNames() != null && Boolean.valueOf(uri.getQueryParameter(Defines$Jsonkey.Instant.getKey())).booleanValue()) {
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        for (String str2 : uri.getQueryParameterNames()) {
                            jSONObject3.put(str2, uri.getQueryParameter(str2));
                        }
                        jSONObject3.put(Defines$Jsonkey.Clicked_Branch_Link.getKey(), true);
                        this.prefHelper_.setSessionParams(jSONObject3.toString());
                        this.isInstantDeepLinkPossible = true;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        if (bypassCurrentActivityIntentState_) {
            this.intentState_ = INTENT_STATE.READY;
        }
        if (this.intentState_ == INTENT_STATE.READY) {
            if (uri != null) {
                try {
                    if (!isIntentParamsAlreadyConsumed(activity)) {
                        String strippedURL = UniversalResourceAnalyser.getInstance(this.context_).getStrippedURL(uri.toString());
                        setSessionReferredLink(strippedURL);
                        if (strippedURL != null && strippedURL.equals(uri.toString()) && activity != null && activity.getIntent() != null && activity.getIntent().getExtras() != null) {
                            Bundle extras = activity.getIntent().getExtras();
                            Set<String> setKeySet = extras.keySet();
                            if (setKeySet.size() > 0) {
                                JSONObject jSONObject4 = new JSONObject();
                                for (String str3 : EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST) {
                                    if (setKeySet.contains(str3)) {
                                        jSONObject4.put(str3, extras.get(str3));
                                    }
                                }
                                if (jSONObject4.length() > 0) {
                                    this.prefHelper_.setExternalIntentExtra(jSONObject4.toString());
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
            if (activity != null) {
                try {
                    if (activity.getIntent() != null && activity.getIntent().getExtras() != null && !isIntentParamsAlreadyConsumed(activity)) {
                        Object obj = activity.getIntent().getExtras().get(Defines$Jsonkey.AndroidPushNotificationKey.getKey());
                        if (obj instanceof String) {
                            string = (String) obj;
                        } else {
                            string = obj instanceof Uri ? ((Uri) obj).toString() : null;
                        }
                        if (!TextUtils.isEmpty(string)) {
                            this.prefHelper_.setPushIdentifier(string);
                            Intent intent2 = activity.getIntent();
                            intent2.putExtra(Defines$Jsonkey.BranchLinkUsed.getKey(), true);
                            activity.setIntent(intent2);
                            return false;
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (uri != null && uri.isHierarchical() && activity != null && !isActivityLaunchedFromHistory(activity)) {
                try {
                    Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.LinkClickID;
                    if (uri.getQueryParameter(defines$Jsonkey2.getKey()) != null) {
                        this.prefHelper_.setLinkClickIdentifier(uri.getQueryParameter(defines$Jsonkey2.getKey()));
                        String str4 = "link_click_id=" + uri.getQueryParameter(defines$Jsonkey2.getKey());
                        String dataString = activity.getIntent() != null ? activity.getIntent().getDataString() : null;
                        if (uri.getQuery().length() == str4.length()) {
                            str = "\\?" + str4;
                        } else if (dataString != null && dataString.length() - str4.length() == dataString.indexOf(str4)) {
                            str = "&" + str4;
                        } else {
                            str = str4 + "&";
                        }
                        if (dataString != null) {
                            activity.getIntent().setData(Uri.parse(dataString.replaceFirst(str, "")));
                            activity.getIntent().putExtra(Defines$Jsonkey.BranchLinkUsed.getKey(), true);
                        } else {
                            PrefHelper.Debug("Warning: URI for the launcher activity is null. Please make sure that intent data is not set to null before calling Branch#InitSession ");
                        }
                        return true;
                    }
                    String scheme = uri.getScheme();
                    Intent intent3 = activity.getIntent();
                    if (scheme != null && intent3 != null && ((scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https")) && uri.getHost() != null && uri.getHost().length() > 0 && !isIntentParamsAlreadyConsumed(activity))) {
                        if (uri.toString().equalsIgnoreCase(UniversalResourceAnalyser.getInstance(this.context_).getStrippedURL(uri.toString()))) {
                            this.prefHelper_.setAppLink(uri.toString());
                        }
                        intent3.putExtra(Defines$Jsonkey.BranchLinkUsed.getKey(), true);
                        activity.setIntent(intent3);
                    }
                } catch (Exception unused3) {
                }
            }
        }
        return false;
    }

    private boolean isIntentParamsAlreadyConsumed(Activity activity) {
        return (activity == null || activity.getIntent() == null || !activity.getIntent().getBooleanExtra(Defines$Jsonkey.BranchLinkUsed.getKey(), false)) ? false : true;
    }

    private boolean isActivityLaunchedFromHistory(Activity activity) {
        return (activity == null || activity.getIntent() == null || (activity.getIntent().getFlags() & 1048576) == 0) ? false : true;
    }

    String getSessionReferredLink() {
        String externalIntentUri = this.prefHelper_.getExternalIntentUri();
        if (externalIntentUri.equals("bnc_no_value")) {
            return null;
        }
        return externalIntentUri;
    }

    private void setSessionReferredLink(String str) {
        this.prefHelper_.setExternalIntentUri(str);
    }

    private void resetSessionReferredLink() {
        setSessionReferredLink(null);
    }

    @Override // io.branch.referral.SystemObserver.AdsParamsFetchEvents
    public void onAdsParamsFetchFinished() {
        this.isGAParamsFetchInProgress_ = false;
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        if (this.performCookieBasedStrongMatchingOnGAIDAvailable) {
            performCookieBasedStrongMatch();
            this.performCookieBasedStrongMatchingOnGAIDAvailable = false;
        } else {
            processNextQueueItem();
        }
    }

    @Override // io.branch.referral.InstallListener.IInstallReferrerEvents
    public void onInstallReferrerEventsFinished() {
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
        processNextQueueItem();
    }

    void updateSkipURLFormats() {
        UniversalResourceAnalyser.getInstance(this.context_).checkAndUpdateSkipURLFormats(this.context_);
    }

    public void setIdentity(@NonNull String str) {
        setIdentity(str, null);
    }

    public void setIdentity(@NonNull String str, @Nullable BranchReferralInitListener branchReferralInitListener) {
        ServerRequestIdentifyUserRequest serverRequestIdentifyUserRequest = new ServerRequestIdentifyUserRequest(this.context_, branchReferralInitListener, str);
        if (!serverRequestIdentifyUserRequest.constructError_ && !serverRequestIdentifyUserRequest.handleErrors(this.context_)) {
            handleNewRequest(serverRequestIdentifyUserRequest);
        } else if (serverRequestIdentifyUserRequest.isExistingID()) {
            serverRequestIdentifyUserRequest.handleUserExist(branchReferral_);
        }
    }

    public boolean isUserIdentified() {
        return !this.prefHelper_.getIdentity().equals("bnc_no_value");
    }

    public void logout() {
        logout(null);
    }

    public void logout(LogoutStatusListener logoutStatusListener) {
        ServerRequestLogout serverRequestLogout = new ServerRequestLogout(this.context_, logoutStatusListener);
        if (serverRequestLogout.constructError_ || serverRequestLogout.handleErrors(this.context_)) {
            return;
        }
        handleNewRequest(serverRequestLogout);
    }

    public int getCredits() {
        return this.prefHelper_.getCreditCount();
    }

    public JSONObject getFirstReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
    }

    public JSONObject getLatestReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
    }

    private JSONObject appendDebugParams(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = this.deeplinkDebugParams_;
                if (jSONObject2 != null) {
                    if (jSONObject2.length() > 0) {
                        PrefHelper.Debug("You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator<String> itKeys = this.deeplinkDebugParams_.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        jSONObject.put(next, this.deeplinkDebugParams_.get(next));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    private JSONObject convertParamsStringToDictionary(String str) {
        if (str.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            try {
                return new JSONObject(new String(Base64.decode(str.getBytes(), 2)));
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processNextQueueItem() {
        try {
            this.serverSema_.acquire();
            if (this.networkCount_ == 0 && this.requestQueue_.getSize() > 0) {
                this.networkCount_ = 1;
                ServerRequest serverRequestPeek = this.requestQueue_.peek();
                this.serverSema_.release();
                if (serverRequestPeek != null) {
                    if (!serverRequestPeek.isWaitingOnProcessToFinish()) {
                        if (!(serverRequestPeek instanceof ServerRequestRegisterInstall) && !hasUser()) {
                            PrefHelper.Debug("Branch Error: User session has not been initialized!");
                            this.networkCount_ = 0;
                            handleFailure(this.requestQueue_.getSize() - 1, -101);
                        } else if (requestNeedsSession(serverRequestPeek) && !isSessionAvailableForRequest()) {
                            this.networkCount_ = 0;
                            handleFailure(this.requestQueue_.getSize() - 1, -101);
                        } else {
                            new BranchPostTask(serverRequestPeek).executeTask(new Void[0]);
                        }
                    } else {
                        this.networkCount_ = 0;
                    }
                } else {
                    this.requestQueue_.remove(null);
                }
            } else {
                this.serverSema_.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean requestNeedsSession(ServerRequest serverRequest) {
        return ((serverRequest instanceof ServerRequestInitSession) || (serverRequest instanceof ServerRequestCreateUrl)) ? false : true;
    }

    private boolean isSessionAvailableForRequest() {
        return hasSession() && hasDeviceFingerPrint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFailure(int i, int i2) {
        ServerRequest serverRequestPeekAt;
        if (i >= this.requestQueue_.getSize()) {
            serverRequestPeekAt = this.requestQueue_.peekAt(r2.getSize() - 1);
        } else {
            serverRequestPeekAt = this.requestQueue_.peekAt(i);
        }
        handleFailure(serverRequestPeekAt, i2);
    }

    private void handleFailure(ServerRequest serverRequest, int i) {
        if (serverRequest == null) {
            return;
        }
        serverRequest.handleFailure(i, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllRequestsInQueue() {
        JSONObject post;
        for (int i = 0; i < this.requestQueue_.getSize(); i++) {
            try {
                ServerRequest serverRequestPeekAt = this.requestQueue_.peekAt(i);
                if (serverRequestPeekAt != null && (post = serverRequestPeekAt.getPost()) != null) {
                    Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.SessionID;
                    if (post.has(defines$Jsonkey.getKey())) {
                        serverRequestPeekAt.getPost().put(defines$Jsonkey.getKey(), this.prefHelper_.getSessionID());
                    }
                    Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.IdentityID;
                    if (post.has(defines$Jsonkey2.getKey())) {
                        serverRequestPeekAt.getPost().put(defines$Jsonkey2.getKey(), this.prefHelper_.getIdentityID());
                    }
                    Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.DeviceFingerprintID;
                    if (post.has(defines$Jsonkey3.getKey())) {
                        serverRequestPeekAt.getPost().put(defines$Jsonkey3.getKey(), this.prefHelper_.getDeviceFingerPrintID());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private boolean hasSession() {
        return !this.prefHelper_.getSessionID().equals("bnc_no_value");
    }

    private boolean hasDeviceFingerPrint() {
        return !this.prefHelper_.getDeviceFingerPrintID().equals("bnc_no_value");
    }

    private boolean hasUser() {
        return !this.prefHelper_.getIdentityID().equals("bnc_no_value");
    }

    private void insertRequestAtFront(ServerRequest serverRequest) {
        if (this.networkCount_ == 0) {
            this.requestQueue_.insert(serverRequest, 0);
        } else {
            this.requestQueue_.insert(serverRequest, 1);
        }
    }

    private void registerInstallOrOpen(ServerRequest serverRequest, BranchReferralInitListener branchReferralInitListener) {
        if (!this.requestQueue_.containsInstallOrOpen()) {
            insertRequestAtFront(serverRequest);
        } else {
            if (branchReferralInitListener != null) {
                this.requestQueue_.setInstallOrOpenCallback(branchReferralInitListener);
            }
            this.requestQueue_.moveInstallOrOpenToFront(serverRequest, this.networkCount_, branchReferralInitListener);
        }
        processNextQueueItem();
    }

    private void initializeSession(BranchReferralInitListener branchReferralInitListener) {
        if (this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
            this.initState_ = SESSION_STATE.UNINITIALISED;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished(null, new BranchError("Trouble initializing Branch.", -114));
            }
            PrefHelper.Debug("Warning: Please enter your branch_key in your project's res/values/strings.xml!");
            return;
        }
        if (this.prefHelper_.getBranchKey() != null && this.prefHelper_.getBranchKey().startsWith("key_test_")) {
            PrefHelper.Debug("Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (getSessionReferredLink() != null || !this.enableFacebookAppLinkCheck_) {
            registerAppInit(branchReferralInitListener, null);
        } else if (DeferredAppLinkDataHandler.fetchDeferredAppLinkData(this.context_, new DeferredAppLinkDataHandler.AppLinkFetchEvents() { // from class: io.branch.referral.Branch.1
            @Override // io.branch.referral.DeferredAppLinkDataHandler.AppLinkFetchEvents
            public void onAppLinkFetchFinished(String str) {
                Branch.this.prefHelper_.setIsAppLinkTriggeredInit(Boolean.TRUE);
                if (str != null) {
                    String queryParameter = Uri.parse(str).getQueryParameter(Defines$Jsonkey.LinkClickID.getKey());
                    if (!TextUtils.isEmpty(queryParameter)) {
                        Branch.this.prefHelper_.setLinkClickIdentifier(queryParameter);
                    }
                }
                Branch.this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
                Branch.this.processNextQueueItem();
            }
        }).booleanValue()) {
            registerAppInit(branchReferralInitListener, ServerRequest.PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        } else {
            registerAppInit(branchReferralInitListener, null);
        }
    }

    private void registerAppInit(BranchReferralInitListener branchReferralInitListener, ServerRequest.PROCESS_WAIT_LOCK process_wait_lock) {
        ServerRequest installOrOpenRequest = getInstallOrOpenRequest(branchReferralInitListener);
        installOrOpenRequest.addProcessWaitLock(process_wait_lock);
        if (this.isGAParamsFetchInProgress_) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        if (this.intentState_ != INTENT_STATE.READY && !isForceSessionEnabled()) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        }
        if (checkInstallReferrer_ && (installOrOpenRequest instanceof ServerRequestRegisterInstall) && !InstallListener.unReportedReferrerAvailable) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
            new InstallListener().captureInstallReferrer(this.context_, playStoreReferrerFetchTime, this);
        }
        registerInstallOrOpen(installOrOpenRequest, branchReferralInitListener);
    }

    private ServerRequest getInstallOrOpenRequest(BranchReferralInitListener branchReferralInitListener) {
        if (hasUser()) {
            return new ServerRequestRegisterOpen(this.context_, branchReferralInitListener);
        }
        return new ServerRequestRegisterInstall(this.context_, branchReferralInitListener, InstallListener.getInstallationID());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onIntentReady(Activity activity, boolean z) {
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        if (z) {
            readAndStripParam(activity.getIntent().getData(), activity);
            if (!isTrackingDisabled() && cookieBasedMatchDomain_ != null && this.prefHelper_.getBranchKey() != null && !this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
                if (this.isGAParamsFetchInProgress_) {
                    this.performCookieBasedStrongMatchingOnGAIDAvailable = true;
                    return;
                } else {
                    performCookieBasedStrongMatch();
                    return;
                }
            }
            processNextQueueItem();
            return;
        }
        processNextQueueItem();
    }

    private void performCookieBasedStrongMatch() {
        if (this.trackingController.isTrackingDisabled()) {
            return;
        }
        WeakReference<Activity> weakReference = this.currentActivityReference_;
        Activity activity = weakReference != null ? weakReference.get() : null;
        Context applicationContext = activity != null ? activity.getApplicationContext() : null;
        if (applicationContext != null) {
            this.requestQueue_.setStrongMatchWaitLock();
            BranchStrongMatchHelper.getInstance().checkForStrongMatch(applicationContext, cookieBasedMatchDomain_, this.deviceInfo_, this.prefHelper_, new BranchStrongMatchHelper.StrongMatchCheckEvents() { // from class: io.branch.referral.Branch.2
                @Override // io.branch.referral.BranchStrongMatchHelper.StrongMatchCheckEvents
                public void onStrongMatchCheckFinished() {
                    Branch.this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                    Branch.this.processNextQueueItem();
                }
            });
        }
    }

    public void handleNewRequest(ServerRequest serverRequest) {
        if (this.trackingController.isTrackingDisabled() && !serverRequest.prepareExecuteWithoutTracking()) {
            serverRequest.reportTrackingDisabledError();
            return;
        }
        if (this.initState_ != SESSION_STATE.INITIALISED && !(serverRequest instanceof ServerRequestInitSession)) {
            if (serverRequest instanceof ServerRequestLogout) {
                serverRequest.handleFailure(-101, "");
                PrefHelper.Debug("Branch is not initialized, cannot logout");
                return;
            } else {
                if (serverRequest instanceof ServerRequestRegisterClose) {
                    PrefHelper.Debug("Branch is not initialized, cannot close session");
                    return;
                }
                WeakReference<Activity> weakReference = this.currentActivityReference_;
                Activity activity = weakReference != null ? weakReference.get() : null;
                if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
                    initUserSessionInternal(null, activity, true);
                } else {
                    initUserSessionInternal(null, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
                }
            }
        }
        this.requestQueue_.enqueue(serverRequest);
        serverRequest.onRequestQueued();
        processNextQueueItem();
    }

    @TargetApi(14)
    private void setActivityLifeCycleObserver(Application application) {
        try {
            BranchActivityLifeCycleObserver branchActivityLifeCycleObserver = new BranchActivityLifeCycleObserver();
            application.unregisterActivityLifecycleCallbacks(branchActivityLifeCycleObserver);
            application.registerActivityLifecycleCallbacks(branchActivityLifeCycleObserver);
            isActivityLifeCycleCallbackRegistered_ = true;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            isActivityLifeCycleCallbackRegistered_ = false;
            isAutoSessionMode_ = false;
            PrefHelper.Debug(new BranchError("", -108).getMessage());
        }
    }

    @TargetApi(14)
    private class BranchActivityLifeCycleObserver implements Application.ActivityLifecycleCallbacks {
        private int activityCnt_;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private BranchActivityLifeCycleObserver() {
            this.activityCnt_ = 0;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Branch branch = Branch.this;
            branch.intentState_ = branch.handleDelayedNewIntents_ ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            Branch.this.isActivityCreatedAndLaunched = true;
            if (BranchViewHandler.getInstance().isInstallOrOpenBranchViewPending(activity.getApplicationContext())) {
                BranchViewHandler.getInstance().showPendingBranchView(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Branch branch = Branch.this;
            branch.intentState_ = branch.handleDelayedNewIntents_ ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            if (Branch.this.initState_ == SESSION_STATE.INITIALISED) {
                try {
                    ContentDiscoverer.getInstance().discoverContent(activity, Branch.this.getSessionReferredLink());
                } catch (Exception unused) {
                }
            }
            if (this.activityCnt_ < 1) {
                if (Branch.this.initState_ == SESSION_STATE.INITIALISED) {
                    Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                }
                Branch.this.startSession(activity);
            } else if (Branch.this.checkIntentForSessionRestart(activity.getIntent())) {
                Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                Branch.this.startSession(activity);
            }
            this.activityCnt_++;
            Branch.this.isActivityCreatedAndLaunched = false;
            maybeRefreshAdvertisingID(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (Branch.this.checkIntentForSessionRestart(activity.getIntent())) {
                Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                Branch.this.startSession(activity);
            }
            Branch.this.currentActivityReference_ = new WeakReference<>(activity);
            if (!Branch.this.handleDelayedNewIntents_ || Branch.bypassCurrentActivityIntentState_) {
                return;
            }
            Branch.this.intentState_ = INTENT_STATE.READY;
            Branch.this.onIntentReady(activity, (activity.getIntent() == null || Branch.this.initState_ == SESSION_STATE.INITIALISED) ? false : true);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (Branch.this.shareLinkManager_ != null) {
                Branch.this.shareLinkManager_.cancelShareLinkDialog(true);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            ContentDiscoverer.getInstance().onActivityStopped(activity);
            int i = this.activityCnt_ - 1;
            this.activityCnt_ = i;
            if (i < 1) {
                Branch branch = Branch.this;
                branch.isInstantDeepLinkPossible = false;
                branch.closeSessionInternal();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            WeakReference<Activity> weakReference = Branch.this.currentActivityReference_;
            if (weakReference != null && weakReference.get() == activity) {
                Branch.this.currentActivityReference_.clear();
            }
            BranchViewHandler.getInstance().onCurrentActivityDestroyed(activity);
        }

        private void maybeRefreshAdvertisingID(Context context) {
            if ((Branch.this.trackingController == null || Branch.this.deviceInfo_ == null || Branch.this.deviceInfo_.getSystemObserver() == null || Branch.this.prefHelper_ == null || Branch.this.prefHelper_.getSessionID() == null) ? false : true) {
                if (Branch.this.prefHelper_.getSessionID().equals(Branch.this.deviceInfo_.getSystemObserver().getAIDInitializationSessionID()) || Branch.this.isGAParamsFetchInProgress_ || Branch.this.trackingController.isTrackingDisabled()) {
                    return;
                }
                Branch branch = Branch.this;
                branch.isGAParamsFetchInProgress_ = branch.deviceInfo_.getSystemObserver().prefetchAdsParams(context, Branch.this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSession(Activity activity) {
        Uri data = activity.getIntent() != null ? activity.getIntent().getData() : null;
        WeakReference<BranchReferralInitListener> weakReference = this.deferredInitListener_;
        BranchReferralInitListener branchReferralInitListener = weakReference != null ? weakReference.get() : null;
        this.isInitReportedThroughCallBack = false;
        initSession(branchReferralInitListener, data, activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkIntentForSessionRestart(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.ForceNewBranchSession;
            if (intent.getBooleanExtra(defines$Jsonkey.getKey(), false)) {
                try {
                    intent.putExtra(defines$Jsonkey.getKey(), false);
                } catch (Throwable unused) {
                }
            } else {
                if (intent.getStringExtra(Defines$Jsonkey.AndroidPushNotificationKey.getKey()) == null) {
                    return false;
                }
                if (intent.getBooleanExtra(Defines$Jsonkey.BranchLinkUsed.getKey(), false)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused2) {
            return false;
        }
    }

    private class BranchPostTask extends BranchAsyncTask<Void, Void, ServerResponse> {
        ServerRequest thisReq_;

        public BranchPostTask(ServerRequest serverRequest) {
            this.thisReq_ = serverRequest;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            this.thisReq_.onPreExecute();
            this.thisReq_.doFinalUpdateOnMainThread();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ServerResponse doInBackground(Void... voidArr) {
            Branch.this.addExtraInstrumentationData(this.thisReq_.getRequestPath() + "-" + Defines$Jsonkey.Queue_Wait_Time.getKey(), String.valueOf(this.thisReq_.getQueueWaitTime()));
            this.thisReq_.doFinalUpdateOnBackgroundThread();
            if (!Branch.this.isTrackingDisabled() || this.thisReq_.prepareExecuteWithoutTracking()) {
                return this.thisReq_.isGetRequest() ? Branch.this.branchRemoteInterface_.make_restful_get(this.thisReq_.getRequestUrl(), this.thisReq_.getGetParams(), this.thisReq_.getRequestPath(), Branch.this.prefHelper_.getBranchKey()) : Branch.this.branchRemoteInterface_.make_restful_post(this.thisReq_.getPostWithInstrumentationValues(Branch.this.instrumentationExtraData_), this.thisReq_.getRequestUrl(), this.thisReq_.getRequestPath(), Branch.this.prefHelper_.getBranchKey());
            }
            return new ServerResponse(this.thisReq_.getRequestPath(), -117);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ServerResponse serverResponse) {
            boolean z;
            super.onPostExecute(serverResponse);
            if (serverResponse != null) {
                try {
                    int statusCode = serverResponse.getStatusCode();
                    Branch.this.hasNetwork_ = true;
                    if (serverResponse.getStatusCode() == -117) {
                        this.thisReq_.reportTrackingDisabledError();
                        Branch.this.requestQueue_.remove(this.thisReq_);
                    } else if (statusCode == 200) {
                        Branch.this.hasNetwork_ = true;
                        ServerRequest serverRequest = this.thisReq_;
                        if (serverRequest instanceof ServerRequestCreateUrl) {
                            if (serverResponse.getObject() != null) {
                                Branch.this.linkCache_.put(((ServerRequestCreateUrl) this.thisReq_).getLinkPost(), serverResponse.getObject().getString(ImagesContract.URL));
                            }
                        } else if (serverRequest instanceof ServerRequestLogout) {
                            Branch.this.linkCache_.clear();
                            Branch.this.requestQueue_.clear();
                        }
                        Branch.this.requestQueue_.dequeue();
                        ServerRequest serverRequest2 = this.thisReq_;
                        if (!(serverRequest2 instanceof ServerRequestInitSession) && !(serverRequest2 instanceof ServerRequestIdentifyUserRequest)) {
                            serverRequest2.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                        } else {
                            JSONObject object = serverResponse.getObject();
                            if (object != null) {
                                if (Branch.this.isTrackingDisabled()) {
                                    z = false;
                                } else {
                                    Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.SessionID;
                                    if (object.has(defines$Jsonkey.getKey())) {
                                        Branch.this.prefHelper_.setSessionID(object.getString(defines$Jsonkey.getKey()));
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.IdentityID;
                                    if (object.has(defines$Jsonkey2.getKey())) {
                                        if (!Branch.this.prefHelper_.getIdentityID().equals(object.getString(defines$Jsonkey2.getKey()))) {
                                            Branch.this.linkCache_.clear();
                                            Branch.this.prefHelper_.setIdentityID(object.getString(defines$Jsonkey2.getKey()));
                                            z = true;
                                        }
                                    }
                                    Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.DeviceFingerprintID;
                                    if (object.has(defines$Jsonkey3.getKey())) {
                                        Branch.this.prefHelper_.setDeviceFingerPrintID(object.getString(defines$Jsonkey3.getKey()));
                                        z = true;
                                    }
                                }
                                if (z) {
                                    Branch.this.updateAllRequestsInQueue();
                                }
                                ServerRequest serverRequest3 = this.thisReq_;
                                if (!(serverRequest3 instanceof ServerRequestInitSession)) {
                                    serverRequest3.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                                } else {
                                    Branch.this.initState_ = SESSION_STATE.INITIALISED;
                                    this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                                    if (!Branch.this.isInitReportedThroughCallBack && !((ServerRequestInitSession) this.thisReq_).handleBranchViewIfAvailable(serverResponse)) {
                                        Branch.this.checkForAutoDeepLinkConfiguration();
                                    }
                                    if (((ServerRequestInitSession) this.thisReq_).hasCallBack()) {
                                        Branch.this.isInitReportedThroughCallBack = true;
                                    }
                                    if (Branch.this.getLatestReferringParamsLatch != null) {
                                        Branch.this.getLatestReferringParamsLatch.countDown();
                                    }
                                    if (Branch.this.getFirstReferringParamsLatch != null) {
                                        Branch.this.getFirstReferringParamsLatch.countDown();
                                    }
                                }
                            }
                        }
                    } else {
                        if (this.thisReq_ instanceof ServerRequestInitSession) {
                            Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                        }
                        if (statusCode == 400 || statusCode == 409) {
                            Branch.this.requestQueue_.remove(this.thisReq_);
                            ServerRequest serverRequest4 = this.thisReq_;
                            if (serverRequest4 instanceof ServerRequestCreateUrl) {
                                ((ServerRequestCreateUrl) serverRequest4).handleDuplicateURLError();
                            } else {
                                PrefHelper.LogAlways("Branch API Error: Conflicting resource error code from API");
                                Branch.this.handleFailure(0, statusCode);
                            }
                        } else {
                            Branch.this.hasNetwork_ = false;
                            ArrayList<ServerRequest> arrayList = new ArrayList();
                            for (int i = 0; i < Branch.this.requestQueue_.getSize(); i++) {
                                arrayList.add(Branch.this.requestQueue_.peekAt(i));
                            }
                            for (ServerRequest serverRequest5 : arrayList) {
                                if (serverRequest5 == null || !serverRequest5.shouldRetryOnFail()) {
                                    Branch.this.requestQueue_.remove(serverRequest5);
                                }
                            }
                            Branch.this.networkCount_ = 0;
                            for (ServerRequest serverRequest6 : arrayList) {
                                if (serverRequest6 != null) {
                                    serverRequest6.handleFailure(statusCode, serverResponse.getFailReason());
                                    if (serverRequest6.shouldRetryOnFail()) {
                                        serverRequest6.clearCallbacks();
                                    }
                                }
                            }
                        }
                    }
                    Branch.this.networkCount_ = 0;
                    if (!Branch.this.hasNetwork_ || Branch.this.initState_ == SESSION_STATE.UNINITIALISED) {
                        return;
                    }
                    Branch.this.processNextQueueItem();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkForAutoDeepLinkConfiguration() {
        WeakReference<Activity> weakReference;
        Bundle bundle;
        JSONObject latestReferringParams = getLatestReferringParams();
        String str = null;
        try {
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Clicked_Branch_Link;
            if (latestReferringParams.has(defines$Jsonkey.getKey()) && latestReferringParams.getBoolean(defines$Jsonkey.getKey()) && latestReferringParams.length() > 0) {
                Bundle bundle2 = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128).metaData;
                if (bundle2 == null || !bundle2.getBoolean("io.branch.sdk.auto_link_disable", false)) {
                    ActivityInfo[] activityInfoArr = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), voOSType.VOOSMP_PID_AUDIO_EFFECT_ENDPOINT_TYPE).activities;
                    int i = 1501;
                    if (activityInfoArr != null) {
                        for (ActivityInfo activityInfo : activityInfoArr) {
                            if (activityInfo != null && (bundle = activityInfo.metaData) != null && ((bundle.getString("io.branch.sdk.auto_link_keys") != null || activityInfo.metaData.getString("io.branch.sdk.auto_link_path") != null) && (checkForAutoDeepLinkKeys(latestReferringParams, activityInfo) || checkForAutoDeepLinkPath(latestReferringParams, activityInfo)))) {
                                str = activityInfo.name;
                                i = activityInfo.metaData.getInt("io.branch.sdk.auto_link_request_code", 1501);
                                break;
                            }
                        }
                    }
                    if (str == null || (weakReference = this.currentActivityReference_) == null) {
                        return;
                    }
                    Activity activity = weakReference.get();
                    if (activity != null) {
                        Intent intent = new Intent(activity, Class.forName(str));
                        intent.putExtra("io.branch.sdk.auto_linked", "true");
                        intent.putExtra(Defines$Jsonkey.ReferringData.getKey(), latestReferringParams.toString());
                        Iterator<String> itKeys = latestReferringParams.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            intent.putExtra(next, latestReferringParams.getString(next));
                        }
                        activity.startActivityForResult(intent, i);
                        return;
                    }
                    PrefHelper.Debug("No activity reference to launch deep linked activity");
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            PrefHelper.Debug("Warning: Please make sure Activity names set for auto deep link are correct!");
        } catch (ClassNotFoundException unused2) {
            PrefHelper.Debug("Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity " + ((String) null));
        } catch (Exception unused3) {
        }
    }

    private boolean checkForAutoDeepLinkKeys(JSONObject jSONObject, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString("io.branch.sdk.auto_link_keys") != null) {
            for (String str : activityInfo.metaData.getString("io.branch.sdk.auto_link_keys").split(",")) {
                if (jSONObject.has(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean checkForAutoDeepLinkPath(org.json.JSONObject r5, android.content.pm.ActivityInfo r6) {
        /*
            r4 = this;
            r0 = 0
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines$Jsonkey.AndroidDeepLinkPath     // Catch: org.json.JSONException -> L2c
            java.lang.String r2 = r1.getKey()     // Catch: org.json.JSONException -> L2c
            boolean r2 = r5.has(r2)     // Catch: org.json.JSONException -> L2c
            if (r2 == 0) goto L17
            java.lang.String r1 = r1.getKey()     // Catch: org.json.JSONException -> L2c
            java.lang.String r5 = r5.getString(r1)     // Catch: org.json.JSONException -> L2c
        L15:
            r0 = r5
            goto L2c
        L17:
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines$Jsonkey.DeepLinkPath     // Catch: org.json.JSONException -> L2c
            java.lang.String r2 = r1.getKey()     // Catch: org.json.JSONException -> L2c
            boolean r2 = r5.has(r2)     // Catch: org.json.JSONException -> L2c
            if (r2 == 0) goto L2c
            java.lang.String r1 = r1.getKey()     // Catch: org.json.JSONException -> L2c
            java.lang.String r5 = r5.getString(r1)     // Catch: org.json.JSONException -> L2c
            goto L15
        L2c:
            android.os.Bundle r5 = r6.metaData
            java.lang.String r1 = "io.branch.sdk.auto_link_path"
            java.lang.String r5 = r5.getString(r1)
            r2 = 0
            if (r5 == 0) goto L5a
            if (r0 == 0) goto L5a
            android.os.Bundle r5 = r6.metaData
            java.lang.String r5 = r5.getString(r1)
            java.lang.String r6 = ","
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r1 = r2
        L47:
            if (r1 >= r6) goto L5a
            r3 = r5[r1]
            java.lang.String r3 = r3.trim()
            boolean r3 = r4.pathMatch(r3, r0)
            if (r3 == 0) goto L57
            r5 = 1
            return r5
        L57:
            int r1 = r1 + 1
            goto L47
        L5a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.checkForAutoDeepLinkPath(org.json.JSONObject, android.content.pm.ActivityInfo):boolean");
    }

    private boolean pathMatch(String str, String str2) {
        String[] strArrSplit = str.split("\\?")[0].split("/");
        String[] strArrSplit2 = str2.split("\\?")[0].split("/");
        if (strArrSplit.length != strArrSplit2.length) {
            return false;
        }
        for (int i = 0; i < strArrSplit.length && i < strArrSplit2.length; i++) {
            String str3 = strArrSplit[i];
            if (!str3.equals(strArrSplit2[i]) && !str3.contains("*")) {
                return false;
            }
        }
        return true;
    }

    static boolean isSimulatingInstalls() {
        return isSimulatingInstalls_;
    }

    public static boolean isForceSessionEnabled() {
        return isForcedSession_;
    }

    public void addExtraInstrumentationData(String str, String str2) {
        this.instrumentationExtraData_.put(str, str2);
    }

    @Override // io.branch.referral.BranchViewHandler.IBranchViewEvents
    public void onBranchViewAccepted(String str, String str2) {
        if (ServerRequestInitSession.isInitSessionAction(str)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    @Override // io.branch.referral.BranchViewHandler.IBranchViewEvents
    public void onBranchViewCancelled(String str, String str2) {
        if (ServerRequestInitSession.isInitSessionAction(str)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    @Override // io.branch.referral.BranchViewHandler.IBranchViewEvents
    public void onBranchViewError(int i, String str, String str2) {
        if (ServerRequestInitSession.isInitSessionAction(str2)) {
            checkForAutoDeepLinkConfiguration();
        }
    }
}
