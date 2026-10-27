package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.view.Display;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.p;
import androidx.mediarouter.media.r;
import androidx.mediarouter.media.u;
import androidx.mediarouter.media.x;
import androidx.mediarouter.media.y;
import androidx.mediarouter.media.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
abstract class D extends MediaRouteProvider {

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(24)
    private static class a extends d {
        public a(Context context, f fVar) {
            super(context, fVar);
        }

        @Override // androidx.mediarouter.media.D.d, androidx.mediarouter.media.D.c, androidx.mediarouter.media.D.b
        protected void O(b.C0034b c0034b, p.a aVar) {
            super.O(c0034b, aVar);
            aVar.g(w.a(c0034b.f927a));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(16)
    static class b extends D implements x.a, x.g {
        private static final ArrayList<IntentFilter> u;
        private static final ArrayList<IntentFilter> v;
        private final f i;
        protected final Object j;
        protected final Object k;
        protected final Object l;
        protected final Object m;
        protected int n;
        protected boolean o;
        protected boolean p;
        protected final ArrayList<C0034b> q;
        protected final ArrayList<c> r;
        private x.e s;
        private x.c t;

        /* JADX INFO: compiled from: caradapter-bug */
        protected static final class a extends MediaRouteProvider.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Object f926a;

            public a(Object obj) {
                this.f926a = obj;
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.d
            public void g(int i) {
                x.d.i(this.f926a, i);
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.d
            public void j(int i) {
                x.d.j(this.f926a, i);
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.D$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        protected static final class C0034b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final Object f927a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final String f928b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public p f929c;

            public C0034b(Object obj, String str) {
                this.f927a = obj;
                this.f928b = str;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        protected static final class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final u.g f930a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final Object f931b;

            public c(u.g gVar, Object obj) {
                this.f930a = gVar;
                this.f931b = obj;
            }
        }

        static {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            ArrayList<IntentFilter> arrayList = new ArrayList<>();
            u = arrayList;
            arrayList.add(intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
            ArrayList<IntentFilter> arrayList2 = new ArrayList<>();
            v = arrayList2;
            arrayList2.add(intentFilter2);
        }

        public b(Context context, f fVar) {
            super(context);
            this.q = new ArrayList<>();
            this.r = new ArrayList<>();
            this.i = fVar;
            this.j = x.g(context);
            this.k = G();
            this.l = H();
            this.m = x.d(this.j, context.getResources().getString(R$string.mr_user_route_category_name), false);
            T();
        }

        private boolean E(Object obj) {
            if (N(obj) != null || I(obj) >= 0) {
                return false;
            }
            C0034b c0034b = new C0034b(obj, F(obj));
            S(c0034b);
            this.q.add(c0034b);
            return true;
        }

        private String F(Object obj) {
            String str = L() == obj ? "DEFAULT_ROUTE" : String.format(Locale.US, "ROUTE_%08x", Integer.valueOf(M(obj).hashCode()));
            if (J(str) < 0) {
                return str;
            }
            int i = 2;
            while (true) {
                String str2 = String.format(Locale.US, "%s_%d", str, Integer.valueOf(i));
                if (J(str2) < 0) {
                    return str2;
                }
                i++;
            }
        }

        private void T() {
            R();
            Iterator it = x.h(this.j).iterator();
            boolean zE = false;
            while (it.hasNext()) {
                zE |= E(it.next());
            }
            if (zE) {
                P();
            }
        }

        @Override // androidx.mediarouter.media.D
        public void A(u.g gVar) {
            if (gVar.q() == this) {
                int I = I(x.i(this.j, 8388611));
                if (I < 0 || !this.q.get(I).f928b.equals(gVar.e())) {
                    return;
                }
                gVar.H();
                return;
            }
            Object objE = x.e(this.j, this.m);
            c cVar = new c(gVar, objE);
            x.d.k(objE, cVar);
            x.f.e(objE, this.l);
            U(cVar);
            this.r.add(cVar);
            x.b(this.j, objE);
        }

        @Override // androidx.mediarouter.media.D
        public void B(u.g gVar) {
            int iK;
            if (gVar.q() == this || (iK = K(gVar)) < 0) {
                return;
            }
            U(this.r.get(iK));
        }

        @Override // androidx.mediarouter.media.D
        public void C(u.g gVar) {
            int iK;
            if (gVar.q() == this || (iK = K(gVar)) < 0) {
                return;
            }
            c cVarRemove = this.r.remove(iK);
            x.d.k(cVarRemove.f931b, null);
            x.f.e(cVarRemove.f931b, null);
            x.k(this.j, cVarRemove.f931b);
        }

        @Override // androidx.mediarouter.media.D
        public void D(u.g gVar) {
            if (gVar.B()) {
                if (gVar.q() != this) {
                    int iK = K(gVar);
                    if (iK >= 0) {
                        Q(this.r.get(iK).f931b);
                        return;
                    }
                    return;
                }
                int iJ = J(gVar.e());
                if (iJ >= 0) {
                    Q(this.q.get(iJ).f927a);
                }
            }
        }

        protected Object G() {
            return x.c(this);
        }

        protected Object H() {
            return x.f(this);
        }

        protected int I(Object obj) {
            int size = this.q.size();
            for (int i = 0; i < size; i++) {
                if (this.q.get(i).f927a == obj) {
                    return i;
                }
            }
            return -1;
        }

        protected int J(String str) {
            int size = this.q.size();
            for (int i = 0; i < size; i++) {
                if (this.q.get(i).f928b.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        protected int K(u.g gVar) {
            int size = this.r.size();
            for (int i = 0; i < size; i++) {
                if (this.r.get(i).f930a == gVar) {
                    return i;
                }
            }
            return -1;
        }

        protected Object L() {
            if (this.t == null) {
                this.t = new x.c();
            }
            return this.t.a(this.j);
        }

        protected String M(Object obj) {
            CharSequence charSequenceA = x.d.a(obj, n());
            return charSequenceA != null ? charSequenceA.toString() : "";
        }

        protected c N(Object obj) {
            Object objE = x.d.e(obj);
            if (objE instanceof c) {
                return (c) objE;
            }
            return null;
        }

        protected void O(C0034b c0034b, p.a aVar) {
            int iD = x.d.d(c0034b.f927a);
            if ((iD & 1) != 0) {
                aVar.b(u);
            }
            if ((iD & 2) != 0) {
                aVar.b(v);
            }
            aVar.n(x.d.c(c0034b.f927a));
            aVar.m(x.d.b(c0034b.f927a));
            aVar.p(x.d.f(c0034b.f927a));
            aVar.r(x.d.h(c0034b.f927a));
            aVar.q(x.d.g(c0034b.f927a));
        }

        protected void P() {
            r.a aVar = new r.a();
            int size = this.q.size();
            for (int i = 0; i < size; i++) {
                aVar.a(this.q.get(i).f929c);
            }
            x(aVar.c());
        }

        protected void Q(Object obj) {
            if (this.s == null) {
                this.s = new x.e();
            }
            this.s.a(this.j, 8388611, obj);
        }

        protected void R() {
            if (this.p) {
                this.p = false;
                x.j(this.j, this.k);
            }
            int i = this.n;
            if (i != 0) {
                this.p = true;
                x.a(this.j, i, this.k);
            }
        }

        protected void S(C0034b c0034b) {
            p.a aVar = new p.a(c0034b.f928b, M(c0034b.f927a));
            O(c0034b, aVar);
            c0034b.f929c = aVar.c();
        }

        protected void U(c cVar) {
            x.f.a(cVar.f931b, cVar.f930a.l());
            x.f.c(cVar.f931b, cVar.f930a.n());
            x.f.b(cVar.f931b, cVar.f930a.m());
            x.f.d(cVar.f931b, cVar.f930a.r());
            x.f.g(cVar.f931b, cVar.f930a.t());
            x.f.f(cVar.f931b, cVar.f930a.s());
        }

        @Override // androidx.mediarouter.media.x.a
        public void b(Object obj) {
            int I;
            if (N(obj) != null || (I = I(obj)) < 0) {
                return;
            }
            this.q.remove(I);
            P();
        }

        @Override // androidx.mediarouter.media.x.a
        public void c(Object obj, Object obj2) {
        }

        @Override // androidx.mediarouter.media.x.a
        public void d(Object obj) {
            int I;
            if (N(obj) != null || (I = I(obj)) < 0) {
                return;
            }
            S(this.q.get(I));
            P();
        }

        @Override // androidx.mediarouter.media.x.a
        public void e(int i, Object obj) {
        }

        @Override // androidx.mediarouter.media.x.g
        public void f(Object obj, int i) {
            c cVarN = N(obj);
            if (cVarN != null) {
                cVarN.f930a.G(i);
            }
        }

        @Override // androidx.mediarouter.media.x.a
        public void g(Object obj, Object obj2, int i) {
        }

        @Override // androidx.mediarouter.media.x.a
        public void h(Object obj) {
            if (E(obj)) {
                P();
            }
        }

        @Override // androidx.mediarouter.media.x.g
        public void i(Object obj, int i) {
            c cVarN = N(obj);
            if (cVarN != null) {
                cVarN.f930a.F(i);
            }
        }

        @Override // androidx.mediarouter.media.x.a
        public void j(Object obj) {
            int I;
            if (N(obj) != null || (I = I(obj)) < 0) {
                return;
            }
            C0034b c0034b = this.q.get(I);
            int iF = x.d.f(obj);
            if (iF != c0034b.f929c.u()) {
                p.a aVar = new p.a(c0034b.f929c);
                aVar.p(iF);
                c0034b.f929c = aVar.c();
                P();
            }
        }

        @Override // androidx.mediarouter.media.x.a
        public void k(int i, Object obj) {
            if (obj != x.i(this.j, 8388611)) {
                return;
            }
            c cVarN = N(obj);
            if (cVarN != null) {
                cVarN.f930a.H();
                return;
            }
            int I = I(obj);
            if (I >= 0) {
                this.i.c(this.q.get(I).f928b);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public MediaRouteProvider.d t(String str) {
            int iJ = J(str);
            if (iJ >= 0) {
                return new a(this.q.get(iJ).f927a);
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public void v(q qVar) {
            boolean zE;
            int i = 0;
            if (qVar != null) {
                List<String> listE = qVar.d().e();
                int size = listE.size();
                int i2 = 0;
                while (i < size) {
                    String str = listE.get(i);
                    i2 = str.equals("android.media.intent.category.LIVE_AUDIO") ? i2 | 1 : str.equals("android.media.intent.category.LIVE_VIDEO") ? i2 | 2 : i2 | 8388608;
                    i++;
                }
                zE = qVar.e();
                i = i2;
            } else {
                zE = false;
            }
            if (this.n == i && this.o == zE) {
                return;
            }
            this.n = i;
            this.o = zE;
            T();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(17)
    private static class c extends b implements y.b {
        private y.a w;
        private y.d x;

        public c(Context context, f fVar) {
            super(context, fVar);
        }

        @Override // androidx.mediarouter.media.D.b
        protected Object G() {
            return y.a(this);
        }

        @Override // androidx.mediarouter.media.D.b
        protected void O(b.C0034b c0034b, p.a aVar) {
            super.O(c0034b, aVar);
            if (!y.e.b(c0034b.f927a)) {
                aVar.h(false);
            }
            if (V(c0034b)) {
                aVar.e(1);
            }
            Display displayA = y.e.a(c0034b.f927a);
            if (displayA != null) {
                aVar.o(displayA.getDisplayId());
            }
        }

        @Override // androidx.mediarouter.media.D.b
        protected void R() {
            super.R();
            if (this.w == null) {
                this.w = new y.a(n(), q());
            }
            this.w.a(this.o ? this.n : 0);
        }

        protected boolean V(b.C0034b c0034b) {
            if (this.x == null) {
                this.x = new y.d();
            }
            return this.x.a(c0034b.f927a);
        }

        @Override // androidx.mediarouter.media.y.b
        public void a(Object obj) {
            int I = I(obj);
            if (I >= 0) {
                b.C0034b c0034b = this.q.get(I);
                Display displayA = y.e.a(obj);
                int displayId = displayA != null ? displayA.getDisplayId() : -1;
                if (displayId != c0034b.f929c.s()) {
                    p.a aVar = new p.a(c0034b.f929c);
                    aVar.o(displayId);
                    c0034b.f929c = aVar.c();
                    P();
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(18)
    private static class d extends c {
        public d(Context context, f fVar) {
            super(context, fVar);
        }

        @Override // androidx.mediarouter.media.D.b
        protected Object L() {
            return z.b(this.j);
        }

        @Override // androidx.mediarouter.media.D.c, androidx.mediarouter.media.D.b
        protected void O(b.C0034b c0034b, p.a aVar) {
            super.O(c0034b, aVar);
            CharSequence charSequenceA = z.a.a(c0034b.f927a);
            if (charSequenceA != null) {
                aVar.f(charSequenceA.toString());
            }
        }

        @Override // androidx.mediarouter.media.D.b
        protected void Q(Object obj) {
            x.l(this.j, 8388611, obj);
        }

        @Override // androidx.mediarouter.media.D.c, androidx.mediarouter.media.D.b
        protected void R() {
            if (this.p) {
                x.j(this.j, this.k);
            }
            this.p = true;
            z.a(this.j, this.n, this.k, (this.o ? 1 : 0) | 2);
        }

        @Override // androidx.mediarouter.media.D.b
        protected void U(b.c cVar) {
            super.U(cVar);
            z.b.a(cVar.f931b, cVar.f930a.d());
        }

        @Override // androidx.mediarouter.media.D.c
        protected boolean V(b.C0034b c0034b) {
            return z.a.b(c0034b.f927a);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class e extends D {
        private static final ArrayList<IntentFilter> l;
        final AudioManager i;
        private final b j;
        int k;

        /* JADX INFO: compiled from: caradapter-bug */
        final class a extends MediaRouteProvider.d {
            a() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.d
            public void g(int i) {
                e.this.i.setStreamVolume(3, i, 0);
                e.this.E();
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.d
            public void j(int i) {
                int streamVolume = e.this.i.getStreamVolume(3);
                if (Math.min(e.this.i.getStreamMaxVolume(3), Math.max(0, i + streamVolume)) != streamVolume) {
                    e.this.i.setStreamVolume(3, streamVolume, 0);
                }
                e.this.E();
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        final class b extends BroadcastReceiver {
            b() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra;
                if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3 && (intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1)) >= 0) {
                    e eVar = e.this;
                    if (intExtra != eVar.k) {
                        eVar.E();
                    }
                }
            }
        }

        static {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            intentFilter.addCategory("android.media.intent.category.LIVE_VIDEO");
            ArrayList<IntentFilter> arrayList = new ArrayList<>();
            l = arrayList;
            arrayList.add(intentFilter);
        }

        public e(Context context) {
            super(context);
            this.k = -1;
            this.i = (AudioManager) context.getSystemService("audio");
            b bVar = new b();
            this.j = bVar;
            context.registerReceiver(bVar, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            E();
        }

        void E() {
            Resources resources = n().getResources();
            int streamMaxVolume = this.i.getStreamMaxVolume(3);
            this.k = this.i.getStreamVolume(3);
            p.a aVar = new p.a("DEFAULT_ROUTE", resources.getString(R$string.mr_system_route_name));
            aVar.b(l);
            aVar.m(3);
            aVar.n(0);
            aVar.q(1);
            aVar.r(streamMaxVolume);
            aVar.p(this.k);
            p pVarC = aVar.c();
            r.a aVar2 = new r.a();
            aVar2.a(pVarC);
            x(aVar2.c());
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public MediaRouteProvider.d t(String str) {
            if (str.equals("DEFAULT_ROUTE")) {
                return new a();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface f {
        void c(String str);
    }

    protected D(Context context) {
        super(context, new MediaRouteProvider.c(new ComponentName("android", D.class.getName())));
    }

    public static D z(Context context, f fVar) {
        int i = Build.VERSION.SDK_INT;
        return i >= 24 ? new a(context, fVar) : i >= 18 ? new d(context, fVar) : i >= 17 ? new c(context, fVar) : i >= 16 ? new b(context, fVar) : new e(context);
    }

    public void A(u.g gVar) {
    }

    public void B(u.g gVar) {
    }

    public void C(u.g gVar) {
    }

    public void D(u.g gVar) {
    }
}
