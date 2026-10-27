package org.chromium.chrome.browser.base;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;
import defpackage.mh;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatRemoteViewsService extends RemoteViewsService {
    public final String e = "mh";
    public a f;

    public static abstract class a {
        public SplitCompatRemoteViewsService a;

        public abstract mh.b a(Intent intent);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        Context contextB = SplitCompatApplication.b(context);
        a aVar = (a) BundleUtils.e(contextB, this.e);
        this.f = aVar;
        aVar.a = this;
        super.attachBaseContext(contextB);
    }

    @Override // android.widget.RemoteViewsService
    public final RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return this.f.a(intent);
    }
}
