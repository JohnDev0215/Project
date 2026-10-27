package com.byd.mvp.ui.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import b.a.d.e.b.b;
import b.a.d.e.b.c;
import b.a.d.e.b.d;
import b.a.d.e.b.e;
import b.a.d.e.b.f;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public class BridgeWebView extends WebView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Map<String, d> f698b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, b.a.d.e.b.a> f699c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.a.d.e.b.a f700d;
    public List<f> e;
    public long f;

    public class a implements d {

        /* JADX INFO: renamed from: com.byd.mvp.ui.jsbridge.BridgeWebView$a$a, reason: collision with other inner class name */
        public class C0033a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f702a;

            public C0033a(String str) {
                this.f702a = str;
            }

            @Override // b.a.d.e.b.d
            public void a(String str) {
                f fVar = new f();
                fVar.j(this.f702a);
                fVar.i(str);
                BridgeWebView.this.j(fVar);
            }
        }

        public class b implements d {
            public b() {
            }

            @Override // b.a.d.e.b.d
            public void a(String str) {
            }
        }

        public a() {
        }

        @Override // b.a.d.e.b.d
        public void a(String str) {
            try {
                List<f> listK = f.k(str);
                if (listK == null || listK.size() == 0) {
                    return;
                }
                for (int i = 0; i < listK.size(); i++) {
                    f fVar = listK.get(i);
                    String strE = fVar.e();
                    if (TextUtils.isEmpty(strE)) {
                        String strA = fVar.a();
                        d c0033a = !TextUtils.isEmpty(strA) ? new C0033a(strA) : new b();
                        b.a.d.e.b.a aVar = !TextUtils.isEmpty(fVar.c()) ? BridgeWebView.this.f699c.get(fVar.c()) : BridgeWebView.this.f700d;
                        if (aVar != null) {
                            aVar.a(fVar.b(), c0033a);
                        }
                    } else {
                        BridgeWebView.this.f698b.get(strE).a(fVar.d());
                        BridgeWebView.this.f698b.remove(strE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f698b = new HashMap();
        this.f699c = new HashMap();
        this.f700d = new e();
        this.e = new ArrayList();
        this.f = 0L;
        f();
    }

    public static boolean g(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public void b(f fVar) {
        String str = String.format("javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');", fVar.l().replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2").replaceAll("(?<=[^\\\\])(\")", "\\\\\"").replaceAll("(?<=[^\\\\])(')", "\\\\'").replaceAll("%7B", URLEncoder.encode("%7B")).replaceAll("%7D", URLEncoder.encode("%7D")).replaceAll("%22", URLEncoder.encode("%22")));
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(str);
        }
    }

    public void c() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            h("javascript:WebViewJavascriptBridge._fetchQueue();", new a());
        }
    }

    public c d() {
        return new c(this);
    }

    public void e(String str) {
        String strC = b.c(str);
        d dVar = this.f698b.get(strC);
        String strB = b.b(str);
        if (dVar != null) {
            dVar.a(strB);
            this.f698b.remove(strC);
        }
    }

    public final void f() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        if (g(getContext())) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setWebViewClient(d());
    }

    public List<f> getStartupMessage() {
        return this.e;
    }

    public void h(String str, d dVar) {
        loadUrl(str);
        this.f698b.put(b.d(str), dVar);
    }

    public void i() {
    }

    public final void j(f fVar) {
        List<f> list = this.e;
        if (list != null) {
            list.add(fVar);
        } else {
            b(fVar);
        }
    }

    public void setDefaultHandler(b.a.d.e.b.a aVar) {
        this.f700d = aVar;
    }

    public void setStartupMessage(List<f> list) {
        this.e = list;
    }

    public BridgeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f698b = new HashMap();
        this.f699c = new HashMap();
        this.f700d = new e();
        this.e = new ArrayList();
        this.f = 0L;
        f();
    }

    public BridgeWebView(Context context) {
        super(context);
        this.f698b = new HashMap();
        this.f699c = new HashMap();
        this.f700d = new e();
        this.e = new ArrayList();
        this.f = 0L;
        f();
    }
}
