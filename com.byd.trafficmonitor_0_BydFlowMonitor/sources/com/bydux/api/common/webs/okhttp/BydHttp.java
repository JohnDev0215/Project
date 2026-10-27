package com.bydux.api.common.webs.okhttp;

import android.content.Context;
import c.b.b.d;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.bean.ResponseInfo;
import com.bydux.api.common.webs.okhttp.constant.MediaType;
import f.e;
import f.f;
import f.u;
import f.w;
import f.x;
import f.y;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
public class BydHttp {
    private Context mContext;
    private u mHttpClient;

    public BydHttp(Context context) {
        this.mContext = null;
        this.mHttpClient = null;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        u.b bVar = new u.b();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        bVar.c(10L, timeUnit);
        bVar.h(60L, timeUnit);
        bVar.g(60L, timeUnit);
        bVar.d(true);
        bVar.e(new HostnameVerifier() { // from class: com.bydux.api.common.webs.okhttp.BydHttp.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        u uVarA = bVar.a();
        this.mHttpClient = uVarA;
        uVarA.g().j(10);
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

    private <T> e enqueue(w wVar, final BydHttpCallBack<T> bydHttpCallBack) {
        e eVarO = this.mHttpClient.o(wVar);
        eVarO.w(new f() { // from class: com.bydux.api.common.webs.okhttp.BydHttp.2
            @Override // f.f
            public void onFailure(e eVar, IOException iOException) {
                BydLogHelper.logV("error=" + iOException.getMessage());
                BydHttpCallBack bydHttpCallBack2 = bydHttpCallBack;
                if (bydHttpCallBack2 != null) {
                    bydHttpCallBack2.onFailure(eVar, iOException);
                }
            }

            @Override // f.f
            public void onResponse(e eVar, y yVar) throws IOException {
                String strT = yVar.w().T();
                BydLogHelper.logV("code=" + yVar.O() + ", data=" + strT);
                try {
                    Object objJ = new d().j(strT, bydHttpCallBack.getType());
                    BydHttpCallBack bydHttpCallBack2 = bydHttpCallBack;
                    if (bydHttpCallBack2 != null) {
                        bydHttpCallBack2.onResponse(eVar, new ResponseInfo(yVar, objJ, strT));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        return eVarO;
    }

    private y execute(w wVar) {
        try {
            return this.mHttpClient.o(wVar).z();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String mapToQueryString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            try {
                int size = map.size();
                int i2 = 0;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    i2++;
                    String key = entry.getKey();
                    String value = entry.getValue();
                    sb.append(key);
                    sb.append("=");
                    if (value != null) {
                        sb.append(URLEncoder.encode(value, "UTF-8"));
                    }
                    if (i2 != size) {
                        sb.append("&");
                    }
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }

    public <T> e get(String str, BydHttpCallBack<T> bydHttpCallBack) {
        return get(str, null, null, bydHttpCallBack);
    }

    public y getSync(String str) {
        return getSync(str, null, null);
    }

    public <T> e postForm(String str, Map<String, String> map, BydHttpCallBack<T> bydHttpCallBack) {
        return postForm(str, null, map, bydHttpCallBack);
    }

    public y postFormSync(String str, Map<String, String> map) {
        return postFormSync(str, null, map);
    }

    public <T> e postJson(String str, String str2, BydHttpCallBack<T> bydHttpCallBack) {
        return postJson(str, null, str2, bydHttpCallBack);
    }

    public y postJsonSync(String str, String str2) {
        return postJsonSync(str, null, str2);
    }

    public <T> e get(String str, Map<String, String> map, BydHttpCallBack<T> bydHttpCallBack) {
        return get(str, null, map, bydHttpCallBack);
    }

    public y getSync(String str, Map<String, String> map) {
        return getSync(str, null, map);
    }

    public <T> e postForm(String str, Map<String, String> map, Map<String, String> map2, BydHttpCallBack<T> bydHttpCallBack) {
        if (!checkNetwork(bydHttpCallBack)) {
            return null;
        }
        w.a aVar = new w.a();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                aVar = aVar.a(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (map2 == null || map2.size() <= 0) ? "" : mapToQueryString(map2);
        BydLogHelper.logV("url=" + str + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        return enqueue(aVar.i(str).g(x.create(MediaType.FORM, strMapToQueryString)).b(), bydHttpCallBack);
    }

    public y postFormSync(String str, Map<String, String> map, Map<String, String> map2) {
        if (!checkNetwork()) {
            return null;
        }
        w.a aVar = new w.a();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                aVar = aVar.a(entry.getKey(), entry.getValue());
            }
        }
        String strMapToQueryString = (map2 == null || map2.size() <= 0) ? "" : mapToQueryString(map2);
        BydLogHelper.logV("url=" + str + ", paramString=" + strMapToQueryString + ", pkgName=" + this.mContext.getPackageName());
        return execute(aVar.i(str).g(x.create(MediaType.FORM, strMapToQueryString)).b());
    }

    public <T> e postJson(String str, Map<String, String> map, String str2, BydHttpCallBack<T> bydHttpCallBack) {
        if (!checkNetwork(bydHttpCallBack)) {
            return null;
        }
        BydLogHelper.logV("url=" + str + ", jsonString=" + str2 + ", pkgName=" + this.mContext.getPackageName());
        w.a aVar = new w.a();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                aVar = aVar.a(entry.getKey(), entry.getValue());
            }
        }
        return enqueue(aVar.i(str).g(x.create(MediaType.JSON, str2)).b(), bydHttpCallBack);
    }

    public y postJsonSync(String str, Map<String, String> map, String str2) {
        if (!checkNetwork()) {
            return null;
        }
        BydLogHelper.logV("url=" + str + ", jsonString=" + str2 + ", pkgName=" + this.mContext.getPackageName());
        w.a aVar = new w.a();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                aVar = aVar.a(entry.getKey(), entry.getValue());
            }
        }
        return execute(aVar.i(str).g(x.create(MediaType.JSON, str2)).b());
    }

    public <T> e get(String str, Map<String, String> map, Map<String, String> map2, BydHttpCallBack<T> bydHttpCallBack) {
        if (!checkNetwork(bydHttpCallBack)) {
            return null;
        }
        w.a aVar = new w.a();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                aVar = aVar.a(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null && map2.size() > 0) {
            str = str + "?" + mapToQueryString(map2);
        }
        BydLogHelper.logV("url=" + str + ", pkgName=" + this.mContext.getPackageName());
        return enqueue(aVar.i(str).b(), bydHttpCallBack);
    }

    public y getSync(String str, Map<String, String> map, Map<String, String> map2) {
        if (!checkNetwork()) {
            return null;
        }
        w.a aVar = new w.a();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                aVar = aVar.a(entry.getKey(), entry.getValue());
            }
        }
        if (map2 != null && map2.size() > 0) {
            str = str + "?" + mapToQueryString(map2);
        }
        BydLogHelper.logV("url=" + str + ", pkgName=" + this.mContext.getPackageName());
        return execute(aVar.i(str).b());
    }

    private boolean checkNetwork() {
        if (BydNetHelper.isNetworkConnected(this.mContext)) {
            return true;
        }
        BydLogHelper.logV("current network disconnect");
        return false;
    }
}
