package android.support.v4.media;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.browse.MediaBrowser;
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
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final boolean f5b = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f6a;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String f;
        private final Bundle g;
        private final c h;

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i, Bundle bundle) {
            if (this.h == null) {
                return;
            }
            MediaSessionCompat.a(bundle);
            if (i == -1) {
                this.h.a(this.f, this.g, bundle);
                return;
            }
            if (i == 0) {
                this.h.c(this.f, this.g, bundle);
                return;
            }
            if (i == 1) {
                this.h.b(this.f, this.g, bundle);
                return;
            }
            Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.g + ", resultData=" + bundle + ")");
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class ItemReceiver extends ResultReceiver {
        private final String f;
        private final d g;

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.c(bundle);
            }
            if (i != 0 || bundle == null || !bundle.containsKey("media_item")) {
                this.g.a(this.f);
                return;
            }
            Parcelable parcelable = bundle.getParcelable("media_item");
            if (parcelable == null || (parcelable instanceof MediaItem)) {
                this.g.b((MediaItem) parcelable);
            } else {
                this.g.a(this.f);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class SearchResultReceiver extends ResultReceiver {
        private final String f;
        private final Bundle g;
        private final k h;

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.c(bundle);
            }
            if (i != 0 || bundle == null || !bundle.containsKey("search_results")) {
                this.h.a(this.f, this.g);
                return;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
            if (parcelableArray == null) {
                this.h.a(this.f, this.g);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) parcelable);
            }
            this.h.b(this.f, this.g, arrayList);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<j> f8a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WeakReference<Messenger> f9b;

        a(j jVar) {
            this.f8a = new WeakReference<>(jVar);
        }

        void a(Messenger messenger) {
            this.f9b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            WeakReference<Messenger> weakReference = this.f9b;
            if (weakReference == null || weakReference.get() == null || this.f8a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            j jVar = this.f8a.get();
            Messenger messenger = this.f9b.get();
            try {
                int i = message.what;
                if (i == 1) {
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle);
                    jVar.f(messenger, data.getString("data_media_item_id"), (MediaSessionCompat.Token) data.getParcelable("data_media_session_token"), bundle);
                } else if (i == 2) {
                    jVar.c(messenger);
                } else if (i != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle("data_notify_children_changed_options");
                    MediaSessionCompat.a(bundle3);
                    jVar.i(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    jVar.c(messenger);
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaBrowser.ConnectionCallback f10a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        InterfaceC0001b f11b;

        /* JADX INFO: compiled from: caradapter-bug */
        @RequiresApi(21)
        private class a extends MediaBrowser.ConnectionCallback {
            a() {
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnected() {
                InterfaceC0001b interfaceC0001b = b.this.f11b;
                if (interfaceC0001b != null) {
                    interfaceC0001b.g();
                }
                b.this.a();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionFailed() {
                InterfaceC0001b interfaceC0001b = b.this.f11b;
                if (interfaceC0001b != null) {
                    interfaceC0001b.h();
                }
                b.this.b();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionSuspended() {
                InterfaceC0001b interfaceC0001b = b.this.f11b;
                if (interfaceC0001b != null) {
                    interfaceC0001b.b();
                }
                b.this.c();
            }
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        interface InterfaceC0001b {
            void b();

            void g();

            void h();
        }

        public b() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f10a = new a();
            } else {
                this.f10a = null;
            }
        }

        public abstract void a();

        public abstract void b();

        public abstract void c();

        void d(InterfaceC0001b interfaceC0001b) {
            this.f11b = interfaceC0001b;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class c {
        public abstract void a(String str, Bundle bundle, Bundle bundle2);

        public abstract void b(String str, Bundle bundle, Bundle bundle2);

        public abstract void c(String str, Bundle bundle, Bundle bundle2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class d {
        public abstract void a(@NonNull String str);

        public abstract void b(MediaItem mediaItem);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface e {
        @NonNull
        MediaSessionCompat.Token a();

        void d();

        void e();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(21)
    static class f implements e, j, b.InterfaceC0001b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f13a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final MediaBrowser f14b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final Bundle f15c;
        protected final a d = new a(this);
        private final b.a.a<String, m> e = new b.a.a<>();
        protected l f;
        protected Messenger g;
        private MediaSessionCompat.Token h;

        f(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f13a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f15c = bundle2;
            bundle2.putInt("extra_client_version", 1);
            this.f15c.putInt("extra_calling_pid", Process.myPid());
            bVar.d(this);
            this.f14b = new MediaBrowser(context, componentName, bVar.f10a, this.f15c);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e
        @NonNull
        public MediaSessionCompat.Token a() {
            if (this.h == null) {
                this.h = MediaSessionCompat.Token.a(this.f14b.getSessionToken());
            }
            return this.h;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0001b
        public void b() {
            this.f = null;
            this.g = null;
            this.h = null;
            this.d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.j
        public void c(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e
        public void d() {
            Messenger messenger;
            l lVar = this.f;
            if (lVar != null && (messenger = this.g) != null) {
                try {
                    lVar.f(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            this.f14b.disconnect();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e
        public void e() {
            this.f14b.connect();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.j
        public void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0001b
        public void g() {
            try {
                Bundle extras = this.f14b.getExtras();
                if (extras == null) {
                    return;
                }
                extras.getInt("extra_service_version", 0);
                IBinder iBinderA = androidx.core.app.e.a(extras, "extra_messenger");
                if (iBinderA != null) {
                    this.f = new l(iBinderA, this.f15c);
                    Messenger messenger = new Messenger(this.d);
                    this.g = messenger;
                    this.d.a(messenger);
                    try {
                        this.f.d(this.f13a, this.g);
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                IMediaSession iMediaSessionN0 = IMediaSession.Stub.n0(androidx.core.app.e.a(extras, "extra_session_binder"));
                if (iMediaSessionN0 != null) {
                    this.h = MediaSessionCompat.Token.b(this.f14b.getSessionToken(), iMediaSessionN0);
                }
            } catch (IllegalStateException e) {
                Log.e("MediaBrowserCompat", "Unexpected IllegalStateException", e);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0001b
        public void h() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.j
        public void i(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2) {
            if (this.g != messenger) {
                return;
            }
            m mVar = this.e.get(str);
            if (mVar == null) {
                if (MediaBrowserCompat.f5b) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                    return;
                }
                return;
            }
            n nVarA = mVar.a(bundle);
            if (nVarA != null) {
                if (bundle == null) {
                    if (list == null) {
                        nVarA.c(str);
                        return;
                    } else {
                        nVarA.a(str, list);
                        return;
                    }
                }
                if (list == null) {
                    nVarA.d(str, bundle);
                } else {
                    nVarA.b(str, list, bundle);
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(23)
    static class g extends f {
        g(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(26)
    static class h extends g {
        h(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            super(context, componentName, bVar, bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class i implements e, j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f16a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ComponentName f17b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b f18c;
        final Bundle d;
        final a e = new a(this);
        private final b.a.a<String, m> f = new b.a.a<>();
        int g = 1;
        c h;
        l i;
        Messenger j;
        private String k;
        private MediaSessionCompat.Token l;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                i iVar = i.this;
                if (iVar.g == 0) {
                    return;
                }
                iVar.g = 2;
                if (MediaBrowserCompat.f5b && iVar.h != null) {
                    throw new RuntimeException("mServiceConnection should be null. Instead it is " + i.this.h);
                }
                i iVar2 = i.this;
                if (iVar2.i != null) {
                    throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + i.this.i);
                }
                if (iVar2.j != null) {
                    throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + i.this.j);
                }
                Intent intent = new Intent("android.media.browse.MediaBrowserService");
                intent.setComponent(i.this.f17b);
                i iVar3 = i.this;
                iVar3.h = iVar3.new c();
                boolean zBindService = false;
                try {
                    zBindService = i.this.f16a.bindService(intent, i.this.h, 1);
                } catch (Exception unused) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + i.this.f17b);
                }
                if (!zBindService) {
                    i.this.g();
                    i.this.f18c.b();
                }
                if (MediaBrowserCompat.f5b) {
                    Log.d("MediaBrowserCompat", "connect...");
                    i.this.b();
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                i iVar = i.this;
                Messenger messenger = iVar.j;
                if (messenger != null) {
                    try {
                        iVar.i.c(messenger);
                    } catch (RemoteException unused) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + i.this.f17b);
                    }
                }
                i iVar2 = i.this;
                int i = iVar2.g;
                iVar2.g();
                if (i != 0) {
                    i.this.g = i;
                }
                if (MediaBrowserCompat.f5b) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    i.this.b();
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class c implements ServiceConnection {

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements Runnable {

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ ComponentName f22c;
                final /* synthetic */ IBinder d;

                a(ComponentName componentName, IBinder iBinder) {
                    this.f22c = componentName;
                    this.d = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.f5b) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.f22c + " binder=" + this.d);
                        i.this.b();
                    }
                    if (c.this.a("onServiceConnected")) {
                        i iVar = i.this;
                        iVar.i = new l(this.d, iVar.d);
                        i.this.j = new Messenger(i.this.e);
                        i iVar2 = i.this;
                        iVar2.e.a(iVar2.j);
                        i.this.g = 2;
                        try {
                            if (MediaBrowserCompat.f5b) {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                i.this.b();
                            }
                            i.this.i.b(i.this.f16a, i.this.j);
                        } catch (RemoteException unused) {
                            Log.w("MediaBrowserCompat", "RemoteException during connect for " + i.this.f17b);
                            if (MediaBrowserCompat.f5b) {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                i.this.b();
                            }
                        }
                    }
                }
            }

            /* JADX INFO: compiled from: caradapter-bug */
            class b implements Runnable {

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ ComponentName f23c;

                b(ComponentName componentName) {
                    this.f23c = componentName;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.f5b) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.f23c + " this=" + this + " mServiceConnection=" + i.this.h);
                        i.this.b();
                    }
                    if (c.this.a("onServiceDisconnected")) {
                        i iVar = i.this;
                        iVar.i = null;
                        iVar.j = null;
                        iVar.e.a(null);
                        i iVar2 = i.this;
                        iVar2.g = 4;
                        iVar2.f18c.c();
                    }
                }
            }

            c() {
            }

            private void b(Runnable runnable) {
                if (Thread.currentThread() == i.this.e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    i.this.e.post(runnable);
                }
            }

            boolean a(String str) {
                int i;
                i iVar = i.this;
                if (iVar.h == this && (i = iVar.g) != 0 && i != 1) {
                    return true;
                }
                int i2 = i.this.g;
                if (i2 == 0 || i2 == 1) {
                    return false;
                }
                Log.i("MediaBrowserCompat", str + " for " + i.this.f17b + " with mServiceConnection=" + i.this.h + " this=" + this);
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

        public i(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            }
            if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (bVar == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            }
            this.f16a = context;
            this.f17b = componentName;
            this.f18c = bVar;
            this.d = bundle == null ? null : new Bundle(bundle);
        }

        private static String h(int i) {
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

        private boolean k(Messenger messenger, String str) {
            int i;
            if (this.j == messenger && (i = this.g) != 0 && i != 1) {
                return true;
            }
            int i2 = this.g;
            if (i2 == 0 || i2 == 1) {
                return false;
            }
            Log.i("MediaBrowserCompat", str + " for " + this.f17b + " with mCallbacksMessenger=" + this.j + " this=" + this);
            return false;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e
        @NonNull
        public MediaSessionCompat.Token a() {
            if (j()) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.g + ")");
        }

        void b() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.f17b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.f18c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.d);
            Log.d("MediaBrowserCompat", "  mState=" + h(this.g));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.h);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.i);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.j);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.k);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.l);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.j
        public void c(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.f17b);
            if (k(messenger, "onConnectFailed")) {
                if (this.g == 2) {
                    g();
                    this.f18c.b();
                    return;
                }
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + h(this.g) + "... ignoring");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e
        public void d() {
            this.g = 0;
            this.e.post(new b());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e
        public void e() {
            int i = this.g;
            if (i == 0 || i == 1) {
                this.g = 2;
                this.e.post(new a());
            } else {
                throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + h(this.g) + ")");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.j
        public void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (k(messenger, "onConnect")) {
                if (this.g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + h(this.g) + "... ignoring");
                    return;
                }
                this.k = str;
                this.l = token;
                this.g = 3;
                if (MediaBrowserCompat.f5b) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    b();
                }
                this.f18c.a();
                try {
                    for (Map.Entry<String, m> entry : this.f.entrySet()) {
                        String key = entry.getKey();
                        m value = entry.getValue();
                        List<n> listB = value.b();
                        List<Bundle> listC = value.c();
                        for (int i = 0; i < listB.size(); i++) {
                            this.i.a(key, listB.get(i).f28a, listC.get(i), this.j);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        void g() {
            c cVar = this.h;
            if (cVar != null) {
                this.f16a.unbindService(cVar);
            }
            this.g = 1;
            this.h = null;
            this.i = null;
            this.j = null;
            this.e.a(null);
            this.k = null;
            this.l = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.j
        public void i(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2) {
            if (k(messenger, "onLoadChildren")) {
                if (MediaBrowserCompat.f5b) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.f17b + " id=" + str);
                }
                m mVar = this.f.get(str);
                if (mVar == null) {
                    if (MediaBrowserCompat.f5b) {
                        Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                        return;
                    }
                    return;
                }
                n nVarA = mVar.a(bundle);
                if (nVarA != null) {
                    if (bundle == null) {
                        if (list == null) {
                            nVarA.c(str);
                            return;
                        } else {
                            nVarA.a(str, list);
                            return;
                        }
                    }
                    if (list == null) {
                        nVarA.d(str, bundle);
                    } else {
                        nVarA.b(str, list, bundle);
                    }
                }
            }
        }

        public boolean j() {
            return this.g == 3;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface j {
        void c(Messenger messenger);

        void f(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void i(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class k {
        public abstract void a(@NonNull String str, Bundle bundle);

        public abstract void b(@NonNull String str, Bundle bundle, @NonNull List<MediaItem> list);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Messenger f24a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Bundle f25b;

        public l(IBinder iBinder, Bundle bundle) {
            this.f24a = new Messenger(iBinder);
            this.f25b = bundle;
        }

        private void e(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f24a.send(messageObtain);
        }

        void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            androidx.core.app.e.b(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            e(3, bundle2, messenger);
        }

        void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putInt("data_calling_pid", Process.myPid());
            bundle.putBundle("data_root_hints", this.f25b);
            e(1, bundle, messenger);
        }

        void c(Messenger messenger) throws RemoteException {
            e(2, null, messenger);
        }

        void d(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putInt("data_calling_pid", Process.myPid());
            bundle.putBundle("data_root_hints", this.f25b);
            e(6, bundle, messenger);
        }

        void f(Messenger messenger) throws RemoteException {
            e(7, null, messenger);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<n> f26a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<Bundle> f27b = new ArrayList();

        public n a(Bundle bundle) {
            for (int i = 0; i < this.f27b.size(); i++) {
                if (androidx.media.a.a(this.f27b.get(i), bundle)) {
                    return this.f26a.get(i);
                }
            }
            return null;
        }

        public List<n> b() {
            return this.f26a;
        }

        public List<Bundle> c() {
            return this.f27b;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final IBinder f28a = new Binder();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        WeakReference<m> f29b;

        /* JADX INFO: compiled from: caradapter-bug */
        @RequiresApi(21)
        private class a extends MediaBrowser.SubscriptionCallback {
            a() {
            }

            List<MediaItem> a(List<MediaItem> list, Bundle bundle) {
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

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(@NonNull String str, List<MediaBrowser.MediaItem> list) {
                WeakReference<m> weakReference = n.this.f29b;
                m mVar = weakReference == null ? null : weakReference.get();
                if (mVar == null) {
                    n.this.a(str, MediaItem.b(list));
                    return;
                }
                List<MediaItem> listB = MediaItem.b(list);
                List<n> listB2 = mVar.b();
                List<Bundle> listC = mVar.c();
                for (int i = 0; i < listB2.size(); i++) {
                    Bundle bundle = listC.get(i);
                    if (bundle == null) {
                        n.this.a(str, listB);
                    } else {
                        n.this.b(str, a(listB, bundle), bundle);
                    }
                }
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(@NonNull String str) {
                n.this.c(str);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        @RequiresApi(26)
        private class b extends a {
            b() {
                super();
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaBrowser.MediaItem> list, @NonNull Bundle bundle) {
                MediaSessionCompat.a(bundle);
                n.this.b(str, MediaItem.b(list), bundle);
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(@NonNull String str, @NonNull Bundle bundle) {
                MediaSessionCompat.a(bundle);
                n.this.d(str, bundle);
            }
        }

        public n() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                new b();
            } else if (i >= 21) {
                new a();
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
            this.f6a = new h(context, componentName, bVar, bundle);
            return;
        }
        if (i2 >= 23) {
            this.f6a = new g(context, componentName, bVar, bundle);
        } else if (i2 >= 21) {
            this.f6a = new f(context, componentName, bVar, bundle);
        } else {
            this.f6a = new i(context, componentName, bVar, bundle);
        }
    }

    public void a() {
        Log.d("MediaBrowserCompat", "Connecting to a MediaBrowserService.");
        this.f6a.e();
    }

    public void b() {
        this.f6a.d();
    }

    @NonNull
    public MediaSessionCompat.Token c() {
        return this.f6a.a();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"BanParcelableUsage"})
    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f7c;
        private final MediaDescriptionCompat d;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Parcelable.Creator<MediaItem> {
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

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.e())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f7c = i;
            this.d = mediaDescriptionCompat;
        }

        public static MediaItem a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            MediaBrowser.MediaItem mediaItem = (MediaBrowser.MediaItem) obj;
            return new MediaItem(MediaDescriptionCompat.a(mediaItem.getDescription()), mediaItem.getFlags());
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

        @NonNull
        public String toString() {
            return "MediaItem{mFlags=" + this.f7c + ", mDescription=" + this.d + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f7c);
            this.d.writeToParcel(parcel, i);
        }

        MediaItem(Parcel parcel) {
            this.f7c = parcel.readInt();
            this.d = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
