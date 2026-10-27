package com.android.car.media.common.source;

import android.car.utils.Logs;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.media.utils.MediaConstants;
import com.android.car.ui.R$integer;
import java.util.Objects;
import spi.b1;
import spi.b7;
import spi.q6;

/* JADX INFO: compiled from: MediaBrowserConnector.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final Context a;
    public final d b;
    public final int c;

    @Nullable
    public b7 d;

    @Nullable
    public MediaBrowserCompat e;
    public int f = 0;

    /* JADX INFO: compiled from: MediaBrowserConnector.java */
    public class b extends MediaBrowserCompat.ConnectionCallback {
        public final int a;
        public final String b;

        public final boolean a(String str) {
            if (this.a == a.this.f) {
                StringBuilder sbN = spi.c.n(str, " ");
                sbN.append(a.this.c());
                sbN.append(" mBrowser:");
                sbN.append(b1.a(a.this.e));
                Logs.w("MediaBrowserConnector", sbN.toString());
                return true;
            }
            StringBuilder sbK = spi.c.k("Callback: ");
            sbK.append(b1.a(this));
            sbK.append(" ignoring ");
            sbK.append(str);
            sbK.append(" for ");
            sbK.append(this.b);
            sbK.append(" seq: ");
            sbK.append(this.a);
            sbK.append(" current: ");
            sbK.append(a.this.f);
            sbK.append(" package: ");
            sbK.append(a.this.c());
            Logs.e("MediaBrowserConnector", sbK.toString());
            return false;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public final void onConnected() {
            if (a("onConnected")) {
                MediaBrowserCompat mediaBrowserCompat = a.this.e;
                if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                    a.this.d(e.REJECTED);
                } else {
                    a.this.d(e.CONNECTED);
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public final void onConnectionFailed() {
            if (a("onConnectionFailed")) {
                a.this.d(e.REJECTED);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public final void onConnectionSuspended() {
            if (a("onConnectionSuspended")) {
                a.this.d(e.SUSPENDED);
            }
        }

        private b() {
            int i = a.this.f + 1;
            a.this.f = i;
            this.a = i;
            this.b = a.this.c();
            StringBuilder sbK = spi.c.k("New Callback: ");
            sbK.append(b1.a(this));
            Logs.i("MediaBrowserConnector", sbK.toString());
        }
    }

    /* JADX INFO: compiled from: MediaBrowserConnector.java */
    public static class c {

        @NonNull
        public final b7 a;

        @NonNull
        public final MediaBrowserCompat b;

        @NonNull
        public final e c;

        @VisibleForTesting
        public c(@NonNull b7 b7Var, @NonNull MediaBrowserCompat mediaBrowserCompat, @NonNull e eVar) {
            a.a(b7Var, "source can't be null");
            this.a = b7Var;
            a.a(mediaBrowserCompat, "browser can't be null");
            this.b = mediaBrowserCompat;
            a.a(eVar, "status can't be null");
            this.c = eVar;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || c.class != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            return this.a.equals(cVar.a) && this.b.equals(cVar.b) && this.c == cVar.c;
        }

        public final int hashCode() {
            return Objects.hash(this.a, this.b, this.c);
        }
    }

    /* JADX INFO: compiled from: MediaBrowserConnector.java */
    public interface d {
    }

    /* JADX INFO: compiled from: MediaBrowserConnector.java */
    public enum e {
        CONNECTING,
        CONNECTED,
        REJECTED,
        SUSPENDED,
        DISCONNECTING
    }

    public a(@NonNull Context context, @NonNull d dVar) {
        this.a = context;
        this.b = dVar;
        this.c = context.getResources().getInteger(R$integer.media_items_bitmap_max_size_px);
    }

    public static Object a(Object obj, Object obj2) {
        Objects.requireNonNull(obj, String.valueOf(obj2));
        return obj;
    }

    public final void b(@Nullable b7 b7Var) {
        MediaBrowserCompat mediaBrowserCompat = this.e;
        if (mediaBrowserCompat != null && mediaBrowserCompat.isConnected()) {
            StringBuilder sbK = spi.c.k("Disconnecting: ");
            sbK.append(c());
            sbK.append(" mBrowser: ");
            sbK.append(b1.a(this.e));
            Logs.w("MediaBrowserConnector", sbK.toString());
            d(e.DISCONNECTING);
            this.e.disconnect();
        }
        this.d = b7Var;
        if (b7Var == null) {
            this.e = null;
            return;
        }
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putInt(MediaConstants.BROWSER_ROOT_HINTS_KEY_MEDIA_ART_SIZE_PIXELS, this.c);
        this.e = new MediaBrowserCompat(this.a, b7Var.a, bVar, bundle);
        StringBuilder sbK2 = spi.c.k("Connecting to: ");
        sbK2.append(c());
        sbK2.append(" mBrowser: ");
        sbK2.append(b1.a(this.e));
        Logs.w("MediaBrowserConnector", sbK2.toString());
        try {
            d(e.CONNECTING);
            this.e.connect();
        } catch (IllegalStateException e2) {
            Logs.e("MediaBrowserConnector", "Connection exception: " + e2);
            d(e.SUSPENDED);
        }
    }

    public final String c() {
        b7 b7Var = this.d;
        if (b7Var == null) {
            return null;
        }
        return b7Var.a.getPackageName();
    }

    public final void d(e eVar) {
        b7 b7Var = this.d;
        if (b7Var == null) {
            Logs.e("MediaBrowserConnector", "sendNewState mMediaSource is null!");
            return;
        }
        MediaBrowserCompat mediaBrowserCompat = this.e;
        if (mediaBrowserCompat == null) {
            Logs.e("MediaBrowserConnector", "sendNewState mBrowser is null!");
            return;
        }
        d dVar = this.b;
        ((MutableLiveData) ((q6) dVar).a).setValue(new c(b7Var, mediaBrowserCompat, eVar));
    }
}
