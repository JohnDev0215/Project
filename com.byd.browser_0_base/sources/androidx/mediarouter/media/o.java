package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.h;
import defpackage.rd1;
import defpackage.td1;
import defpackage.z70;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class o extends MediaRouteProvider implements ServiceConnection {
    public final ComponentName m;
    public final d n;
    public final ArrayList<c> o;
    public boolean p;
    public boolean q;
    public a r;
    public boolean s;
    public b t;

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public final class a implements IBinder.DeathRecipient {
        public final Messenger a;
        public final e b;
        public final Messenger c;
        public int f;
        public int g;
        public int d = 1;
        public int e = 1;
        public final SparseArray<h.c> h = new SparseArray<>();

        /* JADX INFO: renamed from: androidx.mediarouter.media.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
        public class RunnableC0021a implements Runnable {
            public RunnableC0021a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                a aVar = a.this;
                o oVar = o.this;
                if (oVar.r == aVar) {
                    oVar.s();
                }
            }
        }

        public a(Messenger messenger) {
            this.a = messenger;
            e eVar = new e(this);
            this.b = eVar;
            this.c = new Messenger(eVar);
        }

        public final void a(int i) {
            int i2 = this.d;
            this.d = i2 + 1;
            c(5, i2, i, null, null);
        }

        public final boolean b(int i, Intent intent, h.c cVar) {
            int i2 = this.d;
            this.d = i2 + 1;
            if (!c(9, i2, i, intent, null)) {
                return false;
            }
            if (cVar == null) {
                return true;
            }
            this.h.put(i2, cVar);
            return true;
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            o.this.n.post(new RunnableC0021a());
        }

        public final boolean c(int i, int i2, int i3, Object obj, Bundle bundle) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            messageObtain.obj = obj;
            messageObtain.setData(bundle);
            messageObtain.replyTo = this.c;
            try {
                this.a.send(messageObtain);
                return true;
            } catch (DeadObjectException unused) {
                return false;
            } catch (RemoteException e) {
                if (i == 2) {
                    return false;
                }
                Log.e("MediaRouteProviderProxy", "Could not send message to service.", e);
                return false;
            }
        }

        public final void d(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i2);
            int i3 = this.d;
            this.d = i3 + 1;
            c(7, i3, i, null, bundle);
        }

        public final void e(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i2);
            int i3 = this.d;
            this.d = i3 + 1;
            c(8, i3, i, null, bundle);
        }
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public interface b {
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public interface c {
        int a();

        void b();

        void c(a aVar);
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public static final class d extends Handler {
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public static final class e extends Handler {
        public final WeakReference<a> a;

        public e(a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            MediaRouteProvider.b.C0013b c0013b;
            a aVar = this.a.get();
            if (aVar != null) {
                int i = message.what;
                int i2 = message.arg1;
                int i3 = message.arg2;
                Object obj = message.obj;
                Bundle bundlePeekData = message.peekData();
                SparseArray<h.c> sparseArray = aVar.h;
                c cVar = null;
                c cVar2 = null;
                o oVar = o.this;
                switch (i) {
                    case 0:
                        if (i2 == aVar.g) {
                            aVar.g = 0;
                            if (oVar.r == aVar) {
                                oVar.t();
                            }
                        }
                        h.c cVar3 = sparseArray.get(i2);
                        if (cVar3 != null) {
                            sparseArray.remove(i2);
                            cVar3.a(null, null);
                        }
                        break;
                    case 2:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle = (Bundle) obj;
                            if (aVar.f == 0 && i2 == aVar.g && i3 >= 1) {
                                aVar.g = 0;
                                aVar.f = i3;
                                td1 td1VarA = td1.a(bundle);
                                if (oVar.r == aVar) {
                                    oVar.p(td1VarA);
                                }
                                if (oVar.r == aVar) {
                                    oVar.s = true;
                                    ArrayList<c> arrayList = oVar.o;
                                    int size = arrayList.size();
                                    for (int i4 = 0; i4 < size; i4++) {
                                        arrayList.get(i4).c(oVar.r);
                                    }
                                    rd1 rd1Var = oVar.i;
                                    if (rd1Var != null) {
                                        a aVar2 = oVar.r;
                                        int i5 = aVar2.d;
                                        aVar2.d = i5 + 1;
                                        aVar2.c(10, i5, 0, rd1Var.a, null);
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    case 3:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle2 = (Bundle) obj;
                            h.c cVar4 = sparseArray.get(i2);
                            if (cVar4 != null) {
                                sparseArray.remove(i2);
                                cVar4.b(bundle2);
                            }
                        }
                        break;
                    case 4:
                        if (obj == null || (obj instanceof Bundle)) {
                            String string = bundlePeekData != null ? bundlePeekData.getString("error") : null;
                            Bundle bundle3 = (Bundle) obj;
                            h.c cVar5 = sparseArray.get(i2);
                            if (cVar5 != null) {
                                sparseArray.remove(i2);
                                cVar5.a(string, bundle3);
                            }
                        }
                        break;
                    case 5:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle4 = (Bundle) obj;
                            if (aVar.f != 0) {
                                td1 td1VarA2 = td1.a(bundle4);
                                if (oVar.r == aVar) {
                                    oVar.p(td1VarA2);
                                }
                            }
                        }
                        break;
                    case 6:
                        if (obj instanceof Bundle) {
                            Bundle bundle5 = (Bundle) obj;
                            h.c cVar6 = sparseArray.get(i2);
                            if (bundle5 == null || !bundle5.containsKey("routeId")) {
                                cVar6.a("DynamicGroupRouteController is created without valid route id.", bundle5);
                            } else {
                                sparseArray.remove(i2);
                                cVar6.b(bundle5);
                            }
                        } else {
                            Log.w("MediaRouteProviderProxy", "No further information on the dynamic group controller");
                        }
                        break;
                    case 7:
                        if (obj == null || (obj instanceof Bundle)) {
                            Bundle bundle6 = (Bundle) obj;
                            if (aVar.f != 0) {
                                Bundle bundle7 = (Bundle) bundle6.getParcelable("groupRoute");
                                androidx.mediarouter.media.d dVar = bundle7 != null ? new androidx.mediarouter.media.d(bundle7) : null;
                                ArrayList<Bundle> parcelableArrayList = bundle6.getParcelableArrayList("dynamicRoutes");
                                ArrayList arrayList2 = new ArrayList();
                                for (Bundle bundle8 : parcelableArrayList) {
                                    if (bundle8 == null) {
                                        c0013b = null;
                                    } else {
                                        Bundle bundle9 = bundle8.getBundle("mrDescriptor");
                                        c0013b = new MediaRouteProvider.b.C0013b(bundle9 != null ? new androidx.mediarouter.media.d(bundle9) : null, bundle8.getInt("selectionState", 1), bundle8.getBoolean("isUnselectable", false), bundle8.getBoolean("isGroupable", false), bundle8.getBoolean("isTransferable", false));
                                    }
                                    arrayList2.add(c0013b);
                                }
                                if (oVar.r == aVar) {
                                    Iterator<c> it = oVar.o.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            c next = it.next();
                                            if (next.a() == i3) {
                                                cVar2 = next;
                                            }
                                        }
                                    }
                                    if (cVar2 instanceof f) {
                                        ((f) cVar2).m(dVar, arrayList2);
                                    }
                                }
                            }
                        }
                        break;
                    case 8:
                        if (oVar.r == aVar) {
                            ArrayList<c> arrayList3 = oVar.o;
                            Iterator<c> it2 = arrayList3.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    c next2 = it2.next();
                                    if (next2.a() == i3) {
                                        cVar = next2;
                                    }
                                }
                            }
                            b bVar = oVar.t;
                            if (bVar != null && (cVar instanceof MediaRouteProvider.e)) {
                                MediaRouteProvider.e eVar = (MediaRouteProvider.e) cVar;
                                h.d dVar2 = (h.d) ((p) ((z70) bVar).f).b;
                                if (dVar2.s == eVar) {
                                    dVar2.j(dVar2.c(), 2);
                                }
                            }
                            arrayList3.remove(cVar);
                            cVar.b();
                            oVar.u();
                        }
                        break;
                }
            }
        }
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public final class f extends MediaRouteProvider.b implements c {
        public final String f;
        public String g;
        public String h;
        public boolean i;
        public int k;
        public a l;
        public int j = -1;
        public int m = -1;

        /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
        public class a extends h.c {
            public a() {
            }

            @Override // androidx.mediarouter.media.h.c
            public final void a(String str, Bundle bundle) {
                Objects.toString(bundle);
            }

            @Override // androidx.mediarouter.media.h.c
            public final void b(Bundle bundle) {
                String string = bundle.getString("groupableTitle");
                f fVar = f.this;
                fVar.g = string;
                fVar.h = bundle.getString("transferableTitle");
            }
        }

        public f(String str) {
            this.f = str;
        }

        @Override // androidx.mediarouter.media.o.c
        public final int a() {
            return this.m;
        }

        @Override // androidx.mediarouter.media.o.c
        public final void b() {
            a aVar = this.l;
            if (aVar != null) {
                int i = this.m;
                int i2 = aVar.d;
                aVar.d = i2 + 1;
                aVar.c(4, i2, i, null, null);
                this.l = null;
                this.m = 0;
            }
        }

        @Override // androidx.mediarouter.media.o.c
        public final void c(a aVar) {
            a aVar2 = new a();
            this.l = aVar;
            int i = aVar.e;
            aVar.e = i + 1;
            int i2 = aVar.d;
            aVar.d = i2 + 1;
            Bundle bundle = new Bundle();
            bundle.putString("memberRouteId", this.f);
            aVar.c(11, i2, i, null, bundle);
            aVar.h.put(i2, aVar2);
            this.m = i;
            if (this.i) {
                aVar.a(i);
                int i3 = this.j;
                if (i3 >= 0) {
                    aVar.d(this.m, i3);
                    this.j = -1;
                }
                int i4 = this.k;
                if (i4 != 0) {
                    aVar.e(this.m, i4);
                    this.k = 0;
                }
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final boolean d(Intent intent, h.c cVar) {
            a aVar = this.l;
            if (aVar != null) {
                return aVar.b(this.m, intent, cVar);
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void e() {
            o oVar = o.this;
            oVar.o.remove(this);
            b();
            oVar.u();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void f() {
            this.i = true;
            a aVar = this.l;
            if (aVar != null) {
                aVar.a(this.m);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void g(int i) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.d(this.m, i);
            } else {
                this.j = i;
                this.k = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void h() {
            i(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void i(int i) {
            this.i = false;
            a aVar = this.l;
            if (aVar != null) {
                int i2 = this.m;
                Bundle bundle = new Bundle();
                bundle.putInt("unselectReason", i);
                int i3 = aVar.d;
                aVar.d = i3 + 1;
                aVar.c(6, i3, i2, null, bundle);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void j(int i) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.e(this.m, i);
            } else {
                this.k += i;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final String k() {
            return this.g;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final String l() {
            return this.h;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void n(String str) {
            a aVar = this.l;
            if (aVar != null) {
                int i = this.m;
                aVar.getClass();
                Bundle bundle = new Bundle();
                bundle.putString("memberRouteId", str);
                int i2 = aVar.d;
                aVar.d = i2 + 1;
                aVar.c(12, i2, i, null, bundle);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void o(String str) {
            a aVar = this.l;
            if (aVar != null) {
                int i = this.m;
                aVar.getClass();
                Bundle bundle = new Bundle();
                bundle.putString("memberRouteId", str);
                int i2 = aVar.d;
                aVar.d = i2 + 1;
                aVar.c(13, i2, i, null, bundle);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.b
        public final void p(List<String> list) {
            a aVar = this.l;
            if (aVar != null) {
                int i = this.m;
                aVar.getClass();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("memberRouteIds", new ArrayList<>(list));
                int i2 = aVar.d;
                aVar.d = i2 + 1;
                aVar.c(14, i2, i, null, bundle);
            }
        }
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
    public final class g extends MediaRouteProvider.e implements c {
        public final String a;
        public final String b;
        public boolean c;
        public int d = -1;
        public int e;
        public a f;
        public int g;

        public g(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // androidx.mediarouter.media.o.c
        public final int a() {
            return this.g;
        }

        @Override // androidx.mediarouter.media.o.c
        public final void b() {
            a aVar = this.f;
            if (aVar != null) {
                int i = this.g;
                int i2 = aVar.d;
                aVar.d = i2 + 1;
                aVar.c(4, i2, i, null, null);
                this.f = null;
                this.g = 0;
            }
        }

        @Override // androidx.mediarouter.media.o.c
        public final void c(a aVar) {
            this.f = aVar;
            int i = aVar.e;
            aVar.e = i + 1;
            Bundle bundle = new Bundle();
            bundle.putString("routeId", this.a);
            bundle.putString("routeGroupId", this.b);
            int i2 = aVar.d;
            aVar.d = i2 + 1;
            aVar.c(3, i2, i, null, bundle);
            this.g = i;
            if (this.c) {
                aVar.a(i);
                int i3 = this.d;
                if (i3 >= 0) {
                    aVar.d(this.g, i3);
                    this.d = -1;
                }
                int i4 = this.e;
                if (i4 != 0) {
                    aVar.e(this.g, i4);
                    this.e = 0;
                }
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final boolean d(Intent intent, h.c cVar) {
            a aVar = this.f;
            if (aVar != null) {
                return aVar.b(this.g, intent, cVar);
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void e() {
            o oVar = o.this;
            oVar.o.remove(this);
            b();
            oVar.u();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void f() {
            this.c = true;
            a aVar = this.f;
            if (aVar != null) {
                aVar.a(this.g);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void g(int i) {
            a aVar = this.f;
            if (aVar != null) {
                aVar.d(this.g, i);
            } else {
                this.d = i;
                this.e = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void h() {
            i(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void i(int i) {
            this.c = false;
            a aVar = this.f;
            if (aVar != null) {
                int i2 = this.g;
                Bundle bundle = new Bundle();
                bundle.putInt("unselectReason", i);
                int i3 = aVar.d;
                aVar.d = i3 + 1;
                aVar.c(6, i3, i2, null, bundle);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.e
        public final void j(int i) {
            a aVar = this.f;
            if (aVar != null) {
                aVar.e(this.g, i);
            } else {
                this.e += i;
            }
        }
    }

    public o(Context context, ComponentName componentName) {
        super(context, new MediaRouteProvider.d(componentName));
        this.o = new ArrayList<>();
        this.m = componentName;
        this.n = new d();
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final MediaRouteProvider.b l(String str) {
        if (str == null) {
            throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
        }
        td1 td1Var = this.k;
        if (td1Var != null) {
            List<androidx.mediarouter.media.d> list = td1Var.a;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).d().equals(str)) {
                    f fVar = new f(str);
                    this.o.add(fVar);
                    if (this.s) {
                        fVar.c(this.r);
                    }
                    u();
                    return fVar;
                }
            }
        }
        return null;
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final MediaRouteProvider.e m(String str) {
        if (str != null) {
            return r(str, null);
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final MediaRouteProvider.e n(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return r(str, str2);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public final void o(rd1 rd1Var) {
        if (this.s) {
            a aVar = this.r;
            int i = aVar.d;
            aVar.d = i + 1;
            aVar.c(10, i, 0, rd1Var != null ? rd1Var.a : null, null);
        }
        u();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onServiceConnected(android.content.ComponentName r9, android.os.IBinder r10) {
        /*
            r8 = this;
            boolean r9 = r8.q
            if (r9 == 0) goto L61
            r8.s()
            if (r10 == 0) goto Lf
            android.os.Messenger r9 = new android.os.Messenger
            r9.<init>(r10)
            goto L10
        Lf:
            r9 = 0
        L10:
            r10 = 0
            r0 = 1
            if (r9 == 0) goto L1c
            android.os.IBinder r1 = r9.getBinder()     // Catch: java.lang.NullPointerException -> L1c
            if (r1 == 0) goto L1c
            r1 = r0
            goto L1d
        L1c:
            r1 = r10
        L1d:
            if (r1 == 0) goto L4b
            androidx.mediarouter.media.o$a r1 = new androidx.mediarouter.media.o$a
            r1.<init>(r9)
            int r4 = r1.d
            int r9 = r4 + 1
            r1.d = r9
            r1.g = r4
            r3 = 1
            r5 = 4
            r6 = 0
            r7 = 0
            r2 = r1
            boolean r9 = r2.c(r3, r4, r5, r6, r7)
            if (r9 != 0) goto L38
            goto L46
        L38:
            android.os.Messenger r9 = r1.a     // Catch: android.os.RemoteException -> L43
            android.os.IBinder r9 = r9.getBinder()     // Catch: android.os.RemoteException -> L43
            r9.linkToDeath(r1, r10)     // Catch: android.os.RemoteException -> L43
            r10 = r0
            goto L46
        L43:
            r1.binderDied()
        L46:
            if (r10 == 0) goto L61
            r8.r = r1
            goto L61
        L4b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            java.lang.String r8 = ": Service returned invalid messenger binder"
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            java.lang.String r9 = "MediaRouteProviderProxy"
            android.util.Log.e(r9, r8)
        L61:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.o.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        s();
    }

    public final g r(String str, String str2) {
        td1 td1Var = this.k;
        if (td1Var == null) {
            return null;
        }
        List<androidx.mediarouter.media.d> list = td1Var.a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).d().equals(str)) {
                g gVar = new g(str, str2);
                this.o.add(gVar);
                if (this.s) {
                    gVar.c(this.r);
                }
                u();
                return gVar;
            }
        }
        return null;
    }

    public final void s() {
        if (this.r != null) {
            p(null);
            this.s = false;
            ArrayList<c> arrayList = this.o;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                arrayList.get(i).b();
            }
            a aVar = this.r;
            aVar.c(2, 0, 0, null, null);
            aVar.b.a.clear();
            aVar.a.getBinder().unlinkToDeath(aVar, 0);
            o.this.n.post(new n(aVar));
            this.r = null;
        }
    }

    public final void t() {
        if (this.q) {
            this.q = false;
            s();
            try {
                this.e.unbindService(this);
            } catch (IllegalArgumentException e2) {
                Log.e("MediaRouteProviderProxy", this + ": unbindService failed", e2);
            }
        }
    }

    public final String toString() {
        return "Service connection " + this.m.flattenToShortString();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u() {
        /*
            r3 = this;
            boolean r0 = r3.p
            if (r0 == 0) goto L13
            rd1 r0 = r3.i
            r1 = 1
            if (r0 == 0) goto La
            goto L14
        La:
            java.util.ArrayList<androidx.mediarouter.media.o$c> r0 = r3.o
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L13
            goto L14
        L13:
            r1 = 0
        L14:
            if (r1 == 0) goto L31
            boolean r0 = r3.q
            if (r0 != 0) goto L34
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.media.MediaRouteProviderService"
            r0.<init>(r1)
            android.content.ComponentName r1 = r3.m
            r0.setComponent(r1)
            android.content.Context r1 = r3.e     // Catch: java.lang.SecurityException -> L34
            r2 = 4097(0x1001, float:5.741E-42)
            boolean r0 = r1.bindService(r0, r3, r2)     // Catch: java.lang.SecurityException -> L34
            r3.q = r0     // Catch: java.lang.SecurityException -> L34
            goto L34
        L31:
            r3.t()
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.o.u():void");
    }
}
