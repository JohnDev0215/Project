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
import b.q.e;
import b.q.f;
import b.q.g;
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
    public static final boolean f1047b = Log.isLoggable("MBServiceCompat", 3);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f1048c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f1050e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public MediaSessionCompat.Token f1052g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b.e.a<IBinder, f> f1049d = new b.e.a<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f1051f = new q();

    public class a extends m<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ f f1053f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f1054g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Bundle f1055h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Bundle f1056i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Object obj, f fVar, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f1053f = fVar;
            this.f1054g = str;
            this.f1055h = bundle;
            this.f1056i = bundle2;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.f1049d.get(this.f1053f.f1068f.asBinder()) != this.f1053f) {
                if (MediaBrowserServiceCompat.f1047b) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f1053f.f1063a + " id=" + this.f1054g);
                    return;
                }
                return;
            }
            if ((a() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.b(list, this.f1055h);
            }
            try {
                this.f1053f.f1068f.b(this.f1054g, list, this.f1055h, this.f1056i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.f1054g + " package=" + this.f1053f.f1063a);
            }
        }
    }

    public class b extends m<MediaBrowserCompat.MediaItem> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ResultReceiver f1057f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f1057f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(MediaBrowserCompat.MediaItem mediaItem) {
            if ((a() & 2) != 0) {
                this.f1057f.k(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f1057f.k(0, bundle);
        }
    }

    public class c extends m<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ResultReceiver f1059f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f1059f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(List<MediaBrowserCompat.MediaItem> list) {
            if ((a() & 4) != 0 || list == null) {
                this.f1059f.k(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f1059f.k(0, bundle);
        }
    }

    public class d extends m<Bundle> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ResultReceiver f1061f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f1061f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void c(Bundle bundle) {
            this.f1061f.k(-1, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(Bundle bundle) {
            this.f1061f.k(0, bundle);
        }
    }

    public static final class e {
    }

    public class f implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1063a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1064b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1065c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b.q.h f1066d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bundle f1067e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final o f1068f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final HashMap<String, List<b.h.l.c<IBinder, Bundle>>> f1069g = new HashMap<>();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                MediaBrowserServiceCompat.this.f1049d.remove(fVar.f1068f.asBinder());
            }
        }

        public f(String str, int i2, int i3, Bundle bundle, o oVar) {
            this.f1063a = str;
            this.f1064b = i2;
            this.f1065c = i3;
            this.f1066d = new b.q.h(str, i2, i3);
            this.f1067e = bundle;
            this.f1068f = oVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.f1051f.post(new a());
        }
    }

    public interface g {
        void a();

        IBinder e(Intent intent);
    }

    public class h implements g, e.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<Bundle> f1072a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f1073b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Messenger f1074c;

        public class a extends m<List<MediaBrowserCompat.MediaItem>> {

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ e.c f1076f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, e.c cVar) {
                super(obj);
                this.f1076f = cVar;
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
                this.f1076f.b(arrayList);
            }
        }

        public h() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = b.q.e.a(MediaBrowserServiceCompat.this, this);
            this.f1073b = objA;
            b.q.e.c(objA);
        }

        @Override // b.q.e.d
        public void b(String str, e.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.f(str, new a(str, cVar));
        }

        @Override // b.q.e.d
        public e.a c(String str, int i2, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.f1074c = new Messenger(MediaBrowserServiceCompat.this.f1051f);
                bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                b.h.d.e.b(bundle2, "extra_messenger", this.f1074c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.f1052g;
                if (token != null) {
                    a.d.a.b.f.b bVarL = token.l();
                    b.h.d.e.b(bundle2, "extra_session_binder", bVarL == null ? null : bVarL.asBinder());
                } else {
                    this.f1072a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.f1050e = mediaBrowserServiceCompat.new f(str, -1, i2, bundle, null);
            e eVarE = MediaBrowserServiceCompat.this.e(str, i2, bundle);
            MediaBrowserServiceCompat.this.f1050e = null;
            if (eVarE == null) {
                return null;
            }
            Objects.requireNonNull(bundle2);
            throw null;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder e(Intent intent) {
            return b.q.e.b(this.f1073b, intent);
        }
    }

    public class i extends h implements f.b {

        public class a extends m<MediaBrowserCompat.MediaItem> {

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ e.c f1079f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, e.c cVar) {
                super(obj);
                this.f1079f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.m
            /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
            public void d(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem == null) {
                    this.f1079f.b(null);
                    return;
                }
                Parcel parcelObtain = Parcel.obtain();
                mediaItem.writeToParcel(parcelObtain, 0);
                this.f1079f.b(parcelObtain);
            }
        }

        public i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = b.q.f.a(MediaBrowserServiceCompat.this, this);
            this.f1073b = objA;
            b.q.e.c(objA);
        }

        @Override // b.q.f.b
        public void d(String str, e.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.h(str, new a(str, cVar));
        }
    }

    public class j extends i implements g.c {

        public class a extends m<List<MediaBrowserCompat.MediaItem>> {

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ g.b f1082f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, g.b bVar) {
                super(obj);
                this.f1082f = bVar;
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
                this.f1082f.b(arrayList, a());
            }
        }

        public j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.i, androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            Object objA = b.q.g.a(MediaBrowserServiceCompat.this, this);
            this.f1073b = objA;
            b.q.e.c(objA);
        }

        @Override // b.q.g.c
        public void f(String str, g.b bVar, Bundle bundle) {
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
        public Messenger f1085a;

        public l() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void a() {
            this.f1085a = new Messenger(MediaBrowserServiceCompat.this.f1051f);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public IBinder e(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.f1085a.getBinder();
            }
            return null;
        }
    }

    public static class m<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1087a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1088b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1089c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1090d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1091e;

        public m(Object obj) {
            this.f1087a = obj;
        }

        public int a() {
            return this.f1091e;
        }

        public boolean b() {
            return this.f1088b || this.f1089c || this.f1090d;
        }

        public void c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f1087a);
        }

        public void d(T t) {
            throw null;
        }

        public void e(Bundle bundle) {
            if (!this.f1089c && !this.f1090d) {
                this.f1090d = true;
                c(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f1087a);
            }
        }

        public void f(T t) {
            if (!this.f1089c && !this.f1090d) {
                this.f1089c = true;
                d(t);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f1087a);
            }
        }

        public void g(int i2) {
            this.f1091e = i2;
        }
    }

    public class n {

        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1093b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1094c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f1095d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f1096e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ Bundle f1097f;

            public a(o oVar, String str, int i2, int i3, Bundle bundle) {
                this.f1093b = oVar;
                this.f1094c = str;
                this.f1095d = i2;
                this.f1096e = i3;
                this.f1097f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1093b.asBinder();
                MediaBrowserServiceCompat.this.f1049d.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.f1094c, this.f1095d, this.f1096e, this.f1097f, this.f1093b);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.f1050e = fVar;
                e eVarE = mediaBrowserServiceCompat.e(this.f1094c, this.f1096e, this.f1097f);
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.f1050e = null;
                if (eVarE != null) {
                    try {
                        mediaBrowserServiceCompat2.f1049d.put(iBinderAsBinder, fVar);
                        iBinderAsBinder.linkToDeath(fVar, 0);
                        if (MediaBrowserServiceCompat.this.f1052g != null) {
                            throw null;
                        }
                        return;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.f1094c);
                        MediaBrowserServiceCompat.this.f1049d.remove(iBinderAsBinder);
                        return;
                    }
                }
                Log.i("MBServiceCompat", "No root for client " + this.f1094c + " from service " + a.class.getName());
                try {
                    this.f1093b.a();
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.f1094c);
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1099b;

            public b(o oVar) {
                this.f1099b = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVarRemove = MediaBrowserServiceCompat.this.f1049d.remove(this.f1099b.asBinder());
                if (fVarRemove != null) {
                    fVarRemove.f1068f.asBinder().unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1101b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1102c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ IBinder f1103d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ Bundle f1104e;

            public c(o oVar, String str, IBinder iBinder, Bundle bundle) {
                this.f1101b = oVar;
                this.f1102c = str;
                this.f1103d = iBinder;
                this.f1104e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1049d.get(this.f1101b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.a(this.f1102c, fVar, this.f1103d, this.f1104e);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.f1102c);
            }
        }

        public class d implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1106b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1107c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ IBinder f1108d;

            public d(o oVar, String str, IBinder iBinder) {
                this.f1106b = oVar;
                this.f1107c = str;
                this.f1108d = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1049d.get(this.f1106b.asBinder());
                if (fVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.f1107c);
                    return;
                }
                if (MediaBrowserServiceCompat.this.p(this.f1107c, fVar, this.f1108d)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.f1107c + " which is not subscribed");
            }
        }

        public class e implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1110b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1111c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ ResultReceiver f1112d;

            public e(o oVar, String str, ResultReceiver resultReceiver) {
                this.f1110b = oVar;
                this.f1111c = str;
                this.f1112d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1049d.get(this.f1110b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.n(this.f1111c, fVar, this.f1112d);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.f1111c);
            }
        }

        public class f implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1114b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1115c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f1116d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f1117e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ Bundle f1118f;

            public f(o oVar, String str, int i2, int i3, Bundle bundle) {
                this.f1114b = oVar;
                this.f1115c = str;
                this.f1116d = i2;
                this.f1117e = i3;
                this.f1118f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1114b.asBinder();
                MediaBrowserServiceCompat.this.f1049d.remove(iBinderAsBinder);
                f fVar = MediaBrowserServiceCompat.this.new f(this.f1115c, this.f1116d, this.f1117e, this.f1118f, this.f1114b);
                MediaBrowserServiceCompat.this.f1049d.put(iBinderAsBinder, fVar);
                try {
                    iBinderAsBinder.linkToDeath(fVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        public class g implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1120b;

            public g(o oVar) {
                this.f1120b = oVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f1120b.asBinder();
                f fVarRemove = MediaBrowserServiceCompat.this.f1049d.remove(iBinderAsBinder);
                if (fVarRemove != null) {
                    iBinderAsBinder.unlinkToDeath(fVarRemove, 0);
                }
            }
        }

        public class h implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1122b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1123c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ Bundle f1124d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ ResultReceiver f1125e;

            public h(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1122b = oVar;
                this.f1123c = str;
                this.f1124d = bundle;
                this.f1125e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1049d.get(this.f1122b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.o(this.f1123c, this.f1124d, fVar, this.f1125e);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.f1123c);
            }
        }

        public class i implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ o f1127b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ String f1128c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ Bundle f1129d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ ResultReceiver f1130e;

            public i(o oVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f1127b = oVar;
                this.f1128c = str;
                this.f1129d = bundle;
                this.f1130e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = MediaBrowserServiceCompat.this.f1049d.get(this.f1127b.asBinder());
                if (fVar != null) {
                    MediaBrowserServiceCompat.this.l(this.f1128c, this.f1129d, fVar, this.f1130e);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.f1128c + ", extras=" + this.f1129d);
            }
        }

        public n() {
        }

        public void a(String str, IBinder iBinder, Bundle bundle, o oVar) {
            MediaBrowserServiceCompat.this.f1051f.a(new c(oVar, str, iBinder, bundle));
        }

        public void b(String str, int i2, int i3, Bundle bundle, o oVar) {
            if (MediaBrowserServiceCompat.this.c(str, i3)) {
                MediaBrowserServiceCompat.this.f1051f.a(new a(oVar, str, i2, i3, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void c(o oVar) {
            MediaBrowserServiceCompat.this.f1051f.a(new b(oVar));
        }

        public void d(String str, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f1051f.a(new e(oVar, str, resultReceiver));
        }

        public void e(o oVar, String str, int i2, int i3, Bundle bundle) {
            MediaBrowserServiceCompat.this.f1051f.a(new f(oVar, str, i2, i3, bundle));
        }

        public void f(String str, IBinder iBinder, o oVar) {
            MediaBrowserServiceCompat.this.f1051f.a(new d(oVar, str, iBinder));
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f1051f.a(new h(oVar, str, bundle, resultReceiver));
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, o oVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f1051f.a(new i(oVar, str, bundle, resultReceiver));
        }

        public void i(o oVar) {
            MediaBrowserServiceCompat.this.f1051f.a(new g(oVar));
        }
    }

    public interface o {
        void a() throws RemoteException;

        IBinder asBinder();

        void b(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;
    }

    public static class p implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Messenger f1132a;

        public p(Messenger messenger) {
            this.f1132a = messenger;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public void a() throws RemoteException {
            c(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.o
        public IBinder asBinder() {
            return this.f1132a.getBinder();
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

        public final void c(int i2, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f1132a.send(messageObtain);
        }
    }

    public final class q extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n f1133a;

        public q() {
            this.f1133a = MediaBrowserServiceCompat.this.new n();
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
                    this.f1133a.b(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new p(message.replyTo));
                    break;
                case 2:
                    this.f1133a.c(new p(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.a(bundle2);
                    this.f1133a.a(data.getString("data_media_item_id"), b.h.d.e.a(data, "data_callback_token"), bundle2, new p(message.replyTo));
                    break;
                case 4:
                    this.f1133a.f(data.getString("data_media_item_id"), b.h.d.e.a(data, "data_callback_token"), new p(message.replyTo));
                    break;
                case 5:
                    this.f1133a.d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.a(bundle3);
                    this.f1133a.e(new p(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.f1133a.i(new p(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.a(bundle4);
                    this.f1133a.g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.a(bundle5);
                    this.f1133a.h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new p(message.replyTo));
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

    public void a(String str, f fVar, IBinder iBinder, Bundle bundle) {
        List<b.h.l.c<IBinder, Bundle>> arrayList = fVar.f1069g.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (b.h.l.c<IBinder, Bundle> cVar : arrayList) {
            if (iBinder == cVar.f2575a && b.q.d.a(bundle, cVar.f2576b)) {
                return;
            }
        }
        arrayList.add(new b.h.l.c<>(iBinder, bundle));
        fVar.f1069g.put(str, arrayList);
        m(str, fVar, bundle, null);
        this.f1050e = fVar;
        j(str, bundle);
        this.f1050e = null;
    }

    public List<MediaBrowserCompat.MediaItem> b(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
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

    public boolean c(String str, int i2) {
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

    public void l(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        d dVar = new d(str, resultReceiver);
        this.f1050e = fVar;
        d(str, bundle, dVar);
        this.f1050e = null;
        if (dVar.b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    public void m(String str, f fVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, fVar, str, bundle, bundle2);
        this.f1050e = fVar;
        if (bundle == null) {
            f(str, aVar);
        } else {
            g(str, aVar, bundle);
        }
        this.f1050e = null;
        if (aVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + fVar.f1063a + " id=" + str);
    }

    public void n(String str, f fVar, ResultReceiver resultReceiver) {
        b bVar = new b(str, resultReceiver);
        this.f1050e = fVar;
        h(str, bVar);
        this.f1050e = null;
        if (bVar.b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    public void o(String str, Bundle bundle, f fVar, ResultReceiver resultReceiver) {
        c cVar = new c(str, resultReceiver);
        this.f1050e = fVar;
        i(str, bundle, cVar);
        this.f1050e = null;
        if (cVar.b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1048c.e(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f1048c = new k();
        } else if (i2 >= 26) {
            this.f1048c = new j();
        } else if (i2 >= 23) {
            this.f1048c = new i();
        } else if (i2 >= 21) {
            this.f1048c = new h();
        } else {
            this.f1048c = new l();
        }
        this.f1048c.a();
    }

    public boolean p(String str, f fVar, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                return fVar.f1069g.remove(str) != null;
            }
            List<b.h.l.c<IBinder, Bundle>> list = fVar.f1069g.get(str);
            if (list != null) {
                Iterator<b.h.l.c<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f2575a) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    fVar.f1069g.remove(str);
                }
            }
            return z;
        } finally {
            this.f1050e = fVar;
            k(str);
            this.f1050e = null;
        }
    }
}
