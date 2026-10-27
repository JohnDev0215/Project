package com.bydux.api.common.webs.okhttp;

import android.content.Context;
import b.b.a.d;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.bean.ResponseInfo;
import com.bydux.api.common.webs.okhttp.constant.MediaType;
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

/* JADX INFO: loaded from: classes2.dex */
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
        TimeUnit timeUnit = TimeUnit.SECONDS;
        builder.connectTimeout(10L, timeUnit);
        builder.writeTimeout(60L, timeUnit);
        builder.readTimeout(60L, timeUnit);
        builder.followRedirects(true);
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.bydux.api.common.webs.okhttp.BydHttp.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        OkHttpClient okHttpClientBuild = builder.build();
        this.mHttpClient = okHttpClientBuild;
        okHttpClientBuild.dispatcher().setMaxRequestsPerHost(10);
    }

    private <T> boolean checkNetwork(BydHttpCallBack<T> bydHttpCallBack) {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        if (bydHttpCallBack == null) {
            return false;
        }
        bydHttpCallBack.onNetworkDisconnect();
        return false;
    }

    private <T> Call enqueue(Request request, final BydHttpCallBack<T> bydHttpCallBack) {
        Call callNewCall = this.mHttpClient.newCall(request);
        callNewCall.enqueue(new Callback() { // from class: com.bydux.api.common.webs.okhttp.BydHttp.2
            public void onFailure(Call call, IOException iOException) {
                BydLogHelper.logV("error=" + iOException.getMessage());
                BydHttpCallBack bydHttpCallBack2 = bydHttpCallBack;
                if (bydHttpCallBack2 != null) {
                    bydHttpCallBack2.onFailure(call, iOException);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                String strString = response.body().string();
                BydLogHelper.logV("code=" + response.code() + ", data=" + strString);
                try {
                    Object objJ = new d().j(strString, bydHttpCallBack.getType());
                    BydHttpCallBack bydHttpCallBack2 = bydHttpCallBack;
                    if (bydHttpCallBack2 != null) {
                        bydHttpCallBack2.onResponse(call, new ResponseInfo(response, objJ, strString));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public <T> Call get(String str, BydHttpCallBack<T> bydHttpCallBack) {
        return get(str, null, null, bydHttpCallBack);
    }

    public Response getSync(String str) {
        return getSync(str, null, null);
    }

    public <T> Call postForm(String str, Map<String, String> map, BydHttpCallBack<T> bydHttpCallBack) {
        return postForm(str, null, map, bydHttpCallBack);
    }

    public Response postFormSync(String str, Map<String, String> map) {
        return postFormSync(str, null, map);
    }

    public <T> Call postJson(String str, String str2, BydHttpCallBack<T> bydHttpCallBack) {
        return postJson(str, null, str2, bydHttpCallBack);
    }

    public Response postJsonSync(String str, String str2) {
        return postJsonSync(str, null, str2);
    }

    public <T> Call get(String str, Map<String, String> map, BydHttpCallBack<T> bydHttpCallBack) {
        return get(str, null, map, bydHttpCallBack);
    }

    public Response getSync(String str, Map<String, String> map) {
        return getSync(str, null, map);
    }

    public <T> Call postForm(String str, Map<String, String> map, Map<String, String> map2, BydHttpCallBack<T> bydHttpCallBack) {
        if (!checkNetwork(bydHttpCallBack)) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (map2 == null || map2.size() <= 0) ? "" : mapToQueryString(map2);
        BydLogHelper.logV("url=" + str + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        return enqueue(builder.url(str).post(RequestBody.create(MediaType.FORM, strMapToQueryString)).build(), bydHttpCallBack);
    }

    public Response postFormSync(String str, Map<String, String> map, Map<String, String> map2) {
        if (!checkNetwork()) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (map2 == null || map2.size() <= 0) ? "" : mapToQueryString(map2);
        BydLogHelper.logV("url=" + str + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        return execute(builder.url(str).post(RequestBody.create(MediaType.FORM, strMapToQueryString)).build());
    }

    public <T> Call postJson(String str, Map<String, String> map, String str2, BydHttpCallBack<T> bydHttpCallBack) {
        if (!checkNetwork(bydHttpCallBack)) {
            return null;
        }
        BydLogHelper.logV("url=" + str + ", jsonString=" + str2 + ", pkgName=" + this.mContext.getPackageName());
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return enqueue(builder.url(str).post(RequestBody.create(MediaType.JSON, str2)).build(), bydHttpCallBack);
    }

    public Response postJsonSync(String str, Map<String, String> map, String str2) {
        if (!checkNetwork()) {
            return null;
        }
        BydLogHelper.logV("url=" + str + ", jsonString=" + str2 + ", pkgName=" + this.mContext.getPackageName());
        Request.Builder builder = new Request.Builder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder = builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return execute(builder.url(str).post(RequestBody.create(MediaType.JSON, str2)).build());
    }

    public <T> Call get(String str, Map<String, String> map, Map<String, String> map2, BydHttpCallBack<T> bydHttpCallBack) {
        if (!checkNetwork(bydHttpCallBack)) {
            return null;
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
        BydLogHelper.logV("url=" + str + ", pkgName=" + this.mContext.getPackageName());
        return enqueue(builder.url(str).build(), bydHttpCallBack);
    }

    public Response getSync(String str, Map<String, String> map, Map<String, String> map2) {
        if (!checkNetwork()) {
            return null;
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
        BydLogHelper.logV("url=" + str + ", pkgName=" + this.mContext.getPackageName());
        return execute(builder.url(str).build());
    }

    private boolean checkNetwork() {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        return false;
    }
}
