package spi;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.template.ILogger;

/* JADX INFO: compiled from: _ARouter.java */
/* JADX INFO: loaded from: classes.dex */
public final class gd implements InterceptorCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ NavigationCallback b;
    public final /* synthetic */ Postcard c;
    public final /* synthetic */ hd d;

    public gd(hd hdVar, int i, NavigationCallback navigationCallback, Postcard postcard) {
        this.d = hdVar;
        this.a = i;
        this.b = navigationCallback;
        this.c = postcard;
    }

    @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
    public final void onContinue(Postcard postcard) {
        hd hdVar = this.d;
        int i = this.a;
        NavigationCallback navigationCallback = this.b;
        ILogger iLogger = hd.a;
        hdVar.a(postcard, i, navigationCallback);
    }

    @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
    public final void onInterrupt(Throwable th) {
        NavigationCallback navigationCallback = this.b;
        if (navigationCallback != null) {
            navigationCallback.onInterrupt(this.c);
        }
        ILogger iLogger = hd.a;
        StringBuilder sbK = c.k("Navigation failed, termination by interceptor : ");
        sbK.append(th.getMessage());
        iLogger.info("ARouter::", sbK.toString());
    }
}
