package OooO00o.OooO00o.OooO0Oo.OooO0Oo.f;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loostone.libserver.version2.net.FileRequest;
import com.loostone.libserver.version2.net.HeaderJsonObjectRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static RequestQueue f163b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f162a = new i();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f164c = OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.b.f136a.a();

    public static final void c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, VolleyError volleyError) {
        kotlin.jvm.internal.h.d(bVar, "$callback");
        bVar.onError(String.valueOf(volleyError.getMessage()));
    }

    public static final void d(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, String str, Boolean bool) {
        kotlin.jvm.internal.h.d(str, "$filePath");
        if (bVar == null) {
            return;
        }
        kotlin.jvm.internal.h.c(bool, "it");
        if (bool.booleanValue()) {
            bVar.onSuccess(str);
        } else {
            bVar.onError("download file error");
        }
    }

    public static final void e(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, JSONObject jSONObject) {
        kotlin.jvm.internal.h.d(bVar, "$callback");
        bVar.onSuccess(jSONObject.toString());
    }

    public static final void j(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, VolleyError volleyError) {
        kotlin.jvm.internal.h.d(bVar, "$callback");
        bVar.onError(String.valueOf(volleyError.getMessage()));
    }

    public static final void k(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, JSONObject jSONObject) {
        kotlin.jvm.internal.h.d(bVar, "$callback");
        bVar.onSuccess(jSONObject.toString());
    }

    public static final void m(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, VolleyError volleyError) {
        if (bVar == null) {
            return;
        }
        bVar.onError("volley error, download file error");
    }

    public static final void n(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, JSONObject jSONObject) {
        if (bVar == null) {
            return;
        }
        bVar.onSuccess(jSONObject.toString());
    }

    public static final void o(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, VolleyError volleyError) {
        if (bVar == null) {
            return;
        }
        bVar.onError(String.valueOf(volleyError.getMessage()));
    }

    public final synchronized RequestQueue a(Context context) {
        kotlin.jvm.internal.h.d(context, "context");
        if (f163b == null) {
            RequestQueue requestQueueNewRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
            f163b = requestQueueNewRequestQueue;
            if (requestQueueNewRequestQueue != null) {
                requestQueueNewRequestQueue.start();
            }
        }
        return f163b;
    }

    public final void b() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("OTANetRequest", "cancel all request");
        RequestQueue requestQueue = f163b;
        if (requestQueue == null) {
            return;
        }
        requestQueue.cancelAll("OTANetRequest");
    }

    public final synchronized void f(Context context, String str, final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(context, "context");
        kotlin.jvm.internal.h.d(str, "fileUrl");
        if (f163b == null) {
            f163b = a(context);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(0, str, null, new Response.Listener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.h
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                i.n(bVar, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.b
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                i.o(bVar, volleyError);
            }
        });
        jsonObjectRequest.setTag("OTANetRequest");
        RequestQueue requestQueue = f163b;
        if (requestQueue != null) {
            requestQueue.add(jsonObjectRequest);
        }
    }

    public final synchronized void g(Context context, String str, final String str2, final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(context, "context");
        kotlin.jvm.internal.h.d(str, "fileUrl");
        kotlin.jvm.internal.h.d(str2, "filePath");
        if (f163b == null) {
            f163b = a(context);
        }
        FileRequest fileRequest = new FileRequest(str, str2, new Response.Listener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.g
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                i.d(bVar, str2, (Boolean) obj);
            }
        }, new Response.ErrorListener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.c
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                i.m(bVar, volleyError);
            }
        });
        fileRequest.setTag("OTANetRequest");
        RequestQueue requestQueue = f163b;
        if (requestQueue != null) {
            requestQueue.add(fileRequest);
        }
    }

    public final synchronized void h(Context context, String str, JSONObject jSONObject, final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(context, "context");
        kotlin.jvm.internal.h.d(str, "api");
        kotlin.jvm.internal.h.d(jSONObject, "jsonObject");
        kotlin.jvm.internal.h.d(bVar, "callback");
        if (f163b == null) {
            f163b = a(context);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, kotlin.jvm.internal.h.i(f164c, str), jSONObject, new Response.Listener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.a
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                i.e(bVar, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.f
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                i.c(bVar, volleyError);
            }
        });
        jsonObjectRequest.setTag("OTANetRequest");
        RequestQueue requestQueue = f163b;
        if (requestQueue != null) {
            requestQueue.add(jsonObjectRequest);
        }
    }

    public final String i() {
        return f164c;
    }

    public final synchronized void l(Context context, String str, JSONObject jSONObject, final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(context, "context");
        kotlin.jvm.internal.h.d(str, "api");
        kotlin.jvm.internal.h.d(jSONObject, "jsonObject");
        kotlin.jvm.internal.h.d(bVar, "callback");
        if (f163b == null) {
            f163b = a(context);
        }
        String string = jSONObject.getString("header");
        jSONObject.remove("header");
        HeaderJsonObjectRequest.OooO00o oooO00o = HeaderJsonObjectRequest.Companion;
        kotlin.jvm.internal.h.c(string, "header");
        oooO00o.OooO00o(string);
        HeaderJsonObjectRequest headerJsonObjectRequest = new HeaderJsonObjectRequest(1, kotlin.jvm.internal.h.i(f164c, str), jSONObject, new Response.Listener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.d
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                i.k(bVar, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.e
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                i.j(bVar, volleyError);
            }
        });
        headerJsonObjectRequest.setTag("OTANetRequest");
        RequestQueue requestQueue = f163b;
        if (requestQueue != null) {
            requestQueue.add(headerJsonObjectRequest);
        }
    }
}
