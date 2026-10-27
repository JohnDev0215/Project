package com.amazon.mp3.store.html5.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.IMetricsRecorder;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.bridge.MessageType;
import com.amazon.mp3.store.html5.map.CookiesManager;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.store.html5.util.StoreUtil;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public abstract class StoreMessageHandler {
    private static final String TAG = "StoreMessageHandler";
    private final Context mContext;
    private final CookiesManager mMapCookiesManager = MAPCookiesManager.getCookiesManager();
    private JSONObject mProfile;
    private final StoreWebView mWebView;

    protected void handleAudioFocusReleasedBroadcast(Message broadcast) {
    }

    protected void handleCachedBroadcast(Message broadcast) {
    }

    protected void handleMetricBroadcast(Message broadcast) {
    }

    protected void handlePlayVideoBroadcast(Message broadcast) {
    }

    protected void handleRefineMenuVisibilityBroadcast(Message broadcast) {
    }

    protected void handleRefreshRobinStatusBroadcast(Message broadcast) {
    }

    protected void handleResponse(Message response) {
    }

    protected void handleSearchStateChanged(Message broadcast) {
    }

    protected void handleShowDetailPageBroadcast(Message broadcast) {
    }

    protected void handleShowInLibraryBroadcast(Message broadcast) {
    }

    protected void handleShowLibraryBroadcast(Message broadcast) {
    }

    protected void handleShowPrimeBrowse(Message broadcast) {
    }

    protected void handleStoreOfflineBroadcast(Message broadcast) {
    }

    protected void handleUpdateViewTitlesBroadcast(Message broadcast) {
    }

    public StoreMessageHandler(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mWebView = StoreWebView.getInstance(applicationContext);
        initProfile();
    }

    protected void respondToRequest(Message request, Object... options) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < options.length; i += 2) {
                jSONObject.put((String) options[i], options[i + 1]);
            }
            this.mWebView.getJavascriptBridge().respond(request, request.getName(), jSONObject);
        } catch (JSONException e) {
            throw new RuntimeException("Error responding to " + request.getName(), e);
        }
    }

    protected void handleRequest(Message request) {
        switch (AnonymousClass4.$SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.fromString(request.getName()).ordinal()]) {
            case 1:
                handleGetProfileRequest(request);
                break;
            case 2:
                handleGetConnectivityTypeRequest(request);
                break;
            case 3:
                checkForPurchasePermissionAndHandleRequest(request);
                break;
            case 4:
                handleSignInRequest(request);
                break;
            case 5:
                handleSyncCloudLibraryRequest(request);
                break;
            case 6:
                handleRequestAudioFocusRequest(request);
                break;
            case 7:
                handleEnableLoggingRequest(request);
                break;
            case 8:
                handleCacheRefreshed(request);
                break;
            case 9:
                handleClearView(request);
                break;
            case 10:
                handleInvalidate(request);
                break;
            default:
                Log.debug(TAG, "Unhandled request from store: %s", request.getName());
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.store.html5.webview.StoreMessageHandler$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType;

        static {
            int[] iArr = new int[MessageType.values().length];
            $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType = iArr;
            try {
                iArr[MessageType.GetProfile.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.GetConnectivityType.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.PermissionRequest.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.SignIn.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.SyncCloudLibrary.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.RequestAudioFocus.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.EnableLogging.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.CacheRefreshed.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.ClearView.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.Invalidate.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.Initialized.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.ShowLibrary.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.PurchaseCompleted.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.ShowDetailPage.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.StoreOffline.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.Cached.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.Metric.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.PlayVideo.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.BridgeOpened.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.TopLevelControllerStarted.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.SetSoftKeyboardVisibility.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.LogRequestFailure.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.SearchStateChanged.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.AudioFocusReleased.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.ShortLivedTokenExpired.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.UpdateViewTitles.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.RefineMenuVisibility.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.RefreshRobinStatus.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.ShowInLibrary.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.ShowPrimeBrowse.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkForPurchasePermissionAndHandleRequest(final com.amazon.mp3.store.html5.bridge.Message r5) {
        /*
            r4 = this;
            org.json.JSONObject r0 = r5.getOptions()
            r1 = 0
            if (r0 == 0) goto L26
            java.lang.String r2 = "permission"
            java.lang.String r0 = r0.getString(r2)     // Catch: org.json.JSONException -> L22
            if (r0 == 0) goto L26
            java.lang.String r2 = "freePurchase"
            boolean r2 = r2.equals(r0)     // Catch: org.json.JSONException -> L22
            java.lang.String r3 = "nonFreePurchase"
            boolean r0 = r3.equals(r0)     // Catch: org.json.JSONException -> L22
            if (r2 != 0) goto L20
            if (r0 == 0) goto L26
        L20:
            r0 = 1
            goto L27
        L22:
            r0 = move-exception
            r0.printStackTrace()
        L26:
            r0 = r1
        L27:
            com.amazon.mp3.store.html5.webview.StoreMessageHandler$1 r2 = new com.amazon.mp3.store.html5.webview.StoreMessageHandler$1
            r2.<init>()
            java.lang.Void[] r5 = new java.lang.Void[r1]
            r2.execute(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.store.html5.webview.StoreMessageHandler.checkForPurchasePermissionAndHandleRequest(com.amazon.mp3.store.html5.bridge.Message):void");
    }

    protected void handleBroadcast(Message broadcast) {
        switch (AnonymousClass4.$SwitchMap$com$amazon$mp3$store$html5$bridge$MessageType[MessageType.fromString(broadcast.getName()).ordinal()]) {
            case 11:
                this.mWebView.setState(StoreWebView.State.Loaded);
                handleInitializedBroadcast(broadcast);
                break;
            case 12:
                handleShowLibraryBroadcast(broadcast);
                break;
            case 13:
                handlePurchaseCompletedBroadcast(broadcast);
                break;
            case 14:
                handleShowDetailPageBroadcast(broadcast);
                break;
            case 15:
                handleStoreOfflineBroadcast(broadcast);
                break;
            case 16:
                handleCachedBroadcast(broadcast);
                break;
            case 17:
                handleMetricBroadcast(broadcast);
                break;
            case 18:
                handlePlayVideoBroadcast(broadcast);
                break;
            case 19:
                handleBridgeOpened();
                break;
            case 20:
                handleTopLevelControllerStartedBroadcast(broadcast);
                break;
            case 21:
                handleSetSoftKeyboardVisibilityBroadcast(broadcast);
                break;
            case 22:
                handleLogRequestFailure(broadcast);
                break;
            case 23:
                handleSearchStateChanged(broadcast);
                break;
            case 24:
                handleAudioFocusReleasedBroadcast(broadcast);
                break;
            case 25:
                handleShortLivedTokenBroadcast(broadcast);
                break;
            case 26:
                handleUpdateViewTitlesBroadcast(broadcast);
                break;
            case 27:
                handleRefineMenuVisibilityBroadcast(broadcast);
                break;
            case 28:
                handleRefreshRobinStatusBroadcast(broadcast);
                break;
            case 29:
                handleShowInLibraryBroadcast(broadcast);
                break;
            case 30:
                handleShowPrimeBrowse(broadcast);
                break;
            default:
                Log.debug(TAG, "Unhandled broadcast from store: %s", broadcast.getName());
                break;
        }
    }

    protected void handleBridgeOpened() {
        StoreUtil.broadcastCredentials(this.mContext);
    }

    protected void handleGetProfileRequest(Message request) {
        respondToRequest(request, Scopes.PROFILE, this.mProfile);
    }

    protected void respondToGetConnectivityTypeRequest(Message request, final String connectivityType) {
        respondToRequest(request, "connectivityType", connectivityType);
    }

    protected void handleRequestPermissionRequest(Message request) {
        respondToRequestPermissionRequest(request, false);
    }

    protected void respondToRequestPermissionRequest(Message request, boolean granted) {
        respondToRequest(request, "granted", Boolean.valueOf(granted));
    }

    protected void handleSignInRequest(Message request) {
        respondToSignInRequest(request, false);
    }

    protected void respondToSignInRequest(Message request, boolean successful) {
        respondToRequest(request, "successful", Boolean.valueOf(successful));
    }

    protected void handleSyncCloudLibraryRequest(Message request) {
        respondToSyncCloudLibraryRequest(request, false);
    }

    protected void respondToSyncCloudLibraryRequest(Message request, boolean successful) {
        respondToRequest(request, "successful", Boolean.valueOf(successful));
    }

    protected void respondToRequestAudioFocusRequest(Message request, boolean granted) {
        respondToRequest(request, "granted", Boolean.valueOf(granted));
    }

    protected void handleLogRequestFailure(Message broadcast) {
        try {
            JSONObject options = broadcast.getOptions();
            if (options != null) {
                Log.error(TAG, "Store request failed against %s, with operation %s. Request ID: %s", options.getString(IntlConfiguration.ENDPOINT_T), options.getString("operation"), options.getString(TetheredMessageKey.requestId));
            }
        } catch (JSONException unused) {
            Log.error(TAG, "handleLogRequestFailure passed invalid parameters, unable to log failure.");
        }
    }

    protected void handlePurchaseCompletedBroadcast(Message broadcast) {
        IMetricsRecorder metricsRecorder = BaseMetricsRecorder.getMetricsRecorder(this.mContext.getApplicationContext());
        metricsRecorder.getReferralAndCampaignMetrics().recordCampaignPurchase();
        metricsRecorder.getReferralAndCampaignMetrics().recordReferralPurchaseComplete();
    }

    protected void handleClearView(final Message request) {
        this.mWebView.clearView();
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.store.html5.webview.StoreMessageHandler.2
            @Override // java.lang.Runnable
            public void run() {
                StoreMessageHandler.this.respondToRequest(request, new Object[0]);
            }
        });
    }

    protected void handleInvalidate(final Message request) {
        int iOptInt = request.getOptions().optInt("left");
        int iOptInt2 = request.getOptions().optInt("right");
        this.mWebView.invalidate(iOptInt, request.getOptions().optInt("top"), iOptInt2, request.getOptions().optInt("bottom"));
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.store.html5.webview.StoreMessageHandler.3
            @Override // java.lang.Runnable
            public void run() {
                StoreMessageHandler.this.respondToRequest(request, new Object[0]);
            }
        });
    }

    public void handleSetSoftKeyboardVisibilityBroadcast(Message broadcast) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (broadcast.getOptions().optBoolean("visible")) {
            inputMethodManager.showSoftInput(this.mWebView, 0);
        } else {
            inputMethodManager.hideSoftInputFromWindow(this.mWebView.getWindowToken(), 0);
        }
    }

    public void handleTopLevelControllerStartedBroadcast(Message broadcast) {
        String strOptString = broadcast.getOptions().optString("route");
        if ("".equals(strOptString)) {
            return;
        }
        Log.verbose(TAG, "most recent route based on broadcast : %s", strOptString);
    }

    protected void handleGetConnectivityTypeRequest(Message request) {
        respondToGetConnectivityTypeRequest(request, ConnectivityUtil.getConnectionType() == 1 ? "wifi" : ConnectivityUtil.getCarrierName());
    }

    protected void handleRequestAudioFocusRequest(Message message) {
        this.mContext.sendBroadcast(new Intent("com.amazon.mp3.playerservicecommand.pause"));
        respondToRequestAudioFocusRequest(message, true);
    }

    protected void handleShortLivedTokenBroadcast(Message broadcast) {
        if (AccountCredentialUtil.get(this.mContext).isSignedIn()) {
            Log.verbose(TAG, "Fetching cookies refreshed cookies");
            fetchCookiesFromMap(new MAPCookiesCallback(this.mContext), true);
        }
    }

    protected void handleInitializedBroadcast(Message broadcast) {
        this.mWebView.clearHistory();
        this.mWebView.setBackgroundDrawable(null);
    }

    public void handleEnableLoggingRequest(Message request) {
        Log.setNonCriticalLoggingEnabled(true);
        respondToRequest(request, "enabled", Boolean.TRUE);
        StoreUtil.setWebViewLoggingEnabled(this.mContext);
    }

    protected void handleCacheRefreshed(Message broadcast) {
        this.mWebView.setAppCacheUpdated(broadcast.getOptions().optBoolean("reload"));
    }

    protected void initProfile() {
        try {
            Capabilities capabilities = AmazonApplication.getCapabilities();
            JSONObject jSONObjectPut = new JSONObject().put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Android").put("version", Build.VERSION.RELEASE);
            JSONObject jSONObjectPut2 = new JSONObject().put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, capabilities.getPlatformName()).put("version", Build.VERSION.INCREMENTAL);
            DisplayMetrics displayMetrics = this.mWebView.getContext().getResources().getDisplayMetrics();
            JSONObject jSONObjectPut3 = new JSONObject().put("density", displayMetrics.densityDpi).put("width", displayMetrics.widthPixels).put("height", displayMetrics.heightPixels);
            JSONObject jSONObjectPut4 = new JSONObject().put(Splash.PARAMS_LOCALE, AmazonApplication.getLocale().toString().replace('_', '-')).put("os", jSONObjectPut).put("device", jSONObjectPut2).put("capabilities", new JSONObject().put("display", jSONObjectPut3).put("input", new JSONObject().put("touch", true)).put("browser", new JSONObject().put("iframes", false).put("pushState", false)).put("usesMultipleConnectivityTypes", ConnectivityUtil.isWifiOnlyDevice() ? false : true).put("auth", new JSONObject().put("atMainSupported", capabilities.isAppToWebSSOEnabled()).put("appToWebSSO", false)).put("prime", new JSONObject().put("nativePrimeBrowse", true))).put("custom", new JSONObject().put("purchaseRequiresPermission", true).put("purchaseRequiresSync", true).put("startupRequiresCredentials", true).put("validationTheme", "kindle_fire").put("clearViewOnSwap", true).put("buyVsOwnEnabled", true).put("robinEnabled", AccountDetailUtil.get().isPrimeMusicSupported())).put("schemaVersion", 1);
            this.mProfile = jSONObjectPut4;
            Log.debug(TAG, jSONObjectPut4.toString());
        } catch (JSONException e) {
            throw new RuntimeException("Unable to initialize profile", e);
        }
    }

    protected void fetchCookiesFromMap(MAPCookiesCallback callback, boolean forceRefresh) {
        this.mMapCookiesManager.fetchCookies(callback, forceRefresh);
    }
}
