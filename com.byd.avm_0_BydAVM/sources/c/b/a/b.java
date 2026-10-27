package c.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceInterface.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: c.b.a.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAvmServiceInterface.java */
        private static class C0056a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f2116a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f2117b;

            C0056a(IBinder iBinder) {
                this.f2117b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2117b;
            }

            @Override // c.b.a.b
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
                    if (this.f2117b.transact(6, parcelObtain, parcelObtain2, 0) || a.l() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.l().b(cmdBean);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.b.a.b
            public int d(c.b.a.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f2117b.transact(7, parcelObtain, parcelObtain2, 0) && a.l() != null) {
                        return a.l().d(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.b.a.b
            public int g(c cVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (!this.f2117b.transact(2, parcelObtain, parcelObtain2, 0) && a.l() != null) {
                        return a.l().g(cVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.b.a.b
            public int i() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    if (!this.f2117b.transact(1, parcelObtain, parcelObtain2, 0) && a.l() != null) {
                        return a.l().i();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b k(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ts.avm.IAvmServiceInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0056a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b l() {
            return C0056a.f2116a;
        }
    }

    void b(CmdBean cmdBean) throws RemoteException;

    int d(c.b.a.a aVar) throws RemoteException;

    int g(c cVar) throws RemoteException;

    int i() throws RemoteException;
}
