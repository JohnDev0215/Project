package t2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceInterface.java */
    public static abstract class a extends Binder implements c {

        /* JADX INFO: renamed from: t2.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAvmServiceInterface.java */
        public static class C0064a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static c f5041a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f5042b;

            public C0064a(IBinder iBinder) {
                this.f5042b = iBinder;
            }

            @Override // t2.c
            public int E(t2.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f5042b.transact(9, parcelObtain, parcelObtain2, 0) && a.G() != null) {
                        return a.G().E(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5042b;
            }

            @Override // t2.c
            public void l(CmdBean cmdBean) throws RemoteException {
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
                    if (this.f5042b.transact(6, parcelObtain, parcelObtain2, 0) || a.G() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.G().l(cmdBean);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // t2.c
            public int n(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f5042b.transact(7, parcelObtain, parcelObtain2, 0) && a.G() != null) {
                        return a.G().n(bVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // t2.c
            public int u(d dVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f5042b.transact(2, parcelObtain, parcelObtain2, 0) && a.G() != null) {
                        return a.G().u(dVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // t2.c
            public int z() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    if (!this.f5042b.transact(1, parcelObtain, parcelObtain2, 0) && a.G() != null) {
                        return a.G().z();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static c F(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ts.avm.IAvmServiceInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0064a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }

        public static c G() {
            return C0064a.f5041a;
        }
    }

    int E(t2.a aVar) throws RemoteException;

    void l(CmdBean cmdBean) throws RemoteException;

    int n(b bVar) throws RemoteException;

    int u(d dVar) throws RemoteException;

    int z() throws RemoteException;
}
