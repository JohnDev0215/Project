package T;

import android.os.Parcel;
import android.os.Parcelable;
import e0.AbstractC1155B;
import e0.AbstractC1156C;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import e0.InterfaceC1176p;

/* JADX INFO: renamed from: T.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0734c0 extends AbstractC1155B implements Parcelable, InterfaceC1176p, X, Q0 {
    public static final Parcelable.Creator<C0734c0> CREATOR = new Z(2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public E0 f8236m;

    public C0734c0(long j3) {
        E0 e02 = new E0(j3);
        if (AbstractC1174n.f13720a.p() != null) {
            E0 e03 = new E0(j3);
            e03.f13675a = 1;
            e02.f13676b = e03;
        }
        this.f8236m = e02;
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.f8236m;
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
        return Long.valueOf(i());
    }

    public final long i() {
        return ((E0) AbstractC1174n.t(this.f8236m, this)).c;
    }

    public final void j(long j3) {
        AbstractC1167g abstractC1167gK;
        E0 e02 = (E0) AbstractC1174n.i(this.f8236m);
        if (e02.c != j3) {
            E0 e03 = this.f8236m;
            synchronized (AbstractC1174n.f13721b) {
                abstractC1167gK = AbstractC1174n.k();
                ((E0) AbstractC1174n.o(e03, this, abstractC1167gK, e02)).c = j3;
            }
            AbstractC1174n.n(abstractC1167gK, this);
        }
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C l(AbstractC1156C abstractC1156C, AbstractC1156C abstractC1156C2, AbstractC1156C abstractC1156C3) {
        if (((E0) abstractC1156C2).c == ((E0) abstractC1156C3).c) {
            return abstractC1156C2;
        }
        return null;
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord");
        this.f8236m = (E0) abstractC1156C;
    }

    @Override // T.X
    public void setValue(Object obj) {
        j(((Number) obj).longValue());
    }

    public final String toString() {
        return "MutableLongState(value=" + ((E0) AbstractC1174n.i(this.f8236m)).c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(i());
    }
}
