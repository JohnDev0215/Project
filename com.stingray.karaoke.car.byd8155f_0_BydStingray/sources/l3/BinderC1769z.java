package l3;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.List;
import k3.C1646h0;

/* JADX INFO: renamed from: l3.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC1769z extends Binder implements InterfaceC1748d {
    public static final /* synthetic */ int d = 0;
    public final WeakReference c;

    public BinderC1769z(C1646h0 c1646h0) {
        attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        this.c = new WeakReference(c1646h0);
    }

    @Override // l3.InterfaceC1748d
    public final void E(X x8) {
        C1646h0 c1646h0 = (C1646h0) this.c.get();
        if (c1646h0 != null) {
            c1646h0.i(2, x8, null);
        }
    }

    @Override // l3.InterfaceC1748d
    public final void R0(C1741F c1741f) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1748d
    public final void X(int i7) {
        C1646h0 c1646h0 = (C1646h0) this.c.get();
        if (c1646h0 != null) {
            c1646h0.i(12, Integer.valueOf(i7), null);
        }
    }

    @Override // l3.InterfaceC1748d
    public final void Z(Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // l3.InterfaceC1748d
    public final void d(int i7) {
        C1646h0 c1646h0 = (C1646h0) this.c.get();
        if (c1646h0 != null) {
            c1646h0.i(9, Integer.valueOf(i7), null);
        }
    }

    @Override // l3.InterfaceC1748d
    public final void m0(List list) {
        throw new AssertionError();
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        if (i7 == 1598968902) {
            parcel2.getClass();
            parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
        }
        WeakReference weakReference = this.c;
        switch (i7) {
            case 1:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                String string = parcel.readString();
                Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                C1646h0 c1646h0 = (C1646h0) weakReference.get();
                if (c1646h0 != null) {
                    c1646h0.i(1, string, bundle);
                }
                return true;
            case 2:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                u();
                return true;
            case 3:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                E(parcel.readInt() != 0 ? X.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 4:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                R0(parcel.readInt() != 0 ? C1741F.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 5:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                m0(parcel.createTypedArrayList(M.CREATOR));
                return true;
            case 6:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                q(parcel.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                return true;
            case 7:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                Z(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 8:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                y(parcel.readInt() != 0 ? V.CREATOR.createFromParcel(parcel) : null);
                return true;
            case 9:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                d(parcel.readInt());
                return true;
            case 10:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                parcel.readInt();
                return true;
            case 11:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                boolean z6 = parcel.readInt() != 0;
                C1646h0 c1646h02 = (C1646h0) weakReference.get();
                if (c1646h02 != null) {
                    c1646h02.i(11, Boolean.valueOf(z6), null);
                }
                return true;
            case 12:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                X(parcel.readInt());
                return true;
            case 13:
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                C1646h0 c1646h03 = (C1646h0) weakReference.get();
                if (c1646h03 != null) {
                    c1646h03.i(13, null, null);
                }
                return true;
            default:
                return super.onTransact(i7, parcel, parcel2, i10);
        }
    }

    @Override // l3.InterfaceC1748d
    public final void q(CharSequence charSequence) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1748d
    public final void u() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1748d
    public final void y(V v10) {
        throw new AssertionError();
    }
}
