package com.amazon.identity.auth.device;

import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class g5 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t5 f347a;
    public final /* synthetic */ WebView b;
    public final /* synthetic */ String c;

    public g5(MAPRuntimePermissionHandler mAPRuntimePermissionHandler, t5 t5Var, WebView webView, String str) {
        this.f347a = t5Var;
        this.b = webView;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Log.i(ga.a(MAPRuntimePermissionHandler.g), "Refreshing cookie to indicate latest permission and metadata information.");
        ab.a(this.f347a, this.b.getUrl(), "map-md", this.c, "/ap", null, true, true, true);
    }
}
