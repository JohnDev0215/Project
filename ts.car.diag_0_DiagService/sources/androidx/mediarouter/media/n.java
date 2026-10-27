package androidx.mediarouter.media;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.r;
import androidx.mediarouter.media.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(30)
class n extends MediaRouteProvider {
    final MediaRouter2 i;
    final a j;
    final Map<MediaRouter2.RoutingController, b> k;
    private final MediaRouter2.RouteCallback l;
    private final MediaRouter2.TransferCallback m;
    private final Handler n;
    private final Executor o;
    private List<MediaRoute2Info> p;

    /* JADX INFO: compiled from: caradapter-bug */
    static abstract class a {
        a() {
        }

        public abstract void a(@NonNull MediaRouteProvider.d dVar);

        public abstract void b(int i);

        public abstract void c(@NonNull String str, int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class b extends MediaRouteProvider.DynamicGroupRouteController {
        final String f;
        final MediaRouter2.RoutingController g;

        @Nullable
        final Messenger h;

        @Nullable
        final Messenger i;
        final SparseArray<u.d> j = new SparseArray<>();
        int k = 0;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends Handler {
            a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                int i2 = message.arg1;
                int i3 = message.arg2;
                Object obj = message.obj;
                Bundle bundlePeekData = message.peekData();
                u.d dVar = b.this.j.get(i2);
                if (dVar == null) {
                    Log.w("MR2Provider", "Pending callback not found for control request.");
                    return;
                }
                b.this.j.remove(i2);
                if (i == 3) {
                    dVar.b((Bundle) obj);
                } else {
                    if (i != 4) {
                        return;
                    }
                    dVar.a(bundlePeekData == null ? null : bundlePeekData.getString("error"), (Bundle) obj);
                }
            }
        }

        b(@NonNull n nVar, @NonNull String str, MediaRouter2.RoutingController routingController) {
            this.f = str;
            this.g = routingController;
            Messenger messengerZ = n.z(routingController);
            this.h = messengerZ;
            this.i = messengerZ == null ? null : new Messenger(new a());
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public boolean d(Intent intent, @Nullable u.d dVar) {
            MediaRouter2.RoutingController routingController = this.g;
            if (routingController != null && !routingController.isReleased() && this.h != null) {
                int i = this.k;
                this.k = i + 1;
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.arg1 = i;
                messageObtain.obj = intent;
                messageObtain.replyTo = this.i;
                try {
                    this.h.send(messageObtain);
                    if (dVar == null) {
                        return true;
                    }
                    this.j.put(i, dVar);
                    return true;
                } catch (DeadObjectException unused) {
                } catch (RemoteException e) {
                    Log.e("MR2Provider", "Could not send control request to service.", e);
                }
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void e() {
            this.g.release();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void g(int i) {
            MediaRouter2.RoutingController routingController = this.g;
            if (routingController == null) {
                return;
            }
            routingController.setVolume(i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void j(int i) {
            MediaRouter2.RoutingController routingController = this.g;
            if (routingController == null) {
                return;
            }
            routingController.setVolume(routingController.getVolume() + i);
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
    private class c extends MediaRouteProvider.d {
        c(@NonNull n nVar, @Nullable String str, String str2) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class d extends MediaRouter2.RouteCallback {
        d() {
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesAdded(@NonNull List<MediaRoute2Info> list) {
            n.this.D();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesChanged(@NonNull List<MediaRoute2Info> list) {
            n.this.D();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesRemoved(@NonNull List<MediaRoute2Info> list) {
            n.this.D();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(30)
    final class e extends MediaRouter2.TransferCallback {
        e() {
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onStop(@NonNull MediaRouter2.RoutingController routingController) {
            n.this.j.a(n.this.k.remove(routingController));
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onTransfer(@NonNull MediaRouter2.RoutingController routingController, @NonNull MediaRouter2.RoutingController routingController2) {
            n.this.k.remove(routingController);
            if (routingController2 == n.this.i.getSystemController()) {
                n.this.j.b(3);
                return;
            }
            List<MediaRoute2Info> selectedRoutes = routingController2.getSelectedRoutes();
            if (selectedRoutes.isEmpty()) {
                Log.w("MR2Provider", "Selected routes are empty. This shouldn't happen.");
                return;
            }
            String id = selectedRoutes.get(0).getId();
            n.this.k.put(routingController2, new b(n.this, id, routingController2));
            n.this.j.c(id, 3);
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onTransferFailure(@NonNull MediaRoute2Info mediaRoute2Info) {
            Log.w("MR2Provider", "Transfer failed. requestedRoute=" + mediaRoute2Info);
        }
    }

    static {
        Log.isLoggable("MR2Provider", 3);
    }

    n(@NonNull Context context, @NonNull a aVar) {
        super(context);
        this.k = new ArrayMap();
        this.l = new d();
        this.m = new e();
        this.p = new ArrayList();
        this.i = MediaRouter2.getInstance(context);
        this.j = aVar;
        final Handler handler = new Handler();
        this.n = handler;
        Objects.requireNonNull(handler);
        this.o = new Executor() { // from class: androidx.mediarouter.media.c
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    @Nullable
    static String B(@Nullable MediaRouteProvider.d dVar) {
        MediaRouter2.RoutingController routingController;
        if ((dVar instanceof b) && (routingController = ((b) dVar).g) != null) {
            return routingController.getId();
        }
        return null;
    }

    static /* synthetic */ boolean C(MediaRoute2Info mediaRoute2Info) {
        return !mediaRoute2Info.isSystemRoute();
    }

    @Nullable
    static Messenger z(@Nullable MediaRouter2.RoutingController routingController) {
        Bundle controlHints;
        if (routingController == null || (controlHints = routingController.getControlHints()) == null) {
            return null;
        }
        return (Messenger) controlHints.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
    }

    @Nullable
    MediaRoute2Info A(@Nullable String str) {
        if (str == null) {
            return null;
        }
        for (MediaRoute2Info mediaRoute2Info : this.p) {
            if (TextUtils.equals(mediaRoute2Info.getId(), str)) {
                return mediaRoute2Info;
            }
        }
        return null;
    }

    protected void D() {
        List<MediaRoute2Info> list = (List) this.i.getRoutes().stream().distinct().filter(new Predicate() { // from class: androidx.mediarouter.media.d
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return n.C((MediaRoute2Info) obj);
            }
        }).collect(Collectors.toList());
        if (list.equals(this.p)) {
            return;
        }
        this.p = list;
        List list2 = (List) list.stream().map(new Function() { // from class: androidx.mediarouter.media.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return v.e((MediaRoute2Info) obj);
            }
        }).filter(new Predicate() { // from class: androidx.mediarouter.media.k
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((p) obj);
            }
        }).collect(Collectors.toList());
        r.a aVar = new r.a();
        aVar.e(true);
        aVar.b(list2);
        x(aVar.c());
    }

    public void E(@NonNull String str) {
        MediaRoute2Info mediaRoute2InfoA = A(str);
        if (mediaRoute2InfoA != null) {
            this.i.transferTo(mediaRoute2InfoA);
            return;
        }
        Log.w("MR2Provider", "Specified route not found. routeId=" + str);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    @Nullable
    public MediaRouteProvider.DynamicGroupRouteController s(@NonNull String str) {
        Iterator<Map.Entry<MediaRouter2.RoutingController, b>> it = this.k.entrySet().iterator();
        while (it.hasNext()) {
            b value = it.next().getValue();
            if (TextUtils.equals(str, value.f)) {
                return value;
            }
        }
        return null;
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    @Nullable
    public MediaRouteProvider.d t(@NonNull String str) {
        return new c(this, str, null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    @Nullable
    public MediaRouteProvider.d u(@NonNull String str, @NonNull String str2) {
        return new c(this, str, str2);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void v(@Nullable q qVar) {
        if (u.f() > 0) {
            this.i.registerRouteCallback(this.o, this.l, v.b(qVar));
            this.i.registerTransferCallback(this.o, this.m);
        } else {
            this.i.unregisterRouteCallback(this.l);
            this.i.unregisterTransferCallback(this.m);
        }
    }
}
