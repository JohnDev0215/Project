package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.util.Log;
import defpackage.n3;

/* JADX INFO: loaded from: classes.dex */
public final class CustomTabsSessionToken {
    public final ICustomTabsCallback a;
    public final PendingIntent b;
    public final a c;

    public class a extends n3 {
        public a() {
        }

        @Override // defpackage.n3
        public final void L(String str, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.a.extraCallback(str, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        @Override // defpackage.n3
        public final Bundle M(String str, Bundle bundle) {
            try {
                return CustomTabsSessionToken.this.a.extraCallbackWithResult(str, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                return null;
            }
        }

        @Override // defpackage.n3
        public final void R0(int i, int i2, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.a.onActivityResized(i, i2, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        @Override // defpackage.n3
        public final void T0(int i, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.a.onNavigationEvent(i, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }

        @Override // defpackage.n3
        public final void U0(int i, Uri uri, boolean z, Bundle bundle) {
            try {
                CustomTabsSessionToken.this.a.onRelationshipValidationResult(i, uri, z, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }
    }

    public CustomTabsSessionToken(ICustomTabsCallback iCustomTabsCallback, PendingIntent pendingIntent) {
        if (iCustomTabsCallback == null && pendingIntent == null) {
            throw new IllegalStateException("CustomTabsSessionToken must have either a session id or a callback (or both).");
        }
        this.a = iCustomTabsCallback;
        this.b = pendingIntent;
        this.c = iCustomTabsCallback == null ? null : new a();
    }

    public static CustomTabsSessionToken b(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        IBinder binder = extras.getBinder("android.support.customtabs.extra.SESSION");
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("android.support.customtabs.extra.SESSION_ID");
        if (binder == null && pendingIntent == null) {
            return null;
        }
        return new CustomTabsSessionToken(binder != null ? ICustomTabsCallback.Stub.asInterface(binder) : null, pendingIntent);
    }

    public final IBinder a() {
        ICustomTabsCallback iCustomTabsCallback = this.a;
        if (iCustomTabsCallback != null) {
            return iCustomTabsCallback.asBinder();
        }
        throw new IllegalStateException("CustomTabSessionToken must have valid binder or pending session");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CustomTabsSessionToken)) {
            return false;
        }
        CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) obj;
        PendingIntent pendingIntent = customTabsSessionToken.b;
        PendingIntent pendingIntent2 = this.b;
        if ((pendingIntent2 == null) != (pendingIntent == null)) {
            return false;
        }
        return pendingIntent2 != null ? pendingIntent2.equals(pendingIntent) : a().equals(customTabsSessionToken.a());
    }

    public final int hashCode() {
        PendingIntent pendingIntent = this.b;
        return pendingIntent != null ? pendingIntent.hashCode() : a().hashCode();
    }
}
