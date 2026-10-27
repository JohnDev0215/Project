package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface jl extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceInterface.java */
    public static abstract class a extends Binder implements jl {

        /* JADX INFO: renamed from: jl$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAvmServiceInterface.java */
        public static class C0013a implements jl {
            public static jl a;
            public IBinder b;

            public C0013a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // defpackage.jl
            public void b(CmdBean cmdBean) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    if (cmdBean != null) {
                        parcelObtain.writeInt(1);
                        cmdBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.b.transact(6, parcelObtain, parcelObtain2, 0) || a.m() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.m().b(cmdBean);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.jl
            public int d(il ilVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(ilVar != null ? ilVar.asBinder() : null);
                    if (!this.b.transact(7, parcelObtain, parcelObtain2, 0) && a.m() != null) {
                        return a.m().d(ilVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.jl
            public int g(kl klVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(klVar != null ? klVar.asBinder() : null);
                    if (!this.b.transact(2, parcelObtain, parcelObtain2, 0) && a.m() != null) {
                        return a.m().g(klVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.jl
            public int k(hl hlVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(hlVar != null ? hlVar.asBinder() : null);
                    if (!this.b.transact(9, parcelObtain, parcelObtain2, 0) && a.m() != null) {
                        return a.m().k(hlVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static jl l(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ts.avm.IAvmServiceInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof jl)) ? new C0013a(iBinder) : (jl) iInterfaceQueryLocalInterface;
        }

        public static jl m() {
            return C0013a.a;
        }
    }

    void b(CmdBean cmdBean) throws RemoteException;

    int d(il ilVar) throws RemoteException;

    int g(kl klVar) throws RemoteException;

    int k(hl hlVar) throws RemoteException;
}
