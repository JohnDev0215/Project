package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.RouteDiscoveryPreference;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.d;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.h;
import defpackage.jd1;
import defpackage.rd1;
import defpackage.td1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: MediaRoute2Provider.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends MediaRouteProvider {
    public final MediaRouter2 m;
    public final AbstractC0016a n;
    public final ArrayMap o;
    public final e p;
    public final f q;
    public final b r;
    public final jd1 s;
    public ArrayList t;
    public final ArrayMap u;

    /* JADX INFO: renamed from: androidx.mediarouter.media.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaRoute2Provider.java */
    public static abstract class AbstractC0016a {
    }

    /* JADX INFO: compiled from: MediaRoute2Provider.java */
    public class b extends MediaRouter2.ControllerCallback {
        public b() {
        }

        @Override // android.media.MediaRouter2.ControllerCallback
        public final void onControllerUpdated(MediaRouter2.RoutingController routingController) {
            a.this.t(routingController);
        }
    }

    /* JADX INFO: compiled from: MediaRoute2Provider.java */
    public class c extends MediaRouteProvider.b {
        public final String f;
        public final MediaRouter2.RoutingController g;
        public final Messenger h;
        public final Messenger i;
        public final Handler k;
        public androidx.mediarouter.media.d o;
        public final SparseArray<h.c> j = new SparseArray<>();
        public final AtomicInteger l = new AtomicInteger(1);
        public final androidx.appcompat.widget.a m = new androidx.appcompat.widget.a(5, this);
        public int n = -1;

        /* JADX INFO: renamed from: androidx.mediarouter.media.a$c$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MediaRoute2Provider.java */
        public class HandlerC0017a extends Handler {
            public HandlerC0017a() {
                super(Looper.getMainLooper());
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                int i2 = message.arg1;
                Object obj = message.obj;
                Bundle bundlePeekData = message.peekData();
                c cVar = c.this;
                h.c cVar2 = cVar.j.get(i2);
                if (cVar2 == null) {
                    Log.w("MR2Provider", "Pending callback not found for control request.");
                    return;
                }
                cVar.j.remove(i2);
                if (i == 3) {
                    cVar2.b((Bundle) obj);
                } else {
                    if (i != 4) {
                        return;
                    }
                    cVar2.a(bundlePeekData == null ? null : bundlePeekData.getString("error"), (Bundle) obj);
                }
            }
        }

        public c(MediaRouter2.RoutingController routingController, String str) {
            Bundle controlHints;
            this.g = routingController;
            this.f = str;
            Messenger messenger = (routingController == null || (controlHints = routingController.getControlHints()) == null) ? null : (Messenger) controlHints.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
            this.h = messenger;
            this.i = messenger != null ? new Messenger(new HandlerC0017a()) : null;
            this.k = new Handler(Looper.getMainLooper());
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final boolean d(Intent intent, h.c cVar) {
            Messenger messenger;
            MediaRouter2.RoutingController routingController = this.g;
            if (routingController != null && !routingController.isReleased() && (messenger = this.h) != null) {
                int andIncrement = this.l.getAndIncrement();
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.arg1 = andIncrement;
                messageObtain.obj = intent;
                messageObtain.replyTo = this.i;
                try {
                    messenger.send(messageObtain);
                    if (cVar == null) {
                        return true;
                    }
                    this.j.put(andIncrement, cVar);
                    return true;
                } catch (DeadObjectException unused) {
                } catch (RemoteException e) {
                    Log.e("MR2Provider", "Could not send control request to service.", e);
                }
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void e() {
            this.g.release();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void g(int i) {
            MediaRouter2.RoutingController routingController = this.g;
            if (routingController == null) {
                return;
            }
            routingController.setVolume(i);
            this.n = i;
            Handler handler = this.k;
            androidx.appcompat.widget.a aVar = this.m;
            handler.removeCallbacks(aVar);
            handler.postDelayed(aVar, 1000L);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void j(int i) {
            MediaRouter2.RoutingController routingController = this.g;
            if (routingController == null) {
                return;
            }
            int volume = this.n;
            if (volume < 0) {
                volume = routingController.getVolume();
            }
            int iMax = Math.max(0, Math.min(volume + i, routingController.getVolumeMax()));
            this.n = iMax;
            routingController.setVolume(iMax);
            Handler handler = this.k;
            androidx.appcompat.widget.a aVar = this.m;
            handler.removeCallbacks(aVar);
            handler.postDelayed(aVar, 1000L);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void n(String str) {
            if (str == null || str.isEmpty()) {
                Log.w("MR2Provider", "onAddMemberRoute: Ignoring null or empty routeId.");
                return;
            }
            MediaRoute2Info mediaRoute2InfoR = a.this.r(str);
            if (mediaRoute2InfoR == null) {
                Log.w("MR2Provider", "onAddMemberRoute: Specified route not found. routeId=".concat(str));
            } else {
                this.g.selectRoute(mediaRoute2InfoR);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void o(String str) {
            if (str == null || str.isEmpty()) {
                Log.w("MR2Provider", "onRemoveMemberRoute: Ignoring null or empty routeId.");
                return;
            }
            MediaRoute2Info mediaRoute2InfoR = a.this.r(str);
            if (mediaRoute2InfoR == null) {
                Log.w("MR2Provider", "onRemoveMemberRoute: Specified route not found. routeId=".concat(str));
            } else {
                this.g.deselectRoute(mediaRoute2InfoR);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void p(List<String> list) {
            if (list == null || list.isEmpty()) {
                Log.w("MR2Provider", "onUpdateMemberRoutes: Ignoring null or empty routeIds.");
                return;
            }
            String str = list.get(0);
            a aVar = a.this;
            MediaRoute2Info mediaRoute2InfoR = aVar.r(str);
            if (mediaRoute2InfoR != null) {
                aVar.m.transferTo(mediaRoute2InfoR);
                return;
            }
            Log.w("MR2Provider", "onUpdateMemberRoutes: Specified route not found. routeId=" + str);
        }
    }

    /* JADX INFO: compiled from: MediaRoute2Provider.java */
    public class d extends MediaRouteProvider.e {
        public final String a;
        public final c b;

        public d(String str, c cVar) {
            this.a = str;
            this.b = cVar;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void g(int i) {
            c cVar;
            MediaRouter2.RoutingController routingController;
            Messenger messenger;
            String str = this.a;
            if (str == null || (cVar = this.b) == null || (routingController = cVar.g) == null || routingController.isReleased() || (messenger = cVar.h) == null) {
                return;
            }
            int andIncrement = cVar.l.getAndIncrement();
            Message messageObtain = Message.obtain();
            messageObtain.what = 7;
            messageObtain.arg1 = andIncrement;
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i);
            bundle.putString("routeId", str);
            messageObtain.setData(bundle);
            messageObtain.replyTo = cVar.i;
            try {
                messenger.send(messageObtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2Provider", "Could not send control request to service.", e);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void j(int i) {
            c cVar;
            MediaRouter2.RoutingController routingController;
            Messenger messenger;
            String str = this.a;
            if (str == null || (cVar = this.b) == null || (routingController = cVar.g) == null || routingController.isReleased() || (messenger = cVar.h) == null) {
                return;
            }
            int andIncrement = cVar.l.getAndIncrement();
            Message messageObtain = Message.obtain();
            messageObtain.what = 8;
            messageObtain.arg1 = andIncrement;
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i);
            bundle.putString("routeId", str);
            messageObtain.setData(bundle);
            messageObtain.replyTo = cVar.i;
            try {
                messenger.send(messageObtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2Provider", "Could not send control request to service.", e);
            }
        }
    }

    /* JADX INFO: compiled from: MediaRoute2Provider.java */
    public class e extends MediaRouter2.RouteCallback {
        public e() {
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public final void onRoutesAdded(List<MediaRoute2Info> list) {
            a.this.s();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public final void onRoutesChanged(List<MediaRoute2Info> list) {
            a.this.s();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public final void onRoutesRemoved(List<MediaRoute2Info> list) {
            a.this.s();
        }
    }

    /* JADX INFO: compiled from: MediaRoute2Provider.java */
    public class f extends MediaRouter2.TransferCallback {
        public f() {
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public final void onStop(MediaRouter2.RoutingController routingController) {
            h.C0020h c0020hC;
            MediaRouteProvider.e eVar = (MediaRouteProvider.e) a.this.o.remove(routingController);
            if (eVar == null) {
                Log.w("MR2Provider", "onStop: No matching routeController found. routingController=" + routingController);
            } else {
                h.d dVar = h.d.this;
                if (eVar != dVar.s || dVar.g() == (c0020hC = dVar.c())) {
                    return;
                }
                dVar.k(c0020hC, 2);
            }
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public final void onTransfer(MediaRouter2.RoutingController routingController, MediaRouter2.RoutingController routingController2) {
            h.C0020h next;
            a.this.o.remove(routingController);
            if (routingController2 == a.this.m.getSystemController()) {
                h.d dVar = h.d.this;
                h.C0020h c0020hC = dVar.c();
                if (dVar.g() != c0020hC) {
                    dVar.k(c0020hC, 3);
                    return;
                }
                return;
            }
            List selectedRoutes = routingController2.getSelectedRoutes();
            if (selectedRoutes.isEmpty()) {
                Log.w("MR2Provider", "Selected routes are empty. This shouldn't happen.");
                return;
            }
            String id = ((MediaRoute2Info) selectedRoutes.get(0)).getId();
            a.this.o.put(routingController2, a.this.new c(routingController2, id));
            h.d dVar2 = h.d.this;
            Iterator<h.C0020h> it = dVar2.g.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.d() == dVar2.e && TextUtils.equals(id, next.b)) {
                    break;
                }
            }
            if (next == null) {
                Log.w("MediaRouter", "onSelectRoute: The target RouteInfo is not found for descriptorId=" + id);
            } else {
                dVar2.k(next, 3);
            }
            a.this.t(routingController2);
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public final void onTransferFailure(MediaRoute2Info mediaRoute2Info) {
            Log.w("MR2Provider", "Transfer failed. requestedRoute=" + mediaRoute2Info);
        }
    }

    public a(Context context, h.d.e eVar) {
        super(context);
        this.o = new ArrayMap();
        this.p = new e();
        this.q = new f();
        this.r = new b();
        this.t = new ArrayList();
        this.u = new ArrayMap();
        this.m = MediaRouter2.getInstance(context);
        this.n = eVar;
        this.s = new jd1(0, new Handler(Looper.getMainLooper()));
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final MediaRouteProvider.b l(String str) {
        Iterator it = this.o.entrySet().iterator();
        while (it.hasNext()) {
            c cVar = (c) ((Map.Entry) it.next()).getValue();
            if (TextUtils.equals(str, cVar.f)) {
                return cVar;
            }
        }
        return null;
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final MediaRouteProvider.e m(String str) {
        return new d((String) this.u.get(str), null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final MediaRouteProvider.e n(String str, String str2) {
        String str3 = (String) this.u.get(str);
        for (c cVar : this.o.values()) {
            androidx.mediarouter.media.d dVar = cVar.o;
            if (TextUtils.equals(str2, dVar != null ? dVar.d() : cVar.g.getId())) {
                return new d(str3, cVar);
            }
        }
        Log.w("MR2Provider", "Could not find the matching GroupRouteController. routeId=" + str + ", routeGroupId=" + str2);
        return new d(str3, null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final void o(rd1 rd1Var) {
        int i = h.c == null ? 0 : h.c().y;
        b bVar = this.r;
        f fVar = this.q;
        e eVar = this.p;
        MediaRouter2 mediaRouter2 = this.m;
        if (i <= 0) {
            mediaRouter2.unregisterRouteCallback(eVar);
            mediaRouter2.unregisterTransferCallback(fVar);
            mediaRouter2.unregisterControllerCallback(bVar);
            return;
        }
        h.c();
        if (rd1Var == null) {
            rd1Var = new rd1(g.c, false);
        }
        rd1Var.a();
        ArrayList arrayListB = rd1Var.b.b();
        arrayListB.remove("android.media.intent.category.LIVE_AUDIO");
        g.a aVar = new g.a();
        aVar.a(arrayListB);
        RouteDiscoveryPreference routeDiscoveryPreferenceB = m.b(new rd1(aVar.c(), rd1Var.b()));
        jd1 jd1Var = this.s;
        mediaRouter2.registerRouteCallback(jd1Var, eVar, routeDiscoveryPreferenceB);
        mediaRouter2.registerTransferCallback(jd1Var, fVar);
        mediaRouter2.registerControllerCallback(jd1Var, bVar);
    }

    public final MediaRoute2Info r(String str) {
        if (str == null) {
            return null;
        }
        for (MediaRoute2Info mediaRoute2Info : this.t) {
            if (TextUtils.equals(mediaRoute2Info.getId(), str)) {
                return mediaRoute2Info;
            }
        }
        return null;
    }

    public final void s() {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        for (MediaRoute2Info mediaRoute2Info : this.m.getRoutes()) {
            if (mediaRoute2Info != null && !arraySet.contains(mediaRoute2Info) && !mediaRoute2Info.isSystemRoute()) {
                arraySet.add(mediaRoute2Info);
                arrayList.add(mediaRoute2Info);
            }
        }
        if (arrayList.equals(this.t)) {
            return;
        }
        this.t = arrayList;
        ArrayMap arrayMap = this.u;
        arrayMap.clear();
        for (MediaRoute2Info mediaRoute2Info2 : this.t) {
            Bundle extras = mediaRoute2Info2.getExtras();
            if (extras == null || extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID") == null) {
                Log.w("MR2Provider", "Cannot find the original route Id. route=" + mediaRoute2Info2);
            } else {
                arrayMap.put(mediaRoute2Info2.getId(), extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID"));
            }
        }
        ArrayList<androidx.mediarouter.media.d> arrayList2 = new ArrayList();
        for (MediaRoute2Info mediaRoute2Info3 : this.t) {
            androidx.mediarouter.media.d dVarD = m.d(mediaRoute2Info3);
            if (mediaRoute2Info3 != null) {
                arrayList2.add(dVarD);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        if (!arrayList2.isEmpty()) {
            for (androidx.mediarouter.media.d dVar : arrayList2) {
                if (dVar == null) {
                    throw new IllegalArgumentException("route must not be null");
                }
                if (arrayList3.contains(dVar)) {
                    throw new IllegalArgumentException("route descriptor already added");
                }
                arrayList3.add(dVar);
            }
        }
        p(new td1(arrayList3, true));
    }

    public final void t(MediaRouter2.RoutingController routingController) {
        d.a aVar;
        c cVar = (c) this.o.get(routingController);
        if (cVar == null) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: No matching routeController found. routingController=" + routingController);
            return;
        }
        List selectedRoutes = routingController.getSelectedRoutes();
        if (selectedRoutes.isEmpty()) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: No selected routes. This may happen when the selected routes become invalid.routingController=" + routingController);
            return;
        }
        ArrayList<String> arrayListA = m.a(selectedRoutes);
        androidx.mediarouter.media.d dVarD = m.d((MediaRoute2Info) selectedRoutes.get(0));
        Bundle controlHints = routingController.getControlHints();
        String string = this.e.getString(R$string.mr_dialog_default_group_name);
        androidx.mediarouter.media.d dVar = null;
        if (controlHints != null) {
            try {
                String string2 = controlHints.getString("androidx.mediarouter.media.KEY_SESSION_NAME");
                if (!TextUtils.isEmpty(string2)) {
                    string = string2;
                }
                Bundle bundle = controlHints.getBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE");
                if (bundle != null) {
                    dVar = new androidx.mediarouter.media.d(bundle);
                }
            } catch (Exception e2) {
                Log.w("MR2Provider", "Exception while unparceling control hints.", e2);
            }
        }
        if (dVar == null) {
            aVar = new d.a(routingController.getId(), string);
            Bundle bundle2 = aVar.a;
            bundle2.putInt("connectionState", 2);
            bundle2.putInt("playbackType", 1);
        } else {
            aVar = new d.a(dVar);
        }
        int volume = routingController.getVolume();
        Bundle bundle3 = aVar.a;
        bundle3.putInt("volume", volume);
        bundle3.putInt("volumeMax", routingController.getVolumeMax());
        bundle3.putInt("volumeHandling", routingController.getVolumeHandling());
        ArrayList<IntentFilter> arrayList = aVar.c;
        if (arrayList != null) {
            arrayList.clear();
        }
        dVarD.a();
        aVar.a(dVarD.c);
        ArrayList<String> arrayList2 = aVar.b;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (!arrayListA.isEmpty()) {
            for (String str : arrayListA) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("groupMemberId must not be empty");
                }
                if (aVar.b == null) {
                    aVar.b = new ArrayList<>();
                }
                if (!aVar.b.contains(str)) {
                    aVar.b.add(str);
                }
            }
        }
        androidx.mediarouter.media.d dVarB = aVar.b();
        ArrayList arrayListA2 = m.a(routingController.getSelectableRoutes());
        ArrayList arrayListA3 = m.a(routingController.getDeselectableRoutes());
        td1 td1Var = this.k;
        if (td1Var == null) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: providerDescriptor is not set.");
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        List<androidx.mediarouter.media.d> list = td1Var.a;
        if (!list.isEmpty()) {
            for (androidx.mediarouter.media.d dVar2 : list) {
                String strD = dVar2.d();
                arrayList3.add(new MediaRouteProvider.b.C0013b(dVar2, arrayListA.contains(strD) ? 3 : 1, arrayListA3.contains(strD), arrayListA2.contains(strD), true));
            }
        }
        cVar.o = dVarB;
        cVar.m(dVarB, arrayList3);
    }
}
