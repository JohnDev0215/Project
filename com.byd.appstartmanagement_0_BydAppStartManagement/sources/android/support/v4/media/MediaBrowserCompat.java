package android.support.v4.media;

import a.b.a.a.a;
import a.b.a.a.b;
import a.b.a.a.f.b;
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
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserCompatUtils;
import androidx.media.MediaBrowserProtocol;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f57a = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f58b;

    public static class CustomActionResultReceiver extends ResultReceiver {
        public final Bundle e;
        public final c f;

        @Override // android.support.v4.os.ResultReceiver
        public void a(int i, Bundle bundle) {
            if (this.f == null) {
                return;
            }
            MediaSessionCompat.a(bundle);
            if (i == -1 || i == 0 || i == 1) {
                throw null;
            }
            Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.e + ", resultData=" + bundle + ")");
        }
    }

    public static class ItemReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        public void a(int i, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            if (i != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                throw null;
            }
            Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
            if (parcelable != null && !(parcelable instanceof MediaItem)) {
                throw null;
            }
            throw null;
        }
    }

    public static class SearchResultReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        public void a(int i, Bundle bundle) {
            Parcelable[] parcelableArray;
            MediaSessionCompat.a(bundle);
            if (i != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS) || (parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) == null) {
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
        public final WeakReference<i> f61a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakReference<Messenger> f62b;

        public a(i iVar) {
            this.f61a = new WeakReference<>(iVar);
        }

        public void a(Messenger messenger) {
            this.f62b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.f62b;
            if (weakReference == null || weakReference.get() == null || this.f61a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            i iVar = this.f61a.get();
            Messenger messenger = this.f62b.get();
            try {
                int i = message.what;
                if (i == 1) {
                    Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.a(bundle);
                    iVar.f(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (MediaSessionCompat.Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), bundle);
                } else if (i == 2) {
                    iVar.d(messenger);
                } else if (i != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                    MediaSessionCompat.a(bundle3);
                    iVar.g(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), bundle2, bundle3);
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
        public a mConnectionCallbackInternal;
        public final Object mConnectionCallbackObj;

        public interface a {
            void a();

            void b();

            void c();
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        public class C0009b implements a.InterfaceC0001a {
            public C0009b() {
            }

            @Override // a.b.a.a.a.InterfaceC0001a
            public void a() {
                a aVar = b.this.mConnectionCallbackInternal;
                if (aVar != null) {
                    aVar.a();
                }
                b.this.onConnected();
            }

            @Override // a.b.a.a.a.InterfaceC0001a
            public void b() {
                a aVar = b.this.mConnectionCallbackInternal;
                if (aVar != null) {
                    aVar.b();
                }
                b.this.onConnectionFailed();
            }

            @Override // a.b.a.a.a.InterfaceC0001a
            public void c() {
                a aVar = b.this.mConnectionCallbackInternal;
                if (aVar != null) {
                    aVar.c();
                }
                b.this.onConnectionSuspended();
            }
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.mConnectionCallbackObj = a.b.a.a.a.c(new C0009b());
            } else {
                this.mConnectionCallbackObj = null;
            }
        }

        public void onConnected() {
            throw null;
        }

        public void onConnectionFailed() {
            throw null;
        }

        public void onConnectionSuspended() {
            throw null;
        }

        public void setInternalConnectionCallback(a aVar) {
            this.mConnectionCallbackInternal = aVar;
        }
    }

    public static abstract class c {
    }

    public interface d {
        @NonNull
        MediaSessionCompat.Token e();

        void h();

        void i();
    }

    @RequiresApi(21)
    public static class e implements d, i, b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f64a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f65b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f66c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f67d = new a(this);
        public final ArrayMap<String, k> e = new ArrayMap<>();
        public int f;
        public j g;
        public Messenger h;
        public MediaSessionCompat.Token i;
        public Bundle j;

        public e(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f64a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f66c = bundle2;
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            bVar.setInternalConnectionCallback(this);
            this.f65b = a.b.a.a.a.b(context, componentName, bVar.mConnectionCallbackObj, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void a() {
            Bundle bundleF = a.b.a.a.a.f(this.f65b);
            if (bundleF == null) {
                return;
            }
            this.f = bundleF.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
            IBinder binder = BundleCompat.getBinder(bundleF, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
            if (binder != null) {
                this.g = new j(binder, this.f66c);
                Messenger messenger = new Messenger(this.f67d);
                this.h = messenger;
                this.f67d.a(messenger);
                try {
                    this.g.d(this.f64a, this.h);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
            a.b.a.a.f.b bVarB = b.a.B(BundleCompat.getBinder(bundleF, MediaBrowserProtocol.EXTRA_SESSION_BINDER));
            if (bVarB != null) {
                this.i = MediaSessionCompat.Token.b(a.b.a.a.a.g(this.f65b), bVarB);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void b() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.a
        public void c() {
            this.g = null;
            this.h = null;
            this.i = null;
            this.f67d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void d(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        @NonNull
        public MediaSessionCompat.Token e() {
            if (this.i == null) {
                this.i = MediaSessionCompat.Token.a(a.b.a.a.a.g(this.f65b));
            }
            return this.i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void g(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.h != messenger) {
                return;
            }
            k kVar = this.e.get(str);
            if (kVar == null) {
                if (MediaBrowserCompat.f57a) {
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
            a.b.a.a.a.e(this.f65b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void i() {
            a.b.a.a.a.a(this.f65b);
        }
    }

    @RequiresApi(23)
    public static class f extends e {
        public f(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    @RequiresApi(26)
    public static class g extends f {
        public g(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    public static class h implements d, i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f68a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ComponentName f69b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b f70c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Bundle f71d;
        public final a e = new a(this);
        public final ArrayMap<String, k> f = new ArrayMap<>();
        public int g = 1;
        public c h;
        public j i;
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
                if (hVar.g == 0) {
                    return;
                }
                hVar.g = 2;
                if (MediaBrowserCompat.f57a && hVar.h != null) {
                    throw new RuntimeException("mServiceConnection should be null. Instead it is " + h.this.h);
                }
                if (hVar.i != null) {
                    throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + h.this.i);
                }
                if (hVar.j != null) {
                    throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + h.this.j);
                }
                Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
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
                    h.this.f70c.onConnectionFailed();
                }
                if (MediaBrowserCompat.f57a) {
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
                if (MediaBrowserCompat.f57a) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    h.this.a();
                }
            }
        }

        public class c implements ServiceConnection {

            public class a implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ ComponentName f75b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public final /* synthetic */ IBinder f76c;

                public a(ComponentName componentName, IBinder iBinder) {
                    this.f75b = componentName;
                    this.f76c = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    boolean z = MediaBrowserCompat.f57a;
                    if (z) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.f75b + " binder=" + this.f76c);
                        h.this.a();
                    }
                    if (c.this.a("onServiceConnected")) {
                        h hVar = h.this;
                        hVar.i = new j(this.f76c, hVar.f71d);
                        h hVar2 = h.this;
                        hVar2.j = new Messenger(hVar2.e);
                        h hVar3 = h.this;
                        hVar3.e.a(hVar3.j);
                        h.this.g = 2;
                        if (z) {
                            try {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                h.this.a();
                            } catch (RemoteException unused) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + h.this.f69b);
                                if (MediaBrowserCompat.f57a) {
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

            public class b implements Runnable {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ ComponentName f78b;

                public b(ComponentName componentName) {
                    this.f78b = componentName;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.f57a) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.f78b + " this=" + this + " mServiceConnection=" + h.this.h);
                        h.this.a();
                    }
                    if (c.this.a("onServiceDisconnected")) {
                        h hVar = h.this;
                        hVar.i = null;
                        hVar.j = null;
                        hVar.e.a(null);
                        h hVar2 = h.this;
                        hVar2.g = 4;
                        hVar2.f70c.onConnectionSuspended();
                    }
                }
            }

            public c() {
            }

            public boolean a(String str) {
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

            public final void b(Runnable runnable) {
                if (Thread.currentThread() == h.this.e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    h.this.e.post(runnable);
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
            this.f68a = context;
            this.f69b = componentName;
            this.f70c = bVar;
            this.f71d = bundle == null ? null : new Bundle(bundle);
        }

        public static String c(int i) {
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

        public void a() {
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

        public void b() {
            c cVar = this.h;
            if (cVar != null) {
                this.f68a.unbindService(cVar);
            }
            this.g = 1;
            this.h = null;
            this.i = null;
            this.j = null;
            this.e.a(null);
            this.k = null;
            this.l = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void d(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.f69b);
            if (k(messenger, "onConnectFailed")) {
                if (this.g == 2) {
                    b();
                    this.f70c.onConnectionFailed();
                    return;
                }
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.g) + "... ignoring");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        @NonNull
        public MediaSessionCompat.Token e() {
            if (j()) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.g + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (k(messenger, "onConnect")) {
                if (this.g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + c(this.g) + "... ignoring");
                    return;
                }
                this.k = str;
                this.l = token;
                this.m = bundle;
                this.g = 3;
                if (MediaBrowserCompat.f57a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    a();
                }
                this.f70c.onConnected();
                try {
                    for (Map.Entry<String, k> entry : this.f.entrySet()) {
                        String key = entry.getKey();
                        k value = entry.getValue();
                        List<l> listB = value.b();
                        List<Bundle> listC = value.c();
                        for (int i = 0; i < listB.size(); i++) {
                            this.i.a(key, listB.get(i).f85b, listC.get(i), this.j);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void g(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (k(messenger, "onLoadChildren")) {
                boolean z = MediaBrowserCompat.f57a;
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

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void h() {
            this.g = 0;
            this.e.post(new b());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void i() {
            int i = this.g;
            if (i == 0 || i == 1) {
                this.g = 2;
                this.e.post(new a());
            } else {
                throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + c(this.g) + ")");
            }
        }

        public boolean j() {
            return this.g == 3;
        }

        public final boolean k(Messenger messenger, String str) {
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
    }

    public interface i {
        void d(Messenger messenger);

        void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void g(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);
    }

    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Messenger f80a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Bundle f81b;

        public j(IBinder iBinder, Bundle bundle) {
            this.f80a = new Messenger(iBinder);
            this.f81b = bundle;
        }

        public void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle2, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            e(3, bundle2, messenger);
        }

        public void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.f81b);
            e(1, bundle, messenger);
        }

        public void c(Messenger messenger) throws RemoteException {
            e(2, null, messenger);
        }

        public void d(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.f81b);
            e(6, bundle, messenger);
        }

        public final void e(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f80a.send(messageObtain);
        }

        public void f(Messenger messenger) throws RemoteException {
            e(7, null, messenger);
        }
    }

    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<l> f82a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<Bundle> f83b = new ArrayList();

        public l a(Bundle bundle) {
            for (int i = 0; i < this.f83b.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.f83b.get(i), bundle)) {
                    return this.f82a.get(i);
                }
            }
            return null;
        }

        public List<l> b() {
            return this.f82a;
        }

        public List<Bundle> c() {
            return this.f83b;
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f84a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final IBinder f85b = new Binder();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakReference<k> f86c;

        public class a implements a.d {
            public a() {
            }

            @Override // a.b.a.a.a.d
            public void c(@NonNull String str, List<?> list) {
                WeakReference<k> weakReference = l.this.f86c;
                k kVar = weakReference == null ? null : weakReference.get();
                if (kVar == null) {
                    l.this.a(str, MediaItem.b(list));
                    return;
                }
                List<MediaItem> listB = MediaItem.b(list);
                List<l> listB2 = kVar.b();
                List<Bundle> listC = kVar.c();
                for (int i = 0; i < listB2.size(); i++) {
                    Bundle bundle = listC.get(i);
                    if (bundle == null) {
                        l.this.a(str, listB);
                    } else {
                        l.this.b(str, e(listB, bundle), bundle);
                    }
                }
            }

            @Override // a.b.a.a.a.d
            public void d(@NonNull String str) {
                l.this.c(str);
            }

            public List<MediaItem> e(List<MediaItem> list, Bundle bundle) {
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

        public class b extends a implements b.a {
            public b() {
                super();
            }

            @Override // a.b.a.a.b.a
            public void a(@NonNull String str, List<?> list, @NonNull Bundle bundle) {
                l.this.b(str, MediaItem.b(list), bundle);
            }

            @Override // a.b.a.a.b.a
            public void b(@NonNull String str, @NonNull Bundle bundle) {
                l.this.d(str, bundle);
            }
        }

        public l() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                this.f84a = a.b.a.a.b.a(new b());
            } else if (i >= 21) {
                this.f84a = a.b.a.a.a.d(new a());
            } else {
                this.f84a = null;
            }
        }

        public void a(@NonNull String str, @NonNull List<MediaItem> list) {
        }

        public void b(@NonNull String str, @NonNull List<MediaItem> list, @NonNull Bundle bundle) {
        }

        public void c(@NonNull String str) {
        }

        public void d(@NonNull String str, @NonNull Bundle bundle) {
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, b bVar, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f58b = new g(context, componentName, bVar, bundle);
            return;
        }
        if (i2 >= 23) {
            this.f58b = new f(context, componentName, bVar, bundle);
        } else if (i2 >= 21) {
            this.f58b = new e(context, componentName, bVar, bundle);
        } else {
            this.f58b = new h(context, componentName, bVar, bundle);
        }
    }

    public void a() {
        this.f58b.i();
    }

    public void b() {
        this.f58b.h();
    }

    @NonNull
    public MediaSessionCompat.Token c() {
        return this.f58b.e();
    }

    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f59b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final MediaDescriptionCompat f60c;

        public static class a implements Parcelable.Creator<MediaItem> {
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

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.c())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f59b = i;
            this.f60c = mediaDescriptionCompat;
        }

        public static MediaItem a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.a(a.c.a(obj)), a.c.b(obj));
        }

        public static List<MediaItem> b(List<?> list) {
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
            return "MediaItem{mFlags=" + this.f59b + ", mDescription=" + this.f60c + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f59b);
            this.f60c.writeToParcel(parcel, i);
        }

        public MediaItem(Parcel parcel) {
            this.f59b = parcel.readInt();
            this.f60c = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
