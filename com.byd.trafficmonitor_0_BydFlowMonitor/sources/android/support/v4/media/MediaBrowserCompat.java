package android.support.v4.media;

import a.d.a.b.a;
import a.d.a.b.b;
import a.d.a.b.f.b;
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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f62a = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f63b;

    public static class CustomActionResultReceiver extends ResultReceiver {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bundle f64e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final c f65f;

        @Override // android.support.v4.os.ResultReceiver
        public void j(int i2, Bundle bundle) {
            if (this.f65f == null) {
                return;
            }
            MediaSessionCompat.a(bundle);
            if (i2 == -1 || i2 == 0 || i2 == 1) {
                throw null;
            }
            Log.w("MediaBrowserCompat", "Unknown result code: " + i2 + " (extras=" + this.f64e + ", resultData=" + bundle + ")");
        }
    }

    public static class ItemReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        public void j(int i2, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            if (i2 != 0 || bundle == null || !bundle.containsKey("media_item")) {
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
        public void j(int i2, Bundle bundle) {
            Parcelable[] parcelableArray;
            MediaSessionCompat.a(bundle);
            if (i2 != 0 || bundle == null || !bundle.containsKey("search_results") || (parcelableArray = bundle.getParcelableArray("search_results")) == null) {
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
        public final WeakReference<i> f68a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakReference<Messenger> f69b;

        public a(i iVar) {
            this.f68a = new WeakReference<>(iVar);
        }

        public void a(Messenger messenger) {
            this.f69b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.f69b;
            if (weakReference == null || weakReference.get() == null || this.f68a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            i iVar = this.f68a.get();
            Messenger messenger = this.f69b.get();
            try {
                int i2 = message.what;
                if (i2 == 1) {
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle);
                    iVar.h(messenger, data.getString("data_media_item_id"), (MediaSessionCompat.Token) data.getParcelable("data_media_session_token"), bundle);
                } else if (i2 == 2) {
                    iVar.d(messenger);
                } else if (i2 != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle("data_notify_children_changed_options");
                    MediaSessionCompat.a(bundle3);
                    iVar.i(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), bundle2, bundle3);
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
        public final Object f70a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f71b;

        public interface a {
            void a();

            void b();

            void c();
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        public class C0010b implements a.InterfaceC0002a {
            public C0010b() {
            }

            @Override // a.d.a.b.a.InterfaceC0002a
            public void a() {
                a aVar = b.this.f71b;
                if (aVar != null) {
                    aVar.a();
                }
                b.this.c();
            }

            @Override // a.d.a.b.a.InterfaceC0002a
            public void b() {
                a aVar = b.this.f71b;
                if (aVar != null) {
                    aVar.b();
                }
                b.this.a();
            }

            @Override // a.d.a.b.a.InterfaceC0002a
            public void c() {
                a aVar = b.this.f71b;
                if (aVar != null) {
                    aVar.c();
                }
                b.this.b();
            }
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f70a = a.d.a.b.a.c(new C0010b());
            } else {
                this.f70a = null;
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
            this.f71b = aVar;
        }
    }

    public static abstract class c {
    }

    public interface d {
        MediaSessionCompat.Token e();

        void f();

        void g();
    }

    public static class e implements d, i, b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f73a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f74b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f75c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f76d = new a(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final b.e.a<String, k> f77e = new b.e.a<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f78f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public j f79g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Messenger f80h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public MediaSessionCompat.Token f81i;
        public Bundle j;

        public e(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f73a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f75c = bundle2;
            bundle2.putInt("extra_client_version", 1);
            bVar.d(this);
            this.f74b = a.d.a.b.a.b(context, componentName, bVar.f70a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void a() {
            this.f79g = null;
            this.f80h = null;
            this.f81i = null;
            this.f76d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void b() {
            Bundle bundleF = a.d.a.b.a.f(this.f74b);
            if (bundleF == null) {
                return;
            }
            this.f78f = bundleF.getInt("extra_service_version", 0);
            IBinder iBinderA = b.h.d.e.a(bundleF, "extra_messenger");
            if (iBinderA != null) {
                this.f79g = new j(iBinderA, this.f75c);
                Messenger messenger = new Messenger(this.f76d);
                this.f80h = messenger;
                this.f76d.a(messenger);
                try {
                    this.f79g.d(this.f73a, this.f80h);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
            a.d.a.b.f.b bVarK = b.a.K(b.h.d.e.a(bundleF, "extra_session_binder"));
            if (bVarK != null) {
                this.f81i = MediaSessionCompat.Token.k(a.d.a.b.a.g(this.f74b), bVarK);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void c() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void d(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token e() {
            if (this.f81i == null) {
                this.f81i = MediaSessionCompat.Token.j(a.d.a.b.a.g(this.f74b));
            }
            return this.f81i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void f() {
            Messenger messenger;
            j jVar = this.f79g;
            if (jVar != null && (messenger = this.f80h) != null) {
                try {
                    jVar.f(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            a.d.a.b.a.e(this.f74b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void g() {
            a.d.a.b.a.a(this.f74b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void h(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void i(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.f80h != messenger) {
                return;
            }
            k kVar = this.f77e.get(str);
            if (kVar == null) {
                if (MediaBrowserCompat.f62a) {
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
        public final Context f82a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ComponentName f83b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b f84c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Bundle f85d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final a f86e = new a(this);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final b.e.a<String, k> f87f = new b.e.a<>();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f88g = 1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public c f89h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public j f90i;
        public Messenger j;
        public String k;
        public MediaSessionCompat.Token l;
        public Bundle m;
        public Bundle n;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                h hVar = h.this;
                if (hVar.f88g == 0) {
                    return;
                }
                hVar.f88g = 2;
                if (MediaBrowserCompat.f62a && hVar.f89h != null) {
                    throw new RuntimeException("mServiceConnection should be null. Instead it is " + h.this.f89h);
                }
                if (hVar.f90i != null) {
                    throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + h.this.f90i);
                }
                if (hVar.j != null) {
                    throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + h.this.j);
                }
                Intent intent = new Intent("android.media.browse.MediaBrowserService");
                intent.setComponent(h.this.f83b);
                h hVar2 = h.this;
                hVar2.f89h = hVar2.new c();
                boolean zBindService = false;
                try {
                    h hVar3 = h.this;
                    zBindService = hVar3.f82a.bindService(intent, hVar3.f89h, 1);
                } catch (Exception unused) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + h.this.f83b);
                }
                if (!zBindService) {
                    h.this.b();
                    h.this.f84c.b();
                }
                if (MediaBrowserCompat.f62a) {
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
                Messenger messenger = hVar.j;
                if (messenger != null) {
                    try {
                        hVar.f90i.c(messenger);
                    } catch (RemoteException unused) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f83b);
                    }
                }
                h hVar2 = h.this;
                int i2 = hVar2.f88g;
                hVar2.b();
                if (i2 != 0) {
                    h.this.f88g = i2;
                }
                if (MediaBrowserCompat.f62a) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    h.this.a();
                }
            }
        }

        public class c implements ServiceConnection {

            public class a implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ ComponentName f94b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public final /* synthetic */ IBinder f95c;

                public a(ComponentName componentName, IBinder iBinder) {
                    this.f94b = componentName;
                    this.f95c = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    boolean z = MediaBrowserCompat.f62a;
                    if (z) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.f94b + " binder=" + this.f95c);
                        h.this.a();
                    }
                    if (c.this.a("onServiceConnected")) {
                        h hVar = h.this;
                        hVar.f90i = new j(this.f95c, hVar.f85d);
                        h hVar2 = h.this;
                        hVar2.j = new Messenger(hVar2.f86e);
                        h hVar3 = h.this;
                        hVar3.f86e.a(hVar3.j);
                        h.this.f88g = 2;
                        if (z) {
                            try {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                h.this.a();
                            } catch (RemoteException unused) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f83b);
                                if (MediaBrowserCompat.f62a) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    h.this.a();
                                    return;
                                }
                                return;
                            }
                        }
                        h hVar4 = h.this;
                        hVar4.f90i.b(hVar4.f82a, hVar4.j);
                    }
                }
            }

            public class b implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ ComponentName f97b;

                public b(ComponentName componentName) {
                    this.f97b = componentName;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.f62a) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.f97b + " this=" + this + " mServiceConnection=" + h.this.f89h);
                        h.this.a();
                    }
                    if (c.this.a("onServiceDisconnected")) {
                        h hVar = h.this;
                        hVar.f90i = null;
                        hVar.j = null;
                        hVar.f86e.a(null);
                        h hVar2 = h.this;
                        hVar2.f88g = 4;
                        hVar2.f84c.c();
                    }
                }
            }

            public c() {
            }

            public boolean a(String str) {
                int i2;
                h hVar = h.this;
                if (hVar.f89h == this && (i2 = hVar.f88g) != 0 && i2 != 1) {
                    return true;
                }
                int i3 = hVar.f88g;
                if (i3 == 0 || i3 == 1) {
                    return false;
                }
                Log.i("MediaBrowserCompat", str + " for " + h.this.f83b + " with mServiceConnection=" + h.this.f89h + " this=" + this);
                return false;
            }

            public final void b(Runnable runnable) {
                if (Thread.currentThread() == h.this.f86e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    h.this.f86e.post(runnable);
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
            this.f82a = context;
            this.f83b = componentName;
            this.f84c = bVar;
            this.f85d = bundle == null ? null : new Bundle(bundle);
        }

        public static String c(int i2) {
            if (i2 == 0) {
                return "CONNECT_STATE_DISCONNECTING";
            }
            if (i2 == 1) {
                return "CONNECT_STATE_DISCONNECTED";
            }
            if (i2 == 2) {
                return "CONNECT_STATE_CONNECTING";
            }
            if (i2 == 3) {
                return "CONNECT_STATE_CONNECTED";
            }
            if (i2 == 4) {
                return "CONNECT_STATE_SUSPENDED";
            }
            return "UNKNOWN/" + i2;
        }

        public void a() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.f83b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.f84c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.f85d);
            Log.d("MediaBrowserCompat", "  mState=" + c(this.f88g));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.f89h);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.f90i);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.j);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.k);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.l);
        }

        public void b() {
            c cVar = this.f89h;
            if (cVar != null) {
                this.f82a.unbindService(cVar);
            }
            this.f88g = 1;
            this.f89h = null;
            this.f90i = null;
            this.j = null;
            this.f86e.a(null);
            this.k = null;
            this.l = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void d(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.f83b);
            if (k(messenger, "onConnectFailed")) {
                if (this.f88g == 2) {
                    b();
                    this.f84c.b();
                    return;
                }
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.f88g) + "... ignoring");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token e() {
            if (j()) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.f88g + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void f() {
            this.f88g = 0;
            this.f86e.post(new b());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void g() {
            int i2 = this.f88g;
            if (i2 == 0 || i2 == 1) {
                this.f88g = 2;
                this.f86e.post(new a());
            } else {
                throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + c(this.f88g) + ")");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void h(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (k(messenger, "onConnect")) {
                if (this.f88g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.f88g) + "... ignoring");
                    return;
                }
                this.k = str;
                this.l = token;
                this.m = bundle;
                this.f88g = 3;
                if (MediaBrowserCompat.f62a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    a();
                }
                this.f84c.a();
                try {
                    for (Map.Entry<String, k> entry : this.f87f.entrySet()) {
                        String key = entry.getKey();
                        k value = entry.getValue();
                        List<l> listB = value.b();
                        List<Bundle> listC = value.c();
                        for (int i2 = 0; i2 < listB.size(); i2++) {
                            this.f90i.a(key, listB.get(i2).f104b, listC.get(i2), this.j);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void i(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (k(messenger, "onLoadChildren")) {
                boolean z = MediaBrowserCompat.f62a;
                if (z) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.f83b + " id=" + str);
                }
                k kVar = this.f87f.get(str);
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

        public boolean j() {
            return this.f88g == 3;
        }

        public final boolean k(Messenger messenger, String str) {
            int i2;
            if (this.j == messenger && (i2 = this.f88g) != 0 && i2 != 1) {
                return true;
            }
            int i3 = this.f88g;
            if (i3 == 0 || i3 == 1) {
                return false;
            }
            Log.i("MediaBrowserCompat", str + " for " + this.f83b + " with mCallbacksMessenger=" + this.j + " this=" + this);
            return false;
        }
    }

    public interface i {
        void d(Messenger messenger);

        void h(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void i(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);
    }

    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Messenger f99a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Bundle f100b;

        public j(IBinder iBinder, Bundle bundle) {
            this.f99a = new Messenger(iBinder);
            this.f100b = bundle;
        }

        public void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            b.h.d.e.b(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            e(3, bundle2, messenger);
        }

        public void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.f100b);
            e(1, bundle, messenger);
        }

        public void c(Messenger messenger) throws RemoteException {
            e(2, null, messenger);
        }

        public void d(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.f100b);
            e(6, bundle, messenger);
        }

        public final void e(int i2, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f99a.send(messageObtain);
        }

        public void f(Messenger messenger) throws RemoteException {
            e(7, null, messenger);
        }
    }

    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<l> f101a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<Bundle> f102b = new ArrayList();

        public l a(Bundle bundle) {
            for (int i2 = 0; i2 < this.f102b.size(); i2++) {
                if (b.q.d.a(this.f102b.get(i2), bundle)) {
                    return this.f101a.get(i2);
                }
            }
            return null;
        }

        public List<l> b() {
            return this.f101a;
        }

        public List<Bundle> c() {
            return this.f102b;
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f103a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final IBinder f104b = new Binder();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakReference<k> f105c;

        public class a implements a.d {
            public a() {
            }

            @Override // a.d.a.b.a.d
            public void c(String str, List<?> list) {
                WeakReference<k> weakReference = l.this.f105c;
                k kVar = weakReference == null ? null : weakReference.get();
                if (kVar == null) {
                    l.this.a(str, MediaItem.k(list));
                    return;
                }
                List<MediaItem> listK = MediaItem.k(list);
                List<l> listB = kVar.b();
                List<Bundle> listC = kVar.c();
                for (int i2 = 0; i2 < listB.size(); i2++) {
                    Bundle bundle = listC.get(i2);
                    if (bundle == null) {
                        l.this.a(str, listK);
                    } else {
                        l.this.b(str, e(listK, bundle), bundle);
                    }
                }
            }

            @Override // a.d.a.b.a.d
            public void d(String str) {
                l.this.c(str);
            }

            public List<MediaItem> e(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i2 = bundle.getInt("android.media.browse.extra.PAGE", -1);
                int i3 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                if (i2 == -1 && i3 == -1) {
                    return list;
                }
                int i4 = i3 * i2;
                int size = i4 + i3;
                if (i2 < 0 || i3 < 1 || i4 >= list.size()) {
                    return Collections.emptyList();
                }
                if (size > list.size()) {
                    size = list.size();
                }
                return list.subList(i4, size);
            }
        }

        public class b extends a implements b.a {
            public b() {
                super();
            }

            @Override // a.d.a.b.b.a
            public void a(String str, List<?> list, Bundle bundle) {
                l.this.b(str, MediaItem.k(list), bundle);
            }

            @Override // a.d.a.b.b.a
            public void b(String str, Bundle bundle) {
                l.this.d(str, bundle);
            }
        }

        public l() {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 26) {
                this.f103a = a.d.a.b.b.a(new b());
            } else if (i2 >= 21) {
                this.f103a = a.d.a.b.a.d(new a());
            } else {
                this.f103a = null;
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
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f63b = new g(context, componentName, bVar, bundle);
            return;
        }
        if (i2 >= 23) {
            this.f63b = new f(context, componentName, bVar, bundle);
        } else if (i2 >= 21) {
            this.f63b = new e(context, componentName, bVar, bundle);
        } else {
            this.f63b = new h(context, componentName, bVar, bundle);
        }
    }

    public void a() {
        this.f63b.g();
    }

    public void b() {
        this.f63b.f();
    }

    public MediaSessionCompat.Token c() {
        return this.f63b.e();
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f66b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final MediaDescriptionCompat f67c;

        public static class a implements Parcelable.Creator<MediaItem> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public MediaItem[] newArray(int i2) {
                return new MediaItem[i2];
            }
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i2) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.l())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f66b = i2;
            this.f67c = mediaDescriptionCompat;
        }

        public static MediaItem j(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.j(a.c.a(obj)), a.c.b(obj));
        }

        public static List<MediaItem> k(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(j(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.f66b + ", mDescription=" + this.f67c + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f66b);
            this.f67c.writeToParcel(parcel, i2);
        }

        public MediaItem(Parcel parcel) {
            this.f66b = parcel.readInt();
            this.f67c = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
