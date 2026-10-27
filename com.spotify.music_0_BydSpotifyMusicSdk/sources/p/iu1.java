package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class iu1 implements Parcelable {
    public static final Parcelable.Creator<iu1> CREATOR = new bg1(27);
    public final Set A;
    public final Set B;
    public final Set C;
    public final Set c;
    public final Set e;
    public final Set k;
    public final Set l;
    public final Set m;
    public final Set n;
    public final Set o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Set f137p;
    public final Set q;
    public final Set r;
    public final Set s;
    public final Set t;
    public final Set u;
    public final Set v;
    public final Set w;
    public final Set x;
    public final Set y;
    public final Set z;

    public iu1(Set set, Set set2, Set set3, Set set4, Set set5, Set set6, Set set7, Set set8, Set set9, Set set10, Set set11, Set set12, Set set13, Set set14, Set set15, Set set16, Set set17, Set set18, Set set19, Set set20, Set set21) {
        this.c = a(set);
        this.e = a(set2);
        this.k = a(set3);
        this.l = a(set4);
        this.m = a(set5);
        this.n = a(set6);
        this.o = a(set7);
        this.f137p = a(set8);
        this.q = a(set9);
        this.r = a(set10);
        this.s = a(set11);
        this.t = a(set12);
        this.u = a(set13);
        this.v = a(set14);
        this.w = a(set15);
        this.x = a(set16);
        this.y = a(set17);
        this.z = a(set18);
        this.A = a(set19);
        this.B = a(set20);
        this.C = a(set21);
    }

    public static Set a(Set set) {
        if (set == null) {
            set = Collections.emptySet();
        }
        return Collections.unmodifiableSet(set);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iu1)) {
            return false;
        }
        iu1 iu1Var = (iu1) obj;
        return this.m.equals(iu1Var.m) && this.e.equals(iu1Var.e) && this.c.equals(iu1Var.c) && this.n.equals(iu1Var.n) && this.r.equals(iu1Var.r) && this.l.equals(iu1Var.l) && this.k.equals(iu1Var.k) && this.o.equals(iu1Var.o) && this.f137p.equals(iu1Var.f137p) && this.q.equals(iu1Var.q) && this.s.equals(iu1Var.s) && this.t.equals(iu1Var.t) && this.u.equals(iu1Var.u) && this.v.equals(iu1Var.v) && this.w.equals(iu1Var.w) && this.x.equals(iu1Var.x) && this.y.equals(iu1Var.y) && this.z.equals(iu1Var.z) && this.A.equals(iu1Var.A) && this.B.equals(iu1Var.B) && this.C.equals(iu1Var.C);
    }

    public final int hashCode() {
        return this.C.hashCode() + ((this.B.hashCode() + ((this.A.hashCode() + ((this.z.hashCode() + ((this.y.hashCode() + ((this.x.hashCode() + ((this.w.hashCode() + ((this.v.hashCode() + ((this.u.hashCode() + ((this.t.hashCode() + ((this.s.hashCode() + ((this.r.hashCode() + ((this.q.hashCode() + ((this.f137p.hashCode() + ((this.o.hashCode() + ((this.n.hashCode() + ((this.m.hashCode() + ((this.l.hashCode() + ((this.k.hashCode() + ((this.e.hashCode() + (this.c.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ux2.D(parcel, this.c);
        ux2.D(parcel, this.e);
        ux2.D(parcel, this.k);
        ux2.D(parcel, this.l);
        ux2.D(parcel, this.m);
        ux2.D(parcel, this.n);
        ux2.D(parcel, this.o);
        ux2.D(parcel, this.f137p);
        ux2.D(parcel, this.q);
        ux2.D(parcel, this.r);
        ux2.D(parcel, this.s);
        ux2.D(parcel, this.t);
        ux2.D(parcel, this.u);
        ux2.D(parcel, this.v);
        ux2.D(parcel, this.w);
        ux2.D(parcel, this.x);
        ux2.D(parcel, this.y);
        ux2.D(parcel, this.z);
        ux2.D(parcel, this.A);
        ux2.D(parcel, this.B);
        ux2.D(parcel, this.C);
    }

    public iu1(Parcel parcel) {
        this.c = ux2.x(parcel);
        this.e = ux2.x(parcel);
        this.k = ux2.x(parcel);
        this.l = ux2.x(parcel);
        this.m = ux2.x(parcel);
        this.n = ux2.x(parcel);
        this.o = ux2.x(parcel);
        this.f137p = ux2.x(parcel);
        this.q = ux2.x(parcel);
        this.r = ux2.x(parcel);
        this.s = ux2.x(parcel);
        this.t = ux2.x(parcel);
        this.u = ux2.x(parcel);
        this.v = ux2.x(parcel);
        this.w = ux2.x(parcel);
        this.x = ux2.x(parcel);
        this.y = ux2.x(parcel);
        this.z = ux2.x(parcel);
        this.A = ux2.x(parcel);
        this.B = ux2.x(parcel);
        this.C = ux2.x(parcel);
    }
}
