package com.bydux.api.common.webs.okhttp;

import android.content.Context;
import com.bumptech.glide.load.Key;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.constant.MediaType;
import com.bydux.api.common.webs.okhttp.download.Downloader;
import com.bydux.api.common.webs.okhttp.upload.Uploader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes5.dex */
public class BydHttpClient {
    private HashMap<Integer, Call> mCallMap;
    private Context mContext;
    private Downloader mDownloader;
    private OkHttpClient mHttpClient;
    private BydHttpClientListener mHttpClientListener;
    private Uploader mUploader;

    public BydHttpClient(Context context) {
        this(context, null);
    }

    public BydHttpClient(Context context, ArrayList<Interceptor> interceptorList) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mHttpClientListener = null;
        this.mDownloader = null;
        this.mUploader = null;
        this.mCallMap = new HashMap<>();
        this.mContext = context;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        builder.writeTimeout(60L, TimeUnit.SECONDS);
        builder.readTimeout(60L, TimeUnit.SECONDS);
        if (interceptorList != null) {
            Iterator<Interceptor> it = interceptorList.iterator();
            while (it.hasNext()) {
                builder.networkInterceptors().add(it.next());
            }
        }
        builder.followRedirects(true);
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.bydux.api.common.webs.okhttp.BydHttpClient.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        OkHttpClient okHttpClientBuild = builder.build();
        this.mHttpClient = okHttpClientBuild;
        okHttpClientBuild.dispatcher().setMaxRequestsPerHost(10);
    }

    public void setHttpClientListener(BydHttpClientListener listener) {
        this.mHttpClientListener = listener;
    }

    public Downloader getDownloader() {
        if (this.mDownloader == null) {
            this.mDownloader = new Downloader(this.mContext, this.mHttpClient);
        }
        return this.mDownloader;
    }

    public Uploader getUploader() {
        if (this.mUploader == null) {
            this.mUploader = new Uploader(this.mContext, this.mHttpClient);
        }
        return this.mUploader;
    }

    public boolean get(final int fn, String url) {
        return get(fn, url, null, null);
    }

    public boolean get(final int fn, String url, Map<String, String> paramMap) {
        return get(fn, url, null, paramMap);
    }

    public boolean get(final int fn, String url, Map<String, String> headMap, Map<String, String> paramMap) {
        if (!checkNetwork(fn)) {
            return false;
        }
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (paramMap != null && paramMap.size() > 0) {
            url = url + "?" + mapToQueryString(paramMap);
        }
        BydLogHelper.logV("fn=" + fn + ", url=" + url + ", pkgName=" + this.mContext.getPackageName());
        enqueue(fn, builder.url(url).build());
        return true;
    }

    public boolean postJson(final int fn, String url, String jsonString) {
        return postJson(fn, url, null, jsonString);
    }

    public boolean postJson(final int fn, String url, Map<String, String> headMap, String jsonString) {
        if (!checkNetwork(fn)) {
            return false;
        }
        BydLogHelper.logV("fn=" + fn + ", url=" + url + ", jsonString=" + jsonString + ", pkgName=" + this.mContext.getPackageName());
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        enqueue(fn, builder.url(url).post(RequestBody.create(MediaType.JSON, jsonString)).build());
        return true;
    }

    public boolean postForm(final int fn, String url, Map<String, String> paramMap) {
        return postForm(fn, url, null, paramMap);
    }

    public boolean postForm(final int fn, String url, Map<String, String> headMap, Map<String, String> paramMap) {
        if (!checkNetwork(fn)) {
            return false;
        }
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (paramMap == null || paramMap.size() <= 0) ? "" : mapToQueryString(paramMap);
        BydLogHelper.logV("fn=" + fn + ", url=" + url + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        enqueue(fn, builder.url(url).post(RequestBody.create(MediaType.FORM, strMapToQueryString)).build());
        return true;
    }

    public void cancel(int fn) {
        BydLogHelper.logV("fn=" + fn);
        synchronized (this.mCallMap) {
            Call call = this.mCallMap.get(Integer.valueOf(fn));
            if (call != null) {
                call.cancel();
            }
            removeCall(fn);
        }
    }

    public void cancelAll() {
        BydLogHelper.logV();
        synchronized (this.mCallMap) {
            Iterator<Map.Entry<Integer, Call>> it = this.mCallMap.entrySet().iterator();
            while (it.hasNext()) {
                Call value = it.next().getValue();
                if (value != null) {
                    value.cancel();
                }
            }
            clearCalls();
        }
    }

    private void enqueue(final int fn, Request request) {
        if (containCall(fn)) {
            return;
        }
        Call callNewCall = this.mHttpClient.newCall(request);
        addCall(fn, callNewCall);
        callNewCall.enqueue(new Callback() { // from class: com.bydux.api.common.webs.okhttp.BydHttpClient.2
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                BydLogHelper.logV("fn=" + fn + ", code=" + response.code());
                BydHttpClient.this.removeCall(fn);
                if (BydHttpClient.this.mHttpClientListener != null) {
                    BydHttpClient.this.mHttpClientListener.onResponse(fn, call, response);
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                BydLogHelper.logV("fn=" + fn + ", error=" + e.getMessage());
                BydHttpClient.this.removeCall(fn);
                if (BydHttpClient.this.mHttpClientListener != null) {
                    BydHttpClient.this.mHttpClientListener.onFailure(fn, call, e);
                }
            }
        });
    }

    private boolean containCall(int fn) {
        synchronized (this.mCallMap) {
            if (this.mCallMap.containsKey(Integer.valueOf(fn))) {
                BydLogHelper.logV("contain fn=" + fn);
                return true;
            }
            BydLogHelper.logV("new fn=" + fn);
            return false;
        }
    }

    private void addCall(int fn, Call call) {
        BydLogHelper.logV("fn=" + fn);
        synchronized (this.mCallMap) {
            this.mCallMap.put(Integer.valueOf(fn), call);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCall(int fn) {
        BydLogHelper.logV("fn=" + fn);
        synchronized (this.mCallMap) {
            this.mCallMap.remove(Integer.valueOf(fn));
        }
    }

    private void clearCalls() {
        BydLogHelper.logV();
        synchronized (this.mCallMap) {
            this.mCallMap.clear();
        }
    }

    private String mapToQueryString(Map<String, String> paramMap) {
        StringBuilder sb = new StringBuilder();
        if (paramMap != null) {
            try {
                int size = paramMap.size();
                int i = 0;
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    i++;
                    String key = entry.getKey();
                    String value = entry.getValue();
                    sb.append(key);
                    sb.append("=");
                    if (value != null) {
                        sb.append(URLEncoder.encode(value, Key.STRING_CHARSET_NAME));
                    }
                    if (i != size) {
                        sb.append("&");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private boolean checkNetwork(int fn) {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        BydHttpClientListener bydHttpClientListener = this.mHttpClientListener;
        if (bydHttpClientListener == null) {
            return false;
        }
        bydHttpClientListener.onNetworkDisconnect(fn);
        return false;
    }
}
