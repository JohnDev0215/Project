package com.facebook.react.modules.network;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.StandardCharsets;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import okhttp3.Headers;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = NetworkingModule.NAME)
public final class NetworkingModule extends NativeNetworkingAndroidSpec {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    public static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";

    @Nullable
    private static CustomClientBuilder customClientBuilder;
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;

    @Nullable
    private final String mDefaultUserAgent;
    private final List<RequestBodyHandler> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    private final List<ResponseHandler> mResponseHandlers;
    private boolean mShuttingDown;
    private final List<UriHandler> mUriHandlers;

    public interface CustomClientBuilder {
        void apply(OkHttpClient.Builder builder);
    }

    public interface RequestBodyHandler {
        boolean supports(ReadableMap readableMap);

        RequestBody toRequestBody(ReadableMap readableMap, String str);
    }

    public interface ResponseHandler {
        boolean supports(String str);

        WritableMap toResponseData(ResponseBody responseBody) throws IOException;
    }

    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldDispatch(long j, long j2) {
        return j2 + 100000000 < j;
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void removeListeners(double d) {
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, @Nullable String str, OkHttpClient okHttpClient, @Nullable List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder builderNewBuilder = okHttpClient.newBuilder();
            Iterator<NetworkInterceptorCreator> it = list.iterator();
            while (it.hasNext()) {
                builderNewBuilder.addNetworkInterceptor(it.next().create());
            }
            okHttpClient = builderNewBuilder.build();
        }
        this.mClient = okHttpClient;
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
        this.mCookieJarContainer = (CookieJarContainer) okHttpClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = str;
        this.mRequestIds = new HashSet();
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, @Nullable String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, null, OkHttpClientProvider.createClient(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, OkHttpClientProvider.createClient(reactApplicationContext), null);
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.destroy();
        this.mCookieJarContainer.removeCookieJar();
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    public void addUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.add(uriHandler);
    }

    public void addRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.add(requestBodyHandler);
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.add(responseHandler);
    }

    public void removeUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.remove(uriHandler);
    }

    public void removeRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.remove(requestBodyHandler);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.remove(responseHandler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void sendRequest(String str, String str2, double d, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, double d2, boolean z2) {
        int i = (int) d;
        try {
            sendRequestInternal(str, str2, i, readableArray, readableMap, str3, z, (int) d2, z2);
        } catch (Throwable th) {
            FLog.e(TAG, "Failed to send url request: " + str2, th);
            DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequest error");
            if (eventEmitter != null) {
                ResponseUtil.onRequestError(eventEmitter, i, th.getMessage(), th);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x0191  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendRequestInternal(java.lang.String r16, java.lang.String r17, final int r18, com.facebook.react.bridge.ReadableArray r19, com.facebook.react.bridge.ReadableMap r20, final java.lang.String r21, final boolean r22, int r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.network.NetworkingModule.sendRequestInternal(java.lang.String, java.lang.String, int, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableMap, java.lang.String, boolean, int, boolean):void");
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, final int i) {
        if (requestBody == null) {
            return null;
        }
        return RequestBodyUtil.createProgressRequest(requestBody, new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.3
            long last = System.nanoTime();

            @Override // com.facebook.react.modules.network.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                long jNanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(jNanoTime, this.last)) {
                    ResponseUtil.onDataSend(rCTDeviceEventEmitter, i, j, j2);
                    this.last = jNanoTime;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readWithProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, ResponseBody responseBody) throws IOException {
        long j;
        Charset charset;
        long contentLength = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j = progressResponseBody.totalBytesRead();
            try {
                contentLength = progressResponseBody.getContentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        if (responseBody.get$contentType() == null) {
            charset = StandardCharsets.UTF_8;
        } else {
            charset = responseBody.get$contentType().charset(StandardCharsets.UTF_8);
        }
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(charset);
        InputStream inputStreamByteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i2 = inputStreamByteStream.read(bArr);
                if (i2 == -1) {
                    return;
                } else {
                    ResponseUtil.onIncrementalDataReceived(rCTDeviceEventEmitter, i, progressiveStringDecoder.decodeNext(bArr, i2), j, contentLength);
                }
            }
        } finally {
            inputStreamByteStream.close();
        }
    }

    private synchronized void addRequest(int i) {
        this.mRequestIds.add(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeRequest(int i) {
        this.mRequestIds.remove(Integer.valueOf(i));
    }

    private synchronized void cancelAllRequests() {
        Iterator<Integer> it = this.mRequestIds.iterator();
        while (it.hasNext()) {
            cancelRequest(it.next().intValue());
        }
        this.mRequestIds.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WritableMap translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < headers.size(); i++) {
            String strName = headers.name(i);
            if (bundle.containsKey(strName)) {
                bundle.putString(strName, bundle.getString(strName) + ", " + headers.value(i));
            } else {
                bundle.putString(strName, headers.value(i));
            }
        }
        return Arguments.fromBundle(bundle);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void abortRequest(double d) {
        int i = (int) d;
        cancelRequest(i);
        removeRequest(i);
    }

    private void cancelRequest(final int i) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.network.NetworkingModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                OkHttpCallUtil.cancelTag(NetworkingModule.this.mClient, Integer.valueOf(i));
            }
        }.execute(new Void[0]);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    @ReactMethod
    public void clearCookies(Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }

    @Nullable
    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int i) {
        MediaType mediaType;
        DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("constructMultipartBody");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            Headers headersExtractHeaders = extractHeaders(map.getArray("headers"), null);
            if (headersExtractHeaders == null) {
                ResponseUtil.onRequestError(eventEmitter, i, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            String str2 = headersExtractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                headersExtractHeaders = headersExtractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(headersExtractHeaders, RequestBody.create(mediaType, map.getString(REQUEST_BODY_KEY_STRING)));
            } else if (map.hasKey(REQUEST_BODY_KEY_URI)) {
                if (mediaType == null) {
                    ResponseUtil.onRequestError(eventEmitter, i, "Binary FormData part needs a content-type header.", null);
                    return null;
                }
                String string = map.getString(REQUEST_BODY_KEY_URI);
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, i, "Could not retrieve file for uri " + string, null);
                    return null;
                }
                builder.addPart(headersExtractHeaders, RequestBodyUtil.create(mediaType, fileInputStream));
            } else {
                ResponseUtil.onRequestError(eventEmitter, i, "Unrecognized FormData part.", null);
            }
        }
        return builder;
    }

    @Nullable
    private Headers extractHeaders(@Nullable ReadableArray readableArray, @Nullable ReadableMap readableMap) {
        String str;
        if (readableArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = readableArray.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ReadableArray array = readableArray.getArray(i);
            if (array != null && array.size() == 2) {
                String strStripHeaderName = HeaderUtil.stripHeaderName(array.getString(0));
                String strStripHeaderValue = HeaderUtil.stripHeaderValue(array.getString(1));
                if (strStripHeaderName != null && strStripHeaderValue != null) {
                    builder.add(strStripHeaderName, strStripHeaderValue);
                }
            }
            return null;
        }
        if (builder.get(USER_AGENT_HEADER_NAME) == null && (str = this.mDefaultUserAgent) != null) {
            builder.add(USER_AGENT_HEADER_NAME, str);
        }
        if (readableMap != null && readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
            z = true;
        }
        if (!z) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter(String str) {
        if (getReactApplicationContextIfActiveOrWarn() != null) {
            return (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        return null;
    }
}
