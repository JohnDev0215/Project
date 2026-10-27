package T;

import android.os.Parcel;
import android.os.Parcelable;
import e0.AbstractC1155B;
import e0.AbstractC1156C;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import e0.InterfaceC1176p;

/* JADX INFO: renamed from: T.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0732b0 extends AbstractC1155B implements Parcelable, InterfaceC1176p, X, Q0 {
    public static final Parcelable.Creator<C0732b0> CREATOR = new Z(1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public D0 f8234m;

    public C0732b0(int i7) {
        D0 d02 = new D0(i7);
        if (AbstractC1174n.f13720a.p() != null) {
            D0 d03 = new D0(i7);
            d03.f13675a = 1;
            d02.f13676b = d03;
        }
        this.f8234m = d02;
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.f8234m;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // e0.InterfaceC1176p
    public final G0 f() {
        return S.f8224q;
    }

    @Override // T.Q0
    public Object getValue() {
        return Integer.valueOf(i());
    }

    public final int i() {
        return ((D0) AbstractC1174n.t(this.f8234m, this)).c;
    }

    public final void j(int i7) {
        AbstractC1167g abstractC1167gK;
        D0 d02 = (D0) AbstractC1174n.i(this.f8234m);
        if (d02.c != i7) {
            D0 d03 = this.f8234m;
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                ((D0) AbstractC1174n.o(d03, this, abstractC1167gK, d02)).c = i7;
            }
            AbstractC1174n.n(abstractC1167gK, this);
        }
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C l(AbstractC1156C abstractC1156C, AbstractC1156C abstractC1156C2, AbstractC1156C abstractC1156C3) {
        if (((D0) abstractC1156C2).c == ((D0) abstractC1156C3).c) {
            return abstractC1156C2;
        }
        return null;
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord");
        this.f8234m = (D0) abstractC1156C;
    }

    @Override // T.X
    public void setValue(Object obj) {
        j(((Number) obj).intValue());
    }

    public final String toString() {
        return "MutableIntState(value=" + ((D0) AbstractC1174n.i(this.f8234m)).c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(i());
    }
}
