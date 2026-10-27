package com.bydux.api.common.webs.okhttp;

import android.content.Context;
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

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
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

    private void addCall(int i, Call call) {
        BydLogHelper.logV("fn=" + i);
        synchronized (this.mCallMap) {
            this.mCallMap.put(Integer.valueOf(i), call);
        }
    }

    private boolean checkNetwork(int i) {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        BydHttpClientListener bydHttpClientListener = this.mHttpClientListener;
        if (bydHttpClientListener == null) {
            return false;
        }
        bydHttpClientListener.onNetworkDisconnect(i);
        return false;
    }

    private void clearCalls() {
        BydLogHelper.logV();
        synchronized (this.mCallMap) {
            this.mCallMap.clear();
        }
    }

    private boolean containCall(int i) {
        synchronized (this.mCallMap) {
            if (this.mCallMap.containsKey(Integer.valueOf(i))) {
                BydLogHelper.logV("contain fn=" + i);
                return true;
            }
            BydLogHelper.logV("new fn=" + i);
            return false;
        }
    }

    private void enqueue(final int i, Request request) {
        if (containCall(i)) {
            return;
        }
        Call callNewCall = this.mHttpClient.newCall(request);
        addCall(i, callNewCall);
        callNewCall.enqueue(new Callback() { // from class: com.bydux.api.common.webs.okhttp.BydHttpClient.2
            public void onFailure(Call call, IOException iOException) {
                BydLogHelper.logV("fn=" + i + ", error=" + iOException.getMessage());
                BydHttpClient.this.removeCall(i);
                if (BydHttpClient.this.mHttpClientListener != null) {
                    BydHttpClient.this.mHttpClientListener.onFailure(i, call, iOException);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                BydLogHelper.logV("fn=" + i + ", code=" + response.code());
                BydHttpClient.this.removeCall(i);
                if (BydHttpClient.this.mHttpClientListener != null) {
                    BydHttpClient.this.mHttpClientListener.onResponse(i, call, response);
                }
            }
        });
    }

    private String mapToQueryString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            try {
                int size = map.size();
                int i = 0;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    i++;
                    String key = entry.getKey();
                    String value = entry.getValue();
                    sb.append(key);
                    sb.append("=");
                    if (value != null) {
                        sb.append(URLEncoder.encode(value, "UTF-8"));
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

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCall(int i) {
        BydLogHelper.logV("fn=" + i);
        synchronized (this.mCallMap) {
            this.mCallMap.remove(Integer.valueOf(i));
        }
    }

    public void cancel(int i) {
        BydLogHelper.logV("fn=" + i);
        synchronized (this.mCallMap) {
            Call call = this.mCallMap.get(Integer.valueOf(i));
            if (call != null) {
                call.cancel();
            }
            removeCall(i);
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

    public boolean get(int i, String str) {
        return get(i, str, null, null);
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

    public boolean postForm(int i, String str, Map<String, String> map) {
        return postForm(i, str, null, map);
    }

    public boolean postJson(int i, String str, String str2) {
        return postJson(i, str, null, str2);
    }

    public void setHttpClientListener(BydHttpClientListener bydHttpClientListener) {
        this.mHttpClientListener = bydHttpClientListener;
    }

    public BydHttpClient(Context context, ArrayList<Interceptor> arrayList) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mHttpClientListener = null;
        this.mDownloader = null;
        this.mUploader = null;
        this.mCallMap = new HashMap<>();
        this.mContext = context;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        builder.connectTimeout(10L, timeUnit);
        builder.writeTimeout(60L, timeUnit);
        builder.readTimeout(60L, timeUnit);
        if (arrayList != null) {
            Iterator<Interceptor> it = arrayList.iterator();
            while (it.hasNext()) {
                builder.networkInterceptors().add(it.next());
            }
        }
        builder.followRedirects(true);
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.bydux.api.common.webs.okhttp.BydHttpClient.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        OkHttpClient okHttpClientBuild = builder.build();
        this.mHttpClient = okHttpClientBuild;
        okHttpClientBuild.dispatcher().setMaxRequestsPerHost(10);
    }

    public boolean get(int i, String str, Map<String, String> map) {
        return get(i, str, null, map);
    }

    public boolean postForm(int i, String str, Map<String, String> map, Map<String, String> map2) {
        if (!checkNetwork(i)) {
            return false;
        }
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (map2 == null || map2.size() <= 0) ? "" : mapToQueryString(map2);
        BydLogHelper.logV("fn=" + i + ", url=" + str + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        enqueue(i, builder.url(str).post(RequestBody.create(MediaType.FORM, strMapToQueryString)).build());
        return true;
    }

    public boolean postJson(int i, String str, Map<String, String> map, String str2) {
        if (!checkNetwork(i)) {
            return false;
        }
        BydLogHelper.logV("fn=" + i + ", url=" + str + ", jsonString=" + str2 + ", pkgName=" + this.mContext.getPackageName());
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        enqueue(i, builder.url(str).post(RequestBody.create(MediaType.JSON, str2)).build());
        return true;
    }

    public boolean get(int i, String str, Map<String, String> map, Map<String, String> map2) {
        if (!checkNetwork(i)) {
            return false;
        }
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null && map2.size() > 0) {
            str = str + "?" + mapToQueryString(map2);
        }
        BydLogHelper.logV("fn=" + i + ", url=" + str + ", pkgName=" + this.mContext.getPackageName());
        enqueue(i, builder.url(str).build());
        return true;
    }
}
