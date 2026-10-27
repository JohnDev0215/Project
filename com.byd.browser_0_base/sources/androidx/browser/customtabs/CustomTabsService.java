package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService;
import androidx.browser.customtabs.CustomTabsService;
import defpackage.wm2;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class CustomTabsService extends Service {
    public final wm2<IBinder, IBinder.DeathRecipient> e = new wm2<>();
    public final a f = new a();

    public class a extends ICustomTabsService.Stub {
        public a() {
        }

        public static PendingIntent A(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("android.support.customtabs.extra.SESSION_ID");
            bundle.remove("android.support.customtabs.extra.SESSION_ID");
            return pendingIntent;
        }

        public final boolean B(ICustomTabsCallback iCustomTabsCallback, PendingIntent pendingIntent) {
            final CustomTabsSessionToken customTabsSessionToken = new CustomTabsSessionToken(iCustomTabsCallback, pendingIntent);
            try {
                IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: i50
                    @Override // android.os.IBinder.DeathRecipient
                    public final void binderDied() {
                        CustomTabsService.this.a(customTabsSessionToken);
                    }
                };
                synchronized (CustomTabsService.this.e) {
                    iCustomTabsCallback.asBinder().linkToDeath(deathRecipient, 0);
                    CustomTabsService.this.e.put(iCustomTabsCallback.asBinder(), deathRecipient);
                }
                return CustomTabsService.this.d(customTabsSessionToken);
            } catch (RemoteException unused) {
                return false;
            }
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final Bundle extraCommand(String str, Bundle bundle) {
            return CustomTabsService.this.b(str, bundle);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle, List<Bundle> list) {
            return CustomTabsService.this.c(new CustomTabsSessionToken(iCustomTabsCallback, A(bundle)), uri, bundle, list);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean newSession(ICustomTabsCallback iCustomTabsCallback) {
            return B(iCustomTabsCallback, null);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean newSessionWithExtras(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) {
            return B(iCustomTabsCallback, A(bundle));
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final int postMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) {
            return CustomTabsService.this.e(new CustomTabsSessionToken(iCustomTabsCallback, A(bundle)), str, bundle);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean receiveFile(ICustomTabsCallback iCustomTabsCallback, Uri uri, int i, Bundle bundle) {
            return CustomTabsService.this.f(i, uri, bundle, new CustomTabsSessionToken(iCustomTabsCallback, A(bundle)));
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback, Uri uri) {
            return CustomTabsService.this.g(new CustomTabsSessionToken(iCustomTabsCallback, null), uri);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean requestPostMessageChannelWithExtras(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle) {
            return CustomTabsService.this.g(new CustomTabsSessionToken(iCustomTabsCallback, A(bundle)), uri);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) {
            return CustomTabsService.this.h(new CustomTabsSessionToken(iCustomTabsCallback, A(bundle)), bundle);
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback, int i, Uri uri, Bundle bundle) {
            return CustomTabsService.this.i(i, uri, bundle, new CustomTabsSessionToken(iCustomTabsCallback, A(bundle)));
        }

        @Override // android.support.customtabs.ICustomTabsService
        public final boolean warmup(long j) {
            return CustomTabsService.this.j(j);
        }
    }

    public boolean a(CustomTabsSessionToken customTabsSessionToken) {
        try {
            synchronized (this.e) {
                ICustomTabsCallback iCustomTabsCallback = customTabsSessionToken.a;
                IBinder iBinderAsBinder = iCustomTabsCallback == null ? null : iCustomTabsCallback.asBinder();
                if (iBinderAsBinder == null) {
                    return false;
                }
                iBinderAsBinder.unlinkToDeath(this.e.get(iBinderAsBinder), 0);
                this.e.remove(iBinderAsBinder);
                return true;
            }
        } catch (NoSuchElementException unused) {
            return false;
        }
    }

    public abstract Bundle b(String str, Bundle bundle);

    public abstract boolean c(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list);

    public abstract boolean d(CustomTabsSessionToken customTabsSessionToken);

    public abstract int e(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle);

    public abstract boolean f(int i, Uri uri, Bundle bundle, CustomTabsSessionToken customTabsSessionToken);

    public abstract boolean g(CustomTabsSessionToken customTabsSessionToken, Uri uri);

    public abstract boolean h(CustomTabsSessionToken customTabsSessionToken, Bundle bundle);

    public abstract boolean i(int i, Uri uri, Bundle bundle, CustomTabsSessionToken customTabsSessionToken);

    public abstract boolean j(long j);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f;
    }
}
