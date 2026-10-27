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
import b.b.m.f.e.b;
import b.b.m.f.e.c;
import b.b.m.f.e.d;
import b.b.m.f.e.e;
import b.b.m.f.e.f;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public class BridgeWebView extends WebView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, d> f3761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Map<String, b.b.m.f.e.a> f3762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.m.f.e.a f3763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<f> f3764d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3765e;

    public class a implements d {

        /* JADX INFO: renamed from: com.byd.mvp.ui.jsbridge.BridgeWebView$a$a, reason: collision with other inner class name */
        public class C0099a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f3767a;

            public C0099a(String str) {
                this.f3767a = str;
            }

            @Override // b.b.m.f.e.d
            public void a(String str) {
                f fVar = new f();
                fVar.j(this.f3767a);
                fVar.i(str);
                BridgeWebView.this.j(fVar);
            }
        }

        public class b implements d {
            public b() {
            }

            @Override // b.b.m.f.e.d
            public void a(String str) {
            }
        }

        public a() {
        }

        @Override // b.b.m.f.e.d
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
                        d c0099a = !TextUtils.isEmpty(strA) ? new C0099a(strA) : new b();
                        b.b.m.f.e.a aVar = !TextUtils.isEmpty(fVar.c()) ? BridgeWebView.this.f3762b.get(fVar.c()) : BridgeWebView.this.f3763c;
                        if (aVar != null) {
                            aVar.a(fVar.b(), c0099a);
                        }
                    } else {
                        BridgeWebView.this.f3761a.get(strE).a(fVar.d());
                        BridgeWebView.this.f3761a.remove(strE);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public BridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3761a = new HashMap();
        this.f3762b = new HashMap();
        this.f3763c = new e();
        this.f3764d = new ArrayList();
        this.f3765e = 0L;
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
        d dVar = this.f3761a.get(strC);
        String strB = b.b(str);
        if (dVar != null) {
            dVar.a(strB);
            this.f3761a.remove(strC);
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
        return this.f3764d;
    }

    public void h(String str, d dVar) {
        loadUrl(str);
        this.f3761a.put(b.d(str), dVar);
    }

    public void i() {
    }

    public final void j(f fVar) {
        List<f> list = this.f3764d;
        if (list != null) {
            list.add(fVar);
        } else {
            b(fVar);
        }
    }

    public void setDefaultHandler(b.b.m.f.e.a aVar) {
        this.f3763c = aVar;
    }

    public void setStartupMessage(List<f> list) {
        this.f3764d = list;
    }

    public BridgeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3761a = new HashMap();
        this.f3762b = new HashMap();
        this.f3763c = new e();
        this.f3764d = new ArrayList();
        this.f3765e = 0L;
        f();
    }

    public BridgeWebView(Context context) {
        super(context);
        this.f3761a = new HashMap();
        this.f3762b = new HashMap();
        this.f3763c = new e();
        this.f3764d = new ArrayList();
        this.f3765e = 0L;
        f();
    }
}
