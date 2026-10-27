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
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import h.a;
import h.b;
import i.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f90a = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f91b;

    public static class CustomActionResultReceiver extends ResultReceiver {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final Bundle f92g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final c f93h;

        @Override // android.support.v4.os.ResultReceiver
        public void f(int i8, Bundle bundle) {
            if (this.f93h == null) {
                return;
            }
            MediaSessionCompat.a(bundle);
            if (i8 == -1 || i8 == 0 || i8 == 1) {
                throw null;
            }
            Log.w("MediaBrowserCompat", "Unknown result code: " + i8 + " (extras=" + this.f92g + ", resultData=" + bundle + ")");
        }
    }

    public static class ItemReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        public void f(int i8, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            if (i8 != 0 || bundle == null || !bundle.containsKey("media_item")) {
                throw null;
            }
            Parcelable parcelable = bundle.getParcelable("media_item");
            if (parcelable != null && !(parcelable instanceof MediaItem)) {
                throw null;
            }
            throw null;
        }
    }

    public static class SearchResultReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        public void f(int i8, Bundle bundle) {
            Parcelable[] parcelableArray;
            MediaSessionCompat.a(bundle);
            if (i8 != 0 || bundle == null || !bundle.containsKey("search_results") || (parcelableArray = bundle.getParcelableArray("search_results")) == null) {
                throw null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) parcelable);
            }
            throw null;
        }
    }

    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<i> f96a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakReference<Messenger> f97b;

        public a(i iVar) {
            this.f96a = new WeakReference<>(iVar);
        }

        public void a(Messenger messenger) {
            this.f97b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.f97b;
            if (weakReference == null || weakReference.get() == null || this.f96a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            i iVar = this.f96a.get();
            Messenger messenger = this.f97b.get();
            try {
                int i8 = message.what;
                if (i8 == 1) {
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle);
                    iVar.f(messenger, data.getString("data_media_item_id"), (MediaSessionCompat.Token) data.getParcelable("data_media_session_token"), bundle);
                } else if (i8 == 2) {
                    iVar.d(messenger);
                } else if (i8 != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle("data_notify_children_changed_options");
                    MediaSessionCompat.a(bundle3);
                    iVar.g(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    iVar.d(messenger);
                }
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f98a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f99b;

        public interface a {
            void a();

            void b();

            void c();
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        public class C0003b implements a.InterfaceC0048a {
            public C0003b() {
            }

            @Override // h.a.InterfaceC0048a
            public void a() {
                a aVar = b.this.f99b;
                if (aVar != null) {
                    aVar.a();
                }
                b.this.a();
            }

            @Override // h.a.InterfaceC0048a
            public void b() {
                a aVar = b.this.f99b;
                if (aVar != null) {
                    aVar.b();
                }
                b.this.b();
            }

            @Override // h.a.InterfaceC0048a
            public void c() {
                a aVar = b.this.f99b;
                if (aVar != null) {
                    aVar.c();
                }
                b.this.c();
            }
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f98a = h.a.c(new C0003b());
            } else {
                this.f98a = null;
            }
        }

        public void a() {
            throw null;
        }

        public void b() {
            throw null;
        }

        public void c() {
            throw null;
        }

        public void d(a aVar) {
            this.f99b = aVar;
        }
    }

    public static abstract class c {
    }

    public interface d {
        void disconnect();

        MediaSessionCompat.Token e();

        void h();
    }

    public static class e implements d, i, b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f101a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f102b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f103c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f104d = new a(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final v.a<String, k> f105e = new v.a<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f106f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public j f107g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Messenger f108h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public MediaSessionCompat.Token f109i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Bundle f110j;

        public e(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f101a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f103c = bundle2;
            bundle2.putInt("extra_client_version", 1);
            bVar.d(this);
            this.f102b = h.a.b(context, componentName, bVar.f98a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void a() {
            Bundle bundleF = h.a.f(this.f102b);
            if (bundleF == null) {
                return;
            }
            this.f106f = bundleF.getInt("extra_service_version", 0);
            IBinder iBinderA = d0.c.a(bundleF, "extra_messenger");
            if (iBinderA != null) {
                this.f107g = new j(iBinderA, this.f103c);
                Messenger messenger = new Messenger(this.f104d);
                this.f108h = messenger;
                this.f104d.a(messenger);
                try {
                    this.f107g.d(this.f101a, this.f108h);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
            i.b bVarA0 = b.a.A0(d0.c.a(bundleF, "extra_session_binder"));
            if (bVarA0 != null) {
                this.f109i = MediaSessionCompat.Token.g(h.a.g(this.f102b), bVarA0);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void b() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void c() {
            this.f107g = null;
            this.f108h = null;
            this.f109i = null;
            this.f104d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void d(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void disconnect() {
            Messenger messenger;
            j jVar = this.f107g;
            if (jVar != null && (messenger = this.f108h) != null) {
                try {
                    jVar.f(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            h.a.e(this.f102b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token e() {
            if (this.f109i == null) {
                this.f109i = MediaSessionCompat.Token.f(h.a.g(this.f102b));
            }
            return this.f109i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void g(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.f108h != messenger) {
                return;
            }
            k kVar = this.f105e.get(str);
            if (kVar == null) {
                if (MediaBrowserCompat.f90a) {
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
                    this.f110j = bundle2;
                    lVarA.a(str, list);
                    this.f110j = null;
                    return;
                }
                if (list == null) {
                    lVarA.d(str, bundle);
                    return;
                }
                this.f110j = bundle2;
                lVarA.b(str, list, bundle);
                this.f110j = null;
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void h() {
            h.a.a(this.f102b);
        }
    }

    public static class f extends e {
        public f(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    public static class g extends f {
        public g(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    public static class h implements d, i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f111a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ComponentName f112b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b f113c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Bundle f114d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final a f115e = new a(this);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final v.a<String, k> f116f = new v.a<>();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f117g = 1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public c f118h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public j f119i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Messenger f120j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public String f121k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public MediaSessionCompat.Token f122l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public Bundle f123m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public Bundle f124n;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h hVar = h.this;
                if (hVar.f117g == 0) {
                    return;
                }
                hVar.f117g = 2;
                if (MediaBrowserCompat.f90a && hVar.f118h != null) {
                    throw new RuntimeException("mServiceConnection should be null. Instead it is " + h.this.f118h);
                }
                if (hVar.f119i != null) {
                    throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + h.this.f119i);
                }
                if (hVar.f120j != null) {
                    throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + h.this.f120j);
                }
                Intent intent = new Intent("android.media.browse.MediaBrowserService");
                intent.setComponent(h.this.f112b);
                h hVar2 = h.this;
                hVar2.f118h = hVar2.new c();
                boolean zBindService = false;
                try {
                    h hVar3 = h.this;
                    zBindService = hVar3.f111a.bindService(intent, hVar3.f118h, 1);
                } catch (Exception unused) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + h.this.f112b);
                }
                if (!zBindService) {
                    h.this.b();
                    h.this.f113c.b();
                }
                if (MediaBrowserCompat.f90a) {
                    Log.d("MediaBrowserCompat", "connect...");
                    h.this.a();
                }
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h hVar = h.this;
                Messenger messenger = hVar.f120j;
                if (messenger != null) {
                    try {
                        hVar.f119i.c(messenger);
                    } catch (RemoteException unused) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f112b);
                    }
                }
                h hVar2 = h.this;
                int i8 = hVar2.f117g;
                hVar2.b();
                if (i8 != 0) {
                    h.this.f117g = i8;
                }
                if (MediaBrowserCompat.f90a) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    h.this.a();
                }
            }
        }

        public class c implements ServiceConnection {

            public class a implements Runnable {

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                public final /* synthetic */ ComponentName f128d;

                /* JADX INFO: renamed from: e, reason: collision with root package name */
                public final /* synthetic */ IBinder f129e;

                public a(ComponentName componentName, IBinder iBinder) {
                    this.f128d = componentName;
                    this.f129e = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    boolean z7 = MediaBrowserCompat.f90a;
                    if (z7) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.f128d + " binder=" + this.f129e);
                        h.this.a();
                    }
                    if (c.this.a("onServiceConnected")) {
                        h hVar = h.this;
                        hVar.f119i = new j(this.f129e, hVar.f114d);
                        h hVar2 = h.this;
                        hVar2.f120j = new Messenger(hVar2.f115e);
                        h hVar3 = h.this;
                        hVar3.f115e.a(hVar3.f120j);
                        h.this.f117g = 2;
                        if (z7) {
                            try {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                h.this.a();
                            } catch (RemoteException unused) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f112b);
                                if (MediaBrowserCompat.f90a) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    h.this.a();
                                    return;
                                }
                                return;
                            }
                        }
                        h hVar4 = h.this;
                        hVar4.f119i.b(hVar4.f111a, hVar4.f120j);
                    }
                }
            }

            public class b implements Runnable {

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                public final /* synthetic */ ComponentName f131d;

                public b(ComponentName componentName) {
                    this.f131d = componentName;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.f90a) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.f131d + " this=" + this + " mServiceConnection=" + h.this.f118h);
                        h.this.a();
                    }
                    if (c.this.a("onServiceDisconnected")) {
                        h hVar = h.this;
                        hVar.f119i = null;
                        hVar.f120j = null;
                        hVar.f115e.a(null);
                        h hVar2 = h.this;
                        hVar2.f117g = 4;
                        hVar2.f113c.c();
                    }
                }
            }

            public c() {
            }

            public boolean a(String str) {
                int i8;
                h hVar = h.this;
                if (hVar.f118h == this && (i8 = hVar.f117g) != 0 && i8 != 1) {
                    return true;
                }
                int i9 = hVar.f117g;
                if (i9 == 0 || i9 == 1) {
                    return false;
                }
                Log.i("MediaBrowserCompat", str + " for " + h.this.f112b + " with mServiceConnection=" + h.this.f118h + " this=" + this);
                return false;
            }

            public final void b(Runnable runnable) {
                if (Thread.currentThread() == h.this.f115e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    h.this.f115e.post(runnable);
                }
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
            this.f111a = context;
            this.f112b = componentName;
            this.f113c = bVar;
            this.f114d = bundle == null ? null : new Bundle(bundle);
        }

        public static String c(int i8) {
            if (i8 == 0) {
                return "CONNECT_STATE_DISCONNECTING";
            }
            if (i8 == 1) {
                return "CONNECT_STATE_DISCONNECTED";
            }
            if (i8 == 2) {
                return "CONNECT_STATE_CONNECTING";
            }
            if (i8 == 3) {
                return "CONNECT_STATE_CONNECTED";
            }
            if (i8 == 4) {
                return "CONNECT_STATE_SUSPENDED";
            }
            return "UNKNOWN/" + i8;
        }

        public void a() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.f112b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.f113c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.f114d);
            Log.d("MediaBrowserCompat", "  mState=" + c(this.f117g));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.f118h);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.f119i);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.f120j);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.f121k);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.f122l);
        }

        public void b() {
            c cVar = this.f118h;
            if (cVar != null) {
                this.f111a.unbindService(cVar);
            }
            this.f117g = 1;
            this.f118h = null;
            this.f119i = null;
            this.f120j = null;
            this.f115e.a(null);
            this.f121k = null;
            this.f122l = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void d(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.f112b);
            if (j(messenger, "onConnectFailed")) {
                if (this.f117g == 2) {
                    b();
                    this.f113c.b();
                    return;
                }
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.f117g) + "... ignoring");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void disconnect() {
            this.f117g = 0;
            this.f115e.post(new b());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token e() {
            if (i()) {
                return this.f122l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.f117g + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (j(messenger, "onConnect")) {
                if (this.f117g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.f117g) + "... ignoring");
                    return;
                }
                this.f121k = str;
                this.f122l = token;
                this.f123m = bundle;
                this.f117g = 3;
                if (MediaBrowserCompat.f90a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    a();
                }
                this.f113c.a();
                try {
                    for (Map.Entry<String, k> entry : this.f116f.entrySet()) {
                        String key = entry.getKey();
                        k value = entry.getValue();
                        List<l> listB = value.b();
                        List<Bundle> listC = value.c();
                        for (int i8 = 0; i8 < listB.size(); i8++) {
                            this.f119i.a(key, listB.get(i8).f138b, listC.get(i8), this.f120j);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void g(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (j(messenger, "onLoadChildren")) {
                boolean z7 = MediaBrowserCompat.f90a;
                if (z7) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.f112b + " id=" + str);
                }
                k kVar = this.f116f.get(str);
                if (kVar == null) {
                    if (z7) {
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
                        this.f124n = bundle2;
                        lVarA.a(str, list);
                        this.f124n = null;
                        return;
                    }
                    if (list == null) {
                        lVarA.d(str, bundle);
                        return;
                    }
                    this.f124n = bundle2;
                    lVarA.b(str, list, bundle);
                    this.f124n = null;
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void h() {
            int i8 = this.f117g;
            if (i8 == 0 || i8 == 1) {
                this.f117g = 2;
                this.f115e.post(new a());
            } else {
                throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + c(this.f117g) + ")");
            }
        }

        public boolean i() {
            return this.f117g == 3;
        }

        public final boolean j(Messenger messenger, String str) {
            int i8;
            if (this.f120j == messenger && (i8 = this.f117g) != 0 && i8 != 1) {
                return true;
            }
            int i9 = this.f117g;
            if (i9 == 0 || i9 == 1) {
                return false;
            }
            Log.i("MediaBrowserCompat", str + " for " + this.f112b + " with mCallbacksMessenger=" + this.f120j + " this=" + this);
            return false;
        }
    }

    public interface i {
        void d(Messenger messenger);

        void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void g(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);
    }

    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Messenger f133a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Bundle f134b;

        public j(IBinder iBinder, Bundle bundle) {
            this.f133a = new Messenger(iBinder);
            this.f134b = bundle;
        }

        public void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            d0.c.b(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            e(3, bundle2, messenger);
        }

        public void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.f134b);
            e(1, bundle, messenger);
        }

        public void c(Messenger messenger) throws RemoteException {
            e(2, null, messenger);
        }

        public void d(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.f134b);
            e(6, bundle, messenger);
        }

        public final void e(int i8, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f133a.send(messageObtain);
        }

        public void f(Messenger messenger) throws RemoteException {
            e(7, null, messenger);
        }
    }

    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<l> f135a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<Bundle> f136b = new ArrayList();

        public l a(Bundle bundle) {
            for (int i8 = 0; i8 < this.f136b.size(); i8++) {
                if (b1.d.a(this.f136b.get(i8), bundle)) {
                    return this.f135a.get(i8);
                }
            }
            return null;
        }

        public List<l> b() {
            return this.f135a;
        }

        public List<Bundle> c() {
            return this.f136b;
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final IBinder f138b = new Binder();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakReference<k> f139c;

        public class a implements a.d {
            public a() {
            }

            @Override // h.a.d
            public void c(String str, List<?> list) {
                WeakReference<k> weakReference = l.this.f139c;
                k kVar = weakReference == null ? null : weakReference.get();
                if (kVar == null) {
                    l.this.a(str, MediaItem.g(list));
                    return;
                }
                List<MediaItem> listG = MediaItem.g(list);
                List<l> listB = kVar.b();
                List<Bundle> listC = kVar.c();
                for (int i8 = 0; i8 < listB.size(); i8++) {
                    Bundle bundle = listC.get(i8);
                    if (bundle == null) {
                        l.this.a(str, listG);
                    } else {
                        l.this.b(str, e(listG, bundle), bundle);
                    }
                }
            }

            @Override // h.a.d
            public void d(String str) {
                l.this.c(str);
            }

            public List<MediaItem> e(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i8 = bundle.getInt("android.media.browse.extra.PAGE", -1);
                int i9 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                if (i8 == -1 && i9 == -1) {
                    return list;
                }
                int i10 = i9 * i8;
                int size = i10 + i9;
                if (i8 < 0 || i9 < 1 || i10 >= list.size()) {
                    return Collections.emptyList();
                }
                if (size > list.size()) {
                    size = list.size();
                }
                return list.subList(i10, size);
            }
        }

        public class b extends a implements b.a {
            public b() {
                super();
            }

            @Override // h.b.a
            public void a(String str, List<?> list, Bundle bundle) {
                l.this.b(str, MediaItem.g(list), bundle);
            }

            @Override // h.b.a
            public void b(String str, Bundle bundle) {
                l.this.d(str, bundle);
            }
        }

        public l() {
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 26) {
                this.f137a = h.b.a(new b());
            } else if (i8 >= 21) {
                this.f137a = h.a.d(new a());
            } else {
                this.f137a = null;
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
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, b bVar, Bundle bundle) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 26) {
            this.f91b = new g(context, componentName, bVar, bundle);
            return;
        }
        if (i8 >= 23) {
            this.f91b = new f(context, componentName, bVar, bundle);
        } else if (i8 >= 21) {
            this.f91b = new e(context, componentName, bVar, bundle);
        } else {
            this.f91b = new h(context, componentName, bVar, bundle);
        }
    }

    public void a() {
        this.f91b.h();
    }

    public void b() {
        this.f91b.disconnect();
    }

    public MediaSessionCompat.Token c() {
        return this.f91b.e();
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f94d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final MediaDescriptionCompat f95e;

        public static class a implements Parcelable.Creator<MediaItem> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public MediaItem[] newArray(int i8) {
                return new MediaItem[i8];
            }
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i8) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.h())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f94d = i8;
            this.f95e = mediaDescriptionCompat;
        }

        public static MediaItem f(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.f(a.c.a(obj)), a.c.b(obj));
        }

        public static List<MediaItem> g(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(f(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.f94d + ", mDescription=" + this.f95e + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeInt(this.f94d);
            this.f95e.writeToParcel(parcel, i8);
        }

        public MediaItem(Parcel parcel) {
            this.f94d = parcel.readInt();
            this.f95e = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
