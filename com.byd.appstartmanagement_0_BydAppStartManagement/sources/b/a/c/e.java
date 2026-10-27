package b.a.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.CallbackMsg;
import com.byd.mvcadapter.rpc.GCMsg;
import java.util.Map;

/* JADX INFO: compiled from: IRemoteModelCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface e extends IInterface {

    /* JADX INFO: compiled from: IRemoteModelCallback.java */
    public static abstract class a extends Binder implements e {

        /* JADX INFO: renamed from: b.a.c.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRemoteModelCallback.java */
        public static class C0021a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static e f229a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f230b;

            public C0021a(IBinder iBinder) {
                this.f230b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f230b;
            }

            @Override // b.a.c.e
            public void o(CallbackMsg callbackMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (callbackMsg != null) {
                        parcelObtain.writeInt(1);
                        callbackMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f230b.transact(3, parcelObtain, null, 1) || a.C() == null) {
                        return;
                    }
                    a.C().o(callbackMsg);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.e
            public Reply t(CallbackMsg callbackMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (callbackMsg != null) {
                        parcelObtain.writeInt(1);
                        callbackMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f230b.transact(2, parcelObtain, parcelObtain2, 0) && a.C() != null) {
                        return a.C().t(callbackMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.c.e
            public void u(Reply reply) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (reply != null) {
                        parcelObtain.writeInt(1);
                        reply.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f230b.transact(4, parcelObtain, null, 1) || a.C() == null) {
                        return;
                    }
                    a.C().u(reply);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.byd.mvcadapter.IRemoteModelCallback");
        }

        public static e B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.mvcadapter.IRemoteModelCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof e)) ? new C0021a(iBinder) : (e) iInterfaceQueryLocalInterface;
        }

        public static e C() {
            return C0021a.f229a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                ComplexData complexDataF = f(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? ComplexData.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (complexDataF != null) {
                    parcel2.writeInt(1);
                    complexDataF.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                Reply replyT = t(parcel.readInt() != 0 ? CallbackMsg.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (replyT != null) {
                    parcel2.writeInt(1);
                    replyT.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                o(parcel.readInt() != 0 ? CallbackMsg.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                u(parcel.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                e(parcel.readInt() != 0 ? GCMsg.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.byd.mvcadapter.IRemoteModelCallback");
            return true;
        }
    }

    void e(GCMsg gCMsg) throws RemoteException;

    ComplexData f(Map map, ComplexData complexData) throws RemoteException;

    void o(CallbackMsg callbackMsg) throws RemoteException;

    Reply t(CallbackMsg callbackMsg) throws RemoteException;

    void u(Reply reply) throws RemoteException;
}
