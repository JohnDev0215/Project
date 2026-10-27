package com.amazon.identity.mobi.common.javascript;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import com.amazon.identity.mobi.common.utils.MetricsEmitter;
import com.amazon.identity.mobi.common.utils.StringUtils;
import com.amazon.identity.mobi.common.utils.ThreadUtils;
import com.amazon.identity.mobi.common.utils.UrlCommonUtils;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JavaScriptBridgeCommonV2 {
    protected static final String CALLBACK_ID = "callingId";
    protected static final String DEFAULT_CALLBACK_NAME = "mapJSCallback";
    public static final String ERROR = "error";
    public static final String ERROR_MESSAGE = "errorMessage";
    protected static final String INJECT_TOKEN_CALLBACK_NAME = "injectTokenCallback";
    public static final String MESSAGE = "message";
    public static final String NEW_GENERAL_ERROR = "{\"error\":\"General_Error\",\"errorMessage\":{\"message\":\"An internal MAP error exception occurred\"}}";
    public static final String NEW_INPUT_ERROR = "{\"error\":\"Input_Error\",\"errorMessage\":{\"message\":\"The API input is invalid JSON\"}}";
    public static final String NEW_NOT_SUPPORTED_ERROR = "{\"error\":\"Not_Supported\",\"errorMessage\":{\"message\":\"This call is unsupported in MAP\"}}";
    public static final String NEW_SERVICE_ERROR = "{\"error\":\"Service_Error\",\"errorMessage\":{\"message\":\"A backend service error occurred\"}}";
    protected static final String ORIGINAL_FUNCTION_KEY = "originalFunction";
    protected static final String TOKEN_KEY = "token";
    private final JavaScriptBridgeCommon.FunctionEvaluator mFunctionEvaluator;
    private final MetricsEmitter mMetricsEmitter;
    protected final String mTag;
    private final Map<String, String> mTokenMap;
    protected final WebView mWebView;

    protected JavaScriptBridgeCommonV2(WebView webView, String str, MetricsEmitter metricsEmitter, JavaScriptBridgeCommon.FunctionEvaluator functionEvaluator, Map<String, String> map) {
        this.mWebView = webView;
        this.mTag = str;
        this.mMetricsEmitter = metricsEmitter;
        this.mFunctionEvaluator = functionEvaluator;
        this.mTokenMap = map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeCallbackWithError(String str, String str2, String str3, String str4) {
        if (this.mFunctionEvaluator.shouldFunctionReturnBoolean(str)) {
            loadCallbackFunction(DEFAULT_CALLBACK_NAME, str2, String.valueOf(false));
        } else {
            loadCallbackFunctionWithErrorMessage(DEFAULT_CALLBACK_NAME, str2, str3, str4);
        }
    }

    boolean containsIFrameProtectionToken(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(TOKEN_KEY, null);
            String string = jSONObject.getString(CALLBACK_ID);
            if (strOptString == null) {
                Log.i(this.mTag, "Injecting token for " + str2 + " call.");
                injectToken(string, str2, str);
                return false;
            }
            String strRemove = this.mTokenMap.remove(string);
            if (strRemove != null ? strRemove.equals(strOptString) : false) {
                Log.i(this.mTag, str2 + " call was successfully authenticated.");
                this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":SuccessfulAuth");
                return true;
            }
            Log.i(this.mTag, str2 + " call failed due to authentication error.");
            this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":InvalidAuthToken");
            invokeCallbackWithError(str2, str, NEW_GENERAL_ERROR, "Unauthorized to call");
            return false;
        } catch (JSONException unused) {
            Log.i(this.mTag, str2 + " call failed due to invalid input");
            invokeCallbackWithError(str2, "Invalid JSON input", NEW_INPUT_ERROR, "Unauthorized to call");
            return false;
        } catch (Exception unused2) {
            Log.i(this.mTag, str2 + " call failed due exception being thrown while authenticating the call");
            invokeCallbackWithError(str2, str, NEW_GENERAL_ERROR, "Unauthorized to call");
            return false;
        }
    }

    void injectToken(final String str, String str2, String str3) {
        try {
            if (this.mTokenMap.containsKey(str)) {
                Log.i(this.mTag, str2 + " inject token failed because the token map already contains an entry for the callbackId.");
                this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":TokenAlreadyInjected");
                invokeCallbackWithError(str2, str3, NEW_GENERAL_ERROR, "Unauthorized to call");
                return;
            }
            if (this.mTokenMap.size() >= 15) {
                Log.i(this.mTag, str2 + " inject token failed due to too many token keys in the token map.");
                this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":TooManyTokens");
                invokeCallbackWithError(str2, str3, NEW_GENERAL_ERROR, "Unauthorized to call");
                return;
            }
            String string = UUID.randomUUID().toString();
            this.mWebView.loadUrl("javascript: var newDiv = document.createElement(\"div\");newDiv.innerText = \"" + string + "\";newDiv.setAttribute(\"id\",\"" + str + "\");newDiv.style.display=\"none\";document.body.appendChild(newDiv);");
            this.mTokenMap.put(str, string);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommonV2.3
                @Override // java.lang.Runnable
                public void run() {
                    JavaScriptBridgeCommonV2.this.mTokenMap.remove(str);
                }
            }, 10000L);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CALLBACK_ID, str);
            jSONObject.put(ORIGINAL_FUNCTION_KEY, str2);
            loadCallbackFunction(INJECT_TOKEN_CALLBACK_NAME, str3, jSONObject.toString());
        } catch (Exception unused) {
            Log.i(this.mTag, str2 + " inject token call failed because an exception was thrown.");
            this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":InjectTokenException");
            invokeCallbackWithError(str2, str3, NEW_GENERAL_ERROR, "Unauthorized to call");
        }
    }

    protected void invokeWithEncoding(final String str, final String str2, final AsyncJavaScriptCall asyncJavaScriptCall) {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommonV2.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (JavaScriptBridgeCommonV2.this.isAmazonDomain(str)) {
                        String str3 = str2;
                        if (str3 != null) {
                            final String strDecodeBase64URLToString = StringUtils.decodeBase64URLToString(str3);
                            JSONObject jSONObject = new JSONObject(strDecodeBase64URLToString);
                            if (!jSONObject.getString(JavaScriptBridgeCommonV2.CALLBACK_ID).matches("\\w+\\d+")) {
                                JavaScriptBridgeCommonV2.this.invokeCallbackWithError(str, "\"Invalid JSON Input\"", JavaScriptBridgeCommonV2.NEW_INPUT_ERROR, "Invalid callbackId");
                                return;
                            } else {
                                if (JavaScriptBridgeCommonV2.this.containsIFrameProtectionToken(strDecodeBase64URLToString, str)) {
                                    asyncJavaScriptCall.run(jSONObject, new Promise() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommonV2.2.1
                                        @Override // com.amazon.identity.mobi.common.javascript.Promise
                                        public void setResult(String str4) {
                                            JavaScriptBridgeCommonV2.this.loadCallbackFunction(JavaScriptBridgeCommonV2.DEFAULT_CALLBACK_NAME, strDecodeBase64URLToString, str4);
                                        }

                                        @Override // com.amazon.identity.mobi.common.javascript.Promise
                                        public void setResultWithError(String str4, String str5) {
                                            JavaScriptBridgeCommonV2.this.loadCallbackFunctionWithErrorMessage(JavaScriptBridgeCommonV2.DEFAULT_CALLBACK_NAME, strDecodeBase64URLToString, str4, str5);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                        }
                        Log.i(JavaScriptBridgeCommonV2.this.mTag, "Call to " + str + " failed because JSON input was null");
                        JavaScriptBridgeCommonV2.this.mMetricsEmitter.incrementCounterAndRecord(str + ":NullInput");
                        JavaScriptBridgeCommonV2.this.invokeCallbackWithError(str, null, JavaScriptBridgeCommonV2.NEW_INPUT_ERROR, "JSON input was null");
                    }
                } catch (JSONException unused) {
                    JavaScriptBridgeCommonV2.this.invokeCallbackWithError(str, "\"Invalid JSON Input\"", JavaScriptBridgeCommonV2.NEW_INPUT_ERROR, "JSONException while parsing input");
                } catch (Exception unused2) {
                    JavaScriptBridgeCommonV2.this.invokeCallbackWithError(str, StringUtils.decodeBase64URLToString(str2, "Original param is not proper encoded with Base64URL"), JavaScriptBridgeCommonV2.NEW_GENERAL_ERROR, "Exception thrown while executing function");
                }
            }
        });
    }

    protected boolean isAmazonDomain(String str) {
        URL urlCreateUrl = UrlCommonUtils.createUrl(this.mWebView.getUrl());
        if (urlCreateUrl == null) {
            return false;
        }
        String host = urlCreateUrl.getHost();
        String path = urlCreateUrl.getPath();
        this.mMetricsEmitter.incrementCounterAndRecord(str + ":" + host + ":" + path);
        return UrlCommonUtils.isAmazonOwnedDomain(urlCreateUrl.toString());
    }

    protected void loadCallbackFunction(String str, String str2, String str3) {
        Log.i(this.mTag, "loadCallbackFunction");
        final String str4 = String.format("javascript:if (typeof %1$s !== 'undefined' && typeof %1$s === 'function'){%1$s(%2$s,%3$s);}", str, StringUtils.encodeToBase64URL(str2), StringUtils.encodeToBase64URL(str3));
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommonV2.1
            @Override // java.lang.Runnable
            public void run() {
                JavaScriptBridgeCommonV2.this.mWebView.loadUrl(str4);
            }
        });
    }

    protected void loadCallbackFunctionWithErrorMessage(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject(str3);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("message", str4);
            } catch (JSONException unused) {
                Log.e(this.mTag, "Error assembling errorMessage blob, this shouldn't happen");
            }
            jSONObject.put("errorMessage", jSONObject2);
            loadCallbackFunction(str, str2, jSONObject.toString());
        } catch (Exception unused2) {
            loadCallbackFunction(str, str2, NEW_GENERAL_ERROR);
        }
    }
}
