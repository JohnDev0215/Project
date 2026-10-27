package l3;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class J extends Binder implements InterfaceC1750f {
    public static final /* synthetic */ int d = 0;
    public final AtomicReference c;

    public J(L l) {
        attachInterface(this, "android.support.v4.media.session.IMediaSession");
        this.c = new AtomicReference(l);
    }

    @Override // l3.InterfaceC1750f
    public final void A(boolean z6) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void D0(long j3) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void E0(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void G(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void G0(int i7, int i10) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final V I0() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void J0() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final boolean L() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final PendingIntent M() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void M0(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final int N() {
        K k10 = (K) this.c.get();
        if (k10 != null) {
            return k10.f18531k;
        }
        return -1;
    }

    @Override // l3.InterfaceC1750f
    public final void P(int i7) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final int Q() {
        return 0;
    }

    @Override // l3.InterfaceC1750f
    public final void Q0(int i7) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void R(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final boolean T() {
        return false;
    }

    @Override // l3.InterfaceC1750f
    public final void W(C1740E c1740e, int i7) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void Y0(String str, Bundle bundle, N n10) {
        throw new AssertionError();
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // l3.InterfaceC1750f
    public final void b() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void c() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void c0(InterfaceC1748d interfaceC1748d) {
        K k10 = (K) this.c.get();
        if (k10 == null || interfaceC1748d == null) {
            return;
        }
        k10.f18526f.unregister(interfaceC1748d);
        Binder.getCallingPid();
        Binder.getCallingUid();
        synchronized (k10.d) {
        }
    }

    @Override // l3.InterfaceC1750f
    public final X e() {
        K k10 = (K) this.c.get();
        if (k10 == null) {
            return null;
        }
        X x8 = k10.f18527g;
        C1741F c1741f = k10.f18529i;
        if (x8 == null) {
            return x8;
        }
        long j3 = x8.f18552m;
        long jA = -1;
        if (j3 == -1) {
            return x8;
        }
        int i7 = x8.l;
        if (i7 != 3 && i7 != 4 && i7 != 5) {
            return x8;
        }
        if (x8.f18557s <= 0) {
            return x8;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j10 = ((long) (x8.o * (jElapsedRealtime - r6))) + j3;
        if (c1741f != null && c1741f.l.containsKey("android.media.metadata.DURATION")) {
            jA = c1741f.a("android.media.metadata.DURATION");
        }
        long j11 = (jA < 0 || j10 <= jA) ? j10 < 0 ? 0L : j10 : jA;
        ArrayList arrayList = new ArrayList();
        AbstractCollection abstractCollection = x8.f18558t;
        if (abstractCollection != null) {
            arrayList.addAll(abstractCollection);
        }
        return new X(x8.l, j11, x8.f18553n, x8.o, x8.f18554p, x8.f18555q, x8.f18556r, jElapsedRealtime, arrayList, x8.f18559u, x8.f18560v);
    }

    @Override // l3.InterfaceC1750f
    public final void g() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final String getPackageName() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void h(int i7) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final List h0() {
        return null;
    }

    @Override // l3.InterfaceC1750f
    public final void h1(InterfaceC1748d interfaceC1748d) {
        K k10 = (K) this.c.get();
        if (k10 == null || interfaceC1748d == null) {
            return;
        }
        k10.f18526f.register(interfaceC1748d, new T("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
        synchronized (k10.d) {
        }
    }

    @Override // l3.InterfaceC1750f
    public final int i() {
        K k10 = (K) this.c.get();
        if (k10 != null) {
            return k10.f18530j;
        }
        return -1;
    }

    @Override // l3.InterfaceC1750f
    public final long j() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void j0(int i7, int i10) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void k(long j3) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void l(float f10) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final Bundle m() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final boolean m1(KeyEvent keyEvent) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void n0(C1740E c1740e) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void next() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void o0() {
        throw new AssertionError();
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        if (i7 == 1598968902) {
            parcel2.getClass();
            parcel2.writeString("android.support.v4.media.session.IMediaSession");
            return true;
        }
        InterfaceC1748d interfaceC1748d = null;
        InterfaceC1748d interfaceC1748d2 = null;
        switch (i7) {
            case 1:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Y0(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? N.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean zM1 = m1(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(zM1 ? 1 : 0);
                return true;
            case 3:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1748d)) {
                        C1747c c1747c = new C1747c();
                        c1747c.c = strongBinder;
                        interfaceC1748d2 = c1747c;
                    } else {
                        interfaceC1748d2 = (InterfaceC1748d) iInterfaceQueryLocalInterface;
                    }
                }
                h1(interfaceC1748d2);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof InterfaceC1748d)) {
                        C1747c c1747c2 = new C1747c();
                        c1747c2.c = strongBinder2;
                        interfaceC1748d = c1747c2;
                    } else {
                        interfaceC1748d = (InterfaceC1748d) iInterfaceQueryLocalInterface2;
                    }
                }
                c0(interfaceC1748d);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean zL = L();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(zL ? 1 : 0);
                return true;
            case 6:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String packageName = getPackageName();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeString(packageName);
                return true;
            case 7:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String strZ = z();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeString(strZ);
                return true;
            case 8:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                PendingIntent pendingIntentM = M();
                parcel2.getClass();
                parcel2.writeNoException();
                if (pendingIntentM != null) {
                    parcel2.writeInt(1);
                    pendingIntentM.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 9:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                long j3 = j();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeLong(j3);
                return true;
            case 10:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                V vI0 = I0();
                parcel2.getClass();
                parcel2.writeNoException();
                if (vI0 != null) {
                    parcel2.writeInt(1);
                    vI0.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 11:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int i11 = parcel.readInt();
                int i12 = parcel.readInt();
                parcel.readString();
                j0(i11, i12);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 12:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int i13 = parcel.readInt();
                int i14 = parcel.readInt();
                parcel.readString();
                G0(i13, i14);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 13:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                g();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                z0(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 15:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                E0(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                M0(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 17:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                D0(parcel.readLong());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 18:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                b();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 19:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                stop();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 20:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                next();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 21:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                previous();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 22:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                J0();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                o0();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 24:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                k(parcel.readLong());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 25:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                s0(parcel.readInt() != 0 ? Y.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 26:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                r(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 27:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                C1741F c1741fV0 = v0();
                parcel2.getClass();
                parcel2.writeNoException();
                if (c1741fV0 != null) {
                    parcel2.writeInt(1);
                    parcel2.writeBundle(c1741fV0.l);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 28:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                X xE = e();
                parcel2.getClass();
                parcel2.writeNoException();
                if (xE != null) {
                    parcel2.writeInt(1);
                    xE.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 29:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                List listH0 = h0();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeTypedList(listH0);
                return true;
            case 30:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                CharSequence charSequenceQ0 = q0();
                parcel2.getClass();
                parcel2.writeNoException();
                if (charSequenceQ0 != null) {
                    parcel2.writeInt(1);
                    TextUtils.writeToParcel(charSequenceQ0, parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 31:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Bundle bundleM = m();
                parcel2.getClass();
                parcel2.writeNoException();
                if (bundleM != null) {
                    parcel2.writeInt(1);
                    bundleM.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 32:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int iQ = Q();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(iQ);
                return true;
            case 33:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                c();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 34:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                w0(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 35:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                R(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 36:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                G(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 37:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int i15 = i();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(i15);
                return true;
            case 38:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case 39:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                h(parcel.readInt());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 40:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel.readInt();
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 41:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                p0(parcel.readInt() != 0 ? C1740E.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 42:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                W(parcel.readInt() != 0 ? C1740E.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 43:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                n0(parcel.readInt() != 0 ? C1740E.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 44:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                P(parcel.readInt());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 45:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean zT = T();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(zT ? 1 : 0);
                return true;
            case 46:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                A(parcel.readInt() != 0);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 47:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                int iN = N();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(iN);
                return true;
            case 48:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Q0(parcel.readInt());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 49:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                l(parcel.readFloat());
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            case 50:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Bundle bundleX0 = x0();
                parcel2.getClass();
                parcel2.writeNoException();
                if (bundleX0 != null) {
                    parcel2.writeInt(1);
                    bundleX0.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 51:
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                y0(parcel.readInt() != 0 ? Y.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i7, parcel, parcel2, i10);
        }
    }

    @Override // l3.InterfaceC1750f
    public final void p0(C1740E c1740e) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void previous() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final CharSequence q0() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void r(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void s0(Y y10) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void stop() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final C1741F v0() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void w0(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final Bundle x0() {
        Bundle bundle;
        K k10 = (K) this.c.get();
        if (k10 == null || (bundle = k10.f18525e) == null) {
            return null;
        }
        return new Bundle(bundle);
    }

    @Override // l3.InterfaceC1750f
    public final void y0(Y y10, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final String z() {
        throw new AssertionError();
    }

    @Override // l3.InterfaceC1750f
    public final void z0(String str, Bundle bundle) {
        throw new AssertionError();
    }
}
