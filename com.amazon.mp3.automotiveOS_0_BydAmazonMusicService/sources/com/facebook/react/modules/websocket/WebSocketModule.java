package com.facebook.react.modules.websocket;

import androidx.annotation.Nullable;
import androidx.core.view.PointerIconCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(hasConstants = false, name = "WebSocketModule")
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    public static final String NAME = "WebSocketModule";
    public static final String TAG = "WebSocketModule";
    private final Map<Integer, ContentHandler> mContentHandlers;
    private ForwardingCookieHandler mCookieHandler;
    private final Map<Integer, WebSocket> mWebSocketConnections;

    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "WebSocketModule";
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void removeListeners(double d) {
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mWebSocketConnections = new ConcurrentHashMap();
        this.mContentHandlers = new ConcurrentHashMap();
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        Iterator<WebSocket> it = this.mWebSocketConnections.values().iterator();
        while (it.hasNext()) {
            it.next().close(PointerIconCompat.TYPE_CONTEXT_MENU, null);
        }
        this.mWebSocketConnections.clear();
        this.mContentHandlers.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext.hasActiveReactInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    public void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(i), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void connect(String str, @Nullable ReadableArray readableArray, @Nullable ReadableMap readableMap, double d) {
        boolean z;
        final int i = (int) d;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        OkHttpClient okHttpClientBuild = builder.connectTimeout(10L, timeUnit).writeTimeout(10L, timeUnit).readTimeout(0L, TimeUnit.MINUTES).build();
        Request.Builder builderUrl = new Request.Builder().tag(Integer.valueOf(i)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            builderUrl.addHeader("Cookie", cookie);
        }
        if (readableMap != null && readableMap.hasKey("headers") && readableMap.getType("headers").equals(ReadableType.Map)) {
            ReadableMap map = readableMap.getMap("headers");
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = map.keySetIterator();
            z = false;
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                if (ReadableType.String.equals(map.getType(strNextKey))) {
                    if (strNextKey.equalsIgnoreCase("origin")) {
                        z = true;
                    }
                    builderUrl.addHeader(strNextKey, map.getString(strNextKey));
                } else {
                    FLog.w("ReactNative", "Ignoring: requested " + strNextKey + ", value not a string");
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            builderUrl.addHeader("origin", getDefaultOrigin(str));
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder("");
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                String strTrim = readableArray.getString(i2).trim();
                if (!strTrim.isEmpty() && !strTrim.contains(",")) {
                    sb.append(strTrim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), "");
                builderUrl.addHeader("Sec-WebSocket-Protocol", sb.toString());
            }
        }
        okHttpClientBuild.newWebSocket(builderUrl.build(), new WebSocketListener() { // from class: com.facebook.react.modules.websocket.WebSocketModule.1
            @Override // okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(i), webSocket);
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putInt("id", i);
                writableMapCreateMap.putString("protocol", response.header("Sec-WebSocket-Protocol", ""));
                WebSocketModule.this.sendEvent("websocketOpen", writableMapCreateMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, int i3, String str2) {
                webSocket.close(i3, str2);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, int i3, String str2) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putInt("id", i);
                writableMapCreateMap.putInt("code", i3);
                writableMapCreateMap.putString("reason", str2);
                WebSocketModule.this.sendEvent("websocketClosed", writableMapCreateMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                WebSocketModule.this.notifyWebSocketFailed(i, th.getMessage());
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str2) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putInt("id", i);
                writableMapCreateMap.putString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "text");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(str2, writableMapCreateMap);
                } else {
                    writableMapCreateMap.putString("data", str2);
                }
                WebSocketModule.this.sendEvent("websocketMessage", writableMapCreateMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, ByteString byteString) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putInt("id", i);
                writableMapCreateMap.putString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "binary");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(byteString, writableMapCreateMap);
                } else {
                    writableMapCreateMap.putString("data", byteString.base64());
                }
                WebSocketModule.this.sendEvent("websocketMessage", writableMapCreateMap);
            }
        });
        okHttpClientBuild.dispatcher().executorService().shutdown();
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void close(double d, String str, double d2) {
        int i = (int) d2;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            return;
        }
        try {
            webSocket.close((int) d, str);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
        } catch (Exception e) {
            FLog.e("ReactNative", "Could not close WebSocket connection for id " + i, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void send(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void sendBinary(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(ByteString byteString, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void ping(double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWebSocketFailed(int i, String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("id", i);
        writableMapCreateMap.putString("message", str);
        sendEvent("websocketFailed", writableMapCreateMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getDefaultOrigin(java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.getDefaultOrigin(java.lang.String):java.lang.String");
    }

    private String getCookie(String str) {
        try {
            List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
