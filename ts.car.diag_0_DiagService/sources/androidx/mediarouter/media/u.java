package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.mediarouter.media.B;
import androidx.mediarouter.media.C;
import androidx.mediarouter.media.D;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.n;
import androidx.mediarouter.media.t;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final boolean f1000c = Log.isLoggable("MediaRouter", 3);
    static e d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f1001a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<c> f1002b = new ArrayList<>();

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class b {
        public void a(u uVar, f fVar) {
        }

        public void b(u uVar, f fVar) {
        }

        public void c(u uVar, f fVar) {
        }

        public void d(u uVar, g gVar) {
        }

        public abstract void e(u uVar, g gVar);

        public void f(u uVar, g gVar) {
        }

        public void g(u uVar, g gVar) {
        }

        @Deprecated
        public void h(u uVar, g gVar) {
        }

        public void i(@NonNull u uVar, @NonNull g gVar, int i) {
            h(uVar, gVar);
        }

        @Deprecated
        public void j(u uVar, g gVar) {
        }

        public void k(u uVar, g gVar, int i) {
            j(uVar, gVar);
        }

        public void l(u uVar, g gVar) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final u f1003a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f1004b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public t f1005c = t.f996c;
        public int d;

        public c(u uVar, b bVar) {
            this.f1003a = uVar;
            this.f1004b = bVar;
        }

        public boolean a(g gVar) {
            return (this.d & 2) != 0 || gVar.D(this.f1005c);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class d {
        public void a(String str, Bundle bundle) {
        }

        public void b(Bundle bundle) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class e implements D.f, B.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f1006a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1007b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final n f1008c;
        final D l;
        private final boolean m;
        private B n;
        private g o;
        private g p;
        g q;
        MediaRouteProvider.d r;
        private q t;
        private int u;
        private c v;
        private MediaSessionCompat w;
        final ArrayList<WeakReference<u>> d = new ArrayList<>();
        private final ArrayList<g> e = new ArrayList<>();
        private final Map<androidx.core.d.b<String, String>, String> f = new HashMap();
        private final ArrayList<f> g = new ArrayList<>();
        private final ArrayList<f> h = new ArrayList<>();
        final C.a i = new C.a();
        private final C0036e j = new C0036e();
        final b k = new b();
        private final Map<String, MediaRouteProvider.d> s = new HashMap();
        MediaRouteProvider.DynamicGroupRouteController.c x = new a();

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements MediaRouteProvider.DynamicGroupRouteController.c {
            a() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.c
            public void a(@NonNull MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, @Nullable p pVar, @NonNull Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                e eVar = e.this;
                if (dynamicGroupRouteController == eVar.r) {
                    if (pVar != null) {
                        if (TextUtils.equals(eVar.q.e(), pVar.m())) {
                            e eVar2 = e.this;
                            eVar2.I(eVar2.q, pVar);
                        } else {
                            f fVarP = e.this.q.p();
                            String strM = pVar.m();
                            g gVar = new g(fVarP, strM, e.this.e(fVarP, strM));
                            gVar.E(pVar);
                            e.this.x(gVar, 3);
                        }
                    }
                    e.this.q.K(collection);
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private final class b extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final ArrayList<c> f1010a = new ArrayList<>();

            b() {
            }

            private void a(c cVar, int i, Object obj, int i2) {
                u uVar = cVar.f1003a;
                b bVar = cVar.f1004b;
                int i3 = 65280 & i;
                if (i3 != 256) {
                    if (i3 != 512) {
                        return;
                    }
                    f fVar = (f) obj;
                    switch (i) {
                        case 513:
                            bVar.a(uVar, fVar);
                            break;
                        case 514:
                            bVar.c(uVar, fVar);
                            break;
                        case 515:
                            bVar.b(uVar, fVar);
                            break;
                    }
                }
                g gVar = (g) obj;
                if (cVar.a(gVar)) {
                    switch (i) {
                        case 257:
                            bVar.d(uVar, gVar);
                            break;
                        case 258:
                            bVar.g(uVar, gVar);
                            break;
                        case 259:
                            bVar.e(uVar, gVar);
                            break;
                        case 260:
                            bVar.l(uVar, gVar);
                            break;
                        case 261:
                            bVar.f(uVar, gVar);
                            break;
                        case 262:
                            bVar.i(uVar, gVar, i2);
                            break;
                        case 263:
                            bVar.k(uVar, gVar, i2);
                            break;
                    }
                }
            }

            private void d(int i, Object obj) {
                if (i == 262) {
                    e.this.l.D((g) obj);
                }
                switch (i) {
                    case 257:
                        e.this.l.A((g) obj);
                        break;
                    case 258:
                        e.this.l.C((g) obj);
                        break;
                    case 259:
                        e.this.l.B((g) obj);
                        break;
                }
            }

            public void b(int i, Object obj) {
                obtainMessage(i, obj).sendToTarget();
            }

            public void c(int i, Object obj, int i2) {
                Message messageObtainMessage = obtainMessage(i, obj);
                messageObtainMessage.arg1 = i2;
                messageObtainMessage.sendToTarget();
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                Object obj = message.obj;
                int i2 = message.arg1;
                if (i == 259 && e.this.q().j().equals(((g) obj).j())) {
                    e.this.J(true);
                }
                d(i, obj);
                try {
                    int size = e.this.d.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        u uVar = e.this.d.get(size).get();
                        if (uVar == null) {
                            e.this.d.remove(size);
                        } else {
                            this.f1010a.addAll(uVar.f1002b);
                        }
                    }
                    int size2 = this.f1010a.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        a(this.f1010a.get(i3), i, obj, i2);
                    }
                } finally {
                    this.f1010a.clear();
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private final class c {
            public abstract void a();

            public abstract void b(int i, int i2, int i3, @Nullable String str);

            public abstract MediaSessionCompat.Token c();
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private final class d extends n.a {
            private d() {
            }

            @Override // androidx.mediarouter.media.n.a
            public void a(@Nullable MediaRouteProvider.d dVar) {
                if (dVar == null) {
                    return;
                }
                if (dVar == e.this.r) {
                    d(2);
                } else if (u.f1000c) {
                    Log.d("MediaRouter", "A RouteController unrelated to the selected route is released. controller=" + dVar);
                }
            }

            @Override // androidx.mediarouter.media.n.a
            public void b(int i) {
                d(i);
            }

            @Override // androidx.mediarouter.media.n.a
            public void c(@NonNull String str, int i) {
                g next;
                Iterator<g> it = e.this.p().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (next.q() == e.this.f1008c && TextUtils.equals(str, next.e())) {
                        break;
                    }
                }
                if (next != null) {
                    e.this.B(next, i);
                    return;
                }
                Log.w("MediaRouter", "onSelectRoute: The target RouteInfo is not found for descriptorId=" + str);
            }

            void d(int i) {
                g gVarF = e.this.f();
                if (e.this.q() != gVarF) {
                    e.this.B(gVarF, i);
                }
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.u$e$e, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        private final class C0036e extends MediaRouteProvider.a {
            C0036e() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.a
            public void a(@NonNull MediaRouteProvider mediaRouteProvider, r rVar) {
                e.this.H(mediaRouteProvider, rVar);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private final class f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final C f1014a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ e f1015b;

            public void a() {
                this.f1014a.a(this.f1015b.i);
            }
        }

        @SuppressLint({"SyntheticAccessor", "NewApi"})
        e(Context context) {
            String string;
            this.f1006a = context;
            androidx.core.a.a.a.a(context);
            this.m = androidx.core.app.b.a((ActivityManager) context.getSystemService("activity"));
            try {
                string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("androidx.mediarouter.FEATURE");
            } catch (Exception e) {
                Log.w("MediaRouter", "GlobalMediaRouter: Exception while getting feature.", e);
                string = null;
            }
            if (TextUtils.equals(string, "seamless_transfer")) {
                this.f1007b = Build.VERSION.SDK_INT >= 30;
            } else if (TextUtils.equals(string, "dynamic_group")) {
                this.f1007b = false;
            } else {
                this.f1007b = false;
            }
            if (this.f1007b) {
                this.f1008c = new n(this.f1006a, new d());
            } else {
                this.f1008c = null;
            }
            this.l = D.z(context, this);
        }

        @SuppressLint({"NewApi"})
        private void F() {
            g gVar = this.q;
            if (gVar == null) {
                c cVar = this.v;
                if (cVar != null) {
                    cVar.a();
                    return;
                }
                return;
            }
            this.i.f923a = gVar.r();
            this.i.f924b = this.q.t();
            this.i.f925c = this.q.s();
            this.i.d = this.q.m();
            this.i.e = this.q.n();
            if (this.f1007b && this.q.q() == this.f1008c) {
                this.i.f = n.B(this.r);
            } else {
                this.i.f = null;
            }
            int size = this.h.size();
            for (int i = 0; i < size; i++) {
                this.h.get(i).a();
            }
            if (this.v != null) {
                if (this.q == l() || this.q == j()) {
                    this.v.a();
                    return;
                }
                int i2 = this.i.f925c == 1 ? 2 : 0;
                c cVar2 = this.v;
                C.a aVar = this.i;
                cVar2.b(i2, aVar.f924b, aVar.f923a, aVar.f);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void G(f fVar, r rVar) {
            boolean z;
            if (fVar.h(rVar)) {
                int i = 0;
                if (rVar == null || !(rVar.d() || rVar == this.l.o())) {
                    Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + rVar);
                    z = false;
                } else {
                    List<p> listC = rVar.c();
                    ArrayList<androidx.core.d.b> arrayList = new ArrayList();
                    ArrayList<androidx.core.d.b> arrayList2 = new ArrayList();
                    z = false;
                    for (p pVar : listC) {
                        if (pVar == null || !pVar.y()) {
                            Log.w("MediaRouter", "Ignoring invalid system route descriptor: " + pVar);
                        } else {
                            String strM = pVar.m();
                            int iB = fVar.b(strM);
                            if (iB < 0) {
                                g gVar = new g(fVar, strM, e(fVar, strM));
                                int i2 = i + 1;
                                fVar.f1017b.add(i, gVar);
                                this.e.add(gVar);
                                if (pVar.k().size() > 0) {
                                    arrayList.add(new androidx.core.d.b(gVar, pVar));
                                } else {
                                    gVar.E(pVar);
                                    if (u.f1000c) {
                                        Log.d("MediaRouter", "Route added: " + gVar);
                                    }
                                    this.k.b(257, gVar);
                                }
                                i = i2;
                            } else if (iB < i) {
                                Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + pVar);
                            } else {
                                g gVar2 = fVar.f1017b.get(iB);
                                int i3 = i + 1;
                                Collections.swap(fVar.f1017b, iB, i);
                                if (pVar.k().size() > 0) {
                                    arrayList2.add(new androidx.core.d.b(gVar2, pVar));
                                } else if (I(gVar2, pVar) != 0 && gVar2 == this.q) {
                                    z = true;
                                }
                                i = i3;
                            }
                        }
                    }
                    for (androidx.core.d.b bVar : arrayList) {
                        g gVar3 = (g) bVar.f475a;
                        gVar3.E((p) bVar.f476b);
                        if (u.f1000c) {
                            Log.d("MediaRouter", "Route added: " + gVar3);
                        }
                        this.k.b(257, gVar3);
                    }
                    for (androidx.core.d.b bVar2 : arrayList2) {
                        g gVar4 = (g) bVar2.f475a;
                        if (I(gVar4, (p) bVar2.f476b) != 0 && gVar4 == this.q) {
                            z = true;
                        }
                    }
                }
                for (int size = fVar.f1017b.size() - 1; size >= i; size--) {
                    g gVar5 = fVar.f1017b.get(size);
                    gVar5.E(null);
                    this.e.remove(gVar5);
                }
                J(z);
                for (int size2 = fVar.f1017b.size() - 1; size2 >= i; size2--) {
                    g gVarRemove = fVar.f1017b.remove(size2);
                    if (u.f1000c) {
                        Log.d("MediaRouter", "Route removed: " + gVarRemove);
                    }
                    this.k.b(258, gVarRemove);
                }
                if (u.f1000c) {
                    Log.d("MediaRouter", "Provider changed: " + fVar);
                }
                this.k.b(515, fVar);
            }
        }

        private f h(MediaRouteProvider mediaRouteProvider) {
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                if (this.g.get(i).f1016a == mediaRouteProvider) {
                    return this.g.get(i);
                }
            }
            return null;
        }

        private int i(String str) {
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                if (this.e.get(i).f1021c.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        private boolean t(g gVar) {
            return gVar.q() == this.l && gVar.f1020b.equals("DEFAULT_ROUTE");
        }

        private boolean u(g gVar) {
            return gVar.q() == this.l && gVar.I("android.media.intent.category.LIVE_AUDIO") && !gVar.I("android.media.intent.category.LIVE_VIDEO");
        }

        void A(@NonNull g gVar, int i) {
            if (!this.e.contains(gVar)) {
                Log.w("MediaRouter", "Ignoring attempt to select removed route: " + gVar);
                return;
            }
            if (!gVar.g) {
                Log.w("MediaRouter", "Ignoring attempt to select disabled route: " + gVar);
                return;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                MediaRouteProvider mediaRouteProviderQ = gVar.q();
                n nVar = this.f1008c;
                if (mediaRouteProviderQ == nVar && this.q != gVar) {
                    nVar.E(gVar.e());
                    return;
                }
            }
            B(gVar, i);
        }

        void B(@NonNull g gVar, int i) {
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerT;
            if (u.d == null || (this.p != null && gVar.u())) {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                for (int i2 = 3; i2 < stackTrace.length; i2++) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    sb.append(stackTraceElement.getClassName());
                    sb.append(".");
                    sb.append(stackTraceElement.getMethodName());
                    sb.append(":");
                    sb.append(stackTraceElement.getLineNumber());
                    sb.append("  ");
                }
                if (u.d == null) {
                    Log.w("MediaRouter", "setSelectedRouteInternal is called while sGlobal is null: pkgName=" + this.f1006a.getPackageName() + ", callers=" + sb.toString());
                } else {
                    Log.w("MediaRouter", "Default route is selected while a BT route is available: pkgName=" + this.f1006a.getPackageName() + ", callers=" + sb.toString());
                }
            }
            if (this.q == gVar) {
                return;
            }
            g(i);
            if (gVar.p().g()) {
                MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerS = gVar.q().s(gVar.f1020b);
                dynamicGroupRouteControllerT = dynamicGroupRouteControllerS;
                if (dynamicGroupRouteControllerS != null) {
                    dynamicGroupRouteControllerS.q(androidx.core.content.a.g(this.f1006a), this.x);
                    dynamicGroupRouteControllerT = dynamicGroupRouteControllerS;
                }
            } else {
                dynamicGroupRouteControllerT = gVar.q().t(gVar.f1020b);
            }
            this.r = dynamicGroupRouteControllerT;
            this.q = gVar;
            if (dynamicGroupRouteControllerT != null) {
                dynamicGroupRouteControllerT.f();
            }
            if (u.f1000c) {
                Log.d("MediaRouter", "Route selected: " + this.q);
            }
            this.k.c(262, this.q, i);
            if (this.q.x()) {
                List<g> listK = this.q.k();
                this.s.clear();
                for (g gVar2 : listK) {
                    MediaRouteProvider.d dVarU = gVar2.q().u(gVar2.f1020b, this.q.f1020b);
                    dVarU.f();
                    this.s.put(gVar2.f1021c, dVarU);
                }
            }
            F();
        }

        public void C() {
            b(this.l);
            n nVar = this.f1008c;
            if (nVar != null) {
                b(nVar);
            }
            B b2 = new B(this.f1006a, this);
            this.n = b2;
            b2.f();
        }

        void D(@NonNull g gVar) {
            if (!(this.r instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            g.a aVarH = gVar.h();
            if (aVarH == null || !aVarH.c()) {
                Log.w("MediaRouter", "Ignoring attempt to transfer to a non-transferable route.");
            } else {
                ((MediaRouteProvider.DynamicGroupRouteController) this.r).p(Collections.singletonList(gVar.e()));
            }
        }

        public void E() {
            t.a aVar = new t.a();
            int size = this.d.size();
            int i = 0;
            boolean z = false;
            boolean z2 = false;
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                u uVar = this.d.get(size).get();
                if (uVar == null) {
                    this.d.remove(size);
                } else {
                    int size2 = uVar.f1002b.size();
                    i += size2;
                    for (int i2 = 0; i2 < size2; i2++) {
                        c cVar = uVar.f1002b.get(i2);
                        aVar.c(cVar.f1005c);
                        if ((cVar.d & 1) != 0) {
                            z = true;
                            z2 = true;
                        }
                        if ((cVar.d & 4) != 0 && !this.m) {
                            z = true;
                        }
                        if ((cVar.d & 8) != 0) {
                            z = true;
                        }
                    }
                }
            }
            this.u = i;
            t tVarD = z ? aVar.d() : t.f996c;
            q qVar = this.t;
            if (qVar != null && qVar.d().equals(tVarD) && this.t.e() == z2) {
                return;
            }
            if (!tVarD.f() || z2) {
                this.t = new q(tVarD, z2);
            } else if (this.t == null) {
                return;
            } else {
                this.t = null;
            }
            if (u.f1000c) {
                Log.d("MediaRouter", "Updated discovery request: " + this.t);
            }
            if (z && !z2 && this.m) {
                Log.i("MediaRouter", "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
            }
            int size3 = this.g.size();
            for (int i3 = 0; i3 < size3; i3++) {
                this.g.get(i3).f1016a.y(this.t);
            }
        }

        void H(MediaRouteProvider mediaRouteProvider, r rVar) {
            f fVarH = h(mediaRouteProvider);
            if (fVarH != null) {
                G(fVarH, rVar);
            }
        }

        int I(g gVar, p pVar) {
            int iE = gVar.E(pVar);
            if (iE != 0) {
                if ((iE & 1) != 0) {
                    if (u.f1000c) {
                        Log.d("MediaRouter", "Route changed: " + gVar);
                    }
                    this.k.b(259, gVar);
                }
                if ((iE & 2) != 0) {
                    if (u.f1000c) {
                        Log.d("MediaRouter", "Route volume changed: " + gVar);
                    }
                    this.k.b(260, gVar);
                }
                if ((iE & 4) != 0) {
                    if (u.f1000c) {
                        Log.d("MediaRouter", "Route presentation display changed: " + gVar);
                    }
                    this.k.b(261, gVar);
                }
            }
            return iE;
        }

        void J(boolean z) {
            g gVar = this.o;
            if (gVar != null && !gVar.A()) {
                Log.i("MediaRouter", "Clearing the default route because it is no longer selectable: " + this.o);
                this.o = null;
            }
            if (this.o == null && !this.e.isEmpty()) {
                Iterator<g> it = this.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    g next = it.next();
                    if (t(next) && next.A()) {
                        this.o = next;
                        Log.i("MediaRouter", "Found default route: " + this.o);
                        break;
                    }
                }
            }
            g gVar2 = this.p;
            if (gVar2 != null && !gVar2.A()) {
                Log.i("MediaRouter", "Clearing the bluetooth route because it is no longer selectable: " + this.p);
                this.p = null;
            }
            if (this.p == null && !this.e.isEmpty()) {
                Iterator<g> it2 = this.e.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    g next2 = it2.next();
                    if (u(next2) && next2.A()) {
                        this.p = next2;
                        Log.i("MediaRouter", "Found bluetooth route: " + this.p);
                        break;
                    }
                }
            }
            g gVar3 = this.q;
            if (gVar3 == null || !gVar3.w()) {
                Log.i("MediaRouter", "Unselecting the current route because it is no longer selectable: " + this.q);
                B(f(), 0);
                return;
            }
            if (z) {
                if (this.q.x()) {
                    List<g> listK = this.q.k();
                    HashSet hashSet = new HashSet();
                    Iterator<g> it3 = listK.iterator();
                    while (it3.hasNext()) {
                        hashSet.add(it3.next().f1021c);
                    }
                    Iterator<Map.Entry<String, MediaRouteProvider.d>> it4 = this.s.entrySet().iterator();
                    while (it4.hasNext()) {
                        Map.Entry<String, MediaRouteProvider.d> next3 = it4.next();
                        if (!hashSet.contains(next3.getKey())) {
                            MediaRouteProvider.d value = next3.getValue();
                            value.i(0);
                            value.e();
                            it4.remove();
                        }
                    }
                    for (g gVar4 : listK) {
                        if (!this.s.containsKey(gVar4.f1021c)) {
                            MediaRouteProvider.d dVarU = gVar4.q().u(gVar4.f1020b, this.q.f1020b);
                            dVarU.f();
                            this.s.put(gVar4.f1021c, dVarU);
                        }
                    }
                }
                F();
            }
        }

        @Override // androidx.mediarouter.media.B.c
        public void a(MediaRouteProvider mediaRouteProvider) {
            f fVarH = h(mediaRouteProvider);
            if (fVarH != null) {
                mediaRouteProvider.w(null);
                mediaRouteProvider.y(null);
                G(fVarH, null);
                if (u.f1000c) {
                    Log.d("MediaRouter", "Provider removed: " + fVarH);
                }
                this.k.b(514, fVarH);
                this.g.remove(fVarH);
            }
        }

        @Override // androidx.mediarouter.media.B.c
        public void b(MediaRouteProvider mediaRouteProvider) {
            if (h(mediaRouteProvider) == null) {
                f fVar = new f(mediaRouteProvider);
                this.g.add(fVar);
                if (u.f1000c) {
                    Log.d("MediaRouter", "Provider added: " + fVar);
                }
                this.k.b(513, fVar);
                G(fVar, mediaRouteProvider.o());
                mediaRouteProvider.w(this.j);
                mediaRouteProvider.y(this.t);
            }
        }

        @Override // androidx.mediarouter.media.D.f
        public void c(String str) {
            g gVarA;
            this.k.removeMessages(262);
            f fVarH = h(this.l);
            if (fVarH == null || (gVarA = fVarH.a(str)) == null) {
                return;
            }
            gVarA.H();
        }

        void d(@NonNull g gVar) {
            if (!(this.r instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            g.a aVarH = gVar.h();
            if (!this.q.k().contains(gVar) && aVarH != null && aVarH.b()) {
                ((MediaRouteProvider.DynamicGroupRouteController) this.r).n(gVar.e());
                return;
            }
            Log.w("MediaRouter", "Ignoring attemp to add a non-groupable route to dynamic group : " + gVar);
        }

        String e(f fVar, String str) {
            String strFlattenToShortString = fVar.c().flattenToShortString();
            String str2 = strFlattenToShortString + ":" + str;
            if (i(str2) < 0) {
                this.f.put(new androidx.core.d.b<>(strFlattenToShortString, str), str2);
                return str2;
            }
            Log.w("MediaRouter", "Either " + str + " isn't unique in " + strFlattenToShortString + " or we're trying to assign a unique ID for an already added route");
            int i = 2;
            while (true) {
                String str3 = String.format(Locale.US, "%s_%d", str2, Integer.valueOf(i));
                if (i(str3) < 0) {
                    this.f.put(new androidx.core.d.b<>(strFlattenToShortString, str), str3);
                    return str3;
                }
                i++;
            }
        }

        g f() {
            for (g gVar : this.e) {
                if (gVar != this.o && u(gVar) && gVar.A()) {
                    return gVar;
                }
            }
            return this.o;
        }

        void g(int i) {
            if (this.q == null) {
                return;
            }
            if (u.f1000c) {
                Log.d("MediaRouter", "Route unselected: " + this.q + " reason: " + i);
            }
            this.k.c(263, this.q, i);
            MediaRouteProvider.d dVar = this.r;
            if (dVar != null) {
                dVar.i(i);
                this.r.e();
                this.r = null;
            }
            if (!this.s.isEmpty()) {
                for (MediaRouteProvider.d dVar2 : this.s.values()) {
                    dVar2.i(i);
                    dVar2.e();
                }
                this.s.clear();
            }
            this.q = null;
        }

        g j() {
            return this.p;
        }

        int k() {
            return this.u;
        }

        @NonNull
        g l() {
            g gVar = this.o;
            if (gVar != null) {
                return gVar;
            }
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
        }

        public MediaSessionCompat.Token m() {
            c cVar = this.v;
            if (cVar != null) {
                return cVar.c();
            }
            MediaSessionCompat mediaSessionCompat = this.w;
            if (mediaSessionCompat != null) {
                return mediaSessionCompat.b();
            }
            return null;
        }

        public g n(String str) {
            for (g gVar : this.e) {
                if (gVar.f1021c.equals(str)) {
                    return gVar;
                }
            }
            return null;
        }

        public u o(Context context) {
            int size = this.d.size();
            while (true) {
                size--;
                if (size < 0) {
                    u uVar = new u(context);
                    this.d.add(new WeakReference<>(uVar));
                    return uVar;
                }
                u uVar2 = this.d.get(size).get();
                if (uVar2 == null) {
                    this.d.remove(size);
                } else if (uVar2.f1001a == context) {
                    return uVar2;
                }
            }
        }

        public List<g> p() {
            return this.e;
        }

        @NonNull
        g q() {
            g gVar = this.q;
            if (gVar != null) {
                return gVar;
            }
            throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
        }

        String r(f fVar, String str) {
            return this.f.get(new androidx.core.d.b(fVar.c().flattenToShortString(), str));
        }

        public boolean s(t tVar, int i) {
            if (tVar.f()) {
                return false;
            }
            if ((i & 2) == 0 && this.m) {
                return true;
            }
            int size = this.e.size();
            for (int i2 = 0; i2 < size; i2++) {
                g gVar = this.e.get(i2);
                if (((i & 1) == 0 || !gVar.v()) && gVar.D(tVar)) {
                    return true;
                }
            }
            return false;
        }

        boolean v() {
            return this.f1007b;
        }

        void w(@NonNull g gVar) {
            if (!(this.r instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            g.a aVarH = gVar.h();
            if (this.q.k().contains(gVar) && aVarH != null && aVarH.d()) {
                if (this.q.k().size() <= 1) {
                    Log.w("MediaRouter", "Ignoring attempt to remove the last member route.");
                    return;
                } else {
                    ((MediaRouteProvider.DynamicGroupRouteController) this.r).o(gVar.e());
                    return;
                }
            }
            Log.w("MediaRouter", "Ignoring attempt to remove a non-unselectable member route : " + gVar);
        }

        void x(g gVar, int i) {
            g gVar2 = this.q;
            if (gVar2 == gVar) {
                return;
            }
            this.k.c(263, gVar2, i);
            this.q = gVar;
            this.k.c(262, gVar, i);
            F();
        }

        public void y(g gVar, int i) {
            MediaRouteProvider.d dVar;
            MediaRouteProvider.d dVar2;
            if (gVar == this.q && (dVar2 = this.r) != null) {
                dVar2.g(i);
            } else {
                if (this.s.isEmpty() || (dVar = this.s.get(gVar.f1021c)) == null) {
                    return;
                }
                dVar.g(i);
            }
        }

        public void z(g gVar, int i) {
            MediaRouteProvider.d dVar;
            if (gVar != this.q || (dVar = this.r) == null) {
                return;
            }
            dVar.j(i);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaRouteProvider f1016a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final List<g> f1017b = new ArrayList();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final MediaRouteProvider.c f1018c;
        private r d;

        f(MediaRouteProvider mediaRouteProvider) {
            this.f1016a = mediaRouteProvider;
            this.f1018c = mediaRouteProvider.r();
        }

        g a(String str) {
            int size = this.f1017b.size();
            for (int i = 0; i < size; i++) {
                if (this.f1017b.get(i).f1020b.equals(str)) {
                    return this.f1017b.get(i);
                }
            }
            return null;
        }

        int b(String str) {
            int size = this.f1017b.size();
            for (int i = 0; i < size; i++) {
                if (this.f1017b.get(i).f1020b.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        public ComponentName c() {
            return this.f1018c.a();
        }

        public String d() {
            return this.f1018c.b();
        }

        public MediaRouteProvider e() {
            u.d();
            return this.f1016a;
        }

        public List<g> f() {
            u.d();
            return Collections.unmodifiableList(this.f1017b);
        }

        boolean g() {
            r rVar = this.d;
            return rVar != null && rVar.e();
        }

        boolean h(r rVar) {
            if (this.d == rVar) {
                return false;
            }
            this.d = rVar;
            return true;
        }

        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + d() + " }";
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final f f1019a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final String f1020b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final String f1021c;
        private String d;
        private String e;
        private Uri f;
        boolean g;
        private int h;
        private boolean i;
        private int k;
        private int l;
        private int m;
        private int n;
        private int o;
        private int p;
        private Bundle r;
        private IntentSender s;
        p t;
        MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor u;
        private a v;
        private final ArrayList<IntentFilter> j = new ArrayList<>();
        private int q = -1;
        private List<g> w = new ArrayList();

        /* JADX INFO: compiled from: caradapter-bug */
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public class a {
            public a() {
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public int a() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = g.this.u;
                if (dynamicRouteDescriptor != null) {
                    return dynamicRouteDescriptor.c();
                }
                return 1;
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public boolean b() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = g.this.u;
                return dynamicRouteDescriptor != null && dynamicRouteDescriptor.d();
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public boolean c() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = g.this.u;
                return dynamicRouteDescriptor != null && dynamicRouteDescriptor.e();
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public boolean d() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = g.this.u;
                return dynamicRouteDescriptor == null || dynamicRouteDescriptor.f();
            }
        }

        g(f fVar, String str, String str2) {
            this.f1019a = fVar;
            this.f1020b = str;
            this.f1021c = str2;
        }

        private static boolean C(g gVar) {
            return TextUtils.equals(gVar.q().r().b(), "android");
        }

        private boolean y(IntentFilter intentFilter, IntentFilter intentFilter2) {
            int iCountActions;
            if (intentFilter == intentFilter2) {
                return true;
            }
            if (intentFilter == null || intentFilter2 == null || (iCountActions = intentFilter.countActions()) != intentFilter2.countActions()) {
                return false;
            }
            for (int i = 0; i < iCountActions; i++) {
                if (!intentFilter.getAction(i).equals(intentFilter2.getAction(i))) {
                    return false;
                }
            }
            int iCountCategories = intentFilter.countCategories();
            if (iCountCategories != intentFilter2.countCategories()) {
                return false;
            }
            for (int i2 = 0; i2 < iCountCategories; i2++) {
                if (!intentFilter.getCategory(i2).equals(intentFilter2.getCategory(i2))) {
                    return false;
                }
            }
            return true;
        }

        private boolean z(List<IntentFilter> list, List<IntentFilter> list2) {
            if (list == list2) {
                return true;
            }
            if (list == null || list2 == null) {
                return false;
            }
            ListIterator<IntentFilter> listIterator = list.listIterator();
            ListIterator<IntentFilter> listIterator2 = list2.listIterator();
            while (listIterator.hasNext() && listIterator2.hasNext()) {
                if (!y(listIterator.next(), listIterator2.next())) {
                    return false;
                }
            }
            return (listIterator.hasNext() || listIterator2.hasNext()) ? false : true;
        }

        boolean A() {
            return this.t != null && this.g;
        }

        public boolean B() {
            u.d();
            return u.d.q() == this;
        }

        public boolean D(@NonNull t tVar) {
            if (tVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            u.d();
            return tVar.h(this.j);
        }

        int E(p pVar) {
            if (this.t != pVar) {
                return J(pVar);
            }
            return 0;
        }

        public void F(int i) {
            u.d();
            u.d.y(this, Math.min(this.p, Math.max(0, i)));
        }

        public void G(int i) {
            u.d();
            if (i != 0) {
                u.d.z(this, i);
            }
        }

        public void H() {
            u.d();
            u.d.A(this, 3);
        }

        public boolean I(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            u.d();
            int size = this.j.size();
            for (int i = 0; i < size; i++) {
                if (this.j.get(i).hasCategory(str)) {
                    return true;
                }
            }
            return false;
        }

        int J(p pVar) {
            int i;
            this.t = pVar;
            if (pVar == null) {
                return 0;
            }
            if (androidx.core.d.a.a(this.d, pVar.p())) {
                i = 0;
            } else {
                this.d = pVar.p();
                i = 1;
            }
            if (!androidx.core.d.a.a(this.e, pVar.h())) {
                this.e = pVar.h();
                i |= 1;
            }
            if (!androidx.core.d.a.a(this.f, pVar.l())) {
                this.f = pVar.l();
                i |= 1;
            }
            if (this.g != pVar.x()) {
                this.g = pVar.x();
                i |= 1;
            }
            if (this.h != pVar.f()) {
                this.h = pVar.f();
                i |= 1;
            }
            if (!z(this.j, pVar.g())) {
                this.j.clear();
                this.j.addAll(pVar.g());
                i |= 1;
            }
            if (this.k != pVar.r()) {
                this.k = pVar.r();
                i |= 1;
            }
            if (this.l != pVar.q()) {
                this.l = pVar.q();
                i |= 1;
            }
            if (this.m != pVar.i()) {
                this.m = pVar.i();
                i |= 1;
            }
            if (this.n != pVar.v()) {
                this.n = pVar.v();
                i |= 3;
            }
            if (this.o != pVar.u()) {
                this.o = pVar.u();
                i |= 3;
            }
            if (this.p != pVar.w()) {
                this.p = pVar.w();
                i |= 3;
            }
            if (this.q != pVar.s()) {
                this.q = pVar.s();
                i |= 5;
            }
            if (!androidx.core.d.a.a(this.r, pVar.j())) {
                this.r = pVar.j();
                i |= 1;
            }
            if (!androidx.core.d.a.a(this.s, pVar.t())) {
                this.s = pVar.t();
                i |= 1;
            }
            if (this.i != pVar.b()) {
                this.i = pVar.b();
                i |= 5;
            }
            List<String> listK = pVar.k();
            ArrayList arrayList = new ArrayList();
            boolean z = listK.size() != this.w.size();
            Iterator<String> it = listK.iterator();
            while (it.hasNext()) {
                g gVarN = u.d.n(u.d.r(p(), it.next()));
                if (gVarN != null) {
                    arrayList.add(gVarN);
                    if (!z && !this.w.contains(gVarN)) {
                        z = true;
                    }
                }
            }
            if (!z) {
                return i;
            }
            this.w = arrayList;
            return i | 1;
        }

        void K(Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            this.w.clear();
            for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor : collection) {
                g gVarB = b(dynamicRouteDescriptor);
                if (gVarB != null) {
                    gVarB.u = dynamicRouteDescriptor;
                    if (dynamicRouteDescriptor.c() == 2 || dynamicRouteDescriptor.c() == 3) {
                        this.w.add(gVarB);
                    }
                }
            }
            u.d.k.b(259, this);
        }

        public boolean a() {
            return this.i;
        }

        g b(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor) {
            return p().a(dynamicRouteDescriptor.b().m());
        }

        public int c() {
            return this.h;
        }

        @Nullable
        public String d() {
            return this.e;
        }

        String e() {
            return this.f1020b;
        }

        public int f() {
            return this.m;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public MediaRouteProvider.DynamicGroupRouteController g() {
            MediaRouteProvider.d dVar = u.d.r;
            if (dVar instanceof MediaRouteProvider.DynamicGroupRouteController) {
                return (MediaRouteProvider.DynamicGroupRouteController) dVar;
            }
            return null;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public a h() {
            if (this.v == null && this.u != null) {
                this.v = new a();
            }
            return this.v;
        }

        public Uri i() {
            return this.f;
        }

        @NonNull
        public String j() {
            return this.f1021c;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public List<g> k() {
            return Collections.unmodifiableList(this.w);
        }

        public String l() {
            return this.d;
        }

        public int m() {
            return this.l;
        }

        public int n() {
            return this.k;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public int o() {
            return this.q;
        }

        public f p() {
            return this.f1019a;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public MediaRouteProvider q() {
            return this.f1019a.e();
        }

        public int r() {
            return this.o;
        }

        public int s() {
            return this.n;
        }

        public int t() {
            return this.p;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MediaRouter.RouteInfo{ uniqueId=" + this.f1021c + ", name=" + this.d + ", description=" + this.e + ", iconUri=" + this.f + ", enabled=" + this.g + ", connectionState=" + this.h + ", canDisconnect=" + this.i + ", playbackType=" + this.k + ", playbackStream=" + this.l + ", deviceType=" + this.m + ", volumeHandling=" + this.n + ", volume=" + this.o + ", volumeMax=" + this.p + ", presentationDisplayId=" + this.q + ", extras=" + this.r + ", settingsIntent=" + this.s + ", providerPackageName=" + this.f1019a.d());
            if (x()) {
                sb.append(", members=[");
                int size = this.w.size();
                for (int i = 0; i < size; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    if (this.w.get(i) != this) {
                        sb.append(this.w.get(i).j());
                    }
                }
                sb.append(']');
            }
            sb.append(" }");
            return sb.toString();
        }

        public boolean u() {
            u.d();
            return u.d.l() == this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public boolean v() {
            if (u() || this.m == 3) {
                return true;
            }
            return C(this) && I("android.media.intent.category.LIVE_AUDIO") && !I("android.media.intent.category.LIVE_VIDEO");
        }

        public boolean w() {
            return this.g;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public boolean x() {
            return k().size() >= 1;
        }
    }

    u(Context context) {
        this.f1001a = context;
    }

    static void d() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    private int e(b bVar) {
        int size = this.f1002b.size();
        for (int i = 0; i < size; i++) {
            if (this.f1002b.get(i).f1004b == bVar) {
                return i;
            }
        }
        return -1;
    }

    static int f() {
        e eVar = d;
        if (eVar == null) {
            return 0;
        }
        return eVar.k();
    }

    public static u g(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        d();
        if (d == null) {
            e eVar = new e(context.getApplicationContext());
            d = eVar;
            eVar.C();
        }
        return d.o(context);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static boolean l() {
        e eVar = d;
        if (eVar == null) {
            return false;
        }
        return eVar.v();
    }

    public void a(t tVar, b bVar) {
        b(tVar, bVar, 0);
    }

    public void b(@NonNull t tVar, @NonNull b bVar, int i) {
        c cVar;
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        d();
        if (f1000c) {
            Log.d("MediaRouter", "addCallback: selector=" + tVar + ", callback=" + bVar + ", flags=" + Integer.toHexString(i));
        }
        int iE = e(bVar);
        if (iE < 0) {
            cVar = new c(this, bVar);
            this.f1002b.add(cVar);
        } else {
            cVar = this.f1002b.get(iE);
        }
        boolean z = false;
        boolean z2 = true;
        if (i != cVar.d) {
            cVar.d = i;
            z = true;
        }
        if (cVar.f1005c.b(tVar)) {
            z2 = z;
        } else {
            t.a aVar = new t.a(cVar.f1005c);
            aVar.c(tVar);
            cVar.f1005c = aVar.d();
        }
        if (z2) {
            d.E();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void c(g gVar) {
        d();
        d.d(gVar);
    }

    public MediaSessionCompat.Token h() {
        return d.m();
    }

    public List<g> i() {
        d();
        return d.p();
    }

    @NonNull
    public g j() {
        d();
        return d.q();
    }

    public boolean k(@NonNull t tVar, int i) {
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        d();
        return d.s(tVar, i);
    }

    public void m(@NonNull b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        d();
        if (f1000c) {
            Log.d("MediaRouter", "removeCallback: callback=" + bVar);
        }
        int iE = e(bVar);
        if (iE >= 0) {
            this.f1002b.remove(iE);
            d.E();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void n(g gVar) {
        d();
        d.w(gVar);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void o(@NonNull g gVar) {
        d();
        d.D(gVar);
    }

    public void p(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        d();
        g gVarF = d.f();
        if (d.q() != gVarF) {
            d.A(gVarF, i);
        } else {
            e eVar = d;
            eVar.A(eVar.l(), i);
        }
    }
}
