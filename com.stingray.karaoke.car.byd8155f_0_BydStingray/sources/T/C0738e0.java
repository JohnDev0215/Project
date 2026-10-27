package T;

import android.os.Parcel;
import android.os.Parcelable;
import e0.AbstractC1155B;
import e0.AbstractC1156C;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import e0.InterfaceC1176p;

/* JADX INFO: renamed from: T.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0738e0 extends AbstractC1155B implements Parcelable, InterfaceC1176p {
    public static final Parcelable.Creator<C0738e0> CREATOR = new C0736d0();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final G0 f8243m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public F0 f8244n;

    public C0738e0(Object obj, G0 g02) {
        this.f8243m = g02;
        F0 f02 = new F0(obj);
        if (AbstractC1174n.f13720a.p() != null) {
            F0 f03 = new F0(obj);
            f03.f13675a = 1;
            f02.f13676b = f03;
        }
        this.f8244n = f02;
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.f8244n;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // e0.InterfaceC1176p
    public final G0 f() {
        return this.f8243m;
    }

    @Override // T.Q0
    public final Object getValue() {
        return ((F0) AbstractC1174n.t(this.f8244n, this)).c;
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C l(AbstractC1156C abstractC1156C, AbstractC1156C abstractC1156C2, AbstractC1156C abstractC1156C3) {
        if (this.f8243m.a(((F0) abstractC1156C2).c, ((F0) abstractC1156C3).c)) {
            return abstractC1156C2;
        }
        return null;
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl>");
        this.f8244n = (F0) abstractC1156C;
    }

    @Override // T.X
    public final void setValue(Object obj) {
        AbstractC1167g abstractC1167gK;
        F0 f02 = (F0) AbstractC1174n.i(this.f8244n);
        if (this.f8243m.a(f02.c, obj)) {
            return;
        }
        F0 f03 = this.f8244n;
        synchronized (AbstractC1174n.f13721b) {
            abstractC1167gK = AbstractC1174n.k();
            ((F0) AbstractC1174n.o(f03, this, abstractC1167gK, f02)).c = obj;
        }
        AbstractC1174n.n(abstractC1167gK, this);
    }

    public final String toString() {
        return "MutableState(value=" + ((F0) AbstractC1174n.i(this.f8244n)).c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        int i10;
        parcel.writeValue(getValue());
        S s6 = S.f8222n;
        G0 g02 = this.f8243m;
        if (A7.m.a(g02, s6)) {
            i10 = 0;
        } else if (A7.m.a(g02, S.f8224q)) {
            i10 = 1;
        } else {
            if (!A7.m.a(g02, S.o)) {
                throw new IllegalStateException("Only known types of MutableState's SnapshotMutationPolicy are supported");
            }
            i10 = 2;
        }
        parcel.writeInt(i10);
    }
}
