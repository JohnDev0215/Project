package androidx.media;

import android.app.Service;
import android.content.Intent;
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
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.media.e;
import androidx.media.f;
import androidx.media.g;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final boolean f1525b = Log.isLoggable("MBServiceCompat", 3);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f1526c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    f f1528e;
    MediaSessionCompat.Token g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final b.d.a<IBinder, f> f1527d = new b.d.a<>();
    final q f = new q();

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
            if (MediaBrowserServiceCompat.this.f1527d.get(this.f.f.asBinder()) != this.f) {
                if (MediaBrowserServiceCompat.f1525b) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f.f1529a + " id=" + this.g);
                    return;
                }
                return;
            }
            if ((a() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.b(list, this.h);
            }
            try {
                this.f.f.b(this.g, list, this.h, this.i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.g + " package=" + this.f.f1529a);
            }
        }
    }

    class b extends m<MediaBrowserCompat.MediaItem> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(MediaBrowserCompat.MediaItem mediaItem) {
            if ((a() & 2) != 0) {
                this.f.c(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f.c(0, bundle);
        }
    }

    class c extends m<List<MediaBrowserCompat.MediaItem>> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if ((a() & 4) != 0 || list == null) {
                this.f.c(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f.c(0, bundle);
        }
    }

    class d extends m<Bundle> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        void c(Bundle bundle) {
            this.f.c(-1, bundle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(Bundle bundle) {
            this.f.c(0, bundle);
        }
    }

    public static final class e {
    }

    private class f implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1529a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1530b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1531c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final androidx.media.h f1532d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bundle f1533e;
        public final o f;
        public final HashMap<String, List<b.g.j.d<IBinder, Bundle>>> g = new HashMap<>();

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                MediaBrowserServiceCompat.this.f1527d.remove(fVar.f.asBinder());
            }
        }

        f(String str, int i, int i2, Bundle bundle, o oVar) {
            this.f1529a = str;
            this.f1530b = i;
            this.f1531c = i2;
            this.f1532d = new androidx.media.h(str, i, i2);
            this.f1533e = bundle;
            this.f = oVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.f.post(new a());
        }
    }

    interface g {
        void a();

        IBinder e(Intent intent);
    }

    class h implements g, e.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<Bundle> f1535a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Object f1536b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Messenger f1537c;

        class a extends m<List<MediaBrowserCompat.MediaItem>> {
            final /* synthetic */ e.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Object obj, e.c cVar) {
                super(obj);
                this.f = cVar;
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

        h() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = androidx.media.e.a(MediaBrowserServiceCompat.this, this);
            this.f1536b = objA;
            androidx.media.e.c(objA);
        }

        @Override // androidx.media.e.d
        public void b(String str, e.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.f(str, new a(str, cVar));
        }

        @Override // androidx.media.e.d
        public e.a c(String str, int i, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.f1537c = new Messenger(MediaBrowserServiceCompat.this.f);
                bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                androidx.core.app.d.b(bundle2, "extra_messenger", this.f1537c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.g;
                if (token != null) {
                    android.support.v4.media.session.b bVarF = token.f();
                    androidx.core.app.d.b(bundle2, "extra_session_binder", bVarF == null ? null : bVarF.asBinder());
                } else {
                    this.f1535a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.f1528e = mediaBrowserServiceCompat.new f(str, -1, i, bundle, null);
            e eVarE = MediaBrowserServiceCompat.this.e(str, i, bundle);
            MediaBrowserServiceCompat.this.f1528e = null;
            if (eVarE == null) {
                return null;
            }
            Objects.requireNonNull(bundle2);
            throw null;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder e(Intent intent) {
            return androidx.media.e.b(this.f1536b, intent);
        }
    }

    class i extends h implements f.b {

        class a extends m<MediaBrowserCompat.MediaItem> {
            final /* synthetic */ e.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Object obj, e.c cVar) {
                super(obj);
                this.f = cVar;
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

        i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = androidx.media.f.a(MediaBrowserServiceCompat.this, this);
            this.f1536b = objA;
            androidx.media.e.c(objA);
        }

        @Override // androidx.media.f.b
        public void d(String str, e.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.h(str, new a(str, cVar));
        }
    }

    class j extends i implements g.c {

        class a extends m<List<MediaBrowserCompat.MediaItem>> {
            final /* synthetic */ g.b f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Object obj, g.b bVar) {
                super(obj);
                this.f = bVar;
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
                this.f.b(arrayList, a());
            }
        }

        j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.i, androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = androidx.media.g.a(MediaBrowserServiceCompat.this, this);
            this.f1536b = objA;
            androidx.media.e.c(objA);
        }

        @Override // androidx.media.g.c
        public void f(String str, g.b bVar, Bundle bundle) {
            MediaBrowserServiceCompat.this.g(str, new a(str, bVar), bundle);
        }
    }

    class k extends j {
        k() {
            super();
        }
    }

    class l implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Messenger f1540a;

        l() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            this.f1540a = new Messenger(MediaBrowserServiceCompat.this.f);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder e(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.f1540a.getBinder();
            }
            return null;
        }
    }

    public static class m<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f1542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1545d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1546e;

        m(Object obj) {
            this.f1542a = obj;
        }

        int a() {
            return this.f1546e;
        }

        boolean b() {
            return this.f1543b || this.f1544c || this.f1545d;
        }

        void c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f1542a);
        }

        void d(T t) {
            throw null;
        }

        public void e(Bundle bundle) {
            if (!this.f1544c && !this.f1545d) {
                this.f1545d = true;
                c(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f1542a);
            }
        }

        public void f(T t) {
            if (!this.f1544c && !this.f1545d) {
                this.f1544c = true;
                d(t);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f1542a);
            }
        }

        void g(int i) {
            this.f1546e = i;
        }
    }

    private class n {

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1548b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1549c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f1550d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int f1551e;
            final /* synthetic */ Bundle f;

            a(o oVar, String str, int i, int i2, Bundle bundle) {
                this.f1548b = oVar;
                this.f1549c = str;
                this.f1550d = i;
                this.f1551e = i2;
                this.f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1548b.asBinder();
                MediaBrowserServiceCompat.this.f1527d.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.f1549c, this.f1550d, this.f1551e, this.f, this.f1548b);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.f1528e = fVar;
                e eVarE = mediaBrowserServiceCompat.e(this.f1549c, this.f1551e, this.f);
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.f1528e = null;
                if (eVarE != null) {
                    try {
                        mediaBrowserServiceCompat2.f1527d.put(iBinderAsBinder, fVar);
                        iBinderAsBinder.linkToDeath(fVar, 0);
                        if (MediaBrowserServiceCompat.this.g != null) {
                            throw null;
                        }
                        return;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.f1549c);
                        MediaBrowserServiceCompat.this.f1527d.remove(iBinderAsBinder);
                        return;
                    }
                }
                Log.i("MBServiceCompat", "No root for client " + this.f1549c + " from service " + a.class.getName());
                try {
                    this.f1548b.a();
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.f1549c);
                }
            }
        }

        class b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1552b;

            b(o oVar) {
                this.f1552b = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVarRemove = MediaBrowserServiceCompat.this.f1527d.remove(this.f1552b.asBinder());
                if (fVarRemove != null) {
                    fVarRemove.f.asBinder().unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        class c implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1554b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1555c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ IBinder f1556d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ Bundle f1557e;

            c(o oVar, String str, IBinder iBinder, Bundle bundle) {
                this.f1554b = oVar;
                this.f1555c = str;
                this.f1556d = iBinder;
                this.f1557e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1527d.get(this.f1554b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.f1555c, fVar, this.f1556d, this.f1557e);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.f1555c);
            }
        }

        class d implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1558b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1559c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ IBinder f1560d;

            d(o oVar, String str, IBinder iBinder) {
                this.f1558b = oVar;
                this.f1559c = str;
                this.f1560d = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1527d.get(this.f1558b.asBinder());
                if (fVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.f1559c);
                    return;
                }
                if (MediaBrowserServiceCompat.this.p(this.f1559c, fVar, this.f1560d)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.f1559c + " which is not subscribed");
            }
        }

        class e implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1562b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1563c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ ResultReceiver f1564d;

            e(o oVar, String str, ResultReceiver resultReceiver) {
                this.f1562b = oVar;
                this.f1563c = str;
                this.f1564d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1527d.get(this.f1562b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.n(this.f1563c, fVar, this.f1564d);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.f1563c);
            }
        }

        class f implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1566b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1567c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f1568d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int f1569e;
            final /* synthetic */ Bundle f;

            f(o oVar, String str, int i, int i2, Bundle bundle) {
                this.f1566b = oVar;
                this.f1567c = str;
                this.f1568d = i;
                this.f1569e = i2;
                this.f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1566b.asBinder();
                MediaBrowserServiceCompat.this.f1527d.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.f1567c, this.f1568d, this.f1569e, this.f, this.f1566b);
                MediaBrowserServiceCompat.this.f1527d.put(iBinderAsBinder, fVar);
                try {
                    iBinderAsBinder.linkToDeath(fVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        class g implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1570b;

            g(o oVar) {
                this.f1570b = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1570b.asBinder();
                f fVarRemove = MediaBrowserServiceCompat.this.f1527d.remove(iBinderAsBinder);
                if (fVarRemove != null) {
                    iBinderAsBinder.unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        class h implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1572b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1573c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ Bundle f1574d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ ResultReceiver f1575e;

            h(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1572b = oVar;
                this.f1573c = str;
                this.f1574d = bundle;
                this.f1575e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1527d.get(this.f1572b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.o(this.f1573c, this.f1574d, fVar, this.f1575e);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.f1573c);
            }
        }

        class i implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ o f1576b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f1577c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ Bundle f1578d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ ResultReceiver f1579e;

            i(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1576b = oVar;
                this.f1577c = str;
                this.f1578d = bundle;
                this.f1579e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1527d.get(this.f1576b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.l(this.f1577c, this.f1578d, fVar, this.f1579e);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.f1577c + ", extras=" + this.f1578d);
            }
        }

        n() {
        }

        public void a(String str, IBinder iBinder, Bundle bundle, o oVar) {
            MediaBrowserServiceCompat.this.f.a(new c(oVar, str, iBinder, bundle));
        }

        public void b(String str, int i2, int i3, Bundle bundle, o oVar) {
            if (MediaBrowserServiceCompat.this.c(str, i3)) {
                MediaBrowserServiceCompat.this.f.a(new a(oVar, str, i2, i3, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void c(o oVar) {
            MediaBrowserServiceCompat.this.f.a(new b(oVar));
        }

        public void d(String str, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f.a(new e(oVar, str, resultReceiver));
        }

        public void e(o oVar, String str, int i2, int i3, Bundle bundle) {
            MediaBrowserServiceCompat.this.f.a(new f(oVar, str, i2, i3, bundle));
        }

        public void f(String str, IBinder iBinder, o oVar) {
            MediaBrowserServiceCompat.this.f.a(new d(oVar, str, iBinder));
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f.a(new h(oVar, str, bundle, resultReceiver));
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f.a(new i(oVar, str, bundle, resultReceiver));
        }

        public void i(o oVar) {
            MediaBrowserServiceCompat.this.f.a(new g(oVar));
        }
    }

    private interface o {
        void a() throws RemoteException;

        IBinder asBinder();

        void b(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;
    }

    private static class p implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Messenger f1580a;

        p(Messenger messenger) {
            this.f1580a = messenger;
        }

        private void c(int i, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f1580a.send(messageObtain);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public void a() throws RemoteException {
            c(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public IBinder asBinder() {
            return this.f1580a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public void b(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle("data_notify_children_changed_options", bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            c(3, bundle3);
        }
    }

    private final class q extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final n f1581a;

        q() {
            this.f1581a = MediaBrowserServiceCompat.this.new n();
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
                    this.f1581a.b(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new p(message.replyTo));
                    break;
                case 2:
                    this.f1581a.c(new p(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    this.f1581a.a(data.getString("data_media_item_id"), androidx.core.app.d.a(data, "data_callback_token"), bundle2, new p(message.replyTo));
                    break;
                case 4:
                    this.f1581a.f(data.getString("data_media_item_id"), androidx.core.app.d.a(data, "data_callback_token"), new p(message.replyTo));
                    break;
                case 5:
                    this.f1581a.d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle3);
                    this.f1581a.e(new p(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.f1581a.i(new p(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.a(bundle4);
                    this.f1581a.g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.a(bundle5);
                    this.f1581a.h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
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
            data.putInt("data_calling_pid", Binder.getCallingPid());
            return super.sendMessageAtTime(message, j);
        }
    }

    void a(String str, f fVar, IBinder iBinder, Bundle bundle) {
        List<b.g.j.d<IBinder, Bundle>> arrayList = fVar.g.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (b.g.j.d<IBinder, Bundle> dVar : arrayList) {
            if (iBinder == dVar.f2292a && androidx.media.d.a(bundle, dVar.f2293b)) {
                return;
            }
        }
        arrayList.add(new b.g.j.d<>(iBinder, bundle));
        fVar.g.put(str, arrayList);
        m(str, fVar, bundle, null);
        this.f1528e = fVar;
        j(str, bundle);
        this.f1528e = null;
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

    public void d(String str, Bundle bundle, m<Bundle> mVar) {
        mVar.e(null);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract e e(String str, int i2, Bundle bundle);

    public abstract void f(String str, m<List<MediaBrowserCompat.MediaItem>> mVar);

    public void g(String str, m<List<MediaBrowserCompat.MediaItem>> mVar, Bundle bundle) {
        mVar.g(1);
        f(str, mVar);
    }

    public void h(String str, m<MediaBrowserCompat.MediaItem> mVar) {
        mVar.g(2);
        mVar.f(null);
    }

    public void i(String str, Bundle bundle, m<List<MediaBrowserCompat.MediaItem>> mVar) {
        mVar.g(4);
        mVar.f(null);
    }

    public void j(String str, Bundle bundle) {
    }

    public void k(String str) {
    }

    void l(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        d dVar = new d(str, resultReceiver);
        this.f1528e = fVar;
        d(str, bundle, dVar);
        this.f1528e = null;
        if (dVar.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    void m(String str, f fVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, fVar, str, bundle, bundle2);
        this.f1528e = fVar;
        if (bundle == null) {
            f(str, aVar);
        } else {
            g(str, aVar, bundle);
        }
        this.f1528e = null;
        if (aVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + fVar.f1529a + " id=" + str);
    }

    void n(String str, f fVar, ResultReceiver resultReceiver) {
        b bVar = new b(str, resultReceiver);
        this.f1528e = fVar;
        h(str, bVar);
        this.f1528e = null;
        if (bVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    void o(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        c cVar = new c(str, resultReceiver);
        this.f1528e = fVar;
        i(str, bundle, cVar);
        this.f1528e = null;
        if (cVar.b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1526c.e(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f1526c = new k();
        } else if (i2 >= 26) {
            this.f1526c = new j();
        } else if (i2 >= 23) {
            this.f1526c = new i();
        } else if (i2 >= 21) {
            this.f1526c = new h();
        } else {
            this.f1526c = new l();
        }
        this.f1526c.a();
    }

    boolean p(String str, f fVar, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                return fVar.g.remove(str) != null;
            }
            List<b.g.j.d<IBinder, Bundle>> list = fVar.g.get(str);
            if (list != null) {
                Iterator<b.g.j.d<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f2292a) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    fVar.g.remove(str);
                }
            }
            return z;
        } finally {
            this.f1528e = fVar;
            k(str);
            this.f1528e = null;
        }
    }
}
