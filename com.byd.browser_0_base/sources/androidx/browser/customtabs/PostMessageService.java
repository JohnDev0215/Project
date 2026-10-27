package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService;

/* JADX INFO: loaded from: classes.dex */
public class PostMessageService extends Service {
    public final a e = new a();

    public class a extends IPostMessageService.Stub {
        @Override // android.support.customtabs.IPostMessageService
        public final void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) {
            iCustomTabsCallback.onMessageChannelReady(bundle);
        }

        @Override // android.support.customtabs.IPostMessageService
        public final void onPostMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) {
            iCustomTabsCallback.onPostMessage(str, bundle);
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.e;
    }
}
