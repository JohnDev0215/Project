package androidx.media.session;

import android.app.ForegroundServiceStartNotAllowedException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import android.view.KeyEvent;
import defpackage.C0175b;
import defpackage.bz;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class MediaButtonReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    public static class a extends MediaBrowserCompat.ConnectionCallback {
        public final Context a;
        public final Intent b;
        public final BroadcastReceiver.PendingResult c;
        public MediaBrowserCompat d;

        public a(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.a = context;
            this.b = intent;
            this.c = pendingResult;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public final void onConnected() {
            new MediaControllerCompat(this.a, this.d.getSessionToken()).dispatchMediaButtonEvent((KeyEvent) this.b.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            this.d.disconnect();
            this.c.finish();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public final void onConnectionFailed() {
            this.d.disconnect();
            this.c.finish();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public final void onConnectionSuspended() {
            this.d.disconnect();
            this.c.finish();
        }
    }

    public static ComponentName a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        StringBuilder sbN = C0175b.n("Expected 1 service that handles ", str, ", found ");
        sbN.append(listQueryIntentServices.size());
        throw new IllegalStateException(sbN.toString());
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Objects.toString(intent);
            return;
        }
        ComponentName componentNameA = a(context, "android.intent.action.MEDIA_BUTTON");
        if (componentNameA == null) {
            ComponentName componentNameA2 = a(context, "android.media.browse.MediaBrowserService");
            if (componentNameA2 == null) {
                throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
            }
            BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            Context applicationContext = context.getApplicationContext();
            a aVar = new a(applicationContext, intent, pendingResultGoAsync);
            MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(applicationContext, componentNameA2, aVar, null);
            aVar.d = mediaBrowserCompat;
            mediaBrowserCompat.connect();
            return;
        }
        intent.setComponent(componentNameA);
        try {
            Object obj = bz.a;
            context.startForegroundService(intent);
        } catch (IllegalStateException e) {
            if (Build.VERSION.SDK_INT < 31 || !(e instanceof ForegroundServiceStartNotAllowedException)) {
                throw e;
            }
            Log.e("MediaButtonReceiver", "caught exception when trying to start a foreground service from the background: " + ((ForegroundServiceStartNotAllowedException) e).getMessage());
        }
    }
}
