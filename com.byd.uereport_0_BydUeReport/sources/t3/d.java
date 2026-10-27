package t3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.CallbackMsg;

/* JADX INFO: compiled from: IRemoteModelCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: IRemoteModelCallback.java */
    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: t3.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRemoteModelCallback.java */
        public static class C0125a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static d f8029a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f8030b;

            public C0125a(IBinder iBinder) {
                this.f8030b = iBinder;
            }

            @Override // t3.d
            public void O(CallbackMsg callbackMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (callbackMsg != null) {
                        parcelObtain.writeInt(1);
                        callbackMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f8030b.transact(3, parcelObtain, null, 1) || a.B0() == null) {
                        return;
                    }
                    a.B0().O(callbackMsg);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f8030b;
            }

            @Override // t3.d
            public Reply h0(CallbackMsg callbackMsg) throws RemoteException {
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
                    if (!this.f8030b.transact(2, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().h0(callbackMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // t3.d
            public void i0(Reply reply) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (reply != null) {
                        parcelObtain.writeInt(1);
                        reply.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f8030b.transact(4, parcelObtain, null, 1) || a.B0() == null) {
                        return;
                    }
                    a.B0().i0(reply);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static d A0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.mvcadapter.IRemoteModelCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0125a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        public static d B0() {
            return C0125a.f8029a;
        }
    }

    void O(CallbackMsg callbackMsg) throws RemoteException;

    Reply h0(CallbackMsg callbackMsg) throws RemoteException;

    void i0(Reply reply) throws RemoteException;
}
