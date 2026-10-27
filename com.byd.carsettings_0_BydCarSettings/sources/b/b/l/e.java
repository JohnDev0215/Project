package b.b.l;

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

        /* JADX INFO: renamed from: b.b.l.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRemoteModelCallback.java */
        public static class C0083a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static e f1982a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f1983b;

            public C0083a(IBinder iBinder) {
                this.f1983b = iBinder;
            }

            @Override // b.b.l.e
            public void G(CallbackMsg callbackMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (callbackMsg != null) {
                        parcelObtain.writeInt(1);
                        callbackMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f1983b.transact(3, parcelObtain, null, 1) || a.e0() == null) {
                        return;
                    }
                    a.e0().G(callbackMsg);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.l.e
            public Reply Q(CallbackMsg callbackMsg) throws RemoteException {
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
                    if (!this.f1983b.transact(2, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().Q(callbackMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.l.e
            public void R(Reply reply) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (reply != null) {
                        parcelObtain.writeInt(1);
                        reply.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f1983b.transact(4, parcelObtain, null, 1) || a.e0() == null) {
                        return;
                    }
                    a.e0().R(reply);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1983b;
            }
        }

        public a() {
            attachInterface(this, "com.byd.mvcadapter.IRemoteModelCallback");
        }

        public static e d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.mvcadapter.IRemoteModelCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof e)) ? new C0083a(iBinder) : (e) iInterfaceQueryLocalInterface;
        }

        public static e e0() {
            return C0083a.f1982a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                ComplexData complexDataL = l(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? ComplexData.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (complexDataL != null) {
                    parcel2.writeInt(1);
                    complexDataL.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                Reply replyQ = Q(parcel.readInt() != 0 ? CallbackMsg.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (replyQ != null) {
                    parcel2.writeInt(1);
                    replyQ.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                G(parcel.readInt() != 0 ? CallbackMsg.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                R(parcel.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelCallback");
                i(parcel.readInt() != 0 ? GCMsg.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.byd.mvcadapter.IRemoteModelCallback");
            return true;
        }
    }

    void G(CallbackMsg callbackMsg) throws RemoteException;

    Reply Q(CallbackMsg callbackMsg) throws RemoteException;

    void R(Reply reply) throws RemoteException;

    void i(GCMsg gCMsg) throws RemoteException;

    ComplexData l(Map map, ComplexData complexData) throws RemoteException;
}
