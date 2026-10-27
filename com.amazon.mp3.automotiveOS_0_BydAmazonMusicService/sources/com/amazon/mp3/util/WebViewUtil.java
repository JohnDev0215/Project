package com.amazon.mp3.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebSettings;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.activity.BaseAccountValidationWebView;
import com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes4.dex */
public final class WebViewUtil {
    public static final int RESULT_WEBVIEW_FAILED_GEOCHECK;
    public static final int RESULT_WEBVIEW_INTERNAL_ERROR;
    public static final int RESULT_WEBVIEW_INVALID_REQUEST_ERROR;
    public static final int RESULT_WEBVIEW_MAX_TOKEN_REQUESTED;
    public static final int RESULT_WEBVIEW_NETWORK_ERROR;
    public static final int RESULT_WEBVIEW_NO_NETWORK_CONNECTION_ERROR;
    public static final int RESULT_WEBVIEW_SERVICE_ERROR;
    public static final int RESULT_WEBVIEW_SSL_ERROR;
    public static final int RESULT_WEBVIEW_UNKNOWN_ERROR;
    private static volatile boolean sBlueMoonWebViewShown;
    private static HashSet<Integer> sNonFreePurchaseHandledErrorSet;
    private static HashSet<Integer> sWebViewErrors;
    private static HashMap<WebViewReasonType, Integer> sWebViewResultMap;

    public static abstract class WebViewResult {
        public abstract void cancelled();

        public void cancelledTimedOut() {
        }

        public abstract void done();

        public void doneTimedOut() {
        }

        public abstract void error(WebViewReasonType webViewReasonType, Integer error);
    }

    public static boolean isBlueMoon(Context context) {
        return true;
    }

