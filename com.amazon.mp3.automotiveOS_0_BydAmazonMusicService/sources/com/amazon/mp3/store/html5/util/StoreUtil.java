package com.amazon.mp3.store.html5.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.mp3.net.RequestAuthorizer;
import com.amazon.mp3.store.html5.bridge.MessageType;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.store.purchase.PurchaseUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.subscription.BrowseMode;
import com.amazon.music.subscription.UserSubscription;
import kotlin.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public abstract class StoreUtil {
    public static void setWebViewLoggingEnabled(Context context) {
        StoreWebView.getInstance(context).setWebChromeClient(new WebChromeClient() { // from class: com.amazon.mp3.store.html5.util.StoreUtil.1
            @Override // android.webkit.WebChromeClient
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.warning("StoreUtil.javascript", "(line %d): %s", Integer.valueOf(lineNumber), message);
            }
        });
    }

    public static void requestAudioFocusFromStore() {
        StoreWebView.getInstance(AmazonApplication.getContext()).getJavascriptBridge().request(MessageType.RequestAudioFocus.getName());
    }

    public static void broadcastStoreHostVisibilityChanged(Activity context, boolean visible) {
        try {
            StoreWebView.getInstance(context).getJavascriptBridge().broadcast(MessageType.HostVisibilityChanged.getName(), new JSONObject().put("visible", visible));
        } catch (JSONException e) {
            Log.error("StoreUtil", "Internal error setting visibility", e);
        }
    }

    public static void broadcastCredentials(Context context) {
        broadcastCredentials(context, new JSONArray());
    }

    public static void broadcastCredentials(Context context, JSONArray cookies) {
        try {
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            Pair<String, String> pairAuthKV = new RequestAuthorizer().authKV();
            JSONObject jSONObject = new JSONObject();
            if (pairAuthKV != null) {
                jSONObject.put(pairAuthKV.component1(), pairAuthKV.component2());
            }
            jSONObject.put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId()).put(Splash.PARAMS_DEVICE_TYPE, accountCredentialStorage.getDeviceType()).put("isRobinUser", userSubscription.isPrime());
            appendHawkfireCredentials(jSONObject);
            if (cookies.length() > 0) {
                jSONObject.put("portalCookies", cookies);
            }
            String associateTag = AssociateTag.getInstance(context).getAssociateTag();
            if (associateTag != null && associateTag.length() > 0) {
                jSONObject.put("associateTag", associateTag);
            }
            StoreWebView.getInstance(context).getJavascriptBridge().broadcast(MessageType.SetCredentials.getName(), jSONObject);
        } catch (JSONException e) {
            Log.error("StoreUtil", "Unable to generate credentials json for store.", e);
        }
    }

    private static void appendHawkfireCredentials(final JSONObject options) throws JSONException {
        if (new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME)) {
            options.put("hawkfireUser", false);
        } else {
            options.put("hawkfireUser", true);
        }
    }

    public static void broadcastReferrer(Context context) {
        try {
            String referrerPackageName = PurchaseUtil.getReferrerPackageName();
            long referrerExpireTimeout = PurchaseUtil.getReferrerExpireTimeout();
            if (referrerPackageName == null || referrerExpireTimeout <= 0) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("referrer", referrerPackageName);
            jSONObject.put("expires", referrerExpireTimeout);
            StoreWebView.getInstance(context).getJavascriptBridge().broadcast(MessageType.SetReferringApplication.getName(), jSONObject);
        } catch (JSONException e) {
            Log.error("StoreUtil", "Unable to generate referrer json for store.", e);
        }
    }

    public static void broadcastRefineMenuVisibility(Context context, boolean showRefineMenu) {
        try {
            StoreWebView.getInstance(context).getJavascriptBridge().broadcast(MessageType.RefineMenuVisibility.getName(), new JSONObject().put("visible", showRefineMenu));
        } catch (JSONException e) {
            Log.error("StoreUtil", "Error setting refine menu visibility", e);
        }
    }

    public static void navigateFromBundle(Context context, Bundle bundle) {
        StoreWebView storeWebView = StoreWebView.getInstance(context);
        if (storeWebView == null) {
            return;
        }
        switch (bundle.getInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 0)) {
            case 1:
                storeWebView.loadUrl(StoreUrls.getSearchUrl(bundle));
                break;
            case 2:
            case 14:
            default:
                storeWebView.loadUrl(StoreUrls.getBaseUrl());
                break;
            case 3:
                storeWebView.loadUrl(StoreUrls.getAlbumDetailUrl(bundle));
                break;
            case 4:
                storeWebView.loadUrl(StoreUrls.getArtistDetailUrl(bundle));
                break;
            case 5:
                storeWebView.loadUrl(StoreUrls.getBestsellersUrl(bundle));
                break;
            case 6:
                storeWebView.loadUrl(StoreUrls.getGenreDetailUrl(bundle));
                break;
            case 7:
                storeWebView.loadUrl(StoreUrls.getTrackDetailUrl(bundle));
                break;
            case 8:
                storeWebView.loadUrl(StoreUrls.getCampaignDetailUrl(bundle));
                break;
            case 9:
                storeWebView.loadUrl(StoreUrls.getGenresUrl());
                break;
            case 10:
                storeWebView.loadUrl(StoreUrls.getNewReleasesUrl(bundle));
                break;
            case 11:
                storeWebView.loadUrl(StoreUrls.getDeepLinkUrl(bundle));
                break;
            case 12:
                storeWebView.loadUrl(StoreUrls.getBestsellersAll());
                break;
            case 13:
                storeWebView.loadUrl(StoreUrls.getRecommendedForYouUrl(bundle));
                break;
            case 15:
                storeWebView.loadUrl(StoreUrls.getPrimePlaylistsUrl(bundle));
                break;
            case 16:
                storeWebView.loadUrl(StoreUrls.getRouteUrl(bundle));
                break;
        }
        if (bundle.getBoolean("com.amazon.mp3.extra.EXTRA_CLEAR_HISTORY", false)) {
            storeWebView.clearHistory();
        }
    }
}
