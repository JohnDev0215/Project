package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.a;
import android.support.v4.media.b;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.b;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final boolean f53a = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f54b;

    private static class CustomActionResultReceiver extends ResultReceiver {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Bundle f55e;
        private final c f;

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i, Bundle bundle) {
            if (this.f == null) {
                return;
            }
            MediaSessionCompat.a(bundle);
            if (i == -1 || i == 0 || i == 1) {
                throw null;
            }
            Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.f55e + ", resultData=" + bundle + ")");
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            if (i != 0 || bundle == null || !bundle.containsKey("media_item")) {
                throw null;
            }
            Parcelable parcelable = bundle.getParcelable("media_item");
            if (parcelable != null && !(parcelable instanceof MediaItem)) {
                throw null;
            }
            throw null;
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i, Bundle bundle) {
            Parcelable[] parcelableArray;
            MediaSessionCompat.a(bundle);
            if (i != 0 || bundle == null || !bundle.containsKey("search_results") || (parcelableArray = bundle.getParcelableArray("search_results")) == null) {
                throw null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) parcelable);
            }
            throw null;
        }
    }

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<i> f58a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WeakReference<Messenger> f59b;

        a(i iVar) {
            this.f58a = new WeakReference<>(iVar);
        }

        void a(Messenger messenger) {
            this.f59b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.f59b;
            if (weakReference == null || weakReference.get() == null || this.f58a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            i iVar = this.f58a.get();
            Messenger messenger = this.f59b.get();
            try {
                int i = message.what;
                if (i == 1) {
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle);
                    iVar.j(messenger, data.getString("data_media_item_id"), (MediaSessionCompat.Token) data.getParcelable("data_media_session_token"), bundle);
                } else if (i == 2) {
                    iVar.f(messenger);
                } else if (i != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle("data_notify_children_changed_options");
                    MediaSessionCompat.a(bundle3);
                    iVar.k(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    iVar.f(messenger);
                }
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f60a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        a f61b;

        interface a {
            void a();

            void b();

            void c();
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        private class C0002b implements a.InterfaceC0003a {
            C0002b() {
            }

            @Override // android.support.v4.media.a.InterfaceC0003a
            public void a() {
                a aVar = b.this.f61b;
                if (aVar != null) {
                    aVar.a();
                }
                b.this.c();
            }

            @Override // android.support.v4.media.a.InterfaceC0003a
            public void b() {
                a aVar = b.this.f61b;
                if (aVar != null) {
                    aVar.b();
                }
                b.this.a();
            }

            @Override // android.support.v4.media.a.InterfaceC0003a
            public void c() {
                a aVar = b.this.f61b;
                if (aVar != null) {
                    aVar.c();
                }
                b.this.b();
            }
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f60a = android.support.v4.media.a.c(new C0002b());
            } else {
                this.f60a = null;
            }
        }

        public void a() {
            throw null;
        }

        public void b() {
        }

        public void c() {
        }

        void d(a aVar) {
            this.f61b = aVar;
        }
    }

    public static abstract class c {
    }

    interface d {
        String d();

        void e(String str, Bundle bundle, l lVar);

        MediaSessionCompat.Token g();

        void h();

        void i();
    }

    static class e implements d, i, b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f63a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final Object f64b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final Bundle f65c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected final a f66d = new a(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final b.d.a<String, k> f67e = new b.d.a<>();
        protected int f;
        protected j g;
        protected Messenger h;
        private MediaSessionCompat.Token i;
        private Bundle j;

        e(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f63a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f65c = bundle2;
            bundle2.putInt("extra_client_version", 1);
            bVar.d(this);
            this.f64b = android.support.v4.media.a.b(context, componentName, bVar.f60a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void a() {
            this.g = null;
            this.h = null;
            this.i = null;
            this.f66d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void b() {
            Bundle bundleF = android.support.v4.media.a.f(this.f64b);
            if (bundleF == null) {
                return;
            }
            this.f = bundleF.getInt("extra_service_version", 0);
            IBinder iBinderA = androidx.core.app.d.a(bundleF, "extra_messenger");
            if (iBinderA != null) {
                this.g = new j(iBinderA, this.f65c);
                Messenger messenger = new Messenger(this.f66d);
                this.h = messenger;
                this.f66d.a(messenger);
                try {
                    this.g.d(this.f63a, this.h);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
            android.support.v4.media.session.b bVarC0 = b.a.c0(androidx.core.app.d.a(bundleF, "extra_session_binder"));
            if (bVarC0 != null) {
                this.i = MediaSessionCompat.Token.c(android.support.v4.media.a.h(this.f64b), bVarC0);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void c() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public String d() {
            return android.support.v4.media.a.g(this.f64b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void e(String str, Bundle bundle, l lVar) {
            k kVar = this.f67e.get(str);
            if (kVar == null) {
                kVar = new k();
                this.f67e.put(str, kVar);
            }
            lVar.e(kVar);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            kVar.d(bundle2, lVar);
            j jVar = this.g;
            if (jVar == null) {
                android.support.v4.media.a.i(this.f64b, str, lVar.f85a);
                return;
            }
            try {
                jVar.a(str, lVar.f86b, bundle2, this.h);
            } catch (RemoteException unused) {
                Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void f(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token g() {
            if (this.i == null) {
                this.i = MediaSessionCompat.Token.a(android.support.v4.media.a.h(this.f64b));
            }
            return this.i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void h() {
            Messenger messenger;
            j jVar = this.g;
            if (jVar != null && (messenger = this.h) != null) {
                try {
                    jVar.f(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            android.support.v4.media.a.e(this.f64b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void i() {
            android.support.v4.media.a.a(this.f64b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void j(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void k(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.h != messenger) {
                return;
            }
            k kVar = this.f67e.get(str);
            if (kVar == null) {
                if (MediaBrowserCompat.f53a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                    return;
                }
                return;
            }
            l lVarA = kVar.a(bundle);
            if (lVarA != null) {
                if (bundle == null) {
                    if (list == null) {
                        lVarA.c(str);
                        return;
                    }
                    this.j = bundle2;
                    lVarA.a(str, list);
                    this.j = null;
                    return;
                }
                if (list == null) {
                    lVarA.d(str, bundle);
                    return;
                }
                this.j = bundle2;
                lVarA.b(str, list, bundle);
                this.j = null;
            }
        }
    }

    static class f extends e {
        f(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    static class g extends f {
        g(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e, android.support.v4.media.MediaBrowserCompat.d
        public void e(String str, Bundle bundle, l lVar) {
            if (this.g != null && this.f >= 2) {
                super.e(str, bundle, lVar);
            } else if (bundle == null) {
                android.support.v4.media.a.i(this.f64b, str, lVar.f85a);
            } else {
                android.support.v4.media.b.b(this.f64b, str, bundle, lVar.f85a);
            }
        }
    }

    static class h implements d, i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f68a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ComponentName f69b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b f70c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Bundle f71d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final a f72e = new a(this);
        private final b.d.a<String, k> f = new b.d.a<>();
        int g = 1;
        c h;
        j i;
        Messenger j;
        private String k;
        private MediaSessionCompat.Token l;
        private Bundle m;
        private Bundle n;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h hVar = h.this;
                if (hVar.g == 0) {
                    return;
                }
                hVar.g = 2;
                if (MediaBrowserCompat.f53a && hVar.h != null) {
                    throw new RuntimeException("mServiceConnection should be null. Instead it is " + h.this.h);
                }
                if (hVar.i != null) {
                    throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + h.this.i);
                }
                if (hVar.j != null) {
                    throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + h.this.j);
                }
                Intent intent = new Intent("android.media.browse.MediaBrowserService");
                intent.setComponent(h.this.f69b);
                h hVar2 = h.this;
                hVar2.h = hVar2.new c();
                boolean zBindService = false;
                try {
                    h hVar3 = h.this;
                    zBindService = hVar3.f68a.bindService(intent, hVar3.h, 1);
                } catch (Exception unused) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + h.this.f69b);
                }
                if (!zBindService) {
                    h.this.b();
                    h.this.f70c.b();
                }
                if (MediaBrowserCompat.f53a) {
                    Log.d("MediaBrowserCompat", "connect...");
                    h.this.a();
                }
            }
        }

        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h hVar = h.this;
                Messenger messenger = hVar.j;
                if (messenger != null) {
                    try {
                        hVar.i.c(messenger);
                    } catch (RemoteException unused) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f69b);
                    }
                }
                h hVar2 = h.this;
                int i = hVar2.g;
                hVar2.b();
                if (i != 0) {
                    h.this.g = i;
                }
                if (MediaBrowserCompat.f53a) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    h.this.a();
                }
            }
        }

        private class c implements ServiceConnection {

            class a implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ ComponentName f76b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ IBinder f77c;

                a(ComponentName componentName, IBinder iBinder) {
                    this.f76b = componentName;
                    this.f77c = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    boolean z = MediaBrowserCompat.f53a;
                    if (z) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.f76b + " binder=" + this.f77c);
                        h.this.a();
                    }
                    if (c.this.a("onServiceConnected")) {
                        h hVar = h.this;
                        hVar.i = new j(this.f77c, hVar.f71d);
                        h hVar2 = h.this;
                        hVar2.j = new Messenger(hVar2.f72e);
                        h hVar3 = h.this;
                        hVar3.f72e.a(hVar3.j);
                        h.this.g = 2;
                        if (z) {
                            try {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                h.this.a();
                            } catch (RemoteException unused) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f69b);
                                if (MediaBrowserCompat.f53a) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    h.this.a();
                                    return;
                                }
                                return;
                            }
                        }
                        h hVar4 = h.this;
                        hVar4.i.b(hVar4.f68a, hVar4.j);
                    }
                }
            }

            class b implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ ComponentName f79b;

                b(ComponentName componentName) {
                    this.f79b = componentName;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.f53a) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.f79b + " this=" + this + " mServiceConnection=" + h.this.h);
                        h.this.a();
                    }
                    if (c.this.a("onServiceDisconnected")) {
                        h hVar = h.this;
                        hVar.i = null;
                        hVar.j = null;
                        hVar.f72e.a(null);
                        h hVar2 = h.this;
                        hVar2.g = 4;
                        hVar2.f70c.c();
                    }
                }
            }

            c() {
            }

            private void b(Runnable runnable) {
                if (Thread.currentThread() == h.this.f72e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    h.this.f72e.post(runnable);
                }
            }

            boolean a(String str) {
                int i;
                h hVar = h.this;
                if (hVar.h == this && (i = hVar.g) != 0 && i != 1) {
                    return true;
                }
                int i2 = hVar.g;
                if (i2 == 0 || i2 == 1) {
                    return false;
                }
                Log.i("MediaBrowserCompat", str + " for " + h.this.f69b + " with mServiceConnection=" + h.this.h + " this=" + this);
                return false;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                b(new a(componentName, iBinder));
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                b(new b(componentName));
            }
        }

        public h(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            }
            if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (bVar == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            }
            this.f68a = context;
            this.f69b = componentName;
            this.f70c = bVar;
            this.f71d = bundle == null ? null : new Bundle(bundle);
        }

        private static String c(int i) {
            if (i == 0) {
                return "CONNECT_STATE_DISCONNECTING";
            }
            if (i == 1) {
                return "CONNECT_STATE_DISCONNECTED";
            }
            if (i == 2) {
                return "CONNECT_STATE_CONNECTING";
            }
            if (i == 3) {
                return "CONNECT_STATE_CONNECTED";
            }
            if (i == 4) {
                return "CONNECT_STATE_SUSPENDED";
            }
            return "UNKNOWN/" + i;
        }

        private boolean m(Messenger messenger, String str) {
            int i;
            if (this.j == messenger && (i = this.g) != 0 && i != 1) {
                return true;
            }
            int i2 = this.g;
            if (i2 == 0 || i2 == 1) {
                return false;
            }
            Log.i("MediaBrowserCompat", str + " for " + this.f69b + " with mCallbacksMessenger=" + this.j + " this=" + this);
            return false;
        }

        void a() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.f69b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.f70c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.f71d);
            Log.d("MediaBrowserCompat", "  mState=" + c(this.g));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.h);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.i);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.j);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.k);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.l);
        }

        void b() {
            c cVar = this.h;
            if (cVar != null) {
                this.f68a.unbindService(cVar);
            }
            this.g = 1;
            this.h = null;
            this.i = null;
            this.j = null;
            this.f72e.a(null);
            this.k = null;
            this.l = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public String d() {
            if (l()) {
                return this.k;
            }
            throw new IllegalStateException("getRoot() called while not connected(state=" + c(this.g) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void e(String str, Bundle bundle, l lVar) {
            k kVar = this.f.get(str);
            if (kVar == null) {
                kVar = new k();
                this.f.put(str, kVar);
            }
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            kVar.d(bundle2, lVar);
            if (l()) {
                try {
                    this.i.a(str, lVar.f86b, bundle2, this.j);
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + str);
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void f(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.f69b);
            if (m(messenger, "onConnectFailed")) {
                if (this.g == 2) {
                    b();
                    this.f70c.b();
                    return;
                }
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.g) + "... ignoring");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token g() {
            if (l()) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.g + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void h() {
            this.g = 0;
            this.f72e.post(new b());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void i() {
            int i = this.g;
            if (i == 0 || i == 1) {
                this.g = 2;
                this.f72e.post(new a());
            } else {
                throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + c(this.g) + ")");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void j(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (m(messenger, "onConnect")) {
                if (this.g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.g) + "... ignoring");
                    return;
                }
                this.k = str;
                this.l = token;
                this.m = bundle;
                this.g = 3;
                if (MediaBrowserCompat.f53a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    a();
                }
                this.f70c.a();
                try {
                    for (Map.Entry<String, k> entry : this.f.entrySet()) {
                        String key = entry.getKey();
                        k value = entry.getValue();
                        List<l> listB = value.b();
                        List<Bundle> listC = value.c();
                        for (int i = 0; i < listB.size(); i++) {
                            this.i.a(key, listB.get(i).f86b, listC.get(i), this.j);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void k(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (m(messenger, "onLoadChildren")) {
                boolean z = MediaBrowserCompat.f53a;
                if (z) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.f69b + " id=" + str);
                }
                k kVar = this.f.get(str);
                if (kVar == null) {
                    if (z) {
                        Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                        return;
                    }
                    return;
                }
                l lVarA = kVar.a(bundle);
                if (lVarA != null) {
                    if (bundle == null) {
                        if (list == null) {
                            lVarA.c(str);
                            return;
                        }
                        this.n = bundle2;
                        lVarA.a(str, list);
                        this.n = null;
                        return;
                    }
                    if (list == null) {
                        lVarA.d(str, bundle);
                        return;
                    }
                    this.n = bundle2;
                    lVarA.b(str, list, bundle);
                    this.n = null;
                }
            }
        }

        public boolean l() {
            return this.g == 3;
        }
    }

    interface i {
        void f(Messenger messenger);

        void j(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void k(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);
    }

    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Messenger f81a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Bundle f82b;

        public j(IBinder iBinder, Bundle bundle) {
            this.f81a = new Messenger(iBinder);
            this.f82b = bundle;
        }

        private void e(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f81a.send(messageObtain);
        }

        void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            androidx.core.app.d.b(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            e(3, bundle2, messenger);
        }

        void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.f82b);
            e(1, bundle, messenger);
        }

        void c(Messenger messenger) throws RemoteException {
            e(2, null, messenger);
        }

        void d(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.f82b);
            e(6, bundle, messenger);
        }

        void f(Messenger messenger) throws RemoteException {
            e(7, null, messenger);
        }
    }

    private static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<l> f83a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<Bundle> f84b = new ArrayList();

        public l a(Bundle bundle) {
            for (int i = 0; i < this.f84b.size(); i++) {
                if (androidx.media.d.a(this.f84b.get(i), bundle)) {
                    return this.f83a.get(i);
                }
            }
            return null;
        }

        public List<l> b() {
            return this.f83a;
        }

        public List<Bundle> c() {
            return this.f84b;
        }

        public void d(Bundle bundle, l lVar) {
            for (int i = 0; i < this.f84b.size(); i++) {
                if (androidx.media.d.a(this.f84b.get(i), bundle)) {
                    this.f83a.set(i, lVar);
                    return;
                }
            }
            this.f83a.add(lVar);
            this.f84b.add(bundle);
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f85a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final IBinder f86b = new Binder();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        WeakReference<k> f87c;

        private class a implements a.d {
            a() {
            }

            @Override // android.support.v4.media.a.d
            public void c(String str, List<?> list) {
                WeakReference<k> weakReference = l.this.f87c;
                k kVar = weakReference == null ? null : weakReference.get();
                if (kVar == null) {
                    l.this.a(str, MediaItem.c(list));
                    return;
                }
                List<MediaItem> listC = MediaItem.c(list);
                List<l> listB = kVar.b();
                List<Bundle> listC2 = kVar.c();
                for (int i = 0; i < listB.size(); i++) {
                    Bundle bundle = listC2.get(i);
                    if (bundle == null) {
                        l.this.a(str, listC);
                    } else {
                        l.this.b(str, e(listC, bundle), bundle);
                    }
                }
            }

            @Override // android.support.v4.media.a.d
            public void d(String str) {
                l.this.c(str);
            }

            List<MediaItem> e(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i = bundle.getInt("android.media.browse.extra.PAGE", -1);
                int i2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                if (i == -1 && i2 == -1) {
                    return list;
                }
                int i3 = i2 * i;
                int size = i3 + i2;
                if (i < 0 || i2 < 1 || i3 >= list.size()) {
                    return Collections.emptyList();
                }
                if (size > list.size()) {
                    size = list.size();
                }
                return list.subList(i3, size);
            }
        }

        private class b extends a implements b.a {
            b() {
                super();
            }

            @Override // android.support.v4.media.b.a
            public void a(String str, List<?> list, Bundle bundle) {
                l.this.b(str, MediaItem.c(list), bundle);
            }

            @Override // android.support.v4.media.b.a
            public void b(String str, Bundle bundle) {
                l.this.d(str, bundle);
            }
        }

        public l() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                this.f85a = android.support.v4.media.b.a(new b());
            } else if (i >= 21) {
                this.f85a = android.support.v4.media.a.d(new a());
            } else {
                this.f85a = null;
            }
        }

        public void a(String str, List<MediaItem> list) {
        }

        public void b(String str, List<MediaItem> list, Bundle bundle) {
        }

        public void c(String str) {
        }

        public void d(String str, Bundle bundle) {
        }

        void e(k kVar) {
            this.f87c = new WeakReference<>(kVar);
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, b bVar, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f54b = new g(context, componentName, bVar, bundle);
            return;
        }
        if (i2 >= 23) {
            this.f54b = new f(context, componentName, bVar, bundle);
        } else if (i2 >= 21) {
            this.f54b = new e(context, componentName, bVar, bundle);
        } else {
            this.f54b = new h(context, componentName, bVar, bundle);
        }
    }

    public void a() {
        this.f54b.i();
    }

    public void b() {
        this.f54b.h();
    }

    public String c() {
        return this.f54b.d();
    }

    public MediaSessionCompat.Token d() {
        return this.f54b.g();
    }

    public void e(String str, l lVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (lVar == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.f54b.e(str, null, lVar);
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f56b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final MediaDescriptionCompat f57c;

        static class a implements Parcelable.Creator<MediaItem> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.f())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f56b = i;
            this.f57c = mediaDescriptionCompat;
        }

        public static MediaItem a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.a(a.c.a(obj)), a.c.b(obj));
        }

        public static List<MediaItem> c(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.f56b + ", mDescription=" + this.f57c + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f56b);
            this.f57c.writeToParcel(parcel, i);
        }

        MediaItem(Parcel parcel) {
            this.f56b = parcel.readInt();
            this.f57c = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
