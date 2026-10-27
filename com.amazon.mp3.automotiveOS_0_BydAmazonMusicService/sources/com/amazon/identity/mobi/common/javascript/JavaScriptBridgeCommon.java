package com.amazon.identity.mobi.common.javascript;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.mobi.common.utils.MetricsEmitter;
import com.amazon.identity.mobi.common.utils.ThreadUtils;
import com.amazon.identity.mobi.common.utils.UrlCommonUtils;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class JavaScriptBridgeCommon {
    protected static final String CALLBACK_ID = "callingId";
    protected static final String DEFAULT_CALLBACK_NAME = "mapJSCallback";
    public static final String ERROR = "error";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String GENERAL_ERROR = "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}";
    protected static final String INJECT_TOKEN_CALLBACK_NAME = "injectTokenCallback";
    public static final String INPUT_ERROR = "{\"error\":\"Invalid_Input_Param\",\"errorMessage\":\"The API input is invalid JSON\"}";
    public static final String NOT_SUPPORTED_ERROR = "{\"error\":\"Not_Supported\",\"errorMessage\":\"This call is unsupported in MAP\"}";
    protected static final String ORIGINAL_FUNCTION_KEY = "originalFunction";
    public static final String SERVICE_ERROR = "{\"error\": \"Service_Error\", \"errorMessage\":\"A backend service error occurred\"}";
    protected static final String TOKEN_KEY = "token";
    private final FunctionEvaluator mFunctionEvaluator;
    private final MetricsEmitter mMetricsEmitter;
    protected final String mTag;
    private final Map<String, String> mTokenMap;
    protected final WebView mWebView;

    public interface FunctionEvaluator {
        boolean shouldFunctionReturnBoolean(String str);
    }

    protected JavaScriptBridgeCommon(WebView webView, String str, MetricsEmitter metricsEmitter, FunctionEvaluator functionEvaluator, Map<String, String> map) {
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
            invokeCallbackWithError(str2, str, GENERAL_ERROR, "Unauthorized to call");
            return false;
        } catch (JSONException unused) {
            Log.i(this.mTag, str2 + " call failed due to invalid input");
            invokeCallbackWithError(str2, "Invalid JSON input", INPUT_ERROR, "Unauthorized to call");
            return false;
        } catch (Exception unused2) {
            Log.i(this.mTag, str2 + " call failed due exception being thrown while authenticating the call");
            invokeCallbackWithError(str2, str, GENERAL_ERROR, "Unauthorized to call");
            return false;
        }
    }

    void injectToken(final String str, String str2, String str3) {
        try {
            if (this.mTokenMap.containsKey(str)) {
                Log.i(this.mTag, str2 + " inject token failed because the token map already contains an entry for the callbackId.");
                this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":TokenAlreadyInjected");
                invokeCallbackWithError(str2, str3, GENERAL_ERROR, "Unauthorized to call");
                return;
            }
            if (this.mTokenMap.size() >= 15) {
                Log.i(this.mTag, str2 + " inject token failed due to too many token keys in the token map.");
                this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":TooManyTokens");
                invokeCallbackWithError(str2, str3, GENERAL_ERROR, "Unauthorized to call");
                return;
            }
            String string = UUID.randomUUID().toString();
            this.mWebView.loadUrl("javascript: var newDiv = document.createElement(\"div\");newDiv.innerText = \"" + string + "\";newDiv.setAttribute(\"id\",\"" + str + "\");newDiv.style.display=\"none\";document.body.appendChild(newDiv);");
            this.mTokenMap.put(str, string);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon.4
                @Override // java.lang.Runnable
                public void run() {
                    JavaScriptBridgeCommon.this.mTokenMap.remove(str);
                }
            }, 10000L);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CALLBACK_ID, str);
            jSONObject.put(ORIGINAL_FUNCTION_KEY, str2);
            loadCallbackFunction(INJECT_TOKEN_CALLBACK_NAME, str3, jSONObject.toString());
        } catch (Exception unused) {
            Log.i(this.mTag, str2 + " inject token call failed because an exception was thrown.");
            this.mMetricsEmitter.incrementCounterAndRecord(str2 + ":InjectTokenException");
            invokeCallbackWithError(str2, str3, GENERAL_ERROR, "Unauthorized to call");
        }
    }

    protected void invoke(final String str, final Runnable runnable) {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon.2
            @Override // java.lang.Runnable
            public void run() {
                if (JavaScriptBridgeCommon.this.isAmazonDomain(str)) {
                    runnable.run();
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
        final String str4 = String.format("javascript:if (typeof %1$s !== 'undefined' && typeof %1$s === 'function'){%1$s(%2$s,%3$s);}", str, str2, str3);
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon.1
            @Override // java.lang.Runnable
            public void run() {
                JavaScriptBridgeCommon.this.mWebView.loadUrl(str4);
            }
        });
    }

    protected void loadCallbackFunctionWithErrorMessage(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject(str3);
            jSONObject.put("errorMessage", str4);
            loadCallbackFunction(str, str2, jSONObject.toString());
        } catch (Exception unused) {
            loadCallbackFunction(str, str2, GENERAL_ERROR);
        }
    }

    protected void invoke(final String str, final String str2, final AsyncJavaScriptCall asyncJavaScriptCall) {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (JavaScriptBridgeCommon.this.isAmazonDomain(str)) {
                        if (str2 != null) {
                            JSONObject jSONObject = new JSONObject(str2);
                            if (!jSONObject.getString(JavaScriptBridgeCommon.CALLBACK_ID).matches("\\w+\\d+")) {
                                JavaScriptBridgeCommon.this.invokeCallbackWithError(str, "\"Invalid JSON Input\"", JavaScriptBridgeCommon.INPUT_ERROR, "Invalid callbackId");
                                return;
                            } else {
                                if (JavaScriptBridgeCommon.this.containsIFrameProtectionToken(str2, str)) {
                                    asyncJavaScriptCall.run(jSONObject, new Promise() { // from class: com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon.3.1
                                        @Override // com.amazon.identity.mobi.common.javascript.Promise
                                        public void setResult(String str3) {
                                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                            JavaScriptBridgeCommon.this.loadCallbackFunction(JavaScriptBridgeCommon.DEFAULT_CALLBACK_NAME, str2, str3);
                                        }

                                        @Override // com.amazon.identity.mobi.common.javascript.Promise
                                        public void setResultWithError(String str3, String str4) {
                                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                            JavaScriptBridgeCommon.this.loadCallbackFunctionWithErrorMessage(JavaScriptBridgeCommon.DEFAULT_CALLBACK_NAME, str2, str3, str4);
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                        }
                        Log.i(JavaScriptBridgeCommon.this.mTag, "Call to " + str + " failed because JSON input was null");
                        JavaScriptBridgeCommon.this.mMetricsEmitter.incrementCounterAndRecord(str + ":NullInput");
                        JavaScriptBridgeCommon.this.invokeCallbackWithError(str, null, JavaScriptBridgeCommon.INPUT_ERROR, "JSON input was null");
                    }
                } catch (JSONException unused) {
                    JavaScriptBridgeCommon.this.invokeCallbackWithError(str, "\"Invalid JSON Input\"", JavaScriptBridgeCommon.INPUT_ERROR, "JSONException while parsing input");
                } catch (Exception unused2) {
                    JavaScriptBridgeCommon.this.invokeCallbackWithError(str, str2, JavaScriptBridgeCommon.GENERAL_ERROR, "Exception thrown while executing function");
                }
            }
        });
    }
}
