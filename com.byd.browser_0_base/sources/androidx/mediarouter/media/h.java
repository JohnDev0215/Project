package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import androidx.mediarouter.media.a;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.p;
import defpackage.C0175b;
import defpackage.c91;
import defpackage.ce1;
import defpackage.m8;
import defpackage.qv1;
import defpackage.rd1;
import defpackage.td1;
import defpackage.ud1;
import defpackage.wq0;
import defpackage.xo3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: MediaRouter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static d c;
    public final Context a;
    public final ArrayList<b> b = new ArrayList<>();

    /* JADX INFO: compiled from: MediaRouter.java */
    public static final class b {
        public final h a;
        public final a b;
        public androidx.mediarouter.media.g c = androidx.mediarouter.media.g.c;
        public int d;
        public long e;

        public b(h hVar, a aVar) {
            this.a = hVar;
            this.b = aVar;
        }
    }

    /* JADX INFO: compiled from: MediaRouter.java */
    public static final class d implements SystemMediaRouteProvider.e, p.c {
        public C0019d A;
        public MediaSessionCompat B;
        public final b C;
        public final Context a;
        public boolean b;
        public SystemMediaRouteProvider.a c;
        public boolean d;
        public androidx.mediarouter.media.a e;
        public final boolean n;
        public ud1 o;
        public C0020h p;
        public C0020h q;
        public C0020h r;
        public MediaRouteProvider.e s;
        public C0020h t;
        public MediaRouteProvider.b u;
        public rd1 w;
        public rd1 x;
        public int y;
        public f z;
        public final ArrayList<WeakReference<h>> f = new ArrayList<>();
        public final ArrayList<C0020h> g = new ArrayList<>();
        public final HashMap h = new HashMap();
        public final ArrayList<g> i = new ArrayList<>();
        public final ArrayList<g> j = new ArrayList<>();
        public final q k = new q();
        public final f l = new f();
        public final c m = new c();
        public final HashMap v = new HashMap();

        /* JADX INFO: compiled from: MediaRouter.java */
        public class a implements MediaSessionCompat.OnActiveChangeListener {
            public a() {
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.OnActiveChangeListener
            public final void onActiveChanged() {
                d.this.getClass();
            }
        }

        /* JADX INFO: compiled from: MediaRouter.java */
        public class b implements MediaRouteProvider.b.c {
            public b() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.b.c
            public final void c(MediaRouteProvider.b bVar, androidx.mediarouter.media.d dVar, Collection<MediaRouteProvider.b.C0013b> collection) {
                d dVar2 = d.this;
                if (bVar != dVar2.u || dVar == null) {
                    if (bVar == dVar2.s) {
                        if (dVar != null) {
                            dVar2.o(dVar2.r, dVar);
                        }
                        dVar2.r.p(collection);
                        return;
                    }
                    return;
                }
                g gVar = dVar2.t.a;
                String strD = dVar.d();
                C0020h c0020h = new C0020h(gVar, strD, dVar2.b(gVar, strD));
                c0020h.k(dVar);
                if (dVar2.r == c0020h) {
                    return;
                }
                dVar2.i(dVar2, c0020h, dVar2.u, 3, dVar2.t, collection);
                dVar2.t = null;
                dVar2.u = null;
            }
        }

        /* JADX INFO: compiled from: MediaRouter.java */
        public final class c extends Handler {
            public final ArrayList<b> a = new ArrayList<>();
            public final ArrayList b = new ArrayList();

            public c() {
            }

            public static void a(b bVar, int i, Object obj) {
                boolean z;
                h hVar = bVar.a;
                int i2 = 65280 & i;
                a aVar = bVar.b;
                if (i2 != 256) {
                    if (i2 != 512) {
                        if (i2 == 768 && i == 769) {
                            aVar.j((ce1) obj);
                            return;
                        }
                        return;
                    }
                    switch (i) {
                        case 513:
                            aVar.a();
                            break;
                        case 514:
                            aVar.c();
                            break;
                        case 515:
                            aVar.b();
                            break;
                    }
                }
                C0020h c0020h = (i == 264 || i == 262) ? (C0020h) ((qv1) obj).b : (C0020h) obj;
                if (i == 264 || i == 262) {
                }
                if (c0020h != null) {
                    if ((bVar.d & 2) != 0 || c0020h.j(bVar.c)) {
                        z = true;
                    } else {
                        h.c();
                        z = false;
                    }
                    if (z) {
                        switch (i) {
                            case 257:
                                aVar.d();
                                break;
                            case 258:
                                aVar.f();
                                break;
                            case 259:
                                aVar.e(c0020h);
                                break;
                            case 260:
                                aVar.i(c0020h);
                                break;
                            case 261:
                                aVar.getClass();
                                break;
                            case 262:
                                aVar.g(c0020h);
                                break;
                            case 263:
                                aVar.h();
                                break;
                            case 264:
                                aVar.g(c0020h);
                                break;
                        }
                    }
                }
            }

            public final void b(int i, Object obj) {
                obtainMessage(i, obj).sendToTarget();
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int iU;
                ArrayList<b> arrayList = this.a;
                int i = message.what;
                Object obj = message.obj;
                d dVar = d.this;
                if (i == 259 && dVar.g().c.equals(((C0020h) obj).c)) {
                    dVar.p(true);
                }
                ArrayList arrayList2 = this.b;
                if (i == 262) {
                    C0020h c0020h = (C0020h) ((qv1) obj).b;
                    dVar.c.A(c0020h);
                    if (dVar.p != null && c0020h.f()) {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            dVar.c.z((C0020h) it.next());
                        }
                        arrayList2.clear();
                    }
                } else if (i != 264) {
                    switch (i) {
                        case 257:
                            dVar.c.y((C0020h) obj);
                            break;
                        case 258:
                            dVar.c.z((C0020h) obj);
                            break;
                        case 259:
                            SystemMediaRouteProvider.a aVar = dVar.c;
                            C0020h c0020h2 = (C0020h) obj;
                            aVar.getClass();
                            if (c0020h2.d() != aVar && (iU = aVar.u(c0020h2)) >= 0) {
                                aVar.F(aVar.v.get(iU));
                            }
                            break;
                    }
                } else {
                    C0020h c0020h3 = (C0020h) ((qv1) obj).b;
                    arrayList2.add(c0020h3);
                    dVar.c.y(c0020h3);
                    dVar.c.A(c0020h3);
                }
                try {
                    int size = dVar.f.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            int size2 = arrayList.size();
                            for (int i2 = 0; i2 < size2; i2++) {
                                a(arrayList.get(i2), i, obj);
                            }
                            return;
                        }
                        ArrayList<WeakReference<h>> arrayList3 = dVar.f;
                        h hVar = arrayList3.get(size).get();
                        if (hVar == null) {
                            arrayList3.remove(size);
                        } else {
                            arrayList.addAll(hVar.b);
                        }
                    }
                } finally {
                    arrayList.clear();
                }
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.h$d$d, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MediaRouter.java */
        public final class C0019d {
            public final MediaSessionCompat a;
            public l b;

            public C0019d(MediaSessionCompat mediaSessionCompat) {
                this.a = mediaSessionCompat;
            }

            public final void a() {
                MediaSessionCompat mediaSessionCompat = this.a;
                if (mediaSessionCompat != null) {
                    mediaSessionCompat.setPlaybackToLocal(d.this.k.d);
                    this.b = null;
                }
            }
        }

        /* JADX INFO: compiled from: MediaRouter.java */
        public final class e extends a.AbstractC0016a {
            public e() {
            }
        }

        /* JADX INFO: compiled from: MediaRouter.java */
        public final class f extends MediaRouteProvider.a {
            public f() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.a
            public final void a(MediaRouteProvider mediaRouteProvider, td1 td1Var) {
                d dVar = d.this;
                g gVarE = dVar.e(mediaRouteProvider);
                if (gVarE != null) {
                    dVar.n(gVarE, td1Var);
                }
            }
        }

        /* JADX INFO: compiled from: MediaRouter.java */
        public final class g {
            public g() {
                throw null;
            }
        }

        public d(Context context) {
            new a();
            this.C = new b();
            this.a = context;
            this.n = ((ActivityManager) context.getSystemService("activity")).isLowRamDevice();
        }

        public final void a(MediaRouteProvider mediaRouteProvider) {
            if (e(mediaRouteProvider) == null) {
                g gVar = new g(mediaRouteProvider);
                this.i.add(gVar);
                this.m.b(513, gVar);
                n(gVar, mediaRouteProvider.k);
                h.b();
                mediaRouteProvider.h = this.l;
                mediaRouteProvider.q(this.w);
            }
        }

        public final String b(g gVar, String str) {
            String strFlattenToShortString = gVar.c.a.flattenToShortString();
            String strK = C0175b.k(strFlattenToShortString, ":", str);
            int iF = f(strK);
            HashMap map = this.h;
            if (iF < 0) {
                map.put(new qv1(strFlattenToShortString, str), strK);
                return strK;
            }
            Log.w("MediaRouter", "Either " + str + " isn't unique in " + strFlattenToShortString + " or we're trying to assign a unique ID for an already added route");
            int i = 2;
            while (true) {
                String str2 = String.format(Locale.US, "%s_%d", strK, Integer.valueOf(i));
                if (f(str2) < 0) {
                    map.put(new qv1(strFlattenToShortString, str), str2);
                    return str2;
                }
                i++;
            }
        }

        public final C0020h c() {
            for (C0020h c0020h : this.g) {
                if (c0020h != this.p) {
                    if ((c0020h.d() == this.c && c0020h.o("android.media.intent.category.LIVE_AUDIO") && !c0020h.o("android.media.intent.category.LIVE_VIDEO")) && c0020h.h()) {
                        return c0020h;
                    }
                }
            }
            return this.p;
        }

        @SuppressLint({"NewApi", "SyntheticAccessor"})
        public final void d() {
            if (this.b) {
                return;
            }
            this.b = true;
            int i = Build.VERSION.SDK_INT;
            Context context = this.a;
            if (i >= 30) {
                int i2 = MediaTransferReceiver.a;
                Intent intent = new Intent(context, (Class<?>) MediaTransferReceiver.class);
                intent.setPackage(context.getPackageName());
                this.d = context.getPackageManager().queryBroadcastReceivers(intent, 0).size() > 0;
            } else {
                this.d = false;
            }
            if (this.d) {
                this.e = new androidx.mediarouter.media.a(context, new e());
            } else {
                this.e = null;
            }
            this.c = new SystemMediaRouteProvider.a(context, this);
            this.o = new ud1(new i(this));
            a(this.c);
            androidx.mediarouter.media.a aVar = this.e;
            if (aVar != null) {
                a(aVar);
            }
            p pVar = new p(context, this);
            if (pVar.f) {
                return;
            }
            pVar.f = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
            intentFilter.addDataScheme("package");
            p.a aVar2 = pVar.g;
            Handler handler = pVar.c;
            if (i < 33) {
                pVar.a.registerReceiver(aVar2, intentFilter, null, handler);
            } else {
                pVar.a.registerReceiver(aVar2, intentFilter, null, handler, 4);
            }
            handler.post(pVar.h);
        }

        public final g e(MediaRouteProvider mediaRouteProvider) {
            ArrayList<g> arrayList = this.i;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).a == mediaRouteProvider) {
                    return arrayList.get(i);
                }
            }
            return null;
        }

        public final int f(String str) {
            ArrayList<C0020h> arrayList = this.g;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).c.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        public final C0020h g() {
            C0020h c0020h = this.r;
            if (c0020h != null) {
                return c0020h;
            }
            throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
        }

        public final void h() {
            if (this.r.g()) {
                List<C0020h> listC = this.r.c();
                HashSet hashSet = new HashSet();
                Iterator<C0020h> it = listC.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().c);
                }
                HashMap map = this.v;
                Iterator it2 = map.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it2.next();
                    if (!hashSet.contains(entry.getKey())) {
                        MediaRouteProvider.e eVar = (MediaRouteProvider.e) entry.getValue();
                        eVar.i(0);
                        eVar.e();
                        it2.remove();
                    }
                }
                for (C0020h c0020h : listC) {
                    if (!map.containsKey(c0020h.c)) {
                        MediaRouteProvider.e eVarN = c0020h.d().n(c0020h.b, this.r.b);
                        eVarN.f();
                        map.put(c0020h.c, eVarN);
                    }
                }
            }
        }

        public final void i(d dVar, C0020h c0020h, MediaRouteProvider.e eVar, int i, C0020h c0020h2, Collection<MediaRouteProvider.b.C0013b> collection) {
            f fVar = this.z;
            if (fVar != null) {
                if (!fVar.i && !fVar.j) {
                    fVar.j = true;
                    MediaRouteProvider.e eVar2 = fVar.a;
                    if (eVar2 != null) {
                        eVar2.i(0);
                        eVar2.e();
                    }
                }
                this.z = null;
            }
            f fVar2 = new f(dVar, c0020h, eVar, i, c0020h2, collection);
            this.z = fVar2;
            fVar2.a();
        }

        public final void j(C0020h c0020h, int i) {
            if (!this.g.contains(c0020h)) {
                Log.w("MediaRouter", "Ignoring attempt to select removed route: " + c0020h);
                return;
            }
            if (!c0020h.g) {
                Log.w("MediaRouter", "Ignoring attempt to select disabled route: " + c0020h);
                return;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                MediaRouteProvider mediaRouteProviderD = c0020h.d();
                androidx.mediarouter.media.a aVar = this.e;
                if (mediaRouteProviderD == aVar && this.r != c0020h) {
                    String str = c0020h.b;
                    MediaRoute2Info mediaRoute2InfoR = aVar.r(str);
                    if (mediaRoute2InfoR != null) {
                        aVar.m.transferTo(mediaRoute2InfoR);
                        return;
                    }
                    Log.w("MR2Provider", "transferTo: Specified route not found. routeId=" + str);
                    return;
                }
            }
            k(c0020h, i);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void k(androidx.mediarouter.media.h.C0020h r13, int r14) {
            /*
                Method dump skipped, instruction units count: 309
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.h.d.k(androidx.mediarouter.media.h$h, int):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00bd  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void l() {
            /*
                Method dump skipped, instruction units count: 310
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.h.d.l():void");
        }

        @SuppressLint({"NewApi"})
        public final void m() {
            MediaRouter2.RoutingController routingController;
            C0020h c0020h = this.r;
            if (c0020h == null) {
                C0019d c0019d = this.A;
                if (c0019d != null) {
                    c0019d.a();
                    return;
                }
                return;
            }
            int i = c0020h.o;
            q qVar = this.k;
            qVar.a = i;
            qVar.b = c0020h.p;
            qVar.c = c0020h.e();
            C0020h c0020h2 = this.r;
            qVar.d = c0020h2.l;
            int i2 = c0020h2.k;
            qVar.getClass();
            if (this.d && c0020h2.d() == this.e) {
                MediaRouteProvider.e eVar = this.s;
                qVar.e = ((eVar instanceof a.c) && (routingController = ((a.c) eVar).g) != null) ? routingController.getId() : null;
            } else {
                qVar.e = null;
            }
            ArrayList<g> arrayList = this.j;
            if (arrayList.size() > 0) {
                arrayList.get(0).getClass();
                throw null;
            }
            C0019d c0019d2 = this.A;
            if (c0019d2 != null) {
                C0020h c0020h3 = this.r;
                C0020h c0020h4 = this.p;
                if (c0020h4 == null) {
                    throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
                }
                if (c0020h3 == c0020h4 || c0020h3 == this.q) {
                    c0019d2.a();
                    return;
                }
                int i3 = qVar.c == 1 ? 2 : 0;
                int i4 = qVar.b;
                int i5 = qVar.a;
                String str = qVar.e;
                MediaSessionCompat mediaSessionCompat = c0019d2.a;
                if (mediaSessionCompat != null) {
                    l lVar = c0019d2.b;
                    if (lVar == null || i3 != 0 || i4 != 0) {
                        l lVar2 = new l(c0019d2, i3, i4, i5, str);
                        c0019d2.b = lVar2;
                        mediaSessionCompat.setPlaybackToRemote(lVar2);
                    } else {
                        lVar.d = i5;
                        lVar.a().setCurrentVolume(i5);
                        xo3.a aVar = lVar.e;
                        if (aVar != null) {
                            aVar.onVolumeChanged(lVar);
                        }
                    }
                }
            }
        }

        public final void n(g gVar, td1 td1Var) {
            boolean z;
            boolean z2;
            int i;
            if (gVar.d != td1Var) {
                gVar.d = td1Var;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                ArrayList<C0020h> arrayList = this.g;
                ArrayList arrayList2 = gVar.b;
                c cVar = this.m;
                if (td1Var == null || !(td1Var.b() || td1Var == this.c.k)) {
                    Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + td1Var);
                    z2 = false;
                    i = 0;
                } else {
                    ArrayList<qv1> arrayList3 = new ArrayList();
                    ArrayList<qv1> arrayList4 = new ArrayList();
                    boolean z3 = false;
                    i = 0;
                    for (androidx.mediarouter.media.d dVar : td1Var.a) {
                        if (dVar == null || !dVar.i()) {
                            Log.w("MediaRouter", "Ignoring invalid system route descriptor: " + dVar);
                        } else {
                            String strD = dVar.d();
                            int size = arrayList2.size();
                            int i2 = 0;
                            while (true) {
                                if (i2 >= size) {
                                    i2 = -1;
                                    break;
                                } else if (((C0020h) arrayList2.get(i2)).b.equals(strD)) {
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                            if (i2 < 0) {
                                C0020h c0020h = new C0020h(gVar, strD, b(gVar, strD));
                                int i3 = i + 1;
                                arrayList2.add(i, c0020h);
                                arrayList.add(c0020h);
                                if (dVar.b().size() > 0) {
                                    arrayList3.add(new qv1(c0020h, dVar));
                                } else {
                                    c0020h.k(dVar);
                                    cVar.b(257, c0020h);
                                }
                                i = i3;
                            } else if (i2 < i) {
                                Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + dVar);
                            } else {
                                C0020h c0020h2 = (C0020h) arrayList2.get(i2);
                                int i4 = i + 1;
                                Collections.swap(arrayList2, i2, i);
                                if (dVar.b().size() > 0) {
                                    arrayList4.add(new qv1(c0020h2, dVar));
                                } else if (o(c0020h2, dVar) != 0 && c0020h2 == this.r) {
                                    i = i4;
                                    z3 = true;
                                }
                                i = i4;
                            }
                        }
                    }
                    for (qv1 qv1Var : arrayList3) {
                        C0020h c0020h3 = (C0020h) qv1Var.a;
                        c0020h3.k((androidx.mediarouter.media.d) qv1Var.b);
                        cVar.b(257, c0020h3);
                    }
                    z2 = z3;
                    for (qv1 qv1Var2 : arrayList4) {
                        C0020h c0020h4 = (C0020h) qv1Var2.a;
                        if (o(c0020h4, (androidx.mediarouter.media.d) qv1Var2.b) != 0 && c0020h4 == this.r) {
                            z2 = true;
                        }
                    }
                }
                for (int size2 = arrayList2.size() - 1; size2 >= i; size2--) {
                    C0020h c0020h5 = (C0020h) arrayList2.get(size2);
                    c0020h5.k(null);
                    arrayList.remove(c0020h5);
                }
                p(z2);
                for (int size3 = arrayList2.size() - 1; size3 >= i; size3--) {
                    cVar.b(258, (C0020h) arrayList2.remove(size3));
                }
                cVar.b(515, gVar);
            }
        }

        public final int o(C0020h c0020h, androidx.mediarouter.media.d dVar) {
            int iK = c0020h.k(dVar);
            if (iK != 0) {
                int i = iK & 1;
                c cVar = this.m;
                if (i != 0) {
                    d dVar2 = h.c;
                    cVar.b(259, c0020h);
                }
                if ((iK & 2) != 0) {
                    d dVar3 = h.c;
                    cVar.b(260, c0020h);
                }
                if ((iK & 4) != 0) {
                    d dVar4 = h.c;
                    cVar.b(261, c0020h);
                }
            }
            return iK;
        }

        public final void p(boolean z) {
            C0020h c0020h = this.p;
            if (c0020h != null && !c0020h.h()) {
                Log.i("MediaRouter", "Clearing the default route because it is no longer selectable: " + this.p);
                this.p = null;
            }
            C0020h c0020h2 = this.p;
            ArrayList<C0020h> arrayList = this.g;
            if (c0020h2 == null && !arrayList.isEmpty()) {
                Iterator<C0020h> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    C0020h next = it.next();
                    if ((next.d() == this.c && next.b.equals("DEFAULT_ROUTE")) && next.h()) {
                        this.p = next;
                        Log.i("MediaRouter", "Found default route: " + this.p);
                        break;
                    }
                }
            }
            C0020h c0020h3 = this.q;
            if (c0020h3 != null && !c0020h3.h()) {
                Log.i("MediaRouter", "Clearing the bluetooth route because it is no longer selectable: " + this.q);
                this.q = null;
            }
            if (this.q == null && !arrayList.isEmpty()) {
                Iterator<C0020h> it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    C0020h next2 = it2.next();
                    if ((next2.d() == this.c && next2.o("android.media.intent.category.LIVE_AUDIO") && !next2.o("android.media.intent.category.LIVE_VIDEO")) && next2.h()) {
                        this.q = next2;
                        Log.i("MediaRouter", "Found bluetooth route: " + this.q);
                        break;
                    }
                }
            }
            C0020h c0020h4 = this.r;
            if (c0020h4 == null || !c0020h4.g) {
                Log.i("MediaRouter", "Unselecting the current route because it is no longer selectable: " + this.r);
                k(c(), 0);
                return;
            }
            if (z) {
                h();
                m();
            }
        }
    }

    /* JADX INFO: compiled from: MediaRouter.java */
    public interface e {
    }

    /* JADX INFO: compiled from: MediaRouter.java */
    public static final class f {
        public final MediaRouteProvider.e a;
        public final int b;
        public final C0020h c;
        public final C0020h d;
        public final C0020h e;
        public final ArrayList f;
        public final WeakReference<d> g;
        public c91<Void> h = null;
        public boolean i = false;
        public boolean j = false;

        public f(d dVar, C0020h c0020h, MediaRouteProvider.e eVar, int i, C0020h c0020h2, Collection<MediaRouteProvider.b.C0013b> collection) {
            this.g = new WeakReference<>(dVar);
            this.d = c0020h;
            this.a = eVar;
            this.b = i;
            this.c = dVar.r;
            this.e = c0020h2;
            this.f = collection != null ? new ArrayList(collection) : null;
            dVar.m.postDelayed(new wq0(6, this), 15000L);
        }

        public final void a() {
            c91<Void> c91Var;
            h.b();
            if (this.i || this.j) {
                return;
            }
            WeakReference<d> weakReference = this.g;
            d dVar = weakReference.get();
            MediaRouteProvider.e eVar = this.a;
            if (dVar == null || dVar.z != this || ((c91Var = this.h) != null && c91Var.isCancelled())) {
                if (this.i || this.j) {
                    return;
                }
                this.j = true;
                if (eVar != null) {
                    eVar.i(0);
                    eVar.e();
                    return;
                }
                return;
            }
            this.i = true;
            dVar.z = null;
            d dVar2 = weakReference.get();
            int i = this.b;
            C0020h c0020h = this.c;
            if (dVar2 != null && dVar2.r == c0020h) {
                Message messageObtainMessage = dVar2.m.obtainMessage(263, c0020h);
                messageObtainMessage.arg1 = i;
                messageObtainMessage.sendToTarget();
                MediaRouteProvider.e eVar2 = dVar2.s;
                if (eVar2 != null) {
                    eVar2.i(i);
                    dVar2.s.e();
                }
                HashMap map = dVar2.v;
                if (!map.isEmpty()) {
                    for (MediaRouteProvider.e eVar3 : map.values()) {
                        eVar3.i(i);
                        eVar3.e();
                    }
                    map.clear();
                }
                dVar2.s = null;
            }
            d dVar3 = weakReference.get();
            if (dVar3 == null) {
                return;
            }
            C0020h c0020h2 = this.d;
            dVar3.r = c0020h2;
            dVar3.s = eVar;
            d.c cVar = dVar3.m;
            C0020h c0020h3 = this.e;
            if (c0020h3 == null) {
                Message messageObtainMessage2 = cVar.obtainMessage(262, new qv1(c0020h, c0020h2));
                messageObtainMessage2.arg1 = i;
                messageObtainMessage2.sendToTarget();
            } else {
                Message messageObtainMessage3 = cVar.obtainMessage(264, new qv1(c0020h3, c0020h2));
                messageObtainMessage3.arg1 = i;
                messageObtainMessage3.sendToTarget();
            }
            dVar3.v.clear();
            dVar3.h();
            dVar3.m();
            ArrayList arrayList = this.f;
            if (arrayList != null) {
                dVar3.r.p(arrayList);
            }
        }
    }

    /* JADX INFO: compiled from: MediaRouter.java */
    public static final class g {
        public final MediaRouteProvider a;
        public final ArrayList b = new ArrayList();
        public final MediaRouteProvider.d c;
        public td1 d;

        public g(MediaRouteProvider mediaRouteProvider) {
            this.a = mediaRouteProvider;
            this.c = mediaRouteProvider.f;
        }

        public final C0020h a(String str) {
            ArrayList arrayList = this.b;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((C0020h) arrayList.get(i)).b.equals(str)) {
                    return (C0020h) arrayList.get(i);
                }
            }
            return null;
        }

        public final String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + this.c.a.getPackageName() + " }";
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.media.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaRouter.java */
    public static class C0020h {
        public final g a;
        public final String b;
        public final String c;
        public String d;
        public String e;
        public Uri f;
        public boolean g;
        public int h;
        public boolean i;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public Bundle r;
        public IntentSender s;
        public androidx.mediarouter.media.d t;
        public m8 v;
        public final ArrayList<IntentFilter> j = new ArrayList<>();
        public int q = -1;
        public ArrayList u = new ArrayList();

        /* JADX INFO: renamed from: androidx.mediarouter.media.h$h$a */
        /* JADX INFO: compiled from: MediaRouter.java */
        public static final class a {
            public final MediaRouteProvider.b.C0013b a;

            public a(MediaRouteProvider.b.C0013b c0013b) {
                this.a = c0013b;
            }
        }

        public C0020h(g gVar, String str, String str2) {
            this.a = gVar;
            this.b = str;
            this.c = str2;
        }

        public static MediaRouteProvider.b a() {
            h.b();
            MediaRouteProvider.e eVar = h.c().s;
            if (eVar instanceof MediaRouteProvider.b) {
                return (MediaRouteProvider.b) eVar;
            }
            return null;
        }

        public final a b(C0020h c0020h) {
            if (c0020h == null) {
                throw new NullPointerException("route must not be null");
            }
            m8 m8Var = this.v;
            if (m8Var == null) {
                return null;
            }
            String str = c0020h.c;
            if (m8Var.containsKey(str)) {
                return new a((MediaRouteProvider.b.C0013b) this.v.get(str));
            }
            return null;
        }

        public final List<C0020h> c() {
            return Collections.unmodifiableList(this.u);
        }

        public final MediaRouteProvider d() {
            g gVar = this.a;
            gVar.getClass();
            h.b();
            return gVar.a;
        }

        public final int e() {
            boolean z;
            if (g()) {
                if (h.c == null) {
                    z = false;
                } else {
                    h.c().getClass();
                    z = true;
                }
                if (!z) {
                    return 0;
                }
            }
            return this.n;
        }

        public final boolean f() {
            h.b();
            C0020h c0020h = h.c().p;
            if (c0020h == null) {
                throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
            }
            if ((c0020h == this) || this.m == 3) {
                return true;
            }
            return TextUtils.equals(d().f.a.getPackageName(), "android") && o("android.media.intent.category.LIVE_AUDIO") && !o("android.media.intent.category.LIVE_VIDEO");
        }

        public final boolean g() {
            return c().size() >= 1;
        }

        public final boolean h() {
            return this.t != null && this.g;
        }

        public final boolean i() {
            h.b();
            return h.c().g() == this;
        }

        public final boolean j(androidx.mediarouter.media.g gVar) {
            if (gVar == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            h.b();
            ArrayList<IntentFilter> arrayList = this.j;
            if (arrayList == null) {
                return false;
            }
            gVar.a();
            if (gVar.b.isEmpty()) {
                return false;
            }
            for (IntentFilter intentFilter : arrayList) {
                if (intentFilter != null) {
                    Iterator<String> it = gVar.b.iterator();
                    while (it.hasNext()) {
                        if (intentFilter.hasCategory(it.next())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:126:0x00fd A[EDGE_INSN: B:126:0x00fd->B:66:0x00fd BREAK  A[LOOP:0: B:29:0x0089->B:129:?], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:129:? A[LOOP:0: B:29:0x0089->B:129:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x00fb  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int k(androidx.mediarouter.media.d r14) {
            /*
                Method dump skipped, instruction units count: 585
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.h.C0020h.k(androidx.mediarouter.media.d):int");
        }

        public final void l(int i) {
            MediaRouteProvider.e eVar;
            MediaRouteProvider.e eVar2;
            h.b();
            d dVarC = h.c();
            int iMin = Math.min(this.p, Math.max(0, i));
            if (this == dVarC.r && (eVar2 = dVarC.s) != null) {
                eVar2.g(iMin);
                return;
            }
            HashMap map = dVarC.v;
            if (map.isEmpty() || (eVar = (MediaRouteProvider.e) map.get(this.c)) == null) {
                return;
            }
            eVar.g(iMin);
        }

        public final void m(int i) {
            MediaRouteProvider.e eVar;
            MediaRouteProvider.e eVar2;
            h.b();
            if (i != 0) {
                d dVarC = h.c();
                if (this == dVarC.r && (eVar2 = dVarC.s) != null) {
                    eVar2.j(i);
                    return;
                }
                HashMap map = dVarC.v;
                if (map.isEmpty() || (eVar = (MediaRouteProvider.e) map.get(this.c)) == null) {
                    return;
                }
                eVar.j(i);
            }
        }

        public final void n() {
            h.b();
            h.c().j(this, 3);
        }

        public final boolean o(String str) {
            h.b();
            ArrayList<IntentFilter> arrayList = this.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).hasCategory(str)) {
                    return true;
                }
            }
            return false;
        }

        public final void p(Collection<MediaRouteProvider.b.C0013b> collection) {
            this.u.clear();
            if (this.v == null) {
                this.v = new m8();
            }
            this.v.clear();
            for (MediaRouteProvider.b.C0013b c0013b : collection) {
                C0020h c0020hA = this.a.a(c0013b.a.d());
                if (c0020hA != null) {
                    this.v.put(c0020hA.c, c0013b);
                    int i = c0013b.b;
                    if (i == 2 || i == 3) {
                        this.u.add(c0020hA);
                    }
                }
            }
            h.c().m.b(259, this);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("MediaRouter.RouteInfo{ uniqueId=");
            sb.append(this.c);
            sb.append(", name=");
            sb.append(this.d);
            sb.append(", description=");
            sb.append(this.e);
            sb.append(", iconUri=");
            sb.append(this.f);
            sb.append(", enabled=");
            sb.append(this.g);
            sb.append(", connectionState=");
            sb.append(this.h);
            sb.append(", canDisconnect=");
            sb.append(this.i);
            sb.append(", playbackType=");
            sb.append(this.k);
            sb.append(", playbackStream=");
            sb.append(this.l);
            sb.append(", deviceType=");
            sb.append(this.m);
            sb.append(", volumeHandling=");
            sb.append(this.n);
            sb.append(", volume=");
            sb.append(this.o);
            sb.append(", volumeMax=");
            sb.append(this.p);
            sb.append(", presentationDisplayId=");
            sb.append(this.q);
            sb.append(", extras=");
            sb.append(this.r);
            sb.append(", settingsIntent=");
            sb.append(this.s);
            sb.append(", providerPackageName=");
            sb.append(this.a.c.a.getPackageName());
            if (g()) {
                sb.append(", members=[");
                int size = this.u.size();
                for (int i = 0; i < size; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    if (this.u.get(i) != this) {
                        sb.append(((C0020h) this.u.get(i)).c);
                    }
                }
                sb.append(']');
            }
            sb.append(" }");
            return sb.toString();
        }
    }

    public h(Context context) {
        this.a = context;
    }

    public static void b() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    public static d c() {
        d dVar = c;
        if (dVar == null) {
            return null;
        }
        dVar.d();
        return c;
    }

    public static h d(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        b();
        if (c == null) {
            c = new d(context.getApplicationContext());
        }
        ArrayList<WeakReference<h>> arrayList = c.f;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                h hVar = new h(context);
                arrayList.add(new WeakReference<>(hVar));
                return hVar;
            }
            h hVar2 = arrayList.get(size).get();
            if (hVar2 == null) {
                arrayList.remove(size);
            } else if (hVar2.a == context) {
                return hVar2;
            }
        }
    }

    public static MediaSessionCompat.Token e() {
        d dVar = c;
        if (dVar != null) {
            d.C0019d c0019d = dVar.A;
            if (c0019d != null) {
                MediaSessionCompat mediaSessionCompat = c0019d.a;
                if (mediaSessionCompat != null) {
                    return mediaSessionCompat.getSessionToken();
                }
            } else {
                MediaSessionCompat mediaSessionCompat2 = dVar.B;
                if (mediaSessionCompat2 != null) {
                    return mediaSessionCompat2.getSessionToken();
                }
            }
        }
        return null;
    }

    public static C0020h f() {
        b();
        return c().g();
    }

    public static boolean g(androidx.mediarouter.media.g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        b();
        d dVarC = c();
        dVarC.getClass();
        if (gVar.c()) {
            return false;
        }
        if (!dVarC.n) {
            ArrayList<C0020h> arrayList = dVarC.g;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0020h c0020h = arrayList.get(i);
                if (c0020h.f() || !c0020h.j(gVar)) {
                }
            }
            return false;
        }
        return true;
    }

    public static void i(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        b();
        d dVarC = c();
        C0020h c0020hC = dVarC.c();
        if (dVarC.g() != c0020hC) {
            dVarC.j(c0020hC, i);
        }
    }

    public final void a(androidx.mediarouter.media.g gVar, a aVar, int i) {
        b bVar;
        if (gVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        b();
        ArrayList<b> arrayList = this.b;
        int size = arrayList.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (arrayList.get(i2).b == aVar) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 < 0) {
            bVar = new b(this, aVar);
            arrayList.add(bVar);
        } else {
            bVar = arrayList.get(i2);
        }
        boolean z2 = true;
        if (i != bVar.d) {
            bVar.d = i;
            z = true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if ((i & 1) != 0) {
            z = true;
        }
        bVar.e = jElapsedRealtime;
        androidx.mediarouter.media.g gVar2 = bVar.c;
        gVar2.a();
        gVar.a();
        if (gVar2.b.containsAll(gVar.b)) {
            z2 = z;
        } else {
            g.a aVar2 = new g.a(bVar.c);
            aVar2.b(gVar);
            bVar.c = aVar2.c();
        }
        if (z2) {
            c().l();
        }
    }

    public final void h(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        b();
        ArrayList<b> arrayList = this.b;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (arrayList.get(i).b == aVar) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            arrayList.remove(i);
            c().l();
        }
    }

    public void setOnPrepareTransferListener(e eVar) {
        b();
        c().getClass();
    }

    /* JADX INFO: compiled from: MediaRouter.java */
    public static abstract class a {
        public abstract void e(C0020h c0020h);

        public void a() {
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
        }

        public void f() {
        }

        @Deprecated
        public void h() {
        }

        @Deprecated
        public void g(C0020h c0020h) {
        }

        public void i(C0020h c0020h) {
        }

        public void j(ce1 ce1Var) {
        }
    }

    /* JADX INFO: compiled from: MediaRouter.java */
    public static abstract class c {
        public void b(Bundle bundle) {
        }

        public void a(String str, Bundle bundle) {
        }
    }
}
