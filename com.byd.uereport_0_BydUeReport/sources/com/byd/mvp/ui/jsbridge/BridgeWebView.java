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
import f4.b;
import f4.c;
import f4.d;
import f4.e;
import f4.f;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public class BridgeWebView extends WebView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<String, d> f2818d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<String, f4.a> f2819e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f4.a f2820f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<f> f2821g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f2822h;

    public class a implements d {

        /* JADX INFO: renamed from: com.byd.mvp.ui.jsbridge.BridgeWebView$a$a, reason: collision with other inner class name */
        public class C0018a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f2824a;

            public C0018a(String str) {
                this.f2824a = str;
            }

            @Override // f4.d
            public void a(String str) {
                f fVar = new f();
                fVar.j(this.f2824a);
                fVar.i(str);
                BridgeWebView.this.j(fVar);
            }
        }

        public class b implements d {
            public b() {
            }

            @Override // f4.d
            public void a(String str) {
            }
        }

        public a() {
        }

        @Override // f4.d
        public void a(String str) {
            try {
                List<f> listK = f.k(str);
                if (listK == null || listK.size() == 0) {
                    return;
                }
                for (int i8 = 0; i8 < listK.size(); i8++) {
                    f fVar = listK.get(i8);
                    String strE = fVar.e();
                    if (TextUtils.isEmpty(strE)) {
                        String strA = fVar.a();
                        d c0018a = !TextUtils.isEmpty(strA) ? new C0018a(strA) : new b();
                        f4.a aVar = !TextUtils.isEmpty(fVar.c()) ? BridgeWebView.this.f2819e.get(fVar.c()) : BridgeWebView.this.f2820f;
                        if (aVar != null) {
                            aVar.a(fVar.b(), c0018a);
                        }
                    } else {
                        BridgeWebView.this.f2818d.get(strE).a(fVar.d());
                        BridgeWebView.this.f2818d.remove(strE);
                    }
                }
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
    }

    public BridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2818d = new HashMap();
        this.f2819e = new HashMap();
        this.f2820f = new e();
        this.f2821g = new ArrayList();
        this.f2822h = 0L;
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
        d dVar = this.f2818d.get(strC);
        String strB = b.b(str);
        if (dVar != null) {
            dVar.a(strB);
            this.f2818d.remove(strC);
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
        return this.f2821g;
    }

    public void h(String str, d dVar) {
        loadUrl(str);
        this.f2818d.put(b.d(str), dVar);
    }

    public void i() {
    }

    public final void j(f fVar) {
        List<f> list = this.f2821g;
        if (list != null) {
            list.add(fVar);
        } else {
            b(fVar);
        }
    }

    public void setDefaultHandler(f4.a aVar) {
        this.f2820f = aVar;
    }

    public void setStartupMessage(List<f> list) {
        this.f2821g = list;
    }

    public BridgeWebView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2818d = new HashMap();
        this.f2819e = new HashMap();
        this.f2820f = new e();
        this.f2821g = new ArrayList();
        this.f2822h = 0L;
        f();
    }

    public BridgeWebView(Context context) {
        super(context);
        this.f2818d = new HashMap();
        this.f2819e = new HashMap();
        this.f2820f = new e();
        this.f2821g = new ArrayList();
        this.f2822h = 0L;
        f();
    }
}
