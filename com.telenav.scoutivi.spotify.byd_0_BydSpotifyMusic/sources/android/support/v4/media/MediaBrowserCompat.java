package android.support.v4.media;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
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

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserCompat {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final boolean f152b = Log.isLoggable("MediaBrowserCompat", 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f153a;

    private static class CustomActionResultReceiver extends ResultReceiver {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final String f154i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final Bundle f155j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final c f156k;

        CustomActionResultReceiver(String str, Bundle bundle, c cVar, Handler handler) {
            super(handler);
            this.f154i = str;
            this.f155j = bundle;
            this.f156k = cVar;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void b(int i7, Bundle bundle) {
            if (this.f156k == null) {
                return;
            }
            MediaSessionCompat.a(bundle);
            if (i7 == -1) {
                this.f156k.a(this.f154i, this.f155j, bundle);
                return;
            }
            if (i7 == 0) {
                this.f156k.c(this.f154i, this.f155j, bundle);
                return;
            }
            if (i7 == 1) {
                this.f156k.b(this.f154i, this.f155j, bundle);
                return;
            }
            Log.w("MediaBrowserCompat", "Unknown result code: " + i7 + " (extras=" + this.f155j + ", resultData=" + bundle + ")");
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        @Override // android.support.v4.os.ResultReceiver
        protected void b(int i7, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.b(bundle);
            }
            if (i7 != 0 || bundle == null || !bundle.containsKey("media_item")) {
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

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final String f159i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final Bundle f160j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final i f161k;

        SearchResultReceiver(String str, Bundle bundle, i iVar, Handler handler) {
            super(handler);
            this.f159i = str;
            this.f160j = bundle;
            this.f161k = iVar;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void b(int i7, Bundle bundle) {
            if (bundle != null) {
                bundle = MediaSessionCompat.b(bundle);
            }
            if (i7 != 0 || bundle == null || !bundle.containsKey("search_results")) {
                this.f161k.a(this.f159i, this.f160j);
                return;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
            if (parcelableArray == null) {
                this.f161k.a(this.f159i, this.f160j);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaItem) parcelable);
            }
            this.f161k.b(this.f159i, this.f160j, arrayList);
        }
    }

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<h> f162a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WeakReference<Messenger> f163b;

        a(h hVar) {
            this.f162a = new WeakReference<>(hVar);
        }

        void a(Messenger messenger) {
            this.f163b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.f163b;
            if (weakReference == null || weakReference.get() == null || this.f162a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.a(data);
            h hVar = this.f162a.get();
            Messenger messenger = this.f163b.get();
            try {
                int i7 = message.what;
                if (i7 == 1) {
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle);
                    hVar.j(messenger, data.getString("data_media_item_id"), (MediaSessionCompat.Token) data.getParcelable("data_media_session_token"), bundle);
                } else if (i7 == 2) {
                    hVar.e(messenger);
                } else if (i7 != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    Bundle bundle3 = data.getBundle("data_notify_children_changed_options");
                    MediaSessionCompat.a(bundle3);
                    hVar.n(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    hVar.e(messenger);
                }
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaBrowser.ConnectionCallback f164a = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        InterfaceC0005b f165b;

        private class a extends MediaBrowser.ConnectionCallback {
            a() {
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnected() {
                InterfaceC0005b interfaceC0005b = b.this.f165b;
                if (interfaceC0005b != null) {
                    interfaceC0005b.k();
                }
                b.this.a();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionFailed() {
                InterfaceC0005b interfaceC0005b = b.this.f165b;
                if (interfaceC0005b != null) {
                    interfaceC0005b.l();
                }
                b.this.b();
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionSuspended() {
                InterfaceC0005b interfaceC0005b = b.this.f165b;
                if (interfaceC0005b != null) {
                    interfaceC0005b.d();
                }
                b.this.c();
            }
        }

        /* JADX INFO: renamed from: android.support.v4.media.MediaBrowserCompat$b$b, reason: collision with other inner class name */
        interface InterfaceC0005b {
            void d();

            void k();

            void l();
        }

        public void a() {
        }

        public void b() {
        }

        public void c() {
        }

        void d(InterfaceC0005b interfaceC0005b) {
            this.f165b = interfaceC0005b;
        }
    }

    public static abstract class c {
        public void a(String str, Bundle bundle, Bundle bundle2) {
        }

        public void b(String str, Bundle bundle, Bundle bundle2) {
        }

        public void c(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    interface d {
        String a();

        void b(String str, Bundle bundle, l lVar);

        boolean c();

        void f(String str, Bundle bundle, c cVar);

        MediaSessionCompat.Token g();

        void h();

        void i();

        void m(String str, l lVar);

        void o(String str, Bundle bundle, i iVar);
    }

    static class e implements d, h, b.InterfaceC0005b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f167a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final MediaBrowser f168b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected final Bundle f169c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected final a f170d = new a(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final n.a<String, k> f171e = new n.a<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        protected int f172f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        protected j f173g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        protected Messenger f174h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private MediaSessionCompat.Token f175i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private Bundle f176j;

        class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ i f177e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ String f178g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ Bundle f179h;

            a(i iVar, String str, Bundle bundle) {
                this.f177e = iVar;
                this.f178g = str;
                this.f179h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f177e.a(this.f178g, this.f179h);
            }
        }

        class b implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ i f181e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ String f182g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ Bundle f183h;

            b(i iVar, String str, Bundle bundle) {
                this.f181e = iVar;
                this.f182g = str;
                this.f183h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f181e.a(this.f182g, this.f183h);
            }
        }

        class c implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ c f185e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ String f186g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ Bundle f187h;

            c(c cVar, String str, Bundle bundle) {
                this.f185e = cVar;
                this.f186g = str;
                this.f187h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f185e.a(this.f186g, this.f187h, null);
            }
        }

        class d implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ c f189e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ String f190g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ Bundle f191h;

            d(c cVar, String str, Bundle bundle) {
                this.f189e = cVar;
                this.f190g = str;
                this.f191h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f189e.a(this.f190g, this.f191h, null);
            }
        }

        e(Context context, ComponentName componentName, b bVar, Bundle bundle) {
            this.f167a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f169c = bundle2;
            bundle2.putInt("extra_client_version", 1);
            bundle2.putInt("extra_calling_pid", Process.myPid());
            bVar.d(this);
            this.f168b = new MediaBrowser(context, componentName, bVar.f164a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public String a() {
            return this.f168b.getRoot();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void b(String str, Bundle bundle, l lVar) {
            k kVar = this.f171e.get(str);
            if (kVar == null) {
                kVar = new k();
                this.f171e.put(str, kVar);
            }
            lVar.e(kVar);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            kVar.e(bundle2, lVar);
            j jVar = this.f173g;
            if (jVar == null) {
                this.f168b.subscribe(str, lVar.f197a);
                return;
            }
            try {
                jVar.a(str, lVar.f198b, bundle2, this.f174h);
            } catch (RemoteException unused) {
                Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public boolean c() {
            return this.f168b.isConnected();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0005b
        public void d() {
            this.f173g = null;
            this.f174h = null;
            this.f175i = null;
            this.f170d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.h
        public void e(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void f(String str, Bundle bundle, c cVar) {
            if (!c()) {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
            }
            if (this.f173g == null) {
                Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
                if (cVar != null) {
                    this.f170d.post(new c(cVar, str, bundle));
                }
            }
            try {
                this.f173g.e(str, bundle, new CustomActionResultReceiver(str, bundle, cVar, this.f170d), this.f174h);
            } catch (RemoteException e7) {
                Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e7);
                if (cVar != null) {
                    this.f170d.post(new d(cVar, str, bundle));
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public MediaSessionCompat.Token g() {
            if (this.f175i == null) {
                this.f175i = MediaSessionCompat.Token.b(this.f168b.getSessionToken());
            }
            return this.f175i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void h() {
            Messenger messenger;
            j jVar = this.f173g;
            if (jVar != null && (messenger = this.f174h) != null) {
                try {
                    jVar.g(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            this.f168b.disconnect();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void i() {
            this.f168b.connect();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.h
        public void j(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0005b
        public void k() {
            try {
                Bundle extras = this.f168b.getExtras();
                if (extras == null) {
                    return;
                }
                this.f172f = extras.getInt("extra_service_version", 0);
                IBinder iBinderA = w.e.a(extras, "extra_messenger");
                if (iBinderA != null) {
                    this.f173g = new j(iBinderA, this.f169c);
                    Messenger messenger = new Messenger(this.f170d);
                    this.f174h = messenger;
                    this.f170d.a(messenger);
                    try {
                        this.f173g.b(this.f167a, this.f174h);
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                android.support.v4.media.session.b bVarT = b.a.T(w.e.a(extras, "extra_session_binder"));
                if (bVarT != null) {
                    this.f175i = MediaSessionCompat.Token.c(this.f168b.getSessionToken(), bVarT);
                }
            } catch (IllegalStateException e7) {
                Log.e("MediaBrowserCompat", "Unexpected IllegalStateException", e7);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b.InterfaceC0005b
        public void l() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void m(String str, l lVar) {
            k kVar = this.f171e.get(str);
            if (kVar == null) {
                return;
            }
            j jVar = this.f173g;
            if (jVar != null) {
                try {
                    if (lVar == null) {
                        jVar.c(str, null, this.f174h);
                    } else {
                        List<l> listB = kVar.b();
                        List<Bundle> listC = kVar.c();
                        for (int size = listB.size() - 1; size >= 0; size--) {
                            if (listB.get(size) == lVar) {
                                this.f173g.c(str, lVar.f198b, this.f174h);
                                listB.remove(size);
                                listC.remove(size);
                            }
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + str);
                }
            } else if (lVar == null) {
                this.f168b.unsubscribe(str);
            } else {
                List<l> listB2 = kVar.b();
                List<Bundle> listC2 = kVar.c();
                for (int size2 = listB2.size() - 1; size2 >= 0; size2--) {
                    if (listB2.get(size2) == lVar) {
                        listB2.remove(size2);
                        listC2.remove(size2);
                    }
                }
                if (listB2.size() == 0) {
                    this.f168b.unsubscribe(str);
                }
            }
            if (kVar.d() || lVar == null) {
                this.f171e.remove(str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.h
        public void n(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2) {
            if (this.f174h != messenger) {
                return;
            }
            k kVar = this.f171e.get(str);
            if (kVar == null) {
                if (MediaBrowserCompat.f152b) {
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
                    this.f176j = bundle2;
                    lVarA.a(str, list);
                    this.f176j = null;
                    return;
                }
                if (list == null) {
                    lVarA.d(str, bundle);
                    return;
                }
                this.f176j = bundle2;
                lVarA.b(str, list, bundle);
                this.f176j = null;
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.d
        public void o(String str, Bundle bundle, i iVar) {
            if (!c()) {
                throw new IllegalStateException("search() called while not connected");
            }
            if (this.f173g == null) {
                Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
                this.f170d.post(new a(iVar, str, bundle));
                return;
            }
            try {
                this.f173g.d(str, bundle, new SearchResultReceiver(str, bundle, iVar, this.f170d), this.f174h);
            } catch (RemoteException e7) {
                Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e7);
                this.f170d.post(new b(iVar, str, bundle));
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
        public void b(String str, Bundle bundle, l lVar) {
            if (this.f173g != null && this.f172f >= 2) {
                super.b(str, bundle, lVar);
            } else if (bundle == null) {
                this.f168b.subscribe(str, lVar.f197a);
            } else {
                this.f168b.subscribe(str, bundle, lVar.f197a);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.e, android.support.v4.media.MediaBrowserCompat.d
        public void m(String str, l lVar) {
            if (this.f173g != null && this.f172f >= 2) {
                super.m(str, lVar);
            } else if (lVar == null) {
                this.f168b.unsubscribe(str);
            } else {
                this.f168b.unsubscribe(str, lVar.f197a);
            }
        }
    }

    interface h {
        void e(Messenger messenger);

        void j(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void n(Messenger messenger, String str, List<MediaItem> list, Bundle bundle, Bundle bundle2);
    }

    public static abstract class i {
        public abstract void a(String str, Bundle bundle);

        public abstract void b(String str, Bundle bundle, List<MediaItem> list);
    }

    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Messenger f193a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Bundle f194b;

        public j(IBinder iBinder, Bundle bundle) {
            this.f193a = new Messenger(iBinder);
            this.f194b = bundle;
        }

        private void f(int i7, Bundle bundle, Messenger messenger) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = 1;
            messageObtain.setData(bundle);
            messageObtain.replyTo = messenger;
            this.f193a.send(messageObtain);
        }

        void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            w.e.b(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            f(3, bundle2, messenger);
        }

        void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putInt("data_calling_pid", Process.myPid());
            bundle.putBundle("data_root_hints", this.f194b);
            f(6, bundle, messenger);
        }

        void c(String str, IBinder iBinder, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            w.e.b(bundle, "data_callback_token", iBinder);
            f(4, bundle, messenger);
        }

        void d(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_search_query", str);
            bundle2.putBundle("data_search_extras", bundle);
            bundle2.putParcelable("data_result_receiver", resultReceiver);
            f(8, bundle2, messenger);
        }

        void e(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_custom_action", str);
            bundle2.putBundle("data_custom_action_extras", bundle);
            bundle2.putParcelable("data_result_receiver", resultReceiver);
            f(9, bundle2, messenger);
        }

        void g(Messenger messenger) throws RemoteException {
            f(7, null, messenger);
        }
    }

    private static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<l> f195a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<Bundle> f196b = new ArrayList();

        public l a(Bundle bundle) {
            for (int i7 = 0; i7 < this.f196b.size(); i7++) {
                if (v0.a.a(this.f196b.get(i7), bundle)) {
                    return this.f195a.get(i7);
                }
            }
            return null;
        }

        public List<l> b() {
            return this.f195a;
        }

        public List<Bundle> c() {
            return this.f196b;
        }

        public boolean d() {
            return this.f195a.isEmpty();
        }

        public void e(Bundle bundle, l lVar) {
            for (int i7 = 0; i7 < this.f196b.size(); i7++) {
                if (v0.a.a(this.f196b.get(i7), bundle)) {
                    this.f195a.set(i7, lVar);
                    return;
                }
            }
            this.f195a.add(lVar);
            this.f196b.add(bundle);
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        WeakReference<k> f199c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final IBinder f198b = new Binder();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaBrowser.SubscriptionCallback f197a = new b();

        private class a extends MediaBrowser.SubscriptionCallback {
            a() {
            }

            List<MediaItem> a(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i7 = bundle.getInt("android.media.browse.extra.PAGE", -1);
                int i8 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                if (i7 == -1 && i8 == -1) {
                    return list;
                }
                int i9 = i8 * i7;
                int size = i9 + i8;
                if (i7 < 0 || i8 < 1 || i9 >= list.size()) {
                    return Collections.emptyList();
                }
                if (size > list.size()) {
                    size = list.size();
                }
                return list.subList(i9, size);
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
                WeakReference<k> weakReference = l.this.f199c;
                k kVar = weakReference == null ? null : weakReference.get();
                if (kVar == null) {
                    l.this.a(str, MediaItem.c(list));
                    return;
                }
                List<MediaItem> listC = MediaItem.c(list);
                List<l> listB = kVar.b();
                List<Bundle> listC2 = kVar.c();
                for (int i7 = 0; i7 < listB.size(); i7++) {
                    Bundle bundle = listC2.get(i7);
                    if (bundle == null) {
                        l.this.a(str, listC);
                    } else {
                        l.this.b(str, a(listC, bundle), bundle);
                    }
                }
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(String str) {
                l.this.c(str);
            }
        }

        private class b extends a {
            b() {
                super();
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                l.this.b(str, MediaItem.c(list), bundle);
            }

            @Override // android.media.browse.MediaBrowser.SubscriptionCallback
            public void onError(String str, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                l.this.d(str, bundle);
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
            this.f199c = new WeakReference<>(kVar);
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, b bVar, Bundle bundle) {
        this.f153a = new g(context, componentName, bVar, bundle);
    }

    public void a() {
        Log.d("MediaBrowserCompat", "Connecting to a MediaBrowserService.");
        this.f153a.i();
    }

    public void b() {
        this.f153a.h();
    }

    public String c() {
        return this.f153a.a();
    }

    public MediaSessionCompat.Token d() {
        return this.f153a.g();
    }

    public boolean e() {
        return this.f153a.c();
    }

    public void f(String str, Bundle bundle, i iVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        }
        if (iVar == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.f153a.o(str, bundle, iVar);
    }

    public void g(String str, Bundle bundle, c cVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        this.f153a.f(str, bundle, cVar);
    }

    public void h(String str, Bundle bundle, l lVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (lVar == null) {
            throw new IllegalArgumentException("callback is null");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.f153a.b(str, bundle, lVar);
    }

    public void i(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.f153a.m(str, null);
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f157e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final MediaDescriptionCompat f158g;

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
            public MediaItem[] newArray(int i7) {
                return new MediaItem[i7];
            }
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i7) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.k())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.f157e = i7;
            this.f158g = mediaDescriptionCompat;
        }

        public static MediaItem b(Object obj) {
            if (obj == null) {
                return null;
            }
            MediaBrowser.MediaItem mediaItem = (MediaBrowser.MediaItem) obj;
            return new MediaItem(MediaDescriptionCompat.b(mediaItem.getDescription()), mediaItem.getFlags());
        }

        public static List<MediaItem> c(List<?> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(b(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat h() {
            return this.f158g;
        }

        public String j() {
            return this.f158g.k();
        }

        public boolean k() {
            return (this.f157e & 1) != 0;
        }

        public boolean m() {
            return (this.f157e & 2) != 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.f157e + ", mDescription=" + this.f158g + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f157e);
            this.f158g.writeToParcel(parcel, i7);
        }

        MediaItem(Parcel parcel) {
            this.f157e = parcel.readInt();
            this.f158g = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
