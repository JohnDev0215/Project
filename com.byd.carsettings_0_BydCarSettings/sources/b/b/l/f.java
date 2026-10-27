package b.b.l;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import b.b.l.e;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.Map;

/* JADX INFO: compiled from: IRemoteModelService.java */
/* JADX INFO: loaded from: classes2.dex */
public interface f extends IInterface {

    /* JADX INFO: compiled from: IRemoteModelService.java */
    public static abstract class a extends Binder implements f {

        /* JADX INFO: renamed from: b.b.l.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRemoteModelService.java */
        public static class C0084a implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static f f1984a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f1985b;

            public C0084a(IBinder iBinder) {
                this.f1985b = iBinder;
            }

            @Override // b.b.l.f
            public Reply D(InvokeMsg invokeMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    if (invokeMsg != null) {
                        parcelObtain.writeInt(1);
                        invokeMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f1985b.transact(6, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().D(invokeMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.l.f
            public Reply M(GetMsg getMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    if (getMsg != null) {
                        parcelObtain.writeInt(1);
                        getMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f1985b.transact(5, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().M(getMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.l.f
            public void N(e eVar, String str, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    parcelObtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (this.f1985b.transact(2, parcelObtain, parcelObtain2, 0) || a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.e0().N(eVar, str, i, j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.l.f
            public void O(e eVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    parcelObtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    if (this.f1985b.transact(3, parcelObtain, parcelObtain2, 0) || a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.e0().O(eVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.l.f
            public int W() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    if (!this.f1985b.transact(1, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().W();
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
                return this.f1985b;
            }

            @Override // b.b.l.f
            public void x(InvokeMsg invokeMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    if (invokeMsg != null) {
                        parcelObtain.writeInt(1);
                        invokeMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f1985b.transact(7, parcelObtain, null, 1) || a.e0() == null) {
                        return;
                    }
                    a.e0().x(invokeMsg);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.mvcadapter.IRemoteModelService");
        }

        public static f d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.mvcadapter.IRemoteModelService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof f)) ? new C0084a(iBinder) : (f) iInterfaceQueryLocalInterface;
        }

        public static f e0() {
            return C0084a.f1984a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.byd.mvcadapter.IRemoteModelService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    int iW = W();
                    parcel2.writeNoException();
                    parcel2.writeInt(iW);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    N(e.a.d0(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    O(e.a.d0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    ComplexData complexDataL = l(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? ComplexData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (complexDataL != null) {
                        parcel2.writeInt(1);
                        complexDataL.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyM = M(parcel.readInt() != 0 ? GetMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyM != null) {
                        parcel2.writeInt(1);
                        replyM.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyD = D(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyD != null) {
                        parcel2.writeInt(1);
                        replyD.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    x(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    i(parcel.readInt() != 0 ? GCMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Reply D(InvokeMsg invokeMsg) throws RemoteException;

    Reply M(GetMsg getMsg) throws RemoteException;

    void N(e eVar, String str, int i, long j) throws RemoteException;

    void O(e eVar) throws RemoteException;

    int W() throws RemoteException;

    void i(GCMsg gCMsg) throws RemoteException;

    ComplexData l(Map map, ComplexData complexData) throws RemoteException;

    void x(InvokeMsg invokeMsg) throws RemoteException;
}
