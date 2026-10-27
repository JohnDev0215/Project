package com.amazon.mp3.store.html5.webview;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarStoreTitleView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.DevModeCapabilities;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.store.html5.bridge.MessageType;
import com.amazon.mp3.store.html5.util.StoreContextWrapper;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.util.Log;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class StoreWebView extends WebView implements JavascriptBridge.OnMessageReceivedListener {
    static final String TAG = StoreWebView.class.getSimpleName();
    private static final DevModeCapabilities sDevModeCapabilities = AmazonApplication.getDevModeCapabilities();
    private static volatile StoreWebView sInstance;
    private boolean mAppCacheUpdated;
    private Activity mAttachedActivity;
    private final StoreContextWrapper mContextWrapper;
    private boolean mDestroyed;
    private final JavascriptBridge mJsBridge;
    private final BroadcastReceiver mLowMemoryBroadcastReceiver;
    private StoreMessageHandler mMessageHandler;
    private State mState;

    public enum State {
        NotLoaded,
        Loading,
        Loaded,
        Failed
    }

    private StoreWebView(Context context) {
        super(new StoreContextWrapper());
        this.mJsBridge = new JavascriptBridge("AndroidJsBridge");
        this.mContextWrapper = (StoreContextWrapper) getContext();
        this.mState = State.NotLoaded;
        this.mLowMemoryBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.store.html5.webview.StoreWebView.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Handler handler = StoreWebView.this.getHandler();
                if (handler != null) {
                    handler.post(new Runnable() { // from class: com.amazon.mp3.store.html5.webview.StoreWebView.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (StoreWebView.this.mDestroyed) {
                                return;
                            }
                            StoreWebView.this.freeMemory();
                        }
                    });
                }
            }
        };
        init(context);
    }

    public static boolean instanceCreated() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return sInstance != null;
        }
        throw new IllegalStateException("You must call this method on the main thread");
    }

    public static StoreWebView getInstance(Context c) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("You must call this method on the main thread");
        }
        if (sInstance == null) {
            StoreWebView storeWebView = new StoreWebView(c);
            storeWebView.setBackground(ContextCompat.getDrawable(c, R.drawable.background_gradient));
            sInstance = storeWebView;
        }
        return sInstance;
    }

    public void registerJavascriptListener(JavascriptBridge.OnMessageReceivedListener listener) {
        this.mJsBridge.attachListener(listener);
    }

    public void unregisterJavascriptListener(JavascriptBridge.OnMessageReceivedListener listener) {
        this.mJsBridge.detachListener(listener);
    }

    public JavascriptBridge getJavascriptBridge() {
        return this.mJsBridge;
    }

    @Override // com.amazon.mp3.store.html5.bridge.JavascriptBridge.OnMessageReceivedListener
    public void onMessageReceived(Message message) {
        if (this.mMessageHandler != null) {
            int i = AnonymousClass5.$SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type[message.getType().ordinal()];
            if (i == 1) {
                this.mMessageHandler.handleRequest(message);
            } else if (i == 2) {
                this.mMessageHandler.handleResponse(message);
            } else {
                if (i != 3) {
                    return;
                }
                this.mMessageHandler.handleBroadcast(message);
            }
        }
    }

    public static void destroyWebViewIfNotAttached() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("You must call this method on the main thread");
        }
        if (sInstance == null || sInstance.mAttachedActivity != null) {
            return;
        }
        sInstance.onPause();
        sInstance.mJsBridge.detach(sInstance);
        sInstance.clearView();
        sInstance.freeMemory();
        sInstance.destroy();
        sInstance = null;
    }

    @Override // android.webkit.WebView
    public void destroy() {
        getContext().getApplicationContext().unregisterReceiver(this.mLowMemoryBroadcastReceiver);
        unregisterJavascriptListener(this);
        this.mDestroyed = true;
        super.destroy();
    }

    @Override // android.webkit.WebView
    public void loadUrl(String url) {
        if (!this.mDestroyed) {
            internalLoadUrl(url, null);
        } else {
            Log.warning(TAG, "Attempting to loadUrl on a destroyed webview. Punting");
        }
    }

    @Override // android.webkit.WebView
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (!this.mDestroyed) {
            internalLoadUrl(url, additionalHttpHeaders);
        } else {
            Log.warning(TAG, "Attempting to loadUrl on a destroyed webview. Punting");
        }
    }

    public void loadUrlAndClearHistory(final String url) {
        loadUrlAndClearHistory(url, false);
    }

    public void loadUrlAndClearHistory(final String url, final boolean forceLoad) {
        if (forceLoad) {
            super.loadUrl(url);
        } else {
            loadUrl(url);
        }
        clearHistory();
    }

    public void loadBlankAndClearHistory() {
        loadUrl("about:blank");
        clearHistory();
    }

    @Override // android.webkit.WebView
    public void clearHistory() {
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.store.html5.webview.StoreWebView.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    StoreWebView.super.clearHistory();
                } catch (NullPointerException e) {
                    Log.warning(StoreWebView.TAG, "Null pointer while clearing history", e);
                }
            }
        });
    }

    @Override // android.webkit.WebView, android.view.View
    public void onWindowVisibilityChanged(final int visibility) {
        super.onWindowVisibilityChanged(visibility);
        setOverScrollMode(visibility == 0 ? 0 : 2);
    }

    public void setMessageHandler(StoreMessageHandler handler) {
        this.mMessageHandler = handler;
    }

    public void setState(State state) {
        this.mState = state;
        View viewFindViewById = findViewById(R.id.progress);
        int i = AnonymousClass5.$SwitchMap$com$amazon$mp3$store$html5$webview$StoreWebView$State[state.ordinal()];
        if (i == 1) {
            if (viewFindViewById == null) {
                View.inflate(getContext(), R.layout.common_progress_spinner, this).bringToFront();
            }
        } else if ((i == 2 || i == 3 || i == 4) && viewFindViewById != null) {
            removeView((View) viewFindViewById.getParent());
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.store.html5.webview.StoreWebView$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$store$html5$webview$StoreWebView$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$amazon$mp3$store$html5$webview$StoreWebView$State = iArr;
            try {
                iArr[State.Loading.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$webview$StoreWebView$State[State.Failed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$webview$StoreWebView$State[State.Loaded.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$webview$StoreWebView$State[State.NotLoaded.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Message.Type.values().length];
            $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type = iArr2;
            try {
                iArr2[Message.Type.Request.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type[Message.Type.Response.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$store$html5$bridge$Message$Type[Message.Type.Broadcast.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public State getState() {
        return this.mState;
    }

    public void aggressivelyFreeMemory() {
        onPause();
        freeMemory();
        onResume();
    }

    public void attachActivity(Activity activity) {
        this.mContextWrapper.setContext(activity);
        this.mAttachedActivity = activity;
    }

    public void detachActivity(Activity activity) {
        if (this.mAppCacheUpdated) {
            reload();
            this.mAppCacheUpdated = false;
        }
        aggressivelyFreeMemory();
        this.mContextWrapper.setContext(null);
        if (activity == this.mAttachedActivity) {
            this.mAttachedActivity = null;
        }
    }

    private void init(Context context) {
        this.mContextWrapper.setContext(context);
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.action.LOW_MEMORY");
        Context applicationContext = context.getApplicationContext();
        applicationContext.registerReceiver(this.mLowMemoryBroadcastReceiver, intentFilter);
        applicationContext.getCacheDir().getAbsolutePath();
        WebSettings settings = getSettings();
        settings.setCacheMode(-1);
        settings.setUserAgentString(settings.getUserAgentString() + " ANDEMB");
        settings.setSupportZoom(false);
        settings.setMixedContentMode(2);
        if (getWindowVisibility() != 0) {
            setOverScrollMode(2);
        }
        registerJavascriptListener(this);
        setVerticalScrollBarEnabled(false);
        installWorkarounds();
        this.mJsBridge.attach(this, this);
    }

    private void installWorkarounds() {
        requestFocus(130);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.mp3.store.html5.webview.StoreWebView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if ((action != 0 && action != 1) || v.hasFocus()) {
                    return false;
                }
                v.requestFocus();
                return false;
            }
        });
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.mp3.store.html5.webview.StoreWebView.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    public void resetState() {
        String url = getUrl();
        stopLoading();
        if (url == null) {
            url = StoreUrls.getBaseUrl();
        }
        loadUrlAndClearHistory(StoreUrls.appendChangedUser(Uri.parse(url)).toString(), true);
        clearCache(true);
        reload();
    }

    public void setAppCacheUpdated(boolean updated) {
        this.mAppCacheUpdated = updated;
    }

    private void internalLoadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (this.mDestroyed) {
            Log.warning(TAG, "Attempting to loadUrl on a destroyed webview. Punting");
            return;
        }
        if (url.startsWith("javascript:")) {
            if (this.mState != State.Loaded) {
                Log.info(TAG, "Attempting to execute Javascript in a non-loaded state.");
            }
            super.loadUrl(url, null);
            return;
        }
        if (url.equals("about:blank")) {
            setState(State.NotLoaded);
            super.loadUrl(url, null);
            return;
        }
        Uri uriUriWithoutPort = uriWithoutPort(Uri.parse(url));
        Uri uriUriWithoutPort2 = uriWithoutPort(getCurrentUri());
        if (sDevModeCapabilities.isStoreDebugModeEnabled()) {
            if (url.contains("gamma")) {
                url = Uri.parse(url).buildUpon().appendQueryParameter("cirrusGamma", "true").build().toString();
            }
        } else {
            url = Uri.parse(url).buildUpon().appendQueryParameter("appcache", "1").build().toString();
        }
        if (uriUriWithoutPort.equals(uriUriWithoutPort2) && ActionBarStoreTitleView.isTitleLoaded()) {
            return;
        }
        String fragment = Uri.parse(url).getFragment();
        if (ActionBarStoreTitleView.isTitleLoaded() && getState() == State.Loaded && StoreUrls.isStoreUrl(uriUriWithoutPort) && StoreUrls.isStoreUrl(uriUriWithoutPort2)) {
            try {
                JSONObject jSONObjectPut = new JSONObject().put("route", fragment);
                Log.debug(TAG, "triggering navigation to route: %s", fragment);
                this.mJsBridge.broadcast(MessageType.Navigate.getName(), jSONObjectPut);
                return;
            } catch (JSONException unused) {
                throw new RuntimeException("JSON generation failed");
            }
        }
        String str = TAG;
        Log.verbose(str, "Store. Loading full URL");
        Log.debugBuildOnly(str, "%s", uriUriWithoutPort.toString());
        setState(State.Loading);
        super.loadUrl("about:blank", null);
        clearView();
        super.loadUrl(url, additionalHttpHeaders);
    }

    private Uri getCurrentUri() {
        String url = getUrl();
        if (url == null || "".equals(url) || "about:blank".equals(url)) {
            return Uri.EMPTY;
        }
        return Uri.parse(url);
    }

    private static Uri uriWithoutPort(Uri uri) {
        String authority = uri.getAuthority();
        if (authority != null) {
            authority = authority.split(":")[0];
        }
        return new Uri.Builder().scheme(uri.getScheme()).authority(authority).path(uri.getPath()).fragment(uri.getFragment()).encodedQuery(uri.getQuery()).build();
    }
}
