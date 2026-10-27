package androidx.media;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
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
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaBrowserServiceCompat extends Service {
    static final boolean j = Log.isLoggable("MBServiceCompat", 3);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f808c;
    f g;
    MediaSessionCompat.Token i;
    final f d = new f("android.media.session.MediaController", -1, -1, null, null);
    final ArrayList<f> e = new ArrayList<>();
    final b.a.a<IBinder, f> f = new b.a.a<>();
    final r h = new r();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends m<List<MediaBrowserCompat.MediaItem>> {
        final /* synthetic */ f f;
        final /* synthetic */ String g;
        final /* synthetic */ Bundle h;
        final /* synthetic */ Bundle i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Object obj, f fVar, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f = fVar;
            this.g = str;
            this.h = bundle;
            this.i = bundle2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.f.get(this.f.d.asBinder()) != this.f) {
                if (MediaBrowserServiceCompat.j) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f.f811a + " id=" + this.g);
                    return;
                }
                return;
            }
            if ((a() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.b(list, this.h);
            }
            try {
                this.f.d.c(this.g, list, this.h, this.i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.g + " package=" + this.f.f811a);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends m<MediaBrowserCompat.MediaItem> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(MediaBrowserCompat.MediaItem mediaItem) {
            if ((a() & 2) != 0) {
                this.f.b(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f.b(0, bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c extends m<List<MediaBrowserCompat.MediaItem>> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if ((a() & 4) != 0 || list == null) {
                this.f.b(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f.b(0, bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d extends m<Bundle> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        void c(Bundle bundle) {
            this.f.b(-1, bundle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(Bundle bundle) {
            this.f.b(0, bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f809a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Bundle f810b;

        public e(@NonNull String str, @Nullable Bundle bundle) {
            if (str == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead");
            }
            this.f809a = str;
            this.f810b = bundle;
        }

        public Bundle c() {
            return this.f810b;
        }

        public String d() {
            return this.f809a;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class f implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f811a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f812b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f813c;
        public final p d;
        public final HashMap<String, List<androidx.core.d.b<IBinder, Bundle>>> e = new HashMap<>();
        public e f;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                MediaBrowserServiceCompat.this.f.remove(fVar.d.asBinder());
            }
        }

        f(String str, int i, int i2, Bundle bundle, p pVar) {
            this.f811a = str;
            this.f812b = i;
            this.f813c = i2;
            new androidx.media.b(str, i, i2);
            this.d = pVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.h.post(new a());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface g {
        void a();

        IBinder b(Intent intent);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(21)
    class h implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<Bundle> f815a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        MediaBrowserService f816b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Messenger f817c;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends m<List<MediaBrowserCompat.MediaItem>> {
            final /* synthetic */ n f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(h hVar, Object obj, n nVar) {
                super(obj);
                this.f = nVar;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.m
            /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.b(arrayList);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b extends MediaBrowserService {
            b(Context context) {
                attachBaseContext(context);
            }

            @Override // android.service.media.MediaBrowserService
            @SuppressLint({"SyntheticAccessor"})
            public MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                e eVarC = h.this.c(str, i, bundle == null ? null : new Bundle(bundle));
                if (eVarC == null) {
                    return null;
                }
                return new MediaBrowserService.BrowserRoot(eVarC.f809a, eVarC.f810b);
            }

            @Override // android.service.media.MediaBrowserService
            public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
                h.this.d(str, new n<>(result));
            }
        }

        h() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            b bVar = new b(MediaBrowserServiceCompat.this);
            this.f816b = bVar;
            bVar.onCreate();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder b(Intent intent) {
            return this.f816b.onBind(intent);
        }

        public e c(String str, int i, Bundle bundle) {
            int i2;
            Bundle bundleC;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                i2 = -1;
                bundleC = null;
            } else {
                bundle.remove("extra_client_version");
                this.f817c = new Messenger(MediaBrowserServiceCompat.this.h);
                bundleC = new Bundle();
                bundleC.putInt("extra_service_version", 2);
                androidx.core.app.e.b(bundleC, "extra_messenger", this.f817c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.i;
                if (token != null) {
                    IMediaSession iMediaSessionC = token.c();
                    androidx.core.app.e.b(bundleC, "extra_session_binder", iMediaSessionC == null ? null : iMediaSessionC.asBinder());
                } else {
                    this.f815a.add(bundleC);
                }
                int i3 = bundle.getInt("extra_calling_pid", -1);
                bundle.remove("extra_calling_pid");
                i2 = i3;
            }
            f fVar = MediaBrowserServiceCompat.this.new f(str, i2, i, bundle, null);
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.g = fVar;
            e eVarE = mediaBrowserServiceCompat.e(str, i, bundle);
            MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat2.g = null;
            if (eVarE == null) {
                return null;
            }
            if (this.f817c != null) {
                mediaBrowserServiceCompat2.e.add(fVar);
            }
            if (bundleC == null) {
                bundleC = eVarE.c();
            } else if (eVarE.c() != null) {
                bundleC.putAll(eVarE.c());
            }
            return new e(eVarE.d(), bundleC);
        }

        public void d(String str, n<List<Parcel>> nVar) {
            a aVar = new a(this, str, nVar);
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.g = mediaBrowserServiceCompat.d;
            mediaBrowserServiceCompat.f(str, aVar);
            MediaBrowserServiceCompat.this.g = null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(23)
    class i extends h {

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends m<MediaBrowserCompat.MediaItem> {
            final /* synthetic */ n f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(i iVar, Object obj, n nVar) {
                super(obj);
                this.f = nVar;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.m
            /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem == null) {
                    this.f.b(null);
                    return;
                }
                Parcel parcelObtain = Parcel.obtain();
                mediaItem.writeToParcel(parcelObtain, 0);
                this.f.b(parcelObtain);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b extends h.b {
            b(Context context) {
                super(context);
            }

            @Override // android.service.media.MediaBrowserService
            public void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
                i.this.e(str, new n<>(result));
            }
        }

        i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            b bVar = new b(MediaBrowserServiceCompat.this);
            this.f816b = bVar;
            bVar.onCreate();
        }

        public void e(String str, n<Parcel> nVar) {
            a aVar = new a(this, str, nVar);
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.g = mediaBrowserServiceCompat.d;
            mediaBrowserServiceCompat.h(str, aVar);
            MediaBrowserServiceCompat.this.g = null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(26)
    class j extends i {

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends m<List<MediaBrowserCompat.MediaItem>> {
            final /* synthetic */ n f;
            final /* synthetic */ Bundle g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Object obj, n nVar, Bundle bundle) {
                super(obj);
                this.f = nVar;
                this.g = bundle;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.m
            /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(List<MediaBrowserCompat.MediaItem> list) {
                if (list == null) {
                    this.f.b(null);
                    return;
                }
                if ((a() & 1) != 0) {
                    list = MediaBrowserServiceCompat.this.b(list, this.g);
                }
                ArrayList arrayList = new ArrayList();
                for (MediaBrowserCompat.MediaItem mediaItem : list) {
                    Parcel parcelObtain = Parcel.obtain();
                    mediaItem.writeToParcel(parcelObtain, 0);
                    arrayList.add(parcelObtain);
                }
                this.f.b(arrayList);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b extends i.b {
            b(Context context) {
                super(context);
            }

            @Override // android.service.media.MediaBrowserService
            public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                j jVar = j.this;
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.g = mediaBrowserServiceCompat.d;
                jVar.f(str, new n<>(result), bundle);
                MediaBrowserServiceCompat.this.g = null;
            }
        }

        j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.i, androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            b bVar = new b(MediaBrowserServiceCompat.this);
            this.f816b = bVar;
            bVar.onCreate();
        }

        public void f(String str, n<List<Parcel>> nVar, Bundle bundle) {
            a aVar = new a(str, nVar, bundle);
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.g = mediaBrowserServiceCompat.d;
            mediaBrowserServiceCompat.g(str, aVar, bundle);
            MediaBrowserServiceCompat.this.g = null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(28)
    class k extends j {
        k(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            super();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class l implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Messenger f819a;

        l() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            this.f819a = new Messenger(MediaBrowserServiceCompat.this.h);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder b(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.f819a.getBinder();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class m<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f821a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f822b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f823c;
        private boolean d;
        private int e;

        m(Object obj) {
            this.f821a = obj;
        }

        int a() {
            return this.e;
        }

        boolean b() {
            return this.f822b || this.f823c || this.d;
        }

        void c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f821a);
        }

        abstract void d(T t);

        public void e(Bundle bundle) {
            if (!this.f823c && !this.d) {
                this.d = true;
                c(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f821a);
            }
        }

        public void f(T t) {
            if (!this.f823c && !this.d) {
                this.f823c = true;
                d(t);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f821a);
            }
        }

        void g(int i) {
            this.e = i;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(21)
    static class n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        MediaBrowserService.Result f824a;

        n(MediaBrowserService.Result result) {
            this.f824a = result;
        }

        List<MediaBrowser.MediaItem> a(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add((MediaBrowser.MediaItem) MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(T t) {
            if (t instanceof List) {
                this.f824a.sendResult(a((List) t));
                return;
            }
            if (!(t instanceof Parcel)) {
                this.f824a.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) t;
            parcel.setDataPosition(0);
            this.f824a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class o {

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f826c;
            final /* synthetic */ String d;
            final /* synthetic */ int e;
            final /* synthetic */ int f;
            final /* synthetic */ Bundle g;

            a(p pVar, String str, int i, int i2, Bundle bundle) {
                this.f826c = pVar;
                this.d = str;
                this.e = i;
                this.f = i2;
                this.g = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f826c.asBinder();
                MediaBrowserServiceCompat.this.f.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.d, this.e, this.f, this.g, this.f826c);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.g = fVar;
                e eVarE = mediaBrowserServiceCompat.e(this.d, this.f, this.g);
                fVar.f = eVarE;
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.g = null;
                if (eVarE != null) {
                    try {
                        mediaBrowserServiceCompat2.f.put(iBinderAsBinder, fVar);
                        iBinderAsBinder.linkToDeath(fVar, 0);
                        if (MediaBrowserServiceCompat.this.i != null) {
                            this.f826c.b(fVar.f.d(), MediaBrowserServiceCompat.this.i, fVar.f.c());
                            return;
                        }
                        return;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.d);
                        MediaBrowserServiceCompat.this.f.remove(iBinderAsBinder);
                        return;
                    }
                }
                Log.i("MBServiceCompat", "No root for client " + this.d + " from service " + a.class.getName());
                try {
                    this.f826c.a();
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.d);
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f827c;

            b(p pVar) {
                this.f827c = pVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVarRemove = MediaBrowserServiceCompat.this.f.remove(this.f827c.asBinder());
                if (fVarRemove != null) {
                    fVarRemove.d.asBinder().unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class c implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f828c;
            final /* synthetic */ String d;
            final /* synthetic */ IBinder e;
            final /* synthetic */ Bundle f;

            c(p pVar, String str, IBinder iBinder, Bundle bundle) {
                this.f828c = pVar;
                this.d = str;
                this.e = iBinder;
                this.f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f.get(this.f828c.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.d, fVar, this.e, this.f);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.d);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class d implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f829c;
            final /* synthetic */ String d;
            final /* synthetic */ IBinder e;

            d(p pVar, String str, IBinder iBinder) {
                this.f829c = pVar;
                this.d = str;
                this.e = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f.get(this.f829c.asBinder());
                if (fVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.d);
                    return;
                }
                if (MediaBrowserServiceCompat.this.p(this.d, fVar, this.e)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.d + " which is not subscribed");
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class e implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f830c;
            final /* synthetic */ String d;
            final /* synthetic */ ResultReceiver e;

            e(p pVar, String str, ResultReceiver resultReceiver) {
                this.f830c = pVar;
                this.d = str;
                this.e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f.get(this.f830c.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.n(this.d, fVar, this.e);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.d);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class f implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f831c;
            final /* synthetic */ int d;
            final /* synthetic */ String e;
            final /* synthetic */ int f;
            final /* synthetic */ Bundle g;

            f(p pVar, int i, String str, int i2, Bundle bundle) {
                this.f831c = pVar;
                this.d = i;
                this.e = str;
                this.f = i2;
                this.g = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f831c.asBinder();
                MediaBrowserServiceCompat.this.f.remove(iBinderAsBinder);
                Iterator<f> it = MediaBrowserServiceCompat.this.e.iterator();
                f fVar = null;
                while (it.hasNext()) {
                    f next = it.next();
                    if (next.f813c == this.d) {
                        if (TextUtils.isEmpty(this.e) || this.f <= 0) {
                            fVar = MediaBrowserServiceCompat.this.new f(next.f811a, next.f812b, next.f813c, this.g, this.f831c);
                        }
                        it.remove();
                    }
                }
                if (fVar == null) {
                    fVar = MediaBrowserServiceCompat.this.new f(this.e, this.f, this.d, this.g, this.f831c);
                }
                MediaBrowserServiceCompat.this.f.put(iBinderAsBinder, fVar);
                try {
                    iBinderAsBinder.linkToDeath(fVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class g implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f832c;

            g(p pVar) {
                this.f832c = pVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f832c.asBinder();
                f fVarRemove = MediaBrowserServiceCompat.this.f.remove(iBinderAsBinder);
                if (fVarRemove != null) {
                    iBinderAsBinder.unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class h implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f833c;
            final /* synthetic */ String d;
            final /* synthetic */ Bundle e;
            final /* synthetic */ ResultReceiver f;

            h(p pVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f833c = pVar;
                this.d = str;
                this.e = bundle;
                this.f = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f.get(this.f833c.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.o(this.d, this.e, fVar, this.f);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.d);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class i implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f834c;
            final /* synthetic */ String d;
            final /* synthetic */ Bundle e;
            final /* synthetic */ ResultReceiver f;

            i(p pVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f834c = pVar;
                this.d = str;
                this.e = bundle;
                this.f = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f.get(this.f834c.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.l(this.d, this.e, fVar, this.f);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.d + ", extras=" + this.e);
            }
        }

        o() {
        }

        public void a(String str, IBinder iBinder, Bundle bundle, p pVar) {
            MediaBrowserServiceCompat.this.h.a(new c(pVar, str, iBinder, bundle));
        }

        public void b(String str, int i2, int i3, Bundle bundle, p pVar) {
            if (MediaBrowserServiceCompat.this.c(str, i3)) {
                MediaBrowserServiceCompat.this.h.a(new a(pVar, str, i2, i3, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void c(p pVar) {
            MediaBrowserServiceCompat.this.h.a(new b(pVar));
        }

        public void d(String str, ResultReceiver resultReceiver, p pVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.h.a(new e(pVar, str, resultReceiver));
        }

        public void e(p pVar, String str, int i2, int i3, Bundle bundle) {
            MediaBrowserServiceCompat.this.h.a(new f(pVar, i3, str, i2, bundle));
        }

        public void f(String str, IBinder iBinder, p pVar) {
            MediaBrowserServiceCompat.this.h.a(new d(pVar, str, iBinder));
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, p pVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.h.a(new h(pVar, str, bundle, resultReceiver));
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, p pVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.h.a(new i(pVar, str, bundle, resultReceiver));
        }

        public void i(p pVar) {
            MediaBrowserServiceCompat.this.h.a(new g(pVar));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private interface p {
        void a();

        IBinder asBinder();

        void b(String str, MediaSessionCompat.Token token, Bundle bundle);

        void c(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class q implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Messenger f835a;

        q(Messenger messenger) {
            this.f835a = messenger;
        }

        private void d(int i, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f835a.send(messageObtain);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.p
        public void a() throws RemoteException {
            d(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.p
        public IBinder asBinder() {
            return this.f835a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.p
        public void b(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 2);
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putParcelable("data_media_session_token", token);
            bundle2.putBundle("data_root_hints", bundle);
            d(1, bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.p
        public void c(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle("data_notify_children_changed_options", bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            d(3, bundle3);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class r extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final o f836a;

        r() {
            this.f836a = MediaBrowserServiceCompat.this.new o();
        }

        public void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle);
                    this.f836a.b(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new q(message.replyTo));
                    break;
                case 2:
                    this.f836a.c(new q(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    this.f836a.a(data.getString("data_media_item_id"), androidx.core.app.e.a(data, "data_callback_token"), bundle2, new q(message.replyTo));
                    break;
                case 4:
                    this.f836a.f(data.getString("data_media_item_id"), androidx.core.app.e.a(data, "data_callback_token"), new q(message.replyTo));
                    break;
                case 5:
                    this.f836a.d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new q(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle3);
                    this.f836a.e(new q(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.f836a.i(new q(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.a(bundle4);
                    this.f836a.g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new q(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.a(bundle5);
                    this.f836a.h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new q(message.replyTo));
                    break;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    break;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            int callingPid = Binder.getCallingPid();
            if (callingPid > 0) {
                data.putInt("data_calling_pid", callingPid);
            } else if (!data.containsKey("data_calling_pid")) {
                data.putInt("data_calling_pid", -1);
            }
            return super.sendMessageAtTime(message, j);
        }
    }

    void a(String str, f fVar, IBinder iBinder, Bundle bundle) {
        List<androidx.core.d.b<IBinder, Bundle>> arrayList = fVar.e.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (androidx.core.d.b<IBinder, Bundle> bVar : arrayList) {
            if (iBinder == bVar.f475a && androidx.media.a.a(bundle, bVar.f476b)) {
                return;
            }
        }
        arrayList.add(new androidx.core.d.b<>(iBinder, bundle));
        fVar.e.put(str, arrayList);
        m(str, fVar, bundle, null);
        j(str, bundle);
    }

    List<MediaBrowserCompat.MediaItem> b(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
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

    boolean c(String str, int i2) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i2)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void d(@NonNull String str, Bundle bundle, @NonNull m<Bundle> mVar) {
        mVar.e(null);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Nullable
    public abstract e e(@NonNull String str, int i2, @Nullable Bundle bundle);

    public abstract void f(@NonNull String str, @NonNull m<List<MediaBrowserCompat.MediaItem>> mVar);

    public void g(@NonNull String str, @NonNull m<List<MediaBrowserCompat.MediaItem>> mVar, @NonNull Bundle bundle) {
        mVar.g(1);
        f(str, mVar);
    }

    public void h(String str, @NonNull m<MediaBrowserCompat.MediaItem> mVar) {
        mVar.g(2);
        mVar.f(null);
    }

    public void i(@NonNull String str, Bundle bundle, @NonNull m<List<MediaBrowserCompat.MediaItem>> mVar) {
        mVar.g(4);
        mVar.f(null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void j(String str, Bundle bundle) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void k(String str) {
    }

    void l(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        d dVar = new d(this, str, resultReceiver);
        d(str, bundle, dVar);
        if (dVar.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    void m(String str, f fVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, fVar, str, bundle, bundle2);
        if (bundle == null) {
            f(str, aVar);
        } else {
            g(str, aVar, bundle);
        }
        if (aVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + fVar.f811a + " id=" + str);
    }

    void n(String str, f fVar, ResultReceiver resultReceiver) {
        b bVar = new b(this, str, resultReceiver);
        h(str, bVar);
        if (bVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    void o(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        c cVar = new c(this, str, resultReceiver);
        i(str, bundle, cVar);
        if (cVar.b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f808c.b(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f808c = new k(this);
        } else if (i2 >= 26) {
            this.f808c = new j();
        } else if (i2 >= 23) {
            this.f808c = new i();
        } else if (i2 >= 21) {
            this.f808c = new h();
        } else {
            this.f808c = new l();
        }
        this.f808c.a();
    }

    boolean p(String str, f fVar, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                return fVar.e.remove(str) != null;
            }
            List<androidx.core.d.b<IBinder, Bundle>> list = fVar.e.get(str);
            if (list != null) {
                Iterator<androidx.core.d.b<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f475a) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    fVar.e.remove(str);
                }
            }
            return z;
        } finally {
            k(str);
        }
    }
}
