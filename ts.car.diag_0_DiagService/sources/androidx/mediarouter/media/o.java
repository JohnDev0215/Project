package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRoute2ProviderService;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(api = 30)
class o extends MediaRoute2ProviderService {
    static final boolean i = Log.isLoggable("MR2ProviderService", 3);
    final MediaRouteProviderService.b d;
    private volatile r h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f977c = new Object();

    @GuardedBy("mLock")
    final Map<String, MediaRouteProvider.DynamicGroupRouteController> e = new b.a.a();
    final SparseArray<String> f = new SparseArray<>();
    final Map<String, Messenger> g = new b.a.a();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends u.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f978a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Intent f979b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Messenger f980c;
        final /* synthetic */ int d;

        a(o oVar, String str, Intent intent, Messenger messenger, int i) {
            this.f978a = str;
            this.f979b = intent;
            this.f980c = messenger;
            this.d = i;
        }

        @Override // androidx.mediarouter.media.u.d
        public void a(String str, Bundle bundle) {
            if (o.i) {
                Log.d("MR2ProviderService", "Route control request failed, sessionId=" + this.f978a + ", intent=" + this.f979b + ", error=" + str + ", data=" + bundle);
            }
            if (str == null) {
                c(this.f980c, 4, this.d, 0, bundle, null);
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("error", str);
            c(this.f980c, 4, this.d, 0, bundle, bundle2);
        }

        @Override // androidx.mediarouter.media.u.d
        public void b(Bundle bundle) {
            if (o.i) {
                Log.d("MR2ProviderService", "Route control request succeeded, sessionId=" + this.f978a + ", intent=" + this.f979b + ", data=" + bundle);
            }
            c(this.f980c, 3, this.d, 0, bundle, null);
        }

        void c(Messenger messenger, int i, int i2, int i3, Object obj, Bundle bundle) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            messageObtain.obj = obj;
            messageObtain.setData(bundle);
            try {
                messenger.send(messageObtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2ProviderService", "Could not send message to the client.", e);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class b extends MediaRouteProvider.DynamicGroupRouteController {
        private final MediaRouteProvider.d f;

        b(MediaRouteProvider.d dVar) {
            this.f = dVar;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public boolean d(Intent intent, u.d dVar) {
            return this.f.d(intent, dVar);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void e() {
            this.f.e();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void f() {
            this.f.f();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void g(int i) {
            this.f.g(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void i(int i) {
            this.f.i(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void j(int i) {
            this.f.j(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void n(@NonNull String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void o(String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void p(@Nullable List<String> list) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final o f981a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f982b;

        c(o oVar, String str) {
            super(Looper.myLooper());
            this.f981a = oVar;
            this.f982b = str;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Messenger messenger = message.replyTo;
            int i = message.what;
            int i2 = message.arg1;
            Object obj = message.obj;
            if (i == 9 && (obj instanceof Intent)) {
                this.f981a.i(messenger, i2, this.f982b, (Intent) obj);
            }
        }
    }

    o(MediaRouteProviderService.b bVar) {
        this.d = bVar;
    }

    private String b(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController) {
        String string;
        synchronized (this.f977c) {
            do {
                string = UUID.randomUUID().toString();
            } while (this.e.containsKey(string));
            this.e.put(string, dynamicGroupRouteController);
        }
        return string;
    }

    private MediaRouteProvider.DynamicGroupRouteController c(String str) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController;
        synchronized (this.f977c) {
            dynamicGroupRouteController = this.e.get(str);
        }
        return dynamicGroupRouteController;
    }

    private MediaRouteProvider d() {
        MediaRouteProviderService mediaRouteProviderServiceV = this.d.v();
        if (mediaRouteProviderServiceV == null) {
            return null;
        }
        return mediaRouteProviderServiceV.c();
    }

    private p e(String str, String str2) {
        if (d() == null || this.h == null) {
            Log.w("MR2ProviderService", str2 + ": no provider info");
            return null;
        }
        for (p pVar : this.h.c()) {
            if (TextUtils.equals(pVar.m(), str)) {
                return pVar;
            }
        }
        Log.w("MR2ProviderService", str2 + ": Couldn't find a route : " + str);
        return null;
    }

    static /* synthetic */ MediaRoute2Info g(MediaRoute2Info mediaRoute2Info) {
        return mediaRoute2Info;
    }

    static /* synthetic */ MediaRoute2Info h(MediaRoute2Info mediaRoute2Info, MediaRoute2Info mediaRoute2Info2) {
        return mediaRoute2Info2;
    }

    void a(MediaRouteProvider.d dVar, int i2, String str, String str2) {
        p pVarE = e(str2, "addRouteController");
        if (pVarE == null) {
            return;
        }
        String strB = b(dVar instanceof MediaRouteProvider.DynamicGroupRouteController ? (MediaRouteProvider.DynamicGroupRouteController) dVar : new b(dVar));
        this.f.put(i2, strB);
        notifySessionCreated(0L, new RoutingSessionInfo.Builder(strB, str).addSelectedRoute(str2).setName(pVarE.p()).setVolumeHandling(pVarE.v()).setVolume(pVarE.u()).setVolumeMax(pVarE.w()).build());
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    void i(Messenger messenger, int i2, String str, Intent intent) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onCustomCommand: Couldn't find a session");
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerC = c(str);
        if (dynamicGroupRouteControllerC != null) {
            dynamicGroupRouteControllerC.d(intent, new a(this, str, intent, messenger, i2));
        } else {
            Log.w("MR2ProviderService", "onControlRequest: Couldn't find a controller");
            notifyRequestFailed(i2, 3);
        }
    }

    void j(int i2) {
        String str = this.f.get(i2);
        if (str == null) {
            return;
        }
        this.f.remove(i2);
        synchronized (this.f977c) {
            this.e.remove(str);
            notifySessionReleased(str);
        }
    }

    public void k(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, p pVar, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
        String key;
        synchronized (this.f977c) {
            Iterator<Map.Entry<String, MediaRouteProvider.DynamicGroupRouteController>> it = this.e.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    key = null;
                    break;
                }
                Map.Entry<String, MediaRouteProvider.DynamicGroupRouteController> next = it.next();
                if (next.getValue() == dynamicGroupRouteController) {
                    key = next.getKey();
                    break;
                }
            }
        }
        if (key == null) {
            Log.w("MR2ProviderService", "setDynamicRouteDescriptor: Couldn't find a routing session");
            return;
        }
        RoutingSessionInfo sessionInfo = getSessionInfo(key);
        if (sessionInfo == null) {
            Log.w("MR2ProviderService", "setDynamicRouteDescriptor: Couldn't find a routing session");
            return;
        }
        RoutingSessionInfo.Builder builderClearTransferableRoutes = new RoutingSessionInfo.Builder(sessionInfo).clearSelectedRoutes().clearSelectableRoutes().clearDeselectableRoutes().clearTransferableRoutes();
        if (pVar != null) {
            builderClearTransferableRoutes.setName(pVar.p()).setVolume(pVar.u()).setVolumeMax(pVar.w()).setVolumeHandling(pVar.v());
        }
        for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor : collection) {
            String strM = dynamicRouteDescriptor.b().m();
            int i2 = dynamicRouteDescriptor.f941b;
            if (i2 == 2 || i2 == 3) {
                builderClearTransferableRoutes.addSelectedRoute(strM);
            }
            if (dynamicRouteDescriptor.d()) {
                builderClearTransferableRoutes.addSelectableRoute(strM);
            }
            if (dynamicRouteDescriptor.f()) {
                builderClearTransferableRoutes.addDeselectableRoute(strM);
            }
            if (dynamicRouteDescriptor.e()) {
                builderClearTransferableRoutes.addTransferableRoute(strM);
            }
        }
        RoutingSessionInfo routingSessionInfoBuild = builderClearTransferableRoutes.build();
        m(pVar.m(), sessionInfo, routingSessionInfoBuild);
        notifySessionUpdated(routingSessionInfoBuild);
    }

    public void l(@Nullable r rVar) {
        this.h = rVar;
        notifyRoutes(((Map) (rVar == null ? Collections.emptyList() : rVar.c()).stream().map(new Function() { // from class: androidx.mediarouter.media.i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return v.d((p) obj);
            }
        }).filter(new Predicate() { // from class: androidx.mediarouter.media.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((MediaRoute2Info) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: androidx.mediarouter.media.g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaRoute2Info) obj).getId();
            }
        }, new Function() { // from class: androidx.mediarouter.media.e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj;
                o.g(mediaRoute2Info);
                return mediaRoute2Info;
            }
        }, new BinaryOperator() { // from class: androidx.mediarouter.media.f
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj2;
                o.h((MediaRoute2Info) obj, mediaRoute2Info);
                return mediaRoute2Info;
            }
        }))).values());
    }

    void m(String str, RoutingSessionInfo routingSessionInfo, RoutingSessionInfo routingSessionInfo2) {
        List<String> listEmptyList = routingSessionInfo == null ? Collections.emptyList() : routingSessionInfo.getSelectedRoutes();
        List<String> listEmptyList2 = routingSessionInfo2 == null ? Collections.emptyList() : routingSessionInfo2.getSelectedRoutes();
        for (String str2 : listEmptyList2) {
            if (this.d.A(str2) == null) {
                this.d.z(str2, str).f();
            }
        }
        for (String str3 : listEmptyList) {
            if (!listEmptyList2.contains(str3)) {
                this.d.D(str3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onCreateSession(long j, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        MediaRouteProvider.DynamicGroupRouteController bVar;
        MediaRouteProvider mediaRouteProviderD = d();
        p pVarE = e(str2, "onCreateSession");
        if (pVarE == null) {
            notifyRequestFailed(j, 3);
            return;
        }
        if (this.h.e()) {
            bVar = mediaRouteProviderD.s(str2);
            if (bVar == null) {
                Log.w("MR2ProviderService", "onCreateSession: Couldn't create a dynamic controller");
                notifyRequestFailed(j, 1);
                return;
            }
        } else {
            MediaRouteProvider.d dVarT = mediaRouteProviderD.t(str2);
            if (dVarT == null) {
                Log.w("MR2ProviderService", "onCreateSession: Couldn't create a controller");
                notifyRequestFailed(j, 1);
                return;
            }
            bVar = new b(dVarT);
        }
        String strB = b(bVar);
        bVar.f();
        RoutingSessionInfo.Builder volumeMax = new RoutingSessionInfo.Builder(strB, str).addSelectedRoute(str2).setName(pVarE.p()).setVolumeHandling(pVarE.v()).setVolume(pVarE.u()).setVolumeMax(pVarE.w());
        Messenger messenger = new Messenger(new c(this, strB));
        this.g.put(strB, messenger);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("androidx.mediarouter.media.KEY_MESSENGER", messenger);
        volumeMax.setControlHints(bundle2).build();
        notifySessionCreated(j, volumeMax.build());
        this.d.E(bVar);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDeselectRoute(long j, @NonNull String str, @NonNull String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onDeselectRoute: Couldn't find a session");
            notifyRequestFailed(j, 4);
        } else {
            if (e(str2, "onDeselectRoute") == null) {
                notifyRequestFailed(j, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerC = c(str);
            if (dynamicGroupRouteControllerC != null) {
                dynamicGroupRouteControllerC.o(str2);
            } else {
                Log.w("MR2ProviderService", "onDeselectRoute: Couldn't find a controller");
                notifyRequestFailed(j, 3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDiscoveryPreferenceChanged(@NonNull RouteDiscoveryPreference routeDiscoveryPreference) {
        t.a aVar = new t.a();
        aVar.a((Collection) routeDiscoveryPreference.getPreferredFeatures().stream().map(new Function() { // from class: androidx.mediarouter.media.l
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return v.a((String) obj);
            }
        }).collect(Collectors.toList()));
        this.d.x(new q(aVar.d(), routeDiscoveryPreference.shouldPerformActiveScan()));
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onReleaseSession(long j, @NonNull String str) {
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerRemove;
        RoutingSessionInfo sessionInfo = getSessionInfo(str);
        if (sessionInfo == null) {
            return;
        }
        m(null, sessionInfo, null);
        synchronized (this.f977c) {
            dynamicGroupRouteControllerRemove = this.e.remove(str);
            this.g.remove(str);
        }
        if (dynamicGroupRouteControllerRemove == null) {
            Log.w("MR2ProviderService", "onReleaseSession: Couldn't find a controller");
            notifyRequestFailed(j, 4);
        } else {
            dynamicGroupRouteControllerRemove.i(0);
            dynamicGroupRouteControllerRemove.e();
            notifySessionReleased(str);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSelectRoute(long j, @NonNull String str, @NonNull String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onSelectRoute: Couldn't find a session");
            notifyRequestFailed(j, 4);
        } else {
            if (e(str2, "onSelectRoute") == null) {
                notifyRequestFailed(j, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerC = c(str);
            if (dynamicGroupRouteControllerC != null) {
                dynamicGroupRouteControllerC.n(str2);
            } else {
                Log.w("MR2ProviderService", "onSelectRoute: Couldn't find a controller");
                notifyRequestFailed(j, 3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetRouteVolume(long j, @NonNull String str, int i2) {
        MediaRouteProvider.d dVarA = this.d.A(str);
        if (dVarA != null) {
            dVarA.g(i2);
            return;
        }
        Log.w("MR2ProviderService", "onSetRouteVolume: Couldn't find a controller for routeId=" + str);
        notifyRequestFailed(j, 3);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetSessionVolume(long j, @NonNull String str, int i2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onSetSessionVolume: Couldn't find a session");
            notifyRequestFailed(j, 4);
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerC = c(str);
        if (dynamicGroupRouteControllerC != null) {
            dynamicGroupRouteControllerC.g(i2);
        } else {
            Log.w("MR2ProviderService", "onSetSessionVolume: Couldn't find a controller");
            notifyRequestFailed(j, 3);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onTransferToRoute(long j, @NonNull String str, @NonNull String str2) {
        if (getSessionInfo(str) == null) {
            Log.w("MR2ProviderService", "onTransferToRoute: Couldn't find a session");
            notifyRequestFailed(j, 4);
        } else {
            if (e(str2, "onTransferToRoute") == null) {
                notifyRequestFailed(j, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerC = c(str);
            if (dynamicGroupRouteControllerC != null) {
                dynamicGroupRouteControllerC.p(Collections.singletonList(str2));
            } else {
                Log.w("MR2ProviderService", "onTransferToRoute: Couldn't find a controller");
                notifyRequestFailed(j, 3);
            }
        }
    }
}
