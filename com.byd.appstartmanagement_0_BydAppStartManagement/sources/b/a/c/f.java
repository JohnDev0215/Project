package b.a.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import b.a.c.e;
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

        /* JADX INFO: renamed from: b.a.c.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRemoteModelService.java */
        public static class C0022a implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static f f231a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f232b;

            public C0022a(IBinder iBinder) {
                this.f232b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f232b;
            }

            @Override // b.a.c.f
            public void k(InvokeMsg invokeMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    if (invokeMsg != null) {
                        parcelObtain.writeInt(1);
                        invokeMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f232b.transact(7, parcelObtain, null, 1) || a.C() == null) {
                        return;
                    }
                    a.C().k(invokeMsg);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.f
            public Reply n(InvokeMsg invokeMsg) throws RemoteException {
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
                    if (!this.f232b.transact(6, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().n(invokeMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.f
            public Reply p(GetMsg getMsg) throws RemoteException {
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
                    if (!this.f232b.transact(5, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().p(getMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.f
            public void q(e eVar, String str, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    parcelObtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (this.f232b.transact(2, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().q(eVar, str, i, j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.f
            public void r(e eVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    parcelObtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    if (this.f232b.transact(3, parcelObtain, parcelObtain2, 0) || a.C() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.C().r(eVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.f
            public int v() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelService");
                    if (!this.f232b.transact(1, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().v();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.mvcadapter.IRemoteModelService");
        }

        public static f B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.mvcadapter.IRemoteModelService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof f)) ? new C0022a(iBinder) : (f) iInterfaceQueryLocalInterface;
        }

        public static f C() {
            return C0022a.f231a;
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
                    int iV = v();
                    parcel2.writeNoException();
                    parcel2.writeInt(iV);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    q(e.a.B(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    r(e.a.B(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    ComplexData complexDataF = f(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? ComplexData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (complexDataF != null) {
                        parcel2.writeInt(1);
                        complexDataF.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyP = p(parcel.readInt() != 0 ? GetMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyP != null) {
                        parcel2.writeInt(1);
                        replyP.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyN = n(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyN != null) {
                        parcel2.writeInt(1);
                        replyN.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    k(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    e(parcel.readInt() != 0 ? GCMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void e(GCMsg gCMsg) throws RemoteException;

    ComplexData f(Map map, ComplexData complexData) throws RemoteException;

    void k(InvokeMsg invokeMsg) throws RemoteException;

    Reply n(InvokeMsg invokeMsg) throws RemoteException;

    Reply p(GetMsg getMsg) throws RemoteException;

    void q(e eVar, String str, int i, long j) throws RemoteException;

    void r(e eVar) throws RemoteException;

    int v() throws RemoteException;
}
