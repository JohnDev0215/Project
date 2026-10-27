package p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: loaded from: classes.dex */
public final class rl1 extends Binder implements x21 {
    public static final /* synthetic */ int c = 0;
    public final /* synthetic */ MultiInstanceInvalidationService b;

    public rl1(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.b = multiInstanceInvalidationService;
        attachInterface(this, "androidx.room.IMultiInstanceInvalidationService");
    }

    @Override // p.x21
    public final int G(v21 v21Var, String str) {
        v03.j(v21Var, "callback");
        int i = 0;
        if (str == null) {
            return 0;
        }
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.b;
        synchronized (multiInstanceInvalidationService.k) {
            try {
                int i2 = multiInstanceInvalidationService.c + 1;
                multiInstanceInvalidationService.c = i2;
                if (multiInstanceInvalidationService.k.register(v21Var, Integer.valueOf(i2))) {
                    multiInstanceInvalidationService.e.put(Integer.valueOf(i2), str);
                    i = i2;
                } else {
                    multiInstanceInvalidationService.c--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    @Override // p.x21
    public final void W(int i, String[] strArr) {
        v03.j(strArr, "tables");
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.b;
        synchronized (multiInstanceInvalidationService.k) {
            try {
                String str = (String) multiInstanceInvalidationService.e.get(Integer.valueOf(i));
                if (str == null) {
                    return;
                }
                int iBeginBroadcast = multiInstanceInvalidationService.k.beginBroadcast();
                for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                    try {
                        Object broadcastCookie = multiInstanceInvalidationService.k.getBroadcastCookie(i2);
                        v03.g(broadcastCookie, "null cannot be cast to non-null type kotlin.Int");
                        int iIntValue = ((Integer) broadcastCookie).intValue();
                        String str2 = (String) multiInstanceInvalidationService.e.get(Integer.valueOf(iIntValue));
                        if (i != iIntValue && v03.b(str, str2)) {
                            try {
                                ((v21) multiInstanceInvalidationService.k.getBroadcastItem(i2)).e(strArr);
                            } catch (RemoteException unused) {
                            }
                        }
                    } finally {
                        multiInstanceInvalidationService.k.finishBroadcast();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationService");
        }
        if (i == 1598968902) {
            parcel2.writeString("androidx.room.IMultiInstanceInvalidationService");
            return true;
        }
        v21 v21Var = null;
        v21 v21Var2 = null;
        if (i == 1) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof v21)) {
                    u21 u21Var = new u21();
                    u21Var.b = strongBinder;
                    v21Var = u21Var;
                } else {
                    v21Var = (v21) iInterfaceQueryLocalInterface;
                }
            }
            int iG = G(v21Var, parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(iG);
        } else if (i == 2) {
            IBinder strongBinder2 = parcel.readStrongBinder();
            if (strongBinder2 != null) {
                IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
                if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof v21)) {
                    u21 u21Var2 = new u21();
                    u21Var2.b = strongBinder2;
                    v21Var2 = u21Var2;
                } else {
                    v21Var2 = (v21) iInterfaceQueryLocalInterface2;
                }
            }
            int i3 = parcel.readInt();
            v03.j(v21Var2, "callback");
            MultiInstanceInvalidationService multiInstanceInvalidationService = this.b;
            synchronized (multiInstanceInvalidationService.k) {
                multiInstanceInvalidationService.k.unregister(v21Var2);
            }
            parcel2.writeNoException();
        } else {
            if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            W(parcel.readInt(), parcel.createStringArray());
        }
        return true;
    }
}
