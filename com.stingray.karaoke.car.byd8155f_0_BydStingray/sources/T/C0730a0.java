package T;

import android.os.Parcel;
import android.os.Parcelable;
import e0.AbstractC1155B;
import e0.AbstractC1156C;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import e0.InterfaceC1176p;

/* JADX INFO: renamed from: T.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0730a0 extends AbstractC1155B implements Parcelable, InterfaceC1176p, X, Q0 {
    public static final Parcelable.Creator<C0730a0> CREATOR = new Z(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0 f8231m;

    public C0730a0(float f10) {
        C0 c02 = new C0(f10);
        if (AbstractC1174n.f13720a.p() != null) {
            C0 c03 = new C0(f10);
            c03.f13675a = 1;
            c02.f13676b = c03;
        }
        this.f8231m = c02;
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C d() {
        return this.f8231m;
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
        return Float.valueOf(i());
    }

    public final float i() {
        return ((C0) AbstractC1174n.t(this.f8231m, this)).c;
    }

    public final void j(float f10) {
        AbstractC1167g abstractC1167gK;
        C0 c02 = (C0) AbstractC1174n.i(this.f8231m);
        if (c02.c == f10) {
            return;
        }
        C0 c03 = this.f8231m;
        synchronized (AbstractC1174n.f13721b) {
            abstractC1167gK = AbstractC1174n.k();
            ((C0) AbstractC1174n.o(c03, this, abstractC1167gK, c02)).c = f10;
        }
        AbstractC1174n.n(abstractC1167gK, this);
    }

    @Override // e0.InterfaceC1154A
    public final AbstractC1156C l(AbstractC1156C abstractC1156C, AbstractC1156C abstractC1156C2, AbstractC1156C abstractC1156C3) {
        if (((C0) abstractC1156C2).c == ((C0) abstractC1156C3).c) {
            return abstractC1156C2;
        }
        return null;
    }

    @Override // e0.InterfaceC1154A
    public final void q(AbstractC1156C abstractC1156C) {
        A7.m.d(abstractC1156C, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord");
        this.f8231m = (C0) abstractC1156C;
    }

    @Override // T.X
    public void setValue(Object obj) {
        j(((Number) obj).floatValue());
    }

    public final String toString() {
        return "MutableFloatState(value=" + ((C0) AbstractC1174n.i(this.f8231m)).c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeFloat(i());
    }
}
