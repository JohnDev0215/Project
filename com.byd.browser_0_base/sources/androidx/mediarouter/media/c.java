package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2ProviderService;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.h;
import defpackage.bz;
import defpackage.m8;
import defpackage.rd1;
import defpackage.td1;
import defpackage.y70;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: compiled from: MediaRoute2ProviderServiceAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends MediaRoute2ProviderService {
    public static final /* synthetic */ int j = 0;
    public final MediaRouteProviderService.b f;
    public volatile td1 i;
    public final Object e = new Object();
    public final m8 g = new m8();
    public final SparseArray<String> h = new SparseArray<>();

    /* JADX INFO: compiled from: MediaRoute2ProviderServiceAdapter.java */
    public static class b extends Handler {
        public final c a;
        public final String b;

        public b(c cVar, String str) {
            super(Looper.myLooper());
            this.a = cVar;
            this.b = str;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Messenger messenger = message.replyTo;
            int i = message.what;
            int i2 = message.arg1;
            Object obj = message.obj;
            Bundle data = message.getData();
            c cVar = this.a;
            if (i == 7) {
                int i3 = data.getInt("volume", -1);
                String string = data.getString("routeId");
                if (i3 < 0 || string == null) {
                    return;
                }
                MediaRouteProvider.e eVarB = cVar.b(string);
                if (eVarB == null) {
                    Log.w("MR2ProviderService", "setRouteVolume: Couldn't find a controller for routeId=".concat(string));
                    return;
                } else {
                    eVarB.g(i3);
                    return;
                }
            }
            if (i == 8) {
                int i4 = data.getInt("volume", 0);
                String string2 = data.getString("routeId");
                if (i4 == 0 || string2 == null) {
                    return;
                }
                MediaRouteProvider.e eVarB2 = cVar.b(string2);
                if (eVarB2 == null) {
                    Log.w("MR2ProviderService", "updateRouteVolume: Couldn't find a controller for routeId=".concat(string2));
                    return;
                } else {
                    eVarB2.j(i4);
                    return;
                }
            }
            if (i == 9 && (obj instanceof Intent)) {
                Intent intent = (Intent) obj;
                String str = this.b;
                if (cVar.getSessionInfo(str) == null) {
                    Log.w("MR2ProviderService", "onCustomCommand: Couldn't find a session");
                    return;
                }
                MediaRouteProvider.b bVarC = cVar.c(str);
                if (bVarC != null) {
                    bVarC.d(intent, new androidx.mediarouter.media.b(str, intent, messenger, i2));
                } else {
                    Log.w("MR2ProviderService", "onControlRequest: Couldn't find a controller");
                    cVar.notifyRequestFailed(i2, 3);
                }
            }
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.media.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaRoute2ProviderServiceAdapter.java */
    public final class C0018c {
        public final MediaRouteProvider.b b;
        public final long c;
        public final int d;
        public final WeakReference<MediaRouteProviderService.b.a> e;
        public boolean g;
        public RoutingSessionInfo h;
        public String i;
        public String j;
        public final m8 a = new m8();
        public boolean f = false;

        public C0018c(MediaRouteProvider.b bVar, long j, int i, MediaRouteProviderService.b.a aVar) {
            this.b = bVar;
            this.c = j;
            this.d = i;
            this.e = new WeakReference<>(aVar);
        }

        public final MediaRouteProvider.e a(String str) {
            MediaRouteProviderService.b.a aVar = this.e.get();
            return aVar != null ? (MediaRouteProvider.e) aVar.i.get(str) : (MediaRouteProvider.e) this.a.get(str);
        }

        public final void b(boolean z) {
            MediaRouteProviderService.b.a aVar;
            if (this.g) {
                return;
            }
            int i = this.d;
            if ((i & 3) == 3) {
                d(null, this.h, null);
            }
            int i2 = 1;
            if (z) {
                MediaRouteProvider.e eVar = this.b;
                eVar.i(2);
                eVar.e();
                if ((i & 1) == 0 && (aVar = this.e.get()) != null) {
                    if (eVar instanceof a) {
                        eVar = ((a) eVar).g;
                    }
                    String str = this.j;
                    SparseArray<MediaRouteProvider.e> sparseArray = aVar.f;
                    int iIndexOfValue = sparseArray.indexOfValue(eVar);
                    int iKeyAt = iIndexOfValue < 0 ? -1 : sparseArray.keyAt(iIndexOfValue);
                    aVar.f(iKeyAt);
                    if (aVar.b < 4) {
                        aVar.k.put(str, Integer.valueOf(iKeyAt));
                        aVar.j.postDelayed(new y70(aVar, i2, str), 5000L);
                        td1 td1Var = MediaRouteProviderService.b.this.a.h.k;
                        if (td1Var != null) {
                            MediaRouteProviderService.e(aVar.a, 5, 0, 0, aVar.a(td1Var), null);
                        }
                    } else if (iKeyAt < 0) {
                        Log.w("MediaRouteProviderSrv", "releaseControllerByProvider: Can't find the controller. route ID=" + str);
                    } else {
                        MediaRouteProviderService.e(aVar.a, 8, 0, iKeyAt, null, null);
                    }
                }
            }
            this.g = true;
            c.this.notifySessionReleased(this.i);
        }

        public final void c(RoutingSessionInfo routingSessionInfo) {
            if (this.h != null) {
                Log.w("MR2ProviderService", "setSessionInfo: This shouldn't be called after sessionInfo is set");
                return;
            }
            Messenger messenger = new Messenger(new b(c.this, this.i));
            RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(routingSessionInfo);
            Bundle bundle = new Bundle();
            bundle.putParcelable("androidx.mediarouter.media.KEY_MESSENGER", messenger);
            bundle.putString("androidx.mediarouter.media.KEY_SESSION_NAME", routingSessionInfo.getName() != null ? routingSessionInfo.getName().toString() : null);
            this.h = builder.setControlHints(bundle).build();
        }

        public final void d(String str, RoutingSessionInfo routingSessionInfo, RoutingSessionInfo routingSessionInfo2) {
            m8 m8Var;
            MediaRouteProvider.e eVar;
            List<String> listEmptyList = routingSessionInfo == null ? Collections.emptyList() : routingSessionInfo.getSelectedRoutes();
            List listEmptyList2 = routingSessionInfo2 == null ? Collections.emptyList() : routingSessionInfo2.getSelectedRoutes();
            Iterator it = listEmptyList2.iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                m8Var = this.a;
                if (!zHasNext) {
                    break;
                }
                String str2 = (String) it.next();
                if (a(str2) == null) {
                    MediaRouteProvider.e eVarN = (MediaRouteProvider.e) m8Var.get(str2);
                    if (eVarN == null) {
                        c cVar = c.this;
                        if (str == null) {
                            MediaRouteProviderService mediaRouteProviderService = cVar.f.a;
                            eVarN = (mediaRouteProviderService != null ? mediaRouteProviderService.h : null).m(str2);
                        } else {
                            MediaRouteProviderService mediaRouteProviderService2 = cVar.f.a;
                            eVarN = (mediaRouteProviderService2 != null ? mediaRouteProviderService2.h : null).n(str2, str);
                        }
                        if (eVarN != null) {
                            m8Var.put(str2, eVarN);
                        }
                    }
                    eVarN.f();
                }
            }
            for (String str3 : listEmptyList) {
                if (!listEmptyList2.contains(str3) && (eVar = (MediaRouteProvider.e) m8Var.remove(str3)) != null) {
                    eVar.i(0);
                    eVar.e();
                }
            }
        }

        public final void e(d dVar, Collection<MediaRouteProvider.b.C0013b> collection) {
            RoutingSessionInfo routingSessionInfo = this.h;
            if (routingSessionInfo == null) {
                Log.w("MR2ProviderService", "updateSessionInfo: mSessionInfo is null. This shouldn't happen.");
                return;
            }
            c cVar = c.this;
            if (dVar != null && !dVar.a.getBoolean("enabled", true)) {
                cVar.onReleaseSession(0L, this.i);
                return;
            }
            RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(routingSessionInfo);
            if (dVar != null) {
                this.j = dVar.d();
                builder.setName(dVar.e()).setVolume(dVar.f()).setVolumeMax(dVar.h()).setVolumeHandling(dVar.g());
                builder.clearSelectedRoutes();
                if (dVar.b().isEmpty()) {
                    builder.addSelectedRoute(this.j);
                } else {
                    Iterator<String> it = dVar.b().iterator();
                    while (it.hasNext()) {
                        builder.addSelectedRoute(it.next());
                    }
                }
                Bundle controlHints = routingSessionInfo.getControlHints();
                if (controlHints == null) {
                    Log.w("MR2ProviderService", "updateSessionInfo: controlHints is null. This shouldn't happen.");
                    controlHints = new Bundle();
                }
                controlHints.putString("androidx.mediarouter.media.KEY_SESSION_NAME", dVar.e());
                controlHints.putBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE", dVar.a);
                builder.setControlHints(controlHints);
            }
            this.h = builder.build();
            if (collection != null && !collection.isEmpty()) {
                builder.clearSelectedRoutes();
                builder.clearSelectableRoutes();
                builder.clearDeselectableRoutes();
                builder.clearTransferableRoutes();
                boolean z = false;
                for (MediaRouteProvider.b.C0013b c0013b : collection) {
                    String strD = c0013b.a.d();
                    int i = c0013b.b;
                    if (i == 2 || i == 3) {
                        builder.addSelectedRoute(strD);
                        z = true;
                    }
                    if (c0013b.d) {
                        builder.addSelectableRoute(strD);
                    }
                    if (c0013b.c) {
                        builder.addDeselectableRoute(strD);
                    }
                    if (c0013b.e) {
                        builder.addTransferableRoute(strD);
                    }
                }
                if (z) {
                    this.h = builder.build();
                }
            }
            int i2 = c.j;
            if ((this.d & 5) == 5 && dVar != null) {
                d(dVar.d(), routingSessionInfo, this.h);
            }
            boolean z2 = this.f;
            if (z2) {
                cVar.notifySessionUpdated(this.h);
            } else if (z2) {
                Log.w("MR2ProviderService", "notifySessionCreated: Routing session is already created.");
            } else {
                this.f = true;
                cVar.notifySessionCreated(this.c, this.h);
            }
        }
    }

    public c(MediaRouteProviderService.b bVar) {
        this.f = bVar;
    }

    public final String a(C0018c c0018c) {
        String string;
        synchronized (this.e) {
            do {
                string = UUID.randomUUID().toString();
            } while (this.g.containsKey(string));
            c0018c.i = string;
            this.g.put(string, c0018c);
        }
        return string;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final MediaRouteProvider.e b(String str) {
        ArrayList arrayList;
        synchronized (this.e) {
            arrayList = new ArrayList(this.g.values());
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MediaRouteProvider.e eVarA = ((C0018c) it.next()).a(str);
            if (eVarA != null) {
                return eVarA;
            }
        }
        return null;
    }

    public final MediaRouteProvider.b c(String str) {
        MediaRouteProvider.b bVar;
        synchronized (this.e) {
            C0018c c0018c = (C0018c) this.g.get(str);
            bVar = c0018c == null ? null : c0018c.b;
        }
        return bVar;
    }

    public final d d(String str, String str2) {
        MediaRouteProviderService mediaRouteProviderService = this.f.a;
        if ((mediaRouteProviderService == null ? null : mediaRouteProviderService.h) == null || this.i == null) {
            Log.w("MR2ProviderService", str2.concat(": no provider info"));
            return null;
        }
        for (d dVar : this.i.a) {
            if (TextUtils.equals(dVar.d(), str)) {
                return dVar;
            }
        }
        Log.w("MR2ProviderService", str2 + ": Couldn't find a route : " + str);
        return null;
    }

    public final void e(MediaRouteProviderService.b.a aVar, MediaRouteProvider.e eVar, int i, String str, String str2) {
        int i2;
        MediaRouteProvider.b aVar2;
        d dVarD = d(str2, "notifyRouteControllerAdded");
        if (dVarD == null) {
            return;
        }
        if (eVar instanceof MediaRouteProvider.b) {
            aVar2 = (MediaRouteProvider.b) eVar;
            i2 = 6;
        } else {
            i2 = !dVarD.b().isEmpty() ? 2 : 0;
            aVar2 = new a(eVar, str2);
        }
        C0018c c0018c = new C0018c(aVar2, 0L, i2, aVar);
        c0018c.j = str2;
        String strA = a(c0018c);
        this.h.put(i, strA);
        RoutingSessionInfo.Builder volumeMax = new RoutingSessionInfo.Builder(strA, str).setName(dVarD.e()).setVolumeHandling(dVarD.g()).setVolume(dVarD.f()).setVolumeMax(dVarD.h());
        if (dVarD.b().isEmpty()) {
            volumeMax.addSelectedRoute(str2);
        } else {
            Iterator<String> it = dVarD.b().iterator();
            while (it.hasNext()) {
                volumeMax.addSelectedRoute(it.next());
            }
        }
        c0018c.c(volumeMax.build());
    }

    public final void f(int i) {
        C0018c c0018c;
        String str = this.h.get(i);
        if (str == null) {
            return;
        }
        this.h.remove(i);
        synchronized (this.e) {
            c0018c = (C0018c) this.g.remove(str);
        }
        if (c0018c != null) {
            c0018c.b(false);
        }
    }

    public final void g(MediaRouteProvider.b bVar, d dVar, Collection<MediaRouteProvider.b.C0013b> collection) {
        C0018c c0018c;
        synchronized (this.e) {
            Iterator it = ((m8.a) this.g.entrySet()).iterator();
            while (true) {
                m8.d dVar2 = (m8.d) it;
                if (!dVar2.hasNext()) {
                    c0018c = null;
                    break;
                }
                dVar2.next();
                c0018c = (C0018c) dVar2.getValue();
                if (c0018c.b == bVar) {
                    break;
                }
            }
        }
        if (c0018c == null) {
            Log.w("MR2ProviderService", "setDynamicRouteDescriptor: Ignoring unknown controller");
        } else {
            c0018c.e(dVar, collection);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onCreateSession(long j2, String str, String str2, Bundle bundle) {
        int i;
        MediaRouteProvider.b aVar;
        MediaRouteProviderService mediaRouteProviderService = this.f.a;
        MediaRouteProvider mediaRouteProvider = mediaRouteProviderService == null ? null : mediaRouteProviderService.h;
        d dVarD = d(str2, "onCreateSession");
        if (dVarD == null) {
            notifyRequestFailed(j2, 3);
            return;
        }
        if (this.i.b) {
            MediaRouteProvider.b bVarL = mediaRouteProvider.l(str2);
            if (bVarL == null) {
                Log.w("MR2ProviderService", "onCreateSession: Couldn't create a dynamic controller");
                notifyRequestFailed(j2, 1);
                return;
            } else {
                aVar = bVarL;
                i = 7;
            }
        } else {
            MediaRouteProvider.e eVarM = mediaRouteProvider.m(str2);
            if (eVarM == null) {
                Log.w("MR2ProviderService", "onCreateSession: Couldn't create a controller");
                notifyRequestFailed(j2, 1);
                return;
            } else {
                i = dVarD.b().isEmpty() ? 1 : 3;
                aVar = new a(eVarM, str2);
            }
        }
        aVar.f();
        C0018c c0018c = new C0018c(aVar, j2, i, null);
        RoutingSessionInfo.Builder volumeMax = new RoutingSessionInfo.Builder(a(c0018c), str).setName(dVarD.e()).setVolumeHandling(dVarD.g()).setVolume(dVarD.f()).setVolumeMax(dVarD.h());
        if (dVarD.b().isEmpty()) {
            volumeMax.addSelectedRoute(str2);
        } else {
            Iterator<String> it = dVarD.b().iterator();
            while (it.hasNext()) {
                volumeMax.addSelectedRoute(it.next());
            }
        }
        RoutingSessionInfo routingSessionInfoBuild = volumeMax.build();
        c0018c.c(routingSessionInfoBuild);
        if ((i & 6) == 2) {
            c0018c.d(str2, null, routingSessionInfoBuild);
        }
        MediaRouteProviderService.b bVar = this.f;
        Context applicationContext = bVar.a.getApplicationContext();
        Object obj = bz.a;
        aVar.q(applicationContext.getMainExecutor(), bVar.h);
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onDeselectRoute(long j2, String str, String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onDeselectRoute: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        } else {
            if (d(str2, "onDeselectRoute") == null) {
                notifyRequestFailed(j2, 3);
                return;
            }
            MediaRouteProvider.b bVarC = c(str);
            if (bVarC != null) {
                bVarC.o(str2);
            } else {
                Log.w("MR2ProviderService", "onDeselectRoute: Couldn't find a controller");
                notifyRequestFailed(j2, 3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onDiscoveryPreferenceChanged(RouteDiscoveryPreference routeDiscoveryPreference) {
        MediaRouteProviderService.b bVar = this.f;
        ArrayList arrayList = new ArrayList();
        for (String str : routeDiscoveryPreference.getPreferredFeatures()) {
            str.getClass();
            switch (str) {
                case "android.media.route.feature.REMOTE_PLAYBACK":
                    str = "android.media.intent.category.REMOTE_PLAYBACK";
                    break;
                case "android.media.route.feature.LIVE_AUDIO":
                    str = "android.media.intent.category.LIVE_AUDIO";
                    break;
                case "android.media.route.feature.LIVE_VIDEO":
                    str = "android.media.intent.category.LIVE_VIDEO";
                    break;
            }
            arrayList.add(str);
        }
        g.a aVar = new g.a();
        aVar.a(arrayList);
        rd1 rd1Var = new rd1(aVar.c(), routeDiscoveryPreference.shouldPerformActiveScan());
        bVar.getClass();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (!Objects.equals(bVar.d, rd1Var) || rd1Var.b()) {
            bVar.d = rd1Var;
            bVar.e = jElapsedRealtime;
            bVar.g();
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onReleaseSession(long j2, String str) {
        C0018c c0018c;
        if (getSessionInfo(str) == null) {
            return;
        }
        synchronized (this.e) {
            c0018c = (C0018c) this.g.remove(str);
        }
        if (c0018c != null) {
            c0018c.b(true);
        } else {
            Log.w("MR2ProviderService", "onReleaseSession: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onSelectRoute(long j2, String str, String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onSelectRoute: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        } else {
            if (d(str2, "onSelectRoute") == null) {
                notifyRequestFailed(j2, 3);
                return;
            }
            MediaRouteProvider.b bVarC = c(str);
            if (bVarC != null) {
                bVarC.n(str2);
            } else {
                Log.w("MR2ProviderService", "onSelectRoute: Couldn't find a controller");
                notifyRequestFailed(j2, 3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onSetRouteVolume(long j2, String str, int i) {
        MediaRouteProvider.e eVarB = b(str);
        if (eVarB != null) {
            eVarB.g(i);
            return;
        }
        Log.w("MR2ProviderService", "onSetRouteVolume: Couldn't find a controller for routeId=" + str);
        notifyRequestFailed(j2, 3);
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onSetSessionVolume(long j2, String str, int i) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onSetSessionVolume: Couldn't find a session");
            notifyRequestFailed(j2, 4);
            return;
        }
        MediaRouteProvider.b bVarC = c(str);
        if (bVarC != null) {
            bVarC.g(i);
        } else {
            Log.w("MR2ProviderService", "onSetSessionVolume: Couldn't find a controller");
            notifyRequestFailed(j2, 3);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public final void onTransferToRoute(long j2, String str, String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onTransferToRoute: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        } else {
            if (d(str2, "onTransferToRoute") == null) {
                notifyRequestFailed(j2, 3);
                return;
            }
            MediaRouteProvider.b bVarC = c(str);
            if (bVarC != null) {
                bVarC.p(Collections.singletonList(str2));
            } else {
                Log.w("MR2ProviderService", "onTransferToRoute: Couldn't find a controller");
                notifyRequestFailed(j2, 3);
            }
        }
    }

    /* JADX INFO: compiled from: MediaRoute2ProviderServiceAdapter.java */
    public static class a extends MediaRouteProvider.b {
        public final String f;
        public final MediaRouteProvider.e g;

        public a(MediaRouteProvider.e eVar, String str) {
            this.f = str;
            this.g = eVar;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final boolean d(Intent intent, h.c cVar) {
            return this.g.d(intent, cVar);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void e() {
            this.g.e();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void f() {
            this.g.f();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void g(int i) {
            this.g.g(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void i(int i) {
            this.g.i(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void j(int i) {
            this.g.j(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void n(String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void o(String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void p(List<String> list) {
        }
    }
}
