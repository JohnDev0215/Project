package w6;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceInterface.java */
    public static abstract class a extends Binder implements c {

        /* JADX INFO: renamed from: w6.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAvmServiceInterface.java */
        public static class C0139a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static c f8754a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f8755b;

            public C0139a(IBinder iBinder) {
                this.f8755b = iBinder;
            }

            @Override // w6.c
            public int H(w6.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f8755b.transact(9, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().H(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // w6.c
            public void L(CmdBean cmdBean) throws RemoteException {
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
                    if (this.f8755b.transact(6, parcelObtain, parcelObtain2, 0) || a.B0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.B0().L(cmdBean);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // w6.c
            public int P(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f8755b.transact(7, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().P(bVar);
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
                return this.f8755b;
            }

            @Override // w6.c
            public int u(d dVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f8755b.transact(2, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().u(dVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // w6.c
            public int z(String str, String str2, String str3, int i8, int i9, int i10, boolean z7) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i8);
                    parcelObtain.writeInt(i9);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(z7 ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.f8755b.transact(8, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        int iZ = a.B0().z(str, str2, str3, i8, i9, i10, z7);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iZ;
                    }
                    parcelObtain2.readException();
                    int i11 = parcelObtain2.readInt();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i11;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }
        }

        public static c A0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ts.avm.IAvmServiceInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0139a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }

        public static c B0() {
            return C0139a.f8754a;
        }
    }

    int H(w6.a aVar) throws RemoteException;

    void L(CmdBean cmdBean) throws RemoteException;

    int P(b bVar) throws RemoteException;

    int u(d dVar) throws RemoteException;

    int z(String str, String str2, String str3, int i8, int i9, int i10, boolean z7) throws RemoteException;
}
