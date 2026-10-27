package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.r;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaRouteProviderService extends Service {
    static final boolean i = Log.isLoggable("MediaRouteProviderSrv", 3);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f947c = new e(this);
    final Messenger d = new Messenger(this.f947c);
    final d e = new d();
    private final MediaRouteProvider.a f;
    MediaRouteProvider g;
    final a h;

    /* JADX INFO: compiled from: caradapter-bug */
    interface a {
        boolean a(Messenger messenger, int i, int i2, List<String> list);

        IBinder b(Intent intent);

        boolean c(Messenger messenger, int i, int i2, String str);

        void d(Messenger messenger);

        boolean e(Messenger messenger, int i, int i2, String str);

        boolean f(Messenger messenger, int i, int i2);

        MediaRouteProvider.a g();

        boolean h(Messenger messenger, int i, q qVar);

        boolean i(Messenger messenger, int i, int i2, String str);

        boolean j(Messenger messenger, int i, int i2, String str, String str2);

        boolean k(Messenger messenger, int i, int i2, String str);

        boolean l(Messenger messenger, int i, int i2, int i3);

        boolean m(Messenger messenger, int i, int i2, Intent intent);

        boolean n(Messenger messenger, int i, int i2, int i3);

        void o(Context context);

        boolean p(Messenger messenger, int i, int i2, int i3);

        boolean q(Messenger messenger, int i, int i2);

        boolean r(Messenger messenger, int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(api = 30)
    static class b extends c {
        o e;
        final Map<String, MediaRouteProvider.d> f;
        final MediaRouteProvider.DynamicGroupRouteController.c g;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends c.b {
            a(Messenger messenger, int i, String str) {
                super(messenger, i, str);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public Bundle a(String str, int i) {
                Bundle bundleA = super.a(str, i);
                if (bundleA != null && this.f956c != null) {
                    b.this.e.a(this.e.get(i), i, this.f956c, str);
                }
                return bundleA;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public boolean b(String str, String str2, int i) {
                MediaRouteProvider.d dVar = b.this.f.get(str);
                if (dVar != null) {
                    this.e.put(i, dVar);
                    return true;
                }
                boolean zB = super.b(str, str2, i);
                if (str2 == null && zB && this.f956c != null) {
                    b.this.e.a(this.e.get(i), i, this.f956c, str);
                }
                if (zB) {
                    b.this.f.put(str, this.e.get(i));
                }
                return zB;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public boolean g(int i) {
                b.this.e.j(i);
                MediaRouteProvider.d dVar = this.e.get(i);
                if (dVar != null) {
                    Iterator<Map.Entry<String, MediaRouteProvider.d>> it = b.this.f.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<String, MediaRouteProvider.d> next = it.next();
                        if (next.getValue() == dVar) {
                            b.this.f.remove(next.getKey());
                            break;
                        }
                    }
                }
                return super.g(i);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            void h(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, p pVar, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                super.h(dynamicGroupRouteController, pVar, collection);
                o oVar = b.this.e;
                if (oVar != null) {
                    oVar.k(dynamicGroupRouteController, pVar, collection);
                }
            }
        }

        b(MediaRouteProviderService mediaRouteProviderService) {
            super(mediaRouteProviderService);
            this.f = new ArrayMap();
            this.g = new MediaRouteProvider.DynamicGroupRouteController.c() { // from class: androidx.mediarouter.media.h
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.c
                public final void a(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, p pVar, Collection collection) {
                    this.f968a.C(dynamicGroupRouteController, pVar, collection);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void C(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, p pVar, Collection collection) {
            this.e.k(dynamicGroupRouteController, pVar, collection);
        }

        MediaRouteProvider.d A(String str) {
            return this.f.get(str);
        }

        boolean D(String str) {
            MediaRouteProvider.d dVarRemove = this.f.remove(str);
            if (dVarRemove == null) {
                return false;
            }
            dVarRemove.i(0);
            dVarRemove.e();
            return true;
        }

        void E(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController) {
            dynamicGroupRouteController.q(androidx.core.content.a.g(this.f948a.getApplicationContext()), this.g);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c, androidx.mediarouter.media.MediaRouteProviderService.a
        public IBinder b(Intent intent) {
            this.f948a.a();
            if (this.e == null) {
                this.e = new o(this);
                if (this.f948a.getBaseContext() != null) {
                    this.e.attachBaseContext(this.f948a);
                }
            }
            IBinder iBinderB = super.b(intent);
            return iBinderB != null ? iBinderB : this.e.onBind(intent);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c, androidx.mediarouter.media.MediaRouteProviderService.a
        public void o(Context context) {
            o oVar = this.e;
            if (oVar != null) {
                oVar.attachBaseContext(context);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c
        c.b s(Messenger messenger, int i, String str) {
            return new a(messenger, i, str);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c
        void w(r rVar) {
            super.w(rVar);
            this.e.l(rVar);
        }

        MediaRouteProvider.d z(String str, String str2) {
            MediaRouteProvider.d dVar = this.f.get(str);
            if (dVar != null) {
                return dVar;
            }
            MediaRouteProvider.d dVarT = str2 == null ? this.f948a.c().t(str) : this.f948a.c().u(str, str2);
            if (dVarT != null) {
                this.f.put(str, dVarT);
            }
            return dVarT;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaRouteProviderService f948a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<b> f949b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        q f950c;
        q d;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends u.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ b f951a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f952b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Intent f953c;
            final /* synthetic */ Messenger d;
            final /* synthetic */ int e;

            a(b bVar, int i, Intent intent, Messenger messenger, int i2) {
                this.f951a = bVar;
                this.f952b = i;
                this.f953c = intent;
                this.d = messenger;
                this.e = i2;
            }

            @Override // androidx.mediarouter.media.u.d
            public void a(String str, Bundle bundle) {
                if (MediaRouteProviderService.i) {
                    Log.d("MediaRouteProviderSrv", this.f951a + ": Route control request failed, controllerId=" + this.f952b + ", intent=" + this.f953c + ", error=" + str + ", data=" + bundle);
                }
                if (c.this.t(this.d) >= 0) {
                    if (str == null) {
                        MediaRouteProviderService.g(this.d, 4, this.e, 0, bundle, null);
                        return;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("error", str);
                    MediaRouteProviderService.g(this.d, 4, this.e, 0, bundle, bundle2);
                }
            }

            @Override // androidx.mediarouter.media.u.d
            public void b(Bundle bundle) {
                if (MediaRouteProviderService.i) {
                    Log.d("MediaRouteProviderSrv", this.f951a + ": Route control request succeeded, controllerId=" + this.f952b + ", intent=" + this.f953c + ", data=" + bundle);
                }
                if (c.this.t(this.d) >= 0) {
                    MediaRouteProviderService.g(this.d, 3, this.e, 0, bundle, null);
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements IBinder.DeathRecipient {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final Messenger f954a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final int f955b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final String f956c;
            public q d;
            final SparseArray<MediaRouteProvider.d> e = new SparseArray<>();
            final MediaRouteProvider.DynamicGroupRouteController.c f = new a();

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements MediaRouteProvider.DynamicGroupRouteController.c {
                a() {
                }

                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.c
                public void a(@NonNull MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, @NonNull p pVar, @NonNull Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                    b.this.h(dynamicGroupRouteController, pVar, collection);
                }
            }

            b(Messenger messenger, int i, String str) {
                this.f954a = messenger;
                this.f955b = i;
                this.f956c = str;
            }

            public Bundle a(String str, int i) {
                MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerS;
                if (this.e.indexOfKey(i) >= 0 || (dynamicGroupRouteControllerS = c.this.f948a.c().s(str)) == null) {
                    return null;
                }
                dynamicGroupRouteControllerS.q(androidx.core.content.a.g(c.this.f948a.getApplicationContext()), this.f);
                this.e.put(i, dynamicGroupRouteControllerS);
                Bundle bundle = new Bundle();
                bundle.putString("groupableTitle", dynamicGroupRouteControllerS.k());
                bundle.putString("transferableTitle", dynamicGroupRouteControllerS.l());
                return bundle;
            }

            public boolean b(String str, String str2, int i) {
                if (this.e.indexOfKey(i) >= 0) {
                    return false;
                }
                MediaRouteProvider.d dVarT = str2 == null ? c.this.f948a.c().t(str) : c.this.f948a.c().u(str, str2);
                if (dVarT == null) {
                    return false;
                }
                this.e.put(i, dVarT);
                return true;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                c.this.f948a.e.obtainMessage(1, this.f954a).sendToTarget();
            }

            public void c() {
                int size = this.e.size();
                for (int i = 0; i < size; i++) {
                    this.e.valueAt(i).e();
                }
                this.e.clear();
                this.f954a.getBinder().unlinkToDeath(this, 0);
                i(null);
            }

            public MediaRouteProvider.d d(int i) {
                return this.e.get(i);
            }

            public boolean e(Messenger messenger) {
                return this.f954a.getBinder() == messenger.getBinder();
            }

            public boolean f() {
                try {
                    this.f954a.getBinder().linkToDeath(this, 0);
                    return true;
                } catch (RemoteException unused) {
                    binderDied();
                    return false;
                }
            }

            public boolean g(int i) {
                MediaRouteProvider.d dVar = this.e.get(i);
                if (dVar == null) {
                    return false;
                }
                this.e.remove(i);
                dVar.e();
                return true;
            }

            void h(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, p pVar, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                int iIndexOfValue = this.e.indexOfValue(dynamicGroupRouteController);
                if (iIndexOfValue < 0) {
                    Log.w("MediaRouteProviderSrv", "Ignoring unknown dynamic group route controller: " + dynamicGroupRouteController);
                    return;
                }
                int iKeyAt = this.e.keyAt(iIndexOfValue);
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().g());
                }
                Bundle bundle = new Bundle();
                if (pVar != null) {
                    bundle.putParcelable("groupRoute", pVar.a());
                }
                bundle.putParcelableArrayList("dynamicRoutes", arrayList);
                MediaRouteProviderService.g(this.f954a, 7, 0, iKeyAt, bundle, null);
            }

            public boolean i(q qVar) {
                if (androidx.core.d.a.a(this.d, qVar)) {
                    return false;
                }
                this.d = qVar;
                return c.this.y();
            }

            public String toString() {
                return MediaRouteProviderService.b(this.f954a);
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.MediaRouteProviderService$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        class C0035c extends MediaRouteProvider.a {
            C0035c() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.a
            public void a(@NonNull MediaRouteProvider mediaRouteProvider, r rVar) {
                c.this.w(rVar);
            }
        }

        c(MediaRouteProviderService mediaRouteProviderService) {
            this.f948a = mediaRouteProviderService;
        }

        private b u(Messenger messenger) {
            int iT = t(messenger);
            if (iT >= 0) {
                return this.f949b.get(iT);
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean a(Messenger messenger, int i, int i2, List<String> list) {
            b bVarU = u(messenger);
            if (bVarU == null) {
                return false;
            }
            MediaRouteProvider.d dVarD = bVarU.d(i2);
            if (!(dVarD instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) dVarD).p(list);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Updated list of member routes, controllerId=" + i2 + ", memberIds=" + list);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public IBinder b(Intent intent) {
            if (!intent.getAction().equals("android.media.MediaRouteProviderService")) {
                return null;
            }
            this.f948a.a();
            if (this.f948a.c() != null) {
                return this.f948a.d.getBinder();
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean c(Messenger messenger, int i, int i2, String str) {
            if (i2 < 1 || t(messenger) >= 0) {
                return false;
            }
            b bVarS = s(messenger, i2, str);
            if (!bVarS.f()) {
                return false;
            }
            this.f949b.add(bVarS);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarS + ": Registered, version=" + i2);
            }
            if (i != 0) {
                MediaRouteProviderService.g(messenger, 2, i, 2, MediaRouteProviderService.createDescriptorBundleForClientVersion(this.f948a.c().o(), bVarS.f955b), null);
            }
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public void d(Messenger messenger) {
            int iT = t(messenger);
            if (iT >= 0) {
                b bVarRemove = this.f949b.remove(iT);
                if (MediaRouteProviderService.i) {
                    Log.d("MediaRouteProviderSrv", bVarRemove + ": Binder died");
                }
                bVarRemove.c();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean e(Messenger messenger, int i, int i2, String str) {
            b bVarU = u(messenger);
            if (bVarU == null) {
                return false;
            }
            MediaRouteProvider.d dVarD = bVarU.d(i2);
            if (!(dVarD instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) dVarD).o(str);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Removed a member route, controllerId=" + i2 + ", memberId=" + str);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean f(Messenger messenger, int i, int i2) {
            MediaRouteProvider.d dVarD;
            b bVarU = u(messenger);
            if (bVarU == null || (dVarD = bVarU.d(i2)) == null) {
                return false;
            }
            dVarD.f();
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route selected, controllerId=" + i2);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public MediaRouteProvider.a g() {
            return new C0035c();
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean h(Messenger messenger, int i, q qVar) {
            b bVarU = u(messenger);
            if (bVarU == null) {
                return false;
            }
            boolean zI = bVarU.i(qVar);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Set discovery request, request=" + qVar + ", actuallyChanged=" + zI + ", compositeDiscoveryRequest=" + this.f950c);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean i(Messenger messenger, int i, int i2, String str) {
            Bundle bundleA;
            b bVarU = u(messenger);
            if (bVarU == null || (bundleA = bVarU.a(str, i2)) == null) {
                return false;
            }
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route controller created, controllerId=" + i2 + ", initialMemberRouteId=" + str);
            }
            MediaRouteProviderService.g(messenger, 6, i, 2, bundleA, null);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean j(Messenger messenger, int i, int i2, String str, String str2) {
            b bVarU = u(messenger);
            if (bVarU == null || !bVarU.b(str, str2, i2)) {
                return false;
            }
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route controller created, controllerId=" + i2 + ", routeId=" + str + ", routeGroupId=" + str2);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean k(Messenger messenger, int i, int i2, String str) {
            b bVarU = u(messenger);
            if (bVarU == null) {
                return false;
            }
            MediaRouteProvider.d dVarD = bVarU.d(i2);
            if (!(dVarD instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) dVarD).n(str);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Added a member route, controllerId=" + i2 + ", memberId=" + str);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean l(Messenger messenger, int i, int i2, int i3) {
            MediaRouteProvider.d dVarD;
            b bVarU = u(messenger);
            if (bVarU == null || (dVarD = bVarU.d(i2)) == null) {
                return false;
            }
            dVarD.g(i3);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route volume changed, controllerId=" + i2 + ", volume=" + i3);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean m(Messenger messenger, int i, int i2, Intent intent) {
            MediaRouteProvider.d dVarD;
            b bVarU = u(messenger);
            if (bVarU == null || (dVarD = bVarU.d(i2)) == null) {
                return false;
            }
            if (!dVarD.d(intent, i != 0 ? new a(bVarU, i2, intent, messenger, i) : null)) {
                return false;
            }
            if (!MediaRouteProviderService.i) {
                return true;
            }
            Log.d("MediaRouteProviderSrv", bVarU + ": Route control request delivered, controllerId=" + i2 + ", intent=" + intent);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean n(Messenger messenger, int i, int i2, int i3) {
            MediaRouteProvider.d dVarD;
            b bVarU = u(messenger);
            if (bVarU == null || (dVarD = bVarU.d(i2)) == null) {
                return false;
            }
            dVarD.i(i3);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route unselected, controllerId=" + i2);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public void o(Context context) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean p(Messenger messenger, int i, int i2, int i3) {
            MediaRouteProvider.d dVarD;
            b bVarU = u(messenger);
            if (bVarU == null || (dVarD = bVarU.d(i2)) == null) {
                return false;
            }
            dVarD.j(i3);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route volume updated, controllerId=" + i2 + ", delta=" + i3);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean q(Messenger messenger, int i, int i2) {
            b bVarU = u(messenger);
            if (bVarU == null || !bVarU.g(i2)) {
                return false;
            }
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarU + ": Route controller released, controllerId=" + i2);
            }
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public boolean r(Messenger messenger, int i) {
            int iT = t(messenger);
            if (iT < 0) {
                return false;
            }
            b bVarRemove = this.f949b.remove(iT);
            if (MediaRouteProviderService.i) {
                Log.d("MediaRouteProviderSrv", bVarRemove + ": Unregistered");
            }
            bVarRemove.c();
            MediaRouteProviderService.f(messenger, i);
            return true;
        }

        b s(Messenger messenger, int i, String str) {
            return new b(messenger, i, str);
        }

        int t(Messenger messenger) {
            int size = this.f949b.size();
            for (int i = 0; i < size; i++) {
                if (this.f949b.get(i).e(messenger)) {
                    return i;
                }
            }
            return -1;
        }

        public MediaRouteProviderService v() {
            return this.f948a;
        }

        void w(r rVar) {
            int size = this.f949b.size();
            for (int i = 0; i < size; i++) {
                b bVar = this.f949b.get(i);
                MediaRouteProviderService.g(bVar.f954a, 5, 0, 0, MediaRouteProviderService.createDescriptorBundleForClientVersion(rVar, bVar.f955b), null);
                if (MediaRouteProviderService.i) {
                    Log.d("MediaRouteProviderSrv", bVar + ": Sent descriptor change event, descriptor=" + rVar);
                }
            }
        }

        boolean x(q qVar) {
            if (androidx.core.d.a.a(this.d, qVar)) {
                return false;
            }
            this.d = qVar;
            return y();
        }

        boolean y() {
            t.a aVar;
            boolean zE;
            q qVar = this.d;
            q qVar2 = null;
            if (qVar != null) {
                zE = qVar.e();
                aVar = new t.a(this.d.d());
            } else {
                aVar = null;
                zE = false;
            }
            int size = this.f949b.size();
            for (int i = 0; i < size; i++) {
                q qVar3 = this.f949b.get(i).d;
                if (qVar3 != null && (!qVar3.d().f() || qVar3.e())) {
                    zE |= qVar3.e();
                    if (qVar2 == null) {
                        qVar2 = qVar3;
                    } else {
                        if (aVar == null) {
                            aVar = new t.a(qVar2.d());
                        }
                        aVar.c(qVar3.d());
                    }
                }
            }
            if (aVar != null) {
                qVar2 = new q(aVar.d(), zE);
            }
            if (androidx.core.d.a.a(this.f950c, qVar2)) {
                return false;
            }
            this.f950c = qVar2;
            this.f948a.c().y(qVar2);
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class d extends Handler {
        d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            MediaRouteProviderService.this.h.d((Messenger) message.obj);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class e extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<MediaRouteProviderService> f960a;

        public e(MediaRouteProviderService mediaRouteProviderService) {
            this.f960a = new WeakReference<>(mediaRouteProviderService);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private boolean a(int i, Messenger messenger, int i2, int i3, Object obj, Bundle bundle, String str) {
            MediaRouteProviderService mediaRouteProviderService = this.f960a.get();
            if (mediaRouteProviderService != null) {
                switch (i) {
                    case 1:
                        return mediaRouteProviderService.h.c(messenger, i2, i3, str);
                    case 2:
                        return mediaRouteProviderService.h.r(messenger, i2);
                    case 3:
                        String string = bundle.getString("routeId");
                        String string2 = bundle.getString("routeGroupId");
                        if (string != null) {
                            return mediaRouteProviderService.h.j(messenger, i2, i3, string, string2);
                        }
                        break;
                    case 4:
                        return mediaRouteProviderService.h.q(messenger, i2, i3);
                    case 5:
                        return mediaRouteProviderService.h.f(messenger, i2, i3);
                    case 6:
                        return mediaRouteProviderService.h.n(messenger, i2, i3, bundle != null ? bundle.getInt("unselectReason", 0) : 0);
                    case 7:
                        int i4 = bundle.getInt("volume", -1);
                        if (i4 >= 0) {
                            return mediaRouteProviderService.h.l(messenger, i2, i3, i4);
                        }
                        break;
                    case 8:
                        int i5 = bundle.getInt("volume", 0);
                        if (i5 != 0) {
                            return mediaRouteProviderService.h.p(messenger, i2, i3, i5);
                        }
                        break;
                    case 9:
                        if (obj instanceof Intent) {
                            return mediaRouteProviderService.h.m(messenger, i2, i3, (Intent) obj);
                        }
                        break;
                    case 10:
                        if (obj == null || (obj instanceof Bundle)) {
                            q qVarC = q.c((Bundle) obj);
                            a aVar = mediaRouteProviderService.h;
                            if (qVarC == null || !qVarC.f()) {
                                qVarC = null;
                            }
                            return aVar.h(messenger, i2, qVarC);
                        }
                        break;
                    case 11:
                        String string3 = bundle.getString("memberRouteId");
                        if (string3 != null) {
                            return mediaRouteProviderService.h.i(messenger, i2, i3, string3);
                        }
                        break;
                    case 12:
                        String string4 = bundle.getString("memberRouteId");
                        if (string4 != null) {
                            return mediaRouteProviderService.h.k(messenger, i2, i3, string4);
                        }
                        break;
                    case 13:
                        String string5 = bundle.getString("memberRouteId");
                        if (string5 != null) {
                            return mediaRouteProviderService.h.e(messenger, i2, i3, string5);
                        }
                        break;
                    case 14:
                        ArrayList<String> stringArrayList = bundle.getStringArrayList("memberRouteIds");
                        if (stringArrayList != null) {
                            return mediaRouteProviderService.h.a(messenger, i2, i3, stringArrayList);
                        }
                        break;
                }
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r16) {
            /*
                r15 = this;
                r0 = r16
                android.os.Messenger r8 = r0.replyTo
                boolean r1 = androidx.mediarouter.media.s.a(r8)
                java.lang.String r9 = "MediaRouteProviderSrv"
                if (r1 == 0) goto L91
                int r10 = r0.what
                int r11 = r0.arg1
                int r12 = r0.arg2
                java.lang.Object r13 = r0.obj
                android.os.Bundle r14 = r16.peekData()
                r1 = 0
                r2 = 1
                if (r10 != r2) goto L3f
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 21
                if (r2 < r3) goto L3f
                r2 = r15
                java.lang.ref.WeakReference<androidx.mediarouter.media.MediaRouteProviderService> r3 = r2.f960a
                java.lang.Object r3 = r3.get()
                androidx.mediarouter.media.MediaRouteProviderService r3 = (androidx.mediarouter.media.MediaRouteProviderService) r3
                android.content.pm.PackageManager r3 = r3.getPackageManager()
                int r0 = r0.sendingUid
                java.lang.String[] r0 = r3.getPackagesForUid(r0)
                if (r0 == 0) goto L40
                int r3 = r0.length
                if (r3 <= 0) goto L40
                r1 = 0
                r0 = r0[r1]
                r7 = r0
                goto L41
            L3f:
                r2 = r15
            L40:
                r7 = r1
            L41:
                r0 = r15
                r1 = r10
                r2 = r8
                r3 = r11
                r4 = r12
                r5 = r13
                r6 = r14
                boolean r0 = r0.a(r1, r2, r3, r4, r5, r6, r7)
                if (r0 != 0) goto L9a
                boolean r0 = androidx.mediarouter.media.MediaRouteProviderService.i
                if (r0 == 0) goto L8d
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = androidx.mediarouter.media.MediaRouteProviderService.b(r8)
                r0.append(r1)
                java.lang.String r1 = ": Message failed, what="
                r0.append(r1)
                r0.append(r10)
                java.lang.String r1 = ", requestId="
                r0.append(r1)
                r0.append(r11)
                java.lang.String r1 = ", arg="
                r0.append(r1)
                r0.append(r12)
                java.lang.String r1 = ", obj="
                r0.append(r1)
                r0.append(r13)
                java.lang.String r1 = ", data="
                r0.append(r1)
                r0.append(r14)
                java.lang.String r0 = r0.toString()
                android.util.Log.d(r9, r0)
            L8d:
                androidx.mediarouter.media.MediaRouteProviderService.e(r8, r11)
                goto L9a
            L91:
                boolean r0 = androidx.mediarouter.media.MediaRouteProviderService.i
                if (r0 == 0) goto L9a
                java.lang.String r0 = "Ignoring message without valid reply messenger."
                android.util.Log.d(r9, r0)
            L9a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouteProviderService.e.handleMessage(android.os.Message):void");
        }
    }

    @SuppressLint({"NewApi"})
    public MediaRouteProviderService() {
        if (Build.VERSION.SDK_INT >= 30) {
            this.h = new b(this);
        } else {
            this.h = new c(this);
        }
        this.f = this.h.g();
    }

    static String b(Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }

    @VisibleForTesting
    static Bundle createDescriptorBundleForClientVersion(r rVar, int i2) {
        if (rVar == null) {
            return null;
        }
        r.a aVar = new r.a(rVar);
        aVar.d(null);
        for (p pVar : rVar.c()) {
            if (i2 >= pVar.o() && i2 <= pVar.n()) {
                aVar.a(pVar);
            }
        }
        return aVar.c().a();
    }

    static void e(Messenger messenger, int i2) {
        if (i2 != 0) {
            g(messenger, 0, i2, 0, null, null);
        }
    }

    static void f(Messenger messenger, int i2) {
        if (i2 != 0) {
            g(messenger, 1, i2, 0, null, null);
        }
    }

    static void g(Messenger messenger, int i2, int i3, int i4, Object obj, Bundle bundle) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i2;
        messageObtain.arg1 = i3;
        messageObtain.arg2 = i4;
        messageObtain.obj = obj;
        messageObtain.setData(bundle);
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e2) {
            Log.e("MediaRouteProviderSrv", "Could not send message to " + b(messenger), e2);
        }
    }

    void a() {
        MediaRouteProvider mediaRouteProviderD;
        if (this.g != null || (mediaRouteProviderD = d()) == null) {
            return;
        }
        String strB = mediaRouteProviderD.r().b();
        if (strB.equals(getPackageName())) {
            this.g = mediaRouteProviderD;
            mediaRouteProviderD.w(this.f);
            return;
        }
        throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + strB + ".  Service package name: " + getPackageName() + ".");
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(@NonNull Context context) {
        super.attachBaseContext(context);
        this.h.o(context);
    }

    public MediaRouteProvider c() {
        return this.g;
    }

    public abstract MediaRouteProvider d();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.h.b(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        MediaRouteProvider mediaRouteProvider = this.g;
        if (mediaRouteProvider != null) {
            mediaRouteProvider.w(null);
        }
        super.onDestroy();
    }
}
