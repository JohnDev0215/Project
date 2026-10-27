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
import b1.e;
import b1.f;
import b1.g;
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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1466d = Log.isLoggable("MBServiceCompat", 3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f1467e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public f f1469g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public MediaSessionCompat.Token f1471i;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final v.a<IBinder, f> f1468f = new v.a<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final q f1470h = new q();

    public class a extends m<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ f f1472f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f1473g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Bundle f1474h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Bundle f1475i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Object obj, f fVar, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f1472f = fVar;
            this.f1473g = str;
            this.f1474h = bundle;
            this.f1475i = bundle2;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.f1468f.get(this.f1472f.f1488f.asBinder()) != this.f1472f) {
                if (MediaBrowserServiceCompat.f1466d) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f1472f.f1483a + " id=" + this.f1473g);
                    return;
                }
                return;
            }
            if ((a() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.b(list, this.f1474h);
            }
            try {
                this.f1472f.f1488f.b(this.f1473g, list, this.f1474h, this.f1475i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.f1473g + " package=" + this.f1472f.f1483a);
            }
        }
    }

    public class b extends m<MediaBrowserCompat.MediaItem> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ResultReceiver f1477f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f1477f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(MediaBrowserCompat.MediaItem mediaItem) {
            if ((a() & 2) != 0) {
                this.f1477f.g(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f1477f.g(0, bundle);
        }
    }

    public class c extends m<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ResultReceiver f1479f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f1479f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if ((a() & 4) != 0 || list == null) {
                this.f1479f.g(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f1479f.g(0, bundle);
        }
    }

    public class d extends m<Bundle> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ResultReceiver f1481f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f1481f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void c(Bundle bundle) {
            this.f1481f.g(-1, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(Bundle bundle) {
            this.f1481f.g(0, bundle);
        }
    }

    public static final class e {
    }

    public class f implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1483a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1484b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1485c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b1.h f1486d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bundle f1487e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final o f1488f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final HashMap<String, List<n0.d<IBinder, Bundle>>> f1489g = new HashMap<>();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                MediaBrowserServiceCompat.this.f1468f.remove(fVar.f1488f.asBinder());
            }
        }

        public f(String str, int i8, int i9, Bundle bundle, o oVar) {
            this.f1483a = str;
            this.f1484b = i8;
            this.f1485c = i9;
            this.f1486d = new b1.h(str, i8, i9);
            this.f1487e = bundle;
            this.f1488f = oVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.f1470h.post(new a());
        }
    }

    public interface g {
        void a();

        IBinder d(Intent intent);
    }

    public class h implements g, e.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<Bundle> f1492a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f1493b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Messenger f1494c;

        public class a extends m<List<MediaBrowserCompat.MediaItem>> {

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ e.c f1496f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, e.c cVar) {
                super(obj);
                this.f1496f = cVar;
            }

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
                this.f1496f.b(arrayList);
            }
        }

        public h() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = b1.e.a(MediaBrowserServiceCompat.this, this);
            this.f1493b = objA;
            b1.e.c(objA);
        }

        @Override // b1.e.d
        public void b(String str, e.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.f(str, new a(str, cVar));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder d(Intent intent) {
            return b1.e.b(this.f1493b, intent);
        }

        @Override // b1.e.d
        public e.a f(String str, int i8, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.f1494c = new Messenger(MediaBrowserServiceCompat.this.f1470h);
                bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                d0.c.b(bundle2, "extra_messenger", this.f1494c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.f1471i;
                if (token != null) {
                    i.b bVarH = token.h();
                    d0.c.b(bundle2, "extra_session_binder", bVarH == null ? null : bVarH.asBinder());
                } else {
                    this.f1492a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.f1469g = mediaBrowserServiceCompat.new f(str, -1, i8, bundle, null);
            e eVarE = MediaBrowserServiceCompat.this.e(str, i8, bundle);
            MediaBrowserServiceCompat.this.f1469g = null;
            if (eVarE == null) {
                return null;
            }
            Objects.requireNonNull(bundle2);
            throw null;
        }
    }

    public class i extends h implements f.b {

        public class a extends m<MediaBrowserCompat.MediaItem> {

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ e.c f1499f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, e.c cVar) {
                super(obj);
                this.f1499f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.m
            /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem == null) {
                    this.f1499f.b(null);
                    return;
                }
                Parcel parcelObtain = Parcel.obtain();
                mediaItem.writeToParcel(parcelObtain, 0);
                this.f1499f.b(parcelObtain);
            }
        }

        public i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = b1.f.a(MediaBrowserServiceCompat.this, this);
            this.f1493b = objA;
            b1.e.c(objA);
        }

        @Override // b1.f.b
        public void c(String str, e.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.h(str, new a(str, cVar));
        }
    }

    public class j extends i implements g.c {

        public class a extends m<List<MediaBrowserCompat.MediaItem>> {

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ g.b f1502f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, g.b bVar) {
                super(obj);
                this.f1502f = bVar;
            }

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
                this.f1502f.b(arrayList, a());
            }
        }

        public j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.i, androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = b1.g.a(MediaBrowserServiceCompat.this, this);
            this.f1493b = objA;
            b1.e.c(objA);
        }

        @Override // b1.g.c
        public void e(String str, g.b bVar, Bundle bundle) {
            MediaBrowserServiceCompat.this.g(str, new a(str, bVar), bundle);
        }
    }

    public class k extends j {
        public k() {
            super();
        }
    }

    public class l implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Messenger f1505a;

        public l() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            this.f1505a = new Messenger(MediaBrowserServiceCompat.this.f1470h);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder d(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.f1505a.getBinder();
            }
            return null;
        }
    }

    public static class m<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1507a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1508b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1509c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1510d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1511e;

        public m(Object obj) {
            this.f1507a = obj;
        }

        public int a() {
            return this.f1511e;
        }

        public boolean b() {
            return this.f1508b || this.f1509c || this.f1510d;
        }

        public void c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f1507a);
        }

        public void d(T t7) {
            throw null;
        }

        public void e(Bundle bundle) {
            if (!this.f1509c && !this.f1510d) {
                this.f1510d = true;
                c(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f1507a);
            }
        }

        public void f(T t7) {
            if (!this.f1509c && !this.f1510d) {
                this.f1509c = true;
                d(t7);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f1507a);
            }
        }

        public void g(int i8) {
            this.f1511e = i8;
        }
    }

    public class n {

        public class a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1513d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1514e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ int f1515f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public final /* synthetic */ int f1516g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            public final /* synthetic */ Bundle f1517h;

            public a(o oVar, String str, int i8, int i9, Bundle bundle) {
                this.f1513d = oVar;
                this.f1514e = str;
                this.f1515f = i8;
                this.f1516g = i9;
                this.f1517h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1513d.asBinder();
                MediaBrowserServiceCompat.this.f1468f.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.f1514e, this.f1515f, this.f1516g, this.f1517h, this.f1513d);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.f1469g = fVar;
                e eVarE = mediaBrowserServiceCompat.e(this.f1514e, this.f1516g, this.f1517h);
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.f1469g = null;
                if (eVarE != null) {
                    try {
                        mediaBrowserServiceCompat2.f1468f.put(iBinderAsBinder, fVar);
                        iBinderAsBinder.linkToDeath(fVar, 0);
                        if (MediaBrowserServiceCompat.this.f1471i != null) {
                            throw null;
                        }
                        return;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.f1514e);
                        MediaBrowserServiceCompat.this.f1468f.remove(iBinderAsBinder);
                        return;
                    }
                }
                Log.i("MBServiceCompat", "No root for client " + this.f1514e + " from service " + a.class.getName());
                try {
                    this.f1513d.a();
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.f1514e);
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1519d;

            public b(o oVar) {
                this.f1519d = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVarRemove = MediaBrowserServiceCompat.this.f1468f.remove(this.f1519d.asBinder());
                if (fVarRemove != null) {
                    fVarRemove.f1488f.asBinder().unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1521d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1522e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ IBinder f1523f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Bundle f1524g;

            public c(o oVar, String str, IBinder iBinder, Bundle bundle) {
                this.f1521d = oVar;
                this.f1522e = str;
                this.f1523f = iBinder;
                this.f1524g = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1468f.get(this.f1521d.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.f1522e, fVar, this.f1523f, this.f1524g);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.f1522e);
            }
        }

        public class d implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1526d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1527e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ IBinder f1528f;

            public d(o oVar, String str, IBinder iBinder) {
                this.f1526d = oVar;
                this.f1527e = str;
                this.f1528f = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1468f.get(this.f1526d.asBinder());
                if (fVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.f1527e);
                    return;
                }
                if (MediaBrowserServiceCompat.this.p(this.f1527e, fVar, this.f1528f)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.f1527e + " which is not subscribed");
            }
        }

        public class e implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1530d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1531e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ResultReceiver f1532f;

            public e(o oVar, String str, ResultReceiver resultReceiver) {
                this.f1530d = oVar;
                this.f1531e = str;
                this.f1532f = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1468f.get(this.f1530d.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.n(this.f1531e, fVar, this.f1532f);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.f1531e);
            }
        }

        public class f implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1534d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1535e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ int f1536f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public final /* synthetic */ int f1537g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            public final /* synthetic */ Bundle f1538h;

            public f(o oVar, String str, int i8, int i9, Bundle bundle) {
                this.f1534d = oVar;
                this.f1535e = str;
                this.f1536f = i8;
                this.f1537g = i9;
                this.f1538h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1534d.asBinder();
                MediaBrowserServiceCompat.this.f1468f.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.f1535e, this.f1536f, this.f1537g, this.f1538h, this.f1534d);
                MediaBrowserServiceCompat.this.f1468f.put(iBinderAsBinder, fVar);
                try {
                    iBinderAsBinder.linkToDeath(fVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        public class g implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1540d;

            public g(o oVar) {
                this.f1540d = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1540d.asBinder();
                f fVarRemove = MediaBrowserServiceCompat.this.f1468f.remove(iBinderAsBinder);
                if (fVarRemove != null) {
                    iBinderAsBinder.unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        public class h implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1542d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1543e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ Bundle f1544f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public final /* synthetic */ ResultReceiver f1545g;

            public h(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1542d = oVar;
                this.f1543e = str;
                this.f1544f = bundle;
                this.f1545g = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1468f.get(this.f1542d.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.o(this.f1543e, this.f1544f, fVar, this.f1545g);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.f1543e);
            }
        }

        public class i implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ o f1547d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ String f1548e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ Bundle f1549f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public final /* synthetic */ ResultReceiver f1550g;

            public i(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1547d = oVar;
                this.f1548e = str;
                this.f1549f = bundle;
                this.f1550g = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1468f.get(this.f1547d.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.l(this.f1548e, this.f1549f, fVar, this.f1550g);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.f1548e + ", extras=" + this.f1549f);
            }
        }

        public n() {
        }

        public void a(String str, IBinder iBinder, Bundle bundle, o oVar) {
            MediaBrowserServiceCompat.this.f1470h.a(new c(oVar, str, iBinder, bundle));
        }

        public void b(String str, int i8, int i9, Bundle bundle, o oVar) {
            if (MediaBrowserServiceCompat.this.c(str, i9)) {
                MediaBrowserServiceCompat.this.f1470h.a(new a(oVar, str, i8, i9, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i9 + " package=" + str);
        }

        public void c(o oVar) {
            MediaBrowserServiceCompat.this.f1470h.a(new b(oVar));
        }

        public void d(String str, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f1470h.a(new e(oVar, str, resultReceiver));
        }

        public void e(o oVar, String str, int i8, int i9, Bundle bundle) {
            MediaBrowserServiceCompat.this.f1470h.a(new f(oVar, str, i8, i9, bundle));
        }

        public void f(String str, IBinder iBinder, o oVar) {
            MediaBrowserServiceCompat.this.f1470h.a(new d(oVar, str, iBinder));
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f1470h.a(new h(oVar, str, bundle, resultReceiver));
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f1470h.a(new i(oVar, str, bundle, resultReceiver));
        }

        public void i(o oVar) {
            MediaBrowserServiceCompat.this.f1470h.a(new g(oVar));
        }
    }

    public interface o {
        void a() throws RemoteException;

        IBinder asBinder();

        void b(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;
    }

    public static class p implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Messenger f1552a;

        public p(Messenger messenger) {
            this.f1552a = messenger;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public void a() throws RemoteException {
            c(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public IBinder asBinder() {
            return this.f1552a.getBinder();
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

        public final void c(int i8, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f1552a.send(messageObtain);
        }
    }

    public final class q extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n f1553a;

        public q() {
            this.f1553a = MediaBrowserServiceCompat.this.new n();
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
                    this.f1553a.b(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new p(message.replyTo));
                    break;
                case 2:
                    this.f1553a.c(new p(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    this.f1553a.a(data.getString("data_media_item_id"), d0.c.a(data, "data_callback_token"), bundle2, new p(message.replyTo));
                    break;
                case 4:
                    this.f1553a.f(data.getString("data_media_item_id"), d0.c.a(data, "data_callback_token"), new p(message.replyTo));
                    break;
                case 5:
                    this.f1553a.d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle3);
                    this.f1553a.e(new p(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.f1553a.i(new p(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.a(bundle4);
                    this.f1553a.g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.a(bundle5);
                    this.f1553a.h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    break;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j8) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            data.putInt("data_calling_pid", Binder.getCallingPid());
            return super.sendMessageAtTime(message, j8);
        }
    }

    public void a(String str, f fVar, IBinder iBinder, Bundle bundle) {
        List<n0.d<IBinder, Bundle>> arrayList = fVar.f1489g.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (n0.d<IBinder, Bundle> dVar : arrayList) {
            if (iBinder == dVar.f6506a && b1.d.a(bundle, dVar.f6507b)) {
                return;
            }
        }
        arrayList.add(new n0.d<>(iBinder, bundle));
        fVar.f1489g.put(str, arrayList);
        m(str, fVar, bundle, null);
        this.f1469g = fVar;
        j(str, bundle);
        this.f1469g = null;
    }

    public List<MediaBrowserCompat.MediaItem> b(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
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

    public boolean c(String str, int i8) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i8)) {
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

    public abstract e e(String str, int i8, Bundle bundle);

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

    public void l(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        d dVar = new d(str, resultReceiver);
        this.f1469g = fVar;
        d(str, bundle, dVar);
        this.f1469g = null;
        if (dVar.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    public void m(String str, f fVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, fVar, str, bundle, bundle2);
        this.f1469g = fVar;
        if (bundle == null) {
            f(str, aVar);
        } else {
            g(str, aVar, bundle);
        }
        this.f1469g = null;
        if (aVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + fVar.f1483a + " id=" + str);
    }

    public void n(String str, f fVar, ResultReceiver resultReceiver) {
        b bVar = new b(str, resultReceiver);
        this.f1469g = fVar;
        h(str, bVar);
        this.f1469g = null;
        if (bVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    public void o(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        c cVar = new c(str, resultReceiver);
        this.f1469g = fVar;
        i(str, bundle, cVar);
        this.f1469g = null;
        if (cVar.b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1467e.d(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 28) {
            this.f1467e = new k();
        } else if (i8 >= 26) {
            this.f1467e = new j();
        } else if (i8 >= 23) {
            this.f1467e = new i();
        } else if (i8 >= 21) {
            this.f1467e = new h();
        } else {
            this.f1467e = new l();
        }
        this.f1467e.a();
    }

    public boolean p(String str, f fVar, IBinder iBinder) {
        boolean z7 = false;
        try {
            if (iBinder == null) {
                return fVar.f1489g.remove(str) != null;
            }
            List<n0.d<IBinder, Bundle>> list = fVar.f1489g.get(str);
            if (list != null) {
                Iterator<n0.d<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f6506a) {
                        it.remove();
                        z7 = true;
                    }
                }
                if (list.size() == 0) {
                    fVar.f1489g.remove(str);
                }
            }
            return z7;
        } finally {
            this.f1469g = fVar;
            k(str);
            this.f1469g = null;
        }
    }
}
