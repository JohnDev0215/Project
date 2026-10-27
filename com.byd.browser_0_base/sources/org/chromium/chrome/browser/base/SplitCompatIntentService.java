package org.chromium.chrome.browser.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatIntentService extends IntentService {
    public final String e;
    public a f;

    public SplitCompatIntentService(String str, String str2) {
        super(str2);
        this.e = str;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        Context contextB = SplitCompatApplication.b(context);
        a aVar = (a) BundleUtils.e(contextB, this.e);
        this.f = aVar;
        aVar.a = this;
        aVar.b();
        super.attachBaseContext(contextB);
    }

    @Override // android.app.IntentService
    public final void onHandleIntent(Intent intent) {
        this.f.a(intent);
    }

    public static abstract class a {
        public SplitCompatIntentService a;

        public abstract void a(Intent intent);

        public void b() {
        }
    }
}
