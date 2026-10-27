package androidx.mediarouter.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.c;
import androidx.mediarouter.media.g;
import defpackage.C0175b;
import defpackage.bz;
import defpackage.m8;
import defpackage.pq2;
import defpackage.pz0;
import defpackage.rd1;
import defpackage.td1;
import defpackage.ud1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaRouteProviderService extends Service {
    public static final /* synthetic */ int j = 0;
    public final Messenger e = new Messenger(new e(this));
    public final d f = new d();
    public final c.C0014c g;
    public MediaRouteProvider h;
    public final c i;

    public interface a {
        void a(Context context);

        IBinder b(Intent intent);
    }

    public static class b extends c {
        public androidx.mediarouter.media.c g;
        public final pq2 h;

        public class a extends c.b {
            public final m8 i;
            public final Handler j;
            public final Map<String, Integer> k;

            public a(Messenger messenger, int i, String str) {
                super(messenger, i, str);
                this.i = new m8();
                this.j = new Handler(Looper.getMainLooper());
                if (i < 4) {
                    this.k = new m8();
                } else {
                    this.k = Collections.emptyMap();
                }
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public final Bundle a(td1 td1Var) {
                Map<String, Integer> map = this.k;
                if (map.isEmpty()) {
                    return super.a(td1Var);
                }
                ArrayList arrayList = new ArrayList();
                for (androidx.mediarouter.media.d dVar : td1Var.a) {
                    if (map.containsKey(dVar.d())) {
                        Bundle bundle = new Bundle(dVar.a);
                        ArrayList<String> arrayList2 = !dVar.b().isEmpty() ? new ArrayList<>(dVar.b()) : null;
                        dVar.a();
                        ArrayList<? extends Parcelable> arrayList3 = dVar.c.isEmpty() ? null : new ArrayList<>(dVar.c);
                        bundle.putBoolean("enabled", false);
                        if (arrayList3 != null) {
                            bundle.putParcelableArrayList("controlFilters", arrayList3);
                        }
                        if (arrayList2 != null) {
                            bundle.putStringArrayList("groupMemberIds", arrayList2);
                        }
                        arrayList.add(new androidx.mediarouter.media.d(bundle));
                    } else {
                        arrayList.add(dVar);
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                arrayList4.addAll(td1Var.a);
                arrayList4.clear();
                arrayList4.addAll(arrayList);
                return super.a(new td1(arrayList4, td1Var.b));
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public final Bundle b(int i, String str) {
                Bundle bundleB = super.b(i, str);
                if (bundleB != null && this.c != null) {
                    b.this.g.e(this, this.f.get(i), i, this.c, str);
                }
                return bundleB;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public final boolean c(int i, String str, String str2) {
                m8 m8Var = this.i;
                MediaRouteProvider.e eVar = (MediaRouteProvider.e) m8Var.get(str);
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                if (eVar != null) {
                    sparseArray.put(i, eVar);
                    return true;
                }
                boolean zC = super.c(i, str, str2);
                if (str2 == null && zC && this.c != null) {
                    b.this.g.e(this, sparseArray.get(i), i, this.c, str);
                }
                if (zC) {
                    m8Var.put(str, sparseArray.get(i));
                }
                return zC;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public final void d() {
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    b.this.g.f(sparseArray.keyAt(i));
                }
                this.i.clear();
                super.d();
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public final boolean f(int i) {
                td1 td1Var;
                b bVar = b.this;
                bVar.g.f(i);
                MediaRouteProvider.e eVar = this.f.get(i);
                if (eVar != null) {
                    m8 m8Var = this.i;
                    Iterator it = ((m8.a) m8Var.entrySet()).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry entry = (Map.Entry) it.next();
                        if (entry.getValue() == eVar) {
                            m8Var.remove(entry.getKey());
                            break;
                        }
                    }
                }
                Map<String, Integer> map = this.k;
                Iterator<Map.Entry<String, Integer>> it2 = map.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Map.Entry<String, Integer> next = it2.next();
                    if (next.getValue().intValue() == i) {
                        if (map.remove(next.getKey()) != null && (td1Var = bVar.a.h.k) != null) {
                            MediaRouteProviderService.e(this.a, 5, 0, 0, a(td1Var), null);
                        }
                    }
                }
                return super.f(i);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.c.b
            public final void g(MediaRouteProvider.b bVar, androidx.mediarouter.media.d dVar, Collection<MediaRouteProvider.b.C0013b> collection) {
                super.g(bVar, dVar, collection);
                androidx.mediarouter.media.c cVar = b.this.g;
                if (cVar != null) {
                    cVar.g(bVar, dVar, collection);
                }
            }
        }

        public b(MediaRouteProviderService mediaRouteProviderService) {
            super(mediaRouteProviderService);
            this.h = new pq2(1, this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c, androidx.mediarouter.media.MediaRouteProviderService.a
        public final void a(Context context) {
            androidx.mediarouter.media.c cVar = this.g;
            if (cVar != null) {
                cVar.attachBaseContext(context);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c, androidx.mediarouter.media.MediaRouteProviderService.a
        public final IBinder b(Intent intent) {
            MediaRouteProviderService mediaRouteProviderService = this.a;
            mediaRouteProviderService.b();
            if (this.g == null) {
                this.g = new androidx.mediarouter.media.c(this);
                if (mediaRouteProviderService.getBaseContext() != null) {
                    this.g.attachBaseContext(mediaRouteProviderService);
                }
            }
            IBinder iBinderB = super.b(intent);
            return iBinderB != null ? iBinderB : this.g.onBind(intent);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c
        public final c.b c(Messenger messenger, int i, String str) {
            return new a(messenger, i, str);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.c
        public final void f(td1 td1Var) {
            super.f(td1Var);
            androidx.mediarouter.media.c cVar = this.g;
            cVar.i = td1Var;
            List<androidx.mediarouter.media.d> listEmptyList = td1Var == null ? Collections.emptyList() : td1Var.a;
            m8 m8Var = new m8();
            for (androidx.mediarouter.media.d dVar : listEmptyList) {
                if (dVar != null) {
                    m8Var.put(dVar.d(), dVar);
                }
            }
            ArrayList<c.C0018c> arrayList = new ArrayList();
            synchronized (cVar.e) {
                Iterator it = ((m8.e) cVar.g.values()).iterator();
                while (true) {
                    pz0 pz0Var = (pz0) it;
                    if (!pz0Var.hasNext()) {
                        break;
                    }
                    c.C0018c c0018c = (c.C0018c) pz0Var.next();
                    if ((c0018c.d & 4) == 0) {
                        arrayList.add(c0018c);
                    }
                }
            }
            for (c.C0018c c0018c2 : arrayList) {
                c.a aVar = (c.a) c0018c2.b;
                if (m8Var.containsKey(aVar.f)) {
                    c0018c2.e((androidx.mediarouter.media.d) m8Var.get(aVar.f), null);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = ((m8.e) m8Var.values()).iterator();
            while (true) {
                pz0 pz0Var2 = (pz0) it2;
                if (!pz0Var2.hasNext()) {
                    cVar.notifyRoutes(arrayList2);
                    return;
                } else {
                    MediaRoute2Info mediaRoute2InfoC = m.c((androidx.mediarouter.media.d) pz0Var2.next());
                    if (mediaRoute2InfoC != null) {
                        arrayList2.add(mediaRoute2InfoC);
                    }
                }
            }
        }
    }

    public final class d extends Handler {
        public d() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            c cVar;
            int iD;
            if (message.what == 1 && (iD = (cVar = MediaRouteProviderService.this.i).d((Messenger) message.obj)) >= 0) {
                c.b bVarRemove = cVar.b.remove(iD);
                int i = MediaRouteProviderService.j;
                bVarRemove.d();
            }
        }
    }

    public static final class e extends Handler {
        public final WeakReference<MediaRouteProviderService> a;

        public e(MediaRouteProviderService mediaRouteProviderService) {
            this.a = new WeakReference<>(mediaRouteProviderService);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:132:0x021a  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x000e  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void handleMessage(android.os.Message r14) {
            /*
                Method dump skipped, instruction units count: 590
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouteProviderService.e.handleMessage(android.os.Message):void");
        }
    }

    public MediaRouteProviderService() {
        if (Build.VERSION.SDK_INT >= 30) {
            this.i = new b(this);
        } else {
            this.i = new c(this);
        }
        c cVar = this.i;
        cVar.getClass();
        this.g = cVar.new C0014c();
    }

    public static Bundle a(td1 td1Var, int i) {
        if (td1Var == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<androidx.mediarouter.media.d> list = td1Var.a;
        arrayList.addAll(list);
        arrayList.clear();
        boolean z = i < 4 ? false : td1Var.b;
        for (androidx.mediarouter.media.d dVar : list) {
            if (i >= dVar.a.getInt("minClientVersion", 1) && i <= dVar.a.getInt("maxClientVersion", Integer.MAX_VALUE)) {
                if (arrayList.contains(dVar)) {
                    throw new IllegalArgumentException("route descriptor already added");
                }
                arrayList.add(dVar);
            }
        }
        List listEmptyList = arrayList.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(arrayList));
        Bundle bundle = new Bundle();
        if (!listEmptyList.isEmpty()) {
            int size = listEmptyList.size();
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(size);
            for (int i2 = 0; i2 < size; i2++) {
                arrayList2.add(((androidx.mediarouter.media.d) listEmptyList.get(i2)).a);
            }
            bundle.putParcelableArrayList("routes", arrayList2);
        }
        bundle.putBoolean("supportsDynamicGroupRoute", z);
        return bundle;
    }

    public static void d(Messenger messenger, int i) {
        if (i != 0) {
            e(messenger, 1, i, 0, null, null);
        }
    }

    public static void e(Messenger messenger, int i, int i2, int i3, Object obj, Bundle bundle) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.arg2 = i3;
        messageObtain.obj = obj;
        messageObtain.setData(bundle);
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e2) {
            StringBuilder sb = new StringBuilder("Could not send message to ");
            sb.append("Client connection " + messenger.getBinder().toString());
            Log.e("MediaRouteProviderSrv", sb.toString(), e2);
        }
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.i.a(context);
    }

    public final void b() {
        MediaRouteProvider mediaRouteProviderC;
        if (this.h != null || (mediaRouteProviderC = c()) == null) {
            return;
        }
        String packageName = mediaRouteProviderC.f.a.getPackageName();
        if (packageName.equals(getPackageName())) {
            this.h = mediaRouteProviderC;
            h.b();
            mediaRouteProviderC.h = this.g;
        } else {
            StringBuilder sbN = C0175b.n("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: ", packageName, ".  Service package name: ");
            sbN.append(getPackageName());
            sbN.append(".");
            throw new IllegalStateException(sbN.toString());
        }
    }

    public abstract MediaRouteProvider c();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.i.b(intent);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        MediaRouteProvider mediaRouteProvider = this.h;
        if (mediaRouteProvider != null) {
            h.b();
            mediaRouteProvider.h = null;
        }
        super.onDestroy();
    }

    public static class c implements a {
        public final MediaRouteProviderService a;
        public rd1 c;
        public rd1 d;
        public long e;
        public final ArrayList<b> b = new ArrayList<>();
        public final ud1 f = new ud1(new a());

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                c.this.g();
            }
        }

        public class b implements IBinder.DeathRecipient {
            public final Messenger a;
            public final int b;
            public final String c;
            public rd1 d;
            public long e;
            public final SparseArray<MediaRouteProvider.e> f = new SparseArray<>();
            public final a g = new a();

            public class a implements MediaRouteProvider.b.c {
                public a() {
                }

                @Override // androidx.mediarouter.media.MediaRouteProvider.b.c
                public final void c(MediaRouteProvider.b bVar, androidx.mediarouter.media.d dVar, Collection<MediaRouteProvider.b.C0013b> collection) {
                    b.this.g(bVar, dVar, collection);
                }
            }

            public b(Messenger messenger, int i, String str) {
                this.a = messenger;
                this.b = i;
                this.c = str;
            }

            public Bundle a(td1 td1Var) {
                return MediaRouteProviderService.a(td1Var, this.b);
            }

            public Bundle b(int i, String str) {
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                if (sparseArray.indexOfKey(i) >= 0) {
                    return null;
                }
                c cVar = c.this;
                MediaRouteProvider.b bVarL = cVar.a.h.l(str);
                if (bVarL == null) {
                    return null;
                }
                Context applicationContext = cVar.a.getApplicationContext();
                Object obj = bz.a;
                bVarL.q(applicationContext.getMainExecutor(), this.g);
                sparseArray.put(i, bVarL);
                Bundle bundle = new Bundle();
                bundle.putString("groupableTitle", bVarL.k());
                bundle.putString("transferableTitle", bVarL.l());
                return bundle;
            }

            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                c.this.a.f.obtainMessage(1, this.a).sendToTarget();
            }

            public boolean c(int i, String str, String str2) {
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                if (sparseArray.indexOfKey(i) >= 0) {
                    return false;
                }
                c cVar = c.this;
                MediaRouteProvider.e eVarM = str2 == null ? cVar.a.h.m(str) : cVar.a.h.n(str, str2);
                if (eVarM == null) {
                    return false;
                }
                sparseArray.put(i, eVarM);
                return true;
            }

            public void d() {
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    sparseArray.valueAt(i).e();
                }
                sparseArray.clear();
                this.a.getBinder().unlinkToDeath(this, 0);
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (Objects.equals(this.d, null)) {
                    return;
                }
                this.d = null;
                this.e = jElapsedRealtime;
                c.this.g();
            }

            public final MediaRouteProvider.e e(int i) {
                return this.f.get(i);
            }

            public boolean f(int i) {
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                MediaRouteProvider.e eVar = sparseArray.get(i);
                if (eVar == null) {
                    return false;
                }
                sparseArray.remove(i);
                eVar.e();
                return true;
            }

            public void g(MediaRouteProvider.b bVar, androidx.mediarouter.media.d dVar, Collection<MediaRouteProvider.b.C0013b> collection) {
                SparseArray<MediaRouteProvider.e> sparseArray = this.f;
                int iIndexOfValue = sparseArray.indexOfValue(bVar);
                if (iIndexOfValue < 0) {
                    Log.w("MediaRouteProviderSrv", "Ignoring unknown dynamic group route controller: " + bVar);
                    return;
                }
                int iKeyAt = sparseArray.keyAt(iIndexOfValue);
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                for (MediaRouteProvider.b.C0013b c0013b : collection) {
                    if (c0013b.f == null) {
                        Bundle bundle = new Bundle();
                        c0013b.f = bundle;
                        bundle.putBundle("mrDescriptor", c0013b.a.a);
                        c0013b.f.putInt("selectionState", c0013b.b);
                        c0013b.f.putBoolean("isUnselectable", c0013b.c);
                        c0013b.f.putBoolean("isGroupable", c0013b.d);
                        c0013b.f.putBoolean("isTransferable", c0013b.e);
                    }
                    arrayList.add(c0013b.f);
                }
                Bundle bundle2 = new Bundle();
                if (dVar != null) {
                    bundle2.putParcelable("groupRoute", dVar.a);
                }
                bundle2.putParcelableArrayList("dynamicRoutes", arrayList);
                MediaRouteProviderService.e(this.a, 7, 0, iKeyAt, bundle2, null);
            }

            public final String toString() {
                int i = MediaRouteProviderService.j;
                return "Client connection " + this.a.getBinder().toString();
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.MediaRouteProviderService$c$c, reason: collision with other inner class name */
        public class C0014c extends MediaRouteProvider.a {
            public C0014c() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.a
            public final void a(MediaRouteProvider mediaRouteProvider, td1 td1Var) {
                c.this.f(td1Var);
            }
        }

        public c(MediaRouteProviderService mediaRouteProviderService) {
            this.a = mediaRouteProviderService;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public IBinder b(Intent intent) {
            if (!intent.getAction().equals("android.media.MediaRouteProviderService")) {
                return null;
            }
            MediaRouteProviderService mediaRouteProviderService = this.a;
            mediaRouteProviderService.b();
            if (mediaRouteProviderService.h != null) {
                return mediaRouteProviderService.e.getBinder();
            }
            return null;
        }

        public b c(Messenger messenger, int i, String str) {
            return new b(messenger, i, str);
        }

        public final int d(Messenger messenger) {
            ArrayList<b> arrayList = this.b;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).a.getBinder() == messenger.getBinder()) {
                    return i;
                }
            }
            return -1;
        }

        public final b e(Messenger messenger) {
            int iD = d(messenger);
            if (iD >= 0) {
                return this.b.get(iD);
            }
            return null;
        }

        public void f(td1 td1Var) {
            ArrayList<b> arrayList = this.b;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                b bVar = arrayList.get(i);
                MediaRouteProviderService.e(bVar.a, 5, 0, 0, bVar.a(td1Var), null);
            }
        }

        public final boolean g() {
            g.a aVar;
            ud1 ud1Var = this.f;
            ud1Var.c = 0L;
            ud1Var.e = false;
            ud1Var.d = SystemClock.elapsedRealtime();
            ud1Var.a.removeCallbacks(ud1Var.b);
            rd1 rd1Var = this.d;
            if (rd1Var != null) {
                ud1Var.a(this.e, rd1Var.b());
                rd1 rd1Var2 = this.d;
                rd1Var2.a();
                aVar = new g.a(rd1Var2.b);
            } else {
                aVar = null;
            }
            ArrayList<b> arrayList = this.b;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                b bVar = arrayList.get(i);
                rd1 rd1Var3 = bVar.d;
                if (rd1Var3 != null) {
                    rd1Var3.a();
                    if (!rd1Var3.b.c() || rd1Var3.b()) {
                        ud1Var.a(bVar.e, rd1Var3.b());
                        if (aVar == null) {
                            rd1Var3.a();
                            aVar = new g.a(rd1Var3.b);
                        } else {
                            rd1Var3.a();
                            aVar.b(rd1Var3.b);
                        }
                    }
                }
            }
            if (ud1Var.e) {
                long j = ud1Var.c;
                if (j > 0) {
                    ud1Var.a.postDelayed(ud1Var.b, j);
                }
            }
            rd1 rd1Var4 = aVar != null ? new rd1(aVar.c(), ud1Var.e) : null;
            if (Objects.equals(this.c, rd1Var4)) {
                return false;
            }
            this.c = rd1Var4;
            MediaRouteProvider mediaRouteProvider = this.a.h;
            if (mediaRouteProvider == null) {
                return true;
            }
            mediaRouteProvider.q(rd1Var4);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.a
        public void a(Context context) {
        }
    }
}
