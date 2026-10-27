package com.amazon.mp3.store.html5.bridge;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.capability.DevModeCapabilities;
import com.amazon.mp3.prime.upsell.UpsellUrls;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.ATCUrls;
import com.amplifyframework.core.model.ModelIdentifier;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class JavascriptBridge {
    private static final String TAG = "JavascriptBridge";
    private static final DevModeCapabilities sDevModeCapabilities = AmazonApplication.getDevModeCapabilities();
    private final String mJsInterfaceName;
    private OnMessageReceivedListener mListener;
    private WebView mWebView;
    private final Handler mHandler = new Handler();
    private final HashMap<String, OnMessageReceivedListener> mResponseListeners = new HashMap<>();

    public interface OnMessageReceivedListener {
        void onMessageReceived(Message message);
    }

    public JavascriptBridge(final String jsInterfaceName) {
        this.mJsInterfaceName = jsInterfaceName;
    }

    public void attach(WebView webView, OnMessageReceivedListener listener) throws IllegalStateException, IllegalArgumentException {
        Log.debug(TAG, "Bridge attached to webview");
        if (webView == null) {
            throw new IllegalArgumentException("webView must be non-null");
        }
        if (this.mWebView != null) {
            throw new IllegalStateException("This instance is already attached to a WebView");
        }
        this.mListener = listener;
        this.mWebView = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        safeRemoveJavascriptInterface(this.mWebView, this.mJsInterfaceName);
        this.mWebView.addJavascriptInterface(new Injectable(this, null), this.mJsInterfaceName);
    }

    public void attachListener(OnMessageReceivedListener listener) {
        this.mListener = listener;
    }

    public void detach(WebView webView) {
        if (webView != this.mWebView) {
            throw new IllegalArgumentException("attempting to detach a different WebView than the one that is attached");
        }
        this.mResponseListeners.clear();
        this.mListener = null;
        this.mWebView = null;
    }

    public void detachListener(OnMessageReceivedListener listener) {
        if (this.mListener == listener) {
            this.mListener = null;
        }
    }

    public void broadcast(String name, JSONObject options) {
        sendMessage(Message.createBroadcast(name, options));
    }

    public void broadcast(String name) {
        broadcast(name, null);
    }

    public void request(String name) {
        sendMessage(Message.createRequest(name));
    }

    public void respond(Message request, String name, JSONObject options) {
        sendMessage(Message.createResponse(request.getId(), name, options));
    }

    private void sendMessage(Message m) {
        if (sDevModeCapabilities.isStoreDebugModeEnabled()) {
            Log.debug(TAG, "send: %s", m.toString());
        }
        WebView webView = this.mWebView;
        if (webView == null || !isWhitelistedWebviewUrl(webView.getUrl())) {
            return;
        }
        this.mWebView.loadUrl("javascript:if(window.androidHostBridge){window.androidHostBridge.onMessageFromHost(\"{{arguments}}\")}".replace("{{arguments}}", m.toString().replace(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "\\\"")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitelistedWebviewUrl(final String url) {
        UpsellUrls upsellUrls = new UpsellUrls();
        return StoreUrls.isStoreDomain(url) || upsellUrls.isUpsellUrl(url) || upsellUrls.isSplashUrl(url) || ATCUrls.isATCUrl(url);
    }

    private static void safeRemoveJavascriptInterface(final WebView instance, final String name) {
        try {
            instance.removeJavascriptInterface(name);
        } catch (Exception e) {
            Log.debug(TAG, "Unable to removeJavascriptInterface", e);
        }
    }

    private class Injectable {
        private Injectable() {
        }

        /* synthetic */ Injectable(JavascriptBridge javascriptBridge, AnonymousClass1 anonymousClass1) {
            this();
        }

        @JavascriptInterface
        public void sendMessage(String rawMessage) {
            final Message messageFromJsonString = Message.fromJsonString(rawMessage);
            if (messageFromJsonString == null) {
                Log.debug(JavascriptBridge.TAG, "Message failed to parse");
            } else {
                JavascriptBridge.this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.store.html5.bridge.JavascriptBridge.Injectable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JavascriptBridge.this.mWebView == null || !JavascriptBridge.isWhitelistedWebviewUrl(JavascriptBridge.this.mWebView.getUrl())) {
                            return;
                        }
                        Injectable.this.handleMessage(messageFromJsonString);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleMessage(final Message message) {
            OnMessageReceivedListener onMessageReceivedListener;
            try {
                if (AnonymousClass1.$SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type[message.getType().ordinal()] == 2 && (onMessageReceivedListener = (OnMessageReceivedListener) JavascriptBridge.this.mResponseListeners.remove(message.getId())) != null) {
                    onMessageReceivedListener.onMessageReceived(message);
                }
                if (JavascriptBridge.this.mListener != null) {
                    JavascriptBridge.this.mListener.onMessageReceived(message);
                }
            } catch (Throwable th) {
                Log.error(JavascriptBridge.TAG, "JavaScriptBridge: Unexpected exception handling message from JS", th);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.store.html5.bridge.JavascriptBridge$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type;

        static {
            int[] iArr = new int[Message.Type.values().length];
            $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type = iArr;
            try {
                iArr[Message.Type.Broadcast.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type[Message.Type.Response.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
