package k3;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import d5.C1077F;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h3.C1350a;
import io.sentry.C1458d0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: k3.n0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC1658n0 extends Binder implements InterfaceC1651k {
    public final WeakReference c;

    public BinderC1658n0(C1628b0 c1628b0) {
        attachInterface(this, "androidx.media3.session.IMediaController");
        this.c = new WeakReference(c1628b0);
    }

    public static InterfaceC1651k n1(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("androidx.media3.session.IMediaController");
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof InterfaceC1651k)) {
            return (InterfaceC1651k) iInterfaceQueryLocalInterface;
        }
        C1649j c1649j = new C1649j();
        c1649j.c = iBinder;
        return c1649j;
    }

    @Override // k3.InterfaceC1651k
    public final void N0(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            o1(new C1654l0(e2.S.c(bundle)));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for Commands", e10);
        }
    }

    @Override // k3.InterfaceC1651k
    public final void S0(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            o1(new G2.a(B1.b(bundle), 19));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for SessionPositionInfo", e10);
        }
    }

    @Override // k3.InterfaceC1651k
    public final void T0(int i7, Bundle bundle, boolean z6) {
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(o1.d, z6);
        bundle2.putBoolean(o1.f18025e, true);
        d1(i7, bundle, bundle2);
    }

    @Override // k3.InterfaceC1651k
    public final void a() {
        o1(new C1350a(19));
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // k3.InterfaceC1651k
    public final void d1(int i7, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        try {
            int iP1 = p1();
            if (iP1 == -1) {
                return;
            }
            try {
                o1(new C1458d0(q1.r(iP1, bundle), 15, new o1(bundle2.getBoolean(o1.d, false), bundle2.getBoolean(o1.f18025e, false))));
            } catch (RuntimeException e10) {
                AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for BundlingExclusions", e10);
            }
        } catch (RuntimeException e11) {
            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for PlayerInfo", e11);
        }
    }

    @Override // k3.InterfaceC1651k
    public final void f(int i7) {
        o1(new C1350a(18));
    }

    @Override // k3.InterfaceC1651k
    public final void i1(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            q1(i7, C1.a(bundle));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for SessionResult", e10);
        }
    }

    @Override // k3.InterfaceC1651k
    public final void n(int i7, List list) {
        if (list == null) {
            return;
        }
        try {
            int iP1 = p1();
            if (iP1 == -1) {
                return;
            }
            C1077F c1077fU = d5.I.u();
            for (int i10 = 0; i10 < list.size(); i10++) {
                Bundle bundle = (Bundle) list.get(i10);
                bundle.getClass();
                c1077fU.a(C1627b.a(iP1, bundle));
            }
            o1(new C1652k0(i7, c1077fU.h(), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for CommandButton", e10);
        }
    }

    public final void o1(InterfaceC1656m0 interfaceC1656m0) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            C1628b0 c1628b0 = (C1628b0) this.c.get();
            if (c1628b0 == null) {
                return;
            }
            AbstractC1343A.K(c1628b0.f17904a.f18137e, new A2.m(c1628b0, 21, interfaceC1656m0));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        int i11;
        String str;
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface("androidx.media3.session.IMediaController");
        }
        if (i7 == 1598968902) {
            parcel2.writeString("androidx.media3.session.IMediaController");
            return true;
        }
        if (i7 == 4001) {
            parcel.readInt();
            String string = parcel.readString();
            i11 = parcel.readInt();
            Bundle bundle = (Bundle) n1.a(parcel, Bundle.CREATOR);
            if (TextUtils.isEmpty(string)) {
                AbstractC1346c.z("MediaControllerStub", "onChildrenChanged(): Ignoring empty parentId");
            } else if (i11 < 0) {
                str = "onChildrenChanged(): Ignoring negative itemCount: ";
                AbstractC1347d.p(str, i11, "MediaControllerStub");
            } else if (bundle == null) {
                o1(new C1350a(20));
            } else {
                try {
                    C1660o0.a(bundle);
                    o1(new C1350a(20));
                } catch (RuntimeException e10) {
                    AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for LibraryParams", e10);
                }
            }
        } else if (i7 != 4002) {
            switch (i7) {
                case 3001:
                    r0(parcel.readInt(), (Bundle) n1.a(parcel, Bundle.CREATOR));
                    break;
                case 3002:
                    i1(parcel.readInt(), (Bundle) n1.a(parcel, Bundle.CREATOR));
                    break;
                case 3003:
                    t(parcel.readInt(), (Bundle) n1.a(parcel, Bundle.CREATOR));
                    break;
                case 3004:
                    n(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                    break;
                case 3005:
                    int i12 = parcel.readInt();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    Bundle bundle2 = (Bundle) n1.a(parcel, creator);
                    Bundle bundle3 = (Bundle) n1.a(parcel, creator);
                    if (bundle2 == null || bundle3 == null) {
                        AbstractC1346c.z("MediaControllerStub", "Ignoring custom command with null args.");
                    } else {
                        try {
                            o1(new C1616J(i12, y1.a(bundle2), bundle3));
                        } catch (RuntimeException e11) {
                            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for SessionCommand", e11);
                        }
                    }
                    break;
                case 3006:
                    parcel.readInt();
                    a();
                    break;
                case 3007:
                    T0(parcel.readInt(), (Bundle) n1.a(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                    break;
                case 3008:
                    S0(parcel.readInt(), (Bundle) n1.a(parcel, Bundle.CREATOR));
                    break;
                case 3009:
                    N0(parcel.readInt(), (Bundle) n1.a(parcel, Bundle.CREATOR));
                    break;
                case 3010:
                    parcel.readInt();
                    Parcelable.Creator creator2 = Bundle.CREATOR;
                    Bundle bundle4 = (Bundle) n1.a(parcel, creator2);
                    Bundle bundle5 = (Bundle) n1.a(parcel, creator2);
                    if (bundle4 != null && bundle5 != null) {
                        try {
                            try {
                                o1(new C1458d0(z1.a(bundle4), 16, e2.S.c(bundle5)));
                            } catch (RuntimeException e12) {
                                AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for Commands", e12);
                            }
                        } catch (RuntimeException e13) {
                            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for SessionCommands", e13);
                        }
                    }
                    break;
                case 3011:
                    f(parcel.readInt());
                    break;
                case 3012:
                    parcel.readInt();
                    if (((Bundle) n1.a(parcel, Bundle.CREATOR)) == null) {
                        AbstractC1346c.z("MediaControllerStub", "Ignoring null Bundle for extras");
                    } else {
                        o1(new C1350a(22));
                    }
                    break;
                case 3013:
                    int i13 = parcel.readInt();
                    Parcelable.Creator creator3 = Bundle.CREATOR;
                    d1(i13, (Bundle) n1.a(parcel, creator3), (Bundle) n1.a(parcel, creator3));
                    break;
                case 3014:
                    parcel.readInt();
                    if (((PendingIntent) n1.a(parcel, PendingIntent.CREATOR)) == null) {
                        AbstractC1346c.z("MediaControllerStub", "Ignoring null session activity intent");
                    } else {
                        o1(new C1350a(21));
                    }
                    break;
                case 3015:
                    parcel.readInt();
                    try {
                        A1.a((Bundle) n1.a(parcel, Bundle.CREATOR));
                        o1(new C1350a(17));
                    } catch (RuntimeException e14) {
                        AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for SessionError", e14);
                    }
                    break;
                case 3016:
                    int i14 = parcel.readInt();
                    ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(Bundle.CREATOR);
                    if (arrayListCreateTypedArrayList != null) {
                        try {
                            int iP1 = p1();
                            if (iP1 != -1) {
                                C1077F c1077fU = d5.I.u();
                                for (int i15 = 0; i15 < arrayListCreateTypedArrayList.size(); i15++) {
                                    Bundle bundle6 = (Bundle) arrayListCreateTypedArrayList.get(i15);
                                    bundle6.getClass();
                                    c1077fU.a(C1627b.a(iP1, bundle6));
                                }
                                o1(new C1652k0(i14, c1077fU.h(), 0));
                            }
                        } catch (RuntimeException e15) {
                            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for CommandButton", e15);
                        }
                    }
                    break;
                default:
                    return super.onTransact(i7, parcel, parcel2, i10);
            }
        } else {
            parcel.readInt();
            String string2 = parcel.readString();
            i11 = parcel.readInt();
            Bundle bundle7 = (Bundle) n1.a(parcel, Bundle.CREATOR);
            if (TextUtils.isEmpty(string2)) {
                AbstractC1346c.z("MediaControllerStub", "onSearchResultChanged(): Ignoring empty query");
            } else if (i11 < 0) {
                str = "onSearchResultChanged(): Ignoring negative itemCount: ";
                AbstractC1347d.p(str, i11, "MediaControllerStub");
            } else if (bundle7 == null) {
                o1(new C1350a(20));
            } else {
                try {
                    C1660o0.a(bundle7);
                    o1(new C1350a(20));
                } catch (RuntimeException e16) {
                    AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for LibraryParams", e16);
                }
            }
        }
        return true;
    }

    public final int p1() {
        E1 e12;
        C1628b0 c1628b0 = (C1628b0) this.c.get();
        if (c1628b0 == null || (e12 = c1628b0.l) == null) {
            return -1;
        }
        return e12.f17758a.r();
    }

    public final void q1(int i7, Object obj) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            C1628b0 c1628b0 = (C1628b0) this.c.get();
            if (c1628b0 == null) {
                return;
            }
            c1628b0.f17905b.v(i7, obj);
            c1628b0.f17904a.X0(new M.g(i7, 1, c1628b0));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // k3.InterfaceC1651k
    public final void r0(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            o1(new G2.a(C1647i.a(bundle), 20));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaControllerStub", "Malformed Bundle for ConnectionResult. Disconnected from the session.", e10);
            a();
        }
    }

    @Override // k3.InterfaceC1651k
    public final void t(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            q1(i7, r.a(bundle));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaControllerStub", "Ignoring malformed Bundle for LibraryResult", e10);
        }
    }
}
