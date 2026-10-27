package k3;

import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import d5.C1077F;
import d5.C1078G;
import h2.AbstractC1343A;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: k3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1647i {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final String f17968A;
    public static final String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final String f17969C;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f17970p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f17971q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f17972r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f17973s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f17974t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f17975u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String f17976v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final String f17977w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final String f17978x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String f17979y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final String f17980z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17981a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17982b;
    public final InterfaceC1655m c;
    public final PendingIntent d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final z1 f17983e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e2.S f17984f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e2.S f17985g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Bundle f17986h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Bundle f17987i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final q1 f17988j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final d5.I f17989k;
    public final d5.I l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final MediaSession.Token f17990m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d5.d0 f17991n;

    static {
        int i7 = AbstractC1343A.f15195a;
        o = Integer.toString(0, 36);
        f17970p = Integer.toString(1, 36);
        f17971q = Integer.toString(2, 36);
        f17972r = Integer.toString(9, 36);
        f17973s = Integer.toString(14, 36);
        f17974t = Integer.toString(13, 36);
        f17975u = Integer.toString(3, 36);
        f17976v = Integer.toString(4, 36);
        f17977w = Integer.toString(5, 36);
        f17978x = Integer.toString(6, 36);
        f17979y = Integer.toString(11, 36);
        f17980z = Integer.toString(7, 36);
        f17968A = Integer.toString(8, 36);
        B = Integer.toString(10, 36);
        f17969C = Integer.toString(12, 36);
    }

    public C1647i(int i7, int i10, InterfaceC1655m interfaceC1655m, PendingIntent pendingIntent, d5.I i11, d5.I i12, d5.d0 d0Var, z1 z1Var, e2.S s6, e2.S s10, Bundle bundle, Bundle bundle2, q1 q1Var, MediaSession.Token token) {
        this.f17981a = i7;
        this.f17982b = i10;
        this.c = interfaceC1655m;
        this.d = pendingIntent;
        this.f17989k = i11;
        this.l = i12;
        this.f17991n = d0Var;
        this.f17983e = z1Var;
        this.f17984f = s6;
        this.f17985g = s10;
        this.f17986h = bundle;
        this.f17987i = bundle2;
        this.f17988j = q1Var;
        this.f17990m = token;
    }

    public static C1647i a(Bundle bundle) {
        d5.d0 d0VarH;
        d5.d0 d0VarH2;
        d5.d0 d0VarH3;
        InterfaceC1655m interfaceC1655m;
        IBinder binder = bundle.getBinder(B);
        if (binder instanceof BinderC1645h) {
            return ((BinderC1645h) binder).c;
        }
        int i7 = bundle.getInt(o, 0);
        int i10 = bundle.getInt(f17968A, 0);
        IBinder binder2 = bundle.getBinder(f17970p);
        binder2.getClass();
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(f17971q);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(f17972r);
        if (parcelableArrayList != null) {
            C1077F c1077fU = d5.I.u();
            for (int i11 = 0; i11 < parcelableArrayList.size(); i11++) {
                Bundle bundle2 = (Bundle) parcelableArrayList.get(i11);
                bundle2.getClass();
                c1077fU.a(C1627b.a(i10, bundle2));
            }
            d0VarH = c1077fU.h();
        } else {
            C1078G c1078g = d5.I.f13384m;
            d0VarH = d5.d0.f13415p;
        }
        d5.d0 d0Var = d0VarH;
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(f17973s);
        if (parcelableArrayList2 != null) {
            C1077F c1077fU2 = d5.I.u();
            for (int i12 = 0; i12 < parcelableArrayList2.size(); i12++) {
                Bundle bundle3 = (Bundle) parcelableArrayList2.get(i12);
                bundle3.getClass();
                c1077fU2.a(C1627b.a(i10, bundle3));
            }
            d0VarH2 = c1077fU2.h();
        } else {
            C1078G c1078g2 = d5.I.f13384m;
            d0VarH2 = d5.d0.f13415p;
        }
        d5.d0 d0Var2 = d0VarH2;
        ArrayList parcelableArrayList3 = bundle.getParcelableArrayList(f17974t);
        if (parcelableArrayList3 != null) {
            C1077F c1077fU3 = d5.I.u();
            for (int i13 = 0; i13 < parcelableArrayList3.size(); i13++) {
                Bundle bundle4 = (Bundle) parcelableArrayList3.get(i13);
                bundle4.getClass();
                c1077fU3.a(C1627b.a(i10, bundle4));
            }
            d0VarH3 = c1077fU3.h();
        } else {
            C1078G c1078g3 = d5.I.f13384m;
            d0VarH3 = d5.d0.f13415p;
        }
        d5.d0 d0Var3 = d0VarH3;
        Bundle bundle5 = bundle.getBundle(f17975u);
        z1 z1VarA = bundle5 == null ? z1.f18172b : z1.a(bundle5);
        Bundle bundle6 = bundle.getBundle(f17977w);
        e2.S sC = bundle6 == null ? e2.S.f13999b : e2.S.c(bundle6);
        Bundle bundle7 = bundle.getBundle(f17976v);
        e2.S sC2 = bundle7 == null ? e2.S.f13999b : e2.S.c(bundle7);
        Bundle bundle8 = bundle.getBundle(f17978x);
        Bundle bundle9 = bundle.getBundle(f17979y);
        Bundle bundle10 = bundle.getBundle(f17980z);
        q1 q1VarR = bundle10 == null ? q1.f18034F : q1.r(i10, bundle10);
        MediaSession.Token token = (MediaSession.Token) bundle.getParcelable(f17969C);
        int i14 = m1.f18013i;
        IInterface iInterfaceQueryLocalInterface = binder2.queryLocalInterface("androidx.media3.session.IMediaSession");
        if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1655m)) {
            C1653l c1653l = new C1653l();
            c1653l.c = binder2;
            interfaceC1655m = c1653l;
        } else {
            interfaceC1655m = (InterfaceC1655m) iInterfaceQueryLocalInterface;
        }
        return new C1647i(i7, i10, interfaceC1655m, pendingIntent, d0Var, d0Var2, d0Var3, z1VarA, sC2, sC, bundle8 == null ? Bundle.EMPTY : bundle8, bundle9 == null ? Bundle.EMPTY : bundle9, q1VarR, token);
    }

    public final Bundle b(int i7) {
        Bundle bundle = new Bundle();
        bundle.putInt(o, this.f17981a);
        bundle.putBinder(f17970p, this.c.asBinder());
        bundle.putParcelable(f17971q, this.d);
        d5.I i10 = this.f17989k;
        boolean zIsEmpty = i10.isEmpty();
        String str = f17972r;
        if (!zIsEmpty) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(i10.size());
            Iterator<E> it = i10.iterator();
            while (it.hasNext()) {
                arrayList.add(((C1627b) it.next()).b());
            }
            bundle.putParcelableArrayList(str, arrayList);
        }
        d5.I i11 = this.l;
        if (!i11.isEmpty()) {
            if (i7 >= 7) {
                ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(i11.size());
                Iterator<E> it2 = i11.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((C1627b) it2.next()).b());
                }
                bundle.putParcelableArrayList(f17973s, arrayList2);
            } else {
                ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>(i11.size());
                Iterator<E> it3 = i11.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(((C1627b) it3.next()).b());
                }
                bundle.putParcelableArrayList(str, arrayList3);
            }
        }
        d5.d0 d0Var = this.f17991n;
        if (!d0Var.isEmpty()) {
            ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>(d0Var.o);
            C1078G c1078gListIterator = d0Var.listIterator(0);
            while (c1078gListIterator.hasNext()) {
                arrayList4.add(((C1627b) c1078gListIterator.next()).b());
            }
            bundle.putParcelableArrayList(f17974t, arrayList4);
        }
        z1 z1Var = this.f17983e;
        z1Var.getClass();
        Bundle bundle2 = new Bundle();
        ArrayList<? extends Parcelable> arrayList5 = new ArrayList<>();
        d5.s0 it4 = z1Var.f18173a.iterator();
        while (it4.hasNext()) {
            arrayList5.add(((y1) it4.next()).b());
        }
        bundle2.putParcelableArrayList(z1.c, arrayList5);
        bundle.putBundle(f17975u, bundle2);
        e2.S s6 = this.f17984f;
        bundle.putBundle(f17976v, s6.d());
        e2.S s10 = this.f17985g;
        bundle.putBundle(f17977w, s10.d());
        bundle.putBundle(f17978x, this.f17986h);
        bundle.putBundle(f17979y, this.f17987i);
        bundle.putBundle(f17980z, this.f17988j.q(n1.g(s6, s10), false, false).t(i7));
        bundle.putInt(f17968A, this.f17982b);
        MediaSession.Token token = this.f17990m;
        if (token != null) {
            bundle.putParcelable(f17969C, token);
        }
        return bundle;
    }
}
