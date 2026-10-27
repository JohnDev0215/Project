package com.loostone.libserver.version1.net;

import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class NetRequest {
    private static final String TAG = "NetRequest";
    private static RequestQueue mQueue;

    public static void cancelRequest(String str) {
        RequestQueue requestQueue = mQueue;
        if (requestQueue == null || str == null) {
            return;
        }
        requestQueue.cancelAll(str);
    }

    public static synchronized RequestQueue getQueue(Context context) {
        if (mQueue == null) {
            RequestQueue requestQueueNewRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
            mQueue = requestQueueNewRequestQueue;
            requestQueueNewRequestQueue.start();
        }
        return mQueue;
    }

    static /* synthetic */ void lambda$request$0(NetParam netParam, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, String str) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "response-success: url=" + netParam.url + ", response:" + str);
        if (bVar != null) {
            bVar.onSuccess(str);
        }
    }

    static /* synthetic */ void lambda$request$1(NetParam netParam, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, VolleyError volleyError) {
        OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "response-error: url=" + netParam.url + " response error");
        if (bVar != null) {
            bVar.onError("无法连接服务器");
        }
    }

    public static synchronized StringRequest request(Context context, final NetParam netParam, final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        MyStringRequest myStringRequest;
        if (mQueue == null) {
            mQueue = getQueue(context);
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "request:url=" + netParam.url + ", param:" + netParam.param.toString());
        myStringRequest = new MyStringRequest(1, netParam.url, new Response.Listener() { // from class: com.loostone.libserver.version1.net.a
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                NetRequest.lambda$request$0(netParam, bVar, (String) obj);
            }
        }, new Response.ErrorListener() { // from class: com.loostone.libserver.version1.net.b
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                NetRequest.lambda$request$1(netParam, bVar, volleyError);
            }
        }) { // from class: com.loostone.libserver.version1.net.NetRequest.1
            @Override // com.android.volley.Request
            public byte[] getBody() {
                NetParam netParam2 = netParam;
                return netParam2.isRawRequest ? netParam2.param.toString().getBytes() : super.getBody();
            }

            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() {
                Map<String, String> map = netParam.header;
                return map != null ? map : super.getHeaders();
            }

            @Override // com.android.volley.Request
            public Map<String, String> getParams() {
                NetParam netParam2 = netParam;
                return netParam2.isRawRequest ? super.getParams() : (Map) netParam2.param;
            }

            @Override // com.android.volley.Request
            public RetryPolicy getRetryPolicy() {
                NetParam netParam2 = netParam;
                return new DefaultRetryPolicy(netParam2.timeout, netParam2.retry, 1.0f);
            }
        };
        myStringRequest.setTag(netParam.requestTag);
        mQueue.add(myStringRequest);
        return myStringRequest;
    }
}
