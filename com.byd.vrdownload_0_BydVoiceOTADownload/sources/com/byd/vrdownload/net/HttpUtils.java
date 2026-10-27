package com.byd.vrdownload.net;

import com.byd.vrdownload.bean.HttpResult;
import com.byd.vrdownload.model.Result;
import com.byd.vrdownload.utils.GsonUtils;
import com.byd.vrdownload.utils.LogUtils;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public class HttpUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "HttpUtils";
    private static volatile OkHttpClient okHttpClient;
    private static volatile Semaphore semaphore;
    private Map<String, String> headerMap;
    private Map<String, String> paramMap;
    private Request.Builder request;
    private String url;

    public interface ICallBack {
        void onFailure(Call call, String str);

        void onSuccessful(Call call, String str);
    }

    private HttpUtils() {
        if (okHttpClient == null) {
            synchronized (HttpUtils.class) {
                if (okHttpClient == null) {
                    buildTrustManagers();
                    okHttpClient = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
                    addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Semaphore getSemaphoreInstance() {
        synchronized (HttpUtils.class) {
            if (semaphore == null) {
                semaphore = new Semaphore(0);
            }
        }
        return semaphore;
    }

    public static HttpUtils builder() {
        return new HttpUtils();
    }

    public HttpUtils url(String str) {
        this.url = str;
        return this;
    }

    public HttpUtils addParam(String str, String str2) {
        if (this.paramMap == null) {
            this.paramMap = new LinkedHashMap(16);
        }
        this.paramMap.put(str, str2);
        return this;
    }

    public HttpUtils addParam(Map<String, String> map) {
        if (this.paramMap == null) {
            this.paramMap = new LinkedHashMap(16);
        }
        this.paramMap.putAll(map);
        return this;
    }

    public HttpUtils addHeader(String str, String str2) {
        if (this.headerMap == null) {
            this.headerMap = new LinkedHashMap(16);
        }
        this.headerMap.put(str, str2);
        return this;
    }

    public HttpUtils get() {
        this.request = new Request.Builder().get();
        StringBuilder sb = new StringBuilder(this.url);
        if (this.paramMap != null) {
            sb.append("?");
            try {
                for (Map.Entry<String, String> entry : this.paramMap.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=").append(URLEncoder.encode(entry.getValue(), "utf-8")).append("&");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        this.request.url(sb.toString());
        return this;
    }

    public HttpUtils post(boolean z) {
        RequestBody requestBodyBuild;
        if (z) {
            Map<String, String> map = this.paramMap;
            requestBodyBuild = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), map != null ? GsonUtils.toJson((Map) map) : "");
        } else {
            final FormBody.Builder builder = new FormBody.Builder();
            Map<String, String> map2 = this.paramMap;
            if (map2 != null) {
                Objects.requireNonNull(builder);
                map2.forEach(new BiConsumer() { // from class: com.byd.vrdownload.net.HttpUtils$$ExternalSyntheticLambda0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        builder.add((String) obj, (String) obj2);
                    }
                });
            }
            requestBodyBuild = builder.build();
        }
        this.request = new Request.Builder().post(requestBodyBuild).url(this.url);
        return this;
    }

    public String sync() {
        setHeader(this.request);
        try {
            return okHttpClient.newCall(this.request.build()).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求失败：" + e.getMessage();
        }
    }

    public <T> Result<T> sync(Type type) {
        setHeader(this.request);
        try {
            String strString = okHttpClient.newCall(this.request.build()).execute().body().string();
            LogUtils.i(TAG, "sync: " + strString);
            if (strString.isEmpty()) {
                throw new InvalidObjectException("responseStr is empty");
            }
            HttpResult httpResult = (HttpResult) GsonUtils.fromJson(strString, type);
            LogUtils.i(TAG, "sync: " + httpResult);
            return httpResult.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e);
        }
    }

    public Map<String, String> getParamMap() {
        return this.paramMap;
    }

    public String async() {
        final StringBuilder sb = new StringBuilder("");
        setHeader(this.request);
        okHttpClient.newCall(this.request.build()).enqueue(new Callback() { // from class: com.byd.vrdownload.net.HttpUtils.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                sb.append("请求出错：").append(iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                sb.append(response.body().string());
                HttpUtils.getSemaphoreInstance().release();
            }
        });
        try {
            getSemaphoreInstance().acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void async(final ICallBack iCallBack) {
        setHeader(this.request);
        okHttpClient.newCall(this.request.build()).enqueue(new Callback() { // from class: com.byd.vrdownload.net.HttpUtils.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                iCallBack.onFailure(call, iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                iCallBack.onSuccessful(call, response.body().string());
            }
        });
    }

    private void setHeader(Request.Builder builder) {
        Map<String, String> map = this.headerMap;
        if (map != null) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustManagerArr) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{new X509TrustManager() { // from class: com.byd.vrdownload.net.HttpUtils.3
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
    }
}