    static {
        int iNextUniqueCode = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_NETWORK_ERROR = iNextUniqueCode;
        int iNextUniqueCode2 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_UNKNOWN_ERROR = iNextUniqueCode2;
        int iNextUniqueCode3 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_INVALID_REQUEST_ERROR = iNextUniqueCode3;
        int iNextUniqueCode4 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_SERVICE_ERROR = iNextUniqueCode4;
        int iNextUniqueCode5 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_INTERNAL_ERROR = iNextUniqueCode5;
        int iNextUniqueCode6 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_SSL_ERROR = iNextUniqueCode6;
        int iNextUniqueCode7 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_MAX_TOKEN_REQUESTED = iNextUniqueCode7;
        int iNextUniqueCode8 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_FAILED_GEOCHECK = iNextUniqueCode8;
        int iNextUniqueCode9 = UniqueCodeUtil.nextUniqueCode() * (-1);
        RESULT_WEBVIEW_NO_NETWORK_CONNECTION_ERROR = iNextUniqueCode9;
        sBlueMoonWebViewShown = false;
        HashSet<Integer> hashSet = new HashSet<>();
        sWebViewErrors = hashSet;
        hashSet.add(Integer.valueOf(iNextUniqueCode));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode2));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode3));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode4));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode5));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode6));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode7));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode8));
        sWebViewErrors.add(Integer.valueOf(iNextUniqueCode9));
        HashSet<Integer> hashSet2 = new HashSet<>();
        sNonFreePurchaseHandledErrorSet = hashSet2;
        hashSet2.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_12));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_13));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_14));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_15));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_16));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_17));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_18));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_19));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_20));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_21));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_22));
        sNonFreePurchaseHandledErrorSet.add(Integer.valueOf(R.string.dmusic_purchase_partnersvc_error_23));
        HashMap<WebViewReasonType, Integer> map = new HashMap<>();
        sWebViewResultMap = map;
        map.put(WebViewReasonType.TOKEN, Integer.valueOf(iNextUniqueCode7));
        sWebViewResultMap.put(WebViewReasonType.UNKNOWN, Integer.valueOf(iNextUniqueCode2));
        sWebViewResultMap.put(WebViewReasonType.INVALID_REQUEST, Integer.valueOf(iNextUniqueCode3));
        sWebViewResultMap.put(WebViewReasonType.SERVICE, Integer.valueOf(iNextUniqueCode4));
        sWebViewResultMap.put(WebViewReasonType.INTERNAL, Integer.valueOf(iNextUniqueCode5));
        sWebViewResultMap.put(WebViewReasonType.FAILED_GEOCHECK, Integer.valueOf(iNextUniqueCode8));
    }

    public static final class WebViewReturnInfo {
        private final WebViewReasonType mWebViewReasonType;
        private final WebViewResultType mWebViewResultType;

        WebViewReturnInfo(WebViewResultType webViewResultType, WebViewReasonType webViewReasonType) {
            this.mWebViewResultType = webViewResultType;
            this.mWebViewReasonType = webViewReasonType;
        }

        public WebViewResultType getResult() {
            return this.mWebViewResultType;
        }

        public WebViewReasonType getReason() {
            return this.mWebViewReasonType;
        }
    }

    public enum WebViewResultType {
        NONE("none"),
        CANCELED("canceled"),
        DONE("done"),
        ERROR("error");

        private final String mResultType;

        WebViewResultType(String resultType) {
            this.mResultType = resultType;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mResultType;
        }
    }

    public enum WebViewReasonType {
        NONE("none"),
        TOKEN("token"),
        INVALID_REQUEST("invalid_request"),
        SERVICE(NotificationCompat.CATEGORY_SERVICE),
        INTERNAL("internal"),
        FAILED_GEOCHECK("failed_geocheck"),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

        private final String mReasonType;

        WebViewReasonType(String reasonType) {
            this.mReasonType = reasonType;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mReasonType;
        }
    }

    public enum FlowType {
        PURCHASE("purchase"),
        PURCHASE_FREE("purchase_free"),
        LIBRARY("cloudplayer");

        private final String mFlowType;

        FlowType(String flowType) {
            this.mFlowType = flowType;
        }

        public String getString() {
            return this.mFlowType;
        }
    }

    private static EndPointURL getURL(String key) {
        try {
            EndPointURL endPointURL = new EndPointURL(EndpointsProvider.get().getBMWVEndpoint());
            if ("url_account_validation".equals(key)) {
                if (AccountDetailUtil.get().isRowMarketplace()) {
                    endPointURL.appendPath("/account_validate");
                } else {
                    endPointURL.appendPath("gp/dmusic/device/manage/account_validate");
                }
            } else if ("url_device_auth".equals(key)) {
                if (AccountDetailUtil.get().isRowMarketplace()) {
                    endPointURL.appendPath("/device_auth");
                } else {
                    endPointURL.appendPath("gp/dmusic/device/manage/device_auth");
                }
            }
            return endPointURL;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static EndPointURL buildURL(Context context, String key, String appendLeaf, FlowType flowType) {
        String strEncode;
        EndPointURL url = getURL(key);
        if (appendLeaf != null && appendLeaf.length() > 0) {
            url.appendPath(appendLeaf);
        }
        String cirrusNonce = AccountCredentialStorage.get(context).getCirrusNonce();
        try {
            strEncode = URLEncoder.encode(cirrusNonce, ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException unused) {
            strEncode = URLEncoder.encode(cirrusNonce);
        }
        url.appendQueryParameter("token", strEncode);
        if (ScreenUtil.isNormalXXHdpiDisplay()) {
            url.appendQueryParameter("platform", "default-xxhdpi");
        } else {
            url.appendQueryParameter("platform", "default");
        }
        if (flowType != null && flowType != FlowType.LIBRARY) {
            url.appendQueryParameter("marketplaceId", AccountDetailUtil.get(context).getHomeMarketPlaceId());
        }
        addCommonQueryParameters(url);
        return url;
    }

    private static void addCommonQueryParameters(EndPointURL endPointURL) {
        endPointURL.appendQueryParameter(Splash.PARAMS_LOCALE, AmazonApplication.getLocale().toString());
        endPointURL.appendQueryParameter("platformVersion", Build.VERSION.INCREMENTAL);
        endPointURL.appendQueryParameter(Splash.PARAMS_DEVICE_TYPE, AccountCredentialStorage.get().getDeviceType());
    }

    public static String retrieveAccountValidationURL(Context context, FlowType flowType, boolean purchaseException) {
        EndPointURL endPointURLBuildURL = buildURL(context, "url_account_validation", (purchaseException && (flowType == FlowType.PURCHASE || flowType == FlowType.PURCHASE_FREE)) ? "payment_method" : null, flowType);
        endPointURLBuildURL.appendQueryParameter("flow", flowType.getString());
        endPointURLBuildURL.appendQueryParameter(Splash.PARAMS_DEVICE_ID, AccountCredentialStorage.get(context).getDeviceId());
        return endPointURLBuildURL.toString();
    }

    public static String retrieveDeviceAuthorizationURL(Context context) {
        return buildURL(context, "url_device_auth", null, FlowType.LIBRARY).toString();
    }

    private static WebViewReturnInfo processURL(String url) {
        WebViewResultType webViewResultType = WebViewResultType.NONE;
        WebViewReasonType webViewReasonType = WebViewReasonType.NONE;
        try {
            Uri uri = Uri.parse(url);
            String path = uri.getPath();
            if (path != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("/");
                WebViewResultType webViewResultType2 = WebViewResultType.DONE;
                sb.append(webViewResultType2.toString());
                if (path.contains(sb.toString())) {
                    webViewResultType = webViewResultType2;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("/");
                    webViewResultType2 = WebViewResultType.CANCELED;
                    sb2.append(webViewResultType2.toString());
                    if (path.contains(sb2.toString())) {
                        webViewResultType = webViewResultType2;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("/");
                        WebViewResultType webViewResultType3 = WebViewResultType.ERROR;
                        sb3.append(webViewResultType3.toString());
                        if (path.contains(sb3.toString())) {
                            webViewReasonType = WebViewReasonType.UNKNOWN;
                            String queryParameter = uri.getQueryParameter("reason");
                            if (queryParameter != null) {
                                WebViewReasonType webViewReasonType2 = WebViewReasonType.TOKEN;
                                if (queryParameter.compareToIgnoreCase(webViewReasonType2.toString()) == 0) {
                                    webViewReasonType = webViewReasonType2;
                                } else {
                                    webViewReasonType2 = WebViewReasonType.INVALID_REQUEST;
                                    if (queryParameter.compareToIgnoreCase(webViewReasonType2.toString()) != 0) {
                                        webViewReasonType2 = WebViewReasonType.SERVICE;
                                        if (queryParameter.compareToIgnoreCase(webViewReasonType2.toString()) != 0) {
                                            webViewReasonType2 = WebViewReasonType.INTERNAL;
                                            if (queryParameter.compareToIgnoreCase(webViewReasonType2.toString()) != 0) {
                                                if (queryParameter.compareToIgnoreCase(webViewReasonType.toString()) != 0) {
                                                    webViewReasonType2 = WebViewReasonType.FAILED_GEOCHECK;
                                                    if (queryParameter.compareToIgnoreCase(webViewReasonType2.toString()) == 0) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    webViewReasonType = webViewReasonType2;
                                }
                            }
                            webViewResultType = webViewResultType3;
                        }
                    }
                }
            }
        } catch (NullPointerException unused) {
            webViewResultType = WebViewResultType.ERROR;
            webViewReasonType = WebViewReasonType.UNKNOWN;
        }
        Log.debug("BlueMoon_WebViewUtil", "processURL: result=%s, reason=%s", webViewResultType.toString(), webViewReasonType.toString());
        return new WebViewReturnInfo(webViewResultType, webViewReasonType);
    }

    public static final class PurchaseAccountValidation {
        public static int getContactUsId() {
            return R.string.dmusic_purchase_account_validation_contact_us;
        }

        public static int getFailedGeocheckErrorStringId() {
            return R.string.dmusic_purchase_account_validation_failed_geocheck_error;
        }

        public static int getInternalErrorStringId() {
            return R.string.dmusic_purchase_account_validation_internal_error;
        }

        public static int getInvalidRequestErrorStringId() {
            return R.string.dmusic_purchase_account_validation_invalid_request_error;
        }

        public static int getMaxTokenRequestedErrorStringId() {
            return R.string.dmusic_purchase_account_validation_max_token_requested_error;
        }

        public static int getNetworkErrorStringId() {
            return R.string.dmusic_purchase_account_validation_network_error;
        }

        public static int getPositiveButtonId() {
            return R.string.dmusic_purchase_account_validation_positive_button;
        }

        public static int getSSLErrorStringId() {
            return R.string.dmusic_purchase_account_validation_ssl_error;
        }

        public static int getServiceErrorStringId() {
            return R.string.dmusic_purchase_account_validation_service_error;
        }

        public static int getTitleStringId() {
            return R.string.dmusic_purchase_account_validation_error_title;
        }

        public static int getUnknownErrorStringId() {
            return R.string.dmusic_purchase_account_validation_unknown_error;
        }

        protected PurchaseAccountValidation() {
        }
    }

    public static final class LibraryAccountValidation {
        public static int getContactUsId() {
            return R.string.dmusic_library_cloud_account_validation_contact_us;
        }

        public static int getInternalErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_internal_error;
        }

        public static int getInvalidRequestErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_invalid_request_error;
        }

        public static int getMaxTokenRequestedErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_max_token_requested_error;
        }

        public static int getNetworkErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_network_error;
        }

        public static int getPositiveButtonId() {
            return R.string.dmusic_library_cloud_account_validation_positive_button;
        }

        public static int getSSLErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_ssl_error;
        }

        public static int getServiceErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_service_error;
        }

        public static int getTitleStringId() {
            return R.string.dmusic_library_cloud_account_validation_error_title;
        }

        public static int getUnknownErrorStringId() {
            return R.string.dmusic_library_cloud_account_validation_unknown_error;
        }

        protected LibraryAccountValidation() {
        }
    }

    public static final class LibraryDeviceAuthorization {
        public static int getContactUsId() {
            return R.string.dmusic_library_device_authorization_contact_us;
        }

        public static int getInternalErrorStringId() {
            return R.string.dmusic_library_device_authorization_internal_error;
        }

        public static int getInvalidRequestErrorStringId() {
            return R.string.dmusic_library_device_authorization_invalid_request_error;
        }

        public static int getMaxTokenRequestedErrorStringId() {
            return R.string.dmusic_library_device_authorization_max_token_requested_error;
        }

        public static int getNetworkErrorStringId() {
            return R.string.dmusic_library_device_authorization_network_error;
        }

        public static int getPositiveButtonId() {
            return R.string.dmusic_library_device_authorization_positive_button;
        }

        public static int getSSLErrorStringId() {
            return R.string.dmusic_library_device_authorization_ssl_error;
        }

        public static int getServiceErrorStringId() {
            return R.string.dmusic_library_device_authorization_service_error;
        }

        public static int getTitleStringId() {
            return R.string.dmusic_library_device_authorization_error_title;
        }

        public static int getUnknownErrorStringId() {
            return R.string.dmusic_library_device_authorization_unknown_error;
        }

        protected LibraryDeviceAuthorization() {
        }

        public static int getNoConnectionStringId() {
            return ConnectivityUtil.isWifiOnlyDevice() ? R.string.dmusic_no_connection_desc_wifi_only : R.string.dmusic_no_connection_desc_generic;
        }
    }

    public static Dialog createAccountValidationErrorDialog(final Activity activity, FlowType flowType, int resultCode) {
        int unknownErrorStringId;
        int positiveButtonId;
        int contactUsId;
        int titleStringId = 0;
        if (resultCode == RESULT_WEBVIEW_NETWORK_ERROR) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getNetworkErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getNetworkErrorStringId();
            }
        } else if (resultCode == RESULT_WEBVIEW_UNKNOWN_ERROR) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getUnknownErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getUnknownErrorStringId();
            }
        } else if (resultCode == RESULT_WEBVIEW_INVALID_REQUEST_ERROR) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getInvalidRequestErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getInvalidRequestErrorStringId();
            }
        } else if (resultCode == RESULT_WEBVIEW_SERVICE_ERROR) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getServiceErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getServiceErrorStringId();
            }
        } else if (resultCode == RESULT_WEBVIEW_INTERNAL_ERROR) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getInternalErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getInternalErrorStringId();
            }
        } else if (resultCode == RESULT_WEBVIEW_SSL_ERROR) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getSSLErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getSSLErrorStringId();
            }
        } else if (resultCode == RESULT_WEBVIEW_MAX_TOKEN_REQUESTED) {
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getMaxTokenRequestedErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getMaxTokenRequestedErrorStringId();
            }
            titleStringId = R.string.dmusic_cloud_player_error;
        } else {
            if (resultCode != RESULT_WEBVIEW_FAILED_GEOCHECK) {
                return null;
            }
            if (flowType == FlowType.PURCHASE) {
                unknownErrorStringId = PurchaseAccountValidation.getFailedGeocheckErrorStringId();
            } else {
                unknownErrorStringId = LibraryAccountValidation.getUnknownErrorStringId();
            }
        }
        if (titleStringId == 0) {
            if (flowType == FlowType.PURCHASE) {
                titleStringId = PurchaseAccountValidation.getTitleStringId();
            } else {
                titleStringId = LibraryAccountValidation.getTitleStringId();
            }
        }
        FlowType flowType2 = FlowType.PURCHASE;
        if (flowType == flowType2) {
            positiveButtonId = PurchaseAccountValidation.getPositiveButtonId();
        } else {
            positiveButtonId = LibraryAccountValidation.getPositiveButtonId();
        }
        if (flowType == flowType2) {
            contactUsId = PurchaseAccountValidation.getContactUsId();
        } else {
            contactUsId = LibraryAccountValidation.getContactUsId();
        }
        return createErrorDialog(activity, titleStringId, unknownErrorStringId, positiveButtonId, contactUsId);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.app.Dialog createDeviceAuthorizationErrorDialog(final android.app.Activity r5, int r6) {
        /*
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_NETWORK_ERROR
            r1 = 0
            if (r6 != r0) goto Lb
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getNetworkErrorStringId()
        L9:
            r2 = r1
            goto L56
        Lb:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_UNKNOWN_ERROR
            if (r6 != r0) goto L14
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getUnknownErrorStringId()
            goto L9
        L14:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_INVALID_REQUEST_ERROR
            if (r6 != r0) goto L1d
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getInvalidRequestErrorStringId()
            goto L9
        L1d:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_SERVICE_ERROR
            if (r6 != r0) goto L26
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getServiceErrorStringId()
            goto L9
        L26:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_INTERNAL_ERROR
            if (r6 != r0) goto L2f
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getInternalErrorStringId()
            goto L9
        L2f:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_SSL_ERROR
            if (r6 != r0) goto L38
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getSSLErrorStringId()
            goto L9
        L38:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_MAX_TOKEN_REQUESTED
            if (r6 != r0) goto L44
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getMaxTokenRequestedErrorStringId()
            r2 = 2132017901(0x7f1402ed, float:1.9674093E38)
            goto L56
        L44:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_FAILED_GEOCHECK
            if (r6 != r0) goto L4d
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getUnknownErrorStringId()
            goto L9
        L4d:
            int r0 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_NO_NETWORK_CONNECTION_ERROR
            if (r6 != r0) goto L6d
            int r0 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getNoConnectionStringId()
            goto L9
        L56:
            if (r2 != 0) goto L5c
            int r2 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getTitleStringId()
        L5c:
            int r3 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getPositiveButtonId()
            int r4 = com.amazon.mp3.util.WebViewUtil.RESULT_WEBVIEW_NO_NETWORK_CONNECTION_ERROR
            if (r6 == r4) goto L68
            int r1 = com.amazon.mp3.util.WebViewUtil.LibraryDeviceAuthorization.getContactUsId()
        L68:
            android.app.Dialog r5 = createErrorDialog(r5, r2, r0, r3, r1)
            return r5
        L6d:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.WebViewUtil.createDeviceAuthorizationErrorDialog(android.app.Activity, int):android.app.Dialog");
    }

    private static Dialog createErrorDialog(final Activity activity, int titleId, int messageId, int positiveButtonId, int contactUsButtonId) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(activity);
        bauhausDialogBuilder.setTitle(titleId);
        bauhausDialogBuilder.setMessage(messageId);
        bauhausDialogBuilder.setCancelable(false);
        bauhausDialogBuilder.setPositiveButton(positiveButtonId, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.WebViewUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                Activity activity2 = activity;
                activity2.startActivity(MusicHomeActivity.getStartIntent(activity2, LastViewedScreenUtil.LastViewedSource.DEVICE));
            }
        });
        if (contactUsButtonId != 0) {
            bauhausDialogBuilder.setNegativeButton(contactUsButtonId, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.WebViewUtil.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    EmailUtil.contactCustomerSupport(activity);
                    activity.finish();
                }
            });
        }
        return bauhausDialogBuilder.create();
    }

    public static boolean isWebViewActivity(BaseActivity activity) {
        if (activity == null) {
            return false;
        }
        return (activity instanceof BaseAccountValidationWebView) || (activity instanceof LibraryDeviceAuthorizationWebViewActivity);
    }

    public static boolean isWebViewError(int error) {
        return sWebViewErrors.contains(Integer.valueOf(error));
    }

    public static boolean isAccountValidationValid(Context context) {
        return isAccountValidationValid(context, FlowType.LIBRARY);
    }

    public static boolean isAccountValidationValid(Context context, FlowType flowType) {
        AccountDetailStorage accountDetailStorage = AccountDetailStorage.get(context);
        return (flowType == FlowType.LIBRARY ? accountDetailStorage.isAccountVerified() : true) && accountDetailStorage.hasAcceptedTermsOfUse();
    }

    public static boolean isBlueMoonAndAccountActive(Context context) {
        try {
            User user = AccountManagerSingleton.get().getUser();
            if (user.isAccountValid()) {
                return !user.isAccountLocked();
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    public static Intent getDeviceAuthorizationIntent(Context context) {
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported() && AccountDetailStorage.get(context).isAccountVerified() && isBlueMoonAndAccountActive(context) && !DeviceAuthorizationManager.getInstance().autoAuthorize(context)) {
            return LibraryDeviceAuthorizationWebViewActivity.getStartIntent(context);
        }
        return null;
    }

    public static void startBrowserActivity(final Context context, String url) {
        if (url == null || url.length() <= 0) {
            return;
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
    }

    public static boolean shouldOpenBrowser(String url) {
        Uri uri;
        if (url == null || (uri = Uri.parse(url)) == null) {
            return false;
        }
        String path = uri.getPath();
        return (path.contains("/gp/dmusic/device/manage") || path.contains("/gp/dmusic/shared/messaging") || path.contains("/ap/signin") || path.contains("/gp/redirect.html") || path.contains("/BMWV/device_auth") || path.contains("/gp/dmusic/player/settings") || path.contains("/music/settings") || path.contains("/BMWV/account_validate") || path.contains("/settings")) ? false : true;
    }

    public static class WebViewProgressBar extends Dialog {
        private Activity mActivity;

        protected WebViewProgressBar(Activity activity) {
            super(activity, R.style.Theme_Dialog_windowNoTitle_progressbar_general);
            this.mActivity = activity;
            requestWindowFeature(1);
            setContentView(R.layout.common_webview_progressbar);
            setCancelable(false);
        }

        public void showProgressBar() {
            showProgressBar(-1);
        }

        public void showProgressBar(int stringId) {
            TextView textView = (TextView) findViewById(R.id.text);
            if (textView != null) {
                if (stringId > 0) {
                    textView.setText(getContext().getString(stringId));
                    textView.setVisibility(0);
                } else {
                    textView.setVisibility(8);
                }
            }
            dismissProgressBar();
            findViewById(R.id.progress).setVisibility(0);
            if (this.mActivity.isFinishing()) {
                return;
            }
            show();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r1v4 */
        public void dismissProgressBar() {
            if (isShowing()) {
                int iFindViewById = 2131429863;
                iFindViewById = 2131429863;
                try {
                    try {
                        dismiss();
                    } catch (IllegalArgumentException unused) {
                        Log.warning("BlueMoon_WebViewUtil", "IllegalArgumentException trying to dismiss the dialog");
                    }
                } finally {
                    findViewById(iFindViewById).setVisibility(8);
                }
            }
        }
    }

    public static WebViewProgressBar createWebViewProgressBar(Activity activity) {
        if (activity == null) {
            return null;
        }
        return new WebViewProgressBar(activity);
    }

    public static void processPageFinished(String url, boolean timeOut, WebViewResult onResult) {
        WebViewReturnInfo webViewReturnInfoProcessURL;
        WebViewResultType result;
        if (onResult == null || WebViewResultType.NONE == (result = (webViewReturnInfoProcessURL = processURL(url)).getResult())) {
            return;
        }
        Log.debug("BlueMoon_WebViewUtil", "processPageFinished: result type is %s", result.toString());
        if (result == WebViewResultType.DONE) {
            if (!timeOut) {
                onResult.done();
                return;
            } else {
                Log.warning("BlueMoon_WebViewUtil", "The timer time out before the page finished via the done URL");
                onResult.doneTimedOut();
                return;
            }
        }
        if (result == WebViewResultType.CANCELED) {
            if (!timeOut) {
                onResult.cancelled();
                return;
            } else {
                Log.warning("BlueMoon_WebViewUtil", "The timer time out before the page finished via the cancel URL");
                onResult.cancelledTimedOut();
                return;
            }
        }
        if (result != WebViewResultType.ERROR || timeOut) {
            return;
        }
        WebViewReasonType reason = webViewReturnInfoProcessURL.getReason();
        Log.error("BlueMoon_WebViewUtil", "processPageFinished: result type is %s and the reason type is %s", result.toString(), reason.toString());
        onResult.error(reason, sWebViewResultMap.get(reason));
    }

    public static void handleSslError(final Context context, final WebSettings webSettings) {
        if (webSettings == null || webSettings.getUserAgentString() == null) {
            return;
        }
        String userAgentString = webSettings.getUserAgentString();
        Log.debug("BlueMoon_WebViewUtil", userAgentString);
        if (userAgentString.contains("Chrome/53.") || userAgentString.contains("Chrome/54.")) {
            showDialog(context, false);
            sendFlexEvent(userAgentString);
        }
    }

    public static void handleSslErrorWithMapSignInWebView(final Context context) {
        showDialog(context, true);
        sendFlexEvent("MAPSignInWebView");
    }

    private static void showDialog(final Context context, final boolean closeParentActivityAfterClick) {
        int i;
        int i2;
        if (PlatformUtil.isVersionOrGreater(24)) {
            i = R.string.dmusic_error_upgrade_chrome_message;
            i2 = R.string.dmusic_error_upgrade_chrome_button;
        } else {
            i = R.string.dmusic_error_upgrade_webview_message;
            i2 = R.string.dmusic_error_upgrade_webview_button;
        }
        new BauhausDialogBuilder(context).setTitle(R.string.dmusic_error_upgrade_webview_title).setMessage(i).setPositiveButton(i2, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.util.WebViewUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(PlatformUtil.isVersionOrGreater(24) ? "market://details?id=com.android.chrome" : "market://details?id=com.google.android.webview"));
                intent.setFlags(268435456);
                context.startActivity(intent);
                if (closeParentActivityAfterClick) {
                    dialogInterface.dismiss();
                    Log.info("BlueMoon_WebViewUtil", "Closing parent activity of web view error dialog.");
                    ((Activity) context).finish();
                }
            }
        }).create().show();
    }

    private static void sendFlexEvent(final String userAgentString) {
        FlexEvent.Builder builder = FlexEvent.builder("userHasWebviewSslBug");
        if (userAgentString.contains("Chrome/53.")) {
            builder.withFlexStr1("Chrome/53.");
        } else if (userAgentString.contains("Chrome/54.")) {
            builder.withFlexStr1("Chrome/54.");
        } else if (userAgentString.equals("MAPSignInWebView")) {
            builder.withFlexStr1("MAPSignInWebView");
        }
        MetricsLogger.sendEvent(builder.build());
    }

    @NonNull
    public static String addLocaleToUrlString(@NonNull String urlString) {
        Locale locale = AmazonApplication.getLocale();
        String str = locale.getLanguage() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + locale.getCountry();
        HttpUrl httpUrl = HttpUrl.parse(urlString);
        HttpUrl httpUrlBuild = httpUrl != null ? httpUrl.newBuilder().addQueryParameter(Splash.PARAMS_LOCALE, str).build() : null;
        return httpUrlBuild != null ? httpUrlBuild.getUrl() : urlString;
    }
}
