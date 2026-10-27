package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
final class A extends MediaRouteProvider implements ServiceConnection {
    static final boolean p = Log.isLoggable("MediaRouteProviderProxy", 3);
    private final ComponentName i;
    final c j;
    private final ArrayList<b> k;
    private boolean l;
    private boolean m;
    private a n;
    private boolean o;

    /* JADX INFO: compiled from: caradapter-bug */
    private final class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Messenger f908a;
        private int f;
        private int g;
        private int d = 1;
        private int e = 1;
        private final SparseArray<u.d> h = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d f909b = new d(this);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Messenger f910c = new Messenger(this.f909b);

        /* JADX INFO: renamed from: androidx.mediarouter.media.A$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        class RunnableC0033a implements Runnable {
            RunnableC0033a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e();
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                A.this.I(aVar);
            }
        }

        public a(Messenger messenger) {
            this.f908a = messenger;
        }

        private boolean s(int i, int i2, int i3, Object obj, Bundle bundle) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            messageObtain.obj = obj;
            messageObtain.setData(bundle);
            messageObtain.replyTo = this.f910c;
            try {
                this.f908a.send(messageObtain);
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

        public void a(int i, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("memberRouteId", str);
            int i2 = this.d;
            this.d = i2 + 1;
            s(12, i2, i, null, bundle);
        }

        public int b(String str, u.d dVar) {
            int i = this.e;
            this.e = i + 1;
            int i2 = this.d;
            this.d = i2 + 1;
            Bundle bundle = new Bundle();
            bundle.putString("memberRouteId", str);
            s(11, i2, i, null, bundle);
            this.h.put(i2, dVar);
            return i;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            A.this.j.post(new b());
        }

        public int c(String str, String str2) {
            int i = this.e;
            this.e = i + 1;
            Bundle bundle = new Bundle();
            bundle.putString("routeId", str);
            bundle.putString("routeGroupId", str2);
            int i2 = this.d;
            this.d = i2 + 1;
            s(3, i2, i, null, bundle);
            return i;
        }

        public void d() {
            s(2, 0, 0, null, null);
            this.f909b.a();
            this.f908a.getBinder().unlinkToDeath(this, 0);
            A.this.j.post(new RunnableC0033a());
        }

        void e() {
            int size = this.h.size();
            for (int i = 0; i < size; i++) {
                this.h.valueAt(i).a(null, null);
            }
            this.h.clear();
        }

        public boolean f(int i, String str, Bundle bundle) {
            u.d dVar = this.h.get(i);
            if (dVar == null) {
                return false;
            }
            this.h.remove(i);
            dVar.a(str, bundle);
            return true;
        }

        public boolean g(int i, Bundle bundle) {
            u.d dVar = this.h.get(i);
            if (dVar == null) {
                return false;
            }
            this.h.remove(i);
            dVar.b(bundle);
            return true;
        }

        public boolean h(Bundle bundle) {
            if (this.f == 0) {
                return false;
            }
            A.this.H(this, r.b(bundle));
            return true;
        }

        public void i(int i, Bundle bundle) {
            u.d dVar = this.h.get(i);
            if (bundle == null || !bundle.containsKey("routeId")) {
                dVar.a("DynamicGroupRouteController is created without valid route id.", bundle);
            } else {
                this.h.remove(i);
                dVar.b(bundle);
            }
        }

        public boolean j(int i, Bundle bundle) {
            if (this.f == 0) {
                return false;
            }
            Bundle bundle2 = (Bundle) bundle.getParcelable("groupRoute");
            p pVarE = bundle2 != null ? p.e(bundle2) : null;
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("dynamicRoutes");
            ArrayList arrayList = new ArrayList();
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                arrayList.add(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor.a((Bundle) it.next()));
            }
            A.this.M(this, i, pVarE, arrayList);
            return true;
        }

        public boolean k(int i) {
            if (i == this.g) {
                this.g = 0;
                A.this.J(this, "Registration failed");
            }
            u.d dVar = this.h.get(i);
            if (dVar == null) {
                return true;
            }
            this.h.remove(i);
            dVar.a(null, null);
            return true;
        }

        public boolean l(int i) {
            return true;
        }

        public boolean m(int i, int i2, Bundle bundle) {
            if (this.f != 0 || i != this.g || i2 < 1) {
                return false;
            }
            this.g = 0;
            this.f = i2;
            A.this.H(this, r.b(bundle));
            A.this.K(this);
            return true;
        }

        public boolean n() {
            int i = this.d;
            this.d = i + 1;
            this.g = i;
            if (!s(1, i, 3, null, null)) {
                return false;
            }
            try {
                this.f908a.getBinder().linkToDeath(this, 0);
                return true;
            } catch (RemoteException unused) {
                binderDied();
                return false;
            }
        }

        public void o(int i) {
            int i2 = this.d;
            this.d = i2 + 1;
            s(4, i2, i, null, null);
        }

        public void p(int i, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("memberRouteId", str);
            int i2 = this.d;
            this.d = i2 + 1;
            s(13, i2, i, null, bundle);
        }

        public void q(int i) {
            int i2 = this.d;
            this.d = i2 + 1;
            s(5, i2, i, null, null);
        }

        public boolean r(int i, Intent intent, u.d dVar) {
            int i2 = this.d;
            this.d = i2 + 1;
            if (!s(9, i2, i, intent, null)) {
                return false;
            }
            if (dVar == null) {
                return true;
            }
            this.h.put(i2, dVar);
            return true;
        }

        public void t(q qVar) {
            int i = this.d;
            this.d = i + 1;
            s(10, i, 0, qVar != null ? qVar.a() : null, null);
        }

        public void u(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i2);
            int i3 = this.d;
            this.d = i3 + 1;
            s(7, i3, i, null, bundle);
        }

        public void v(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("unselectReason", i2);
            int i3 = this.d;
            this.d = i3 + 1;
            s(6, i3, i, null, bundle);
        }

        public void w(int i, List<String> list) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("memberRouteIds", new ArrayList<>(list));
            int i2 = this.d;
            this.d = i2 + 1;
            s(14, i2, i, null, bundle);
        }

        public void x(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i2);
            int i3 = this.d;
            this.d = i3 + 1;
            s(8, i3, i, null, bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        int a();

        void b(a aVar);

        void c();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class c extends Handler {
        c() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<a> f913a;

        public d(a aVar) {
            this.f913a = new WeakReference<>(aVar);
        }

        private boolean b(a aVar, int i, int i2, int i3, Object obj, Bundle bundle) {
            switch (i) {
                case 0:
                    aVar.k(i2);
                    return true;
                case 1:
                    aVar.l(i2);
                    return true;
                case 2:
                    if (obj == null || (obj instanceof Bundle)) {
                        return aVar.m(i2, i3, (Bundle) obj);
                    }
                    return false;
                case 3:
                    if (obj == null || (obj instanceof Bundle)) {
                        return aVar.g(i2, (Bundle) obj);
                    }
                    return false;
                case 4:
                    if (obj == null || (obj instanceof Bundle)) {
                        return aVar.f(i2, bundle == null ? null : bundle.getString("error"), (Bundle) obj);
                    }
                    return false;
                case 5:
                    if (obj == null || (obj instanceof Bundle)) {
                        return aVar.h((Bundle) obj);
                    }
                    return false;
                case 6:
                    if (obj instanceof Bundle) {
                        aVar.i(i2, (Bundle) obj);
                        return false;
                    }
                    Log.w("MediaRouteProviderProxy", "No further information on the dynamic group controller");
                    return false;
                case 7:
                    if (obj == null || (obj instanceof Bundle)) {
                        return aVar.j(i3, (Bundle) obj);
                    }
                    return false;
                default:
                    return false;
            }
        }

        public void a() {
            this.f913a.clear();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = this.f913a.get();
            if (aVar == null || b(aVar, message.what, message.arg1, message.arg2, message.obj, message.peekData()) || !A.p) {
                return;
            }
            Log.d("MediaRouteProviderProxy", "Unhandled message from server: " + message);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class e extends MediaRouteProvider.DynamicGroupRouteController implements b {
        private final String f;
        String g;
        String h;
        private boolean i;
        private int k;
        private a l;
        private int j = -1;
        private int m = -1;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends u.d {
            a() {
            }

            @Override // androidx.mediarouter.media.u.d
            public void a(String str, Bundle bundle) {
                Log.d("MediaRouteProviderProxy", "Error: " + str + ", data: " + bundle);
            }

            @Override // androidx.mediarouter.media.u.d
            public void b(Bundle bundle) {
                e.this.g = bundle.getString("groupableTitle");
                e.this.h = bundle.getString("transferableTitle");
            }
        }

        e(String str) {
            this.f = str;
        }

        @Override // androidx.mediarouter.media.A.b
        public int a() {
            return this.m;
        }

        @Override // androidx.mediarouter.media.A.b
        public void b(a aVar) {
            a aVar2 = new a();
            this.l = aVar;
            int iB = aVar.b(this.f, aVar2);
            this.m = iB;
            if (this.i) {
                aVar.q(iB);
                int i = this.j;
                if (i >= 0) {
                    aVar.u(this.m, i);
                    this.j = -1;
                }
                int i2 = this.k;
                if (i2 != 0) {
                    aVar.x(this.m, i2);
                    this.k = 0;
                }
            }
        }

        @Override // androidx.mediarouter.media.A.b
        public void c() {
            a aVar = this.l;
            if (aVar != null) {
                aVar.o(this.m);
                this.l = null;
                this.m = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public boolean d(Intent intent, u.d dVar) {
            a aVar = this.l;
            if (aVar != null) {
                return aVar.r(this.m, intent, dVar);
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void e() {
            A.this.L(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void f() {
            this.i = true;
            a aVar = this.l;
            if (aVar != null) {
                aVar.q(this.m);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void g(int i) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.u(this.m, i);
            } else {
                this.j = i;
                this.k = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void h() {
            i(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void i(int i) {
            this.i = false;
            a aVar = this.l;
            if (aVar != null) {
                aVar.v(this.m, i);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void j(int i) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.x(this.m, i);
            } else {
                this.k += i;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public String k() {
            return this.g;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public String l() {
            return this.h;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void n(@NonNull String str) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.a(this.m, str);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void o(@NonNull String str) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.p(this.m, str);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void p(@Nullable List<String> list) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.w(this.m, list);
            }
        }

        void r(p pVar, List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> list) {
            m(pVar, list);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class f extends MediaRouteProvider.d implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f915a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f916b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f917c;
        private int d = -1;
        private int e;
        private a f;
        private int g;

        f(String str, String str2) {
            this.f915a = str;
            this.f916b = str2;
        }

        @Override // androidx.mediarouter.media.A.b
        public int a() {
            return this.g;
        }

        @Override // androidx.mediarouter.media.A.b
        public void b(a aVar) {
            this.f = aVar;
            int iC = aVar.c(this.f915a, this.f916b);
            this.g = iC;
            if (this.f917c) {
                aVar.q(iC);
                int i = this.d;
                if (i >= 0) {
                    aVar.u(this.g, i);
                    this.d = -1;
                }
                int i2 = this.e;
                if (i2 != 0) {
                    aVar.x(this.g, i2);
                    this.e = 0;
                }
            }
        }

        @Override // androidx.mediarouter.media.A.b
        public void c() {
            a aVar = this.f;
            if (aVar != null) {
                aVar.o(this.g);
                this.f = null;
                this.g = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public boolean d(Intent intent, u.d dVar) {
            a aVar = this.f;
            if (aVar != null) {
                return aVar.r(this.g, intent, dVar);
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void e() {
            A.this.L(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void f() {
            this.f917c = true;
            a aVar = this.f;
            if (aVar != null) {
                aVar.q(this.g);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void g(int i) {
            a aVar = this.f;
            if (aVar != null) {
                aVar.u(this.g, i);
            } else {
                this.d = i;
                this.e = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void h() {
            i(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void i(int i) {
            this.f917c = false;
            a aVar = this.f;
            if (aVar != null) {
                aVar.v(this.g, i);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.d
        public void j(int i) {
            a aVar = this.f;
            if (aVar != null) {
                aVar.x(this.g, i);
            } else {
                this.e += i;
            }
        }
    }

    public A(Context context, ComponentName componentName) {
        super(context, new MediaRouteProvider.c(componentName));
        this.k = new ArrayList<>();
        this.i = componentName;
        this.j = new c();
    }

    private void A() {
        if (this.m) {
            return;
        }
        if (p) {
            Log.d("MediaRouteProviderProxy", this + ": Binding");
        }
        Intent intent = new Intent("android.media.MediaRouteProviderService");
        intent.setComponent(this.i);
        try {
            boolean zBindService = n().bindService(intent, this, Build.VERSION.SDK_INT >= 29 ? 4097 : 1);
            this.m = zBindService;
            if (zBindService || !p) {
                return;
            }
            Log.d("MediaRouteProviderProxy", this + ": Bind failed");
        } catch (SecurityException e2) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Bind failed", e2);
            }
        }
    }

    private MediaRouteProvider.DynamicGroupRouteController B(String str) {
        r rVarO = o();
        if (rVarO == null) {
            return null;
        }
        List<p> listC = rVarO.c();
        int size = listC.size();
        for (int i = 0; i < size; i++) {
            if (listC.get(i).m().equals(str)) {
                e eVar = new e(str);
                this.k.add(eVar);
                if (this.o) {
                    eVar.b(this.n);
                }
                S();
                return eVar;
            }
        }
        return null;
    }

    private MediaRouteProvider.d C(String str, String str2) {
        r rVarO = o();
        if (rVarO == null) {
            return null;
        }
        List<p> listC = rVarO.c();
        int size = listC.size();
        for (int i = 0; i < size; i++) {
            if (listC.get(i).m().equals(str)) {
                f fVar = new f(str, str2);
                this.k.add(fVar);
                if (this.o) {
                    fVar.b(this.n);
                }
                S();
                return fVar;
            }
        }
        return null;
    }

    private void D() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            this.k.get(i).c();
        }
    }

    private void E() {
        if (this.n != null) {
            x(null);
            this.o = false;
            D();
            this.n.d();
            this.n = null;
        }
    }

    private b F(int i) {
        for (b bVar : this.k) {
            if (bVar.a() == i) {
                return bVar;
            }
        }
        return null;
    }

    private boolean O() {
        if (this.l) {
            return (p() == null && this.k.isEmpty()) ? false : true;
        }
        return false;
    }

    private void R() {
        if (this.m) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Unbinding");
            }
            this.m = false;
            E();
            try {
                n().unbindService(this);
            } catch (IllegalArgumentException e2) {
                Log.e("MediaRouteProviderProxy", this + ": unbindService failed", e2);
            }
        }
    }

    private void S() {
        if (O()) {
            A();
        } else {
            R();
        }
    }

    private void z() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            this.k.get(i).b(this.n);
        }
    }

    public boolean G(String str, String str2) {
        return this.i.getPackageName().equals(str) && this.i.getClassName().equals(str2);
    }

    void H(a aVar, r rVar) {
        if (this.n == aVar) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Descriptor changed, descriptor=" + rVar);
            }
            x(rVar);
        }
    }

    void I(a aVar) {
        if (this.n == aVar) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Service connection died");
            }
            E();
        }
    }

    void J(a aVar, String str) {
        if (this.n == aVar) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Service connection error - " + str);
            }
            R();
        }
    }

    void K(a aVar) {
        if (this.n == aVar) {
            this.o = true;
            z();
            q qVarP = p();
            if (qVarP != null) {
                this.n.t(qVarP);
            }
        }
    }

    void L(b bVar) {
        this.k.remove(bVar);
        bVar.c();
        S();
    }

    void M(a aVar, int i, p pVar, List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> list) {
        if (this.n == aVar) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": DynamicRouteDescriptors changed, descriptors=" + list);
            }
            b bVarF = F(i);
            if (bVarF instanceof e) {
                ((e) bVarF).r(pVar, list);
            }
        }
    }

    public void N() {
        if (this.n == null && O()) {
            R();
            A();
        }
    }

    public void P() {
        if (this.l) {
            return;
        }
        if (p) {
            Log.d("MediaRouteProviderProxy", this + ": Starting");
        }
        this.l = true;
        S();
    }

    public void Q() {
        if (this.l) {
            if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Stopping");
            }
            this.l = false;
            S();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (p) {
            Log.d("MediaRouteProviderProxy", this + ": Connected");
        }
        if (this.m) {
            E();
            Messenger messenger = iBinder != null ? new Messenger(iBinder) : null;
            if (!s.a(messenger)) {
                Log.e("MediaRouteProviderProxy", this + ": Service returned invalid messenger binder");
                return;
            }
            a aVar = new a(messenger);
            if (aVar.n()) {
                this.n = aVar;
            } else if (p) {
                Log.d("MediaRouteProviderProxy", this + ": Registration failed");
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (p) {
            Log.d("MediaRouteProviderProxy", this + ": Service disconnected");
        }
        E();
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.DynamicGroupRouteController s(@NonNull String str) {
        if (str != null) {
            return B(str);
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.d t(@NonNull String str) {
        if (str != null) {
            return C(str, null);
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    public String toString() {
        return "Service connection " + this.i.flattenToShortString();
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.d u(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return C(str, str2);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void v(q qVar) {
        if (this.o) {
            this.n.t(qVar);
        }
        S();
    }
}
