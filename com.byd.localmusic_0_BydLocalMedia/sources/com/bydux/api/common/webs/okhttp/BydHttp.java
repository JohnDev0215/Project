package com.bydux.api.common.webs.okhttp;

import android.content.Context;
import com.bumptech.glide.load.Key;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.bean.ResponseInfo;
import com.bydux.api.common.webs.okhttp.constant.MediaType;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes5.dex */
public class BydHttp {
    private Context mContext;
    private OkHttpClient mHttpClient;

    public BydHttp(Context context) {
        this.mContext = null;
        this.mHttpClient = null;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        builder.writeTimeout(60L, TimeUnit.SECONDS);
        builder.readTimeout(60L, TimeUnit.SECONDS);
        builder.followRedirects(true);
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.bydux.api.common.webs.okhttp.BydHttp.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        OkHttpClient okHttpClientBuild = builder.build();
        this.mHttpClient = okHttpClientBuild;
        okHttpClientBuild.dispatcher().setMaxRequestsPerHost(10);
    }

    public <T> Call get(String url, BydHttpCallBack<T> callBack) {
        return get(url, null, null, callBack);
    }

    public <T> Call get(String url, Map<String, String> paramMap, BydHttpCallBack<T> callBack) {
        return get(url, null, paramMap, callBack);
    }

    public <T> Call get(String url, Map<String, String> headMap, Map<String, String> paramMap, BydHttpCallBack<T> callBack) {
        if (!checkNetwork(callBack)) {
            return null;
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
        BydLogHelper.logV("url=" + url + ", pkgName=" + this.mContext.getPackageName());
        return enqueue(builder.url(url).build(), callBack);
    }

    public Response getSync(String url) {
        return getSync(url, null, null);
    }

    public Response getSync(String url, Map<String, String> paramMap) {
        return getSync(url, null, paramMap);
    }

    public Response getSync(String url, Map<String, String> headMap, Map<String, String> paramMap) {
        if (!checkNetwork()) {
            return null;
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
        BydLogHelper.logV("url=" + url + ", pkgName=" + this.mContext.getPackageName());
        return execute(builder.url(url).build());
    }

    public <T> Call postJson(String url, String jsonString, BydHttpCallBack<T> callBack) {
        return postJson(url, null, jsonString, callBack);
    }

    public <T> Call postJson(String url, Map<String, String> headMap, String jsonString, BydHttpCallBack<T> callBack) {
        if (!checkNetwork(callBack)) {
            return null;
        }
        BydLogHelper.logV("url=" + url + ", jsonString=" + jsonString + ", pkgName=" + this.mContext.getPackageName());
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return enqueue(builder.url(url).post(RequestBody.create(MediaType.JSON, jsonString)).build(), callBack);
    }

    public Response postJsonSync(String url, String jsonString) {
        return postJsonSync(url, null, jsonString);
    }

    public Response postJsonSync(String url, Map<String, String> headMap, String jsonString) {
        if (!checkNetwork()) {
            return null;
        }
        BydLogHelper.logV("url=" + url + ", jsonString=" + jsonString + ", pkgName=" + this.mContext.getPackageName());
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return execute(builder.url(url).post(RequestBody.create(MediaType.JSON, jsonString)).build());
    }

    public <T> Call postForm(String url, Map<String, String> paramMap, BydHttpCallBack<T> callBack) {
        return postForm(url, null, paramMap, callBack);
    }

    public <T> Call postForm(String url, Map<String, String> headMap, Map<String, String> paramMap, BydHttpCallBack<T> callBack) {
        if (!checkNetwork(callBack)) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (paramMap == null || paramMap.size() <= 0) ? "" : mapToQueryString(paramMap);
        BydLogHelper.logV("url=" + url + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        return enqueue(builder.url(url).post(RequestBody.create(MediaType.FORM, strMapToQueryString)).build(), callBack);
    }

    public Response postFormSync(String url, Map<String, String> paramMap) {
        return postFormSync(url, null, paramMap);
    }

    public Response postFormSync(String url, Map<String, String> headMap, Map<String, String> paramMap) {
        if (!checkNetwork()) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        if (headMap != null && headMap.size() > 0) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (paramMap == null || paramMap.size() <= 0) ? "" : mapToQueryString(paramMap);
        BydLogHelper.logV("url=" + url + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        return execute(builder.url(url).post(RequestBody.create(MediaType.FORM, strMapToQueryString)).build());
    }

    private <T> Call enqueue(Request request, final BydHttpCallBack<T> callBack) {
        Call callNewCall = this.mHttpClient.newCall(request);
        callNewCall.enqueue(new Callback() { // from class: com.bydux.api.common.webs.okhttp.BydHttp.2
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws Exception {
                String strString = response.body().string();
                BydLogHelper.logV("code=" + response.code() + ", data=" + strString);
                try {
                    Object objFromJson = new Gson().fromJson(strString, callBack.getType());
                    if (callBack != null) {
                        callBack.onResponse(call, new ResponseInfo(response, objFromJson, strString));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                BydLogHelper.logV("error=" + e.getMessage());
                BydHttpCallBack bydHttpCallBack = callBack;
                if (bydHttpCallBack != null) {
                    bydHttpCallBack.onFailure(call, e);
                }
            }
        });
        return callNewCall;
    }

    private Response execute(Request request) {
        try {
            return this.mHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    private <T> boolean checkNetwork(BydHttpCallBack<T> callBack) {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        if (callBack == null) {
            return false;
        }
        callBack.onNetworkDisconnect();
        return false;
    }

    private boolean checkNetwork() {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        return false;
    }
}
