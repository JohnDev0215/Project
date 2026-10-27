package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.versionedparcelable.ParcelImpl;
import java.lang.ref.WeakReference;
import p.at2;
import p.ln1;
import p.uo;

/* JADX INFO: loaded from: classes.dex */
class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {
    public WeakReference c;

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, Bundle bundle) {
        c cVar = (c) this.c.get();
        if (cVar == null || bundle == null) {
            return;
        }
        synchronized (cVar.e) {
            MediaSessionCompat$Token mediaSessionCompat$Token = cVar.m;
            b bVarG0 = a.g0(uo.a(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
            synchronized (mediaSessionCompat$Token.c) {
                mediaSessionCompat$Token.k = bVarG0;
            }
            MediaSessionCompat$Token mediaSessionCompat$Token2 = cVar.m;
            at2 at2Var = null;
            try {
                Bundle bundle2 = (Bundle) bundle.getParcelable("android.support.v4.media.session.SESSION_TOKEN2");
                if (bundle2 != null) {
                    bundle2.setClassLoader(ln1.class.getClassLoader());
                    Parcelable parcelable = bundle2.getParcelable("a");
                    if (!(parcelable instanceof ParcelImpl)) {
                        throw new IllegalArgumentException("Invalid parcel");
                    }
                    at2Var = ((ParcelImpl) parcelable).c;
                }
            } catch (RuntimeException unused) {
            }
            synchronized (mediaSessionCompat$Token2.c) {
                mediaSessionCompat$Token2.l = at2Var;
            }
            cVar.b();
        }
    }
}
